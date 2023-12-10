package sxcce;
import java.sql.*;
import java.util.StringTokenizer;
import com.jspsmart.upload.*;
import java.util.Date;
import java.io.*;
import dd.DBConnectionManager;

public class markcopo
	{
				String rep[] = new String[1000];
				String repin[] = new String[30];
				String report[][] = new String[30][10000];
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
int cid;
String sql="";	
String quer="";	
String error="";			
String academicyear="";		

String date1="";	
String date2="";	

public String nbapeotomissionInsert(int ddno, String accyear)
{
String sql="";
error="...";

try
{
DBConnectionManager connMgr;
connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  			Statement stmt = con.createStatement();
			ResultSet rs;
			PreparedStatement ps;


for(int i=0;i<count;i++)
for(int j=0;j<count1;j++)
{
try
{
sql="update nbapeotomission set corr=? where dno="+ddno+" and peono="+report1[i]+" and mno="+report2[j]+" and academicyear='"+accyear+"'";
					ps=con.prepareStatement(sql);
					ps.setString(1,report[i][j]);
					int k = ps.executeUpdate();
					ps.close();
}catch(Exception e){error+=e.toString();}
//error+=sql+report[i][j];
try
{
sql="insert into nbapeotomission(dno,academicyear,peono,mno,corr) values(?,?,?,?,?)";
					ps=con.prepareStatement(sql);
					ps.setInt(1,ddno);
					ps.setString(2,accyear);
					ps.setString(3,report1[i]);
					ps.setString(4,report2[j]);
					ps.setString(5,report[i][j]);
					int k = ps.executeUpdate();
					ps.close();
}catch(Exception e){}

}
                    stmt.close();
					connMgr.freeConnection("xavier",con);
					}catch(Exception e){error=(error+=e.toString());}
return error;	
}


public String nbapeotomissionList(int ddno)
{
count=0;
count1=0;
try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;
					    
sql="select qno from nbadeptvision where dno="+ddno+" and type=3 and academicyear in (select max(academicyear) from nbadeptvision where dno="+ddno+" ) order by qno";
rs = stmt.executeQuery(sql);
while(rs.next())
{
	report1[count]=rs.getString(1);
	count++;
}
rs.close();

sql="select qno from nbadeptvision where dno="+ddno+" and type=2 and academicyear in (select max(academicyear) from nbadeptvision where dno="+ddno+" ) order by qno";
rs = stmt.executeQuery(sql);
while(rs.next())
{
	report2[count1]=rs.getString(1);
	count1++;
}
rs.close();
report2[count1]="0";
count1++;

for(int i=0;i<count;i++)
for(int j=0;j<count1;j++)
{
try
{
sql="select corr from nbapeotomission where dno="+ddno+" and peono="+report1[i]+" and mno="+report2[j]+" and academicyear in (select max(academicyear) from nbapeotomission where dno="+ddno+")";
rs = stmt.executeQuery(sql);
if(rs.next())
{
	report[i][j]=rs.getString(1);
}
else
	report[i][j]="0";
rs.close();
}catch(Exception e){report[i][j]="0";}

}

stmt.close();
connMgr.freeConnection("xavier",con);
error= "success"; 
}catch(Exception e){error=(e.toString()+count+count1+sql);}
return error; 
}

public String nbapsotopoInsert(int ddno, String accyear)
{
String sql="";
error="...";

try
{
DBConnectionManager connMgr;
connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  			Statement stmt = con.createStatement();
			ResultSet rs;
			PreparedStatement ps;


for(int i=0;i<count;i++)
for(int j=0;j<count1;j++)
{
try
{
sql="update nbapsotopo set corr=? where dno="+ddno+" and psono="+report1[i]+" and pono="+report2[j]+" and academicyear='"+accyear+"'";
					ps=con.prepareStatement(sql);
					ps.setString(1,report[i][j]);
					int k = ps.executeUpdate();
					ps.close();
}catch(Exception e){error+=e.toString();}
//error+=sql+report[i][j];
try
{
sql="insert into nbapsotopo(dno,academicyear,psono,pono,corr) values(?,?,?,?,?)";
					ps=con.prepareStatement(sql);
					ps.setInt(1,ddno);
					ps.setString(2,accyear);
					ps.setString(3,report1[i]);
					ps.setString(4,report2[j]);
					ps.setString(5,report[i][j]);
					int k = ps.executeUpdate();
					ps.close();
}catch(Exception e){}

}
                    stmt.close();
					connMgr.freeConnection("xavier",con);
					}catch(Exception e){error=(error+=e.toString());}
return error;	
}


