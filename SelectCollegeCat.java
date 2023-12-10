package sxcce;
import java.sql.*;
public class SelectCollegeCat
	{
	int count;
	int slno[] = new int[100];
	String cname[] = new String[100];
		public String[] getCname()
			{
			for(int i=0;i<100;i++)
				{
				cname[i]="";
				slno[i]=0;
				}
				count=0;
				try
					{
					Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
					Connection con = DriverManager.getConnection("jdbc:odbc:xavier","sxcce","tamil5india6");
					Statement stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery("select * from collegecategory order by slno");
					while(rs.next())
						{
						slno[count]=rs.getInt(1);
						cname[count]=rs.getString(2);
						count++;	
						}
					rs.close();
					stmt.close();
					con.close();
					}catch(Exception e){}
				return cname;
			}
			public int[] getSlno()
			{
				return this.slno;
			}
			public int getCount()
			{
				return this.count;
			}
	}