package sxcce;
import java.sql.*;
public class SelectDept
	{
	int count;
	int dno[] = new int[100];
	String dname[] = new String[100];
	String sf[] = new String[100];
	String deptname="";
	int sections[] = new int[100];
		public String[] getDname()
			{
			for(int i=0;i<100;i++)
				{
				dname[i]="";
				dno[i]=0;
				sections[i]=0;
				sf[i]="";
				}
				count=0;
				try
					{
					Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
					Connection con = DriverManager.getConnection("jdbc:odbc:xavier","sxcce","tamil5india6");
					Statement stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery("select * from department order by dno");
//					ResultSet rs = stmt.executeQuery("select * from department where dno in(select distinct departmentno from student) order by dno");
					while(rs.next())
						{
						dno[count]=rs.getInt(1);
						dname[count]=rs.getString(2);
						sections[count]=rs.getInt(3);
						sf[count]=rs.getString(4);
						count++;	
						}
					rs.close();
					stmt.close();
					con.close();
					}catch(Exception e){}
				return dname;
			}
			
public String getDeptName(int dno1)
			{
				count=0;
                 deptname="";
				try
					{
					Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
					Connection con = DriverManager.getConnection("jdbc:odbc:xavier","sxcce","tamil5india6");
					Statement stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery("select dname from department where dno="+dno1);
					if(rs.next())
						{
						deptname=rs.getString(1);
						}
					rs.close();
					stmt.close();
					con.close();
					}catch(Exception e){}
				return deptname;
			}

		public String[] getDept()
			{
			for(int i=0;i<100;i++)
				{
				dname[i]="";
				dno[i]=0;
				sections[i]=0;
				}
				count=0;
				try
					{
					Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
					Connection con = DriverManager.getConnection("jdbc:odbc:xavier","sxcce","tamil5india6");
					Statement stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery("select * from department where dty>0 order by dty");
					while(rs.next())
						{
						dno[count]=rs.getInt(1);
						dname[count]=rs.getString(2);
						sections[count]=rs.getInt(3);
						count++;	
						}
					rs.close();
					stmt.close();
					con.close();
					}catch(Exception e){}
				return dname;
			}
			
			public int[] getDno()
			{
				return dno;
			}
			public int[] getSections()
			{
				return sections;
			}
			public int getCount()
			{
				return count;
			}
			public String[] getSf()
			{
				return sf;
			}

	}