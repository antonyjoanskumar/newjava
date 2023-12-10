package sxcce;
import java.sql.*;
import java.util.StringTokenizer;
import com.jspsmart.upload.*;
import java.util.Date;
import java.io.*;
import java.math.*;
import java.text.DecimalFormat;
import dd.DBConnectionManager;

public class cfile
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


public String ListSubjectStatus(String staffid,String accyear,String oddeven)
{
String error="";				
int semno=1;
if(oddeven.equals("ODD"))
semno=1;
else
semno=2;
 				
count=0;
	   
DBConnectionManager connMgr=null; Connection con =null;
try
{
 connMgr = DBConnectionManager.getInstance();
 con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;
					    

/*
sql="select 0,academicyear,dno,semester,subjecthandled.subcode,syllabus.subjectname,8,subjecthandled.staffid from subjecthandled,subjectidentify,syllabus where "
+" subjecthandled.id=subjectidentify.id and subjecthandled.id=syllabus.id and subjecthandled.subcode=subjectcode and mainorsub='M' "
+" and staffid='"+staffid+"' and academicyear='"+accyear+"' and (semester%2)= ("+semno+"%2)";
*/

sql="select 0,academicyear,d.dno,semester,subjecthandled.subcode,syllabus.subjectname,8,subjecthandled.staffid,sf from subjecthandled,subjectidentify,syllabus,department d where "
+" d.dno=subjectidentify.dno and subjecthandled.id=subjectidentify.id and subjecthandled.id=syllabus.id and subjecthandled.subcode=subjectcode and mainorsub='M' "
+" and staffid='"+staffid+"' and academicyear='"+accyear+"' and (semester%2)= ("+semno+"%2)";



rs = stmt.executeQuery(sql);

while(rs.next())
{
	for(int i=1;i<=8;i++)
	{
		report[i][count]=rs.getString(i);
		report[i][count+1]=report[i][count];
	}
		report[0][count]=rs.getString(9);
		report[0][count+1]=report[0][count];
		report[16][count]=report[6][count];
		report[16][count+1]=report[6][count+1];
		report[6][count]="1";
		report[6][count+1]="2";
		report[9][count]="-";
		report[9][count+1]="-";
		report[10][count]="0";
		report[10][count+1]="0";

	count+=2;
}
rs.close();

for(int i=0;i<count;i++)
{
sql="select cfid,fname1,hodstatus,deanstatus from cfile where  academicyear='"+report[2][i]+"' and dno="+report[3][i]+" and sem="+report[4][i]+" and subcode='"+report[5][i]+"' and part="+report[6][i];
rs = stmt.executeQuery(sql);

if(rs.next())
{
	report[1][i]=rs.getString(1);
	report[9][i]=rs.getString(2);
	report[10][i]=rs.getString(3);
	report[12][i]=rs.getString(4);
}
rs.close();
}

stmt.close();
error=sql;
}catch(Exception e){error=e.toString();}
finally{connMgr.freeConnection("xavier",con);}
return error;
}


public int cfidCheck()
{
String error="";				

int cfid=0;	   
DBConnectionManager connMgr=null; Connection con =null;
try
{
 connMgr = DBConnectionManager.getInstance();
 con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;
					    

sql="select cfid from cfile where  academicyear='"+repin[2]+"' and dno="+repin[3]+" and sem="+repin[4]+" and subcode='"+repin[5]+"' and part="+repin[6];
rs = stmt.executeQuery(sql);

if(rs.next())
{
	cfid=Integer.parseInt(rs.getString(1));
}

stmt.close();
error=sql;
}catch(Exception e){error=e.toString();}
finally{connMgr.freeConnection("xavier",con);}
return cfid;
}


public String CFileInsert()
{
sql="";
int cfid=1;
try
{
DBConnectionManager connMgr;
connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  			Statement stmt = con.createStatement();
			ResultSet rs;
			PreparedStatement ps;

sql="select isnull((max(cfid)+1),1) from cfile";
rs = stmt.executeQuery(sql);
if(rs.next())
{
	cfid=rs.getInt(1);
}
if(Integer.parseInt(repin[6])>0)
{
                    sql="insert into cfile(cfid,academicyear,dno,sem,subcode,part,date,staffid,fname1,hodstatus,deanstatus) values(?,?,?,?,?,?,CURRENT_TIMESTAMP,?,?,?,?)";
					ps=con.prepareStatement(sql);
					ps.setInt(1,cfid);
					ps.setString(2,repin[2]);
					ps.setString(3,repin[3]);
					ps.setString(4,repin[4]);
					ps.setString(5,repin[5]);
					ps.setString(6,repin[6]);
					ps.setString(7,repin[8]);
					ps.setString(8,"-");
					ps.setString(9,"0");
					ps.setString(10,"0");
					int j = ps.executeUpdate();
					ps.close();
}				
     
                    stmt.close();
					connMgr.freeConnection("xavier",con);
					error="...";
					}catch(Exception e){error=(e.toString());}
return error;	
}

public String CFileUpload(int id)
{
try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;
					    PreparedStatement ps;
			    
                  sql="update cfile set fname1=? where cfid=?";
					ps=con.prepareStatement(sql);
					ps.setString(1,repin[9]);
					ps.setInt(2,id);
					int i = ps.executeUpdate();
					ps.close();
                    stmt.close();
					connMgr.freeConnection("xavier",con);
					error="success";
					}catch(Exception e){error=sql+repin[11]+id+repin[1]+(e.toString());		}
return error;
}

public String cfileStatusUpdate(int cfid,int status, int hod)
{
try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;
					    PreparedStatement ps;
if(hod==1)					    
                  sql="update cfile set hodstatus=?, hoddate=CURRENT_TIMESTAMP where cfid=?";
else                  
                  sql="update cfile set deanstatus=?, deandate=CURRENT_TIMESTAMP where cfid=?";
					ps=con.prepareStatement(sql);
					ps.setInt(1,status);
					ps.setInt(2,cfid);
					int i = ps.executeUpdate();
					ps.close();
                    stmt.close();
					connMgr.freeConnection("xavier",con);
					error="success";
					}catch(Exception e){error=(e.toString());
					}
					
		return error;
}


public int cfilestat(String accyear,int dno1, int sem1, String subcode, int part1)
{
int s=0;
count=0;
try
{
DBConnectionManager connMgr;
connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
Statement stmt = con.createStatement();
ResultSet rs;
sql="select hodstatus from cfile where academicyear='"+accyear+"' and dno="+dno1+" and sem="+sem1+" and part="+part1+" and subcode='"+subcode+"'";
rs = stmt.executeQuery(sql);
if(rs.next()) s=rs.getInt(1);
rs.close();
stmt.close();
connMgr.freeConnection("xavier",con);
error= "success"; 
}catch(Exception e){s=0;}
return s; 
}


public int cfileid(String accyear,int dno1, int sem1, String subcode, int part1)
{
int s=0;
count=0;
try
{
DBConnectionManager connMgr;
connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
Statement stmt = con.createStatement();
ResultSet rs;
sql="select cfid from cfile where academicyear='"+accyear+"' and dno="+dno1+" and sem="+sem1+" and part="+part1+" and subcode='"+subcode+"'";
rs = stmt.executeQuery(sql);
if(rs.next()) s=rs.getInt(1);
rs.close();
stmt.close();
connMgr.freeConnection("xavier",con);
error= "success"; 
}catch(Exception e){s=0;}
return s; 
}

