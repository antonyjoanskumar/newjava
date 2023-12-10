package sxcce;
import java.sql.*;
import java.util.StringTokenizer;
import com.jspsmart.upload.*;
import java.util.Date;
import java.io.*;
import dd.DBConnectionManager;

public class grievance
	{
				String rep[] = new String[1000];
				String repin[] = new String[30];
				String report[][] = new String[30][10000];
				String report1[] = new String[1000];
                String fdate="";
                String tdate="";
                String pname="";
                String fname="";
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

public String GrievanceInsert()
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
try
{
			StringTokenizer st1 = new StringTokenizer(repin[2],"/");
		    d1=st1.nextToken();
		    m1=st1.nextToken();
		    y1=st1.nextToken();
			date1=m1+"/"+d1+"/"+y1;

i1=Integer.parseInt(repin[1]);			
}
catch(Exception e){}			

			
					sql="select max(id)+1 from grievance";
					rs = stmt.executeQuery(sql);
					if(rs.next())     { 	cid=rs.getInt(1); 	    }
					rs.close();
					
				
                    sql="insert into  grievance(id,rollno,date,type,details) values(?,?,?,?,?)";
					ps=con.prepareStatement(sql);
					ps.setInt(1,cid);
					ps.setInt(2,i1);
					ps.setString(3,date1);
					ps.setString(4,repin[3]);
					ps.setString(5,repin[4]);
					int j = ps.executeUpdate();
					ps.close();
     
                    stmt.close();
					connMgr.freeConnection("xavier",con);
					error="...";
					}catch(Exception e){error=(e.toString());}
return error;	
}


public String GrievanceUpdate(int id)
{
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
			StringTokenizer st1 = new StringTokenizer(repin[2],"/");
		    d1=st1.nextToken();
		    m1=st1.nextToken();
		    y1=st1.nextToken();
			date1=m1+"/"+d1+"/"+y1;

i1=Integer.parseInt(repin[1]);
}
catch(Exception e){}			
					    
                  sql="update grievance set date=?,type=?,details=? where id=? and rollno=?";
					ps=con.prepareStatement(sql);

					ps.setString(1,date1);
					ps.setString(2,repin[3]);
					ps.setString(3,repin[4]);
					ps.setInt(4,id);
					ps.setInt(5,i1);
					int i = ps.executeUpdate();
					ps.close();
                    stmt.close();
					connMgr.freeConnection("xavier",con);
					error="success";
					}catch(Exception e){error=(e.toString());
					}
					
		return error;
}


public String GrievanceActionUpdate(int id)
{
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
			StringTokenizer st2 = new StringTokenizer(repin[6],"/");
		    d1=st2.nextToken();
		    m1=st2.nextToken();
		    y1=st2.nextToken();
			date2=m1+"/"+d1+"/"+y1;
i1=Integer.parseInt(repin[1]);
}
catch(Exception e){}			
					    
                  sql="update grievance set action=?,adate=? where id=? and rollno=?";
					ps=con.prepareStatement(sql);
					ps.setString(1,repin[5]);
					ps.setString(2,date2);
					ps.setInt(3,id);
					ps.setInt(4,i1);
					int i = ps.executeUpdate();
					ps.close();
                    stmt.close();
					connMgr.freeConnection("xavier",con);
					error="success";
					}catch(Exception e){error=(e.toString());
					}
					
		return error;
}


public String GrievanceDel(int id)
{

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
i1=Integer.parseInt(repin[1]);
}
catch(Exception e){}			
					    
                  sql="delete from grievance where id=? and rollno=?";
					ps=con.prepareStatement(sql);
					ps.setInt(1,id);
					ps.setInt(2,i1);
					int i = ps.executeUpdate();
					ps.close();
                    stmt.close();
					connMgr.freeConnection("xavier",con);
					error="success";
					}catch(Exception e){error=(e.toString());
					}
					
		return error;
}


public String GrievanceList()
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
sql="select id,rollno,"
+"substring(('000'+ltrim(rtrim(str(day(date))))), len('000'+ltrim(rtrim(str(day(date)))))-1,2)+'/'+  substring(('000'+ltrim(rtrim(str(month(date))))), "
+"len('000'+ltrim(rtrim(str(month(date)))))-1,2)+'/'+ ltrim(rtrim(str(year(date)))),type,details,action, "
+"substring(('000'+ltrim(rtrim(str(day(adate))))), len('000'+ltrim(rtrim(str(day(adate)))))-1,2)+'/'+  substring(('000'+ltrim(rtrim(str(month(adate))))), "
+"len('000'+ltrim(rtrim(str(month(adate)))))-1,2)+'/'+ ltrim(rtrim(str(year(adate)))) from grievance where rollno="+repin[1];

rs = stmt.executeQuery(sql);

