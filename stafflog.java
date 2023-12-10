package sxcce;
import java.sql.*;
import java.util.StringTokenizer;
import com.jspsmart.upload.*;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.io.*;
import dd.DBConnectionManager;

public class stafflog
{
String rep[] = new String[1000];
String repin[] = new String[50];
String report[][] = new String[50][10000];
String report3[][] = new String[50][10000];
String report5[][] = new String[50][10000];

String report1[] = new String[1000];
String report2[] = new String[1000];
String fdate="";
String tdate="";

String d1="";
String m1="";
String y1="";
String str1="";
int count;
int count1;
int count2;
int cid;
String sql="";	
String quer="";	
String error="";			
String academicyear="";		

String date1="";	
String date2="";	

String sday="";

public String missedUpdate(int id)
{
try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;
					    PreparedStatement ps;
					    
                    sql="update attendance.dbo.missedatt set status=1 where id=?";
					ps=con.prepareStatement(sql);
					ps.setInt(1,id);
					int i = ps.executeUpdate();
					ps.close();
                    stmt.close();
					connMgr.freeConnection("xavier",con);
					error="success";
}catch(Exception e){error=(e.toString());}
return error;
}

public String missedidatt(int tid)
{
String sql="";
error="...";

try
{
DBConnectionManager connMgr;
connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
Statement stmt = con.createStatement();
ResultSet rs=null;
PreparedStatement ps;

count=0;

quer="select mdate,substring(('000'+ltrim(rtrim(str(day(mdate))))), len('000'+ltrim(rtrim(str(day(mdate)))))-1,2)+'/'+  substring(('000'+ltrim(rtrim(str(month(mdate))))), "
+" len('000'+ltrim(rtrim(str(month(mdate)))))-1,2)+'/'+ ltrim(rtrim(str(year(mdate)))),period,m.dno,m.semester,m.staffid,m.status,name,dname,sf,id,subid "
+" from attendance.dbo.missedatt m,staff s,department d where m.staffid=s.staffid and m.dno=d.dno and m.id="+tid+" order by m.staffid,mdate,m.dno,period";

rs=stmt.executeQuery(quer);
while(rs.next())
{
for(int j=1;j<=12;j++)
{
	report[j-1][count]=rs.getString(j);
}
count++;
}
rs.close();
stmt.close();
//error=quer;
connMgr.freeConnection("xavier",con);
}catch(Exception e){error=(e.toString());}
return error;	
}



public String missedstaffatt(String fd,String td, String staffid)
{
String sql="";
error="...";

try
{
DBConnectionManager connMgr;
connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
Statement stmt = con.createStatement();
ResultSet rs=null;
PreparedStatement ps;

count=0;
try
{
			StringTokenizer st1 = new StringTokenizer(fd,"/");
		    d1=st1.nextToken();
		    m1=st1.nextToken();
		    y1=st1.nextToken();
			date1=m1+"/"+d1+"/"+y1;

			StringTokenizer st2 = new StringTokenizer(td,"/");
		    d1=st2.nextToken();
		    m1=st2.nextToken();
		    y1=st2.nextToken();
			date2=m1+"/"+d1+"/"+y1;
} catch(Exception e){}			



count=0;

quer="select mdate,substring(('000'+ltrim(rtrim(str(day(mdate))))), len('000'+ltrim(rtrim(str(day(mdate)))))-1,2)+'/'+  substring(('000'+ltrim(rtrim(str(month(mdate))))), "
+" len('000'+ltrim(rtrim(str(month(mdate)))))-1,2)+'/'+ ltrim(rtrim(str(year(mdate)))),period,m.dno,m.semester,m.staffid,m.status,name,dname,sf,id,subid "
+" from attendance.dbo.missedatt m,staff s,department d where m.staffid=s.staffid and m.dno=d.dno and m.staffid='"+staffid+"'  and mdate between '"+date1+"' and '"+date2+"' order by m.staffid,mdate,m.dno,period";

rs=stmt.executeQuery(quer);
while(rs.next())
{
for(int j=1;j<=12;j++)
{
	report[j-1][count]=rs.getString(j);
}
count++;
}
rs.close();
for(int i=0;i<count;i++)
{
try{
quer="select subcode from attendance.dbo.log l where date='"+report[0][i]+"' and period="+report[2][i]+" and id="+report[11][i];
rs=stmt.executeQuery(quer);
if(rs.next())
	report[12][i]=rs.getString(1);
else	
	report[12][i]="-";

}catch(Exception e){error=e.toString();}
finally{rs.close();}

}



                    stmt.close();
					connMgr.freeConnection("xavier",con);
					}catch(Exception e){error=(e.toString());}
return error;	
}


