package sxcce;
import java.sql.*;
import java.util.StringTokenizer;
import com.jspsmart.upload.*;
import java.util.Date;
import java.io.*;
import java.math.*;
import java.text.DecimalFormat;
import dd.DBConnectionManager;

public class chronicle
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



public String CellChInsert()
{
sql="";
int cid=1;
try
{
DBConnectionManager connMgr;
connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  			Statement stmt = con.createStatement();
			ResultSet rs;
			PreparedStatement ps;

sql="select isnull((max(cid)+1),1) from cellchronicles";
rs = stmt.executeQuery(sql);
if(rs.next())
{
	cid=rs.getInt(1);
}
if(Integer.parseInt(repin[2])>0)
{
//                    sql="insert into cellchronicles(cid,cellid,pid,photourl,caption,report) values(?,?,?,?,?,?,CURRENT_TIMESTAMP,?,?,?,?)";
                    sql="insert into cellchronicles(cid,cellid,pid,caption,report) values(?,?,?,?,?)";
					ps=con.prepareStatement(sql);
					ps.setInt(1,cid);
					ps.setString(2,repin[2]);
					ps.setString(3,repin[3]);
					ps.setString(4,repin[4]);
					ps.setString(5,repin[5]);
					int j = ps.executeUpdate();
					ps.close();

}				
                    stmt.close();
					connMgr.freeConnection("xavier",con);
					error="...";
					}catch(Exception e){error=(e.toString());}
return error;	
}

public String CellChUpdate(int cid)
{
sql="";
try
{
DBConnectionManager connMgr;
connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  			Statement stmt = con.createStatement();
			ResultSet rs;
			PreparedStatement ps;
if(cid>0)
{
                    sql="update cellchronicles set caption=?, report=? where cid=?";
					ps=con.prepareStatement(sql);
					ps.setString(1,repin[4]);
					ps.setString(2,repin[5]);
					ps.setInt(3,cid);
					int j = ps.executeUpdate();
					ps.close();
}				
                    stmt.close();
					connMgr.freeConnection("xavier",con);
					error="...";
					}catch(Exception e){error=(e.toString());}
return error;	
}


public String CellChStatusUpdate(int cid,String staffid,int staff)
{
try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;
					    PreparedStatement ps;
if(staff==1)					    
                  sql="update cellchronicles set estaffid=?, edate=CURRENT_TIMESTAMP where cid=?";
else if(staff==2) 
                  sql="update cellchronicles set vstaffid=?, edate=CURRENT_TIMESTAMP where cid=?";
else                
                  sql="update cellchronicles set astaffid=?, edate=CURRENT_TIMESTAMP where cid=?";
					ps=con.prepareStatement(sql);
					ps.setString(1,staffid);
					ps.setInt(2,cid);
					int i = ps.executeUpdate();
					ps.close();
                    stmt.close();
					connMgr.freeConnection("xavier",con);
					error="success";
					}catch(Exception e){error=(e.toString());
					}
					
		return error;
}


public String CellChPView(int cellid,int pid)
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
sql="select * from cellchronicles where cellid="+cellid+" and pid="+pid;

rs = stmt.executeQuery(sql);

if(rs.next())
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

public String CellChPViewAll(String fd, String td,String staffid)
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

			StringTokenizer st2 = new StringTokenizer(td,"/");
		    d1=st2.nextToken();
		    m1=st2.nextToken();
		    y1=st2.nextToken();
			date2=m1+"/"+d1+"/"+y1;

}catch(Exception e){}