while(rs.next())
{
	for(i=1;i<=7;i++)
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


public String GrievanceView()
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
sql="select id,rollno,"
+"substring(('000'+ltrim(rtrim(str(day(date))))), len('000'+ltrim(rtrim(str(day(date)))))-1,2)+'/'+  substring(('000'+ltrim(rtrim(str(month(date))))), "
+"len('000'+ltrim(rtrim(str(month(date)))))-1,2)+'/'+ ltrim(rtrim(str(year(date)))),type,details,action, "
+"substring(('000'+ltrim(rtrim(str(day(adate))))), len('000'+ltrim(rtrim(str(day(adate)))))-1,2)+'/'+  substring(('000'+ltrim(rtrim(str(month(adate))))), "
+"len('000'+ltrim(rtrim(str(month(adate)))))-1,2)+'/'+ ltrim(rtrim(str(year(adate)))) from grievance where rollno="+repin[1]+" and id="+repin[0];

rs = stmt.executeQuery(sql);

while(rs.next())
{
	for(i=1;i<=7;i++)
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

public String GrievanceAllList(String fd, String td,int act)
{
int i=0;
count=0;

try
{
			StringTokenizer st1 = new StringTokenizer(fd,"/");
		    d1=st1.nextToken();
		    m1=st1.nextToken();
		    y1=st1.nextToken();
			date1=m1+"/"+d1+"/"+y1;
}
catch(Exception e){}			

try
{
			StringTokenizer st2 = new StringTokenizer(td,"/");
		    d1=st2.nextToken();
		    m1=st2.nextToken();
		    y1=st2.nextToken();
			date2=m1+"/"+d1+"/"+y1;
}
catch(Exception e){}			

try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;
if(act==1)					    
sql="select id,student.rollno,substring(('000'+ltrim(rtrim(str(day(date))))), len('000'+ltrim(rtrim(str(day(date)))))-1,2)+'/'+  substring(('000'+ltrim(rtrim(str(month(date))))), "
+"len('000'+ltrim(rtrim(str(month(date)))))-1,2)+'/'+ ltrim(rtrim(str(year(date)))),type,details,action, "
+"substring(('000'+ltrim(rtrim(str(day(adate))))), len('000'+ltrim(rtrim(str(day(adate)))))-1,2)+'/'+  substring(('000'+ltrim(rtrim(str(month(adate))))), "
+"len('000'+ltrim(rtrim(str(month(adate)))))-1,2)+'/'+ ltrim(rtrim(str(year(adate)))),name,sf from grievance,student,department where student.rollno=grievance.rollno and department.dno=student.departmentno "
+"and date between '"+date1+"' and '"+date2+"' and type in("+repin[10]+") and departmentno in ("+repin[9]+") and action is null";
else
sql="select id,student.rollno,substring(('000'+ltrim(rtrim(str(day(date))))), len('000'+ltrim(rtrim(str(day(date)))))-1,2)+'/'+  substring(('000'+ltrim(rtrim(str(month(date))))), "
+"len('000'+ltrim(rtrim(str(month(date)))))-1,2)+'/'+ ltrim(rtrim(str(year(date)))),type,details,action, "
+"substring(('000'+ltrim(rtrim(str(day(adate))))), len('000'+ltrim(rtrim(str(day(adate)))))-1,2)+'/'+  substring(('000'+ltrim(rtrim(str(month(adate))))), "
+"len('000'+ltrim(rtrim(str(month(adate)))))-1,2)+'/'+ ltrim(rtrim(str(year(adate)))),name,sf from grievance,student,department where student.rollno=grievance.rollno and department.dno=student.departmentno "
+"and date between '"+date1+"' and '"+date2+"' and type in("+repin[10]+") and departmentno in ("+repin[9]+")";

rs = stmt.executeQuery(sql);

while(rs.next())
{
	for(i=1;i<=9;i++)
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

public String GrievanceAllView()
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
sql="select id,student.rollno,"
+"substring(('000'+ltrim(rtrim(str(day(date))))), len('000'+ltrim(rtrim(str(day(date)))))-1,2)+'/'+  substring(('000'+ltrim(rtrim(str(month(date))))), "
+"len('000'+ltrim(rtrim(str(month(date)))))-1,2)+'/'+ ltrim(rtrim(str(year(date)))),type,details,action, "
+"substring(('000'+ltrim(rtrim(str(day(adate))))), len('000'+ltrim(rtrim(str(day(adate)))))-1,2)+'/'+  substring(('000'+ltrim(rtrim(str(month(adate))))), "
+"len('000'+ltrim(rtrim(str(month(adate)))))-1,2)+'/'+ ltrim(rtrim(str(year(adate)))),name,sf from grievance,student,department where student.rollno=grievance.rollno and department.dno=student.departmentno and id="+repin[0];


rs = stmt.executeQuery(sql);

while(rs.next())
{
	for(i=1;i<=9;i++)
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



}




	
	