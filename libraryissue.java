package library;
import java.sql.*;
public class libraryissue
{
int day=0;
int month=0;
int year=0;
String date="";
String rollnoorstaffid="";
String memid="";
String dname="";
String name="";
String desigoryear="";
String academicyear="";
String accessionno="";
String title="";
String author="";
String subjectheader="";
String subject="";
String department="";
String edition="";
String publisher="";
String ttdate="";
String retdate="";
int memberfound=0;
int stafforstudent=0;
int bookfound=0;
String fine=""; 
String noofrenewalstaff=""; 
String noofrenewalstudent=""; 
String staffduration=""; 
String studentduration=""; 
String noofcardfirstyear=""; 
String noofcardfourthyear=""; 
String noofcardpg=""; 
String noofcardnonteachingstaff=""; 
String noofcardsecondyear=""; 
String noofcardteachingstaff=""; 
String noofcardthirdyear=""; 
int count=0;
int vday[]=new int[50];int vmonth[]=new int[50];
int vyear[]=new int[50];
String vdate[] = new String[50];
String baccessionno[] = new String[50];
String btitle[] = new String[50];
String tttdate[] = new String[50];
String bauthor[] = new String[50];
String bsubjectheader[] = new String[50];
String bsubject[] = new String[50];
String bdepartment[] = new String[50];
String bedition[] = new String[50];
String bpublisher[] = new String[50];
String bretdate[] = new String[50];

int renewalcount=0;
String renewaldate[] = new String[10];

int vrenewalcount[] = new int[50];
String vrenewaldate[][] = new String[10][50];

public String select()
{
if(month>7)academicyear=year+" -- "+(year+1);
else academicyear=(year-1)+" -- "+year;
String quer="";
try
{
Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
Connection con = DriverManager.getConnection("jdbc:odbc:xavier","sxcce","tamil5india6");
Statement stmt = con.createStatement();
ResultSet rs;
quer="select staffid,name,department.dname,staffdesignation.cname from staff,"+
"department,staffdesignation where staffid='"+memid+"' and "+
"staff.designation=staffdesignation.slno and "+
"staff.dno=department.dno";
rs = stmt.executeQuery(quer);
if(rs.next())
{
rollnoorstaffid=rs.getString(1);
name=rs.getString(2);
dname=rs.getString(3);
desigoryear=rs.getString(4);
memberfound=1;
stafforstudent=1;
}
rs.close();
if(memberfound==0)
{
//quer="select rollno,name,department.dname,year from stud,department where "+
quer="select rollno,name,department.dname,year from stud,department where academicyear='"+academicyear+"' "+
" and rollno='"+memid+"' and stud.department=department.dno";
rs = stmt.executeQuery(quer);
if(rs.next())
{
rollnoorstaffid=rs.getString(1);
name=rs.getString(2);
dname=rs.getString(3);
desigoryear=rs.getString(4);
memberfound=1;
stafforstudent=2;
}
rs.close();
}
stmt.close();
con.close();
}catch(Exception e){return e.toString()+quer;}


try
{
Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
Connection con = DriverManager.getConnection("jdbc:odbc:library","sxcce","tamil5india6");
Statement stmt = con.createStatement();
ResultSet rs;
quer="select title,author1,author2,author3,subjectheader.headername,"+
"subject.name,sxcce.dbo.department.dname,noofedition,"+
"publisher.name from book,subjectheader,subject,"+
"sxcce.dbo.department,publisher "+
"where accessionno="+accessionno+" and headno=subjectheader "+
"and subject.no=subject and sxcce.dbo.department.dno=department "+
"and publisher.no=publisher";
rs = stmt.executeQuery(quer);
if(rs.next())
{
	String author2,author3;
title=rs.getString(1);
author=rs.getString(2);
author2=rs.getString(3);
author3=rs.getString(4);
if(author2.length()>2)author=author+", "+author2;
if(author3.length()>2)author=author+", "+author3;
subjectheader=rs.getString(5);
subject=rs.getString(6);
department=rs.getString(7);
edition=rs.getString(8);
publisher=rs.getString(9);
bookfound=1;
}
rs.close();
date=month+"/"+day+"/"+year;
rs = stmt.executeQuery("select * from general");
if(rs.next())
{
staffduration=rs.getString(1);
studentduration=rs.getString(2);
noofrenewalstaff=rs.getString(3);
noofrenewalstudent=rs.getString(4);
fine=rs.getString(5);
}
rs.close();
if (renewalcount>0)
   {
if(stafforstudent==1)
rs = stmt.executeQuery("select day(dateadd(day,"+staffduration+",'"+ttdate+"')),month(dateadd(day,"+staffduration+",'"+ttdate+"')),year(dateadd(day,"+staffduration+",'"+ttdate+"'))");
else 
rs = stmt.executeQuery("select day(dateadd(day,"+studentduration+",'"+ttdate+"')),month(dateadd(day,"+studentduration+",'"+ttdate+"')),year(dateadd(day,"+studentduration+",'"+ttdate+"'))");
   }
else 
  {
if(stafforstudent==1)
rs = stmt.executeQuery("select day(dateadd(day,"+staffduration+",'"+date+"')),month(dateadd(day,"+staffduration+",'"+date+"')),year(dateadd(day,"+staffduration+",'"+date+"'))");
else 
rs = stmt.executeQuery("select day(dateadd(day,"+studentduration+",'"+date+"')),month(dateadd(day,"+studentduration+",'"+date+"')),year(dateadd(day,"+studentduration+",'"+date+"'))");
  }
if(rs.next())
{
	retdate=rs.getString(1)+"/"+rs.getString(2)+"/"+rs.getString(3);
}
rs.close();
stmt.close();
con.close();
}catch(Exception e){return e.toString();}
return "suc";
}




public String selectall()
{String dd="";
memberfound=0;
	if(academicyear.length()<6)
if(vmonth[0]>6)academicyear=vyear[0]+" -- "+(vyear[0]+1);
else academicyear=(vyear[0]-1)+" -- "+vyear[0];
if(count<1)academicyear="2023 -- 2024";
String quer="";
try
{
Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
Connection con = DriverManager.getConnection("jdbc:odbc:xavier","sxcce","tamil5india6");
Statement stmt = con.createStatement();
ResultSet rs;
quer="select staffid,name,department.dname,staffdesignation.cname from staff,"+
"department,staffdesignation where staffid='"+memid+"' and "+
"staff.designation=staffdesignation.slno and "+
"staff.dno=department.dno";
rs = stmt.executeQuery(quer);
if(rs.next())
{
rollnoorstaffid=rs.getString(1);
name=rs.getString(2);
dname=rs.getString(3);
desigoryear=rs.getString(4);
memberfound=1;
stafforstudent=1;
}
rs.close();
if(memberfound==0)
{
//quer="select rollno,name,department.dname,year from stud,department where "+
quer="select rollno,name,department.dname,year from stud,department where academicyear='"+academicyear+"' "+
"and rollno='"+memid+"' and stud.department=department.dno";
dd=quer;
rs = stmt.executeQuery(quer);
if(rs.next())
{
rollnoorstaffid=rs.getString(1);
name=rs.getString(2);
dname=rs.getString(3);
desigoryear=rs.getString(4);
memberfound=1;
stafforstudent=2;
}
rs.close();
}
stmt.close();
con.close();

}catch(Exception e){return e.toString();}


try
{
Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
Connection con = DriverManager.getConnection("jdbc:odbc:library","sxcce","tamil5india6");
Statement stmt = con.createStatement();
ResultSet rs;
rs = stmt.executeQuery("select * from general");
if(rs.next())
{

staffduration=rs.getString(1);
studentduration=rs.getString(2);
noofrenewalstaff=rs.getString(3);
noofrenewalstudent=rs.getString(4);
fine=rs.getString(5);

noofcardfirstyear=rs.getString(6);
noofcardsecondyear=rs.getString(7);
noofcardthirdyear=rs.getString(8);
noofcardfourthyear=rs.getString(9);
noofcardteachingstaff=rs.getString(10);
noofcardnonteachingstaff=rs.getString(11);
noofcardpg=rs.getString(12);
}
rs.close();
for(int i=0;i<count;i++){
quer="select title,author1,author2,author3,subjectheader.headername,"+
"subject.name,sxcce.dbo.department.dname,noofedition,"+
"publisher.name from book,subjectheader,subject,"+
"sxcce.dbo.department,publisher "+
"where accessionno="+baccessionno[i]+" and headno=subjectheader "+
"and subject.no=subject and sxcce.dbo.department.dno=department "+
"and publisher.no=publisher";
rs = stmt.executeQuery(quer);
if(rs.next())
{
	String author2,author3;
btitle[i]=rs.getString(1);
bauthor[i]=rs.getString(2);
author2=rs.getString(3);
author3=rs.getString(4);
if(author2.length()>2)bauthor[i]=bauthor[i]+", "+author2;
if(author3.length()>2)bauthor[i]=bauthor[i]+", "+author3;
bsubjectheader[i]=rs.getString(5);
bsubject[i]=rs.getString(6);
bdepartment[i]=rs.getString(7);
bedition[i]=rs.getString(8);
bpublisher[i]=rs.getString(9);
bookfound=1;
}
rs.close();
date=vmonth[i]+"/"+vday[i]+"/"+vyear[i];
if(vrenewalcount[i]>0)
  {
if(stafforstudent==1)
rs = stmt.executeQuery("select day(dateadd(day,"+staffduration+",'"+tttdate[i]+"')),month(dateadd(day,"+staffduration+",'"+tttdate[i]+"')),year(dateadd(day,"+staffduration+",'"+tttdate[i]+"'))");
else 
rs = stmt.executeQuery("select day(dateadd(day,"+studentduration+",'"+tttdate[i]+"')),month(dateadd(day,"+studentduration+",'"+tttdate[i]+"')),year(dateadd(day,"+studentduration+",'"+tttdate[i]+"'))");
  }
else 
{
if(stafforstudent==1)
rs = stmt.executeQuery("select day(dateadd(day,"+staffduration+",'"+date+"')),month(dateadd(day,"+staffduration+",'"+date+"')),year(dateadd(day,"+staffduration+",'"+date+"'))");
else 
rs = stmt.executeQuery("select day(dateadd(day,"+studentduration+",'"+date+"')),month(dateadd(day,"+studentduration+",'"+date+"')),year(dateadd(day,"+studentduration+",'"+date+"'))");
}
if(rs.next())
{
	bretdate[i]=rs.getString(1)+"/"+rs.getString(2)+"/"+rs.getString(3);
}
rs.close();

}

stmt.close();
con.close();
}catch(Exception e){return e.toString();}
return dd+"suc";
}





public String Insert()
{
	date=month+"/"+day+"/"+year;
try
{
Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
Connection con = DriverManager.getConnection("jdbc:odbc:library","sxcce","tamil5india6");
PreparedStatement ps = con.prepareStatement("insert into issue values ( ?,?,?)");
ps.setString(1,memid);
ps.setString(2,date);
ps.setString(3,accessionno);
int i = ps.executeUpdate();
ps.close();
con.close();
}catch(Exception e){return e.toString();}
return("success");
}
public String Update(String memid1,String accessionno1)
{
try
{
Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
Connection con = DriverManager.getConnection("jdbc:odbc:library","sxcce","tamil5india6");
PreparedStatement ps = con.prepareStatement("update issue set memid=?,accessionno=? where accessionno=?");
ps.setString(1,memid1); 
ps.setString(2,accessionno1);
ps.setString(3,accessionno);
int i = ps.executeUpdate();
ps.close();
con.close();
}catch(Exception e){return e.toString();}
return("success");
}
public String Delete()
{
try
{
Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
Connection con = DriverManager.getConnection("jdbc:odbc:library","sxcce","tamil5india6");
PreparedStatement ps = con.prepareStatement("delete from issue where accessionno=?");
ps.setString(1,accessionno); 
int i = ps.executeUpdate();
ps.close();
con.close();
}catch(Exception e){return e.toString();}
return("success");
}
public String viewall()
{
	count=0;
try
{
Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
Connection con = DriverManager.getConnection("jdbc:odbc:library","sxcce","tamil5india6");
Statement stmt = con.createStatement();
ResultSet rs = stmt.executeQuery("select memberid,day(date),month(date),year(date),accessionno from issue where memberid='"+memid+"'");
while(rs.next())
{
vday[count]=rs.getInt(2);
vmonth[count]=rs.getInt(3);
vyear[count]=rs.getInt(4);
baccessionno[count]=rs.getString(5);
count++;
}
rs.close();
for(int i=0;i<count;i++)
{
date=vmonth[i]+"/"+vday[i]+"/"+vyear[i];
rs = stmt.executeQuery("select day(date),month(date),year(date),date from renewal where date>='"+date+"' and accessionno="+baccessionno[i]);
vrenewalcount[i]=0;
while(rs.next())
{
	String dd="";
	dd=rs.getString(1);
	if(dd.length()<2)dd="0"+dd;
	vrenewaldate[i][vrenewalcount[i]]=dd+"/";
	dd=rs.getString(2);
	if(dd.length()<2)dd="0"+dd;
	vrenewaldate[i][vrenewalcount[i]]=vrenewaldate[i][vrenewalcount[i]]+dd+"/"+rs.getString(3);
	tttdate[i]=rs.getString(4);
	vrenewalcount[i]++;
}
rs.close();
	
}
stmt.close();
con.close();
selectall();
}catch(Exception e){return e.toString();}
return "suc"; 
}

public int View()
{
int found=0;
try
{
Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
Connection con = DriverManager.getConnection("jdbc:odbc:library","sxcce","tamil5india6");
Statement stmt = con.createStatement();
ResultSet rs = stmt.executeQuery("select memberid,day(date),month(date),year(date),accessionno from issue where accessionno="+accessionno);
if(rs.next())
{
memid=rs.getString(1);
day=rs.getInt(2);
month=rs.getInt(3);
year=rs.getInt(4);
accessionno=rs.getString(5);
found=1;
}
rs.close();
date=month+"/"+day+"/"+year;
rs = stmt.executeQuery("select day(date),month(date),year(date),date from renewal where date>='"+date+"' and accessionno="+accessionno);
renewalcount=0;
while(rs.next())
{
	String dd="";
	dd=rs.getString(1);
	if(dd.length()<2)dd="0"+dd;
	renewaldate[renewalcount]=dd+"/";
	dd=rs.getString(2);
	if(dd.length()<2)dd="0"+dd;
	renewaldate[renewalcount]=renewaldate[renewalcount]+dd+"/"+rs.getString(3);
	ttdate=rs.getString(4);
	renewalcount++;
}
rs.close();


stmt.close();
con.close();
}catch(Exception e){}
return found; 
}

public void setcount(int count) 
{ 
this.count = count; 
} 
public int getcount() 
{ 
return this.count; 
} 

public void setrenewalcount(int renewalcount) 
{ 
this.renewalcount = renewalcount; 
} 
public int getrenewalcount() 
{ 
return this.renewalcount; 
} 

public void setvrenewalcount(int[] vrenewalcount) 
{ 
this.vrenewalcount = vrenewalcount; 
} 
public int[] getvrenewalcount() 
{ 
return this.vrenewalcount; 
} 

public void setday(int day) 
{ 
this.day = day; 
} 
public int getday() 
{ 
return this.day; 
} 

public void setmonth(int month) 
{ 
this.month = month; 
} 
public int getmonth() 
{ 
return this.month; 
} 

public void setyear(int year) 
{ 
this.year = year; 
} 
public int getyear() 
{ 
return this.year; 
} 

public void setdate(String date) 
{ 
this.date = date; 
} 
public String getdate() 
{ 
return this.date; 
} 

public void setacademicyear(String academicyear) 
{ 
this.academicyear = academicyear; 
} 
public String getacademicyear() 
{ 
return this.academicyear; 
} 
public void setaccessionno(String accessionno) 
{ 
this.accessionno = accessionno; 
} 
public String getaccessionno() 
{ 
return this.accessionno; 
} 
public void setauthor(String author) 
{ 
this.author = author; 
} 
public String getauthor() 
{ 
return this.author; 
} 
public void setbookfound(int bookfound) 
{ 
this.bookfound = bookfound; 
} 
public int getbookfound() 
{ 
return this.bookfound; 
} 
public void setstafforstudent(int stafforstudent) 
{ 
this.stafforstudent = stafforstudent; 
} 
public int getstafforstudent() 
{ 
return this.stafforstudent; 
} 
public void setdepartment(String department) 
{ 
this.department = department; 
} 
public String getdepartment() 
{ 
return this.department; 
} 
public void setdesigoryear(String desigoryear) 
{ 
this.desigoryear = desigoryear; 
} 
public String getdesigoryear() 
{ 
return this.desigoryear; 
} 
public void setdname(String dname) 
{ 
this.dname = dname; 
} 
public String getdname() 
{ 
return this.dname; 
} 
public void setedition(String edition) 
{ 
this.edition = edition; 
} 
public String getedition() 
{ 
return this.edition; 
} 
public void setmemberfound(int memberfound) 
{ 
this.memberfound = memberfound; 
} 
public int getmemberfound() 
{ 
return this.memberfound; 
} 
public void setmemid(String memid) 
{ 
this.memid = memid; 
} 
public String getmemid() 
{ 
return this.memid; 
} 
public void setname(String name) 
{ 
this.name = name; 
} 
public String getname() 
{ 
return this.name; 
} 
public void setpublisher(String publisher) 
{ 
this.publisher = publisher; 
} 
public String getpublisher() 
{ 
return this.publisher; 
} 
public void setrollnoorstaffid(String rollnoorstaffid) 
{ 
this.rollnoorstaffid = rollnoorstaffid; 
} 
public String getrollnoorstaffid() 
{ 
return this.rollnoorstaffid; 
} 
public void setsubject(String subject) 
{ 
this.subject = subject; 
} 
public String getsubject() 
{ 
return this.subject; 
} 
public void setsubjectheader(String subjectheader) 
{ 
this.subjectheader = subjectheader; 
} 
public String getsubjectheader() 
{ 
return this.subjectheader; 
} 
public void settitle(String title) 
{ 
this.title = title; 
} 
public String gettitle() 
{ 
return this.title; 
} 

