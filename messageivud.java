package sxcce;
import java.sql.*;
import dd.DBConnectionManager;
import java.io.*;
public class messageivud
{
String matchno[] = new String[5000];
String matchsubject[] = new String[5000];
String matchdate[] = new String[5000];
String matchsender[] = new String[5000];
String viewers[] = new String[5000];
String recipients[] = new String[5000];
String report[][] = new String[100][10];
String quer="";

String pstaffid[] = new String[5000];
String pname[] = new String[5000];
String pdname[] = new String[5000];

int count=0;
int active=0;
String sender="";
String date="";
String subject="";
String name="";
String body="";
int no=0;

String mgmt[] = new String[20];
String dno[] = new String[50];
String staff[] = new String[20];

int mgmtcount=0;
int dnocount=0;
int staffcount=0;

String staffid="";
String hod="";
String management="";


String fday="";
String fmonth="";
String fyear="";
String tday="";
String tmonth="";
String tyear="";
String tdate="";
String fdate="";


public int Maxno()
{
	int temp=100;
try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
Statement stmt = con.createStatement();
ResultSet rs = stmt.executeQuery("select max(no) from message");
if(rs.next())
{
temp=rs.getInt(1);
}
if(temp==0)temp=101;
else temp++;
no=temp;
rs.close();
stmt.close();
connMgr.freeConnection("xavier",con);
}catch(Exception e){}
return no; 
}

public String dashboard()
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

quer="select count(*),max(sex) sex from student where active=1 and rollno in(select rollno from stud where academicyear in(select academicyear from academicyear)) and departmentno not in(7,30) group by sex order by sex";

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


stmt.close();
connMgr.freeConnection("xavier",con);
}catch(Exception e){return quer+e.toString();}
return "suc";
}



public String pending()
{count=0;
try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
Statement stmt = con.createStatement();
ResultSet rs;
quer="select * from messagetomgmt where no="+no+" and mgmt not in "+
"(select mgmt from messageackmgmt where no="+no+")";

rs = stmt.executeQuery(quer);
while(rs.next())
   {
	pstaffid[count]=rs.getString(2);
	if(pstaffid[count].equals("1"))pname[count]="Correspondent";
	else if(pstaffid[count].equals("2"))pname[count]="Principal";
	else if(pstaffid[count].equals("3"))pname[count]="Bursar";
	pdname[count]="Mgmt";
   	count++;
   }
rs.close();

quer="select department.dno,department.sf from messagetodno, "+
"department where no="+no+" and messagetodno.dno not in "+
"(select dno from messageackdno where no="+no+") "+
"and messagetodno.dno=department.dno";


rs = stmt.executeQuery(quer);
while(rs.next())
   {
	pstaffid[count]=rs.getString(1);
	pname[count]="HOD";
	pdname[count]=rs.getString(2);
   	count++;
   }
rs.close();

quer="select name,staffid,department.sf from staff,department where staffid not in "+
"(select staff from messageackstaff where no="+no+") "+
"and category in (select staff from messagetostaff "+
"where no="+no+") and department.dno in (select dno from messagetodno where no="+no+") and staff.active=1 and department.dno=staff.dno and "+
"appdate<(select date from message where no="+no+") "+
"order by staff.dno,designation ";




rs = stmt.executeQuery(quer);
while(rs.next())
   {
	pname[count]=rs.getString(1);;
	pstaffid[count]=rs.getString(2);
	pdname[count]=rs.getString(3);
   	count++;
   }
rs.close();
stmt.close();
connMgr.freeConnection("xavier",con);
}catch(Exception e){return quer+e.toString();}
return "suc";
}


public String staffchecknew()
{count=0;
try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
Statement stmt = con.createStatement();
ResultSet rs;
quer="select no,subject,substring(('000'+ltrim(rtrim(str(day(date))))), "+
"len('000'+ltrim(rtrim(str(day(date)))))-1,2)+'/'+  "+
"substring(('000'+ltrim(rtrim(str(month(date))))), "+
"len('000'+ltrim(rtrim(str(month(date)))))-1,2)+'/'+ "+
"ltrim(rtrim(str(year(date)))),sender,active  from message where date>(select appdate from staff where staffid='"+staffid+"') and no in "+
"(select no from messagetodno where dno in "+
"(select dno from staff where staffid='"+staffid+"') and no in "+
"(select no from messagetostaff where staff in "+
"(select category from staff where staffid='"+staffid+"'))) "+
"and active=1 and no not in (select no from messageackstaff where staff='"+staffid+"') order by date desc";

rs = stmt.executeQuery(quer);
while(rs.next())
   {
	matchno[count]=rs.getString(1);
	matchsubject[count]=rs.getString(2);
	matchdate[count]=rs.getString(3);
	matchsender[count]=rs.getString(4);
   	count++;
   }
rs.close();

stmt.close();
connMgr.freeConnection("xavier",con);
}catch(Exception e){return quer+e.toString();}
return "suc";
}


