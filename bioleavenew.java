package sxcce;
import java.sql.*;
import java.util.StringTokenizer;
import dd.DBConnectionManager;
//import java.util.*;

public class bioleavenew
	{
				String report[][] = new String[35][5000];
				String report1[][] = new String[35][5000];
				String report2[][] = new String[500][5000];

                String option;
                int leaveyearid;
                int leaveregisterid;
                String fhours;
                String fminutes;
                String ftime;
                String thours;
                String tminutes;
                String ttime;
                String fdate=" ";
                String tdate=" ";
                String status;
                String reason;
                String staffid;
                String astaffid;
                String ahours;
                String submitdate=" ";
                String hodapprovdate;
                String princapprovdate;
                String corresapprovdate;
                String leavedate=" ";
                String cancelleddate;
                String sanstatus;
                String hodstatus;
                String princstatus;
                String corresstatus;
				int year;
				int dno;
				int datecount=0;
				float days=0;
String d1="";
String m1="";
String y1="";
String submitdate1="";
String leavedate1="";
String fdate1="";
String tdate1="";
String device="";
int count;
int acount;
String sql="";	
String ex="";	
String error="";
String error1="";
float fk;
			
public String Insert(String sdate)
{
                String sql="";
                String e1="";
                error="Success";
                String pdate="";
                int p=0;
                int ecl=0;
                int acl=0;
                int cl=count;
                float adays=0;
                float ajk=0;
                float ajk1=0;
                for(int i=0;i<count;i++)
                {
					adays+=Float.parseFloat(report[11][i]);
               	
                }

try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;
					    PreparedStatement ps;
					    sql="select leavetypeid from leave_type where status='"+option+"'";
					    rs = stmt.executeQuery(sql);
					    if(rs.next())
					    {
						leaveyearid=rs.getInt(1);
					    }
					    rs.close();
					    sql="select max(leaveregisterid)+1 from leave_register";
					    rs = stmt.executeQuery(sql);
					    if(rs.next())
					    {
						leaveregisterid=rs.getInt(1);
					    }
					    rs.close();
                hodstatus="N";
                princstatus="N";
                corresstatus="N";
                status="Y";
                ftime = fhours + ":" + fminutes ;
                ttime = thours + ":" + tminutes;
                astaffid = "-";
                ahours = "-";
			StringTokenizer st = new StringTokenizer(sdate,"/");
		    d1=st.nextToken();
		    m1=st.nextToken();
		    y1=st.nextToken();
			submitdate1=m1+"/"+d1+"/"+y1;
year=Integer.parseInt(y1);

					    
            if (option.matches("P")) {
			StringTokenizer st1 = new StringTokenizer(leavedate,"/");
		    d1=st1.nextToken();
		    m1=st1.nextToken();
		    y1=st1.nextToken();
			leavedate1=m1+"/"+d1+"/"+y1;
			  d1=(Integer.parseInt(d1))+"";
  	          m1=(Integer.parseInt(m1))+"";
leavedate=d1+"/"+m1+"/"+y1;

sql="SELECT ltrim(str(day(leavedate)))+'/'+ltrim(str(month(leavedate)))+'/'+ltrim(str(year(leavedate))),leaveyearid from leave_register,leave_type where leavetypeid=leaveyearid and leave_register.status='Y' and leave_type.status='"+option+"' and staffid='"+staffid+"' and month(leavedate)="+m1+" and year(leavedate)="+y1;
					    rs = stmt.executeQuery(sql);
					    while(rs.next())
					    {
					    	p++;
						pdate=rs.getString(1);
						if(pdate.matches(leavedate)) error="Not Successful: One Permission Per Day Allowed";
						
					    }
					    rs.close();
                    if(p>=2) error="Not Successful: Permission Exceeds the Limit";
                    
if(error.matches("Success"))	
{				
                    sql="INSERT into leave_register(leaveyearid,fromtime,totime,reason,staffid,astaffid,ahours,submitdate,hodstatus,princstatus,corresstatus,leavedate,year,status,leaveregisterid) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

					ps=con.prepareStatement(sql);
					ps.setInt(1,leaveyearid);
					ps.setString(2,ftime);
					ps.setString(3,ttime);
					ps.setString(4,reason);
					ps.setString(5,staffid);
					ps.setString(6,astaffid);
					ps.setString(7,ahours);
					ps.setString(8,submitdate1);
					ps.setString(9,hodstatus);
					ps.setString(10,princstatus);
					ps.setString(11,corresstatus);
					ps.setString(12,leavedate1);
					ps.setInt(13,year);
					ps.setString(14,status);
					ps.setInt(15,leaveregisterid);
					int i = ps.executeUpdate();
					ps.close();
				}
          }
          else
          {
          	
 			StringTokenizer st2 = new StringTokenizer(fdate,"/");
		    d1=st2.nextToken();
		    m1=st2.nextToken();
		    y1=st2.nextToken();
			fdate1=m1+"/"+d1+"/"+y1;
			int mm1=Integer.parseInt(m1);
			String yy1=y1;
			 			  d1=(Integer.parseInt(d1))+"";
  	          m1=(Integer.parseInt(m1))+"";
leavedate=d1+"/"+m1+"/"+y1;

			StringTokenizer st3 = new StringTokenizer(tdate,"/");
		    d1=st3.nextToken();
		    m1=st3.nextToken();
		    y1=st3.nextToken();
			tdate1=m1+"/"+d1+"/"+y1;
			
        if (option.matches("C")) {

sql="select max(datediff(M,appdate,'"+fdate1+"'))+1 from staff where staffid='"+staffid+"'";
					    rs = stmt.executeQuery(sql);
					    if(rs.next())
					    {
						p=rs.getInt(1);
					    }
					    rs.close();
    				if(p>=12) ecl=12;
    				else
    				{
    					if(p>mm1) ecl=mm1;
    					else
    					ecl=p;
    				}

int j1=0;
sql="select max(datediff(d,appdate,'"+fdate1+"'))+1 from staff where staffid='"+staffid+"'";
					    rs = stmt.executeQuery(sql);
					    if(rs.next())
					    {
						j1=rs.getInt(1);
					    }
					    rs.close();
    				if(j1>365) ecl=12;
    				else
    				{
    					if(p>mm1) ecl=mm1;
    					else
    					ecl=p;
    				}

sql="select sum(days) from leave_register where leaveyearid=7 and status='Y' and staffid='"+staffid+"' and year(fromdate)="+yy1;
					    rs = stmt.executeQuery(sql);
					    if(rs.next())
					    {
						ajk=rs.getFloat(1);
					    }
					    rs.close();
 ajk1=(float)ecl - ajk;
 if(ajk1<adays) error="Eligible Cl:"+ecl+"    Availed Cl:"+ajk+"   ... You can Submit LOP";
}
 			
        if (!option.matches("P")) {


sql="SELECT ltrim(str(day(ldate)))+'/'+ltrim(str(month(ldate)))+'/'+ltrim(str(year(ldate))) from leave_classwork,leave_register where leaveclassid=leaveregisterid and leave_register.status='Y' and leaveyearid=7 and leave_classwork.staffid='"+staffid+"'  and year(ldate)="+yy1;
						    rs = stmt.executeQuery(sql);
					    while(rs.next())
					    {
					    pdate=rs.getString(1);
						if(pdate.matches(leavedate)) error="Not Successful: One Leave-Application Per Day Allowed";
						
					    }
					    rs.close();

sql="select status from leave_register where staffid='"+staffid+"' and fromdate between '"+fdate1+"' and '"+tdate1+"'  and todate between '"+fdate1+"' and '"+tdate1+"'";
						    rs = stmt.executeQuery(sql);
					    if(rs.next())
					    {
							error="Not Successful: One Leave-Application Per Day Allowed";
					    }
					    rs.close();

}
if(error.matches("Success"))	
{				
 
		 e1=InsertArrange();			
if(e1.matches("..."))	
{				


                   sql="INSERT into leave_register(leaveyearid,fromdate,todate,reason,staffid,astaffid,ahours,submitdate,hodstatus,princstatus,corresstatus,year,status,days,leaveregisterid) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

					ps=con.prepareStatement(sql);
					ps.setInt(1,leaveyearid);
					ps.setString(2,fdate1);
					ps.setString(3,tdate1);
					ps.setString(4,reason);
					ps.setString(5,staffid);
					ps.setString(6,astaffid);
					ps.setString(7,ahours);
					ps.setString(8,submitdate1);
					ps.setString(9,hodstatus);
					ps.setString(10,princstatus);
					ps.setString(11,corresstatus);
					ps.setInt(12,year);
					ps.setString(13,status);
//					ps.setInt(14,count);
					ps.setFloat(14,adays);
					ps.setInt(15,leaveregisterid);
					int i = ps.executeUpdate();
					ps.close();
}
else
{

error="Invalid Entry";
}
  }        	
          	
	      }
                    stmt.close();
					connMgr.freeConnection("xavier",con); 
					}catch(Exception e){error=e.toString();}
					
		               return(error+e1);	
					
					

}


public String InsertArrange()
{
days=0;
                String sql="";

try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;
					    PreparedStatement ps;
for(int i=0;i<count;i++)
{
			StringTokenizer st1 = new StringTokenizer(report[0][i],"/");
		    d1=st1.nextToken();
		    m1=st1.nextToken();
		    y1=st1.nextToken();
			leavedate1=m1+"/"+d1+"/"+y1;
					
                    sql="delete from leave_classwork where ldate='"+leavedate1+"' and staffid='"+staffid+"'";
					ps=con.prepareStatement(sql);
					int k = ps.executeUpdate();
					ps.close();

					
                    sql="INSERT into leave_classwork values(?,?,?,?,?,?,?,?,?,?,?,?,?)";

					ps=con.prepareStatement(sql);
					ps.setInt(1,leaveregisterid);
					ps.setString(2,leavedate1);
					ps.setString(3,report[10][i]);
					ps.setString(4,report[1][i]);
					ps.setString(5,report[2][i]);
					ps.setString(6,report[3][i]);
					ps.setString(7,report[4][i]);
					ps.setString(8,report[5][i]);
					ps.setString(9,report[6][i]);
					ps.setString(10,report[7][i]);
					ps.setString(11,report[8][i]);
					ps.setString(12,report[9][i]);
					ps.setFloat(13,Float.parseFloat(report[11][i]));
					int j = ps.executeUpdate();
					ps.close();
	      }
     
                    stmt.close();
					connMgr.freeConnection("xavier",con);
					error1="...";
					}catch(Exception e){error1=(e.toString());}

		return error1;	

}

