package sxcce;
import java.sql.*;
public class transfercertificate
	{
		String report[][] = new String[40][100];
		String dno="";
		String year="";
		int dorl=0;
		int rollno=0;
		String academicyear="";
		int count=0;
		
		public String Report()
			{
				String deep="";
				if(rollno>0)deep=" and stud.rollno="+rollno+" ";
String quer="select student.name,initial,community,religion,"+
"substring(('000'+ltrim(rtrim(str(day(dateofbirth))))),"+
"len('000'+ltrim(rtrim(str(day(dateofbirth)))))-1,2)+'-'+ "+ 
"substring(('000'+ltrim(rtrim(str(month(dateofbirth))))), "+
"len('000'+ltrim(rtrim(str(month(dateofbirth)))))-1,2)+'-'+ "+
"ltrim(rtrim(str(year(dateofbirth)))),admissionno, "+
"ltrim(dname),student.rollno,sex, "+
"substring(('000'+ltrim(rtrim(str(day(yearofjoining))))), "+
"len('000'+ltrim(rtrim(str(day(yearofjoining)))))-1,2)+'-'+ "+
"substring(('000'+ltrim(rtrim(str(month(yearofjoining))))), "+
"len('000'+ltrim(rtrim(str(month(yearofjoining)))))-1,2)+'-'+ "+
"ltrim(rtrim(str(year(yearofjoining)))) "+
"from student,stud,department where "+
"stud.rollno=student.rollno and active=1 and "+
"stud.academicyear='"+academicyear+"' and year="+year+" "+
"and department.dno="+dno+" "+
"and ";
if(dorl==1)
  quer=quer+"student.directorlateral='Direct' and ";
if(dorl==2)
  quer=quer+"student.directorlateral='Lateral' and ";
quer=quer+" department.dno=student.departmentno "+deep+""+
"order by stud.rollno,department.dno";







				try
					{
					Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
					Connection con = DriverManager.getConnection("jdbc:odbc:xavier","sxcce","tamil5india6");
					Statement stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery(quer);
					while(rs.next())
					  {

					  	for(int i=0;i<10;i++)
					  	   report[i][count]=rs.getString(i+1);
					  	count++;   
					  }
					  for(int i=0;i<count;i++)
					    {
					    	


					    	if(report[3][i].equals("Other Diocese Catholic") || report[3][i].equals("Other Christians") || report[3][i].equals("Kottar Diocese Catholic") || report[3][i].equals("Kuzhithurai Diocese Catholic"))
					    	   report[3][i]="Christian";
					    }
					  for(int i=0;i<count;i++)
					     {int len=report[5][i].length();
					     	for (int z=len;z<5;z++)
					     	  report[5][i]="0"+report[5][i];
					     }
					    
					rs.close();
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
public String getacademicyear()
{
	return this.academicyear;
}
public void setacademicyear(String academicyear)
{
	this.academicyear=academicyear;
}
}
