package sxcce;
import java.sql.*;
import java.util.StringTokenizer;
import com.jspsmart.upload.*;
import java.util.Date;
import java.io.*;
import java.math.*;
import java.text.DecimalFormat;
import dd.DBConnectionManager;

public class discipline
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

public String disciplineInsert()
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
i1=Integer.parseInt(repin[3]);			
i2=Integer.parseInt(repin[4]);			
}
catch(Exception e){}			
					sql="select max(cid)+1 from discipline";
					rs = stmt.executeQuery(sql);
					if(rs.next())    cid=rs.getInt(1); 	    else cid=1;
					rs.close();
                    sql="insert into  discipline(cid,cdate,ctype,rollno,staffid,cdetails,maction,mstatus,hstatus,pstatus) values(?,GETDATE(),?,?,?,?,0,0,0,0)";
					ps=con.prepareStatement(sql);
					ps.setInt(1,cid);
					ps.setInt(2,i1);
					ps.setInt(3,i2);
					ps.setString(4,repin[5]);
					ps.setString(5,repin[6]);
					int j = ps.executeUpdate();
					ps.close();
                    stmt.close();
					connMgr.freeConnection("xavier",con);
					error="Success";
					}catch(Exception e){error=(e.toString());}
return error;	
}

public String dMentorUpdate(int id)
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
i1=Integer.parseInt(repin[8]);
i2=Integer.parseInt(repin[10]);
}
catch(Exception e){}			
					    
                  sql="update discipline set mdate=getdate(),maction=?,adetails=?,mstatus=? where cid=?";
					ps=con.prepareStatement(sql);
					ps.setInt(1,i1);
					ps.setString(2,repin[9]);
					ps.setInt(3,i2);
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

public String dHodUpdate(int id)
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
i1=Integer.parseInt(repin[12]);
}
catch(Exception e){}			
					    
                  sql="update discipline set hdate=getdate(),hstatus=? where cid=?";
					ps=con.prepareStatement(sql);
					ps.setInt(1,i1);
					ps.setInt(2,id);
					int i = ps.executeUpdate();
					ps.close();
                    stmt.close();
					connMgr.freeConnection("xavier",con);
					error="success";
					}catch(Exception e){error=(e.toString());
					}
					
		return error;
}

public String dPrincUpdate(int id)
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
i1=Integer.parseInt(repin[14]);
}
catch(Exception e){}			
					    
                  sql="update discipline set pdate=getdate(),pstatus=? where cid=?";
					ps=con.prepareStatement(sql);
					ps.setInt(1,i1);
					ps.setInt(2,id);
					int i = ps.executeUpdate();
					ps.close();
                    stmt.close();
					connMgr.freeConnection("xavier",con);
					error="success";
					}catch(Exception e){error=(e.toString());
					}
					
		return error;
}

public String disciplineView(int id)
{
String quer="";

try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;

quer="select * from (select cid,d.cdate,ctype,d.rollno r1,d.staffid,cdetails,mdate,maction,adetails,mstatus,hdate,hstatus,pdate,pstatus, "
+" CONVERT(varchar, d.cdate, 103) cdt, CONVERT(varchar, mdate, 103) mdt, CONVERT(varchar, hdate, 103) hdt, CONVERT(varchar, pdate, 103) pdt, "
+" s.name n1,s.year,de.sf,st.name n2,dtypename,dtype,s.department,da.atypename  "
+" from discipline d,disciplinetypes dt,disciplineactions da,stud s,staff st,department de where s.department=de.dno and d.staffid=st.staffid and s.rollno=d.rollno and d.ctype=dt.dtypeno and d.maction=da.atypeno "
+" and academicyear in (select academicyear from academicyear) and cid="+id+") p "
+" left outer join (select staff.staffid,tutor.rollno r2,name n3 from tutor,staff where staff.staffid=tutor.staffid and academicyear in (select academicyear from academicyear)) q1 on p.r1=q1.r2 "
+" left outer join (select discipline.rollno r3,count(*) c from discipline,student where discipline.rollno=student.rollno group by discipline.rollno) q2 on p.r1=q2.r3 "
+" order by cdate desc";

rs = stmt.executeQuery(quer);

count=0;
while(rs.next())
{
	for(int i=1;i<=31;i++)
	{
		report[i-1][count]=rs.getString(i);
	}
	count++;
}
rs.close();

stmt.close();
con.close();
}catch(Exception e){return e.toString()+quer;}
return "suc";
}

