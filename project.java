package sxcce;
import java.sql.*;
import java.util.StringTokenizer;
import com.jspsmart.upload.*;
import java.util.Date;
import java.io.*;
import dd.DBConnectionManager;

public class project
	{
				String rep[] = new String[1000];
				String repin[] = new String[30];
				String report[][] = new String[30][10000];
				String report1[] = new String[1000];
				String report2[][] = new String[30][10000];
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
	

public String projectView(int dno, int semester, String academicyear1)
{
int i=0;
count=0;
int scount=0;
int yr=(semester+1)/2;
try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;
sql="select pid,rtrim(title)+' ['+rtrim(staffid)+']' from prj_title where semester="+semester+" and dno="+dno+" and academicyear='"+academicyear1+"'";
rs = stmt.executeQuery(sql);

while(rs.next())
{
	report2[0][scount]=rs.getString(1);
	report2[1][scount]=rs.getString(2);
	scount++;
}
rs.close();

sql="select p.rollno,name,department,year,academicyear,isnull(registerno,0) from (select stud.rollno,stud.name,department,year,academicyear from stud,student "
+" where stud.rollno=student.rollno and academicyear='"+academicyear1+"' and year="+yr+" and departmentno="+dno+" and active=1 ) p "
+" left outer join (select rollno,registerno from registerno) q on p.rollno=q.rollno order by p.rollno";

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

for(i=0;i<count;i++)
{
report[6][i]="";
report[7][i]="";

for(int j=0;j<scount;j++)
{
	sql="select pid from prj_stud where pid="+report2[0][j]+" and rollno="+report[0][i];
rs = stmt.executeQuery(sql);

if(rs.next())
{
report[6][i]=report[6][i]+"<option value="+ report2[0][j]+" selected>"+report2[1][j]+"</option>";
report[7][i]=report[7][i]+" "+ report2[1][j];
}
else
report[6][i]=report[6][i]+"<option value="+ report2[0][j]+">"+report2[1][j]+"</option>";
rs.close();

}		
}


stmt.close();
connMgr.freeConnection("xavier",con);
error= "success"; 
}catch(Exception e){error=(e.toString()+count+i+sql);}
return error; 
}



public String projectInsert(int rollno, int semester, String academicyear1,int pid)
{
String sql="";
try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  			Statement stmt = con.createStatement();
			ResultSet rs;
int cid=0;
			PreparedStatement ps;

                    sql="insert into prj_stud values(?,?,?,?)";
					ps=con.prepareStatement(sql);
					ps.setInt(1,rollno);
					ps.setString(2,academicyear1);
					ps.setInt(3,semester);
					ps.setInt(4,pid);
					int j = ps.executeUpdate();
					ps.close();
     
                    stmt.close();
					connMgr.freeConnection("xavier",con);
					error="...";
					}catch(Exception e){error=(e.toString());}

return error;	

}

public String projectDelete(int rollno, int semester, String academicyear1)
{
String sql="";
try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  			Statement stmt = con.createStatement();
			ResultSet rs;
int cid=0;
			PreparedStatement ps;
                    sql="delete from prj_stud where rollno="+rollno+" and semester="+semester+" and academicyear='"+academicyear1+"'";
					ps=con.prepareStatement(sql);
					int k = ps.executeUpdate();
					ps.close();
                    stmt.close();
					connMgr.freeConnection("xavier",con);
					error="...";
					}catch(Exception e){error=(e.toString());}

return error;	

}

public String TitleInsert()
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

					sql="select max(pid)+1 from prj_title";
					rs = stmt.executeQuery(sql);
					if(rs.next())     { 	cid=rs.getInt(1); 	    }
					rs.close();

				
                    sql="insert into prj_title(pid,academicyear,dno,semester,title,title_final,staffid,type,ptype,extref,kstatus,karea,outcome,coracl,corg,status) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
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
					int j = ps.executeUpdate();
					ps.close();
     
                    stmt.close();
					connMgr.freeConnection("xavier",con);
					error="...";
					}catch(Exception e){error=(e.toString());}
