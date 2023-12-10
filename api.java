package sxcce;
import java.sql.*;
import java.util.StringTokenizer;
import com.jspsmart.upload.*;
import java.util.Date;
import java.io.*;
import dd.DBConnectionManager;

public class api
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

public String ExperienceInsert()
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
			StringTokenizer st1 = new StringTokenizer(repin[7],"/");
		    d1=st1.nextToken();
		    m1=st1.nextToken();
		    y1=st1.nextToken();
			date1=m1+"/"+d1+"/"+y1;

			StringTokenizer st2 = new StringTokenizer(repin[8],"/");
		    d1=st2.nextToken();
		    m1=st2.nextToken();
		    y1=st2.nextToken();
			date2=m1+"/"+d1+"/"+y1;
i1=Integer.parseInt(repin[9]);			
}
catch(Exception e){}			

			
					sql="select max(id)+1 from apiexperience";
					rs = stmt.executeQuery(sql);
					if(rs.next())     { 	cid=rs.getInt(1); 	    }
					rs.close();
					
				
                    sql="insert into  apiexperience values(?,?,?,?,?,?,?,?,?,?,?)";
					ps=con.prepareStatement(sql);
					ps.setInt(1,cid);
					ps.setString(2,repin[1]);
					ps.setString(3,repin[2]);
					ps.setString(4,repin[3]);
					ps.setString(5,repin[4]);
					ps.setString(6,repin[5]);
					ps.setString(7,repin[6]);
					ps.setString(8,date1);
					ps.setString(9,date2);
					ps.setInt(10,i1);
					ps.setString(11,repin[10]);
					int j = ps.executeUpdate();
					ps.close();
     
                    stmt.close();
					connMgr.freeConnection("xavier",con);
					error="...";
					}catch(Exception e){error=(e.toString());}
return error;	
}

public String ExperienceUpdate(int id)
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
			StringTokenizer st1 = new StringTokenizer(repin[7],"/");
		    d1=st1.nextToken();
		    m1=st1.nextToken();
		    y1=st1.nextToken();
			date1=m1+"/"+d1+"/"+y1;

			StringTokenizer st2 = new StringTokenizer(repin[8],"/");
		    d1=st2.nextToken();
		    m1=st2.nextToken();
		    y1=st2.nextToken();
			date2=m1+"/"+d1+"/"+y1;
i1=Integer.parseInt(repin[9]);
}
catch(Exception e){}			
					    
                  sql="update apiexperience set type=?,designation=?,employer=?,department=?,jobdescription=?,fdate=?,tdate=?,pay=? where id=? and staffid=?";
					ps=con.prepareStatement(sql);

					ps.setString(1,repin[2]);
					ps.setString(2,repin[3]);
					ps.setString(3,repin[4]);
					ps.setString(4,repin[5]);
					ps.setString(5,repin[6]);
					ps.setString(6,date1);
					ps.setString(7,date2);
					ps.setInt(8,i1);

					ps.setInt(9,id);
					ps.setString(10,repin[1]);
					int i = ps.executeUpdate();
					ps.close();
                    stmt.close();
					connMgr.freeConnection("xavier",con);
					error="success";
					}catch(Exception e){error=(e.toString());
					}
					
		return error;
}

public String ExperienceUpload(int id)
{
try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;
					    PreparedStatement ps;
			    
                  sql="update apiexperience set fname1=? where id=? and staffid=?";
					ps=con.prepareStatement(sql);
					ps.setString(1,repin[10]);
					ps.setInt(2,id);
					ps.setString(3,repin[1]);
					int i = ps.executeUpdate();
					ps.close();
                    stmt.close();
					connMgr.freeConnection("xavier",con);
					error="success";
					}catch(Exception e){error=sql+repin[11]+id+repin[1]+(e.toString());		}
return error;
}


public String ExperienceDel(int id)
{

try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;
					    PreparedStatement ps;
                  sql="delete from apiexperience where id=? and staffid=?";
					ps=con.prepareStatement(sql);
					ps.setInt(1,id);
					ps.setString(2,repin[1]);
					int i = ps.executeUpdate();
					ps.close();
                    stmt.close();
					connMgr.freeConnection("xavier",con);
					error="success";
					}catch(Exception e){error=(e.toString());
					}
					
		return error;
}


public String ExperienceList()
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
sql="select id,staffid,type,designation,employer,department,jobdescription, "
+"substring(('000'+ltrim(rtrim(str(day(fdate))))), len('000'+ltrim(rtrim(str(day(fdate)))))-1,2)+'/'+  substring(('000'+ltrim(rtrim(str(month(fdate))))), "
+"len('000'+ltrim(rtrim(str(month(fdate)))))-1,2)+'/'+ ltrim(rtrim(str(year(fdate)))), "
+"substring(('000'+ltrim(rtrim(str(day(tdate))))), len('000'+ltrim(rtrim(str(day(tdate)))))-1,2)+'/'+  substring(('000'+ltrim(rtrim(str(month(tdate))))), "
+"len('000'+ltrim(rtrim(str(month(tdate)))))-1,2)+'/'+ ltrim(rtrim(str(year(tdate)))),pay,fname1 from apiexperience where staffid='"+repin[1]+"'";

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


public String ExperienceView()
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
sql="select id,staffid,type,designation,employer,department,jobdescription, "
+"substring(('000'+ltrim(rtrim(str(day(fdate))))), len('000'+ltrim(rtrim(str(day(fdate)))))-1,2)+'/'+  substring(('000'+ltrim(rtrim(str(month(fdate))))), "
+"len('000'+ltrim(rtrim(str(month(fdate)))))-1,2)+'/'+ ltrim(rtrim(str(year(fdate)))), "
+"substring(('000'+ltrim(rtrim(str(day(tdate))))), len('000'+ltrim(rtrim(str(day(tdate)))))-1,2)+'/'+  substring(('000'+ltrim(rtrim(str(month(tdate))))), "
+"len('000'+ltrim(rtrim(str(month(tdate)))))-1,2)+'/'+ ltrim(rtrim(str(year(tdate)))),pay,fname1 from apiexperience where staffid='"+repin[1]+"' and id="+repin[0];

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


public String QualificationInsert()
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
			StringTokenizer st1 = new StringTokenizer(repin[12],"/");
		    d1=st1.nextToken();
		    m1=st1.nextToken();
		    y1=st1.nextToken();
			date1=m1+"/"+d1+"/"+y1;

			StringTokenizer st2 = new StringTokenizer(repin[13],"/");
		    d1=st2.nextToken();
		    m1=st2.nextToken();
		    y1=st2.nextToken();
			date2=m1+"/"+d1+"/"+y1;

}
catch(Exception e){}
try
{
i1=Integer.parseInt(repin[8]);
i2=Integer.parseInt(repin[9]);
f1=Float.parseFloat(repin[10]);
}
catch(Exception e){}
			
					sql="select max(id)+1 from apiqualification";
					rs = stmt.executeQuery(sql);
					if(rs.next())     { 	cid=rs.getInt(1); 	    }
					rs.close();
					
				
                    sql="insert into  apiQualification values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
					ps=con.prepareStatement(sql);
					ps.setInt(1,cid);
					ps.setString(2,repin[1]);
					ps.setString(3,repin[2]);
					ps.setString(4,repin[3]);
					ps.setString(5,repin[4]);
					ps.setString(6,repin[5]);
					ps.setString(7,repin[6]);
					ps.setString(8,repin[7]);
					ps.setInt(9,i1);
					ps.setInt(10,i2);
					ps.setFloat(11,f1);
					ps.setString(12,repin[11]);
					ps.setString(13,date1);
					ps.setString(14,date2);
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

public String QualificationUpdate(int id)
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
			StringTokenizer st1 = new StringTokenizer(repin[12],"/");
		    d1=st1.nextToken();
		    m1=st1.nextToken();
		    y1=st1.nextToken();
			date1=m1+"/"+d1+"/"+y1;

			StringTokenizer st2 = new StringTokenizer(repin[13],"/");
		    d1=st2.nextToken();
		    m1=st2.nextToken();
		    y1=st2.nextToken();
			date2=m1+"/"+d1+"/"+y1;
}
catch(Exception e){}			
try
{
i1=Integer.parseInt(repin[8]);
i2=Integer.parseInt(repin[9]);
f1=Float.parseFloat(repin[10]);
}
catch(Exception e){}
					    
                  sql="update apiqualification set type=?,degree=?,specialization=?,department=?,college=?,university=?,monthofpassing=?,yearofpassing=?,percentage=?,grade=?,thesisdate=?,phddate=? where id=? and staffid=?";
					ps=con.prepareStatement(sql);

					ps.setString(1,repin[2]);
					ps.setString(2,repin[3]);
					ps.setString(3,repin[4]);
					ps.setString(4,repin[5]);
					ps.setString(5,repin[6]);
					ps.setString(6,repin[7]);
					ps.setInt(7,i1);
					ps.setInt(8,i2);
					ps.setFloat(9,f1);
					ps.setString(10,repin[11]);
					ps.setString(11,date1);
					ps.setString(12,date2);
					ps.setInt(13,id);
					ps.setString(14,repin[1]);
					int i = ps.executeUpdate();
					ps.close();
                    stmt.close();
					connMgr.freeConnection("xavier",con);
					error="success";
					}catch(Exception e){error=(e.toString());
					}
					
		return error;
}

public String QualificationUpload(int id,int jk)
{
try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;
					    PreparedStatement ps;
if(jk==1)			    
                  sql="update apiqualification set fname1=? where id=? and staffid=?";
else                  
                  sql="update apiqualification set fname2=? where id=? and staffid=?";
					ps=con.prepareStatement(sql);
					ps.setString(1,repin[14]);
					ps.setInt(2,id);
					ps.setString(3,repin[1]);
					int i = ps.executeUpdate();
					ps.close();
                    stmt.close();
					connMgr.freeConnection("xavier",con);
					error="success";
					}catch(Exception e){error=sql+repin[11]+id+repin[1]+(e.toString());		}
return error;
}


public String QualificationDel(int id)
{

try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;
					    PreparedStatement ps;
                  sql="delete from apiQualification where id=? and staffid=?";
					ps=con.prepareStatement(sql);
					ps.setInt(1,id);
					ps.setString(2,repin[1]);
					int i = ps.executeUpdate();
					ps.close();
                    stmt.close();
					connMgr.freeConnection("xavier",con);
					error="success";
					}catch(Exception e){error=(e.toString());
					}
					
		return error;
}


public String QualificationList()
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
sql="select id,staffid,type,degree,specialization,department,college,university,substring(DATENAME(m, str(monthofpassing) + '/1/2017'),1,3),yearofpassing,CAST(percentage AS numeric(10,2)),grade, "
+"substring(('000'+ltrim(rtrim(str(day(thesisdate))))), len('000'+ltrim(rtrim(str(day(thesisdate)))))-1,2)+'/'+  substring(('000'+ltrim(rtrim(str(month(thesisdate))))), "
+"len('000'+ltrim(rtrim(str(month(thesisdate)))))-1,2)+'/'+ ltrim(rtrim(str(year(thesisdate)))), "
+"substring(('000'+ltrim(rtrim(str(day(phddate))))), len('000'+ltrim(rtrim(str(day(phddate)))))-1,2)+'/'+  substring(('000'+ltrim(rtrim(str(month(phddate))))), "
+"len('000'+ltrim(rtrim(str(month(phddate)))))-1,2)+'/'+ ltrim(rtrim(str(year(phddate)))),fname1,fname2 from apiQualification where staffid='"+repin[1]+"' order by yearofpassing desc";

rs = stmt.executeQuery(sql);

while(rs.next())
{
	for(i=1;i<=16;i++)
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


public String QualificationView()
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
sql="select id,staffid,type,degree,specialization,department,college,university,monthofpassing,yearofpassing,CAST(percentage AS numeric(10,2)),grade, "
+"substring(('000'+ltrim(rtrim(str(day(thesisdate))))), len('000'+ltrim(rtrim(str(day(thesisdate)))))-1,2)+'/'+  substring(('000'+ltrim(rtrim(str(month(thesisdate))))), "
+"len('000'+ltrim(rtrim(str(month(thesisdate)))))-1,2)+'/'+ ltrim(rtrim(str(year(thesisdate)))), "
+"substring(('000'+ltrim(rtrim(str(day(phddate))))), len('000'+ltrim(rtrim(str(day(phddate)))))-1,2)+'/'+  substring(('000'+ltrim(rtrim(str(month(phddate))))), "
+"len('000'+ltrim(rtrim(str(month(phddate)))))-1,2)+'/'+ ltrim(rtrim(str(year(phddate)))),fname1,fname2 from apiQualification where staffid='"+repin[1]+"' and id="+repin[0];

rs = stmt.executeQuery(sql);

while(rs.next())
{
	for(i=1;i<=16;i++)
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

public String ResearchInsert()
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

try{
	i1=Integer.parseInt(repin[8]);
	i2=Integer.parseInt(repin[9]);
}catch(Exception e){}

					sql="select max(id)+1 from apiresearch";
					rs = stmt.executeQuery(sql);
					if(rs.next())     { 	cid=rs.getInt(1); 	    }
					rs.close();
			
                    sql="insert into  apiresearch values(?,?,?,?,?,?,?,?,?,?,?)";
					ps=con.prepareStatement(sql);
					ps.setInt(1,cid);
					ps.setString(2,repin[1]);
					ps.setString(3,repin[2]);
					ps.setString(4,repin[3]);
					ps.setString(5,repin[4]);
					ps.setString(6,repin[5]);
					ps.setString(7,repin[6]);
					ps.setString(8,repin[7]);
					ps.setInt(9,i1);
					ps.setInt(10,i2);
					ps.setString(11,repin[10]);
					
					int j = ps.executeUpdate();
					ps.close();
     
                    stmt.close();
					connMgr.freeConnection("xavier",con);
					error="...";
					}catch(Exception e){error=(e.toString());}
return error;	
}

public String ResearchUpdate(int id)
{
try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;
					    PreparedStatement ps;
try{
	i1=Integer.parseInt(repin[8]);
	i2=Integer.parseInt(repin[9]);
}catch(Exception e){}
					    
                  sql="update apiresearch set researcharea=?,supervisoryesno=?,university=?,referenceno=?,faculty=?,department=?,scholarsdoing=?,scholarscompleted=? where id=? and staffid=?";
					ps=con.prepareStatement(sql);
					ps.setString(1,repin[2]);
					ps.setString(2,repin[3]);
					ps.setString(3,repin[4]);
					ps.setString(4,repin[5]);
					ps.setString(5,repin[6]);
					ps.setString(6,repin[7]);
					ps.setInt(7,i1);
					ps.setInt(8,i2);
					ps.setInt(9,id);
					ps.setString(10,repin[1]);
					int i = ps.executeUpdate();
					ps.close();
                    stmt.close();
					connMgr.freeConnection("xavier",con);
					error="success";
					}catch(Exception e){error=(e.toString());
					}
					
		return error;
}

public String ResearchUpload(int id)
{
try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;
					    PreparedStatement ps;
			    
                  sql="update apiresearch set fname1=? where id=? and staffid=?";
					ps=con.prepareStatement(sql);
					ps.setString(1,repin[10]);
					ps.setInt(2,id);
					ps.setString(3,repin[1]);
					int i = ps.executeUpdate();
					ps.close();
                    stmt.close();
					connMgr.freeConnection("xavier",con);
					error="success";
					}catch(Exception e){error=sql+repin[11]+id+repin[1]+(e.toString());		}
return error;
}


public String ResearchDel(int id)
{

try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;
					    PreparedStatement ps;
                  sql="delete from apiresearch where id=? and staffid=?";
					ps=con.prepareStatement(sql);
					ps.setInt(1,id);
					ps.setString(2,repin[1]);
					int i = ps.executeUpdate();
					ps.close();
                    stmt.close();
					connMgr.freeConnection("xavier",con);
					error="success";
					}catch(Exception e){error=(e.toString());
					}
					
		return error;
}

public String ResearchList()
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
sql="select * from apiresearch where staffid='"+repin[1]+"'";

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


public String ResearchView()
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
sql="select * from apiresearch where staffid='"+repin[1]+"' and id="+repin[0];

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


public String ScholarInsert()
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
try{
	i1=Integer.parseInt(repin[9]);
}catch(Exception e){}

try
{
			StringTokenizer st1 = new StringTokenizer(repin[14],"/");
		    d1=st1.nextToken();
		    m1=st1.nextToken();
		    y1=st1.nextToken();
			date1=m1+"/"+d1+"/"+y1;
}
catch(Exception e){}			


			
					sql="select max(id)+1 from apischolar";
					rs = stmt.executeQuery(sql);
					if(rs.next())     { 	cid=rs.getInt(1); 	    }
					rs.close();
			
                    sql="insert into  apischolar values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
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
					ps.setInt(10,i1);
					ps.setString(11,repin[10]);
					ps.setString(12,repin[11]);
					ps.setString(13,repin[12]);
					ps.setString(14,repin[13]);
					ps.setString(15,date1);
					
					int j = ps.executeUpdate();
					ps.close();
     
                    stmt.close();
					connMgr.freeConnection("xavier",con);
					error="...";
					}catch(Exception e){error=(e.toString());}
