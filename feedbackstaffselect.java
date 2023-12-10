package sxcce;
import java.sql.*;
import dd.DBConnectionManager;
public class feedbackstaffselect
	{
		int rollno=0;
		int dno=0;
		int semesterno=0;
		String semester="";
		String academicyear="";
		String staffid[] = new String[500];
		String staffname[] = new String[500];
		String subjectcode[] = new String[500];
		String subjectname[] = new String[500];
		int theoryorlab[] = new int[500];
		int passmark[] = new int[500];
		int externalmark[] = new int[500];
		int pass[] = new int[500];
		int id=0;
		int count=0;
		int maxnamelen=0;
		String query="Query";

public String Selectacademic(int jk)
{
	String quer="";
	int found=0;
	int yr=0;
try
{
 DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
Statement stmt = con.createStatement();
quer="select * from feedbackidentify";
ResultSet rs = stmt.executeQuery(quer);
if(rs.next())
{
academicyear=rs.getString(1);
semester=rs.getString(2);
}
rs.close();
quer="select department,year from stud where academicyear='"+academicyear+"' and rollno="+rollno;
rs = stmt.executeQuery(quer);
if(rs.next())
{
dno=rs.getInt(1);
semesterno=rs.getInt(2);
yr=semesterno;
if(semesterno==2)semesterno=3;
else if(semesterno==3)semesterno=5;
else if(semesterno==4)semesterno=7;
if(semester.equals("EVEN"))
  semesterno++;
if(semester.equals("MID-EVEN"))
  semesterno++;
}
rs.close();
query=quer;

if(jk==1)
{
quer="select staff.staffid,upper(name),subjectcode,subjectname,"+
"syllabus.id,theoryorpractical,passmark from subjecthandled,syllabus,staff where "+
"staff.staffid=subjecthandled.staffid and "+
"syllabus.id=subjecthandled.id and subjectcode=subcode "+
"and syllabus.id=(select id from subjectidentify where "+
"semester="+semesterno+" and dno="+dno+" and academicyear='"+academicyear+"')and  theoryorpractical in (0,1,5) and "+
"subcode <> 'LIB12' and subjecthandled.mainorsub='M' and staff.staffid not in (select "+
"staffid from feedbackenteredornot where rollno="+rollno+" and oddeven='"+semester+"' and subjectcode=syllabus.subjectcode and "+
"id=syllabus.id) order by mainorsub,subcode";
count=0;
rs = stmt.executeQuery(quer);
while(rs.next())
{
	staffid[count]=rs.getString(1);
	staffname[count]=rs.getString(2);
	subjectcode[count]=rs.getString(3);
	subjectname[count]=rs.getString(4);
	id=rs.getInt(5);
	theoryorlab[count]=rs.getInt(6);
count++;
}

quer="select staff.staffid,upper(name),subjectcode,subjectname,syllabus.id,theoryorpractical,passmark from subjecthandled,syllabus,staff,subjectelective where "+
"staff.staffid=subjecthandled.staffid and syllabus.id=subjecthandled.id and subjectcode=subjecthandled.subcode "+
"and syllabus.id=subjectelective.id and  rollno="+rollno+" and subjectcode=subjectelective.subcode "+
"and syllabus.id=(select id from subjectidentify where "+
"semester="+semesterno+" and dno="+dno+" and academicyear='"+academicyear+"')and  theoryorpractical in (4) and "+
"subjecthandled.subcode <> 'LIB12' and subjecthandled.mainorsub='M' and staff.staffid not in (select "+
"staffid from feedbackenteredornot where rollno="+rollno+" and oddeven='"+semester+"' and subjectcode=syllabus.subjectcode and "+
"id=syllabus.id) order by mainorsub,subjecthandled.subcode";
rs = stmt.executeQuery(quer);
while(rs.next())
{
	staffid[count]=rs.getString(1);
	staffname[count]=rs.getString(2);
	subjectcode[count]=rs.getString(3);
	subjectname[count]=rs.getString(4);
	id=rs.getInt(5);
	theoryorlab[count]=rs.getInt(6);
count++;
}




rs.close();
for(int i=0;i<count;i++)
   {if(theoryorlab[i]==1)continue;
   	quer="select name from staff where staffid in(select staffid from subjecthandled where id="+id+" and subcode='"+subjectcode[i]+"' and mainorsub='S')";
rs = stmt.executeQuery(quer);
while(rs.next())
{
	staffname[i]=staffname[i]+" , "+rs.getString(1);
}
rs.close();
   	quer="select name from staff where staffid in(select staffid from subjecthandled where id="+id+" and subcode='"+subjectcode[i]+"' and mainorsub='Z')";
rs = stmt.executeQuery(quer);
while(rs.next())
{
	staffname[i]=staffname[i]+" , "+rs.getString(1);
}
rs.close();
   }
quer="select id from subjectidentify where academicyear='"+academicyear+"' and semester="+semesterno+" and dno="+dno;
rs = stmt.executeQuery(quer);
if(rs.next())
{
	id=rs.getInt(1);
}
rs.close();
/*

if((semester.equals("ODD"))||(semester.equals("EVEN")))
{

quer="select id from feedbackenteredornot where rollno="+rollno+" and id="+id+" and staffid='Administration'";
rs = stmt.executeQuery(quer);
if(!rs.next())
{
	staffid[count]="Administration";
	staffname[count]="Administration and Others";
	subjectcode[count]="admin";
	subjectname[count]="";
	theoryorlab[count]=3;
	count++;
}
rs.close();
}

*/



if((semester.equals("ODD"))||(semester.equals("EVEN")))
{

quer="select staff.staffid,upper(name),subjectcode,subjectname,"+
"syllabus.id,theoryorpractical,passmark from subjecthandled,syllabus,staff where "+
"staff.staffid=subjecthandled.staffid and "+
"syllabus.id=subjecthandled.id and subjectcode=subcode "+
"and syllabus.id=(select id from subjectidentify where "+
"semester="+semesterno+" and dno="+dno+" and academicyear='"+academicyear+"')and  theoryorpractical in (3) and "+
"subcode <> 'LIB12' and subjecthandled.mainorsub='M' and staff.staffid not in (select "+
"staffid from feedbackenteredornot where rollno="+rollno+" and oddeven='"+semester+"' and subjectcode=syllabus.subjectcode and "+
"id=syllabus.id) order by mainorsub,subcode";
rs = stmt.executeQuery(quer);
while(rs.next())
{
	staffid[count]=rs.getString(1);
	staffname[count]=rs.getString(2);
	subjectcode[count]=rs.getString(3);
	subjectname[count]=rs.getString(4);
	id=rs.getInt(5);
	theoryorlab[count]=18;
//	theoryorlab[count]=rs.getInt(6);
count++;
}


quer="select tutor.staffid,name from tutor,staff where  tutor.staffid=staff.staffid and academicyear='"+academicyear+"' and rollno="+rollno;
rs = stmt.executeQuery(quer);
if(rs.next())
{
	staffid[count]=rs.getString(1);
	staffname[count]=rs.getString(2);
	subjectcode[count]="mentor";
	subjectname[count]="Mentoring";
	theoryorlab[count]=6;
	count++;
}
rs.close();


quer="select id from feedbackenteredornot where rollno="+rollno+" and id="+id+" and subjectcode='mentor'";
rs = stmt.executeQuery(quer);

if(rs.next()) found=1;
rs.close();

if(found==1) count--;


quer="select id from feedbackenteredornot where rollno="+rollno+" and id="+id+" and staffid='Library'";
rs = stmt.executeQuery(quer);
if(!rs.next())
{
	staffid[count]="Library";
	staffname[count]="Library";
	subjectcode[count]="Library";
	subjectname[count]="Library";
	theoryorlab[count]=7;
	count++;
}
rs.close();

quer="select id from feedbackenteredornot where rollno="+rollno+" and id="+id+" and staffid='Department'";
rs = stmt.executeQuery(quer);
if(!rs.next())
{
	staffid[count]="Department";
	staffname[count]="Department";
	subjectcode[count]="Department";
	subjectname[count]="Department";
	theoryorlab[count]=8;
	count++;
}
rs.close();


quer="select id from feedbackenteredornot where rollno="+rollno+" and id="+id+" and staffid='Placement'";
rs = stmt.executeQuery(quer);
if(!rs.next())
{
	staffid[count]="Placement";
	staffname[count]="Placement and Entrepreneurship";
	subjectcode[count]="Placement";
	subjectname[count]="Placement & EDC";
	theoryorlab[count]=9;
	count++;
}
rs.close();

quer="select id from feedbackenteredornot where rollno="+rollno+" and id="+id+" and staffid='Infrastructure'";
rs = stmt.executeQuery(quer);
if(!rs.next())
{
	staffid[count]="Infrastructure";
	staffname[count]="Infrastructure";
	subjectcode[count]="Infrastructure";
	subjectname[count]="Facilities";
	theoryorlab[count]=10;
	count++;
}
rs.close();

quer="select id from feedbackenteredornot where rollno="+rollno+" and id="+id+" and staffid='Management'";
rs = stmt.executeQuery(quer);
if(!rs.next())
{
	staffid[count]="Management";
	staffname[count]="Management & Administration";
	subjectcode[count]="Management";
	subjectname[count]="Management";
	theoryorlab[count]=11;
	count++;
}
rs.close();

quer="select id from feedbackenteredornot where rollno="+rollno+" and id="+id+" and staffid='Office'";
rs = stmt.executeQuery(quer);
if(!rs.next())
{
	staffid[count]="Office";
	staffname[count]="Office";
	subjectcode[count]="Office";
	subjectname[count]="College Office";
	theoryorlab[count]=12;
	count++;
}
rs.close();


quer="select ltrim(rtrim(str(cells.cellid))),cellname from cells,celloffbearers "
+"where cells.cellid=celloffbearers.cellid and academicyear='"+academicyear+"' and userid='"+rollno+"'"
+"and cells.cellid not in(select staffid from feedbackenteredornot where rollno="+rollno+" and id="+id+" and subjectcode='Cells')";

query=quer;
rs = stmt.executeQuery(quer);

while(rs.next())
{
	staffid[count]=rs.getString(1);
	staffname[count]=rs.getString(2);
	subjectcode[count]="Cells";
	subjectname[count]="Cells";
	theoryorlab[count]=13;
	count++;
}
rs.close();
}

if((semester.equals("ODD"))||(semester.equals("EVEN")))
{
quer="select id from feedbackenteredornot where rollno="+rollno+" and id="+id+" and staffid='Canteen'";
rs = stmt.executeQuery(quer);
if(!rs.next())
{
	staffid[count]="Canteen";	staffname[count]="Canteen";	subjectcode[count]="Canteen";	subjectname[count]="Canteen";	theoryorlab[count]=19;
	count++;
}
rs.close();

quer="select id from feedbackenteredornot where rollno="+rollno+" and id="+id+" and staffid='Transport'";
rs = stmt.executeQuery(quer);
if(!rs.next())
{
	staffid[count]="Transport";	staffname[count]="Transport";	subjectcode[count]="Transport";	subjectname[count]="Transport";	theoryorlab[count]=20;
	count++;
}
rs.close();

quer="select id from feedbackenteredornot where rollno="+rollno+" and id="+id+" and staffid='Hostel'";
rs = stmt.executeQuery(quer);
if(!rs.next())
{
	staffid[count]="Hostel";	staffname[count]="Hostel";	subjectcode[count]="Hostel";	subjectname[count]="Hostel";	theoryorlab[count]=21;
	count++;
}
rs.close();

}

}
if(jk==2)
{
if((yr==1) && (semester.equals("EVEN")) && ( (dno<=8) || (dno==14) || (dno==17) || (dno==27) ))
{
quer="select id from feedbackenteredornot where rollno="+rollno+" and id="+id+" and staffid='First'";
rs = stmt.executeQuery(quer);
if(!rs.next())
{
	staffid[count]="First";
	staffname[count]="First";
	subjectcode[count]="First";
	subjectname[count]="First Year Exit Survey";
	theoryorlab[count]=16;
	count++;
}
rs.close();
}

if((semester.equals("ODD"))||(semester.equals("EVEN")))
{


quer="select staff.staffid,upper(name),subjectcode,subjectname,"+
"syllabus.id,theoryorpractical,passmark from subjecthandled,syllabus,staff where "+
"staff.staffid=subjecthandled.staffid and "+
"syllabus.id=subjecthandled.id and subjectcode=subcode "+
"and syllabus.id=(select id from subjectidentify where "+
"semester="+semesterno+" and dno="+dno+" and academicyear='"+academicyear+"')and  theoryorpractical in (0,1,3,4,5) and "+
"subcode <> 'LIB12' and subjecthandled.mainorsub='M' and 'COEXS' not in (select "+
"staffid from feedbackenteredornot where rollno="+rollno+" and oddeven='"+semester+"' and subjectcode=syllabus.subjectcode and "+
"id=syllabus.id) order by mainorsub,subcode";
rs = stmt.executeQuery(quer);
while(rs.next())
{
	staffid[count]=rs.getString(1);
	staffid[count]="COEXS";
	staffname[count]=rs.getString(2);
	staffname[count]="Course Exit Survey";
	subjectcode[count]=rs.getString(3);
	subjectname[count]=rs.getString(4);
	id=rs.getInt(5);
	theoryorlab[count]=rs.getInt(6);
count++;
}
}
}

/*

quer="select id from feedbackenteredornot where rollno="+rollno+" and id="+id+" and subjectcode='Cells'";
rs = stmt.executeQuery(quer);

found=0;
if(rs.next()) found=1;
rs.close();

if(found==1) count--;


quer="select syllabus.subjectcode,subjectname,maximummark,passmark,externalmark,id "+
"from syllabus,universitymarks where id = (select id from subjectidentify "+
"where semester="+semesterno+" and dno="+dno+" and academicyear='"+academicyear+"') "+
"and theoryorpractical=1 and universitymarks.subjectcode=syllabus.subjectcode "+
"and rollno="+rollno+" and month=(select month from resultidentify) and "+
"year = (select year from resultidentify) and universitymarks.subjectcode not in "+
"(select subjectcode from feedbackenteredornot where rollno="+rollno+" "+
"and id in (select id from subjectidentify where "+
"semester="+semesterno+" and dno="+dno+" and academicyear='"+academicyear+"') and staffid in ('resultanalysis1','resultanalysis2'))";
rs = stmt.executeQuery(quer);
   while(rs.next())
      {

	staffname[count]="Result Analysis";
	subjectcode[count]=rs.getString(1);
	subjectname[count]=rs.getString(2);
	if(rs.getInt(4)<=rs.getInt(5))pass[count]=1;
	else pass[count]=0;
	id=rs.getInt(6);
	if(pass[count]==1)
	theoryorlab[count]=4;
	else theoryorlab[count]=5;
	if(pass[count]==1){
	subjectname[count]=subjectname[count]+" --> Pass ";
	staffid[count]="resultanalysis1";
	}
	else 
	{
	subjectname[count]=subjectname[count]+" --> Fail ";
	staffid[count]="resultanalysis2";
	}
count++;
      }
rs.close();

*/

stmt.close();
connMgr.freeConnection("xavier",con);
}catch(Exception e){return semester+quer+e.toString();}
return semester+quer;

}


public String SelectStaff(int dno1, int cat)
{
	String quer="";
	int found=0;
try
{
 DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
Statement stmt = con.createStatement();
quer="select * from hodfeedbackidentify";
ResultSet rs = stmt.executeQuery(quer);
if(rs.next())
{
academicyear=rs.getString(1);
semester=rs.getString(2);
}
rs.close();
if(dno1==0)
{
quer="select staff.staffid,upper(name),'"+academicyear+"',cname,"+cat+","+cat+" from staff,department,staffdesignation "
+" where slno=designation and department.dno=staff.dno "
+" and active=1 and category='Teaching' and"
+" staff.staffid not in (select staffid from feedbackenteredornot where id="+cat+" and oddeven='"+semester+"' and subjectcode='"+academicyear+"')"
+" order by staff.dno,designation,staffid";
}
else
{
quer="select staff.staffid,upper(name),'"+academicyear+"',cname,"+cat+","+cat+" from staff,department,staffdesignation "
+" where slno=designation and department.dno=staff.dno "
+" and active=1 and category='Teaching' and staff.dno="+dno1+" and"
+" staff.staffid not in (select staffid from feedbackenteredornot where id="+cat+" and oddeven='"+semester+"' and subjectcode='"+academicyear+"')"
+" order by designation,staffid";
}
count=0;
rs = stmt.executeQuery(quer);
while(rs.next())
{
	staffid[count]=rs.getString(1);
	staffname[count]=rs.getString(2);
	subjectcode[count]=rs.getString(3);
	subjectname[count]=rs.getString(4);
	id=rs.getInt(5);
	theoryorlab[count]=rs.getInt(6);
count++;
}
rs.close();

stmt.close();
connMgr.freeConnection("xavier",con);
}catch(Exception e){return semester+quer+e.toString();}
return semester+quer;

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

public void setcount(int count) 
{ 
this.count = count; 
} 
public int getcount() 
{ 
return this.count; 
}

public void setsemesterno(int semesterno) 
{ 
this.semesterno = semesterno; 
} 
public int getsemesterno() 
{ 
return this.semesterno; 
}


public void setdno(int dno) 
{ 
this.dno = dno; 
} 
public int getdno() 
{ 
return this.dno; 
}

public void setrollno(int rollno) 
{ 
this.rollno = rollno; 
} 
public int getrollno() 
{ 
return this.rollno; 
}

public void setid(int id) 
{ 
this.id = id; 
} 
public int getid() 
{ 
return this.id; 
}

public String getquery() 
{ 
return this.query; 
}

 public void setstaffid(String[] staffid) 
{ 
this.staffid = staffid; 
} 
public String[] getstaffid() 
{ 
return this.staffid; 
} 
public void setstaffname(String[] staffname) 
{ 
this.staffname = staffname; 
} 
public String[] getstaffname() 
{ 
return this.staffname; 
} 
public void setsubjectcode(String[] subjectcode) 
{ 
this.subjectcode = subjectcode; 
} 
public String[] getsubjectcode() 
{ 
return this.subjectcode; 
} 
public void setsubjectname(String[] subjectname) 
{ 
this.subjectname = subjectname; 
} 
public String[] getsubjectname() 
{ 
return this.subjectname; 
} 
public int[] gettheoryorlab()
{
	return this.theoryorlab;
}

public int[] getpass()
{
	return this.pass;
}

	}
	