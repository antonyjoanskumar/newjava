
package sxcce;
import java.sql.*;
import java.util.StringTokenizer;

public class SalarySaveas
	{
String academicyear=""; 
String academicyear1=""; 
String month=""; 
String month1=""; 
String d1="";
String m1="";
String y1="";
String fdate="";
String fdate1="";

		public void Delete()
		{
				try
					{
					Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
					Connection con = DriverManager.getConnection("jdbc:odbc:xavier","sxcce","tamil5india6");
					PreparedStatement ps;
					ps = con.prepareStatement("delete from salary where month=? and academicyear=?");
					ps.setString(1,month1);
					ps.setString(2,academicyear1);
					int j=ps.executeUpdate();
					ps.close();
					con.close();
					}catch(Exception e){}
		}

		public void Saveas()
			{
			StringTokenizer st = new StringTokenizer(fdate,"/");
		    d1=st.nextToken();
		    m1=st.nextToken();
		    y1=st.nextToken();
			fdate1=m1+"/"+d1+"/"+y1;

		String quer="insert into salary select staffid,consoldate,dayslossofpay,accountno,bank,"+
		"basic,ma,others,pp,rd,lic,loan,pfper,fa,society,club,spclub,saladvance,telephone,"+
		"cooptex,khadi,protax,incometax,processfee,others1,scale,?,?,?,scsa,splallow from salary "+
		"where month=? and academicyear=?";
				try
					{
					Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
					Connection con = DriverManager.getConnection("jdbc:odbc:xavier","sxcce","tamil5india6");
					PreparedStatement ps;
					ps = con.prepareStatement(quer);
					ps.setString(1,month1);
					ps.setString(2,academicyear1);
					ps.setString(3,fdate1);
					ps.setString(4,month);
					ps.setString(5,academicyear);
					int j=ps.executeUpdate();
					ps.close();
					con.close();
					}catch(Exception e){}
			}
public void Enable(int en)
		{
				try
					{
					Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
					Connection con = DriverManager.getConnection("jdbc:odbc:xavier","sxcce","tamil5india6");
					PreparedStatement ps;
					ps = con.prepareStatement("delete from salenable where month=? and academicyear=?");
					ps.setString(1,month1);
					ps.setString(2,academicyear1);
					int j=ps.executeUpdate();
					ps.close();
					if(en==1)
					ps = con.prepareStatement("insert into salenable values(?,?,?)");
					ps.setString(1,month1);
					ps.setString(2,academicyear1);
					ps.setInt(3,en);
					j=ps.executeUpdate();
					ps.close();

					con.close();
					}catch(Exception e){}
		}

 public void setacademicyear(String academicyear) 
{ 
this.academicyear = academicyear; 
} 
public String getacademicyear() 
{ 
return this.academicyear; 
} 
public void setacademicyear1(String academicyear1) 
{ 
this.academicyear1 = academicyear1; 
} 
public String getacademicyear1() 
{ 
return this.academicyear1; 
} 
public void setmonth(String month) 
{ 
this.month = month; 
} 
public String getmonth() 
{ 
return this.month; 
} 
public void setmonth1(String month1) { this.month1 = month1; } 
public String getmonth1() { return this.month1; } 

public void setfdate(String fdate) { this.fdate = fdate; } 
public String getfdate() { return this.fdate; } 

	}