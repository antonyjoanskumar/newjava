package sxcce;
import java.sql.*;
import java.util.StringTokenizer;
import com.jspsmart.upload.*;
import java.util.Date;
import java.io.*;
import java.text.*;
import dd.DBConnectionManager;

public class attanalysis
	{
				String rep[] = new String[1000];
				String repin[] = new String[30];
				String report[][] = new String[30][10000];
				String report1[] = new String[1000];
				String report2[][] = new String[30][10000];
                String fdate="";
                String tdate="";
                String pname="";
                String fname="";
String d1="";
String m1="";
String y1="";
String str1="";
int count;
int dcount=0;
int cid;
int tid;
String sql="";	
String error="";			
String academicyear="";		

String date1="";	
String date2="";	
String date3="";	
String date4="";	

int i1=0;
int i2=0;
int i3=0;
int i4=0;
int i5=0;


float f1=0;
float f2=0;
float f3=0;

public String analysisrep(int min,int max, int order)
{
count=0;
dcount=0;

double per=0;
int s=0;
int tothrs=0;
int yr=1;
String sem="1,3,5,7";
if(repin[2].equals("1"))
{ s=0;
 sem="1,3,5,7";
}
 else
{ s=1;
 sem="2,4,6,8";
} 	

try
{
			StringTokenizer st1 = new StringTokenizer(repin[3],"/");
		    d1=st1.nextToken();
		    m1=st1.nextToken();
		    y1=st1.nextToken();
			date1=m1+"/"+d1+"/"+y1;

			StringTokenizer st2 = new StringTokenizer(repin[4],"/");
		    d1=st2.nextToken();
		    m1=st2.nextToken();
		    y1=st2.nextToken();
			date2=m1+"/"+d1+"/"+y1;

}
catch(Exception e){}			

try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;
sql="select * from department where dno in("+repin[0]+")  order by dno";

rs = stmt.executeQuery(sql);

while(rs.next())
{
	for(int i=1;i<=4;i++)
	{
		report2[i-1][dcount]=rs.getString(i);
	}
	dcount++;
}
rs.close();


for(int i=0;i<dcount;i++)
{
	for(int j=1;j<=7;j+=2)
	{
yr=(j+1)/2;
sql="select count(*) from attendance.dbo.log b where date between  '"+date1+"' and '"+date2+"' and "
+"id in(select id from subjectidentify where semester="+(j+s)+" and dno="+report2[0][i]+" and academicyear='"+repin[1]+"')";
rs = stmt.executeQuery(sql);
if(rs.next()) tothrs=rs.getInt(1); 
rs.close();		

sql="select t1,t2,t3,isnull(t4,0) from (select stud.rollno t1,ltrim(rtrim(registerno)) t2,ltrim(rtrim(stud.name)) t3 from stud,registerno,student "
+"where student.rollno=stud.rollno and stud.rollno=registerno.rollno and year="+yr+" and department="+report2[0][i]+" and academicyear='"+repin[1]+"' and student.active=1 ) q1 "
+"left outer join (select count(*) t4,max(rollno) t5 from attendance.dbo.attendance a,attendance.dbo.log b where a.date=b.date  "
+"and a.date between  '"+date1+"' and '"+date2+"' and hours like '%'+ltrim(rtrim(str(period)))+'%' and id in (select id from subjectidentify "
+"where semester="+(j+s)+" and dno="+report2[0][i]+" and academicyear='"+repin[1]+"') group by rollno) q2 on q1.t1=q2.t5 order by t1";

rs = stmt.executeQuery(sql);
while(rs.next())
{

report[2][count]=rs.getString(1);
report[11][count]=rs.getString(2);
report[3][count]=rs.getString(3);
per=Math.round(((((tothrs-rs.getInt(4)*1.0)/(tothrs*1.0))*1.0)*10000.0))/100.0;

if((per>=min) && (per<=max))
{
report[0][count]=report2[3][i];
report[1][count]=yr+"";
report[4][count]=per+"";
count++;

}

}
rs.close();


	}
}
stmt.close();
connMgr.freeConnection("xavier",con);
error= "success"; 
}catch(Exception e){error=(e.toString()+sql);}
return error; 
}

