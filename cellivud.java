package sxcce;
import java.sql.*;
import java.util.StringTokenizer;
import com.jspsmart.upload.*;
import java.util.Date;
import java.io.*;
import dd.DBConnectionManager;

public class cellivud
	{
				String rep[][] = new String[20][1000];
				String report[][] = new String[20][10000];
				String report1[] = new String[1000];
				String report2[] = new String[1000];
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
int ecount;
int ypid;
int pid;
int uid;
int aid;
int mid;
int meid;
int pubid;
int cellid;
String sql="";	
String error="";			
String error1="";	
String academicyear="";		

String cellidin="0";
String dnoin="0";
String celltypein="'0'";
String ptypein="'0'";
String resourcepersonfromin="'0'";
String programin="'0'";
String atypein="'0'";
String alevelin="'0'";
String pubtypein="'0'";
String plcampusin="'0'";


public String MaxCellNo()
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
					    sql="select max(cellid)+1 from cells";
					    rs = stmt.executeQuery(sql);
					    if(rs.next())
					    {
						cellid=rs.getInt(1);
					    }
					    rs.close();
                    stmt.close();
					connMgr.freeConnection("xavier",con);
					error1="...";
					}catch(Exception e){error1=(e.toString());}
		return cellid+"";	
}

public String CellResType()
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
sql="select responsibility from cellrestype order by slno";

rs = stmt.executeQuery(sql);

while(rs.next())
{
	report2[count]=rs.getString(1);
	count++;
}
rs.close();

stmt.close();
connMgr.freeConnection("xavier",con);
error= "success"; 
}catch(Exception e){error=(e.toString()+count+i+sql);}
return error; 
}

public String CellInsert()
{
int found=0;
String sql="";
String date2="";
int j=0;

cellid=Integer.parseInt(report1[0].toString());
try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  			Statement stmt = con.createStatement();
			ResultSet rs;
			PreparedStatement ps;
			StringTokenizer st1 = new StringTokenizer(report1[5],"/");
		    d1=st1.nextToken();
		    m1=st1.nextToken();
		    y1=st1.nextToken();
			date2=m1+"/"+d1+"/"+y1;

					    sql="select academicyear from academicyear";
					    rs = stmt.executeQuery(sql);
					    if(rs.next())
					    {
						academicyear=rs.getString(1);
					    }
					    rs.close();

/*					
					    sql="select max(cellid)+1 from cells";
					    rs = stmt.executeQuery(sql);
					    if(rs.next())
					    {
						cellid=rs.getInt(1);
					    }
					    rs.close();

                    sql="delete from cells where cellid="+cellid;
					ps=con.prepareStatement(sql);
					int k = ps.executeUpdate();
					ps.close();

                    sql="delete from cellmoto where cellid="+cellid+" and academicyear='"+academicyear+"'";
					ps=con.prepareStatement(sql);
					k = ps.executeUpdate();
					ps.close();
*/

                    sql="select * from cells where cellid="+cellid;
					rs = stmt.executeQuery(sql);
					if(rs.next()) found=1;
					rs.close();
					stmt.close();
if(found==0)
{
					
                    sql="insert into cells values(?,?,?,?,?)";

					ps=con.prepareStatement(sql);
					ps.setInt(1,cellid);
					ps.setString(2,report1[1]);
					ps.setString(3,report1[2]);
					ps.setString(4,report1[3]);
					ps.setString(5,date2);
					j = ps.executeUpdate();
					ps.close();

                    sql="insert into cellmoto values(?,?,?,?,?,?,?,?)";

					ps=con.prepareStatement(sql);
					ps.setInt(1,cellid);
					ps.setString(2,academicyear);
					ps.setString(3,report1[12]);
					ps.setString(4,report1[13]);
					ps.setString(5,report1[14]);
					ps.setString(6,report1[15]);
					ps.setString(7,report1[16]);
					ps.setString(8,report1[17]);
					
					j = ps.executeUpdate();
					ps.close();
}
else
{
                    sql="update cells set cellname=?, celltype=?, convener=?,startdate=? where cellid="+cellid;
					ps=con.prepareStatement(sql);
					ps.setString(1,report1[1]);
					ps.setString(2,report1[2]);
					ps.setString(3,report1[3]);
					ps.setString(4,date2);
					j = ps.executeUpdate();
					ps.close();
}


     
                    stmt.close();
					connMgr.freeConnection("xavier",con);
					error1="...";
					}catch(Exception e){error1=(e.toString());}

		return error1;	

}


public String MotoInsert()
{

String sql="";
String date2="";
int k=0;
int j=0;
int found=0;
cellid=Integer.parseInt(report1[0].toString());
try
{
DBConnectionManager connMgr;
connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  			Statement stmt = con.createStatement();
			ResultSet rs;
			PreparedStatement ps;


					    sql="select academicyear from academicyear";
					    rs = stmt.executeQuery(sql);
					    if(rs.next())
					    {
						academicyear=rs.getString(1);
					    }
					    rs.close();
/*
                    sql="delete from cellmoto where cellid="+cellid+" and academicyear='"+academicyear+"'";
					ps=con.prepareStatement(sql);
					k = ps.executeUpdate();
					ps.close();
*/					
                    sql="select * from cellmoto where cellid="+cellid+" and academicyear='"+academicyear+"'";
					rs = stmt.executeQuery(sql);
					if(rs.next()) found=1;
					rs.close();
					stmt.close();
if(found==0)
{
                    sql="insert into cellmoto values(?,?,?,?,?,?,?,?)";
					ps=con.prepareStatement(sql);
					ps.setInt(1,cellid);
					ps.setString(2,academicyear);
					ps.setString(3,report1[12]);
					ps.setString(4,report1[13]);
					ps.setString(5,report1[14]);
					ps.setString(6,report1[15]);
					ps.setString(7,report1[16]);
					ps.setString(8,report1[17]);
					j = ps.executeUpdate();
					ps.close();
}
else
{
                    sql="update cellmoto set vision=?, mission=?, objective=?,outcome=?, moto=?, compositionrule=? where cellid="+cellid+" and academicyear='"+academicyear+"'";
					ps=con.prepareStatement(sql);
					ps.setString(1,report1[12]);
					ps.setString(2,report1[13]);
					ps.setString(3,report1[14]);
					ps.setString(4,report1[15]);
					ps.setString(5,report1[16]);
					ps.setString(6,report1[17]);
					j = ps.executeUpdate();
					ps.close();
}

     
                    stmt.close();
					connMgr.freeConnection("xavier",con);
					error1="...";
					}catch(Exception e){error1=(e.toString()+sql);}

		return error1;	

}

public String ListCells()
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
sql="select cellid,cellname,celltype,convener,startdate,substring(('000'+ltrim(rtrim(str(day(startdate))))), len('000'+ltrim(rtrim(str(day(startdate)))))-1,2)+'/'+  substring(('000'+ltrim(rtrim(str(month(startdate))))), "
+"len('000'+ltrim(rtrim(str(month(startdate)))))-1,2)+'/'+ ltrim(rtrim(str(year(startdate)))),name from cells,staff where staffid=convener order by cellid";

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


public String CellsStatus()
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
sql="select cellid,cellname,celltype,convener,name,stdate,isnull(moto, 0), isnull(members, 0), isnull(yearplan, 0), isnull(program, 0), isnull(pgmatt, 0), isnull(awards, 0), isnull(mou, 0), isnull(pub, 0), isnull(meeting, 0) "
+"from (select * from(select * from (select * from (select cellid,cellname,celltype,convener,name, "
+"substring(('000'+ltrim(rtrim(str(day(startdate))))), len('000'+ltrim(rtrim(str(day(startdate)))))-1,2)+'/'+  substring(('000'+ltrim(rtrim(str(month(startdate))))), "
+"len('000'+ltrim(rtrim(str(month(startdate)))))-1,2)+'/'+ ltrim(rtrim(str(year(startdate)))) stdate from cells,staff where staffid=convener) a "
+"Left Outer join (select cellid c1,count(*) moto from cellmoto group by cellid) b on a.cellid=b.c1) c "
+"Left Outer join (select cellid c2,count(*) members from celloffbearers group by cellid) d on c.cellid=d.c2) e "
+"Left Outer join (select cellid c3,count(*) yearplan from cellyearplan group by cellid) f on e.cellid=f.c3) g "
+"Left Outer join (select cellid c4,count(*) program from cellprogram group by cellid) h on g.cellid=h.c4 "
+"Left Outer join (select cellid c5,count(*) pgmatt from cellpgmatt group by cellid) i on g.cellid=i.c5 "
+"Left Outer join (select cellid c6,count(*) awards from cellawards group by cellid) j on g.cellid=j.c6 "
+"Left Outer join (select cellid c7,count(*) mou from cellmoudetails group by cellid) k on g.cellid=k.c7 "
+"Left Outer join (select cellid c8,count(*) pub from cellpublications group by cellid) l on g.cellid=l.c8 " 
+"Left Outer join (select cellid c9,count(*) meeting from cellmeetings group by cellid) m on g.cellid=m.c9 order by cellid";