return error;	
}

public String ScholarUpdate(int id)
{
try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;
					    PreparedStatement ps;
try{
	i1=Integer.parseInt(repin[9]);
}catch(Exception e){}
try
{
			StringTokenizer st1 = new StringTokenizer(repin[14],"/");
		    d1=st1.nextToken();
		    m1=st1.nextToken();
		    y1=st1.nextToken();
			date1=m1+"/"+d1+"/"+y1;
}
catch(Exception e){}			

					    
                  sql="update apischolar set sregno=?,sname=?,degree=?,university=?,faculty=?,department=?,category=?,regyear=?,regsession=?,areaofresearch=?,status=?,vdate=? where id=? and staffid=?";
					ps=con.prepareStatement(sql);
					ps.setString(1,repin[2]);
					ps.setString(2,repin[3]);
					ps.setString(3,repin[4]);
					ps.setString(4,repin[5]);
					ps.setString(5,repin[6]);
					ps.setString(6,repin[7]);
					ps.setString(7,repin[8]);
					ps.setInt(8,i1);
					ps.setString(9,repin[10]);
					ps.setString(10,repin[11]);
					ps.setString(11,repin[12]);
					ps.setString(12,date1);
					ps.setInt(13,id);
					ps.setString(14,repin[1]);
					int i = ps.executeUpdate();
					ps.close();
                    stmt.close();
					connMgr.freeConnection("xavier",con);
					error="success";
					}catch(Exception e){error=(e.toString());
					}
					
		return error;
}

public String ScholarUpload(int id)
{
try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;
					    PreparedStatement ps;
			    
                  sql="update apischolar set fname1=? where id=? and staffid=?";
					ps=con.prepareStatement(sql);
					ps.setString(1,repin[13]);
					ps.setInt(2,id);
					ps.setString(3,repin[1]);
					int i = ps.executeUpdate();
					ps.close();
                    stmt.close();
					connMgr.freeConnection("xavier",con);
					error="success";
					}catch(Exception e){error=sql+repin[11]+id+repin[1]+(e.toString());		}
return error;
}


public String ScholarDel(int id)
{

try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;
					    PreparedStatement ps;
                  sql="delete from apischolar where id=? and staffid=?";
					ps=con.prepareStatement(sql);
					ps.setInt(1,id);
					ps.setString(2,repin[1]);
					int i = ps.executeUpdate();
					ps.close();
                    stmt.close();
					connMgr.freeConnection("xavier",con);
					error="success";
					}catch(Exception e){error=(e.toString());
					}
					
		return error;
}

public String ScholarList()
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
sql="SELECT id,staffid,sregno,sname,degree,university,faculty,department,category,regyear,regsession,areaofresearch,status,fname1, "
+" substring(('000'+ltrim(rtrim(str(day(vdate))))), len('000'+ltrim(rtrim(str(day(vdate)))))-1,2)+'/'+  substring(('000'+ltrim(rtrim(str(month(vdate))))),  "
+" len('000'+ltrim(rtrim(str(month(vdate)))))-1,2)+'/'+ ltrim(rtrim(str(year(vdate)))) "
+" from apischolar where staffid='"+repin[1]+"' order by status";

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


public String ScholarView()
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
sql="SELECT id,staffid,sregno,sname,degree,university,faculty,department,category,regyear,regsession,areaofresearch,status,fname1, "
+" substring(('000'+ltrim(rtrim(str(day(vdate))))), len('000'+ltrim(rtrim(str(day(vdate)))))-1,2)+'/'+  substring(('000'+ltrim(rtrim(str(month(vdate))))),  "
+" len('000'+ltrim(rtrim(str(month(vdate)))))-1,2)+'/'+ ltrim(rtrim(str(year(vdate)))) "
+" from apischolar where staffid='"+repin[1]+"' and id="+repin[0];

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



public String JournalInsert()
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
try{
	i1=Integer.parseInt(repin[12]);
	i2=Integer.parseInt(repin[13]);
	i3=Integer.parseInt(repin[20]);

}catch(Exception e){}
			
					sql="select max(id)+1 from apijournal";
					rs = stmt.executeQuery(sql);
					if(rs.next())     { 	cid=rs.getInt(1); 	    }
					rs.close();
			
                    sql="insert into  apijournal values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
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
					ps.setInt(13,i1);
					ps.setInt(14,i2);
					ps.setString(15,repin[14]);
					ps.setString(16,repin[15]);
					ps.setString(17,repin[16]);
					ps.setString(18,repin[17]);
					ps.setString(19,repin[18]);
					ps.setString(20,repin[19]);
					ps.setInt(21,i3);
					ps.setString(22,repin[21]);
					
					int j = ps.executeUpdate();
					ps.close();
     
                    stmt.close();
					connMgr.freeConnection("xavier",con);
					error="...";
					}catch(Exception e){error=(e.toString());}
return error;	
}

public String JournalUpdate(int id)
{
try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;
					    PreparedStatement ps;
try{
	i1=Integer.parseInt(repin[12]);
	i2=Integer.parseInt(repin[13]);
	i3=Integer.parseInt(repin[20]);
}catch(Exception e){}
					    
                  sql="update apijournal set type=?,title=?,authors=?,affiliation=?,authortype=?,journalname=?,publisher=?,intornat=?,issn=?,doi=?,monthofpub=?,yearofpub=?,volissuepage=?,indexing=?,impact=?,snip=?,sjr=?,citationcount=?,interdept=? where id=? and staffid=?";
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
					ps.setString(10,repin[11]);
					ps.setInt(11,i1);
					ps.setInt(12,i2);
					ps.setString(13,repin[14]);
					ps.setString(14,repin[15]);
					ps.setString(15,repin[16]);
					ps.setString(16,repin[17]);
					ps.setString(17,repin[18]);
					ps.setInt(18,i3);
					ps.setString(19,repin[21]);
					
					ps.setInt(20,id);
					ps.setString(21,repin[1]);
					
					int i = ps.executeUpdate();
					ps.close();
                    stmt.close();
					connMgr.freeConnection("xavier",con);
					error="success";
					}catch(Exception e){error=(e.toString());
					}
					
		return error;
}

public String JournalUpload(int id)
{
try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;
					    PreparedStatement ps;
			    
                  sql="update apijournal set fname1=? where id=? and staffid=?";
					ps=con.prepareStatement(sql);
					ps.setString(1,repin[19]);
					ps.setInt(2,id);
					ps.setString(3,repin[1]);
					int i = ps.executeUpdate();
					ps.close();
                    stmt.close();
					connMgr.freeConnection("xavier",con);
					error="success";
					}catch(Exception e){error=sql+repin[11]+id+repin[1]+(e.toString());		}
return error;
}


public String JournalDel(int id)
{

try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;
					    PreparedStatement ps;
                  sql="delete from apijournal where id=? and staffid=?";
					ps=con.prepareStatement(sql);
					ps.setInt(1,id);
					ps.setString(2,repin[1]);
					int i = ps.executeUpdate();
					ps.close();
                    stmt.close();
					connMgr.freeConnection("xavier",con);
					error="success";
					}catch(Exception e){error=(e.toString());
					}
					
		return error;
}

public String JournalList()
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
sql="select id,staffid,type,title,authors,affiliation,authortype,journalname,publisher,intornat,issn,doi, "
+"substring(DATENAME(m, str(monthofpub) + '/1/2017'),1,3),yearofpub,volissuepage,indexing,impact,snip,sjr,fname1,citationcount,interdept from apijournal  where staffid='"+repin[1]+"' order by yearofpub desc";

rs = stmt.executeQuery(sql);

while(rs.next())
{
	for(i=1;i<=22;i++)
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


public String JournalView()
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
sql="select * from apijournal where staffid='"+repin[1]+"' and id="+repin[0];

rs = stmt.executeQuery(sql);

while(rs.next())
{
	for(i=1;i<=22;i++)
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


public String ConferenceInsert()
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

			StringTokenizer st2 = new StringTokenizer(repin[10],"/");
		    d1=st2.nextToken();
		    m1=st2.nextToken();
		    y1=st2.nextToken();
			date2=m1+"/"+d1+"/"+y1;
}
catch(Exception e){}			
try{
	i1=Integer.parseInt(repin[13]);
	i2=Integer.parseInt(repin[17]);
}catch(Exception e){}

			
					sql="select max(id)+1 from apiconference";
					rs = stmt.executeQuery(sql);
					if(rs.next())     { 	cid=rs.getInt(1); 	    }
					rs.close();
				
			
                    sql="insert into  apiconference values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
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
					ps.setString(10,date1);
					ps.setString(11,date2);
					ps.setString(12,repin[11]);
					ps.setString(13,repin[12]);
					ps.setInt(14,i1);
					ps.setString(15,repin[14]);
					ps.setString(16,repin[15]);
					ps.setString(17,repin[16]);
					ps.setInt(18,i2);
					ps.setString(19,repin[18]);
					int j = ps.executeUpdate();
					ps.close();
     
                    stmt.close();
					connMgr.freeConnection("xavier",con);
					error="...";
					}catch(Exception e){error=(e.toString());}
return error;	
}

public String ConferenceUpdate(int id)
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

			StringTokenizer st2 = new StringTokenizer(repin[10],"/");
		    d1=st2.nextToken();
		    m1=st2.nextToken();
		    y1=st2.nextToken();
			date2=m1+"/"+d1+"/"+y1;
}
catch(Exception e){}			
try{
	i1=Integer.parseInt(repin[13]);
	i2=Integer.parseInt(repin[17]);
}catch(Exception e){}
					    
                  sql="update apiconference set authors=?,affiliation=?,venue=?,title=?,authortype=?,conferencename=?,intornat=?,fdate=?,tdate=?,issn=?,doi=?,yearofpub=?,volissuepage=?,indexing=?,citationcount=?,interdept=? where id=? and staffid=?";
					ps=con.prepareStatement(sql);

					ps.setString(1,repin[2]);
					ps.setString(2,repin[3]);
					ps.setString(3,repin[4]);
					ps.setString(4,repin[5]);
					ps.setString(5,repin[6]);
					ps.setString(6,repin[7]);
					ps.setString(7,repin[8]);
					ps.setString(8,date1);
					ps.setString(9,date2);
					ps.setString(10,repin[11]);
					ps.setString(11,repin[12]);
					ps.setInt(12,i1);
					ps.setString(13,repin[14]);
					ps.setString(14,repin[16]);
					ps.setInt(15,i2);
					ps.setString(16,repin[18]);

					ps.setInt(17,id);
					ps.setString(18,repin[1]);
					int i = ps.executeUpdate();
					ps.close();
                    stmt.close();
					connMgr.freeConnection("xavier",con);
					error="success";
					}catch(Exception e){error=(e.toString());
					}
					
		return error;
}

public String ConferenceUpload(int id)
{
try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;
					    PreparedStatement ps;
			    
                  sql="update apiconference set fname1=? where id=? and staffid=?";
					ps=con.prepareStatement(sql);
					ps.setString(1,repin[15]);
					ps.setInt(2,id);
					ps.setString(3,repin[1]);
					int i = ps.executeUpdate();
					ps.close();
                    stmt.close();
					connMgr.freeConnection("xavier",con);
					error="success";
					}catch(Exception e){error=sql+repin[11]+id+repin[1]+(e.toString());		}
return error;
}


public String ConferenceDel(int id)
{

try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;
					    PreparedStatement ps;
                  sql="delete from apiconference where id=? and staffid=?";
					ps=con.prepareStatement(sql);
					ps.setInt(1,id);
					ps.setString(2,repin[1]);
					int i = ps.executeUpdate();
					ps.close();
                    stmt.close();
					connMgr.freeConnection("xavier",con);
					error="success";
					}catch(Exception e){error=(e.toString());
					}
					
		return error;
}

public String ConferenceList()
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
sql="select id,staffid,authors,affiliation,venue,title,authortype,conferencename,intornat, "
+"substring(('000'+ltrim(rtrim(str(day(fdate))))), len('000'+ltrim(rtrim(str(day(fdate)))))-1,2)+'/'+  substring(('000'+ltrim(rtrim(str(month(fdate))))), "
+"len('000'+ltrim(rtrim(str(month(fdate)))))-1,2)+'/'+ ltrim(rtrim(str(year(fdate)))), "
+"substring(('000'+ltrim(rtrim(str(day(tdate))))), len('000'+ltrim(rtrim(str(day(tdate)))))-1,2)+'/'+  substring(('000'+ltrim(rtrim(str(month(tdate))))), "
+"len('000'+ltrim(rtrim(str(month(tdate)))))-1,2)+'/'+ ltrim(rtrim(str(year(tdate)))), "
+"issn,doi,yearofpub,volissuepage,fname1,indexing,citationcount,interdept from apiconference where staffid='"+repin[1]+"' order by fdate desc";

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


public String ConferenceView()
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
sql="select id,staffid,authors,affiliation,venue,title,authortype,conferencename,intornat, "
+"substring(('000'+ltrim(rtrim(str(day(fdate))))), len('000'+ltrim(rtrim(str(day(fdate)))))-1,2)+'/'+  substring(('000'+ltrim(rtrim(str(month(fdate))))), "
+"len('000'+ltrim(rtrim(str(month(fdate)))))-1,2)+'/'+ ltrim(rtrim(str(year(fdate)))), "
+"substring(('000'+ltrim(rtrim(str(day(tdate))))), len('000'+ltrim(rtrim(str(day(tdate)))))-1,2)+'/'+  substring(('000'+ltrim(rtrim(str(month(tdate))))), "
+"len('000'+ltrim(rtrim(str(month(tdate)))))-1,2)+'/'+ ltrim(rtrim(str(year(tdate)))), "
+"issn,doi,yearofpub,volissuepage,fname1,indexing,citationcount,interdept from apiconference where staffid='"+repin[1]+"' and id="+repin[0];

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



public String FundedInsert()
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
			StringTokenizer st1 = new StringTokenizer(repin[6],"/");
		    d1=st1.nextToken();
		    m1=st1.nextToken();
		    y1=st1.nextToken();
			date1=m1+"/"+d1+"/"+y1;

			StringTokenizer st2 = new StringTokenizer(repin[7],"/");
		    d1=st2.nextToken();
		    m1=st2.nextToken();
		    y1=st2.nextToken();
			date2=m1+"/"+d1+"/"+y1;

			StringTokenizer st3 = new StringTokenizer(repin[8],"/");
		    d1=st3.nextToken();
		    m1=st3.nextToken();
		    y1=st3.nextToken();
			date3=m1+"/"+d1+"/"+y1;

			StringTokenizer st4 = new StringTokenizer(repin[12],"/");
		    d1=st4.nextToken();
		    m1=st4.nextToken();
		    y1=st4.nextToken();
			date4=m1+"/"+d1+"/"+y1;
}
catch(Exception e){}			

try{
	i1=Integer.parseInt(repin[13]);
	i2=Integer.parseInt(repin[14]);
	i3=Integer.parseInt(repin[15]);
}catch(Exception e){}

			
					sql="select max(id)+1 from apifunded";
					rs = stmt.executeQuery(sql);
					if(rs.next())     { 	cid=rs.getInt(1); 	    }
					rs.close();
				
			
                    sql="insert into  apifunded values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
					ps=con.prepareStatement(sql);
					ps.setInt(1,cid);
					ps.setString(2,repin[1]);
					ps.setString(3,repin[2]);
					ps.setString(4,repin[3]);
					ps.setString(5,repin[4]);
					ps.setString(6,repin[5]);
					ps.setString(7,date1);
					ps.setString(8,date2);
					ps.setString(9,date3);
					ps.setString(10,repin[9]);
					ps.setString(11,repin[10]);
					ps.setString(12,repin[11]);
					ps.setString(13,date4);
					ps.setInt(14,i1);
					ps.setInt(15,i2);
					ps.setInt(16,i3);
					ps.setString(17,repin[16]);
					ps.setString(18,repin[17]);
					ps.setString(19,repin[18]);
					int j = ps.executeUpdate();
					ps.close();
     
                    stmt.close();
					connMgr.freeConnection("xavier",con);
					error="...";
					}catch(Exception e){error=(e.toString());}
return error;	
}