public String attallperrep()
{
count=0;
dcount=0;

double per=0;
int s=0;
int tothrs=0;
int totstud=0;
int yr=1;
String sem="1,3,5,7";
if(repin[2].equals("1"))
{ s=0;
 sem="1,3,5,7";
}
 else
{ s=1;
 sem="2,4,6,8";
} 	

sem=repin[5];
try
{
			StringTokenizer st1 = new StringTokenizer(repin[3],"/");
		    d1=st1.nextToken();
		    m1=st1.nextToken();
		    y1=st1.nextToken();
			date1=m1+"/"+d1+"/"+y1;

			StringTokenizer st2 = new StringTokenizer(repin[4],"/");
		    d1=st2.nextToken();
		    m1=st2.nextToken();
		    y1=st2.nextToken();
			date2=m1+"/"+d1+"/"+y1;

}
catch(Exception e){}			

try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;
sql="select max(s.id),max(s.dno),(semester+1)/2,max(dname),max(semester),count(*) from attendance.dbo.log l,subjectidentify s,department d where l.id=s.id and s.dno=d.dno "
+" and date between  '"+date1+"' and '"+date2+"' and semester in ("+sem+") and s.dno in ("+repin[0]+") and academicyear='"+repin[1]+"' "
+" group by s.dno,semester order by s.dno,semester";

rs = stmt.executeQuery(sql);

while(rs.next())
{
	for(int i=1;i<=6;i++)
	{
		report[i-1][count]=rs.getString(i);
	}
	count++;
}
rs.close();

for(int i=0;i<count;i++)
{
tothrs=Integer.parseInt(report[5][i]);

sql="select count(*) from stud where department="+report[1][i]+" and year="+report[2][i]+" and academicyear='"+repin[1]+"'";
rs = stmt.executeQuery(sql);
if(rs.next()) totstud=rs.getInt(1); 
rs.close();		

sql="select count(*) from attendance.dbo.attendance a,attendance.dbo.log b where a.date=b.date  "
+" and a.date between  '"+date1+"' and '"+date2+"' and hours like '%'+ltrim(rtrim(str(period)))+'%' and id="+report[0][i]
+" and rollno in(select rollno from stud where department="+report[1][i]+" and year="+report[2][i]+" and academicyear='"+repin[1]+"')";

rs = stmt.executeQuery(sql);
if(rs.next())
{
	tothrs=tothrs*totstud;
per=Math.round(((((tothrs-rs.getInt(1)*1.0)/(tothrs*1.0))*1.0)*10000.0))/100.0;

String str1 = "123.00";
DecimalFormat df = new DecimalFormat("0.00");
df.setMaximumFractionDigits(2);
str1 = df.format(per);


report[6][i]=str1;
report[7][i]=totstud+"";

}
rs.close();

}
stmt.close();
connMgr.freeConnection("xavier",con);
error= "success"; 
}catch(Exception e){error=(e.toString()+sql);}
return error; 
}