public String hodchecknew()
{count=0;
try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
Statement stmt = con.createStatement();
ResultSet rs;
quer="select no,subject,substring(('000'+ltrim(rtrim(str(day(date))))), "+
"len('000'+ltrim(rtrim(str(day(date)))))-1,2)+'/'+  "+
"substring(('000'+ltrim(rtrim(str(month(date))))), "+
"len('000'+ltrim(rtrim(str(month(date)))))-1,2)+'/'+ "+
"ltrim(rtrim(str(year(date)))),sender,active  from message where no in "+
"(select no from messagetodno where dno ="+staffid+") "+
"and active=1 and no not in (select no from messageackdno where dno='"+staffid+"') order by date desc";

rs = stmt.executeQuery(quer);
while(rs.next())
   {
	matchno[count]=rs.getString(1);
	matchsubject[count]=rs.getString(2);
	matchdate[count]=rs.getString(3);
	matchsender[count]=rs.getString(4);
   	count++;
   }
rs.close();

stmt.close();
connMgr.freeConnection("xavier",con);
}catch(Exception e){return quer+e.toString();}
return "suc";
}

public String fullchecknew()
{count=0;
try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
Statement stmt = con.createStatement();
ResultSet rs;
quer="select no,subject,substring(('000'+ltrim(rtrim(str(day(date))))), "+
"len('000'+ltrim(rtrim(str(day(date)))))-1,2)+'/'+  "+
"substring(('000'+ltrim(rtrim(str(month(date))))), "+
"len('000'+ltrim(rtrim(str(month(date)))))-1,2)+'/'+ "+
"ltrim(rtrim(str(year(date)))),sender,active  from message where active=1 order by date desc";

//"ltrim(rtrim(str(year(date)))),sender,active  from message where no in "+
//"(select no from messagetomgmt where mgmt ="+staffid+") "+
//"and active=1 order by date desc";
//"and active=1 and no not in (select no from messageackmgmt where mgmt='"+staffid+"') order by date desc";

rs = stmt.executeQuery(quer);
while(rs.next())
   {
	matchno[count]=rs.getString(1);
	matchsubject[count]=rs.getString(2);
	matchdate[count]=rs.getString(3);
	matchsender[count]=rs.getString(4);
   	count++;
   }
rs.close();

for(int i=0;i<count;i++)
{

quer="select count(staffid) from staff,messagetodno,messagetostaff,message where messagetodno.dno=staff.dno and messagetodno.no="+matchno[i]+" and "+
"message.no=messagetodno.no and appdate<=date and messagetostaff.no=messagetodno.no and messagetostaff.staff=staff.category and staff.active=1";

rs = stmt.executeQuery(quer);
if(rs.next()) { recipients[i]=rs.getString(1);}
rs.close();

quer="select count(staff) from messageackstaff,staff where staff=staffid and staff.active=1 and no="+matchno[i];

rs = stmt.executeQuery(quer);
if(rs.next()) { viewers[i]=rs.getString(1);}
rs.close();

	
}


stmt.close();
connMgr.freeConnection("xavier",con);
}catch(Exception e){return quer+e.toString();}
return "suc";
}