public String FundedUpdate(int id)
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
			StringTokenizer st1 = new StringTokenizer(repin[6],"/");
		    d1=st1.nextToken();
		    m1=st1.nextToken();
		    y1=st1.nextToken();
			date1=m1+"/"+d1+"/"+y1;

			StringTokenizer st2 = new StringTokenizer(repin[7],"/");
		    d1=st2.nextToken();
		    m1=st2.nextToken();
		    y1=st2.nextToken();
			date2=m1+"/"+d1+"/"+y1;

			StringTokenizer st3 = new StringTokenizer(repin[8],"/");
		    d1=st3.nextToken();
		    m1=st3.nextToken();
		    y1=st3.nextToken();
			date3=m1+"/"+d1+"/"+y1;

			StringTokenizer st4 = new StringTokenizer(repin[12],"/");
		    d1=st4.nextToken();
		    m1=st4.nextToken();
		    y1=st4.nextToken();
			date4=m1+"/"+d1+"/"+y1;

}
catch(Exception e){}			
try{
	i1=Integer.parseInt(repin[13]);
	i2=Integer.parseInt(repin[14]);
	i3=Integer.parseInt(repin[15]);
}catch(Exception e){}
					    
                  sql="update apifunded set type=?,researcharea=?,coinvestigator=?,title=?,appdate=?,fdate=?,tdate=?,status=?,agency=?,orderno=?,sanctioneddate=?,requiredamt=?,sanctionedamt=?,receivedamt=?,outcome=? where id=? and staffid=?";
					ps=con.prepareStatement(sql);

					ps.setString(1,repin[2]);
					ps.setString(2,repin[3]);
					ps.setString(3,repin[4]);
					ps.setString(4,repin[5]);
					ps.setString(5,date1);
					ps.setString(6,date2);
					ps.setString(7,date3);
					ps.setString(8,repin[9]);
					ps.setString(9,repin[10]);
					ps.setString(10,repin[11]);
					ps.setString(11,date4);
					ps.setInt(12,i1);
					ps.setInt(13,i2);
					ps.setInt(14,i3);
					ps.setString(15,repin[16]);
					ps.setInt(16,id);
					ps.setString(17,repin[1]);
					int i = ps.executeUpdate();
					ps.close();
                    stmt.close();
					connMgr.freeConnection("xavier",con);
					error="success";
					}catch(Exception e){error=(e.toString());
					}
					
		return error;
}

public String FundedUpload(int id,int jk)
{
try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;
					    PreparedStatement ps;
if (jk==1)			    
                  sql="update apifunded set fname1=? where id=? and staffid=?";
else
                  sql="update apifunded set fname2=? where id=? and staffid=?";
					ps=con.prepareStatement(sql);
					ps.setString(1,repin[17]);
					ps.setInt(2,id);
					ps.setString(3,repin[1]);
					int i = ps.executeUpdate();
					ps.close();
                    stmt.close();
					connMgr.freeConnection("xavier",con);
					error="success";
					}catch(Exception e){error=sql+repin[11]+id+repin[1]+(e.toString());		}
return error;
}


public String FundedDel(int id)
{

try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;
					    PreparedStatement ps;
                  sql="delete from apifunded where id=? and staffid=?";
					ps=con.prepareStatement(sql);
					ps.setInt(1,id);
					ps.setString(2,repin[1]);
					int i = ps.executeUpdate();
					ps.close();
                    stmt.close();
					connMgr.freeConnection("xavier",con);
					error="success";
					}catch(Exception e){error=(e.toString());
					}
					
		return error;
}

public String FundedList()
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
sql="select id,staffid,type,researcharea,coinvestigator,title, "
+"substring(('000'+ltrim(rtrim(str(day(appdate))))), len('000'+ltrim(rtrim(str(day(appdate)))))-1,2)+'/'+  substring(('000'+ltrim(rtrim(str(month(appdate))))), "
+"len('000'+ltrim(rtrim(str(month(appdate)))))-1,2)+'/'+ ltrim(rtrim(str(year(appdate)))), "
+"substring(('000'+ltrim(rtrim(str(day(fdate))))), len('000'+ltrim(rtrim(str(day(fdate)))))-1,2)+'/'+  substring(('000'+ltrim(rtrim(str(month(fdate))))), "
+"len('000'+ltrim(rtrim(str(month(fdate)))))-1,2)+'/'+ ltrim(rtrim(str(year(fdate)))), "
+"substring(('000'+ltrim(rtrim(str(day(tdate))))), len('000'+ltrim(rtrim(str(day(tdate)))))-1,2)+'/'+  substring(('000'+ltrim(rtrim(str(month(tdate))))), "
+"len('000'+ltrim(rtrim(str(month(tdate)))))-1,2)+'/'+ ltrim(rtrim(str(year(tdate)))), "
+"status,agency,orderno, "
+"substring(('000'+ltrim(rtrim(str(day(sanctioneddate))))), len('000'+ltrim(rtrim(str(day(sanctioneddate)))))-1,2)+'/'+  substring(('000'+ltrim(rtrim(str(month(sanctioneddate))))), "
+"len('000'+ltrim(rtrim(str(month(sanctioneddate)))))-1,2)+'/'+ ltrim(rtrim(str(year(sanctioneddate)))), "
+"requiredamt,sanctionedamt,receivedamt,outcome,fname1,fname2 from apifunded where staffid='"+repin[1]+"' order by appdate desc";

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


public String FundedView()
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
sql="select id,staffid,type,researcharea,coinvestigator,title, "
+"substring(('000'+ltrim(rtrim(str(day(appdate))))), len('000'+ltrim(rtrim(str(day(appdate)))))-1,2)+'/'+  substring(('000'+ltrim(rtrim(str(month(appdate))))), "
+"len('000'+ltrim(rtrim(str(month(appdate)))))-1,2)+'/'+ ltrim(rtrim(str(year(appdate)))), "
+"substring(('000'+ltrim(rtrim(str(day(fdate))))), len('000'+ltrim(rtrim(str(day(fdate)))))-1,2)+'/'+  substring(('000'+ltrim(rtrim(str(month(fdate))))), "
+"len('000'+ltrim(rtrim(str(month(fdate)))))-1,2)+'/'+ ltrim(rtrim(str(year(fdate)))), "
+"substring(('000'+ltrim(rtrim(str(day(tdate))))), len('000'+ltrim(rtrim(str(day(tdate)))))-1,2)+'/'+  substring(('000'+ltrim(rtrim(str(month(tdate))))), "
+"len('000'+ltrim(rtrim(str(month(tdate)))))-1,2)+'/'+ ltrim(rtrim(str(year(tdate)))), "
+"status,agency,orderno, "
+"substring(('000'+ltrim(rtrim(str(day(sanctioneddate))))), len('000'+ltrim(rtrim(str(day(sanctioneddate)))))-1,2)+'/'+  substring(('000'+ltrim(rtrim(str(month(sanctioneddate))))), "
+"len('000'+ltrim(rtrim(str(month(sanctioneddate)))))-1,2)+'/'+ ltrim(rtrim(str(year(sanctioneddate)))), "
+"requiredamt,sanctionedamt,receivedamt,outcome,fname1,fname2 from apifunded where staffid='"+repin[1]+"' and id="+repin[0];

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


public String ConsultancyInsert()
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
			StringTokenizer st1 = new StringTokenizer(repin[4],"/");
		    d1=st1.nextToken();
		    m1=st1.nextToken();
		    y1=st1.nextToken();
			date1=m1+"/"+d1+"/"+y1;

			StringTokenizer st2 = new StringTokenizer(repin[5],"/");
		    d1=st2.nextToken();
		    m1=st2.nextToken();
		    y1=st2.nextToken();
			date2=m1+"/"+d1+"/"+y1;

			StringTokenizer st3 = new StringTokenizer(repin[9],"/");
		    d1=st3.nextToken();
		    m1=st3.nextToken();
		    y1=st3.nextToken();
			date3=m1+"/"+d1+"/"+y1;

}
catch(Exception e){}			
try{
	i1=Integer.parseInt(repin[10]);
	i2=Integer.parseInt(repin[11]);
}catch(Exception e){}

			
					sql="select max(id)+1 from apiconsultancy";
					rs = stmt.executeQuery(sql);
					if(rs.next())     { 	cid=rs.getInt(1); 	    }
					rs.close();
				
			
                    sql="insert into  apiconsultancy values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
					ps=con.prepareStatement(sql);
					ps.setInt(1,cid);
					ps.setString(2,repin[1]);
					ps.setString(3,repin[2]);
					ps.setString(4,repin[3]);
					ps.setString(5,date1);
					ps.setString(6,date2);
					ps.setString(7,repin[6]);
					ps.setString(8,repin[7]);
					ps.setString(9,repin[8]);
					ps.setString(10,date3);
					ps.setInt(11,i1);
					ps.setInt(12,i2);
					ps.setString(13,repin[12]);
					ps.setString(14,repin[13]);
					ps.setString(15,repin[14]);
					int j = ps.executeUpdate();
					ps.close();
     
                    stmt.close();
					connMgr.freeConnection("xavier",con);
					error="...";
					}catch(Exception e){error=(e.toString());}
return error;	
}

public String ConsultancyUpdate(int id)
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
			StringTokenizer st1 = new StringTokenizer(repin[4],"/");
		    d1=st1.nextToken();
		    m1=st1.nextToken();
		    y1=st1.nextToken();
			date1=m1+"/"+d1+"/"+y1;

			StringTokenizer st2 = new StringTokenizer(repin[5],"/");
		    d1=st2.nextToken();
		    m1=st2.nextToken();
		    y1=st2.nextToken();
			date2=m1+"/"+d1+"/"+y1;

			StringTokenizer st3 = new StringTokenizer(repin[9],"/");
		    d1=st3.nextToken();
		    m1=st3.nextToken();
		    y1=st3.nextToken();
			date3=m1+"/"+d1+"/"+y1;
}
catch(Exception e){}			
try{
	i1=Integer.parseInt(repin[10]);
	i2=Integer.parseInt(repin[11]);
}catch(Exception e){}
					    
                  sql="update apiconsultancy set type=?,title=?,fdate=?,tdate=?,status=?,agency=?,orderno=?,sanctioneddate=?,sanctionedamt=?,receivedamt=?,outcome=? where id=? and staffid=?";
					ps=con.prepareStatement(sql);

					ps.setString(1,repin[2]);
					ps.setString(2,repin[3]);
					ps.setString(3,date1);
					ps.setString(4,date2);
					ps.setString(5,repin[6]);
					ps.setString(6,repin[7]);
					ps.setString(7,repin[8]);
					ps.setString(8,date3);
					ps.setInt(9,i1);
					ps.setInt(10,i2);
					ps.setString(11,repin[12]);

					ps.setInt(12,id);
					ps.setString(13,repin[1]);
					int i = ps.executeUpdate();
					ps.close();
                    stmt.close();
					connMgr.freeConnection("xavier",con);
					error="success";
					}catch(Exception e){error=(e.toString());
					}
					
		return error;
}

public String ConsultancyUpload(int id,int jk)
{
try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;
					    PreparedStatement ps;
if (jk==1)			    
                  sql="update apiconsultancy set fname1=? where id=? and staffid=?";
else
                  sql="update apiconsultancy set fname2=? where id=? and staffid=?";
					ps=con.prepareStatement(sql);
					ps.setString(1,repin[13]);
					ps.setInt(2,id);
					ps.setString(3,repin[1]);
					int i = ps.executeUpdate();
					ps.close();
                    stmt.close();
					connMgr.freeConnection("xavier",con);
					error="success";
					}catch(Exception e){error=sql+repin[11]+id+repin[1]+(e.toString());		}
return error;
}


public String ConsultancyDel(int id)
{

try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;
					    PreparedStatement ps;
                  sql="delete from apiconsultancy where id=? and staffid=?";
					ps=con.prepareStatement(sql);
					ps.setInt(1,id);
					ps.setString(2,repin[1]);
					int i = ps.executeUpdate();
					ps.close();
                    stmt.close();
					connMgr.freeConnection("xavier",con);
					error="success";
					}catch(Exception e){error=(e.toString());
					}
					
		return error;
}

public String ConsultancyList()
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
sql="select id,staffid,type,title, "
+"substring(('000'+ltrim(rtrim(str(day(fdate))))), len('000'+ltrim(rtrim(str(day(fdate)))))-1,2)+'/'+  substring(('000'+ltrim(rtrim(str(month(fdate))))), "
+"len('000'+ltrim(rtrim(str(month(fdate)))))-1,2)+'/'+ ltrim(rtrim(str(year(fdate)))), "
+"substring(('000'+ltrim(rtrim(str(day(tdate))))), len('000'+ltrim(rtrim(str(day(tdate)))))-1,2)+'/'+  substring(('000'+ltrim(rtrim(str(month(tdate))))), "
+"len('000'+ltrim(rtrim(str(month(tdate)))))-1,2)+'/'+ ltrim(rtrim(str(year(tdate)))), "
+"status,agency,orderno, "
+"substring(('000'+ltrim(rtrim(str(day(sanctioneddate))))), len('000'+ltrim(rtrim(str(day(sanctioneddate)))))-1,2)+'/'+  substring(('000'+ltrim(rtrim(str(month(sanctioneddate))))), "
+"len('000'+ltrim(rtrim(str(month(sanctioneddate)))))-1,2)+'/'+ ltrim(rtrim(str(year(sanctioneddate)))), "
+"sanctionedamt,receivedamt,outcome,fname1,fname2 from apiconsultancy where staffid='"+repin[1]+"' order by fdate desc";

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


public String ConsultancyView()
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
sql="select id,staffid,type,title, "
+"substring(('000'+ltrim(rtrim(str(day(fdate))))), len('000'+ltrim(rtrim(str(day(fdate)))))-1,2)+'/'+  substring(('000'+ltrim(rtrim(str(month(fdate))))), "
+"len('000'+ltrim(rtrim(str(month(fdate)))))-1,2)+'/'+ ltrim(rtrim(str(year(fdate)))), "
+"substring(('000'+ltrim(rtrim(str(day(tdate))))), len('000'+ltrim(rtrim(str(day(tdate)))))-1,2)+'/'+  substring(('000'+ltrim(rtrim(str(month(tdate))))), "
+"len('000'+ltrim(rtrim(str(month(tdate)))))-1,2)+'/'+ ltrim(rtrim(str(year(tdate)))), "
+"status,agency,orderno, "
+"substring(('000'+ltrim(rtrim(str(day(sanctioneddate))))), len('000'+ltrim(rtrim(str(day(sanctioneddate)))))-1,2)+'/'+  substring(('000'+ltrim(rtrim(str(month(sanctioneddate))))), "
+"len('000'+ltrim(rtrim(str(month(sanctioneddate)))))-1,2)+'/'+ ltrim(rtrim(str(year(sanctioneddate)))), "
+"sanctionedamt,receivedamt,outcome,fname1,fname2 from apiconsultancy where staffid='"+repin[1]+"' and id="+repin[0];

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


public String PatentInsert()
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
			StringTokenizer st1 = new StringTokenizer(repin[6],"/");
		    d1=st1.nextToken();
		    m1=st1.nextToken();
		    y1=st1.nextToken();
			date1=m1+"/"+d1+"/"+y1;

			StringTokenizer st2 = new StringTokenizer(repin[7],"/");
		    d1=st2.nextToken();
		    m1=st2.nextToken();
		    y1=st2.nextToken();
			date2=m1+"/"+d1+"/"+y1;


}
catch(Exception e){}			

			
					sql="select max(id)+1 from apipatent";
					rs = stmt.executeQuery(sql);
					if(rs.next())     { 	cid=rs.getInt(1); 	    }
					rs.close();
				
                    sql="insert into  apipatent values(?,?,?,?,?,?,?,?,?,?,?)";
					ps=con.prepareStatement(sql);
					ps.setInt(1,cid);
					ps.setString(2,repin[1]);
					ps.setString(3,repin[2]);
					ps.setString(4,repin[3]);
					ps.setString(5,repin[4]);
					ps.setString(6,repin[5]);
					ps.setString(7,date1);
					ps.setString(8,date2);
					ps.setString(9,repin[8]);
					ps.setString(10,repin[9]);
					ps.setString(11,repin[10]);
					int j = ps.executeUpdate();
					ps.close();
                    stmt.close();
					connMgr.freeConnection("xavier",con);
					error="...";
					}catch(Exception e){error=(e.toString());}
return error;	
}

public String PatentUpdate(int id)
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
			StringTokenizer st1 = new StringTokenizer(repin[6],"/");
		    d1=st1.nextToken();
		    m1=st1.nextToken();
		    y1=st1.nextToken();
			date1=m1+"/"+d1+"/"+y1;

			StringTokenizer st2 = new StringTokenizer(repin[7],"/");
		    d1=st2.nextToken();
		    m1=st2.nextToken();
		    y1=st2.nextToken();
			date2=m1+"/"+d1+"/"+y1;

}
catch(Exception e){}			
					    
                  sql="update apipatent set authors=?,filed=?,agency=?,intornat=?,fdate=?,sdate=?,outcome=?,document=? where id=? and staffid=?";
					ps=con.prepareStatement(sql);

					ps.setString(1,repin[2]);
					ps.setString(2,repin[3]);
					ps.setString(3,repin[4]);
					ps.setString(4,repin[5]);
					ps.setString(5,date1);
					ps.setString(6,date2);
					ps.setString(7,repin[8]);
					ps.setString(8,repin[9]);

					ps.setInt(9,id);
					ps.setString(10,repin[1]);
					int i = ps.executeUpdate();
					ps.close();
                    stmt.close();
					connMgr.freeConnection("xavier",con);
					error="success";
					}catch(Exception e){error=(e.toString());
					}
					
		return error;
}

