package sxcce;
import java.sql.*;

public class CommonFine
	{
	String report[][] = new String[10000][20];
	int count=0;

		public String Insert(String today,int rollno,int slno,String reason,float amount,String login)
			{
                String sql="";
				try
					{
					Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
					Connection con = DriverManager.getConnection("jdbc:odbc:xavier","sxcce","tamil5india6");
					Statement stmt = con.createStatement();
					ResultSet rs;
					PreparedStatement ps;

		if (slno<3000)
		{ 
					
					rs = stmt.executeQuery("select max(head)+1 from accounts.dbo.dues where rollno="+rollno);
					if(rs.next())
					{
						slno=rs.getInt(1);
					}
					rs.close();
					if (slno<2006) slno=2006;
sql="insert into accounts.dbo.dues values(?,?,?,?,?,?)";
		}

		if (slno==3000)
		{ 
			rs = stmt.executeQuery("select max(head)+1 from accounts.dbo.busfee where rollno="+rollno);
			if(rs.next())
					{
						slno=rs.getInt(1);
					}
					rs.close();
					if (slno<3001) slno=3001;
					sql="insert into accounts.dbo.busfee values(?,?,?,?,?,?)";
		}	
		if (slno==4000)
		{ 
			rs = stmt.executeQuery("select max(head)+1 from accounts.dbo.examfee where rollno="+rollno);
			if(rs.next())
					{
						slno=rs.getInt(1);
					}
					rs.close();
					if (slno<4001) slno=4001;
					sql="insert into accounts.dbo.examfee values(?,?,?,?,?,?)";
		}	

		if (slno==5000)
		{ 
			rs = stmt.executeQuery("select max(head)+1 from accounts.dbo.revalfee where rollno="+rollno);
			if(rs.next())
					{
						slno=rs.getInt(1);
					}
					rs.close();
					if (slno<5001) slno=5001;
					sql="insert into accounts.dbo.revalfee values(?,?,?,?,?,?)";
		}	
					ps=con.prepareStatement(sql);
					ps.setString(1,today);
					ps.setInt(2,rollno);
					ps.setInt(3,slno);
					ps.setString(4,reason);
					ps.setFloat(5,amount);
					ps.setString(6,login);
					int i = ps.executeUpdate();
					ps.close();
					con.close();
					}catch(Exception e){return("error:"+e.toString());}
		return("success");	
			}

		public String Report(int slno,String reason)
			{
                String quer="";
                String table="";
                count=0;
                int amount=0;
				try
					{
					Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
					Connection con = DriverManager.getConnection("jdbc:odbc:xavier","sxcce","tamil5india6");
					Statement stmt = con.createStatement();
					ResultSet rs;

		if (slno==4000)  table="examfee";
		if (slno==5000) table="revalfee";
              quer="select ltrim(rtrim(ltrim(rtrim(str(day(date))))+'/'+ltrim(rtrim(str(month(date))))+'/'+ltrim(rtrim(str(year(date)))))) date1,sxcce.dbo.student.rollno,"
             +" sxcce.dbo.student.name,ltrim(rtrim(ltrim(rtrim(str(amount))))),login,head from accounts.dbo."+table+",sxcce.dbo.student where accounts.dbo."+table+".rollno=sxcce.dbo.student.rollno and reason='"+reason+"' order by date1,sxcce.dbo.student.rollno";

		rs = stmt.executeQuery(quer);
		while(rs.next())
	  		{
	  			report[count][0]=rs.getString(1);
	  			report[count][1]=rs.getString(2);
	  			report[count][2]=rs.getString(3);
	  			report[count][3]=rs.getString(4);
	  			report[count][4]=rs.getString(5);
	  			report[count][5]=rs.getString(6);
	  			report[count][6]="0";
	  			count++;
	  		}
		rs.close();
        for(int j=0;j<count;j++)
        {
        	amount=0;
        	quer="select isnull(sum(amount),0) from accounts.dbo.payment where head="+report[j][5]+" and rollno="+report[j][1];

		rs = stmt.executeQuery(quer);
        if(rs.next())					
		{	
			amount=rs.getInt(1);
			amount=Integer.parseInt(report[j][3])-amount;
			report[j][6]=Integer.toString(amount);
		}
			rs.close();
        }
		stmt.close();
					con.close();
					}catch(Exception e){return("error"+e.toString());}
		return("success");	
			}

		public String Report1(int slno,String reason, String fdate,String tdate)
			{
                String quer="";
                String table="";
                count=0;
                int amount=0;
				try
					{
					Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
					Connection con = DriverManager.getConnection("jdbc:odbc:xavier","sxcce","tamil5india6");
					Statement stmt = con.createStatement();
					ResultSet rs;

		if (slno==4000)  table="examfee";
		if (slno==5000) table="revalfee";
              quer="select ltrim(rtrim(ltrim(rtrim(str(day(date))))+'/'+ltrim(rtrim(str(month(date))))+'/'+ltrim(rtrim(str(year(date)))))) date1,sxcce.dbo.student.rollno,"
             +" sxcce.dbo.student.name,ltrim(rtrim(ltrim(rtrim(str(amount))))),login,head from accounts.dbo."+table+",sxcce.dbo.student where accounts.dbo."+table+".rollno=sxcce.dbo.student.rollno and reason='"+reason+"' and date between '"+fdate+"' and '"+tdate+"' order by date1,sxcce.dbo.student.rollno";

		rs = stmt.executeQuery(quer);
		while(rs.next())
	  		{
	  			report[count][0]=rs.getString(1);
	  			report[count][1]=rs.getString(2);
	  			report[count][2]=rs.getString(3);
	  			report[count][3]=rs.getString(4);
	  			report[count][4]=rs.getString(5);
	  			report[count][5]=rs.getString(6);
	  			report[count][6]="0";
	  			count++;
	  		}
		rs.close();
        for(int j=0;j<count;j++)
        {
        	amount=0;
        	quer="select isnull(sum(amount),0) from accounts.dbo.payment where head="+report[j][5]+" and rollno="+report[j][1];

		rs = stmt.executeQuery(quer);
        if(rs.next())					
		{	
			amount=rs.getInt(1);
			amount=Integer.parseInt(report[j][3])-amount;
			report[j][6]=Integer.toString(amount);
		}
			rs.close();
        }
		stmt.close();
					con.close();
					}catch(Exception e){return("error"+e.toString());}
		return("success");	
			}

		public String Report11(int slno,String reason, String fdate,String tdate)
			{
                String quer="";
                String table="";
                count=0;
                int amount=0;
				try
					{
					Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
					Connection con = DriverManager.getConnection("jdbc:odbc:xavier","sxcce","tamil5india6");
					Statement stmt = con.createStatement();
					ResultSet rs;

		if (slno==4000)  table="examfee";
		if (slno==5000) table="revalfee";
              quer="select ltrim(rtrim(ltrim(rtrim(str(day(date))))+'/'+ltrim(rtrim(str(month(date))))+'/'+ltrim(rtrim(str(year(date)))))) date1,sxcce.dbo.student.rollno,ltrim(rtrim(registerno)),"
             +" sxcce.dbo.student.name,ltrim(rtrim(ltrim(rtrim(str(amount))))),login,head from accounts.dbo."+table+",sxcce.dbo.student,sxcce.dbo.registerno "
             +" where sxcce.dbo.registerno.rollno=accounts.dbo."+table+".rollno and accounts.dbo."+table+".rollno=sxcce.dbo.student.rollno and reason='"+reason+"' and date between '"+fdate+"' and '"+tdate+"' order by date1,sxcce.dbo.student.rollno";

		rs = stmt.executeQuery(quer);
		while(rs.next())
	  		{
	  			report[count][0]=rs.getString(1);
	  			report[count][1]=rs.getString(2);
	  			report[count][2]=rs.getString(3);
	  			report[count][3]=rs.getString(4);
	  			report[count][4]=rs.getString(5);
	  			report[count][5]=rs.getString(6);
	  			report[count][6]=rs.getString(7);
	  			report[count][7]="0";
	  			count++;
	  		}
		rs.close();
        for(int j=0;j<count;j++)
        {
        	amount=0;
        	quer="select isnull(sum(amount),0) from accounts.dbo.payment where head="+report[j][6]+" and rollno="+report[j][1];

		rs = stmt.executeQuery(quer);
        if(rs.next())					
		{	
			amount=rs.getInt(1);
			amount=Integer.parseInt(report[j][4])-amount;
			report[j][7]=Integer.toString(amount);
		}
			rs.close();
        }
		stmt.close();
					con.close();
					}catch(Exception e){return("error"+e.toString());}
		return("success");	
			}


		public String Report2(int slno,String reason, String fdate,String tdate)
			{
                String quer="";
                String table="";
                count=0;
                int amount=0;
				try
					{
					Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
					Connection con = DriverManager.getConnection("jdbc:odbc:xavier","sxcce","tamil5india6");
					Statement stmt = con.createStatement();
					ResultSet rs;

		if (slno==4000)  table="examfee";
		if (slno==5000) table="revalfee";
              quer="select ltrim(rtrim(ltrim(rtrim(str(day(date))))+'/'+ltrim(rtrim(str(month(date))))+'/'+ltrim(rtrim(str(year(date)))))) date1,sxcce.dbo.student.rollno,"
             +" sxcce.dbo.student.name,ltrim(rtrim(ltrim(rtrim(str(amount))))),login,head from accounts.dbo."+table+",sxcce.dbo.student where accounts.dbo."+table+".rollno=sxcce.dbo.student.rollno and reason='"+reason+"' and date between '"+fdate+"' and '"+tdate+"' order by date1,sxcce.dbo.student.rollno";

		rs = stmt.executeQuery(quer);
		while(rs.next())
	  		{
	  			report[count][0]=rs.getString(1);
	  			report[count][1]=rs.getString(2);
	  			report[count][2]=rs.getString(3);
	  			report[count][3]=rs.getString(4);
	  			report[count][4]=rs.getString(5);
	  			report[count][5]=rs.getString(6);
	  			report[count][6]="0";
	  			count++;
	  		}
		rs.close();
        for(int j=0;j<count;j++)
        {
        	amount=0;
        	quer="select isnull(sum(amount),0) from accounts.dbo.payment where head="+report[j][5]+" and rollno="+report[j][1];

		rs = stmt.executeQuery(quer);
        if(rs.next())					
		{	
			amount=rs.getInt(1);
			amount=Integer.parseInt(report[j][3])-amount;
			report[j][6]=Integer.toString(amount);
		}
			rs.close();
        }
		stmt.close();
					con.close();
					}catch(Exception e){return("error"+e.toString());}
		return("success");	
			}



public String[][] getreport() 
{ 
return this.report; 
} 
public int getcount() 
{ 
return this.count; 
} 
}
	