rs = stmt.executeQuery(sql);

while(rs.next())
{
	for(i=1;i<=15;i++)
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


public String CellView(int cid)
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
sql="select cellid,cellname,celltype,convener,startdate,substring(('000'+ltrim(rtrim(str(day(startdate))))), len('000'+ltrim(rtrim(str(day(startdate)))))-1,2)+'/'+  substring(('000'+ltrim(rtrim(str(month(startdate))))), "
+"len('000'+ltrim(rtrim(str(month(startdate)))))-1,2)+'/'+ ltrim(rtrim(str(year(startdate)))),name from cells,staff where staffid=convener and cellid="+cid;

rs = stmt.executeQuery(sql);

if(rs.next())
{
	for(i=1;i<=7;i++)
	{
		report1[i-1]=rs.getString(i);
	}
}
rs.close();

sql="select * from cellmoto where academicyear in (select academicyear from academicyear) and cellid="+cid;

rs = stmt.executeQuery(sql);

if(rs.next())
{
	for(i=1;i<=8;i++)
	{
		report1[i+10]=rs.getString(i);
	}
}
rs.close();

stmt.close();
connMgr.freeConnection("xavier",con);
error= "success"; 
}catch(Exception e){error=(e.toString()+count+i+sql);}
return error; 
}

public String staffcells(String sid)
{
int i=0;
	count1=0;
try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;
sql="select cellid,cellname,celltype,convener,startdate,substring(('000'+ltrim(rtrim(str(day(startdate))))), len('000'+ltrim(rtrim(str(day(startdate)))))-1,2)+'/'+  substring(('000'+ltrim(rtrim(str(month(startdate))))), "
+"len('000'+ltrim(rtrim(str(month(startdate)))))-1,2)+'/'+ ltrim(rtrim(str(year(startdate)))),name from cells,staff where staffid=convener and convener='"+sid+"'";

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

sql="select count(*) from cells,staff where convener=staff.staffid and dno in (select dno from celloffbearers,staff "
//+" where userid=staffid and cellid=1051 and academicyear in(select academicyear from academicyear) "
+" where userid=staffid and cellid=1066 and academicyear in(select academicyear from academicyear) "
+" and responsibility='Staff Member' and userid='"+sid+"')";

rs = stmt.executeQuery(sql);

if(rs.next())
{
	count1=rs.getInt(1);
}
rs.close();


stmt.close();
connMgr.freeConnection("xavier",con);
error= "success"; 
}catch(Exception e){error=(e.toString()+count+i+sql);}
return error; 
}





public String CellDel(int cid)
{

try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;
					    PreparedStatement ps;
					    
					    sql="select academicyear from academicyear";
					    rs = stmt.executeQuery(sql);
					    if(rs.next())
					    {
						academicyear=rs.getString(1);
					    }
					    rs.close();
					    
					    
                  sql="delete from cells where cellid=?";

					ps=con.prepareStatement(sql);
					ps.setInt(1,cid);
					int i = ps.executeUpdate();
					ps.close();

                    sql="delete from cellmoto where cellid="+cid+" and academicyear='"+academicyear+"'";
					ps=con.prepareStatement(sql);
					i = ps.executeUpdate();
					ps.close();

                    stmt.close();
					connMgr.freeConnection("xavier",con);
					error="success";
					}catch(Exception e){error=(e.toString());
					}
					
		return error;
}


public String YearplanView(int cid)
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
sql="select cellid,ypid,ypdate,substring(('000'+ltrim(rtrim(str(day(ypdate))))), len('000'+ltrim(rtrim(str(day(ypdate)))))-1,2)+'/'+  substring(('000'+ltrim(rtrim(str(month(ypdate))))), "
+"len('000'+ltrim(rtrim(str(month(ypdate)))))-1,2)+'/'+ ltrim(rtrim(str(year(ypdate)))),program,ptype,objective,outcome,beneficiaries from cellyearplan where cellid="+cid+" order by ypdate desc";

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


public String YearplanInsert()
{
String sql="";
String date2="";
cellid=Integer.parseInt(report1[0].toString());
try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  			Statement stmt = con.createStatement();
			ResultSet rs;
			PreparedStatement ps;
			StringTokenizer st1 = new StringTokenizer(report1[1],"/");
		    d1=st1.nextToken();
		    m1=st1.nextToken();
		    y1=st1.nextToken();
			date2=m1+"/"+d1+"/"+y1;
					sql="select max(ypid)+1 from cellyearplan where cellid="+cellid;
					rs = stmt.executeQuery(sql);
					if(rs.next())     { 	ypid=rs.getInt(1); 	    }
					rs.close();
					
                    sql="delete from cellyearplan where cellid="+cellid+" and ypid="+ypid;
					ps=con.prepareStatement(sql);
					int k = ps.executeUpdate();
					ps.close();
					
                    sql="insert into cellyearplan values(?,?,?,?,?,?,?,?)";
					ps=con.prepareStatement(sql);
					ps.setInt(1,cellid);
					ps.setInt(2,ypid);
					ps.setString(3,date2);
					ps.setString(4,report1[2]);
					ps.setString(5,report1[3]);
					ps.setString(6,report1[4]);
					ps.setString(7,report1[5]);
					ps.setString(8,report1[6]);
					int j = ps.executeUpdate();
					ps.close();
     
                    stmt.close();
					connMgr.freeConnection("xavier",con);
					error1="...";
					}catch(Exception e){error1=(e.toString());}

return error1;	

}



public String YearplanDel(int cid,int yid)
{

try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;
					    PreparedStatement ps;
                  sql="delete from cellyearplan where cellid=? and ypid=?";
					ps=con.prepareStatement(sql);
					ps.setInt(1,cid);
					ps.setInt(2,yid);
					int i = ps.executeUpdate();
					ps.close();
                    stmt.close();
					connMgr.freeConnection("xavier",con);
					error="success";
					}catch(Exception e){error=(e.toString());
					}
					
		return error;
}


public String PlacementView(int cid)
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
sql="select cellid,plid,pldate,substring(('000'+ltrim(rtrim(str(day(pldate))))), len('000'+ltrim(rtrim(str(day(pldate)))))-1,2)+'/'+  substring(('000'+ltrim(rtrim(str(month(pldate))))), "
+"len('000'+ltrim(rtrim(str(month(pldate)))))-1,2)+'/'+ ltrim(rtrim(str(year(pldate)))),plcampus,plvenue,plorg,sf,pnos,snos from cellplacement,department where cellid="+cid+" and pdno=dno order by pldate desc";

rs = stmt.executeQuery(sql);

while(rs.next())
{
	for(i=1;i<=10;i++)
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


public String PlacementInsert()
{
String sql="";
String date2="";
cellid=Integer.parseInt(report1[0].toString());
try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  			Statement stmt = con.createStatement();
			ResultSet rs;
			PreparedStatement ps;
			StringTokenizer st1 = new StringTokenizer(report1[1],"/");
		    d1=st1.nextToken();
		    m1=st1.nextToken();
		    y1=st1.nextToken();
			date2=m1+"/"+d1+"/"+y1;
					sql="select max(plid)+1 from cellplacement where cellid="+cellid;
					rs = stmt.executeQuery(sql);
					if(rs.next())     { 	ypid=rs.getInt(1); 	    }
					rs.close();
					
                    sql="delete from cellplacement where cellid="+cellid+" and plid="+ypid;
					ps=con.prepareStatement(sql);
					int k = ps.executeUpdate();
					ps.close();
					
                    sql="insert into cellplacement values(?,?,?,?,?,?,?,?,?)";
					ps=con.prepareStatement(sql);
					ps.setInt(1,cellid);
					ps.setInt(2,ypid);
					ps.setString(3,date2);
					ps.setString(4,report1[2]);
					ps.setString(5,report1[3]);
					ps.setString(6,report1[4]);
					ps.setString(7,report1[5]);
					ps.setString(8,report1[6]);
					ps.setString(9,report1[7]);
					int j = ps.executeUpdate();
					ps.close();
     
                    stmt.close();
					connMgr.freeConnection("xavier",con);
					error1="...";
					}catch(Exception e){error1=(e.toString());}

return error1;	

}



public String PlacementDel(int cid,int yid)
{

try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;
					    PreparedStatement ps;
                  sql="delete from cellplacement where cellid=? and plid=?";
					ps=con.prepareStatement(sql);
					ps.setInt(1,cid);
					ps.setInt(2,yid);
					int i = ps.executeUpdate();
					ps.close();
                    stmt.close();
					connMgr.freeConnection("xavier",con);
					error="success";
					}catch(Exception e){error=(e.toString());
					}
					
		return error;
}