public String Status(String staff,int cyear)
{
int i=0;
	count=0;
try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;

           if (option.matches("P")) {

sql="SELECT ltrim(str(leaveyearid)),typename,leave_type.status,fromtime,totime,"+
"ltrim(str(day(fromdate)))+'/'+ltrim(str(month(fromdate)))+'/'+ltrim(str(year(fromdate))),"+
"ltrim(str(day(todate)))+'/'+ltrim(str(month(todate)))+'/'+ltrim(str(year(todate))),reason,"+
"ltrim(str(day(submitdate)))+'/'+ltrim(str(month(submitdate)))+'/'+ltrim(str(year(submitdate))),"+
"ltrim(str(day(leavedate)))+'/'+ltrim(str(month(leavedate)))+'/'+ltrim(str(year(leavedate))),hodstatus,princstatus,days,leaveregisterid "+
"FROM leave_register,leave_type where leaveyearid=leavetypeid and leave_register.status='Y' and staffid='"+staff+"' and leave_type.status='P' "+
"and year(leavedate)="+cyear+" order by leavedate";
}
else
{
sql="SELECT ltrim(str(leaveyearid)),typename,leave_type.status,fromtime,totime,"+
"ltrim(str(day(fromdate)))+'/'+ltrim(str(month(fromdate)))+'/'+ltrim(str(year(fromdate))),"+
"ltrim(str(day(todate)))+'/'+ltrim(str(month(todate)))+'/'+ltrim(str(year(todate))),reason,"+
"ltrim(str(day(submitdate)))+'/'+ltrim(str(month(submitdate)))+'/'+ltrim(str(year(submitdate))),"+
"ltrim(str(day(leavedate)))+'/'+ltrim(str(month(leavedate)))+'/'+ltrim(str(year(leavedate))),hodstatus,princstatus,days,leaveregisterid "+
"FROM leave_register,leave_type where leaveyearid=leavetypeid and leave_register.status='Y' and staffid='"+staff+"' and leave_type.status<>'P' "+
"and year(fromdate)="+cyear+" order by fromdate";

	}
rs = stmt.executeQuery(sql);

while(rs.next())
{
	for(i=1;i<=14;i++)
	{
		report[i-1][count]=rs.getString(i);
	}
	count++;
}
rs.close();

stmt.close();
connMgr.freeConnection("xavier",con);
error= "success"; 
}catch(Exception e){error=(e.toString()+count+i+sql);}
return error; 
}


public String hodstatus(String staff,String approve)
{
int i=0;
	count=0;
	 			StringTokenizer st2 = new StringTokenizer(fdate,"/");
		    d1=st2.nextToken();
		    m1=st2.nextToken();
		    y1=st2.nextToken();
			fdate1=m1+"/"+d1+"/"+y1;
			StringTokenizer st3 = new StringTokenizer(tdate,"/");
		    d1=st3.nextToken();
		    m1=st3.nextToken();
		    y1=st3.nextToken();
			tdate1=m1+"/"+d1+"/"+y1;	

try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;
if (option.matches("P")) {
if (approve.matches("H"))

sql="SELECT ltrim(str(leaveyearid))typename,leave_type.status,fromtime,totime,"+
"ltrim(str(day(fromdate)))+'/'+ltrim(str(month(fromdate)))+'/'+ltrim(str(year(fromdate))),"+
"ltrim(str(day(todate)))+'/'+ltrim(str(month(todate)))+'/'+ltrim(str(year(todate))),reason,"+
"ltrim(str(day(submitdate)))+'/'+ltrim(str(month(submitdate)))+'/'+ltrim(str(year(submitdate))),"+
"ltrim(str(day(leavedate)))+'/'+ltrim(str(month(leavedate)))+'/'+ltrim(str(year(leavedate))),hodstatus,princstatus,name,ltrim(str(leaveregisterid)),days,leaveregisterid,leave_register.staffid,ltrim(str(leaveregisterid)) "+
"FROM leave_register,leave_type,staff where staff.staffid=leave_register.staffid and leaveyearid=leavetypeid and leave_register.status='Y' and leave_type.status='P'"+
"and leavedate>='"+fdate1+"' and leavedate<='"+tdate1+"' and dno="+dno+" order by leavedate";
else
sql="SELECT ltrim(str(leaveyearid))typename,leave_type.status,fromtime,totime,"+
"ltrim(str(day(fromdate)))+'/'+ltrim(str(month(fromdate)))+'/'+ltrim(str(year(fromdate))),"+
"ltrim(str(day(todate)))+'/'+ltrim(str(month(todate)))+'/'+ltrim(str(year(todate))),reason,"+
"ltrim(str(day(submitdate)))+'/'+ltrim(str(month(submitdate)))+'/'+ltrim(str(year(submitdate))),"+
"ltrim(str(day(leavedate)))+'/'+ltrim(str(month(leavedate)))+'/'+ltrim(str(year(leavedate))),hodstatus,princstatus,name,ltrim(str(leaveregisterid)),days,leaveregisterid,leave_register.staffid,ltrim(str(leaveregisterid)) "+
"FROM leave_register,leave_type,staff where staff.staffid=leave_register.staffid and leaveyearid=leavetypeid and leave_type.status='P'"+
"and leavedate>='"+fdate1+"' and leavedate<='"+tdate1+"' order by leavedate";

}
else
{
	if (approve.matches("H"))

sql="SELECT ltrim(str(leaveyearid)),typename,leave_type.status,fromtime,totime,"+
"ltrim(str(day(fromdate)))+'/'+ltrim(str(month(fromdate)))+'/'+ltrim(str(year(fromdate))),"+
"ltrim(str(day(todate)))+'/'+ltrim(str(month(todate)))+'/'+ltrim(str(year(todate))),reason,"+
"ltrim(str(day(submitdate)))+'/'+ltrim(str(month(submitdate)))+'/'+ltrim(str(year(submitdate))),"+
"ltrim(str(day(leavedate)))+'/'+ltrim(str(month(leavedate)))+'/'+ltrim(str(year(leavedate))),hodstatus,princstatus,name,ltrim(str(leaveregisterid)),days,leaveregisterid,leave_register.staffid,ltrim(str(leaveregisterid)) "+
"FROM leave_register,leave_type,staff where staff.staffid=leave_register.staffid and leaveyearid=leavetypeid and leave_register.status='Y' and leave_type.status<>'P'"+
"and fromdate>='"+fdate1+"' and fromdate<='"+tdate1+"' and dno="+dno+" order by fromdate";
else

sql="SELECT ltrim(str(leaveyearid)),typename,leave_type.status,fromtime,totime,"+
"ltrim(str(day(fromdate)))+'/'+ltrim(str(month(fromdate)))+'/'+ltrim(str(year(fromdate))),"+
"ltrim(str(day(todate)))+'/'+ltrim(str(month(todate)))+'/'+ltrim(str(year(todate))),reason,"+
"ltrim(str(day(submitdate)))+'/'+ltrim(str(month(submitdate)))+'/'+ltrim(str(year(submitdate))),"+
"ltrim(str(day(leavedate)))+'/'+ltrim(str(month(leavedate)))+'/'+ltrim(str(year(leavedate))),hodstatus,princstatus,name,ltrim(str(leaveregisterid)),days,leaveregisterid,leave_register.staffid,ltrim(str(leaveregisterid)) "+
"FROM leave_register,leave_type,staff where staff.staffid=leave_register.staffid and leaveyearid=leavetypeid and leave_type.status<>'P'"+
"and fromdate>='"+fdate1+"' and fromdate<='"+tdate1+"' order by fromdate";
}	
rs = stmt.executeQuery(sql);

while(rs.next())
{
	for(i=1;i<=17;i++)
	{
		report[i-1][count]=rs.getString(i);
	}
	count++;
}
rs.close();

stmt.close();
connMgr.freeConnection("xavier",con);
error="sucess";
}catch(Exception e){error=(e.toString()+count+i+sql);}
return error; 
}



public String hodInsert(String sdate,String approve)
{
try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;
					    PreparedStatement ps;
			StringTokenizer st = new StringTokenizer(sdate,"/");
		    d1=st.nextToken();
		    m1=st.nextToken();
		    y1=st.nextToken();
			submitdate1=m1+"/"+d1+"/"+y1;
					
 	for(int j=0;j<count;j++)
 	{
 		           if (approve.matches("H")) 

                   sql="update leave_register set hodstatus =?,hodapprovdate=? where leaveregisterid=?";
else
                   sql="update leave_register set princstatus =?,princapprovdate=? where leaveregisterid=?";

					ps=con.prepareStatement(sql);
					ps.setString(1,report[1][j]);
					ps.setString(2,submitdate1);
					ps.setInt(3,Integer.parseInt(report[0][j]));
					int i = ps.executeUpdate();
					ps.close();
}
                    stmt.close();
					connMgr.freeConnection("xavier",con);
					error="success";
					}catch(Exception e){error=(e.toString()+approve);}
		return error;	
}