public String cfilename(String accyear,int dno1, int sem1, String subcode, int part1)
{
String fname="-";
count=0;
try
{
DBConnectionManager connMgr;
connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
Statement stmt = con.createStatement();
ResultSet rs;
sql="select fname1 from cfile where academicyear='"+accyear+"' and dno="+dno1+" and sem="+sem1+" and part="+part1+" and subcode='"+subcode+"'";
rs = stmt.executeQuery(sql);
if(rs.next()) fname=rs.getString(1);
rs.close();
stmt.close();
connMgr.freeConnection("xavier",con);
error= "success"; 
}catch(Exception e){}
return fname; 
}


public String cfileView(int cfid)
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
sql="select * from cfile where id="+cfid;

rs = stmt.executeQuery(sql);

if(rs.next())
{
	for(i=1;i<=13;i++)
	{
		report1[i]=rs.getString(i);
	}
}
rs.close();

stmt.close();
connMgr.freeConnection("xavier",con);
error= "success"; 
}catch(Exception e){error=(e.toString()+count+i+sql);}
return error; 
}



public String cfileassessmentView(int cfid,int part)
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
sql="select a.qid,a.qno,quest,isnull(cfaid,0),isnull(rating,-1),ltrim(rtrim(isnull(obs,''))) from "
+" (select * from cfilequest where part="+part+" ) a "
+" left join (select cfaid,cfid,qid,rating,obs from cfileass where cfid="+cfid+") b on a.qid=b.qid "
+" order by a.qno";

rs = stmt.executeQuery(sql);

while(rs.next())
{
	for(i=1;i<=6;i++)
	{
		report[i][count]=rs.getString(i);
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

public String cfileassessmentInsert(int cfid,int count)
{
sql="";
int cfaid=0;
try
{
DBConnectionManager connMgr;
connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  			Statement stmt = con.createStatement();
			ResultSet rs;
			PreparedStatement ps;
for(int i=0;i<count;i++)
{
cfaid=0;	
sql="select cfaid from cfileass where cfid="+cfid+" and qid="+report[1][i];
rs = stmt.executeQuery(sql);
if(rs.next())
{
	cfaid=rs.getInt(1);
}
rs.close();

if(cfaid>0)
{
                    sql="update cfileass set rating=?, obs=? where cfaid="+cfaid;
					ps=con.prepareStatement(sql);
					ps.setString(1,report[2][i]);
					ps.setString(2,report[3][i]);
					int j = ps.executeUpdate();
					ps.close();
}
else
{
sql="select isnull((max(cfaid)+1),1) from cfileass";
rs = stmt.executeQuery(sql);
if(rs.next())
{
	cfaid=rs.getInt(1);
}
rs.close();

                    sql="insert into cfileass values(?,?,?,?,?)";
					ps=con.prepareStatement(sql);
					ps.setInt(1,cfaid);
					ps.setInt(2,cfid);
					ps.setString(3,report[1][i]);
					ps.setString(4,report[2][i]);
					ps.setString(5,report[3][i]);
					int j = ps.executeUpdate();
					ps.close();
}				
}
     
                    stmt.close();
					connMgr.freeConnection("xavier",con);
					error="Success";
					}catch(Exception e){error=(e.toString());}
return error;	
}



//-----------------------------------------------------

public String QuestionUpdate(int id)
{
try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;
					    PreparedStatement ps;
					    
                  sql="update cfile set qno=?, part=?, sdiv=?, question=?, maxmarks=?, clevel=?, co=? where id=?";
					ps=con.prepareStatement(sql);
					ps.setString(1,repin[7]);
					ps.setString(2,repin[8]);
					ps.setString(3,repin[9]);
					ps.setString(4,repin[10]);
					ps.setString(5,repin[11]);
					ps.setString(6,repin[12]);
					ps.setString(7,repin[13]);
					ps.setInt(8,id);
					int i = ps.executeUpdate();
					ps.close();
                    stmt.close();
					connMgr.freeConnection("xavier",con);
					error="success";
					}catch(Exception e){error=(e.toString());
					}
					
		return error;
}



public String QuestionDel(int id)
{

try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;
					    PreparedStatement ps;
                  sql="delete from cfile where cfid=?";
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

public String questionsetselect(int cid)
{

   int j=0,i=0,previous=0;
	count=0;
	DBConnectionManager connMgr=null; Connection con =null;
	try
	{
                       connMgr = DBConnectionManager.getInstance(); 
                       con = connMgr.getConnection("xavier");
  					    Statement stmt;
					    ResultSet rs;
					    PreparedStatement ps;

                    sql="select qu.id,ltrim(rtrim(subjectcode)),ltrim(rtrim(subjectname)),ltrim(rtrim(exam)),si.semester,sf,si.dno,qu.examno,qset,fname1,year "
+" from syllabus sy,subjectidentify si,examyear ey,questionupload qu,department d "
+" where sy.id=si.id and ey.semester=si.semester and ey.dno=si.dno and sy.subjectcode=qu.subcode "
+" and d.dno=si.dno and si.academicyear=year and qu.examno=ey.examno and qu.id="+cid;
                    stmt= con.createStatement();
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

error= "success"; 
}catch(Exception e){error=(e.toString()+count+i+sql);}
finally{connMgr.freeConnection("xavier",con);
return error; 
}
}

public String QuestionSetUpload(int id)
{
try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;
					    PreparedStatement ps;
			    
                  sql="update cfile set fname1=? where cfid=?";
					ps=con.prepareStatement(sql);
					ps.setString(1,repin[9]);
					ps.setInt(2,id);
					int i = ps.executeUpdate();
					ps.close();
                    stmt.close();
					connMgr.freeConnection("xavier",con);
					error="success";
					}catch(Exception e){error=sql+repin[11]+id+repin[1]+(e.toString());		}
return error;
}

public String QuestionStaffList(int unit,String subcode,String accyear,String oddeven)
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
if(unit==0)
sql="select * from cfile where subcode='"+subcode+"' and academicyear='"+accyear+"' and oddeven='"+oddeven+"' order by part,unit,qno,sdiv";
else
sql="select * from cfile where unit="+unit+" and subcode='"+subcode+"' and academicyear='"+accyear+"' and oddeven='"+oddeven+"' order by part,qno,sdiv";

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
error= "success"; 
}catch(Exception e){error=(e.toString()+count+i+sql);}
return error; 
}

public String QuestionStaffList1(int unit,String subcode,String accyear,String oddeven)
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
if(unit==0)
sql="select * from cfile where subcode='"+subcode+"' and academicyear='"+accyear+"' and oddeven='"+oddeven+"' order by unit,part,qno,sdiv";
else
sql="select * from cfile where unit="+unit+" and subcode='"+subcode+"' and academicyear='"+accyear+"' and oddeven='"+oddeven+"' order by part,qno,sdiv";

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
error= "success"; 
}catch(Exception e){error=(e.toString()+count+i+sql);}
return error; 
}



