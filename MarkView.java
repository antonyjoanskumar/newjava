package sxcce;
import java.sql.*;
import dd.DBConnectionManager;
import java.util.*;
import java.text.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MarkView
	{
	String repin[] = new String[25];
	String report[][] = new String[25][10000];
		int mark[] = new int[500];
		int mark1[] = new int[500];
		int tp[] = new int[500];
		int ap[] = new int[500];
		int tothrs=0;
		String vdate="";

		String date="";
		String fdate="";
		String tdate="";
		String fday="";
		String fmonth="";
		String fyear="";
		String tday="";
		String tmonth="";
		String tyear="";
		String attendance[][]= new String[500][15];
String dispdate[] = new String[500];

		int hours[] = new int[500];
		int thours[] = new int[500];
		int rollno[] = new int[500];
		int rollno1[] = new int[500];
		int attper[] = new int[500];
		String regno[] = new String[500];
		int attper1[] = new int[500];
		String quer="";
		String quer1="";
		String exdt="";
		String ddt="";
		String name[]=new String[500];
		int count,count1,scount;
		int examno=0;
		int logcount=0;
		String logdate[]=new String[500];
		String logstaffid[]=new String[500];
		String logstaffname[]=new String[500];

		String parentname[]=new String[500];
		String parenthouse[]= new String[500];
		String parenttown[]=new String[500];
		String parentdistrict[]=new String[500];
		String parentstate[]=new String[500];
		String parentpincode[] = new String[500];
String error="..."; 
String sql="";
String sql1="";


public String ViewMarkcopo(String subjectcode,String exam,int order,int dno,String examyear,int semester)
		    {

	DBConnectionManager connMgr=null; Connection con =null;
	try
	{
                       connMgr = DBConnectionManager.getInstance(); 
                       con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;
					quer="select st.rollno,ISNULL(ltrim(rtrim(rno.registerno)),0) as regno,st.name,ISNULL(ltrim(rtrim(mark)),-3) as mark from "
+" (SELECT stud.rollno as rollno, stud.name as name  FROM stud,student WHERE stud.rollno=student.rollno and student.active=1 "
+" and stud.department="+dno+" and stud.year="+((semester+1)/2) +"and stud.academicyear='"+examyear+"') st "
+" left outer join (SELECT registerno.rollno,registerno FROM registerno) as rno on st.rollno = rno.rollno "
+" left outer join (SELECT mark.rollno,mark FROM mark,examyear where examyear.examno=mark.examno "
+" and examyear.semester="+semester+" and examyear.exam='"+exam+"' and examyear.year='"+examyear+"' "
+" And mark.subjectcode='"+subjectcode+"') as m on st.rollno = m.rollno order BY st.rollno";

					count=0;

quer1="select theoryorpractical from subjecthandled,subjectidentify,syllabus where "
+" subjecthandled.id=subjectidentify.id and subjecthandled.id=syllabus.id and subjecthandled.subcode=subjectcode and mainorsub='M' "
+" and dno="+dno+" and subcode='"+subjectcode+"' and academicyear='"+examyear+"' and semester="+semester;

int ajk=0;

rs = stmt.executeQuery(quer1);
				if(rs.next())
						{
								ajk=rs.getInt(1);
						}
					rs.close();

if(ajk==4)
quer="select st.rollno,ISNULL(ltrim(rtrim(rno.registerno)),0) as regno,st.name,ISNULL(ltrim(rtrim(mark)),-3) as mark from "
+" (SELECT stud.rollno as rollno, stud.name as name  FROM stud,student,subjectelective WHERE stud.rollno=student.rollno and student.active=1 "
+" and stud.department="+dno+" and stud.year="+((semester+1)/2) +"and stud.academicyear='"+examyear+"'  and subjectelective.rollno=student.rollno and subjectelective.subcode='"+subjectcode+"') st "
+" left outer join (SELECT registerno.rollno,registerno FROM registerno) as rno on st.rollno = rno.rollno "
+" left outer join (SELECT mark.rollno,mark FROM mark,examyear where examyear.examno=mark.examno "
+" and examyear.semester="+semester+" and examyear.exam='"+exam+"' and examyear.year='"+examyear+"' "
+" And mark.subjectcode='"+subjectcode+"') as m on st.rollno = m.rollno order BY st.rollno";

					rs = stmt.executeQuery(quer);
					while(rs.next())
						{
						rollno[count]=rs.getInt(1);
						regno[count]=rs.getString(2);
						name[count]=rs.getString(3);
						mark[count]=rs.getInt(4);
						count++;
						}
					rs.close();
					
		

	try
	{
quer1="select examno from examyear where dno="+dno+" and semester="+semester+" and exam='"+exam+"' and year='"+examyear+"' ";
					rs = stmt.executeQuery(quer1);
					examno=0;
					if(rs.next())
						{
						examno=rs.getInt(1);
						}
					rs.close();
}catch(Exception e){}

			
	try
	{
quer1="SELECT substring(('000'+ltrim(rtrim(str(day(date))))),len('000'+ltrim(rtrim(str(day(date)))))-1,2)+'/'+substring(('000'+ltrim(rtrim(str(month(date))))),len('000'+ltrim(rtrim(str(month(date)))))-1,2)+'/'+ltrim(rtrim(str(year(date)))),max(marklogin.staffid),max(name) FROM staff,marklogin, examyear WHERE examyear.examno=marklogin.examno "+
"and staff.staffid=marklogin.staffid and examyear.semester="+semester+" and examyear.exam='"+exam+"' and marklogin.subjectcode='"+subjectcode+
"' and examyear.year='"+examyear+"' and examyear.dno="+dno+" group by date,marklogin.staffid ORDER BY date desc";

					rs = stmt.executeQuery(quer1);
					logcount=0;
					while(rs.next())
						{
						logdate[logcount]=rs.getString(1);
						logstaffid[logcount]=rs.getString(2);
						logstaffname[logcount]=rs.getString(3);
						logcount++;	
						}
					rs.close();
}catch(Exception e){}


					stmt.close();
}catch(Exception e){error=(e.toString()+count+quer);}
finally{connMgr.freeConnection("xavier",con);
return error; 
}
}


public String ViewMark(String subjectcode,String exam,int order,int dno,String examyear,int semester)
		    {

int yr=(semester+1)/2;
	DBConnectionManager connMgr=null; Connection con =null;
	try
	{
                       connMgr = DBConnectionManager.getInstance(); 
                       con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;
					count1=0;
					quer="SELECT stud.rollno, stud.name, max(mark.mark),10 FROM stud,student,mark, examyear WHERE stud.rollno=mark.rollno and stud.rollno=student.rollno and student.active=1 And examyear.examno=mark.examno And stud.department="+dno+" and examyear.semester="+semester+" and examyear.exam='"+exam+"' and examyear.year=stud.academicyear and examyear.year='"+examyear+"' And mark.subjectcode='"+subjectcode+"' group by stud.rollno,stud.name order BY stud.rollno";
					if(order==1)
					quer="SELECT stud.rollno, stud.name, max(mark.mark),10 FROM stud,student,mark, examyear WHERE stud.rollno=mark.rollno and stud.rollno=student.rollno and student.active=1 And examyear.examno=mark.examno And stud.department="+dno+" and examyear.semester="+semester+" and examyear.exam='"+exam+"' and examyear.year=stud.academicyear and examyear.year='"+examyear+"' And mark.subjectcode='"+subjectcode+"' group by stud.rollno,stud.name order BY stud.rollno";
//					quer="SELECT stud.rollno, stud.name, max(mark.mark), max(attper.percentage) FROM stud,student,mark, examyear,attper WHERE stud.rollno=mark.rollno and stud.rollno=attper.rollno and stud.rollno=student.rollno and student.active=1 And examyear.examno=mark.examno And stud.department="+dno+" and examyear.semester="+semester+" and attper.semester="+semester+" and examyear.exam='"+exam+"' and examyear.year=stud.academicyear and examyear.year='"+examyear+"' And mark.subjectcode='"+subjectcode+"' group by stud.rollno,stud.name order BY stud.rollno";
//					quer="SELECT stud.rollno, stud.name, max(mark.mark), max(attper.percentage) FROM stud,student,mark, examyear,attper WHERE stud.rollno=mark.rollno and stud.rollno=attper.rollno and stud.rollno=student.rollno and student.active=1 And examyear.examno=mark.examno And stud.department="+dno+" and examyear.semester="+semester+" and examyear.exam='"+exam+"' and examyear.year=stud.academicyear and examyear.year='"+examyear+"' And mark.subjectcode='"+subjectcode+"' group by stud.rollno,stud.name order BY mark.rollno";
					else if(order==2)
					quer="SELECT stud.rollno, stud.name, mark.mark,10 FROM stud,student, mark, examyear WHERE stud.rollno=mark.rollno and stud.rollno=student.rollno and student.active=1 And examyear.examno=mark.examno And stud.department="+dno+" and examyear.semester="+semester+" and examyear.exam='"+exam+"' and examyear.year=stud.academicyear and examyear.year='"+examyear+"' And mark.subjectcode='"+subjectcode+"'ORDER BY mark.mark,stud.rollno";
					else if(order==3)
					quer="SELECT stud.rollno, stud.name, mark.mark,10 FROM stud,student, mark, examyear WHERE stud.rollno=mark.rollno and stud.rollno=student.rollno and student.active=1 And examyear.examno=mark.examno And stud.department="+dno+" and examyear.semester="+semester+" and examyear.exam='"+exam+"' and examyear.year=stud.academicyear and examyear.year='"+examyear+"' And mark.subjectcode='"+subjectcode+"'ORDER BY mark.mark DESC,stud.rollno";
					else if(order==4)
					quer="select st.rollno,st.name,st.mark,ISNULL(ltrim(rtrim(rno.registerno)),0) as regno from (SELECT stud.rollno as rollno, stud.name as name , max(mark.mark) as mark FROM stud,student,mark, examyear WHERE stud.rollno=mark.rollno and "
					+" stud.rollno=student.rollno and student.active=1 And examyear.examno=mark.examno And stud.department="+dno+" and examyear.semester="+semester+" and examyear.exam='"+exam+"' and examyear.year=stud.academicyear and examyear.year='"+examyear+"' And mark.subjectcode='"+subjectcode+"' group by stud.rollno,stud.name) st "
				    +" left outer join (SELECT registerno.rollno,registerno FROM registerno) as rno on st.rollno = rno.rollno order BY regno,st.rollno";
					
					else if(order==5)
/*
quer="select p.rollno,name,mark,regno,p.examno,ISNULL(att.hours,0) as hours,ISNULL(att.thours,0) as thours from (select st.rollno,st.name,st.mark,ISNULL(ltrim(rtrim(rno.registerno)),0) as regno,st.examno from "
+"(SELECT stud.rollno as rollno, stud.name as name , max(mark.mark) as mark, max(mark.examno) as examno,max(subjectcode) as subjectcode "
+"FROM stud,student,mark, examyear WHERE stud.rollno=mark.rollno and "
+"stud.rollno=student.rollno and student.active=1 And examyear.examno=mark.examno And stud.department="+dno+" and examyear.semester="+semester+" and examyear.exam='"+exam+"' and examyear.year=stud.academicyear "
+"and examyear.year='"+examyear+"' And mark.subjectcode='"+subjectcode+"' group by stud.rollno,stud.name) st "
+"left outer join (SELECT registerno.rollno,registerno FROM registerno) as rno on st.rollno = rno.rollno) p "
+"left outer join (SELECT rollno,hours,thours,examno FROM markatt where subjectcode='"+subjectcode+"') as att on att.rollno = p.rollno and att.examno=p.examno  order BY regno,p.rollno ";

*/
quer="select st.rollno,name,isnull(mark,0) mark,regno,isnull(p.examno,0),ISNULL(att.hours,0) as hours,ISNULL(att.thours,0) as thours from "
+" (select stud.rollno as rollno,stud.name as name from stud,student WHERE stud.rollno=student.rollno and student.active=1 and stud.department="+dno+" and stud.academicyear='"+examyear+"' and year="+yr+") st "
+" left outer join (SELECT registerno.rollno,registerno  as regno FROM registerno) as rno on st.rollno = rno.rollno "
+" left outer join (select mark.rollno as rollno,mark.examno,mark.mark from mark, examyear WHERE examyear.examno=mark.examno and subjectcode='"+subjectcode+"' and examyear.semester="+semester+" and examyear.exam='"+exam+"' and examyear.year='"+examyear+"')  as p on p.rollno = rno.rollno "
+" left outer join (SELECT rollno,hours,thours,examno FROM markatt where subjectcode='"+subjectcode+"') as att on att.rollno = p.rollno and att.examno=p.examno  order BY regno,p.rollno ";

					else
					quer="SELECT stud.rollno, stud.name, max(mark.mark),10 FROM stud,student,mark, examyear WHERE stud.rollno=mark.rollno and stud.rollno=student.rollno and student.active=1 And examyear.examno=mark.examno And stud.department="+dno+" and examyear.semester="+semester+" and examyear.exam='"+exam+"' and examyear.year=stud.academicyear and examyear.year='"+examyear+"' And mark.subjectcode='"+subjectcode+"' group by stud.rollno,stud.name order BY stud.rollno";
					rs = stmt.executeQuery(quer);
					while(rs.next())
						{
						if(order==11)
						{

						rollno1[count1]=rs.getInt(1);
						name[count]=rs.getString(2);
						regno[count]=rs.getString(3);
						mark1[count1]=rs.getInt(3);
			            attper1[count1]=rs.getInt(4);
						}
						else if(order==4)
							{	
						rollno[count1]=rs.getInt(1);
						name[count1]=rs.getString(2);
						mark[count1]=rs.getInt(3);
			            regno[count1]=rs.getString(4);
			           }

						else if(order==5)
							{	
						rollno[count1]=rs.getInt(1);
						name[count1]=rs.getString(2);
						mark[count1]=rs.getInt(3);
			            regno[count1]=rs.getString(4);
						hours[count1]=rs.getInt(5);
						hours[count1]=rs.getInt(6);
						thours[count1]=rs.getInt(7);
			           }
							else
							{	
						rollno[count1]=rs.getInt(1);
						name[count1]=rs.getString(2);
						mark[count1]=rs.getInt(3);
			            attper[count1]=rs.getInt(4);
			           }
						count1++;	
						}
					rs.close();
					count=count1;
					
					if(count1==0)
					{

					quer="select rollno,name from student where rollno in (select rollno from subjectelective where subcode='"+subjectcode+"' "
+"and id in (select id from subjectidentify where academicyear='"+examyear+"' and dno="+dno+" and semester="+semester+")) and active=1 order by rollno";
					rs = stmt.executeQuery(quer);
					while(rs.next())
					{
	
						rollno[count1]=rs.getInt(1);
						name[count1]=rs.getString(2);
						count1++;	
					}
					}

					if(count1==0)
					{
					quer="select stud.rollno,stud.name from stud,student where stud.rollno=student.rollno and "
                    +" academicyear='"+examyear+"' and department="+dno+" and year=(("+semester+"+1)/2) and active=1 order by stud.rollno";

					rs = stmt.executeQuery(quer);
					while(rs.next())
					{
						rollno[count1]=rs.getInt(1);
						name[count1]=rs.getString(2);
						count1++;	
					}
					}
				
//					if(order==1)
					if(order==11)
					{
					count=0;
//					rs = stmt.executeQuery("SELECT stud.rollno, stud.name FROM stud,student WHERE stud.rollno=student.rollno And student.active=1 and stud.department="+dno+" and stud.year="+(semester+1)/2+" and stud.academicyear='"+examyear+"' ORDER BY stud.rollno");

					rs = stmt.executeQuery("SELECT stud.rollno, stud.name,initial,parenthouse,parenttown,parentdistrict,parentstate,parentpincode FROM stud,student WHERE stud.rollno=student.rollno And student.active=1 and stud.department="+dno+" and stud.year="+(semester+1)/2+" and stud.academicyear='"+examyear+"' ORDER BY stud.rollno");
					while(rs.next())
						{
						rollno[count]=rs.getInt(1);
						name[count]=rs.getString(2);
						parentname[count]=rs.getString(3);
						parenthouse[count]=rs.getString(4);
						parenttown[count]=rs.getString(5);
						parentdistrict[count]=rs.getString(6);
						parentstate[count]=rs.getString(7);
						parentpincode[count]=rs.getString(8);
						
						mark[count]=-1;
						attper[count]=0;
						
						for (int j=0; j<count1;j++)
						{
							if(rollno[count]==rollno1[j])
							{		mark[count]=mark1[j];
							        attper[count]=attper1[j];
							        j=count1;
							       }

					    }
						count++;	
						}
					rs.close();
				}
				
quer1="SELECT substring(('000'+ltrim(rtrim(str(day(date))))),len('000'+ltrim(rtrim(str(day(date)))))-1,2)+'/'+substring(('000'+ltrim(rtrim(str(month(date))))),len('000'+ltrim(rtrim(str(month(date)))))-1,2)+'/'+ltrim(rtrim(str(year(date)))),max(marklogin.staffid),max(name) FROM staff,marklogin, examyear WHERE examyear.examno=marklogin.examno "+
"and staff.staffid=marklogin.staffid and examyear.semester="+semester+" and examyear.exam='"+exam+"' and marklogin.subjectcode='"+subjectcode+
"' and examyear.year='"+examyear+"' and examyear.dno="+dno+" group by date,marklogin.staffid ORDER BY date desc";
					rs = stmt.executeQuery(quer1);
					logcount=0;
					while(rs.next())
						{
						logdate[logcount]=rs.getString(1);
						logstaffid[logcount]=rs.getString(2);
						logstaffname[logcount]=rs.getString(3);
						logcount++;	
						}
					rs.close();


					stmt.close();
}catch(Exception e){error=(e.toString()+count+quer);}
finally{connMgr.freeConnection("xavier",con);
return error; 
}
}

public String ViewMarka(String subjectcode,String exam,int order,int dno,String examyear,int semester)
		    {

	DBConnectionManager connMgr=null; Connection con =null;
	try
	{
                       connMgr = DBConnectionManager.getInstance(); 
                       con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;
					quer="select st.rollno,ISNULL(ltrim(rtrim(rno.registerno)),0) as regno,st.name,ISNULL(ltrim(rtrim(mark)),-3) as mark,ISNULL(ltrim(rtrim(marka)),-3) as marka from "
+" (SELECT stud.rollno as rollno, stud.name as name  FROM stud,student WHERE stud.rollno=student.rollno and student.active=1 "
+" and stud.department="+dno+" and stud.year="+((semester+1)/2) +"and stud.academicyear='"+examyear+"') st "
+" left outer join (SELECT registerno.rollno,registerno FROM registerno) as rno on st.rollno = rno.rollno "
+" left outer join (SELECT marka.rollno,mark,marka FROM marka,examyear where examyear.examno=marka.examno "
+" and examyear.semester="+semester+" and examyear.exam='"+exam+"' and examyear.year='"+examyear+"' "
+" And marka.subjectcode='"+subjectcode+"') as m on st.rollno = m.rollno order BY st.rollno";
					count=0;

					if(order==1)
					quer="select st.rollno,ISNULL(ltrim(rtrim(rno.registerno)),0) as regno,st.name,ISNULL(ltrim(rtrim(mark)),-3) as mark,ISNULL(ltrim(rtrim(marka)),-3) as marka from "
+" (SELECT stud.rollno as rollno, stud.name as name  FROM stud,student WHERE stud.rollno=student.rollno and student.active=1 "
+" and stud.department="+dno+" and stud.year="+((semester+1)/2) +"and stud.academicyear='"+examyear+"') st "
+" left outer join (SELECT registerno.rollno,registerno FROM registerno) as rno on st.rollno = rno.rollno "
+" left outer join (SELECT marka.rollno,mark,marka FROM marka,examyear where examyear.examno=marka.examno "
+" and examyear.semester="+semester+" and examyear.exam='"+exam+"' and examyear.year='"+examyear+"' "
+" And marka.subjectcode='"+subjectcode+"') as m on st.rollno = m.rollno order BY st.rollno";

quer1="select theoryorpractical from subjecthandled,subjectidentify,syllabus where "
+" subjecthandled.id=subjectidentify.id and subjecthandled.id=syllabus.id and subjecthandled.subcode=subjectcode and mainorsub='M' "
+" and subcode='"+subjectcode+"' and academicyear='"+examyear+"' and semester="+semester;

int ajk=0;

rs = stmt.executeQuery(quer1);
				if(rs.next())
						{
								ajk=rs.getInt(1);
						}
rs.close();

if(ajk==4)
quer="select st.rollno,ISNULL(ltrim(rtrim(rno.registerno)),0) as regno,st.name,ISNULL(ltrim(rtrim(mark)),-3) as mark,ISNULL(ltrim(rtrim(marka)),-3) as marka from "
+" (SELECT stud.rollno as rollno, stud.name as name  FROM stud,student,subjectelective WHERE stud.rollno=student.rollno and student.active=1 "
+" and stud.department="+dno+" and stud.year="+((semester+1)/2) +"and stud.academicyear='"+examyear+"'  and subjectelective.rollno=student.rollno and subjectelective.subcode='"+subjectcode+"') st "
+" left outer join (SELECT registerno.rollno,registerno FROM registerno) as rno on st.rollno = rno.rollno "
+" left outer join (SELECT marka.rollno,mark,marka FROM marka,examyear where examyear.examno=marka.examno "
+" and examyear.semester="+semester+" and examyear.exam='"+exam+"' and examyear.year='"+examyear+"' "
+" And marka.subjectcode='"+subjectcode+"') as m on st.rollno = m.rollno order BY st.rollno";

					rs = stmt.executeQuery(quer);
					while(rs.next())
						{
							for(int i=1;i<=5;i++)
							{
								report[i-1][count]=rs.getString(i);
							}
							count++;
						}
					rs.close();
					
			
	try
	{
quer1="SELECT substring(('000'+ltrim(rtrim(str(day(date))))),len('000'+ltrim(rtrim(str(day(date)))))-1,2)+'/'+substring(('000'+ltrim(rtrim(str(month(date))))),len('000'+ltrim(rtrim(str(month(date)))))-1,2)+'/'+ltrim(rtrim(str(year(date)))),max(marklogin.staffid),max(name) FROM staff,marklogin, examyear WHERE examyear.examno=marklogin.examno "+
"and staff.staffid=marklogin.staffid and examyear.semester="+semester+" and examyear.exam='"+exam+"' and marklogin.subjectcode='"+subjectcode+
"' and examyear.year='"+examyear+"' and examyear.dno="+dno+" group by date,marklogin.staffid ORDER BY date desc";

					rs = stmt.executeQuery(quer1);
					logcount=0;
					while(rs.next())
						{
						logdate[logcount]=rs.getString(1);
						logstaffid[logcount]=rs.getString(2);
						logstaffname[logcount]=rs.getString(3);
						logcount++;	
						}
					rs.close();
}catch(Exception e){}


					stmt.close();
}catch(Exception e){error=(e.toString()+count+quer);}
finally{connMgr.freeConnection("xavier",con);
return error; 
}
}


public String ViewMarkUniv(String subjectcode,String exam1,String exam2,String test1,int order,int dno,String examyear,int semester)
		    {
String ie=exam1;
String it="";
String ct="";
String at="";
String exam=exam1;
it=exam2;
at=test1;
ct=exam1;
/*
if(exam.trim().equals("Internal Exam I")) { it="Improvement Test I"; ct="Class Test 1"; at="Assignment 1";}
if(exam.trim().equals("Internal Exam II")) { it="Improvement Test II"; ct="Class Test 2"; at="Assignment 2";}
if(exam.trim().equals("Internal Exam III")) { it="Improvement Test III"; ct="Class Test 3"; at="Assignment 3";}
*/

String ord="";

if(order==1) ord=" order by st.rollno";
if(order==2) ord=" order BY m1";
if(order==3) ord=" order BY m1 desc";

	DBConnectionManager connMgr=null; Connection con =null;
	try
	{
                       connMgr = DBConnectionManager.getInstance(); 
                       con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;


quer="select st.rollno,ISNULL(ltrim(rtrim(rno.registerno)),0) as regno,st.name, "
+" ISNULL(ltrim(rtrim(m.mark)),-3) as m,ISNULL(ltrim(rtrim(mi.mark)),-3) as mi,ISNULL(ltrim(rtrim(mc.mark)),-3) as mc, "
+" ISNULL(ltrim(rtrim(ma.mark)),-3) as mc,FLOOR(round(((m.mark*.7)+(mc.mark*.2)+(ma.mark*.1)),-3)) as m1 from "
+" (SELECT stud.rollno as rollno, stud.name as name  FROM stud,student WHERE stud.rollno=student.rollno and student.active=1 "
+"  and stud.department="+dno+"and stud.year="+((semester+1)/2) +" and stud.academicyear='"+examyear+"') st "
+"  left outer join (SELECT registerno.rollno,registerno FROM registerno) as rno on st.rollno = rno.rollno "
+"  left outer join (SELECT m.rollno,m.mark FROM marka m,examyear where examyear.examno=m.examno "
+"  and examyear.semester="+semester+"  and examyear.exam='"+ie+"' and examyear.year='"+examyear+"' "
+"  And m.subjectcode='"+subjectcode+"') as m on st.rollno = m.rollno "
+"  left outer join (SELECT mi.rollno,mi.mark FROM marka mi,examyear where examyear.examno=mi.examno "
+"  and examyear.semester="+semester+"  and examyear.exam='"+it+"' and examyear.year='"+examyear+"' "
+"  And mi.subjectcode='"+subjectcode+"') as mi on st.rollno = mi.rollno "
+"  left outer join (SELECT mc.rollno,mark FROM marka mc,examyear where examyear.examno=mc.examno "
+"  and examyear.semester="+semester+"  and examyear.exam='"+ct+"' and examyear.year='"+examyear+"' "
+"  And mc.subjectcode='"+subjectcode+"') as mc on st.rollno = mc.rollno "
+"  left outer join (SELECT ma.rollno,mark FROM marka ma,examyear where examyear.examno=ma.examno "
+"  and examyear.semester="+semester+"  and examyear.exam='"+at+"' and examyear.year='"+examyear+"' "
+"  And ma.subjectcode='"+subjectcode+"') as ma on st.rollno = ma.rollno "
+ord;

count=0;


quer1="select theoryorpractical from subjecthandled,subjectidentify,syllabus where "
+" subjecthandled.id=subjectidentify.id and subjecthandled.id=syllabus.id and subjecthandled.subcode=subjectcode and mainorsub='M' "
+" and dno="+dno+" and subcode='"+subjectcode+"' and academicyear='"+examyear+"' and semester="+semester;

int ajk=0;

rs = stmt.executeQuery(quer1);
				if(rs.next())
						{
								ajk=rs.getInt(1);
						}
rs.close();

if(ajk==4)

quer="select st.rollno,ISNULL(ltrim(rtrim(rno.registerno)),0) as regno,st.name, "
+" ISNULL(ltrim(rtrim(m.mark)),-3) as m,ISNULL(ltrim(rtrim(mi.mark)),-3) as mi,ISNULL(ltrim(rtrim(mc.mark)),-3) as mc, "
+" ISNULL(ltrim(rtrim(ma.mark)),-3) as mc,FLOOR(round(((m.mark*.7)+(mc.mark*.2)+(ma.mark*.1)),-3)) as m1 from "
+" (SELECT stud.rollno as rollno, stud.name as name  FROM stud,student,subjectelective WHERE stud.rollno=student.rollno and student.active=1 "
+" and stud.department="+dno+" and stud.year="+((semester+1)/2) +" and stud.academicyear='"+examyear+"'  and subjectelective.rollno=student.rollno and subjectelective.subcode='"+subjectcode+"') st "
+"  left outer join (SELECT registerno.rollno,registerno FROM registerno) as rno on st.rollno = rno.rollno "
+"  left outer join (SELECT m.rollno,m.mark FROM marka m,examyear where examyear.examno=m.examno "
+"  and examyear.semester="+semester+"  and examyear.exam='"+ie+"' and examyear.year='"+examyear+"' "
+"  And m.subjectcode='"+subjectcode+"') as m on st.rollno = m.rollno "
+"  left outer join (SELECT mi.rollno,mi.mark FROM marka mi,examyear where examyear.examno=mi.examno "
+"  and examyear.semester="+semester+"  and examyear.exam='"+it+"' and examyear.year='"+examyear+"' "
+"  And mi.subjectcode='"+subjectcode+"') as mi on st.rollno = mi.rollno "
+"  left outer join (SELECT mc.rollno,mark FROM marka mc,examyear where examyear.examno=mc.examno "
+"  and examyear.semester="+semester+"  and examyear.exam='"+ct+"' and examyear.year='"+examyear+"' "
+"  And mc.subjectcode='"+subjectcode+"') as mc on st.rollno = mc.rollno "
+"  left outer join (SELECT ma.rollno,mark FROM marka ma,examyear where examyear.examno=ma.examno "
+"  and examyear.semester="+semester+"  and examyear.exam='"+at+"' and examyear.year='"+examyear+"' "
+"  And ma.subjectcode='"+subjectcode+"') as ma on st.rollno = ma.rollno "
+ord;

					rs = stmt.executeQuery(quer);
					while(rs.next())
						{
							for(int i=1;i<=8;i++)
							{
								report[i-1][count]=rs.getString(i);
							}
							count++;
						}
					rs.close();
					
			

					stmt.close();
}catch(Exception e){error=(e.toString()+count+quer);}
finally{connMgr.freeConnection("xavier",con);
return error; 
}
}

/*

public String ViewMarkUniv(String subjectcode,String exam,int order,int dno,String examyear,int semester)
		    {
String ie=exam;
String it="";
String ct="";
String at="";
if(exam.trim().equals("Internal Exam I")) { it="Improvement Test I"; ct="Class Test 1"; at="Assignment 1";}
if(exam.trim().equals("Internal Exam II")) { it="Improvement Test II"; ct="Class Test 2"; at="Assignment 2";}
if(exam.trim().equals("Internal Exam III")) { it="Improvement Test III"; ct="Class Test 3"; at="Assignment 3";}
String ord="";
if(order==1) ord=" order by st.rollno";
if(order==2) ord=" order BY m1";
if(order==3) ord=" order BY m1 desc";

	DBConnectionManager connMgr=null; Connection con =null;
	try
	{
                       connMgr = DBConnectionManager.getInstance(); 
                       con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;


quer="select st.rollno,ISNULL(ltrim(rtrim(rno.registerno)),0) as regno,st.name, "
+" ISNULL(ltrim(rtrim(m.mark)),-3) as m,ISNULL(ltrim(rtrim(mi.mark)),-3) as mi,ISNULL(ltrim(rtrim(mc.mark)),-3) as mc, "
+" ISNULL(ltrim(rtrim(ma.mark)),-3) as mc,FLOOR(round(((m.mark*.7)+(mc.mark*.2)+(ma.mark*.1)),-3)) as m1 from "
+" (SELECT stud.rollno as rollno, stud.name as name  FROM stud,student WHERE stud.rollno=student.rollno and student.active=1 "
+"  and stud.department="+dno+"and stud.year="+((semester+1)/2) +" and stud.academicyear='"+examyear+"') st "
+"  left outer join (SELECT registerno.rollno,registerno FROM registerno) as rno on st.rollno = rno.rollno "
+"  left outer join (SELECT m.rollno,m.mark FROM marka m,examyear where examyear.examno=m.examno "
+"  and examyear.semester="+semester+"  and examyear.exam='"+ie+"' and examyear.year='"+examyear+"' "
+"  And m.subjectcode='"+subjectcode+"') as m on st.rollno = m.rollno "
+"  left outer join (SELECT mi.rollno,mi.mark FROM marka mi,examyear where examyear.examno=mi.examno "
+"  and examyear.semester="+semester+"  and examyear.exam='"+it+"' and examyear.year='"+examyear+"' "
+"  And mi.subjectcode='"+subjectcode+"') as mi on st.rollno = mi.rollno "
+"  left outer join (SELECT mc.rollno,mark FROM marka mc,examyear where examyear.examno=mc.examno "
+"  and examyear.semester="+semester+"  and examyear.exam='"+ct+"' and examyear.year='"+examyear+"' "
+"  And mc.subjectcode='"+subjectcode+"') as mc on st.rollno = mc.rollno "
+"  left outer join (SELECT ma.rollno,mark FROM marka ma,examyear where examyear.examno=ma.examno "
+"  and examyear.semester="+semester+"  and examyear.exam='"+at+"' and examyear.year='"+examyear+"' "
+"  And ma.subjectcode='"+subjectcode+"') as ma on st.rollno = ma.rollno "
+ord;

count=0;


quer1="select theoryorpractical from subjecthandled,subjectidentify,syllabus where "
+" subjecthandled.id=subjectidentify.id and subjecthandled.id=syllabus.id and subjecthandled.subcode=subjectcode and mainorsub='M' "
+" and subcode='"+subjectcode+"' and academicyear='"+examyear+"' and semester="+semester;

int ajk=0;

rs = stmt.executeQuery(quer1);
				if(rs.next())
						{
								ajk=rs.getInt(1);
						}
rs.close();

if(ajk==4)

quer="select st.rollno,ISNULL(ltrim(rtrim(rno.registerno)),0) as regno,st.name, "
+" ISNULL(ltrim(rtrim(m.mark)),-3) as m,ISNULL(ltrim(rtrim(mi.mark)),-3) as mi,ISNULL(ltrim(rtrim(mc.mark)),-3) as mc, "
+" ISNULL(ltrim(rtrim(ma.mark)),-3) as mc,FLOOR(round(((m.mark*.7)+(mc.mark*.2)+(ma.mark*.1)),-3)) as m1 from "
+" (SELECT stud.rollno as rollno, stud.name as name  FROM stud,student,subjectelective WHERE stud.rollno=student.rollno and student.active=1 "
+" and stud.department="+dno+" and stud.year="+((semester+1)/2) +" and stud.academicyear='"+examyear+"'  and subjectelective.rollno=student.rollno and subjectelective.subcode='"+subjectcode+"') st "
+"  left outer join (SELECT registerno.rollno,registerno FROM registerno) as rno on st.rollno = rno.rollno "
+"  left outer join (SELECT m.rollno,m.mark FROM marka m,examyear where examyear.examno=m.examno "
+"  and examyear.semester="+semester+"  and examyear.exam='"+ie+"' and examyear.year='"+examyear+"' "
+"  And m.subjectcode='"+subjectcode+"') as m on st.rollno = m.rollno "
+"  left outer join (SELECT mi.rollno,mi.mark FROM marka mi,examyear where examyear.examno=mi.examno "
+"  and examyear.semester="+semester+"  and examyear.exam='"+it+"' and examyear.year='"+examyear+"' "
+"  And mi.subjectcode='"+subjectcode+"') as mi on st.rollno = mi.rollno "
+"  left outer join (SELECT mc.rollno,mark FROM marka mc,examyear where examyear.examno=mc.examno "
+"  and examyear.semester="+semester+"  and examyear.exam='"+ct+"' and examyear.year='"+examyear+"' "
+"  And mc.subjectcode='"+subjectcode+"') as mc on st.rollno = mc.rollno "
+"  left outer join (SELECT ma.rollno,mark FROM marka ma,examyear where examyear.examno=ma.examno "
+"  and examyear.semester="+semester+"  and examyear.exam='"+at+"' and examyear.year='"+examyear+"' "
+"  And ma.subjectcode='"+subjectcode+"') as ma on st.rollno = ma.rollno "
+ord;

					rs = stmt.executeQuery(quer);
					while(rs.next())
						{
							for(int i=1;i<=8;i++)
							{
								report[i-1][count]=rs.getString(i);
							}
							count++;
						}
					rs.close();
					
			

					stmt.close();
}catch(Exception e){error=(e.toString()+count+quer);}
finally{connMgr.freeConnection("xavier",con);
return error; 
}
}

*/

public String ViewMarkAtt(int dno,int year,int semester,String fd,String td,String academicyear, String exam)
    {
    count=0;
    tothrs=0;
    examno=0;
    scount=0;
	error="";
	String q1="";
	String q2="";
	DBConnectionManager connMgr=null; Connection con =null;
	try
	{
                    connMgr = DBConnectionManager.getInstance(); 
                    con = connMgr.getConnection("xavier");
   			    	Statement stmt = con.createStatement();
					ResultSet rs;
		try
		{

					quer="select count(*) from attendance.dbo.log b where date between  '"+fd+"' and '"+td+"' and "
						+" id in(select id from subjectidentify where semester="+semester+" and dno="+dno+" and academicyear='"+academicyear+"')";
//						error=error+quer;
					rs = stmt.executeQuery(quer);
					if(rs.next()) tothrs=rs.getInt(1);					
					rs.close();
		}catch(Exception e){error+=e.toString();}
		try
		{

					quer="select examno from examyear where dno="+dno+" and semester="+semester+" and year='"+academicyear+"' and exam='"+exam+"'";
					rs = stmt.executeQuery(quer);
					if(rs.next()) examno=rs.getInt(1);					
					rs.close();
		}catch(Exception e){error+=e.toString();}
		try
		{
					quer="select subjectcode from subjectidentify si,syllabus s where si.id=s.id and dno="+dno+" and semester="+semester+" and academicyear='"+academicyear+"' and theoryorpractical in (1,4,5) order by subjectcode";
					rs = stmt.executeQuery(quer);
					while(rs.next())
					{
						 repin[scount]=rs.getString(1);
						 scount++;					
					}
					rs.close();
		}catch(Exception e){error+=e.toString();}
				q1="select st.rollno,ISNULL(ltrim(rtrim(rno.registerno)),0) as regno,st.name,t4,0 ";
				q2=" from (SELECT stud.rollno as rollno, stud.name as name  FROM stud,student WHERE stud.rollno=student.rollno and student.active=1 "
+" and stud.department="+dno+" and stud.year="+year+" and stud.academicyear='"+academicyear+"') st "
+" left outer join (SELECT registerno.rollno,registerno FROM registerno) as rno on st.rollno = rno.rollno "
+" left outer join (select count(*) t4,max(rollno) t5 from attendance.dbo.attendance a,attendance.dbo.log b where a.date=b.date  "
+" and a.date between  '"+fd+"' and '"+td+"' and hours like '%'+ltrim(rtrim(str(period)))+'%' "
+" and id in (select id from subjectidentify where semester="+semester+" and dno="+dno+" and academicyear='"+academicyear+"') group by rollno) q2 on st.rollno=q2.t5 ";


for (int i=1;i<=scount;i++)
{
					q1+=" ,ISNULL(ltrim(rtrim(m"+i+".mark)),-3) as m"+i;
					q2+="  left outer join (SELECT m"+i+".rollno,m"+i+".mark FROM marka m"+i+" where examno="+examno+" and subjectcode='"+repin[i-1]+"') as m"+i+" on st.rollno = m"+i+".rollno ";
}
	quer=q1+q2+" order by st.rollno";			
		
                   
						error=error+quer;
					rs = stmt.executeQuery(quer);
					while(rs.next())
						{
							for(int i=1;i<=(scount+5);i++)
							{
								report[i-1][count]=rs.getString(i);
							}
							count++;
						}
					rs.close();

					stmt.close();
				
}catch(Exception e){error+=(e.toString()+count+quer);}
finally{connMgr.freeConnection("xavier",con);}

int count1=count;
fdate=fd;
tdate=td;					
error+=getOD(count1,dno,year,academicyear);	
for(int i=0;i<count1;i++)
  {
	report[4][i]=mark1[i]+"";
  }
  
count=count1;
return error; 
}



public String ViewTotAtt(int dno,int year,int semester,String fd,String td,String academicyear)
    {
    count=0;
    tothrs=0;
	DBConnectionManager connMgr=null; Connection con =null;
	try
	{
                    connMgr = DBConnectionManager.getInstance(); 
                    con = connMgr.getConnection("xavier");
   			    	Statement stmt = con.createStatement();
					ResultSet rs;
					quer="select count(*) from attendance.dbo.log b where date between  '"+fd+"' and '"+td+"' and "
						+" id in(select id from subjectidentify where semester="+semester+" and dno="+dno+" and academicyear='"+academicyear+"')";
						error=error+quer;
					rs = stmt.executeQuery(quer);
					if(rs.next()) tothrs=rs.getInt(1);					
					rs.close();
					
					quer="select t1,isnull(t2,0),t3,isnull(t4,0),isnull(t6,0) from (select stud.rollno t1,ltrim(rtrim(stud.name)) t3 from stud,student "
 +" where student.rollno=stud.rollno and year="+year+" and department="+dno+" and academicyear='"+academicyear+"' and student.active=1 ) q1 "
 +" left outer join (select ltrim(rtrim(registerno)) t2,rollno r1 from registerno) q3 on q1.t1=q3.r1 "
 +" left outer join (select count(*) t4,max(rollno) t5 from attendance.dbo.attendance a,attendance.dbo.log b where a.date=b.date  "
 +" and a.date between  '"+fd+"' and '"+td+"' and hours like '%'+ltrim(rtrim(str(period)))+'%' and id in (select id from subjectidentify where semester="+semester+" and dno="+dno+" and academicyear='"+academicyear+"') group by rollno) q2 on q1.t1=q2.t5 "
 +" left outer join (select count(*) t6,max(rollno) t7 from attendance.dbo.od o,attendance.dbo.log l where o.date=l.date  "
 +" and o.date between  '"+fd+"' and '"+td+"' and hours like '%'+ltrim(rtrim(str(period)))+'%' and id in (select id from subjectidentify where semester="+semester+" and dno="+dno+" and academicyear='"+academicyear+"') group by rollno) q4 on q1.t1=q4.t7 "
 +" order by t1";

//					quer="select t1,t2,t3,t4 from (select stud.rollno t1,ltrim(rtrim(registerno)) t2,ltrim(rtrim(stud.name)) t3 from stud,registerno,student where student.rollno=stud.rollno and stud.rollno=registerno.rollno and year="+year+" and department="+dno+" and academicyear='"+academicyear+"' and student.active=1 ) q1 "
//                    +" left outer join (select count(*) t4,max(rollno) t5 from attendance.dbo.attendance a,attendance.dbo.log b where a.date=b.date  "
//                    +" and a.date between  '"+fd+"' and '"+td+"' and hours like '%'+ltrim(rtrim(str(period)))+'%' and id in (select id from subjectidentify where semester="+semester+" and dno="+dno+" and academicyear='"+academicyear+"') group by rollno) q2 on q1.t1=q2.t5 order by t1";
                    
						error=error+quer;
					rs = stmt.executeQuery(quer);
					while(rs.next())
						{
						rollno[count]=rs.getInt(1);
			            regno[count]=rs.getString(2);
						name[count]=rs.getString(3);
						mark[count]=rs.getInt(4);
						mark1[count]=rs.getInt(5);
						mark1[count]=0;
						count++;	
						}
					rs.close();
					stmt.close();
				
}catch(Exception e){error=(e.toString()+count+quer);}
finally{connMgr.freeConnection("xavier",con);}

int count1=count;
fdate=fd;
tdate=td;					
error=getOD(count1,dno,year,academicyear);	
count=count1;
return error; 
}


public String getOD(int cnt,int dno,int year,String academicyear)
  {
  	String error="succ";
  	date=fdate;
  	vdate=fdate;
  	datediffer();
  	error=error+"-"+vdate;
  	error=error+"-"+dno+"-"+year+"-"+academicyear;

DBConnectionManager connMgr=null; Connection con =null;
int noofhours=0;
int noofhoursabsent=0;
int noofhoursod=0;

for(int j=0;j<cnt;j++)
  {
  	date=fdate;
  	vdate=fdate;

	try
	{
connMgr = DBConnectionManager.getInstance(); 
con = connMgr.getConnection("attendance");
String tempdate[] = new String[1000];
String temphours[] = new String[1000];
int tempcount=0;
Statement stmt = con.createStatement();
ResultSet rs;
int temp=0;

String hours="",ab="",od="",blank="";
for(int i=0;i<count;i++)
  {
  	for(int z=0;z<10;z++)
  	  attendance[i][z]="";
  rs = stmt.executeQuery("execute individualattendanceview "+dno+","+year+","+rollno[j]+",'"+date+"','"+academicyear+"'");
  if(rs.next())
     {
     	hours=rs.getString(1);
     	ab=rs.getString(2);
     	od=rs.getString(3);
     	blank=rs.getString(4);
try{
     	dispdate[i]=vdate;
     	for(int h=0;h<hours.length();h++)
     	  attendance[i][Integer.parseInt(hours.substring(h,h+1))]="/";
     	for(int h=0;h<ab.length();h++)
     	  attendance[i][Integer.parseInt(ab.substring(h,h+1))]="ab";
     	for(int h=0;h<od.length();h++)
     	   if(attendance[i][Integer.parseInt(od.substring(h,h+1))]=="ab")
     	  attendance[i][Integer.parseInt(od.substring(h,h+1))]="od";
     	for(int h=0;h<blank.length();h++)
     	  attendance[i][Integer.parseInt(blank.substring(h,h+1))]="NA";
}catch(Exception e){error=error+e.toString();}
     }
  rs.close();  
  dateadd();
  }
stmt.close();
noofhours=0;
noofhoursabsent=0;
noofhoursod=0;
for(int i=0;i<count;i++)
  {
  	for(int z=0;z<10;z++)
  	{
  	 if(attendance[i][z]=="/") noofhours+=1;
  	 if(attendance[i][z]=="ab") noofhoursabsent+=1;
  	 if(attendance[i][z]=="od") noofhoursod+=1;
  	}
  	  	  
  }
//error=error+"-"+j+"-"+date;

mark1[j]= noofhoursod; 
/*
noofhours+=noofhoursabsent+noofhoursod;
if(noofhours>0)
{
percentage=(float)(noofhours-noofhoursod-noofhoursabsent)/(float)(noofhours)*100;
percentagewod=(float)(noofhours-noofhoursabsent)/(float)(noofhours)*100;
*/
}catch(Exception e){mark1[j]=1; return e.toString();}
finally{connMgr.freeConnection("attendance",con);}
}

return error;
}

 
public void datediffer()
{
DBConnectionManager connMgr=null; Connection con =null;
	try
	{
connMgr = DBConnectionManager.getInstance(); 
con = connMgr.getConnection("xavier");
Statement stmt = con.createStatement();
ResultSet rs = stmt.executeQuery("select datediff(d,'"+fdate+"','"+tdate+"')");
if(rs.next())
{
	count=rs.getInt(1)+1;
}
rs.close();
stmt.close();
}catch(Exception e){}
finally{connMgr.freeConnection("xavier",con);}

   }

public void dateadd()
{
	String month="",day="",year="";
DBConnectionManager connMgr=null; Connection con =null;
	try
	{
connMgr = DBConnectionManager.getInstance(); 
con = connMgr.getConnection("xavier");
Statement stmt = con.createStatement();
ResultSet rs = stmt.executeQuery("select month(dateadd(day,1,'"+date+"')),day(dateadd(day,1,'"+date+"')),year(dateadd(day,1,'"+date+"'))");
if(rs.next())
{
	month=rs.getString(1);
	day=rs.getString(2);
	year=rs.getString(3);
	date=month+"/"+day+"/"+year;
  	if(day.length()==1)day="0"+day;
  	if(month.length()==1)month="0"+month;
	vdate=day+"/"+month+"/"+year;
}
rs.close();
stmt.close();
}catch(Exception e){}
finally{connMgr.freeConnection("xavier",con);}

}  






public String ViewAttAndMark(String subjectcode,String exam,int dno,String examyear,int semester,String fd,String td,String academicyear)
    {

	DBConnectionManager connMgr=null; Connection con =null;
	try
	{
                    connMgr = DBConnectionManager.getInstance(); 
                    con = connMgr.getConnection("xavier");
   			    	Statement stmt = con.createStatement();
					ResultSet rs;
/*					
					quer="select st.rollno,st.name,st.mark,ISNULL(ltrim(rtrim(rno.registerno)),0) as regno from (SELECT stud.rollno as rollno, stud.name as name , max(mark.mark) as mark FROM stud,student,mark, examyear WHERE stud.rollno=mark.rollno and "
					+" stud.rollno=student.rollno and student.active=1 And examyear.examno=mark.examno And stud.department="+dno+" and examyear.semester="+semester+" and examyear.exam='"+exam+"' and examyear.year=stud.academicyear and examyear.year='"+examyear+"' And mark.subjectcode='"+subjectcode+"' group by stud.rollno,stud.name) st "
				    +" left outer join (SELECT registerno.rollno,registerno FROM registerno) as rno on st.rollno = rno.rollno order BY regno,st.rollno";
*/				    
				    quer="select a.rollno,ltrim(rtrim(a.name)),ISNULL(ltrim(rtrim(b.mark)),-2),ISNULL(ltrim(rtrim(rno.registerno)),0) as regno from "
+" (select stud.rollno as rollno,stud.name as name from student,stud where stud.rollno=student.rollno and active=1 and department="+dno+" and year="+((semester+1)/2)+" and academicyear='"+academicyear+"') a "
+" left outer join (select  rollno,mark from mark,examyear where examyear.examno=mark.examno and examyear.semester="+semester+" and examyear.exam='"+exam+"' and dno="+dno+"  and examyear.year='"+academicyear+"' And mark.subjectcode='"+subjectcode+"') b on a.rollno=b.rollno "
+" left outer join (SELECT registerno.rollno,registerno FROM registerno) rno on a.rollno = rno.rollno order BY regno,a.rollno ";

					count1=0;
					rs = stmt.executeQuery(quer);
					while(rs.next())
						{
						rollno[count1]=rs.getInt(1);
						name[count1]=rs.getString(2);
						mark[count1]=rs.getInt(3);
			            regno[count1]=rs.getString(4);
						count1++;	
						}
					rs.close();
					count=count1;
					
					for (int j=0; j<count1;j++)
					{
                        sql="select count(*) from attendance.dbo.log where date between '"+fd+"' and '"+td+"' and subcode='"+subjectcode+"' and id in(select id from subjectidentify where semester="+semester+" and dno="+dno+" and academicyear='"+academicyear+"')";
  						rs = stmt.executeQuery(sql);
						if(rs.next())
						{
						tp[j]=rs.getInt(1);
						}
						rs.close();
                        sql="select count(*) from attendance.dbo.attendance a,attendance.dbo.log b where a.date=b.date  "+
							"and rollno="+rollno[j]+" and a.date between '"+fd+"' and '"+td+"' and subcode='"+subjectcode+"' "+
							"and hours like '%'+ltrim(rtrim(str(period)))+'%' and id in(select id from subjectidentify where semester="+semester+" and dno="+dno+" and academicyear='"+academicyear+"')";
  						rs = stmt.executeQuery(sql);
						if(rs.next())
						{
						ap[j]=tp[j]-rs.getInt(1);
						}
						rs.close();

					}
					quer="SELECT substring(('000'+ltrim(rtrim(str(day(date))))),len('000'+ltrim(rtrim(str(day(date)))))-1,2)+'/'+substring(('000'+ltrim(rtrim(str(month(date))))),len('000'+ltrim(rtrim(str(month(date)))))-1,2)+'/'+ltrim(rtrim(str(year(date)))),max(marklogin.staffid),max(name) FROM staff,marklogin, examyear WHERE examyear.examno=marklogin.examno "+
					"and staff.staffid=marklogin.staffid and examyear.semester="+semester+" and examyear.exam='"+exam+"' and marklogin.subjectcode='"+subjectcode+
					"' and examyear.year='"+examyear+"' and examyear.dno="+dno+" group by date,marklogin.staffid ORDER BY date desc";
					rs = stmt.executeQuery(quer);
					logcount=0;
					while(rs.next())
						{
						logdate[logcount]=rs.getString(1);
						logstaffid[logcount]=rs.getString(2);
						logstaffname[logcount]=rs.getString(3);
						logcount++;	
						}
					rs.close();

					stmt.close();
}catch(Exception e){error=(e.toString()+count+quer+sql);}
finally{connMgr.freeConnection("xavier",con);
return error; 
}
}


public String ViewAttAndMarkUniv(int ord,String subjectcode,String exam,int dno,String examyear,int semester,String fd,String td,String academicyear)
    {
String ie=exam;
String it="";
String ct="";
String at="";

if(exam.trim().equals("Internal Exam I")) { it="Improvement Test I"; ct="Class Test 1"; at="Assignment 1";}
if(exam.trim().equals("Internal Exam II")) { it="Improvement Test II"; ct="Class Test 2"; at="Assignment 2";}
if(exam.trim().equals("Internal Exam III")) { it="Improvement Test III"; ct="Class Test 3"; at="Assignment 3";}

int im=0,m=0,inm=0,imm=0,ctm=0,atm=0,m70=0;

	DBConnectionManager connMgr=null; Connection con =null;
	try
	{
                       connMgr = DBConnectionManager.getInstance(); 
                       con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;


quer="select st.rollno,ISNULL(ltrim(rtrim(rno.registerno)),0) as regno,st.name, "
+" ISNULL(ltrim(rtrim(m.mark)),-3) as m,ISNULL(ltrim(rtrim(mi.mark)),-3) as mi,ISNULL(ltrim(rtrim(mc.mark)),-3) as mc, "
+" ISNULL(ltrim(rtrim(ma.mark)),-3) as mc,FLOOR(round(((m.mark*.7)+(mc.mark*.2)+(ma.mark*.1)),-3)) as m1 from "
+" (SELECT stud.rollno as rollno, stud.name as name  FROM stud,student WHERE stud.rollno=student.rollno and student.active=1 "
+"  and stud.department="+dno+"and stud.year="+((semester+1)/2) +" and stud.academicyear='"+examyear+"') st "
+"  left outer join (SELECT registerno.rollno,registerno FROM registerno) as rno on st.rollno = rno.rollno "
+"  left outer join (SELECT m.rollno,m.mark FROM marka m,examyear where examyear.examno=m.examno "
+"  and examyear.semester="+semester+"  and examyear.exam='"+ie+"' and examyear.year='"+examyear+"' "
+"  And m.subjectcode='"+subjectcode+"') as m on st.rollno = m.rollno "
+"  left outer join (SELECT mi.rollno,mi.mark FROM marka mi,examyear where examyear.examno=mi.examno "
+"  and examyear.semester="+semester+"  and examyear.exam='"+it+"' and examyear.year='"+examyear+"' "
+"  And mi.subjectcode='"+subjectcode+"') as mi on st.rollno = mi.rollno "
+"  left outer join (SELECT mc.rollno,mark FROM marka mc,examyear where examyear.examno=mc.examno "
+"  and examyear.semester="+semester+"  and examyear.exam='"+ct+"' and examyear.year='"+examyear+"' "
+"  And mc.subjectcode='"+subjectcode+"') as mc on st.rollno = mc.rollno "
+"  left outer join (SELECT ma.rollno,mark FROM marka ma,examyear where examyear.examno=ma.examno "
+"  and examyear.semester="+semester+"  and examyear.exam='"+at+"' and examyear.year='"+examyear+"' "
+"  And ma.subjectcode='"+subjectcode+"') as ma on st.rollno = ma.rollno order by regno,st.rollno";

count=0;
count1=0;
					rs = stmt.executeQuery(quer);
	try
	{
					while(rs.next())
						{
im=0;m=0;inm=0;imm=0;ctm=0;atm=0;m70=0;							
							for(int i=1;i<=8;i++)
							{
								report[i-1][count1]=rs.getString(i);
							}
try{ rollno[count1]=Integer.parseInt(report[0][count1]); }catch(Exception e){rollno[count1]=0;}
						regno[count1]=report[1][count1];
						name[count1]=report[2][count1];

try{ inm=Integer.parseInt(report[3][count1]);}catch(Exception e){inm=0;}
try{ imm=Integer.parseInt(report[4][count1]); }catch(Exception e){imm=0;}
try{ ctm=Integer.parseInt(report[5][count1]); ctm=(int)Math.round(((ctm*20.0)/100.0));}catch(Exception e){ctm=0;}
try{ atm=Integer.parseInt(report[6][count1]); atm=(int)Math.round(((atm*10.0)/100.0));}catch(Exception e){atm=0;}


if(inm<imm) im=imm; else im=inm;

if(im<0) im=0;

try{ m70=im;}catch(Exception e){m70=0;}

if(m70<0) m70=0;

int a1=0,a2=0;
try{ 
if(ctm<0) a1=0; else a1=ctm;
if(atm<0) a2=0; else a2=atm;

m=m70+a1+a2;}catch(Exception e){m=0;}




//try{ m=m70+ctm+atm;}catch(Exception e){m=0;}

if(m>100) m=100;
if(m<0) m=0;

						mark[count1]=m;
						count1++;	
						}
}catch(Exception e){}					
					rs.close();
					count=count1;
					
					for (int j=0; j<count1;j++)
					{
	try
	{
                        sql="select count(*) from attendance.dbo.log where date between '"+fd+"' and '"+td+"' and subcode='"+subjectcode+"' and id in(select id from subjectidentify where semester="+semester+" and dno="+dno+" and academicyear='"+academicyear+"')";
  						rs = stmt.executeQuery(sql);
						if(rs.next())
						{
						tp[j]=rs.getInt(1);
						}
						rs.close();
                        sql="select count(*) from attendance.dbo.attendance a,attendance.dbo.log b where a.date=b.date  "+
							"and rollno="+rollno[j]+" and a.date between '"+fd+"' and '"+td+"' and subcode='"+subjectcode+"' "+
							"and hours like '%'+ltrim(rtrim(str(period)))+'%' and id in(select id from subjectidentify where semester="+semester+" and dno="+dno+" and academicyear='"+academicyear+"')";
  						rs = stmt.executeQuery(sql);
						if(rs.next())
						{
						ap[j]=tp[j]-rs.getInt(1);
						}
						rs.close();
}catch(Exception e){}					
					}
					
					quer1="SELECT substring(('000'+ltrim(rtrim(str(day(date))))),len('000'+ltrim(rtrim(str(day(date)))))-1,2)+'/'+substring(('000'+ltrim(rtrim(str(month(date))))),len('000'+ltrim(rtrim(str(month(date)))))-1,2)+'/'+ltrim(rtrim(str(year(date)))),max(marklogin.staffid),max(name) FROM staff,marklogin, examyear WHERE examyear.examno=marklogin.examno "+
					"and staff.staffid=marklogin.staffid and examyear.semester="+semester+" and examyear.exam='"+exam+"' and marklogin.subjectcode='"+subjectcode+
					"' and examyear.year='"+examyear+"' and examyear.dno="+dno+" group by date,marklogin.staffid ORDER BY date desc";
					rs = stmt.executeQuery(quer1);
					logcount=0;
					while(rs.next())
						{
						logdate[logcount]=rs.getString(1);
						logstaffid[logcount]=rs.getString(2);
						logstaffname[logcount]=rs.getString(3);
						logcount++;	
						}
					rs.close();

					stmt.close();
}catch(Exception e){error=(e.toString()+count+quer+sql);}
finally{connMgr.freeConnection("xavier",con);
return error; 
}
}


public String ViewAttAndMarkUnivNew(int ord,String subjectcode,String exam1,String exam2,String test1,float exam1p,float test1p,String dnoin,String examyear,int semester,String fd,String td,String academicyear)
    {
String ie=exam1;
String it="";
String ct="";
String at="";
it=exam2;
at=test1;
ct=exam1;

int dno=0;

/*
if(exam.trim().equals("Internal Exam I")) { it="Improvement Test I"; ct="Class Test 1"; at="Assignment 1";}
if(exam.trim().equals("Internal Exam II")) { it="Improvement Test II"; ct="Class Test 2"; at="Assignment 2";}
if(exam.trim().equals("Internal Exam III")) { it="Improvement Test III"; ct="Class Test 3"; at="Assignment 3";}
*/
int im=0,m=0,inm=0,imm=0,ctm=0,atm=0,m70=0;


	DBConnectionManager connMgr=null; Connection con =null;
	try
	{
                       connMgr = DBConnectionManager.getInstance(); 
                       con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;


sql="select st.rollno,ISNULL(ltrim(rtrim(rno.registerno)),0) as regno,st.name, "
+" ISNULL(ltrim(rtrim(m.mark)),-3) as m,ISNULL(ltrim(rtrim(mi.mark)),-3) as mi,ISNULL(ltrim(rtrim(mc.mark)),-3) as mc, "
+" ISNULL(ltrim(rtrim(ma.mark)),-3) as ma,dept from "
+" (SELECT stud.rollno as rollno, stud.name as name, stud.department as dept FROM stud,student WHERE stud.rollno=student.rollno and student.active=1 "
+"  and stud.department in("+dnoin+") and stud.year="+((semester+1)/2) +" and stud.academicyear='"+examyear+"') st "
+"  left outer join (SELECT registerno.rollno,registerno FROM registerno) as rno on st.rollno = rno.rollno "
+"  left outer join (SELECT m.rollno,m.mark FROM marka m,examyear where examyear.examno=m.examno "
+"  and examyear.semester="+semester+"  and examyear.exam='"+ie+"' and examyear.year='"+examyear+"' "
+"  And m.subjectcode='"+subjectcode+"') as m on st.rollno = m.rollno "
+"  left outer join (SELECT mi.rollno,mi.mark FROM marka mi,examyear where examyear.examno=mi.examno "
+"  and examyear.semester="+semester+"  and examyear.exam='"+it+"' and examyear.year='"+examyear+"' "
+"  And mi.subjectcode='"+subjectcode+"') as mi on st.rollno = mi.rollno "
+"  left outer join (SELECT mc.rollno,mark FROM marka mc,examyear where examyear.examno=mc.examno "
+"  and examyear.semester="+semester+"  and examyear.exam='"+ct+"' and examyear.year='"+examyear+"' "
+"  And mc.subjectcode='"+subjectcode+"') as mc on st.rollno = mc.rollno "
+"  left outer join (SELECT ma.rollno,mark FROM marka ma,examyear where examyear.examno=ma.examno "
+"  and examyear.semester="+semester+"  and examyear.exam='"+at+"' and examyear.year='"+examyear+"' "
+"  And ma.subjectcode='"+subjectcode+"') as ma on st.rollno = ma.rollno order by regno,st.rollno";

count=0;
count1=0;
					rs = stmt.executeQuery(sql);
	try
	{
					while(rs.next())
						{
im=0;m=0;inm=0;imm=0;ctm=0;atm=0;m70=0;							
							for(int i=1;i<=8;i++)
							{
								report[i-1][count1]=rs.getString(i);
							}
try{ rollno[count1]=Integer.parseInt(report[0][count1]); }catch(Exception e){rollno[count1]=0;}
						regno[count1]=report[1][count1];
						name[count1]=report[2][count1];

try{ inm=Integer.parseInt(report[3][count1]);}catch(Exception e){inm=0;}
try{ imm=Integer.parseInt(report[4][count1]); }catch(Exception e){imm=0;}
//try{ ctm=Integer.parseInt(report[5][count1]); ctm=(int)Math.round(((ctm*0.0)/100.0));}catch(Exception e){ctm=0;}
ctm=0;
try{ atm=Integer.parseInt(report[6][count1]); atm=(int)Math.round(((atm*test1p)/100.0));}catch(Exception e){atm=0;}


if(inm<imm) im=imm; else im=inm;


try{
//	 m70=im;
m70=(int)Math.round(((im*exam1p)/100.0));
	 }catch(Exception e){m70=0;}

if(m70<0) m70=0;

int a1=0,a2=0;
try{ 
if(ctm<0) a1=0; else a1=ctm;
if(atm<0) a2=0; else a2=atm;

m=m70+a1+a2;}catch(Exception e){m=0;}




//try{ m=m70+ctm+atm;}catch(Exception e){m=0;}

if(m>100) m=100;
if(m<0) m=0;

						mark[count1]=m;
						count1++;	
						}

}catch(Exception e){}					
					rs.close();
					count=count1;
					
					for (int j=0; j<count1;j++)
					{
	try
	{
try{ dno=Integer.parseInt(report[7][j]);}catch(Exception e){dno=0;}

                        sql1="select count(*) from attendance.dbo.log where date between '"+fd+"' and '"+td+"' and subcode='"+subjectcode+"' and id in(select id from subjectidentify where semester="+semester+" and  dno="+dno+" and academicyear='"+academicyear+"')";
  						rs = stmt.executeQuery(sql1);
						if(rs.next())
						{
						tp[j]=rs.getInt(1);
						}
						rs.close();
                        sql1="select count(*) from attendance.dbo.attendance a,attendance.dbo.log b where a.date=b.date  "+
							"and rollno="+rollno[j]+" and a.date between '"+fd+"' and '"+td+"' and subcode='"+subjectcode+"' "+
							"and hours like '%'+ltrim(rtrim(str(period)))+'%' and id in(select id from subjectidentify where semester="+semester+" and dno="+dno+" and academicyear='"+academicyear+"')";
  						rs = stmt.executeQuery(sql1);
						if(rs.next())
						{
						ap[j]=tp[j]-rs.getInt(1);
						}
						rs.close();
}catch(Exception e){}					
					}
					
					quer1="SELECT substring(('000'+ltrim(rtrim(str(day(date))))),len('000'+ltrim(rtrim(str(day(date)))))-1,2)+'/'+substring(('000'+ltrim(rtrim(str(month(date))))),len('000'+ltrim(rtrim(str(month(date)))))-1,2)+'/'+ltrim(rtrim(str(year(date)))),max(marklogin.staffid),max(name) FROM staff,marklogin, examyear WHERE examyear.examno=marklogin.examno "+
					"and staff.staffid=marklogin.staffid and examyear.semester="+semester+" and examyear.exam='"+exam1+"' and marklogin.subjectcode='"+subjectcode+
					"' and examyear.year='"+examyear+"' and examyear.dno="+dno+" group by date,marklogin.staffid ORDER BY date desc";
					rs = stmt.executeQuery(quer1);
					logcount=0;
					while(rs.next())
						{
						logdate[logcount]=rs.getString(1);
						logstaffid[logcount]=rs.getString(2);
						logstaffname[logcount]=rs.getString(3);
						logcount++;	
						}
					rs.close();

					stmt.close();
}catch(Exception e){error=(e.toString()+count+quer+sql);}
finally{connMgr.freeConnection("xavier",con);
return error; 
}
}

public String ViewAttAndMarkUnivNew1(String exam11,String exam22,String test11,float exam11p,float test11p,int ord,String subjectcode,String exam1,String exam2,String test1,float exam1p,float test1p,String dnoin,String examyear,int semester,String fd,String td,String academicyear)
    {
String ie=exam1;
int dno=0;
int m1=0,m2=0,m3=0,m4=0,b1=0,b2=0,b3=0,a1=0,a2=0,ba=0,m=0,a=0,tm=0;
	DBConnectionManager connMgr=null; Connection con =null;
	try
	{
                       connMgr = DBConnectionManager.getInstance(); 
                       con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;


sql="select st.rollno,ISNULL(ltrim(rtrim(rno.registerno)),0) as regno,st.name, "
+" ISNULL(ltrim(rtrim(m.mark)),-3) as m,ISNULL(ltrim(rtrim(mi.mark)),-3) as mi,ISNULL(ltrim(rtrim(mc.mark)),-3) as mc, ISNULL(ltrim(rtrim(ma.mark)),-3) as ma, "
+" ISNULL(ltrim(rtrim(mi1.mark)),-3) as mi1,ISNULL(ltrim(rtrim(mc1.mark)),-3) as mc1, ISNULL(ltrim(rtrim(ma1.mark)),-3) as ma1, "
+" dept from "
+" (SELECT stud.rollno as rollno, stud.name as name, stud.department as dept FROM stud,student WHERE stud.rollno=student.rollno and student.active=1 "
+"  and stud.department in("+dnoin+") and stud.year="+((semester+1)/2) +" and stud.academicyear='"+examyear+"') st "
+"  left outer join (SELECT registerno.rollno,registerno FROM registerno) as rno on st.rollno = rno.rollno "
+"  left outer join (SELECT m.rollno,m.mark FROM marka m,examyear where examyear.examno=m.examno "
+"  and examyear.semester="+semester+"  and examyear.exam='"+ie+"' and examyear.year='"+examyear+"' "
+"  And m.subjectcode='"+subjectcode+"') as m on st.rollno = m.rollno "
+"  left outer join (SELECT mi.rollno,mi.mark FROM marka mi,examyear where examyear.examno=mi.examno "
+"  and examyear.semester="+semester+"  and examyear.exam='"+exam1+"' and examyear.year='"+examyear+"' "
+"  And mi.subjectcode='"+subjectcode+"') as mi on st.rollno = mi.rollno "
+"  left outer join (SELECT mc.rollno,mark FROM marka mc,examyear where examyear.examno=mc.examno "
+"  and examyear.semester="+semester+"  and examyear.exam='"+exam2+"' and examyear.year='"+examyear+"' "
+"  And mc.subjectcode='"+subjectcode+"') as mc on st.rollno = mc.rollno "
+"  left outer join (SELECT ma.rollno,mark FROM marka ma,examyear where examyear.examno=ma.examno "
+"  and examyear.semester="+semester+"  and examyear.exam='"+test1+"' and examyear.year='"+examyear+"' "
+"  And ma.subjectcode='"+subjectcode+"') as ma on st.rollno = ma.rollno "
+"  left outer join (SELECT mi.rollno,mi.mark FROM marka mi,examyear where examyear.examno=mi.examno "
+"  and examyear.semester="+semester+"  and examyear.exam='"+exam11+"' and examyear.year='"+examyear+"' "
+"  And mi.subjectcode='"+subjectcode+"') as mi1 on st.rollno = mi1.rollno "
+"  left outer join (SELECT mc.rollno,mark FROM marka mc,examyear where examyear.examno=mc.examno "
+"  and examyear.semester="+semester+"  and examyear.exam='"+exam22+"' and examyear.year='"+examyear+"' "
+"  And mc.subjectcode='"+subjectcode+"') as mc1 on st.rollno = mc1.rollno "
+"  left outer join (SELECT ma.rollno,mark FROM marka ma,examyear where examyear.examno=ma.examno "
+"  and examyear.semester="+semester+"  and examyear.exam='"+test11+"' and examyear.year='"+examyear+"' "
+"  And ma.subjectcode='"+subjectcode+"') as ma1 on st.rollno = ma1.rollno "

+" order by regno,st.rollno";

count=0;
count1=0;
					rs = stmt.executeQuery(sql);
	try
	{
					while(rs.next())
						{
m1=0;m2=0;m3=0;m4=0;b1=0;b2=0;b3=0;a1=0;a2=0;ba=0;m=0;a=0;tm=0;
						
							for(int i=1;i<=11;i++)
							{
								report[i-1][count1]=rs.getString(i);
							}
try{ rollno[count1]=Integer.parseInt(report[0][count1]); }catch(Exception e){rollno[count1]=0;}
						regno[count1]=report[1][count1];
						name[count1]=report[2][count1];

try{ m1=Integer.parseInt(report[4][count1]);}catch(Exception e){m1=0;}
try{ m2=Integer.parseInt(report[5][count1]); }catch(Exception e){m2=0;}
try{ a1=Integer.parseInt(report[6][count1]); }catch(Exception e){a1=0;}

try{ m3=Integer.parseInt(report[7][count1]);}catch(Exception e){m3=0;}
try{ m4=Integer.parseInt(report[8][count1]); }catch(Exception e){m4=0;}
try{ a2=Integer.parseInt(report[9][count1]); }catch(Exception e){a2=0;}

if(m1<m2) b1=m2; else b1=m1;
if(m3<m4) b2=m4; else b2=m3;
//if(a1<a2) b3=a2; else b3=a1;

try{ m=(int)Math.round(((b1*exam1p)/100.0)+((b2*exam11p)/100.0));}catch(Exception e){m=0;}

try{ a1=(int)Math.round(((a1*test1p)/100.0));}catch(Exception e){a1=0;}
try{ a2=(int)Math.round(((a2*test11p)/100.0));}catch(Exception e){a2=0;}

tm=m+a1+a2;

if(tm<0) tm=0;

if(tm>100) tm=100;
						mark[count1]=tm;
						count1++;	
						}
}catch(Exception e){}					
					rs.close();
					count=count1;
					
					for (int j=0; j<count1;j++)
					{
	try
	{
try{ 
dno=Integer.parseInt(report[10][j]);}
catch(Exception e){dno=0;}

                        sql1="select count(*) from attendance.dbo.log where date between '"+fd+"' and '"+td+"' and subcode='"+subjectcode+"' and id in(select id from subjectidentify where semester="+semester+" and  dno="+dno+" and academicyear='"+academicyear+"')";
  						rs = stmt.executeQuery(sql1);
						if(rs.next())
						{
						tp[j]=rs.getInt(1);
						}
						rs.close();
                        sql1="select count(*) from attendance.dbo.attendance a,attendance.dbo.log b where a.date=b.date  "+
							"and rollno="+rollno[j]+" and a.date between '"+fd+"' and '"+td+"' and subcode='"+subjectcode+"' "+
							"and hours like '%'+ltrim(rtrim(str(period)))+'%' and id in(select id from subjectidentify where semester="+semester+" and dno="+dno+" and academicyear='"+academicyear+"')";
  						rs = stmt.executeQuery(sql1);
						if(rs.next())
						{
						ap[j]=tp[j]-rs.getInt(1);
						}
						rs.close();
}catch(Exception e){}					
					}
					
					quer1="SELECT substring(('000'+ltrim(rtrim(str(day(date))))),len('000'+ltrim(rtrim(str(day(date)))))-1,2)+'/'+substring(('000'+ltrim(rtrim(str(month(date))))),len('000'+ltrim(rtrim(str(month(date)))))-1,2)+'/'+ltrim(rtrim(str(year(date)))),max(marklogin.staffid),max(name) FROM staff,marklogin, examyear WHERE examyear.examno=marklogin.examno "+
					"and staff.staffid=marklogin.staffid and examyear.semester="+semester+" and examyear.exam='"+exam1+"' and marklogin.subjectcode='"+subjectcode+
					"' and examyear.year='"+examyear+"' and examyear.dno="+dno+" group by date,marklogin.staffid ORDER BY date desc";
					rs = stmt.executeQuery(quer1);
					logcount=0;
					while(rs.next())
						{
						logdate[logcount]=rs.getString(1);
						logstaffid[logcount]=rs.getString(2);
						logstaffname[logcount]=rs.getString(3);
						logcount++;	
						}
					rs.close();

					stmt.close();
}catch(Exception e){error=(e.toString()+count+quer+sql);}
finally{connMgr.freeConnection("xavier",con);
return error; 
}
}

public String ViewAttV5(String examyear,int semester, String dnoin, String subjectcode, String fd,String td)
    {
String academicyear=examyear;
int dno=0;
	DBConnectionManager connMgr=null; Connection con =null;
	try
	{
                       connMgr = DBConnectionManager.getInstance(); 
                       con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;


sql="select st.rollno,ISNULL(ltrim(rtrim(rno.registerno)),0) as regno,st.name,dept from "
+" (SELECT stud.rollno as rollno, stud.name as name, stud.department as dept FROM stud,student WHERE stud.rollno=student.rollno and student.active=1 "
+"  and stud.department in("+dnoin+") and stud.year="+((semester+1)/2) +" and stud.academicyear='"+examyear+"') st "
+"  left outer join (SELECT registerno.rollno,registerno FROM registerno) as rno on st.rollno = rno.rollno "
+"  order by regno,st.rollno";

count=0;
count1=0;
rs = stmt.executeQuery(sql);
try
{
		while(rs.next())
		{
			for(int i=1;i<=4;i++)
			{
				report[i-1][count1]=rs.getString(i);
			}
try{ rollno[count1]=Integer.parseInt(report[0][count1]); }catch(Exception e){rollno[count1]=0;}
						regno[count1]=report[1][count1];
						name[count1]=report[2][count1];
				
				count1++;	
		}
}catch(Exception e){}					
rs.close();
count=count1;
					
for (int j=0; j<count1;j++)
{
	try
	{
try{ 
dno=Integer.parseInt(report[3][j]);}
catch(Exception e){dno=0;}

                        sql1="select count(*) from attendance.dbo.log where date between '"+fd+"' and '"+td+"' and subcode='"+subjectcode+"' and id in(select id from subjectidentify where semester="+semester+" and  dno in("+dnoin+") and academicyear='"+academicyear+"')";
  						rs = stmt.executeQuery(sql1);
						if(rs.next())
						{
						tp[j]=rs.getInt(1);
						}
						rs.close();
                        sql1="select count(*) from attendance.dbo.attendance a,attendance.dbo.log b where a.date=b.date  "+
							"and rollno="+rollno[j]+" and a.date between '"+fd+"' and '"+td+"' and subcode='"+subjectcode+"' "+
							"and hours like '%'+ltrim(rtrim(str(period)))+'%' and id in(select id from subjectidentify where semester="+semester+" and dno in("+dnoin+") and academicyear='"+academicyear+"')";
  						rs = stmt.executeQuery(sql1);
						if(rs.next())
						{
						ap[j]=tp[j]-rs.getInt(1);
						}
						rs.close();
}catch(Exception e){}					
}
					
stmt.close();
}catch(Exception e){error=(e.toString()+count+quer+sql);}
finally{connMgr.freeConnection("xavier",con);
return error; 
}
}



public String ViewAttAndMarkUnivV3(String c1,String c2,String c3,String i1,String i2,String i3,String a1,String a2,String a3,float c1p,float c2p,float c3p,float a1p,float a2p,float a3p,String examyear,int semester, String dnoin, String subjectcode, String fd,String td)
    {
String academicyear=examyear;
int dno=0;
int mc1=0,mc2=0,mc3=0,mi1=0,mi2=0,mi3=0,ma1=0,ma2=0,ma3=0,b1=0,b2=0,b3=0,m=0,a=0,tm=0;
	DBConnectionManager connMgr=null; Connection con =null;
	try
	{
                       connMgr = DBConnectionManager.getInstance(); 
                       con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;


sql="select st.rollno,ISNULL(ltrim(rtrim(rno.registerno)),0) as regno,st.name, "
+" ISNULL(ltrim(rtrim(c1.mark)),-3),ISNULL(ltrim(rtrim(c2.mark)),-3),ISNULL(ltrim(rtrim(c3.mark)),-3), "
+" ISNULL(ltrim(rtrim(i1.mark)),-3),ISNULL(ltrim(rtrim(i2.mark)),-3),ISNULL(ltrim(rtrim(i3.mark)),-3), "
+" ISNULL(ltrim(rtrim(a1.mark)),-3),ISNULL(ltrim(rtrim(a2.mark)),-3),ISNULL(ltrim(rtrim(a3.mark)),-3),dept from "
+" (SELECT stud.rollno as rollno, stud.name as name, stud.department as dept FROM stud,student WHERE stud.rollno=student.rollno and student.active=1 "
+"  and stud.department in("+dnoin+") and stud.year="+((semester+1)/2) +" and stud.academicyear='"+examyear+"') st "
+"  left outer join (SELECT registerno.rollno,registerno FROM registerno) as rno on st.rollno = rno.rollno "
+"  left outer join (SELECT rollno,mark FROM marka,examyear where examyear.examno=marka.examno "
+"  and examyear.semester="+semester+"  and examyear.exam='"+c1+"' and examyear.year='"+examyear+"' "
+"  And marka.subjectcode='"+subjectcode+"') as c1 on st.rollno = c1.rollno "
+"  left outer join (SELECT rollno,mark FROM marka,examyear where examyear.examno=marka.examno "
+"  and examyear.semester="+semester+"  and examyear.exam='"+c2+"' and examyear.year='"+examyear+"' "
+"  And marka.subjectcode='"+subjectcode+"') as c2 on st.rollno = c2.rollno "
+"  left outer join (SELECT rollno,mark FROM marka,examyear where examyear.examno=marka.examno "
+"  and examyear.semester="+semester+"  and examyear.exam='"+c3+"' and examyear.year='"+examyear+"' "
+"  And marka.subjectcode='"+subjectcode+"') as c3 on st.rollno = c3.rollno "
+"  left outer join (SELECT rollno,mark FROM marka,examyear where examyear.examno=marka.examno "
+"  and examyear.semester="+semester+"  and examyear.exam='"+i1+"' and examyear.year='"+examyear+"' "
+"  And marka.subjectcode='"+subjectcode+"') as i1 on st.rollno = i1.rollno "
+"  left outer join (SELECT rollno,mark FROM marka,examyear where examyear.examno=marka.examno "
+"  and examyear.semester="+semester+"  and examyear.exam='"+i2+"' and examyear.year='"+examyear+"' "
+"  And marka.subjectcode='"+subjectcode+"') as i2 on st.rollno = i2.rollno "
+"  left outer join (SELECT rollno,mark FROM marka,examyear where examyear.examno=marka.examno "
+"  and examyear.semester="+semester+"  and examyear.exam='"+i3+"' and examyear.year='"+examyear+"' "
+"  And marka.subjectcode='"+subjectcode+"') as i3 on st.rollno = i3.rollno "
+"  left outer join (SELECT rollno,mark FROM marka,examyear where examyear.examno=marka.examno "
+"  and examyear.semester="+semester+"  and examyear.exam='"+a1+"' and examyear.year='"+examyear+"' "
+"  And marka.subjectcode='"+subjectcode+"') as a1 on st.rollno = a1.rollno "
+"  left outer join (SELECT rollno,mark FROM marka,examyear where examyear.examno=marka.examno "
+"  and examyear.semester="+semester+"  and examyear.exam='"+a2+"' and examyear.year='"+examyear+"' "
+"  And marka.subjectcode='"+subjectcode+"') as a2 on st.rollno = a2.rollno "
+"  left outer join (SELECT rollno,mark FROM marka,examyear where examyear.examno=marka.examno "
+"  and examyear.semester="+semester+"  and examyear.exam='"+a3+"' and examyear.year='"+examyear+"' "
+"  And marka.subjectcode='"+subjectcode+"') as a3 on st.rollno = a3.rollno "
+"  order by regno,st.rollno";


count=0;
count1=0;
					rs = stmt.executeQuery(sql);
	try
	{
					while(rs.next())
						{
mc1=0;mc2=0;mc3=0;mi1=0;mi2=0;mi3=0;ma1=0;ma2=0;ma3=0;b1=0;b2=0;b3=0;m=0;a=0;tm=0;						
							for(int i=1;i<=13;i++)
							{
								report[i-1][count1]=rs.getString(i);
							}
try{ rollno[count1]=Integer.parseInt(report[0][count1]); }catch(Exception e){rollno[count1]=0;}
						regno[count1]=report[1][count1];
						name[count1]=report[2][count1];

try{ mc1=Integer.parseInt(report[3][count1]);}catch(Exception e){mc1=0;}
try{ mc2=Integer.parseInt(report[4][count1]); }catch(Exception e){mc2=0;}
try{ mc3=Integer.parseInt(report[5][count1]); }catch(Exception e){mc3=0;}

try{ mi1=Integer.parseInt(report[6][count1]);}catch(Exception e){mi1=0;}
try{ mi2=Integer.parseInt(report[7][count1]); }catch(Exception e){mi2=0;}
try{ mi3=Integer.parseInt(report[8][count1]); }catch(Exception e){mi3=0;}

try{ ma1=Integer.parseInt(report[9][count1]);}catch(Exception e){ma1=0;}
try{ ma2=Integer.parseInt(report[10][count1]); }catch(Exception e){ma2=0;}
try{ ma3=Integer.parseInt(report[11][count1]); }catch(Exception e){ma3=0;}

if(mc1<mi1) b1=mi1; else b1=mc1;
if(mc2<mi2) b2=mi2; else b2=mc2;
if(mc3<mi3) b3=mi3; else b3=mc3;

if(b1<0) b1=0;
if(b2<0) b2=0;
if(b3<0) b3=0;

try{ m=(int)Math.round(((b1*c1p)/100.0)+((b2*c2p)/100.0)+((b3*c3p)/100.0) );}catch(Exception e){m=0;}

try{ a=(int)Math.round(((ma1*a1p)/100.0)+((ma2*a2p)/100.0)+((ma3*a3p)/100.0) );}catch(Exception e){a=0;}

tm=m+a;

if(tm<0) tm=0;

if(tm>100) tm=100;
						mark[count1]=tm;
						count1++;	
						}
}catch(Exception e){}					
					rs.close();
					count=count1;
					
					for (int j=0; j<count1;j++)
					{
	try
	{
try{ 
dno=Integer.parseInt(report[10][j]);}
catch(Exception e){dno=0;}

                        sql1="select count(*) from attendance.dbo.log where date between '"+fd+"' and '"+td+"' and subcode='"+subjectcode+"' and id in(select id from subjectidentify where semester="+semester+" and  dno in("+dnoin+") and academicyear='"+academicyear+"')";
  						rs = stmt.executeQuery(sql1);
						if(rs.next())
						{
						tp[j]=rs.getInt(1);
						}
						rs.close();
                        sql1="select count(*) from attendance.dbo.attendance a,attendance.dbo.log b where a.date=b.date  "+
							"and rollno="+rollno[j]+" and a.date between '"+fd+"' and '"+td+"' and subcode='"+subjectcode+"' "+
							"and hours like '%'+ltrim(rtrim(str(period)))+'%' and id in(select id from subjectidentify where semester="+semester+" and dno in("+dnoin+") and academicyear='"+academicyear+"')";
  						rs = stmt.executeQuery(sql1);
						if(rs.next())
						{
						ap[j]=tp[j]-rs.getInt(1);
						}
						rs.close();
}catch(Exception e){}					
					}
					
					quer1="SELECT substring(('000'+ltrim(rtrim(str(day(date))))),len('000'+ltrim(rtrim(str(day(date)))))-1,2)+'/'+substring(('000'+ltrim(rtrim(str(month(date))))),len('000'+ltrim(rtrim(str(month(date)))))-1,2)+'/'+ltrim(rtrim(str(year(date)))),max(marklogin.staffid),max(name) FROM staff,marklogin, examyear WHERE examyear.examno=marklogin.examno "+
					"and staff.staffid=marklogin.staffid and examyear.semester="+semester+" and examyear.exam='"+c1+"' and marklogin.subjectcode='"+subjectcode+
					"' and examyear.year='"+examyear+"' and examyear.dno in("+dnoin+") group by date,marklogin.staffid ORDER BY date desc";
					rs = stmt.executeQuery(quer1);
					logcount=0;
					while(rs.next())
						{
						logdate[logcount]=rs.getString(1);
						logstaffid[logcount]=rs.getString(2);
						logstaffname[logcount]=rs.getString(3);
						logcount++;	
						}
					rs.close();

					stmt.close();
}catch(Exception e){error=(e.toString()+count+quer+sql);}
finally{connMgr.freeConnection("xavier",con);
return error; 
}
}


public String ViewMarkUniv(String c1,String c2,String c3,String i1,String i2,String i3,String a1,String a2,String a3,float c1p,float c2p,float c3p,float a1p,float a2p,float a3p,String examyear,int semester, String dnoin, String subjectcode,String p1,String p2,String pr1,String pr2,String pr3,float p1p,float p2p,float pr1p,float pr2p,float pr3p,int TP)
{
String academicyear=examyear;
int dno=0;
int mc1=0,mc2=0,mc3=0,mi1=0,mi2=0,mi3=0,ma1=0,ma2=0,ma3=0,b1=0,b2=0,b3=0,m=0,a=0,tm=0;
int mp1,mp2,mpr1,mpr2,mpr3;
	DBConnectionManager connMgr=null; Connection con =null;
	try
	{
                       connMgr = DBConnectionManager.getInstance(); 
                       con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;

sql="select st.rollno,ISNULL(ltrim(rtrim(rno.registerno)),0) as regno,st.name, "
+" ISNULL(ltrim(rtrim(c1.mark)),-3),ISNULL(ltrim(rtrim(c2.mark)),-3),ISNULL(ltrim(rtrim(c3.mark)),-3), "
+" ISNULL(ltrim(rtrim(i1.mark)),-3),ISNULL(ltrim(rtrim(i2.mark)),-3),ISNULL(ltrim(rtrim(i3.mark)),-3), "
+" ISNULL(ltrim(rtrim(a1.mark)),-3),ISNULL(ltrim(rtrim(a2.mark)),-3),ISNULL(ltrim(rtrim(a3.mark)),-3), "
+" ISNULL(ltrim(rtrim(p1.mark)),-3),ISNULL(ltrim(rtrim(p2.mark)),-3), "
+" ISNULL(ltrim(rtrim(pr1.mark)),-3),ISNULL(ltrim(rtrim(pr2.mark)),-3),ISNULL(ltrim(rtrim(pr3.mark)),-3), "
+" dept from (SELECT stud.rollno as rollno, stud.name as name, stud.department as dept FROM stud,student WHERE stud.rollno=student.rollno and student.active=1 "
+"  and stud.department in("+dnoin+") and stud.year="+((semester+1)/2) +" and stud.academicyear='"+examyear+"') st "
+"  left outer join (SELECT registerno.rollno,registerno FROM registerno) as rno on st.rollno = rno.rollno "
+"  left outer join (SELECT rollno,max(mark) mark FROM marka,examyear where examyear.examno=marka.examno "
+"  and examyear.semester="+semester+"  and examyear.exam='"+c1+"' and examyear.year='"+examyear+"' "
+"  And marka.subjectcode='"+subjectcode+"' group by rollno) as c1 on st.rollno = c1.rollno "
+"  left outer join (SELECT rollno,max(mark) mark FROM marka,examyear where examyear.examno=marka.examno "
+"  and examyear.semester="+semester+"  and examyear.exam='"+c2+"' and examyear.year='"+examyear+"' "
+"  And marka.subjectcode= '"+subjectcode+"' group by rollno) as c2 on st.rollno = c2.rollno "
+"  left outer join (SELECT rollno,max(mark) mark FROM marka,examyear where examyear.examno=marka.examno "
+"  and examyear.semester="+semester+"  and examyear.exam='"+c3+"' and examyear.year='"+examyear+"' "
+"  And marka.subjectcode= '"+subjectcode+"' group by rollno) as c3 on st.rollno = c3.rollno "
+"  left outer join (SELECT rollno,max(mark) mark FROM marka,examyear where examyear.examno=marka.examno "
+"  and examyear.semester="+semester+"  and examyear.exam='"+i1+"' and examyear.year='"+examyear+"' "
+"  And marka.subjectcode= '"+subjectcode+"' group by rollno) as i1 on st.rollno = i1.rollno "
+"  left outer join (SELECT rollno,max(mark) mark FROM marka,examyear where examyear.examno=marka.examno "
+"  and examyear.semester="+semester+"  and examyear.exam='"+i2+"' and examyear.year='"+examyear+"' "
+"  And marka.subjectcode= '"+subjectcode+"' group by rollno) as i2 on st.rollno = i2.rollno "
+"  left outer join (SELECT rollno,max(mark) mark FROM marka,examyear where examyear.examno=marka.examno "
+"  and examyear.semester="+semester+"  and examyear.exam='"+i3+"' and examyear.year='"+examyear+"' "
+"  And marka.subjectcode= '"+subjectcode+"' group by rollno) as i3 on st.rollno = i3.rollno "
+"  left outer join (SELECT rollno,max(mark) mark FROM marka,examyear where examyear.examno=marka.examno "
+"  and examyear.semester="+semester+"  and examyear.exam='"+a1+"' and examyear.year='"+examyear+"' "
+"  And marka.subjectcode= '"+subjectcode+"' group by rollno) as a1 on st.rollno = a1.rollno "
+"  left outer join (SELECT rollno,max(mark) mark FROM marka,examyear where examyear.examno=marka.examno "
+"  and examyear.semester="+semester+"  and examyear.exam='"+a2+"' and examyear.year='"+examyear+"' "
+"  And marka.subjectcode= '"+subjectcode+"' group by rollno) as a2 on st.rollno = a2.rollno "
+"  left outer join (SELECT rollno,max(mark) mark FROM marka,examyear where examyear.examno=marka.examno "
+"  and examyear.semester="+semester+"  and examyear.exam='"+a3+"' and examyear.year='"+examyear+"' "
+"  And marka.subjectcode= '"+subjectcode+"' group by rollno) as a3 on st.rollno = a3.rollno "
+"  left outer join (SELECT rollno,max(mark) mark FROM marka,examyear where examyear.examno=marka.examno "
+"  and examyear.semester="+semester+"  and examyear.exam='"+p1+"' and examyear.year='"+examyear+"' "
+"  And marka.subjectcode= '"+subjectcode+"' group by rollno) as p1 on st.rollno = p1.rollno "
+"  left outer join (SELECT rollno,max(mark) mark FROM marka,examyear where examyear.examno=marka.examno "
+"  and examyear.semester="+semester+"  and examyear.exam='"+p2+"' and examyear.year='"+examyear+"' "
+"  And marka.subjectcode= '"+subjectcode+"' group by rollno) as p2 on st.rollno = p2.rollno "
+"  left outer join (SELECT rollno,max(mark) mark FROM marka,examyear where examyear.examno=marka.examno "
+"  and examyear.semester="+semester+"  and examyear.exam='"+pr1+"' and examyear.year='"+examyear+"' "
+"  And marka.subjectcode= '"+subjectcode+"' group by rollno) as pr1 on st.rollno = pr1.rollno "
+"  left outer join (SELECT rollno,max(mark) mark FROM marka,examyear where examyear.examno=marka.examno "
+"  and examyear.semester="+semester+"  and examyear.exam='"+pr2+"' and examyear.year='"+examyear+"' "
+"  And marka.subjectcode= '"+subjectcode+"' group by rollno) as pr2 on st.rollno = pr2.rollno "
+"  left outer join (SELECT rollno,max(mark) mark FROM marka,examyear where examyear.examno=marka.examno "
+"  and examyear.semester="+semester+"  and examyear.exam='"+pr3+"' and examyear.year='"+examyear+"' "
+"  And marka.subjectcode= '"+subjectcode+"' group by rollno) as pr3 on st.rollno = pr3.rollno "
+"  order by regno,st.rollno";

/*
sql="select st.rollno,ISNULL(ltrim(rtrim(rno.registerno)),0) as regno,st.name, "
+" ISNULL(ltrim(rtrim(c1.mark)),-3),ISNULL(ltrim(rtrim(c2.mark)),-3),ISNULL(ltrim(rtrim(c3.mark)),-3), "
+" ISNULL(ltrim(rtrim(i1.mark)),-3),ISNULL(ltrim(rtrim(i2.mark)),-3),ISNULL(ltrim(rtrim(i3.mark)),-3), "
+" ISNULL(ltrim(rtrim(a1.mark)),-3),ISNULL(ltrim(rtrim(a2.mark)),-3),ISNULL(ltrim(rtrim(a3.mark)),-3), "
+" ISNULL(ltrim(rtrim(p1.mark)),-3),ISNULL(ltrim(rtrim(p2.mark)),-3), "
+" ISNULL(ltrim(rtrim(pr1.mark)),-3),ISNULL(ltrim(rtrim(pr2.mark)),-3),ISNULL(ltrim(rtrim(pr3.mark)),-3), "
+" dept from (SELECT stud.rollno as rollno, stud.name as name, stud.department as dept FROM stud,student WHERE stud.rollno=student.rollno and student.active=1 "
+"  and stud.department in("+dnoin+") and stud.year="+((semester+1)/2) +" and stud.academicyear='"+examyear+"') st "
+"  left outer join (SELECT registerno.rollno,registerno FROM registerno) as rno on st.rollno = rno.rollno "
+"  left outer join (SELECT rollno,mark FROM marka,examyear where examyear.examno=marka.examno "
+"  and examyear.semester="+semester+"  and examyear.exam='"+c1+"' and examyear.year='"+examyear+"' "
+"  And marka.subjectcode='"+subjectcode+"' order by rollno) as c1 on st.rollno = c1.rollno "
+"  left outer join (SELECT rollno,mark FROM marka,examyear where examyear.examno=marka.examno "
+"  and examyear.semester="+semester+"  and examyear.exam='"+c2+"' and examyear.year='"+examyear+"' "
+"  And marka.subjectcode='"+subjectcode+"') as c2 on st.rollno = c2.rollno "
+"  left outer join (SELECT rollno,mark FROM marka,examyear where examyear.examno=marka.examno "
+"  and examyear.semester="+semester+"  and examyear.exam='"+c3+"' and examyear.year='"+examyear+"' "
+"  And marka.subjectcode='"+subjectcode+"') as c3 on st.rollno = c3.rollno "
+"  left outer join (SELECT rollno,mark FROM marka,examyear where examyear.examno=marka.examno "
+"  and examyear.semester="+semester+"  and examyear.exam='"+i1+"' and examyear.year='"+examyear+"' "
+"  And marka.subjectcode='"+subjectcode+"') as i1 on st.rollno = i1.rollno "
+"  left outer join (SELECT rollno,mark FROM marka,examyear where examyear.examno=marka.examno "
+"  and examyear.semester="+semester+"  and examyear.exam='"+i2+"' and examyear.year='"+examyear+"' "
+"  And marka.subjectcode='"+subjectcode+"') as i2 on st.rollno = i2.rollno "
+"  left outer join (SELECT rollno,mark FROM marka,examyear where examyear.examno=marka.examno "
+"  and examyear.semester="+semester+"  and examyear.exam='"+i3+"' and examyear.year='"+examyear+"' "
+"  And marka.subjectcode='"+subjectcode+"') as i3 on st.rollno = i3.rollno "
+"  left outer join (SELECT rollno,mark FROM marka,examyear where examyear.examno=marka.examno "
+"  and examyear.semester="+semester+"  and examyear.exam='"+a1+"' and examyear.year='"+examyear+"' "
+"  And marka.subjectcode='"+subjectcode+"') as a1 on st.rollno = a1.rollno "
+"  left outer join (SELECT rollno,mark FROM marka,examyear where examyear.examno=marka.examno "
+"  and examyear.semester="+semester+"  and examyear.exam='"+a2+"' and examyear.year='"+examyear+"' "
+"  And marka.subjectcode='"+subjectcode+"') as a2 on st.rollno = a2.rollno "
+"  left outer join (SELECT rollno,mark FROM marka,examyear where examyear.examno=marka.examno "
+"  and examyear.semester="+semester+"  and examyear.exam='"+a3+"' and examyear.year='"+examyear+"' "
+"  And marka.subjectcode='"+subjectcode+"') as a3 on st.rollno = a3.rollno "
+"  left outer join (SELECT rollno,mark FROM marka,examyear where examyear.examno=marka.examno "
+"  and examyear.semester="+semester+"  and examyear.exam='"+p1+"' and examyear.year='"+examyear+"' "
+"  And marka.subjectcode='"+subjectcode+"') as p1 on st.rollno = p1.rollno "
+"  left outer join (SELECT rollno,mark FROM marka,examyear where examyear.examno=marka.examno "
+"  and examyear.semester="+semester+"  and examyear.exam='"+p2+"' and examyear.year='"+examyear+"' "
+"  And marka.subjectcode='"+subjectcode+"') as p2 on st.rollno = p2.rollno "
+"  left outer join (SELECT rollno,mark FROM marka,examyear where examyear.examno=marka.examno "
+"  and examyear.semester="+semester+"  and examyear.exam='"+pr1+"' and examyear.year='"+examyear+"' "
+"  And marka.subjectcode='"+subjectcode+"') as pr1 on st.rollno = pr1.rollno "
+"  left outer join (SELECT rollno,mark FROM marka,examyear where examyear.examno=marka.examno "
+"  and examyear.semester="+semester+"  and examyear.exam='"+pr2+"' and examyear.year='"+examyear+"' "
+"  And marka.subjectcode='"+subjectcode+"') as pr2 on st.rollno = pr2.rollno "
+"  left outer join (SELECT rollno,mark FROM marka,examyear where examyear.examno=marka.examno "
+"  and examyear.semester="+semester+"  and examyear.exam='"+pr3+"' and examyear.year='"+examyear+"' "
+"  And marka.subjectcode='"+subjectcode+"') as pr3 on st.rollno = pr3.rollno "
+"  order by regno,st.rollno";

*/

count=0;
count1=0;
int ttm=0;

rs = stmt.executeQuery(sql);
	try
	{
		while(rs.next())
		{
		mc1=0;mc2=0;mc3=0;mi1=0;mi2=0;mi3=0;ma1=0;ma2=0;ma3=0;b1=0;b2=0;b3=0;m=0;a=0;tm=0;						
			for(int i=1;i<=18;i++)
			{
				report[i-1][count1]=rs.getString(i);
			}

try{ rollno[count1]=Integer.parseInt(report[0][count1]); }catch(Exception e){rollno[count1]=0;}
regno[count1]=report[1][count1];
name[count1]=report[2][count1];

try{ mc1=Integer.parseInt(report[3][count1]);}catch(Exception e){mc1=0;}
try{ mc2=Integer.parseInt(report[4][count1]); }catch(Exception e){mc2=0;}
try{ mc3=Integer.parseInt(report[5][count1]); }catch(Exception e){mc3=0;}

try{ mi1=Integer.parseInt(report[6][count1]);}catch(Exception e){mi1=0;}
try{ mi2=Integer.parseInt(report[7][count1]); }catch(Exception e){mi2=0;}
try{ mi3=Integer.parseInt(report[8][count1]); }catch(Exception e){mi3=0;}

try{ ma1=Integer.parseInt(report[9][count1]);}catch(Exception e){ma1=0;}
try{ ma2=Integer.parseInt(report[10][count1]); }catch(Exception e){ma2=0;}
try{ ma3=Integer.parseInt(report[11][count1]); }catch(Exception e){ma3=0;}

try{ mp1=Integer.parseInt(report[12][count1]);}catch(Exception e){mp1=0;}
try{ mp2=Integer.parseInt(report[13][count1]); }catch(Exception e){mp2=0;}

try{ mpr1=Integer.parseInt(report[14][count1]);}catch(Exception e){mpr1=0;}
try{ mpr2=Integer.parseInt(report[15][count1]); }catch(Exception e){mpr2=0;}
try{ mpr3=Integer.parseInt(report[16][count1]); }catch(Exception e){mpr3=0;}



if(mc1<mi1) b1=mi1; else b1=mc1;
if(mc2<mi2) b2=mi2; else b2=mc2;
if(mc3<mi3) b3=mi3; else b3=mc3;

if(b1<0) b1=0;
if(b2<0) b2=0;
if(b3<0) b3=0;

if(ma1<0)ma1=0;
if(ma2<0)ma2=0;
if(ma3<0)ma3=0;

float fb1=b1;
float fb2=b2;
float fb3=b3;
float fb=0;
try{ 
fb1=fb1*c1p;
fb2=fb2*c2p;
fb3=fb3*c3p;
fb=fb1+fb2+fb3;
 m=(int)Math.round(fb/100.0);
}catch(Exception e){m=0;}

//try{ m=(int)Math.round((((b1*c1p)/100.0)+((b2*c2p)/100.0)+((b3*c3p)/100.0)) );}catch(Exception e){m=0;}

try{ 
fb1=ma1*a1p;
fb2=ma2*a2p;
fb3=ma3*a3p;
fb=fb1+fb2+fb3;
 a=(int)Math.round(fb/100.0);
}catch(Exception e){a=0;}

//a=(int)Math.round((((ma1*a1p)/100.0)+((ma2*a2p)/100.0)+((ma3*a3p)/100.0)) );}catch(Exception e){a=0;}

tm=m+a;

if(TP==0)
{
try{ tm=(int)Math.round(((mp1*p1p)/100.0)+((mp2*p2p)/100.0) );}catch(Exception e){tm=0;}
}

if(TP==3)
{
try{ tm=(int)Math.round(((mpr1*pr1p)/100.0)+((mpr2*pr2p)/100.0)+((mpr3*pr3p)/100.0) );}catch(Exception e){tm=0;}
}

if(TP==5)
{
try{ m=(int)Math.round(((b1*c1p)/100.0) );}catch(Exception e){m=0;}
//try{ m=(int)Math.round(((b1*c1p)/100.0)+((b2*c2p)/100.0) );}catch(Exception e){m=0;}
try{ a=(int)Math.round(((ma1*a1p)/100.0) );}catch(Exception e){a=0;}
//try{ a=(int)Math.round(((ma1*a1p)/100.0)+((ma2*a2p)/100.0) +((ma3*a3p)/100.0) );}catch(Exception e){a=0;}
try{ tm=(int)Math.round(((mp1*p1p)/100.0)+((mp2*p2p)/100.0) );}catch(Exception e){tm=0;}
ttm=(int)Math.round((m*100.0/c1p)+a);
//ttm=(int)((m*100.0/(c1p+c2p))+a);
if(ttm>100) ttm=100;
//tm=(int)((tm+ttm)/2.0);
tm=(int)(m+a+tm);

}

if(tm<0) tm=0;
if(tm>100) tm=100;
mark[count1]=tm;
count1++;	
}
}catch(Exception e){}					
					rs.close();
					count=count1;
					stmt.close();
}catch(Exception e){error=(e.toString()+count+quer+sql);}
finally{connMgr.freeConnection("xavier",con);
return error; 
}
}


public String ViewAttAndMarkOrder(int ord,String subjectcode,String exam,int dno,String examyear,int semester,String fd,String td,String academicyear)
    {

	DBConnectionManager connMgr=null; Connection con =null;
	try
	{
                    connMgr = DBConnectionManager.getInstance(); 
                    con = connMgr.getConnection("xavier");
   			    	Statement stmt = con.createStatement();
					ResultSet rs;
/*					
					quer="select st.rollno,st.name,st.mark,ISNULL(ltrim(rtrim(rno.registerno)),0) as regno from (SELECT stud.rollno as rollno, stud.name as name , max(mark.mark) as mark FROM stud,student,mark, examyear WHERE stud.rollno=mark.rollno and "
					+" stud.rollno=student.rollno and student.active=1 And examyear.examno=mark.examno And stud.department="+dno+" and examyear.semester="+semester+" and examyear.exam='"+exam+"' and examyear.year=stud.academicyear and examyear.year='"+examyear+"' And mark.subjectcode='"+subjectcode+"' group by stud.rollno,stud.name) st "
				    +" left outer join (SELECT registerno.rollno,registerno FROM registerno) as rno on st.rollno = rno.rollno order BY regno,st.rollno";
*/				    
if(ord==0)
{
				    quer="select a.rollno,ltrim(rtrim(a.name)),ISNULL(ltrim(rtrim(b.mark)),-2),ISNULL(ltrim(rtrim(rno.registerno)),0) as regno from "
+" (select stud.rollno as rollno,stud.name as name from student,stud where stud.rollno=student.rollno and active=1 and departmentno="+dno+" and year="+((semester+1)/2)+" and academicyear='"+academicyear+"') a "
+" left outer join (select  rollno,mark from mark,examyear where examyear.examno=mark.examno and examyear.semester="+semester+" and examyear.exam='"+exam+"' and dno="+dno+" and examyear.year='"+academicyear+"' And mark.subjectcode='"+subjectcode+"') b on a.rollno=b.rollno "
+" left outer join (SELECT registerno.rollno,registerno FROM registerno) rno on a.rollno = rno.rollno order BY regno,a.rollno ";
}
else
{
				    quer="select a.rollno,ltrim(rtrim(a.name)),ISNULL(ltrim(rtrim(b.mark)),-2),ISNULL(ltrim(rtrim(rno.registerno)),0) as regno from "
+" (select stud.rollno as rollno,stud.name as name from student,stud where stud.rollno=student.rollno and active=1 and departmentno="+dno+" and year="+((semester+1)/2)+" and academicyear='"+academicyear+"') a "
+" left outer join (select  rollno,mark from mark,examyear where examyear.examno=mark.examno and examyear.semester="+semester+" and examyear.exam='"+exam+"' and dno="+dno+"  and examyear.year='"+academicyear+"' And mark.subjectcode='"+subjectcode+"') b on a.rollno=b.rollno "
+" left outer join (SELECT registerno.rollno,registerno FROM registerno) rno on a.rollno = rno.rollno order BY a.rollno ";
}

					count1=0;
					rs = stmt.executeQuery(quer);
					while(rs.next())
						{
						rollno[count1]=rs.getInt(1);
						name[count1]=rs.getString(2);
						mark[count1]=rs.getInt(3);
			            regno[count1]=rs.getString(4);
						count1++;	
						}
					rs.close();
					count=count1;
					
					for (int j=0; j<count1;j++)
					{
                        sql="select count(*) from attendance.dbo.log where date between '"+fd+"' and '"+td+"' and subcode='"+subjectcode+"' and id in(select id from subjectidentify where semester="+semester+" and dno="+dno+" and academicyear='"+academicyear+"')";
  						rs = stmt.executeQuery(sql);
						if(rs.next())
						{
						tp[j]=rs.getInt(1);
						}
						rs.close();
                        sql="select count(*) from attendance.dbo.attendance a,attendance.dbo.log b where a.date=b.date  "+
							"and rollno="+rollno[j]+" and a.date between '"+fd+"' and '"+td+"' and subcode='"+subjectcode+"' "+
							"and hours like '%'+ltrim(rtrim(str(period)))+'%' and id in(select id from subjectidentify where semester="+semester+" and dno="+dno+" and academicyear='"+academicyear+"')";
  						rs = stmt.executeQuery(sql);
						if(rs.next())
						{
						ap[j]=tp[j]-rs.getInt(1);
						}
						rs.close();

					}
					quer="SELECT substring(('000'+ltrim(rtrim(str(day(date))))),len('000'+ltrim(rtrim(str(day(date)))))-1,2)+'/'+substring(('000'+ltrim(rtrim(str(month(date))))),len('000'+ltrim(rtrim(str(month(date)))))-1,2)+'/'+ltrim(rtrim(str(year(date)))),max(marklogin.staffid),max(name) FROM staff,marklogin, examyear WHERE examyear.examno=marklogin.examno "+
					"and staff.staffid=marklogin.staffid and examyear.semester="+semester+" and examyear.exam='"+exam+"' and marklogin.subjectcode='"+subjectcode+
					"' and examyear.year='"+examyear+"' and examyear.dno="+dno+" group by date,marklogin.staffid ORDER BY date desc";
					rs = stmt.executeQuery(quer);
					logcount=0;
					while(rs.next())
						{
						logdate[logcount]=rs.getString(1);
						logstaffid[logcount]=rs.getString(2);
						logstaffname[logcount]=rs.getString(3);
						logcount++;	
						}
					rs.close();

					stmt.close();
}catch(Exception e){error=(e.toString()+count+quer+sql);}
finally{connMgr.freeConnection("xavier",con);
return error; 
}
}

public String ViewAttAndMarkA(String subjectcode,String exam,int dno,String examyear,int semester,String fd,String td,String academicyear, int a)
    {
	String m="mark";
	if(a==0) m="mark";
	else m="marka";

	DBConnectionManager connMgr=null; Connection con =null;
	try
	{
                    connMgr = DBConnectionManager.getInstance(); 
                    con = connMgr.getConnection("xavier");
   			    	Statement stmt = con.createStatement();
					ResultSet rs;
/*					
					quer="select st.rollno,st.name,st.mark,ISNULL(ltrim(rtrim(rno.registerno)),0) as regno from (SELECT stud.rollno as rollno, stud.name as name , max(mark.mark) as mark FROM stud,student,mark, examyear WHERE stud.rollno=mark.rollno and "
					+" stud.rollno=student.rollno and student.active=1 And examyear.examno=mark.examno And stud.department="+dno+" and examyear.semester="+semester+" and examyear.exam='"+exam+"' and examyear.year=stud.academicyear and examyear.year='"+examyear+"' And mark.subjectcode='"+subjectcode+"' group by stud.rollno,stud.name) st "
				    +" left outer join (SELECT registerno.rollno,registerno FROM registerno) as rno on st.rollno = rno.rollno order BY regno,st.rollno";
*/		

quer="select theoryorpractical from subjecthandled,subjectidentify,syllabus where "
+" subjecthandled.id=subjectidentify.id and subjecthandled.id=syllabus.id and subjecthandled.subcode=subjectcode and mainorsub='M' "
+" and dno="+dno+" and subcode='"+subjectcode+"' and academicyear='"+examyear+"' and semester="+semester;

int ajk=0;
int jk=0;

rs = stmt.executeQuery(quer);
				if(rs.next())
						{
								ajk=rs.getInt(1);
						}
					rs.close();

				    quer="select a.rollno,ltrim(rtrim(a.name)),ISNULL(ltrim(rtrim(b.mark)),-2),ISNULL(ltrim(rtrim(rno.registerno)),0) as regno from "
+" (select stud.rollno as rollno,stud.name as name from student,stud where stud.rollno=student.rollno and active=1 and departmentno="+dno+" and year="+((semester+1)/2)+" and academicyear='"+academicyear+"') a "
+" left outer join (select  rollno,mark from "+m+",examyear where examyear.examno="+m+".examno and examyear.semester="+semester+" and examyear.exam='"+exam+"' and examyear.year='"+academicyear+"' And "+m+".subjectcode='"+subjectcode+"') b on a.rollno=b.rollno "
+" left outer join (SELECT registerno.rollno,registerno FROM registerno) rno on a.rollno = rno.rollno order BY regno,a.rollno ";


					count1=0;
					rs = stmt.executeQuery(quer);
					while(rs.next())
						{
						rollno[count1]=rs.getInt(1);
						name[count1]=rs.getString(2);
						mark[count1]=rs.getInt(3);
			            regno[count1]=rs.getString(4);

			            if((ajk!=4) && (mark[count1]==-2)) mark[count1]=-1;

						count1++;	
						}
					rs.close();


					count=count1;
					
					for (int j=0; j<count1;j++)
					{
jk=0;
if(ajk==4)
{
sql="select * from subjectelective where rollno="+rollno[j]+" and subcode='"+subjectcode+"' and id in "
+" (select id from subjectidentify where dno="+dno+" and subcode='"+subjectcode+"' and academicyear='"+examyear+"' and semester="+semester+")";
				rs = stmt.executeQuery(sql);
						if(rs.next())
						{
						jk=1;
						}
						rs.close();
}
if((jk==1) && (mark[j]==-2)) mark[j]=-1;
					}
					quer="SELECT substring(('000'+ltrim(rtrim(str(day(date))))),len('000'+ltrim(rtrim(str(day(date)))))-1,2)+'/'+substring(('000'+ltrim(rtrim(str(month(date))))),len('000'+ltrim(rtrim(str(month(date)))))-1,2)+'/'+ltrim(rtrim(str(year(date)))),max(marklogin.staffid),max(name) FROM staff,marklogin, examyear WHERE examyear.examno=marklogin.examno "+
					"and staff.staffid=marklogin.staffid and examyear.semester="+semester+" and examyear.exam='"+exam+"' and marklogin.subjectcode='"+subjectcode+
					"' and examyear.year='"+examyear+"' and examyear.dno="+dno+" group by date,marklogin.staffid ORDER BY date desc";
					rs = stmt.executeQuery(quer);
					logcount=0;
					while(rs.next())
						{
						logdate[logcount]=rs.getString(1);
						logstaffid[logcount]=rs.getString(2);
						logstaffname[logcount]=rs.getString(3);
						logcount++;	
						}
					rs.close();

					stmt.close();
}catch(Exception e){error=(e.toString()+count+quer+sql);}
finally{connMgr.freeConnection("xavier",con);
return error; 
}
}
	public String ViewAttMark(String subjectcode,String exam,int order,int dno,String examyear,int semester)
		    {

	DBConnectionManager connMgr=null; Connection con =null;
	try
	{
                       connMgr = DBConnectionManager.getInstance(); 
                       con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;
					count=0;
					quer="SELECT stud.rollno, stud.name, max(markatt.hours),max(markatt.thours) FROM stud,student,markatt, examyear "
+"WHERE stud.rollno=markatt.rollno and stud.rollno=student.rollno and student.active=1 And examyear.examno=markatt.examno "
+"And stud.department="+dno+" and examyear.semester="+semester+" and examyear.exam='"+exam+"' and examyear.year=stud.academicyear "
+"and examyear.year='"+examyear+"' And markatt.subjectcode='"+subjectcode+"' group by stud.rollno,stud.name order BY stud.rollno";

				
					if(order==1)
					quer="SELECT stud.rollno, stud.name, max(markatt.hours),max(markatt.thours) FROM stud,student,markatt, examyear "
+"WHERE stud.rollno=markatt.rollno and stud.rollno=student.rollno and student.active=1 And examyear.examno=markatt.examno "
+"And stud.department="+dno+" and examyear.semester="+semester+" and examyear.exam='"+exam+"' and examyear.year=stud.academicyear "
+"and examyear.year='"+examyear+"' And markatt.subjectcode='"+subjectcode+"' group by stud.rollno,stud.name order BY stud.rollno";

					else
					quer="SELECT stud.rollno, stud.name, max(markatt.hours),max(markatt.thours) FROM stud,student,markatt, examyear "
+"WHERE stud.rollno=markatt.rollno and stud.rollno=student.rollno and student.active=1 And examyear.examno=markatt.examno "
+"And stud.department="+dno+" and examyear.semester="+semester+" and examyear.exam='"+exam+"' and examyear.year=stud.academicyear "
+"and examyear.year='"+examyear+"' And markatt.subjectcode='"+subjectcode+"' group by stud.rollno,stud.name order BY stud.rollno";
					rs = stmt.executeQuery(quer);
					while(rs.next())
						{

						rollno[count]=rs.getInt(1);
						name[count]=rs.getString(2);
						hours[count]=rs.getInt(3);
			            thours[count]=rs.getInt(4);
						count++;	
						}
					rs.close();
					
				
quer="SELECT substring(('000'+ltrim(rtrim(str(day(date))))),len('000'+ltrim(rtrim(str(day(date)))))-1,2)+'/'+substring(('000'+ltrim(rtrim(str(month(date))))),len('000'+ltrim(rtrim(str(month(date)))))-1,2)+'/'+ltrim(rtrim(str(year(date)))),max(markattlogin.staffid),max(name) FROM staff,markattlogin, examyear WHERE examyear.examno=markattlogin.examno "+
"and staff.staffid=markattlogin.staffid and examyear.semester="+semester+" and examyear.exam='"+exam+"' and markattlogin.subjectcode='"+subjectcode+
"' and examyear.year='"+examyear+"' and examyear.dno="+dno+" group by date,markattlogin.staffid ORDER BY date desc";
					rs = stmt.executeQuery(quer);
					logcount=0;
					while(rs.next())
						{
						logdate[logcount]=rs.getString(1);
						logstaffid[logcount]=rs.getString(2);
						logstaffname[logcount]=rs.getString(3);
						logcount++;	
						}
					rs.close();


					stmt.close();
}catch(Exception e){error=(e.toString()+count+quer);}
finally{connMgr.freeConnection("xavier",con);
return error; 
}
}


public String exstatus(String subcode1,String exam1,int dno1,String exyear1,int sem1)
{
String jk="N";
String dt="";
int wday=1;
int ldays=0;
int diff=0;
int allowed=3;
int ent=0;
error="C";
Calendar time = new GregorianCalendar();  
//Calendar time = new Calendar();  
//DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT);  


//  System.out.println("With " + i + " days to produce, the due date is : " + time.getTime());  

	DBConnectionManager connMgr=null; Connection con =null;
	try
	{
                       connMgr = DBConnectionManager.getInstance(); 
                       con = connMgr.getConnection("xavier");
  					    Statement stmt;
					    ResultSet rs;
                    sql="select datepart (dw,date),datediff(dd,date,getdate()),status,date,ltrim(str(day(date)))+'/'+ltrim(str(month(date)))+'/'+ltrim(str(year(date))),examyear.examno from examyear,examdate where examdate.examno=examyear.examno and year='"+exyear1+"' and exam='"+exam1+"' and semester="+sem1+" and dno="+dno1+" and subcode='"+subcode1+"'";
                    stmt= con.createStatement();
                    
					rs = stmt.executeQuery(sql);
					if(rs.next())
					{
						wday=rs.getInt(1);
						diff=rs.getInt(2);
						jk=rs.getString(3);
						dt=rs.getString(4);
						exdt=rs.getString(5);
						examno=rs.getInt(6);
						ent=1;
					}
					rs.close();
error=dt;
SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
java.util.Date date1 = formatter.parse(dt);  			
time.setTime(date1);  

  for (int promise_days=3 ; promise_days!=0 ; promise_days--) {  
    time.add(Calendar.DATE, 1);  
    	
     SimpleDateFormat formatter1 = new SimpleDateFormat("MM/dd/yyyy");
     ddt=formatter1.format(time.getTime());  
 	 ldays=0;
                    sql="select count(*)  from attendance.dbo.holidaymaster where noofperiods=0 and fdate='"+ddt+"'";
					rs = stmt.executeQuery(sql);
					if(rs.next())
					{
						ldays=rs.getInt(1);
					}
					rs.close();
    if (ldays>0) {  
      	promise_days++;
    }  
    if (time.get(Calendar.DAY_OF_WEEK)==1) {  
      // Sunday == 1.  
      time.add(Calendar.DATE, 1);  
    }  
    	
  }  
    SimpleDateFormat formatter2 = new SimpleDateFormat("dd/MM/yyyy");
 ddt=formatter2.format(time.getTime());  

 	ldays=0;
                    sql="select count(*)  from attendance.dbo.holidaymaster where noofperiods=0 and fdate between '"+dt+"' and getdate()";
					rs = stmt.executeQuery(sql);
					if(rs.next())
					{
						ldays=rs.getInt(1);
					}
					rs.close();

/*
					
sql="DECLARE @StartDate DATE = '"+dt+"'; DECLARE @EndDate DATE = getdate(); "
+" WITH DateRange AS (   SELECT @StartDate AS Date   UNION ALL   SELECT DATEADD(DAY, 1, Date) "
+" FROM DateRange   WHERE DATEADD(DAY, 1, Date) <= @EndDate ) "
+" SELECT COUNT(*) AS SundayCount FROM DateRange WHERE DATENAME(WEEKDAY, Date) = 'Sunday'";

					rs = stmt.executeQuery(sql);
					if(rs.next())
					{
						ldays+=rs.getInt(1);
					}
					rs.close();
*/

        String startDateString = dt;
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        try {
            Date startDate = dateFormat.parse(startDateString);

            int sundayCount = countSundays(startDate);
            ldays+=sundayCount;
        } catch (ParseException e) {}





					stmt.close();
//sun					if((wday==1) && (ldays>=4)) allowed=5;
/*
					if(wday<=4) allowed=3;
					if((wday==2) && (ldays>=3)) allowed=5;
					if((wday==3) && (ldays>=2)) allowed=5;
					if((wday==4) && (ldays>=1)) allowed=5;
					if(wday==5) allowed=4;
					if(wday==6) allowed=4;
					if(wday==7) allowed=4;
*/
//five days allowed					if(wday<=2) allowed=5; else allowed=6;

//one week allowed


//					if((wday==2) && (ldays>=3)) allowed=5;
//					if((wday==3) && (ldays>=2)) allowed=5;
//					if((wday==4) && (ldays>=1)) allowed=5;
//					if(wday==5) allowed=6;
//					if(wday==6) allowed=6;
//					if(wday==7) allowed=6;

					allowed=7;					
					allowed=allowed+ldays;
					error="E";
					if(diff<=allowed) error="N"; else error="C";
					if(jk.charAt(0)=='O')	error="O";
					if(jk.charAt(0)=='E')
					{
						if(diff<=allowed) 
						  error="E";
					    else
						  error="C";
					}
					if(diff<=0) error="C";
//					if(ent==0) error="C";
//				    if(diff<=allowed) error="N";
}catch(Exception e){error="C"+(e.toString()+count+sql);}
finally{connMgr.freeConnection("xavier",con);
return error; 
}
}

public int countSundays(Date startDate) {
        Calendar startCal = Calendar.getInstance();
        startCal.setTime(startDate);

        Calendar currentDate = Calendar.getInstance();
        int sundayCount = 0;

        while (!startCal.after(currentDate)) {
            if (startCal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                sundayCount++;
            }
            startCal.add(Calendar.DAY_OF_MONTH, 1);
        }
        return sundayCount;
    }


	public int[] getRollno()
	{
		return this.rollno;
	}
	public String[] getName()
	{
		return this.name;
	}
	public int[] getMark()
	{
		return this.mark;
	}
	public int[] getMark1()
	{
		return this.mark1;
	}

	public int[] gettp()
	{
		return this.tp;
	}
	public int[] getap()
	{
		return this.ap;
	}

	public int[] getHours()
	{
		return this.hours;
	}
	public int[] getThours()
	{
		return this.thours;
	}

	public int getexamno()
	{
		return this.examno;
	}

	public int getscount()
	{
		return this.scount;
	}

	public int getCount()
	{
		return this.count;
	}
	public int getCount1()
	{
		return this.count1;
	}

	public int getLogcount()
	{
		return this.logcount;
	}
	public String[] getLogdate()
	{
		return this.logdate;
	}
	public String[] getLogstaffid()
	{
		return this.logstaffid;
	}
	public String[] getLogstaffname()
	{
		return this.logstaffname;
	}
	public String getquer()
	{
		return this.quer;
	}
	public String getquer1()
	{
		return this.quer1;
	}
	public String getexdt()
	{
		return this.exdt;
	}
	public String getddt()
	{
		return this.ddt;
	}
	public int[] getattper()
	{
		return this.attper;
	}
	public String[] getParentName()
	{
		return this.parentname;
	}
	public String[] getParenthouse()
	{
		return this.parenthouse;
	}
	public String[] getParenttown()
	{
		return this.parenttown;
	}
	public String[] getParentdistrict()
	{
		return this.parentdistrict;
	}
	public String[] getregno()
	{
		return this.regno;
	}
	public String[] getParentstate()
	{
		return this.parentstate;
	}
	public String[] getParentpincode()
	{
		return this.parentpincode;
	}
	public int gettothrs()
	{
		return this.tothrs;
	}

public void setfdate(String fdate) 
{ 
this.fdate = fdate; 
} 
public String getfdate() 
{ 
return this.fdate; 
} 
public void setfday(String fday) 
{ 
this.fday = fday; 
} 
public String getfday() 
{ 
return this.fday; 
} 
public void setfmonth(String fmonth) 
{ 
this.fmonth = fmonth; 
} 
public String getfmonth() 
{ 
return this.fmonth; 
} 
public void setfyear(String fyear) 
{ 
this.fyear = fyear; 
} 
public String getfyear() 
{ 
return this.fyear; 
} 
public void settdate(String tdate) 
{ 
this.tdate = tdate; 
} 
public String gettdate() 
{ 
return this.tdate; 
} 
public void settday(String tday) 
{ 
this.tday = tday; 
} 
public String gettday() 
{ 
return this.tday; 
} 
public void settmonth(String tmonth) 
{ 
this.tmonth = tmonth; 
} 
public String gettmonth() 
{ 
return this.tmonth; 
} 
public void settyear(String tyear) 
{ 
this.tyear = tyear; 
} 
public String gettyear() 
{ 
return this.tyear; 
} 
public String getsql() { return this.sql; } 
public void setrepin(String[] repin) {this.repin=repin;}  public String[] getrepin() {	return this.repin;}
public void setreport(String[][] report) {this.report=report;}  public String[][] getreport() {	return this.report;}

	}
	