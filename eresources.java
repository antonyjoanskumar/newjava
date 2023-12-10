package sxcce;
import java.sql.*;
import java.util.StringTokenizer;
import com.jspsmart.upload.*;
import java.util.Date;
import java.io.*;
import dd.DBConnectionManager;

public class eresources
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



public String EresourcesInsert()
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

				
                    sql="insert into eresources(date,regulation,dno,semester,subcode,type,details,fname,login,active) values(getdate(),?,?,?,?,?,?,?,?,?)";
					ps=con.prepareStatement(sql);
					ps.setString(1,repin[2]);
					ps.setString(2,repin[3]);
					ps.setString(3,repin[4]);
					ps.setString(4,repin[5]);
					ps.setString(5,repin[6]);
					ps.setString(6,repin[7]);
					ps.setString(7,repin[8]);
					ps.setString(8,repin[9]);
					ps.setString(9,repin[10]);
					int j = ps.executeUpdate();
					ps.close();
     
                    stmt.close();
					connMgr.freeConnection("xavier",con);
					error="...";
					}catch(Exception e){error=(e.toString());}
return error;	
}

public String EresourcesUpdate(int id)
{
try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;
					    PreparedStatement ps;
					    
                  sql="update eresources set type=?,details=?,active=? where id=?";
					ps=con.prepareStatement(sql);

					ps.setString(1,repin[6]);
					ps.setString(2,repin[7]);
					ps.setString(3,repin[10]);
					ps.setInt(4,id);
					int i = ps.executeUpdate();
					ps.close();
                    stmt.close();
					connMgr.freeConnection("xavier",con);
					error="success";
					}catch(Exception e){error=(e.toString());
					}
					
		return error;
}

public String EresourcesUpload(int id)
{
try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;
					    PreparedStatement ps;
			    
                  sql="update eresources set fname=? where id=?";
					ps=con.prepareStatement(sql);
					ps.setString(1,repin[8]);
					ps.setInt(2,id);
					int i = ps.executeUpdate();
					ps.close();
                    stmt.close();
					connMgr.freeConnection("xavier",con);
					error="success";
					}catch(Exception e){error=sql+repin[11]+id+repin[1]+(e.toString());		}
return error;
}

/*
substring(('000'+ltrim(rtrim(str(day(startdate))))), len('000'+ltrim(rtrim(str(day(startdate)))))-1,2)+'/'+  substring(('000'+ltrim(rtrim(str(month(startdate))))), "
+"len('000'+ltrim(rtrim(str(month(startdate)))))-1,2)+'/'+ ltrim(rtrim(str(year(startdate))))
*/

public String EresourcesDel(int id)
{

try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;
					    PreparedStatement ps;
                  sql="delete from eresources where id=?";
					ps=con.prepareStatement(sql);
					ps.setInt(1,id);
					int i = ps.executeUpdate();
					ps.close();
                    stmt.close();
					connMgr.freeConnection("xavier",con);
					error="success";
					}catch(Exception e){error=(e.toString());
					}
					
		return error;
}

public String EresourcesStaffList()
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
sql="select id,date,regulation,sf,semester,subcode,type,details,fname,login,active from eresources,department where eresources.dno=department.dno and login='"+repin[9]+"' order by date desc";

rs = stmt.executeQuery(sql);

while(rs.next())
{
	for(i=1;i<=11;i++)
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


public String EresourcesView(int id)
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
sql="select id,date,regulation,sf,semester,subcode,type,details,fname,login,active from eresources,department where eresources.dno=department.dno and id="+id;

rs = stmt.executeQuery(sql);

while(rs.next())
{
	for(i=1;i<=11;i++)
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

public String EresourcesList(String subcode, String ayear)
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

String opening="";
String closing="";

sql="select min(opening),max(closing) from attendance.dbo.reopening where academicyear in ('"+ayear+"')";

					rs = stmt.executeQuery(sql);
					if(rs.next()){
						 opening=rs.getString(1);					
						 closing=rs.getString(2);					
					}
					rs.close();

					    
sql="select id,date,regulation,sf,semester,subcode,type,details,fname,login,active from eresources,department where eresources.dno=department.dno  and date between '"+opening+"' and '"+closing+"' and subcode='"+subcode+"' order by type";

rs = stmt.executeQuery(sql);

while(rs.next())
{
	for(i=1;i<=11;i++)
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

public String EresourcesStatus()
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
					    
if(repin[0].equals("0"))
sql="select a.s,name,isnull(cnt,0) c from (select ltrim(rtrim(staffid)) s,name from staff  where active=1 and category='Teaching' ) a "
+"left join (select ltrim(rtrim(login)) s,count(*) cnt from eresources where regulation='"+repin[1]+"' group by login) b on a.s=b.s order by c,a.s";
else
sql="select a.s,name,isnull(cnt,0) c from (select ltrim(rtrim(staffid)) s,name from staff  where active=1 and category='Teaching' and dno in ("+repin[0]+")) a "
+"left join (select ltrim(rtrim(login)) s,count(*) cnt from eresources where regulation='"+repin[1]+"' group by login) b on a.s=b.s order by a.s";

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

public String EresourcesAllStatus()
{
int i=0;
int order=1;
order=Integer.parseInt(repin[4]);
String ord=" ddno,semester,a.s,c";
if(order==1) ord=" ddno,semester,a.s,c";
if(order==2) ord=" c,ddno,semester,a.s";
if(order==3) ord=" ddno,a.s";

count=0;
try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;
String opening="";
String closing="";

sql="select min(opening),max(closing) from attendance.dbo.reopening where academicyear='"+repin[2]+"'";

					rs = stmt.executeQuery(sql);
					if(rs.next()){
						 opening=rs.getString(1);					
						 closing=rs.getString(2);					
					}
					rs.close();
					    

sql="select a.s,name,subcode,subjectname,semester,ddno,sf,isnull(cnt,0) c from ( "
+" select ltrim(rtrim(staff.staffid)) s,name,subcode,subjectname,semester,sf,subjectidentify.dno ddno from subjectidentify,subjecthandled,syllabus,staff,department where "
+" department.dno=subjectidentify.dno and subjecthandled.staffid=staff.staffid "
+" and  subjectidentify.id=subjecthandled.id and subjectidentify.id=syllabus.id and subcode=subjectcode and theoryorpractical in (1,4,5) "
+" and academicyear='"+repin[2]+"' and semester in("+repin[1]+") and subjectidentify.dno in ("+repin[0]+") and mainorsub='M' ) a  "
+" left join (select ltrim(rtrim(login)) s,count(*) cnt,semester sem,subcode scode,dno from eresources where type in("+repin[3]+")  and date between '"+opening+"' and '"+closing+"' group by login,semester,subcode,dno) b "
+" on a.s=b.s and a.subcode=b.scode and a.semester=b.sem and a.ddno=b.dno order by "+ord;

rs = stmt.executeQuery(sql);

while(rs.next())
{
	for(i=1;i<=8;i++)
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




	
	