package sxcce;
import java.sql.*;
import dd.DBConnectionManager;
import java.io.*;
public class dashboard
{
String report[][] = new String[100][10];
String quer="";
int count=0;

String fday="";
String fmonth="";
String fyear="";

String tday="";
String tmonth="";
String tyear="";

String tdate="";
String fdate="";




public String dash()
{count=0;
try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
Statement stmt = con.createStatement();
ResultSet rs;
quer="select count(*),max(category) cat from staff where active=1 group by category order by cat";

rs = stmt.executeQuery(quer);
while(rs.next())
   {
	report[count][0]=rs.getString(1);
	report[count][1]=rs.getString(2);
   	count++;
   }
rs.close();

quer="select count(*),max(sex) sex from student where active=1 and rollno in(select rollno from stud where academicyear in(select academicyear from academicyear)) group by sex order by sex";

count=10;

rs = stmt.executeQuery(quer);
while(rs.next())
   {
	report[count][0]=rs.getString(1);
	report[count][1]=rs.getString(2);
    	count++;
   }
rs.close();

quer="select sum(amount),convert(varchar(6), max(date), 107) from accounts.dbo.payment where date>getdate()-10 group by date order by date desc";

count=20;
rs = stmt.executeQuery(quer);
while(rs.next())
   {
	report[count][0]=rs.getString(1);
	report[count][1]=rs.getString(2);
   	count++;
   }
rs.close();

quer="select ISNULL(aa1,0)+ISNULL(aa2,0)+ISNULL(aa3,0)+ISNULL(aa4,0),case when ISNULL(dd1,0)<ISNULL(dd2,0) then convert(varchar(6), dd2, 107) else convert(varchar(6), dd1, 107) end ddd from "+
"(select ISNULL(a1,0) aa1,ISNULL(a2,0) aa2,case when ISNULL(d1,0)<ISNULL(d2,0) then d2 else d1 end dd1, * from (select sum(amount) a1,max(date) d1 from accounts.dbo.busfee where date>getdate()-10 group by date) q1 "+
" full join (select ISNULL(sum(amount),0) a2,max(date) d2 from accounts.dbo.dues where date>getdate()-10 group by date) q2 on d1=d2) p1 "+
" full join "+
"(select ISNULL(a3,0) aa3,ISNULL(a4,0) aa4,case when ISNULL(d3,0)<ISNULL(d4,0) then d4 else d3 end dd2, * from (select sum(amount) a3,max(date) d3 from accounts.dbo.examfee where date>getdate()-10 group by date) q3 "+
" full join (select ISNULL(sum(amount),0) a4,max(date) d4 from accounts.dbo.revalfee where date>getdate()-10 group by date) q4 on d3=d4) p2 on dd1=dd2 "+
" order by ddd desc";

count=30;
rs = stmt.executeQuery(quer);
while(rs.next())
   {
	report[count][0]=rs.getString(1);
	report[count][1]=rs.getString(2);
   	count++;
   }
rs.close();



quer="select count(*),max(sf),max(dno) from library.dbo.book,department where dno=department group by department";

count=40;
rs = stmt.executeQuery(quer);
while(rs.next())
   {
	report[count][0]=rs.getString(1);
	report[count][1]=rs.getString(2);
	report[count][2]=rs.getString(3);
   	count++;
   }
rs.close();



quer="select ISNULL(c1,0)+ISNULL(c1,0),case when ISNULL(ld1,0)<ISNULL(ld2,0) then convert(varchar(6), ld2, 107) else convert(varchar(6), ld1, 107) end ld from "+
"(select count(*) c1,convert(varchar(3), max(lendingdate), 107)+' '+convert(varchar(2), max(lendingdate), 2) ld1,max(lendingdate) d1 from library.dbo.returnbook where lendingdate>getdate()-365 "+
"GROUP by year(lendingdate),MONTH(lendingdate)) q1  full join (select count(*) c2,convert(varchar(3), max(date), 107)+' '+convert(varchar(2), max(date), 2) ld2,max(date) d2 from library.dbo.issue where date>getdate()-365 "+
"GROUP by year(date),MONTH(date)) q2 on ld1=ld2 order by d1 ";

count=60;
rs = stmt.executeQuery(quer);
while(rs.next())
   {
	report[count][0]=rs.getString(1);
	report[count][1]=rs.getString(2);
   	count++;
   }
rs.close();

quer="SELECT sex,count(*) from student,stud where student.rollno=stud.rollno and academicyear in(select academicyear from academicyear) "
+" and active=1 and year=1 and departmentno in(1,2,3,4,5,8,14,17,27) group by sex order by sex ";
count=70;
rs = stmt.executeQuery(quer);
while(rs.next())
   {
	report[count][0]="B.E.";
	report[count][1]=rs.getString(1);
	report[count][2]=rs.getString(2);
   	count++;
   }
rs.close();
	report[count][0]="B.E.";
	report[count][1]="Total";
	report[count][2]=(Integer.parseInt(report[count-1][2])+Integer.parseInt(report[count-2][2]))+"";

quer="SELECT sex,count(*) from student,stud where student.rollno=stud.rollno and academicyear in(select academicyear from academicyear) "
+" and active=1 and year=1 and departmentno in(16) group by sex order by sex ";
count=73;
rs = stmt.executeQuery(quer);
while(rs.next())
   {
	report[count][0]="MBA";
	report[count][1]=rs.getString(1);
	report[count][2]=rs.getString(2);
   	count++;
   }
rs.close();
	report[count][0]="MBA";
	report[count][1]="Total";
	report[count][2]=(Integer.parseInt(report[count-1][2])+Integer.parseInt(report[count-2][2]))+"";

quer="SELECT sex,count(*) from student,stud where student.rollno=stud.rollno and academicyear in(select academicyear from academicyear) "
+" and active=1 and year=1 and departmentno in(18) group by sex order by sex ";
count=76;
rs = stmt.executeQuery(quer);
while(rs.next())
   {
	report[count][0]="MCA";
	report[count][1]=rs.getString(1);
	report[count][2]=rs.getString(2);
   	count++;
   }
rs.close();
	report[count][0]="MCA";
	report[count][1]="Total";
	report[count][2]=(Integer.parseInt(report[count-1][2])+Integer.parseInt(report[count-2][2]))+"";

quer="SELECT sex,count(*) from student,stud where student.rollno=stud.rollno and academicyear in(select academicyear from academicyear) "
+" and active=1 and year=1 and departmentno not in(1,2,3,4,5,8,14,17,27,16,18,6,7,30) group by sex order by sex ";
count=79;
rs = stmt.executeQuery(quer);
while(rs.next())
   {
	report[count][0]="M.E.";
	report[count][1]=rs.getString(1);
	report[count][2]=rs.getString(2);
   	count++;
   }
rs.close();
	report[count][0]="M.E.";
	report[count][1]="Total";
	report[count][2]=(Integer.parseInt(report[count-1][2])+Integer.parseInt(report[count-2][2]))+"";


int wophd=0;
int wosup=0;
int wsup=0;
int totteach=0;

quer="select count(*) from staff where active=1 and category='Teaching'";
rs = stmt.executeQuery(quer);
if(rs.next())   totteach=rs.getInt(1);
rs.close();

quer="select count(*) from apiqualification,staff,department where  department.dno=staff.dno and apiqualification.staffid=staff.staffid and degree='Ph.D' and active=1  and category='Teaching' "
+" and staff.staffid in (select staffid  from apiresearch where supervisoryesno in('Yes'))";
rs = stmt.executeQuery(quer);
if(rs.next())   wsup=rs.getInt(1);
rs.close();

quer="select count(*) from apiqualification,staff,department where  department.dno=staff.dno and apiqualification.staffid=staff.staffid and degree='Ph.D' and active=1  and category='Teaching' "
+" and staff.staffid not in (select staffid  from apiresearch where supervisoryesno not in('No'))";
rs = stmt.executeQuery(quer);
if(rs.next())   wosup=rs.getInt(1);
rs.close();


count=82;
wophd=totteach-(wsup+wosup);
report[count][0]="W/O Ph.D";
report[count][2]=wophd+"";
report[count][1]=totteach+"";
count++;

report[count][0]="W/O Sup";
report[count][2]=wosup+"";
count++;

report[count][0]="Sup";
report[count][2]=wsup+"";
count++;



stmt.close();
connMgr.freeConnection("xavier",con);
}catch(Exception e){return quer+e.toString();}
return "suc";
}








 public void setcount(int count) 
{ 
this.count = count; 
} 
public int getcount() 
{ 
return this.count; 
} 



public void setfday(String fday) 
{ 
this.fday = fday; 
} 
public String getfday() 
{ 
return this.fday; 
} 
public void setfmonth(String fmonth) 
{ 
this.fmonth = fmonth; 
} 
public String getfmonth() 
{ 
return this.fmonth; 
} 
public void setfyear(String fyear) 
{ 
this.fyear = fyear; 
} 
public String getfyear() 
{ 
return this.fyear; 
} 
public void settday(String tday) 
{ 
this.tday = tday; 
} 
public String gettday() 
{ 
return this.tday; 
} 
public void settmonth(String tmonth) 
{ 
this.tmonth = tmonth; 
} 
public String gettmonth() 
{ 
return this.tmonth; 
} 
public void settyear(String tyear) 
{ 
this.tyear = tyear; 
} 
public String[][] getreport() 
{ 
return this.report; 
} 


}