public String PatentUpload(int id)
{
try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;
					    PreparedStatement ps;
                  sql="update apipatent set fname1=? where id=? and staffid=?";
					ps=con.prepareStatement(sql);
					ps.setString(1,repin[10]);
					ps.setInt(2,id);
					ps.setString(3,repin[1]);
					int i = ps.executeUpdate();
					ps.close();
                    stmt.close();
					connMgr.freeConnection("xavier",con);
					error="success";
					}catch(Exception e){error=sql+repin[11]+id+repin[1]+(e.toString());		}
return error;
}


public String PatentDel(int id)
{

try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;
					    PreparedStatement ps;
                  sql="delete from apipatent where id=? and staffid=?";
					ps=con.prepareStatement(sql);
					ps.setInt(1,id);
					ps.setString(2,repin[1]);
					int i = ps.executeUpdate();
					ps.close();
                    stmt.close();
					connMgr.freeConnection("xavier",con);
					error="success";
					}catch(Exception e){error=(e.toString());
					}
					
		return error;
}

public String PatentList()
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
sql="select id,staffid,authors,filed,agency,intornat, "
+"substring(('000'+ltrim(rtrim(str(day(fdate))))), len('000'+ltrim(rtrim(str(day(fdate)))))-1,2)+'/'+  substring(('000'+ltrim(rtrim(str(month(fdate))))), "
+"len('000'+ltrim(rtrim(str(month(fdate)))))-1,2)+'/'+ ltrim(rtrim(str(year(fdate)))), "
+"substring(('000'+ltrim(rtrim(str(day(sdate))))), len('000'+ltrim(rtrim(str(day(sdate)))))-1,2)+'/'+  substring(('000'+ltrim(rtrim(str(month(sdate))))), "
+"len('000'+ltrim(rtrim(str(month(sdate)))))-1,2)+'/'+ ltrim(rtrim(str(year(sdate)))), "
+"outcome,document,fname1 from apipatent where staffid='"+repin[1]+"' order by fdate desc";

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


public String PatentView()
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
sql="select id,staffid,authors,filed,agency,intornat, "
+"substring(('000'+ltrim(rtrim(str(day(fdate))))), len('000'+ltrim(rtrim(str(day(fdate)))))-1,2)+'/'+  substring(('000'+ltrim(rtrim(str(month(fdate))))), "
+"len('000'+ltrim(rtrim(str(month(fdate)))))-1,2)+'/'+ ltrim(rtrim(str(year(fdate)))), "
+"substring(('000'+ltrim(rtrim(str(day(sdate))))), len('000'+ltrim(rtrim(str(day(sdate)))))-1,2)+'/'+  substring(('000'+ltrim(rtrim(str(month(sdate))))), "
+"len('000'+ltrim(rtrim(str(month(sdate)))))-1,2)+'/'+ ltrim(rtrim(str(year(sdate)))), "
+"outcome,document,fname1 from apipatent where staffid='"+repin[1]+"' and id="+repin[0];

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


public String BooksInsert()
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

try{
	i1=Integer.parseInt(repin[8]);
	i2=Integer.parseInt(repin[13]);
	if(i2<1) i2=1;
	if(i2>12) i2=1;
	i3=Integer.parseInt(repin[14]);
	i4=Integer.parseInt(repin[15]);
}catch(Exception e){}
			
					sql="select max(id)+1 from apibooks";
					rs = stmt.executeQuery(sql);
					if(rs.next())     { 	cid=rs.getInt(1); 	    }
					rs.close();
				
                    sql="insert into  apibooks values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
					ps=con.prepareStatement(sql);
					ps.setInt(1,cid);
					ps.setString(2,repin[1]);
					ps.setString(3,repin[2]);
					ps.setString(4,repin[3]);
					ps.setString(5,repin[4]);
					ps.setString(6,repin[5]);
					ps.setString(7,repin[6]);
					ps.setString(8,repin[7]);
					ps.setInt(9,i1);
					ps.setString(10,repin[9]);
					ps.setString(11,repin[10]);
					ps.setString(12,repin[11]);
					ps.setString(13,repin[12]);
					ps.setInt(14,i2);
					ps.setInt(15,i3);
					ps.setInt(16,i4);
					ps.setString(17,repin[16]);
					int j = ps.executeUpdate();
					ps.close();
                    stmt.close();
					connMgr.freeConnection("xavier",con);
					error="...";
					}catch(Exception e){error=(e.toString());}
return error;	
}

public String BooksUpdate(int id)
{
try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;
					    PreparedStatement ps;
try{
	i1=Integer.parseInt(repin[8]);
	i2=Integer.parseInt(repin[13]);
	i3=Integer.parseInt(repin[14]);
	i4=Integer.parseInt(repin[15]);
	if(i2<1) i2=1;
	if(i2>12) i2=1;

}catch(Exception e){}
					    
                  sql="update apibooks set category=?,type=?,title=?,authors=?,authortype=?,affiliation=?,pages=?,isbn=?,language=?,publisher=?,country=?,monthofpub=?,yearofpub=?,edition=? where id=? and staffid=?";
					ps=con.prepareStatement(sql);
					ps.setString(1,repin[2]);
					ps.setString(2,repin[3]);
					ps.setString(3,repin[4]);
					ps.setString(4,repin[5]);
					ps.setString(5,repin[6]);
					ps.setString(6,repin[7]);
					ps.setInt(7,i1);
					ps.setString(8,repin[9]);
					ps.setString(9,repin[10]);
					ps.setString(10,repin[11]);
					ps.setString(11,repin[12]);
					ps.setInt(12,i2);
					ps.setInt(13,i3);
					ps.setInt(14,i4);
					ps.setInt(15,id);
					ps.setString(16,repin[1]);
					int i = ps.executeUpdate();
					ps.close();
                    stmt.close();
					connMgr.freeConnection("xavier",con);
					error="success";
					}catch(Exception e){error=(e.toString());
					}
					
		return error;
}

public String BooksUpload(int id)
{
try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;
					    PreparedStatement ps;
                  sql="update apibooks set fname1=? where id=? and staffid=?";
					ps=con.prepareStatement(sql);
					ps.setString(1,repin[16]);
					ps.setInt(2,id);
					ps.setString(3,repin[1]);
					int i = ps.executeUpdate();
					ps.close();
                    stmt.close();
					connMgr.freeConnection("xavier",con);
					error="success";
					}catch(Exception e){error=sql+repin[11]+id+repin[1]+(e.toString());		}
return error;
}


public String BooksDel(int id)
{

try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;
					    PreparedStatement ps;
                  sql="delete from apibooks where id=? and staffid=?";
					ps=con.prepareStatement(sql);
					ps.setInt(1,id);
					ps.setString(2,repin[1]);
					int i = ps.executeUpdate();
					ps.close();
                    stmt.close();
					connMgr.freeConnection("xavier",con);
					error="success";
					}catch(Exception e){error=(e.toString());
					}
					
		return error;
}

public String BooksList()
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
sql="select id,staffid,category,type,title,authors,authortype,affiliation,pages,isbn,language,publisher,country,substring(DATENAME(m, str( CASE WHEN monthofpub in(0) THEN 1 ELSE monthofpub END) + '/1/2017'),1,3),yearofpub,edition,fname1 from apibooks where staffid='"+repin[1]+"' order by yearofpub desc ";

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


public String BooksView()
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
sql="select id,staffid,category,type,title,authors,authortype,affiliation,pages,isbn,language,publisher,country,substring(DATENAME(m, str( CASE WHEN monthofpub in(0) THEN 1 ELSE monthofpub END) + '/1/2017'),1,3),yearofpub,edition,fname1 from apibooks where staffid='"+repin[1]+"' and id="+repin[0];

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



public String ActivitiesInsert()
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
try{
	i1=Integer.parseInt(repin[7]);
	i2=Integer.parseInt(repin[8]);
}catch(Exception e){}
			
					sql="select max(id)+1 from apiactivities";
					rs = stmt.executeQuery(sql);
					if(rs.next())     { 	cid=rs.getInt(1); 	    }
					rs.close();
			
                    sql="insert into  apiactivities values(?,?,?,?,?,?,?,?,?,?)";
					ps=con.prepareStatement(sql);
					ps.setInt(1,cid);
					ps.setString(2,repin[1]);
					ps.setString(3,repin[2]);
					ps.setString(4,repin[3]);
					ps.setString(5,repin[4]);
					ps.setString(6,repin[5]);
					ps.setString(7,repin[6]);
					ps.setInt(8,i1);
					ps.setInt(9,i2);
					ps.setString(10,repin[9]);
					
					int j = ps.executeUpdate();
					ps.close();
     
                    stmt.close();
					connMgr.freeConnection("xavier",con);
					error="...";
					}catch(Exception e){error=(e.toString());}
return error;	
}

public String ActivitiesUpdate(int id)
{
try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;
					    PreparedStatement ps;
try{
	i1=Integer.parseInt(repin[7]);
	i2=Integer.parseInt(repin[8]);
}catch(Exception e){}
					    
                  sql="update apiactivities set academicyear=?,ugpg=?,studentname=?,title=?,university=?,monthofp=?,yearofp=? where id=? and staffid=?";
					ps=con.prepareStatement(sql);
					ps.setString(1,repin[2]);
					ps.setString(2,repin[3]);
					ps.setString(3,repin[4]);
					ps.setString(4,repin[5]);
					ps.setString(5,repin[6]);
					ps.setInt(6,i1);
					ps.setInt(7,i2);
					ps.setInt(8,id);
					ps.setString(9,repin[1]);
					int i = ps.executeUpdate();
					ps.close();
                    stmt.close();
					connMgr.freeConnection("xavier",con);
					error="success";
					}catch(Exception e){error=(e.toString());
					}
					
		return error;
}

public String ActivitiesUpload(int id)
{
try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;
					    PreparedStatement ps;
			    
                  sql="update apiactivities set fname1=? where id=? and staffid=?";
					ps=con.prepareStatement(sql);
					ps.setString(1,repin[9]);
					ps.setInt(2,id);
					ps.setString(3,repin[1]);
					int i = ps.executeUpdate();
					ps.close();
                    stmt.close();
					connMgr.freeConnection("xavier",con);
					error="success";
					}catch(Exception e){error=sql+repin[11]+id+repin[1]+(e.toString());		}
return error;
}


public String ActivitiesDel(int id)
{

try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;
					    PreparedStatement ps;
                  sql="delete from apiactivities where id=? and staffid=?";
					ps=con.prepareStatement(sql);
					ps.setInt(1,id);
					ps.setString(2,repin[1]);
					int i = ps.executeUpdate();
					ps.close();
                    stmt.close();
					connMgr.freeConnection("xavier",con);
					error="success";
					}catch(Exception e){error=(e.toString());
					}
					
		return error;
}

public String ActivitiesList()
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
sql="select id,staffid,academicyear,ugpg,studentname,title,university, "
+"substring(DATENAME(m, str(monthofp) + '/1/2017'),1,3),yearofp,fname1 from apiactivities  where staffid='"+repin[1]+"' order by yearofp desc";

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


public String ActivitiesView()
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
sql="select * from apiactivities where staffid='"+repin[1]+"' and id="+repin[0];

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

public String AwardsInsert()
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
			StringTokenizer st1 = new StringTokenizer(repin[6],"/");
		    d1=st1.nextToken();
		    m1=st1.nextToken();
		    y1=st1.nextToken();
			date1=m1+"/"+d1+"/"+y1;

}
catch(Exception e){}			

			
					sql="select max(id)+1 from apiawards";
					rs = stmt.executeQuery(sql);
					if(rs.next())     { 	cid=rs.getInt(1); 	    }
					rs.close();
					
				
                    sql="insert into  apiawards values(?,?,?,?,?,?,?,?)";
					ps=con.prepareStatement(sql);
					ps.setInt(1,cid);
					ps.setString(2,repin[1]);
					ps.setString(3,repin[2]);
					ps.setString(4,repin[3]);
					ps.setString(5,repin[4]);
					ps.setString(6,repin[5]);
					ps.setString(7,date1);
					ps.setString(8,repin[7]);
					int j = ps.executeUpdate();
					ps.close();
     
                    stmt.close();
					connMgr.freeConnection("xavier",con);
					error="...";
					}catch(Exception e){error=(e.toString());}
return error;	
}

public String AwardsUpdate(int id)
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
			StringTokenizer st1 = new StringTokenizer(repin[6],"/");
		    d1=st1.nextToken();
		    m1=st1.nextToken();
		    y1=st1.nextToken();
			date1=m1+"/"+d1+"/"+y1;

}
catch(Exception e){}			
					    
                  sql="update apiawards set type=?,details=?,sponsor=?,intornat=?,awarddate=? where id=? and staffid=?";
					ps=con.prepareStatement(sql);
					ps.setString(1,repin[2]);
					ps.setString(2,repin[3]);
					ps.setString(3,repin[4]);
					ps.setString(4,repin[5]);
					ps.setString(5,date1);

					ps.setInt(6,id);
					ps.setString(7,repin[1]);
					int i = ps.executeUpdate();
					ps.close();
                    stmt.close();
					connMgr.freeConnection("xavier",con);
					error="success";
					}catch(Exception e){error=(e.toString());
					}
					
		return error;
}

public String AwardsUpload(int id)
{
try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;
					    PreparedStatement ps;
			    
                  sql="update apiawards set fname1=? where id=? and staffid=?";
					ps=con.prepareStatement(sql);
					ps.setString(1,repin[7]);
					ps.setInt(2,id);
					ps.setString(3,repin[1]);
					int i = ps.executeUpdate();
					ps.close();
                    stmt.close();
					connMgr.freeConnection("xavier",con);
					error="success";
					}catch(Exception e){error=sql+repin[11]+id+repin[1]+(e.toString());		}
return error;
}


public String AwardsDel(int id)
{

try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;
					    PreparedStatement ps;
                  sql="delete from apiawards where id=? and staffid=?";
					ps=con.prepareStatement(sql);
					ps.setInt(1,id);
					ps.setString(2,repin[1]);
					int i = ps.executeUpdate();
					ps.close();
                    stmt.close();
					connMgr.freeConnection("xavier",con);
					error="success";
					}catch(Exception e){error=(e.toString());
					}
					
		return error;
}


public String AwardsList()
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
sql="select id,staffid,type,details,sponsor,intornat, "
+"substring(('000'+ltrim(rtrim(str(day(awarddate))))), len('000'+ltrim(rtrim(str(day(awarddate)))))-1,2)+'/'+  substring(('000'+ltrim(rtrim(str(month(awarddate))))), "
+"len('000'+ltrim(rtrim(str(month(awarddate)))))-1,2)+'/'+ ltrim(rtrim(str(year(awarddate)))),fname1 from apiawards where staffid='"+repin[1]+"' order by awarddate desc";

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


public String AwardsView()
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
sql="select id,staffid,type,details,sponsor,intornat, "
+"substring(('000'+ltrim(rtrim(str(day(awarddate))))), len('000'+ltrim(rtrim(str(day(awarddate)))))-1,2)+'/'+  substring(('000'+ltrim(rtrim(str(month(awarddate))))), "
+"len('000'+ltrim(rtrim(str(month(awarddate)))))-1,2)+'/'+ ltrim(rtrim(str(year(awarddate)))),fname1 from apiawards where staffid='"+repin[1]+"' and id="+repin[0];

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

public String OrganizedInsert()
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
			StringTokenizer st1 = new StringTokenizer(repin[8],"/");
		    d1=st1.nextToken();
		    m1=st1.nextToken();
		    y1=st1.nextToken();
			date1=m1+"/"+d1+"/"+y1;

			StringTokenizer st2 = new StringTokenizer(repin[9],"/");
		    d1=st2.nextToken();
		    m1=st2.nextToken();
		    y1=st2.nextToken();
			date2=m1+"/"+d1+"/"+y1;
}
catch(Exception e){}			

			
					sql="select max(id)+1 from apiorganized";
					rs = stmt.executeQuery(sql);
					if(rs.next())     { 	cid=rs.getInt(1); 	    }
					rs.close();
				
			
                    sql="insert into  apiorganized values(?,?,?,?,?,?,?,?,?,?,?)";
					ps=con.prepareStatement(sql);
					ps.setInt(1,cid);
					ps.setString(2,repin[1]);
					ps.setString(3,repin[2]);
					ps.setString(4,repin[3]);
					ps.setString(5,repin[4]);
					ps.setString(6,repin[5]);
					ps.setString(7,repin[6]);
					ps.setString(8,repin[7]);
					ps.setString(9,date1);
					ps.setString(10,date2);
					ps.setString(11,repin[10]);
					int j = ps.executeUpdate();
					ps.close();
     
                    stmt.close();
					connMgr.freeConnection("xavier",con);
					error="...";
					}catch(Exception e){error=(e.toString());}