try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;
if(staffid.trim().equals("x"))					    
sql="select c.cid,cells.cellid,p.pid,cellname,staff.staffid,name,program,caption,report,pname,fname,isnull(estaffid,0)as e,format(date,'dd/MM/yyyy'),isnull(vstaffid,0)as v from cells,cellprogram p,cellchronicles c,staff "
+" where c.cellid=p.cellid and c.pid=p.pid and p.cellid=cells.cellid and  convener=staff.staffid and isnull(vstaffid,0) not in ('0') and date between '"+date1+"' and '"+date2+"' order by date desc";
else if(staffid.trim().equals("v"))					    
sql="select c.cid,cells.cellid,p.pid,cellname,staff.staffid,name,program,caption,report,pname,fname,isnull(estaffid,0)as e,format(date,'dd/MM/yyyy'),isnull(vstaffid,0)as v from cells,cellprogram p,cellchronicles c,staff "
+" where c.cellid=p.cellid and c.pid=p.pid and p.cellid=cells.cellid and  convener=staff.staffid and date between '"+date1+"' and '"+date2+"' order by v,e desc,date desc";
else					    
sql="select c.cid,cells.cellid,p.pid,cellname,staff.staffid,name,program,caption,report,pname,fname,isnull(estaffid,0)as e,format(date,'dd/MM/yyyy'),isnull(vstaffid,0)as v from cells,cellprogram p,cellchronicles c,staff "
+" where c.cellid=p.cellid and c.pid=p.pid and p.cellid=cells.cellid and  convener=staff.staffid and dno in (select dno from celloffbearers,staff "
//+" where userid=staffid and cellid=1051 and academicyear in(select academicyear from academicyear) "
+" where userid=staffid and cellid=1066 and academicyear in(select academicyear from academicyear) "
+" and responsibility='Staff Member' and userid='"+staffid+"') and date between '"+date1+"' and '"+date2+"' order by v,e,date desc";

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


if(staffid.trim().equals("x"))					    
sql="select c.cid,cells.cellid,p.aid,cellname,staff.staffid,name,awards,caption,report,pname,fname,isnull(estaffid,0)as e,format(p.adate,'dd/MM/yyyy'),isnull(vstaffid,0)as v from cells,cellawards p,cellchronicles c,staff "
+" where c.cellid=p.cellid and c.pid=p.aid+10000 and p.cellid=cells.cellid and  convener=staff.staffid and isnull(vstaffid,0) not in ('0') and p.adate between '"+date1+"' and '"+date2+"' order by p.adate desc";
else if(staffid.trim().equals("v"))					    
sql="select c.cid,cells.cellid,p.aid,cellname,staff.staffid,name,awards,caption,report,pname,fname,isnull(estaffid,0)as e,format(p.adate,'dd/MM/yyyy'),isnull(vstaffid,0)as v from cells,cellawards p,cellchronicles c,staff "
+" where c.cellid=p.cellid and c.pid=p.aid+10000 and p.cellid=cells.cellid and  convener=staff.staffid and p.adate between '"+date1+"' and '"+date2+"' order by v,e desc,p.adate desc";
else					    
sql=" select c.cid,cells.cellid,p.aid,cellname,staff.staffid,name,awards,caption,report,pname,fname,isnull(estaffid,0)as e,format(p.adate,'dd/MM/yyyy'),isnull(vstaffid,0)as v from cells,cellawards p,cellchronicles c,staff "
+" where c.cellid=p.cellid and c.pid=p.aid+10000 and p.cellid=cells.cellid and  convener=staff.staffid and dno in (select dno from celloffbearers,staff "
//+" where userid=staffid and cellid=1051 and academicyear in(select academicyear from academicyear) "
+" where userid=staffid and cellid=1066 and academicyear in(select academicyear from academicyear) "
+" and responsibility='Staff Member' and userid='"+staffid+"') and p.adate between '"+date1+"' and '"+date2+"' order by v,e,p.adate desc";

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

