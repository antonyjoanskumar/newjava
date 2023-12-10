package sxcce;
import java.sql.*;
import java.util.Date;
import dd.DBConnectionManager;
public class studentivud
	{
		int count=0;
		int subcount=0;
		int cercount=0;
		int vercount=0;
		int admissionno=0;
		int bookingid=0;
		
		String category="";
		String fcategory="";
		String ccategory="";
		String qualification="";
		String religion="";
		String state="";
		String noofattempts="";
		String noofimprovements="";
		String rollno="";
		String name="";
		String initial="";
		String parentorguardian="";
		String communicationhouse="";
		String communicationtown="";
		String communicationdistrict="";
		String communicationstate="";
		String communicationpincode="";
		String parenthouse="";
		String parenttown="";
		String parentdistrict="";
		String parentstate="";
		String parentpincode="";
		String phone="";
		String areacode="";
		String email="";
		String cell="";
		String sex="";
		String community="";
		String mothertongue="";
		String occupation="";
		String nationality="";
		String extracurricularactivities="";
		String department="";
		String section="";
		String directorlateral="";
		String medium="";
		String acknowledgementno="";
		String bankchelanno="";
		String chelanamount="";
		String chelandate="0";
		String chelanmonth="0";
		String chelanyear="0";
		String entrancemark="";
		String dacknowledgementno="";
		String nameofthebank="";
		String ddno="";
		String damount="";
		String qualifyingdiploma="";

		String sub[] = new String[10];
		String subm[] = new String[10];
		String maxmark[] = new String[10];
		String cer[] = new String[14];
		String certificateserialno[] = new String[3];
		String registerno[] = new String[3];
		String monthandyearofattempt[] = new String[3];
		String atmrcodeanddate[] = new String[3];

		String matchname[] = new String[100000];
		String matchrollno[] = new String[100000];
		String matchdepartment[] = new String[100000];
		String matchyear[] = new String[100000];
		String matchdateofjoining[] = new String[100000];
		String matchacademicyear[] = new String[100000];
		String matchcategory[] = new String[100000];

		String monthlyincome="";
		String active="";
		String discontinueday="";
		String discontinuemonth="";
		String discontinueyear="";
		String remarks="";
		String appent="";

		String birthmonth="";
		String birthdate="";
		String birthyear="";
		String joiningdate="";
		String joiningmonth="";
		String joiningyear="";


String fatherqualification="";
String mothername="";
String motherqualification="";
String motheroccupation="";
String caste="";
String laststudied="";
String bloodgroup="";
String stay="";
String modeoftransport="";
int distancefromhome=0;
String differentlyabled="";
String introducer="";
String selection="";





		public String Deletestud()
			{int ccc=0;
				
				try
					{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
ResultSet rs;
Statement stmt = con.createStatement();
rs = stmt.executeQuery("select count(rollno) from stud where rollno="+rollno);
if(rs.next())
  ccc=rs.getInt(1);
rs.close();
stmt.close();
					
					PreparedStatement ps = con.prepareStatement("delete from student where rollno="+rollno);
					int i = ps.executeUpdate();
					ps.close();
					ps = con.prepareStatement("delete from qualification where rollno="+rollno);
					i = ps.executeUpdate();
					ps.close();
					ps = con.prepareStatement("delete from certificates where rollno="+rollno);
					i = ps.executeUpdate();
					ps.close();
					ps = con.prepareStatement("delete from hsctutionfees where rollno="+rollno);
					i = ps.executeUpdate();
					ps.close();
					ps = con.prepareStatement("delete from diplomatutionfees where rollno="+rollno);
					i = ps.executeUpdate();
					ps.close();
					ps = con.prepareStatement("delete from verification where rollno="+rollno);
					i = ps.executeUpdate();
					ps.close();
					ps = con.prepareStatement("delete from stud where year=1 and rollno="+rollno);
					if(ccc==1)
					i = ps.executeUpdate();
					ps.close();
					
					connMgr.freeConnection("xavier",con);
					}catch(Exception e){return e.toString();}
				return ("Success");
			}



public String Match(int search)
{count=0;


String quer="";
try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
Statement stmt = con.createStatement();
ResultSet rs;
if(search==1){
quer="select rollno,name,department.dname,year(yearofjoining) from student,department "+
" where rollno="+rollno+" and department.dno=student.departmentno";

rs = stmt.executeQuery(quer);
while(rs.next())
   {
   	matchrollno[count]=rs.getString(1);
   	matchname[count]=rs.getString(2);
   	matchdepartment[count]=rs.getString(3);
   	matchdateofjoining[count]=rs.getString(4);
   	count++;
   }
rs.close();
}
if(search==4){
quer="select bookingid,name,department.dname,year(bookingdate) from booking,department "+
" where bookingid="+rollno+" and department.dno=booking.dno";

rs = stmt.executeQuery(quer);
while(rs.next())
   {
   	matchrollno[count]=rs.getString(1);
   	matchname[count]=rs.getString(2);
   	matchdepartment[count]=rs.getString(3);
   	matchdateofjoining[count]=rs.getString(4);
   	count++;
   }
rs.close();
}

String deeps="";
if(search==2 || search==3){
if(search ==3)	
  deeps="and year(yearofjoining)=(select max(year(yearofjoining)) from student) ";
//quer="select rollno,name,department.dname,year(yearofjoining),collegecategory.cname from collegecategory,student,department "+
//"where collegecategory.slno=student.ccategory and department.dno=student.departmentno "+deeps+" and name like '%"+name+"%' order by department.dno,student.name";

quer="select rollno,name,department.dname,year(yearofjoining),collegecategory.cname from collegecategory,student,department "+
"where collegecategory.slno=student.ccategory and department.dno=student.departmentno "+deeps+" and name like '%"+name+"%' order by department.dno,student.name";


rs = stmt.executeQuery(quer);
while(rs.next())
   {
   	matchrollno[count]=rs.getString(1);
   	matchname[count]=rs.getString(2);
   	matchdepartment[count]=rs.getString(3);
   	matchdateofjoining[count]=rs.getString(4);
   	matchcategory[count]=rs.getString(5);
   	count++;
   }
rs.close();
}
for(int i=0;i<count;i++)
{
	quer="select academicyear,year from stud where rollno="+matchrollno[i]+" and "+
		"academicyear in (select max(academicyear) from stud where rollno="+matchrollno[i]+")";
rs = stmt.executeQuery(quer);
if(rs.next())
   {
   	matchacademicyear[i]=rs.getString(1);
   	matchyear[i]=rs.getString(2);
   }

}
stmt.close();
connMgr.freeConnection("xavier",con);
}catch(Exception e){return e.toString();}
return "suc";
}