public String mentorattrep(int min,int max, String staffid)
{
count=0;
dcount=0;

double per=0;
int s=0;
int tothrs=0;
int yr=1;
String sem="1,3,5,7";
if(repin[2].equals("1"))
{ s=0;
 sem="1,3,5,7";
}
 else
{ s=1;
 sem="2,4,6,8";
} 	

try
{
			StringTokenizer st1 = new StringTokenizer(repin[3],"/");
		    d1=st1.nextToken();
		    m1=st1.nextToken();
		    y1=st1.nextToken();
			date1=m1+"/"+d1+"/"+y1;

			StringTokenizer st2 = new StringTokenizer(repin[4],"/");
		    d1=st2.nextToken();
		    m1=st2.nextToken();
		    y1=st2.nextToken();
			date2=m1+"/"+d1+"/"+y1;

}
catch(Exception e){}			

try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;
sql="select * from department where dno in("+repin[0]+")  order by dno";

rs = stmt.executeQuery(sql);

while(rs.next())
{
	for(int i=1;i<=4;i++)
	{
		report2[i-1][dcount]=rs.getString(i);
	}
	dcount++;
}
rs.close();


for(int i=0;i<dcount;i++)
{
	for(int j=1;j<=7;j+=2)
	{
yr=(j+1)/2;
sql="select count(*) from attendance.dbo.log b where date between  '"+date1+"' and '"+date2+"' and "
+"id in(select id from subjectidentify where semester="+(j+s)+" and dno="+report2[0][i]+" and academicyear='"+repin[1]+"')";
rs = stmt.executeQuery(sql);
if(rs.next()) tothrs=rs.getInt(1); 
rs.close();		

sql="select t1,isnull(t2,0) t2,t3,isnull(t4,0) tt4 from (select stud.rollno t1,ltrim(rtrim(stud.name)) t3 from stud,student "
+" where student.rollno in(select rollno from tutor where academicyear='"+repin[1]+"' and staffid='"+staffid+"') "
+" and student.rollno=stud.rollno and year="+yr+" and department="+report2[0][i]+" and academicyear='"+repin[1]+"' and student.active=1 ) q1 "
+" left outer join (select rollno r1,ltrim(rtrim(registerno)) t2 from registerno) q3 on q1.t1=q3.r1 "
+" left outer join (select count(*) t4,max(rollno) t5 from attendance.dbo.attendance a,attendance.dbo.log b where a.date=b.date  "
+" and a.date between  '"+date1+"' and '"+date2+"' and hours like '%'+ltrim(rtrim(str(period)))+'%' and id in (select id from subjectidentify "
+" where semester="+(j+s)+" and dno="+report2[0][i]+" and academicyear='"+repin[1]+"') group by rollno) q2 on q1.t1=q2.t5 order by tt4 desc";


/*
 *select t1,t2,t3,isnull(t4,0) tt4 from (select stud.rollno t1,ltrim(rtrim(registerno)) t2,ltrim(rtrim(stud.name)) t3 from stud,registerno,student "
+"where student.rollno in(select rollno from tutor where academicyear='"+repin[1]+"' and staffid='"+staffid+"') "
+" and student.rollno=stud.rollno and stud.rollno=registerno.rollno and year="+yr+" and department="+report2[0][i]+" and academicyear='"+repin[1]+"' and student.active=1 ) q1 "
+"left outer join (select count(*) t4,max(rollno) t5 from attendance.dbo.attendance a,attendance.dbo.log b where a.date=b.date  "
+"and a.date between  '"+date1+"' and '"+date2+"' and hours like '%'+ltrim(rtrim(str(period)))+'%' and id in (select id from subjectidentify "
+"where semester="+(j+s)+" and dno="+report2[0][i]+" and academicyear='"+repin[1]+"') group by rollno) q2 on q1.t1=q2.t5 order by tt4 desc";
*/
rs = stmt.executeQuery(sql);
while(rs.next())
{

report[2][count]=rs.getString(1);
report[11][count]=rs.getString(2);
report[3][count]=rs.getString(3);
per=Math.round(((((tothrs-rs.getInt(4)*1.0)/(tothrs*1.0))*1.0)*10000.0))/100.0;

if((per>=min) && (per<=max))
{
report[0][count]=report2[3][i];
report[1][count]=yr+"";
report[4][count]=per+"";
count++;

}

}
rs.close();


	}
}
stmt.close();
connMgr.freeConnection("xavier",con);
error= "success"; 
}catch(Exception e){error=(e.toString()+sql);}
return error; 
}