public String missedatt(String fd,String td)
{
String sql="";
error="...";

try
{
DBConnectionManager connMgr;
connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
Statement stmt = con.createStatement();
ResultSet rs=null;
PreparedStatement ps;

count=0;
try
{
			StringTokenizer st1 = new StringTokenizer(fd,"/");
		    d1=st1.nextToken();
		    m1=st1.nextToken();
		    y1=st1.nextToken();
			date1=m1+"/"+d1+"/"+y1;

			StringTokenizer st2 = new StringTokenizer(td,"/");
		    d1=st2.nextToken();
		    m1=st2.nextToken();
		    y1=st2.nextToken();
			date2=m1+"/"+d1+"/"+y1;
} catch(Exception e){}			



count=0;

quer="select mdate,substring(('000'+ltrim(rtrim(str(day(mdate))))), len('000'+ltrim(rtrim(str(day(mdate)))))-1,2)+'/'+  substring(('000'+ltrim(rtrim(str(month(mdate))))), "
+" len('000'+ltrim(rtrim(str(month(mdate)))))-1,2)+'/'+ ltrim(rtrim(str(year(mdate)))),period,m.dno,m.semester,m.staffid,m.status,name,dname,sf,id,subid "
+" from attendance.dbo.missedatt m,staff s,department d where m.staffid=s.staffid and m.dno=d.dno and mdate between '"+date1+"' and '"+date2+"' order by m.staffid,mdate,m.dno,period";

rs=stmt.executeQuery(quer);
while(rs.next())
{
for(int j=1;j<=12;j++)
{
	report[j-1][count]=rs.getString(j);
}
count++;
}
rs.close();

for(int i=0;i<count;i++)
{
try{
quer="select subcode from attendance.dbo.log l where date='"+report[0][i]+"' and period="+report[2][i]+" and id="+report[11][i];
rs=stmt.executeQuery(quer);
if(rs.next())
	report[12][i]=rs.getString(1);
else	
	report[12][i]="-";

}catch(Exception e){error=e.toString();}
finally{rs.close();}

try{
quer="select isnull(count(*),0) from attendance.dbo.missedatt where staffid='"+report[5][i]+"'";
rs=stmt.executeQuery(quer);
if(rs.next())
	report[13][i]=rs.getString(1);
else	
	report[13][i]="0";

}catch(Exception e){error=e.toString();}
finally{rs.close();}


}

                    stmt.close();
					connMgr.freeConnection("xavier",con);
					}catch(Exception e){error=(e.toString());}
return error;	
}


    	
public String attnotent(String fd,String td,int oddeven)
{
int sd=1;
String sql="";
error="...";
String j1="";
int ajk=0;
String semin="(1,3,5,7)";

if(oddeven==1) semin="(1,3,5,7)";
else  semin="(2,4,6,8)";

semin="("+repin[1]+")";
try
{
DBConnectionManager connMgr;
connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
Statement stmt = con.createStatement();
ResultSet rs=null;
PreparedStatement ps;

count=0;
try
{
			StringTokenizer st1 = new StringTokenizer(fd,"/");
		    d1=st1.nextToken();
		    m1=st1.nextToken();
		    y1=st1.nextToken();
			date1=m1+"/"+d1+"/"+y1;

			StringTokenizer st2 = new StringTokenizer(td,"/");
		    d1=st2.nextToken();
		    m1=st2.nextToken();
		    y1=st2.nextToken();
			date2=m1+"/"+d1+"/"+y1;
} catch(Exception e){}			


SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
SimpleDateFormat sdf1 = new SimpleDateFormat("MM/dd/yyyy");

Calendar c = new GregorianCalendar();
try{     c.setTime(sdf.parse(fd));}catch(Exception e){} 
     
Calendar endCalendar = new GregorianCalendar();
try{     endCalendar.setTime(sdf.parse(td));}catch(Exception e){} 

endCalendar.add(Calendar.DATE, 1);
 
while (c.before(endCalendar)) {
		report[0][count] = sdf.format(c.getTime());  
		report[1][count] = sdf1.format(c.getTime());  
		report[2][count] = c.get(Calendar.DAY_OF_WEEK)+"";
		report[3][count] = c.get(Calendar.DAY_OF_WEEK)+"";
   		c.add(Calendar.DATE, 1);




if(report[2][count].equals("7"))
{
sday="";
try{
quer="select upper(substring(reason,1,3)) from attendance.dbo.holidaymaster where fdate in ('"+report[1][count]+"')";
rs=stmt.executeQuery(quer);
if(rs.next())
	sday=rs.getString(1);
sd=8;
if(sday.equals("MON"))	sd=1;
if(sday.equals("TUE"))	sd=2;
if(sday.equals("WED"))	sd=3;
if(sday.equals("THU"))	sd=4;
if(sday.equals("FRI"))	sd=5;
}catch(Exception e){error=e.toString();}
finally{rs.close();}

report[3][count] = sd+"";
}   		
else
{
report[3][count] = Integer.parseInt(report[3][count])-1+"";
try{
quer="select ltrim(rtrim(reason)) from attendance.dbo.holidaymaster where fdate in ('"+report[1][count]+"')  and noofperiods=0";
rs=stmt.executeQuery(quer);
if(rs.next())
{
	report[4][count]=rs.getString(1);
    report[3][count] = "8";
}
else
	report[4][count]="";

}catch(Exception e){error=e.toString();}
finally{rs.close();}

}  
   		count++;
} 

count1=0;

for(int i=0;i<count;i++)
{
try{
quer="select hour,t1.semester,sf,t1.subcode,name,t1.staffid,t1.dno,t1.id from ("
+" select subcode,hour,periods.id as id,staff.staffid,name,sf,department.dno,semester from subjecthandled,subjectidentify,periods,staff,department " 
+" where subjectidentify.dno=department.dno and staff.staffid=subjecthandled.staffid and subjecthandled.id=subjectidentify.id and  mainorsub='M' and "
+" subjecthandled.id=periods.id and subjecthandled.subcode=periods.subjectcode and "
+" day="+report[3][i]+" and  department.dno in ("+repin[0]+") and"
+" subjectidentify.semester in "+semin+" and subjectidentify.academicyear='"+repin[2]+"') as t1"
+" LEFT JOIN attendance.dbo.log t2 ON t1.id = t2.id and t2.date='"+report[1][i]+"' and t1.hour=t2.period "
+" left join "
+" (select dno,semester,(semester+1)/2 y from attendance.dbo.reopening where  academicyear in (select academicyear from academicyear) "
+" and '"+report[1][i]+"' between opening and closing and dno not in (7,30)) t3 on t3.semester=t1.semester and t3.dno=t1.dno "
+" WHERE t2.ID IS NULL and t3.dno is NOT NULL";

//+" WHERE t2.ID IS NULL";

rs=stmt.executeQuery(quer);
while(rs.next())
{
for(int j=1;j<=8;j++)
{
	report3[j][count1]=rs.getString(j);
}
	report3[0][count1]=report[0][i];
count1++;
}

}catch(Exception e){error=e.toString();}
finally{rs.close();}

}

stmt.close();
connMgr.freeConnection("xavier",con);
					}catch(Exception e){error=(e.toString());}
return error;	
}