public int cfilestatusgetid()
{
sql="";
int qsid=0;
try
{
DBConnectionManager connMgr;
connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  			Statement stmt = con.createStatement();
			ResultSet rs;
			PreparedStatement ps;

sql="select qsid from cfilestatus where academicyear='"+repin[3]+"' and oddeven='"+repin[4]+"' and subcode='"+repin[5]+"'";
rs = stmt.executeQuery(sql);
if(rs.next())
{
	qsid=rs.getInt(1);
}
rs.close();
             stmt.close();
					connMgr.freeConnection("xavier",con);
					error="...";
					}catch(Exception e){error=(e.toString());}
return qsid;	
}


public String cfileassessmentUpdate(int id)
{
try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;
					    PreparedStatement ps;
					    
					    
                  sql="update cfileassessment set qno=?, qstatus=?, qrem=? where id=?";
					ps=con.prepareStatement(sql);
					ps.setString(1,repin[4]);
					ps.setString(2,repin[5]);
					ps.setString(3,repin[6]);
					ps.setInt(4,id);
					int i = ps.executeUpdate();
					ps.close();
                    stmt.close();
					connMgr.freeConnection("xavier",con);
					error="success";
					}catch(Exception e){error=(e.toString());}
return error;
}

public String cfileassessmentDel(int id)
{

try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;
					    PreparedStatement ps;
                  sql="delete from cfileassessment where id=?";
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


public String cfileassessmentList(int qsid)
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
sql="select * from cfileassessment where qsid="+qsid+" order by qno";

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




			
public String QuestionView(int id)
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
sql="select * from cfile where id="+id;

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
error= "success"; 
}catch(Exception e){error=(e.toString()+count+i+sql);}
return error; 
}

public String QuestionList(int unit,String subcode,String accyear,String oddeven)
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
					    
//sql="select * from cfile where examno="+examno+" and choice="+choice+" and subcode='"+subcode+"' order by part,qno,opt";
sql="select * from cfile where unit="+unit+" and subcode='"+subcode+"' and academicyear='"+accyear+"' and oddeven='"+oddeven+"' order by part,qno,sdiv";

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
error= "success"; 
}catch(Exception e){error=(e.toString()+count+i+sql);}
return error; 
}

