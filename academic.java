package sxcce;
import java.sql.*;
import dd.DBConnectionManager;
public class academic
	{
String academicyear="";
		public String getacademicyar()
			{
				try
					{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
					Statement stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery("select academicyear from academicyear");
					if(rs.next())
						academicyear=rs.getString(1);
					rs.close();
					stmt.close();
connMgr.freeConnection("xavier",con);

					}catch(Exception e){}
				return academicyear;
			}

public String getoddeven()
{
int oe=1;
try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
					Statement stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery("select semester from academicyear");
					if(rs.next())
						oe=rs.getInt(1);
					rs.close();
					stmt.close();
connMgr.freeConnection("xavier",con);

					}catch(Exception e){}
if(oe==1)					
				return "ODD";
else
				return "EVEN";
				
			}

public String getsemester()
{
int oe=1;
try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
					Statement stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery("select semester from academicyear");
					if(rs.next())
						oe=rs.getInt(1);
					rs.close();
					stmt.close();
connMgr.freeConnection("xavier",con);

					}catch(Exception e){}
				return oe+"";
				
			}
}