public String mentoruabsrep(String staffid)
{
count=0;
int k=0;
try
{
			StringTokenizer st1 = new StringTokenizer(repin[3],"/");
		    d1=st1.nextToken();
		    m1=st1.nextToken();
		    y1=st1.nextToken();
			date1=m1+"/"+d1+"/"+y1;
}
catch(Exception e){}			

try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;

for(int j=1;j<=8;j++)
{
report[0][count]=j+"";

if(j==1) k=1;
else k=j-1;

sql="select isnull(STUFF((SELECT ', ' + ltrim(rtrim(str(a.rollno)))"
+" from attendance.dbo.attendance a,tutor t where a.rollno=t.rollno and a.date='"+date1+"' and hours like '%"+j+"%' "
+" and t.academicyear='"+repin[1]+"' and staffid='"+staffid+"' "
+" order by a.rollno FOR XML PATH('')),1,1,''),'-') AS CSV "; 
rs = stmt.executeQuery(sql);
if(rs.next())
{
report[1][count]=rs.getString(1);
}
rs.close();

sql="select isnull(STUFF((SELECT ', ' + ltrim(rtrim(str(a.rollno)))"
+" from attendance.dbo.attendance a,tutor t where a.rollno=t.rollno and a.date='"+date1+"' and hours like '%"+j+"%' "
+" and t.academicyear='"+repin[1]+"' and staffid='"+staffid+"' "
+" and a.rollno not in(select a.rollno from attendance.dbo.attendance a,tutor t where a.rollno=t.rollno and a.date='"+date1+"' and hours like '%"+k+"%' "
+" and t.academicyear='"+repin[1]+"' and staffid='"+staffid+"') "
+" order by a.rollno FOR XML PATH('')),1,1,''),'-') AS CSV "; 
rs = stmt.executeQuery(sql);
if(rs.next())
{
report[2][count]=rs.getString(1);
}
rs.close();

count++;

}

sql="select min(rollno),max(rollno) from tutor t where t.academicyear='"+repin[1]+"' and staffid='"+staffid+"'"; 
rs = stmt.executeQuery(sql);
if(rs.next())
{
report[1][count]=rs.getString(1);
report[2][count]=rs.getString(2);
}
rs.close();

stmt.close();
connMgr.freeConnection("xavier",con);
error= "success"; 
}catch(Exception e){error=(e.toString()+sql);}
return error; 
}

public String uabsrep()
{
count=0;
int k=0;
try
{
			StringTokenizer st1 = new StringTokenizer(repin[3],"/");
		    d1=st1.nextToken();
		    m1=st1.nextToken();
		    y1=st1.nextToken();
			date1=m1+"/"+d1+"/"+y1;
}
catch(Exception e){}			

try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;

for(int j=1;j<=8;j++)
{
report[0][count]=j+"";

if(j==1) k=1;
else k=j-1;

sql="select isnull(STUFF((SELECT ', ' + ltrim(rtrim(str(a.rollno))) "
+" from attendance.dbo.attendance a where a.date='"+date1+"' and hours like '%"+j+"%' "
+" order by a.rollno FOR XML PATH('')),1,1,''),'-') AS CSV ";

rs = stmt.executeQuery(sql);
if(rs.next())
{
report[1][count]=rs.getString(1);
}
rs.close();

sql="select isnull(STUFF((SELECT ', ' + ltrim(rtrim(str(a.rollno))) "
+" from attendance.dbo.attendance a where a.date='"+date1+"' and hours like '%"+j+"%' "
+" and a.rollno not in(select a.rollno from attendance.dbo.attendance a where a.date='"+date1+"' and hours like '%"+k+"%') "
+" order by a.rollno FOR XML PATH('')),1,1,''),'-') AS CSV ";

rs = stmt.executeQuery(sql);
if(rs.next())
{
report[2][count]=rs.getString(1);
}
rs.close();

count++;

}


stmt.close();
connMgr.freeConnection("xavier",con);
error= "success"; 
}catch(Exception e){error=(e.toString()+sql);}
return error; 
}

public String startenddates()
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
sql="select min(opening),max(closing), "
+"substring(('000'+ltrim(rtrim(str(day(min(opening)))))), len('000'+ltrim(rtrim(str(day(min(opening))))))-1,2)+'/'+  substring(('000'+ltrim(rtrim(str(month(min(opening)))))),len('000'+ltrim(rtrim(str(month(min(opening))))))-1,2)+'/'+ ltrim(rtrim(str(year(min(opening))))), "
+"substring(('000'+ltrim(rtrim(str(day(max(closing)))))), len('000'+ltrim(rtrim(str(day(max(closing))))))-1,2)+'/'+  substring(('000'+ltrim(rtrim(str(month(max(closing)))))),len('000'+ltrim(rtrim(str(month(max(closing))))))-1,2)+'/'+ ltrim(rtrim(str(year(max(closing))))) "
+"from attendance.dbo.reopening where semester in((select semester+4 from academicyear)) and academicyear in (select academicyear from academicyear)";