public String Maxrollno()
{
	String no="";
	int temp=1;
try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
Statement stmt = con.createStatement();
ResultSet rs = stmt.executeQuery("select max(rollno) from student");
if(rs.next())
{
temp=rs.getInt(1);
}
if(temp==0)temp=1;
else temp++;
no=temp+"";
rollno=no;
rs.close();
stmt.close();
connMgr.freeConnection("xavier",con);
}catch(Exception e){return e.toString();}
return no; 
}
		public int Viewstud()
			{int found=0;
				try
					{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
					Statement stmt=con.createStatement();
				ResultSet ps;
ps = stmt.executeQuery("select rollno,apporentranceno,category,qualification,religion,state,"+
"noofattempts,noofimprovements,name,initial,parentorguardian,"+
"communicationhouse,communicationtown,communicationdistrict,communicationstate,"+
"communicationpincode,parenthouse,parenttown,parentdistrict,parentstate,parentpincode,"+
"phone,areacode,email,cell,sex,day(dateofbirth),month(dateofbirth),year(dateofbirth),"+
"community,mothertonque,occupation,"+
"nationality,extracurricularactivities,"+
"departmentno,section,day(yearofjoining),month(yearofjoining),year(yearofjoining),"+
"directorlateral,medium,fcategory,ccategory,monthlyincome,active,"+
"day(discontinuedate),month(discontinuedate),year(discontinuedate),"+
"remarks,admissionno,fatherqualification,mothername,motherqualification,motheroccupation,caste,laststudied, "+
"bloodgroup,stay,modeoftransport,distancefromhome,differentlyabled,introducer,selection from student where rollno="+rollno);
					if(ps.next())
					{
					rollno=ps.getString(1);
					appent=ps.getString(2);
					category=ps.getString(3);
					qualification=ps.getString(4);
					religion=ps.getString(5);
					state=ps.getString(6);
					noofattempts=ps.getString(7);
					noofimprovements=ps.getString(8);
					name=ps.getString(9);
					initial=ps.getString(10);
					parentorguardian=ps.getString(11);
					communicationhouse=ps.getString(12);
					communicationtown=ps.getString(13);
					communicationdistrict=ps.getString(14);
					communicationstate=ps.getString(15);
					communicationpincode=ps.getString(16);
					parenthouse=ps.getString(17);
					parenttown=ps.getString(18);
					parentdistrict=ps.getString(19);
					parentstate=ps.getString(20);
					parentpincode=ps.getString(21);
					phone=ps.getString(22);
					areacode=ps.getString(23);
					email=ps.getString(24);
					cell=ps.getString(25);
					sex=ps.getString(26);
					birthdate=ps.getString(27);
					birthmonth=ps.getString(28);
					birthyear=ps.getString(29);
					community=ps.getString(30);
					mothertongue=ps.getString(31);
					occupation=ps.getString(32);
					nationality=ps.getString(33);
					extracurricularactivities=ps.getString(34);
					department=ps.getString(35);
					section=ps.getString(36);
					joiningdate=ps.getString(37);
					joiningmonth=ps.getString(38);
					joiningyear=ps.getString(39);
					directorlateral=ps.getString(40);
					medium=ps.getString(41);
					fcategory=ps.getString(42);
					ccategory=ps.getString(43);
					monthlyincome=ps.getString(44);
					active=ps.getString(45);
					discontinueday=ps.getString(46);
					discontinuemonth=ps.getString(47);
					discontinueyear=ps.getString(48);
					remarks=ps.getString(49);
					admissionno=ps.getInt(50);
fatherqualification=ps.getString(51);
mothername=ps.getString(52);
motherqualification=ps.getString(53);
motheroccupation=ps.getString(54);
caste=ps.getString(55);
laststudied=ps.getString(56);
bloodgroup=ps.getString(57);
stay=ps.getString(58);
modeoftransport=ps.getString(59);
distancefromhome=ps.getInt(60);
differentlyabled=ps.getString(61);
introducer=ps.getString(62);
selection=ps.getString(63);

					found=1;
				}
					ps.close();
					stmt.close();
					connMgr.freeConnection("xavier",con);
					}catch(Exception e){return (100);}
					return (found);
			}
		public int Viewsub()
			{int found=0;
				try
					{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
					Statement stmt=con.createStatement();
					ResultSet ps = stmt.executeQuery("select * from qualification where rollno="+rollno);
					while(ps.next())
					{
						sub[subcount]=ps.getString(2);
						subm[subcount]=ps.getString(3);
						maxmark[subcount]=ps.getString(4);
						subcount++;found=1;
					}
					ps.close();
					stmt.close();
					connMgr.freeConnection("xavier",con);
					}catch(Exception e){return (100);}
					return (found);
			}
		public  int Viewcer()
			{int found=0;
				try
					{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
					Statement stmt=con.createStatement();
					ResultSet ps = stmt.executeQuery("select * from certificates where rollno="+rollno);
					while(ps.next())
					{
						cer[cercount]=ps.getString(2);
						cercount++;found=1;
					}
					ps.close();
					stmt.close();
					connMgr.freeConnection("xavier",con);
					}catch(Exception e){return(100);}
					return(found);
			}
		public int Viewhsctutionfees()
			{int found=0;
				try
					{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
					Statement stmt=con.createStatement();
ResultSet ps = stmt.executeQuery("select acknowledgementno,bankchelanno,day(chelandate),"+
"month(chelandate),year(chelandate),amount,"+
"entrancemark from hsctutionfees where rollno="+rollno);
					if(ps.next())
					{
						acknowledgementno=ps.getString(1);
						bankchelanno=ps.getString(2);
						chelandate=ps.getString(3);
						chelanmonth=ps.getString(4);
						chelanyear=ps.getString(5);
						chelanamount=ps.getString(6);
						entrancemark=ps.getString(7);found=1;
					}
					ps.close();
					stmt.close();
					connMgr.freeConnection("xavier",con);
					}catch(Exception e){return 100;}
					return found;
			}
		public int Viewdiplomatutionfees()
			{int found=0;
				try
					{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
					Statement stmt=con.createStatement();
					ResultSet ps = stmt.executeQuery("select * from diplomatutionfees where rollno="+rollno);
					if(ps.next())
					{
						dacknowledgementno=ps.getString(2);
						nameofthebank=ps.getString(3);
						ddno=ps.getString(4);
						damount=ps.getString(5);
						qualifyingdiploma=ps.getString(6);found=1;
					}
					ps.close();
					stmt.close();
					connMgr.freeConnection("xavier",con);
					}catch(Exception e){return 100;}
					return found;
			}
		public void ViewDept()
			{
				try
					{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
					Statement stmt=con.createStatement();
					ResultSet ps = stmt.executeQuery("select dname from department where dno="+department);
					if(ps.next())
					{
						department=ps.getString(1);
					}
					ps.close();
					stmt.close();
					connMgr.freeConnection("xavier",con);
					}catch(Exception e){}
			}
		public int Viewverification()
			{int found=0;
				try
					{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
					Statement stmt=con.createStatement();
					ResultSet ps = stmt.executeQuery("select * from verification where rollno="+rollno);
					while(ps.next())
					{
						certificateserialno[vercount]=ps.getString(2);
						registerno[vercount]=ps.getString(3);
						monthandyearofattempt[vercount]=ps.getString(4);
						atmrcodeanddate[vercount]=ps.getString(5);
						vercount++;found=1;
					}
					ps.close();
					stmt.close();
					connMgr.freeConnection("xavier",con);
					}catch(Exception e){return 100;}
					return found;
			}
			
			
		public String Insert()
			{
				try
					{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
					PreparedStatement ps;
					ps = con.prepareStatement("insert into student values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
					ps.setString(1,rollno);
					ps.setString(2,appent);	
					ps.setString(3,category);
					ps.setString(4,qualification);
					ps.setString(5,religion);
					ps.setString(6,state);
					ps.setString(7,noofattempts);
					ps.setString(8,noofimprovements);
					ps.setString(9,name);
					ps.setString(10,initial);
					ps.setString(11,parentorguardian);
					ps.setString(12,communicationhouse);
					ps.setString(13,communicationtown);
					ps.setString(14,communicationdistrict);
					ps.setString(15,communicationstate);
					ps.setString(16,communicationpincode);
					ps.setString(17,parenthouse);
					ps.setString(18,parenttown);
					ps.setString(19,parentdistrict);
					ps.setString(20,parentstate);
					ps.setString(21,parentpincode);
					ps.setString(22,phone);
					ps.setString(23,areacode);
					ps.setString(24,email);
					ps.setString(25,cell);
					ps.setString(26,sex);
					ps.setString(27,birthmonth+"/"+birthdate+"/"+birthyear);
					ps.setString(28,community);
					ps.setString(29,mothertongue);
					ps.setString(30,occupation);
					ps.setString(31,nationality);
					ps.setString(32,extracurricularactivities);
					ps.setString(33,department);
					ps.setString(34,section);
					ps.setString(35,joiningmonth+"/"+joiningdate+"/"+joiningyear);
					ps.setString(36,directorlateral);
					ps.setString(37,medium);
					ps.setString(38,fcategory);
					ps.setString(39,ccategory);
					ps.setString(40,monthlyincome);
					ps.setString(41,active);
					ps.setString(42,discontinuemonth+"/"+discontinueday+"/"+discontinueyear);
					ps.setString(43,remarks);
					ps.setInt(44,admissionno);
			
					ps.setString(45,fatherqualification);
					ps.setString(46,mothername);
					ps.setString(47,motherqualification);
					ps.setString(48,motheroccupation);
					ps.setString(49,caste);
					ps.setString(50,laststudied);
					ps.setString(51,bloodgroup);
					ps.setString(52,stay);
					ps.setString(53,modeoftransport);
					ps.setInt(54,distancefromhome);
					ps.setString(55,differentlyabled);
					ps.setString(56,introducer);
					ps.setString(57,selection);
					
					int i = ps.executeUpdate();
					ps.close();int j;
					int studinserted=0;

					ps = con.prepareStatement("update stud set name=?,department=? where rollno=?");
					ps.setString(1,name);
					ps.setString(2,department);
					ps.setString(3,rollno);
					studinserted=ps.executeUpdate();
					ps.close();
						
					if(studinserted==0)
					{
					ps = con.prepareStatement("insert into stud values(?,?,?,?,?)");
					ps.setString(1,rollno);
					ps.setString(2,name);
					if(directorlateral.equals("Direct"))
						ps.setInt(3,1);
					else 
					    ps.setInt(3,2);
					ps.setString(4,department);
					ps.setString(5,joiningyear+" -- "+(Integer.parseInt(joiningyear)+1));
					j=ps.executeUpdate();
					ps.close();
					}
					
//					if(Integer.parseInt(active)==0)
//					{
//					ps = con.prepareStatement("delete from stud where rollno=?");
//					ps.setString(1,rollno);
//					j=ps.executeUpdate();
//					ps.close();
//					}


					for (i=0;i<subcount;i++)
				   {
					ps = con.prepareStatement("insert into qualification values(?,?,?,?)");
					ps.setString(1,rollno);
					ps.setString(2,sub[i]);
					ps.setString(3,subm[i]);
					ps.setString(4,maxmark[i]);
					j=ps.executeUpdate();
					ps.close();
					}

					for (i=0;i<vercount;i++)
				   {
					ps = con.prepareStatement("insert into verification values(?,?,?,?,?,?)");
					ps.setInt(1,Integer.parseInt(rollno));
					ps.setString(2,certificateserialno[i]);
					ps.setString(3,registerno[i]);
					ps.setString(4,monthandyearofattempt[i]);
					ps.setString(5,atmrcodeanddate[i]);
					ps.setInt(6,(i+1));
					j=ps.executeUpdate();
					ps.close();
					}

 					for (i=0;i<cercount;i++)
 				    {
					ps = con.prepareStatement("insert into certificates values(?,?)");
					ps.setInt(1,Integer.parseInt(rollno));
					ps.setString(2,cer[i]);
					j=ps.executeUpdate();
					ps.close();
					}

					if(acknowledgementno !=null)
					   if(acknowledgementno.length()>0)
					      {
						ps = con.prepareStatement("insert into hsctutionfees values(?,?,?,?,?,?)");
						ps.setString(1,rollno);
						ps.setString(2,acknowledgementno);
						ps.setString(3,bankchelanno);
						ps.setString(4,chelanmonth+"/"+chelandate+"/"+chelanyear);
						ps.setString(5,chelanamount);
						ps.setString(6,entrancemark);
						j=ps.executeUpdate();
						ps.close();
					      }

					if(dacknowledgementno !=null)
					   if(dacknowledgementno.length()>0)
					      {
						ps = con.prepareStatement("update booking set active=4 where bookingid=?");
						ps.setString(1,rollno);
						ps.setString(2,dacknowledgementno);
						ps.setString(3,nameofthebank);
						ps.setString(4,ddno);
						ps.setString(5,damount);
						ps.setString(6,qualifyingdiploma);
						j=ps.executeUpdate();
						ps.close();
					      }
					if(bookingid !=0)
					   {
						ps = con.prepareStatement("update booking set active=4 where bookingid = ?");
						ps.setInt(1,bookingid);
						j=ps.executeUpdate();
						ps.close();
					   }
					connMgr.freeConnection("xavier",con);
					}catch(Exception e){return e.toString();}
					return "success";
			}



public void setmatchacademicyear(String[] matchacademicyear) 
{ 
this.matchacademicyear = matchacademicyear; 
} 
public String[] getmatchacademicyear() 
{ 
return this.matchacademicyear; 
} 



public void setmatchcategory(String[] matchcategory) 
{ 
this.matchcategory = matchcategory; 
} 
public String[] getmatchcategory() 
{ 
return this.matchcategory; 
} 

public void setmatchyear(String[] matchyear) 
{ 
this.matchyear = matchyear; 
} 
public String[] getmatchyear() 
{ 
return this.matchyear; 
} 

public void setmatchdateofjoining(String[] matchdateofjoining) 
{ 
this.matchdateofjoining = matchdateofjoining; 
} 
public String[] getmatchdateofjoining() 
{ 
return this.matchdateofjoining; 
} 


public void setmatchdepartment(String[] matchdepartment) 
{ 
this.matchdepartment = matchdepartment; 
} 
public String[] getmatchdepartment() 
{ 
return this.matchdepartment; 
} 
public void setmatchname(String[] matchname) 
{ 
this.matchname = matchname; 
} 
public String[] getmatchname() 
{ 
return this.matchname; 
} 
public void setmatchrollno(String[] matchrollno) 
{ 
this.matchrollno = matchrollno; 
} 
public String[] getmatchrollno() 
{ 
return this.matchrollno; 
} 
	
	
 public void setactive(String active) 
{ 
this.active = active; 
} 
public String getactive() 
{ 
return this.active; 
} 
public void setdiscontinuemonth(String discontinuemonth) 
{ 
this.discontinuemonth = discontinuemonth; 
} 
public String getdiscontinuemonth() 
{ 
return this.discontinuemonth; 
} 

public void setdiscontinueday(String discontinueday) 
{ 
this.discontinueday = discontinueday; 
} 
public String getdiscontinueday() 
{ 
return this.discontinueday; 
} 


public void setdiscontinueyear(String discontinueyear) 
{ 
this.discontinueyear = discontinueyear; 
} 
public String getdiscontinueyear() 
{ 
return this.discontinueyear; 
} 


public void setmonthlyincome(String monthlyincome) 
{ 
this.monthlyincome = monthlyincome; 
} 
public String getmonthlyincome() 
{ 
return this.monthlyincome; 
} 
public void setremarks(String remarks) 
{ 
this.remarks = remarks; 
} 
public String getremarks() 
{ 
return this.remarks; 
} 
public void setappent(String appent) 
{ 
this.appent = appent; 
} 
public String getappent() 
{ 
return this.appent; 
} 

 public void setbirthdate(String birthdate) 
{ 
this.birthdate = birthdate; 
} 
public String getbirthdate() 
{ 
return this.birthdate; 
} 

 public void setchelandate(String chelandate) 
{ 
this.chelandate = chelandate; 
} 
public String getchelandate() 
{ 
return this.chelandate; 
} 

 public void setchelanmonth(String chelanmonth) 
{ 
this.chelanmonth = chelanmonth; 
} 
public String getchelanmonth() 
{ 
return this.chelanmonth; 
} 

 public void setchelanyear(String chelanyear) 
{ 
this.chelanyear = chelanyear; 
} 
public String getchelanyear() 
{ 
return this.chelanyear; 
} 


public void setbirthmonth(String birthmonth) 
{ 
this.birthmonth = birthmonth; 
} 
public String getbirthmonth() 
{ 
return this.birthmonth; 
} 
public void setbirthyear(String birthyear) 
{ 
this.birthyear = birthyear; 
} 
public String getbirthyear() 
{ 
return this.birthyear; 
} 
public void setjoiningdate(String joiningdate) 
{ 
this.joiningdate = joiningdate; 
} 
public String getjoiningdate() 
{ 
return this.joiningdate; 
} 


public void setjoiningmonth(String joiningmonth) 
{ 
this.joiningmonth = joiningmonth; 
} 
public String getjoiningmonth() 
{ 
return this.joiningmonth; 
} 
	
public void setjoiningyear(String joiningyear) 
{ 
this.joiningyear = joiningyear; 
} 
public String getjoiningyear() 
{ 
return this.joiningyear; 
} 
	


public void setacknowledgementno(String acknowledgementno) 
{ 
this.acknowledgementno = acknowledgementno; 
} 
public String getacknowledgementno() 
{ 
return this.acknowledgementno; 
} 
public void setareacode(String areacode) 
{ 
this.areacode = areacode; 
} 
public String getareacode() 
{ 
return this.areacode; 
} 
public void setbankchelanno(String bankchelanno) 
{ 
this.bankchelanno = bankchelanno; 
} 
public String getbankchelanno() 
{ 
return this.bankchelanno; 
} 
public void setcategory(String category) 
{ 
this.category = category; 
} 
public String getcategory() 
{ 
return this.category; 
} 
public void setccategory(String ccategory) 
{ 
this.ccategory = ccategory; 
} 
public String getccategory() 
{ 
return this.ccategory; 
} 
public void setcell(String cell) 
{ 
this.cell = cell; 
} 
public String getcell() 
{ 
return this.cell; 
} 
public void setchelanamount(String chelanamount) 
{ 
this.chelanamount = chelanamount; 
} 
public String getchelanamount() 
{ 
return this.chelanamount; 
} 
public void setcommunicationdistrict(String communicationdistrict) 
{ 
this.communicationdistrict = communicationdistrict; 
} 
public String getcommunicationdistrict() 
{ 
return this.communicationdistrict; 
} 
public void setcommunicationhouse(String communicationhouse) 
{ 
this.communicationhouse = communicationhouse; 
} 
public String getcommunicationhouse() 
{ 
return this.communicationhouse; 
} 
public void setcommunicationpincode(String communicationpincode) 
{ 
this.communicationpincode = communicationpincode; 
} 
public String getcommunicationpincode() 
{ 
return this.communicationpincode; 
} 
public void setcommunicationstate(String communicationstate) 
{ 
this.communicationstate = communicationstate; 
} 
public String getcommunicationstate() 
{ 
return this.communicationstate; 
} 
public void setcommunicationtown(String communicationtown) 
{ 
this.communicationtown = communicationtown; 
} 
public String getcommunicationtown() 
{ 
return this.communicationtown; 
} 
public void setcommunity(String community) 
{ 
this.community = community; 
} 
public String getcommunity() 
{ 
return this.community; 
} 
public void setdacknowledgementno(String dacknowledgementno) 
{ 
this.dacknowledgementno = dacknowledgementno; 
} 
public String getdacknowledgementno() 
{ 
return this.dacknowledgementno; 
} 
public void setdamount(String damount) 
{ 
this.damount = damount; 
} 
public String getdamount() 
{ 
return this.damount; 
} 
public void setddno(String ddno) 
{ 
this.ddno = ddno; 
} 
public String getddno() 
{ 
return this.ddno; 
} 
public void setdepartment(String department) 
{ 
this.department = department; 
} 
public String getdepartment() 
{ 
return this.department; 
} 
public void setdirectorlateral(String directorlateral) 
{ 
this.directorlateral = directorlateral; 
} 
public String getdirectorlateral() 
{ 
return this.directorlateral; 
} 
public void setemail(String email) 
{ 
this.email = email; 
} 
public String getemail() 
{ 
return this.email; 
} 
public void setentrancemark(String entrancemark) 
{ 
this.entrancemark = entrancemark; 
} 
public String getentrancemark() 
{ 
return this.entrancemark; 
} 
public void setextracurricularactivities(String extracurricularactivities) 
{ 
this.extracurricularactivities = extracurricularactivities; 
} 
public String getextracurricularactivities() 
{ 
return this.extracurricularactivities; 
} 
public void setfcategory(String fcategory) 
{ 
this.fcategory = fcategory; 
} 
public String getfcategory() 
{ 
return this.fcategory; 
} 
public void setinitial(String initial) 
{ 
this.initial = initial; 
} 
public String getinitial() 
{ 
return this.initial; 
} 
public void setmedium(String medium) 
{ 
this.medium = medium; 
} 
public String getmedium() 
{ 
return this.medium; 
} 
public void setmothertongue(String mothertongue) 
{ 
this.mothertongue = mothertongue; 
} 
public String getmothertongue() 
{ 
return this.mothertongue; 
} 
public void setname(String name) 
{ 
this.name = name; 
} 
public String getname() 
{ 
return this.name; 
} 
public void setnameofthebank(String nameofthebank) 
{ 
this.nameofthebank = nameofthebank; 
} 
public String getnameofthebank() 
{ 
return this.nameofthebank; 
} 
public void setnationality(String nationality) 
{ 
this.nationality = nationality; 
} 
public String getnationality() 
{ 
return this.nationality; 
} 
public void setnoofattempts(String noofattempts) 
{ 
this.noofattempts = noofattempts; 
} 
public String getnoofattempts() 
{ 
return this.noofattempts; 
} 
public void setnoofimprovements(String noofimprovements) 
{ 
this.noofimprovements = noofimprovements; 
} 
public String getnoofimprovements() 
{ 
return this.noofimprovements; 
} 
public void setoccupation(String occupation) 
{ 
this.occupation = occupation; 
} 
public String getoccupation() 
{ 
return this.occupation; 
} 
public void setparentdistrict(String parentdistrict) 
{ 
this.parentdistrict = parentdistrict; 
} 
public String getparentdistrict() 
{ 
return this.parentdistrict; 
} 
public void setparenthouse(String parenthouse) 
{ 
this.parenthouse = parenthouse; 
} 
public String getparenthouse() 
{ 
return this.parenthouse; 
} 
public void setparentorguardian(String parentorguardian) 
{ 
this.parentorguardian = parentorguardian; 
} 
public String getparentorguardian() 
{ 
return this.parentorguardian; 
} 
public void setparenttown(String parenttown) 
{ 
this.parenttown = parenttown; 
} 
public String getparenttown() 
{ 
return this.parenttown; 
} 
public void setparentpincode(String parentpincode) 
{ 
this.parentpincode = parentpincode; 
} 
public String getparentpincode() 
{ 
return this.parentpincode; 
} 
public void setparentstate(String parentstate) 
{ 
this.parentstate = parentstate; 
} 
public String getparentstate() 
{ 
return this.parentstate; 
} 
public void setphone(String phone) 
{ 
this.phone = phone; 
} 
public String getphone() 
{ 
return this.phone; 
} 
public void setqualification(String qualification) 
{ 
this.qualification = qualification; 
} 
public String getqualification() 
{ 
return this.qualification; 
} 
public void setqualifyingdiploma(String qualifyingdiploma) 
{ 
this.qualifyingdiploma = qualifyingdiploma; 
} 
public String getqualifyingdiploma() 
{ 
return this.qualifyingdiploma; 
} 
public void setreligion(String religion) 
{ 
this.religion = religion; 
} 
public String getreligion() 
{ 
return this.religion; 
} 


public void setadmissionno(int admissionno) 
{ 
this.admissionno = admissionno; 
} 
public int getadmissionno() 
{ 
return this.admissionno; 
} 


public void setrollno(String rollno) 
{ 
this.rollno = rollno; 
} 
public String getrollno() 
{ 
return this.rollno; 
} 
public void setsection(String section) 
{ 
this.section = section; 
} 
public String getsection() 
{ 
return this.section; 
} 
public void setsex(String sex) 
{ 
this.sex = sex; 
} 
public String getsex() 
{ 
return this.sex; 
} 
public void setstate(String state) 
{ 
this.state = state; 
} 
public String getstate() 
{ 
return this.state; 
} 

 public void setcercount(int cercount) 
{ 
this.cercount = cercount; 
} 
public int getcercount() 
{ 
return this.cercount; 
} 
public void setcount(int count) 
{ 
this.count = count; 
} 
public int getcount() 
{ 
return this.count; 
} 
public void setsubcount(int subcount) 
{ 
this.subcount = subcount; 
} 
public int getsubcount() 
{ 
return this.subcount; 
} 

public void setbookingid(int bookingid) 
{ 
this.bookingid = bookingid; 
} 
public int getbookingid() 
{ 
return this.bookingid; 
} 

public void setdistancefromhome(int distancefromhome) { this.distancefromhome = distancefromhome; } public int getdistancefromhome() { return this.distancefromhome; } 

public void setlaststudied(String laststudied) { this.laststudied = laststudied; } public String getlaststudied() { return this.laststudied; } 
public void setfatherqualification(String fatherqualification) { this.fatherqualification = fatherqualification; } public String getfatherqualification() { return this.fatherqualification; } 
public void setmothername(String mothername) { this.mothername = mothername; } public String getmothername() { return this.mothername; } 
public void setmotherqualification(String motherqualification) { this.motherqualification = motherqualification; } public String getmotherqualification() { return this.motherqualification; } 
public void setmotheroccupation(String motheroccupation) { this.motheroccupation = motheroccupation; } public String getmotheroccupation() { return this.motheroccupation; } 
public void setcaste(String caste) { this.caste = caste; } public String getcaste() { return this.caste; } 
public void setbloodgroup(String bloodgroup) { this.bloodgroup = bloodgroup; } public String getbloodgroup() { return this.bloodgroup; } 
public void setstay(String stay) { this.stay = stay; } public String getstay() { return this.stay; } 
public void setmodeoftransport(String modeoftransport) { this.modeoftransport = modeoftransport; } public String getmodeoftransport() { return this.modeoftransport; } 
public void setdifferentlyabled(String differentlyabled) { this.differentlyabled = differentlyabled; } public String getdifferentlyabled() { return this.differentlyabled; } 
public void setintroducer(String introducer) { this.introducer = introducer; } public String getintroducer() { return this.introducer; } 
public void setselection(String selection) { this.selection = selection; } public String getselection() { return this.selection; } 







public void setvercount(int vercount) 
{ 
this.vercount = vercount; 
} 
public int getvercount() 
{ 
return this.vercount; 
} 

 public void setatmrcodeanddate(String[] atmrcodeanddate) 
{ 
this.atmrcodeanddate = atmrcodeanddate; 
} 
public String[] getatmrcodeanddate() 
{ 
return this.atmrcodeanddate; 
} 
public void setcer(String[] cer) 
{ 
this.cer = cer; 
} 
public String[] getcer() 
{ 
return this.cer; 
} 
public void setcertificateserialno(String[] certificateserialno) 
{ 
this.certificateserialno = certificateserialno; 
} 
public String[] getcertificateserialno() 
{ 
return this.certificateserialno; 
} 
public void setmaxmark(String[] maxmark) 
{ 
this.maxmark = maxmark; 
} 
public String[] getmaxmark() 
{ 
return this.maxmark; 
} 
public void setmonthandyearofattempt(String[] monthandyearofattempt) 
{ 
this.monthandyearofattempt = monthandyearofattempt; 
} 
public String[] getmonthandyearofattempt() 
{ 
return this.monthandyearofattempt; 
} 
public void setregisterno(String[] registerno) 
{ 
this.registerno = registerno; 
} 
public String[] getregisterno() 
{ 
return this.registerno; 
} 
public void setsub(String[] sub) 
{ 
this.sub = sub; 
} 
public String[] getsub() 
{ 
return this.sub; 
} 
public void setsubm(String[] subm) 
{ 
this.subm = subm; 
} 
public String[] getsubm() 
{ 
return this.subm; 
} 


	}