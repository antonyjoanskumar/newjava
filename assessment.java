package sxcce;
import java.sql.*;
import java.util.StringTokenizer;
import com.jspsmart.upload.*;
import java.util.Date;
import java.io.*;
import java.math.*;
import java.text.DecimalFormat;
import dd.DBConnectionManager;

public class assessment
	{
				String rep[] = new String[1000];
				String repin[] = new String[30];
				String report[][] = new String[50][10000];
				String report1[] = new String[1000];
				String report2[] = new String[1000];
				String report3[][] = new String[30][10000];
                String fdate="";
                String tdate="";
                String pname="";
                String fname="";

String d1="";
String m1="";
String y1="";
String str1="";
int count;
int count1;
int cid;
int i1,i2,i3;
float f1;

String sql="";	
String quer="";	
String error="";			
String academicyear="";		

String date1="";	
String date2="";	


public String GetDept(int dty)
{
count=0;
String error="...";
DBConnectionManager connMgr=null; Connection con =null;
try
{
                       connMgr = DBConnectionManager.getInstance(); 
                       con = connMgr.getConnection("xavier");
  					    Statement stmt;
					    ResultSet rs;
					    PreparedStatement ps;

if(dty==1)
sql="select * from department where dty>0 order by dty";
else
sql="select * from department order by dty";

                    stmt= con.createStatement();
					rs = stmt.executeQuery(sql);
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

error= "success"; 
}catch(Exception e){error=(e.toString()+count+sql);}
finally{connMgr.freeConnection("xavier",con);}
return error; 
}


public String averagemarks(int ord)
{
count=0;
String error="...";
String order=" order by p.r";

if(ord==1) order=" order by m";

if(ord==2) order=" order by m desc";

DBConnectionManager connMgr=null; Connection con =null;
try
{
                       connMgr = DBConnectionManager.getInstance(); 
                       con = connMgr.getConnection("xavier");
  					    Statement stmt;
					    ResultSet rs;
					    PreparedStatement ps;

sql="select sf,year,p.r,p.n,cname,stay,isnull(a,-1) m from "
+" (select sf,year,stud.rollno r,stud.name n,stay,cname from stud,student,department,collegecategory "
+" where collegecategory.slno=ccategory and stud.rollno=student.rollno  and department.dno=departmentno "
+" and academicyear='"+repin[0]+"' and stay in("+repin[3]+") "
+" and departmentno in ("+repin[1]+") and year in ("+repin[2]+") and ccategory in ("+repin[4]+")) p "
+" left join (select rollno,avg(mark) a from mark where mark>=0 group by rollno) q on p.r=q.rollno "
+order;

                    stmt= con.createStatement();
					rs = stmt.executeQuery(sql);
while(rs.next())
{
	for(int i=1;i<=7;i++)
	{
		report[i-1][count]=rs.getString(i);
	}
	count++;
}
rs.close();
stmt.close();

error= "success"; 
}catch(Exception e){error=(e.toString()+count+sql);}
finally{connMgr.freeConnection("xavier",con);}
return error; 
}




public void setrep(String[] rep) {this.rep=rep;}  public String[] getrep() {	return this.rep;}
public void setrepin(String[] repin) {this.repin=repin;}  public String[] getrepin() {	return this.repin;}
public void setreport(String[][] report) {this.report=report;}  public String[][] getreport() {	return this.report;}
public void setreport1(String[] report1) {this.report1=report1;}  public String[] getreport1() {	return this.report1;}
public void setreport2(String[] report2) {this.report2=report2;}  public String[] getreport2() {	return this.report2;}
public void setreport3(String[][] report3) {this.report3=report3;}  public String[][] getreport3() {	return this.report3;}
public void setcount(int count) {this.count=count;} public int getcount(){	return this.count;}
public void setcount1(int count1) {this.count1=count1;} public int getcount1(){	return this.count1;}
public void setsql(String sql) { this.sql = sql; } public String getsql() { return this.sql; } 
public void setfname(String fname) { this.fname = fname; } public String getfname() { return this.fname; } 
public void setpname(String pname) { this.pname = pname; } public String getpname() { return this.pname; } 
public void setacademicyear(String academicyear) { this.academicyear = academicyear; } public String getacademicyear() { return this.academicyear; } 



}




	
	