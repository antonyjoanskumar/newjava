package attendance;
import java.sql.*;
import dd.*;

public class attendance
{
String quer="";
String ddno[] = new String[100];
String dname[] = new String[100];
int dcount=0;
String name[] = new String[500];
String rollno[] = new String[500];
String hours[] = new String[500];
int count=0;
String academicyear="";
String day="";
String rno="";
String hrs="";
String staffid="";
String month="";
String syear="";
String date="";
String vdate="";
String semesters="";
String semester="";
String year="";
String dno="";
String periods[] = new String[10];
String attendanceentered[] = new String[10];
String attendance[][] = new String[10][500];
String contact[] = new String[500];
String rollnoin="";
String periodsupdated="";
String att[] = new String[500];
String substaffid[] = new String[15];
String substaffname[] = new String[15];
int noofperiods=0;


int subcount=0;
String subcode[] = new String[10];
String log[] = new String[10];
String subperiods[] = new String[10];
String subid="";

String odrollno="";
String odsem="";
String odyear="";
String odacademicyear="";
String odname="";
String oddepartment="";
String oddno="";
String odstartingdate="";
String odendingdate="";
String odperiods="";

public String odcheck()
{
	String quer="";
DBConnectionManager connMgr=null; Connection con =null;
	try
	{
connMgr = DBConnectionManager.getInstance(); 
con = connMgr.getConnection("attendance");

Statement stmt = con.createStatement();
ResultSet rs;

quer="select academicyear from sxcce.dbo.academicyear";
	rs = stmt.executeQuery(quer);
	if(rs.next())
	  odacademicyear=rs.getString(1);
	rs.close();

quer="select name,year,department,sxcce.dbo.department.dname "+
"from sxcce.dbo.stud,sxcce.dbo.department "+
"where academicyear='"+odacademicyear+"' and "+
"rollno="+odrollno+" and sxcce.dbo.department.dno=department";

	rs = stmt.executeQuery(quer);
	if(rs.next())
		{
	  	odname=rs.getString(1);
	  	odyear=rs.getString(2);
	  	oddno=rs.getString(3);
	  	oddepartment=rs.getString(4);
	  	}
	rs.close();

semesters="";
if(odyear.equals("1"))semesters="1,2";
else if(odyear.equals("2"))semesters="3,4";
else if(odyear.equals("3"))semesters="5,6";
else if(odyear.equals("4"))semesters="7,8";

quer="select semester from reopening where '"+odstartingdate+"' between opening and closing "+
"and dno="+oddno+" and semester in ("+semesters+")";

	rs = stmt.executeQuery(quer);
	if(rs.next())
		{
			odsem=rs.getString(1);
	  	}
	rs.close();

PreparedStatement ps; 
date=odstartingdate;
for(;;)
	{
int temp=0;

quer="select datediff(day,'"+date+"','"+odendingdate+"')";

	rs = stmt.executeQuery(quer);
	if(rs.next())
		{
			temp=rs.getInt(1);
	  	}
	rs.close();
	if(temp<0)break;
	if(odholidaycheck()==1)
	{
		try{
		odperiods=odperiods.substring(0,noofperiods);
		}catch(Exception e){}
	}
	{//delete and insert
	ps =con.prepareStatement("delete from od where rollno=? and date=?");
	ps.setString(1,odrollno);
	ps.setString(2,date);
	ps.execute();
	ps.close();
	if(odperiods.length()>0)
	{
	ps =con.prepareStatement("insert into od values(?,?,?)");
	ps.setString(1,date);
	ps.setString(2,odrollno);
	ps.setString(3,odperiods);
	ps.execute();
	ps.close();
}

}
quer="select dateadd(day,1,'"+date+"')";

	rs = stmt.executeQuery(quer);
	if(rs.next())
		{
			date=rs.getString(1);
	  	}
	rs.close();

	}


stmt.close();
}catch(Exception e){return e.toString()+quer;}
finally{connMgr.freeConnection("attendance",con);}

return "success";
}


public String blankattcheck()
{
	String quer="";
DBConnectionManager connMgr=null; Connection con =null;
	try
	{
connMgr = DBConnectionManager.getInstance(); 
con = connMgr.getConnection("attendance");

Statement stmt = con.createStatement();
ResultSet rs;

quer="select academicyear from sxcce.dbo.academicyear";
	rs = stmt.executeQuery(quer);
	if(rs.next())
	  odacademicyear=rs.getString(1);
	rs.close();

quer="select name,year,department,sxcce.dbo.department.dname "+
"from sxcce.dbo.stud,sxcce.dbo.department "+
"where academicyear='"+odacademicyear+"' and "+
"rollno="+odrollno+" and sxcce.dbo.department.dno=department";

	rs = stmt.executeQuery(quer);
	if(rs.next())
		{
	  	odname=rs.getString(1);
	  	odyear=rs.getString(2);
	  	oddno=rs.getString(3);
	  	oddepartment=rs.getString(4);
	  	}
	rs.close();

semesters="";
if(odyear.equals("1"))semesters="1,2";
else if(odyear.equals("2"))semesters="3,4";
else if(odyear.equals("3"))semesters="5,6";
else if(odyear.equals("4"))semesters="7,8";

quer="select semester from reopening where '"+odstartingdate+"' between opening and closing "+
"and dno="+oddno+" and semester in ("+semesters+")";

	rs = stmt.executeQuery(quer);
	if(rs.next())
		{
			odsem=rs.getString(1);
	  	}
	rs.close();

PreparedStatement ps; 
date=odstartingdate;
for(;;)
	{
int temp=0;

quer="select datediff(day,'"+date+"','"+odendingdate+"')";

	rs = stmt.executeQuery(quer);
	if(rs.next())
		{
			temp=rs.getInt(1);
	  	}
	rs.close();
	if(temp<0)break;
	if(odholidaycheck()==1)
	{
		odperiods=odperiods.substring(0,noofperiods);
		
		}
	{//delete and insert
	ps =con.prepareStatement("delete from blankatt where rollno=? and date=?");
	ps.setString(1,odrollno);
	ps.setString(2,date);
	ps.execute();
	ps.close();
	if(odperiods.length()>0)
	{
	ps =con.prepareStatement("insert into blankatt values(?,?,?)");
	ps.setString(1,date);
	ps.setString(2,odrollno);
	ps.setString(3,odperiods);
	ps.execute();
	ps.close();
}

}
quer="select dateadd(day,1,'"+date+"')";

	rs = stmt.executeQuery(quer);
	if(rs.next())
		{
			date=rs.getString(1);
	  	}
	rs.close();

	}


stmt.close();
}catch(Exception e){return e.toString();}
finally{connMgr.freeConnection("attendance",con);}

return "success";
}


public int odholidaycheck()
{
	String quer="";
DBConnectionManager connMgr=null; Connection con =null;
	try
	{
connMgr = DBConnectionManager.getInstance(); 
con = connMgr.getConnection("attendance");
Statement stmt = con.createStatement();
ResultSet rs;
quer="select noofperiods from commonworkingdays where day=datepart(dw,'"+date+"')";
	rs = stmt.executeQuery(quer);
	if(rs.next())
	  noofperiods=rs.getInt(1);
	rs.close();

quer="select noofperiods from holidaymaster where '"+date+"' between fdate and tdate and dno=0 and year=0";
	rs = stmt.executeQuery(quer);
	if(rs.next())
	  noofperiods=rs.getInt(1);
	rs.close();
quer="select noofperiods from holidaymaster where '"+date+"' between fdate and tdate and dno="+oddno+" and year=0";
	rs = stmt.executeQuery(quer);
	if(rs.next())
	  noofperiods=rs.getInt(1);
	rs.close();
quer="select noofperiods from holidaymaster where '"+date+"' between fdate and tdate and dno=0 and year="+odyear;
	rs = stmt.executeQuery(quer);
	if(rs.next())
	  noofperiods=rs.getInt(1);
	rs.close();

quer="select noofperiods from holidaymaster where '"+date+"' between fdate and tdate and dno="+oddno+" and year="+odyear;
	rs = stmt.executeQuery(quer);
	if(rs.next())
	  noofperiods=rs.getInt(1);
	rs.close();

stmt.close();
}catch(Exception e){return 10;}
finally{connMgr.freeConnection("attendance",con);}

if(noofperiods>=odperiods.length())
	return 1;
else return 0;	
}

public String holidaycheck()
{
	String quer="";
DBConnectionManager connMgr=null; Connection con =null;
	try
	{
connMgr = DBConnectionManager.getInstance(); 
con = connMgr.getConnection("attendance");
Statement stmt = con.createStatement();
ResultSet rs;
quer="select noofperiods from commonworkingdays where day=datepart(dw,'"+date+"')";
	rs = stmt.executeQuery(quer);
	if(rs.next())
	  noofperiods=rs.getInt(1);
	rs.close();

quer="select noofperiods from holidaymaster where '"+date+"' between fdate and tdate and dno=0 and year=0";
	rs = stmt.executeQuery(quer);
	if(rs.next())
	  noofperiods=rs.getInt(1);
	rs.close();
quer="select noofperiods from holidaymaster where '"+date+"' between fdate and tdate and dno="+dno+" and year=0";
	rs = stmt.executeQuery(quer);
	if(rs.next())
	  noofperiods=rs.getInt(1);
	rs.close();
quer="select noofperiods from holidaymaster where '"+date+"' between fdate and tdate and dno=0 and year="+year;
	rs = stmt.executeQuery(quer);
	if(rs.next())
	  noofperiods=rs.getInt(1);
	rs.close();

quer="select noofperiods from holidaymaster where '"+date+"' between fdate and tdate and dno="+dno+" and year="+year;
	rs = stmt.executeQuery(quer);
	if(rs.next())
	  noofperiods=rs.getInt(1);
	rs.close();

stmt.close();
}catch(Exception e){return e.toString();}
finally{connMgr.freeConnection("attendance",con);}

return "suc";
}

public String ccheck()
  {
  	String quer="";
DBConnectionManager connMgr=null; Connection con =null;
	try
	{
connMgr = DBConnectionManager.getInstance(); 
con = connMgr.getConnection("xavier");
Statement stmt = con.createStatement();
ResultSet rs;
int ddday=0;
rs=stmt.executeQuery("select datepart(dw,'"+date+"')");
if(rs.next())
  ddday=rs.getInt(1);
rs.close();
/*for(int i=0;i<periods.length;i++)
{if(ddday==7)break;
quer="select subcode from subjecthandled where staffid='"+staffid+"' and "+
"id in(select id from subjectidentify "+
"where semester="+semester+" and dno="+dno+" and academicyear='"+academicyear+"') "+
"and subcode in(select subjectcode from periods where id in "+
"(select id from subjectidentify where semester="+semester+" and dno="+dno+" "+
"and academicyear='"+academicyear+"') and day=(select datepart(dw,'"+date+"')-1) "+
"and hour="+periods[i]+")";
	rs = stmt.executeQuery(quer);
	if(!rs.next())
	  if(staffid.substring(0,1).equals("T") || staffid.substring(0,1).equals("N") || staffid.substring(0,1).equals("t") || staffid.substring(0,1).equals("n"))
      noofperiods=0;
	rs.close();
}*/
stmt.close();
}catch(Exception e){return quer;}
finally{connMgr.freeConnection("xavier",con);}

return "suc";
  }

public String viewatt()
{
	String quer="";
	date=month+"/"+day+"/"+syear;
	vdate=day+"/"+month+"/"+syear;
for(int i=0;i<count;i++)att[i]="";
DBConnectionManager connMgr=null; Connection con =null;
	try
	{
connMgr = DBConnectionManager.getInstance(); 
con = connMgr.getConnection("attendance");
Statement stmt = con.createStatement();
ResultSet rs;
String rno="";
quer="select rollno,hours from attendance where date='"+date+"' and rollno in("+rollnoin+") order by rollno";
	rs = stmt.executeQuery(quer);
	while(rs.next())
		{
			rno=rs.getString(1);
			for(int i=0;i<count;i++)
			   if(rno.equals(rollno[i])){att[i]=rs.getString(2);break;}
		}
	rs.close();





stmt.close();
}catch(Exception e){return e.toString();}
finally{connMgr.freeConnection("attendance",con);}

return "suc";

}

public String stafffind()
{
String quer="";
DBConnectionManager connMgr=null; Connection con =null;
	try
	{
connMgr = DBConnectionManager.getInstance(); 
con = connMgr.getConnection("xavier");
Statement stmt = con.createStatement();
ResultSet rs;
for(int i=0;i<8;i++)
  {
  	substaffid[i]="";
  	substaffname[i]="";

quer="select name,staffid from staff where staffid in "+
"(select staffid from subjecthandled where mainorsub='M' and id in (select id from "+
"subjectidentify where semester="+semester+" and dno="+dno+
" and academicyear='"+academicyear+"') and "+
"subcode in(select subjectcode from periods where id in "+
"(select id from subjectidentify where semester="+semester+" and dno="+dno+
" and academicyear='"+academicyear+"') and day=(select datepart(dw,'"+date+"')-1) "+
"and hour="+(i+1)+"))";
	rs = stmt.executeQuery(quer);
	int h=0;
	while(rs.next())
		{
		if(h==0)
		    {
			substaffname[i]=rs.getString(1);
			substaffid[i]=rs.getString(2);
		}
		else 
			{
			substaffname[i]=substaffname[i]+" / "+rs.getString(1);
			substaffid[i]=substaffid[i]+" / "+rs.getString(2);
			}
			h=1;
		}
	rs.close();
}
stmt.close();
}catch(Exception e){return quer+e.toString();}
finally{connMgr.freeConnection("xavier",con);}

return quer+"suc";
}
public void dorollnoin()
{
	Classselect();
if(count>0)rollnoin=rollno[0];
for(int i=1;i<count;i++)
  rollnoin=rollnoin+","+rollno[i];
}
public void dorollnoin1()
{
if(count>0)rollnoin=rollno[0];
for(int i=1;i<count;i++)
  rollnoin=rollnoin+","+rollno[i];
}

public String viewattendance()
  {
date=month+"/"+day+"/"+syear;
vdate=day+"/"+month+"/"+syear;
dorollnoin1();
String quer="";
  	for(int i=0;i<count;i++)
  	for(int j=0;j<periods.length;j++)
  	  attendance[j][i]="/";
DBConnectionManager connMgr=null; Connection con =null;
	try
	{
connMgr = DBConnectionManager.getInstance(); 
con = connMgr.getConnection("attendance");

Statement stmt = con.createStatement();
ResultSet rs;
  	for(int j=0;j<periods.length;j++)
  	  {
quer="select hours from attendance where date='"+date+"' and hours like '%"+periods[j]+"%' and rollno in("+rollnoin+") order by rollno";
rs = stmt.executeQuery(quer);
if(rs.next())
  attendanceentered[j]="1";
else 
  attendanceentered[j]="0";  
rs.close();  

quer="select count(hours) from od where date='"+date+"' and hours like '%"+periods[j]+"%' and rollno in("+rollnoin+")";
rs = stmt.executeQuery(quer);
if(rs.next())
  if(rs.getInt(1)==count)
  attendanceentered[j]="1";



quer="select * from attendancecheck where date='"+date+"' and period="+periods[j]+" and year="+year+" and dno="+dno;	
if(attendanceentered[j].equals("0"))
{
rs = stmt.executeQuery(quer);
if(rs.next())
  attendanceentered[j]="1";
else 
for(int i=0;i<count;i++)
attendance[j][i]="";
rs.close();  
}
  	  }  
for(int i=0;i<count;i++)
  for(int j=0;j<periods.length;j++)
    {
quer=("select hours from attendance where date='"+date+"' and hours like '%"+periods[j]+"%' and rollno="+rollno[i]);
	rs = stmt.executeQuery(quer);
	if(rs.next())
		{
			attendance[j][i]="ab";
		}
	rs.close();
quer=("select hours from od where date='"+date+"' and hours like '%"+periods[j]+"%' and rollno="+rollno[i]);
	rs = stmt.executeQuery(quer);
	if(rs.next())
		{
			attendance[j][i]="od";
		}
	rs.close();
	}
stmt.close();
}catch(Exception e){return e.toString()+quer;}
finally{connMgr.freeConnection("attendance",con);}

return "suc";
}







public String viewstaffcheckattendance()
  {
  	String quer="";
  for(int i=0;i<8;i++)attendanceentered[i]="";
DBConnectionManager connMgr=null; Connection con =null;
	try
	{
connMgr = DBConnectionManager.getInstance(); 
con = connMgr.getConnection("attendance");
Statement stmt = con.createStatement();
ResultSet rs;
  	for(int j=0;j<periods.length;j++)
  	  {
quer="select hours from attendance where date='"+date+"' and hours like '%"+periods[j]+"%' and rollno in("+rollnoin+") order by rollno";
rs = stmt.executeQuery(quer);
if(rs.next())
  attendanceentered[j]="1";
else 
  attendanceentered[j]="0";  
rs.close();  

quer="select count(hours) from od where date='"+date+"' and hours like '%"+periods[j]+"%' and rollno in("+rollnoin+")";
rs = stmt.executeQuery(quer);
if(rs.next())
  if(rs.getInt(1)==count)
  attendanceentered[j]="1";


quer="select * from attendancecheck where date='"+date+"' and period="+periods[j]+" and year="+year+" and dno="+dno;	
if(attendanceentered[j].equals("0"))
{
rs = stmt.executeQuery(quer);
if(rs.next())
  attendanceentered[j]="1";
rs.close();  
}
  	  }  
stmt.close();
}catch(Exception e){return e.toString()+quer;}
finally{connMgr.freeConnection("attendance",con);}

return quer;
}





public String checking()
  {
  	return "success";
  }


public String contactInsert()
{
	String check="";
DBConnectionManager connMgr=null; Connection con =null;
	try
	{
connMgr = DBConnectionManager.getInstance(); 
con = connMgr.getConnection("xavier");
PreparedStatement ps; 
for(int i=0;i<count;i++)
	{
	ps =con.prepareStatement("delete from contact where rollno= ?");
	ps.setString(1,rollno[i]);
	ps.execute();
	ps.close();
	ps =con.prepareStatement("insert into contact values(?,?)");
	ps.setString(1,rollno[i]);
	ps.setString(2,contact[i]);
	ps.execute();
	ps.close();
	}
}catch(Exception e){return e.toString()+"Error !! Invalid Data / Data Already Inserted";}
finally{connMgr.freeConnection("xavier",con);}

check="Data sucessfully Inserted / Updated!!";
return(check);
}


public String Insert()
{
	String check="";
DBConnectionManager connMgr=null; Connection con =null;
	try
	{
connMgr = DBConnectionManager.getInstance(); 
con = connMgr.getConnection("attendance");
PreparedStatement ps;
ps =con.prepareStatement("execute attendanceinsert ?,?,?,?,?,?,?");
ps.setString(1,hrs);
ps.setString(2,rno);
ps.setString(3,dno);
ps.setString(4,year);
ps.setString(5,academicyear);
ps.setString(6,date);
ps.setString(7,periodsupdated);
ps.execute();
for(int i=0;i<subcount;i++)
   {
   	ps=con.prepareStatement("delete from log where date=? and id=? and period=?");
   	ps.setString(1,date);
   	ps.setString(2,subid);
   	ps.setString(3,subperiods[i]);
   	ps.executeUpdate();
   	ps.close();
   	ps=con.prepareStatement("insert into log values(?,?,?,?,?,?)");
   	ps.setString(1,date);
   	ps.setString(2,subid);
   	ps.setString(3,subperiods[i]);
   	ps.setString(4,subcode[i]);
   	ps.setString(5,log[i]);
   	ps.setString(6,staffid);
   	ps.executeUpdate();
   	ps.close();
   }
}catch(Exception e){return e.toString()+"Error !! Invalid Data / Data Already Inserted";}
finally{connMgr.freeConnection("attendance",con);}

check="Data sucessfully Inserted / Updated!!";
return(check);
}


public String delete()
{
DBConnectionManager connMgr=null; Connection con =null;
	try
	{
connMgr = DBConnectionManager.getInstance(); 
con = connMgr.getConnection("attendance");
	PreparedStatement ps=con.prepareStatement("delete from attendance where date=? and rollno in ("+rollnoin+")");
	ps.setString(1,date);
  	int i=ps.executeUpdate();
  	ps.close();
	ps=con.prepareStatement("delete from attendancecheck where date=? and year=? and dno=?");
	ps.setString(1,date);
	ps.setString(2,year);
	ps.setString(3,dno);
   i=ps.executeUpdate();
   
   	ps=con.prepareStatement("delete from log where date=? and id=?");
   	ps.setString(1,date);
   	ps.setString(2,subid);
   	ps.executeUpdate();
   	ps.close();
   	
	}catch(Exception e){return e.toString();}
	finally{connMgr.freeConnection("attendance",con);}

		return "Sucessfully Deleted";
}
public void academic()
{dcount=0;
semesters="";
if(year.equals("1"))semesters="1,2";
else if(year.equals("2"))semesters="3,4";
else if(year.equals("3"))semesters="5,6";
else if(year.equals("4"))semesters="7,8";
String quer="select academicyear,semester from reopening where '"+date+"' between "+
"opening and closing and dno="+dno+" and semester in("+semesters+")";
DBConnectionManager connMgr=null; Connection con =null;
	try
	{
connMgr = DBConnectionManager.getInstance(); 
con = connMgr.getConnection("attendance");
Statement stmt = con.createStatement();
ResultSet rs = stmt.executeQuery(quer);
if(rs.next())
{
	academicyear=rs.getString(1);
	semester=rs.getString(2);
}
rs.close();
stmt.close();
}catch(Exception e){}
finally{connMgr.freeConnection("attendance",con);}

}

public String TotHours(String fdate,String tdate)
{
count=0;
DBConnectionManager connMgr=null; Connection con =null;
	try
	{
connMgr = DBConnectionManager.getInstance(); 
con = connMgr.getConnection("attendance");
Statement stmt = con.createStatement();
ResultSet rs = stmt.executeQuery("select max(dname),year,count(*) from attendancecheck a,sxcce.dbo.department d where a.dno=d.dno and date between '"+fdate+"' and '"+tdate+"' group by a.dno,year order by a.dno,year");
while(rs.next())
{
	dname[count]=rs.getString(1);
	ddno[count]=rs.getString(2);
	hours[count]=rs.getString(3);
	count++;
}
rs.close();
stmt.close();
}catch(Exception e){return e.toString();}
finally{connMgr.freeConnection("attendance",con);}
return "sus"; 
}




public String Viewd1()
{dcount=0;
DBConnectionManager connMgr=null; Connection con =null;
	try
	{
connMgr = DBConnectionManager.getInstance(); 
con = connMgr.getConnection("xavier");
Statement stmt = con.createStatement();
ResultSet rs = stmt.executeQuery("select distinct departmentno,dname from student,department "+
"where departmentno=dno order by departmentno");
while(rs.next())
{
	ddno[dcount]=rs.getString(1);
	dname[dcount]=rs.getString(2);
	dcount++;
}
rs.close();
stmt.close();
}catch(Exception e){return e.toString();}
finally{connMgr.freeConnection("xavier",con);}

return "sus"; 
}

public String Viewd()
{dcount=0;
DBConnectionManager connMgr=null; Connection con =null;
	try
	{
connMgr = DBConnectionManager.getInstance(); 
con = connMgr.getConnection("xavier");
Statement stmt = con.createStatement();
ResultSet rs = stmt.executeQuery("select dno,dname from department order by dno");
while(rs.next())
{
	ddno[dcount]=rs.getString(1);
	dname[dcount]=rs.getString(2);
	dcount++;
}
rs.close();
stmt.close();
}catch(Exception e){return e.toString();}
finally{connMgr.freeConnection("xavier",con);}

return "sus"; 
}


public String Classselect()
{count=0;
if(month.length()>0)
date=month+"/"+day+"/"+syear;
vdate=day+"/"+month+"/"+syear;
academic();
DBConnectionManager connMgr=null; Connection con =null;
	try
	{
connMgr = DBConnectionManager.getInstance(); 
con = connMgr.getConnection("xavier");
Statement stmt = con.createStatement();
ResultSet rs = stmt.executeQuery("select stud.rollno,stud.name from stud,student where stud.department="+dno+" and stud.year="+year+" and "+
"stud.academicyear='"+academicyear+"' and stud.rollno=student.rollno and student.active=1 order by stud.rollno");
//"stud.academicyear in (select academicyear from academicyear) and stud.rollno=student.rollno and student.active=1 order by stud.rollno");
while(rs.next())
{
	rollno[count]=rs.getString(1);
	name[count]=rs.getString(2);
if(rollno[count].equals("3182") || rollno[count].equals("4182") )continue;
	count++;
}
rs.close();
stmt.close();
}catch(Exception e){return e.toString();}
finally{connMgr.freeConnection("xavier",con);}

return "sus"; 
}


public String Logselect()
{
DBConnectionManager connMgr=null; Connection con =null;
	try
	{
connMgr = DBConnectionManager.getInstance(); 
con = connMgr.getConnection("attendance");
Statement stmt = con.createStatement();
ResultSet rs = stmt.executeQuery("select id from "+
"sxcce.dbo.subjectidentify where semester="+semester+" and dno="+dno+
" and academicyear='"+academicyear+"'");
if(rs.next())
{
subid=rs.getString(1);
}
rs.close();
for(int i=0;i<subcount;i++)
   {
   	quer="select subcode,topic,staffid from log where date ='"+date+"' and id="+subid+" and period="+subperiods[i];
   	rs = stmt.executeQuery(quer);
if(rs.next())
   {
   	subcode[i]=rs.getString(1);
   	log[i]=rs.getString(2);
  	substaffid[i]=rs.getString(3);
 
   }
   rs.close();
   }
stmt.close();
}catch(Exception e){return e.toString();}
finally{connMgr.freeConnection("attendance",con);}
return "sus"; 
}



public String contactselect()
{
DBConnectionManager connMgr=null; Connection con =null;
	try
	{
connMgr = DBConnectionManager.getInstance(); 
con = connMgr.getConnection("xavier");
Statement stmt = con.createStatement();
ResultSet rs;
for(int i=0;i<count;i++)
{rs= stmt.executeQuery("select contact from contact where rollno="+rollno[i]);
contact[i]="3";
if(rs.next())
{
	contact[i]=rs.getString(1);
}
rs.close();
}
stmt.close();

}catch(Exception e){return e.toString();}
finally{connMgr.freeConnection("xavier",con);}
return "sus"; 
}


public int datediffer(String tday,String tmonth,String tyear)
   {
   	int no=0;
DBConnectionManager connMgr=null; Connection con =null;
	try
	{
connMgr = DBConnectionManager.getInstance(); 
con = connMgr.getConnection("xavier");
Statement stmt = con.createStatement();

ResultSet rs = stmt.executeQuery("select datediff(d,'"+month+"/"+day+"/"+syear+"','"+tmonth+"/"+tday+"/"+tyear+"')");
if(rs.next())
{
	no=rs.getInt(1);
}
rs.close();
stmt.close();
}catch(Exception e){return no;}
finally{connMgr.freeConnection("xavier",con);}

return no;
   }

public void dateadd()
{
DBConnectionManager connMgr=null; Connection con =null;
	try
	{
connMgr = DBConnectionManager.getInstance(); 
con = connMgr.getConnection("xavier");
Statement stmt = con.createStatement();
ResultSet rs = stmt.executeQuery("select month(dateadd(day,1,'"+date+"')),day(dateadd(day,1,'"+date+"')),year(dateadd(day,1,'"+date+"'))");
if(rs.next())
{
	month=rs.getString(1);
	day=rs.getString(2);
	syear=rs.getString(3);
	date=month+"/"+day+"/"+syear;

}
rs.close();
stmt.close();
}catch(Exception e){}
finally{connMgr.freeConnection("xavier",con);}

}


public void setdcount(int dcount)
{
	this.dcount=dcount;
}
public int getdcount()
{
	return this.dcount;
}
public String[] getddno() 
{ 
return this.ddno; 
} 

public void setdname(String[] dname) 
{ 
this.dname = dname; 
} 
public String[] getdname() 
{ 
return this.dname; 
} 


public void setacademicyear(String academicyear) 
{ 
this.academicyear = academicyear; 
} 
public String getacademicyear() 
{ 
return this.academicyear; 
} 
public void setcount(int count) 
{ 
this.count = count; 
} 
public int getcount() 
{ 
return this.count; 
} 

public void setsubcount(int subcount) 
{ 
this.subcount = subcount; 
} 
public int getsubcount() 
{ 
return this.subcount; 
} 


public void setdate(String date) 
{ 
this.date = date; 
} 
public String getdate() 
{ 
return this.date; 
} 
public String getvdate() 
{ 
return this.vdate; 
} 

public void setday(String day) 
{ 
this.day = day; 
} 
public String getday() 
{ 
return this.day; 
} 
public void setdno(String dno) 
{ 
this.dno = dno; 
} 
public String getdno() 
{ 
return this.dno; 
} 
public void setmonth(String month) 
{ 
this.month = month; 
} 
public String getmonth() 
{ 
return this.month; 
} 
public void setname(String[] name) 
{ 
this.name = name; 
} 
public String[] getname() 
{ 
return this.name; 
} 


public void sethours(String[] hours) 
{ 
this.hours = hours; 
} 
public String[] gethours() 
{ 
return this.hours; 
} 


public void setsubperiods(String[] subperiods) 
{ 
this.subperiods = subperiods; 
} 
public String[] getsubperiods() 
{ 
return this.subperiods; 
} 
public void setsubcode(String[] subcode) 
{ 
this.subcode = subcode; 
} 
public String[] getsubcode() 
{ 
return this.subcode; 
} 
public void setlog(String[] log) 
{ 
this.log = log; 
} 
public String[] getlog() 
{ 
return this.log; 
} 





public void setrollno(String[] rollno) 
{ 
this.rollno = rollno; 
} 
public String[] getrollno() 
{ 
return this.rollno; 
} 
public void setsemesters(String semesters) 
{ 
this.semesters = semesters; 
} 
public String getsemesters() 
{ 
return this.semesters; 
} 
public void setsyear(String syear) 
{ 
this.syear = syear; 
} 
public String getsyear() 
{ 
return this.syear; 
} 
public void setyear(String year) 
{ 
this.year = year; 
} 
public String getyear() 
{ 
return this.year; 
} 

public void setattendance(String[][] attendance) 
{ 
this.attendance = attendance; 
} 
public String[][] getattendance() 
{ 
return this.attendance; 
} 
public void setattendanceentered(String[] attendanceentered) 
{ 
this.attendanceentered = attendanceentered; 
} 
public String[] getattendanceentered() 
{ 
return this.attendanceentered; 
} 
public void setperiods(String[] periods) 
{ 
this.periods = periods; 
} 
public String[] getperiods() 
{ 
return this.periods; 
} 



public void setsubstaffid(String[] substaffid) 
{ 
this.substaffid = substaffid; 
} 
public String[] getsubstaffid() 
{ 
return this.substaffid; 
} 

public void setcontact(String[] contact) 
{ 
this.contact = contact; 
} 
public String[] getcontact() 
{ 
return this.contact; 
} 


public void setsubstaffname(String[] substaffname) 
{ 
this.substaffname = substaffname; 
} 
public String[] getsubstaffname() 
{ 
return this.substaffname; 
} 




public void setatt(String[] att) 
{ 
this.att = att; 
} 
public String[] getatt() 
{ 
return this.att; 
} 


public void setrollnoin(String rollnoin) 
{ 
this.rollnoin = rollnoin; 
} 
public String getrollnoin() 
{ 
return this.rollnoin; 
} 

public void setperiodsupdated(String periodsupdated) 
{ 
this.periodsupdated = periodsupdated; 
} 
public String getperiodsupdated() 
{ 
return this.periodsupdated; 
} 

public void setsemester(String semester) 
{ 
this.semester = semester; 
} 
public String getsemester() 
{ 
return this.semester; 
} 

public void setnoofperiods(int noofperiods)
{
	this.noofperiods=noofperiods;
}
public int getnoofperiods()
{
	return this.noofperiods;
}

public void setstaffid(String staffid) 
{ 
this.staffid = staffid; 
} 
public String getstaffid() 
{ 
return this.staffid; 
} 

public void setrno(String rno) 
{ 
this.rno = rno; 
} 
public String getrno() 
{ 
return this.rno; 
} 
public void sethrs(String hrs) 
{ 
this.hrs = hrs; 
} 
public String gethrs() 
{ 
return this.hrs; 
} 

public void setsubid(String subid) 
{ 
this.subid = subid; 
} 
public String getsubid() 
{ 
return this.subid; 
} 

public void setquer(String quer) 
{ 
this.quer = quer; 
} 
public String getquer() 
{ 
return this.quer; 
} 











public void setodacademicyear(String odacademicyear) 
{ 
this.odacademicyear = odacademicyear; 
} 
public String getodacademicyear() 
{ 
return this.odacademicyear; 
} 
public void setoddepartment(String oddepartment) 
{ 
this.oddepartment = oddepartment; 
} 
public String getoddepartment() 
{ 
return this.oddepartment; 
} 
public void setodendingdate(String odendingdate) 
{ 
this.odendingdate = odendingdate; 
} 
public String getodendingdate() 
{ 
return this.odendingdate; 
} 
public void setodname(String odname) 
{ 
this.odname = odname; 
} 
public String getodname() 
{ 
return this.odname; 
} 
public void setodperiods(String odperiods) 
{ 
this.odperiods = odperiods; 
} 
public String getodperiods() 
{ 
return this.odperiods; 
} 
public void setodrollno(String odrollno) 
{ 
this.odrollno = odrollno; 
} 
public String getodrollno() 
{ 
return this.odrollno; 
} 
public void setodsem(String odsem) 
{ 
this.odsem = odsem; 
} 
public String getodsem() 
{ 
return this.odsem; 
} 
public void setodstartingdate(String odstartingdate) 
{ 
this.odstartingdate = odstartingdate; 
} 
public String getodstartingdate() 
{ 
return this.odstartingdate; 
} 
public void setodyear(String odyear) 
{ 
this.odyear = odyear; 
} 
public String getodyear() 
{ 
return this.odyear; 
} 


public void setoddno(String oddno) 
{ 
this.oddno = oddno; 
} 
public String getoddno() 
{ 
return this.oddno; 
} 





}