public String attnotent(String fd,String td,String period)
{
int sd=1;
String sql="";
error="...";
String j1="";
int ajk=0;
String periodc="";
if(period.trim().equals("0"))
periodc="";
else
periodc="and hour in ("+period+") ";

try
{
DBConnectionManager connMgr;
connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
Statement stmt = con.createStatement();
ResultSet rs=null;
PreparedStatement ps;

count=0;
try
{
			StringTokenizer st1 = new StringTokenizer(fd,"/");
		    d1=st1.nextToken();
		    m1=st1.nextToken();
		    y1=st1.nextToken();
			date1=m1+"/"+d1+"/"+y1;

			StringTokenizer st2 = new StringTokenizer(td,"/");
		    d1=st2.nextToken();
		    m1=st2.nextToken();
		    y1=st2.nextToken();
			date2=m1+"/"+d1+"/"+y1;
} catch(Exception e){}			


SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
SimpleDateFormat sdf1 = new SimpleDateFormat("MM/dd/yyyy");

Calendar c = new GregorianCalendar();
try{     c.setTime(sdf.parse(fd));}catch(Exception e){} 
     
Calendar endCalendar = new GregorianCalendar();
try{     endCalendar.setTime(sdf.parse(td));}catch(Exception e){} 

endCalendar.add(Calendar.DATE, 1);
 
while (c.before(endCalendar)) {
		report[0][count] = sdf.format(c.getTime());  
		report[1][count] = sdf1.format(c.getTime());  
		report[2][count] = c.get(Calendar.DAY_OF_WEEK)+"";
		report[3][count] = c.get(Calendar.DAY_OF_WEEK)+"";
   		c.add(Calendar.DATE, 1);




if(report[2][count].equals("7"))
{
sday="";
try{
quer="select upper(substring(reason,1,3)) from attendance.dbo.holidaymaster where fdate in ('"+report[1][count]+"')";
rs=stmt.executeQuery(quer);
if(rs.next())
	sday=rs.getString(1);
sd=8;
if(sday.equals("MON"))	sd=1;
if(sday.equals("TUE"))	sd=2;
if(sday.equals("WED"))	sd=3;
if(sday.equals("THU"))	sd=4;
if(sday.equals("FRI"))	sd=5;
}catch(Exception e){error=e.toString();}
finally{rs.close();}

report[3][count] = sd+"";
}   		
else
{
report[3][count] = Integer.parseInt(report[3][count])-1+"";
try{
quer="select ltrim(rtrim(reason)) from attendance.dbo.holidaymaster where fdate in ('"+report[1][count]+"')  and noofperiods=0";
rs=stmt.executeQuery(quer);
if(rs.next())
{
	report[4][count]=rs.getString(1);
    report[3][count] = "8";
}
else
	report[4][count]="";

}catch(Exception e){error=e.toString();}
finally{rs.close();}

}  
   		count++;
} 

count1=0;

for(int i=0;i<count;i++)
{
try{
quer="select hour,t1.semester,sf,t1.subcode,name,t1.staffid,t1.dno,t1.id from ("
+" select subcode,hour,periods.id as id,staff.staffid,name,sf,department.dno,semester from subjecthandled,subjectidentify,periods,staff,department " 
+" where subjectidentify.dno=department.dno and staff.staffid=subjecthandled.staffid and subjecthandled.id=subjectidentify.id and  mainorsub='M' and "
+" subjecthandled.id=periods.id and subjecthandled.subcode=periods.subjectcode and "
+" day="+report[3][i]+" "+periodc 
+" and subjectidentify.academicyear  in (select academicyear from academicyear) ) as t1"
+" LEFT JOIN attendance.dbo.log t2 ON t1.id = t2.id and t2.date='"+report[1][i]+"' and t1.hour=t2.period "
+" left join "
+" (select dno,semester,(semester+1)/2 y from attendance.dbo.reopening where  academicyear in (select academicyear from academicyear) "
+" and '"+report[1][i]+"' between opening and closing and dno not in (7,30)) t3 on t3.semester=t1.semester and t3.dno=t1.dno "
+" WHERE t2.ID IS NULL and t3.dno is NOT NULL "
+" order by t1.staffid";


rs=stmt.executeQuery(quer);
while(rs.next())
{
for(int j=1;j<=8;j++)
{
	report3[j][count1]=rs.getString(j);
}
	report3[0][count1]=report[0][i];
count1++;
}

}catch(Exception e){error=e.toString();}
finally{rs.close();}

}

stmt.close();
connMgr.freeConnection("xavier",con);
					}catch(Exception e){error=(e.toString());}
return error;	
}

