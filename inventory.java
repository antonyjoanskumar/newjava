package sxcce;
import java.sql.*;
import java.util.StringTokenizer;
import com.jspsmart.upload.*;
import java.util.Date;
import java.io.*;
import dd.DBConnectionManager;

public class inventory
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
/*
try { i1=Integer.parseInt(repin[1]); }catch(Exception e){}			
try { i2=Integer.parseInt(repin[2]); }catch(Exception e){}			
try { i3=Integer.parseInt(repin[3]); }catch(Exception e){}			
try { i4=Integer.parseInt(repin[5]); }catch(Exception e){}			
*/

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

public String ItemUpdate(int id)
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
			StringTokenizer st1 = new StringTokenizer(repin[11],"/");
		    d1=st1.nextToken();
		    m1=st1.nextToken();
		    y1=st1.nextToken();
			repin[11]=m1+"/"+d1+"/"+y1;

			StringTokenizer st2 = new StringTokenizer(repin[12],"/");
		    d1=st2.nextToken();
		    m1=st2.nextToken();
		    y1=st2.nextToken();
			repin[12]=m1+"/"+d1+"/"+y1;
//i1=Integer.parseInt(repin[9]);
}
catch(Exception e){}			
					    
                  sql="update inv_itemmaster set supplier=?,stype=?,dno=?,cat=?,itype=?,iname=?,spec=?,make=?,partno=?,macid=?,ptype=?,pdate=?,wdate=?,supid=?,billno=?,cost=?,qty=?,unit=?,location=?,incharge=?,register=?,trackid=?,sxid=?,remarks=?,status=? where id=?";
					ps=con.prepareStatement(sql);
					ps.setString(1,repin[25]);
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
					ps.setInt(26,id);
					int i = ps.executeUpdate();
					ps.close();
                    stmt.close();
					connMgr.freeConnection("xavier",con);
					error="success";
					}catch(Exception e){error=(e.toString());
					}
					
		return error;
}

public String ItemDel(int id)
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
					    
                  sql="delete from inv_itemmaster where id=?";
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


public String CatInsert()
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
					sql="select max(id)+1 from inv_cat";
					rs = stmt.executeQuery(sql);
					if(rs.next())     { 	cid=rs.getInt(1); 	    }
					rs.close();
				
                    sql="delete from  inv_cat where id="+cid;
					ps=con.prepareStatement(sql);
					int k = ps.executeUpdate();
					ps.close();
					
                    sql="insert into  inv_cat values(?,?)";
					ps=con.prepareStatement(sql);
					ps.setInt(1,cid);
					ps.setString(2,repin[2]);
					int j = ps.executeUpdate();
					ps.close();
     
                    stmt.close();
					connMgr.freeConnection("xavier",con);
					error="...";
					}catch(Exception e){error=(e.toString());}
return error;	
}