public String SPInsert()
{

try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;
					    PreparedStatement ps;
					
 	for(int j=0;j<count;j++)
 	{

                   sql="delete leave_spl where sdate=? and staffid=?";

					ps=con.prepareStatement(sql);
					ps.setString(1,report[0][j]);
					ps.setString(2,report[1][j]);
					int i = ps.executeUpdate();
					ps.close();
	if (report[2][j].matches("Y"))
{
                   sql="insert into leave_spl values(?,?,?)";

					ps=con.prepareStatement(sql);
					ps.setString(1,report[0][j]);
					ps.setString(2,report[1][j]);
					ps.setString(3,"Special Permission");
					int k = ps.executeUpdate();
					ps.close();
}
		}
                    stmt.close();
					connMgr.freeConnection("xavier",con);
					error="success";
					}catch(Exception e){error=(e.toString());}

		return error;
}

public String SPInsertWReason()
{

try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;
					    PreparedStatement ps;
					
 	for(int j=0;j<count;j++)
 	{

                   sql="delete leave_spl where sdate=? and staffid=?";

					ps=con.prepareStatement(sql);
					ps.setString(1,report[0][j]);
					ps.setString(2,report[1][j]);
					int i = ps.executeUpdate();
					ps.close();
	if (report[2][j].matches("Y"))
{
                   sql="insert into leave_spl values(?,?,?)";

					ps=con.prepareStatement(sql);
					ps.setString(1,report[0][j]);
					ps.setString(2,report[1][j]);
					ps.setString(3,report[3][j]);
//					ps.setString(3,"Special Permission");
					int k = ps.executeUpdate();
					ps.close();
}
		}
                    stmt.close();
					connMgr.freeConnection("xavier",con);
					error="success";
					}catch(Exception e){error=(e.toString());}

		return error;
}


public String LeaveDel()
{

try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;
					    PreparedStatement ps;
					
 	for(int j=0;j<count;j++)
 	{

 if (report[1][j].matches("Y"))
{
                  sql="delete from leave_register where leaveregisterid=?";

					ps=con.prepareStatement(sql);
					ps.setString(1,report[0][j]);
					int i = ps.executeUpdate();
					ps.close();

                  sql="delete from leave_classwork where leaveclassid=?";

					ps=con.prepareStatement(sql);
					ps.setString(1,report[0][j]);
					int k = ps.executeUpdate();
					ps.close();
}
		}
                    stmt.close();
					connMgr.freeConnection("xavier",con);
					error="success";
					}catch(Exception e){error=(e.toString());
					}
					
		return error;
}


public String datediffer()
{
	count=0;
		String month="",day="",year="";
			StringTokenizer st2 = new StringTokenizer(fdate,"/");
		    d1=st2.nextToken();
		    m1=st2.nextToken();
		    y1=st2.nextToken();
			fdate1=m1+"/"+d1+"/"+y1;
			StringTokenizer st3 = new StringTokenizer(tdate,"/");
		    d1=st3.nextToken();
		    m1=st3.nextToken();
		    y1=st3.nextToken();
			tdate1=m1+"/"+d1+"/"+y1;	

try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;

rs = stmt.executeQuery("select datediff(d,'"+fdate1+"','"+tdate1+"')");
if(rs.next())
{
	datecount=rs.getInt(1)+1;
}
rs.close();
for(int i=0;i<datecount;i++)
{
	
rs = stmt.executeQuery("select month(dateadd(day,"+i+",'"+fdate1+"')),day(dateadd(day,"+i+",'"+fdate1+"')),year(dateadd(day,"+i+",'"+fdate1+"'))");
if(rs.next())
{
	month=rs.getString(1);
	day=rs.getString(2);
	year=rs.getString(3);
//String	date1=month+"/"+day+"/"+year;
  	if(day.length()==1)day="0"+day;
  	if(month.length()==1)month="0"+month;

		report[0][count]=day+"/"+month+"/"+year;
		count++;
	
}
rs.close();	
	}

stmt.close();
connMgr.freeConnection("xavier",con);
error="suc";
}catch(Exception e){ error=(e.toString());}

return error;
}


public String assignStatus(int leaveid)
{
int i=0;
	count=0;

try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;
sql="select ltrim(str(leaveclassid)),ltrim(str(day(ldate)))+'/'+ltrim(str(month(ldate)))+'/'+ltrim(str(year(ldate))),staffid,period,hr1,hr2,hr3,hr4,hr5,hr6,hr7,hr8,ltrim(days),hr1 from leave_classwork where leaveclassid="+leaveid+" and staffid='"+staffid+"' order by ldate";


rs = stmt.executeQuery(sql);

while(rs.next())
{
	for(i=1;i<=13;i++)
	{
		report[i-1][count]=rs.getString(i);
	}
	count++;
}
rs.close();

stmt.close();
connMgr.freeConnection("xavier",con);
error="success";
}catch(Exception e){error=(e.toString()+count+i+sql);}
return error;
}

public String leaverep(String fdate2,String tdate2,int leaveno,int dn)
{
int i=0;
	count=0;
			StringTokenizer st = new StringTokenizer(fdate2,"/");
		    d1=st.nextToken();
		    m1=st.nextToken();
		    y1=st.nextToken();
			fdate=m1+"/"+d1+"/"+y1;
			StringTokenizer st1 = new StringTokenizer(tdate2,"/");
		    d1=st1.nextToken();
		    m1=st1.nextToken();
		    y1=st1.nextToken();
			tdate=m1+"/"+d1+"/"+y1;

try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
	
				    ResultSet rs;
if(leaveno>0)
{				    
if (dn>0)
sql="select ltrim(str(day(ldate)))+'/'+ltrim(str(month(ldate)))+'/'+ltrim(str(year(ldate))),leave_classwork.staffid,name,sf,period,leave_classwork.days,typename,rtrim(reason),period"
+" from leave_register,leave_classwork,staff,leave_type,department where leaveregisterid=leaveclassid  and staff.staffid=leave_register.staffid and leavetypeid=leaveyearid and staff.dno=department.dno "
+"and ldate between '"+fdate+"' and '"+tdate+"' and leaveyearid="+leaveno+" and staff.dno="+dn+" order by leave_classwork.staffid,ldate";
else
sql="select ltrim(str(day(ldate)))+'/'+ltrim(str(month(ldate)))+'/'+ltrim(str(year(ldate))),leave_classwork.staffid,name,sf,period,leave_classwork.days,typename,rtrim(reason),period"
+" from leave_register,leave_classwork,staff,leave_type,department where leaveregisterid=leaveclassid  and staff.staffid=leave_register.staffid and leavetypeid=leaveyearid and staff.dno=department.dno "
+"and ldate between '"+fdate+"' and '"+tdate+"' and leaveyearid="+leaveno+" order by leave_classwork.staffid,ldate";
}
else
{
if (dn>0)
sql="select ltrim(str(day(ldate)))+'/'+ltrim(str(month(ldate)))+'/'+ltrim(str(year(ldate))),leave_classwork.staffid,name,sf,period,leave_classwork.days,typename,rtrim(reason),period"
+" from leave_register,leave_classwork,staff,leave_type,department where leaveregisterid=leaveclassid  and staff.staffid=leave_register.staffid and leavetypeid=leaveyearid and staff.dno=department.dno "
+"and ldate between '"+fdate+"' and '"+tdate+"' and staff.dno="+dn+" order by leave_classwork.staffid,ldate";
else
sql="select ltrim(str(day(ldate)))+'/'+ltrim(str(month(ldate)))+'/'+ltrim(str(year(ldate))),leave_classwork.staffid,name,sf,period,leave_classwork.days,typename,rtrim(reason),period"
+" from leave_register,leave_classwork,staff,leave_type,department where leaveregisterid=leaveclassid  and staff.staffid=leave_register.staffid and leavetypeid=leaveyearid and staff.dno=department.dno "
+"and ldate between '"+fdate+"' and '"+tdate+"' order by leave_classwork.staffid,ldate";	
}
rs = stmt.executeQuery(sql);

while(rs.next())
{
	for(i=1;i<=8;i++)
	{
		report[i-1][count]=rs.getString(i);
	}
	count++;
}
rs.close();

stmt.close();
connMgr.freeConnection("xavier",con);
error="success";
}catch(Exception e){error=(e.toString()+count+i+sql);}
return error;
}


public String absrep(String fdate2,int dn)
{
int i=0;
	acount=0;
			StringTokenizer st = new StringTokenizer(fdate2,"/");
		    d1=st.nextToken();
		    m1=st.nextToken();
		    y1=st.nextToken();
			fdate=m1+"/"+d1+"/"+y1;
error1=acount+absgetstaff(dn);
sql=absesslrep(fdate);
return error1+"----------"+sql;
}
			
public String absgetstaff(int dn)
{

try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
	
				    ResultSet rs;
if (dn>0)
sql="select staffid,name,sf from staff,department where department.dno=staff.dno and staff.dno="+dn+" and active=1 order by staff.dno, staffid";
else
sql="select staffid,name,sf from staff,department where department.dno=staff.dno and active=1 order by staff.dno, staffid";

rs = stmt.executeQuery(sql);

while(rs.next())
{
	for(int i=1;i<=3;i++)
	{
		report[i-1][acount]=rs.getString(i);
	}
	acount++;
}
rs.close();

stmt.close();
connMgr.freeConnection("xavier",con);
error="success";
}catch(Exception e){error=(e.toString()+acount+sql);}
return error;
}


public String absesslrep(String fdate3)
{
	int dur=0;
try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("essl");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;
					    count=0;
for(int i=0;i<acount;i++)
{
	dur=0;
sql="select duration from attendancelog,staff where staff.id=attendancelog.staffid  and staffcode='"+report[0][i]+"' and attendancedate='"+fdate3+"'";

rs = stmt.executeQuery(sql);

if(rs.next())
{
	dur=rs.getInt(1);
}
rs.close();
if(dur==0)
{
	report[5][count]=dur+"";
	report[0][count]=report[0][i];
	report[1][count]=report[1][i];
	report[2][count]=report[2][i];
	report[10][count]=report[0][i];
	report[12][count]=fdate3;
	count++;
}
}
stmt.close();
connMgr.freeConnection("essl",con);
error=checkleave();
}catch(Exception e){error=e.toString();}

return error;
}