public String CellChPViewAll(String staffid)
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
if(staffid.trim().equals("x"))					    
sql="select c.cid,cells.cellid,p.pid,cellname,staff.staffid,name,program,caption,report,pname,fname,isnull(estaffid,0)as e,format(date,'dd/MM/yyyy'),isnull(vstaffid,0)as v from cells,cellprogram p,cellchronicles c,staff "
+" where c.cellid=p.cellid and c.pid=p.pid and p.cellid=cells.cellid and  convener=staff.staffid and isnull(vstaffid,0) not in ('0') order by date desc";
else if(staffid.trim().equals("v"))					    
sql="select c.cid,cells.cellid,p.pid,cellname,staff.staffid,name,program,caption,report,pname,fname,isnull(estaffid,0)as e,format(date,'dd/MM/yyyy'),isnull(vstaffid,0)as v from cells,cellprogram p,cellchronicles c,staff "
+" where c.cellid=p.cellid and c.pid=p.pid and p.cellid=cells.cellid and  convener=staff.staffid order by v,e desc,date desc";
else					    
sql="select c.cid,cells.cellid,p.pid,cellname,staff.staffid,name,program,caption,report,pname,fname,isnull(estaffid,0)as e,format(date,'dd/MM/yyyy'),isnull(vstaffid,0)as v from cells,cellprogram p,cellchronicles c,staff "
+" where c.cellid=p.cellid and c.pid=p.pid and p.cellid=cells.cellid and  convener=staff.staffid and dno in (select dno from celloffbearers,staff "
//+" where userid=staffid and cellid=1051 and academicyear in(select academicyear from academicyear) "
+" where userid=staffid and cellid=1066 and academicyear in(select academicyear from academicyear) "
+" and responsibility='Staff Member' and userid='"+staffid+"') order by v,e,date desc";

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


if(staffid.trim().equals("x"))					    
sql="select c.cid,cells.cellid,p.aid,cellname,staff.staffid,name,awards,caption,report,pname,fname,isnull(estaffid,0)as e,format(p.adate,'dd/MM/yyyy'),isnull(vstaffid,0)as v from cells,cellawards p,cellchronicles c,staff "
+" where c.cellid=p.cellid and c.pid=p.aid+10000 and p.cellid=cells.cellid and  convener=staff.staffid and isnull(vstaffid,0) not in ('0') order by p.adate desc";
else if(staffid.trim().equals("v"))					    
sql="select c.cid,cells.cellid,p.aid,cellname,staff.staffid,name,awards,caption,report,pname,fname,isnull(estaffid,0)as e,format(p.adate,'dd/MM/yyyy'),isnull(vstaffid,0)as v from cells,cellawards p,cellchronicles c,staff "
+" where c.cellid=p.cellid and c.pid=p.aid+10000 and p.cellid=cells.cellid and  convener=staff.staffid order by v,e desc,p.adate";
else					    
sql=" select c.cid,cells.cellid,p.aid,cellname,staff.staffid,name,awards,caption,report,pname,fname,isnull(estaffid,0)as e,format(p.adate,'dd/MM/yyyy'),isnull(vstaffid,0)as v from cells,cellawards p,cellchronicles c,staff "
+" where c.cellid=p.cellid and c.pid=p.aid+10000 and p.cellid=cells.cellid and  convener=staff.staffid and dno in (select dno from celloffbearers,staff "
//+" where userid=staffid and cellid=1051 and academicyear in(select academicyear from academicyear) "
+" where userid=staffid and cellid=1066 and academicyear in(select academicyear from academicyear) "
+" and responsibility='Staff Member' and userid='"+staffid+"') order by v,e,p.adate";

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

public String chroniclestaffInsert()
{
sql="";
try
{
DBConnectionManager connMgr;
connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  			Statement stmt = con.createStatement();
			ResultSet rs;
			PreparedStatement ps;


if(Integer.parseInt(repin[1])>0)
{
                    sql="insert into chroniclestaff values(?,?)";
					ps=con.prepareStatement(sql);
					ps.setString(1,repin[1]);
					ps.setString(2,repin[2]);
					int j = ps.executeUpdate();
					ps.close();
}				
    
                    stmt.close();
					connMgr.freeConnection("xavier",con);
					error="...";
					}catch(Exception e){error=(e.toString());}
return error;	
}