public String viewdates(String fd,String td,String staffid)
{
int sd=1;
String sql="";
error="...";
String j1="";
int ajk=0;
String semin="(1,3,5,7)";
try
{
DBConnectionManager connMgr;
connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
Statement stmt = con.createStatement();
ResultSet rs=null;
PreparedStatement ps;

count=0;
try
{
			StringTokenizer st1 = new StringTokenizer(fd,"/");
		    d1=st1.nextToken();
		    m1=st1.nextToken();
		    y1=st1.nextToken();
			date1=m1+"/"+d1+"/"+y1;

			StringTokenizer st2 = new StringTokenizer(td,"/");
		    d1=st2.nextToken();
		    m1=st2.nextToken();
		    y1=st2.nextToken();
			date2=m1+"/"+d1+"/"+y1;
} catch(Exception e){}			

if(Integer.parseInt(m1)>6) semin="(1,3,5,7)";
else  semin="(2,4,6,8)";

if(Integer.parseInt(m1)==12) semin="(2,4,6,8)";

SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
SimpleDateFormat sdf1 = new SimpleDateFormat("MM/dd/yyyy");

Calendar c = new GregorianCalendar();
try{     c.setTime(sdf.parse(fd));}catch(Exception e){} 
     
Calendar endCalendar = new GregorianCalendar();
try{     endCalendar.setTime(sdf.parse(td));}catch(Exception e){} 

endCalendar.add(Calendar.DATE, 1);
 
while (c.before(endCalendar)) {
		report[0][count] = sdf.format(c.getTime());  
		report[1][count] = sdf1.format(c.getTime());  
		report[2][count] = c.get(Calendar.DAY_OF_WEEK)+"";
		report[3][count] = c.get(Calendar.DAY_OF_WEEK)+"";
   		c.add(Calendar.DATE, 1);




if(report[2][count].equals("7"))
{
sday="";
try{
quer="select upper(substring(reason,1,3)) from attendance.dbo.holidaymaster where fdate in ('"+report[1][count]+"')";
rs=stmt.executeQuery(quer);
sd=8;
report[4][count]="";

if(rs.next())
	sday=rs.getString(1);
if(sday.equals("MON"))	sd=1;
if(sday.equals("TUE"))	sd=2;
if(sday.equals("WED"))	sd=3;
if(sday.equals("THU"))	sd=4;
if(sday.equals("FRI"))	sd=5;
}catch(Exception e){error=e.toString();}
finally{rs.close();}

report[3][count] = sd+"";
}   		
else
{
report[3][count] = Integer.parseInt(report[3][count])-1+"";
try{
quer="select ltrim(rtrim(reason)) from attendance.dbo.holidaymaster where fdate in ('"+report[1][count]+"')  and noofperiods=0";
rs=stmt.executeQuery(quer);
if(rs.next())
{
	report[4][count]=rs.getString(1);
    report[3][count] = "8";
}
else
	report[4][count]="";

}catch(Exception e){error=e.toString();}
finally{rs.close();}

}  
   		count++;
} 

for(int i=0;i<count;i++)
{
for(int j=1;j<=8;j++)
{
	report[(10+j)][i]="";
	report[(20+j)][i]="0";
}
try{

quer="select subcode,hour,periods.id,((semester+1)/2),dno from subjecthandled,subjectidentify,periods "
+" where subjecthandled.id=subjectidentify.id and staffid='"+staffid+"' and  mainorsub='M' and "
+" subjecthandled.id=periods.id and subjecthandled.subcode=periods.subjectcode and "
+" day="+report[3][i]+" and"
+" subjectidentify.semester in "+semin+" and subjectidentify.academicyear in(select academicyear from academicyear)";

rs=stmt.executeQuery(quer);
while(rs.next())
{
	j1=rs.getString(1);
	ajk=rs.getInt(2);
	report[(10+ajk)][i]=j1;
	report[(20+ajk)][i]=rs.getInt(3)+"";
	report[(30+ajk)][i]=rs.getInt(4)+"";
	report[(40+ajk)][i]=rs.getInt(5)+"";
}

}catch(Exception e){error=e.toString();}
finally{rs.close();}

}

for(int i=0;i<count;i++)
{
for(int j=1;j<=8;j++)
{
	report3[(10+j)][i]="0";
if(Integer.parseInt(report[(20+j)][i])>0 )
{
	
try{
quer="select subcode,upper(staffid) from attendance.dbo.log where date='"+report[1][i]+"' and id="+report[(20+j)][i]+" and period="+j;
rs=stmt.executeQuery(quer);
if(rs.next())
{
	report3[(10+j)][i]=rs.getString(1)+"<br>";
    j1=rs.getString(2);
if(j1.trim().equals(staffid.trim().toUpperCase()))
	report3[(10+j)][i]+=j1+"";
else
	report3[(10+j)][i]+="<font color=red>"+j1+"</font>";
}
else
	report3[(10+j)][i]="1";
}catch(Exception e){error=e.toString();}
finally{rs.close();}
}
}
}
                    stmt.close();
					connMgr.freeConnection("xavier",con);
					}catch(Exception e){error=(e.toString());}
return error;	
}