public String nbapsotopoList(int ddno)
{
count=0;
count1=0;
try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;
					    
sql="select qno from nbadeptvision where dno="+ddno+" and type=5 and academicyear in (select max(academicyear) from nbadeptvision where dno="+ddno+" ) order by qno";
rs = stmt.executeQuery(sql);
while(rs.next())
{
	report1[count]=rs.getString(1);
	count++;
}
rs.close();

sql="select qno from nbadeptvision where dno="+ddno+" and type=4 and academicyear in (select max(academicyear) from nbadeptvision where dno="+ddno+" ) order by qno";
rs = stmt.executeQuery(sql);
while(rs.next())
{
	report2[count1]=rs.getString(1);
	count1++;
}
rs.close();

for(int i=0;i<count;i++)
for(int j=0;j<count1;j++)
{
try
{
sql="select corr from nbapsotopo where dno="+ddno+" and psono="+report1[i]+" and pono="+report2[j]+" and academicyear in (select max(academicyear) from nbapsotopo where dno="+ddno+")";
rs = stmt.executeQuery(sql);
if(rs.next())
{
	report[i][j]=rs.getString(1);
}
else
	report[i][j]="0";
rs.close();
}catch(Exception e){report[i][j]="0";}

}

stmt.close();
connMgr.freeConnection("xavier",con);
error= "success"; 
}catch(Exception e){error=(e.toString()+count+count1+sql);}
return error; 
}


public String nbapeotopoInsert(int ddno, String accyear)
{
String sql="";
//nbapeotopoList(ddno);
error="...";

try
{
DBConnectionManager connMgr;
connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  			Statement stmt = con.createStatement();
			ResultSet rs;
			PreparedStatement ps;


for(int i=0;i<count;i++)
for(int j=0;j<count1;j++)
{
try
{
sql="update nbapeotopo set corr=? where dno="+ddno+" and peono="+report1[i]+" and pono="+report2[j]+" and academicyear='"+accyear+"'";
					ps=con.prepareStatement(sql);
					ps.setString(1,report[i][j]);
					int k = ps.executeUpdate();
					ps.close();
}catch(Exception e){error+=e.toString();}
//error+=sql+report[i][j];
try
{
sql="insert into nbapeotopo(dno,academicyear,peono,pono,corr) values(?,?,?,?,?)";
					ps=con.prepareStatement(sql);
					ps.setInt(1,ddno);
					ps.setString(2,accyear);
					ps.setString(3,report1[i]);
					ps.setString(4,report2[j]);
					ps.setString(5,report[i][j]);
					int k = ps.executeUpdate();
					ps.close();
}catch(Exception e){}

}
                    stmt.close();
					connMgr.freeConnection("xavier",con);
					}catch(Exception e){error=(error+=e.toString());}
return error;	
}


public String nbapeotopoList(int ddno)
{
count=0;
count1=0;
try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;
					    
sql="select qno from nbadeptvision where dno="+ddno+" and type=3 and academicyear in (select max(academicyear) from nbadeptvision where dno="+ddno+" ) order by qno";
rs = stmt.executeQuery(sql);
while(rs.next())
{
	report1[count]=rs.getString(1);
	count++;
}
rs.close();

sql="select qno from nbadeptvision where dno="+ddno+" and type=4 and academicyear in (select max(academicyear) from nbadeptvision where dno="+ddno+" ) order by qno";
rs = stmt.executeQuery(sql);
while(rs.next())
{
	report2[count1]=rs.getString(1);
	count1++;
}
rs.close();

for(int i=0;i<count;i++)
for(int j=0;j<count1;j++)
{
try
{
sql="select corr from nbapeotopo where dno="+ddno+" and peono="+report1[i]+" and pono="+report2[j]+" and academicyear in (select max(academicyear) from nbapeotopo where dno="+ddno+")";
rs = stmt.executeQuery(sql);
if(rs.next())
{
	report[i][j]=rs.getString(1);
}
else
	report[i][j]="0";
rs.close();
}catch(Exception e){report[i][j]="0";}

}

stmt.close();
connMgr.freeConnection("xavier",con);
error= "success"; 
}catch(Exception e){error=(e.toString()+count+count1+sql);}
return error; 
}

public String nbadeptvisionInsert()
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

if(Integer.parseInt(repin[1])>0)
{
                    sql="insert into nbadeptvision(dno,academicyear,type,qno,statement) values(?,?,?,?,?)";
					ps=con.prepareStatement(sql);
					ps.setString(1,repin[1]);
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

public String nbadeptvisionUpdate(int id)
{
try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;
					    PreparedStatement ps;
					    
                  sql="update nbadeptvision set dno=?, academicyear=?, type=?, qno=?, statement=? where id=?";
					ps=con.prepareStatement(sql);
					ps.setString(1,repin[1]);
					ps.setString(2,repin[2]);
					ps.setString(3,repin[3]);
					ps.setString(4,repin[4]);
					ps.setString(5,repin[5]);
					ps.setInt(6,id);
					int i = ps.executeUpdate();
					ps.close();
                    stmt.close();
					connMgr.freeConnection("xavier",con);
					error="success";
					}catch(Exception e){error=(e.toString());
					}
					
		return error;
}