public String BookingRemUpdate()
{
	int flag=0;
try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();

					    ResultSet rs;

					    PreparedStatement ps;

sql="select * from chroniclestaff where dno="+repin[1];
try
{
rs = stmt.executeQuery(sql);
if(rs.next()) flag=1;
rs.close();
stmt.close();
}catch(Exception e){}

if(flag==0)
{
                    sql="insert into chroniclestaff values(?,?)";
					ps=con.prepareStatement(sql);
					ps.setString(1,repin[1]);
					ps.setString(2,repin[2]);
					int j = ps.executeUpdate();
					ps.close();
	 
}
else
{
                  sql="update chroniclestaff set staffid=? where dno=?";
					ps=con.prepareStatement(sql);
					ps.setString(1,repin[2]);
					ps.setString(2,repin[1]);
					int i = ps.executeUpdate();
					ps.close();
}					
					connMgr.freeConnection("xavier",con);
					error="success";
					}catch(Exception e){error=(e.toString());}
		return error;
}


public String BookingRemDel(int id)
{

try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;
					    PreparedStatement ps;
                  sql="delete from chroniclestaff where dno=?";
					ps=con.prepareStatement(sql);
					ps.setString(1,repin[1]);
					int i = ps.executeUpdate();
					ps.close();
                    stmt.close();
					connMgr.freeConnection("xavier",con);
					error="success";
					}catch(Exception e){error=(e.toString());
					}
					
		return error;
}


public String chroniclestaffView(int id)
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
sql="select c.dno,dname,sf,s.staffid,name from chroniclestaff c,department d,staff s where s.staffid=c.staffid and c.dno=d.dno and d.dno="+id;

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

public String chroniclestaffList()
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
sql="select c.dno,dname,sf,s.staffid,name from chroniclestaff c,department d,staff s where s.staffid=c.staffid and c.dno=d.dno order by c.dno";

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


public String BookingReport1()
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
sql="select stud.rollno,student.name,rtrim(communicationhouse)+'<br>'+rtrim(communicationtown)+'-'+rtrim(communicationpincode)+'<br>'+rtrim(communicationdistrict)+'<br>'+rtrim(communicationstate), "
+" phone+'<BR>'+cell,format(yearofjoining,'dd/MM/yyyy'),community,format(dateofbirth,'dd/MM/yyyy'),religion,parish, "
+" type,quota,b.remarks,format(bookingdate,'dd/MM/yyyy'),staffid,b.active,dno1,dno2,dno3,departmentno "
+" from student,bookingrem b,stud where stud.rollno=student.rollno and bookingid=student.rollno and academicyear='"+repin[1]+"' "
+" and departmentno in ("+repin[2]+") and religion in  ("+repin[3]+") and type in  ("+repin[4]+") and student.active in  ("+repin[5]+") "+repin[7];

rs = stmt.executeQuery(sql);