public String getStaff(int dn)
{
int i=0;
	count=0;

try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;
if (dn>0)
sql="select staffid,name,sf from staff, department where staff.dno=department.dno and active=1 and staff.dno="+dn+" order by staffid";
else
sql="select staffid,name,sf from staff, department where staff.dno=department.dno and active=1 order by staff.dno,staffid";

rs = stmt.executeQuery(sql);

while(rs.next())
{
	for(i=1;i<=3;i++)
	{
		report[i-1][count]=rs.getString(i);
	}
	count++;
}
rs.close();

stmt.close();
connMgr.freeConnection("xavier",con);
error="success";
}catch(Exception e){error=(e.toString()+count+i+sql);}
return error;
}


public String staffpunchrep(String fdate1,String tdate1,String device1,String staff)
{
int i=0;
staffid=staff;
device=" and devicename like '"+device1+"%' order by datetime";
count=0;
			StringTokenizer st = new StringTokenizer(fdate1,"/");
		    d1=st.nextToken();
		    m1=st.nextToken();
		    y1=st.nextToken();
			fdate=m1+"/"+d1+"/"+y1;
			StringTokenizer st1 = new StringTokenizer(tdate1,"/");
		    d1=st1.nextToken();
		    m1=st1.nextToken();
		    y1=st1.nextToken();
			tdate=m1+"/"+d1+"/"+y1;

try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("essl");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;

sql="select ltrim(str(day(datetime)))+'/'+ltrim(str(month(datetime)))+'/'+ltrim(str(year(datetime))),rtrim(SUBSTRING(CONVERT(varchar(24), datetime, 121), 12, 5)),devicename from vw_logrecords where staffcode='"
+staff+"' and datetime between '"+fdate+"' and '"+tdate+"' "+device;
rs = stmt.executeQuery(sql);

while(rs.next())
{
	for(i=1;i<=3;i++)
	{
		report[i-1][count]=rs.getString(i);
	}
	count++;
}

rs.close();

stmt.close();
connMgr.freeConnection("essl",con);
error=sql;
}catch(Exception e){error=e.toString()+sql;}

return error;
}	


public String staffnewpunchrep2017(String fdate1,String tdate1,String staff)
{
int i=0;
staffid=staff;
count=0;
sql=fdate1+staff+tdate1;
			StringTokenizer st = new StringTokenizer(fdate1,"/");
		    d1=st.nextToken();
		    m1=st.nextToken();
		    y1=st.nextToken();
			fdate=m1+"/"+d1+"/"+y1;
			StringTokenizer st1 = new StringTokenizer(tdate1,"/");
		    d1=st1.nextToken();
		    m1=st1.nextToken();
		    y1=st1.nextToken();
			tdate=m1+"/"+d1+"/"+y1;
try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("esslnew");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;
sql="select attendancedate,substring(('000'+ltrim(rtrim(str(day(attendancedate))))), len('000'+ltrim(rtrim(str(day(attendancedate)))))-1,2)+'/'+  substring(('000'+ltrim(rtrim(str(month(attendancedate))))), "
+"len('000'+ltrim(rtrim(str(month(attendancedate)))))-1,2)+'/'+ ltrim(rtrim(str(year(attendancedate)))),punchrecords,status,statuscode,p1status,p2status,Lateby,Earlyby from vw_attendancelogs "
+"where employeecode='" +staff+ "' and attendancedate between '"+fdate+"' and '"+tdate+"' order by attendancedate";

rs = stmt.executeQuery(sql);

while(rs.next())
{
	for(i=1;i<=9;i++)
	{
		report2[i-1][count]=rs.getString(i);
	}
	count++;
}

rs.close();

stmt.close();
connMgr.freeConnection("esslnew",con);
error=sql;
}catch(Exception e){error=e.toString()+sql;}
return error;
}

public String staffnewpunchrep(String fdate1,String tdate1,String device1,String staff,int jflag)
{
int i=0;
staffid=staff;
device=" and devicename like '"+device1+"%' GROUP  BY ldate1 order by max(datetime)";
count=0;
			StringTokenizer st = new StringTokenizer(fdate1,"/");
		    d1=st.nextToken();
		    m1=st.nextToken();
		    y1=st.nextToken();
			fdate=m1+"/"+d1+"/"+y1;
			StringTokenizer st1 = new StringTokenizer(tdate1,"/");
		    d1=st1.nextToken();
		    m1=st1.nextToken();
		    y1=st1.nextToken();
			tdate=m1+"/"+d1+"/"+y1;
try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("essl");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;
if(jflag==1)
sql="SELECT [ldate1],ltrim(str(day(max(datetime))))+'/'+ltrim(str(month(max(datetime))))+'/'+ltrim(str(year(max(datetime)))),"
+" REPLACE(REPLACE(REPLACE((SELECT rtrim(SUBSTRING(CONVERT(varchar(24), datetime, 121), 12, 5)) + '(' + CAST([devicename] AS VARCHAR(MAX)) as A "
+" FROM   vw_logrecords WHERE  ( ldate1 = Results.ldate1  and staffcode='" +staff+ "' ) FOR XML PATH ('')), '</A><A>', '), '),'<A>',''),'</A>',')') AS NameValues "
+" FROM   vw_logrecords Results where staffcode='" +staff+ "' and datetime between '"+fdate+"' and '"+tdate+"' "+device;
else
sql="SELECT [ldate1],ltrim(str(day(max(datetime))))+'/'+ltrim(str(month(max(datetime))))+'/'+ltrim(str(year(max(datetime)))),"
+" REPLACE(REPLACE(REPLACE((SELECT '('+rtrim(SUBSTRING(CONVERT(varchar(24), datetime, 121), 12, 5)) as A  "
+" FROM   vw_logrecords WHERE  ( ldate1 = Results.ldate1  and staffcode='" +staff+ "' ) FOR XML PATH ('')), '</A><A>', '), '),'<A>',''),'</A>',')') AS NameValues "
+" FROM   vw_logrecords Results where staffcode='" +staff+ "' and datetime between '"+fdate+"' and '"+tdate+"' "+device;

rs = stmt.executeQuery(sql);

while(rs.next())
{
	for(i=1;i<=3;i++)
	{
		report2[i-1][count]=rs.getString(i);
	}
	count++;
}

rs.close();

stmt.close();
connMgr.freeConnection("essl",con);
error=sql;
}catch(Exception e){error=e.toString()+sql;}
return error;
}	



public String staffallpunchrep(String fdate1,String tdate1,String device1)
{
int i=0;
device=" and devicename like '"+device1+"%' order by day(datetime)+month(datetime),name";
count=0;
			StringTokenizer st = new StringTokenizer(fdate1,"/");
		    d1=st.nextToken();
		    m1=st.nextToken();
		    y1=st.nextToken();
			fdate=m1+"/"+d1+"/"+y1;
			StringTokenizer st1 = new StringTokenizer(tdate1,"/");
		    d1=st1.nextToken();
		    m1=st1.nextToken();
		    y1=st1.nextToken();
			tdate=m1+"/"+d1+"/"+y1;

try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("essl");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;

sql="select staff.staffcode,name,ltrim(str(day(datetime)))+'/'+ltrim(str(month(datetime)))+'/'+ltrim(str(year(datetime))),rtrim(SUBSTRING(CONVERT(varchar(24), datetime, 121), 12, 5)),devicename from vw_logrecords,staff where vw_logrecords.staffcode=staff.staffcode "
+" and datetime between '"+fdate+"' and '"+tdate+"' "+device;
rs = stmt.executeQuery(sql);

while(rs.next())
{
	for(i=1;i<=5;i++)
	{
		report[i-1][count]=rs.getString(i);
	}
	count++;
}

rs.close();

stmt.close();
connMgr.freeConnection("essl",con);
error=sql;
}catch(Exception e){error=e.toString()+sql;}
return error;
}	

public String staffallpunchrep2017(String fdate1,String tdate1,String device1)
{
int i=0;
if(device1.trim().equalsIgnoreCase("all"))
device=" order by employeecode";
else
device=" and punchrecords like '"+device1+"%' order by attendancedate,departmentid,stringcode desc,numericcode";
count=0;
			StringTokenizer st = new StringTokenizer(fdate1,"/");
		    d1=st.nextToken();
		    m1=st.nextToken();
		    y1=st.nextToken();
			fdate=m1+"/"+d1+"/"+y1;
			StringTokenizer st1 = new StringTokenizer(tdate1,"/");
		    d1=st1.nextToken();
		    m1=st1.nextToken();
		    y1=st1.nextToken();
			tdate=m1+"/"+d1+"/"+y1;

try
{
	
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("esslnew");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;

sql="select attendancedate,substring(('000'+ltrim(rtrim(str(day(attendancedate))))), len('000'+ltrim(rtrim(str(day(attendancedate)))))-1,2)+'/'+  substring(('000'+ltrim(rtrim(str(month(attendancedate))))), "
+"len('000'+ltrim(rtrim(str(month(attendancedate)))))-1,2)+'/'+ ltrim(rtrim(str(year(attendancedate)))),punchrecords,status,statuscode,p1status,p2status,Lateby,Earlyby,duration,employeecode,employeename,ltrim(str(month(attendancedate)))+'/'+ltrim(str(day(attendancedate)))+'/'+ltrim(str(year(attendancedate))) "
+"from vw_attendancelogs,sxcce.dbo.staff where staff.staffid=employeecode and active=1 and  attendancedate between  '"+fdate+"' and '"+tdate+"' "
+device;

//select employeecode,employeename,attendancedate,substring(('000'+ltrim(rtrim(str(day(attendancedate))))), len('000'+ltrim(rtrim(str(day(attendancedate)))))-1,2)+'/'+  substring(('000'+ltrim(rtrim(str(month(attendancedate))))), "
//+"len('000'+ltrim(rtrim(str(month(attendancedate)))))-1,2)+'/'+ ltrim(rtrim(str(year(attendancedate)))),punchrecords,status,statuscode,p1status,p2status,Lateby,Earlyby "
//+"from vw_attendancelogs,sxcce.dbo.staff where staff.staffid=employeecode and active=1 and attendancedate between  '"+fdate+"' and '"+tdate+"' "
//+device;

rs = stmt.executeQuery(sql);

while(rs.next())
{
	for(i=1;i<=13;i++)
	{
		report[i-1][count]=rs.getString(i);
	}
	count++;
}

rs.close();

stmt.close();
connMgr.freeConnection("essl",con);
error=sql+checkleavenew1();
//error=sql;
}catch(Exception e){error=e.toString()+sql;}
return error;
}	