public String fullchecknew1()
{
	count=0;
try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
Statement stmt = con.createStatement();
ResultSet rs;
quer="select no,subject,substring(('000'+ltrim(rtrim(str(day(date))))), "+
"len('000'+ltrim(rtrim(str(day(date)))))-1,2)+'/'+  "+
"substring(('000'+ltrim(rtrim(str(month(date))))), "+
"len('000'+ltrim(rtrim(str(month(date)))))-1,2)+'/'+ "+
"ltrim(rtrim(str(year(date)))),sender,active  from message where active=1 "+
"and no not in (select no from messageackmgmt where mgmt='"+staffid+"') order by date desc";

//"order by date desc";

//"ltrim(rtrim(str(year(date)))),sender,active  from message where no in "+
//"(select no from messagetomgmt where mgmt ="+staffid+") "+
//"and active=1 order by date desc";
//"and active=1 and no not in (select no from messageackmgmt where mgmt='"+staffid+"') order by date desc";

rs = stmt.executeQuery(quer);
while(rs.next())
   {
	matchno[count]=rs.getString(1);
	matchsubject[count]=rs.getString(2);
	matchdate[count]=rs.getString(3);
	matchsender[count]=rs.getString(4);
   	count++;
   }
rs.close();

for(int i=0;i<count;i++)
{

quer="select count(staffid) from staff,messagetodno,messagetostaff,message where messagetodno.dno=staff.dno and messagetodno.no="+matchno[i]+" and "+
"message.no=messagetodno.no and appdate<=date and messagetostaff.no=messagetodno.no and messagetostaff.staff=staff.category and staff.active=1";

rs = stmt.executeQuery(quer);
if(rs.next()) { recipients[i]=rs.getString(1);}
rs.close();

quer="select count(staff) from messageackstaff,staff where staff=staffid and staff.active=1 and no="+matchno[i];

rs = stmt.executeQuery(quer);
if(rs.next()) { viewers[i]=rs.getString(1);}
rs.close();

	
}


stmt.close();
connMgr.freeConnection("xavier",con);
}catch(Exception e){return quer+e.toString();}
return "suc";
}


public String fullsearch()
{count=0;
if(!fday.equals("0"))
  {
  fdate=" and date >= '"+fmonth+"/"+fday+"/"+fyear+"'";
  }
  else fdate="";
if(!tday.equals("0"))
  {
  tdate=" and date <= '"+tmonth+"/"+tday+"/"+tyear+"'";
  }
  else tdate="";

try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
Statement stmt = con.createStatement();
ResultSet rs;
quer="select no,subject,substring(('000'+ltrim(rtrim(str(day(date))))), "+
"len('000'+ltrim(rtrim(str(day(date)))))-1,2)+'/'+  "+
"substring(('000'+ltrim(rtrim(str(month(date))))), "+
"len('000'+ltrim(rtrim(str(month(date)))))-1,2)+'/'+ "+
"ltrim(rtrim(str(year(date)))),sender,active  from message where active=1 and subject like '%"+subject+"%' and sender in ("+sender+") "+fdate+tdate+"  order by date desc";

rs = stmt.executeQuery(quer);
while(rs.next())
   {
	matchno[count]=rs.getString(1);
	matchsubject[count]=rs.getString(2);
	matchdate[count]=rs.getString(3);
	matchsender[count]=rs.getString(4);
   	count++;
   }
rs.close();

for(int i=0;i<count;i++)
{

quer="select count(staffid) from staff,messagetodno,messagetostaff,message where messagetodno.dno=staff.dno and messagetodno.no="+matchno[i]+" and "+
"message.no=messagetodno.no and appdate<=date and messagetostaff.no=messagetodno.no and messagetostaff.staff=staff.category and staff.active=1";

rs = stmt.executeQuery(quer);
if(rs.next()) { recipients[i]=rs.getString(1);}
rs.close();

quer="select count(staff) from messageackstaff,staff where staff=staffid and staff.active=1 and no="+matchno[i];

rs = stmt.executeQuery(quer);
if(rs.next()) { viewers[i]=rs.getString(1);}
rs.close();

	
}


stmt.close();
connMgr.freeConnection("xavier",con);
}catch(Exception e){return quer+e.toString();}
return "suc";
}