public String nbadeptvisionDel(int id)
{

try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;
					    PreparedStatement ps;
                  sql="delete from nbadeptvision where id=?";
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


public String nbadeptvisionView(int id)
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
sql="select * from nbadeptvision where id="+id;

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

public String nbadeptvisionList(int ddno)
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
					    
sql="select * from nbadeptvision where dno="+ddno+" and academicyear in (select max(academicyear) from nbadeptvision where dno="+ddno+" ) order by type,qno";

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


public String QuestionInsert()
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

if(Integer.parseInt(repin[1])>0)
{
                    sql="insert into markquestion(examno,qno,part,opt,sdiv,question,maxmarks,clevel,co,po,pso,subcode,choice) values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
					ps=con.prepareStatement(sql);
					ps.setString(1,repin[1]);
					ps.setString(2,repin[2]);
					ps.setString(3,repin[3]);
					ps.setString(4,repin[4]);
					ps.setString(5,repin[5]);
					ps.setString(6,repin[6]);
					ps.setString(7,repin[7]);
					ps.setString(8,repin[8]);
					ps.setString(9,repin[9]);
					ps.setString(10,repin[10]);
					ps.setString(11,repin[11]);
					ps.setString(12,repin[12]);
					ps.setString(13,repin[13]);
					int j = ps.executeUpdate();
					ps.close();
}				
     
                    stmt.close();
					connMgr.freeConnection("xavier",con);
					error="...";
					}catch(Exception e){error=(e.toString());}
return error;	
}

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
					    
                  sql="update markquestion set qno=?, part=?, opt=?, sdiv=?, question=?, maxmarks=?, clevel=?, co=?, po=?, pso=? where id=?";
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
					ps.setInt(11,id);
					int i = ps.executeUpdate();
					ps.close();
                    stmt.close();
					connMgr.freeConnection("xavier",con);
					error="success";
					}catch(Exception e){error=(e.toString());
					}
					
		return error;
}

public String QuestionUpload(int id)
{
try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;
					    PreparedStatement ps;
			    
                  sql="update markquestion set fname1=? where id=?";
					ps=con.prepareStatement(sql);
					ps.setString(1,repin[14]);
					ps.setInt(2,id);
					int i = ps.executeUpdate();
					ps.close();
                    stmt.close();
					connMgr.freeConnection("xavier",con);
					error="success";
					}catch(Exception e){error=sql+repin[11]+id+repin[1]+(e.toString());		}
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
                  sql="delete from markquestion where id=?";
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

public String QuestionStaffList(int examno,String subcode,int choice)
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
sql="select * from markquestion where examno="+examno+" and choice="+choice+" and subcode='"+subcode+"' order by part,qno,opt";

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
sql="select * from markquestion where id="+id;

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

public String QuestionList(int examno,String subcode,int choice)
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
					    
sql="select * from markquestion where examno="+examno+" and choice="+choice+" and subcode='"+subcode+"' order by part,qno,opt";

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
					
					quer="select isnull(sum(marks),0),isnull(sum(maxmarks),0) from markquestion m,markcopo c where m.id=c.qid "
						+" and rollno="+report[0][i]+" and subcode='"+subjectcode+"' and examno="+report[3][i]+" and marks>=0 and co like '%"+j+"%'";
					rs = stmt.executeQuery(quer);
					if(rs.next())
					{
							report[16+j][i]=rs.getString(1);
							report[22+j][i]=rs.getString(2);
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


public void setrep(String[] rep) {this.rep=rep;}  public String[] getrep() {	return this.rep;}
public void setrepin(String[] repin) {this.repin=repin;}  public String[] getrepin() {	return this.repin;}
public void setreport(String[][] report) {this.report=report;}  public String[][] getreport() {	return this.report;}
public void setreport1(String[] report1) {this.report1=report1;}  public String[] getreport1() {	return this.report1;}
public void setreport2(String[] report2) {this.report2=report2;}  public String[] getreport2() {	return this.report2;}
public void setcount(int count) {this.count=count;} public int getcount(){	return this.count;}
public void setcount1(int count1) {this.count1=count1;} public int getcount1(){	return this.count1;}
public void setfname(String fname) { this.fname = fname; } public String getfname() { return this.fname; } 
public void setpname(String pname) { this.pname = pname; } public String getpname() { return this.pname; } 
public void setacademicyear(String academicyear) { this.academicyear = academicyear; } public String getacademicyear() { return this.academicyear; } 



}




	
	