public String staffrep(String fdate1,String tdate1,String staff)
{
int i=0;
staffid=staff;
			count=0;
			StringTokenizer st = new StringTokenizer(fdate1,"/");
		    d1=st.nextToken();
		    m1=st.nextToken();
		    y1=st.nextToken();
			fdate=m1+"/"+d1+"/"+y1;
			StringTokenizer st1 = new StringTokenizer(tdate1,"/");
		    d1=st1.nextToken();
		    m1=st1.nextToken();
		    y1=st1.nextToken();
			tdate=m1+"/"+d1+"/"+y1;
try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("essl");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;

sql="select ltrim(str(day(attendancedate)))+'/'+ltrim(str(month(attendancedate)))+'/'+ltrim(str(year(attendancedate))),"
+"firstpunch,lastpunch,latecomingby,earlygoingby,duration,logrecords,daystatusid,workstatusid,"
+"logrecords,staffcode,name,ltrim(str(month(attendancedate)))+'/'+ltrim(str(day(attendancedate)))+'/'+ltrim(str(year(attendancedate))) from attendancelog,staff where staff.id=attendancelog.staffid  AND not (period1status='P' and Period2status='P') AND (AttendanceLog.DayStatusId IN (2, 5, 6, 7, 8)) and staffcode='"+staff+"'"
+"and attendancedate between '"+fdate+"' and '"+tdate+"' order by attendancedate";
rs = stmt.executeQuery(sql);

while(rs.next())
{
	for(i=1;i<=13;i++)
	{
		report[i-1][count]=rs.getString(i);
	}
	count++;
}

rs.close();

stmt.close();
connMgr.freeConnection("essl",con);
error=checkleave();
}catch(Exception e){error=e.toString();}
return error;
}	

public String staffrepnew(String fdate1,String tdate1,String staff)
{
int i=0;
staffid=staff;
			count=0;
			StringTokenizer st = new StringTokenizer(fdate1,"/");
		    d1=st.nextToken();
		    m1=st.nextToken();
		    y1=st.nextToken();
			fdate=m1+"/"+d1+"/"+y1;
			StringTokenizer st1 = new StringTokenizer(tdate1,"/");
		    d1=st1.nextToken();
		    m1=st1.nextToken();
		    y1=st1.nextToken();
			tdate=m1+"/"+d1+"/"+y1;
try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("esslnew");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;


sql="select attendancedate,substring(('000'+ltrim(rtrim(str(day(attendancedate))))), len('000'+ltrim(rtrim(str(day(attendancedate)))))-1,2)+'/'+  substring(('000'+ltrim(rtrim(str(month(attendancedate))))), "
+"len('000'+ltrim(rtrim(str(month(attendancedate)))))-1,2)+'/'+ ltrim(rtrim(str(year(attendancedate)))),punchrecords,status,statuscode,p1status,p2status,Lateby,Earlyby,duration,employeecode,employeename,ltrim(str(month(attendancedate)))+'/'+ltrim(str(day(attendancedate)))+'/'+ltrim(str(year(attendancedate))) from vw_attendancelogs "
+"where employeecode='" +staff+ "' and attendancedate between '"+fdate+"' and '"+tdate+"' and  ( statuscode not in ('P','H','WO','SL','SLP') or p1status='Lt' or p2status='Lt' or Lateby>0 or Earlyby>0 ) order by attendancedate";

rs = stmt.executeQuery(sql);


while(rs.next())
{
	for(i=1;i<=13;i++)
	{
		report[i-1][count]=rs.getString(i);
	}
	count++;
}

rs.close();

stmt.close();
connMgr.freeConnection("esslnew",con);
error=checkleavenew();
}catch(Exception e){error=e.toString();}
return error;
}	

public String staffallrep(String fdate1,String tdate1)
{
int i=0;
 
			count=0;
			StringTokenizer st = new StringTokenizer(fdate1,"/");
		    d1=st.nextToken();
		    m1=st.nextToken();
		    y1=st.nextToken();
			fdate=m1+"/"+d1+"/"+y1;
			StringTokenizer st1 = new StringTokenizer(tdate1,"/");
		    d1=st1.nextToken();
		    m1=st1.nextToken();
		    y1=st1.nextToken();
			tdate=m1+"/"+d1+"/"+y1;
try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("essl");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;

sql="select ltrim(str(day(attendancedate)))+'/'+ltrim(str(month(attendancedate)))+'/'+ltrim(str(year(attendancedate))),"
+"firstpunch,lastpunch,latecomingby,earlygoingby,duration,logrecords,daystatusid,workstatusid,"
+"logrecords,staffcode,name,ltrim(str(month(attendancedate)))+'/'+ltrim(str(day(attendancedate)))+'/'+ltrim(str(year(attendancedate))) from attendancelog,staff where staff.id=attendancelog.staffid  AND not (period1status='P' and Period2status='P') AND (AttendanceLog.DayStatusId IN (2, 5, 6, 7, 8)) "
+"and attendancedate between '"+fdate+"' and '"+tdate+"' order by staffcode";
rs = stmt.executeQuery(sql);

while(rs.next())
{
	for(i=1;i<=13;i++)
	{
		report[i-1][count]=rs.getString(i);
	}
	count++;
}

rs.close();

stmt.close();
connMgr.freeConnection("essl",con);
error=checkleave();
}catch(Exception e){error=e.toString();}
return error;
}	

public String staffallrep2017(String fdate1,String tdate1,String device1)
{
int i=0;
if(device1.trim().equalsIgnoreCase("all"))
device=" order by employeecode";
else
device=" and punchrecords like '"+device1+"%' order by attendancedate,departmentid,stringcode desc,numericcode";
count=0;
			StringTokenizer st = new StringTokenizer(fdate1,"/");
		    d1=st.nextToken();
		    m1=st.nextToken();
		    y1=st.nextToken();
			fdate=m1+"/"+d1+"/"+y1;
			StringTokenizer st1 = new StringTokenizer(tdate1,"/");
		    d1=st1.nextToken();
		    m1=st1.nextToken();
		    y1=st1.nextToken();
			tdate=m1+"/"+d1+"/"+y1;

try
{
	
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("esslnew");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;

sql="select attendancedate,substring(('000'+ltrim(rtrim(str(day(attendancedate))))), len('000'+ltrim(rtrim(str(day(attendancedate)))))-1,2)+'/'+  substring(('000'+ltrim(rtrim(str(month(attendancedate))))), "
+" len('000'+ltrim(rtrim(str(month(attendancedate)))))-1,2)+'/'+ ltrim(rtrim(str(year(attendancedate)))),punchrecords,status,statuscode,p1status,p2status,Lateby,Earlyby,duration,employeecode,employeename,ltrim(str(month(attendancedate)))+'/'+ltrim(str(day(attendancedate)))+'/'+ltrim(str(year(attendancedate))) from "
+" vw_attendancelogs ,sxcce.dbo.staff where staff.staffid=employeecode and active=1 and attendancedate between '"+fdate+"' and '"+tdate+"' and  ( statuscode not in ('P','H','WO','SL','SLP') or p1status='Lt' or p2status='Lt' or Lateby>0 or Earlyby>0 ) order by attendancedate,employeecode ";

rs = stmt.executeQuery(sql);

while(rs.next())
{
	for(i=1;i<=13;i++)
	{
		report[i-1][count]=rs.getString(i);
	}
	count++;
}

rs.close();

stmt.close();
connMgr.freeConnection("essl",con);
error=sql+checkleavenew();
}catch(Exception e){error=e.toString()+sql;}
return error;
}


public String StaffApp()
{
int i=0;
count=0;

try
{
	
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("esslnew");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;

sql="select count(*) from vw_AttendanceLogs,sxcce.dbo.staff where employeecode=staffid and active=1 "
+" and staffid not in ('TS312','TS006','NS052','NS075','TS076') "
+" and attendancedate=cast(cast(getdate() as DATE) as DATETIME) and indeviceid='' ";

rs = stmt.executeQuery(sql);

if(rs.next())
	report[0][count]=rs.getString(1);
else
	report[0][count]="";
rs.close();

count++;

sql="select p.staffid,p.name,p.cname,p.sf,isnull(q.typename,'-'),p.d from "
+" (select staffid,name,cname,sf,staff.designation d from vw_AttendanceLogs,sxcce.dbo.staff,sxcce.dbo.staffdesignation,sxcce.dbo.department "
+" where department.dno=staff.dno and slno=staff.designation and employeecode=staffid and active=1 "
+" and staffid not in ('TS312','TS006','NS052','NS075','TS076') "
+" and attendancedate=cast(cast(getdate() as DATE) as DATETIME) and indeviceid='') as p "
+" left outer join  "
+" (select staffid,typename from sxcce.dbo.leave_register lr,sxcce.dbo.leave_type lt where leaveyearid=leavetypeid "
+" and (cast(cast(getdate() as DATE) as DATETIME) between fromdate and todate "
+" or leavedate=cast(cast(getdate() as DATE) as DATETIME))) as q on p.staffid=q.staffid"
+"  order by p.d,p.staffid ";

/*
sql="select staffid,name,cname,sf from vw_AttendanceLogs,sxcce.dbo.staff,sxcce.dbo.staffdesignation,sxcce.dbo.department "
+" where department.dno=staff.dno and slno=staff.designation and employeecode=staffid and active=1 "
+" and staffid not in ('TS312','TS006','NS052','NS075','TS076') "
+" and attendancedate=cast(cast(getdate() as DATE) as DATETIME) and indeviceid='' order by staff.Designation,employeecode";
*/
rs = stmt.executeQuery(sql);

while(rs.next())
{
	for(i=1;i<=5;i++)
	{
		report[i-1][count]=rs.getString(i);
	}
	count++;
}

rs.close();

stmt.close();
connMgr.freeConnection("essl",con);
}catch(Exception e){error=e.toString()+sql;}
return error;
}