 public void setfine(String fine) 
{ 
this.fine = fine; 
} 
public String getfine() 
{ 
return this.fine; 
} 
public void setnoofrenewalstaff(String noofrenewalstaff) 
{ 
this.noofrenewalstaff = noofrenewalstaff; 
} 
public String getnoofrenewalstaff() 
{ 
return this.noofrenewalstaff; 
} 
public void setnoofrenewalstudent(String noofrenewalstudent) 
{ 
this.noofrenewalstudent = noofrenewalstudent; 
} 
public String getnoofrenewalstudent() 
{ 
return this.noofrenewalstudent; 
} 
public void setstaffduration(String staffduration) 
{ 
this.staffduration = staffduration; 
} 
public String getstaffduration() 
{ 
return this.staffduration; 
} 
public void setstudentduration(String studentduration) 
{ 
this.studentduration = studentduration; 
} 
public String getstudentduration() 
{ 
return this.studentduration; 
} 

public void setretdate(String retdate) 
{ 
this.retdate = retdate; 
} 
public String getretdate() 
{ 
return this.retdate; 
} 


 public void setbaccessionno(String[] baccessionno) 
{ 
this.baccessionno = baccessionno; 
} 
public String[] getbaccessionno() 
{ 
return this.baccessionno; 
} 
public void setbauthor(String[] bauthor) 
{ 
this.bauthor = bauthor; 
} 
public String[] getbauthor() 
{ 
return this.bauthor; 
} 
public void setbdepartment(String[] bdepartment) 
{ 
this.bdepartment = bdepartment; 
} 
public String[] getbdepartment() 
{ 
return this.bdepartment; 
} 
public void setbedition(String[] bedition) 
{ 
this.bedition = bedition; 
} 
public String[] getbedition() 
{ 
return this.bedition; 
} 
public void setbpublisher(String[] bpublisher) 
{ 
this.bpublisher = bpublisher; 
} 
public String[] getbpublisher() 
{ 
return this.bpublisher; 
} 
public void setbretdate(String[] bretdate) 
{ 
this.bretdate = bretdate; 
} 
public String[] getbretdate() 
{ 
return this.bretdate; 
} 
public void setbsubject(String[] bsubject) 
{ 
this.bsubject = bsubject; 
} 
public String[] getbsubject() 
{ 
return this.bsubject; 
} 
public void setbsubjectheader(String[] bsubjectheader) 
{ 
this.bsubjectheader = bsubjectheader; 
} 
public String[] getbsubjectheader() 
{ 
return this.bsubjectheader; 
} 
public void setbtitle(String[] btitle) 
{ 
this.btitle = btitle; 
} 
public String[] getbtitle() 
{ 
return this.btitle; 
} 
public void setvdate(String[] vdate) 
{ 
this.vdate = vdate; 
} 
public String[] getvdate() 
{ 
return this.vdate; 
} 

public void setrenewaldate(String[] renewaldate) 
{ 
this.renewaldate = renewaldate; 
} 
public String[] getrenewaldate() 
{ 
return this.renewaldate; 
} 

public void setvrenewaldate(String[][] vrenewaldate) 
{ 
this.vrenewaldate = vrenewaldate; 
} 
public String[][] getvrenewaldate() 
{ 
return this.vrenewaldate; 
} 

public void setvday(int[] vday) 
{ 
this.vday = vday; 
} 
public int[] getvday() 
{ 
return this.vday; 
} 
public void setvmonth(int[] vmonth) 
{ 
this.vmonth = vmonth; 
} 
public int[] getvmonth() 
{ 
return this.vmonth; 
} 
public void setvyear(int[] vyear) 
{ 
this.vyear = vyear; 
} 
public int[] getvyear() 
{ 
return this.vyear; 
} 

