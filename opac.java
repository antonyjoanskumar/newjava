package library;
import java.sql.*;
public class opac
{

String academicyear="";
String date="";
String sys="";
String login="";
String title="";
String author="";
String keywords="";

int count=0;
String report[][] = new String[50][1000];

String report1[] = new String[500];


public String select()
{
String quer="";
try
{
Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
Connection con = DriverManager.getConnection("jdbc:odbc:xavier","sxcce","tamil5india6");
Statement stmt = con.createStatement();
ResultSet rs;
quer="select * from academicyear";
rs = stmt.executeQuery(quer);
if(rs.next())
{
academicyear=rs.getString(1);
}
rs.close();

stmt.close();
con.close();
}catch(Exception e){return e.toString()+quer;}
return "suc";
}


public String InsertEnquiry()
{

try
{
Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
Connection con = DriverManager.getConnection("jdbc:odbc:library","sxcce","tamil5india6");
PreparedStatement ps = con.prepareStatement("insert into enquirylog(sys,login,title) values ( ?,?,?)");
ps.setString(1,sys);
ps.setString(2,login);
ps.setString(3,title);
int i = ps.executeUpdate();
ps.close();
con.close();
}catch(Exception e){return e.toString();}
return("success");
}


public String enquirystat()
{
String quer="";
try
{
Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
Connection con = DriverManager.getConnection("jdbc:odbc:library","sxcce","tamil5india6");
Statement stmt = con.createStatement();
ResultSet rs;

quer="select count(*) from enquirylog where day(date)=day(getdate()) and month(date)=month(getdate()) and year(date)=year(getdate())";
rs = stmt.executeQuery(quer);
if(rs.next())
{
report1[0]=rs.getString(1);
}
rs.close();

quer="select count(*) from enquirylog where month(date)=month(getdate()) and year(date)=year(getdate())";
rs = stmt.executeQuery(quer);
if(rs.next())
{
report1[1]=rs.getString(1);
}
rs.close();
quer="select count(*) from enquirylog where year(date)=year(getdate()) ";
rs = stmt.executeQuery(quer);
if(rs.next())
{
report1[2]=rs.getString(1);
}
rs.close();

stmt.close();
con.close();
}catch(Exception e){return e.toString()+quer;}
return "suc";
}


public String opacstat()
{
String quer="";
try
{
Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
Connection con = DriverManager.getConnection("jdbc:odbc:library","sxcce","tamil5india6");
Statement stmt = con.createStatement();
ResultSet rs;

quer="select count(*) from opaclog where day(date)=day(getdate()) and month(date)=month(getdate()) and year(date)=year(getdate())";
rs = stmt.executeQuery(quer);
if(rs.next())
{
report1[0]=rs.getString(1);
}
rs.close();

quer="select count(*) from opaclog where month(date)=month(getdate()) and year(date)=year(getdate())";
rs = stmt.executeQuery(quer);
if(rs.next())
{
report1[1]=rs.getString(1);
}
rs.close();
quer="select count(*) from opaclog where year(date)=year(getdate()) ";
rs = stmt.executeQuery(quer);
if(rs.next())
{
report1[2]=rs.getString(1);
}
rs.close();

stmt.close();
con.close();
}catch(Exception e){return e.toString()+quer;}
return "suc";
}



public String Insert()
{

try
{
Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
Connection con = DriverManager.getConnection("jdbc:odbc:library","sxcce","tamil5india6");
PreparedStatement ps = con.prepareStatement("insert into opaclog(sys,login,title,author,keywords) values ( ?,?,?,?,?)");
ps.setString(1,sys);
ps.setString(2,login);
ps.setString(3,title);
ps.setString(4,author);
ps.setString(5,keywords);
int i = ps.executeUpdate();
ps.close();
con.close();
}catch(Exception e){return e.toString();}
return("success");
}



public String dlibstat()
{
String quer="";
try
{
Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
Connection con = DriverManager.getConnection("jdbc:odbc:library","sxcce","tamil5india6");
Statement stmt = con.createStatement();
ResultSet rs;

quer="select count(*) from dliblog where day(date)=day(getdate()) and month(date)=month(getdate()) and year(date)=year(getdate())";
rs = stmt.executeQuery(quer);
if(rs.next())
{
report1[0]=rs.getString(1);
}
rs.close();

quer="select count(*) from dliblog where month(date)=month(getdate()) and year(date)=year(getdate())";
rs = stmt.executeQuery(quer);
if(rs.next())
{
report1[1]=rs.getString(1);
}
rs.close();
quer="select count(*) from dliblog where year(date)=year(getdate()) ";
rs = stmt.executeQuery(quer);
if(rs.next())
{
report1[2]=rs.getString(1);
}
rs.close();

stmt.close();
con.close();
}catch(Exception e){return e.toString()+quer;}
return "suc";
}


public String InsertDlib()
{

try
{
Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
Connection con = DriverManager.getConnection("jdbc:odbc:library","sxcce","tamil5india6");
PreparedStatement ps = con.prepareStatement("insert into dliblog(sys,login,title) values (?,?,?)");
ps.setString(1,sys);
ps.setString(2,login);
ps.setString(3,title);
int i = ps.executeUpdate();
ps.close();
con.close();
}catch(Exception e){return e.toString();}
return("success");
}

public String LibLogStat()
{
String quer="";
try
{
Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
Connection con = DriverManager.getConnection("jdbc:odbc:library","sxcce","tamil5india6");
Statement stmt = con.createStatement();
ResultSet rs;

quer="select count(*) from liblog where day(timein)=day(getdate()) and month(timein)=month(getdate()) and year(timein)=year(getdate())";
rs = stmt.executeQuery(quer);
if(rs.next())
{
report1[0]=rs.getString(1);
}
rs.close();

quer="select count(*) from liblog where month(timein)=month(getdate()) and year(timein)=year(getdate())";
rs = stmt.executeQuery(quer);
if(rs.next())
{
report1[1]=rs.getString(1);
}
rs.close();
quer="select count(*) from liblog where year(timein)=year(getdate()) ";
rs = stmt.executeQuery(quer);
if(rs.next())
{
report1[2]=rs.getString(1);
}
rs.close();

quer="select lid,right('0' + rtrim(ltrim(str(DATEPART(HOUR, timein)))),2)+':'+right('0' + rtrim(ltrim(str(DATEPART(MINUTE, timein)))),2),login,isnull(d.name, n1),isnull(d.staff, 'stud1') from liblog a "
+"Left Outer join (select str(rollno) rollno,name n1 from sxcce.dbo.student) b on ltrim(rtrim(a.login))=ltrim(rtrim(str(b.rollno)))  "
+"Left Outer join (select staffid,name,'staff' as staff from sxcce.dbo.staff) d on a.login=d.staffid "
+"where day(timein)=day(getdate()) and month(timein)=month(getdate()) and year(timein)=year(getdate()) and status=1";

rs = stmt.executeQuery(quer);

count=0;
while(rs.next())
{
	for(int i=1;i<=5;i++)
	{
		report[i-1][count]=rs.getString(i);
	}
	count++;
}
rs.close();


stmt.close();
con.close();
}catch(Exception e){return e.toString()+quer;}
return "suc";
}


public String InsertLibLog(String login)
{
int lid=-1;
String quer="";
int found=0;
try
{
Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
Connection con = DriverManager.getConnection("jdbc:odbc:library","sxcce","tamil5india6");
Statement stmt = con.createStatement();
PreparedStatement ps;

ResultSet rs;

quer="select * from sxcce.dbo.student where ltrim(rtrim(str(rollno)))='"+login+"'";
rs = stmt.executeQuery(quer);
if(rs.next()) found=1;
rs.close();

quer="select * from sxcce.dbo.staff where ltrim(rtrim(staffid))='"+login+"'";
rs = stmt.executeQuery(quer);
if(rs.next()) found=1;
rs.close();



if(found==1)
{
quer="select lid from liblog where day(timein)=day(getdate()) and month(timein)=month(getdate()) and year(timein)=year(getdate()) and login='"+login+"' and status=1";
rs = stmt.executeQuery(quer);
if(rs.next())
{
lid=rs.getInt(1);
}
rs.close();

if(lid<0)
{
ps = con.prepareStatement("insert into liblog(timein,login,status) values (getdate(),?,1)");
ps.setString(1,login);
}
else
{
ps = con.prepareStatement("update liblog set timeout=getdate(),status=0 where lid=?");
ps.setInt(1,lid);
}
int i = ps.executeUpdate();
ps.close();
}
stmt.close();
con.close();
}catch(Exception e){return e.toString();}
return("success");
}




public void setcount(int count) 
{ 
this.count = count; 
} 
public int getcount() 
{ 
return this.count; 
} 



public void setacademicyear(String academicyear) 
{ 
this.academicyear = academicyear; 
} 
public String getacademicyear() 
{ 
return this.academicyear; 
} 

public void setsys(String sys) 
{ 
this.sys = sys; 
} 
public String sys() 
{ 
return this.sys; 
} 

public void setlogin(String login) 
{ 
this.login = login; 
} 
public String getlogin() 
{ 
return this.login; 
} 

public void settitle(String title) 
{ 
this.title = title; 
} 
public String gettitle() 
{ 
return this.title; 
} 

public void setauthor(String author) 
{ 
this.author = author; 
} 
public String getauthor() 
{ 
return this.author; 
} 

public void setkeywords(String keywords) 
{ 
this.keywords = keywords; 
} 
public String getkeywords() 
{ 
return this.keywords; 
} 
 




public void setreport(String[][] report) 
{ 
this.report = report; 
} 
public String[][] getreport() 
{ 
return this.report; 
} 

public void setreport1(String[] report1) 
{ 
this.report1 = report1; 
} 
public String[] getreport1() 
{ 
return this.report1; 
} 



}