package sxcce;
import java.sql.*;
import dd.DBConnectionManager;
public class quizuser
{
String password=""; 
String username=""; 
String quesname="";
String matchusername[] = new String[5000];
String matchpassword[] = new String[5000];
String users[] = new String[10000];
int marks[] = new int[10000];

int count=0;
int totquestion=0;
String name="";
//int passcount=0;
public String Insert()
{
try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
PreparedStatement ps = con.prepareStatement("insert into quizuser values ( ?,?)");
ps.setString(1,username);
ps.setString(2,password);
int i = ps.executeUpdate();
ps.close();
connMgr.freeConnection("xavier",con);
}catch(Exception e){return e.toString();}
return("success");
}






public String Delete()
{
try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
PreparedStatement ps = con.prepareStatement("delete from quizuser where username=?");
ps.setString(1,username); int i = ps.executeUpdate();
ps.close();
connMgr.freeConnection("xavier",con);
}catch(Exception e){return e.toString();}
return("success");
}

public String Del()
{
	if(username.length()>1)username=" and username='"+username+"' ";
	else username="";
	if(quesname.length()>1)quesname=" and quesname='"+quesname+"' ";
try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
PreparedStatement ps = con.prepareStatement("delete from quizmarks where 1=1 "+username+quesname);
int i = ps.executeUpdate();
ps.close();
connMgr.freeConnection("xavier",con);
}catch(Exception e){return e.toString();}
return("success");
}



public int View()
{
int found=0;
try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
Statement stmt = con.createStatement();
ResultSet rs = stmt.executeQuery("select * from quizuser where username='"+username+"'");
if(rs.next())
{
username=rs.getString(1);
password=rs.getString(2);
found=1;
}
rs.close();
stmt.close();
connMgr.freeConnection("xavier",con);
}catch(Exception e){}
return found; 
}


public int ViewResult()
{
int found=0;
count=0;
try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
Statement stmt = con.createStatement();
ResultSet rs = stmt.executeQuery("select count(quesno)*10,username from quizmarks, "+
"quizquestion where quesname=(select quesname from quizquestionselect) "+
"and quizquestion.questionno=quizmarks.quesno and "+
"name=quesname "+
"and quizquestion.ans=quizmarks.ans "+
"group by username order by count(quesno) desc,username asc");
while(rs.next())
{
marks[count]=rs.getInt(1);
users[count]=rs.getString(2);
count++;
found=1;
}
rs.close();
rs = stmt.executeQuery("select count(questionno)*10 from quizquestion where name = "+
"(select quesname from quizquestionselect)");
if(rs.next())
{
	totquestion=rs.getInt(1);
}
rs.close();


rs = stmt.executeQuery("select quesname from quizquestionselect");
if(rs.next())
{
	name=rs.getString(1);
}
rs.close();


stmt.close();
connMgr.freeConnection("xavier",con);
}catch(Exception e){}
return found; 
}



public int passverify()
{
int found=0;
try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
Statement stmt = con.createStatement();
ResultSet rs = stmt.executeQuery("select * from quizuser where username='"+username+"' and password='"+password+"'");
if(rs.next())
{
username=rs.getString(1);
password=rs.getString(2);
found=1;
}
rs.close();
stmt.close();
connMgr.freeConnection("xavier",con);
}catch(Exception e){}
return found; 
}



public String Match()
{count=0;
String quer="select username,password from quizuser where username='"+username+"' order by username";
try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
Statement stmt = con.createStatement();
ResultSet rs;
rs = stmt.executeQuery(quer);
while(rs.next())
   {
   	matchusername[count]=rs.getString(1);
   	matchpassword[count]=rs.getString(2);
   	count++;
   }
rs.close();
if(count==0){
quer="select username,password from quizuser where username like '"+username+"%' order by username";
rs = stmt.executeQuery(quer);
while(rs.next())
   {
   	matchusername[count]=rs.getString(1);
   	matchpassword[count]=rs.getString(2);
   	count++;
   }
rs.close();
}
stmt.close();
connMgr.freeConnection("xavier",con);
}catch(Exception e){return e.toString();}
return "suc";
}





 public void setpassword(String password) 
{ 
this.password = password; 
} 
public String getpassword() 
{ 
return this.password; 
} 
public void setusername(String username) 
{ 
this.username = username; 
} 
public String getusername() 
{ 
return this.username; 
} 

public void setquesname(String quesname) 
{ 
this.quesname = quesname; 
} 
public String getquesname() 
{ 
return this.quesname; 
} 



public void setname(String name) 
{ 
this.name = name; 
} 
public String getname() 
{ 
return this.name; 
} 

public void setmatchusername(String[] matchusername) 
{ 
this.matchusername = matchusername; 
} 
public String[] getmatchusername() 
{ 
return this.matchusername; 
} 

public void setmatchpassword(String[] matchpassword) 
{ 
this.matchpassword = matchpassword; 
} 
public String[] getmatchpassword() 
{ 
return this.matchpassword; 
} 


public void setcount(int count)
{
	this.count=count;
}
public int getcount()
{
	return count;
}

public void settotquestion(int totquestion)
{
	this.totquestion=totquestion;
}
public int gettotquestion()
{
	return totquestion;
}


public void setmarks(int[] marks) 
{ 
this.marks = marks; 
} 
public int[] getmarks() 
{ 
return this.marks; 
} 

public void setusers(String[] users) 
{ 
this.users = users; 
} 
public String[] getusers() 
{ 
return this.users; 
} 




}

/*


--------------------------------------------------------------------------------
<% String password=""; 
String username=""; 
String username=""; 
%>
<jsp:useBean class="xavier.xavier." id="sss" >
<jsp:setProperty name="sss" property="*" />
<%
password=sss.getpassword(); 
username=sss.getusername(); 
username=sss.getusername(); 
%>
</jsp:useBean>
*/