public String PtiView(int cid)
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
sql="select cellid,ptid,ptdate,substring(('000'+ltrim(rtrim(str(day(ptdate))))), len('000'+ltrim(rtrim(str(day(ptdate)))))-1,2)+'/'+  substring(('000'+ltrim(rtrim(str(month(ptdate))))), "
+"len('000'+ltrim(rtrim(str(month(ptdate)))))-1,2)+'/'+ ltrim(rtrim(str(year(ptdate)))),sf,parentsatt,pname,fname,mname from cellpti,department where cellid="+cid+" and ptdno=dno order by ptdate desc";


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

public String PtiView1(int cid, int yid)
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
sql="select cellid,ptid,ptdate,substring(('000'+ltrim(rtrim(str(day(ptdate))))), len('000'+ltrim(rtrim(str(day(ptdate)))))-1,2)+'/'+  substring(('000'+ltrim(rtrim(str(month(ptdate))))), "
+"len('000'+ltrim(rtrim(str(month(ptdate)))))-1,2)+'/'+ ltrim(rtrim(str(year(ptdate)))),sf,parentsatt,pname,fname,mname from cellpti,department where cellid="+cid+" and ptid="+yid+" and ptdno=dno order by ptdate desc";


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


public String PtiInsert()
{
String sql="";
String date2="";
cellid=Integer.parseInt(report1[0].toString());
try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  			Statement stmt = con.createStatement();
			ResultSet rs;
			PreparedStatement ps;
			StringTokenizer st1 = new StringTokenizer(report1[1],"/");
		    d1=st1.nextToken();
		    m1=st1.nextToken();
		    y1=st1.nextToken();
			date2=m1+"/"+d1+"/"+y1;
					sql="select max(ptid)+1 from cellpti where cellid="+cellid;
					rs = stmt.executeQuery(sql);
					if(rs.next())     { 	ypid=rs.getInt(1); 	    }
					rs.close();
					
                    sql="delete from cellpti where cellid="+cellid+" and ptid="+ypid;
					ps=con.prepareStatement(sql);
					int k = ps.executeUpdate();
					ps.close();
					
                    sql="insert into cellpti values(?,?,?,?,?,?,?,?)";
					ps=con.prepareStatement(sql);
					ps.setInt(1,cellid);
					ps.setInt(2,ypid);
					ps.setString(3,date2);
					ps.setString(4,report1[2]);
					ps.setString(5,report1[3]);
					ps.setString(6,report1[4]);
					ps.setString(7,report1[5]);
					ps.setString(8,report1[6]);
					int j = ps.executeUpdate();
					ps.close();
     
                    stmt.close();
					connMgr.freeConnection("xavier",con);
					error1="...";
					}catch(Exception e){error1=(e.toString());}

return error1;	

}

public String PtiUpdate(int cid,int yid)
{

try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;
					    PreparedStatement ps;
                  sql="update cellpti set pname=?,fname=?,mname=? where cellid=? and ptid=?";
					ps=con.prepareStatement(sql);
					ps.setString(1,report1[10]);
					ps.setString(2,report1[11]);
					ps.setString(3,report1[12]);
					ps.setInt(4,cid);
					ps.setInt(5,yid);
					int i = ps.executeUpdate();
					ps.close();
                    stmt.close();
					connMgr.freeConnection("xavier",con);
					error="success";
					}catch(Exception e){error=(e.toString());
					}
					
		return error;
}


public String PtiDel(int cid,int yid)
{

try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;
					    PreparedStatement ps;
                  sql="delete from cellpti where cellid=? and ptid=?";
					ps=con.prepareStatement(sql);
					ps.setInt(1,cid);
					ps.setInt(2,yid);
					int i = ps.executeUpdate();
					ps.close();
                    stmt.close();
					connMgr.freeConnection("xavier",con);
					error="success";
					}catch(Exception e){error=(e.toString());
					}
					
		return error;
}



public String PgmAttView(int cid)
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
sql="select cellid,paid,padate,padate1,userid, isnull(d.name, n1),program,ptype,venue,impact,fname from (select cellid,paid,userid,program,ptype,padate,padate1,venue,impact,b.name n1,fname from "
+"(select cellid,paid,padate,substring(('000'+ltrim(rtrim(str(day(padate))))), len('000'+ltrim(rtrim(str(day(padate)))))-1,2)+'/'+  substring(('000'+ltrim(rtrim(str(month(padate))))), "
+"len('000'+ltrim(rtrim(str(month(padate)))))-1,2)+'/'+ ltrim(rtrim(str(year(padate)))) padate1,userid,program,ptype,venue,impact,fname from cellpgmatt where cellid="+cid+") a "
+"Left Outer join (select str(rollno) rollno,name from student) b on ltrim(rtrim(a.userid))=ltrim(rtrim(str(b.rollno)))) c "
+"Left Outer join (select staffid,name from staff) d on c.userid=d.staffid order by padate desc";

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

public String PgmAttView1(int cid, int cpaid)
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
sql="select cellid,paid,padate,padate1,userid, isnull(d.name, n1),program,ptype,venue,impact,fname from (select cellid,paid,userid,program,ptype,padate,padate1,venue,impact,b.name n1,fname from "
+"(select cellid,paid,padate,substring(('000'+ltrim(rtrim(str(day(padate))))), len('000'+ltrim(rtrim(str(day(padate)))))-1,2)+'/'+  substring(('000'+ltrim(rtrim(str(month(padate))))), "
+"len('000'+ltrim(rtrim(str(month(padate)))))-1,2)+'/'+ ltrim(rtrim(str(year(padate)))) padate1,userid,program,ptype,venue,impact,fname from cellpgmatt where cellid="+cid+" and paid="+cpaid+") a "
+"Left Outer join (select str(rollno) rollno,name from student) b on ltrim(rtrim(a.userid))=ltrim(rtrim(str(b.rollno)))) c "
+"Left Outer join (select staffid,name from staff) d on c.userid=d.staffid order by padate desc";

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

public String PgmAttInsert()
{
String sql="";
String date2="";
cellid=Integer.parseInt(report1[0].toString());
try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  			Statement stmt = con.createStatement();
			ResultSet rs;
			PreparedStatement ps;
			StringTokenizer st1 = new StringTokenizer(report1[1],"/");
		    d1=st1.nextToken();
		    m1=st1.nextToken();
		    y1=st1.nextToken();
			date2=m1+"/"+d1+"/"+y1;
					sql="select max(paid)+1 from cellpgmatt where cellid="+cellid;
					rs = stmt.executeQuery(sql);
					if(rs.next())     { 	ypid=rs.getInt(1); 	    }
					rs.close();
					
                    sql="delete from cellpgmatt where cellid="+cellid+" and paid="+ypid;
					ps=con.prepareStatement(sql);
					int k = ps.executeUpdate();
					ps.close();
					
                    sql="insert into cellpgmatt values(?,?,?,?,?,?,?,?,?)";
					ps=con.prepareStatement(sql);
					ps.setInt(1,cellid);
					ps.setInt(2,ypid);
					ps.setString(3,date2);
					ps.setString(4,report1[2]);
					ps.setString(5,report1[3]);
					ps.setString(6,report1[4]);
					ps.setString(7,report1[5]);
					ps.setString(8,report1[6]);
					ps.setString(9,report1[7]);
					int j = ps.executeUpdate();
					ps.close();
     
                    stmt.close();
					connMgr.freeConnection("xavier",con);
					error1="...";
					}catch(Exception e){error1=(e.toString());}

return error1;	

}

public String PgmAttUpdate(int cid,int yid)
{

try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;
					    PreparedStatement ps;
                  sql="update cellpgmatt set fname=? where cellid=? and paid=?";
					ps=con.prepareStatement(sql);
					ps.setString(1,report1[11]);
					ps.setInt(2,cid);
					ps.setInt(3,yid);
					int i = ps.executeUpdate();
					ps.close();
                    stmt.close();
					connMgr.freeConnection("xavier",con);
					error="success";
					}catch(Exception e){error=(e.toString());
					}
					
		return error;
}

public String PgmAttDel(int cid,int yid)
{

try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;
					    PreparedStatement ps;
                  sql="delete from cellpgmatt where cellid=? and paid=?";
					ps=con.prepareStatement(sql);
					ps.setInt(1,cid);
					ps.setInt(2,yid);
					int i = ps.executeUpdate();
					ps.close();
                    stmt.close();
					connMgr.freeConnection("xavier",con);
					error="success";
					}catch(Exception e){error=(e.toString());
					}
					
		return error;
}