public String QuestionPrint(String exam,int unit,String subcode,String accyear,String oddeven,int parta,int partb,int partc)
{
int i=0;
int k=0;
count=0;
int f1=0;
int f2=0;
int f3=0;
int qno=0;
int p=0;
try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;
						PreparedStatement ps;

sql="select * from cfile where unit="+unit+" and exam1 is not null and subcode='"+subcode+"' and academicyear='"+accyear+"' and oddeven='"+oddeven+"' order by part,qno,sdiv";
rs = stmt.executeQuery(sql);
if(rs.next()) f1=1;
rs.close();

String semin="(1,3,5,7)";
if(oddeven.equals("ODD")) semin="(1,3,5,7)";
else
 semin="(2,4,6,8)";

sql="select semester,dname,sf,subjectcode,subjectname,abbr from syllabus s,subjectidentify si,department d where d.dno=si.dno and s.id=si.id and subjectcode='"+subcode+"' and academicyear='"+accyear+"' and semester in "+semin;
rs = stmt.executeQuery(sql);

count1=0;
while(rs.next())
{
	for(i=1;i<=6;i++)
	{
		report3[i-1][count1]=rs.getString(i);
	}
	count1++;
}
rs.close();

sql="select si.semester,dname,sf,subjectcode,subjectname,abbr,examno from syllabus s,subjectidentify si,department d,examyear ey where ey.year=academicyear "
+" and ey.semester=si.semester and ey.dno=si.dno and  d.dno=si.dno and s.id=si.id and subjectcode='"+subcode+"' and academicyear='"+accyear+"' and ey.exam='"+exam+"' and si.semester in "+semin;
rs = stmt.executeQuery(sql);

count2=0;
while(rs.next())
{
	for(i=1;i<=7;i++)
	{
		report4[i-1][count2]=rs.getString(i);
	}
	count2++;
}
rs.close();

if(count1!=count2) f3=1;

if((f1==0) && (f3==0))
{
for(i=1;i<=parta;i++)
{
f2=0;
sql="select top 1 qno,sdiv,qid,maxmarks,clevel,co from cfile where unit="+unit+" and subcode='"+subcode+"' and part=1 and exam1 is null and academicyear='"+accyear+"' and oddeven='"+oddeven+"' order by NEWID()"; 
rs = stmt.executeQuery(sql);
if(rs.next()) {
    qno=Integer.parseInt(rs.getString(1));
	for(k=2;k<=6;k++)
	{
		report1[k]=rs.getString(k);
	}
	 f2=1; 
}
rs.close();
if(f2==1)
{
sql="update cfile set exam1=?, exam1qno=?, exam1opt=0 where unit="+unit+" and qno=? and subcode=? and part=1 and academicyear=? and oddeven=?";
					ps=con.prepareStatement(sql);
					ps.setString(1,unit+"");
					ps.setInt(2,i);
					ps.setInt(3,qno);
					ps.setString(4,	subcode);
					ps.setString(5,	accyear);
					ps.setString(6,	oddeven);
					p = ps.executeUpdate();
					ps.close();
for(k=0;k<count2;k++)
{
sql="insert into markquestion (examno,qno,part,opt,sdiv,question,maxmarks,clevel,co,subcode,choice) values(?,?,?,?,?,?,?,?,?,?,1)";
					ps=con.prepareStatement(sql);
					ps.setString(1,report4[6][k]+"");
					ps.setInt(2,i);
					ps.setInt(3,1);
					ps.setInt(4,0);
					ps.setString(5,	report1[2]);
					ps.setString(6,	report1[3]);
					ps.setString(7,	report1[4]);
					ps.setString(8,	report1[5]);
					ps.setString(9,	report1[6]);
					ps.setString(10, subcode);
					p = ps.executeUpdate();
					ps.close();
}
}
}

for(i=1;i<=partb;i++)
{
f2=0;
sql="select top 1 qno,sdiv,qid,maxmarks,clevel,co from cfile where unit="+unit+" and subcode='"+subcode+"' and part=2 and exam1 is null and academicyear='"+accyear+"' and oddeven='"+oddeven+"' order by NEWID()"; 
rs = stmt.executeQuery(sql);
if(rs.next()) {
    qno=Integer.parseInt(rs.getString(1));
	for(k=2;k<=6;k++)
	{
		report1[k]=rs.getString(k);
	}
	 f2=1; 
}
rs.close();
if(f2==1)
{
sql="update cfile set exam1=?, exam1qno=?, exam1opt=1 where unit="+unit+" and qno=? and subcode=? and part=2 and academicyear=? and oddeven=?";
					ps=con.prepareStatement(sql);
					ps.setString(1,unit+"");
					ps.setInt(2,i+parta);
					ps.setInt(3,qno);
					ps.setString(4,	subcode);
					ps.setString(5,	accyear);
					ps.setString(6,	oddeven);
					p = ps.executeUpdate();
					ps.close();
for(k=0;k<count2;k++)
{
sql="insert into markquestion (examno,qno,part,opt,sdiv,question,maxmarks,clevel,co,subcode,choice) values(?,?,?,?,?,?,?,?,?,?,1)";
					ps=con.prepareStatement(sql);
					ps.setString(1,report4[6][k]+"");
					ps.setInt(2,i+parta);
					ps.setInt(3,2);
					ps.setInt(4,1);
					ps.setString(5,	report1[2]);
					ps.setString(6,	report1[3]);
					ps.setString(7,	report1[4]);
					ps.setString(8,	report1[5]);
					ps.setString(9,	report1[6]);
					ps.setString(10, subcode);
					p = ps.executeUpdate();
					ps.close();
}

}
f2=0;
sql="select top 1 qno,sdiv,qid,maxmarks,clevel,co from cfile where unit="+unit+" and subcode='"+subcode+"' and part=2 and exam1 is null and academicyear='"+accyear+"' and oddeven='"+oddeven+"' order by NEWID()"; 
rs = stmt.executeQuery(sql);
if(rs.next()) {
    qno=Integer.parseInt(rs.getString(1));
	for(k=2;k<=6;k++)
	{
		report1[k]=rs.getString(k);
	}
	 f2=1; 
}
rs.close();
if(f2==1)
{
sql="update cfile set exam1=?, exam1qno=?, exam1opt=2 where unit="+unit+" and qno=? and subcode=? and part=2 and academicyear=? and oddeven=?";
					ps=con.prepareStatement(sql);
					ps.setString(1,unit+"");
					ps.setInt(2,i+parta);
					ps.setInt(3,qno);
					ps.setString(4,	subcode);
					ps.setString(5,	accyear);
					ps.setString(6,	oddeven);
					p = ps.executeUpdate();
					ps.close();
for(k=0;k<count2;k++)
{
sql="insert into markquestion (examno,qno,part,opt,sdiv,question,maxmarks,clevel,co,subcode,choice) values(?,?,?,?,?,?,?,?,?,?,1)";
					ps=con.prepareStatement(sql);
					ps.setString(1,report4[6][k]+"");
					ps.setInt(2,i+parta);
					ps.setInt(3,2);
					ps.setInt(4,2);
					ps.setString(5,	report1[2]);
					ps.setString(6,	report1[3]);
					ps.setString(7,	report1[4]);
					ps.setString(8,	report1[5]);
					ps.setString(9,	report1[6]);
					ps.setString(10, subcode);
					p = ps.executeUpdate();
					ps.close();
}
					
}
}

for(i=1;i<=partc;i++)
{
f2=0;
sql="select top 1 qno,sdiv,qid,maxmarks,clevel,co from cfile where unit="+unit+" and subcode='"+subcode+"' and part=3 and exam1 is null and academicyear='"+accyear+"' and oddeven='"+oddeven+"' order by NEWID()"; 
rs = stmt.executeQuery(sql);
if(rs.next()) {
    qno=Integer.parseInt(rs.getString(1));
	for(k=2;k<=6;k++)
	{
		report1[k]=rs.getString(k);
	}
	 f2=1; 
}
rs.close();
if(f2==1)
{
sql="update cfile set exam1=?, exam1qno=?, exam1opt=1 where unit="+unit+" and qno=? and subcode=? and part=3 and academicyear=? and oddeven=?";
					ps=con.prepareStatement(sql);
					ps.setString(1,unit+"");
					ps.setInt(2,i+parta+partb);
					ps.setInt(3,qno);
					ps.setString(4,	subcode);
					ps.setString(5,	accyear);
					ps.setString(6,	oddeven);
					p = ps.executeUpdate();
					ps.close();
for(k=0;k<count2;k++)
{
sql="insert into markquestion (examno,qno,part,opt,sdiv,question,maxmarks,clevel,co,subcode,choice) values(?,?,?,?,?,?,?,?,?,?,1)";
					ps=con.prepareStatement(sql);
					ps.setString(1,report4[6][k]+"");
					ps.setInt(2,i+parta+partb);
					ps.setInt(3,3);
					ps.setInt(4,1);
					ps.setString(5,	report1[2]);
					ps.setString(6,	report1[3]);
					ps.setString(7,	report1[4]);
					ps.setString(8,	report1[5]);
					ps.setString(9,	report1[6]);
					ps.setString(10, subcode);
					p = ps.executeUpdate();
					ps.close();
}
					
}
f2=0;
sql="select top 1 qno,sdiv,qid,maxmarks,clevel,co from cfile where unit="+unit+" and subcode='"+subcode+"' and part=3 and exam1 is null and academicyear='"+accyear+"' and oddeven='"+oddeven+"' order by NEWID()"; 
rs = stmt.executeQuery(sql);
if(rs.next()) {
    qno=Integer.parseInt(rs.getString(1));
	for(k=2;k<=6;k++)
	{
		report1[k]=rs.getString(k);
	}
	 f2=1; 
}
rs.close();
if(f2==1)
{
sql="update cfile set exam1=?, exam1qno=?, exam1opt=2 where unit="+unit+" and qno=? and subcode=? and part=3 and academicyear=? and oddeven=?";
					ps=con.prepareStatement(sql);
					ps.setString(1,unit+"");
					ps.setInt(2,i+parta+partb);
					ps.setInt(3,qno);
					ps.setString(4,	subcode);
					ps.setString(5,	accyear);
					ps.setString(6,	oddeven);
					p = ps.executeUpdate();
					ps.close();
for(k=0;k<count2;k++)
{
sql="insert into markquestion (examno,qno,part,opt,sdiv,question,maxmarks,clevel,co,subcode,choice) values(?,?,?,?,?,?,?,?,?,?,1)";
					ps=con.prepareStatement(sql);
					ps.setString(1,report4[6][k]+"");
					ps.setInt(2,i+parta+partb);
					ps.setInt(3,3);
					ps.setInt(4,2);
					ps.setString(5,	report1[2]);
					ps.setString(6,	report1[3]);
					ps.setString(7,	report1[4]);
					ps.setString(8,	report1[5]);
					ps.setString(9,	report1[6]);
					ps.setString(10, subcode);
					p = ps.executeUpdate();
					ps.close();
}
}
}
}

sql="select * from cfile where unit="+unit+" and rtrim(exam1)='"+unit+"' and subcode='"+subcode+"' and academicyear='"+accyear+"' and oddeven='"+oddeven+"' order by part,exam1qno,exam1opt";
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

error= "success"; 
if(f3==1) error="T";
}catch(Exception e){error=(e.toString()+count+i+sql);}
return error; 
}

