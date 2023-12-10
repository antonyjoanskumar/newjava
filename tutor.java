package sxcce;
import java.sql.*;
import java.util.StringTokenizer;
import com.jspsmart.upload.*;
import java.util.Date;
import java.io.*;
import dd.DBConnectionManager;

public class tutor
	{
				String rep[][] = new String[20][1000];
				String report[][] = new String[20][1000];
				String report3[][] = new String[20][1000];
				String report1[] = new String[1000];
				String report2[] = new String[1000];
String str1="";
int count;
int count1;
int tid;
String sql="";	
String error="";			
String error1="";	
String academicyear="";		

public String TutorsView(int dno, int year, String academicyear1)
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
sql="select a.rollno,name,isnull(b.staffid, ''),isnull(sname, ''),isnull(tid, -1) from (select stud.rollno,stud.name,department,year,academicyear from stud,student where stud.rollno=student.rollno and active=1) a "
+"Left Outer join (select str(rollno) rollno,staffid,tid from tutor where academicyear='"+academicyear1+"') b on ltrim(rtrim(a.rollno))=ltrim(rtrim(str(b.rollno))) "
+"Left Outer join (select staffid,name sname from staff) d on d.staffid=b.staffid where department="+dno+" and year="+year+" and academicyear='"+academicyear1+"' order by a.rollno";

rs = stmt.executeQuery(sql);

while(rs.next())
{
	for(i=1;i<=5;i++)
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



public String TutorsInsert(int rollno, String staffid, String academicyear1)
{
String sql="";
try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  			Statement stmt = con.createStatement();
			ResultSet rs;
			PreparedStatement ps;
					sql="select max(tid)+1 from tutor where rollno="+rollno;
					rs = stmt.executeQuery(sql);
					if(rs.next())     { 	tid=rs.getInt(1); 	    }
					rs.close();
					    
				    sql="select academicyear from academicyear";
				    rs = stmt.executeQuery(sql);
				    if(rs.next())
				    {
					academicyear=rs.getString(1);
				    }
				    rs.close();
					
					
                    sql="delete from tutor where rollno="+rollno+" and tid="+tid;
					ps=con.prepareStatement(sql);
					int k = ps.executeUpdate();
					ps.close();
					
                    sql="insert into tutor values(?,?,?,?)";
					ps=con.prepareStatement(sql);
					ps.setInt(1,rollno);
					ps.setInt(2,tid);
					ps.setString(3,staffid);
					ps.setString(4,academicyear1);
					int j = ps.executeUpdate();
					ps.close();
     
                    stmt.close();
					connMgr.freeConnection("xavier",con);
					error1="...";
					}catch(Exception e){error1=(e.toString());}

return error1;	

}



public String TutorsUpdate(int rollno, int uid, String staffid, String academicyear1)
{
String sql="";
try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  			Statement stmt = con.createStatement();
			ResultSet rs;
			PreparedStatement ps;
					
                    sql="delete from tutor where rollno="+rollno+" and tid="+uid;
					ps=con.prepareStatement(sql);
					int k = ps.executeUpdate();
					ps.close();
					
                    sql="insert into tutor values(?,?,?,?)";
					ps=con.prepareStatement(sql);
					ps.setInt(1,rollno);
					ps.setInt(2,uid);
					ps.setString(3,staffid);
					ps.setString(4,academicyear1);
					int j = ps.executeUpdate();
					ps.close();
     
                    stmt.close();
					connMgr.freeConnection("xavier",con);
					error1="...";
					}catch(Exception e){error1=(e.toString());}

return error1;	

}

public String MenteesView(String staffid)
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
sql="select tutor.rollno,name from tutor,student where tutor.rollno=student.rollno and staffid='"+staffid+"' and academicyear in (select academicyear from academicyear) order by tutor.rollno";

rs = stmt.executeQuery(sql);

while(rs.next())
{
	for(i=1;i<=2;i++)
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

public String MentorView(String rollno)
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
sql="select staff.staffid,staff.name,academicyear from tutor,staff where rollno="+rollno+" and tutor.staffid=staff.staffid order by tid";

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

public String MenteesView1(String staffid, String accyear)
{
int i=0;
	count1=0;
try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;
sql="select tutor.rollno,name from tutor,student where tutor.rollno=student.rollno and staffid='"+staffid+"' and academicyear='"+accyear+"' order by tutor.rollno";

rs = stmt.executeQuery(sql);

while(rs.next())
{
	for(i=1;i<=2;i++)
	{
		report3[i-1][count1]=rs.getString(i);
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

public String MyStudView(int dno, String accyear)
{
int i=0;
	count1=0;
String yearin="";
String dnoin="";

if(dno==7) 
  {yearin=" and year in(1) "; dnoin="";}
else if((dno==1) || (dno==2) || (dno==3) || (dno==4) || (dno==5) || (dno==8) || (dno==14) || (dno==17) || (dno==27))
  {yearin=" and year in(2,3,4) "; dnoin=" and departmentno in("+dno+") "; }
 else {yearin=""; dnoin=" and departmentno in("+dno+") "; }
try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;
sql="select stud.rollno,stud.name from stud,student where stud.rollno=student.rollno and active=1 "
+dnoin+yearin+" and academicyear='"+accyear+"' order by year desc,stud.rollno";

rs = stmt.executeQuery(sql);

while(rs.next())
{
	for(i=1;i<=2;i++)
	{
		report3[i-1][count1]=rs.getString(i);
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


public String StudList(int stay, String accyear)
{
int i=0;
count1=0;
String stayin="";

if(stay==1) stayin=" and stay='Hostel' and sex='Male' ";
else
 stayin=" and stay='Hostel' and sex='Female' ";

try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;
sql="select stud.rollno,stud.name from stud,student where stud.rollno=student.rollno and active=1 "
+stayin+" and academicyear='"+accyear+"' order by year desc,stud.rollno";

rs = stmt.executeQuery(sql);

while(rs.next())
{
	for(i=1;i<=2;i++)
	{
		report3[i-1][count1]=rs.getString(i);
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


public void setrep(String[][] rep) {this.rep=rep;}  public String[][] getrep() {	return this.rep;}
public void setreport(String[][] report) {this.report=report;}  public String[][] getreport() {	return this.report;}
public void setreport3(String[][] report3) {this.report3=report3;}  public String[][] getreport3() {	return this.report3;}
public void setreport1(String[] report1) {this.report1=report1;}  public String[] getreport1() {	return this.report1;}
public void setreport2(String[] report2) {this.report2=report2;}  public String[] getreport2() {	return this.report2;}
public void setcount(int count) {this.count=count;} public int getcount(){	return this.count;}
public void setcount1(int count1) {this.count1=count1;} public int getcount1(){	return this.count1;}


}




	
	