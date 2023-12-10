package sxcce;
import java.sql.*;
import java.util.StringTokenizer;
import com.jspsmart.upload.*;
import java.util.Date;
import java.io.*;
import dd.DBConnectionManager;

public class admrep
	{
				String rep[] = new String[1000];
				String repin[] = new String[50];
				String report[][] = new String[50][10000];
				String report1[][] = new String[10][1000];
                String fdate="";
                String tdate="";
String d1="";
String m1="";
String y1="";
String str1="";
int count;
int cid;
int tid;
String sql="";	
String error="";			
String academicyear="";		

String date1="";	
String date2="";	
String date3="";	
String date4="";	

int found=0;
int i1=0;
int i2=0;
int i3=0;
int i4=0;
int i5=0;


float f1=0;
float f2=0;
float f3=0;

/*
public String ItemInsert()
{
String sql="";
int c1=0;
int e1=0;
int ce=0;
error="...";
try
{
DBConnectionManager connMgr;
connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  			Statement stmt = con.createStatement();
			ResultSet rs;
			PreparedStatement ps;
try
{
try { i1=Integer.parseInt(repin[1]); }catch(Exception e){}			
try { i2=Integer.parseInt(repin[2]); }catch(Exception e){}			
try { i3=Integer.parseInt(repin[3]); }catch(Exception e){}			
try { i4=Integer.parseInt(repin[5]); }catch(Exception e){}			

}
catch(Exception e){}			

try
{
StringTokenizer st1 = new StringTokenizer(repin[11],"/");
d1=st1.nextToken();
m1=st1.nextToken();
y1=st1.nextToken();
repin[11]=m1+"/"+d1+"/"+y1;
}
catch(Exception e){}			

try
{
StringTokenizer st2 = new StringTokenizer(repin[12],"/");
d1=st2.nextToken();
m1=st2.nextToken();
y1=st2.nextToken();
repin[12]=m1+"/"+d1+"/"+y1;
}
catch(Exception e){}			

					sql="select max(id)+1 from inv_itemmaster";
					rs = stmt.executeQuery(sql);
					if(rs.next())     { 	cid=rs.getInt(1); 	    }
					rs.close();

				
                    sql="insert into  inv_itemmaster values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
					ps=con.prepareStatement(sql);
					ps.setInt(1,cid);
					ps.setString(2,repin[1]);
					ps.setString(3,repin[2]);
					ps.setString(4,repin[3]);
					ps.setString(5,repin[4]);
					ps.setString(6,repin[5]);
					ps.setString(7,repin[6]);
					ps.setString(8,repin[7]);
					ps.setString(9,repin[8]);
					ps.setString(10,repin[9]);
					ps.setString(11,repin[10]);
					ps.setString(12,repin[11]);
					ps.setString(13,repin[12]);
					ps.setString(14,repin[13]);
					ps.setString(15,repin[14]);
					ps.setString(16,repin[15]);
					ps.setString(17,repin[16]);
					ps.setString(18,repin[17]);
					ps.setString(19,repin[18]);
					ps.setString(20,repin[19]);
					ps.setString(21,repin[20]);
					ps.setString(22,repin[21]);
					ps.setString(23,repin[22]);
					ps.setString(24,repin[23]);
					ps.setString(25,repin[24]);
					ps.setString(26,repin[25]);
					int j = ps.executeUpdate();
					ps.close();
     
                    stmt.close();
					connMgr.freeConnection("xavier",con);
			error="suc";
					}catch(Exception e){error=(e.toString());}
return error;	
}
*/


public String studreport()
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
  sql="select max(sections),max(year) year,max(dname) dept,count(case when upper(sex)='MALE' then 1 end) male,count(case when upper(sex)='FEMALE' then 1 end) female, count(*) cnt "
+" from stud,student,department where stud.rollno=student.rollno and department=dno and active=1 "
+" and academicyear='"+repin[0]+"' group by department,year order by max(sections),year,department";
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






public void setrep(String[] rep) {this.rep=rep;}  public String[] getrep() {	return this.rep;}
public void setrepin(String[] repin) {this.repin=repin;}  public String[] getrepin() {	return this.repin;}
public void setreport(String[][] report) {this.report=report;}  public String[][] getreport() {	return this.report;}
public void setreport1(String[][] report1) {this.report1=report1;}  public String[][] getreport1() {	return this.report1;}
public void setcount(int count) {this.count=count;} public int getcount(){	return this.count;}
public void setacademicyear(String academicyear) { this.academicyear = academicyear; } public String getacademicyear() { return this.academicyear; } 



}




	
	