public String ProgramView(int cid)
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
sql="select cellid,pid,date,substring(('000'+ltrim(rtrim(str(day(date))))), len('000'+ltrim(rtrim(str(day(date)))))-1,2)+'/'+  substring(('000'+ltrim(rtrim(str(month(date))))), "
+"len('000'+ltrim(rtrim(str(month(date)))))-1,2)+'/'+ ltrim(rtrim(str(year(date)))),program,ptype,goal,resourceperson,resourcepersonfrom, "
+"noofparticipants,noofstaff,noofstudents,pname,fname from cellprogram where cellid="+cid+" order by date desc";


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


public String ProgramView1(int cid, int yid)
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
sql="select cellid,pid,date,substring(('000'+ltrim(rtrim(str(day(date))))), len('000'+ltrim(rtrim(str(day(date)))))-1,2)+'/'+  substring(('000'+ltrim(rtrim(str(month(date))))), "
+"len('000'+ltrim(rtrim(str(month(date)))))-1,2)+'/'+ ltrim(rtrim(str(year(date)))),program,ptype,goal,resourceperson,resourcepersonfrom, "
+"noofparticipants,noofstaff,noofstudents,pname,fname from cellprogram where cellid="+cid+" and pid="+yid+" order by date desc";


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


public String ProgramInsert()
{
String sql="";
String date2="";
cellid=Integer.parseInt(report1[0].toString());
try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  			Statement stmt = con.createStatement();
			ResultSet rs;
			PreparedStatement ps;
			StringTokenizer st1 = new StringTokenizer(report1[1],"/");
		    d1=st1.nextToken();
		    m1=st1.nextToken();
		    y1=st1.nextToken();
			date2=m1+"/"+d1+"/"+y1;
					sql="select max(pid)+1 from cellprogram where cellid="+cellid;
					rs = stmt.executeQuery(sql);
					if(rs.next())     { 	pid=rs.getInt(1); 	    }
					rs.close();
					
                    sql="delete from cellprogram where cellid="+cellid+" and pid="+pid;
					ps=con.prepareStatement(sql);
					int k = ps.executeUpdate();
					ps.close();
					
                    sql="insert into cellprogram values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
					ps=con.prepareStatement(sql);
					ps.setInt(1,cellid);
					ps.setInt(2,pid);
					ps.setString(3,date2);
					ps.setString(4,report1[2]);
					ps.setString(5,report1[3]);
					ps.setString(6,report1[4]);
					ps.setString(7,report1[5]);
					ps.setString(8,report1[6]);
					ps.setString(9,report1[7]);
					ps.setString(10,report1[8]);
					ps.setString(11,report1[9]);
					ps.setString(12,report1[10]);
					ps.setString(13,report1[11]);

					int j = ps.executeUpdate();
					ps.close();
     
                    stmt.close();
					connMgr.freeConnection("xavier",con);
					error1=pid+"";
					}catch(Exception e){error1=(e.toString());}

return error1;	

}


public String ProgramUpdate(int cid,int yid)
{
String sql="";
String date2="";
try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;
					    PreparedStatement ps;
			StringTokenizer st1 = new StringTokenizer(report1[1],"/");
		    d1=st1.nextToken();
		    m1=st1.nextToken();
		    y1=st1.nextToken();
			date2=m1+"/"+d1+"/"+y1;
					    
                  sql="update cellprogram set date=?,program=?,ptype=?,goal=?,resourceperson=?,resourcepersonfrom=?,noofparticipants=?,noofstaff=?,noofstudents=? where cellid=? and pid=?";
					ps=con.prepareStatement(sql);
					ps.setString(1,date2);
					ps.setString(2,report1[2]);
					ps.setString(3,report1[3]);
					ps.setString(4,report1[4]);
					ps.setString(5,report1[5]);
					ps.setString(6,report1[6]);
					ps.setString(7,report1[7]);
					ps.setString(8,report1[8]);
					ps.setString(9,report1[9]);
					ps.setInt(10,cid);
					ps.setInt(11,yid);
					int i = ps.executeUpdate();
					ps.close();
                    stmt.close();
					connMgr.freeConnection("xavier",con);
					error="success";
					}catch(Exception e){error=(e.toString());
					}
					
		return error;
}


public String ProgramUpload(int cid,int yid)
{

try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;
					    PreparedStatement ps;
                  sql="update cellprogram set pname=?,fname=? where cellid=? and pid=?";
					ps=con.prepareStatement(sql);
					ps.setString(1,report1[10]);
					ps.setString(2,report1[11]);
					ps.setInt(3,cid);
					ps.setInt(4,yid);
					int i = ps.executeUpdate();
					ps.close();
                    stmt.close();
					connMgr.freeConnection("xavier",con);
					error="success";
					}catch(Exception e){error=(e.toString());
					}
					
		return error;
}

public String ProgramDel(int cid,int yid)
{

try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;
					    PreparedStatement ps;
                  sql="delete from cellprogram where cellid=? and pid=?";
					ps=con.prepareStatement(sql);
					ps.setInt(1,cid);
					ps.setInt(2,yid);
					int i = ps.executeUpdate();
					ps.close();
                    stmt.close();
					connMgr.freeConnection("xavier",con);
					error="success";
					}catch(Exception e){error=(e.toString());
					}
					
		return error;
}


public String MeetingsView(int cid)
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
sql="select cellid,meid,medate,substring(('000'+ltrim(rtrim(str(day(medate))))), len('000'+ltrim(rtrim(str(day(medate)))))-1,2)+'/'+  substring(('000'+ltrim(rtrim(str(month(medate))))), "
+"len('000'+ltrim(rtrim(str(month(medate)))))-1,2)+'/'+ ltrim(rtrim(str(year(medate)))),mevenue, "
+"noofmembers,minutesfname from cellmeetings where cellid="+cid+" order by medate desc";


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


public String MeetingsView1(int cid, int meid)
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
					    sql="select cellid,meid,medate,substring(('000'+ltrim(rtrim(str(day(medate))))), len('000'+ltrim(rtrim(str(day(medate)))))-1,2)+'/'+  substring(('000'+ltrim(rtrim(str(month(medate))))), "
+"len('000'+ltrim(rtrim(str(month(medate)))))-1,2)+'/'+ ltrim(rtrim(str(year(medate)))),mevenue, "
+"noofmembers,minutesfname from cellmeetings where cellid="+cid+" and meid="+meid+" order by medate desc";


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


public String MeetingsInsert()
{
String sql="";
String date2="";
cellid=Integer.parseInt(report1[0].toString());
try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  			Statement stmt = con.createStatement();
			ResultSet rs;
			PreparedStatement ps;
			StringTokenizer st1 = new StringTokenizer(report1[1],"/");
		    d1=st1.nextToken();
		    m1=st1.nextToken();
		    y1=st1.nextToken();
			date2=m1+"/"+d1+"/"+y1;
					sql="select max(meid)+1 from cellmeetings where cellid="+cellid;
					rs = stmt.executeQuery(sql);
					if(rs.next())     { 	meid=rs.getInt(1); 	    }
					rs.close();
					
                    sql="delete from cellmeetings where cellid="+cellid+" and meid="+meid;
					ps=con.prepareStatement(sql);
					int k = ps.executeUpdate();
					ps.close();
					
                    sql="insert into cellmeetings values(?,?,?,?,?,?)";
					ps=con.prepareStatement(sql);
					ps.setInt(1,cellid);
					ps.setInt(2,meid);
					ps.setString(3,date2);
					ps.setString(4,report1[2]);
					ps.setString(5,report1[3]);
					ps.setString(6,report1[4]);

					int j = ps.executeUpdate();
					ps.close();
     
                    stmt.close();
					connMgr.freeConnection("xavier",con);
					error1="...";
					}catch(Exception e){error1=(e.toString());}

return error1;	

}


public String MeetingsUpdate(int cid,int cmeid)
{

try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;
					    PreparedStatement ps;
                  sql="update cellmeetings set minutesfname=? where cellid=? and meid=?";
					ps=con.prepareStatement(sql);
					ps.setString(1,report1[11]);
					ps.setInt(2,cid);
					ps.setInt(3,cmeid);
					int i = ps.executeUpdate();
					ps.close();
                    stmt.close();
					connMgr.freeConnection("xavier",con);
					error="success";
					}catch(Exception e){error=(e.toString());
					}
					
		return error;
}


public String MeetingsDel(int cid,int cmeid)
{

try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;
					    PreparedStatement ps;
                  sql="delete from cellmeetings where cellid=? and meid=?";
					ps=con.prepareStatement(sql);
					ps.setInt(1,cid);
					ps.setInt(2,cmeid);
					int i = ps.executeUpdate();
					ps.close();
                    stmt.close();
					connMgr.freeConnection("xavier",con);
					error="success";
					}catch(Exception e){error=(e.toString());
					}
					
		return error;
}

