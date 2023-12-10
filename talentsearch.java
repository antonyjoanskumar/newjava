package sxcce;
import java.sql.*;
import java.util.StringTokenizer;
import com.jspsmart.upload.*;
import java.util.Date;
import java.io.*;
import dd.DBConnectionManager;

public class talentsearch
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
int tid;
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
int i5=0;


float f1=0;
float f2=0;
float f3=0;

public String talentsearchInsert()
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
			StringTokenizer st1 = new StringTokenizer(repin[6],"/");
		    d1=st1.nextToken();
		    m1=st1.nextToken();
		    y1=st1.nextToken();
			date1=m1+"/"+d1+"/"+y1;
try { i1=Integer.parseInt(repin[1]); }catch(Exception e){}			
try { i2=Integer.parseInt(repin[2]); }catch(Exception e){}			
try { i3=Integer.parseInt(repin[3]); }catch(Exception e){}			
try { i4=Integer.parseInt(repin[5]); }catch(Exception e){}			


}
catch(Exception e){}			


					sql="select count(*) from talentsearch where rollno="+repin[1];
					rs = stmt.executeQuery(sql);
					if(rs.next())     { 	e1=rs.getInt(1); 	    }
					rs.close();

					sql="select count(*) from talentsearch where rollno="+repin[1]+" and cat="+repin[2];
					rs = stmt.executeQuery(sql);
					if(rs.next())     { 	c1=rs.getInt(1); 	    }
					rs.close();

					sql="select count(*) from talentsearch where rollno="+repin[1]+" and cat="+repin[2]+" and event="+repin[3];
					rs = stmt.executeQuery(sql);
					if(rs.next())     { 	ce=rs.getInt(1); 	    }
					rs.close();
if(ce>=1)
{
	error="Invalid Entry! Rollno:"+repin[1]+" Already Registered in Category-"+repin[2]+" and events-"+repin[3];
}	
else if(c1>=3)
{
	error="Invalid Entry! Rollno:"+repin[1]+" Already Registered "+c1+" events in Category-"+repin[2];
}	
else if(e1>=6)
{
	error="Invalid Entry! Rollno:"+repin[1]+" Already Registered total "+e1+" events";
}	
else{
					sql="select max(id)+1 from talentsearch";
					rs = stmt.executeQuery(sql);
					if(rs.next())     { 	cid=rs.getInt(1); 	    }
					rs.close();

					sql="select max(teamid)+1 from talentsearch";
					rs = stmt.executeQuery(sql);
					if(rs.next())     { 	tid=rs.getInt(1); 	    }
					rs.close();
					
				
                    sql="insert into  talentsearch values(?,?,?,?,?,?,?)";
					ps=con.prepareStatement(sql);
					ps.setInt(1,cid);
					ps.setInt(2,i1);
					ps.setInt(3,i2);
					ps.setInt(4,i3);
					ps.setInt(5,tid);
					ps.setInt(6,i4);
					ps.setString(7,date1);
					int j = ps.executeUpdate();
					ps.close();
     
                    stmt.close();
					connMgr.freeConnection("xavier",con);
			error="suc";
					}		
					}catch(Exception e){error=(e.toString());}
return error;	
}



public String talentsearchDel(int id)
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
i1=Integer.parseInt(repin[3]);
}
catch(Exception e){}			
					    
                  sql="delete from talentsearch where id=? and teamno=?";
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


public String talentsearchList()
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
sql="select id,talentsearch.rollno,cat,event,teamid,teamleader,entrydate,name, "
+"substring(('000'+ltrim(rtrim(str(day(entrydate))))), len('000'+ltrim(rtrim(str(day(entrydate)))))-1,2)+'/'+  substring(('000'+ltrim(rtrim(str(month(entrydate))))),len('000'+ltrim(rtrim(str(month(entrydate)))))-1,2)+'/'+ ltrim(rtrim(str(year(entrydate)))) "
+"from talentsearch,stud where talentsearch.rollno=stud.rollno  and academicyear in(select academicyear from academicyear) and teamleader="+repin[1]+" order by cat,event";


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



public String talentsearchAllList(int act)
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
if(act==0)					    
sql="select id,talentsearch.rollno,cat,event,teamid,teamleader,entrydate,stud.name, "
+"substring(('000'+ltrim(rtrim(str(day(entrydate))))), len('000'+ltrim(rtrim(str(day(entrydate)))))-1,2)+'/'+  substring(('000'+ltrim(rtrim(str(month(entrydate))))),len('000'+ltrim(rtrim(str(month(entrydate)))))-1,2)+'/'+ ltrim(rtrim(str(year(entrydate)))),sf,year,sex "
+"from talentsearch,stud,student,department where talentsearch.rollno=stud.rollno and department.dno=student.departmentno and student.rollno=stud.rollno  and academicyear in(select academicyear from academicyear) "
+"and departmentno in ("+repin[9]+")  order by cat,event,teamleader,dno,year";
else
sql="select id,talentsearch.rollno,cat,event,teamid,teamleader,entrydate,stud.name, "
+"substring(('000'+ltrim(rtrim(str(day(entrydate))))), len('000'+ltrim(rtrim(str(day(entrydate)))))-1,2)+'/'+  substring(('000'+ltrim(rtrim(str(month(entrydate))))),len('000'+ltrim(rtrim(str(month(entrydate)))))-1,2)+'/'+ ltrim(rtrim(str(year(entrydate)))),sf,year,sex "
+"from talentsearch,stud,student,department where talentsearch.rollno=stud.rollno and department.dno=student.departmentno and student.rollno=stud.rollno  and academicyear in(select academicyear from academicyear) "
+"and cat="+act+" and event in("+repin[10]+") and departmentno in ("+repin[9]+")  order by cat,event,teamleader,dno,year";

rs = stmt.executeQuery(sql);

while(rs.next())
{
	for(i=1;i<=12;i++)
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




	
	