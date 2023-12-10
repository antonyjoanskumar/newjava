package sxcce;
import java.sql.*;
import dd.DBConnectionManager;
public class PassSelect
	{
		String rights="";
		String dname="";
		int found=0;
		int found1=0;
		String name="";
		public void Insert(String username,String password,String rights)
		   {
				try
					{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
					PreparedStatement ps = con.prepareStatement("insert into pass values(?,?,?)");
					ps.setString(1,username);
					ps.setString(2,password);
					ps.setString(3,rights);
					int i = ps.executeUpdate();
					ps.close();
connMgr.freeConnection("xavier",con);
					}catch(Exception e){}
					
		   }

		public void Insert1(String username,String password,String rights)
		{
				try
					{
					Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
					Connection con = DriverManager.getConnection("jdbc:odbc:server2003","login","logon");
					PreparedStatement ps = con.prepareStatement("insert into pass values(?,?,?)");
					ps.setString(1,username);
					ps.setString(2,password);
					ps.setString(3,rights);
					int i = ps.executeUpdate();
					ps.close();
					con.close();
					}catch(Exception e){}
		}	

		public void loginlog(String ip,String username,String password,String status)
		{
				try
					{
					Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
					Connection con = DriverManager.getConnection("jdbc:odbc:server2003","login","logon");
					PreparedStatement ps = con.prepareStatement("insert into loginlog(ip,username,pass,status) values(?,?,?,?)");
					ps.setString(1,ip);
					ps.setString(2,username);
					ps.setString(3,password);
					ps.setString(4,status);
					int i = ps.executeUpdate();
					ps.close();
					con.close();
					}catch(Exception e){}
		}	

		public String getRights(String username,String password)
			{

DBConnectionManager connMgr;
connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
				try
					{
					Statement stmt = con.createStatement();
					ResultSet rs;
					rs = stmt.executeQuery("select rights from pass where username='"+username+"'");
					if(rs.next())
					{
					found=1;
					}
					rs.close();
					rs = stmt.executeQuery("select rights from pass where username='"+username+"' and password='"+password+"'");
					if(rs.next())
					{
					this.rights=rs.getString(1);
					found1=1;
					}
					rs.close();
					if(found==0)
					{

  						rs = stmt.executeQuery("select name from staff where staffid='"+username+"'");
  						if(rs.next() && (password.equals("x") || password.equals("X")))
  						{
  							Insert(username,password,"staff");
							Insert1(username,password,"staff");
  							this.rights="staff";
  							this.name=rs.getString(1);
  						}
  						rs.close();
						try{
  						rs = stmt.executeQuery("select name from stud where rollno="+username+" and academicyear in(select academicyear from academicyear)");
  						if(rs.next() && (password.equals("z") || password.equals("Z")))
  						{
  							Insert(username,password,"student");
  							Insert1(username,password,"student");
  							this.rights="student";
  							this.name=rs.getString(1);
  						}
  						rs.close();
  					}catch(Exception e){}  						
  						rs = stmt.executeQuery("select dname from department where dno="+username);
  						if(rs.next() && (password.equals("x") || password.equals("X")))
  						{
  							Insert(username,password,"hod");
  							Insert1(username,password,"hod");
  							this.rights="hod";
  							this.dname=rs.getString(1);
  						}
  						rs.close();
  					}	
  						rs = stmt.executeQuery("select name from staff where staffid='"+username+"'");
  						if(rs.next())
  						{
  							this.name=rs.getString(1);
  						}
  						rs.close();
  						rs = stmt.executeQuery("select dname from department where dno="+username);
  						if(rs.next())
  						{
  							this.dname=rs.getString(1);
  						}
  						rs.close();
  						rs = stmt.executeQuery("select name from student where rollno="+username+"");
  						if(rs.next())
  						{
  							this.name=rs.getString(1);
  						}
  						rs.close();
					stmt.close();
connMgr.freeConnection("xavier",con);
					}catch(Exception e){
connMgr.freeConnection("xavier",con);
						}
//				if(this.rights.equals(""))	return "staff";
//				else 
				return this.rights;
			}

		public String getParRights(String username)
			{
				try
					{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
					Statement stmt = con.createStatement();
					ResultSet rs;
  						rs = stmt.executeQuery("select name from student where rollno="+username+"");
  						if(rs.next())
  						{
  							this.name=rs.getString(1);
  							this.rights="parent";
  						}
 						rs.close();
					stmt.close();
connMgr.freeConnection("xavier",con);
					}catch(Exception e){}
		return this.rights;
}


    public String getname()
       {
       	return this.name;
       }			
    public String getdname()
    {
    	return this.dname;
    }  
	}