public String MembersView(int cid)
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
sql="select cellid,uid,userid, isnull(d.name, n1),res,slno from (select cellid,uid,userid,res,slno,b.name n1 from "
+"(select cellid,uid,userid,celloffbearers.responsibility res,slno from celloffbearers,cellrestype "
+"where celloffbearers.responsibility=cellrestype.responsibility and academicyear in (select academicyear from academicyear) and cellid="+cid+") a "
+"Left Outer join (select str(rollno) rollno,name from student) b on ltrim(rtrim(a.userid))=ltrim(rtrim(str(b.rollno)))) c "
+"Left Outer join (select staffid,name from staff) d on c.userid=d.staffid order by slno";

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



public String MembersInsert()
{
String sql="";
String date2="";
cellid=Integer.parseInt(report1[0].toString());
try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  			Statement stmt = con.createStatement();
			ResultSet rs;
			PreparedStatement ps;
					sql="select max(uid)+1 from celloffbearers where cellid="+cellid;
					rs = stmt.executeQuery(sql);
					if(rs.next())     { 	uid=rs.getInt(1); 	    }
					rs.close();
					    
				    sql="select academicyear from academicyear";
				    rs = stmt.executeQuery(sql);
				    if(rs.next())
				    {
					academicyear=rs.getString(1);
				    }
				    rs.close();
					
					
                    sql="delete from celloffbearers where cellid="+cellid+" and uid="+uid;
					ps=con.prepareStatement(sql);
					int k = ps.executeUpdate();
					ps.close();
					
                    sql="insert into celloffbearers values(?,?,?,?,?)";
					ps=con.prepareStatement(sql);
					ps.setInt(1,cellid);
					ps.setInt(2,uid);
					ps.setString(3,report1[2]);
					ps.setString(4,report1[3]);
					ps.setString(5,academicyear);
					int j = ps.executeUpdate();
					ps.close();
     
                    stmt.close();
					connMgr.freeConnection("xavier",con);
					error1="...";
					}catch(Exception e){error1=(e.toString());}

return error1;	

}



public String MembersDel(int cid,int uid)
{

try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;
					    PreparedStatement ps;
                  sql="delete from celloffbearers where cellid=? and uid=?";
					ps=con.prepareStatement(sql);
					ps.setInt(1,cid);
					ps.setInt(2,uid);
					int i = ps.executeUpdate();
					ps.close();
                    stmt.close();
					connMgr.freeConnection("xavier",con);
					error="success";
					}catch(Exception e){error=(e.toString());
					}
					
		return error;
}

public String PublicationsView(int cid)
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
sql="select cellid,pubid,pubtype,pubname,pubvolume,pubmonyear,academicyear from cellpublications "
+"where academicyear in (select academicyear from academicyear) and cellid="+cid;

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


public String PublicationsView1(int cid, int uid)
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
sql="select cellid,pubid,pubtype,pubname,pubvolume,pubmonyear,academicyear from cellpublications "
+"where academicyear in (select academicyear from academicyear) and cellid="+cid+" and pubid="+uid;

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

public String PublicationsView2(int cid, int uid)
{
int i=0;
	ecount=0;
try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;
sql="select cellid,pubid,userid,isnull(d.name, n1),responsibility from cellpubeditorial a "
+"Left Outer join (select str(rollno) rollno,name n1 from student) b on ltrim(rtrim(a.userid))=ltrim(rtrim(str(b.rollno)))  "
+"Left Outer join (select staffid,name from staff) d on a.userid=d.staffid where cellid="+cid+" and pubid="+uid;

rs = stmt.executeQuery(sql);

while(rs.next())
{
	for(i=1;i<=5;i++)
	{
		rep[i-1][ecount]=rs.getString(i);
	}
	ecount++;
}
rs.close();

stmt.close();
connMgr.freeConnection("xavier",con);
error= "success"; 
}catch(Exception e){error=(e.toString()+count+i+sql);}
return error; 
}



public String PublicationsInsert()
{
String sql="";
String date2="";
cellid=Integer.parseInt(report1[0].toString());
try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  			Statement stmt = con.createStatement();
			ResultSet rs;
			PreparedStatement ps;
					sql="select max(pubid)+1 from cellpublications where cellid="+cellid;
					rs = stmt.executeQuery(sql);
					if(rs.next())     { 	uid=rs.getInt(1); 	    }
					rs.close();
					    
				    sql="select academicyear from academicyear";
				    rs = stmt.executeQuery(sql);
				    if(rs.next())
				    {
					academicyear=rs.getString(1);
				    }
				    rs.close();
					
					
                    sql="delete from cellpublications where cellid="+cellid+" and pubid="+uid;
					ps=con.prepareStatement(sql);
					int k = ps.executeUpdate();
					ps.close();
					
                    sql="insert into cellpublications values(?,?,?,?,?,?,?)";
					ps=con.prepareStatement(sql);
					ps.setInt(1,cellid);
					ps.setInt(2,uid);
					ps.setString(3,report1[2]);
					ps.setString(4,report1[3]);
					ps.setString(5,report1[4]);
					ps.setString(6,report1[5]);
					ps.setString(7,academicyear);
					int j = ps.executeUpdate();
					ps.close();
     
                    stmt.close();
					connMgr.freeConnection("xavier",con);
					error1="...";
					}catch(Exception e){error1=(e.toString());}

return error1;	

}


public String PubEditorialInsert()
{
String sql="";
String date2="";
cellid=Integer.parseInt(report1[0].toString());
pubid=Integer.parseInt(report1[1].toString());
try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  			Statement stmt = con.createStatement();
			ResultSet rs;
			PreparedStatement ps;
					
                    sql="insert into cellpubeditorial values(?,?,?,?)";
					ps=con.prepareStatement(sql);
					ps.setInt(1,cellid);
					ps.setInt(2,pubid);
					ps.setString(3,report1[2]);
					ps.setString(4,report1[3]);
					int j = ps.executeUpdate();
					ps.close();
     
                    stmt.close();
					connMgr.freeConnection("xavier",con);
					error1="...";
					}catch(Exception e){error1=(e.toString());}

return error1;	

}

public String PubEditorialDel(int cid,int uid, String usid)
{

try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;
					    PreparedStatement ps;
                  sql="delete from cellpubeditorial where cellid=? and pubid=? and userid=?";
					ps=con.prepareStatement(sql);
					ps.setInt(1,cid);
					ps.setInt(2,uid);
					ps.setString(3,usid);
					int i = ps.executeUpdate();
					ps.close();
                    stmt.close();
					connMgr.freeConnection("xavier",con);
					error="success";
					}catch(Exception e){error=(e.toString());
					}
					
		return error;
}



public String PublicationsDel(int cid,int uid)
{
try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;
					    PreparedStatement ps;
                  sql="delete from cellpublications where cellid=? and pubid=?";
					ps=con.prepareStatement(sql);
					ps.setInt(1,cid);
					ps.setInt(2,uid);
					int i = ps.executeUpdate();
					ps.close();
                    stmt.close();
					connMgr.freeConnection("xavier",con);
					error="success";
					}catch(Exception e){error=(e.toString());
					}
		return error;
}

public String AwardsView(int cid)
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
sql="select cellid,aid,adate,adate1,userid, isnull(d.name, n1),awards,atype,alevel,prize,pname,fname from (select cellid,aid,userid,awards,atype,adate,adate1,alevel,prize,b.name n1,pname,fname from "
+"(select cellid,aid,adate,substring(('000'+ltrim(rtrim(str(day(adate))))), len('000'+ltrim(rtrim(str(day(adate)))))-1,2)+'/'+  substring(('000'+ltrim(rtrim(str(month(adate))))), "
+"len('000'+ltrim(rtrim(str(month(adate)))))-1,2)+'/'+ ltrim(rtrim(str(year(adate)))) adate1,userid,awards,atype,alevel,prize,pname,fname from cellawards where cellid="+cid+") a "
+"Left Outer join (select str(rollno) rollno,name from student) b on ltrim(rtrim(a.userid))=ltrim(rtrim(str(b.rollno)))) c "
+"Left Outer join (select staffid,name from staff) d on c.userid=d.staffid order by adate desc";


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


