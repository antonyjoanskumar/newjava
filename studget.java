package sxcce;
import java.sql.*;
import java.util.StringTokenizer;
import com.jspsmart.upload.*;
import java.util.Date;
import java.io.*;
import java.math.*;
import java.text.DecimalFormat;
import dd.DBConnectionManager;

public class studget
	{
				String rep[] = new String[1000];
				String repin[] = new String[30];
				String report[][] = new String[50][10000];
				String report1[] = new String[1000];
				String report2[] = new String[1000];
				String report3[][] = new String[50][10000];
				String report4[][] = new String[50][10000];
                String fdate="";
                String tdate="";
                String pname="";
                String fname="";
String sql="";

String d1="";
String m1="";
String y1="";
String str1="";
int count;
int count1;
int count2;
int cocount;

int cid;
int i1;
float f1;

String quer="";	
String error="";			
String academicyear="";		

String date1="";	
String date2="";	


public String getRollno(String regno)
{
String error="0";				
count=0;
	   
DBConnectionManager connMgr=null; Connection con =null;
try
{
 connMgr = DBConnectionManager.getInstance();
 con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;
					    

sql="select rollno from registerno where registerno='"+regno+"'";

rs = stmt.executeQuery(sql);

if(rs.next())
{
		error=rs.getString(1);
}
rs.close();

stmt.close();
}catch(Exception e){error="0";}
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
public void setcocount(int cocount) {this.cocount=cocount;} public int getcocount(){	return this.cocount;}

public void setsql(String sql) { this.sql = sql; } public String getsql() { return this.sql; } 
public void setfname(String fname) { this.fname = fname; } public String getfname() { return this.fname; } 
public void setpname(String pname) { this.pname = pname; } public String getpname() { return this.pname; } 
public void setacademicyear(String academicyear) { this.academicyear = academicyear; } public String getacademicyear() { return this.academicyear; } 



}




	
	