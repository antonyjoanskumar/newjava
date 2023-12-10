package attendance;
import dd.DBConnectionManager;
import java.sql.*;
public class attendancestud
{
String rollno="";
String year="";
String dno="";
String semester="";
String name="";
String fday="";
String fmonth="";
String fyear="";
String tday="";
String tmonth="";
String tyear="";
String fdate="";
String tdate="";
String academicyear="";
String date="";
String vdate="";
int noofhoursabsent=0;
int noofhoursod=0;
int noofhoursblank=0;
int noofhours=0;
float percentage=0;
float percentagewod=0;
String dispdate[] = new String[500];
String attendance[][]= new String[500][15];
int count=0;

public int init()
  {
  	int returnvalue=0;
  	fdate=fmonth+"/"+fday+"/"+fyear;
  	tdate=tmonth+"/"+tday+"/"+tyear;
  	date=fdate;
  	if(fday.length()==1)fday="0"+fday;
  	if(fmonth.length()==1)fmonth="0"+fmonth;
  	vdate=fday+"/"+fmonth+"/"+fyear;
DBConnectionManager connMgr=null; Connection con =null;
	try
	{
connMgr = DBConnectionManager.getInstance(); 
con = connMgr.getConnection("attendance");
	Statement stmt = con.createStatement();
	ResultSet rs;
	rs = stmt.executeQuery("execute yearselect "+rollno+",'"+fdate+"','"+tdate+"'");
    if(rs.next())
       {
       	academicyear=rs.getString(1);
       	year=rs.getString(2);
       	dno=rs.getString(3);
       	semester=rs.getString(4);
       	name=rs.getString(5);
       }
	rs.close();  
	stmt.close();
	if(academicyear.length()>2 && Integer.parseInt(year)>0 && Integer.parseInt(dno)>0 && Integer.parseInt(semester)>0)returnvalue=1;
  	  }catch(Exception e){return returnvalue;}
  	  finally{connMgr.freeConnection("attendance",con);}

  return returnvalue;	  
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



  
public String viewattendance()
  {
  	String error="succ";
  	datediffer();
DBConnectionManager connMgr=null; Connection con =null;
	try
	{
connMgr = DBConnectionManager.getInstance(); 
con = connMgr.getConnection("attendance");
String tempdate[] = new String[1000];
String temphours[] = new String[1000];
int tempcount=0;

Statement stmt = con.createStatement();
ResultSet rs;
/*
rs = stmt.executeQuery("select sum(len(hours)) from attendance where rollno="+rollno+" and date>='"+fdate+"' and date <='"+tdate+"'");
  if(rs.next())
     {
     	noofhoursabsent=rs.getInt(1);
     }
rs.close();  
rs = stmt.executeQuery("select sum(len(hours)) from od where rollno="+rollno+" and date>='"+fdate+"' and date <='"+tdate+"'");
  if(rs.next())
     {
     	noofhoursod=rs.getInt(1);
     }
rs.close();  
rs = stmt.executeQuery("select sum(len(hours)) from blankatt where rollno="+rollno+" and date>='"+fdate+"' and date <='"+tdate+"'");
  if(rs.next())
     {
     	noofhoursblank=rs.getInt(1);
     }
rs.close();  


rs = stmt.executeQuery("execute noofperiodu "+dno+","+year+",'"+academicyear+"','"+fdate+"','"+tdate+"'");
  if(rs.next())
     {
     	noofhours=rs.getInt(1);
     }
rs.close();

noofhours=noofhours-noofhoursblank;

rs = stmt.executeQuery("select date,hours from od where rollno="+rollno+" and date>='"+fdate+"' and date <='"+tdate+"'");
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
	rs = stmt.executeQuery("select hours from attendance where rollno="+rollno+" and date='"+tempdate[ii]+"'");
  if(rs.next())
     {
     	tripleh=rs.getString(1);
     }
	rs.close();  
   noofhoursabsent+=searc(temphours[ii],tripleh);   	
   }

*/


/*
tempcount=0;

rs = stmt.executeQuery("select date,hours from blankatt where rollno="+rollno+" and date>='"+fdate+"' and date <='"+tdate+"'");
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
	rs = stmt.executeQuery("select hours from attendance where rollno="+rollno+" and date='"+tempdate[ii]+"'");
  if(rs.next())
     {
     	tripleh=rs.getString(1);
     }
	rs.close();  
   noofhoursabsent+=searc(temphours[ii],tripleh);   	
   }

*/


  
int temp=noofhoursabsent-noofhoursod;
if(noofhours>0)
percentage=1/(float)noofhours*(float)(noofhours-temp)*100;

String hours="",ab="",od="",blank="";
for(int i=0;i<count;i++)
  {
  	for(int z=0;z<10;z++)
  	  attendance[i][z]="";
  rs = stmt.executeQuery("execute individualattendanceview "+dno+","+year+","+rollno+",'"+date+"','"+academicyear+"'");
  if(rs.next())
     {
     	hours=rs.getString(1);
     	ab=rs.getString(2);
     	od=rs.getString(3);
     	blank=rs.getString(4);
error=error+hours;
try{
     	dispdate[i]=vdate;
     	for(int h=0;h<hours.length();h++)
     	  attendance[i][Integer.parseInt(hours.substring(h,h+1))]="/";
     	for(int h=0;h<ab.length();h++)
     	  attendance[i][Integer.parseInt(ab.substring(h,h+1))]="ab";
     	for(int h=0;h<od.length();h++)
     	   if(attendance[i][Integer.parseInt(od.substring(h,h+1))]=="ab")
     	  attendance[i][Integer.parseInt(od.substring(h,h+1))]="od";
     	for(int h=0;h<blank.length();h++)
     	  attendance[i][Integer.parseInt(blank.substring(h,h+1))]="NA";
}catch(Exception e){error=error+e.toString();}
     }
  rs.close();  
  dateadd();
  }
stmt.close();
noofhours=0;
noofhoursabsent=0;
noofhoursod=0;
for(int i=0;i<count;i++)
  {
  	for(int z=0;z<10;z++)
  	{
  	 if(attendance[i][z]=="/") noofhours+=1;
  	 if(attendance[i][z]=="ab") noofhoursabsent+=1;
  	 if(attendance[i][z]=="od") noofhoursod+=1;
  	}
  	  	  
  }
  
// OD remove  
// noofhours+=noofhoursabsent-noofhoursod;
noofhours+=noofhoursabsent+noofhoursod;
if(noofhours>0)
{
percentage=(float)(noofhours-noofhoursod-noofhoursabsent)/(float)(noofhours)*100;
percentagewod=(float)(noofhours-noofhoursabsent)/(float)(noofhours)*100;

//percentage=(float)(noofhours-noofhoursabsent)/(float)(noofhours)*100;
//percentagewod=(float)(noofhours+noofhoursod-noofhoursabsent)/(float)(noofhours)*100;

}


//percentage=1/(float)noofhours*(float)(noofhours-temp)*100;

}catch(Exception e){return e.toString();}
finally{connMgr.freeConnection("attendance",con);}

return error;
}

public void datediffer()
{
DBConnectionManager connMgr=null; Connection con =null;
	try
	{
connMgr = DBConnectionManager.getInstance(); 
con = connMgr.getConnection("xavier");
Statement stmt = con.createStatement();
ResultSet rs = stmt.executeQuery("select datediff(d,'"+fdate+"','"+tdate+"')");
if(rs.next())
{
	count=rs.getInt(1)+1;
}
rs.close();
stmt.close();
}catch(Exception e){}
finally{connMgr.freeConnection("xavier",con);}

   }

public void dateadd()
{
	String month="",day="",year="";
DBConnectionManager connMgr=null; Connection con =null;
	try
	{
connMgr = DBConnectionManager.getInstance(); 
con = connMgr.getConnection("xavier");
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
	vdate=day+"/"+month+"/"+year;
}
rs.close();
stmt.close();
}catch(Exception e){}
finally{connMgr.freeConnection("xavier",con);}

}

public void setdispdate(String[] dispdate) 
{ 
this.dispdate = dispdate; 
} 
public String[] getdispdate() 
{ 
return this.dispdate; 
} 

public void setattendance(String[][] attendance) 
{ 
this.attendance = attendance; 
} 
public String[][] getattendance() 
{ 
return this.attendance; 
} 
public void setcount(int count) 
{ 
this.count = count; 
} 
public int getcount() 
{ 
return this.count; 
} 

public void setpercentage(float percentage) 
{ 
this.percentage = percentage; 
} 
public float getpercentage() 
{ 
return this.percentage; 
} 
public float getpercentagewod() 
{ 
return this.percentagewod; 
} 


public void setnoofhours(int noofhours) 
{ 
this.noofhours = noofhours; 
} 
public int getnoofhours() 
{ 
return this.noofhours; 
} 

public void setnoofhoursabsent(int noofhoursabsent) 
{ 
this.noofhoursabsent = noofhoursabsent; 
} 
public int getnoofhoursabsent() 
{ 
return this.noofhoursabsent; 
} 

public void setnoofhoursod(int noofhoursod) 
{ 
this.noofhoursod = noofhoursod; 
} 
public int getnoofhoursod() 
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
public void setrollno(String rollno) 
{ 
this.rollno = rollno; 
} 
public String getrollno() 
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

public void setname(String name) 
{ 
this.name = name; 
} 
public String getname() 
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


}


