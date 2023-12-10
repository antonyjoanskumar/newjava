package sxcce;
import java.sql.*;
public class StudClassSelect2
	{
	int count;
	String dno="";
	String year="";
	int rollno[] = new int[200];
	String name[]= new String[200];
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
					ResultSet rs = stmt.executeQuery("select s.rollno,s.name from stud,student s where stud.rollno=s.rollno and department="+dno+" and active=1 and year="+year+" and stud.academicyear='"+examyear+"' order by s.name");
					while(rs.next())
						{
						rollno[count]=rs.getInt(1);
						name[count]=rs.getString(2);
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
			public int getCount()
			{
				return count;
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