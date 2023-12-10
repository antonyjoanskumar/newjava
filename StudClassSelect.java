package sxcce;
import java.sql.*;
public class StudClassSelect
	{
	int count;
	String dno="";
	String year="";
	String quer="";
	int rollno[] = new int[200];
	int cat=0;
	String name[]= new String[200];
	String pname[] = new String[200];
	String examyear="";
		public int[] getRollno()
			{
			for(int i=0;i<200;i++)
				{
				rollno[i]=0;
				name[i]="";
				}
				count=0;
				try
					{
					Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
					Connection con = DriverManager.getConnection("jdbc:odbc:xavier","sxcce","tamil5india6");
					Statement stmt = con.createStatement();
if(cat==0) 			quer="select rollno,name,initial from student where active=1 and rollno in (select rollno from stud where department="+dno+" and year="+year+" "+
"and stud.academicyear='"+examyear+"') order by rollno";

if(cat==1) 			quer="select rollno,name,initial from student where active=1 and sex='Male' and rollno in (select rollno from stud where department="+dno+" and year="+year+" "+
"and stud.academicyear='"+examyear+"') order by rollno";

if(cat==2) 			quer="select rollno,name,initial from student where active=1 and sex<>'Male' and rollno in (select rollno from stud where department="+dno+" and year="+year+" "+
"and stud.academicyear='"+examyear+"') order by rollno";

ResultSet rs = stmt.executeQuery(quer);
					while(rs.next())
						{
						rollno[count]=rs.getInt(1);
						name[count]=rs.getString(2);
						pname[count]=rs.getString(3);
						count++;	
						}
					rs.close();
					stmt.close();
					con.close();
					}catch(Exception e){}
				return rollno;
			}
			public String[] getName()
			{
				return name;
			}
			public String[] getpname()
			{
				return pname;
			}
			public int getCount()
			{
				return count;
			}
			public void setCat(int cat)
			{
				this.cat=cat;	
			}
			public void setDno(String dno)
			{
				this.dno=dno;	
			}
			public void setYear(String year)
			{
				this.year=year;	
			}
			public String getDno()
			{
				return this.dno;	
			}
			public String getYear()
			{
				return this.year;
			}
			public void setExamyear(String examyear)
			{
				this.examyear=examyear;
			}
	}