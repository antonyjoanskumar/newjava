package sxcce;
import java.sql.*;
import java.util.Date;
public class StaffAddress
	{
		String staffid[]=new String[5000];
		String name[]=new String[5000];
		String address1[]= new String[5000];
		String address2[]=new String[5000];
		String address3[]=new String[5000];
		String address4[]=new String[5000];
		String dept[]=new String[5000];
		String cell[]=new String[5000];
		String text=" active=1";
String dno=""; 
String teachornonteach=""; 
String quer="";
		int count=0;
		public void Viewaddress()
			{
				if(dno.length()>2 && teachornonteach.length()>2)
				    text=" active=1 and "+dno+" and "+teachornonteach;
				else if(dno.length()>2)
				     text=" active=1 and "+dno;
				else if(teachornonteach.length()>2)
				     text=" active=1 and "+teachornonteach;         
quer="select staffid,name,address1,rtrim(address2)+' - '+address3,district,rtrim(state)+'-'+pincode,cell,sf from staff,department where staff.dno=department.dno and"+text;
				try
					{
					Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
					Connection con = DriverManager.getConnection("jdbc:odbc:xavier","sxcce","tamil5india6");
					Statement stmt=con.createStatement();
					ResultSet ps = stmt.executeQuery(quer);
					while(ps.next())
					{
						staffid[count]=ps.getString(1);
						name[count]=ps.getString(2);
						address1[count]=ps.getString(3);
						address2[count]=ps.getString(4);
						address3[count]=ps.getString(5);
						address4[count]=ps.getString(6);
						cell[count]=ps.getString(7);
						dept[count]=ps.getString(8);
						count++;
						staffid[count]="";
						name[count]="";
						address1[count]="";
						address2[count]="";
						address3[count]="";
						address4[count]="";
						cell[count]="";
					}
					ps.close();
					stmt.close();
					con.close();
					}catch(Exception e){}
			}
	public int getCount()
	{
		return this.count;
	}
	public String[] getName()
	{
		return this.name;
	}
	public String[] getStaffid()
	{
		return this.staffid;
	}
public void setaddress1(String[] address1) 
{ 
this.address1 = address1; 
} 
public String[] getaddress1() 
{ 
return this.address1; 
} 
public void setaddress2(String[] address2) 
{ 
this.address2 = address2; 
} 
public String[] getaddress2() 
{ 
return this.address2; 
} 
public void setaddress3(String[] address3) 
{ 
this.address3 = address3; 
} 
public String[] getaddress3() 
{ 
return this.address3; 
} 
public void setaddress4(String[] address4) 
{ 
this.address4 = address4; 
} 
public String[] getaddress4() 
{ 
return this.address4; 
}
public void dept(String[] dept) 
{ 
this.dept = dept; 
} 
public String[] getdept() 
{ 
return this.dept; 
}

public void setcell(String[] cell) 
{ 
this.cell = cell; 
} 
public String[] getcell() 
{ 
return this.cell; 
}
 public void setdno(String dno) 
{ 
this.dno = dno; 
} 
public String getdno() 
{ 
return this.dno; 
} 
public void setteachornonteach(String teachornonteach) 
{ 
this.teachornonteach = teachornonteach; 
} 
public String getteachornonteach() 
{ 
return this.teachornonteach; 
} 
public String getquer()
{
	return this.quer;
}
}