public String dStaffRep(String cstaffid)
{
String quer="";

try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;
quer="select * from (select cid,d.cdate,ctype,d.rollno r1,d.staffid,cdetails,mdate,maction,adetails,mstatus,hdate,hstatus,pdate,pstatus, "
+" CONVERT(varchar, d.cdate, 103) cdt, CONVERT(varchar, mdate, 103) mdt, CONVERT(varchar, hdate, 103) hdt, CONVERT(varchar, pdate, 103) pdt, "
+" s.name n1,s.year,de.sf,st.name n2,dtypename,dtype,s.department,da.atypename "
+" from discipline d,disciplinetypes dt,disciplineactions da,stud s,staff st,department de where s.department=de.dno and d.staffid=st.staffid and s.rollno=d.rollno and d.ctype=dt.dtypeno and d.maction=da.atypeno "
+" and academicyear in (select academicyear from academicyear) and d.staffid='"+cstaffid+"') p "
+" left outer join (select staff.staffid,tutor.rollno r2,name n3 from tutor,staff where staff.staffid=tutor.staffid and academicyear in (select academicyear from academicyear)) q1 on p.r1=q1.r2 "
+" left outer join (select discipline.rollno r3,count(*) c from discipline,student where discipline.rollno=student.rollno group by discipline.rollno) q2 on p.r1=q2.r3 "
+" order by cdate desc";


rs = stmt.executeQuery(quer);

count=0;
while(rs.next())
{
	for(int i=1;i<=31;i++)
	{
		report[i-1][count]=rs.getString(i);
	}
	count++;
}
rs.close();

stmt.close();
con.close();
}catch(Exception e){return e.toString()+quer;}
return "suc";
}

public String dMentorRep(String cstaffid, int flag)
{
String quer="";

try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;
quer="select * from (select cid,d.cdate,ctype,d.rollno r1,d.staffid,cdetails,mdate,maction,adetails,mstatus,hdate,hstatus,pdate,pstatus, "
+" CONVERT(varchar, d.cdate, 103) cdt, CONVERT(varchar, mdate, 103) mdt, CONVERT(varchar, hdate, 103) hdt, CONVERT(varchar, pdate, 103) pdt, "
+" s.name n1,s.year,de.sf,st.name n2,dtypename,dtype,s.department,da.atypename "
+" from discipline d,disciplinetypes dt,disciplineactions da,stud s,staff st,department de where s.department=de.dno and d.staffid=st.staffid and s.rollno=d.rollno and d.ctype=dt.dtypeno and d.maction=da.atypeno and academicyear in (select academicyear from academicyear) ) p "
+" left outer join (select staff.staffid s1,tutor.rollno r2,name n3 from tutor,staff where staff.staffid=tutor.staffid and academicyear in (select academicyear from academicyear)) q1 on p.r1=q1.r2 "
+" left outer join (select discipline.rollno r3,count(*) c from discipline,student where discipline.rollno=student.rollno group by discipline.rollno) q2 on p.r1=q2.r3 "
+" where q1.s1='"+cstaffid+"' order by p.mstatus,cdate desc";

if(flag==1)
quer="select * from (select cid,d.cdate,ctype,d.rollno r1,d.staffid,cdetails,mdate,maction,adetails,mstatus,hdate,hstatus,pdate,pstatus, "
+" CONVERT(varchar, d.cdate, 103) cdt, CONVERT(varchar, mdate, 103) mdt, CONVERT(varchar, hdate, 103) hdt, CONVERT(varchar, pdate, 103) pdt, "
+" s.name n1,s.year,de.sf,st.name n2,dtypename,dtype,s.department,da.atypename "
+" from discipline d,disciplinetypes dt,disciplineactions da,stud s,staff st,department de where s.department=de.dno and d.staffid=st.staffid and s.rollno=d.rollno and d.ctype=dt.dtypeno and d.maction=da.atypeno and academicyear in (select academicyear from academicyear) ) p "
+" left outer join (select staff.staffid s1,tutor.rollno r2,name n3 from tutor,staff where staff.staffid=tutor.staffid and academicyear in (select academicyear from academicyear)) q1 on p.r1=q1.r2 "
+" left outer join (select discipline.rollno r3,count(*) c from discipline,student where discipline.rollno=student.rollno group by discipline.rollno) q2 on p.r1=q2.r3 "
+" where q1.s1='"+cstaffid+"' and p.mstatus=0 order by cdate desc";

rs = stmt.executeQuery(quer);

count=0;
while(rs.next())
{
	for(int i=1;i<=31;i++)
	{
		report[i-1][count]=rs.getString(i);
	}
	count++;
}
rs.close();

stmt.close();
con.close();
}catch(Exception e){return e.toString()+quer;}
return "suc";
}


