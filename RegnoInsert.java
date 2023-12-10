package sxcce;
import java.sql.*;
public class RegnoInsert
{
	int rollno[] = new int[200];
	String regno[] = new String[200];
	String name[] = new String[200];
	int count=0;
		String examyear="";
		int dno=0;
		int year=0;
public String Insert(int rollno[],String regno[],int count)
{
try
{
Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
Connection con = DriverManager.getConnection("jdbc:odbc:xavier","sxcce","tamil5india6");
PreparedStatement ps;
for(int i=0;i<count;i++)
{
ps = con.prepareStatement("insert into registerno values (?,?)");
ps.setInt(1,rollno[i]);
ps.setString(2,regno[i]);
int j = ps.executeUpdate();
ps.close();
}
con.close();
}catch(Exception e){return e.toString();}
return("success");
}
public String Delete()
{
String quer="delete from registerno where rollno in "+
"(select rollno from stud where department="+dno+" and year="+year+" and "+
"stud.academicyear='"+examyear+"')"; 
try
{
Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
Connection con = DriverManager.getConnection("jdbc:odbc:xavier","sxcce","tamil5india6");
PreparedStatement ps = con.prepareStatement(quer);
 int i = ps.executeUpdate();
ps.close();
con.close();
}catch(Exception e){return e.toString();}
return("success");
}
public int View(int select)
{count=0;
int found=0;
String quer="";
if(select!=2)
quer="select registerno.rollno,s.name,registerno from registerno,stud,student s where stud.rollno=s.rollno and "+
" stud.rollno=registerno.rollno and stud.department="+dno+" and active=1 and"+
" year="+year+" and stud.academicyear='"+examyear+"' order by stud.name";

else
quer="select registerno.rollno,s.name,registerno from registerno,stud,student s where stud.rollno=s.rollno and "+
" stud.rollno=registerno.rollno and stud.department="+dno+" and active=1 and "+
" year="+year+" and stud.academicyear='"+examyear+"' order by registerno";
try
{
Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
Connection con = DriverManager.getConnection("jdbc:odbc:xavier","sxcce","tamil5india6");
Statement stmt = con.createStatement();
ResultSet rs = stmt.executeQuery(quer);
while(rs.next())
{
rollno[count]=rs.getInt(1);
name[count]=rs.getString(2);
regno[count]=rs.getString(3);
count++;
found=1;
}
rs.close();
stmt.close();
con.close();
}catch(Exception e){}
return found; 
}




public void View1()
{
String quer="";
try
{
Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
Connection con = DriverManager.getConnection("jdbc:odbc:xavier","sxcce","tamil5india6");
Statement stmt = con.createStatement();
ResultSet rs;
for(int i=0;i<count;i++){
quer="select registerno from registerno where rollno="+rollno[i];
rs = stmt.executeQuery(quer);
while(rs.next())
{
regno[i]=rs.getString(1);
}
rs.close();
}
stmt.close();
con.close();
}catch(Exception e){}

}



	public void setExamyear(String examyear)
	{
		this.examyear=examyear;
	}
	public void setDno(int dno)
	{
		this.dno=dno;
	}
	public void setYear(int year)
	{
		this.year=year;
	}
	public int[] getRollno()
	{
		return this.rollno;
	}
	public void setRegno(String regno[])
	{
		this.regno=regno;
	}
	public void setrollno(int rollno[])
	{
		this.rollno=rollno;
	}
	public String[] getRegno()
	{
		return this.regno;
	}
	public String[] getName()
	{
		return this.name;
	}
	public int getCount()
	{
		return this.count;
	}
	public void setcount(int count)
	{
		this.count=count;
	}
}
/*------------------------------------------------------------------------------
<% String appfilled=""; 
String fdate=""; 
String noofdays=""; 
String reachingtime=""; 
String reason=""; 
String tdate=""; 
String workarranged=""; 
%>
<jsp:useBean class="sxcce." id="ss2" >
<jsp:setProperty name="ss2" property="*" />
<%
appfilled=ss2.getappfilled(); 
fdate=ss2.getfdate(); 
noofdays=ss2.getnoofdays(); 
reachingtime=ss2.getreachingtime(); 
reason=ss2.getreason(); 
tdate=ss2.gettdate(); 
workarranged=ss2.getworkarranged(); 
%>
</jsp:useBean>*/