public String StaffLeaveDetails(String fd,String td,String staffid)
{
	count=0;
	int i=0;
try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;
try
{
			StringTokenizer st1 = new StringTokenizer(fd,"/");
		    d1=st1.nextToken();
		    m1=st1.nextToken();
		    y1=st1.nextToken();
			date1=m1+"/"+d1+"/"+y1;

			StringTokenizer st2 = new StringTokenizer(td,"/");
		    d1=st2.nextToken();
		    m1=st2.nextToken();
		    y1=st2.nextToken();
			date2=m1+"/"+d1+"/"+y1;
}
catch(Exception e){}			

sql="select 'No.of Days Present',isnull(sum(present),0) from etimetracklite1.dbo.vw_AttendanceLogs where employeecode='"+staffid+"' and attendancedate between '"+date1+"' and '"+date2+"'";
rs = stmt.executeQuery(sql);
if(rs.next())
{
	for(i=1;i<=2;i++)
	{
		report[i-1][count]=rs.getString(i);
	}
	count++;
}
rs.close();

sql="select max(typename) t,isnull(sum(lc.days),0) lop from leave_classwork lc,leave_register,leave_type lr where leavetypeid=leaveyearid and leaveclassid=leaveregisterid and lc.staffid='"+staffid+"' and ldate between '"+date1+"' and '"+date2+"' group by leaveyearid order by t";
rs = stmt.executeQuery(sql);
while(rs.next())
{
	for(i=1;i<=2;i++)
	{
		report[i-1][count]=rs.getString(i);
	}
	count++;
}
rs.close();
stmt.close();					    
					connMgr.freeConnection("xavier",con);
					error="success";
					}catch(Exception e){error=(e.toString());
					}
					
		return error;
}


public String StaffLogDetails(String staffid, String accyear, String oddeven)
{
	count=0;
	int i=0;
	String semin="(1,3,5,7)";

if(oddeven.equals("ODD")) 
	semin="(1,3,5,7)";
else
	semin="(2,4,6,8)";
try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;
					    
sql="select si.id,si.dno,semester,academicyear,subcode,subjectname,abbr,theoryorpractical,sf from subjectidentify si,subjecthandled sh,syllabus sy,department dp where dp.dno=si.dno and si.id=sh.id and sy.id=si.id and sy.subjectcode=sh.subcode and staffid='"+staffid+"' and mainorsub='M' and academicyear='"+accyear+"' and semester in "+semin;

rs = stmt.executeQuery(sql);
while(rs.next())
{
	for(i=1;i<=9;i++)
	{
		report[i-1][count]=rs.getString(i);
	}
	count++;
}
rs.close();

for(i=0;i<count;i++)
{
try
{

sql="select substring(('000'+ltrim(rtrim(str(day(opening))))), len('000'+ltrim(rtrim(str(day(opening)))))-1,2)+'/'+  substring(('000'+ltrim(rtrim(str(month(opening))))), "
+" len('000'+ltrim(rtrim(str(month(opening)))))-1,2)+'/'+ ltrim(rtrim(str(year(opening)))), "
+" substring(('000'+ltrim(rtrim(str(day(closing))))), len('000'+ltrim(rtrim(str(day(closing)))))-1,2)+'/'+  substring(('000'+ltrim(rtrim(str(month(closing))))), "
+" len('000'+ltrim(rtrim(str(month(closing)))))-1,2)+'/'+ ltrim(rtrim(str(year(closing)))), "
+" CONVERT(VARCHAR(10),   opening, 101),CONVERT(VARCHAR(10),   closing, 101) "
+" from attendance.dbo.reopening where academicyear='"+accyear+"' and dno="+report[1][i]+" and semester="+report[2][i];
rs = stmt.executeQuery(sql);
if(rs.next())
{
		report[10][i]=rs.getString(1);
		report[11][i]=rs.getString(2);
		report[12][i]=rs.getString(3);
		report[13][i]=rs.getString(4);
}
rs.close();

sql="select isnull(count(*),0) from attendance.dbo.log where subcode='"+report[4][i]+"' and id="+report[0][i]+" and date between '"+report[12][i]+"' and '"+report[13][i]+"'";

rs = stmt.executeQuery(sql);
if(rs.next())
{
		report[14][i]=rs.getString(1);
}
rs.close();

sql="select count(*)*15 from periods where id="+report[0][i]+" and subjectcode='"+report[4][i]+"'";

rs = stmt.executeQuery(sql);
if(rs.next())
{
		report[15][i]=rs.getString(1);
}
rs.close();
}
catch(Exception e){}			

}
stmt.close();					    
					connMgr.freeConnection("xavier",con);
					error="success";
					}catch(Exception e){error=(e.toString());
					}
					
		return error;
}