public String dHodRep(String cstaffid, int flag,String depts)
{
String quer="";
String yearin="1";
try{
if(depts.equals("7"))
	yearin="1";
else if(depts.equals("16"))
	yearin="1,2";
else if(depts.equals("18"))
	yearin="1,2,3";
else
	yearin="2,3,4";
}catch(Exception e){}

try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;
quer="select * from (select cid,d.cdate,ctype,d.rollno r1,d.staffid,cdetails,mdate,maction,adetails,mstatus,hdate,hstatus,pdate,pstatus, "
+" CONVERT(varchar, d.cdate, 103) cdt, CONVERT(varchar, mdate, 103) mdt, CONVERT(varchar, hdate, 103) hdt, CONVERT(varchar, pdate, 103) pdt, "
+" s.name n1,s.year,de.sf,st.name n2,dtypename,dtype,s.department,da.atypename "
+" from discipline d,disciplinetypes dt,disciplineactions da,stud s,staff st,department de where s.department=de.dno and d.staffid=st.staffid and s.rollno=d.rollno and d.ctype=dt.dtypeno and d.maction=da.atypeno and academicyear in (select academicyear from academicyear) ) p "
+" left outer join (select staff.staffid s1,tutor.rollno r2,name n3 from tutor,staff where staff.staffid=tutor.staffid and academicyear in (select academicyear from academicyear)) q1 on p.r1=q1.r2 "
+" left outer join (select discipline.rollno r3,count(*) c from discipline,student where discipline.rollno=student.rollno group by discipline.rollno) q2 on p.r1=q2.r3 "
+" where department in ("+depts+") order by p.hstatus,cdate desc";

if(flag==1)
{
if(depts.equals("7"))
{
quer="select * from (select cid,d.cdate,ctype,d.rollno r1,d.staffid,cdetails,mdate,maction,adetails,mstatus,hdate,hstatus,pdate,pstatus, "
+" CONVERT(varchar, d.cdate, 103) cdt, CONVERT(varchar, mdate, 103) mdt, CONVERT(varchar, hdate, 103) hdt, CONVERT(varchar, pdate, 103) pdt, "
+" s.name n1,s.year,de.sf,st.name n2,dtypename,dtype,s.department,da.atypename "
+" from discipline d,disciplinetypes dt,disciplineactions da,stud s,staff st,department de where s.department=de.dno and d.staffid=st.staffid and s.rollno=d.rollno and d.ctype=dt.dtypeno and d.maction=da.atypeno and academicyear in (select academicyear from academicyear) ) p "
+" left outer join (select staff.staffid s1,tutor.rollno r2,name n3 from tutor,staff where staff.staffid=tutor.staffid and academicyear in (select academicyear from academicyear)) q1 on p.r1=q1.r2 "
+" left outer join (select discipline.rollno r3,count(*) c from discipline,student where discipline.rollno=student.rollno group by discipline.rollno) q2 on p.r1=q2.r3 "
+" where department in(1,2,3,4,5,8,14,17,27,91,92,93,94,95,96,97,98) and year in("+yearin+") and p.hstatus=0 order by cdate desc";
}
else
{
quer="select * from (select cid,d.cdate,ctype,d.rollno r1,d.staffid,cdetails,mdate,maction,adetails,mstatus,hdate,hstatus,pdate,pstatus, "
+" CONVERT(varchar, d.cdate, 103) cdt, CONVERT(varchar, mdate, 103) mdt, CONVERT(varchar, hdate, 103) hdt, CONVERT(varchar, pdate, 103) pdt, "
+" s.name n1,s.year,de.sf,st.name n2,dtypename,dtype,s.department,da.atypename "
+" from discipline d,disciplinetypes dt,disciplineactions da,stud s,staff st,department de where s.department=de.dno and d.staffid=st.staffid and s.rollno=d.rollno and d.ctype=dt.dtypeno and d.maction=da.atypeno and academicyear in (select academicyear from academicyear) ) p "
+" left outer join (select staff.staffid s1,tutor.rollno r2,name n3 from tutor,staff where staff.staffid=tutor.staffid and academicyear in (select academicyear from academicyear)) q1 on p.r1=q1.r2 "
+" left outer join (select discipline.rollno r3,count(*) c from discipline,student where discipline.rollno=student.rollno group by discipline.rollno) q2 on p.r1=q2.r3 "
+" where department in ("+depts+") and year in("+yearin+") and p.hstatus=0 order by cdate desc";
	
}
}
else
{
if(depts.equals("7"))
{
quer="select * from (select cid,d.cdate,ctype,d.rollno r1,d.staffid,cdetails,mdate,maction,adetails,mstatus,hdate,hstatus,pdate,pstatus, "
+" CONVERT(varchar, d.cdate, 103) cdt, CONVERT(varchar, mdate, 103) mdt, CONVERT(varchar, hdate, 103) hdt, CONVERT(varchar, pdate, 103) pdt, "
+" s.name n1,s.year,de.sf,st.name n2,dtypename,dtype,s.department,da.atypename "
+" from discipline d,disciplinetypes dt,disciplineactions da,stud s,staff st,department de where s.department=de.dno and d.staffid=st.staffid and s.rollno=d.rollno and d.ctype=dt.dtypeno and d.maction=da.atypeno and academicyear in (select academicyear from academicyear) ) p "
+" left outer join (select staff.staffid s1,tutor.rollno r2,name n3 from tutor,staff where staff.staffid=tutor.staffid and academicyear in (select academicyear from academicyear)) q1 on p.r1=q1.r2 "
+" left outer join (select discipline.rollno r3,count(*) c from discipline,student where discipline.rollno=student.rollno group by discipline.rollno) q2 on p.r1=q2.r3 "
+" where department in(1,2,3,4,5,8,14,17,27,91,92,93,94,95,96,97,98) and year in("+yearin+") order by cdate desc";
}
else
{
quer="select * from (select cid,d.cdate,ctype,d.rollno r1,d.staffid,cdetails,mdate,maction,adetails,mstatus,hdate,hstatus,pdate,pstatus, "
+" CONVERT(varchar, d.cdate, 103) cdt, CONVERT(varchar, mdate, 103) mdt, CONVERT(varchar, hdate, 103) hdt, CONVERT(varchar, pdate, 103) pdt, "
+" s.name n1,s.year,de.sf,st.name n2,dtypename,dtype,s.department,da.atypename "
+" from discipline d,disciplinetypes dt,disciplineactions da,stud s,staff st,department de where s.department=de.dno and d.staffid=st.staffid and s.rollno=d.rollno and d.ctype=dt.dtypeno and d.maction=da.atypeno and academicyear in (select academicyear from academicyear) ) p "
+" left outer join (select staff.staffid s1,tutor.rollno r2,name n3 from tutor,staff where staff.staffid=tutor.staffid and academicyear in (select academicyear from academicyear)) q1 on p.r1=q1.r2 "
+" left outer join (select discipline.rollno r3,count(*) c from discipline,student where discipline.rollno=student.rollno group by discipline.rollno) q2 on p.r1=q2.r3 "
+" where department in ("+depts+") and year in("+yearin+") order by cdate desc";
	
}
}


rs = stmt.executeQuery(quer);

count=0;
while(rs.next())
{
	for(int i=1;i<=31;i++)
	{
		report[i-1][count]=rs.getString(i);
	}
	count++;
}
rs.close();

stmt.close();
con.close();
}catch(Exception e){return e.toString()+quer;}
return "suc";
}