public String hodsearch()
{count=0;
if(!fday.equals("0"))
  {
  fdate=" and date >= '"+fmonth+"/"+fday+"/"+fyear+"'";
  }
  else fdate="";
if(!tday.equals("0"))
  {
  tdate=" and date <= '"+tmonth+"/"+tday+"/"+tyear+"'";
  }
  else tdate="";

try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
Statement stmt = con.createStatement();
ResultSet rs;
quer="select no,subject,substring(('000'+ltrim(rtrim(str(day(date))))), "+
"len('000'+ltrim(rtrim(str(day(date)))))-1,2)+'/'+  "+
"substring(('000'+ltrim(rtrim(str(month(date))))), "+
"len('000'+ltrim(rtrim(str(month(date)))))-1,2)+'/'+ "+
"ltrim(rtrim(str(year(date)))),sender,active  from message where no in "+
"(select no from messagetodno where dno ="+staffid+") "+
"and active=1 and subject like '%"+subject+"%' and sender in ("+sender+") "+fdate+tdate+"  order by date desc";

rs = stmt.executeQuery(quer);
while(rs.next())
   {
	matchno[count]=rs.getString(1);
	matchsubject[count]=rs.getString(2);
	matchdate[count]=rs.getString(3);
	matchsender[count]=rs.getString(4);
   	count++;
   }
rs.close();

stmt.close();
connMgr.freeConnection("xavier",con);
}catch(Exception e){return quer+e.toString();}
return "suc";
}


public String staffsearch()
{count=0;
if(!fday.equals("0"))
  {
  fdate=" and date >= '"+fmonth+"/"+fday+"/"+fyear+"'";
  }
  else fdate="";
if(!tday.equals("0"))
  {
  tdate=" and date <= '"+tmonth+"/"+tday+"/"+tyear+"'";
  }
  else tdate="";

try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
Statement stmt = con.createStatement();
ResultSet rs;
quer="select no,subject,substring(('000'+ltrim(rtrim(str(day(date))))), "+
"len('000'+ltrim(rtrim(str(day(date)))))-1,2)+'/'+  "+
"substring(('000'+ltrim(rtrim(str(month(date))))), "+
"len('000'+ltrim(rtrim(str(month(date)))))-1,2)+'/'+ "+
"ltrim(rtrim(str(year(date)))),sender,active  from message where no in "+
"(select no from messagetodno where dno in "+
"(select dno from staff where staffid='"+staffid+"') and no in "+
"(select no from messagetostaff where staff in "+
"(select category from staff where staffid='"+staffid+"'))) "+
"and active=1 and subject like '%"+subject+"%' and sender in ("+sender+") "+fdate+tdate+"  order by date desc";

rs = stmt.executeQuery(quer);
while(rs.next())
   {
	matchno[count]=rs.getString(1);
	matchsubject[count]=rs.getString(2);
	matchdate[count]=rs.getString(3);
	matchsender[count]=rs.getString(4);
   	count++;
   }
rs.close();

stmt.close();
connMgr.freeConnection("xavier",con);
}catch(Exception e){return quer+e.toString();}
return "suc";
}






public String Match(int search)
{count=0;

try
{
DBConnectionManager connMgr;
quer="a";
 connMgr = DBConnectionManager.getInstance();
quer="b";
Connection con = connMgr.getConnection("xavier");
quer="c";
Statement stmt = con.createStatement();
quer="d";
ResultSet rs;
quer="e";
if(search==1){
quer="select no,subject,substring(('000'+ltrim(rtrim(str(day(date))))), "+
"len('000'+ltrim(rtrim(str(day(date)))))-1,2)+'/'+ "+
"substring(('000'+ltrim(rtrim(str(month(date))))), "+
"len('000'+ltrim(rtrim(str(month(date)))))-1,2)+'/'+ "+
"ltrim(rtrim(str(year(date)))),sender from message where no="+no+" order by date desc";
rs = stmt.executeQuery(quer);
while(rs.next())
   {
	matchno[count]=rs.getString(1);
	matchsubject[count]=rs.getString(2);
	matchdate[count]=rs.getString(3);
	matchsender[count]=rs.getString(4);
   	count++;
   }
rs.close();
}
if(search==2){
	
quer="select no,subject,substring(('000'+ltrim(rtrim(str(day(date))))), "+
"len('000'+ltrim(rtrim(str(day(date)))))-1,2)+'/'+ "+
"substring(('000'+ltrim(rtrim(str(month(date))))), "+
"len('000'+ltrim(rtrim(str(month(date)))))-1,2)+'/'+ "+
"ltrim(rtrim(str(year(date)))),sender from message "+
"where subject like '"+name+"%' order by date desc";
	

rs = stmt.executeQuery(quer);
while(rs.next())
   {
	matchno[count]=rs.getString(1);
	matchsubject[count]=rs.getString(2);
	matchdate[count]=rs.getString(3);
	matchsender[count]=rs.getString(4);
   	count++;
   }
rs.close();


quer="select no,subject,substring(('000'+ltrim(rtrim(str(day(date))))), "+
"len('000'+ltrim(rtrim(str(day(date)))))-1,2)+'/'+ "+
"substring(('000'+ltrim(rtrim(str(month(date))))), "+
"len('000'+ltrim(rtrim(str(month(date)))))-1,2)+'/'+ "+
"ltrim(rtrim(str(year(date)))),sender from message "+
"where  subject like '%"+name+"%' and subject not like '"+name+"%'  order by date desc";



rs = stmt.executeQuery(quer);
while(rs.next())
   {
	matchno[count]=rs.getString(1);
	matchsubject[count]=rs.getString(2);
	matchdate[count]=rs.getString(3);
	matchsender[count]=rs.getString(4);
   	count++;
   }
rs.close();
}

for(int i=0;i<count;i++)
{

quer="select count(staffid) from staff,messagetodno,messagetostaff,message where messagetodno.dno=staff.dno and messagetodno.no="+matchno[i]+" and "+
"message.no=messagetodno.no and appdate<=date and messagetostaff.no=messagetodno.no and messagetostaff.staff=staff.category and staff.active=1";

rs = stmt.executeQuery(quer);
if(rs.next()) { recipients[i]=rs.getString(1);}
rs.close();

quer="select count(staff) from messageackstaff,staff where staff=staffid and staff.active=1 and no="+matchno[i];

rs = stmt.executeQuery(quer);
if(rs.next()) { viewers[i]=rs.getString(1);}
rs.close();

	
}


stmt.close();
connMgr.freeConnection("xavier",con);
}catch(Exception e){return quer+e.toString();}
return "suc";
}



