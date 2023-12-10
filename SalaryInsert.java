package sxcce;
import java.sql.*;
public class SalaryInsert
	{
		public String InsertSal(String[] staffid,String[] hval,String head,int count,String month,String academicyear)
			{
				try
					{
					Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
					Connection con = DriverManager.getConnection("jdbc:odbc:xavier","sxcce","tamil5india6");
					PreparedStatement ps;
					for(int i=0;i<count;i++)
					{
						ps = con.prepareStatement("update salary set "+head+"=? where staffid=? and month=? and academicyear=?");
						ps.setString(1,hval[i]);
						ps.setString(2,staffid[i]);
						ps.setString(3,month);
						ps.setString(4,academicyear);
						int j=ps.executeUpdate();
					ps.close();
					}
					con.close();
					}catch(Exception e){return e.toString();}
			return "success";
			}
	}