public String QuestionPrintModel(String exam,int unit,String subcode,String accyear,String oddeven,int parta,int partb,int partc)
{
int i=0;
int j=0;
int k=0;
int q=1;
count=0;
int f1=0;
int f2=0;
int f3=0;
int qno=0;
int p=0;
try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;
						PreparedStatement ps;

sql="select * from cfile where  exam2 is not null and subcode='"+subcode+"' and academicyear='"+accyear+"' and oddeven='"+oddeven+"' order by part,qno,sdiv";
rs = stmt.executeQuery(sql);
if(rs.next()) f1=1;
rs.close();

String semin="(1,3,5,7)";
if(oddeven.equals("ODD")) semin="(1,3,5,7)";
else
 semin="(2,4,6,8)";

sql="select semester,dname,sf,subjectcode,subjectname,abbr from syllabus s,subjectidentify si,department d where d.dno=si.dno and s.id=si.id and subjectcode='"+subcode+"' and academicyear='"+accyear+"' and semester in "+semin;
rs = stmt.executeQuery(sql);

count1=0;
while(rs.next())
{
	for(i=1;i<=6;i++)
	{
		report3[i-1][count1]=rs.getString(i);
	}
	count1++;
}
rs.close();

sql="select si.semester,dname,sf,subjectcode,subjectname,abbr,examno from syllabus s,subjectidentify si,department d,examyear ey where ey.year=academicyear "
+" and ey.semester=si.semester and ey.dno=si.dno and  d.dno=si.dno and s.id=si.id and subjectcode='"+subcode+"' and academicyear='"+accyear+"' and ey.exam='"+exam+"' and si.semester in "+semin;
rs = stmt.executeQuery(sql);

count2=0;
while(rs.next())
{
	for(i=1;i<=7;i++)
	{
		report4[i-1][count2]=rs.getString(i);
	}
	count2++;
}
rs.close();

if(count1!=count2) f3=1;

if((f1==0) && (f3==0))
{


for(j=1;j<=5;j++)
for(i=1;i<=parta/5;i++)
{
f2=0;
sql="select top 1 qno,sdiv,qid,maxmarks,clevel,co,unit from cfile where unit="+j+" and subcode='"+subcode+"' and part=1 and exam2 is null and academicyear='"+accyear+"' and oddeven='"+oddeven+"' order by NEWID()"; 
rs = stmt.executeQuery(sql);
if(rs.next()) {
    qno=Integer.parseInt(rs.getString(1));
	for(k=2;k<=7;k++)
	{
		report1[k]=rs.getString(k);
	}
	 f2=1; 
}
rs.close();
if(f2==1)
{
sql="update cfile set exam2=?, exam2qno=?, exam2opt=0 where unit="+j+" and qno=? and subcode=? and part=1 and academicyear=? and oddeven=? and unit=?";
					ps=con.prepareStatement(sql);
					ps.setString(1,unit+"");
					ps.setInt(2,q);
					ps.setInt(3,qno);
					ps.setString(4,	subcode);
					ps.setString(5,	accyear);
					ps.setString(6,	oddeven);
					ps.setString(7,	report1[7]);
					p = ps.executeUpdate();
					ps.close();
for(k=0;k<count2;k++)
{
sql="insert into markquestion (examno,qno,part,opt,sdiv,question,maxmarks,clevel,co,subcode,choice) values(?,?,?,?,?,?,?,?,?,?,1)";
					ps=con.prepareStatement(sql);
					ps.setString(1,report4[6][k]+"");
					ps.setInt(2,q);
					ps.setInt(3,1);
					ps.setInt(4,0);
					ps.setString(5,	report1[2]);
					ps.setString(6,	report1[3]);
					ps.setString(7,	report1[4]);
					ps.setString(8,	report1[5]);
					ps.setString(9,	report1[6]);
					ps.setString(10, subcode);
					p = ps.executeUpdate();
					ps.close();
}
}
q++;
}


for(j=1;j<=5;j++)
for(i=1;i<=partb/5;i++)
{
f2=0;
sql="select top 1 qno,sdiv,qid,maxmarks,clevel,co,unit from cfile where unit="+j+" and subcode='"+subcode+"' and part=2 and exam2 is null and academicyear='"+accyear+"' and oddeven='"+oddeven+"' order by NEWID()"; 
rs = stmt.executeQuery(sql);
if(rs.next()) {
    qno=Integer.parseInt(rs.getString(1));
	for(k=2;k<=7;k++)
	{
		report1[k]=rs.getString(k);
	}
	 f2=1; 
}
rs.close();
if(f2==1)
{
sql="update cfile set exam2=?, exam2qno=?, exam2opt=1 where unit="+j+" and qno=? and subcode=? and part=2 and academicyear=? and oddeven=? and unit=?";
					ps=con.prepareStatement(sql);
					ps.setString(1,unit+"");
					ps.setInt(2,q);
					ps.setInt(3,qno);
					ps.setString(4,	subcode);
					ps.setString(5,	accyear);
					ps.setString(6,	oddeven);
					ps.setString(7,	report1[7]);
					p = ps.executeUpdate();
					ps.close();
for(k=0;k<count2;k++)
{
sql="insert into markquestion (examno,qno,part,opt,sdiv,question,maxmarks,clevel,co,subcode,choice) values(?,?,?,?,?,?,?,?,?,?,1)";
					ps=con.prepareStatement(sql);
					ps.setString(1,report4[6][k]+"");
					ps.setInt(2,q);
					ps.setInt(3,2);
					ps.setInt(4,1);
					ps.setString(5,	report1[2]);
					ps.setString(6,	report1[3]);
					ps.setString(7,	report1[4]);
					ps.setString(8,	report1[5]);
					ps.setString(9,	report1[6]);
					ps.setString(10, subcode);
					p = ps.executeUpdate();
					ps.close();
}

}
f2=0;
sql="select top 1 qno,sdiv,qid,maxmarks,clevel,co,unit from cfile where unit="+j+" and subcode='"+subcode+"' and part=2 and exam2 is null and academicyear='"+accyear+"' and oddeven='"+oddeven+"' order by NEWID()"; 
rs = stmt.executeQuery(sql);
if(rs.next()) {
    qno=Integer.parseInt(rs.getString(1));
	for(k=2;k<=7;k++)
	{
		report1[k]=rs.getString(k);
	}
	 f2=1; 
}
rs.close();
if(f2==1)
{
sql="update cfile set exam2=?, exam2qno=?, exam2opt=2 where unit="+j+" and qno=? and subcode=? and part=2 and academicyear=? and oddeven=? and unit=?";
					ps=con.prepareStatement(sql);
					ps.setString(1,unit+"");
					ps.setInt(2,q);
					ps.setInt(3,qno);
					ps.setString(4,	subcode);
					ps.setString(5,	accyear);
					ps.setString(6,	oddeven);
					ps.setString(7,	report1[7]);
					p = ps.executeUpdate();
					ps.close();
for(k=0;k<count2;k++)
{
sql="insert into markquestion (examno,qno,part,opt,sdiv,question,maxmarks,clevel,co,subcode,choice) values(?,?,?,?,?,?,?,?,?,?,1)";
					ps=con.prepareStatement(sql);
					ps.setString(1,report4[6][k]+"");
					ps.setInt(2,q);
					ps.setInt(3,2);
					ps.setInt(4,2);
					ps.setString(5,	report1[2]);
					ps.setString(6,	report1[3]);
					ps.setString(7,	report1[4]);
					ps.setString(8,	report1[5]);
					ps.setString(9,	report1[6]);
					ps.setString(10, subcode);
					p = ps.executeUpdate();
					ps.close();
}
					
}
q++;
}

for(i=1;i<=partc;i++)
{
f2=0;
sql="select top 1 qno,sdiv,qid,maxmarks,clevel,co,unit from cfile where subcode='"+subcode+"' and part=3 and exam2 is null and clevel>2 and academicyear='"+accyear+"' and oddeven='"+oddeven+"' order by NEWID()"; 
rs = stmt.executeQuery(sql);
if(rs.next()) {
    qno=Integer.parseInt(rs.getString(1));
	for(k=2;k<=7;k++)
	{
		report1[k]=rs.getString(k);
	}
	 f2=1; 
}
rs.close();
if(f2==1)
{
sql="update cfile set exam2=?, exam2qno=?, exam2opt=1 where qno=? and subcode=? and part=3 and academicyear=? and oddeven=? and unit=?";
					ps=con.prepareStatement(sql);
					ps.setString(1,unit+"");
					ps.setInt(2,q);
					ps.setInt(3,qno);
					ps.setString(4,	subcode);
					ps.setString(5,	accyear);
					ps.setString(6,	oddeven);
					ps.setString(7,	report1[7]);
					p = ps.executeUpdate();
					ps.close();
for(k=0;k<count2;k++)
{
sql="insert into markquestion (examno,qno,part,opt,sdiv,question,maxmarks,clevel,co,subcode,choice) values(?,?,?,?,?,?,?,?,?,?,1)";
					ps=con.prepareStatement(sql);
					ps.setString(1,report4[6][k]+"");
					ps.setInt(2,q);
					ps.setInt(3,3);
					ps.setInt(4,1);
					ps.setString(5,	report1[2]);
					ps.setString(6,	report1[3]);
					ps.setString(7,	report1[4]);
					ps.setString(8,	report1[5]);
					ps.setString(9,	report1[6]);
					ps.setString(10, subcode);
					p = ps.executeUpdate();
					ps.close();
}
}
f2=0;
sql="select top 1 qno,sdiv,qid,maxmarks,clevel,co,unit from cfile where subcode='"+subcode+"' and part=3 and clevel>2 and exam2 is null and academicyear='"+accyear+"' and oddeven='"+oddeven+"' order by NEWID()"; 
rs = stmt.executeQuery(sql);
if(rs.next()) {
    qno=Integer.parseInt(rs.getString(1));
	for(k=2;k<=7;k++)
	{
		report1[k]=rs.getString(k);
	}
	 f2=1; 
}
rs.close();
if(f2==1)
{
sql="update cfile set exam2=?, exam2qno=?, exam2opt=2 where qno=? and subcode=? and part=3 and academicyear=? and oddeven=? and unit=?";
					ps=con.prepareStatement(sql);
					ps.setString(1,unit+"");
					ps.setInt(2,q);
					ps.setInt(3,qno);
					ps.setString(4,	subcode);
					ps.setString(5,	accyear);
					ps.setString(6,	oddeven);
					ps.setString(7,	report1[7]);
					p = ps.executeUpdate();
					ps.close();
for(k=0;k<count2;k++)
{
sql="insert into markquestion (examno,qno,part,opt,sdiv,question,maxmarks,clevel,co,subcode,choice) values(?,?,?,?,?,?,?,?,?,?,1)";
					ps=con.prepareStatement(sql);
					ps.setString(1,report4[6][k]+"");
					ps.setInt(2,q);
					ps.setInt(3,3);
					ps.setInt(4,2);
					ps.setString(5,	report1[2]);
					ps.setString(6,	report1[3]);
					ps.setString(7,	report1[4]);
					ps.setString(8,	report1[5]);
					ps.setString(9,	report1[6]);
					ps.setString(10, subcode);
					p = ps.executeUpdate();
					ps.close();
}
}
q++;
}
}

sql="select * from cfile where rtrim(exam2)='"+unit+"' and subcode='"+subcode+"' and academicyear='"+accyear+"' and oddeven='"+oddeven+"' order by part,exam2qno,exam2opt";
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

error= "success"; 
if(f3==1) error="T";
}catch(Exception e){error=(e.toString()+count+i+sql);}
return error; 
}