public String AwardsView1(int cid, int yid)
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
sql="select cellid,aid,adate,adate1,userid, isnull(d.name, n1),awards,atype,alevel,prize,pname,fname from (select cellid,aid,userid,awards,atype,adate,adate1,alevel,prize,b.name n1,pname,fname from "
+"(select cellid,aid,adate,substring(('000'+ltrim(rtrim(str(day(adate))))), len('000'+ltrim(rtrim(str(day(adate)))))-1,2)+'/'+  substring(('000'+ltrim(rtrim(str(month(adate))))), "
+"len('000'+ltrim(rtrim(str(month(adate)))))-1,2)+'/'+ ltrim(rtrim(str(year(adate)))) adate1,userid,awards,atype,alevel,prize,pname,fname from cellawards where cellid="+cid+" and aid="+yid+" ) a "
+"Left Outer join (select str(rollno) rollno,name from student) b on ltrim(rtrim(a.userid))=ltrim(rtrim(str(b.rollno)))) c "
+"Left Outer join (select staffid,name from staff) d on c.userid=d.staffid order by adate desc";

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


public String AwardsInsert()
{
String sql="";
String date2="";
cellid=Integer.parseInt(report1[0].toString());
try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  			Statement stmt = con.createStatement();
			ResultSet rs;
			PreparedStatement ps;
			StringTokenizer st1 = new StringTokenizer(report1[1],"/");
		    d1=st1.nextToken();
		    m1=st1.nextToken();
		    y1=st1.nextToken();
			date2=m1+"/"+d1+"/"+y1;
					sql="select max(aid)+1 from cellawards where cellid="+cellid;
					rs = stmt.executeQuery(sql);
					if(rs.next())     { 	aid=rs.getInt(1); 	    }
					rs.close();
					
                    sql="delete from cellawards where cellid="+cellid+" and aid="+aid;
					ps=con.prepareStatement(sql);
					int k = ps.executeUpdate();
					ps.close();
					
                    sql="insert into cellawards values(?,?,?,?,?,?,?,?,'','')";
					ps=con.prepareStatement(sql);
					ps.setInt(1,cellid);
					ps.setInt(2,aid);
					ps.setString(3,date2);
					ps.setString(4,report1[2]);
					ps.setString(5,report1[3]);
					ps.setString(6,report1[4]);
					ps.setString(7,report1[5]);
					ps.setString(8,report1[6]);
					int j = ps.executeUpdate();
					ps.close();
     
                    stmt.close();
					connMgr.freeConnection("xavier",con);
					error1=aid+"";
					}catch(Exception e){error1=(e.toString());}

return error1;	

}


public String AwardsUpdate(int cid,int yid)
{
String sql="";
String date2="";
try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;
					    PreparedStatement ps;
			StringTokenizer st1 = new StringTokenizer(report1[1],"/");
		    d1=st1.nextToken();
		    m1=st1.nextToken();
		    y1=st1.nextToken();
			date2=m1+"/"+d1+"/"+y1;
					    
                  sql="update cellawards set adate=?,userid=?,awards=?,atype=?,alevel=?,prize=? where cellid=? and aid=?";
					ps=con.prepareStatement(sql);
					ps.setString(1,date2);
					ps.setString(2,report1[2]);
					ps.setString(3,report1[3]);
					ps.setString(4,report1[4]);
					ps.setString(5,report1[5]);
					ps.setString(6,report1[6]);
					ps.setInt(7,cid);
					ps.setInt(8,yid);
					int i = ps.executeUpdate();
					ps.close();
                    stmt.close();
					connMgr.freeConnection("xavier",con);
					error="success";
					}catch(Exception e){error=(e.toString());
					}
					
		return error;
}


public String AwardsUpload(int cid,int yid)
{

try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;
					    PreparedStatement ps;
                  sql="update cellawards set pname=?,fname=? where cellid=? and aid=?";
					ps=con.prepareStatement(sql);
					ps.setString(1,report1[10]);
					ps.setString(2,report1[11]);
					ps.setInt(3,cid);
					ps.setInt(4,yid);
					int i = ps.executeUpdate();
					ps.close();
                    stmt.close();
					connMgr.freeConnection("xavier",con);
					error="success";
					}catch(Exception e){error=(e.toString());
					}
					
		return error;
}




public String AwardsDel(int cid,int aaid)
{

try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;
					    PreparedStatement ps;
                  sql="delete from cellawards where cellid=? and aid=?";
					ps=con.prepareStatement(sql);
					ps.setInt(1,cid);
					ps.setInt(2,aaid);
					int i = ps.executeUpdate();
					ps.close();
                    stmt.close();
					connMgr.freeConnection("xavier",con);
					error="success";
					}catch(Exception e){error=(e.toString());
					}
					
		return error;
}

public String MouView(int cid)
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
sql="select cellid,mid,mdate,substring(('000'+ltrim(rtrim(str(day(mdate))))), len('000'+ltrim(rtrim(str(day(mdate)))))-1,2)+'/'+  substring(('000'+ltrim(rtrim(str(month(mdate))))), "
+"len('000'+ltrim(rtrim(str(month(mdate)))))-1,2)+'/'+ ltrim(rtrim(str(year(mdate)))),moulinkage,details, "
+"substring(('000'+ltrim(rtrim(str(day(validity))))), len('000'+ltrim(rtrim(str(day(validity)))))-1,2)+'/'+  substring(('000'+ltrim(rtrim(str(month(validity))))), "
+"len('000'+ltrim(rtrim(str(month(validity)))))-1,2)+'/'+ ltrim(rtrim(str(year(validity)))),validity,fname from cellmoudetails where cellid="+cid+" order by mdate desc";

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


public String MouView1(int cid, int yid)
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
sql="select cellid,mid,mdate,substring(('000'+ltrim(rtrim(str(day(mdate))))), len('000'+ltrim(rtrim(str(day(mdate)))))-1,2)+'/'+  substring(('000'+ltrim(rtrim(str(month(mdate))))), "
+"len('000'+ltrim(rtrim(str(month(mdate)))))-1,2)+'/'+ ltrim(rtrim(str(year(mdate)))),moulinkage,details, "
+"substring(('000'+ltrim(rtrim(str(day(validity))))), len('000'+ltrim(rtrim(str(day(validity)))))-1,2)+'/'+  substring(('000'+ltrim(rtrim(str(month(validity))))), "
+"len('000'+ltrim(rtrim(str(month(validity)))))-1,2)+'/'+ ltrim(rtrim(str(year(validity)))),validity,fname from cellmoudetails where cellid="+cid+" and mid="+yid+" order by mdate desc";

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


public String MouInsert()
{
String sql="";
String date2="";
String date3="";
cellid=Integer.parseInt(report1[0].toString());
try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  			Statement stmt = con.createStatement();
			ResultSet rs;
			PreparedStatement ps;
			StringTokenizer st1 = new StringTokenizer(report1[1],"/");
		    d1=st1.nextToken();
		    m1=st1.nextToken();
		    y1=st1.nextToken();
			date2=m1+"/"+d1+"/"+y1;

			StringTokenizer st2 = new StringTokenizer(report1[4],"/");
		    d1=st2.nextToken();
		    m1=st2.nextToken();
		    y1=st2.nextToken();
			date3=m1+"/"+d1+"/"+y1;

					sql="select max(mid)+1 from cellmoudetails where cellid="+cellid;
					rs = stmt.executeQuery(sql);
					if(rs.next())     { 	mid=rs.getInt(1); 	    }
					rs.close();
					
                    sql="delete from cellmoudetails where cellid="+cellid+" and mid="+mid;
					ps=con.prepareStatement(sql);
					int k = ps.executeUpdate();
					ps.close();
					
                    sql="insert into cellmoudetails values(?,?,?,?,?,?,?)";
					ps=con.prepareStatement(sql);
					ps.setInt(1,cellid);
					ps.setInt(2,mid);
					ps.setString(3,date2);
					ps.setString(4,report1[2]);
					ps.setString(5,report1[3]);
					ps.setString(6,date3);
					ps.setString(7,report1[5]);
					int j = ps.executeUpdate();
					ps.close();
     
                    stmt.close();
					connMgr.freeConnection("xavier",con);
					error1="...";
					}catch(Exception e){error1=(e.toString());}

return error1;	

}

public String MouUpdate(int cid,int cmid)
{

try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;
					    PreparedStatement ps;
                  sql="update cellmoudetails set fname=? where cellid=? and mid=?";
					ps=con.prepareStatement(sql);
					ps.setString(1,report1[11]);
					ps.setInt(2,cid);
					ps.setInt(3,cmid);
					int i = ps.executeUpdate();
					ps.close();
                    stmt.close();
					connMgr.freeConnection("xavier",con);
					error="success";
					}catch(Exception e){error=(e.toString());
					}
					
		return error;
}



public String MouDel(int cid,int cmid)
{

try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;
					    PreparedStatement ps;
                  sql="delete from cellmoudetails where cellid=? and mid=?";
					ps=con.prepareStatement(sql);
					ps.setInt(1,cid);
					ps.setInt(2,cmid);
					int i = ps.executeUpdate();
					ps.close();
                    stmt.close();
					connMgr.freeConnection("xavier",con);
					error="success";
					}catch(Exception e){error=(e.toString());
					}
					
		return error;
}



