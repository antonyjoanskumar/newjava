package attendance;
import dd.DBConnectionManager;
import java.sql.*;
public class attendancereport
{
String rollno[]=new String[200];
String year="";
String dno="";
String semester="";
String dname="";
String name[]=new String[200];
String fday="";
String fmonth="";
String fyear="";
int datecount=0;
String tday="";
String tmonth="";
String tyear="";
String fdate="";
String tdate="";
String academicyear="";
String date="";
int noofhoursabsent[] = new int[200];
int noofhoursod[] = new int[200];
int noofhours[] = new int[200];

//int noofhours=0;
float percentage[] = new float[200];
int count=0;
String quer="";
String attendance[][]= new String[300][15];

int tutors[][][]=new int[100][500][10];
String tutorsdate[][] = new String [100][500];
int tutorsdatecount[]=new int[500];

public String init()
  {
  	int returnvalue=0;
  	fdate=fmonth+"/"+fday+"/"+fyear;
  	tdate=tmonth+"/"+tday+"/"+tyear;
  	date=fdate;
	academic();
	Classselect();  	
if(count>0)
{
DBConnectionManager connMgr=null; Connection con =null;
	try
	{
connMgr = DBConnectionManager.getInstance(); 
con = connMgr.getConnection("attendance");
Statement stmt = con.createStatement();
ResultSet rs;
rs = stmt.executeQuery("execute noofperiodu "+dno+","+year+",'"+academicyear+"','"+fdate+"','"+tdate+"'");
  if(rs.next())
     {
     	noofhours[0]=rs.getInt(1);
     }
rs.close();  
stmt.close();
}catch(Exception e){return e.toString();}
finally{connMgr.freeConnection("attendance",con);}
}
return "suc";
}
  
  
int fin(String str1,char str2)
  {
  int found=1;
  for(int i=0;i<str1.length();i++)
     if(str1.charAt(i)==str2){found=0;break;}
  return found;
  }
int searc(String str1,String str2)
   {
   int found=0;
   for(int i=0;i<str1.length();i++)
     found+=fin(str2,str1.charAt(i));
   return found;
   }


public String bensirattendance()
   {

  	fdate=fmonth+"/"+fday+"/"+fyear;
  	tdate=tmonth+"/"+tday+"/"+tyear;
String q="A";
DBConnectionManager connMgr=null; Connection con =null;
	try
	{
connMgr = DBConnectionManager.getInstance(); 
con = connMgr.getConnection("attendance");
Statement stmt = con.createStatement();
ResultSet rs;
String temphours="";
String tempdate[][] = new String[100][500];
count=0;
q="select sxcce.dbo.stud.rollno,sxcce.dbo.stud.name from sxcce.dbo.stud,sxcce.dbo.student where sxcce.dbo.stud.rollno=sxcce.dbo.student.rollno and sxcce.dbo.student.active=1 and sxcce.dbo.stud.department="+dno+" and sxcce.dbo.stud.year="+year+" and academicyear='"+academicyear+"' order by sxcce.dbo.stud.rollno";
rs = stmt.executeQuery(q);
  while(rs.next())
     {
        rollno[count]=rs.getString(1);
     	name[count]=rs.getString(2);
     	count++;
     }
     
rs.close();  

rs = stmt.executeQuery("select dname from sxcce.dbo.department where dno="+dno);
  if(rs.next())
     {
     	dname=rs.getString(1);
     }
     
rs.close();  


for(int i=0;i<count;i++){
rs = stmt.executeQuery("select date,substring(('000'+ltrim(rtrim(str(day(date))))),"+
"len('000'+ltrim(rtrim(str(day(date)))))-1,2)+'/'+ "+
"substring(('000'+ltrim(rtrim(str(month(date))))), "+
"len('000'+ltrim(rtrim(str(month(date)))))-1,2)+'/'+ "+
"ltrim(rtrim(str(year(date)))),hours from attendance where rollno="+rollno[i]+" and date>='"+fdate+"' and date <='"+tdate+"'");
tutorsdatecount[i]=0;
  while(rs.next())
     {
     tempdate[i][tutorsdatecount[i]]=rs.getString(1);
     tutorsdate[i][tutorsdatecount[i]]=rs.getString(2);
     temphours=rs.getString(3);
     
     for(int z=0;z<temphours.length();z++)
        tutors[i][tutorsdatecount[i]][Integer.parseInt(temphours.substring(z,z+1))]=1;
     
     tutorsdatecount[i]++;
     }
     
rs.close();  
}

//for(int i=0;i<count;i++)
//   {

//rs = stmt.executeQuery("select name from sxcce.dbo.student where rollno="+rollno[i]);
//  if(rs.next())
//     {
//     	name[i]=rs.getString(1);
//     }
     
//rs.close();  
  	
//   }

for(int i=0;i<count;i++)
for(int j=0;j<tutorsdatecount[i];j++)
{
rs = stmt.executeQuery("select hours from od where rollno="+rollno[i]+" and date='"+tempdate[i][j]+"'");
  if(rs.next())
     {
     temphours=rs.getString(1);
     
     for(int z=0;z<temphours.length();z++)
        tutors[i][j][Integer.parseInt(temphours.substring(z,z+1))]=0;
     }
     
rs.close();  
}



   stmt.close();
}catch(Exception e){return e.toString()+count+dname+q+fdate+tdate;}
finally{connMgr.freeConnection("attendance",con);}



return "suc";
} 
  
  
  
  
public String viewattendance()
  {
  	datediffer();

String tempdate[] = new String[1000];
String temphours[] = new String[1000];
int tempcount=0;

DBConnectionManager connMgr=null; Connection con =null;
	try
	{
connMgr = DBConnectionManager.getInstance(); 
con = connMgr.getConnection("attendance");
 
Statement stmt = con.createStatement();
ResultSet rs;
for(int i=0;i<count;i++)
{
	/*
	tempcount=0;
rs = stmt.executeQuery("select sum(len(hours)) from attendance where rollno="+rollno[i]+" and date>='"+fdate+"' and date <='"+tdate+"'");
  if(rs.next())
     {
     	noofhoursabsent[i]=rs.getInt(1);
     }
rs.close();  
rs = stmt.executeQuery("select sum(len(hours)) from od where rollno="+rollno[i]+" and date>='"+fdate+"' and date <='"+tdate+"'");
  if(rs.next())
     {
     	noofhoursod[i]=rs.getInt(1);
     }
rs.close();  


rs = stmt.executeQuery("select date,hours from od where rollno="+rollno[i]+" and date>='"+fdate+"' and date <='"+tdate+"'");
  while(rs.next())
     {
     	tempdate[tempcount]=rs.getString(1);
     	temphours[tempcount]=rs.getString(2);
     	tempcount++;
     }
rs.close();  

for(int ii=0;ii<tempcount;ii++)
   {
   	String tripleh="";
	rs = stmt.executeQuery("select hours from attendance where rollno="+rollno[i]+" and date='"+tempdate[ii]+"'");
  if(rs.next())
     {
     	tripleh=rs.getString(1);
     }
	rs.close();  
   noofhoursabsent[i]+=searc(temphours[ii],tripleh);   	
   }




int temp=noofhoursabsent[i]-noofhoursod[i];
if(noofhours>0)
percentage[i]=1/(float)noofhours*(float)(noofhours-temp)*100;
*/



String hours="",ab="",od="",blank="";
  	date=fdate;

for(int j=0;j<datecount;j++)
  {

  	for(int z=0;z<10;z++)
  	  attendance[j][z]="";
  rs = stmt.executeQuery("execute individualattendanceview "+dno+","+year+","+rollno[i]+",'"+date+"','"+academicyear+"'");

  if(rs.next())
     {
     	hours=rs.getString(1);
     	ab=rs.getString(2);
     	od=rs.getString(3);
     	blank=rs.getString(4);
 //    	dispdate[j]=vdate;
     	for(int h=0;h<hours.length();h++)
     	  attendance[j][Integer.parseInt(hours.substring(h,h+1))]="/";
     	for(int h=0;h<ab.length();h++)
     	  attendance[j][Integer.parseInt(ab.substring(h,h+1))]="ab";
     	for(int h=0;h<od.length();h++)
     	   if(attendance[j][Integer.parseInt(od.substring(h,h+1))]=="ab")
     	  attendance[j][Integer.parseInt(od.substring(h,h+1))]="od";
     	for(int h=0;h<blank.length();h++)
     	  attendance[j][Integer.parseInt(blank.substring(h,h+1))]="NA";
     	  
     }
  rs.close();  
  dateadd();
  }
noofhours[i]=0;
noofhoursabsent[i]=0;
noofhoursod[i]=0;
for(int j=0;j<datecount;j++)
  {
  	for(int z=0;z<10;z++)
  	{
  	 if(attendance[j][z]=="/") noofhours[i]+=1;
  	 if(attendance[j][z]=="ab") noofhoursabsent[i]+=1;
  	 if(attendance[j][z]=="od") noofhoursod[i]+=1;
  	}
  	  	  
  }
  noofhours[i]+=noofhoursabsent[i]+noofhoursod[i];
if(noofhours[i]>0)
percentage[i]=(float)(noofhours[i]-noofhoursabsent[i])/(float)(noofhours[i])*100;

//percentage[i]=(float)(noofhours[i]-noofhoursabsent[i])/(float)450*100;

if(percentage[i]>100)percentage[i]=100;

//percentage[i]=(float)(1/(float)noofhours[i]*(float)(noofhours-temp)*100);

attperwrite();

}
//  dateadd();
stmt.close();

}catch(Exception e){return e.toString()+dno+" - "+year+" - "+rollno[0]+" - "+date+" - "+academicyear;}
finally{connMgr.freeConnection("attendance",con);}
return "suc";
}

public String attperwrite()
{
quer=" ";
DBConnectionManager connMgr=null; Connection con =null;
	try
	{
connMgr = DBConnectionManager.getInstance(); 
con = connMgr.getConnection("xavier");
PreparedStatement ps;
for(int i=0;i<count;i++)
{
ps = con.prepareStatement("delete from attper where rollno=? and semester=?");
ps.setString(1,rollno[i]);
ps.setString(2,semester);
int aj = ps.executeUpdate();
ps.close();

ps = con.prepareStatement("insert into attper values ( ?,?,?,?,?,?,?)");
ps.setString(1,rollno[i]);
ps.setString(2,semester);
ps.setInt(3,noofhours[i]);
ps.setInt(4,noofhours[i]-noofhoursabsent[i]);
ps.setInt(5,noofhoursabsent[i]);
ps.setInt(6,noofhoursod[i]);
ps.setInt(7,(int)percentage[i]);
int ak = ps.executeUpdate();
ps.close();

}
}catch(Exception e){return e.toString();}
finally{connMgr.freeConnection("xavier",con);}

return "sus"; 
}


public void datediffer()
{
try
{
Connection con = DriverManager.getConnection("jdbc:odbc:xavier","sxcce","tamil5india6");
Statement stmt = con.createStatement();
ResultSet rs = stmt.executeQuery("select datediff(d,'"+fdate+"','"+tdate+"')");
if(rs.next())
{
	datecount=rs.getInt(1)+1;
}
rs.close();
stmt.close();
con.close();
}catch(Exception e){}

   }

public void dateadd()
{
	String month="",day="",year="";
try
{

Connection con = DriverManager.getConnection("jdbc:odbc:xavier","sxcce","tamil5india6");
Statement stmt = con.createStatement();
ResultSet rs = stmt.executeQuery("select month(dateadd(day,1,'"+date+"')),day(dateadd(day,1,'"+date+"')),year(dateadd(day,1,'"+date+"'))");
if(rs.next())
{
	month=rs.getString(1);
	day=rs.getString(2);
	year=rs.getString(3);
	date=month+"/"+day+"/"+year;
  	if(day.length()==1)day="0"+day;
  	if(month.length()==1)month="0"+month;
}
rs.close();
stmt.close();
con.close();
}catch(Exception e){}
}


public void academic()
{
String semesters="";
if(year.equals("1"))semesters="1,2";
else if(year.equals("2"))semesters="3,4";
else if(year.equals("3"))semesters="5,6";
else if(year.equals("4"))semesters="7,8";
String quer="select academicyear,semester from reopening where '"+date+"' between "+
"opening and closing and dno="+dno+" and semester in("+semesters+")";
DBConnectionManager connMgr=null; Connection con =null;
	try
	{
connMgr = DBConnectionManager.getInstance(); 
con = connMgr.getConnection("attendance");
Statement stmt = con.createStatement();
ResultSet rs = stmt.executeQuery(quer);
if(rs.next())
{
	academicyear=rs.getString(1);
	semester=rs.getString(2);
}
rs.close();
stmt.close();
}catch(Exception e){}
finally{connMgr.freeConnection("attendance",con);}

}

public String Classselect()
{count=0;
academic();
DBConnectionManager connMgr=null; Connection con =null;
	try
	{
connMgr = DBConnectionManager.getInstance(); 
con = connMgr.getConnection("xavier");
quer="select stud.rollno,stud.name from stud,student where stud.rollno=student.rollno and student.active=1 and stud.department="+dno+" and stud.year="+year+" and "+
"academicyear='"+academicyear+"' order by stud.rollno";
Statement stmt = con.createStatement();
ResultSet rs = stmt.executeQuery(quer);
while(rs.next())
{
	rollno[count]=rs.getString(1);
	name[count]=rs.getString(2);
	count++;
}
rs.close();
stmt.close();
}catch(Exception e){return e.toString();}
finally{connMgr.freeConnection("xavier",con);}

return "sus"; 
}



public void setcount(int count) 
{ 
this.count = count; 
} 
public int getcount() 
{ 
return this.count; 
} 

public void setpercentage(float[] percentage) 
{ 
this.percentage = percentage; 
} 
public float[] getpercentage() 
{ 
return this.percentage; 
} 


public void setnoofhours(int[] noofhours) 
{ 
this.noofhours = noofhours; 
} 
public int[] getnoofhours() 
{ 
return this.noofhours; 
} 


public void setnoofhoursabsent(int[] noofhoursabsent) 
{ 
this.noofhoursabsent = noofhoursabsent; 
} 
public int[] getnoofhoursabsent() 
{ 
return this.noofhoursabsent; 
} 


public void setnoofhoursod(int[] noofhoursod) 
{ 
this.noofhoursod = noofhoursod; 
} 
public int[] getnoofhoursod() 
{ 
return this.noofhoursod; 
} 

public void setdate(String date) 
{ 
this.date = date; 
} 
public String getdate() 
{ 
return this.date; 
} 
public void setdno(String dno) 
{ 
this.dno = dno; 
} 
public String getdno() 
{ 
return this.dno; 
} 
public void setfdate(String fdate) 
{ 
this.fdate = fdate; 
} 
public String getfdate() 
{ 
return this.fdate; 
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
public void setrollno(String[] rollno) 
{ 
this.rollno = rollno; 
} 
public String[] getrollno() 
{ 
return this.rollno; 
} 
public void setsemester(String semester) 
{ 
this.semester = semester; 
} 
public String getsemester() 
{ 
return this.semester; 
} 
public void settdate(String tdate) 
{ 
this.tdate = tdate; 
} 
public String gettdate() 
{ 
return this.tdate; 
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
public String gettyear() 
{ 
return this.tyear; 
} 
public void setyear(String year) 
{ 
this.year = year; 
} 
public String getyear() 
{ 
return this.year; 
} 

public void setname(String[] name) 
{ 
this.name = name; 
} 
public String[] getname() 
{ 
return this.name; 
} 

public void setacademicyear(String academicyear) 
{ 
this.academicyear = academicyear; 
} 
public String getacademicyear() 
{ 
return this.academicyear; 
} 


public void setquer(String quer) 
{ 
this.quer = quer; 
} 
public String getquer() 
{ 
return this.quer; 
} 

public String getdname() 
{ 
return this.dname; 
} 


public void settutors(int[][][] tutors) 
{ 
this.tutors = tutors; 
} 
public int[][][] gettutors() 
{ 
return this.tutors; 
} 


public void settutorsdatecount(int[] tutorsdatecount) 
{ 
this.tutorsdatecount = tutorsdatecount; 
} 
public int[] gettutorsdatecount() 
{ 
return this.tutorsdatecount; 
} 


public void settutorsdate(String[][] tutorsdate) 
{ 
this.tutorsdate = tutorsdate; 
} 
public String[][] gettutorsdate() 
{ 
return this.tutorsdate; 
} 



}
