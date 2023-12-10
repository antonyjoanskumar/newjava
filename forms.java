package sxcce;
import java.sql.*;
import java.util.StringTokenizer;
import java.util.Date;
import java.io.*;
import dd.DBConnectionManager;

public class forms
{
	String repin[] = new String[20];
	String report[][] = new String[20][10000];
	String report1[][] = new String[20][10000];
    String fdate="";
	String tdate="";
	String d1="";
	String m1="";
	String y1="";
	String str1="";
	int count=0;
	int count1=0;
	String sql="";	
	String error="";			

public String coursefile(int ugpg)
{
int i=0;
count=0;
String tp="1,4,5";
if(repin[3].trim().equals("IV"))
tp="0,1,3,4,5";
else if(repin[3].trim().equals("V"))
tp="0";
else
tp="1,4,5";


try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;
if(ugpg==1)
{
sql="select staff.staffid,name,subjectcode,subjectname from subjecthandled,syllabus,staff,department "+
"where staff.staffid=subjecthandled.staffid and department.dno=staff.dno and staff.dno in("+repin[0]+") and "+
"syllabus.id=subjecthandled.id and subjectcode=subcode and syllabus.id in "+
"(select id from subjectidentify where semester in ("+repin[1]+") and academicyear='"+repin[2]+"' and dno in(1,2,3,4,5,8,14,17,27,34,91,92,93,94,95,96,97,98)) "+
"and  theoryorpractical in("+tp+") and subcode <> 'LIB12' and subjecthandled.mainorsub='M' order by syllabus.id,subjectcode";
}
else
{
sql="select staff.staffid,name,subjectcode,subjectname from subjecthandled,syllabus,staff,department "+
"where staff.staffid=subjecthandled.staffid and department.dno=staff.dno and staff.dno in ("+repin[0]+") and "+
"syllabus.id=subjecthandled.id and subjectcode=subcode and syllabus.id in "+
"(select id from subjectidentify where semester in ("+repin[1]+") and academicyear='"+repin[2]+"' and dno not in(1,2,3,4,5,8,14,17,27,34,91,92,93,94,95,96,97,98)) "+
"and  theoryorpractical in("+tp+") and subcode <> 'LIB12' and subjecthandled.mainorsub='M' order by syllabus.id,subjectcode";
}
rs = stmt.executeQuery(sql);

while(rs.next())
{
	for(i=1;i<=4;i++)
	{
		report[i-1][count]=rs.getString(i);
	}
	count++;
}
rs.close();

stmt.close();
connMgr.freeConnection("xavier",con);
error= "success"; 
}catch(Exception e){error=(e.toString()+count+i+sql);}
return error; 
}

public String answerscript(int ugpg)
{
int i=0;
count=0;
String tp="1,4,5";
if(repin[3].trim().equals("III"))
tp="0,1,3,4,5";
else
tp="1,4,5";


try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;
if(ugpg==1)
{
sql="select staff.staffid,name,subjectcode,subjectname from subjecthandled,syllabus,staff,department "+
"where staff.staffid=subjecthandled.staffid and department.dno=staff.dno and "+
"syllabus.id=subjecthandled.id and subjectcode=subcode and syllabus.id in "+
"(select id from subjectidentify where semester in ("+repin[1]+") and academicyear='"+repin[2]+"' and dno in ("+repin[0]+")) "+
"and  theoryorpractical in("+tp+") and subcode <> 'LIB12' and subjecthandled.mainorsub='M' order by syllabus.id,subjectcode";
}
else
{
sql="select staff.staffid,name,subjectcode,subjectname from subjecthandled,syllabus,staff,department "+
"where staff.staffid=subjecthandled.staffid and department.dno=staff.dno and "+
"syllabus.id=subjecthandled.id and subjectcode=subcode and syllabus.id in "+
"(select id from subjectidentify where semester in ("+repin[1]+") and academicyear='"+repin[2]+"' and dno not in ("+repin[0]+")) "+
"and  theoryorpractical in("+tp+") and subcode <> 'LIB12' and subjecthandled.mainorsub='M' order by syllabus.id,subjectcode";
}
rs = stmt.executeQuery(sql);

while(rs.next())
{
	for(i=1;i<=4;i++)
	{
		report[i-1][count]=rs.getString(i);
	}
	count++;
}
rs.close();

stmt.close();
connMgr.freeConnection("xavier",con);
error= "success"; 
}catch(Exception e){error=(e.toString()+count+i+sql);}
return error; 
}