public String QuestionStatus()
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
+"left join (select ltrim(rtrim(login)) s,count(*) cnt from markquestion where regulation='"+repin[1]+"' group by login) b on a.s=b.s order by c,a.s";
else
sql="select a.s,name,isnull(cnt,0) c from (select ltrim(rtrim(staffid)) s,name from staff  where active=1 and category='Teaching' and dno in ("+repin[0]+")) a "
+"left join (select ltrim(rtrim(login)) s,count(*) cnt from markquestion where regulation='"+repin[1]+"' group by login) b on a.s=b.s order by c,a.s";

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

public String MarkList(int examno,String subcode,int rollno)
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
					    
sql="select a.id,qno,part,opt,sdiv,question,maxmarks,isnull(marks,-3),isnull(marka,-3) from "
+" (select id,qno,part,opt,sdiv,question,maxmarks,examno,subcode from markquestion where subcode='"+subcode+"' and examno="+examno+" ) a"
+" left outer join (select qid,marks from markcopo where rollno="+rollno+") as b on a.id=b.qid "
+" left outer join (select mark,marka,examno,subjectcode from marka where rollno="+rollno+") as c "
+" on c.examno=a.examno and c.subjectcode=a.subcode order by part,qno,opt";



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


public String ViewMark(String subjectcode,String exam,int order,int dno,String examyear,int semester)
		    {
error="success";
	DBConnectionManager connMgr=null; Connection con =null;
	try
	{
                       connMgr = DBConnectionManager.getInstance(); 
                       con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;
					count=0;
					quer="SELECT stud.rollno, stud.name, max(mark.mark),max(examyear.examno) FROM stud,student,mark, examyear WHERE stud.rollno=mark.rollno and stud.rollno=student.rollno and student.active=1 And examyear.examno=mark.examno And stud.department="+dno+" and examyear.semester="+semester+" and examyear.exam='"+exam+"' and examyear.year=stud.academicyear and examyear.year='"+examyear+"' And mark.subjectcode='"+subjectcode+"' group by stud.rollno,stud.name order BY stud.rollno";
					if(order==1)
					quer="SELECT stud.rollno, stud.name, max(mark.mark),max(examyear.examno) FROM stud,student,mark, examyear WHERE stud.rollno=mark.rollno and stud.rollno=student.rollno and student.active=1 And examyear.examno=mark.examno And stud.department="+dno+" and examyear.semester="+semester+" and examyear.exam='"+exam+"' and examyear.year=stud.academicyear and examyear.year='"+examyear+"' And mark.subjectcode='"+subjectcode+"' group by stud.rollno,stud.name order BY stud.rollno";
					else if(order==2)
					quer="SELECT stud.rollno, stud.name, mark.mark,max(examyear.examno) FROM stud,student, mark, examyear WHERE stud.rollno=mark.rollno and stud.rollno=student.rollno and student.active=1 And examyear.examno=mark.examno And stud.department="+dno+" and examyear.semester="+semester+" and examyear.exam='"+exam+"' and examyear.year=stud.academicyear and examyear.year='"+examyear+"' And mark.subjectcode='"+subjectcode+"'ORDER BY mark.mark,stud.rollno";
					else if(order==3)
					quer="SELECT stud.rollno, stud.name, mark.mark,max(examyear.examno) FROM stud,student, mark, examyear WHERE stud.rollno=mark.rollno and stud.rollno=student.rollno and student.active=1 And examyear.examno=mark.examno And stud.department="+dno+" and examyear.semester="+semester+" and examyear.exam='"+exam+"' and examyear.year=stud.academicyear and examyear.year='"+examyear+"' And mark.subjectcode='"+subjectcode+"'ORDER BY mark.mark DESC,stud.rollno";
					else
					quer="SELECT stud.rollno, stud.name, max(mark.mark),max(examyear.examno) FROM stud,student,mark, examyear WHERE stud.rollno=mark.rollno and stud.rollno=student.rollno and student.active=1 And examyear.examno=mark.examno And stud.department="+dno+" and examyear.semester="+semester+" and examyear.exam='"+exam+"' and examyear.year=stud.academicyear and examyear.year='"+examyear+"' And mark.subjectcode='"+subjectcode+"' group by stud.rollno,stud.name order BY stud.rollno";
					rs = stmt.executeQuery(quer);
					while(rs.next())
						{
							report[0][count]=rs.getString(1);
							report[1][count]=rs.getString(2);
							report[2][count]=rs.getString(3);
							report[3][count]=rs.getString(4);
  						    count++;	
						}
					rs.close();
					count1=count;
					if(count==0)
					{

					quer="select rollno,name from student where rollno in (select rollno from subjectelective where subcode='"+subjectcode+"' "
+"and id in (select id from subjectidentify where academicyear='"+examyear+"' and dno="+dno+" and semester="+semester+")) and active=1 order by rollno";
					rs = stmt.executeQuery(quer);
					while(rs.next())
					{
							report[0][count]=rs.getString(1);
							report[1][count]=rs.getString(2);
						count++;	
					}
					rs.close();
					}

					if(count==0)
					{
					quer="select stud.rollno,stud.name from stud,student where stud.rollno=student.rollno and "
                    +" academicyear='"+examyear+"' and department="+dno+" and year=(("+semester+"+1)/2) and active=1 order by stud.rollno";

					rs = stmt.executeQuery(quer);
					while(rs.next())
					{
							report[0][count]=rs.getString(1);
							report[1][count]=rs.getString(2);
						count++;	
					}
					rs.close();
					}
					
quer="select isnull(max(qno),0) from nbasubjectco where subcode='"+subjectcode+"' and subid in (select id from subjectidentify where academicyear='"+examyear+"' and dno="+dno+" and semester="+semester+")";
					rs = stmt.executeQuery(quer);
					if(rs.next())
					{
							cocount=rs.getInt(1);
					}
					rs.close();
					
					for(int i=0;i<count; i++)
					{
					for(int j=1;j<=6; j++)
					{
					quer="select isnull(sum(marks),0),isnull(sum(maxmarks),0) from markquestion m,markcopo c where m.id=c.qid "
						+" and rollno="+report[0][i]+" and subcode='"+subjectcode+"' and examno="+report[3][i]+" and marks>=0 and clevel="+j;
					rs = stmt.executeQuery(quer);
					if(rs.next())
					{
							report[4+j][i]=rs.getString(1);
							report[10+j][i]=rs.getString(2);
					}
					rs.close();
					
					}
					for(int j=1;j<=cocount; j++)
					{
				
					quer="select isnull(sum(marks),0),isnull(sum(maxmarks),0) from markquestion m,markcopo c where m.id=c.qid "
						+" and rollno="+report[0][i]+" and subcode='"+subjectcode+"' and examno="+report[3][i]+" and marks>=0 and co like '%"+j+"%'";
					rs = stmt.executeQuery(quer);
					if(rs.next())
					{
							report[16+j][i]=rs.getString(1);
							report[21+j][i]=rs.getString(2);
					}
					rs.close();
					}						
					}
					stmt.close();
}catch(Exception e){error=(e.toString()+count+quer);}
finally{connMgr.freeConnection("xavier",con);
return error; 
}
}

