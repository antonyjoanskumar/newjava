package sxcce;
import java.sql.*;
public class coursecertificate
	{
		String report[][] = new String[100][100];
		String dno="";
		int rollno=0;
		String year="";
		String academicyear="";
		int dorl=0;
		int count=0;
		public String Report()
			{
				String deep="";
				if(rollno>0)deep=" and stud.rollno="+rollno+" ";
String quer="select student.name,departmentno, "+
"admissionno,directorlateral,student.rollno,ltrim(dname),sex  from student,stud,department where "+
"stud.rollno=student.rollno and stud.academicyear= "+
"'"+academicyear+"' and active=1 and department.dno=departmentno and ";

if(dorl==1)
  quer=quer+"student.directorlateral='Direct' and ";
if(dorl==2)
  quer=quer+"student.directorlateral='Lateral' and ";



quer=quer+" stud.department="+dno+" "+deep+""+
"and stud.year="+year+" order by stud.rollno";


				try
					{
					Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
					Connection con = DriverManager.getConnection("jdbc:odbc:xavier","sxcce","tamil5india6");
					Statement stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery(quer);
					while(rs.next())
					  {

					  	for(int i=0;i<7;i++)
					  	   report[i][count]=rs.getString(i+1);
					  	count++;   
					  }
					  for(int i=0;i<count;i++)
					     {int len=report[2][i].length();
					     	for (int z=len;z<5;z++)
					     	  report[2][i]="0"+report[2][i];
					     }
					rs.close();
for(int i=0;i<count;i++)
{rs= stmt.executeQuery("select contact from contact where rollno="+report[4][i]);
report[7][i]="3";
if(rs.next())
{
	report[7][i]=rs.getString(1);
}
rs.close();
}
					stmt.close();
					con.close();
					}catch(Exception e){return quer+e.toString();}
					return quer;
			}
public void setdno(String dno) 
{ 
this.dno = dno; 
} 
public String getdno() 
{ 
return this.dno; 
} 
public void setyear(String year) 
{ 
this.year = year; 
} 
public String getyear() 
{ 
return this.year; 
}
public String[][] getreport()
{
	return this.report;
}
public int getcount()
{
	return this.count;
}
public String getacademicyear()
{
	return this.academicyear;
}
public void setacademicyear(String academicyear)
{
	this.academicyear=academicyear;
}
public void setrollno(int rollno)
   {
   	this.rollno=rollno;
   }

public void setdorl(int dorl)
   {
   	this.dorl=dorl;
   }
   public int getdorl()
   {
   	return this.dorl;
   }

}