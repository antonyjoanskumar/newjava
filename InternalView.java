package sxcce;
import java.sql.*;

public class InternalView
	{
		int mark1[] = new int[200];
		int mark[] = new int[200];
		String rollno1[] = new String[200];
		String rollno[] = new String[200];
		String regno[] = new String[200];
		String name[]=new String[200];
		int attper[] = new int[200];
		int count;
		int count1;
		public void ViewMark(String subjectcode,String exam,int dno,String examyear,int semester)
		    {
		    	try
		    	{
					Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
					Connection con = DriverManager.getConnection("jdbc:odbc:xavier","sxcce","tamil5india6");
					Statement stmt = con.createStatement();
					ResultSet rs;
					count1=0;
					rs = stmt.executeQuery("SELECT stud.rollno, stud.name, mark.mark FROM stud,mark,examyear WHERE stud.rollno=mark.rollno And examyear.examno=mark.examno And stud.department="+dno+" and examyear.semester="+semester+" and examyear.exam='"+exam+"' and examyear.year=stud.academicyear and examyear.year='"+examyear+"' And mark.subjectcode='"+subjectcode+"'ORDER BY stud.rollno");
					while(rs.next())
						{
						rollno1[count1]=rs.getString(1);
						name[count1]=rs.getString(2);
						mark1[count1]=rs.getInt(3);
						count1++;	
						}
					rs.close();
					count=0;
					rs = stmt.executeQuery("SELECT stud.rollno,registerno, stud.name ,percentage FROM stud,registerno,student,attper WHERE  attper.rollno=stud.rollno and attper.semester = "+semester+" and stud.rollno=registerno.rollno and stud.rollno=student.rollno And student.active=1 and stud.department="+dno+" and stud.year="+(semester+1)/2+" and stud.academicyear='"+examyear+"' ORDER BY stud.rollno");
					while(rs.next())
						{
						rollno[count]=rs.getString(1);
						regno[count]=rs.getString(2);
						name[count]=rs.getString(3);
						attper[count]=rs.getInt(4);
						mark[count]=-1;
						
						for (int j=0; j<count1;j++)
						{
							if(rollno[count].equals(rollno1[j]))
							{		mark[count]=mark1[j];
							        j=count1;
							       }

					    }
						count++;	
						}
					rs.close();


					stmt.close();
					con.close();
		    	}catch(Exception e){}
		    }
	public String[] getRollno()
	{
		return this.rollno;
	}
	public String[] getRegno()
	{
		return this.regno;
	}
	public String[] getName()
	{
		return this.name;
	}
	public int[] getMark()
	{
		return this.mark;
	}
	public int[] getAttper()
	{
		return this.attper;
	}
	public int getCount()
	{
		return this.count;
	}
	}

