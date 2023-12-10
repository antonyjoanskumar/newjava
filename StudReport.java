package sxcce;
import java.util.Date;
import java.sql.*;
import java.util.StringTokenizer;
public class StudReport
	{
		String report[][] = new String[40][3000];
		String dno="";
		String year="";
		String order="";
		String academicyear="";
		int count=0;

String qtrainingin="'1'";
String qholidaysin="'1'";
	
String qx="70";
String qxii="70";
String qcgpa="6.5";
String qarrears="0";

		
String dnoin=""; 
String activein=""; 
String yearin=""; 
String ccategoryin=""; 
String communityin=""; 
String directorlateralin=""; 
String mediumin=""; 
String religionin=""; 
String sexin=""; 
String statein=""; 
String ucategoryin="";
String subject="Mathematics";

String d1="";
String m1="";
String y1="";
String fd1="";
String error1="";
public String RemarksInsert(String fd,int rollno,String remarks,String staffid)
{
                String sql="";

				try
					{
					Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
					Connection con = DriverManager.getConnection("jdbc:odbc:xavier","sxcce","tamil5india6");
					Statement stmt = con.createStatement();
					    ResultSet rs;
					    PreparedStatement ps;
			StringTokenizer st1 = new StringTokenizer(fd,"/");
		    d1=st1.nextToken();
		    m1=st1.nextToken();
		    y1=st1.nextToken();
			fd1=m1+"/"+d1+"/"+y1;
					
					
                    sql="INSERT into studremarks values(?,?,?,?)";

					ps=con.prepareStatement(sql);
					ps.setString(1,fd1);
					ps.setInt(2,rollno);
					ps.setString(3,remarks);
					ps.setString(4,staffid);
					int j = ps.executeUpdate();
					ps.close();
     
                    stmt.close();
//					con.close();
					error1="...";
					}catch(Exception e){return error1+e.toString();}
					return error1;

}

public String remview(String rollno)
{
count=0;
String quer="select rtrim(ltrim(str(day(date))))+'/'+ltrim(rtrim(str(month(date))))+'/'+ltrim(rtrim(str(year(date)))), "+
"remarks,enteredby from studremarks where rollno ="+rollno+" order by date ";

				try
					{
					Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
					Connection con = DriverManager.getConnection("jdbc:odbc:xavier","sxcce","tamil5india6");
					Statement stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery(quer);
					while(rs.next())
					  {
					  	report[0][count]=(count+1)+"";
					  	for(int i=1;i<4;i++)
					  	   report[i][count]=rs.getString(i);
					  	count++;   
					  }
					rs.close();
					stmt.close();
					con.close();
					}catch(Exception e){return quer+e.toString();}
					return quer;
}

public String hodremview(String Staffid,String rollno)
{
count=0;
String quer="select rtrim(ltrim(str(day(date))))+'/'+ltrim(rtrim(str(month(date))))+'/'+ltrim(rtrim(str(year(date)))), "+
"remarks,enteredby from studremarks where rollno ="+rollno+" and enteredby='"+Staffid+"' order by date ";

				try
					{
					Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
					Connection con = DriverManager.getConnection("jdbc:odbc:xavier","sxcce","tamil5india6");
					Statement stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery(quer);
					while(rs.next())
					  {
					  	report[0][count]=(count+1)+"";
					  	for(int i=1;i<4;i++)
					  	   report[i][count]=rs.getString(i);
					  	count++;   
					  }
					rs.close();
					stmt.close();
					con.close();
					}catch(Exception e){return quer+e.toString();}
					return quer;
}



public String report5()
		    {

String quer="";
				try
					{
					Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
					Connection con = DriverManager.getConnection("jdbc:odbc:xavier","sxcce","tamil5india6");
					Statement stmt = con.createStatement();
					ResultSet rs ;
					for(int i=0;i<count;i++)
					{
					  	report[6][i]="1";
					  	report[7][i]="1";
					  	report[8][i]="1";
					  	report[9][i]="1";
					  	report[10][i]="1";
						
quer="select marks,maxmark from qualification where rollno="+report[1][i] +" and subject='Physics'";
                    rs= stmt.executeQuery(quer);
					if(rs.next())
					  {
					  	report[6][i]=rs.getString(1);
					  	report[7][i]=rs.getString(2);
					  }
					rs.close();
					
quer="select marks,maxmark from qualification where rollno="+report[1][i] +" and subject='Chemistry'";
                    rs= stmt.executeQuery(quer);
					if(rs.next())
					  {
					  	report[8][i]=rs.getString(1);
					  	report[9][i]=rs.getString(2);
					  }
					rs.close();
try{
					  	report[10][i]=((Float.valueOf(report[4][i]).floatValue()/Float.valueOf(report[5][i]).floatValue())*50+(Float.valueOf(report[6][i]).floatValue()/Float.valueOf(report[7][i]).floatValue())*25+(Float.valueOf(report[8][i]).floatValue()/Float.valueOf(report[9][i]).floatValue())*25)+"";
					}catch(Exception e){report[10][i]="0";}
					
					}

					stmt.close();
					con.close();
					}catch(Exception e){return quer+e.toString();}
					return quer;
		    }


public String report4(String rollno)
		    {
String quer="select student.rollno,name,subject,marks,maxmark from student, "+
"qualification where student.rollno=qualification.rollno "+
"and student.rollno ="+rollno+" order by (marks) ";

				try
					{
					Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
					Connection con = DriverManager.getConnection("jdbc:odbc:xavier","sxcce","tamil5india6");
					Statement stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery(quer);
					while(rs.next())
					  {
					  	report[0][count]=(count+1)+"";
					  	for(int i=1;i<6;i++)
					  	   report[i][count]=rs.getString(i);
					  	count++;   
					  }
					rs.close();
					stmt.close();
					con.close();
					}catch(Exception e){return quer+e.toString();}
					return quer;
		    }


public String report3(int asc)
		    {

//String quer="select student.rollno,name,subject,marks,maxmark,community from student, "+
//"qualification where student.rollno=qualification.rollno and student.active=1 "+

String quer="select student.rollno,name,subject,marks,maxmark,community,cname from student, "+
"qualification,universitycategory where category=slno and student.rollno=qualification.rollno and student.active=1 " +
"and student.rollno in(select rollno from stud where "+
"department="+dno+" and year="+year+" and academicyear='"+academicyear+"') "+
"and subject='"+subject+"' order by (marks) ";

if(asc==2)quer=quer+" desc"; 


				try
					{
					Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
					Connection con = DriverManager.getConnection("jdbc:odbc:xavier","sxcce","tamil5india6");
					Statement stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery(quer);
					while(rs.next())
					  {
					  	report[0][count]=(count+1)+"";
					  	for(int i=1;i<6;i++)
					  	   report[i][count]=rs.getString(i);
					  	   report[30][count]=rs.getString(6);
					  	   report[31][count]=rs.getString(7);
					  	count++;   
					  }
					rs.close();
					stmt.close();
					con.close();
					}catch(Exception e){return quer+e.toString();}
					return quer;
		    }


		public String report2()
		    {
String quer="select distinct subject from qualification where "+
" rollno in(select rollno from stud "+
" where year="+year+" and department="+dno+" and academicyear='"+academicyear+"') order by subject";

				try
					{
					Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
					Connection con = DriverManager.getConnection("jdbc:odbc:xavier","sxcce","tamil5india6");
					Statement stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery(quer);
					while(rs.next())
					  {
					  	report[0][count]=(count+1)+"";
					  	for(int i=1;i<2;i++)
					  	   report[i][count]=rs.getString(i);
					  	count++;   
					  }
					rs.close();
					stmt.close();
					con.close();
					}catch(Exception e){return quer+e.toString();}
					return quer;
		    }

		

		public String report1()
		    {
String quer="select name,department.dname, "+
"directorlateral,apporentranceno from student,department "+
"where department.dno=student.departmentno and year(yearofjoining)=2003 "+
"order by department.dno,directorlateral,student.name";

				try
					{
					Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
					Connection con = DriverManager.getConnection("jdbc:odbc:xavier","sxcce","tamil5india6");
					Statement stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery(quer);
					while(rs.next())
					  {
					  	report[0][count]=(count+1)+"";
					  	for(int i=1;i<5;i++)
					  	   report[i][count]=rs.getString(i);
					  	count++;   
					  }
					rs.close();
					stmt.close();
					con.close();
					}catch(Exception e){return quer+e.toString();}
					return quer;
		    }
public String Report()
			{
String quer="";
if(year.equals("1")) 
{
quer="select student.rollno,'Not Available',name,ltrim(rtrim(initial))+'<br>'+ltrim(rtrim(parenthouse))+'  '+ "+
"ltrim(rtrim(parenttown))+'<br>'+ltrim(rtrim(parentdistrict))+'<br>'+ "+
"ltrim(rtrim(parentstate))+'<br>'+ltrim(rtrim(parentpincode)),sex, "+
"day(dateofbirth),month(dateofbirth),year(dateofbirth),mothertonque, "+
"universitycategory.cname,admissioncategory.cname, "+
"collegecategory.cname,religion,state,community, "+
"nationality,occupation,extracurricularactivities,medium,apporentranceno,qualification, "+
"noofattempts,noofimprovements,initial,parentorguardian,rtrim(ltrim(areacode))+'-'+phone,cell, "+
"email,rtrim(ltrim(str(day(yearofjoining))))+'/'+ltrim(rtrim(str(month(yearofjoining))))+'/'+ltrim(rtrim(str(year(yearofjoining)))),directorlateral,admissionno,caste,sf,len(remarks) "+
"from universitycategory,collegecategory,admissioncategory,department,  "+
"student where student.departmentno=department.dno and  student.active in("+activein+") and student.rollno in(select rollno from stud where "+
"stud.academicyear='"+academicyear+"' and stud.department in("+dnoin+") and stud.year in("+yearin+")) "+
//"stud.academicyear='"+academicyear+"' and stud.department="+dno+" and stud.year="+year+") "+
//stud.academicyear='2011 -- 2012' and stud.department in("+dnoin+") and stud.year in("+yearin+") 

"and student.category=universitycategory.slno "+
//"and universitycategory.slno in (1) "+
"and universitycategory.slno in ("+ucategoryin+") "+
"and collegecategory.slno in ("+ccategoryin+") "+
"and student.community in("+communityin+") "+
"and student.directorlateral in("+directorlateralin+") "+
"and student.medium in("+mediumin+") "+
"and student.religion in("+religionin+") "+
"and student.sex in("+sexin+") "+
"and student.state in("+statein+") "+
"and student.fcategory= admissioncategory.slno and student.ccategory=collegecategory.slno "+order;
}
else
{
quer="select student.rollno,'Not Available',name,ltrim(rtrim(initial))+'<br>'+ltrim(rtrim(parenthouse))+'  '+ "+
"ltrim(rtrim(parenttown))+'<br>'+ltrim(rtrim(parentdistrict))+'<br>'+ "+
"ltrim(rtrim(parentstate))+'<br>'+ltrim(rtrim(parentpincode)),sex, "+
"day(dateofbirth),month(dateofbirth),year(dateofbirth),mothertonque, "+
"universitycategory.cname,admissioncategory.cname, "+
"collegecategory.cname,religion,state,community, "+
"nationality,occupation,extracurricularactivities,medium,apporentranceno,qualification, "+
"noofattempts,noofimprovements,initial,parentorguardian,rtrim(ltrim(areacode))+'-'+phone,cell, "+
"email,rtrim(ltrim(str(day(yearofjoining))))+'/'+ltrim(rtrim(str(month(yearofjoining))))+'/'+ltrim(rtrim(str(year(yearofjoining)))),directorlateral,admissionno,caste,sf,len(remarks) "+
"from universitycategory,collegecategory,admissioncategory,department,  "+
"student where student.departmentno=department.dno and student.active in("+activein+") and student.rollno in(select rollno from stud where "+
"stud.academicyear='"+academicyear+"' and stud.department in("+dnoin+") and stud.year in("+yearin+")) "+
//"stud.academicyear='"+academicyear+"' and stud.department="+dno+" and stud.year="+year+") "+
"and student.category=universitycategory.slno "+
"and universitycategory.slno in ("+ucategoryin+") "+
"and collegecategory.slno in ("+ccategoryin+") "+
"and student.community in("+communityin+") "+
"and student.directorlateral in("+directorlateralin+") "+
"and student.medium in("+mediumin+") "+
"and student.religion in("+religionin+") "+
"and student.sex in("+sexin+") "+
"and student.state in("+statein+") "+
"and student.fcategory= admissioncategory.slno and student.ccategory=collegecategory.slno "+order;
}

				try
					{
					Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
					Connection con = DriverManager.getConnection("jdbc:odbc:xavier","sxcce","tamil5india6");
					Statement stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery(quer);
					int tripleh=0;
					while(rs.next())
					  {tripleh=0;
					  
					  	report[0][count]=(count+1)+"";
					  	
					  	
					  	for(int i=1;i<=33;i++)
					  	   report[i][count]=rs.getString(i);
					  	
					  	   
					  	tripleh=rs.getInt(34);
					  	if(report[6][count].length()<2)report[6][count]="0"+report[6][count];   
					  	if(report[7][count].length()<2)report[7][count]="0"+report[7][count];   
					  	report[6][count]=report[6][count]+"/"+report[7][count]+"/"+report[8][count];
					  	for(int i=7;i<34;i++)
					  	   report[i][count]=report[i+2][count];
					  	//if(tripleh>0)report[1][count]="**";
					  		count++;   
					  	
					  }
					rs.close();
					for(int z=0;z<count;z++){
						quer="select registerno from registerno where rollno="+report[1][z];
					rs = stmt.executeQuery(quer);
					if(rs.next())
					  {
					  	report[2][z]=rs.getString(1);
					  }
					rs.close();
				}
					
					stmt.close();
					con.close();
					}catch(Exception e){return quer+e.toString();}
					return quer;
			}
public String PlacementReport()
{
String quer="";
count=0;
quer="select stud.rollno,regno,stud.name,sf,stud.year,CONVERT(varchar,(convert(datetime, (ltrim(str(birthdate))+'/'+ltrim(str(birthmonth))+'/'+ltrim(str(birthyear))), 103)),103) dob,student.sex, "+
" studentplacement.email,studentplacement.cell,studentplacement.phone,xper,xyear,xiiper,xiiyear,pper,pyear,cper,cyear, "+
" sem1cgpa,sem2cgpa,sem3cgpa,sem4cgpa,sem5cgpa,sem6cgpa,sem7cgpa,sem8cgpa,cgpa,arrears,address1+'<br>'+address2+'<br>'+address3+'<br>'+address4 "+
" from studentplacement,department,stud,student where active=1 and stud.rollno= studentplacement.rollno and stud.rollno=student.rollno "+
" and stud.academicyear='"+academicyear+"' and stud.department in("+dnoin+") and stud.year in("+yearin+") and dno=dept "+
" and xper>="+qx+" and xiiper>="+qxii+" and cgpa>="+qcgpa+" and arrears<="+qarrears+ " and training in("+qtrainingin+") and holidays in("+qholidaysin+") "+
" and student.sex in("+sexin+") "+order;


				try
					{
					Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
					Connection con = DriverManager.getConnection("jdbc:odbc:xavier","sxcce","tamil5india6");
					Statement stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery(quer);
					while(rs.next())
					{
				  	report[0][count]=(count+1)+"";
 				  		for(int i=1;i<30;i++)
				  		{
					  	   report[i][count]=rs.getString(i);
					    }
					  	   count++;
					}
					rs.close();
					stmt.close();
					con.close();
					}catch(Exception e){return quer+e.toString();}
					return quer;
}			

public void setdno(String dno) 
{ 
this.dno = dno; 
} 
public void setorder(String order) 
{ 
this.order = order; 
} 
public String getorder()
{
	return this.order;
}
public String getdno() 
{ 
return this.dno; 
} 
public void setyear(String year) 
{ 
this.year = year; 
} 
public String getyear() 
{ 
return this.year; 
}
public String[][] getreport()
{
	return this.report;
}
public int getcount()
{
	return this.count;
}
public String getacademicyear()
{
	return this.academicyear;
}
public void setacademicyear(String academicyear)
{
	this.academicyear=academicyear;
}




 public void setccategoryin(String ccategoryin) 
{ 
this.ccategoryin = ccategoryin; 
} 
public String getccategoryin() 
{ 
return this.ccategoryin; 
} 
public void setcommunityin(String communityin) 
{ 
this.communityin = communityin; 
} 
public String getcommunityin() 
{ 
return this.communityin; 
} 
public void setdirectorlateralin(String directorlateralin) 
{ 
this.directorlateralin = directorlateralin; 
} 
public String getdirectorlateralin() 
{ 
return this.directorlateralin; 
} 
public void setmediumin(String mediumin) 
{ 
this.mediumin = mediumin; 
} 
public String getmediumin() 
{ 
return this.mediumin; 
} 
public void setreligionin(String religionin) 
{ 
this.religionin = religionin; 
} 
public String getreligionin() 
{ 
return this.religionin; 
} 
public void setsexin(String sexin) 
{ 
this.sexin = sexin; 
} 
public String getsexin() 
{ 
return this.sexin; 
} 
public void setstatein(String statein) { this.statein = statein; } public String getstatein() { return this.statein; } 
public void setyearin(String yearin) { this.yearin = yearin; } public String getyearin() { return this.yearin; } 
public void setdnoin(String dnoin) { this.dnoin = dnoin; } public String getdnoin() { return this.dnoin; } 
public void setactivein(String activein) { this.activein = activein; } public String getactivein() { return this.activein; } 

public void setucategoryin(String ucategoryin) 
{ 
this.ucategoryin = ucategoryin; 
} 
public String getucategoryin() 
{ 
return this.ucategoryin; 
} 

public void setsubject(String subject)  { this.subject = subject; } 
public String getsubject() { return this.subject; } 

public void setqx(String qx)  { this.qx = qx; } 
public String getqx() { return this.qx; } 

public void setqxii(String qxii)  { this.qxii = qxii; } 
public String getqxii() { return this.qxii; } 

public void setqcgpa(String qcgpa)  { this.qcgpa = qcgpa; } 
public String getqcgpa() { return this.qcgpa; } 

public void setqarrears(String qarrears)  { this.qarrears = qarrears; } 
public String getqarrears() { return this.qarrears; } 

public void setqtrainingin(String qtrainingin)  { this.qtrainingin = qtrainingin; } 
public String getqtrainingin() { return this.qtrainingin; } 

public void setqholidaysin(String qholidaysin)  { this.qholidaysin = qholidaysin; } 
public String getqholidaysin() { return this.qholidaysin; } 



}