return error;	
}

public String OrganizedUpdate(int id)
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
					    
			StringTokenizer st1 = new StringTokenizer(repin[8],"/");
		    d1=st1.nextToken();
		    m1=st1.nextToken();
		    y1=st1.nextToken();
			date1=m1+"/"+d1+"/"+y1;

			StringTokenizer st2 = new StringTokenizer(repin[9],"/");
		    d1=st2.nextToken();
		    m1=st2.nextToken();
		    y1=st2.nextToken();
			date2=m1+"/"+d1+"/"+y1;
}
catch(Exception e){}			
					    
                  sql="update apiorganized set type=?,academicyear=?,programme=?,agency=?,role=?,intornat=?,fdate=?,tdate=? where id=? and staffid=?";
					ps=con.prepareStatement(sql);
					ps.setString(1,repin[2]);
					ps.setString(2,repin[3]);
					ps.setString(3,repin[4]);
					ps.setString(4,repin[5]);
					ps.setString(5,repin[6]);
					ps.setString(6,repin[7]);
					ps.setString(7,date1);
					ps.setString(8,date2);

					ps.setInt(9,id);
					ps.setString(10,repin[1]);
					int i = ps.executeUpdate();
					ps.close();
                    stmt.close();
					connMgr.freeConnection("xavier",con);
					error="success";
					}catch(Exception e){error=(e.toString());
					}
					
		return error;
}

public String OrganizedUpload(int id)
{
try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;
					    PreparedStatement ps;
			    
                  sql="update apiorganized set fname1=? where id=? and staffid=?";
					ps=con.prepareStatement(sql);
					ps.setString(1,repin[10]);
					ps.setInt(2,id);
					ps.setString(3,repin[1]);
					int i = ps.executeUpdate();
					ps.close();
                    stmt.close();
					connMgr.freeConnection("xavier",con);
					error="success";
					}catch(Exception e){error=sql+repin[11]+id+repin[1]+(e.toString());		}
return error;
}


public String OrganizedDel(int id)
{

try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;
					    PreparedStatement ps;
                  sql="delete from apiorganized where id=? and staffid=?";
					ps=con.prepareStatement(sql);
					ps.setInt(1,id);
					ps.setString(2,repin[1]);
					int i = ps.executeUpdate();
					ps.close();
                    stmt.close();
					connMgr.freeConnection("xavier",con);
					error="success";
					}catch(Exception e){error=(e.toString());
					}
					
		return error;
}

public String OrganizedList()
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
sql="select id,staffid,type,academicyear,programme,agency,role,intornat, "
+"substring(('000'+ltrim(rtrim(str(day(fdate))))), len('000'+ltrim(rtrim(str(day(fdate)))))-1,2)+'/'+  substring(('000'+ltrim(rtrim(str(month(fdate))))), "
+"len('000'+ltrim(rtrim(str(month(fdate)))))-1,2)+'/'+ ltrim(rtrim(str(year(fdate)))), "
+"substring(('000'+ltrim(rtrim(str(day(tdate))))), len('000'+ltrim(rtrim(str(day(tdate)))))-1,2)+'/'+  substring(('000'+ltrim(rtrim(str(month(tdate))))), "
+"len('000'+ltrim(rtrim(str(month(tdate)))))-1,2)+'/'+ ltrim(rtrim(str(year(tdate)))), "
+"fname1 from apiorganized where staffid='"+repin[1]+"' order by fdate desc";

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


public String OrganizedView()
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
sql="select id,staffid,type,academicyear,programme,agency,role,intornat, "
+"substring(('000'+ltrim(rtrim(str(day(fdate))))), len('000'+ltrim(rtrim(str(day(fdate)))))-1,2)+'/'+  substring(('000'+ltrim(rtrim(str(month(fdate))))), "
+"len('000'+ltrim(rtrim(str(month(fdate)))))-1,2)+'/'+ ltrim(rtrim(str(year(fdate)))), "
+"substring(('000'+ltrim(rtrim(str(day(tdate))))), len('000'+ltrim(rtrim(str(day(tdate)))))-1,2)+'/'+  substring(('000'+ltrim(rtrim(str(month(tdate))))), "
+"len('000'+ltrim(rtrim(str(month(tdate)))))-1,2)+'/'+ ltrim(rtrim(str(year(tdate)))), "
+"fname1 from apiorganized where staffid='"+repin[1]+"' and id="+repin[0];

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


public String AttendedInsert()
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
			StringTokenizer st1 = new StringTokenizer(repin[8],"/");
		    d1=st1.nextToken();
		    m1=st1.nextToken();
		    y1=st1.nextToken();
			date1=m1+"/"+d1+"/"+y1;

			StringTokenizer st2 = new StringTokenizer(repin[9],"/");
		    d1=st2.nextToken();
		    m1=st2.nextToken();
		    y1=st2.nextToken();
			date2=m1+"/"+d1+"/"+y1;
}
catch(Exception e){}			

			
					sql="select max(id)+1 from apiattended";
					rs = stmt.executeQuery(sql);
					if(rs.next())     { 	cid=rs.getInt(1); 	    }
					rs.close();
				
			
                    sql="insert into  apiattended values(?,?,?,?,?,?,?,?,?,?,?)";
					ps=con.prepareStatement(sql);
					ps.setInt(1,cid);
					ps.setString(2,repin[1]);
					ps.setString(3,repin[2]);
					ps.setString(4,repin[3]);
					ps.setString(5,repin[4]);
					ps.setString(6,repin[5]);
					ps.setString(7,repin[6]);
					ps.setString(8,repin[7]);
					ps.setString(9,date1);
					ps.setString(10,date2);
					ps.setString(11,repin[10]);
					int j = ps.executeUpdate();
					ps.close();
     
                    stmt.close();
					connMgr.freeConnection("xavier",con);
					error="...";
					}catch(Exception e){error=(e.toString());}
return error;	
}

public String AttendedUpdate(int id)
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
					    
			StringTokenizer st1 = new StringTokenizer(repin[8],"/");
		    d1=st1.nextToken();
		    m1=st1.nextToken();
		    y1=st1.nextToken();
			date1=m1+"/"+d1+"/"+y1;

			StringTokenizer st2 = new StringTokenizer(repin[9],"/");
		    d1=st2.nextToken();
		    m1=st2.nextToken();
		    y1=st2.nextToken();
			date2=m1+"/"+d1+"/"+y1;
}
catch(Exception e){}			
					    
                  sql="update apiattended set type=?,academicyear=?,programme=?,venue=?,agency=?,intornat=?,fdate=?,tdate=? where id=? and staffid=?";
					ps=con.prepareStatement(sql);
					ps.setString(1,repin[2]);
					ps.setString(2,repin[3]);
					ps.setString(3,repin[4]);
					ps.setString(4,repin[5]);
					ps.setString(5,repin[6]);
					ps.setString(6,repin[7]);
					ps.setString(7,date1);
					ps.setString(8,date2);

					ps.setInt(9,id);
					ps.setString(10,repin[1]);
					int i = ps.executeUpdate();
					ps.close();
                    stmt.close();
					connMgr.freeConnection("xavier",con);
					error="success";
					}catch(Exception e){error=(e.toString());
					}
					
		return error;
}

public String AttendedUpload(int id)
{
try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;
					    PreparedStatement ps;
			    
                  sql="update apiattended set fname1=? where id=? and staffid=?";
					ps=con.prepareStatement(sql);
					ps.setString(1,repin[10]);
					ps.setInt(2,id);
					ps.setString(3,repin[1]);
					int i = ps.executeUpdate();
					ps.close();
                    stmt.close();
					connMgr.freeConnection("xavier",con);
					error="success";
					}catch(Exception e){error=sql+repin[11]+id+repin[1]+(e.toString());		}
return error;
}


public String AttendedDel(int id)
{

try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;
					    PreparedStatement ps;
                  sql="delete from apiattended where id=? and staffid=?";
					ps=con.prepareStatement(sql);
					ps.setInt(1,id);
					ps.setString(2,repin[1]);
					int i = ps.executeUpdate();
					ps.close();
                    stmt.close();
					connMgr.freeConnection("xavier",con);
					error="success";
					}catch(Exception e){error=(e.toString());
					}
					
		return error;
}

public String AttendedList()
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
sql="select id,staffid,type,academicyear,programme,venue,agency,intornat, "
+"substring(('000'+ltrim(rtrim(str(day(fdate))))), len('000'+ltrim(rtrim(str(day(fdate)))))-1,2)+'/'+  substring(('000'+ltrim(rtrim(str(month(fdate))))), "
+"len('000'+ltrim(rtrim(str(month(fdate)))))-1,2)+'/'+ ltrim(rtrim(str(year(fdate)))), "
+"substring(('000'+ltrim(rtrim(str(day(tdate))))), len('000'+ltrim(rtrim(str(day(tdate)))))-1,2)+'/'+  substring(('000'+ltrim(rtrim(str(month(tdate))))), "
+"len('000'+ltrim(rtrim(str(month(tdate)))))-1,2)+'/'+ ltrim(rtrim(str(year(tdate)))), "
+"fname1 from apiattended where staffid='"+repin[1]+"' order by fdate desc";

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


public String AttendedView()
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
sql="select id,staffid,type,academicyear,programme,venue,agency,intornat, "
+"substring(('000'+ltrim(rtrim(str(day(fdate))))), len('000'+ltrim(rtrim(str(day(fdate)))))-1,2)+'/'+  substring(('000'+ltrim(rtrim(str(month(fdate))))), "
+"len('000'+ltrim(rtrim(str(month(fdate)))))-1,2)+'/'+ ltrim(rtrim(str(year(fdate)))), "
+"substring(('000'+ltrim(rtrim(str(day(tdate))))), len('000'+ltrim(rtrim(str(day(tdate)))))-1,2)+'/'+  substring(('000'+ltrim(rtrim(str(month(tdate))))), "
+"len('000'+ltrim(rtrim(str(month(tdate)))))-1,2)+'/'+ ltrim(rtrim(str(year(tdate)))), "
+"fname1 from apiattended where staffid='"+repin[1]+"' and id="+repin[0];

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

public String ResearchcolInsert()
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

}
catch(Exception e){}			
try{
	i1=Integer.parseInt(repin[5]);
	i2=Integer.parseInt(repin[6]);
}catch(Exception e){}

			
					sql="select max(id)+1 from apiresearchcol";
					rs = stmt.executeQuery(sql);
					if(rs.next())     { 	cid=rs.getInt(1); 	    }
					rs.close();
				
			
                    sql="insert into  apiresearchcol values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
					ps=con.prepareStatement(sql);
					ps.setInt(1,cid);
					ps.setString(2,repin[1]);
					ps.setString(3,date1);
					ps.setString(4,repin[3]);
					ps.setString(5,repin[4]);
					ps.setString(6,repin[13]);
					ps.setInt(7,i1);
					ps.setInt(8,i2);
					ps.setString(9,repin[7]);
					ps.setString(10,repin[8]);
					ps.setString(11,repin[9]);
					ps.setString(12,repin[10]);
					ps.setString(13,repin[11]);
					ps.setString(14,repin[12]);
					int j = ps.executeUpdate();
					ps.close();
     
                    stmt.close();
					connMgr.freeConnection("xavier",con);
					error="...";
					}catch(Exception e){error=(e.toString());}
return error;	
}

public String ResearchcolUpdate(int id)
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

}
catch(Exception e){}			
try{
	i1=Integer.parseInt(repin[5]);
	i2=Integer.parseInt(repin[6]);
}catch(Exception e){}
					    
                  sql="update apiresearchcol set date=?,venue=?,area=?,noofstaff=?,noofstud=?,aim=?,problems=?,queries=?,conclusion=?,outcome=?,topic=? where id=? and staffid=?";
					ps=con.prepareStatement(sql);
					ps.setString(1,date1);
					ps.setString(2,repin[3]);
					ps.setString(3,repin[4]);
					ps.setInt(4,i1);
					ps.setInt(5,i2);
					ps.setString(6,repin[7]);
					ps.setString(7,repin[8]);
					ps.setString(8,repin[9]);
					ps.setString(9,repin[10]);
					ps.setString(10,repin[11]);
					ps.setString(11,repin[13]);
 					ps.setInt(12,id);
					ps.setString(13,repin[1]);
					int i = ps.executeUpdate();
					ps.close();
                    stmt.close();
					connMgr.freeConnection("xavier",con);
					error="success";
					}catch(Exception e){error=(e.toString());
					}
					
		return error;
}

public String ResearchcolUpload(int id)
{
try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;
					    PreparedStatement ps;
			    
                  sql="update apiresearchcol set fname1=? where id=? and staffid=?";
					ps=con.prepareStatement(sql);
					ps.setString(1,repin[12]);
					ps.setInt(2,id);
					ps.setString(3,repin[1]);
					int i = ps.executeUpdate();
					ps.close();
                    stmt.close();
					connMgr.freeConnection("xavier",con);
					error="success";
					}catch(Exception e){error=sql+repin[11]+id+repin[1]+(e.toString());		}
return error;
}


public String ResearchcolDel(int id)
{

try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;
					    PreparedStatement ps;
                  sql="delete from apiresearchcol where id=? and staffid=?";
					ps=con.prepareStatement(sql);
					ps.setInt(1,id);
					ps.setString(2,repin[1]);
					int i = ps.executeUpdate();
					ps.close();
                    stmt.close();
					connMgr.freeConnection("xavier",con);
					error="success";
					}catch(Exception e){error=(e.toString());
					}
					
		return error;
}

public String ResearchcolList()
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
sql="select id,staffid,substring(('000'+ltrim(rtrim(str(day(date))))), len('000'+ltrim(rtrim(str(day(date)))))-1,2)+'/'+  substring(('000'+ltrim(rtrim(str(month(date))))), "
+"len('000'+ltrim(rtrim(str(month(date)))))-1,2)+'/'+ ltrim(rtrim(str(year(date)))),venue,area,noofstaff,noofstud,aim,problems,queries,conclusion,outcome,fname1,topic "
+"from apiresearchcol where staffid='"+repin[1]+"' order by date desc";

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


public String ResearchcolView()
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
sql="select id,staff.staffid,substring(('000'+ltrim(rtrim(str(day(date))))), len('000'+ltrim(rtrim(str(day(date)))))-1,2)+'/'+  substring(('000'+ltrim(rtrim(str(month(date))))), "
+"len('000'+ltrim(rtrim(str(month(date)))))-1,2)+'/'+ ltrim(rtrim(str(year(date)))),venue,area,noofstaff,noofstud,aim,problems,queries,conclusion,outcome,fname1,name,cname,dname,topic "
+"from apiresearchcol,staff,department,staffdesignation where staff.staffid=apiresearchcol.staffid and department.dno=staff.dno and designation=slno and staff.staffid='"+repin[1]+"' and id="+repin[0];

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
public String ApiStatus()
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

