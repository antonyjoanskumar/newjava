
package sxcce;
import java.sql.*;
import com.jspsmart.upload.*;
import java.util.Date;
import java.io.*;
import dd.DBConnectionManager;

public class printout
	{
	int rollno=0;
	String name="";
	String department="";
	String date="";
	String date1="";
	String place="";
	String papersize="";
	String noofpages="";
	String fname="";
	String dispdate="";
	
	String report[][] = new String[10000][20];
	String porr[] = new String[10000];
	String pages[] = new String[10000];
	int count=0;
	
	public void select()
	{
	String quer="";
	try
		{
		DBConnectionManager connMgr;
 		connMgr = DBConnectionManager.getInstance();
		Connection con = connMgr.getConnection("xavier");
		Statement stmt = con.createStatement();
		ResultSet rs;
		quer="select name,department.dname from student,department "+
		"where rollno="+rollno+" and department.dno=departmentno";
		rs = stmt.executeQuery(quer);
		if(rs.next())
	  		{
	  			name=rs.getString(1);
	  			department=rs.getString(2);
	  		}
		rs.close();
		stmt.close();
		connMgr.freeConnection("xavier",con);
		}catch(Exception e){}
	}

public String staffInsert(String place)
	{registered(place);
		try
				{
		DBConnectionManager connMgr;
 		connMgr = DBConnectionManager.getInstance();
		Connection con = connMgr.getConnection("xavier");
				PreparedStatement ps;
				for(int i=0;i<count;i++)
				{
				ps = con.prepareStatement("update printouts set printedorregistered=?,noofpages=? where rollno=? and date=? and fname=? and place=?");
				ps.setString(1,porr[i]);
				ps.setString(2,pages[i]);
				ps.setString(3,report[i][0]);
				ps.setString(4,date);
				ps.setString(5,report[i][1]);
				ps.setString(6,place);
				ps.executeUpdate();
				ps.close();
			}
			connMgr.freeConnection("xavier",con);
				}catch(Exception e){return e.toString();}
			return "success";
	}
	


	public void registered(String place)
	{
	String quer="";
	count=0;
	try
		{
		DBConnectionManager connMgr;
 		connMgr = DBConnectionManager.getInstance();
		Connection con = connMgr.getConnection("xavier");
		Statement stmt = con.createStatement();
		ResultSet rs;
		quer="select rollno,fname,noofpages,printedorregistered from printouts where date='"+date+"' and place='"+place+"' order by rollno";
		rs = stmt.executeQuery(quer);
		while(rs.next())
	  		{
	  			report[count][0]=rs.getString(1);
	  			report[count][1]=rs.getString(2);
	  			report[count][2]=rs.getString(3);
	  			report[count][3]=rs.getString(4);
	  			count++;
	  		}
		rs.close();
		stmt.close();
		connMgr.freeConnection("xavier",con);
		}catch(Exception e){}
	}

	public String registered1(String place)
	{
	String quer="";
	count=0;
	try
		{
		DBConnectionManager connMgr;
 		connMgr = DBConnectionManager.getInstance();
		Connection con = connMgr.getConnection("xavier");
		Statement stmt = con.createStatement();
		ResultSet rs;
		quer="select rollno,fname,noofpages,ltrim(rtrim(ltrim(rtrim(str(day(date)))) "+
"+'/'+ltrim(rtrim(str(month(date)))) "+
"+'/'+ltrim(rtrim(str(year(date)))))),ltrim(rtrim(ltrim(rtrim(str(month(date)))) "+
"+'/'+ltrim(rtrim(str(day(date)))) "+
"+'/'+ltrim(rtrim(str(year(date)))))) from printouts where date>='"+date+"' and date<='"+date1+"' and place='"+place+"'";
		rs = stmt.executeQuery(quer);
		while(rs.next())
	  		{
	  			report[count][0]=rs.getString(1);
	  			report[count][1]=rs.getString(2);
	  			report[count][2]=rs.getString(3);
	  			report[count][3]=rs.getString(4);
	  			report[count][4]=rs.getString(5);
	  			count++;
	  		}
		rs.close();
		stmt.close();
		connMgr.freeConnection("xavier",con);
		}catch(Exception e){return quer+e.toString();}
		return "suc";
	}


	public String registered2()
	{
	String quer="";
	count=0;
	try
		{
		DBConnectionManager connMgr;
 		connMgr = DBConnectionManager.getInstance();
		Connection con = connMgr.getConnection("xavier");
		Statement stmt = con.createStatement();
		ResultSet rs;
		quer="select rollno,fname,noofpages,ltrim(rtrim(ltrim(rtrim(str(day(date)))) "+
"+'/'+ltrim(rtrim(str(month(date)))) "+
"+'/'+ltrim(rtrim(str(year(date)))))),printedorregistered,place, date from printouts where rollno="+rollno+" order by date";
		rs = stmt.executeQuery(quer);
		while(rs.next())
	  		{
	  			report[count][0]=rs.getString(1);
	  			report[count][1]=rs.getString(2);
	  			report[count][2]=rs.getString(3);
	  			report[count][3]=rs.getString(4);
	  			report[count][4]=rs.getString(5);
	  			count++;
	  		}
		rs.close();
		stmt.close();
		connMgr.freeConnection("xavier",con);
		}catch(Exception e){return quer+e.toString();}
		return "suc";
	}

	public String registered3(String place)
	{
	String quer="";
	count=0;
	try
		{
		DBConnectionManager connMgr;
 		connMgr = DBConnectionManager.getInstance();
		Connection con = connMgr.getConnection("xavier");
		Statement stmt = con.createStatement();
		ResultSet rs;
		quer="select rollno,fname,noofpages,ltrim(rtrim(ltrim(rtrim(str(day(date)))) "+
"+'/'+ltrim(rtrim(str(month(date)))) "+
"+'/'+ltrim(rtrim(str(year(date)))))),ltrim(rtrim(ltrim(rtrim(str(month(date)))) "+
"+'/'+ltrim(rtrim(str(day(date)))) "+
"+'/'+ltrim(rtrim(str(year(date)))))) from printouts where date>='"+date+"' and date<='"+date1+"' and place='"+place+"' and printedorregistered=1";
		rs = stmt.executeQuery(quer);
		while(rs.next())
	  		{
	  			report[count][0]=rs.getString(1);
	  			report[count][1]=rs.getString(2);
	  			report[count][2]=rs.getString(3);
	  			report[count][3]=rs.getString(4);
	  			report[count][4]=rs.getString(5);
	  			count++;
	  		}
		rs.close();
		for(int j=0;j<count;j++)
		{
			
		}
		stmt.close();
		connMgr.freeConnection("xavier",con);
		}catch(Exception e){return quer+e.toString();}
		return "suc";
	}


public String Insert()
	{
		try
				{
		DBConnectionManager connMgr;
 		connMgr = DBConnectionManager.getInstance();
		Connection con = connMgr.getConnection("xavier");
				PreparedStatement ps;
				ps = con.prepareStatement("insert into printouts values(?,?,?,?,?,?,?)");
				ps.setInt(1,rollno);
				ps.setString(2,date);
				ps.setString(3,papersize);
				ps.setString(4,noofpages);
				ps.setString(5,fname);
				ps.setString(6,"0");
				ps.setString(7,place);
				ps.executeUpdate();
				ps.close();
			connMgr.freeConnection("xavier",con);
				}catch(Exception e){return e.toString();}
			return "success";
	}
	
public void setname(String name) 
{ 
this.name = name; 
} 
public String getname() 
{ 
return this.name; 
} 
public void setdepartment(String department) 
{ 
this.department = department; 
} 
public String getdepartment() 
{ 
return this.department; 
} 
public void setdate(String date) 
{ 
this.date = date; 
} 
public String getdate() 
{ 
return this.date; 
} 
public void setdate1(String date1) 
{ 
this.date1 = date1; 
} 
public String getdate1() 
{ 
return this.date1; 
} 
public void setdispdate(String dispdate) 
{ 
this.dispdate = dispdate; 
} 
public String getdispdate() 
{ 
return this.dispdate; 
} 
public void setfname(String fname) 
{ 
this.fname = fname; 
} 
public String getfname() 
{ 
return this.fname; 
} 


public void setpapersize(String papersize) 
{ 
this.papersize = papersize; 

} 
public String getpapersize() 
{ 
return this.papersize; 
} 

public void setplace(String place) 
{ 
this.place = place; 

} 
public String getplace() 
{ 
return this.place; 
} 

public void setnoofpages(String noofpages) 
{ 
this.noofpages = noofpages; 

} 
public String getnoofpages() 
{ 
return this.noofpages; 
} 

public void setreport(String[][] report) 
{ 
this.report = report; 

} 
public String[][] getreport() 
{ 
return this.report; 
} 

public void setporr(String[] porr) 
{ 
this.porr = porr; 
} 

public String[] getporr() 
{ 
return this.porr; 
} 

public void setpages(String[] pages) 
{ 
this.pages = pages; 

} 
public String[] getpages() 
{ 
return this.pages; 
} 


public void setrollno(int rollno) 
{ 
this.rollno = rollno; 
} 
public int getrollno() 
{ 
return this.rollno; 
} 

public void setcount(int count) 
{ 
this.count = count; 
} 
public int getcount() 
{ 
return this.count; 
} 

	
	}