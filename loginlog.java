package sxcce;
import java.sql.*;
import java.util.Date;
import dd.DBConnectionManager;

public class loginlog
	{
		String report[][] = new String[15][5000];
		String report1[][] = new String[15][5000];
		String dno=""; 
		String quer="";
		String order="";
		int count=0;
		int count1=0;
		Date today = new Date();
		String text="";
		String sql="";
	public String logdetails(String username)
	{

quer="select ip,convert(varchar(11), ldate, 106)+' '+convert(varchar(5), ldate, 108) from loginlog "+
"where username='"+username+"' and ldate>=getdate()-100 and ip!='192.168.0.250' order by ldate desc";

				try
					{
					Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
					Connection con = DriverManager.getConnection("jdbc:odbc:server2003","login","logon");
					Statement stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery(quer);
					while(rs.next())
					  {
					  	report[0][count]=(count+1)+"";
					  	for(int i=1;i<=2;i++)
					  	   report[i][count]=rs.getString(i);
					  	count++;   
					  }
					rs.close();
					stmt.close();
					con.close();
					

				}catch(Exception e){return e.toString()+quer;}
			return "success";

}

public String stafflogdetails(String fd,String td)
{
count=0;
count1=0;
quer="select ip,convert(varchar(11), ldate, 106)+' '+convert(varchar(5), ldate, 108),convert(varchar(11), ldate, 101),ltrim(rtrim(username)) from loginlog "
+" where ldate between '"+fd+"' and '23:59 "+td+"' and ip!='192.168.0.250' and (username like 'TS%' or username like 'ts%') order by ltrim(rtrim(username)),ldate";


				try
					{
					Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
					Connection con = DriverManager.getConnection("jdbc:odbc:server2003","login","logon");
					Statement stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery(quer);
					while(rs.next())
					  {
					  	report1[0][count1]=(count1+1)+"";
					  	for(int i=1;i<=4;i++)
					  	   report1[i][count1]=rs.getString(i);
					  	count1++;   
					  }
					rs.close();
					stmt.close();
for(int i=0;i<count1;i++)
{
//CheckPunchLog(report1[4][i],report1[3][i]);
if(CheckPunchLog(report1[4][i],report1[3][i])==1)
{
  	report[0][count]=(count+1)+"";
  	report[1][count]=report1[1][i];
  	report[2][count]=report1[2][i];
  	report[3][count]=report1[3][i];
  	report[4][count]=report1[4][i];
count++;
}
}
				con.close();

				}catch(Exception e){return e.toString()+quer;}
			return "success";
}


public int CheckPunchLog(String uname,String pdate)
{
int flag=0;
try
{
DBConnectionManager connMgr;
connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;
sql="select substring(punchrecords,1,1), attendancedate from eTimetracklite1.dbo.vw_AttendanceLogs "
+" where employeecode='"+uname+"'  and AttendanceDate='"+pdate+"' and substring(punchrecords,1,1)=' '";

rs = stmt.executeQuery(sql);
if(rs.next()) flag=1;
rs.close();

report[6][count]=sql;
if(flag==1)
{
sql="select name from sxcce.dbo.staff where staffid='"+uname+"'";
rs = stmt.executeQuery(sql);
if(rs.next()) report[5][count]=rs.getString(1);
rs.close();
}

stmt.close();
connMgr.freeConnection("xavier",con);

}catch(Exception e){report[6][count]=e.toString();}
return flag; 
}



public void setorder(String order) 
{ 
this.order = order; 
} 
public String getorder()
{
	return this.order;
}
public String getsql()
{
	return this.sql;
}
public String getquer()
{
	return this.quer;
}
public String[][] getreport()
{
	return this.report;
}
public String[][] getreport1()
{
	return this.report1;
}
public int getcount()
{
	return this.count;
}


}