public String StaffSubLog(String fd,String td,String staffid,String subcode,String sid)
{
	count=0;
	int i=0;
try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;
try
{
			StringTokenizer st1 = new StringTokenizer(fd,"/");
		    d1=st1.nextToken();
		    m1=st1.nextToken();
		    y1=st1.nextToken();
			date1=m1+"/"+d1+"/"+y1;

			StringTokenizer st2 = new StringTokenizer(td,"/");
		    d1=st2.nextToken();
		    m1=st2.nextToken();
		    y1=st2.nextToken();
			date2=m1+"/"+d1+"/"+y1;
}
catch(Exception e){}			


sql="select substring(('000'+ltrim(rtrim(str(day(date))))), len('000'+ltrim(rtrim(str(day(date)))))-1,2)+'/'+  substring(('000'+ltrim(rtrim(str(month(date))))), "
+" len('000'+ltrim(rtrim(str(month(date)))))-1,2)+'/'+ ltrim(rtrim(str(year(date)))), "
+" period,subcode,topic,staffid  from attendance.dbo.log where subcode='"+subcode+"' and id="+sid+" and date between '"+date1+"' and '"+date2+"'";


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
					connMgr.freeConnection("xavier",con);
					error="success";
					}catch(Exception e){error=(e.toString());
					}
					
		return error;
}


public String StaffLogSelect(String dno1, String accyear, String oddeven)
{
	count=0;
	int i=0;
	String semin="(1,3,5,7)";

if(oddeven.equals("ODD")) 
	semin="(1,3,5,7)";
else
	semin="(2,4,6,8)";
try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;
					    
sql="select si.id,si.dno,semester,academicyear,subcode,subjectname,abbr,theoryorpractical,sf,sh.staffid,name from subjectidentify si,subjecthandled sh,syllabus sy,department dp,staff "
+" where staff.staffid=sh.staffid and dp.dno=si.dno and si.id=sh.id and sy.id=si.id and sy.subjectcode=sh.subcode and staff.dno="+dno1+" and mainorsub='M' and academicyear='"+accyear+"' and semester in  "+semin+" order by sh.staffid";

rs = stmt.executeQuery(sql);
while(rs.next())
{
	for(i=1;i<=11;i++)
	{
		report[i-1][count]=rs.getString(i);
	}
	count++;
}
rs.close();

for(i=0;i<count;i++)
{

sql="select substring(('000'+ltrim(rtrim(str(day(opening))))), len('000'+ltrim(rtrim(str(day(opening)))))-1,2)+'/'+  substring(('000'+ltrim(rtrim(str(month(opening))))), "
+" len('000'+ltrim(rtrim(str(month(opening)))))-1,2)+'/'+ ltrim(rtrim(str(year(opening)))), "
+" substring(('000'+ltrim(rtrim(str(day(closing))))), len('000'+ltrim(rtrim(str(day(closing)))))-1,2)+'/'+  substring(('000'+ltrim(rtrim(str(month(closing))))), "
+" len('000'+ltrim(rtrim(str(month(closing)))))-1,2)+'/'+ ltrim(rtrim(str(year(closing)))), "
+" CONVERT(VARCHAR(10),   opening, 101),CONVERT(VARCHAR(10),   closing, 101) "
+" from attendance.dbo.reopening where academicyear='"+accyear+"' and dno="+report[1][i]+" and semester="+report[2][i];
rs = stmt.executeQuery(sql);
if(rs.next())
{
		report[20][i]=rs.getString(1);
		report[21][i]=rs.getString(2);
		report[22][i]=rs.getString(3);
		report[23][i]=rs.getString(4);
}
rs.close();

sql="select isnull(count(*),0) from attendance.dbo.log where subcode='"+report[4][i]+"' and id="+report[0][i]+" and date between '"+report[22][i]+"' and '"+report[23][i]+"'";

rs = stmt.executeQuery(sql);
if(rs.next())
{
		report[24][i]=rs.getString(1);
}
rs.close();

}
stmt.close();					    
					connMgr.freeConnection("xavier",con);
					error="success";
					}catch(Exception e){error=(e.toString());
					}
					
		return error;
}

public String StaffDeptLeaveDetails(String fd,String td,String dno1)
{
	count=0;
	int i=0;
try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;
try
{
			StringTokenizer st1 = new StringTokenizer(fd,"/");
		    d1=st1.nextToken();
		    m1=st1.nextToken();
		    y1=st1.nextToken();
			date1=m1+"/"+d1+"/"+y1;

			StringTokenizer st2 = new StringTokenizer(td,"/");
		    d1=st2.nextToken();
		    m1=st2.nextToken();
		    y1=st2.nextToken();
			date2=m1+"/"+d1+"/"+y1;
}
catch(Exception e){}			

sql="select at.employeecode ec,max(name),max(st.designation) des,max(cname)'No.of Days Present',isnull(sum(present),0) from etimetracklite1.dbo.vw_AttendanceLogs at,staff st,staffdesignation sd where at.employeecode=st.staffid "
+" and active=1 and dno="+dno1+" and sd.slno=st.designation and attendancedate between '"+date1+"' and '"+date2+"' group by at.employeecode order by des,ec";

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

for(i=0;i<count;i++)
{
for(int j=2;j<=10;j++)
{
sql="select isnull(sum(lc.days),0) lop from leave_classwork lc,leave_register "
+" where leaveyearid="+j+" and leaveclassid=leaveregisterid and lc.staffid='"+report[0][i]+"' and ldate between '"+date1+"' and '"+date2+"'";
rs = stmt.executeQuery(sql);
if(rs.next())
{
		report[10+j][i]=rs.getString(1);
}
rs.close();
if(report[10+j][i].equals("0.0")) report[10+j][i]="";
}
}

stmt.close();					    
					connMgr.freeConnection("xavier",con);
					error="success";
					}catch(Exception e){error=(e.toString());
					}
					
		return error;
}