public String DAttExamList()
{
int i=0;
count=0;
int ttdno=0;

try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;
try{
ttdno=Integer.parseInt(repin[1]);
if(repin[0].equals("2018 -- 2019") && repin[1].equals("27") && repin[2].equals("1") ) ttdno=96;
}catch(Exception e){}
					    
sql="select * from examyear,examdate where examyear.examno=examdate.examno and subcode='"+repin[3]+"' and year='"+repin[0]+"' and semester="+repin[2]+"  and dno="+ttdno+" and status='C' order by exam";

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
try{
ttdno=Integer.parseInt(repin[1]);
//if(ttdno>90) ttdno=ttdno-90;
if(ttdno==91) ttdno=1;
if(ttdno==92) ttdno=2;
if(ttdno==93) ttdno=3;
if(ttdno==94) ttdno=4;
if(ttdno==95) ttdno=5;
if(ttdno==96) ttdno=8;
if(ttdno==97) ttdno=14;
if(ttdno==98) ttdno=17;
if(ttdno==99) ttdno=27;

if(repin[0].equals("2018 -- 2019") && repin[1].equals("27") && repin[2].equals("1") ) ttdno=8;
}catch(Exception e){}
/*
sql="select count(*) from umarks,stud,registerno where stud.rollno=registerno.rollno and umarks.regno=registerno.registerno "
+" and academicyear='"+repin[0]+"' and department="+repin[1]+" and stud.year="+((Integer.parseInt(repin[2])/2)+1)+" and subcode='"+repin[3]+"'";
*/
sql="select count(*) from umarks,stud,registerno where stud.rollno=registerno.rollno and umarks.regno=registerno.registerno "
+" and academicyear='"+repin[0]+"' and department="+ttdno+" and stud.year="+((Integer.parseInt(repin[2])/2)+1)+" and subcode='"+repin[3]+"'";

rs = stmt.executeQuery(sql);

if(rs.next())
{
if(rs.getString(1).equals("0"))
	report[10][0]="-";
else
	report[10][0]="University Examination";
}
else
	report[10][0]="-";
rs.close();

stmt.close();
connMgr.freeConnection("xavier",con);
error= "success"+ttdno; 
}catch(Exception e){report[10][0]="-"; error=(e.toString()+count+i+sql);}
return error; 
}