public String workload(int sem)
{
int i=0;
count=0;
String tp="1,4,5";
String sems="1,3,5,7";

tp="0,1,3,4,5";

if(sem==1)
sems="1,3,5,7";
else
sems="2,4,6,8";


try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;
sql="select staff.staffid,name,subjectcode,subjectname,theoryorpractical,syllabus.id from subjecthandled,syllabus,staff,department "+
"where staff.staffid=subjecthandled.staffid and department.dno=staff.dno and staff.dno in("+repin[0]+") and "+
"syllabus.id=subjecthandled.id and subjectcode=subcode and syllabus.id in "+
"(select id from subjectidentify where semester in ("+sems+") and academicyear='"+repin[2]+"' )"+
"and  theoryorpractical in("+tp+") and subcode <> 'LIB12' and subjecthandled.mainorsub='M' order by staff.staffid";
rs = stmt.executeQuery(sql);

while(rs.next())
{
	for(i=1;i<=6;i++)
	{
		report[i-1][count]=rs.getString(i);
	}
	count++;
}
rs.close();

for(i=0;i<count;i++)
{
sql="select ((semester+1)/2),sf from subjectidentify,department where department.dno=subjectidentify.dno and id="+report[5][i];
rs = stmt.executeQuery(sql);

if(rs.next())
{
report[6][i]=rs.getString(1);	
report[7][i]=rs.getString(2);	
}
}

stmt.close();
connMgr.freeConnection("xavier",con);
error= "success"; 
}catch(Exception e){error=(e.toString()+count+i+sql);}
return error; 
}

public String evaluation()
{
int i=0;
count=0;
String tp="1,4,5";
if(repin[3].trim().equals("III"))
tp="0,1,3,4,5";
else
tp="1,4,5";


try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;
sql="select staff.staffid,name,subjectcode,subjectname from subjecthandled,syllabus,staff,department "+
"where staff.staffid=subjecthandled.staffid and department.dno=staff.dno and "+
"syllabus.id=subjecthandled.id and subjectcode=subcode and syllabus.id in "+
"(select id from subjectidentify where semester in ("+repin[1]+") and academicyear='"+repin[2]+"' and dno in ("+repin[0]+")) "+
"and  theoryorpractical in("+tp+") and subcode <> 'LIB12' and subjecthandled.mainorsub='M' order by syllabus.id,subjectcode";
rs = stmt.executeQuery(sql);

while(rs.next())
{
	for(i=1;i<=4;i++)
	{
		report[i-1][count]=rs.getString(i);
	}
	count++;
}
rs.close();

stmt.close();
connMgr.freeConnection("xavier",con);
error= "success"; 
}catch(Exception e){error=(e.toString()+count+i+sql);}
return error; 
}