while(rs.next())
{
	for(i=1;i<=19;i++)
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


/*
 

public String chroniclesstaffInsert()
{
sql="";
try
{
DBConnectionManager connMgr;
connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  			Statement stmt = con.createStatement();
			ResultSet rs;
			PreparedStatement ps;


if(Integer.parseInt(repin[1])>0)
{
                    sql="insert into chroniclesstaff values(?,?)";
					ps=con.prepareStatement(sql);
					ps.setString(1,repin[1]);
					ps.setString(2,repin[2]);
					ps.setString(3,repin[3]);
					ps.setString(4,repin[4]);
					ps.setString(5,repin[5]);
					ps.setString(6,repin[6]);
					ps.setString(7,repin[7]);
					ps.setString(8,repin[9]);
					ps.setString(9,repin[10]);
					ps.setString(10,repin[11]);
					ps.setString(11,repin[12]);
					int j = ps.executeUpdate();
					ps.close();
}				
     
                    stmt.close();
					connMgr.freeConnection("xavier",con);
					error="...";
					}catch(Exception e){error=(e.toString());}
return error;	
}

public String BookingRemUpdate(int id)
{
	int flag=0;
try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();

					    ResultSet rs;

					    PreparedStatement ps;

sql="select * from bookingrem where bookingid="+id;
try
{
rs = stmt.executeQuery(sql);
if(rs.next()) flag=1;
rs.close();
stmt.close();
}catch(Exception e){}

if(flag==0)
{
//	 BookingRemInsert();
                    sql="insert into bookingrem values(?,?,?,?,?,?,?,GETDATE(),?,?,?,?)";
					ps=con.prepareStatement(sql);
					ps.setString(1,repin[1]);
					ps.setString(2,repin[2]);
					ps.setString(3,repin[3]);
					ps.setString(4,repin[4]);
					ps.setString(5,repin[5]);
					ps.setString(6,repin[6]);
					ps.setString(7,repin[7]);
					ps.setString(8,repin[9]);
					ps.setString(9,repin[10]);
					ps.setString(10,repin[11]);
					ps.setString(11,repin[12]);
					int j = ps.executeUpdate();
					ps.close();
	 
}
else
{
                  sql="update bookingrem set dno1=?, dno2=?, dno3=?, type=?, quota=?, remarks=?, staffid=?, active=?, parish=?, aadhaar=? where bookingid=?";
					ps=con.prepareStatement(sql);
					ps.setString(1,repin[2]);
					ps.setString(2,repin[3]);
					ps.setString(3,repin[4]);
					ps.setString(4,repin[5]);
					ps.setString(5,repin[6]);
					ps.setString(6,repin[7]);
					ps.setString(7,repin[9]);
					ps.setString(8,repin[10]);
					ps.setString(9,repin[11]);
					ps.setString(10,repin[12]);
					ps.setInt(11,id);
					int i = ps.executeUpdate();
					ps.close();
}					
					connMgr.freeConnection("xavier",con);
					error="success";
					}catch(Exception e){error=(e.toString());}
					
		return error;
}



public String BookingRemDel(int id)
{

try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;
					    PreparedStatement ps;
                  sql="delete from bookingrem where id=?";
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


public String BookingRemView(int id)
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
sql="select * from bookingrem where bookingid="+id;

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

public String BookingRemViewAll(int id)
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
sql="select bookingid,dno1,dno2,dno3,type,quota,b.remarks,bookingdate,staffid,b.active,parish,aadhaar,name from student,bookingrem b where bookingid=rollno and bookingid="+id;

rs = stmt.executeQuery(sql);

while(rs.next())
{
	for(i=1;i<=13;i++)
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


public String BookingReport1()
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
sql="select stud.rollno,student.name,rtrim(communicationhouse)+'<br>'+rtrim(communicationtown)+'-'+rtrim(communicationpincode)+'<br>'+rtrim(communicationdistrict)+'<br>'+rtrim(communicationstate), "
+" phone+'<BR>'+cell,format(yearofjoining,'dd/MM/yyyy'),community,format(dateofbirth,'dd/MM/yyyy'),religion,parish, "
+" type,quota,b.remarks,format(bookingdate,'dd/MM/yyyy'),staffid,b.active,dno1,dno2,dno3,departmentno "
+" from student,bookingrem b,stud where stud.rollno=student.rollno and bookingid=student.rollno and academicyear='"+repin[1]+"' "
+" and departmentno in ("+repin[2]+") and religion in  ("+repin[3]+") and type in  ("+repin[4]+") and student.active in  ("+repin[5]+") "+repin[7];

rs = stmt.executeQuery(sql);

while(rs.next())
{
	for(i=1;i<=19;i++)
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
*/

/*
public String nbainnovativeteachingList(int sid)
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
		

sql="select * from nbainnovativeteaching where subid="+sid+" and subcode='"+repin[5]+"' order by id";

rs = stmt.executeQuery(sql);

while(rs.next())
{
	for(i=1;i<=4;i++)
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

*/


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




	
	