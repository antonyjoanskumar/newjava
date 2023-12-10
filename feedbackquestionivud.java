package sxcce;
import java.sql.*;
import dd.DBConnectionManager;
public class feedbackquestionivud
	{
		String semester="";
		String academicyear="";
		String category="";
		String staffid="";
		int questionno[] = new int[100];
		String question[] = new String[100];
		String ansa[] = new String[100];
		String ansb[] = new String[100];
		String ansc[] = new String[100];
		String ansd[] = new String[100];		
		String anse[] = new String[100];		
		int objective[] = new int[100];
		int count=0;
		int rollno=1;
		String subcode="";
		String sql="";

public String Insert()
{
try
{
 DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
PreparedStatement ps;
for(int i=0;i<count;i++)
{
ps = con.prepareStatement("insert into feedbackquestion values ( ?,?,?,?,?,?,?,?,?,?,?)");
ps.setInt(1,questionno[i]);
ps.setString(2,question[i]);
ps.setString(3,ansa[i]);
ps.setString(4,ansb[i]);
ps.setString(5,ansc[i]);
ps.setString(6,ansd[i]);
ps.setString(7,anse[i]);
ps.setString(8,academicyear);
ps.setString(9,semester);
ps.setString(10,category);
ps.setInt(11,objective[i]);
int j = ps.executeUpdate();
ps.close();
}
connMgr.freeConnection("xavier",con);
 
}catch(Exception e){return e.toString();}
return("success");
}
public String Delete()
{
try
{
  DBConnectionManager connMgr;
  connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
PreparedStatement ps = con.prepareStatement("delete from feedbackquestion where semester=? and academicyear=? and category=?");
ps.setString(1,semester);
ps.setString(2,academicyear);
ps.setString(3,category);
 int i = ps.executeUpdate();
ps.close();
connMgr.freeConnection("xavier",con);
}catch(Exception e){return e.toString();}
return("success");
}

public int View()
{
int found=0;
int tyear=0;
int tdno=0;
int sem=0;

int semesterno=1;
if(semester.equals("ODD")) semesterno=1;
else  semesterno=2;
try
{
 DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
Statement stmt = con.createStatement();

sql="select max(year),max(department) from stud where rollno="+rollno+" and academicyear='"+academicyear+"'";

ResultSet rs = stmt.executeQuery(sql);
if(rs.next())
{
	tyear=rs.getInt(1);
	tdno=rs.getInt(2);
}
if(semesterno==1)
sem=(tyear*2)-1;
else
sem=(tyear*2);


if(category.equals("17"))
{
sql="select qno,statement,'Below Average','Average','Good','Very Good','Excellent',academicyear,'"+semester+"',17,1 " 
+"from nbasubjectco,subjectidentify,syllabus where subjectidentify.id=syllabus.id and subcode=subjectcode and subid=syllabus.id "
+"and subcode='"+subcode+"' and   semester="+sem+" and dno="+tdno+" and academicyear='"+academicyear+"' order by qno";
}
else
{
sql="select * from feedbackquestion where semester='"+semester+"' and academicyear='"+academicyear+"' and category="+category+" order by questionno";
}
rs = stmt.executeQuery(sql);
while(rs.next())
{
questionno[count]=rs.getInt(1);
question[count]=rs.getString(2);
ansa[count]=rs.getString(3);
ansb[count]=rs.getString(4);
ansc[count]=rs.getString(5);
ansd[count]=rs.getString(6);
anse[count]=rs.getString(7);
academicyear=rs.getString(8);
semester=rs.getString(9);
category=rs.getString(10);
objective[count]=rs.getInt(11);
count++;
found=1;
}
rs.close();
stmt.close();
connMgr.freeConnection("xavier",con);
}catch(Exception e){sql=sql+e.toString();}

return found; 
}

public int View1(String inid,String insub,int tp)
{
int found=0;
try
{
 DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
Statement stmt = con.createStatement();


sql="select qno,statement,'Below Average','Average','Good','Very Good','Excellent',academicyear,'3',"+tp+",1 "
+" from nbasubjectco,subjectidentify,syllabus where subjectidentify.id=syllabus.id and subcode=subjectcode and subid=syllabus.id "
+" and subid in "+inid+" and subcode in "+insub+" and academicyear='"+academicyear+"' order by qno";
ResultSet rs = stmt.executeQuery(sql);

while(rs.next())
{
questionno[count]=rs.getInt(1);
question[count]=rs.getString(2);
ansa[count]=rs.getString(3);
ansb[count]=rs.getString(4);
ansc[count]=rs.getString(5);
ansd[count]=rs.getString(6);
anse[count]=rs.getString(7);
academicyear=rs.getString(8);
semester=rs.getString(9);
category=rs.getString(10);
objective[count]=rs.getInt(11);
count++;
found=1;
}
rs.close();
stmt.close();
connMgr.freeConnection("xavier",con);
}catch(Exception e){sql=sql+e.toString();}

return found; 
}

 public void setacademicyear(String academicyear) 
{ 
this.academicyear = academicyear; 
} 
public String getacademicyear() 
{ 
return this.academicyear; 
} 
public void setansa(String[] ansa) 
{ 
this.ansa = ansa; 
} 
public String[] getansa() 
{ 
return this.ansa; 
} 
public void setansb(String[] ansb) 
{ 
this.ansb = ansb; 
} 
public String[] getansb() 
{ 
return this.ansb; 
} 
public void setansc(String[] ansc) 
{ 
this.ansc = ansc; 
} 
public String[] getansc() 
{ 
return this.ansc; 
} 
public void setansd(String[] ansd) 
{ 
this.ansd = ansd; 
} 
public String[] getansd() 
{ 
return this.ansd; 
} 
public void setanse(String[] anse) 
{ 
this.anse = anse; 
} 
public String[] getanse() 
{ 
return this.anse; 
} 
public void setquestion(String[] question) 
{ 
this.question = question; 
} 
public String[] getquestion() 
{ 
return this.question; 
} 
public void setquestionno(int[] questionno) 
{ 
this.questionno = questionno; 
} 
public int[] getquestionno() 
{ 
return this.questionno; 
} 


public void setobjective(int[] objective) 
{ 
this.objective = objective; 
} 
public int[] getobjective() 
{ 
return this.objective; 
} 



public void setsemester(String semester) 
{ 
this.semester = semester; 
} 
public String getsemester() 
{ 
return this.semester; 
}

public void setcategory(String category) 
{ 
this.category = category; 
} 
public String getcategory() 
{ 
return this.category; 
}

public void setstaffid(String staffid) 
{ 
this.staffid = staffid; 
} 
public String getstaffid() 
{ 
return this.staffid; 
}

public void setcount(int count) 
{ 
this.count = count; 
} 
public int getcount() 
{ 
return this.count; 
}

public void setrollno(int rollno) 
{ 
this.rollno = rollno; 
} 
public int getrollno() 
{ 
return this.rollno; 
}

public void setsubcode(String subcode) 
{ 
this.subcode = subcode; 
} 
public String getsubcode() 
{ 
return this.subcode; 
}

public void setsql(String sql) 
{ 
this.sql = sql; 
} 
public String getsql() 
{ 
return this.sql; 
}

}
	