public int InsertCheck()
   {
int found=0;
try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
Statement stmt = con.createStatement();
ResultSet rs = stmt.executeQuery("select * from message where no="+no);
if(rs.next())
{
found=1;
}
rs.close();
stmt.close();
connMgr.freeConnection("xavier",con);
}catch(Exception e){}
return found; 
   }
public String Insert()
{
	
int vie=InsertCheck();
if(no==0 || vie==1)Maxno();	
try
{

DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
PreparedStatement ps = con.prepareStatement("insert into message values (?,?,?,?,?)");
ps.setInt(1,no);
ps.setString(2,sender);
ps.setString(3,date);
ps.setInt(4,active);
ps.setString(5,subject);
ps.executeUpdate();
ps.close();
for(int i=0;i<dnocount;i++)
    {
	ps = con.prepareStatement("insert into messagetodno values (?,?)");
	ps.setInt(1,no);
	ps.setString(2,dno[i]);
	ps.executeUpdate();
	ps.close();    
	}
for(int i=0;i<staffcount;i++)
    {
	ps = con.prepareStatement("insert into messagetostaff values (?,?)");
	ps.setInt(1,no);
	ps.setString(2,staff[i]);
	ps.executeUpdate();
	ps.close();    
	}
for(int i=0;i<mgmtcount;i++)
    {
	ps = con.prepareStatement("insert into messagetomgmt values (?,?)");
	ps.setInt(1,no);
	ps.setString(2,mgmt[i]);
	ps.executeUpdate();
	ps.close();    
	}

connMgr.freeConnection("xavier",con);
FileOutputStream fosStream = new FileOutputStream("d:\\tomcat\\webapps\\root\\full\\message\\data\\"+no+".ee");
	PrintWriter pwStream = new PrintWriter(fosStream);



	pwStream.println(body);
	pwStream.close();

}catch(Exception e){return "Error !! "+ e.toString();}
return("success");
}



public String InsertStaffack()
{
	
try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
PreparedStatement ps;
ps = con.prepareStatement("delete from messageackstaff where no=? and staff=?");
ps.setInt(1,no);
ps.setString(2,staffid);
ps.executeUpdate();
ps.close();
ps = con.prepareStatement("insert into messageackstaff values (?,?)");
ps.setInt(1,no);
ps.setString(2,staffid);
ps.executeUpdate();
ps.close();
connMgr.freeConnection("xavier",con);
}catch(Exception e){return "Error !! "+ e.toString();}
return("success");
}