public String dAdminRep(int ctyp)
{
String quer="";

try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;
quer="select * from (select cid,d.cdate,ctype,d.rollno r1,d.staffid,cdetails,mdate,maction,adetails,mstatus,hdate,hstatus,pdate,pstatus, "
+" CONVERT(varchar, d.cdate, 103) cdt, CONVERT(varchar, mdate, 103) mdt, CONVERT(varchar, hdate, 103) hdt, CONVERT(varchar, pdate, 103) pdt, "
+" s.name n1,s.year,de.sf,st.name n2,dtypename,dtype,s.department,da.atypename "
+" from discipline d,disciplinetypes dt,disciplineactions da,stud s,staff st,department de where s.department=de.dno and d.staffid=st.staffid and s.rollno=d.rollno and d.ctype=dt.dtypeno and d.maction=da.atypeno "
+"  and d.cdate between DATEADD(mm,(DATEPART(mm,GETDATE())-1)/6 * 6,DATEADD(yy,YEAR(GETDATE())-1900,0)) and DATEADD(mm,((DATEPART(mm,GETDATE())-1)/6 * 6) + 6,DATEADD(yy,YEAR(GETDATE())-1900,-1)) and academicyear in (select academicyear from academicyear) ) p "
+" left outer join (select staff.staffid,tutor.rollno r2,name n3 from tutor,staff where staff.staffid=tutor.staffid and academicyear in (select academicyear from academicyear)) q1 on p.r1=q1.r2 "
+" left outer join (select discipline.rollno r3,count(*) c from discipline,student where discipline.rollno=student.rollno group by discipline.rollno) q2 on p.r1=q2.r3 "
+" where ctype in("+ctyp+") order by cdate desc";


rs = stmt.executeQuery(quer);

count=0;
while(rs.next())
{
	for(int i=1;i<=31;i++)
	{
		report[i-1][count]=rs.getString(i);
	}
	count++;
}
rs.close();

stmt.close();
con.close();
}catch(Exception e){return e.toString()+quer;}
return "suc";
}

public String dAdminDeptRep(int ctyp)
{
String quer="";

String deptin="1";
String yearin="1";

try{
if(ctyp==7)
{
	deptin="1,2,3,4,5,8,14,17,27";
	yearin="1";
}
else if((ctyp==1) || (ctyp==2) || (ctyp==3) || (ctyp==4) || (ctyp==5) || (ctyp==8) || (ctyp==14) || (ctyp==17) || (ctyp==27) )
{
	deptin=ctyp+"";
	yearin="2,3,4";
}
else
{
	deptin=ctyp+"";
	yearin="1,2,3";
}
}catch(Exception e){}

try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;
quer="select * from (select cid,d.cdate,ctype,d.rollno r1,d.staffid,cdetails,mdate,maction,adetails,mstatus,hdate,hstatus,pdate,pstatus, "
+" CONVERT(varchar, d.cdate, 103) cdt, CONVERT(varchar, mdate, 103) mdt, CONVERT(varchar, hdate, 103) hdt, CONVERT(varchar, pdate, 103) pdt, "
+" s.name n1,s.year,de.sf,st.name n2,dtypename,dtype,s.department,da.atypename,department dept "
+" from discipline d,disciplinetypes dt,disciplineactions da,stud s,staff st,department de where s.department=de.dno and d.staffid=st.staffid and s.rollno=d.rollno and d.ctype=dt.dtypeno and d.maction=da.atypeno "
+"  and d.cdate between DATEADD(mm,(DATEPART(mm,GETDATE())-1)/6 * 6,DATEADD(yy,YEAR(GETDATE())-1900,0)) and DATEADD(mm,((DATEPART(mm,GETDATE())-1)/6 * 6) + 6,DATEADD(yy,YEAR(GETDATE())-1900,-1)) and academicyear in (select academicyear from academicyear) ) p "
+" left outer join (select staff.staffid,tutor.rollno r2,name n3 from tutor,staff where staff.staffid=tutor.staffid and academicyear in (select academicyear from academicyear)) q1 on p.r1=q1.r2 "
+" left outer join (select discipline.rollno r3,count(*) c from discipline,student where discipline.rollno=student.rollno group by discipline.rollno) q2 on p.r1=q2.r3 "
+" where dept in("+deptin+") and year in("+yearin+") order by maction,cdate desc";


rs = stmt.executeQuery(quer);

count=0;
while(rs.next())
{
	for(int i=1;i<=32;i++)
	{
		report[i-1][count]=rs.getString(i);
	}
	count++;
}
rs.close();

stmt.close();
con.close();
}catch(Exception e){return e.toString()+quer;}
return "suc";
}