sql="select staffid,name,sf,isnull(q, 0), isnull(e, 0), isnull(rs, 0), isnull(s, 0), isnull(ra, 0), isnull(j, 0), isnull(c, 0), isnull(fp, 0), isnull(co, 0) , isnull(pa, 0),isnull(bo, 0), isnull(aw, 0), isnull(org, 0), isnull(att, 0) "
+"from (select * from(select * from (select * from (select staffid,name,sf from staff,department where department.dno=staff.dno and active=1 and category='Teaching' ) a "
+"Left Outer join (select staffid c1,count(*) q from apiqualification group by staffid) b on a.staffid=b.c1) c "
+"Left Outer join (select staffid c2,count(*) e from apiexperience group by staffid) d on c.staffid=d.c2) e "
+"Left Outer join (select staffid c3,count(*) rs from apiresearch group by staffid) f on e.staffid=f.c3) g "
+"Left Outer join (select staffid c4,count(*) s from apischolar group by staffid) h on g.staffid=h.c4 "
+"Left Outer join (select staffid c5,count(*) ra from apiactivities group by staffid) i on g.staffid=i.c5 "
+"Left Outer join (select staffid c6,count(*) j from apijournal group by staffid) j on g.staffid=j.c6 "
+"Left Outer join (select staffid c7,count(*) c from apiconference group by staffid) k on g.staffid=k.c7 "
+"Left Outer join (select staffid c8,count(*) fp from apifunded group by staffid) l on g.staffid=l.c8  "
+"Left Outer join (select staffid c9,count(*) co from apiconsultancy group by staffid) m on g.staffid=m.c9 " 
+"Left Outer join (select staffid c10,count(*) pa from apipatent group by staffid) n on g.staffid=n.c10 "
+"Left Outer join (select staffid c14,count(*) bo from apibooks group by staffid) r on g.staffid=r.c14 "
+"Left Outer join (select staffid c11,count(*) aw from apiawards group by staffid) o on g.staffid=o.c11 "
+"Left Outer join (select staffid c12,count(*) org from apiorganized group by staffid) p on g.staffid=p.c12 "
+"Left Outer join (select staffid c13,count(*) att from apiattended group by staffid) q on g.staffid=q.c13 order by sf,staffid";
else
sql="select staffid,name,sf,isnull(q, 0), isnull(e, 0), isnull(rs, 0), isnull(s, 0), isnull(ra, 0), isnull(j, 0), isnull(c, 0), isnull(fp, 0), isnull(co, 0) , isnull(pa, 0),isnull(bo, 0), isnull(aw, 0), isnull(org, 0), isnull(att, 0) "
+"from (select * from(select * from (select * from (select staffid,name,sf from staff,department where department.dno=staff.dno and active=1 and category='Teaching' and staff.dno in ("+repin[0]+")) a "
+"Left Outer join (select staffid c1,count(*) q from apiqualification group by staffid) b on a.staffid=b.c1) c "
+"Left Outer join (select staffid c2,count(*) e from apiexperience group by staffid) d on c.staffid=d.c2) e "
+"Left Outer join (select staffid c3,count(*) rs from apiresearch group by staffid) f on e.staffid=f.c3) g "
+"Left Outer join (select staffid c4,count(*) s from apischolar group by staffid) h on g.staffid=h.c4 "
+"Left Outer join (select staffid c5,count(*) ra from apiactivities group by staffid) i on g.staffid=i.c5 "
+"Left Outer join (select staffid c6,count(*) j from apijournal group by staffid) j on g.staffid=j.c6 "
+"Left Outer join (select staffid c7,count(*) c from apiconference group by staffid) k on g.staffid=k.c7 "
+"Left Outer join (select staffid c8,count(*) fp from apifunded group by staffid) l on g.staffid=l.c8  "
+"Left Outer join (select staffid c9,count(*) co from apiconsultancy group by staffid) m on g.staffid=m.c9 " 
+"Left Outer join (select staffid c10,count(*) pa from apipatent group by staffid) n on g.staffid=n.c10 "
+"Left Outer join (select staffid c14,count(*) bo from apibooks group by staffid) r on g.staffid=r.c14 "
+"Left Outer join (select staffid c11,count(*) aw from apiawards group by staffid) o on g.staffid=o.c11 "
+"Left Outer join (select staffid c12,count(*) org from apiorganized group by staffid) p on g.staffid=p.c12 "
+"Left Outer join (select staffid c13,count(*) att from apiattended group by staffid) q on g.staffid=q.c13 order by sf,staffid";

				    

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

public String ApiReport(int select,String fd,String td)
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
}
catch(Exception e){}			

fd=date1;
td=date2;	
	
try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;
					    
sql="select staffid,ltrim(rtrim(type)),ltrim(rtrim(title)),ltrim(rtrim(authors)),ltrim(rtrim(affiliation)),ltrim(rtrim(authortype)),ltrim(rtrim(journalname)),ltrim(rtrim(publisher)),ltrim(rtrim(intornat)),"
+"ltrim(rtrim(issn)),ltrim(rtrim(doi)),ltrim(rtrim(monthofpub))+'-'+ltrim(rtrim(yearofpub)),ltrim(rtrim(volissuepage)),ltrim(rtrim(indexing)),impact,ltrim(rtrim(snip)),ltrim(rtrim(sjr)),citationcount,ltrim(rtrim(interdept)),fname1 from apijournal";

//if(select==1)

if(select==0)
sql="select staff.staffid,ltrim(rtrim(name)),ltrim(rtrim(type)),ltrim(rtrim(degree)),ltrim(rtrim(specialization)),ltrim(rtrim(department)),ltrim(rtrim(College)),ltrim(rtrim(university)),monthofpassing,yearofpassing, "
+"isnull(cast(cast(round(percentage,2) as decimal(10,2)) as varchar),0)+' ',grade,thesisdate,phddate, "
+"0,0,0,0,fname1,fname2 from apiqualification,staff where staff.staffid=apiqualification.staffid and dno in ("+dnoin+") "  
+"and cast(ltrim(str(monthofpassing))+'/1/'+ltrim(str(yearofpassing)) as datetime) between '"+fd+"' and '"+td+"' "
+"and type in  ("+typein+") and degree in  ("+intornatin+") order by "+order+",yearofpassing desc";

if(select==1)
sql="select staff.staffid,ltrim(rtrim(name)),ltrim(rtrim(type)),ltrim(rtrim(apiexperience.designation)),ltrim(rtrim(employer)),ltrim(rtrim(department)),ltrim(rtrim(jobdescription)),ltrim(rtrim(fdate)),ltrim(rtrim(tdate)),pay, "
+"0,0,0,0,0,0,0,0,0,fname1 from apiexperience,staff where staff.staffid=apiexperience.staffid and dno in ("+dnoin+") " 
+"and fdate  between '"+fd+"' and '"+td+"' "
+"and type in ("+typein+") order by "+order+",year(fdate) desc";

if(select==2)
sql="select staff.staffid,ltrim(rtrim(name)),ltrim(rtrim(researcharea)),ltrim(rtrim(supervisoryesno)),ltrim(rtrim(university)),ltrim(rtrim(referenceno)),ltrim(rtrim(faculty)),ltrim(rtrim(department)),scholarsdoing,scholarscompleted, "
+"0,0,0,0,0,0,0,0,0,fname1 from apiresearch,staff where staff.staffid=apiresearch.staffid and dno in ("+dnoin+") " 
+"and supervisoryesno in  ("+typein+")  order by "+order+" ";

if(select==3)
sql="select staff.staffid,ltrim(rtrim(name)),ltrim(rtrim(sregno)),ltrim(rtrim(sname)),ltrim(rtrim(degree)),ltrim(rtrim(university)),ltrim(rtrim(faculty)),ltrim(rtrim(department)),ltrim(rtrim(apischolar.category)),regyear,ltrim(rtrim(regsession)),ltrim(rtrim(areaofresearch)),ltrim(rtrim(status)), "
+"vdate,0,0,0,0,0,fname1 from apischolar,staff where staff.staffid=apischolar.staffid and dno in ("+dnoin+") " 
+"and cast('1/1/'+ltrim(str(regyear)) as datetime) between '"+fd+"' and '"+td+"' "
+"and degree in  ("+typein+")  and apischolar.category in  ("+intornatin+")  and status in  ("+authortypein+") order by "+order+" ";

if(select==4)
sql="select staff.staffid,ltrim(rtrim(name)),ltrim(rtrim(academicyear)),ltrim(rtrim(ugpg)),ltrim(rtrim(studentname)),ltrim(rtrim(title)),ltrim(rtrim(university)),monthofp,yearofp, "
+"0,0,0,0,0,0,0,0,0,0,fname1 from apiactivities,staff where staff.staffid=apiactivities.staffid and dno in ("+dnoin+") " 
+"and cast(ltrim(str(monthofp))+'/1/'+ltrim(str(yearofp)) as datetime) between '"+fd+"' and '"+td+"' "
+"and academicyear in ("+intornatin+") and ugpg in  ("+typein+") order by "+order+" ";

if(select==5)
sql="select staff.staffid,ltrim(rtrim(type)),ltrim(rtrim(title)),ltrim(rtrim(authors)),ltrim(rtrim(affiliation)),ltrim(rtrim(authortype)),ltrim(rtrim(journalname)),ltrim(rtrim(publisher)),ltrim(rtrim(intornat)),"
+"ltrim(rtrim(issn)),ltrim(rtrim(doi)),ltrim(rtrim(yearofpub)),ltrim(rtrim(volissuepage)),ltrim(rtrim(indexing)),impact,ltrim(rtrim(snip)),ltrim(rtrim(sjr)),citationcount,ltrim(rtrim(interdept)),fname1 from apijournal "
+",staff where staff.staffid=apijournal.staffid and dno in ("+dnoin+") "
+"and cast(ltrim(str(monthofpub))+'/1/'+ltrim(str(yearofpub)) as datetime) between '"+fd+"' and '"+td+"' "
+"and type in ("+typein+") and authortype in ("+authortypein+") and intornat in  ("+intornatin+")  and indexing in  ("+indexingin+") "+affiliationin+yearofpubin+" order by "+order+",yearofpub desc ";

if(select==6)
sql="select staff.staffid,ltrim(rtrim(venue)),ltrim(rtrim(title)),ltrim(rtrim(authors)),ltrim(rtrim(affiliation)),ltrim(rtrim(authortype)),ltrim(rtrim(conferencename)),ltrim(rtrim(fdate))+'-'+ltrim(rtrim(tdate)),ltrim(rtrim(intornat)),"
+"ltrim(rtrim(issn)),ltrim(rtrim(doi)),ltrim(rtrim(yearofpub)),ltrim(rtrim(volissuepage)),ltrim(rtrim(indexing)),0,0,0,citationcount,ltrim(rtrim(interdept)),fname1 from apiconference "
+",staff where staff.staffid=apiconference.staffid and dno in ("+dnoin+") "
+"and fdate between '"+fd+"' and '"+td+"' "
+"and  authortype in ("+authortypein+") and intornat in  ("+intornatin+")  and indexing in  ("+indexingin+") "+affiliationin+yearofpubin+" order by "+order+", yearofpub desc";

if(select==7)
sql="select staff.staffid,ltrim(rtrim(name)),ltrim(rtrim(type)),ltrim(rtrim(researcharea)),ltrim(rtrim(coinvestigator)),ltrim(rtrim(title)),ltrim(rtrim(apifunded.appdate)) appdate,ltrim(rtrim(fdate))+'-'+ltrim(rtrim(tdate)),ltrim(rtrim(status)), "
+"ltrim(rtrim(agency)),ltrim(rtrim(orderno)),ltrim(rtrim(sanctioneddate)),ltrim(rtrim(requiredamt)),ltrim(rtrim(sanctionedamt)),ltrim(rtrim(receivedamt)),0,0,outcome,fname1,fname2 from apifunded,staff "
+"where staff.staffid=apifunded.staffid and dno in ("+dnoin+") "
+"and fdate between '"+fd+"' and '"+td+"' "
+"and  status in ("+authortypein+") and type in  ("+typein+")  and year(apifunded.appdate)>="+yearofpub+" order by "+order+", apifunded.appdate desc";

if(select==8)
sql="select staff.staffid,ltrim(rtrim(name)),ltrim(rtrim(type)),ltrim(rtrim(title)),ltrim(rtrim(fdate)),ltrim(rtrim(tdate)),ltrim(rtrim(status)),ltrim(rtrim(agency)),ltrim(rtrim(orderno)), "
+"ltrim(rtrim(sanctioneddate)),ltrim(rtrim(sanctionedamt)),ltrim(rtrim(receivedamt)),0,0,0,0,0,outcome,fname1,fname2 from apiconsultancy,staff where staff.staffid=apiconsultancy.staffid and dno in ("+dnoin+") "
+"and fdate between '"+fd+"' and '"+td+"' "
+"and  status in ("+authortypein+") and type in  ("+typein+") order by "+order+", fdate desc";

if(select==9)
sql="select staff.staffid,ltrim(rtrim(name)),ltrim(rtrim(filed)),ltrim(rtrim(agency)),ltrim(rtrim(intornat)),ltrim(rtrim(fdate)),ltrim(rtrim(sdate)),ltrim(rtrim(outcome)),ltrim(rtrim(document)), "
+"0,0,0,0,0,0,0,0,0,0,fname1 from apipatent,staff where staff.staffid=apipatent.staffid and dno in ("+dnoin+") "
+"and fdate between '"+fd+"' and '"+td+"' "
+"and  intornat in ("+intornatin+") and filed in  ("+typein+") order by "+order+", fdate desc";

if(select==10)
sql="select staff.staffid,ltrim(rtrim(name)),ltrim(rtrim(apibooks.category)),ltrim(rtrim(type)),ltrim(rtrim(title)),ltrim(rtrim(authors)),ltrim(rtrim(authortype)),ltrim(rtrim(affiliation)),pages,ltrim(rtrim(isbn)),ltrim(rtrim(language)),ltrim(rtrim(publisher)),ltrim(rtrim(country)),monthofpub,yearofpub,edition, "
+"0,0,0,fname1 from apibooks,staff where staff.staffid=apibooks.staffid and dno in ("+dnoin+") "
+"and cast(ltrim(str(monthofpub))+'/1/'+ltrim(str(yearofpub)) as datetime) between '"+fd+"' and '"+td+"' "
+"and  apibooks.category in ("+indexingin+") and type in  ("+typein+") and authortype in ("+authortypein+") and language in ("+intornatin+") "+affiliationin+yearofpubin+" order by "+order+",yearofpub desc";

if(select==11)
sql="select staff.staffid,ltrim(rtrim(name)),ltrim(rtrim(type)),ltrim(rtrim(details)),ltrim(rtrim(sponsor)),ltrim(rtrim(intornat)),ltrim(rtrim(awarddate)),year(awarddate) yr, "
+"0,0,0,0,0,0,0,0,0,0,0,fname1 from apiawards,staff where staff.staffid=apiawards.staffid and dno in ("+dnoin+") "
+"and awarddate between '"+fd+"' and '"+td+"' "
+" and  intornat in ("+intornatin+") and type in("+typein+")  order by "+order+",awarddate desc";

if(select==12)
sql="select staff.staffid,ltrim(rtrim(name)),ltrim(rtrim(type)),ltrim(rtrim(academicyear)),ltrim(rtrim(programme)),ltrim(rtrim(agency)),ltrim(rtrim(role)),ltrim(rtrim(intornat)),ltrim(rtrim(fdate)),ltrim(rtrim(tdate)), "
+"0,0,0,0,0,0,0,0,0,fname1 from apiorganized,staff where staff.staffid=apiorganized.staffid and dno in ("+dnoin+") "
+"and fdate between '"+fd+"' and '"+td+"' "
+" and  intornat in ("+intornatin+") and type in("+typein+")  order by "+order+",year(fdate) desc";

if(select==13)
sql="select staff.staffid,ltrim(rtrim(name)),ltrim(rtrim(type)),ltrim(rtrim(academicyear)),ltrim(rtrim(programme)),ltrim(rtrim(venue)),ltrim(rtrim(agency)),ltrim(rtrim(intornat)),ltrim(rtrim(fdate)),ltrim(rtrim(tdate)), "
+"0,0,0,0,0,0,0,0,0,fname1 from apiattended,staff where staff.staffid=apiattended.staffid and dno in ("+dnoin+") "
+"and fdate between '"+fd+"' and '"+td+"' "
+" and  intornat in ("+intornatin+") and type in("+typein+")  order by "+order+",year(fdate) desc";

if(select==14)
sql="select staff.staffid,ltrim(rtrim(name)),ltrim(rtrim(date)),"
+"ltrim(rtrim(venue)),ltrim(rtrim(area)),ltrim(rtrim(noofstaff)),ltrim(rtrim(noofstud)),ltrim(rtrim(aim)),ltrim(rtrim(problems)),ltrim(rtrim(queries)), ltrim(rtrim(conclusion)),ltrim(rtrim(outcome)),"
+"0,0,0,0,0,0,0,fname1 from apiresearchcol,staff where staff.staffid=apiresearchcol.staffid and dno in ("+dnoin+") "
+"and date between '"+fd+"' and '"+td+"' order by "+order+",year(date) desc";

if(select==15)
sql="select staff.staffid,ltrim(rtrim(name)),ltrim(rtrim(title)), "
+"substring(('000'+ltrim(rtrim(str(day(fdate))))), len('000'+ltrim(rtrim(str(day(fdate)))))-1,2)+'/'+  substring(('000'+ltrim(rtrim(str(month(fdate))))), "
+"len('000'+ltrim(rtrim(str(month(fdate)))))-1,2)+'/'+ ltrim(rtrim(str(year(fdate)))), "
+"substring(('000'+ltrim(rtrim(str(day(tdate))))), len('000'+ltrim(rtrim(str(day(tdate)))))-1,2)+'/'+  substring(('000'+ltrim(rtrim(str(month(tdate))))), "
+"len('000'+ltrim(rtrim(str(month(tdate)))))-1,2)+'/'+ ltrim(rtrim(str(year(tdate)))), "
+"duration,hours,credits,ltrim(rtrim(portal)),ltrim(rtrim(createdby)),ltrim(rtrim(type)),ltrim(rtrim(courselevel)), "
+"substring(('000'+ltrim(rtrim(str(day(examdate))))), len('000'+ltrim(rtrim(str(day(examdate)))))-1,2)+'/'+  substring(('000'+ltrim(rtrim(str(month(examdate))))), "
+"len('000'+ltrim(rtrim(str(month(examdate)))))-1,2)+'/'+ ltrim(rtrim(str(year(examdate)))), "
+"ltrim(rtrim(certificatelevel)),ltrim(rtrim(fname1)), ltrim(rtrim(t1)),ltrim(rtrim(t2)), "
+"0,0,fname1 from apioncocom,staff where staff.staffid=apioncocom.staffid and dno in ("+dnoin+") "
+"and fdate between '"+fd+"' and '"+td+"'  order by "+order+",fdate desc";