public String checkleave()
{
String al="";
String ad="";
String ap="";
String ady="";
String hdy="";
String sphdy="";
String spreason="";

int ajk=0;
String ft="";
String tt="";
try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;
for(int i=0;i<count;i++)
{
report[21][i]="Loss of Pay.";
hdy="";
sphdy="A";
spreason="";

ady="";
al="";
ad="";
ap="";
ajk=0;
ft="";
tt="";


sql="select reason from leave_spl where staffid='"+report[10][i]+"' and sdate='"+report[12][i]+"' ";

rs = stmt.executeQuery(sql);

if(rs.next())
{
report[21][i]=rs.getString(1);
report[22][i]="Y";
rs.close();
}
else 
{
report[22][i]="N";
rs.close();

sql="select flag from leave_holiday where hdate='"+report[12][i]+"'";

rs = stmt.executeQuery(sql);

if(rs.next())
{
hdy=rs.getString(1);
report[23][i]=hdy;
ajk=1;
}

rs.close();


sql="select flag,reason from leave_splholiday where hdate='"+report[12][i]+"'";

rs = stmt.executeQuery(sql);

if(rs.next())
{
sphdy=rs.getString(1);
spreason=rs.getString(2);
report[23][i]=sphdy;
ajk=2;
}



sql="select typename,ltrim(str(day(ldate)))+'/'+ltrim(str(month(ldate)))+'/'+ltrim(str(year(ldate))),leave_classwork.staffid,period,leave_classwork.days,hr1 "
+"from leave_type,leave_register,leave_classwork where leavetypeid=leaveyearid and leaveregisterid=leaveclassid and ldate='"+report[12][i]+"' "
+" and princstatus='Y' and leave_classwork.staffid='"+report[10][i]+"' order by ldate";


rs = stmt.executeQuery(sql);


if(rs.next())
{
al=rs.getString(1);
ad=rs.getString(2);
ap=rs.getString(4);
ady=rs.getString(5);

 
if(Integer.parseInt(report[5][i])>200)
{
 if (Float.parseFloat(ady)==0.5) report[21][i]="Halfday "+al;
 else report[21][i]=al;
 }
else 
{
//if(Integer.parseInt(report[5][i])>=1)
if(Integer.parseInt(report[5][i])>200)
	{
 if (Float.parseFloat(ady)==0.5) report[21][i]="Halfday "+al;
 else report[21][i]=al;
 }
else report[21][i]="Loss of Pay";

}


if (Float.parseFloat(ady)==1) report[21][i]="Fullday "+al;

}
rs.close();


sql="select fromtime,totime from leave_register where status='Y'  and princstatus='Y' and leaveyearid=1 and staffid='"+report[10][i]+"' and leavedate='"+report[12][i]+"'";

rs = stmt.executeQuery(sql);

if(rs.next())
{
ft=rs.getString(1);
tt=rs.getString(2);

if((Integer.parseInt(report[5][i])>400) && (Integer.parseInt(report[8][i])==1))
{
// if (Float.parseFloat(ady)==0.5) report[21][i]="Halfday "+al;
 report[21][i]="Permission";
 }

}
rs.close();


if(Integer.parseInt(report[5][i])>200)
{
 if (ajk==1) report[21][i]="Saturday Halfday";
 }

if(Integer.parseInt(report[5][i])>=1)
{
 if (ajk==2) report[21][i]=spreason;
 }
 
// if (report[21][i].matches("Loss of Pay"))
//{
//report[14][i]=(Float.parseFloat(report[14][i])+1)+"";
//}
}
}
stmt.close();
connMgr.freeConnection("xavier",con);
error="success";
}catch(Exception e){error=(e.toString()+sql);}
return error; 
}


		
		
		
public String checkleavenew()
{
String al="";
String ad="";
String ap="";
String ady="";
String hdy="";
String sphdy="";
String spreason="";

int ajk=0;
String ft="";
String tt="";
try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;
for(int i=0;i<count;i++)
{
report[21][i]="Loss of Pay.";
hdy="";
sphdy="A";
spreason="";

ady="";
al="";
ad="";
ap="";
ajk=0;
ft="";
tt="";

/*
sql="select reason from attendance.dbo.holidaymaster where fdate='"+report[0][i]+"' ";

rs = stmt.executeQuery(sql);

if(rs.next())
{
report[21][i]=rs.getString(1);
report[22][i]="Y";
rs.close();
}

*/

sql="select reason from leave_spl where staffid='"+report[10][i]+"' and sdate='"+report[0][i]+"' ";

rs = stmt.executeQuery(sql);

if(rs.next())
{
report[21][i]=rs.getString(1);
report[22][i]="Y";
rs.close();
}
else 
{
report[22][i]="N";
rs.close();

sql="select flag from leave_holiday where hdate='"+report[0][i]+"'";

rs = stmt.executeQuery(sql);

if(rs.next())
{
hdy=rs.getString(1);
report[23][i]=hdy;
ajk=1;
}

rs.close();


sql="select flag,reason from leave_splholiday where hdate='"+report[0][i]+"'";

rs = stmt.executeQuery(sql);

if(rs.next())
{
sphdy=rs.getString(1);
spreason=rs.getString(2);
report[23][i]=sphdy;
ajk=2;
}




//sql="select typename,ltrim(str(day(ldate)))+'/'+ltrim(str(month(ldate)))+'/'+ltrim(str(year(ldate))),leave_classwork.staffid,period,leave_classwork.days,hr1 "
//+"from leave_type,leave_register,leave_classwork where leavetypeid=leaveyearid and leaveregisterid=leaveclassid and ldate='"+report[0][i]+"' "
//+" and leave_classwork.staffid='"+report[10][i]+"' order by ldate";

sql="select typename,ltrim(str(day(ldate)))+'/'+ltrim(str(month(ldate)))+'/'+ltrim(str(year(ldate))),leave_classwork.staffid,period,leave_classwork.days,hr1 "
+"from leave_type,leave_register,leave_classwork where leavetypeid=leaveyearid and leaveregisterid=leaveclassid and ldate='"+report[0][i]+"' "
+" and princstatus='Y' and leave_classwork.staffid='"+report[10][i]+"' order by ldate";


rs = stmt.executeQuery(sql);


if(rs.next())
{
al=rs.getString(1);
ad=rs.getString(2);
ap=rs.getString(4);
ady=rs.getString(5);

 
if(report[4][i].toString().trim().equals("폩"))
{
 if (Float.parseFloat(ady)==0.5) report[21][i]="Halfday "+al;
 else report[21][i]=al;
 }
else if ((report[4][i].toString().trim().equals("P")) && ((Integer.parseInt(report[7][i].toString().trim())>0) || (Integer.parseInt(report[8][i].toString().trim())>0)))
{
 if (Float.parseFloat(ady)==0.5) report[21][i]="Halfday "+al;
 else report[21][i]=al;
}
else 
{
 report[21][i]="Loss of Pay";

}

//if ((Float.parseFloat(ady)==0.5) && (report[5][i].toString().trim().equals("Lt"))) report[21][i]="Halfday "+al;


if (Float.parseFloat(ady)==1) report[21][i]="Fullday "+al;

}
rs.close();


//sql="select fromtime,totime from leave_register where leaveyearid=1 and staffid='"+report[10][i]+"' and leavedate='"+report[0][i]+"'";

sql="select fromtime,totime from leave_register where status='Y'  and princstatus='Y' and leaveyearid=1 and staffid='"+report[10][i]+"' and leavedate='"+report[0][i]+"'";

rs = stmt.executeQuery(sql);

if(rs.next())
{
ft=rs.getString(1);
tt=rs.getString(2);

if ((report[4][i].toString().trim().equals("P")) && ((Integer.parseInt(report[7][i].toString().trim())>0) || (Integer.parseInt(report[8][i].toString().trim())>0)))
{
 report[21][i]="Permission";
 }
 
}
rs.close();


if(report[4][i].toString().trim().equals("폩"))
{
 if (ajk==1) report[21][i]="Saturday Halfday";
 }

if(report[4][i].toString().trim().equals("A"))
{
 if (ajk==2) report[21][i]=spreason;
 }
 
// if (report[21][i].matches("Loss of Pay"))
//{
//report[14][i]=(Float.parseFloat(report[14][i])+1)+"";
//}
}
}
stmt.close();
connMgr.freeConnection("xavier",con);
error="success";
}catch(Exception e){error=(e.toString()+sql);}
return error; 
}


