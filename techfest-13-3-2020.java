package sxcce;
import java.sql.*;
import java.util.StringTokenizer;
import com.jspsmart.upload.*;
import java.util.Date;
import java.io.*;
import dd.DBConnectionManager;

public class techfest
	{
				String rep[] = new String[1000];
				String repin[] = new String[30];
				String report[][] = new String[30][10000];
				String report1[] = new String[1000];
                String fdate="";
                String tdate="";
                String pname="";
                String fname="";
int cyear=2019;

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

public String techfestInsert()
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
			StringTokenizer st1 = new StringTokenizer(repin[9],"/");
		    d1=st1.nextToken();
		    m1=st1.nextToken();
		    y1=st1.nextToken();
			date1=m1+"/"+d1+"/"+y1;
try { i1=Integer.parseInt(repin[1]); }catch(Exception e){}			
try { i2=Integer.parseInt(repin[2]); }catch(Exception e){}			
try { i3=Integer.parseInt(repin[3]); }catch(Exception e){}			
try { i4=Integer.parseInt(repin[6]); }catch(Exception e){}			
try { i5=Integer.parseInt(repin[8]); }catch(Exception e){}			


}
catch(Exception e){}			

			
					sql="select max(id)+1 from techfest";
					rs = stmt.executeQuery(sql);
					if(rs.next())     { 	cid=rs.getInt(1); 	    }
					rs.close();

					sql="select max(teamid)+1 from techfest";
					rs = stmt.executeQuery(sql);
					if(rs.next())     { 	tid=rs.getInt(1); 	    }
					rs.close();
					
				
                    sql="insert into  techfest values(?,?,?,?,?,?,?,?,?,?,?)";
					ps=con.prepareStatement(sql);
					ps.setInt(1,cid);
					ps.setInt(2,i1);
					ps.setInt(3,i2);
					ps.setInt(4,tid);
					ps.setString(5,repin[4]);
					ps.setString(6,repin[5]);
					ps.setInt(7,i4);
					ps.setString(8,repin[7]);
					ps.setInt(9,i5);
					ps.setString(10,date1);
					ps.setInt(11,cyear);
					int j = ps.executeUpdate();
					ps.close();
     
                    stmt.close();
					connMgr.freeConnection("xavier",con);
					error="...";
					}catch(Exception e){error=(e.toString());}
return error;	
}

public String techfestUpdate(int id)
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
			StringTokenizer st1 = new StringTokenizer(repin[9],"/");
		    d1=st1.nextToken();
		    m1=st1.nextToken();
		    y1=st1.nextToken();
			date1=m1+"/"+d1+"/"+y1;

try { i1=Integer.parseInt(repin[1]); }catch(Exception e){}			
try { i2=Integer.parseInt(repin[2]); }catch(Exception e){}			
try { i3=Integer.parseInt(repin[3]); }catch(Exception e){}			
try { i4=Integer.parseInt(repin[6]); }catch(Exception e){}			
try { i5=Integer.parseInt(repin[8]); }catch(Exception e){}			

}
catch(Exception e){}			
					    
                  sql="update techfest set staffremarks=?,rating=?,hodremarks where id=? and teamid=?";
					ps=con.prepareStatement(sql);

					ps.setString(1,repin[5]);
					ps.setInt(2,i4);
					ps.setString(3,repin[7]);
					ps.setInt(4,id);
					ps.setInt(5,i3);
					int i = ps.executeUpdate();
					ps.close();
                    stmt.close();
					connMgr.freeConnection("xavier",con);
					error="success";
					}catch(Exception e){error=(e.toString());
					}
					
		return error;
}


public String techfestHodUpdate(int id)
{
try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;
					    PreparedStatement ps;
					    
                  sql="update techfest set hodremarks=? where id=?";
					ps=con.prepareStatement(sql);
					ps.setString(1,repin[15].trim());
					ps.setInt(2,id);
					int i = ps.executeUpdate();
					ps.close();
                    stmt.close();
					connMgr.freeConnection("xavier",con);
					error="success"+sql+repin[15];
					}catch(Exception e){error=(e.toString()+sql+repin[15]);
					}
					
		return error;
}

public String techfestStaffUpdate(int id)
{
try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;
					    PreparedStatement ps;
					    
                  sql="update techfest set staffremarks=? where id=?";
					ps=con.prepareStatement(sql);
					ps.setString(1,repin[15].trim());
					ps.setInt(2,id);
					int i = ps.executeUpdate();
					ps.close();
                    stmt.close();
					connMgr.freeConnection("xavier",con);
					error="success"+sql+repin[15];
					}catch(Exception e){error=(e.toString()+sql+repin[15]);
					}
					
		return error;
}

