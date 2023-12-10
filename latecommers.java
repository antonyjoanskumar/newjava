package attendance;
import java.sql.*;
import java.util.StringTokenizer;
import com.jspsmart.upload.*;
import java.util.Date;
import java.io.*;
import dd.DBConnectionManager;

public class latecommers
	{
				String rep[] = new String[1000];
				String repin[] = new String[30];
				String report[][] = new String[30][10000];
				String report1[] = new String[1000];
				String report2[][] = new String[30][10000];
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

String startdate="";
String enddate="";
String startdate1="";
String enddate1="";
String accyear="";
String accsem="";

int i1=0;
int i2=0;
int i3=0;
int i4=0;
int i5=0;


float f1=0;
float f2=0;
float f3=0;

public String DeleteLate(int cid)
{

try
{
Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
Connection con = DriverManager.getConnection("jdbc:odbc:attendance","sxcce","tamil5india6");
PreparedStatement ps = con.prepareStatement("delete from latecommers where id=?");
ps.setInt(1,cid);
int i = ps.executeUpdate();
ps.close();
con.close();
}catch(Exception e){return e.toString();}
return("success");
}

public String LateStat(int cdno,int ctype)
{
String dquery=" 1=1";
String dquery1=" 1=1";

String quer="";
String q1="";

String type="";
if(ctype==0)
{
	type="";
}
else
{
	type="and type="+ctype+" ";
}
if(cdno==0)
{
	dquery=" rollno in (select sxcce.dbo.stud.rollno from sxcce.dbo.stud,sxcce.dbo.student where sxcce.dbo.stud.rollno=sxcce.dbo.student.rollno and academicyear in(select academicyear from sxcce.dbo.academicyear) )";
	dquery1=" rollno in (select sxcce.dbo.stud.rollno from sxcce.dbo.stud,sxcce.dbo.student where sxcce.dbo.stud.rollno=sxcce.dbo.student.rollno and academicyear in(select academicyear from sxcce.dbo.academicyear) and sex='Male' )";
}
else if(cdno==7)
{
//	dquery=" rollno in (select sxcce.dbo.stud.rollno from sxcce.dbo.stud,sxcce.dbo.student where sxcce.dbo.stud.rollno=sxcce.dbo.student.rollno and department in(1,2,3,4,5,8,14,17,27) and academicyear in(select academicyear from sxcce.dbo.academicyear) and year=1)";
//	dquery1=" rollno in (select sxcce.dbo.stud.rollno from sxcce.dbo.stud,sxcce.dbo.student where sxcce.dbo.stud.rollno=sxcce.dbo.student.rollno and department in(1,2,3,4,5,8,14,17,27) and academicyear in(select academicyear from sxcce.dbo.academicyear) and year=1 and sex='Male')";
	dquery=" rollno in (select sxcce.dbo.stud.rollno from sxcce.dbo.stud,sxcce.dbo.student where sxcce.dbo.stud.rollno=sxcce.dbo.student.rollno and department in(1,2,3,4,5,8,14,17,27,91,92,93,94,95,96,97,98) and academicyear in(select academicyear from sxcce.dbo.academicyear) and year=1)";
	dquery1=" rollno in (select sxcce.dbo.stud.rollno from sxcce.dbo.stud,sxcce.dbo.student where sxcce.dbo.stud.rollno=sxcce.dbo.student.rollno and department in(1,2,3,4,5,8,14,17,27,91,92,93,94,95,96,97,98) and academicyear in(select academicyear from sxcce.dbo.academicyear) and year=1 and sex='Male')";
}
else if (cdno==12 || cdno==13 || cdno==15 || cdno==16 || cdno==18 || cdno==19 || cdno==20 || cdno==23 ||cdno==24 ||cdno==25 ||cdno==26 || cdno==28 || cdno==29)
{
	dquery=" rollno in (select sxcce.dbo.stud.rollno from sxcce.dbo.stud,sxcce.dbo.student where sxcce.dbo.stud.rollno=sxcce.dbo.student.rollno and department="+cdno+" and academicyear in(select academicyear from sxcce.dbo.academicyear) and year=1)";
	dquery1=" rollno in (select sxcce.dbo.stud.rollno from sxcce.dbo.stud,sxcce.dbo.student where sxcce.dbo.stud.rollno=sxcce.dbo.student.rollno and department="+cdno+" and academicyear in(select academicyear from sxcce.dbo.academicyear) and year=1 and sex='Male')";
}
else if (cdno==1 || cdno==14 )
{
	dquery=" rollno in (select sxcce.dbo.stud.rollno from sxcce.dbo.stud,sxcce.dbo.student where sxcce.dbo.stud.rollno=sxcce.dbo.student.rollno and department in(1,14) and academicyear in(select academicyear from sxcce.dbo.academicyear) and year>1)";
	dquery1=" rollno in (select sxcce.dbo.stud.rollno from sxcce.dbo.stud,sxcce.dbo.student where sxcce.dbo.stud.rollno=sxcce.dbo.student.rollno and department in(1,14)  and academicyear in(select academicyear from sxcce.dbo.academicyear) and year>1 and sex='Male')";
}
else if (cdno==2 || cdno==17 )
{
	dquery=" rollno in (select sxcce.dbo.stud.rollno from sxcce.dbo.stud,sxcce.dbo.student where sxcce.dbo.stud.rollno=sxcce.dbo.student.rollno and department in(2,17) and academicyear in(select academicyear from sxcce.dbo.academicyear) and year>1)";
	dquery1=" rollno in (select sxcce.dbo.stud.rollno from sxcce.dbo.stud,sxcce.dbo.student where sxcce.dbo.stud.rollno=sxcce.dbo.student.rollno and department in(2,17)  and academicyear in(select academicyear from sxcce.dbo.academicyear) and year>1 and sex='Male')";
}
else if (cdno==8 || cdno==27 )
{
	dquery=" rollno in (select sxcce.dbo.stud.rollno from sxcce.dbo.stud,sxcce.dbo.student where sxcce.dbo.stud.rollno=sxcce.dbo.student.rollno and department in(8,27) and academicyear in(select academicyear from sxcce.dbo.academicyear) and year>1)";
	dquery1=" rollno in (select sxcce.dbo.stud.rollno from sxcce.dbo.stud,sxcce.dbo.student where sxcce.dbo.stud.rollno=sxcce.dbo.student.rollno and department in(8,27)  and academicyear in(select academicyear from sxcce.dbo.academicyear) and year>1 and sex='Male')";
}
else
{
	dquery=" rollno in (select sxcce.dbo.stud.rollno from sxcce.dbo.stud,sxcce.dbo.student where sxcce.dbo.stud.rollno=sxcce.dbo.student.rollno and department="+cdno+" and academicyear in(select academicyear from sxcce.dbo.academicyear) and year>1)";
	dquery1=" rollno in (select sxcce.dbo.stud.rollno from sxcce.dbo.stud,sxcce.dbo.student where sxcce.dbo.stud.rollno=sxcce.dbo.student.rollno and department="+cdno+"  and academicyear in(select academicyear from sxcce.dbo.academicyear) and year>1 and sex='Male')";
}

try
{
Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
Connection con = DriverManager.getConnection("jdbc:odbc:attendance","sxcce","tamil5india6");
Statement stmt = con.createStatement();
ResultSet rs;

quer="select count(*) from (select rollno from latecommers where day(timein)=day(getdate()) and month(timein)=month(getdate()) and year(timein)=year(getdate()) and "+dquery+ type +" group by rollno) q1";

rs = stmt.executeQuery(quer);
if(rs.next())
{
report1[0]=rs.getString(1);
}
rs.close();

quer="select count(*) from latecommers where month(timein)=month(getdate()) and year(timein)=year(getdate()) and "+dquery+type;
rs = stmt.executeQuery(quer);
if(rs.next())
{
report1[1]=rs.getString(1);
}
rs.close();

quer="select count(*) from latecommers where year(timein)=year(getdate()) and "+dquery+type;
rs = stmt.executeQuery(quer);
if(rs.next())
{
report1[2]=rs.getString(1);
}
rs.close();

quer="select count(*) from (select rollno from latecommers where day(timein)=day(getdate()) and month(timein)=month(getdate()) and year(timein)=year(getdate()) and "+dquery1+type+" group by rollno) q1";
rs = stmt.executeQuery(quer);
if(rs.next())
{
report1[3]=rs.getString(1);
}
rs.close();
try{
report1[4]=(Integer.parseInt(report1[0])-Integer.parseInt(report1[3]))+"";
}catch(Exception e){report1[4]=0+"";}

quer="select max(id),right('0' + rtrim(ltrim(str(DATEPART(HOUR, max(timein))))),2)+':'+right('0' + rtrim(ltrim(str(DATEPART(MINUTE, max(timein))))),2),a.rollno,isnull(max(n1),' '),max(type) from latecommers a "
+"Left Outer join (select str(rollno) rollno,name n1 from sxcce.dbo.student) b on ltrim(rtrim(a.rollno))=ltrim(rtrim(str(b.rollno)))  "
+"where day(timein)=day(getdate()) and month(timein)=month(getdate()) and year(timein)=year(getdate()) and a."+dquery+type+" group by a.rollno order by max(timein)";

q1=quer;
rs = stmt.executeQuery(quer);

count=0;
while(rs.next())
{
	for(int i=1;i<=5;i++)
	{
		report[i-1][count]=rs.getString(i);
	}
	count++;
}
rs.close();

quer=startenddates();

for(int i=0;i<count; i++)
{
quer="select isnull(count(*),0) from latecommers where rollno="+report[2][i]+" and timein between '"+startdate+"' and '"+enddate+"' "+type;
rs = stmt.executeQuery(quer);
if(rs.next())
{
report[6][i]=rs.getString(1);
}
rs.close();
}

stmt.close();
con.close();
}catch(Exception e){return e.toString()+quer;}
return "suc"+quer+q1;
}


public String GateStat(int ctype)
{
String dquery=" 1=1";
String dquery1=" 1=1";

String type="";
if(ctype==0)
{
	type="";
}
else
{
	type="and type="+ctype+" ";
}
	dquery=" rollno in (select s.rollno from sxcce.dbo.stud s,sxcce.dbo.student s1 where s.rollno=s1.rollno and academicyear in(select academicyear from sxcce.dbo.academicyear) )";
	dquery1=" rollno in (select s.rollno from sxcce.dbo.stud s,sxcce.dbo.student s1 where s.rollno=s1.rollno and academicyear in(select academicyear from sxcce.dbo.academicyear) and sex='Male' )";

String quer="";
try
{
Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
Connection con = DriverManager.getConnection("jdbc:odbc:attendance","sxcce","tamil5india6");
Statement stmt = con.createStatement();
ResultSet rs;

/*
quer="select count(*) from (select rollno from latecommers where day(timein)=day(getdate()) and month(timein)=month(getdate()) and year(timein)=year(getdate()) and "+dquery+ type +" group by rollno) q1";

rs = stmt.executeQuery(quer);
if(rs.next())
{
report1[0]=rs.getString(1);
}
rs.close();

quer="select count(*) from latecommers where month(timein)=month(getdate()) and year(timein)=year(getdate()) and "+dquery+type;
rs = stmt.executeQuery(quer);
if(rs.next())
{
report1[1]=rs.getString(1);
}
rs.close();

quer="select count(*) from latecommers where year(timein)=year(getdate()) and "+dquery+type;
rs = stmt.executeQuery(quer);
if(rs.next())
{
report1[2]=rs.getString(1);
}
rs.close();

quer="select count(*) from (select rollno from latecommers where day(timein)=day(getdate()) and month(timein)=month(getdate()) and year(timein)=year(getdate()) and "+dquery1+type+" group by rollno) q1";
rs = stmt.executeQuery(quer);
if(rs.next())
{
report1[3]=rs.getString(1);
}
rs.close();

report1[4]=(Integer.parseInt(report1[0])-Integer.parseInt(report1[3]))+"";

*/
quer="select max(id),right('0' + rtrim(ltrim(str(DATEPART(HOUR, max(timein))))),2)+':'+right('0' + rtrim(ltrim(str(DATEPART(MINUTE, max(timein))))),2),a.rollno,isnull(max(n1),' '),max(type) from latecommers a "
+"Left Outer join (select str(rollno) rollno,name n1 from sxcce.dbo.student) b on ltrim(rtrim(a.rollno))=ltrim(rtrim(str(b.rollno)))  "
+"where day(timein)=day(getdate()) and month(timein)=month(getdate()) and year(timein)=year(getdate()) and a."+dquery+type+" group by a.rollno order by max(timein)";

rs = stmt.executeQuery(quer);

count=0;
while(rs.next())
{
	for(int i=1;i<=5;i++)
	{
		report[i-1][count]=rs.getString(i);
	}
	count++;
}
rs.close();

quer=startenddates();

for(int i=0;i<count; i++)
{
quer="select isnull(count(*),0) from latecommers where rollno="+report[2][i]+" and timein between '"+startdate+"' and '"+enddate+"' "+type;
rs = stmt.executeQuery(quer);
if(rs.next())
{
report[6][i]=rs.getString(1);
}
rs.close();
}
stmt.close();
con.close();
}catch(Exception e){return e.toString()+quer;}
return "suc"+quer;
}



public String LateAbsRep()
{
String quer="";

report2[0][0]="1,2,3,4,5,8,14,17,27";
report2[0][1]="1,14";
report2[0][2]="2,17";
report2[0][3]="3";
report2[0][4]="4";
report2[0][5]="5";
report2[0][6]="8,27";
report2[0][7]="16";
report2[0][8]="18";
report2[0][9]="19,13,24,26,28,12,25,15,29,20";

report2[1][0]="FY";
report2[1][1]="CS";
report2[1][2]="EC";
report2[1][3]="EE";
report2[1][4]="CE";
report2[1][5]="IT";
report2[1][6]="ME";
report2[1][7]="MB";
report2[1][8]="MC";
report2[1][9]="PG";

report2[2][0]="1";
report2[2][1]="2,3,4";
report2[2][2]="2,3,4";
report2[2][3]="2,3,4";
report2[2][4]="2,3,4";
report2[2][5]="2,3,4";
report2[2][6]="2,3,4";
report2[2][7]="1,2";
report2[2][8]="1,2,3";
report2[2][9]="1,2";


try
{
Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
Connection con = DriverManager.getConnection("jdbc:odbc:attendance","sxcce","tamil5india6");
Statement stmt = con.createStatement();
ResultSet rs;

for(int i=0;i<10;i++)
{
quer="select count(*) from latecommers l,sxcce.dbo.student st,sxcce.dbo.stud s where l.rollno=s.rollno and s.rollno=st.rollno "
+" and academicyear in(select academicyear from sxcce.dbo.academicyear) "
+" and timein between cast(cast(getdate() as DATE) as DATETIME) and cast(cast(getdate() as DATE) as DATETIME)+1 "
+" and departmentno in ("+report2[0][i]+") and year in ("+report2[2][i]+")";

rs = stmt.executeQuery(quer);
if(rs.next())
{
report[i][0]=rs.getString(1);
}
else
{
report[i][0]=" ";
}
rs.close();
}

int t=0;
int a=0;
for(int j=1;j<=8;j++)
{
	t=0;
for(int i=0;i<10;i++)
{
quer="select count(*) from attendance a,sxcce.dbo.student st,sxcce.dbo.stud s where a.rollno=s.rollno and s.rollno=st.rollno "
+" and academicyear in(select academicyear from sxcce.dbo.academicyear) "
+" and date=cast(cast(getdate() as DATE) as DATETIME) "
+" and hours like '%"+j+"%' and departmentno in ("+report2[0][i]+") and year in ("+report2[2][i]+")";
report[i][j]=" ";

rs = stmt.executeQuery(quer);
if(rs.next())
{
report[i][j]=rs.getString(1);
}
else
{
report[i][j]=" ";
}
rs.close();
t=t+Integer.parseInt(report[i][j]);
}
if(a<t) a=t;
}
report[11][1]=a+"";

quer="select count(*) from latecommers l,sxcce.dbo.student st,sxcce.dbo.stud s where l.rollno=s.rollno and s.rollno=st.rollno "
+" and academicyear in(select academicyear from sxcce.dbo.academicyear) "
+" and timein between cast(cast(getdate() as DATE) as DATETIME) and cast(cast(getdate() as DATE) as DATETIME)+1 ";
report[11][0]=" ";

rs = stmt.executeQuery(quer);
if(rs.next())
{
report[11][0]=rs.getString(1);
}
else
{
report[11][0]=" ";
}
rs.close();




stmt.close();
con.close();
}catch(Exception e){return e.toString()+quer;}
return "suc";
}


public String PeriodAtt(int d,int period)
{
String quer="";

report2[0][0]="1,2,3,4,5,8,14,17,27";
report2[0][1]="1,14";
report2[0][2]="2,17";
report2[0][3]="3";
report2[0][4]="4";
report2[0][5]="5";
report2[0][6]="8,27";
report2[0][7]="16";
report2[0][8]="18";
report2[0][9]="19,13,24,26,28,12,25,15,29,20";

report2[1][0]="FY";
report2[1][1]="CS";
report2[1][2]="EC";
report2[1][3]="EE";
report2[1][4]="CE";
report2[1][5]="IT";
report2[1][6]="ME";
report2[1][7]="MB";
report2[1][8]="MC";
report2[1][9]="PG";

report2[2][0]="1";
report2[2][1]="2,3,4";
report2[2][2]="2,3,4";
report2[2][3]="2,3,4";
report2[2][4]="2,3,4";
report2[2][5]="2,3,4";
report2[2][6]="2,3,4";
report2[2][7]="1,2";
report2[2][8]="1,2,3";
report2[2][9]="1,2";


try
{
Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
Connection con = DriverManager.getConnection("jdbc:odbc:attendance","sxcce","tamil5india6");
Statement stmt = con.createStatement();
ResultSet rs;

quer="select p.period,year,p.dno,sf,q.id,semester,subcode,staffid,name from (select date,period,year,a.dno,sf "
+" from attendancecheck a,sxcce.dbo.department d where a.dno=d.dno and date>getdate()-1 and period="+period+" and a.dno in("+report2[0][d]+") and year in ("+report2[2][d]+")) p "
+" left join (select dno,id,semester from sxcce.dbo.subjectidentify where academicyear in(select academicyear from sxcce.dbo.academicyear) and (semester%2)+1=(select semester+1 from sxcce.dbo.academicyear) ) q on p.dno=q.dno and p.year=(q.semester+1)/2 "
+" left join (select date,period,id,subcode,log.staffid,name from log,sxcce.dbo.staff s where log.staffid=s.staffid) r on q.id=r.id and p.date=r.date and p.period=r.period order by year,sf";

rs = stmt.executeQuery(quer);

count=0;
while(rs.next())
{
	for(int i=1;i<=9;i++)
	{
		report[i-1][count]=rs.getString(i);
	}
	count++;
}
rs.close();

for(int i=0;i<count;i++)
{
quer="select count(*) from attendance a,sxcce.dbo.stud s where  a.rollno=s.rollno and date>getdate()-1 and hours like '%"+period+"%' "
+" and academicyear in(select academicyear from sxcce.dbo.academicyear) and department in ("+report[2][i]+") and year in ("+report[1][i]+")";


//select count(*) from attendance where date>getdate()-1 and rollno in(select rollno from sxcce.dbo.stud "
//+" where academicyear in(select academicyear from sxcce.dbo.academicyear) and department in ("+report[2][i]+") and year in ("+report[1][i]+")) and hours like '%"+period+"%'";

try{
rs = stmt.executeQuery(quer);
if(rs.next())
{
report[9][i]=rs.getString(1);
}
else
{
report[9][i]=" ";
}
rs.close();
}catch(Exception e){}

quer="select count(*) from sxcce.dbo.stud where academicyear in(select academicyear from sxcce.dbo.academicyear) and department in("+report[2][i]+") and year in ("+report[1][i]+")";

try{
rs = stmt.executeQuery(quer);
if(rs.next())
{
report[10][i]=rs.getString(1);
}
else
{
report[10][i]=" ";
}
rs.close();
}catch(Exception e){}
		
}




stmt.close();
con.close();
}catch(Exception e){return e.toString()+quer;}
return "suc";
}


public String LateRep()
{
String quer="";

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

try
{
Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
Connection con = DriverManager.getConnection("jdbc:odbc:attendance","sxcce","tamil5india6");
Statement stmt = con.createStatement();
ResultSet rs;



quer="select department,max(dname),year,count(*) from latecommers l,sxcce.dbo.stud s,sxcce.dbo.student st,sxcce.dbo.department d "
+"where l.rollno=st.rollno and st.rollno=s.rollno and d.dno=s.department and academicyear='"+repin[2]+"'"
+"and timein between '"+date1+" 0:0' and '"+date2+" 23:59' "
+"and department in("+repin[0]+") and year in("+repin[1]+") "
+"group by department,year order by department,year";

rs = stmt.executeQuery(quer);

count=0;
while(rs.next())
{
	for(int i=1;i<=4;i++)
	{
		report[i-1][count]=rs.getString(i);
	}
	count++;
}
rs.close();

for(int i=0;i<count;i++)
{
quer="select count(*) from latecommers l,sxcce.dbo.stud s,sxcce.dbo.student st,sxcce.dbo.department d "
+"where l.rollno=st.rollno and st.rollno=s.rollno and d.dno=s.department and academicyear='"+repin[2]+"'"
+"and timein between '"+date1+" 0:0' and '"+date2+" 23:59' "
+"and department in ("+report[0][i]+") and year in("+report[2][i]+") and sex='Male' "
+"group by department,year order by department,year";

rs = stmt.executeQuery(quer);

if(rs.next())
{
report[4][i]=rs.getString(1);
report[5][i]=(Integer.parseInt(report[3][i])-Integer.parseInt(report[4][i]))+"";
}
else
{
report[4][i]="0";
report[5][i]=(Integer.parseInt(report[3][i])-Integer.parseInt(report[4][i]))+"";
}
rs.close();

		
}


stmt.close();
con.close();
}catch(Exception e){return e.toString()+quer;}
return "suc";
}

public String LateStudRep()
{
String quer="";

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

try
{
Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
Connection con = DriverManager.getConnection("jdbc:odbc:attendance","sxcce","tamil5india6");
Statement stmt = con.createStatement();
ResultSet rs;



quer="select st.rollno,max(st.name),max(sf),max(year),count(*) cnt from latecommers l,sxcce.dbo.stud s,sxcce.dbo.student st,sxcce.dbo.department d "
+"where l.rollno=st.rollno and st.rollno=s.rollno and d.dno=s.department and academicyear='"+repin[2]+"'"
+"and timein between '"+date1+" 0:0' and '"+date2+" 23:59' "
+"and department in("+repin[0]+") and year in("+repin[1]+") "
+"group by st.rollno "+repin[5];

rs = stmt.executeQuery(quer);

count=0;
while(rs.next())
{
	for(int i=1;i<=5;i++)
	{
		report[i-1][count]=rs.getString(i);
	}
	count++;
}
rs.close();

stmt.close();
con.close();
}catch(Exception e){return e.toString()+quer;}
return "suc";
}

public String AbsRep()
{
String quer="";

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

try
{
Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
Connection con = DriverManager.getConnection("jdbc:odbc:attendance","sxcce","tamil5india6");
Statement stmt = con.createStatement();
ResultSet rs;



quer="select department,max(dname),year,count(*) from attendance l,sxcce.dbo.stud s,sxcce.dbo.student st,sxcce.dbo.department d "
+"where l.rollno=st.rollno and st.rollno=s.rollno and d.dno=s.department and academicyear='"+repin[2]+"'"
+"and date between '"+date1+" 0:0' and '"+date2+" 23:59' "
+"and department in("+repin[0]+") and year in("+repin[1]+") "+repin[5]
+"group by department,year order by department,year";

rs = stmt.executeQuery(quer);

count=0;
while(rs.next())
{
	for(int i=1;i<=4;i++)
	{
		report[i-1][count]=rs.getString(i);
	}
	count++;
}
rs.close();

for(int i=0;i<count;i++)
{
quer="select count(*) from attendance l,sxcce.dbo.stud s,sxcce.dbo.student st,sxcce.dbo.department d "
+"where l.rollno=st.rollno and st.rollno=s.rollno and d.dno=s.department and academicyear='"+repin[2]+"'"
+"and date between '"+date1+" 0:0' and '"+date2+" 23:59' "
+"and department in ("+report[0][i]+") and year in("+report[2][i]+") and sex='Male' "+repin[5]
+"group by department,year order by department,year";

rs = stmt.executeQuery(quer);

if(rs.next())
{
report[4][i]=rs.getString(1);
report[5][i]=(Integer.parseInt(report[3][i])-Integer.parseInt(report[4][i]))+"";
}
else
{
report[4][i]="0";
report[5][i]=(Integer.parseInt(report[3][i])-Integer.parseInt(report[4][i]))+"";
}
rs.close();

		
}


stmt.close();
con.close();
}catch(Exception e){return e.toString()+quer;}
return "suc";
}
public String InsertLate()
{
int lid=-1;
String quer="";
int found=0;
int i1,i2;

try {i1=Integer.parseInt(repin[0]); }catch(Exception e){i1=0;}
try {i2=Integer.parseInt(repin[1]); }catch(Exception e){i2=0;}
if(i1>0)
{

try
{
Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
Connection con = DriverManager.getConnection("jdbc:odbc:attendance","sxcce","tamil5india6");
Statement stmt = con.createStatement();
ResultSet rs;
quer="select * from sxcce.dbo.student where ltrim(rtrim(str(rollno)))="+i1;
rs = stmt.executeQuery(quer);
if(rs.next()) found=1;
rs.close();
stmt.close();
con.close();
}catch(Exception e){return e.toString();}

if(found==1)
{

try
{
Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
Connection con = DriverManager.getConnection("jdbc:odbc:attendance","sxcce","tamil5india6");
Statement stmt = con.createStatement();
PreparedStatement ps;
ps = con.prepareStatement("insert into latecommers(rollno,timein,type,login) values (?,getdate(),?,?)");
ps.setInt(1,i1);
ps.setInt(2,i2);
ps.setString(3,repin[2]);
int i = ps.executeUpdate();
ps.close();
stmt.close();
con.close();
}catch(Exception e){return e.toString();}

}
}
return("success");
}



public String startenddates()
{
int i=0;
try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;
sql="select min(opening),max(closing), "
+"substring(('000'+ltrim(rtrim(str(day(max(opening)))))), len('000'+ltrim(rtrim(str(day(max(opening))))))-1,2)+'/'+  substring(('000'+ltrim(rtrim(str(month(max(opening)))))),len('000'+ltrim(rtrim(str(month(max(opening))))))-1,2)+'/'+ ltrim(rtrim(str(year(max(opening))))), "
+"substring(('000'+ltrim(rtrim(str(day(max(closing)))))), len('000'+ltrim(rtrim(str(day(max(closing))))))-1,2)+'/'+  substring(('000'+ltrim(rtrim(str(month(max(closing)))))),len('000'+ltrim(rtrim(str(month(max(closing))))))-1,2)+'/'+ ltrim(rtrim(str(year(max(closing))))) "
+"from attendance.dbo.reopening where semester in((select semester+2 from academicyear)) and academicyear in (select academicyear from academicyear)";

rs = stmt.executeQuery(sql);

if(rs.next())
{
startdate=rs.getString(1);
enddate=rs.getString(2);
startdate1=rs.getString(3);
enddate1=rs.getString(4);
}
rs.close();

sql="select academicyear,semester from academicyear";

rs = stmt.executeQuery(sql);

if(rs.next())
{
accyear=rs.getString(1);
accsem=rs.getString(2);
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



}




	
	