public String StaffSelect(String dno1, String category)
{
	count=0;
	int i=0;
String sql1="";
String sql2="";
	if(dno1.equals("0")) sql1= " "; else sql1=" and dno in ("+dno1+") ";
	if(category.equals("All")) sql2= " "; else sql2=" and category in ('"+category+"') ";
try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;

sql="select staffid,name,cname,cell,email from staff,staffdesignation where slno=designation and active=1 "+sql1+sql2+" order by designation,staffid";

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
					connMgr.freeConnection("xavier",con);
					error="success";
					}catch(Exception e){error=(e.toString()+sql);
					}
					
		return error;
}


public String StaffProfile(String staffid)
{
	count=0;
	int i=0;
try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;

sql="select staffid,name,cname,sex,substring(('000'+ltrim(rtrim(str(day(birthdate))))), len('000'+ltrim(rtrim(str(day(birthdate)))))-1,2)+'/'+  substring(('000'+ltrim(rtrim(str(month(birthdate))))),len('000'+ltrim(rtrim(str(month(birthdate)))))-1,2)+'/'+ ltrim(rtrim(str(year(birthdate)))), "
+" rtrim(basicqual),desiredbranch,cell,email, "
+" (DATEDIFF(MONTH, appdate, GETDATE())+(enggexp * 12)+enggexpmonth)/12, (DATEDIFF(MONTH, appdate, GETDATE())+enggexpmonth) % 12 , "
+" otherexp,otherexpmonth,rtrim(desiredqual),rtrim(desiredquala),rtrim(researchqual),rtrim(researchquala) from staff,staffdesignation where slno=designation and staffid='"+staffid+"'";

rs = stmt.executeQuery(sql);
if(rs.next())
{
	for(i=1;i<=17;i++)
	{
		rep[i-1]=rs.getString(i);
	}
}
rs.close();

if(rep[13].equals("0")) 
    if(rep[14].equals("0")) 	
   	  rep[5]=rep[5];
   	else
      rep[5]=rep[5]+", "+rep[14];
else
	rep[5]=rep[5]+", "+rep[13];

if(rep[15].equals("0")) 
    if(rep[16].equals("0")) 	
   	  rep[5]=rep[5];
   	else
      rep[5]=rep[5]+", "+rep[16];
else
	rep[5]=rep[5]+", "+rep[15];

if(rep[9].equals("0"))      rep[9]=" "; else	rep[9]=rep[9]+" years, ";
if(rep[10].equals("0"))      rep[10]=" "; else 	rep[10]=rep[10]+" months";

if(rep[11].equals("0"))      rep[11]=" "; else	rep[11]=rep[11]+" years, ";
if(rep[12].equals("0"))      rep[12]=" "; else 	rep[12]=rep[12]+" months";


sql="select isnull(aicteno,'-') from staffids where staffid='"+staffid+"'";
rs = stmt.executeQuery(sql);
rep[20]="";
if(rs.next())	rep[20]=rs.getString(1);
rs.close();



sql="select distinct subjectname from subjecthandled,syllabus where syllabus.id=subjecthandled.id and subcode=subjectcode and staffid='"+staffid+"' and mainorsub='M' and theoryorpractical in(1,4,5) and syllabus.id>792";
rs = stmt.executeQuery(sql);
rep[21]="";
while(rs.next())
{
		rep[21]=rep[21]+rs.getString(1)+", ";
}
rs.close();

sql="select count(*) from apijournal where staffid='"+staffid+"' and intornat='International'";
rs = stmt.executeQuery(sql);
rep[22]="";
if(rs.next())	rep[22]=rs.getString(1);
rs.close();

sql="select count(*) from apijournal where staffid='"+staffid+"' and intornat='National'";
rs = stmt.executeQuery(sql);
rep[23]="";
if(rs.next())	rep[23]=rs.getString(1);
rs.close();

sql="select count(*) from apiconference where staffid='"+staffid+"' and intornat='International'";
rs = stmt.executeQuery(sql);
rep[24]="";
if(rs.next())	rep[24]=rs.getString(1);
rs.close();

sql="select count(*) from apiconference where staffid='"+staffid+"' and intornat='National'";
rs = stmt.executeQuery(sql);
rep[25]="";
if(rs.next())	rep[25]=rs.getString(1);
rs.close();

sql="select count(*) from apiactivities where staffid='"+staffid+"' and ugpg='PG'";
rs = stmt.executeQuery(sql);
rep[26]="";
if(rs.next())	rep[26]=rs.getString(1);
rs.close();

sql="select isnull(sum(scholarsdoing+scholarscompleted),0) from apiresearch where staffid='"+staffid+"'";
rs = stmt.executeQuery(sql);
rep[27]="";
if(rs.next())	rep[27]=rs.getString(1);
rs.close();

sql="select sum(c1) from (select count(*) as c1 from apiconsultancy  where staffid='"+staffid+"' union "
+" select count(*) as c1 from apifunded  where staffid='"+staffid+"' and status not in('Applied')) as q";
rs = stmt.executeQuery(sql);
rep[28]="";
if(rs.next())	rep[28]=rs.getString(1);
rs.close();

sql="select count(*) from apipatent where staffid='"+staffid+"'";
rs = stmt.executeQuery(sql);
rep[29]="";
if(rs.next())	rep[29]=rs.getString(1);
rs.close();

sql="select staff.staffid,type,title,authors,affiliation,authortype,journalname,publisher,intornat, "
+" issn,doi,yearofpub,volissuepage,indexing,impact,snip,sjr,citationcount,interdept,fname1 from apijournal "
+" ,staff where staff.staffid=apijournal.staffid and staff.staffid='"+staffid+"' order by yearofpub desc";

rs = stmt.executeQuery(sql);
count=0;
while(rs.next())
{
	for(i=1;i<=20;i++)
	{
		report[i-1][count]=rs.getString(i);
	}
	count++;
}
rs.close();

sql="select staff.staffid,venue,title,authors,affiliation,authortype,conferencename,intornat,issn,doi,yearofpub,volissuepage,indexing,0,0,0,citationcount, "
+" interdept,fname1 from apiconference,staff where staff.staffid=apiconference.staffid and staff.staffid='"+staffid+"' order by  yearofpub desc";

rs = stmt.executeQuery(sql);
count1=0;
while(rs.next())
{
	for(i=1;i<=19;i++)
	{
		report3[i-1][count1]=rs.getString(i);
	}
	count1++;
}
rs.close();

sql="select staff.staffid,name,apibooks.category,type,title,authors,authortype,affiliation,pages,isbn,language, "
+" publisher,country,monthofpub,yearofpub,edition,0,0,0,fname1 from apibooks,staff where staff.staffid=apibooks.staffid and staff.staffid='"+staffid+"' order by apibooks.category, yearofpub desc";

rs = stmt.executeQuery(sql);
count2=0;
while(rs.next())
{
	for(i=1;i<=20;i++)
	{
		report5[i-1][count2]=rs.getString(i);
	}
	count2++;
}
rs.close();


stmt.close();					    
					connMgr.freeConnection("xavier",con);
					error="success";
					}catch(Exception e){error=(e.toString()+sql);
					}
