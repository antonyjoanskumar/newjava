package sxcce;
import java.sql.*;
public class StaffView
	{
	String semname[] = new String[10];
	String semplace[] = new String[10];
	Date semdate[]=new Date[10];
	String semnat[] = new String[10];
	int semcount=0;
	String paptitle[] = new String[10];
	Date papdate[] = new Date[10];
	String papnat[] = new String[10];
	int papcount=0;
	String jortitle[] = new String[10];
	String jorname[]= new String[10];
	Date jordate[] = new Date[10];
	String jornat[] = new String[10];
	int jorcount=0;
	String bokname[] = new String[10];
	String bokpage[] = new String[10];
	String bokcopies[] = new String[10];
	String bokeditions[] = new String[10];
	Date bokdate[] = new Date[10];
	int bokcount=0;		
	String staffid="";
	Date appdate;
	Date idate;
	Date iidate;
	Date iiidate;
	Date ivdate;
	Date cdate;
	Date birthdate;
			String active="";
		String discontinueday="";
		String discontinuemonth="";
		String discontinueyear="";
		String remarks="";

		String field[] = new String[100];
		public int Read(String staffid)
			{
				this.staffid=staffid;
				try
					{
					Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
					Connection con = DriverManager.getConnection("jdbc:odbc:xavier","sxcce","tamil5india6");
					Statement stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery("select * from staff where staffid='"+staffid+"'");
					if(rs.next())
					  for(int i=1;i<=77;i++)
					    	if(i==4)
					    	  birthdate=rs.getDate(i);
					    	else if(i==63)
					    	  appdate=rs.getDate(i);
					    	else if(i==73)
					    	  idate=rs.getDate(i);
					    	else if(i==74)
					    	  iidate=rs.getDate(i);
					    	else if(i==75)
					    	  iiidate=rs.getDate(i);
					    	else if(i==76)
					    	  ivdate=rs.getDate(i);
					    	else if(i==77)
					    	  cdate=rs.getDate(i);
					    	else   
								field[i]=rs.getString(i);
					rs.close();
					rs = stmt.executeQuery("select active, day(discontinuedate),month(discontinuedate),year(discontinuedate),remarks from staff where staffid='"+staffid+"'");
						if(rs.next())
						{
					active=rs.getString(1);
					discontinueday=rs.getString(2);
					discontinuemonth=rs.getString(3);
					discontinueyear=rs.getString(4);
					remarks=rs.getString(5);
				}
					rs.close();
					stmt.close();
					con.close();
					}catch(Exception e){return(0);}
			return(1);
			}
		public void Seminar()
		{
				try
					{
					Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
					Connection con = DriverManager.getConnection("jdbc:odbc:xavier","sxcce","tamil5india6");
					Statement stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery("select * from staffseminar where staffid='"+staffid+"'");
					while(rs.next())
					   {
						semname[semcount]=rs.getString(2);
						semplace[semcount]=rs.getString(3);
						semdate[semcount]=rs.getDate(4);
						semnat[semcount]=rs.getString(5);
						semcount++;
					   }
					rs.close();
					stmt.close();
					con.close();
					}catch(Exception e){}
		}	
		public void Paper()
		{
				try
					{
					Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
					Connection con = DriverManager.getConnection("jdbc:odbc:xavier","sxcce","tamil5india6");
					Statement stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery("select * from staffpaper where staffid='"+staffid+"'");
					while(rs.next())
					   {
						paptitle[papcount]=rs.getString(2);
						papdate[papcount]=rs.getDate(3);
						papnat[papcount]=rs.getString(4);
						papcount++;
					   }
					rs.close();
					stmt.close();
					con.close();
					}catch(Exception e){}
		}	
		public void Journal()
		{
				try
					{
					Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
					Connection con = DriverManager.getConnection("jdbc:odbc:xavier","sxcce","tamil5india6");
					Statement stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery("select * from staffjournal where staffid='"+staffid+"'");
					while(rs.next())
					   {
						jortitle[jorcount]=rs.getString(2);
						jorname[jorcount]=rs.getString(3);
						jordate[jorcount]=rs.getDate(4);
						jornat[jorcount]=rs.getString(5);
						jorcount++;
					   }
					rs.close();
					stmt.close();
					con.close();
					}catch(Exception e){}
		}	
		public void Book()
		{
				try
					{
					Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
					Connection con = DriverManager.getConnection("jdbc:odbc:xavier","sxcce","tamil5india6");
					Statement stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery("select * from staffbook where staffid='"+staffid+"'");
					while(rs.next())
					   {
						bokname[bokcount]=rs.getString(2);
						bokpage[bokcount]=rs.getString(3);
						bokcopies[bokcount]=rs.getString(4);
						bokeditions[bokcount]=rs.getString(5);
						bokdate[bokcount]=rs.getDate(6);
						bokcount++;
					   }
					rs.close();
					stmt.close();
					con.close();
					}catch(Exception e){}
		}	
		public String[] getField()
		{
			return field;
		}
		public String[] getSemname()
		{
			return semname;
		}
		public String[] getSemplace()
		{
			return semplace;
		}
		public Date[] getSemdate()
		{
			return semdate;
		}
		public String[] getSemnat()
		{
			return semnat;
		}
		public String[] getPaptitle()
		{
			return paptitle;
		}
		public Date[] getPapdate()
		{
			return papdate;
		}
		public String[] getPapnat()
		{
			return papnat;
		}
		public String[] getJortitle()
		{
			return jortitle;
		}
		public String[] getJorname()
		{
			return jorname;
		}
		public Date[] getJordate()
		{
			return jordate;
		}
		public String[] getJornat()
		{
			return jornat;
		}
		public String[] getBokname()
		{
			return bokname;
		}
		public String[] getBokpage()
		{
			return bokpage;
		}
		public String[] getBokcopies()
		{
			return bokcopies;
		}
		public String[] getBokeditions()
		{
			return bokeditions;
		}
		public Date[] getBokdate()
		{
			return bokdate;
		}
		public int getSemcount()
		{
			return semcount;
		}
		public int getJorcount()
		{
			return jorcount;
		}
		public int getBokcount()
		{
			return bokcount;
		}
		public int getPapcount()
		{
			return papcount;
		}
		public Date getAppdate()	{			return appdate;		}
		public Date getidate()	{			return idate;		}
		public Date getiidate()	{			return iidate;		}
		public Date getiiidate()	{			return iiidate;		}
		public Date getivdate()	{			return ivdate;		}
		public Date getcdate()	{			return cdate;		}
		public Date getBirthdate()
		{
			return birthdate;
		}
	 public void setactive(String active) 
{ 
this.active = active; 
} 
public String getactive() 
{ 
return this.active; 
} 
public void setdiscontinuemonth(String discontinuemonth) 
{ 
this.discontinuemonth = discontinuemonth; 
} 
public String getdiscontinuemonth() 
{ 
return this.discontinuemonth; 
} 

public void setdiscontinueday(String discontinueday) 
{ 
this.discontinueday = discontinueday; 
} 
public String getdiscontinueday() 
{ 
return this.discontinueday; 
} 


public void setdiscontinueyear(String discontinueyear) 
{ 
this.discontinueyear = discontinueyear; 
} 
public String getdiscontinueyear() 
{ 
return this.discontinueyear; 
} 

public void setremarks(String remarks) 
{ 
this.remarks = remarks; 
} 
public String getremarks() 
{ 
return this.remarks; 
} 


	
	}