public String AnnualReportView(int cid)
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
sql="select cellid,rid,adate,substring(('000'+ltrim(rtrim(str(day(adate))))), len('000'+ltrim(rtrim(str(day(adate)))))-1,2)+'/'+  substring(('000'+ltrim(rtrim(str(month(adate))))), "
+"len('000'+ltrim(rtrim(str(month(adate)))))-1,2)+'/'+ ltrim(rtrim(str(year(adate)))),academicyear,fname  from cellannualreport where cellid="+cid+" order by academicyear";

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

public String AnnualReportInsert()
{
String sql="";
cellid=Integer.parseInt(report1[0].toString());
try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  			Statement stmt = con.createStatement();
			ResultSet rs;
			PreparedStatement ps;
			
					    sql="select academicyear from academicyear";
					    rs = stmt.executeQuery(sql);
					    if(rs.next())
					    {
						academicyear=rs.getString(1);
					    }
					    rs.close();

					sql="select max(rid)+1 from cellannualreport where cellid="+cellid;
					rs = stmt.executeQuery(sql);
					if(rs.next())     { 	mid=rs.getInt(1); 	    }
					rs.close();
					
                    sql="delete from cellannualreport where cellid="+cellid+" and rid="+mid;
					ps=con.prepareStatement(sql);
					int k = ps.executeUpdate();
					ps.close();
					
                    sql="insert into cellannualreport values(?,?,getdate(),?,?)";
					ps=con.prepareStatement(sql);
					ps.setInt(1,cellid);
					ps.setInt(2,mid);
					ps.setString(3,academicyear);
					ps.setString(4,report1[1]);
					int j = ps.executeUpdate();
					ps.close();
     
                    stmt.close();
					connMgr.freeConnection("xavier",con);
					error1="...";
					}catch(Exception e){error1=(e.toString());}

return error1;	
}


public String AnnualReportDel(int cid,int crid)
{

try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;
					    PreparedStatement ps;
                  sql="delete from cellannualreport where cellid=? and rid=?";
					ps=con.prepareStatement(sql);
					ps.setInt(1,cid);
					ps.setInt(2,crid);
					int i = ps.executeUpdate();
					ps.close();
                    stmt.close();
					connMgr.freeConnection("xavier",con);
					error="success";
					}catch(Exception e){error=(e.toString());
					}
					
		return error;
}