public String checkleavenew1()
{
String al="";
String ad="";
String ap="";
String ady="";
String hdy="";
String sphdy="";
String spreason="";

int ajk=0;
String ft="";
String tt="";
try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;
for(int i=0;i<count;i++)
{
report[21][i]="Loss of Pay.";
hdy="";
sphdy="A";
spreason="";

ady="";
al="";
ad="";
ap="";
ajk=0;
ft="";
tt="";

/*
sql="select reason from attendance.dbo.holidaymaster where fdate='"+report[0][i]+"' ";

rs = stmt.executeQuery(sql);

if(rs.next())
{
report[21][i]=rs.getString(1);
report[22][i]="Y";
rs.close();
}

*/

sql="select reason from leave_spl where staffid='"+report[10][i]+"' and sdate='"+report[0][i]+"' ";

rs = stmt.executeQuery(sql);

if(rs.next())
{
report[21][i]=rs.getString(1);
report[22][i]="Y";
rs.close();
}
else 
{
report[22][i]="N";
rs.close();

sql="select flag from leave_holiday where hdate='"+report[0][i]+"'";

rs = stmt.executeQuery(sql);

if(rs.next())
{
hdy=rs.getString(1);
report[23][i]=hdy;
ajk=1;
}

rs.close();


sql="select flag,reason from leave_splholiday where hdate='"+report[0][i]+"'";

rs = stmt.executeQuery(sql);

if(rs.next())
{
sphdy=rs.getString(1);
spreason=rs.getString(2);
report[23][i]=sphdy;
ajk=2;
}




sql="select typename,ltrim(str(day(ldate)))+'/'+ltrim(str(month(ldate)))+'/'+ltrim(str(year(ldate))),leave_classwork.staffid,period,leave_classwork.days,hr1 "
+"from leave_type,leave_register,leave_classwork where leavetypeid=leaveyearid and leaveregisterid=leaveclassid and ldate='"+report[0][i]+"' "
+" and leave_classwork.staffid='"+report[10][i]+"' order by ldate";

//sql="select typename,ltrim(str(day(ldate)))+'/'+ltrim(str(month(ldate)))+'/'+ltrim(str(year(ldate))),leave_classwork.staffid,period,leave_classwork.days,hr1 "
//+"from leave_type,leave_register,leave_classwork where leavetypeid=leaveyearid and leaveregisterid=leaveclassid and ldate='"+report[0][i]+"' "
//+" and princstatus='Y' and leave_classwork.staffid='"+report[10][i]+"' order by ldate";


rs = stmt.executeQuery(sql);


if(rs.next())
{
al=rs.getString(1);
ad=rs.getString(2);
ap=rs.getString(4);
ady=rs.getString(5);

 
if(report[4][i].toString().trim().equals("폩"))
{
 if (Float.parseFloat(ady)==0.5) report[21][i]="Halfday "+al;
 else report[21][i]=al;
 }
else if ((report[4][i].toString().trim().equals("P")) && ((Integer.parseInt(report[7][i].toString().trim())>0) || (Integer.parseInt(report[8][i].toString().trim())>0)))
{
 if (Float.parseFloat(ady)==0.5) report[21][i]="Halfday "+al;
 else report[21][i]=al;
}
else 
{
 report[21][i]="Loss of Pay";

}

//if ((Float.parseFloat(ady)==0.5) && (report[5][i].toString().trim().equals("Lt"))) report[21][i]="Halfday "+al;


if (Float.parseFloat(ady)==1) report[21][i]="Fullday "+al;

}
rs.close();


sql="select fromtime,totime from leave_register where leaveyearid=1 and staffid='"+report[10][i]+"' and leavedate='"+report[0][i]+"'";

//sql="select fromtime,totime from leave_register where status='Y'  and princstatus='Y' and leaveyearid=1 and staffid='"+report[10][i]+"' and leavedate='"+report[0][i]+"'";

rs = stmt.executeQuery(sql);

if(rs.next())
{
ft=rs.getString(1);
tt=rs.getString(2);

if ((report[4][i].toString().trim().equals("P")) && ((Integer.parseInt(report[7][i].toString().trim())>0) || (Integer.parseInt(report[8][i].toString().trim())>0)))
{
 report[21][i]="Permission";
 }
 
}
rs.close();


if(report[4][i].toString().trim().equals("폩"))
{
 if (ajk==1) report[21][i]="Saturday Halfday";
 }

if(report[4][i].toString().trim().equals("A"))
{
 if (ajk==2) report[21][i]=spreason;
 }
 
// if (report[21][i].matches("Loss of Pay"))
//{
//report[14][i]=(Float.parseFloat(report[14][i])+1)+"";
//}
}
}
stmt.close();
connMgr.freeConnection("xavier",con);
error="success";
}catch(Exception e){error=(e.toString()+sql);}
return error; 
}

public String totleavewoapr(String fdate2,String tdate2)
{
			int ajk=0;
	 		StringTokenizer st2 = new StringTokenizer(fdate2,"/");
		    d1=st2.nextToken();
		    m1=st2.nextToken();
		    y1=st2.nextToken();
			fdate1=m1+"/"+d1+"/"+y1;
			StringTokenizer st3 = new StringTokenizer(tdate2,"/");
		    d1=st3.nextToken();
		    m1=st3.nextToken();
		    y1=st3.nextToken();
			tdate1=m1+"/"+d1+"/"+y1;	

try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;
for(int i=0;i<count;i++)
{

sql="select count(*) from leave_register where leavedate between '"+fdate1+"' and '"+tdate1+"'  and leaveyearid=1 and staffid='"+report[0][i]+"' ";

rs = stmt.executeQuery(sql);
report[10][i]=" ";
if(rs.next())
{
report[10][i]=rs.getString(1);
}

rs.close();


for(int j=0;j<9;j++)
{
sql="select sum(days) from leave_register where fromdate between '"+fdate1+"' and '"+tdate1+"'  and leaveyearid="+(j+2) +" and staffid='"+report[0][i]+"' ";

rs = stmt.executeQuery(sql);

report[j+11][i]="";
if(rs.next())
{
report[j+11][i]=rs.getString(1);
}

rs.close();
try
{
if(report[j+11][i].equals("0.0")) report[j+11][i]="";
if(report[j+11][i]==null) report[j+11][i]="";
}catch(Exception e){}
}

/*
try
{
if(report[14][i]==null) fk=0;
else fk=Float.parseFloat(report[14][i]);
}catch(Exception e){}
*/
fk=0;
error=staffesslnewcheck(fdate1,tdate1,i,0);
if((fk>0) && (fk!=100)) report[20][i]=(fk)+"";
else
report[20][i]="";
}
stmt.close();
connMgr.freeConnection("xavier",con);
error=error+"success";
}catch(Exception e){error=(e.toString()+sql);}
return error; 
}

public String totleave(String fdate2,String tdate2)
{
			int ajk=0;
	 		StringTokenizer st2 = new StringTokenizer(fdate2,"/");
		    d1=st2.nextToken();
		    m1=st2.nextToken();
		    y1=st2.nextToken();
			fdate1=m1+"/"+d1+"/"+y1;
			StringTokenizer st3 = new StringTokenizer(tdate2,"/");
		    d1=st3.nextToken();
		    m1=st3.nextToken();
		    y1=st3.nextToken();
			tdate1=m1+"/"+d1+"/"+y1;	

try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;
for(int i=0;i<count;i++)
{

sql="select count(*) from leave_register where leavedate between '"+fdate1+"' and '"+tdate1+"'  and princstatus='Y' and leaveyearid=1 and staffid='"+report[0][i]+"' ";

rs = stmt.executeQuery(sql);
report[10][i]=" ";
if(rs.next())
{
report[10][i]=rs.getString(1);
}

rs.close();


for(int j=0;j<9;j++)
{
sql="select sum(days) from leave_register where fromdate between '"+fdate1+"' and '"+tdate1+"'  and princstatus='Y' and leaveyearid="+(j+2) +" and staffid='"+report[0][i]+"' ";

rs = stmt.executeQuery(sql);

report[j+11][i]="";
if(rs.next())
{
report[j+11][i]=rs.getString(1);
}

rs.close();
try
{
if(report[j+11][i].equals("0.0")) report[j+11][i]="";
if(report[j+11][i]==null) report[j+11][i]="";
}catch(Exception e){}
}

/*
try
{
if(report[14][i]==null) fk=0;
else fk=Float.parseFloat(report[14][i]);
}catch(Exception e){}
*/
fk=0;
error=staffesslnewcheck(fdate1,tdate1,i,1);
if((fk>0) && (fk!=100)) report[20][i]=(fk)+"";
else
report[20][i]="";
}
stmt.close();
connMgr.freeConnection("xavier",con);
error=error+"success";
}catch(Exception e){error=(e.toString()+sql);}
return error; 
}

public String staffesslnewcheck(String fdate2,String tdate2,int jk,int apr)
{
int i=0;
 
			 acount=0;

try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("esslnew");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;

//sql="select ltrim(str(day(attendancedate)))+'/'+ltrim(str(month(attendancedate)))+'/'+ltrim(str(year(attendancedate))),"
//+"firstpunch,lastpunch,latecomingby,earlygoingby,duration,logrecords,daystatusid,workstatusid,"
//+"logrecords,staffcode,name,ltrim(str(month(attendancedate)))+'/'+ltrim(str(day(attendancedate)))+'/'+ltrim(str(year(attendancedate))) from attendancelog,staff where staff.id=attendancelog.staffid  AND not (period1status='P' and Period2status='P') AND (AttendanceLog.DayStatusId IN (2, 5, 6, 7, 8)) "
//+"and attendancedate between '"+fdate2+"' and '"+tdate2+"' and staffcode='"+ report[0][jk] +"' order by attendancedate";

sql="select attendancedate,substring(('000'+ltrim(rtrim(str(day(attendancedate))))), len('000'+ltrim(rtrim(str(day(attendancedate)))))-1,2)+'/'+  substring(('000'+ltrim(rtrim(str(month(attendancedate))))), "
+"len('000'+ltrim(rtrim(str(month(attendancedate)))))-1,2)+'/'+ ltrim(rtrim(str(year(attendancedate)))),punchrecords,status,statuscode,p1status,p2status,Lateby,Earlyby,duration,employeecode,employeename,ltrim(str(month(attendancedate)))+'/'+ltrim(str(day(attendancedate)))+'/'+ltrim(str(year(attendancedate))) from vw_attendancelogs "
+"where employeecode='" +report[0][jk]+ "' and attendancedate between '"+fdate2+"' and '"+tdate2+"' and  ( statuscode not in ('P','H','WO','SL','SLP') or p1status='Lt' or p2status='Lt' or Lateby>0 or Earlyby>0 ) order by attendancedate";


rs = stmt.executeQuery(sql);

while(rs.next())
{
	for(i=1;i<=13;i++)
	{
		report1[i-1][acount]=rs.getString(i);
	}
	acount++;
}
//acount--;
rs.close();

stmt.close();
connMgr.freeConnection("esslnew",con);
error=staffcheckleave(jk,apr);
}catch(Exception e){error=e.toString();}
return error;
}	