public String techfestRatingUpdate(int id,int rating)
{
try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;
					    PreparedStatement ps;
					    
                  sql="update techfest set rating=? where id=?";
					ps=con.prepareStatement(sql);
					ps.setInt(1,rating);
					ps.setInt(2,id);
					int i = ps.executeUpdate();
					ps.close();
                    stmt.close();
					connMgr.freeConnection("xavier",con);
					error="success"+sql+repin[15];
					}catch(Exception e){error=(e.toString()+sql+repin[15]);
					}
		return error;
}


public String techfestActionUpdate(int id)
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
					    
                  sql="update techfest set action=?,adate=? where id=? and rollno=?";
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


public String techfestDel(int id)
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
					    
                  sql="delete from techfest where id=? and teamno=?";
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



public String techfestList()
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
sql="select id,techfest.rollno,event,teamid,staffid,staffremarks,rating,hodremarks,teamleader,entrydate,name, "
+"substring(('000'+ltrim(rtrim(str(day(entrydate))))), len('000'+ltrim(rtrim(str(day(entrydate)))))-1,2)+'/'+  substring(('000'+ltrim(rtrim(str(month(entrydate))))),len('000'+ltrim(rtrim(str(month(entrydate)))))-1,2)+'/'+ ltrim(rtrim(str(year(entrydate)))) "
+"from techfest,stud where techfest.rollno=stud.rollno  and academicyear in(select academicyear from academicyear) and teamleader="+repin[1]+" and cyear="+cyear+" order by event";

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


public String techfestView()
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
+"len('000'+ltrim(rtrim(str(month(adate)))))-1,2)+'/'+ ltrim(rtrim(str(year(adate)))) from techfest where rollno="+repin[1]+" and id="+repin[0];

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

public String techfestAllList(int act)
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
if(act==1)					    
sql="select id,techfest.rollno,event,teamid,staffid,staffremarks,rating,hodremarks,teamleader,entrydate,stud.name, "
+"substring(('000'+ltrim(rtrim(str(day(entrydate))))), len('000'+ltrim(rtrim(str(day(entrydate)))))-1,2)+'/'+  substring(('000'+ltrim(rtrim(str(month(entrydate))))),len('000'+ltrim(rtrim(str(month(entrydate)))))-1,2)+'/'+ ltrim(rtrim(str(year(entrydate)))),sf,year "
+"from techfest,stud,student,department where techfest.rollno=stud.rollno and department.dno=student.departmentno and student.rollno=stud.rollno  and academicyear in(select academicyear from academicyear) "
+" and event in("+repin[10]+") and departmentno in ("+repin[9]+")  and hodremarks='Recomended'  and cyear="+cyear+" order by event,dno,year,teamleader";
else
sql="select id,techfest.rollno,event,teamid,staffid,staffremarks,rating,hodremarks,teamleader,entrydate,stud.name, "
+"substring(('000'+ltrim(rtrim(str(day(entrydate))))), len('000'+ltrim(rtrim(str(day(entrydate)))))-1,2)+'/'+  substring(('000'+ltrim(rtrim(str(month(entrydate))))),len('000'+ltrim(rtrim(str(month(entrydate)))))-1,2)+'/'+ ltrim(rtrim(str(year(entrydate)))),sf,year "
+"from techfest,stud,student,department where techfest.rollno=stud.rollno and department.dno=student.departmentno and student.rollno=stud.rollno  and academicyear in(select academicyear from academicyear) "
+" and event in("+repin[10]+") and departmentno in ("+repin[9]+")  and cyear="+cyear+" order by event,dno,year,teamleader";

rs = stmt.executeQuery(sql);

while(rs.next())
{
	for(i=1;i<=14;i++)
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

public String techfestStaffList(String staffid)
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
sql="select id,techfest.rollno,event,teamid,staffid,staffremarks,rating,hodremarks,teamleader,entrydate,stud.name, "
+"substring(('000'+ltrim(rtrim(str(day(entrydate))))), len('000'+ltrim(rtrim(str(day(entrydate)))))-1,2)+'/'+  substring(('000'+ltrim(rtrim(str(month(entrydate))))),len('000'+ltrim(rtrim(str(month(entrydate)))))-1,2)+'/'+ ltrim(rtrim(str(year(entrydate)))),sf,year "
+"from techfest,stud,student,department where techfest.rollno=stud.rollno and department.dno=student.departmentno and student.rollno=stud.rollno  and academicyear in(select academicyear from academicyear) "
+" and event in("+repin[10]+") and staffid='"+staffid+"'  and cyear="+cyear+" order by event,dno,year,teamleader";

rs = stmt.executeQuery(sql);

while(rs.next())
{
	for(i=1;i<=14;i++)
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


public String techfestAllView()
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
+"len('000'+ltrim(rtrim(str(month(adate)))))-1,2)+'/'+ ltrim(rtrim(str(year(adate)))),name,sf from techfest,student,department where student.rollno=techfest.rollno and department.dno=student.departmentno and id="+repin[0];


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




	
	