if(select==16)
sql="select staff.staffid,ltrim(rtrim(name)),ltrim(rtrim(course)), "
+"credits,duration,hours,ltrim(rtrim(portal)),ltrim(rtrim(type)),ltrim(rtrim(courselevel)),nooflearners, "
+"substring(('000'+ltrim(rtrim(str(day(learnersdate))))), len('000'+ltrim(rtrim(str(day(learnersdate)))))-1,2)+'/'+  substring(('000'+ltrim(rtrim(str(month(learnersdate))))), "
+"len('000'+ltrim(rtrim(str(month(learnersdate)))))-1,2)+'/'+ ltrim(rtrim(str(year(learnersdate)))), "
+"ltrim(rtrim(fname1)), ltrim(rtrim(t1)),ltrim(rtrim(t2)), "
+"0,0,0,0,0,fname1 from apioncocre,staff where staff.staffid=apioncocre.staffid and dno in ("+dnoin+") "
+"and learnersdate between  '"+fd+"' and '"+td+"' order by "+order+",learnersdate desc";

if(select==17)
sql="select staff.staffid,ltrim(rtrim(name)),ltrim(rtrim(exam)), "
+" substring(('000'+ltrim(rtrim(str(day(examdate))))), len('000'+ltrim(rtrim(str(day(examdate)))))-1,2)+'/'+  substring(('000'+ltrim(rtrim(str(month(examdate))))), "
+" len('000'+ltrim(rtrim(str(month(examdate)))))-1,2)+'/'+ ltrim(rtrim(str(year(examdate)))), "
+" substring(('000'+ltrim(rtrim(str(day(qualdate))))), len('000'+ltrim(rtrim(str(day(qualdate)))))-1,2)+'/'+  substring(('000'+ltrim(rtrim(str(month(qualdate))))), "
+" len('000'+ltrim(rtrim(str(month(qualdate)))))-1,2)+'/'+ ltrim(rtrim(str(year(qualdate)))), "
+" ltrim(rtrim(subject)),ltrim(rtrim(regno)),ltrim(rtrim(cerno)),score,airank,srank, "
+" substring(('000'+ltrim(rtrim(str(day(fdate))))), len('000'+ltrim(rtrim(str(day(fdate)))))-1,2)+'/'+  substring(('000'+ltrim(rtrim(str(month(fdate))))), "
+" len('000'+ltrim(rtrim(str(month(fdate)))))-1,2)+'/'+ ltrim(rtrim(str(year(fdate)))), "
+" substring(('000'+ltrim(rtrim(str(day(tdate))))), len('000'+ltrim(rtrim(str(day(tdate)))))-1,2)+'/'+  substring(('000'+ltrim(rtrim(str(month(tdate))))), "
+" len('000'+ltrim(rtrim(str(month(tdate)))))-1,2)+'/'+ ltrim(rtrim(str(year(tdate)))), "
+" ltrim(rtrim(fname1)), ltrim(rtrim(t1)),ltrim(rtrim(t2)), "
+" 0,0,0,fname1 from apionexamqualy,staff where staff.staffid=apionexamqualy.staffid and dno in ("+dnoin+") "
+" and fdate between '"+fd+"' and '"+td+"' order by "+order+",fdate desc";

rs = stmt.executeQuery(sql);

while(rs.next())
{
	for(i=1;i<=20;i++)
	{
		report[i-1][count]=rs.getString(i);
	}
	count++;
}
rs.close();

stmt.close();
connMgr.freeConnection("xavier",con);
error= "success"+sql; 
}catch(Exception e){error=(e.toString()+count+i+sql);}
return error; 
}

public String OnCoComInsert()
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
			StringTokenizer st1 = new StringTokenizer(repin[3],"/");
		    d1=st1.nextToken();
		    m1=st1.nextToken();
		    y1=st1.nextToken();
			date1=m1+"/"+d1+"/"+y1;

			StringTokenizer st2 = new StringTokenizer(repin[4],"/");
		    d1=st2.nextToken();
		    m1=st2.nextToken();
		    y1=st2.nextToken();
			date2=m1+"/"+d1+"/"+y1;
			
			StringTokenizer st3 = new StringTokenizer(repin[12],"/");
		    d1=st3.nextToken();
		    m1=st3.nextToken();
		    y1=st3.nextToken();
			date3=m1+"/"+d1+"/"+y1;
}
catch(Exception e){}			

try
{
i1=Integer.parseInt(repin[5]);
i2=Integer.parseInt(repin[6]);
i3=Integer.parseInt(repin[7]);
}
catch(Exception e){}

			
					sql="select max(id)+1 from apiOnCoCom";
					rs = stmt.executeQuery(sql);
					if(rs.next())     { 	cid=rs.getInt(1); 	    }
					rs.close();
				
                    sql="insert into  apioncocom values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
					ps=con.prepareStatement(sql);
					ps.setInt(1,cid);
					ps.setString(2,repin[1]);
					ps.setString(3,repin[2]);
					ps.setString(4,date1);
					ps.setString(5,date2);
					ps.setInt(6,i1);
					ps.setInt(7,i2);
					ps.setInt(8,i3);
					ps.setString(9,repin[8]);
					ps.setString(10,repin[9]);
					ps.setString(11,repin[10]);
					ps.setString(12,repin[11]);
					ps.setString(13,date3);
					ps.setString(14,repin[13]);
					ps.setString(15,repin[14]);
					ps.setString(16,repin[15]);
					ps.setString(17,repin[16]);
					int j = ps.executeUpdate();
					ps.close();
                    stmt.close();
					connMgr.freeConnection("xavier",con);
					error="...";
					}catch(Exception e){error=(e.toString());}
return error;	
}

public String OnCoComUpdate(int id)
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
			StringTokenizer st1 = new StringTokenizer(repin[3],"/");
		    d1=st1.nextToken();
		    m1=st1.nextToken();
		    y1=st1.nextToken();
			date1=m1+"/"+d1+"/"+y1;

			StringTokenizer st2 = new StringTokenizer(repin[4],"/");
		    d1=st2.nextToken();
		    m1=st2.nextToken();
		    y1=st2.nextToken();
			date2=m1+"/"+d1+"/"+y1;
			
			StringTokenizer st3 = new StringTokenizer(repin[12],"/");
		    d1=st3.nextToken();
		    m1=st3.nextToken();
		    y1=st3.nextToken();
			date3=m1+"/"+d1+"/"+y1;
}
catch(Exception e){}			

try
{
i1=Integer.parseInt(repin[5]);
i2=Integer.parseInt(repin[6]);
i3=Integer.parseInt(repin[7]);
}
catch(Exception e){}

					    
                  sql="update apioncocom set title=?,fdate=?,tdate=?,duration=?,hours=?,credits=?,portal=?,createdby=?,type=?,courselevel=?,examdate=?,certificatelevel=?,fname1=?,t1=?,t2=? where id=? and staffid=?";
					ps=con.prepareStatement(sql);
					ps.setString(1,repin[2]);
					ps.setString(2,date1);
					ps.setString(3,date2);
					ps.setInt(4,i1);
					ps.setInt(5,i2);
					ps.setInt(6,i3);
					ps.setString(7,repin[8]);
					ps.setString(8,repin[9]);
					ps.setString(9,repin[10]);
					ps.setString(10,repin[11]);
					ps.setString(11,date3);
					ps.setString(12,repin[13]);
					ps.setString(13,repin[14]);
					ps.setString(14,repin[15]);
					ps.setString(15,repin[16]);
					ps.setInt(16,id);
					ps.setString(17,repin[1]);

					int i = ps.executeUpdate();
					ps.close();
                    stmt.close();
					connMgr.freeConnection("xavier",con);
					error="success";
					}catch(Exception e){error=(e.toString());
					}
					
		return error;
}

public String OnCoComUpload(int id)
{
try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;
					    PreparedStatement ps;
                  sql="update apioncocom set fname1=? where id=? and staffid=?";
					ps=con.prepareStatement(sql);
					ps.setString(1,repin[14]);
					ps.setInt(2,id);
					ps.setString(3,repin[1]);
					int i = ps.executeUpdate();
					ps.close();
                    stmt.close();
					connMgr.freeConnection("xavier",con);
					error="success";
					}catch(Exception e){error=sql+repin[14]+id+repin[1]+(e.toString());		}
return error;
}


public String OnCoComDel(int id)
{

try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;
					    PreparedStatement ps;
                  sql="delete from apioncocom where id=? and staffid=?";
					ps=con.prepareStatement(sql);
					ps.setInt(1,id);
					ps.setString(2,repin[1]);
					int i = ps.executeUpdate();
					ps.close();
                    stmt.close();
					connMgr.freeConnection("xavier",con);
					error="success";
					}catch(Exception e){error=(e.toString());
					}
					
		return error;
}

public String OnCoComList()
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
sql="select id,staffid,title, "
+" substring(('000'+ltrim(rtrim(str(day(fdate))))), len('000'+ltrim(rtrim(str(day(fdate)))))-1,2)+'/'+  substring(('000'+ltrim(rtrim(str(month(fdate))))), "
+" len('000'+ltrim(rtrim(str(month(fdate)))))-1,2)+'/'+ ltrim(rtrim(str(year(fdate)))), "
+" substring(('000'+ltrim(rtrim(str(day(tdate))))), len('000'+ltrim(rtrim(str(day(tdate)))))-1,2)+'/'+  substring(('000'+ltrim(rtrim(str(month(tdate))))), "
+" len('000'+ltrim(rtrim(str(month(tdate)))))-1,2)+'/'+ ltrim(rtrim(str(year(tdate)))), "
+" duration,hours,credits,portal,createdby,type,courselevel, "
+" substring(('000'+ltrim(rtrim(str(day(examdate))))), len('000'+ltrim(rtrim(str(day(examdate)))))-1,2)+'/'+  substring(('000'+ltrim(rtrim(str(month(examdate))))), "
+" len('000'+ltrim(rtrim(str(month(examdate)))))-1,2)+'/'+ ltrim(rtrim(str(year(examdate)))), "
+" certificatelevel,fname1,t1,t2 from apioncocom where staffid='"+repin[1]+"' order by fdate desc";

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


public String OnCoComView()
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
sql="select id,staffid,title, "
+" substring(('000'+ltrim(rtrim(str(day(fdate))))), len('000'+ltrim(rtrim(str(day(fdate)))))-1,2)+'/'+  substring(('000'+ltrim(rtrim(str(month(fdate))))), "
+" len('000'+ltrim(rtrim(str(month(fdate)))))-1,2)+'/'+ ltrim(rtrim(str(year(fdate)))), "
+" substring(('000'+ltrim(rtrim(str(day(tdate))))), len('000'+ltrim(rtrim(str(day(tdate)))))-1,2)+'/'+  substring(('000'+ltrim(rtrim(str(month(tdate))))), "
+" len('000'+ltrim(rtrim(str(month(tdate)))))-1,2)+'/'+ ltrim(rtrim(str(year(tdate)))), "
+" duration,hours,credits,portal,createdby,type,courselevel, "
+" substring(('000'+ltrim(rtrim(str(day(examdate))))), len('000'+ltrim(rtrim(str(day(examdate)))))-1,2)+'/'+  substring(('000'+ltrim(rtrim(str(month(examdate))))), "
+" len('000'+ltrim(rtrim(str(month(examdate)))))-1,2)+'/'+ ltrim(rtrim(str(year(examdate)))), "
+" certificatelevel,fname1,t1,t2 from apioncocom where staffid='"+repin[1]+"' and id="+repin[0];

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


public String OnCoCreInsert()
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
			StringTokenizer st1 = new StringTokenizer(repin[10],"/");
		    d1=st1.nextToken();
		    m1=st1.nextToken();
		    y1=st1.nextToken();
			date1=m1+"/"+d1+"/"+y1;

}
catch(Exception e){}			

try
{
i1=Integer.parseInt(repin[3]);
i2=Integer.parseInt(repin[4]);
i3=Integer.parseInt(repin[5]);
i4=Integer.parseInt(repin[9]);
}
catch(Exception e){}

			
					sql="select max(id)+1 from apiOnCoCre";
					rs = stmt.executeQuery(sql);
					if(rs.next())     { 	cid=rs.getInt(1); 	    }
					rs.close();
				
                    sql="insert into  apiOnCoCre values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
					ps=con.prepareStatement(sql);
					ps.setInt(1,cid);
					ps.setString(2,repin[1]);
					ps.setString(3,repin[2]);
					ps.setInt(4,i1);
					ps.setInt(5,i2);
					ps.setInt(6,i3);
					ps.setString(7,repin[6]);
					ps.setString(8,repin[7]);
					ps.setString(9,repin[8]);
					ps.setInt(10,i4);
					ps.setString(11,date1);
					ps.setString(12,repin[11]);
					ps.setString(13,repin[12]);
					ps.setString(14,repin[13]);
					int j = ps.executeUpdate();
					ps.close();
                    stmt.close();
					connMgr.freeConnection("xavier",con);
					error="...";
					}catch(Exception e){error=(e.toString());}
return error;	
}

public String OnCoCreUpdate(int id)
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
			StringTokenizer st1 = new StringTokenizer(repin[10],"/");
		    d1=st1.nextToken();
		    m1=st1.nextToken();
		    y1=st1.nextToken();
			date1=m1+"/"+d1+"/"+y1;

}
catch(Exception e){}			

try
{
i1=Integer.parseInt(repin[3]);
i2=Integer.parseInt(repin[4]);
i3=Integer.parseInt(repin[5]);
i4=Integer.parseInt(repin[9]);
}
catch(Exception e){}

					    
                  sql="update apiOnCoCre set course=?,credits=?,duration=?,hours=?,portal=?,type=?,courselevel=?,nooflearners,learnersdate=?,fname1=?,t1=?,t2=? where id=? and staffid=?";
					ps=con.prepareStatement(sql);
					ps.setString(1,repin[2]);
					ps.setInt(2,i1);
					ps.setInt(3,i2);
					ps.setInt(4,i3);
					ps.setString(5,repin[6]);
					ps.setString(6,repin[7]);
					ps.setString(7,repin[8]);
					ps.setInt(8,i4);
					ps.setString(9,date1);
					ps.setString(10,repin[11]);
					ps.setString(11,repin[12]);
					ps.setString(12,repin[13]);
					ps.setInt(13,cid);
					ps.setString(14,repin[1]);

					int i = ps.executeUpdate();
					ps.close();
                    stmt.close();
					connMgr.freeConnection("xavier",con);
					error="success";
					}catch(Exception e){error=(e.toString());
					}
					
		return error;
}

public String OnCoCreUpload(int id)
{
try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;
					    PreparedStatement ps;
                  sql="update apiOnCoCre set fname1=? where id=? and staffid=?";
					ps=con.prepareStatement(sql);
					ps.setString(1,repin[11]);
					ps.setInt(2,id);
					ps.setString(3,repin[1]);
					int i = ps.executeUpdate();
					ps.close();
                    stmt.close();
					connMgr.freeConnection("xavier",con);
					error="success";
					}catch(Exception e){error=sql+repin[14]+id+repin[1]+(e.toString());		}
return error;
}


public String OnCoCreDel(int id)
{

try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;
					    PreparedStatement ps;
                  sql="delete from apiOnCoCre where id=? and staffid=?";
					ps=con.prepareStatement(sql);
					ps.setInt(1,id);
					ps.setString(2,repin[1]);
					int i = ps.executeUpdate();
					ps.close();
                    stmt.close();
					connMgr.freeConnection("xavier",con);
					error="success";
					}catch(Exception e){error=(e.toString());
					}
					
		return error;
}

public String OnCoCreList()
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
sql="select id,staffid,course,credits,duration,hours,portal,type,courselevel,nooflearners, "
+" substring(('000'+ltrim(rtrim(str(day(learnersdate))))), len('000'+ltrim(rtrim(str(day(learnersdate)))))-1,2)+'/'+  substring(('000'+ltrim(rtrim(str(month(learnersdate))))), "
+" len('000'+ltrim(rtrim(str(month(learnersdate)))))-1,2)+'/'+ ltrim(rtrim(str(year(learnersdate)))), "
+" fname1,t1,t2 from apiOnCoCre where staffid='"+repin[1]+"' order by learnersdate desc";

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


public String OnCoCreView()
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
sql="select id,staffid,course,credits,duration,hours,portal,type,courselevel,nooflearners, "
+" substring(('000'+ltrim(rtrim(str(day(learnersdate))))), len('000'+ltrim(rtrim(str(day(learnersdate)))))-1,2)+'/'+  substring(('000'+ltrim(rtrim(str(month(learnersdate))))), "
+" len('000'+ltrim(rtrim(str(month(learnersdate)))))-1,2)+'/'+ ltrim(rtrim(str(year(learnersdate)))), "
+" fname1,t1,t2 from apiOnCoCre where staffid='"+repin[1]+"' and id="+repin[0];

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