public String staffesslcheck(String fdate2,String tdate2,int jk)
{
int i=0;
 
			 acount=0;

try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("essl");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;

sql="select ltrim(str(day(attendancedate)))+'/'+ltrim(str(month(attendancedate)))+'/'+ltrim(str(year(attendancedate))),"
+"firstpunch,lastpunch,latecomingby,earlygoingby,duration,logrecords,daystatusid,workstatusid,"
+"logrecords,staffcode,name,ltrim(str(month(attendancedate)))+'/'+ltrim(str(day(attendancedate)))+'/'+ltrim(str(year(attendancedate))) from attendancelog,staff where staff.id=attendancelog.staffid  AND not (period1status='P' and Period2status='P') AND (AttendanceLog.DayStatusId IN (2, 5, 6, 7, 8)) "
+"and attendancedate between '"+fdate2+"' and '"+tdate2+"' and staffcode='"+ report[0][jk] +"' order by attendancedate";
rs = stmt.executeQuery(sql);

while(rs.next())
{
	for(i=1;i<=13;i++)
	{
		report1[i-1][acount]=rs.getString(i);
	}
	acount++;
}
//acount--;
rs.close();

stmt.close();
connMgr.freeConnection("essl",con);
error=staffcheckleave(jk,1);
}catch(Exception e){error=e.toString();}
return error;
}	


public String staffcheckleave(int jk,int apr)
{
String al="";
String ad="";
String ap="";
String ady="";
String hdy="";
String sphdy="";
String spreason="";

int ajk=0;
String ft="";
String tt="";
try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;
//fk=0;
for(int i=0;i<acount;i++)
{
report1[21][i]="Loss of Pay.";
hdy="";
sphdy="A";
spreason="";
ady="";
al="";
ad="";
ap="";
ajk=0;
ft="";
tt="";

/*
sql="select reason from attendance.dbo.holidaymaster where fdate='"+report1[0][i]+"' ";

rs = stmt.executeQuery(sql);

if(rs.next())
{
report1[21][i]=rs.getString(1);
report1[22][i]="Y";
rs.close();
}
*/

sql="select reason from leave_spl where staffid='"+report1[10][i]+"' and sdate='"+report1[0][i]+"' ";

rs = stmt.executeQuery(sql);

if(rs.next())
{
report1[21][i]=rs.getString(1);
report1[22][i]="Y";
}
else report1[22][i]="N";

rs.close();



sql="select flag from leave_holiday where hdate='"+report1[0][i]+"'";

rs = stmt.executeQuery(sql);

if(rs.next())
{
hdy=rs.getString(1);
report1[23][i]=hdy;
ajk=1;
}

rs.close();


sql="select flag,reason from leave_splholiday where hdate='"+report1[0][i]+"'";

rs = stmt.executeQuery(sql);

if(rs.next())
{
sphdy=rs.getString(1);
spreason=rs.getString(2);
report1[23][i]=sphdy;
ajk=2;
}

rs.close();


if(apr==1)
sql="select typename,ltrim(str(day(ldate)))+'/'+ltrim(str(month(ldate)))+'/'+ltrim(str(year(ldate))),leave_classwork.staffid,period,leave_classwork.days,hr1 "
+"from leave_type,leave_register,leave_classwork where leavetypeid=leaveyearid and leaveregisterid=leaveclassid and ldate='"+report1[0][i]+"' "
+" and princstatus='Y' and leave_classwork.staffid='"+report1[10][i]+"' order by ldate";
else
sql="select typename,ltrim(str(day(ldate)))+'/'+ltrim(str(month(ldate)))+'/'+ltrim(str(year(ldate))),leave_classwork.staffid,period,leave_classwork.days,hr1 "
+"from leave_type,leave_register,leave_classwork where leavetypeid=leaveyearid and leaveregisterid=leaveclassid and ldate='"+report1[0][i]+"' "
+" and leave_classwork.staffid='"+report1[10][i]+"' order by ldate";


rs = stmt.executeQuery(sql);


if(rs.next())
{
al=rs.getString(1);
ad=rs.getString(2);
ap=rs.getString(4);
ady=rs.getString(5);



if(report1[4][i].toString().trim().equals("폩"))
{
 if (Float.parseFloat(ady)==0.5) report1[21][i]="Halfday "+al;
 else report1[21][i]=al;
 }
 else if ((report1[4][i].toString().trim().equals("P")) && ((Integer.parseInt(report1[7][i].toString().trim())>0) || (Integer.parseInt(report1[8][i].toString().trim())>0)))
{
 if (Float.parseFloat(ady)==0.5) report[21][i]="Halfday "+al;
 else report[21][i]=al;
}
else 
{
 report1[21][i]="Loss of Pay";

}

//if ((Float.parseFloat(ady)==0.5) && (report1[5][i].toString().trim().equals("Lt"))) report1[21][i]="Halfday "+al;

 
if (Float.parseFloat(ady)==1) report1[21][i]="Fullday "+al;

}
rs.close();

if(apr==1)
sql="select fromtime,totime from leave_register where status='Y'  and princstatus='Y' and leaveyearid=1 and staffid='"+report1[10][i]+"' and leavedate='"+report1[0][i]+"'";
else
sql="select fromtime,totime from leave_register where status='Y'  and leaveyearid=1 and staffid='"+report1[10][i]+"' and leavedate='"+report1[0][i]+"'";

rs = stmt.executeQuery(sql);

if(rs.next())
{
ft=rs.getString(1);
tt=rs.getString(2);

//if((Integer.parseInt(report1[7][i].toString().trim())>0) || (Integer.parseInt(report1[8][i].toString().trim())>0))
if ((report1[4][i].toString().trim().equals("P")) && ((Integer.parseInt(report1[7][i].toString().trim())>0) || (Integer.parseInt(report1[8][i].toString().trim())>0)))

{
 report1[21][i]="Permission";
 }

}
rs.close();

if(report1[4][i].toString().trim().equals("폩"))
{
 if (ajk==1) report1[21][i]="Saturday Halfday";
 }

if(report1[4][i].toString().trim().equals("A"))
{
 if (ajk==2) report1[21][i]=spreason;
 }



if (report1[21][i].matches("Loss of Pay."))
{
fk++;
}

}
stmt.close();
connMgr.freeConnection("xavier",con);
error="success";
}catch(Exception e){error=("class:staffcheckleave-"+e.toString()+sql);}
return error; 
}



public void setoption(String option) {this.option = option;} public String getoption() {return this.option;}
public void setleaveyearid(int leaveyearid) {this.leaveyearid=leaveyearid;} public int getleaveyearid() {return this.leaveyearid;}
public void setfhours(String fhours) {this.fhours=fhours;} public String getfhours() {return this.fhours;}
public void setfminutes(String fminutes) {this.fminutes=fminutes;} public String getfminutes() {return this.fminutes;}
public void setftime(String ftime) {this.ftime=ftime;} public String getftime() {return this.ftime;}
public void setthours(String thours) {this.thours=thours;} public String getthours() {return this.thours;}
public void settminutes(String tminutes) {this.tminutes=tminutes;} public String gettminutes() {return this.tminutes;}
public void setttime(String ttime) {this.ttime=ttime;} public String getttime() {return this.ttime;}
public void setfdate(String fdate) {this.fdate=fdate;} public String getfdate() {return this.fdate;}
public void settdate(String tdate) {this.tdate=tdate;} public String gettdate() {return this.tdate;}
public void setstatus(String status) {this.status=status;} public String getstatus() {return this.status;}
public void setreason(String reason) {this.reason=reason;} public String getreason() {return this.reason;}
public void setstaffid(String staffid) {this.staffid=staffid;} public String getstaffid() {return this.staffid;}
public void setastaffid(String astaffid) {this.astaffid=astaffid;} public String getastaffid() {return this.astaffid;}
public void setahours(String ahours) {this.ahours=ahours;} public String getahours() {return this.ahours;}
public void setsubmitdate(String submitdate) {this.submitdate=submitdate;} public String getsubmitdate() {return this.submitdate;}
public void sethodapprovdate(String hodapprovdate) {this.hodapprovdate=hodapprovdate;} public String gethodapprovdate() {return this.hodapprovdate;}
public void setprincapprovdate(String princapprovdate) {this.princapprovdate=princapprovdate;} public String getprincapprovdate() {return this.princapprovdate;}
public void setcorresapprovdate(String corresapprovdate) {this.corresapprovdate=corresapprovdate;} public String getcorresapprovdate() {return this.corresapprovdate;}
public void setleavedate(String leavedate) {this.leavedate=leavedate;} public String getleavedate() {return this.leavedate;}
public void setcancelleddate(String cancelleddate) {this.cancelleddate=cancelleddate;} public String getcancelleddate() {return this.cancelleddate;}
public void setsanstatus(String sanstatus) {this.sanstatus=sanstatus;} public String getsanstatus() {return this.sanstatus;}
public void sethodstatus(String hodstatus) {this.hodstatus=hodstatus;} public String gethodstatus() {return this.hodstatus;}
public void setprincstatus(String princstatus) {this.princstatus=princstatus;} public String getprincstatus() {return this.princstatus;}
public void setcorresstatus(String corresstatus) {this.corresstatus=corresstatus;} public String getcorresstatus() {return this.corresstatus;}
public void setyear(int year) {this.year=year;} public int getyear() {return this.year;}
public void setdno(int dno) {this.dno=dno;} public int getdno() {return this.dno;}

public void setreport(String[][] report) {this.report=report;}  public String[][] getreport() {	return this.report;}
public void setreport2(String[][] report2) {this.report2=report2;}  public String[][] getreport2() {	return this.report2;}
public void setcount(int count) {this.count=count;} public int getcount(){	return this.count;}


}




	
	