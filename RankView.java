package sxcce;
import java.sql.*;

public class RankView
	{
		String subcode[] = new String[25];
		String subname[] = new String[25];
		int TP[]=new int[25];
		int subcount=0;
		String sql="";
		public void Viewsubcode(String exam,int dno,String examyear,int semester)
		    {
		    	try
		    	{
					Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
					Connection con = DriverManager.getConnection("jdbc:odbc:xavier","sxcce","tamil5india6");
					Statement stmt = con.createStatement();
					ResultSet rs;
					rs = stmt.executeQuery("SELECT DISTINCT mark.subjectcode,syllabus.subjectname FROM mark, examyear,syllabus,subjectidentify WHERE examyear.examno=mark.examno and syllabus.subjectcode=mark.subjectcode and syllabus.id=subjectidentify.id and subjectidentify.dno=examyear.dno and subjectidentify.dno="+dno+" and examyear.semester="+semester+" and subjectidentify.academicyear=examyear.year and examyear.year='"+examyear+"' and examyear.exam='"+exam+"' ORDER BY mark.subjectcode");
					while(rs.next())
						{
						subcode[subcount]=rs.getString(1);
						subname[subcount]=rs.getString(2);
						subcount++;	
						}
					rs.close();
					stmt.close();
					con.close();
		    	}catch(Exception e){}
		    }

		public void ViewAllSubcode(String exam,int dno,String examyear,int semester,String tp)
		    {
		    	try
		    	{
					Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
					Connection con = DriverManager.getConnection("jdbc:odbc:xavier","sxcce","tamil5india6");
					Statement stmt = con.createStatement();
					ResultSet rs;
					rs = stmt.executeQuery("SELECT DISTINCT subjectcode,subjectname FROM syllabus,subjectidentify WHERE syllabus.id=subjectidentify.id and dno="+dno+" and semester="+semester+" and theoryorpractical in ("+tp+") and academicyear='"+examyear+"' ORDER BY subjectcode ");
					while(rs.next())
						{
						subcode[subcount]=rs.getString(1);
						subname[subcount]=rs.getString(2);
						subcount++;	
						}
					rs.close();
					stmt.close();
					con.close();
		    	}catch(Exception e){}
		    }

		public void ViewSubcode1(String exam,int dno,String examyear,int semester,String tp)
		    {
		    	sql="SELECT subjectcode,subjectname,theoryorpractical FROM syllabus,subjectidentify WHERE syllabus.id=subjectidentify.id and dno="+dno+" and semester="+semester+" "
+" and theoryorpractical in ("+tp+") and academicyear='"+examyear+"' ORDER BY CASE WHEN theoryorpractical in(1,4) THEN 1 "
+" WHEN theoryorpractical in(5) THEN 2 WHEN theoryorpractical in(0) THEN 3 ELSE 4 END,subjectcode";
		    	try
		    	{
					Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
					Connection con = DriverManager.getConnection("jdbc:odbc:xavier","sxcce","tamil5india6");
					Statement stmt = con.createStatement();
					ResultSet rs;
					rs = stmt.executeQuery(sql);
					while(rs.next())
						{
						subcode[subcount]=rs.getString(1);
						subname[subcount]=rs.getString(2);
						TP[subcount]=rs.getInt(3);
						subcount++;	
						}
					rs.close();
					stmt.close();
					con.close();
		    	}catch(Exception e){}
		    }
	public String[] getSubcode()
	{
		return this.subcode;
	}
	public String[] getsubname()
	{
		return this.subname;
	}
	public int[] getTP()
	{
		return this.TP;
	}
	public int getSubcount()
	{
		return this.subcount;
	}
	}
	