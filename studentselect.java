package sxcce;
import java.sql.*;
import java.util.*;
import dd.DBConnectionManager;

public class studentselect
{
String rollno="";
String quer="";
int year=0;
int bon=0;
String name="";
String academicyear="";
String department="";
String sex="";
String initial="";
String crollno[] = new String[200];
String cname[] = new String[200];
int semester=0;
int ccount=0;
int dno=0;
int id=0;

String quesname="";
int quesdno=0;
int quesyear=0;
int queslink=0;

public int sem()
{int found=0;
	String quer="";
quer="select * from fineviewidentify";
DBConnectionManager connMgr=null; Connection con =null;
try
{
connMgr = DBConnectionManager.getInstance(); 
con = connMgr.getConnection("xavier");
Statement stmt = con.createStatement();
ResultSet rs = stmt.executeQuery(quer);
if(rs.next())
{
	academicyear=rs.getString(1);
	semester=rs.getInt(2);
}
rs.close();
int semesterno=0;
if(semester==2)
   {
   if(year==1)semesterno=2;
   else if(year==2)semesterno=4;
   else if(year==3)semesterno=6;
   else if(year==4)semesterno=8;
   }
else if(semester==1)
   {
   if(year==1)semesterno=1;
   else if(year==2)semesterno=3;
   else if(year==3)semesterno=5;
   else if(year==4)semesterno=7;
   }   

rs = stmt.executeQuery("select id from subjectidentify where academicyear='"+academicyear+"' and dno="+dno+" and semester="+semesterno);
if(rs.next())
{
	id=rs.getInt(1);
}
rs.close();
stmt.close();
con.close();
}catch(Exception e){}
finally{connMgr.freeConnection("xavier",con);}
return found; 
}




public int classselect()
{int found=0;
	String quer="";
quer="select rollno,name from stud where academicyear in "+
"(select academicyear from academicyear) and department="+dno+" and year="+year;
DBConnectionManager connMgr=null; Connection con =null;
try
{
connMgr = DBConnectionManager.getInstance(); 
con = connMgr.getConnection("xavier");
Statement stmt = con.createStatement();
ResultSet rs = stmt.executeQuery(quer);
while(rs.next())
{
	crollno[ccount]=rs.getString(1);
	cname[ccount]=rs.getString(2);
	ccount++;
}
rs.close();
stmt.close();
con.close();
}catch(Exception e){}
finally{connMgr.freeConnection("xavier",con);}
return year; 
}


public int studView()
{
	int found=0;

quer="select stud.rollno,stud.name,year,dname,academicyear,dno,sex,initial from stud,student,department where "+
" stud.rollno=student.rollno and stud.rollno="+rollno+" and department=dno and academicyear=(select academicyear from academicyear)";


DBConnectionManager connMgr=null; Connection con =null;
try
{
connMgr = DBConnectionManager.getInstance(); 
con = connMgr.getConnection("xavier");
Statement stmt = con.createStatement();
ResultSet rs = stmt.executeQuery(quer);
if(rs.next())
{
	rollno=rs.getString(1);
	name=rs.getString(2);
	year=rs.getInt(3);
	department=rs.getString(4);
	academicyear=rs.getString(5);	
	dno=rs.getInt(6);
	sex=rs.getString(7);
	initial=rs.getString(8);
	found=1;
}
rs.close();
stmt.close();
con.close();
}catch(Exception e){}
finally{connMgr.freeConnection("xavier",con);}
return found; 
}

public int studbon()
{
	int bon=0;
String cdate="";
Calendar cal = new GregorianCalendar();
cdate=(cal.get(Calendar.MONTH)+1 + "/" + cal.get(Calendar.DAY_OF_MONTH) + "/" + cal.get(Calendar.YEAR));

quer="select max(id)+1 from bonafide";
DBConnectionManager connMgr=null; Connection con =null;
try
{
connMgr = DBConnectionManager.getInstance(); 
con = connMgr.getConnection("xavier");
Statement stmt = con.createStatement();
ResultSet rs;
rs= stmt.executeQuery(quer);
if(rs.next())
{
	bon=rs.getInt(1);
}
rs.close();

quer="insert into bonafide values(?,?,?)";
PreparedStatement ps;
ps = con.prepareStatement(quer);
ps.setInt(1,Integer.parseInt(rollno));
ps.setString(2,cdate);
ps.setInt(3,bon);
int i=ps.executeUpdate();
ps.close();

stmt.close();
con.close();
}catch(Exception e){}
finally{connMgr.freeConnection("xavier",con);}
return bon; 
}


public int View()
{int found=0;

quer="select rollno,name,year,dname,academicyear,dno from stud,department where "+
" rollno="+rollno+" and department=dno and academicyear=(select academicyear from academicyear)";
//quer="select rollno,name,year,dname,academicyear,dno from stud,department where "+
//" rollno="+rollno+" and department=dno";
DBConnectionManager connMgr=null; Connection con =null;
try
{
connMgr = DBConnectionManager.getInstance(); 
con = connMgr.getConnection("xavier");
Statement stmt = con.createStatement();
ResultSet rs = stmt.executeQuery(quer);
if(rs.next())
{
	rollno=rs.getString(1);
	name=rs.getString(2);
	year=rs.getInt(3);
	department=rs.getString(4);
	academicyear=rs.getString(5);	
	dno=rs.getInt(6);
	found=1;
}
rs.close();

int ff=0;

rs = stmt.executeQuery("select * from generalfeedbackquestionselect");
if(rs.next())
{
	quesname=rs.getString(1);
	quesdno=rs.getInt(2);
	quesyear=rs.getInt(3);
	ff=1;
}
rs.close();

if(ff==1)
   {sem();
quer="select * from generalfeedbackenteredornot where rollno="+rollno+" and id="+id+" and quesname='"+quesname+"'";
	rs = stmt.executeQuery(quer);
	if(rs.next())
		{
		ff=0;
	}
	rs.close();


   }

if(quesdno==dno && quesyear==year)queslink=1;

if(quesdno==0 && quesyear==year)queslink=1;

if(quesdno==dno && quesyear==0)queslink=1;

if(quesdno==0 && quesyear==0)queslink=1;

if(ff==0)queslink=0;


stmt.close();
con.close();
}catch(Exception e){}
finally{connMgr.freeConnection("xavier",con);}
return found; 
}

  

public int View1()
{int found=0;

quer="select rollno,name,year,dname,academicyear,dno from stud,department where "+
" rollno="+rollno+" and department=dno";
DBConnectionManager connMgr=null; Connection con =null;
try
{
connMgr = DBConnectionManager.getInstance(); 
con = connMgr.getConnection("xavier");
Statement stmt = con.createStatement();
ResultSet rs = stmt.executeQuery(quer);
if(rs.next())
{
	rollno=rs.getString(1);
	name=rs.getString(2);
	year=rs.getInt(3);
	department=rs.getString(4);
	academicyear=rs.getString(5);	
	dno=rs.getInt(6);
	found=1;
}
rs.close();

int ff=0;

rs = stmt.executeQuery("select * from generalfeedbackquestionselect");
if(rs.next())
{
	quesname=rs.getString(1);
	quesdno=rs.getInt(2);
	quesyear=rs.getInt(3);
	ff=1;
}
rs.close();

if(ff==1)
   {sem();
quer="select * from generalfeedbackenteredornot where rollno="+rollno+" and id="+id+" and quesname='"+quesname+"'";
	rs = stmt.executeQuery(quer);
	if(rs.next())
		{
		ff=0;
	}
	rs.close();


   }

if(quesdno==dno && quesyear==year)queslink=1;

if(quesdno==0 && quesyear==year)queslink=1;

if(quesdno==dno && quesyear==0)queslink=1;

if(quesdno==0 && quesyear==0)queslink=1;

if(ff==0)queslink=0;


stmt.close();
con.close();
}catch(Exception e){}
finally{connMgr.freeConnection("xavier",con);}
return found; 
}

public String StudView(int roll)
{String res="";



//quer="select rollno,name,year,sf,academicyear,dno from stud,department where "+
//" rollno="+roll+" and department=dno and academicyear=(select academicyear from academicyear)";
quer="select rollno,name,year,sf,academicyear,dno from stud,department where "+
" rollno="+roll+" and department=dno and year=(select max(year) from stud where  rollno="+roll+")";


DBConnectionManager connMgr=null; Connection con =null;
try
{
connMgr = DBConnectionManager.getInstance(); 
con = connMgr.getConnection("xavier");
Statement stmt = con.createStatement();
ResultSet rs = stmt.executeQuery(quer);
if(rs.next())
{
	rollno=rs.getString(1);
	name=rs.getString(2);
	year=rs.getInt(3);
	department=rs.getString(4);
	academicyear=rs.getString(5);	
	dno=rs.getInt(6);
}
res=name+" ("+year+" - "+department+")";
rs.close();
stmt.close();
con.close();
}catch(Exception e){}
finally{connMgr.freeConnection("xavier",con);}
return res; 
}

  
  
public void setdepartment(String department) 
{ 
this.department = department; 
} 
public String getdepartment() 
{ 
return this.department; 
} 
public void setname(String name) 
{ 
this.name = name; 
} 
public String getname() 
{ 
return this.name; 
} 
public void setrollno(String rollno) 
{ 
this.rollno = rollno; 
} 
public String getrollno() 
{ 
return this.rollno; 
} 
public void setyear(int year) 
{ 
this.year = year; 
} 
public int getyear() 
{ 
return this.year; 
} 

public void setacademicyear(String academicyear) 
{ 
this.academicyear = academicyear; 
} 
public String getacademicyear() 
{ 
return this.academicyear; 
} 

public void setquer(String quer) 
{ 
this.quer = quer; 
} 
public String getquer() 
{ 
return this.quer; 
} 


public String getsex() 
{ 
return this.sex; 
} 

public String getinitial() 
{ 
return this.initial; 
} 

public void setdno(int dno) 
{ 
this.dno = dno; 
} 
public int getdno() 
{ 
return this.dno; 
} 


public void setid(int id) 
{ 
this.id = id; 
} 
public int getid() 
{ 
return this.id; 
} 

public void setccount(int ccount) 
{ 
this.ccount = ccount; 
} 
public int getccount() 
{ 
return this.ccount; 
} 

public int getbon() 
{ 
return this.bon; 
} 

public void setsemester(int semester) 
{ 
this.semester = semester; 
} 
public int getsemester() 
{ 
return this.semester; 
} 


public void setqueslink(int queslink) 
{ 
this.queslink = queslink; 
} 
public int getqueslink() 
{ 
return this.queslink; 
} 



public void setcname(String[] cname) 
{ 
this.cname = cname; 
} 
public String[] getcname() 
{ 
return this.cname; 
} 

public void setcrollno(String[] crollno) 
{ 
this.crollno = crollno; 
} 
public String[] getcrollno() 
{ 
return this.crollno; 
} 



}