public String feedbackentrep()
{
int i=0;
count=0;
String tp="0,1,3,4,5";
int sub1=0;
String subtype="0,1,3,4,5";
if(repin[3].equals("MID-ODD")) subtype="0,1,4,5";
if(repin[3].equals("MID-EVEN")) subtype="0,1,4,5";
try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;
sql="select max(subjectidentify.dno) t1,max(sf),max(semester) t2,(count(*)) t3 from syllabus,subjectidentify,department where department.dno=subjectidentify.dno and syllabus.id=subjectidentify.id "
+"and semester in ("+repin[1]+") and subjectidentify.dno in ("+repin[4]+") and academicyear='"+repin[2]+"' and theoryorpractical in ("+subtype+") and "
+"subjectcode <> 'LIB12' group by subjectidentify.dno,semester order by t1,t2";

rs = stmt.executeQuery(sql);

while(rs.next())
{
	for(i=1;i<=4;i++)
	{
		report[i-1][count]=rs.getString(i);
	}
	count++;
}
rs.close();



for(int j=0;j<count;j++)
{

int jk=0;
sql="select max(subjectidentify.dno) t1,max(sf),max(semester) t2,(count(*)) t3 from syllabus,subjectidentify,department where department.dno=subjectidentify.dno and syllabus.id=subjectidentify.id "
+"and semester="+report[2][j]+" and subjectidentify.dno="+report[0][j]+" and academicyear='"+repin[2]+"' and theoryorpractical in (4) and "
+"subjectcode <> 'LIB12' group by subjectidentify.dno,semester order by t1,t2";
rs = stmt.executeQuery(sql);
if(rs.next())
{
		jk=rs.getInt(4);
}
rs.close();

jk=Integer.parseInt(report[3][j])-jk;
report[3][j]=jk+"";
	
	
sub1=0;

sql="select count(*) from subjectelective,subjectidentify where subjectidentify.id=subjectelective.id  "
+"and semester="+report[2][j]+" and subjectidentify.dno="+report[0][j]+" and academicyear='"+repin[2]+"' group by rollno ";
rs = stmt.executeQuery(sql);
if(rs.next())
{
		sub1=rs.getInt(1);
}
rs.close();

sub1+=Integer.parseInt(report[3][j]);
report[3][j]=sub1+"";

sql="select count(*) from student,stud where student.rollno=stud.rollno and active=1 and "
+"year=("+report[2][j]+"+1)/2 and departmentno="+report[0][j]+" and academicyear='"+repin[2]+"'";

rs = stmt.executeQuery(sql);
if(rs.next()) report[4][j]=rs.getString(1); 
rs.close();
	
sql="select count(*) from (select max(rollno) t1,isnull(COUNT(*),0) t2 from feedbackenteredornot where id in (select id from subjectidentify where "
+"semester="+report[2][j]+" and dno="+report[0][j]+" and academicyear='"+repin[2]+"'  and oddeven='"+repin[3]+"') group by rollno having count(rollno)<"+report[3][j]+") as q1";
rs = stmt.executeQuery(sql);
if(rs.next()) report[5][j]=rs.getString(1); 
rs.close();

sql="select count(*) from (select max(rollno) t1,isnull(COUNT(*),0) t2 from feedbackenteredornot where id in (select id from subjectidentify where "
+"semester="+report[2][j]+" and dno="+report[0][j]+" and academicyear='"+repin[2]+"'  and oddeven='"+repin[3]+"') group by rollno having count(rollno)>="+report[3][j]+") as q1";
rs = stmt.executeQuery(sql);
if(rs.next()) report[6][j]=rs.getString(1); 
rs.close();

report[7][j]=(Integer.parseInt(report[4][j])-(Integer.parseInt(report[5][j])+Integer.parseInt(report[6][j])))+"";

}
count1=0;

sql="select max(stafffeedback.id),max(stafffeedback.staffid),max(name),max(sf),max(semester),max(stafffeedback.subjectcode), "
+"max(subjectname),(sum(ansb)*25+sum(ansc)*50+sum(ansd)*75+sum(anse)*100.0)/sum(ansa+ansb+ansc+ansd+anse) per "
+"from stafffeedback,staff,department,syllabus,subjectidentify where staff.staffid=stafffeedback.staffid "
+"and subjectidentify.id=syllabus.id and syllabus.subjectcode=stafffeedback.subjectcode and subjectidentify.dno=department.dno "
+"and subjectidentify.id=stafffeedback.id and theoryorpractical in (1,4,5) and academicyear='"+repin[2]+"' and semester in ("+repin[1]+")"
+"and staff.dno in ("+repin[0]+") and oddeven='"+repin[3]+"' group by stafffeedback.id,stafffeedback.staffid order by max(stafffeedback.staffid),per desc";

rs = stmt.executeQuery(sql);

while(rs.next())
{
	for(i=1;i<=8;i++)
	{
		report1[i-1][count1]=rs.getString(i);
	}
	count1++;
}
rs.close();


stmt.close();
connMgr.freeConnection("xavier",con);
error= "success"; 
}catch(Exception e){error=(e.toString()+count+i+sql);}
return error; 
}


public String MentorReport()
{
int i=0;
count=0;
try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;
sql="select tutor.staffid,max(staff.name), min(tutor.rollno) first,max(tutor.rollno) last,max(year) yr,max(sf) sf from tutor,staff,stud,department "+
"where tutor.staffid=staff.staffid and tutor.rollno=stud.rollno and stud.department=department.dno and stud.academicyear='"+repin[2]+"' and tutor.academicyear='"+repin[2]+"' "+
"and staff.dno in ("+repin[0]+") group by tutor.staffid,sf,year order by  sf,yr";

rs = stmt.executeQuery(sql);

while(rs.next())
{
	for(i=1;i<=6;i++)
	{
		report[i-1][count]=rs.getString(i);
	}
	count++;
}
rs.close();

stmt.close();
connMgr.freeConnection("xavier",con);
error= "success"; 
}catch(Exception e){error=(e.toString()+count+i+sql);}
return error; 
}


public String studrep(int dno,int year)
{
int i=0;
count=0;
try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;
sql="select a.rollno,isnull(b.registerno,'-'),a.name from (select stud.rollno,stud.name from student,stud "
+" where stud.rollno=student.rollno and active=1 and academicyear=(select academicyear from academicyear) and department="+dno+" and year="+year+") a "
+" LEFT OUTER JOIN registerno b on a.rollno=b.rollno order by a.rollno";

rs = stmt.executeQuery(sql);

while(rs.next())
{
	for(i=1;i<=3;i++)
	{
		report[i-1][count]=rs.getString(i);
	}
	count++;
}
rs.close();

stmt.close();
connMgr.freeConnection("xavier",con);
error= "success"; 
}catch(Exception e){error=(e.toString()+count+i+sql);}
return error; 
}



public void setrepin(String[] repin) {this.repin=repin;}  public String[] getrepin() {	return this.repin;}
public void setreport(String[][] report) {this.report=report;}  public String[][] getreport() {	return this.report;}
public void setreport1(String[][] report1) {this.report1=report1;}  public String[][] getreport1() {	return this.report1;}
public void setcount(int count) {this.count=count;} public int getcount(){	return this.count;}
public void setcount1(int count1) {this.count1=count1;} public int getcount1(){	return this.count1;}


}




	
	