public String CatUpdate(int id)
{
try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;
					    PreparedStatement ps;
                  sql="update inv_cat set catname=? where id=?";
					ps=con.prepareStatement(sql);
					ps.setString(1,repin[2]);
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


public String CatDel(int id)
{

try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;
					    PreparedStatement ps;
                  sql="delete from inv_cat where id=?";
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

public String CatList()
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
sql="select * from inv_cat order by catname";
rs = stmt.executeQuery(sql);
while(rs.next())
{
	for(i=1;i<=2;i++)
	{
		report1[i-1][count]=rs.getString(i);
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


public String DeptList()
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
sql="select * from department order by dno";
rs = stmt.executeQuery(sql);
while(rs.next())
{
	for(i=1;i<=4;i++)
	{
		report1[i-1][count]=rs.getString(i);
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

public String ItemTypeInsert()
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
					sql="select max(id)+1 from inv_itemtype";
					rs = stmt.executeQuery(sql);
					if(rs.next())     { 	cid=rs.getInt(1); 	    }
					rs.close();
				
                    sql="delete from  inv_itemtype where id="+cid;
					ps=con.prepareStatement(sql);
					int k = ps.executeUpdate();
					ps.close();
					
				
                    sql="insert into  inv_itemtype values(?,?,?,?)";
					ps=con.prepareStatement(sql);
					ps.setInt(1,cid);
					ps.setString(2,repin[2]);
					ps.setString(3,repin[3]);
					ps.setString(4,repin[4]);
					int j = ps.executeUpdate();
					ps.close();
     
                    stmt.close();
					connMgr.freeConnection("xavier",con);
					error="...";
					}catch(Exception e){error=(e.toString());}
return error;	
}

public String ItemTypeUpdate(int id)
{

try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;
					    PreparedStatement ps;
                  sql="update inv_itemtype set itemtype=?,catid=?,dno=? where id=?";
					ps=con.prepareStatement(sql);
					ps.setString(1,repin[2]);
					ps.setString(2,repin[3]);
					ps.setString(3,repin[4]);
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


public String ItemTypeDel(int id)
{

try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;
					    PreparedStatement ps;
                  sql="delete from inv_itemtype where id=?";
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

public String ItemTypeList(String tcatid)
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
if(tcatid.equals("0"))
  sql="select * from inv_itemtype order by itemtype";
else
  sql="select * from inv_itemtype where catid in("+tcatid+") order by itemtype";
rs = stmt.executeQuery(sql);
while(rs.next())
{
	for(i=1;i<=4;i++)
	{
		report1[i-1][count]=rs.getString(i);
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

public String SupplierInsert()
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
					sql="select max(id)+1 from inv_supplier";
					rs = stmt.executeQuery(sql);
					if(rs.next())     { 	cid=rs.getInt(1); 	    }
					rs.close();
				
                    sql="delete from  inv_supplier where id="+cid;
					ps=con.prepareStatement(sql);
					int k = ps.executeUpdate();
					ps.close();
					
				
                    sql="insert into  inv_supplier values(?,?,?,?,?,?,?,?,?)";
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
					int j = ps.executeUpdate();
					ps.close();
     
                    stmt.close();
					connMgr.freeConnection("xavier",con);
					error="...";
					}catch(Exception e){error=(e.toString());}
return error;	
}

public String SupplierUpdate(int id)
{

try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;
					    PreparedStatement ps;
                  sql="update inv_supplier set remarks=?,supname=?,address=?,phone=?,email=?,contact=?,dnos=?,active=? where id=?";
					ps=con.prepareStatement(sql);
					ps.setString(1,repin[8]);
					ps.setString(2,repin[1]);
					ps.setString(3,repin[2]);
					ps.setString(4,repin[3]);
					ps.setString(5,repin[4]);
					ps.setString(6,repin[5]);
					ps.setString(7,repin[6]);
					ps.setString(8,repin[7]);
					ps.setInt(9,id);
					int i = ps.executeUpdate();
					ps.close();
                    stmt.close();
					connMgr.freeConnection("xavier",con);
					error="success";
					}catch(Exception e){error=(e.toString());
					}
					
		return error;
}


public String SupplierDel(int id)
{

try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;
					    PreparedStatement ps;
                  sql="delete from inv_supplier where id=?";
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

public String SupplierList()
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
  sql="select * from inv_supplier order by supname";
rs = stmt.executeQuery(sql);
while(rs.next())
{
	for(i=1;i<=9;i++)
	{
		report1[i-1][count]=rs.getString(i);
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


public String ItemList(int max, int inv, int cat, int grp)
{
int i=0;
count=0;
String stype=" ";
String cat1=" ";
String dept1=" ";
String sup1=" ";

try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;
if(inv==0)
stype=" ";
else
 stype=" and stype in ("+inv+") ";

if(cat==0)
cat1=" ";
else
 cat1=" and cat in ("+cat+") ";

if(repin[0].equals("0,0"))
dept1=" ";

else
 dept1=" and inv_itemmaster.dno in("+repin[0]+") ";

if(repin[2].equals("0"))
sup1=" ";
else
 sup1=" and supid in("+repin[2]+") ";

try
{
StringTokenizer st1 = new StringTokenizer(repin[4],"/");
d1=st1.nextToken();
m1=st1.nextToken();
y1=st1.nextToken();
fdate=m1+"/"+d1+"/"+y1;
}
catch(Exception e){}			

try
{
StringTokenizer st2 = new StringTokenizer(repin[5],"/");
d1=st2.nextToken();
m1=st2.nextToken();
y1=st2.nextToken();
tdate=m1+"/"+d1+"/"+y1;
}
catch(Exception e){}			

if(grp==0)
{
sql="select inv_itemmaster.id,stype,sf,catname,itemtype,iname,spec,make,partno,macid,ptype,pdate,wdate,supname,billno,cost,qty,(cost*qty),unit,location,incharge,register,trackid,sxid,inv_itemmaster.remarks,status,inv_itemmaster.stype,inv_itemmaster.dno,inv_itemmaster.cat,inv_itemmaster.itype,supplier from inv_itemmaster,department,inv_cat,inv_itemtype,inv_supplier "
+" where inv_itemmaster.dno=department.dno and cat=inv_cat.id and itype=inv_itemtype.id and supid=inv_supplier.id "
+stype+cat1+dept1+sup1+" and itype in("+repin[1]+") and status in("+repin[3]+") and pdate between '"+fdate+"' and '"+tdate+"' and cost>="+max +" order by pdate";
}
else
{
sql="select max(inv_itemmaster.id),max(stype),max(sf),max(catname),max(itemtype),max(iname),max(spec),max(make),max(partno),max(macid),max(ptype),max(pdate),max(wdate),max(supname),max(billno),max(cost) ,sum(qty),(max(cost)*sum(qty)), "
+" max(unit),max(location),max(incharge),max(register),max(trackid),max(sxid),max(inv_itemmaster.remarks),max(status),max(inv_itemmaster.stype),max(inv_itemmaster.dno),max(inv_itemmaster.cat),max(inv_itemmaster.itype),max(supplier) from inv_itemmaster,department,inv_cat,inv_itemtype,inv_supplier "
+" where inv_itemmaster.dno=department.dno and cat=inv_cat.id and itype=inv_itemtype.id and supid=inv_supplier.id "
+stype+cat1+dept1+sup1+" and itype in("+repin[1]+") and status in("+repin[3]+") and pdate between '"+fdate+"' and '"+tdate+"' and cost>="+max
+" group by sf,catname,iname,iname,spec,make,cost order by max(inv_itemmaster.dno),max(itemtype),max(pdate)";
}
rs = stmt.executeQuery(sql);

while(rs.next())
{
	for(i=1;i<=31;i++)
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

public String InsuranceList(int max, int inv, int cat, int grp)
{
int i=0;
count=0;
String stype=" ";
String cat1=" ";
String dept1=" ";
String sup1=" ";

try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;
if(inv==0)
stype=" ";
else
 stype=" and stype in ("+inv+") ";

if(cat==0)
cat1=" ";
else
 cat1=" and cat in ("+cat+") ";

if(repin[0].equals("0,0"))
dept1=" ";

else
 dept1=" and inv_itemmaster.dno in("+repin[0]+") ";

if(repin[2].equals("0"))
sup1=" ";
else
 sup1=" and supid in("+repin[2]+") ";

try
{
StringTokenizer st1 = new StringTokenizer(repin[4],"/");
d1=st1.nextToken();
m1=st1.nextToken();
y1=st1.nextToken();
fdate=m1+"/"+d1+"/"+y1;
}
catch(Exception e){}			

try
{
StringTokenizer st2 = new StringTokenizer(repin[5],"/");
d1=st2.nextToken();
m1=st2.nextToken();
y1=st2.nextToken();
tdate=m1+"/"+d1+"/"+y1;
}
catch(Exception e){}			

if(grp==2)
{
sql="select inv_itemmaster.id,stype,sf,catname,itemtype,iname,spec,make,partno,macid,ptype,pdate,wdate,supname,billno,cost,qty,(cost*qty),unit,location,incharge,register,trackid,sxid,inv_itemmaster.remarks,status,inv_itemmaster.stype,inv_itemmaster.dno,inv_itemmaster.cat,inv_itemmaster.itype,supplier from inv_itemmaster,department,inv_cat,inv_itemtype,inv_supplier "
+" where inv_itemmaster.dno=department.dno and cat=inv_cat.id and itype=inv_itemtype.id and supid=inv_supplier.id "
+stype+cat1+dept1+sup1+" and itype in("+repin[1]+") and status in("+repin[3]+") and pdate between '"+fdate+"' and '"+tdate+"' and cost>="+max +" order by inv_itemmaster.dno,location,pdate";
}
else if(grp==1)
{
sql="select inv_itemmaster.id,stype,sf,catname,itemtype,iname,spec,make,partno,macid,ptype,pdate,wdate,supname,billno,cost,qty,(cost*qty),unit,location,incharge,register,trackid,sxid,inv_itemmaster.remarks,status,inv_itemmaster.stype,inv_itemmaster.dno,inv_itemmaster.cat,inv_itemmaster.itype,supplier from inv_itemmaster,department,inv_cat,inv_itemtype,inv_supplier "
+" where inv_itemmaster.dno=department.dno and cat=inv_cat.id and itype=inv_itemtype.id and supid=inv_supplier.id "
+" and wdate>= '"+tdate+"' "
+stype+cat1+dept1+sup1+" and itype in("+repin[1]+") and status in("+repin[3]+") and pdate between '"+fdate+"' and '"+tdate+"' and cost>="+max +" order by inv_itemmaster.dno,location,pdate";
}
else 
{
sql="select inv_itemmaster.id,stype,sf,catname,itemtype,iname,spec,make,partno,macid,ptype,pdate,wdate,supname,billno,cost,qty,(cost*qty),unit,location,incharge,register,trackid,sxid,inv_itemmaster.remarks,status,inv_itemmaster.stype,inv_itemmaster.dno,inv_itemmaster.cat,inv_itemmaster.itype,supplier from inv_itemmaster,department,inv_cat,inv_itemtype,inv_supplier "
+" where inv_itemmaster.dno=department.dno and cat=inv_cat.id and itype=inv_itemtype.id and supid=inv_supplier.id "
+" and wdate< '"+tdate+"' "
+stype+cat1+dept1+sup1+" and itype in("+repin[1]+") and status in("+repin[3]+") and pdate between '"+fdate+"' and '"+tdate+"' and cost>="+max +" order by inv_itemmaster.dno,location,pdate";
}

rs = stmt.executeQuery(sql);

while(rs.next())
{
	for(i=1;i<=31;i++)
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

public String ItemView(int id)
{
int i=0;
count=0;
found=0;
try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;
sql="select *, substring(('000'+ltrim(rtrim(str(day(pdate))))), len('000'+ltrim(rtrim(str(day(pdate)))))-1,2)+'/'+  substring(('000'+ltrim(rtrim(str(month(pdate))))),len('000'+ltrim(rtrim(str(month(pdate)))))-1,2)+'/'+ ltrim(rtrim(str(year(pdate)))), "
+"substring(('000'+ltrim(rtrim(str(day(wdate))))), len('000'+ltrim(rtrim(str(day(wdate)))))-1,2)+'/'+  substring(('000'+ltrim(rtrim(str(month(wdate))))),len('000'+ltrim(rtrim(str(month(wdate)))))-1,2)+'/'+ ltrim(rtrim(str(year(wdate)))) from inv_itemmaster where id="+id;
rs = stmt.executeQuery(sql);
if(rs.next())
{
	for(i=1;i<=28;i++)
	{
		rep[i-1]=rs.getString(i);
	}
	found=1;
}
else
found=0;
rs.close();

stmt.close();
connMgr.freeConnection("xavier",con);
error= "success"; 
}catch(Exception e){error=(e.toString()+count+i+sql);}
return found+""; 
}

public String MaxItemID()
{
int i=0;
count=0;
found=0;
try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;
sql="select max(id)+1 from inv_itemmaster";
rs = stmt.executeQuery(sql);
if(rs.next())
{
found=rs.getInt(1);
}
else
found=0;
rs.close();

stmt.close();
connMgr.freeConnection("xavier",con);
error= "success"; 
}catch(Exception e){error=(e.toString()+count+i+sql);}
return found+""; 
}





public void setrep(String[] rep) {this.rep=rep;}  public String[] getrep() {	return this.rep;}
public void setrepin(String[] repin) {this.repin=repin;}  public String[] getrepin() {	return this.repin;}
public void setreport(String[][] report) {this.report=report;}  public String[][] getreport() {	return this.report;}
public void setreport1(String[][] report1) {this.report1=report1;}  public String[][] getreport1() {	return this.report1;}
public void setcount(int count) {this.count=count;} public int getcount(){	return this.count;}
public void setacademicyear(String academicyear) { this.academicyear = academicyear; } public String getacademicyear() { return this.academicyear; } 



}




	
	