public String Inserthodack()
{
	
try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
PreparedStatement ps;
ps = con.prepareStatement("delete from messageackdno where no=? and dno=?");
ps.setInt(1,no);
ps.setString(2,staffid);
ps.executeUpdate();
ps.close();
ps = con.prepareStatement("insert into messageackdno values (?,?)");
ps.setInt(1,no);
ps.setString(2,staffid);
ps.executeUpdate();
ps.close();
connMgr.freeConnection("xavier",con);
}catch(Exception e){return "Error !! "+ e.toString();}
return("success");
}

public String Insertfullack()
{
	
try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
PreparedStatement ps;
ps = con.prepareStatement("delete from messageackmgmt where no=? and mgmt=?");
ps.setInt(1,no);
ps.setString(2,staffid);
ps.executeUpdate();
ps.close();
ps = con.prepareStatement("insert into messageackmgmt values (?,?)");
ps.setInt(1,no);
ps.setString(2,staffid);
ps.executeUpdate();
ps.close();
connMgr.freeConnection("xavier",con);
}catch(Exception e){return "Error !! "+ e.toString();}
return("success");
}




public String Delete()
{
try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
File ff= new File("d:\\tomcat\\webapps\\root\\full\\message\\data\\"+no+".ee");
ff.delete();

PreparedStatement ps;


ps = con.prepareStatement("delete from messagetodno where no=?");
ps.setInt(1,no);
ps.executeUpdate();
ps.close();

ps = con.prepareStatement("delete from messagetomgmt where no=?");
ps.setInt(1,no);
ps.executeUpdate();
ps.close();

ps = con.prepareStatement("delete from messagetostaff where no=?");
ps.setInt(1,no);
ps.executeUpdate();
ps.close();

ps = con.prepareStatement("delete from messageackdno where no=?");
ps.setInt(1,no);
ps.executeUpdate();
ps.close();

ps = con.prepareStatement("delete from messageackmgmt where no=?");
ps.setInt(1,no);
ps.executeUpdate();
ps.close();

ps = con.prepareStatement("delete from messageackstaff where no=?");
ps.setInt(1,no);
ps.executeUpdate();
ps.close();



ps = con.prepareStatement("delete from message where no=?");
ps.setInt(1,no); 
ps.executeUpdate();
ps.close();
connMgr.freeConnection("xavier",con);
}catch(Exception e){return e.toString();}
return("success");
}

public String Update()
{
try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
PreparedStatement ps = con.prepareStatement("update message set sender=?,date=?,active=?,subject=? where no=?");
ps.setString(1,sender);
ps.setString(2,date);
ps.setInt(3,active);
ps.setString(4,subject);
ps.setInt(5,no);
ps.executeUpdate();
ps.close();

ps = con.prepareStatement("delete from messagetodno where no=?");
ps.setInt(1,no);
ps.executeUpdate();
ps.close();

ps = con.prepareStatement("delete from messagetomgmt where no=?");
ps.setInt(1,no);
ps.executeUpdate();
ps.close();

ps = con.prepareStatement("delete from messagetostaff where no=?");
ps.setInt(1,no);
ps.executeUpdate();
ps.close();

for(int i=0;i<dnocount;i++)
    {
	ps = con.prepareStatement("insert into messagetodno values (?,?)");
	ps.setInt(1,no);
	ps.setString(2,dno[i]);
	ps.executeUpdate();
	ps.close();    
	}
for(int i=0;i<staffcount;i++)
    {
	ps = con.prepareStatement("insert into messagetostaff values (?,?)");
	ps.setInt(1,no);
	ps.setString(2,staff[i]);
	ps.executeUpdate();
	ps.close();    
	}
for(int i=0;i<mgmtcount;i++)
    {
	ps = con.prepareStatement("insert into messagetomgmt values (?,?)");
	ps.setInt(1,no);
	ps.setString(2,mgmt[i]);
	ps.executeUpdate();
	ps.close();    
	}



connMgr.freeConnection("xavier",con);
FileOutputStream fosStream = new FileOutputStream("d:\\tomcat\\webapps\\root\\full\\message\\data\\"+no+".ee");
	PrintWriter pwStream = new PrintWriter(fosStream);
	pwStream.println(body);
	pwStream.close();
}catch(Exception e){return e.toString();}
return("success");
}




