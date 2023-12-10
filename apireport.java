package sxcce;
import java.sql.*;
import java.util.StringTokenizer;
import com.jspsmart.upload.*;
import java.util.Date;
import java.io.*;
import dd.DBConnectionManager;

public class apireport
	{
				String rep[] = new String[1000];
				String repin[] = new String[30];
				String report[][] = new String[30][10000];
				String report1[] = new String[1000];
                String fdate="";
                String tdate="";
                String pname="";
                String fname="";
String order="";
String d1="";
String m1="";
String y1="";
String str1="";
int count;
int cid;
String sql="";	
String error="";			
String academicyear="";		

String date1="";	
String date2="";	
String date3="";	
String date4="";	

int i1=0;
int i2=0;
int i3=0;
int i4=0;

float f1=0;
float f2=0;
float f3=0;

String dnoin="0";
String typein="'0'";
String affiliationin=" ";
String authortypein="'0'";
String intornatin="'0'";
String indexingin="'0'";
String yearofpubin=" ";
String yearofpub=" ";


public String StaffResearchStatus()
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

sql="select staff.staffid,sf,name,cname,'3' from apiqualification,staff,department,staffdesignation where  slno=designation and department.dno=staff.dno and apiqualification.staffid=staff.staffid and  "
+" degree='Ph.D' and active=1  and category='Teaching' and staff.staffid in (select staffid  from apiresearch where supervisoryesno in('Yes')) order by sf,staffid";

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

sql="select staff.staffid,sf,name,cname,'2' from apiqualification,staff,department,staffdesignation where  slno=designation and department.dno=staff.dno and apiqualification.staffid=staff.staffid and "
+" degree='Ph.D' and active=1  and category='Teaching' and staff.staffid not in (select staffid  from apiresearch where supervisoryesno not in('No')) order by sf,staffid";

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

sql="select staff.staffid,sf,name,cname,'1' from staff,department,staffdesignation where  slno=designation and department.dno=staff.dno and "
+" active=1  and category='Teaching' and staff.staffid not in (select staffid  from apiqualification where degree in('Ph.D')) order by sf,staffid";

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



public void setrep(String[] rep) {this.rep=rep;}  public String[] getrep() {	return this.rep;}
public void setrepin(String[] repin) {this.repin=repin;}  public String[] getrepin() {	return this.repin;}
public void setreport(String[][] report) {this.report=report;}  public String[][] getreport() {	return this.report;}
public void setreport1(String[] report1) {this.report1=report1;}  public String[] getreport1() {	return this.report1;}
public void setcount(int count) {this.count=count;} public int getcount(){	return this.count;}
public void setfname(String fname) { this.fname = fname; } public String getfname() { return this.fname; } 
public void setpname(String pname) { this.pname = pname; } public String getpname() { return this.pname; } 
public void setacademicyear(String academicyear) { this.academicyear = academicyear; } public String getacademicyear() { return this.academicyear; } 

public void setdnoin(String dnoin) { this.dnoin = dnoin; } public String getdnoin() { return this.dnoin; } 
public void settypein(String typein) { this.typein = typein; } public String gettypein() { return this.typein; } 
public void setauthortypein(String authortypein) { this.authortypein = authortypein; } public String getauthortypein() { return this.authortypein; } 
public void setintornatin(String intornatin) { this.intornatin = intornatin; } public String getintornatin() { return this.intornatin; } 
public void setindexingin(String indexingin) { this.indexingin = indexingin; } public String getindexingin() { return this.indexingin; } 
public void setaffiliationin(String affiliationin) { this.affiliationin = affiliationin; } public String getaffiliationin() { return this.affiliationin; } 
public void setyearofpubin(String yearofpubin) { this.yearofpubin = yearofpubin; } public String getyearofpubin() { return this.yearofpubin; } 
public void setyearofpub(String yearofpub) { this.yearofpub = yearofpub; } public String getyearofpub() { return this.yearofpub; } 

public void setorder(String order) { this.order = order; } public String getorder() { return this.order; } 

public String getsql() { return this.sql; } 


}




	
	