public String OnExamQualyInsert()
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
			StringTokenizer st1 = new StringTokenizer(repin[3],"/");
		    d1=st1.nextToken();
		    m1=st1.nextToken();
		    y1=st1.nextToken();
			date1=m1+"/"+d1+"/"+y1;

			StringTokenizer st2 = new StringTokenizer(repin[4],"/");
		    d1=st2.nextToken();
		    m1=st2.nextToken();
		    y1=st2.nextToken();
			date2=m1+"/"+d1+"/"+y1;
			
			StringTokenizer st3 = new StringTokenizer(repin[11],"/");
		    d1=st3.nextToken();
		    m1=st3.nextToken();
		    y1=st3.nextToken();
			date3=m1+"/"+d1+"/"+y1;

			StringTokenizer st4 = new StringTokenizer(repin[12],"/");
		    d1=st4.nextToken();
		    m1=st4.nextToken();
		    y1=st4.nextToken();
			date4=m1+"/"+d1+"/"+y1;
}
catch(Exception e){}			

try
{
i1=Integer.parseInt(repin[9]);
i2=Integer.parseInt(repin[10]);
f1=Float.parseFloat(repin[8]);
}
catch(Exception e){}

			
					sql="select max(id)+1 from apiOnExamQualy";
					rs = stmt.executeQuery(sql);
					if(rs.next())     { 	cid=rs.getInt(1); 	    }
					rs.close();
				
                    sql="insert into  apiOnExamQualy values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
					ps=con.prepareStatement(sql);
					ps.setInt(1,cid);
					ps.setString(2,repin[1]);
					ps.setString(3,repin[2]);
					ps.setString(4,date1);
					ps.setString(5,date2);
					ps.setString(6,repin[5]);
					ps.setString(7,repin[6]);
					ps.setString(8,repin[7]);
					ps.setFloat(9,f1);
					ps.setInt(10,i1);
					ps.setInt(11,i2);
					ps.setString(12,date3);
					ps.setString(13,date4);
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

public String OnExamQualyUpdate(int id)
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
			StringTokenizer st1 = new StringTokenizer(repin[3],"/");
		    d1=st1.nextToken();
		    m1=st1.nextToken();
		    y1=st1.nextToken();
			date1=m1+"/"+d1+"/"+y1;

			StringTokenizer st2 = new StringTokenizer(repin[4],"/");
		    d1=st2.nextToken();
		    m1=st2.nextToken();
		    y1=st2.nextToken();
			date2=m1+"/"+d1+"/"+y1;
			
			StringTokenizer st3 = new StringTokenizer(repin[11],"/");
		    d1=st3.nextToken();
		    m1=st3.nextToken();
		    y1=st3.nextToken();
			date3=m1+"/"+d1+"/"+y1;

			StringTokenizer st4 = new StringTokenizer(repin[12],"/");
		    d1=st4.nextToken();
		    m1=st4.nextToken();
		    y1=st4.nextToken();
			date4=m1+"/"+d1+"/"+y1;
}
catch(Exception e){}			

try
{
i1=Integer.parseInt(repin[9]);
i2=Integer.parseInt(repin[10]);
f1=Float.parseFloat(repin[8]);
}
catch(Exception e){}
					    
                  sql="update apiOnExamQualy set exam=?,examdate=?,qualdate=?,subject=?,regno=?,cerno=?,score=?,airank=?,srank=?,fdate=?,tdate=?,fname1=?,t1=?,t2=? where id=? and staffid=?";
					ps=con.prepareStatement(sql);
					ps.setString(1,repin[2]);
					ps.setString(2,date1);
					ps.setString(3,date2);
					ps.setString(4,repin[5]);
					ps.setString(5,repin[6]);
					ps.setString(6,repin[7]);
					ps.setFloat(7,f1);
					ps.setInt(8,i1);
					ps.setInt(9,i2);
					ps.setString(10,date3);
					ps.setString(11,date4);
					ps.setString(12,repin[13]);
					ps.setString(13,repin[14]);
					ps.setString(14,repin[15]);
					ps.setInt(15,id);
					ps.setString(16,repin[1]);

					int i = ps.executeUpdate();
					ps.close();
                    stmt.close();
					connMgr.freeConnection("xavier",con);
					error="success";
					}catch(Exception e){error=(e.toString());
					}
					
		return error+sql;
}

public String OnExamQualyUpload(int id)
{
try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;
					    PreparedStatement ps;
                  sql="update apiOnExamQualy set fname1=? where id=? and staffid=?";
					ps=con.prepareStatement(sql);
					ps.setString(1,repin[13]);
					ps.setInt(2,id);
					ps.setString(3,repin[1]);
					int i = ps.executeUpdate();
					ps.close();
                    stmt.close();
					connMgr.freeConnection("xavier",con);
					error="success";
					}catch(Exception e){error=sql+repin[14]+id+repin[1]+(e.toString());		}
return error;
}


public String OnExamQualyDel(int id)
{

try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;
					    PreparedStatement ps;
                  sql="delete from apiOnExamQualy where id=? and staffid=?";
					ps=con.prepareStatement(sql);
					ps.setInt(1,id);
					ps.setString(2,repin[1]);
					int i = ps.executeUpdate();
					ps.close();
                    stmt.close();
					connMgr.freeConnection("xavier",con);
					error="success";
					}catch(Exception e){error=(e.toString());
					}
					
		return error;
}

public String OnExamQualyList()
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
sql="select id,staffid,exam, "
+" substring(('000'+ltrim(rtrim(str(day(examdate))))), len('000'+ltrim(rtrim(str(day(examdate)))))-1,2)+'/'+  substring(('000'+ltrim(rtrim(str(month(examdate))))), "
+" len('000'+ltrim(rtrim(str(month(examdate)))))-1,2)+'/'+ ltrim(rtrim(str(year(examdate)))), "
+" substring(('000'+ltrim(rtrim(str(day(qualdate))))), len('000'+ltrim(rtrim(str(day(qualdate)))))-1,2)+'/'+  substring(('000'+ltrim(rtrim(str(month(qualdate))))), "
+" len('000'+ltrim(rtrim(str(month(qualdate)))))-1,2)+'/'+ ltrim(rtrim(str(year(qualdate)))), "
+" subject,regno,cerno,score,airank,srank, "
+" substring(('000'+ltrim(rtrim(str(day(fdate))))), len('000'+ltrim(rtrim(str(day(fdate)))))-1,2)+'/'+  substring(('000'+ltrim(rtrim(str(month(fdate))))), "
+" len('000'+ltrim(rtrim(str(month(fdate)))))-1,2)+'/'+ ltrim(rtrim(str(year(fdate)))), "
+" substring(('000'+ltrim(rtrim(str(day(tdate))))), len('000'+ltrim(rtrim(str(day(tdate)))))-1,2)+'/'+  substring(('000'+ltrim(rtrim(str(month(tdate))))), "
+" len('000'+ltrim(rtrim(str(month(tdate)))))-1,2)+'/'+ ltrim(rtrim(str(year(tdate)))), "
+" fname1,t1,t2 from apionExamQualy where staffid='"+repin[1]+"' order by examdate desc";
 	
rs = stmt.executeQuery(sql);

while(rs.next())
{
	for(i=1;i<=16;i++)
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


public String OnExamQualyView()
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
sql="select id,staffid,exam, "
+" substring(('000'+ltrim(rtrim(str(day(examdate))))), len('000'+ltrim(rtrim(str(day(examdate)))))-1,2)+'/'+  substring(('000'+ltrim(rtrim(str(month(examdate))))), "
+" len('000'+ltrim(rtrim(str(month(examdate)))))-1,2)+'/'+ ltrim(rtrim(str(year(examdate)))), "
+" substring(('000'+ltrim(rtrim(str(day(qualdate))))), len('000'+ltrim(rtrim(str(day(qualdate)))))-1,2)+'/'+  substring(('000'+ltrim(rtrim(str(month(qualdate))))), "
+" len('000'+ltrim(rtrim(str(month(qualdate)))))-1,2)+'/'+ ltrim(rtrim(str(year(qualdate)))), "
+" subject,regno,cerno,score,airank,srank, "
+" substring(('000'+ltrim(rtrim(str(day(fdate))))), len('000'+ltrim(rtrim(str(day(fdate)))))-1,2)+'/'+  substring(('000'+ltrim(rtrim(str(month(fdate))))), "
+" len('000'+ltrim(rtrim(str(month(fdate)))))-1,2)+'/'+ ltrim(rtrim(str(year(fdate)))), "
+" substring(('000'+ltrim(rtrim(str(day(tdate))))), len('000'+ltrim(rtrim(str(day(tdate)))))-1,2)+'/'+  substring(('000'+ltrim(rtrim(str(month(tdate))))), "
+" len('000'+ltrim(rtrim(str(month(tdate)))))-1,2)+'/'+ ltrim(rtrim(str(year(tdate)))), "
+" fname1,t1,t2 from apionExamQualy where staffid='"+repin[1]+"' and id="+repin[0];

rs = stmt.executeQuery(sql);

while(rs.next())
{
	for(i=1;i<=16;i++)
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


public String ApiBPASReport(int select,String staffid,String fd,String td)
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
					    
sql="select staffid,ltrim(rtrim(type)),ltrim(rtrim(title)),ltrim(rtrim(authors)),ltrim(rtrim(affiliation)),ltrim(rtrim(authortype)),ltrim(rtrim(journalname)),ltrim(rtrim(publisher)),ltrim(rtrim(intornat)),"
+"ltrim(rtrim(issn)),ltrim(rtrim(doi)),ltrim(rtrim(monthofpub))+'-'+ltrim(rtrim(yearofpub)),ltrim(rtrim(volissuepage)),ltrim(rtrim(indexing)),impact,ltrim(rtrim(snip)),ltrim(rtrim(sjr)),citationcount,ltrim(rtrim(interdept)),fname1 from apijournal";

if(select==1)
sql="select id,staffid,type,title,authors,affiliation,authortype,journalname,publisher,intornat,issn,doi, "
+" substring(DATENAME(m, str(monthofpub) + '/1/2017'),1,3),yearofpub,volissuepage,indexing,impact,snip,sjr,fname1,citationcount,interdept,0,0,0 from apijournal  "
+" where staffid='"+staffid+"' and cast(ltrim(str(monthofpub))+'/1/'+ltrim(str(yearofpub)) as datetime) between '"+fd+"' and '"+td+"' order by yearofpub desc";

if(select==2)
sql="select id,staffid,authors,affiliation,venue,title,authortype,conferencename,intornat, "
+"substring(('000'+ltrim(rtrim(str(day(fdate))))), len('000'+ltrim(rtrim(str(day(fdate)))))-1,2)+'/'+  substring(('000'+ltrim(rtrim(str(month(fdate))))), "
+"len('000'+ltrim(rtrim(str(month(fdate)))))-1,2)+'/'+ ltrim(rtrim(str(year(fdate)))), "
+"substring(('000'+ltrim(rtrim(str(day(tdate))))), len('000'+ltrim(rtrim(str(day(tdate)))))-1,2)+'/'+  substring(('000'+ltrim(rtrim(str(month(tdate))))), "
+"len('000'+ltrim(rtrim(str(month(tdate)))))-1,2)+'/'+ ltrim(rtrim(str(year(tdate)))), "
+"issn,doi,yearofpub,volissuepage,fname1,indexing,citationcount,interdept,0,0,0,0,0,0 from apiconference where staffid='"+staffid+"' and fdate between '"+fd+"' and '"+td+"' order by fdate desc";

if(select==3)
sql="select id,staffid,type,researcharea,coinvestigator,title, "
+"substring(('000'+ltrim(rtrim(str(day(appdate))))), len('000'+ltrim(rtrim(str(day(appdate)))))-1,2)+'/'+  substring(('000'+ltrim(rtrim(str(month(appdate))))), "
+"len('000'+ltrim(rtrim(str(month(appdate)))))-1,2)+'/'+ ltrim(rtrim(str(year(appdate)))), "
+"substring(('000'+ltrim(rtrim(str(day(fdate))))), len('000'+ltrim(rtrim(str(day(fdate)))))-1,2)+'/'+  substring(('000'+ltrim(rtrim(str(month(fdate))))), "
+"len('000'+ltrim(rtrim(str(month(fdate)))))-1,2)+'/'+ ltrim(rtrim(str(year(fdate)))), "
+"substring(('000'+ltrim(rtrim(str(day(tdate))))), len('000'+ltrim(rtrim(str(day(tdate)))))-1,2)+'/'+  substring(('000'+ltrim(rtrim(str(month(tdate))))), "
+"len('000'+ltrim(rtrim(str(month(tdate)))))-1,2)+'/'+ ltrim(rtrim(str(year(tdate)))), "
+"status,agency,orderno, "
+"substring(('000'+ltrim(rtrim(str(day(sanctioneddate))))), len('000'+ltrim(rtrim(str(day(sanctioneddate)))))-1,2)+'/'+  substring(('000'+ltrim(rtrim(str(month(sanctioneddate))))), "
+"len('000'+ltrim(rtrim(str(month(sanctioneddate)))))-1,2)+'/'+ ltrim(rtrim(str(year(sanctioneddate)))), "
+"requiredamt,sanctionedamt,receivedamt,outcome,fname1,fname2,0,0,0,0,0,0 from apifunded where staffid='"+staffid+"' and fdate between '"+fd+"' and '"+td+"'  order by appdate desc";

if(select==5)
sql="select *,0,0,0,0,0,0,0,0,0,0,0,0,0,0 from apipatent  where staffid='"+staffid+"' and fdate between '"+fd+"' and '"+td+"' order by fdate desc";

if(select==6)
sql="select *,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0, "
+" substring(('000'+ltrim(rtrim(str(day(awarddate))))), len('000'+ltrim(rtrim(str(day(awarddate)))))-1,2)+'/'+  substring(('000'+ltrim(rtrim(str(month(awarddate))))), "
+" len('000'+ltrim(rtrim(str(month(awarddate)))))-1,2)+'/'+ ltrim(rtrim(str(year(awarddate)))) "
+" from apiawards  where staffid='"+staffid+"' and awarddate between  '"+fd+"' and '"+td+"' order by type,awarddate desc";

if(select==7)
sql="select *,0,0,0,0,0,0 from apifunded  where staffid='"+staffid+"' and status not in('Applied')  and fdate between '"+fd+"' and '"+td+"' order by fdate desc";

if(select==8)
sql="select *,0,0,0,0,0,0,0,0,0,0 from apiConsultancy  where staffid='"+staffid+"'  and fdate between '"+fd+"' and '"+td+"' order by fdate desc";

if(select==9)
sql="select *,0,0,0,0,0,0,0,0 from apibooks where staffid='"+staffid+"' and cast(ltrim(str(monthofpub))+'/1/'+ltrim(str(yearofpub)) as datetime)  between '"+fd+"' and '"+td+"' order by yearofpub desc";

if(select==10)
sql="select *,0,0,0,0,0,0,0,0,0,0,0,0,"
+"substring(('000'+ltrim(rtrim(str(day(fdate))))), len('000'+ltrim(rtrim(str(day(fdate)))))-1,2)+'/'+  substring(('000'+ltrim(rtrim(str(month(fdate))))), "
+"len('000'+ltrim(rtrim(str(month(fdate)))))-1,2)+'/'+ ltrim(rtrim(str(year(fdate)))), "
+"substring(('000'+ltrim(rtrim(str(day(tdate))))), len('000'+ltrim(rtrim(str(day(tdate)))))-1,2)+'/'+  substring(('000'+ltrim(rtrim(str(month(tdate))))), "
+"len('000'+ltrim(rtrim(str(month(tdate)))))-1,2)+'/'+ ltrim(rtrim(str(year(tdate)))) "
+" from apiorganized where staffid='"+staffid+"' and fdate between '"+fd+"' and '"+td+"' order by fdate desc";



rs = stmt.executeQuery(sql);

while(rs.next())
{
	for(i=1;i<=25;i++)
	{
		report[i-1][count]=rs.getString(i);
	}
	count++;
}
rs.close();

stmt.close();
connMgr.freeConnection("xavier",con);
error= "success"+sql; 
}catch(Exception e){error=(e.toString()+count+i+sql);}
return error; 
}

public String ApiBPASReport1(int select,String staffid,String fd,String td,String accyear)
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
					    
sql="select ugpg,count(*) from apiactivities where staffid='"+staffid+"' and academicyear='"+accyear+"' group by ugpg";

if(select==4)
sql="select ugpg,count(*) from apiactivities where staffid='"+staffid+"' and academicyear='"+accyear+"' group by ugpg";

if(select==11)
sql="select subcode,count(*) from eresources where login='"+staffid+"' and date between '"+fd+"' and '"+td+"' and active=1 group by subcode  order by max(semester)"; 

rs = stmt.executeQuery(sql);

while(rs.next())
{
	for(i=1;i<=2;i++)
	{
		report[i-1][count]=rs.getString(i);
	}
	count++;
}
rs.close();

stmt.close();
connMgr.freeConnection("xavier",con);
error= "success"+sql; 
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




	
	