public int View()
{
String quer="";
int found=0;
try
{
quer="select no,sender,substring(('000'+ltrim(rtrim(str(day(date))))), "+
"len('000'+ltrim(rtrim(str(day(date)))))-1,2)+'/'+ "+
"substring(('000'+ltrim(rtrim(str(month(date))))), "+
"len('000'+ltrim(rtrim(str(month(date)))))-1,2)+'/'+ "+
"ltrim(rtrim(str(year(date)))),active,subject from message where no="+no;

DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
Statement stmt = con.createStatement();
ResultSet rs = stmt.executeQuery(quer);
if(rs.next())
{
no=rs.getInt(1);
sender=rs.getString(2);
date=rs.getString(3);
active=rs.getInt(4);
subject=rs.getString(5);
found=1;
}
rs.close();

quer="select * from messagetodno where no="+no;
rs = stmt.executeQuery(quer);
while(rs.next())
  {
  	dno[dnocount]=rs.getString(2);
  	dnocount++;
  }
rs.close();  

quer="select * from messagetomgmt where no="+no;
rs = stmt.executeQuery(quer);
while(rs.next())
  {
  	mgmt[mgmtcount]=rs.getString(2);
  	mgmtcount++;
  }
rs.close();  


quer="select * from messagetostaff where no="+no;
rs = stmt.executeQuery(quer);
while(rs.next())
  {
  	staff[staffcount]=rs.getString(2);
  	staffcount++;
  }
rs.close();  



stmt.close();
connMgr.freeConnection("xavier",con);
}catch(Exception e){}
return found; 
}

public int ack()
{
String quer="";
int found=0;
try
{
//quer="select staffid,name,cname,dname from messageackstaff,staff,department,staffdesignation "+
//"where slno=designation and department.dno=staff.dno and staff=staffid and no="+no+" order by staffid";

quer="select q1.staffid,q1.name,q1.cname,q1.dname,ISNULL(q2.staff,'x') from "+
"(select staffid,name,cname,dname from staff,department,staffdesignation,messagetodno,messagetostaff,message "+
"where slno=designation and department.dno=staff.dno and messagetodno.dno=staff.dno and messagetodno.no="+no+" and "+
"message.no=messagetodno.no and appdate<=date and messagetostaff.no=messagetodno.no and messagetostaff.staff=staff.category and staff.active=1) q1 "+
"left outer join (select staff from messageackstaff where no="+no+") q2 on q1.staffid=q2.staff order by q1.staffid";


DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
Statement stmt = con.createStatement();
ResultSet rs = stmt.executeQuery(quer);
count=0;
while(rs.next())
{
	matchno[count]=rs.getString(1);
	matchsubject[count]=rs.getString(2);
	matchdate[count]=rs.getString(3);
	matchsender[count]=rs.getString(4);
	pstaffid[count]=rs.getString(5);
   	count++;
}
rs.close();




stmt.close();
connMgr.freeConnection("xavier",con);
}catch(Exception e){}
return found; 
}


public int staffcount()
{
	int found=0;
quer="";
try
{
quer="select count(no) from message where no in "+
"(select no from messagetodno where dno in "+
"(select dno from staff where staffid='"+staffid+"') and no in "+
"(select no from messagetostaff where staff in "+
"(select category from staff where staffid='"+staffid+"'))) "+
"and active=1 and no not in (select no from messageackstaff where staff='"+staffid+"')";

DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
Statement stmt = con.createStatement();
ResultSet rs = stmt.executeQuery(quer);
if(rs.next())
{
found=rs.getInt(1);
}
rs.close();


stmt.close();
connMgr.freeConnection("xavier",con);
}catch(Exception e){return 0;}
return found; 
}

public int hodcount()
{
	int found=0;
quer="";
try
{
quer="select count(no) from message where no in "+
"(select no from messagetodno where dno ="+staffid+") "+
"and active=1 and no not in (select no from messageackdno where dno="+staffid+")";

DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
Statement stmt = con.createStatement();
ResultSet rs = stmt.executeQuery(quer);
if(rs.next())
{
found=rs.getInt(1);
}
rs.close();


stmt.close();
connMgr.freeConnection("xavier",con);
}catch(Exception e){return 0;}
return found; 
}

