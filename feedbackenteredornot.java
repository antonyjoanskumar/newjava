package sxcce;
import java.sql.*;
import dd.DBConnectionManager;
public class feedbackenteredornot
	{
		int rollno[]= new int[100];
		String name[]= new String[100];
		int subjects[]= new int[100];
		int dno=0;
		int semester=0;
		int year=0;
		String academicyear="";
		int maxsubject=0;
		int count=0;
public String Select(String oddeven)
{
	if(semester<3)year=1;
	else if(semester<5)year=2;
	else if(semester<7)year=3;
	else if(semester<9)year=4;
	String quer="";
	int sub1=0;
try
{
 DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
Statement stmt = con.createStatement();
quer="select (count(staff.staffid)+1) from subjecthandled,syllabus,staff where "+
"staff.staffid=subjecthandled.staffid and "+
"syllabus.id=subjecthandled.id and subjectcode=subcode "+
"and syllabus.id=(select id from subjectidentify where "+
"semester="+semester+" and dno="+dno+" and academicyear='"+academicyear+"')and theoryorpractical in (0,1,5) and "+
"subcode <> 'LIB12' and subjecthandled.mainorsub='M' ";

ResultSet rs = stmt.executeQuery(quer);
if(rs.next())
{
	maxsubject=rs.getInt(1);
}
rs.close();

sub1=0;
quer="select count(*) from subjectelective,subjectidentify where subjectidentify.id=subjectelective.id  and "+
"semester="+semester+" and dno="+dno+" and academicyear='"+academicyear+"' group by rollno ";
rs = stmt.executeQuery(quer);
if(rs.next())
{
		sub1=rs.getInt(1);
}
rs.close();

maxsubject+=sub1;


//quer="select stud.name,stud.rollno from stud,student where stud.rollno=student.rollno and active=1 and "+
//"year="+year+" and academicyear='"+academicyear+"' "+
//"and department="+dno+" and stud.rollno not in( "+
//"select rollno from feedbackenteredornot where id in "+
//"(select id from subjectidentify where "+
//"semester="+semester+" and dno="+dno+" and academicyear='"+academicyear+"') "+
//" group by rollno having count(rollno)>="+(maxsubject-1)+") order by stud.rollno";

 
quer="SELECT s.Name,s.rollno, (ISNULL(f.CNT,0)) c1 "
+"FROM (select stud.name,stud.rollno from stud,student where stud.rollno=student.rollno and active=1 and "
+"year="+year+" and academicyear='"+academicyear+"' and department="+dno+" ) s "
+"LEFT OUTER JOIN (select rollno,COUNT(*) CNT from feedbackenteredornot where id in (select id from subjectidentify where "
+"semester="+semester+" and dno="+dno+" and academicyear='"+academicyear+"'  and oddeven='"+oddeven+"') group by rollno having count(rollno)>=0) f ON s.rollno = f.rollno order by c1";

 
rs = stmt.executeQuery(quer);
while(rs.next())
{
	name[count]=rs.getString(1);
	rollno[count]=rs.getInt(2);
	subjects[count]=rs.getInt(3);
	count++;
}
rs.close();
stmt.close();
connMgr.freeConnection("xavier",con);
}catch(Exception e){return e.toString();}
return quer;

}
public void setacademicyear(String academicyear) 
{ 
this.academicyear = academicyear; 
} 
public String getacademicyear() 
{ 
return this.academicyear; 
} 
public void setsemester(int semester) 
{ 
this.semester = semester; 
} 
public int getsemester() 
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

public void setmaxsubject(int  maxsubject) 
{ 
this.maxsubject =  maxsubject; 
} 
public int getmaxsubject() 
{ 
return this.maxsubject; 
}

public void setdno(int dno) 
{ 
this.dno = dno; 
} 
public int getdno() 
{ 
return this.dno; 
}


public void setyear(int year) 
{ 
this.year = year; 
} 
public int getyear() 
{ 
return this.year; 
}

public void setsubjects(int[] subjects) 
{ 
this.subjects = subjects; 
} 
public int[] getsubjects() 
{ 
return this.subjects; 
}


public void setrollno(int[] rollno) 
{ 
this.rollno = rollno; 
} 
public int[] getrollno() 
{ 
return this.rollno; 
}

public void setname(String[] name) 
{ 
this.name = name; 
} 
public String[] getname() 
{ 
return this.name; 
}


}
	