rs = stmt.executeQuery(sql);

if(rs.next())
{
	for(i=1;i<=4;i++)
	{
		report[i-1][0]=rs.getString(i);
	}
}
rs.close();

sql="select semester from academicyear";

rs = stmt.executeQuery(sql);

if(rs.next())
{
		report[4][0]=rs.getString(1);
}
rs.close();

stmt.close();
connMgr.freeConnection("xavier",con);
error= "success"; 
}catch(Exception e){error=(e.toString()+count+i+sql);}
return error; 
}


public String startenddates(int rno)
{
int i=0;
count=0;
int oddeven=1;
String ayear="";
int year=0;
int dno=0;
int sem=0;
try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;

sql="select * from academicyear ";
rs = stmt.executeQuery(sql);

if(rs.next())
{
  ayear=rs.getString(1);
  oddeven=Integer.parseInt(rs.getString(2));
}
rs.close();

sql="select year,department from stud where rollno="+rno+" and academicyear in (select academicyear from academicyear)";
rs = stmt.executeQuery(sql);

if(rs.next())
{
  year=Integer.parseInt(rs.getString(1));
  dno=Integer.parseInt(rs.getString(2));
}
rs.close();

if(oddeven==1)
  sem=(year*2)-1;
else
  sem=year*2;


sql="select opening,closing, "
+" substring(('000'+ltrim(rtrim(str(day((opening)))))), len('000'+ltrim(rtrim(str(day((opening))))))-1,2)+'/'+  substring(('000'+ltrim(rtrim(str(month((opening)))))),len('000'+ltrim(rtrim(str(month((opening))))))-1,2)+'/'+ ltrim(rtrim(str(year((opening))))), "
+" substring(('000'+ltrim(rtrim(str(day((closing)))))), len('000'+ltrim(rtrim(str(day((closing))))))-1,2)+'/'+  substring(('000'+ltrim(rtrim(str(month((closing)))))),len('000'+ltrim(rtrim(str(month((closing))))))-1,2)+'/'+ ltrim(rtrim(str(year((closing))))) "
+" from attendance.dbo.reopening where semester="+sem+" and dno="+dno+" and academicyear in (select academicyear from academicyear)";

rs = stmt.executeQuery(sql);

if(rs.next())
{
	for(i=1;i<=4;i++)
	{
		report[i-1][0]=rs.getString(i);
	}
}
rs.close();

sql="select semester from academicyear";

rs = stmt.executeQuery(sql);

if(rs.next())
{
		report[4][0]=rs.getString(1);
}
rs.close();

stmt.close();
connMgr.freeConnection("xavier",con);
error= "success"; 
}catch(Exception e){error=(e.toString()+count+i+sql);}
return error; 
}


public void setrep(String[] rep) {this.rep=rep;}  public String[] getrep() {	return this.rep;}
public void setrepin(String[] repin) {this.repin=repin;}  public String[] getrepin() {	return this.repin;}
public void setreport(String[][] report) {this.report=report;}  public String[][] getreport() {	return this.report;}
public void setreport1(String[] report1) {this.report1=report1;}  public String[] getreport1() {	return this.report1;}
public void setcount(int count) {this.count=count;} public int getcount(){	return this.count;}
public void setfname(String fname) { this.fname = fname; } public String getfname() { return this.fname; } 
public void setpname(String pname) { this.pname = pname; } public String getpname() { return this.pname; } 
public void setacademicyear(String academicyear) { this.academicyear = academicyear; } public String getacademicyear() { return this.academicyear; } 

public String getsql() { return this.sql; } 

}




	
	