public int fullcount()
{
	int found=0;
quer="";
try
{
quer="select count(no) from message where no in "+
"(select no from messagetomgmt where mgmt ="+staffid+") "+
"and active=1 and no not in (select no from messageackmgmt where mgmt="+staffid+")";

DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
Statement stmt = con.createStatement();
ResultSet rs = stmt.executeQuery(quer);
if(rs.next())
{
found=rs.getInt(1);
}
rs.close();


stmt.close();
connMgr.freeConnection("xavier",con);
}catch(Exception e){return 0;}
return found; 
}



public void setmatchsender(String[] matchsender) 
{ 
this.matchsender = matchsender; 
} 
public String[] getmatchsender() 
{ 
return this.matchsender; 
} 



public void setmatchno(String[] matchno) 
{ 
this.matchno = matchno; 
} 
public String[] getmatchno() 
{ 
return this.matchno; 
} 



public void setmatchdate(String[] matchdate) 
{ 
this.matchdate = matchdate; 
} 
public String[] getmatchdate() 
{ 
return this.matchdate; 
} 



public void setmatchsubject(String[] matchsubject) 
{ 
this.matchsubject = matchsubject; 
} 
public String[] getmatchsubject() 
{ 
return this.matchsubject; 
} 



public void setstaff(String[] staff) 
{ 
this.staff = staff; 
} 
public String[] getstaff() 
{ 
return this.staff; 
} 

public void setdno(String[] dno) 
{ 
this.dno = dno; 
} 
public String[] getdno() 
{ 
return this.dno; 
} 

public void setmgmt(String[] mgmt) 
{ 
this.mgmt = mgmt; 
} 
public String[] getmgmt() 
{ 
return this.mgmt; 
} 

public void setpstaffid(String[] pstaffid) 
{ 
this.pstaffid = pstaffid; 
} 
public String[] getpstaffid() 
{ 
return this.pstaffid; 
} 

public void setpname(String[] pname) 
{ 
this.pname = pname; 
} 
public String[] getpname() 
{ 
return this.pname; 
} 

public void setpdname(String[] pdname) 
{ 
this.pdname = pdname; 
} 
public String[] getpdname() 
{ 
return this.pdname; 
} 







 public void setcount(int count) 
{ 
this.count = count; 
} 
public int getcount() 
{ 
return this.count; 
} 



 public void setmgmtcount(int mgmtcount) 
{ 
this.mgmtcount = mgmtcount; 
} 
public int getmgmtcount() 
{ 
return this.mgmtcount; 
} 

 public void setdnocount(int dnocount) 
{ 
this.dnocount = dnocount; 
} 
public int getdnocount() 
{ 
return this.dnocount; 
} 

 public void setstaffcount(int staffcount) 
{ 
this.staffcount = staffcount; 
} 
public int getstaffcount() 
{ 
return this.staffcount; 
} 



  
public void setname( String name) 
{ 
this.name = name; 
} 
public String getname() 
{ 
return this.name; 
} 
public void setno(int no) 
{ 
this.no = no; 
} 
public int getno() 
{ 
return this.no; 
} 


public void setrecipients(String[] recipients) 
{ 
this.recipients = recipients; 
} 
public String[] getrecipients() 
{ 
return this.recipients; 
} 

public void setviewers(String[] viewers) 
{ 
this.viewers = viewers; 
} 
public String[] getviewers() 
{ 
return this.viewers; 
} 


public void setsender(String sender) 
{ 
this.sender = sender; 
} 
public String getsender() 
{ 
return this.sender; 
} 

public void setactive(int active) 
{ 
this.active = active; 
} 
public int getactive() 
{ 
return this.active; 
} 

 public void setdate(String date) 
{ 
this.date = date; 
} 
public String getdate() 
{ 
return this.date; 
} 

 public void setbody(String body) 
{ 
this.body = body; 
} 
public String getbody() 
{ 
return this.body; 
} 

 public void setsubject(String subject) 
{ 
this.subject = subject; 
} 
public String getsubject() 
{ 
return this.subject; 
} 

 public void setquer(String quer) 
{ 
this.quer = quer; 
} 
public String getquer() 
{ 
return this.quer; 
} 

 public void setstaffid(String staffid) 
{ 
this.staffid = staffid; 
} 
public String getstaffid() 
{ 
return this.staffid; 
} 

 public void sethod(String hod) 
{ 
this.hod = hod; 
} 
public String gethod() 
{ 
return this.hod; 
} 

 public void setmanagement(String management) 
{ 
this.management = management; 
} 
public String getmanagement() 
{ 
return this.management; 
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

