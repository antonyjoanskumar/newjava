
package sxcce;
import java.sql.*;
public class SubjectStaffInsert
{
String semester="";
String academicyear="";
String matchname[] = new String[500];
String matchstaffid[] = new String[500];
String matchdesg[] = new String[500];
String staffid="";
String name="";
String designation="";
String sday="";
int sd=1;
int count=0;

String subjectcode[] = new String[20];
String abbr[] = new String[20];
String hours[][][] = new String[200][20][20];
String subjectname[] = new String[20];
String mainorsub[] = new String[20];
int theoryorpractical[] = new int[20];
int sem[] = new int[20];
String acadyear[] = new String[20];
String vdname[] = new String[20];
int vdno[] = new int[20];
int vid[] = new int[20];
int vcount=0;
int dno=0;

public void View(int evenorodd)
{vcount=0;
String quer="";
if(evenorodd==1)
quer="select syllabus.subjectcode,syllabus.subjectname,subjectidentify.semester,"+
"subjectidentify.academicyear,department.dname,department.dno,"+
"subjectidentify.id,mainorsub,theoryorpractical,abbr from subjecthandled,syllabus,subjectidentify,department "+
"where subjecthandled.id=syllabus.id and staffid='"+staffid+"' and "+
"subjectidentify.dno=department.dno and subjectidentify.id=subjecthandled.id and "+
"syllabus.subjectcode=subjecthandled.subcode and "+
"subjectidentify.semester in(1,3,5,7) and subjectidentify.academicyear='"+academicyear+"'";
else if(evenorodd==2)
quer="select syllabus.subjectcode,syllabus.subjectname,subjectidentify.semester,"+
"subjectidentify.academicyear,department.dname,department.dno,"+
"subjectidentify.id,mainorsub,theoryorpractical,abbr from subjecthandled,syllabus,subjectidentify,department "+
"where subjecthandled.id=syllabus.id and staffid='"+staffid+"' and "+
"subjectidentify.dno=department.dno and subjectidentify.id=subjecthandled.id and "+
"syllabus.subjectcode=subjecthandled.subcode and "+
"subjectidentify.semester in(2,4,6,8) and subjectidentify.academicyear='"+academicyear+"'";
try
{
Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
Connection con = DriverManager.getConnection("jdbc:odbc:xavier","sxcce","tamil5india6");
Statement stmt = con.createStatement();
ResultSet rs = stmt.executeQuery(quer);
while(rs.next())
{
	subjectcode[vcount] = rs.getString(1);
	subjectname[vcount]=rs.getString(2);
	sem[vcount]=rs.getInt(3);
	acadyear[vcount]=rs.getString(4);
	vdname[vcount]=rs.getString(5);
	vdno[vcount] = rs.getInt(6);
	vid[vcount]=rs.getInt(7);
	mainorsub[vcount]=rs.getString(8);
	theoryorpractical[vcount]=rs.getInt(9);
	abbr[vcount]=rs.getString(10);
	vcount++;
}
rs.close();
if(evenorodd==1)
quer="select subcode,subjectidentify.semester,"+
"subjectidentify.academicyear,department.dname,department.dno,"+
"subjectidentify.id from subjecthandled,subjectidentify,department "+
"where staffid='"+staffid+"' and subjectidentify.dno=department.dno and "+
"subjectidentify.id=subjecthandled.id and "+
"subjectidentify.semester in(1,3,5,7) and subjectidentify.academicyear='"+academicyear+"'";
else if(evenorodd==2)
quer="select subcode,subjectidentify.semester,"+
"subjectidentify.academicyear,department.dname,department.dno,"+
"subjectidentify.id from subjecthandled,subjectidentify,department "+
"where staffid='"+staffid+"' and subjectidentify.dno=department.dno and "+
"subjectidentify.id=subjecthandled.id and "+
"subjectidentify.semester in(2,4,6,8) and subjectidentify.academicyear='"+academicyear+"'";
rs=stmt.executeQuery(quer);
	int fou=0;
while(rs.next())
{fou=0;
	subjectcode[vcount] = rs.getString(1);;
	subjectname[vcount]="Library";
	mainorsub[vcount]="M";
	sem[vcount]=rs.getInt(2);
	acadyear[vcount]=rs.getString(3);
	vdname[vcount]=rs.getString(4);
	vdno[vcount] = rs.getInt(5);
	vid[vcount]=rs.getInt(6);
	for(int h=0;h<vcount;h++)
	  if(subjectcode[vcount].equals(subjectcode[h]) && vid[vcount]==vid[h])fou=1;
	if(fou==0)
     	vcount++;
}
rs.close();

quer="select substring(reason,1,3) from attendance.dbo.holidaymaster where fdate in (select max(tdate) from attendance.dbo.holidaymaster)";
rs=stmt.executeQuery(quer);
if(rs.next())
	sday=rs.getString(1);
rs.close();
if(sday.equals("MON"))	sd=1;
if(sday.equals("TUE"))	sd=2;
if(sday.equals("WED"))	sd=3;
if(sday.equals("THU"))	sd=4;
if(sday.equals("FRI"))	sd=5;
stmt.close();
con.close();
}catch(Exception e){}

}
	
	


	
		public void Insert(String staffid,String subcode,String id,String mainorsub)
			{
				try
					{
					Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
					Connection con = DriverManager.getConnection("jdbc:odbc:xavier","sxcce","tamil5india6");
					Statement stmt = con.createStatement();
					PreparedStatement ps = con.prepareStatement("insert into subjecthandled values(?,?,?,1,?)");
					ps.setString(1,staffid);
					ps.setString(2,subcode);
					ps.setString(3,id);
					ps.setString(4,mainorsub);
					int i = ps.executeUpdate();
					ps.close();
					con.close();
					}catch(Exception e){}
			}
		public void Delete(String staffid,String academicyear,int evenorodd)
			{
				try
					{
					Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
					Connection con = DriverManager.getConnection("jdbc:odbc:xavier","sxcce","tamil5india6");
					Statement stmt = con.createStatement();
					PreparedStatement ps=con.prepareStatement("");
					if(evenorodd==1)
					ps = con.prepareStatement("delete from subjecthandled where staffid=? and  id in(select id from subjectidentify where semester in(1,3,5,7) and academicyear=?)");
					else if(evenorodd==2)
					ps = con.prepareStatement("delete from subjecthandled where staffid=? and  id in(select id from subjectidentify where semester in(2,4,6,8) and academicyear=?)");
					ps.setString(1,staffid);
					ps.setString(2,academicyear);
					int i = ps.executeUpdate();
					ps.close();
					con.close();
					}catch(Exception e){}
			}
public void Desig()
{
try
{
Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
Connection con = DriverManager.getConnection("jdbc:odbc:xavier","sxcce","tamil5india6");
Statement stmt = con.createStatement();
ResultSet rs = stmt.executeQuery("select name,staffdesignation.cname,dno from staff,staffdesignation where staff.designation=staffdesignation.slno and staffid='"+staffid+"'");
if(rs.next())
{
	name=rs.getString(1);
	designation=rs.getString(2);
	dno=rs.getInt(3);
}
rs.close();
stmt.close();
con.close();
}catch(Exception e){}
}
public String StaffSelect(int ddno)
{
int found=0;
count=0;
String quer;
if(ddno==0)
quer="select staffid,name,cname from staff,staffdesignation where designation=slno and active=1 and category in('Teaching') order by dno,slno,appdate";
else
quer="select staffid,name,cname from staff,staffdesignation where dno="+ddno+" and designation=slno and active=1 and category in('Teaching') order by slno,appdate";

try
{
Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
Connection con = DriverManager.getConnection("jdbc:odbc:xavier","sxcce","tamil5india6");
Statement stmt = con.createStatement();
ResultSet rs = stmt.executeQuery(quer);
while(rs.next())
{
matchstaffid[count]=rs.getString(1);
matchname[count]=rs.getString(2);
matchdesg[count]=rs.getString(3);
count++;
found=1;
}
rs.close();
stmt.close();
con.close();
}catch(Exception e){}
return found+""; 
}


public int MatchView(int select)
{
int found=0;
String quer;
if(select==1)
   {
   	quer="select staffid,name from staff where staffid='"+staffid+"' and staffid like '%T%' ";
   }
   else {
quer="select staff.staffid,name from staff,salary where staff.staffid=salary.staffid and "+
"(salary.staffid='"+staffid+"') and salary.semester='"+semester+"' and "+
"salary.academicyear='"+academicyear+"'";
}
try
{
Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
Connection con = DriverManager.getConnection("jdbc:odbc:xavier","sxcce","tamil5india6");
Statement stmt = con.createStatement();
ResultSet rs = stmt.executeQuery(quer);
while(rs.next())
{
matchstaffid[count]=rs.getString(1);
matchname[count]=rs.getString(2);
count++;
found=1;
}
rs.close();
if(count==0)
   {
if(select==1)
   {
   	quer="select staffid,name from staff where staffid='"+staffid+"' or name like '"+name+"%' and staffid like '%T%' ";
   }
   else {
   	quer="select staff.staffid,name from staff,salary where staff.staffid=salary.staffid and "+
 "(salary.staffid='"+staffid+"' or staff.name like '"+name+"%') and salary.semester='"+semester+"' and "+
 "salary.academicyear='"+academicyear+"'";}
rs = stmt.executeQuery(quer);
while(rs.next())
{
matchstaffid[count]=rs.getString(1);
matchname[count]=rs.getString(2);
count++;
found=1;
}
rs.close();if(select==1)
   {
   	quer="select staffid,name from staff where staffid='"+staffid+"' or name like '%"+name+"%' and staff.name not in(select name from staff where name like '"+name+"%') and staffid like '%T%' ";
   }
   else {
   	quer="select staff.staffid,name from staff,salary where staff.staffid=salary.staffid and "+
 "(salary.staffid='"+staffid+"' or staff.name like '%"+name+"%' and staff.name not in(select name from staff where name like '"+name+"%')) and salary.semester='"+semester+"' and "+
 "salary.academicyear='"+academicyear+"'";}
rs = stmt.executeQuery(quer);
while(rs.next())
{
matchstaffid[count]=rs.getString(1);
matchname[count]=rs.getString(2);
count++;
found=1;
}
rs.close();   }
stmt.close();
con.close();
}catch(Exception e){}
return found; 
}

public void ViewHour()
{
String quer="";
int a=0;
int b=0;
String c="";

try
{
Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
Connection con = DriverManager.getConnection("jdbc:odbc:xavier","sxcce","tamil5india6");
Statement stmt = con.createStatement();
ResultSet rs;
for(int i=0;i<10;i++)
  for(int j=0;j<10;j++)
    for(int k=0;k<10;k++)
       hours[i][j][k]="";
for(int i=0;i<vcount;i++)
{    
//quer="select * from periods where id="+vid[i]+" and subjectcode='"+subjectcode[i]+"'";    
quer="select max(periods.id), max(periods.day), max(periods.hour), max(periods.subjectcode), max(syllabus.abbr) from syllabus,periods where syllabus.subjectcode = periods.subjectcode and  periods.id="+vid[i]+" and periods.subjectcode='"+subjectcode[i]+"' group by day,hour";

//quer="select periods.id, periods.day, periods.hour, periods.subjectcode, syllabus.abbr from syllabus,periods where syllabus.subjectcode = periods.subjectcode and  periods.id="+vid[i]+" and periods.subjectcode='"+subjectcode[i]+"'";    
rs = stmt.executeQuery(quer);
while(rs.next())
{
//	hours[i][rs.getInt(2)][rs.getInt(3)]=abbr[i];
//	hours[i][rs.getInt(2)][rs.getInt(3)]=subjectcode[i];
//	hours[i][rs.getInt(2)][rs.getInt(3)]=rs.getString(4);
	a=rs.getInt(2);
	b=rs.getInt(3);
	c=rs.getString(4);
    hours[i][a][b]=c;
if(a==sd)
    hours[i][6][b]=c;
}
rs.close();
}


stmt.close();
con.close();
}catch(Exception e){}

}



public String ViewHour1()
{
String quer="";
int a,aj;
try
{
Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
Connection con = DriverManager.getConnection("jdbc:odbc:xavier","sxcce","tamil5india6");
Statement stmt = con.createStatement();
ResultSet rs;
for(int i=0;i<10;i++)
  for(int j=0;j<10;j++)
    for(int k=0;k<10;k++)
       hours[i][j][k]="";
for(int i=0;i<vcount;i++)
{    
quer="select max(periods.id), max(periods.day), max(periods.hour), max(periods.subjectcode), max(syllabus.abbr) from syllabus,periods where syllabus.subjectcode = periods.subjectcode and  periods.id="+vid[i]+" and periods.subjectcode='"+subjectcode[i]+"' group by day,hour";

rs = stmt.executeQuery(quer);
while(rs.next())
{
//	hours[i][rs.getInt(2)][rs.getInt(3)]=rs.getString(4);
    a=rs.getInt(2);
    aj=rs.getInt(3);
	quer=rs.getString(4);
//	hours[i][rs.getInt(2)][rs.getInt(3)]="<a href=javascript:popWinOpen('../legend/subjectname.jsp?scode=" + quer + "',500,50);>"+ quer + "</a>";
	hours[i][a][aj]="<a href=javascript:popWinOpen('../legend/subjectname.jsp?scode=" + quer + "',500,50);>"+ quer + "</a>";
}
rs.close();
}
stmt.close();
con.close();
}catch(Exception e){return e.toString();}
return quer;
}


public void setstaffid(String staffid)
{
	this.staffid=staffid;
}
public String getstaffid()
{
	return this.staffid;
}
public void setacademicyear(String academicyear) 
{ 
this.academicyear = academicyear; 
} 
public String getacademicyear() 
{ 
return this.academicyear; 
} 
public void setsemester(String semester) 
{ 
this.semester = semester; 
} 
public String getsemester() 
{ 
return this.semester; 
} 
public String[] getmatchname()
{
	return this.matchname;
} 
public String[] getmatchstaffid()
{
	return this.matchstaffid;
}
public String[] getmatchdesg()
{
	return this.matchdesg;
} 
public int getcount()
{
	return this.count;
}
public void setname(String name)
{
	this.name=name;
}
public String getname()
{
	return this.name;
}
public String getdesignation()
{
	return this.designation;
}
	public int getvcount()
	{
		return this.vcount;
	}
	public int[] getsem()
	{
		return this.sem;
	}
	public int[] getvdno()
	{
		return this.vdno;
	}
	public int[] getvid()
	{
		return this.vid;
	}
	public String[] getsubjectcode()
	{
		return this.subjectcode;
	}
	public String[] getabbr()
	{
		return this.abbr;
	}
	public int[] gettheoryorpractical()
	{
		return this.theoryorpractical;
	}
	public String[] getsubjectname()
	{
		return this.subjectname;
	}
	public String[] getacadyear()
	{
		return this.acadyear;
	}
	public String[] getvdname()
	{
		return this.vdname;
	}
	public String[] getmainorsub()
	{
		return this.mainorsub;
	}
	public String[][][] gethours()
	{
		return this.hours;
	}
	public int getdno()
	{
		return this.dno;
	}
}
