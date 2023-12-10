package Bus;
import java.sql.*;
import dd.DBConnectionManager;
public class busbill{


int count=0;
String quer="";


public String BusfeeInsert(String date1,String rollno1,String reason1,float amount1,String login1)
{
	String sql="";
try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("accounts");
					Statement stmt = con.createStatement();
					ResultSet rs;
PreparedStatement ps;
int acchead=3001;
if(amount1>0)
{
	rs = stmt.executeQuery("select max(head)+1 from busfee where rollno="+rollno1);
	if(rs.next())					{						acchead=rs.getInt(1);					}					rs.close();
		if (acchead<3001) acchead=3001;
			sql="insert into busfee values(?,?,?,?,?,?)";
	    ps=con.prepareStatement(sql);
		ps.setString(1,date1);
		ps.setString(2,rollno1);
		ps.setInt(3,acchead);
		ps.setString(4,reason1);
		ps.setFloat(5,amount1);
		ps.setString(6,login1);
		int i = ps.executeUpdate();
		ps.close();
}

connMgr.freeConnection("accounts",con);
}catch(Exception e){return "Error !! "+ e.toString();}
return("success");
}

}