return error;	
}

public String TitleUpdate(int id)
{
try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;
					    PreparedStatement ps;
					    
                  sql="update prj_title set title=?, title_final=?, staffid=?, type=?, ptype=?, extref=?, kstatus=?, karea=?, outcome=?, coracl=?, corg=?, status=? where pid=?";
					ps=con.prepareStatement(sql);
					ps.setString(1,repin[4]);
					ps.setString(2,repin[5]);
					ps.setString(3,repin[6]);
					ps.setString(4,repin[7]);
					ps.setString(5,repin[8]);
					ps.setString(6,repin[9]);
					ps.setString(7,repin[10]);
					ps.setString(8,repin[11]);
					ps.setString(9,repin[12]);
					ps.setString(10,repin[13]);
					ps.setString(11,repin[14]);
					ps.setString(12,repin[15]);
					ps.setInt(13,id);
					int i = ps.executeUpdate();
					ps.close();
                    stmt.close();
					connMgr.freeConnection("xavier",con);
					error="success";
					}catch(Exception e){error=(e.toString());
					}
					
		return error;
}

public String TitleUpload(int id)
{
try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;
					    PreparedStatement ps;
			    
                  sql="update prj_title set fname=? where pid=?";
					ps=con.prepareStatement(sql);
					ps.setString(1,repin[16]);
					ps.setInt(2,id);
					int i = ps.executeUpdate();
					ps.close();
                    stmt.close();
					connMgr.freeConnection("xavier",con);
					error="success";
					}catch(Exception e){error=sql+repin[11]+id+repin[1]+(e.toString());		}
return error;
}


public String TitleDel(int id)
{

try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;
					    PreparedStatement ps;
                  sql="delete from prj_title where pid=?";
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

public String TitleList(String accyear, int ddno, int sem)
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
sql="select * from prj_title where academicyear='"+accyear+"' and dno="+ddno+" and semester="+sem+" order by title";

rs = stmt.executeQuery(sql);

while(rs.next())
{
	for(i=1;i<=17;i++)
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


public String TitleView(int id)
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
sql="select * from prj_title where pid="+id;

rs = stmt.executeQuery(sql);

while(rs.next())
{
	for(i=1;i<=17;i++)
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

public String projectStatus()
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
+"left join (select ltrim(rtrim(login)) s,count(*) cnt from project where regulation='"+repin[1]+"' group by login) b on a.s=b.s order by c,a.s";
else
sql="select a.s,name,isnull(cnt,0) c from (select ltrim(rtrim(staffid)) s,name from staff  where active=1 and category='Teaching' and dno in ("+repin[0]+")) a "
+"left join (select ltrim(rtrim(login)) s,count(*) cnt from project where regulation='"+repin[1]+"' group by login) b on a.s=b.s order by a.s";

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

public String projectAllStatus()
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
+" left join (select ltrim(rtrim(login)) s,count(*) cnt,semester sem,subcode scode,dno from project where type in("+repin[3]+")  and date between '"+opening+"' and '"+closing+"' group by login,semester,subcode,dno) b "
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

*/


public void setrep(String[] rep) {this.rep=rep;}  public String[] getrep() {	return this.rep;}
public void setrepin(String[] repin) {this.repin=repin;}  public String[] getrepin() {	return this.repin;}
public void setreport(String[][] report) {this.report=report;}  public String[][] getreport() {	return this.report;}
public void setreport1(String[] report1) {this.report1=report1;}  public String[] getreport1() {	return this.report1;}
public void setcount(int count) {this.count=count;} public int getcount(){	return this.count;}
public void setfname(String fname) { this.fname = fname; } public String getfname() { return this.fname; } 
public void setpname(String pname) { this.pname = pname; } public String getpname() { return this.pname; } 
public void setacademicyear(String academicyear) { this.academicyear = academicyear; } public String getacademicyear() { return this.academicyear; } 



}




	
	