public String dAdminStudRep(int ctyp)
{
String quer="";

try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;
quer="select * from (select cid,d.cdate,ctype,d.rollno r1,d.staffid,cdetails,mdate,maction,adetails,mstatus,hdate,hstatus,pdate,pstatus, "
+" CONVERT(varchar, d.cdate, 103) cdt, CONVERT(varchar, mdate, 103) mdt, CONVERT(varchar, hdate, 103) hdt, CONVERT(varchar, pdate, 103) pdt, "
+" s.name n1,s.year,de.sf,st.name n2,dtypename,dtype,s.department,da.atypename,department dept "
+" from discipline d,disciplinetypes dt,disciplineactions da,stud s,staff st,department de where s.department=de.dno and d.staffid=st.staffid and s.rollno=d.rollno and d.ctype=dt.dtypeno and d.maction=da.atypeno "
+"  and d.cdate between DATEADD(mm,(DATEPART(mm,GETDATE())-1)/6 * 6,DATEADD(yy,YEAR(GETDATE())-1900,0)) and DATEADD(mm,((DATEPART(mm,GETDATE())-1)/6 * 6) + 6,DATEADD(yy,YEAR(GETDATE())-1900,-1)) and academicyear in (select academicyear from academicyear) ) p "
+" left outer join (select staff.staffid,tutor.rollno r2,name n3 from tutor,staff where staff.staffid=tutor.staffid and academicyear in (select academicyear from academicyear)) q1 on p.r1=q1.r2 "
+" left outer join (select discipline.rollno r3,count(*) c from discipline,student where discipline.rollno=student.rollno group by discipline.rollno) q2 on p.r1=q2.r3 "
+" where r1 in("+ctyp+") order by maction,cdate desc";


rs = stmt.executeQuery(quer);

count=0;
while(rs.next())
{
	for(int i=1;i<=31;i++)
	{
		report[i-1][count]=rs.getString(i);
	}
	count++;
}
rs.close();

stmt.close();
con.close();
}catch(Exception e){return e.toString()+quer;}
return "suc";
}

public String GetStaff(String ip)
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

sql="select staff.staffid,name,staff.dno,dname,cname,sex,ip,mac,incharge from staff,staffip,staffdesignation,department "
+" where staff.staffid=staffip.staffid and active=1 and designation=slno and staff.dno=department.dno and ip='"+ip+"'";


                    stmt= con.createStatement();
					rs = stmt.executeQuery(sql);