 public void setnoofcardfirstyear(String noofcardfirstyear) 
{ 
this.noofcardfirstyear = noofcardfirstyear; 
} 
public String getnoofcardfirstyear() 
{ 
return this.noofcardfirstyear; 
} 
public void setnoofcardfourthyear(String noofcardfourthyear) 
{ 
this.noofcardfourthyear = noofcardfourthyear; 
} 
public String getnoofcardfourthyear() 
{ 
return this.noofcardfourthyear; 
} 
public void setnoofcardnonteachingstaff(String noofcardnonteachingstaff) 
{ 
this.noofcardnonteachingstaff = noofcardnonteachingstaff; 
} 
public String getnoofcardnonteachingstaff() 
{ 
return this.noofcardnonteachingstaff; 
} 
public void setnoofcardsecondyear(String noofcardsecondyear) 
{ 
this.noofcardsecondyear = noofcardsecondyear; 
} 
public String getnoofcardsecondyear() 
{ 
return this.noofcardsecondyear; 
} 
public void setnoofcardteachingstaff(String noofcardteachingstaff) 
{ 
this.noofcardteachingstaff = noofcardteachingstaff; 
} 
public String getnoofcardteachingstaff() 
{ 
return this.noofcardteachingstaff; 
} 
public void setnoofcardthirdyear(String noofcardthirdyear) 
{ 
this.noofcardthirdyear = noofcardthirdyear; 
} 
public String getnoofcardthirdyear() 
{ 
return this.noofcardthirdyear; 
} 

public void setnoofcardpg(String noofcardpg) 
{ 
this.noofcardpg = noofcardpg; 
} 
public String getnoofcardpg() 
{ 
return this.noofcardpg; 
} 

}