return error;
}


public String BPASPersonal(String staffid)
{
	count=0;
	int i=0;
try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;

sql="select staffid,name,dname,substring(('000'+ltrim(rtrim(str(day(birthdate))))), len('000'+ltrim(rtrim(str(day(birthdate)))))-1,2)+'/'+  substring(('000'+ltrim(rtrim(str(month(birthdate))))),len('000'+ltrim(rtrim(str(month(birthdate)))))-1,2)+'/'+ ltrim(rtrim(str(year(birthdate)))), "
+" sex,maritalstatus,cell,email,cname from staff,staffdesignation,department where slno=designation and staff.dno=department.dno and staffid='"+staffid+"'";

rs = stmt.executeQuery(sql);
if(rs.next())
{
	for(i=1;i<=9;i++)
	{
		rep[i-1]=rs.getString(i);
	}
}
rs.close();

sql="select cname from salary,salarycategory where  staffid='"+staffid+"' and scale=salarycategory.slno and saldate=(select max(saldate) from salary,salarycategory where  staffid='"+staffid+"' and scale=salarycategory.slno)";

rs = stmt.executeQuery(sql);
if(rs.next())
{
		rep[9]=rs.getString(1);
}
rs.close();


stmt.close();					    
					connMgr.freeConnection("xavier",con);
					error="success";
					}catch(Exception e){error=(e.toString()+sql);
					}
return error;
}


public void setrep(String[] rep) {this.rep=rep;}  public String[] getrep() {	return this.rep;}
public void setrepin(String[] repin) {this.repin=repin;}  public String[] getrepin() {	return this.repin;}
public void setreport(String[][] report) {this.report=report;}  public String[][] getreport() {	return this.report;}
public void setreport3(String[][] report3) {this.report3=report3;}  public String[][] getreport3() {	return this.report3;}
public void setreport5(String[][] report5) {this.report5=report5;}  public String[][] getreport5() {	return this.report5;}
public void setreport1(String[] report1) {this.report1=report1;}  public String[] getreport1() {	return this.report1;}
public void setreport2(String[] report2) {this.report2=report2;}  public String[] getreport2() {	return this.report2;}
public void setcount(int count) {this.count=count;} public int getcount(){	return this.count;}
public void setcount1(int count1) {this.count1=count1;} public int getcount1(){	return this.count1;}
public void setcount2(int count2) {this.count2=count2;} public int getcount2(){	return this.count2;}
public void setacademicyear(String academicyear) { this.academicyear = academicyear; } public String getacademicyear() { return this.academicyear; } 



}




	
	