while(rs.next())
{
	for(int i=1;i<=9;i++)
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


public String GetStaffMac(String cstaffid)
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

sql="select staff.staffid,name,staff.dno,dname,cname,sex,ip,mac,incharge from staff,staffip,staffdesignation,department "
+" where staff.staffid=staffip.staffid and active=1 and designation=slno and staff.dno=department.dno and staffip.staffid='"+cstaffid+"'";


                    stmt= con.createStatement();
					rs = stmt.executeQuery(sql);
while(rs.next())
{
	for(int i=1;i<=9;i++)
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


public String DisciplineTypes()
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
sql="select * from disciplinetypes order by dtypeno";
                    stmt= con.createStatement();
					rs = stmt.executeQuery(sql);
while(rs.next())
{
	for(int i=1;i<=3;i++)
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

public String DisciplineStatus()
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
sql="select p.dtypeno,p.dtypename,isnull(c1,0),isnull(c2,0),isnull(c3,0),isnull(c4,0) from "
+" (select dtypeno,dtypename from disciplinetypes)  p "
//+" left outer join (select count(*) c1,ctype d1 from discipline where mstatus=0 group by ctype) as q1 on p.dtypeno=d1 "
//+" left outer join (select count(*) c2,ctype d2 from discipline where hstatus=0 group by ctype) as q2 on p.dtypeno=d2 "
//+" left outer join (select count(*) c3,ctype d3 from discipline where pstatus=0 group by ctype) as q3 on p.dtypeno=d3 "
//+" left outer join (select count(*) c4,ctype d4 from discipline group by ctype) as q4 on p.dtypeno=d4 "
+" left outer join (select count(*) c1,ctype d1 from discipline where mstatus=0 and cdate between DATEADD(mm,(DATEPART(mm,GETDATE())-1)/6 * 6,DATEADD(yy,YEAR(GETDATE())-1900,0)) and DATEADD(mm,((DATEPART(mm,GETDATE())-1)/6 * 6) + 6,DATEADD(yy,YEAR(GETDATE())-1900,-1)) group by ctype) as q1 on p.dtypeno=d1  "
+" left outer join (select count(*) c2,ctype d2 from discipline where hstatus=0 and cdate between DATEADD(mm,(DATEPART(mm,GETDATE())-1)/6 * 6,DATEADD(yy,YEAR(GETDATE())-1900,0)) and DATEADD(mm,((DATEPART(mm,GETDATE())-1)/6 * 6) + 6,DATEADD(yy,YEAR(GETDATE())-1900,-1))  group by ctype) as q2 on p.dtypeno=d2  "
+" left outer join (select count(*) c3,ctype d3 from discipline where pstatus=0 and cdate between DATEADD(mm,(DATEPART(mm,GETDATE())-1)/6 * 6,DATEADD(yy,YEAR(GETDATE())-1900,0)) and DATEADD(mm,((DATEPART(mm,GETDATE())-1)/6 * 6) + 6,DATEADD(yy,YEAR(GETDATE())-1900,-1))  group by ctype) as q3 on p.dtypeno=d3  "
+" left outer join (select count(*) c4,ctype d4 from discipline  where cdate between DATEADD(mm,(DATEPART(mm,GETDATE())-1)/6 * 6,DATEADD(yy,YEAR(GETDATE())-1900,0)) and DATEADD(mm,((DATEPART(mm,GETDATE())-1)/6 * 6) + 6,DATEADD(yy,YEAR(GETDATE())-1900,-1)) group by ctype) as q4 on p.dtypeno=d4 "
+" order by p.dtypeno";
                    stmt= con.createStatement();
					rs = stmt.executeQuery(sql);
while(rs.next())
{
	for(int i=1;i<=6;i++)
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

public String DisciplineDeptStatus()
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
                    stmt= con.createStatement();
/*
sql="select p.dno,p.sf,isnull(c1,0),isnull(c2,0),isnull(c3,0),isnull(c4,0) from "
+" (select * from department where dty>0) p "
+" left outer join (select count(*) c1,departmentno d1 from discipline,student where discipline.rollno=student.rollno and mstatus=0 group by departmentno) as q1 on p.dno=d1 "
+" left outer join (select count(*) c2,departmentno d2 from discipline,student where discipline.rollno=student.rollno and hstatus=0 group by departmentno) as q2 on p.dno=d2 "
+" left outer join (select count(*) c3,departmentno d3 from discipline,student where discipline.rollno=student.rollno and hstatus=0 group by departmentno) as q3 on p.dno=d3 "
+" left outer join (select count(*) c4,departmentno d4 from discipline,student where discipline.rollno=student.rollno group by departmentno) as q4 on p.dno=d4 "
+" order by dty,p.dno";
*/                    
                    
sql="select '7','FY',sum(isnull(c1,0)),sum(isnull(c2,0)),sum(isnull(c3,0)),sum(isnull(c4,0)) from "
+" (select * from department where dty=1) p "
//+" left outer join (select count(*) c1,department d1 from discipline,stud where discipline.rollno=stud.rollno and mstatus=0 and academicyear in(select academicyear from academicyear) and year in(1) group by department ) as q1 on p.dno=d1 "
//+" left outer join (select count(*) c2,department d2 from discipline,stud where discipline.rollno=stud.rollno and hstatus=0 and academicyear in(select academicyear from academicyear) and year in(1) group by department ) as q2 on p.dno=d2 "
//+" left outer join (select count(*) c3,department d3 from discipline,stud where discipline.rollno=stud.rollno and pstatus=0 and academicyear in(select academicyear from academicyear) and year in(1) group by department ) as q3 on p.dno=d3 "
//+" left outer join (select count(*) c4,department d4 from discipline,stud where discipline.rollno=stud.rollno and academicyear in(select academicyear from academicyear) and year in(1) group by department ) as q4 on p.dno=d4 ";
+" left outer join (select count(*) c1,department d1 from discipline,stud where discipline.rollno=stud.rollno and cdate between DATEADD(mm,(DATEPART(mm,GETDATE())-1)/6 * 6,DATEADD(yy,YEAR(GETDATE())-1900,0)) and DATEADD(mm,((DATEPART(mm,GETDATE())-1)/6 * 6) + 6,DATEADD(yy,YEAR(GETDATE())-1900,-1))  and mstatus=0 and academicyear in(select academicyear from academicyear) and year in(1) group by department ) as q1 on p.dno=d1 "
+" left outer join (select count(*) c2,department d2 from discipline,stud where discipline.rollno=stud.rollno and cdate between DATEADD(mm,(DATEPART(mm,GETDATE())-1)/6 * 6,DATEADD(yy,YEAR(GETDATE())-1900,0)) and DATEADD(mm,((DATEPART(mm,GETDATE())-1)/6 * 6) + 6,DATEADD(yy,YEAR(GETDATE())-1900,-1))  and hstatus=0 and academicyear in(select academicyear from academicyear) and year in(1) group by department ) as q2 on p.dno=d2 "
+" left outer join (select count(*) c3,department d3 from discipline,stud where discipline.rollno=stud.rollno and cdate between DATEADD(mm,(DATEPART(mm,GETDATE())-1)/6 * 6,DATEADD(yy,YEAR(GETDATE())-1900,0)) and DATEADD(mm,((DATEPART(mm,GETDATE())-1)/6 * 6) + 6,DATEADD(yy,YEAR(GETDATE())-1900,-1))  and pstatus=0 and academicyear in(select academicyear from academicyear) and year in(1) group by department ) as q3 on p.dno=d3 "
+" left outer join (select count(*) c4,department d4 from discipline,stud where discipline.rollno=stud.rollno and cdate between DATEADD(mm,(DATEPART(mm,GETDATE())-1)/6 * 6,DATEADD(yy,YEAR(GETDATE())-1900,0)) and DATEADD(mm,((DATEPART(mm,GETDATE())-1)/6 * 6) + 6,DATEADD(yy,YEAR(GETDATE())-1900,-1))  and academicyear in(select academicyear from academicyear) and year in(1) group by department ) as q4 on p.dno=d4 ";

					rs = stmt.executeQuery(sql);
while(rs.next())
{
	for(int i=1;i<=6;i++)
	{
		report[i-1][count]=rs.getString(i);
	}
	count++;
}
rs.close();

sql="select p.dno,p.sf,isnull(c1,0),isnull(c2,0),isnull(c3,0),isnull(c4,0) from "
//+" (select * from department where dty=1) p "
//+" left outer join (select count(*) c1,department d1 from discipline,stud where discipline.rollno=stud.rollno and mstatus=0 and academicyear in(select academicyear from academicyear) and year in(2,3,4) group by department ) as q1 on p.dno=d1 "
//+" left outer join (select count(*) c2,department d2 from discipline,stud where discipline.rollno=stud.rollno and hstatus=0 and academicyear in(select academicyear from academicyear) and year in(2,3,4) group by department ) as q2 on p.dno=d2 "
//+" left outer join (select count(*) c3,department d3 from discipline,stud where discipline.rollno=stud.rollno and pstatus=0 and academicyear in(select academicyear from academicyear) and year in(2,3,4) group by department ) as q3 on p.dno=d3 "
//+" left outer join (select count(*) c4,department d4 from discipline,stud where discipline.rollno=stud.rollno and academicyear in(select academicyear from academicyear) and year in(2,3,4) group by department ) as q4 on p.dno=d4 "
+" (select * from department where dty=1) p "
+" left outer join (select count(*) c1,department d1 from discipline,stud where discipline.rollno=stud.rollno and cdate between DATEADD(mm,(DATEPART(mm,GETDATE())-1)/6 * 6,DATEADD(yy,YEAR(GETDATE())-1900,0)) and DATEADD(mm,((DATEPART(mm,GETDATE())-1)/6 * 6) + 6,DATEADD(yy,YEAR(GETDATE())-1900,-1)) and mstatus=0 and academicyear in(select academicyear from academicyear) and year in(2,3,4) group by department ) as q1 on p.dno=d1 "
+" left outer join (select count(*) c2,department d2 from discipline,stud where discipline.rollno=stud.rollno and cdate between DATEADD(mm,(DATEPART(mm,GETDATE())-1)/6 * 6,DATEADD(yy,YEAR(GETDATE())-1900,0)) and DATEADD(mm,((DATEPART(mm,GETDATE())-1)/6 * 6) + 6,DATEADD(yy,YEAR(GETDATE())-1900,-1)) and hstatus=0 and academicyear in(select academicyear from academicyear) and year in(2,3,4) group by department ) as q2 on p.dno=d2 "
+" left outer join (select count(*) c3,department d3 from discipline,stud where discipline.rollno=stud.rollno and cdate between DATEADD(mm,(DATEPART(mm,GETDATE())-1)/6 * 6,DATEADD(yy,YEAR(GETDATE())-1900,0)) and DATEADD(mm,((DATEPART(mm,GETDATE())-1)/6 * 6) + 6,DATEADD(yy,YEAR(GETDATE())-1900,-1)) and pstatus=0 and academicyear in(select academicyear from academicyear) and year in(2,3,4) group by department ) as q3 on p.dno=d3 "
+" left outer join (select count(*) c4,department d4 from discipline,stud where discipline.rollno=stud.rollno and cdate between DATEADD(mm,(DATEPART(mm,GETDATE())-1)/6 * 6,DATEADD(yy,YEAR(GETDATE())-1900,0)) and DATEADD(mm,((DATEPART(mm,GETDATE())-1)/6 * 6) + 6,DATEADD(yy,YEAR(GETDATE())-1900,-1)) and academicyear in(select academicyear from academicyear) and year in(2,3,4) group by department ) as q4 on p.dno=d4 "
+" order by dty,p.dno";
					rs = stmt.executeQuery(sql);
while(rs.next())
{
	for(int i=1;i<=6;i++)
	{
		report[i-1][count]=rs.getString(i);
	}
	count++;
}
rs.close();
sql="select p.dno,p.sf,isnull(c1,0),isnull(c2,0),isnull(c3,0),isnull(c4,0) from "
+" (select * from department where dty>1) p "
+" left outer join (select count(*) c1,department d1 from discipline,stud where discipline.rollno=stud.rollno and cdate between DATEADD(mm,(DATEPART(mm,GETDATE())-1)/6 * 6,DATEADD(yy,YEAR(GETDATE())-1900,0)) and DATEADD(mm,((DATEPART(mm,GETDATE())-1)/6 * 6) + 6,DATEADD(yy,YEAR(GETDATE())-1900,-1)) and mstatus=0 and academicyear in(select academicyear from academicyear)  group by department ) as q1 on p.dno=d1 "
+" left outer join (select count(*) c2,department d2 from discipline,stud where discipline.rollno=stud.rollno and cdate between DATEADD(mm,(DATEPART(mm,GETDATE())-1)/6 * 6,DATEADD(yy,YEAR(GETDATE())-1900,0)) and DATEADD(mm,((DATEPART(mm,GETDATE())-1)/6 * 6) + 6,DATEADD(yy,YEAR(GETDATE())-1900,-1)) and hstatus=0 and academicyear in(select academicyear from academicyear)  group by department ) as q2 on p.dno=d2 "
+" left outer join (select count(*) c3,department d3 from discipline,stud where discipline.rollno=stud.rollno and cdate between DATEADD(mm,(DATEPART(mm,GETDATE())-1)/6 * 6,DATEADD(yy,YEAR(GETDATE())-1900,0)) and DATEADD(mm,((DATEPART(mm,GETDATE())-1)/6 * 6) + 6,DATEADD(yy,YEAR(GETDATE())-1900,-1)) and pstatus=0 and academicyear in(select academicyear from academicyear)  group by department ) as q3 on p.dno=d3 "
+" left outer join (select count(*) c4,department d4 from discipline,stud where discipline.rollno=stud.rollno and cdate between DATEADD(mm,(DATEPART(mm,GETDATE())-1)/6 * 6,DATEADD(yy,YEAR(GETDATE())-1900,0)) and DATEADD(mm,((DATEPART(mm,GETDATE())-1)/6 * 6) + 6,DATEADD(yy,YEAR(GETDATE())-1900,-1)) and academicyear in(select academicyear from academicyear)  group by department ) as q4 on p.dno=d4 "
+" order by dty,p.dno";
					rs = stmt.executeQuery(sql);
while(rs.next())
{
	for(int i=1;i<=6;i++)
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

public String DisciplineActions()
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
sql="select * from disciplineactions order by atypeno";
                    stmt= con.createStatement();
					rs = stmt.executeQuery(sql);
while(rs.next())
{
	for(int i=1;i<=3;i++)
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

public String StudStatus()
{
String quer="";

try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;
quer="select dty,department,dn,a1,b1,c1,isnull(d2,0),c1,isnull(d3,0),isnull(d1,0) from "
+" (select dty,department,max(dname) dn,max(sf) a1,max(year) b1,max(sex) c1,count(*) d1 from stud,student,department where student.departmentno=department.dno and stud.rollno=student.rollno and active=1  "
+" and dno not in(7,30) and dty in(1,2,3,4)  and academicyear in (select academicyear from academicyear) group by dty,department,year) p "
+" left outer join (select max(sf) a2,max(year) b2,max(sex) c2,count(*) d2 from stud,student,department where student.departmentno=department.dno and stud.rollno=student.rollno and active=1 "
+" and dno not in(7,30) and dty in(1,2,3,4) and sex='Male' and academicyear in (select academicyear from academicyear) group by dty,department,year,sex)  as q on p.a1=a2 and p.b1=b2 "
+" left outer join (select max(sf) a3,max(year) b3,max(sex) c3,count(*) d3 from stud,student,department where student.departmentno=department.dno and stud.rollno=student.rollno and active=1 "
+" and dno not in(7,30) and dty in(1,2,3,4) and sex='Female'  and academicyear in (select academicyear from academicyear) group by dty,department,year,sex) as r on p.a1=a3 and p.b1=b3 "
+" order by dty,department";


rs = stmt.executeQuery(quer);

count=0;
while(rs.next())
{
	for(int i=1;i<=10;i++)
	{
		report[i-1][count]=rs.getString(i);
	}
	count++;
}
rs.close();

stmt.close();
con.close();
}catch(Exception e){return e.toString()+quer;}
return "suc";
}

public String StaffEntryStatus()
{
sql="";

try
{
			StringTokenizer st1 = new StringTokenizer(repin[2],"/");
		    d1=st1.nextToken();
		    m1=st1.nextToken();
		    y1=st1.nextToken();
			date1=m1+"/"+d1+"/"+y1;

			StringTokenizer st2 = new StringTokenizer(repin[3],"/");
		    d1=st2.nextToken();
		    m1=st2.nextToken();
		    y1=st2.nextToken();
			date2=m1+"/"+d1+"/"+y1;
i1=Integer.parseInt(repin[9]);			
}
catch(Exception e){}			

try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;
sql="select p.staffid,p.name,p.cname,p.sf,isnull(c,0) from "
+" (select staffid,name,sf,cname from staff,department,staffdesignation where staff.dno in ("+repin[0]+") and staff.category in ("+repin[1]+") and staff.dno=department.dno and staff.designation=staffdesignation.slno and active=1) p "
+" left outer join (select staffid,count(*) c from discipline d where d.cdate between '"+date1+"' and '"+date2+"' group by d.staffid) q on p.staffid=q.staffid "
+" order by "+repin[4];

rs = stmt.executeQuery(sql);

count=0;
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
con.close();
}catch(Exception e){return e.toString()+quer;}
return "suc";
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




	
	