public String DAttViewMark(String subjectcode,int dno,String examyear,int semester,int cc,int attaintype)
		    {
error="success";
String opening="";
String closing="";

int tp=0;
int ap=0;

int jk=0;

String tot="0";
String att="0";
int ttdno=0;
int tttdno=0;

DecimalFormat df = new DecimalFormat("#");
	DBConnectionManager connMgr=null; Connection con =null;
	try
	{
                       connMgr = DBConnectionManager.getInstance(); 
                       con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;
					count=0;

try{
ttdno=Integer.parseInt(repin[1]);
if(repin[0].equals("2018 -- 2019") && repin[1].equals("27") && repin[2].equals("1") ) ttdno=96;
}catch(Exception e){}

quer="select opening,closing from attendance.dbo.reopening where academicyear='"+examyear+"' and semester="+semester+" and dno="+ttdno;

					rs = stmt.executeQuery(quer);
					if(rs.next()){
						 opening=rs.getString(1);					
						 closing=rs.getString(2);					
					}
					rs.close();


try{
ttdno=Integer.parseInt(repin[1]);
//if(ttdno>90) ttdno=ttdno-90;
tttdno=ttdno;
if(ttdno==91) ttdno=1;
if(ttdno==92) ttdno=2;
if(ttdno==93) ttdno=3;
if(ttdno==94) ttdno=4;
if(ttdno==95) ttdno=5;
if(ttdno==96) ttdno=8;
if(ttdno==97) ttdno=14;
if(ttdno==98) ttdno=17;
if(ttdno==99) ttdno=27;

if(repin[0].equals("2018 -- 2019") && repin[1].equals("27") && repin[2].equals("1") ){ ttdno=27; tttdno=96;}

}catch(Exception e){}


					quer="select rollno,name from student where rollno in (select rollno from subjectelective where subcode='"+subjectcode+"' "
+"and id in (select id from subjectidentify where academicyear='"+examyear+"' and dno="+ttdno+" and semester="+semester+")) and active=1 order by rollno";
					rs = stmt.executeQuery(quer);
					while(rs.next())
					{
							report3[0][count]=rs.getString(1);
							report3[1][count]=rs.getString(2);
							report3[40][count]=report3[0][count];
							report3[41][count]=report3[1][count];
						count++;	
					}
					rs.close();

					if(count==0)
					{
try{
						
					quer="select stud.rollno,stud.name from stud,student where stud.rollno=student.rollno and "
                    +" academicyear='"+examyear+"' and department="+ttdno+" and year=(("+semester+"+1)/2) and active=1 order by stud.rollno";

					rs = stmt.executeQuery(quer);
					while(rs.next())
					{
							report3[0][count]=rs.getString(1);
							report3[1][count]=rs.getString(2);
							report3[40][count]=report3[0][count];
							report3[41][count]=report3[1][count];
						count++;	
					}
}catch(Exception e){}

					rs.close();
					}
					
					for(int i=0;i<count; i++)
					{

try{						sql="select count(*) from attendance.dbo.log where date between '"+opening+"' and '"+closing+"' and subcode='"+subjectcode+"' and id in(select id from subjectidentify where semester="+semester+" and dno="+tttdno+" and academicyear='"+examyear+"')";
  						rs = stmt.executeQuery(sql);
						if(rs.next())
						{
						tp=rs.getInt(1);
						}
						rs.close();
                        sql="select count(*) from attendance.dbo.attendance a,attendance.dbo.log b where a.date=b.date  "+
							"and rollno="+report3[0][i]+" and a.date between '"+opening+"' and '"+closing+"' and subcode='"+subjectcode+"' "+
							"and hours like '%'+ltrim(rtrim(str(period)))+'%' and id in(select id from subjectidentify where semester="+semester+" and dno="+tttdno+" and academicyear='"+examyear+"')";
  						rs = stmt.executeQuery(sql);
						if(rs.next())
						{
						ap=tp-rs.getInt(1);
						}
						rs.close();
						tot=tp+"";
						att=ap+"";
						report3[2][i]=df.format((Float.parseFloat(att)/Float.parseFloat(tot))*100)+"";
						report3[3][i]="0";
}catch(Exception e){}

					for(int j=0;j<=cc; j++)
					{
try{
					quer="select isnull(sum(marks),0),isnull(sum(maxmarks),0) from markquestion m,markcopo c,examyear e where e.examno=m.examno and m.id=c.qid "
						+" and year='"+examyear+"' and semester="+semester+" and dno="+tttdno+" and rollno="+report3[0][i]+" and subcode='"+subjectcode+"' and marks>=0 and co like '%"+j+"%'";
					rs = stmt.executeQuery(quer);
					if(rs.next())
					{
							att=rs.getString(1);
							tot=rs.getString(2);
							if(att.equals("0"))
							report3[cc+j][i]="0";	
								else
							report3[cc+j][i]=df.format((Float.parseFloat(att)/Float.parseFloat(tot))*100)+"";
							if(attaintype==0)
							report3[(cc*2)+j][i]=df.format((Float.parseFloat(report3[cc+j][i])*0.4 +Float.parseFloat(report3[2][i])*0.1+Float.parseFloat(report3[3][i])*0.5))+"";
							else
							report3[(cc*2)+j][i]=df.format((Float.parseFloat(report3[cc+j][i])*0.4 +Float.parseFloat(report3[3][i])*0.6))+"";
							jk=Integer.parseInt(report3[(cc+2)+j][i]);
							if(jk<40) report3[(cc*3)+j][i]="1";
							else if(jk<60) report3[(cc*3)+j][i]="2";
							else report3[(cc*3)+j][i]="3";
					}
					
}catch(Exception e){}
					rs.close();
					
					}						
					}
					count1=count;
					stmt.close();
}catch(Exception e){error=(e.toString()+count+quer);}
finally{connMgr.freeConnection("xavier",con);
return error; 
}
}

public String nbadalaiInsert(int jj)
{
sql="";
int found=1;
try
{
DBConnectionManager connMgr;
connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  			Statement stmt = con.createStatement();
			ResultSet rs;
			PreparedStatement ps;

for(int i=0;i<jj;i++)
{
i1=0;
f1=0;
try
{
i1=Integer.parseInt(report[1][i]);
f1=Float.parseFloat(report[4][i]);
}
catch(Exception e){}
try{
                  sql="delete nbadalai where outcome=? and subid=? and subcode=?";
					ps=con.prepareStatement(sql);
					ps.setString(1,report[3][i]);
					ps.setInt(2,i1);
					ps.setString(3,report[2][i]);
					int j = ps.executeUpdate();
					ps.close();

}
catch(Exception e){found=0;}
try{

	                sql="insert into nbadalai(subid,subcode,outcome,target,observations,actions) values(?,?,?,?,?,?)";
					ps=con.prepareStatement(sql);
					ps.setInt(1,i1);
					ps.setString(2,report[2][i]);
					ps.setString(3,report[3][i]);
					ps.setFloat(4,f1);
					ps.setString(5,report[5][i]);
					ps.setString(6,report[6][i]);
					int j = ps.executeUpdate();
					ps.close();
}
catch(Exception e){found=0;}
					
}				
     
                    stmt.close();
					connMgr.freeConnection("xavier",con);
					error="...";
					}catch(Exception e){error=(e.toString());}
return error;	
}




public String nbadalaiList(int subid,String subcode)
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
		

sql="select * from nbadalai where subid="+subid+" and subcode='"+subcode+"' order by outcome";

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

public String coexsurvey(int sid)
{
int i=0;
int jk=0;
count=0;
try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;
		

sql="select questionno,(sum(ansa)*0+sum(ansb)*25+sum(ansc)*50+sum(ansd)*75+sum(anse)*100)/(sum(ansa)+sum(ansb)+sum(ansc)+sum(ansd)+sum(anse)) "
+" from stafffeedback where subjectcode='"+repin[5]+"' and id="+sid+" and  oddeven='"+repin[6]+"' and staffid='COEXS' "
+" group by questionno,staffid order by questionno";

rs = stmt.executeQuery(sql);

while(rs.next())
{
		report[0][count]=rs.getString(1);
		report[1][count]=rs.getString(2);
		try
         {
							jk=Integer.parseInt(report[1][count]);
							if(jk<40) report[2][count]="1";
							else if(jk<60) report[2][count]="2";
							else report[2][count]="3";
		
          }catch(Exception e){report[2][count]="";}
	count++;
}
rs.close();

stmt.close();
connMgr.freeConnection("xavier",con);
error= "success"; 
}catch(Exception e){error=(e.toString()+count+i+sql);}
return error; 
}

public String getsubject(String accyear, String subcode)
{
sql="";
int f=0;
error="success";

try
{
DBConnectionManager connMgr;
connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  			Statement stmt = con.createStatement();
			ResultSet rs;
			PreparedStatement ps;

sql="select max(subjectname) from syllabus s,subjectidentify si where s.id=si.id and academicyear='"+accyear+"' and subjectcode='"+subcode+"'";
rs = stmt.executeQuery(sql);
if(rs.next()) error=rs.getString(1);
rs.close();
stmt.close();
connMgr.freeConnection("xavier",con);
}catch(Exception e){error+=(e.toString());}
return error;	
}

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




	
	