public String CellReport(int select)
{
int i=0;
	count=0;
	fdate="06/01/"+academicyear.substring(0,4);
	tdate="05/31/"+academicyear.substring(8,12);
	
try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;
					    
sql="select cellid,cellname,celltype,convener,startdate,substring(('000'+ltrim(rtrim(str(day(startdate))))), len('000'+ltrim(rtrim(str(day(startdate)))))-1,2)+'/'+  substring(('000'+ltrim(rtrim(str(month(startdate))))), "
+"len('000'+ltrim(rtrim(str(month(startdate)))))-1,2)+'/'+ ltrim(rtrim(str(year(startdate)))),name,0,0,0,0,0,0,0,0 from cells,staff where staffid=convener order by cellid";

if(select==1)
sql="select cellid,cellname,celltype,convener,name,startdate,substring(('000'+ltrim(rtrim(str(day(startdate))))), len('000'+ltrim(rtrim(str(day(startdate)))))-1,2)+'/'+  substring(('000'+ltrim(rtrim(str(month(startdate))))), "
+"len('000'+ltrim(rtrim(str(month(startdate)))))-1,2)+'/'+ ltrim(rtrim(str(year(startdate)))),0,0,0,0,0,0,0,0 from cells,staff where staffid=convener and celltype in ("+celltypein+") and cellid in ("+cellidin+") order by cellid";

if(select==2)
sql="select cellid,cellname,celltype,userid, isnull(d.name, n1),res,uid,slno,0,0,0,0,0,0,0 from (select cellid,cellname,celltype,uid,userid,res,slno,b.name n1 from "
+"(select cells.cellid,cellname,celltype,uid,userid,celloffbearers.responsibility res,slno from cells,celloffbearers,cellrestype,student  "
+"where  ltrim(rtrim(userid))=ltrim(rtrim(str(rollno))) and  departmentno in ("+dnoin+") and cells.cellid=celloffbearers.cellid and celloffbearers.responsibility=cellrestype.responsibility and academicyear in ('"+academicyear+"') and cells.cellid in("+cellidin+")  and celltype in("+celltypein+")) a "
+"Left Outer join (select str(rollno) rollno,name from student ) b on ltrim(rtrim(a.userid))=ltrim(rtrim(str(b.rollno)))) c "
+"Left Outer join (select staffid,name from staff) d on c.userid=d.staffid order by cellid,slno";

if(select==3)
sql="select cells.cellid,cellname,celltype,substring(('000'+ltrim(rtrim(str(day(ypdate))))), len('000'+ltrim(rtrim(str(day(ypdate)))))-1,2)+'/'+  substring(('000'+ltrim(rtrim(str(month(ypdate))))), "
+"len('000'+ltrim(rtrim(str(month(ypdate)))))-1,2)+'/'+ ltrim(rtrim(str(year(ypdate)))),program,ptype,objective,outcome,beneficiaries,ypid,ypdate,0,0,0,0 from cellyearplan,cells "
+"where cells.cellid=cellyearplan.cellid and cells.cellid in ("+cellidin+") and celltype in("+celltypein+")  and ptype in ("+ptypein+") "
+"and ypdate between '"+fdate+"' and '"+tdate+"' order by ypdate desc";

if(select==4)
sql="select cells.cellid,cellname,celltype,substring(('000'+ltrim(rtrim(str(day(date))))), len('000'+ltrim(rtrim(str(day(date)))))-1,2)+'/'+  substring(('000'+ltrim(rtrim(str(month(date))))), "
+"len('000'+ltrim(rtrim(str(month(date)))))-1,2)+'/'+ ltrim(rtrim(str(year(date)))),program,ptype,goal,resourceperson,resourcepersonfrom, "
+"noofparticipants,noofstaff,noofstudents,pname,fname,date from cellprogram,cells where cells.cellid=cellprogram.cellid "
+"and cells.cellid in ("+cellidin+") and celltype in("+celltypein+")  and ptype in ("+ptypein+") and resourcepersonfrom in ("+ resourcepersonfromin+") "
+"and date between '"+fdate+"' and '"+tdate+"' order by date desc";

if(select==5)
sql="select cellid,cellname,celltype,padate1,userid, isnull(d.name, n1),program,ptype,venue,impact,fname,paid,padate,0,0 from (select cellid,cellname,celltype,paid,userid,program,ptype,padate,padate1,venue,impact,b.name n1,fname from "
+"(select cells.cellid,cellname,celltype,paid,padate,substring(('000'+ltrim(rtrim(str(day(padate))))), len('000'+ltrim(rtrim(str(day(padate)))))-1,2)+'/'+  substring(('000'+ltrim(rtrim(str(month(padate))))), "
+"len('000'+ltrim(rtrim(str(month(padate)))))-1,2)+'/'+ ltrim(rtrim(str(year(padate)))) padate1,userid,program,ptype,venue,impact,fname from cells,cellpgmatt "
+"where cellpgmatt.cellid=cells.cellid  "
+"and padate between '"+fdate+"' and '"+tdate+"' and cells.cellid in ("+cellidin+") and celltype in("+celltypein+")  and ptype in ("+programin+")) a "
+"Left Outer join (select str(rollno) rollno,name from student) b on ltrim(rtrim(a.userid))=ltrim(rtrim(str(b.rollno)))) c "
+"Left Outer join (select staffid,name from staff) d on c.userid=d.staffid order by padate desc";
/*
sql="select cellid,cellname,celltype,padate1,userid, isnull(d.name, n1),program,ptype,venue,impact,fname,paid,padate,0,0 from (select cellid,cellname,celltype,paid,userid,program,ptype,padate,padate1,venue,impact,b.name n1,fname from "
+"(select cells.cellid,cellname,celltype,paid,padate,substring(('000'+ltrim(rtrim(str(day(padate))))), len('000'+ltrim(rtrim(str(day(padate)))))-1,2)+'/'+  substring(('000'+ltrim(rtrim(str(month(padate))))), "
+"len('000'+ltrim(rtrim(str(month(padate)))))-1,2)+'/'+ ltrim(rtrim(str(year(padate)))) padate1,userid,program,ptype,venue,impact,fname from cells,cellpgmatt,student "
+"where ltrim(rtrim(userid))=ltrim(rtrim(str(rollno))) and  departmentno in  ("+dnoin+") and cellpgmatt.cellid=cells.cellid  "
+"and padate between '"+fdate+"' and '"+tdate+"' and cells.cellid in ("+cellidin+") and celltype in("+celltypein+")  and ptype in ("+programin+")) a "
+"Left Outer join (select str(rollno) rollno,name from student) b on ltrim(rtrim(a.userid))=ltrim(rtrim(str(b.rollno)))) c "
+"Left Outer join (select staffid,name from staff) d on c.userid=d.staffid order by padate desc";
*/
if(select==6)
sql="select cellid,cellname,celltype,adate1,userid, isnull(d.name, n1),awards,atype,alevel,prize,aid,adate,0,0,0 from (select cellid,cellname,celltype,aid,userid,awards,atype,adate,adate1,alevel,prize,b.name n1 from "
+"(select cells.cellid,cellname,celltype,aid,adate,substring(('000'+ltrim(rtrim(str(day(adate))))), len('000'+ltrim(rtrim(str(day(adate)))))-1,2)+'/'+  substring(('000'+ltrim(rtrim(str(month(adate))))), "
+"len('000'+ltrim(rtrim(str(month(adate)))))-1,2)+'/'+ ltrim(rtrim(str(year(adate)))) adate1,userid,awards,atype,alevel,prize from cellawards,cells,student "
+"where ltrim(rtrim(userid))=ltrim(rtrim(str(rollno))) and  departmentno in ("+dnoin+") and cellawards.cellid=cells.cellid  "
+"and adate between '"+fdate+"' and '"+tdate+"' and cells.cellid in ("+cellidin+") and celltype in("+celltypein+") and atype in("+atypein+") and alevel in ("+alevelin+") ) a "
+"Left Outer join (select str(rollno) rollno,name from student) b on ltrim(rtrim(a.userid))=ltrim(rtrim(str(b.rollno)))) c "
+"Left Outer join (select staffid,name from staff) d on c.userid=d.staffid order by adate desc";

if(select==7)
sql="select cells.cellid,cellname,celltype,substring(('000'+ltrim(rtrim(str(day(mdate))))), len('000'+ltrim(rtrim(str(day(mdate)))))-1,2)+'/'+  substring(('000'+ltrim(rtrim(str(month(mdate))))), "
+"len('000'+ltrim(rtrim(str(month(mdate)))))-1,2)+'/'+ ltrim(rtrim(str(year(mdate)))),moulinkage,details, "
+"substring(('000'+ltrim(rtrim(str(day(validity))))), len('000'+ltrim(rtrim(str(day(validity)))))-1,2)+'/'+  substring(('000'+ltrim(rtrim(str(month(validity))))), "
+"len('000'+ltrim(rtrim(str(month(validity)))))-1,2)+'/'+ ltrim(rtrim(str(year(validity)))),validity,fname,mid,mdate,0,0,0,0 from cellmoudetails,cells where cellmoudetails.cellid=cells.cellid "
+"and mdate between '"+fdate+"' and '"+tdate+"' and cells.cellid in ("+cellidin+") and celltype in("+celltypein+") order by mdate desc";

if(select==8)
sql="select cells.cellid,cellname,celltype,pubtype,pubname,pubvolume,pubmonyear,academicyear,pubid,0,0,0,0,0,0 from cellpublications,cells "
+"where cells.cellid=cellpublications.cellid and academicyear in ('"+academicyear+"') and cells.cellid in ("+cellidin+") and celltype in("+celltypein+") ";

if(select==9)
sql="select cells.cellid,cellname,celltype,substring(('000'+ltrim(rtrim(str(day(medate))))), len('000'+ltrim(rtrim(str(day(medate)))))-1,2)+'/'+  substring(('000'+ltrim(rtrim(str(month(medate))))), "
+"len('000'+ltrim(rtrim(str(month(medate)))))-1,2)+'/'+ ltrim(rtrim(str(year(medate)))),mevenue,noofmembers,minutesfname,meid,medate,0,0,0,0,0,0 from cellmeetings,cells "
+"where cells.cellid=cellmeetings.cellid and medate between '"+fdate+"' and '"+tdate+"'  and cells.cellid in ("+cellidin+") and celltype in("+celltypein+") order by medate desc ";

if(select==10)
sql="select cellid,dname,substring(('000'+ltrim(rtrim(str(day(ptdate))))), len('000'+ltrim(rtrim(str(day(ptdate)))))-1,2)+'/'+  substring(('000'+ltrim(rtrim(str(month(ptdate))))), "
+"len('000'+ltrim(rtrim(str(month(ptdate)))))-1,2)+'/'+ ltrim(rtrim(str(year(ptdate)))),parentsatt,pname,fname,mname,sf,ptid,ptdate,0,0,0,0,0 from cellpti,department where cellid=1010 and ptdno=dno and ptdno in ("+dnoin+") order by ptdate desc";

if(select==11)
sql="select cellid,dname,substring(('000'+ltrim(rtrim(str(day(pldate))))), len('000'+ltrim(rtrim(str(day(pldate)))))-1,2)+'/'+  substring(('000'+ltrim(rtrim(str(month(pldate))))), "
+"len('000'+ltrim(rtrim(str(month(pldate)))))-1,2)+'/'+ ltrim(rtrim(str(year(pldate)))),plcampus,plvenue,plorg,pnos,snos,sf,plid,pldate,0,0,0,0 from cellplacement,department where cellid=1018 and pdno=dno and pdno in ("+dnoin+") order by pldate desc";

if(select==12)
sql="select cellid,cellname,celltype,userid, isnull(d.name, n1),res,uid,slno,0,0,0,0,0,0,0 from (select cellid,cellname,celltype,uid,userid,res,slno,b.name n1 from "
+"(select cells.cellid,cellname,celltype,uid,userid,celloffbearers.responsibility res,slno from cells,celloffbearers,cellrestype,staff  "
+"where  ltrim(rtrim(userid))=ltrim(rtrim(staffid)) and  dno in ("+dnoin+") and cells.cellid=celloffbearers.cellid and celloffbearers.responsibility=cellrestype.responsibility and academicyear in ('"+academicyear+"') and cells.cellid in("+cellidin+")  and celltype in("+celltypein+")) a "
+"Left Outer join (select str(rollno) rollno,name from student ) b on ltrim(rtrim(a.userid))=ltrim(rtrim(str(b.rollno)))) c "
+"Left Outer join (select staffid,name from staff) d on c.userid=d.staffid order by cellid,slno";

rs = stmt.executeQuery(sql);

while(rs.next())
{
	for(i=1;i<=15;i++)
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



public void setrep(String[][] rep) {this.rep=rep;}  public String[][] getrep() {	return this.rep;}
public void setreport(String[][] report) {this.report=report;}  public String[][] getreport() {	return this.report;}
public void setreport1(String[] report1) {this.report1=report1;}  public String[] getreport1() {	return this.report1;}
public void setreport2(String[] report2) {this.report2=report2;}  public String[] getreport2() {	return this.report2;}
public void setcount(int count) {this.count=count;} public int getcount(){	return this.count;}
public void setcount1(int count1) {this.count1=count1;} public int getcount1(){	return this.count1;}
public void setecount(int ecount) {this.ecount=ecount;} public int getecount(){	return this.ecount;}
public void setfname(String fname) { this.fname = fname; } public String getfname() { return this.fname; } 
public void setpname(String pname) { this.pname = pname; } public String getpname() { return this.pname; } 
public void setacademicyear(String academicyear) { this.academicyear = academicyear; } public String getacademicyear() { return this.academicyear; } 


public void setcellidin(String cellidin) { this.cellidin = cellidin; } public String getcellidin() { return this.cellidin; } 
public void setdnoin(String dnoin) { this.dnoin = dnoin; } public String getdnoin() { return this.dnoin; } 
public void setcelltypein(String celltypein) { this.celltypein = celltypein; } public String getcelltypein() { return this.celltypein; } 
public void setptypein(String ptypein) { this.ptypein = ptypein; } public String getptypein() { return this.ptypein; } 
public void setresourcepersonfromin(String resourcepersonfromin) { this.resourcepersonfromin = resourcepersonfromin; } public String getresourcepersonfromin() { return this.resourcepersonfromin; } 
public void setprogramin(String programin) { this.programin = programin; } public String getprogramin() { return this.programin; } 
public void setatypein(String atypein) { this.atypein = atypein; } public String getatypein() { return this.atypein; } 
public void setalevelin(String alevelin) { this.alevelin = alevelin; } public String getalevelin() { return this.alevelin; } 
public void setpubtypein(String pubtypein) { this.pubtypein = pubtypein; } public String getpubtypein() { return this.pubtypein; } 
public void setplcampusin(String plcampusin) { this.plcampusin = plcampusin; } public String getplcampusin() { return this.plcampusin; } 

}




	
	