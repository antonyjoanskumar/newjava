package sxcce;
import java.sql.*;
import java.util.StringTokenizer;
import com.jspsmart.upload.*;
import java.util.Date;
import java.io.*;
import dd.DBConnectionManager;

public class umarks
	{
				String rep[] = new String[1000];
				String repin[] = new String[100];
				String report[][] = new String[100][10000];
				String report1[] = new String[1000];
				String report2[] = new String[1000];
				String report3[][] = new String[100][10000];
                String fdate="";
                String tdate="";
                String pname="";
                String fname="";

String d1="";
String m1="";
String y1="";
String str1="";
int count;
int count1;
int cid;
String sql="";	
String quer="";	
String error="";			
String academicyear="";		

String date1="";	
String date2="";	



public String umarksInsert()
{
String sql="";
try
{
DBConnectionManager connMgr;
connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  			Statement stmt = con.createStatement();
			ResultSet rs;
			PreparedStatement ps;

if(Integer.parseInt(repin[4])>0)
{
                    sql="insert into umarks(regno,subcode,grade,year,month) values(?,?,?,?,?)";
					ps=con.prepareStatement(sql);
					ps.setString(1,repin[1]);
					ps.setString(2,repin[2]);
					ps.setString(3,repin[3]);
					ps.setString(4,repin[4]);
					ps.setString(5,repin[5]);
					int j = ps.executeUpdate();
					ps.close();
}				
                    stmt.close();
					connMgr.freeConnection("xavier",con);
					error="...";
					}catch(Exception e){error=(e.toString());}
return error;	
}

public String umarksUpdate(int id)
{
try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;
					    PreparedStatement ps;
					    
                  sql="update umarks set subcode=?, grade=?,year=?,month=? where id=?";
					ps=con.prepareStatement(sql);
					ps.setString(1,repin[2]);
					ps.setString(2,repin[3]);
					ps.setString(3,repin[4]);
					ps.setString(4,repin[5]);
					ps.setInt(5,id);
					int i = ps.executeUpdate();
					ps.close();
                    stmt.close();
					connMgr.freeConnection("xavier",con);
					error="success";
					}catch(Exception e){error=(e.toString());
					}
					
		return error;
}



public String umarksDel(int id)
{

try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;
					    PreparedStatement ps;
                  sql="delete from umarks where id=?";
					ps=con.prepareStatement(sql);
					ps.setInt(1,id);
					int i = ps.executeUpdate();
					ps.close();
                    stmt.close();
					connMgr.freeConnection("xavier",con);
					error="success";
					}catch(Exception e){error=(e.toString());
					}
					
		return error;
}


public String umarksView(int id)
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
sql="select * from umarks where id="+id;

rs = stmt.executeQuery(sql);

while(rs.next())
{
	for(i=1;i<=6;i++)
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

public String umarksArrearList(String subcode)
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
sql="select s.rollno,regno,s.name,s.year,sf,grade from umarks u,stud s,student st,registerno r,department d "
+" where r.registerno=regno and s.rollno=st.rollno and st.rollno=r.rollno and d.dno=s.department "
+" and active=1 and s.academicyear in (select academicyear from academicyear) and subcode='"+subcode+"' and grade in ('U','UA','RA')"
//+" and u.year+u.month in (select max(year+month) from umarks) order by s.year desc,d.dno,grade";
+" and u.year+u.month in (select max(year+month) from umarks) and u.year=(select max(year) from umarks)order by s.year desc,d.dno,grade";


rs = stmt.executeQuery(sql);

while(rs.next())
{
	for(i=1;i<=6;i++)
	{
		report[i-1][count]=rs.getString(i);
	}
	count++;
}
rs.close();

sql="select max(subjectname) from syllabus where subjectcode='"+subcode+"'";

rs = stmt.executeQuery(sql);

if(rs.next())
{
		report[0][count]=rs.getString(1);
}
rs.close();

stmt.close();
connMgr.freeConnection("xavier",con);
error= "success"; 
}catch(Exception e){error=(e.toString()+count+i+sql);}
return error; 
}

public String umarksList(int rno)
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
		
quer="select max(umarks.id),max(semester),max(subcode),max(subjectname),max(credit),max(grade),max(syllabus.theoryorpractical) tp,max(umarks.year),max(month),max(umarks.id) "
+" from subjectidentify,syllabus,stud,umarks,registerno  "
+" where registerno.registerno=umarks.regno and umarks.subcode=syllabus.subjectcode and subjectidentify.id=syllabus.id "
+" and stud.rollno="+rno+" and subjectidentify.dno=stud.department and registerno.rollno=stud.rollno "
+" and subjectidentify.academicyear=stud.academicyear "
+" group by semester,subcode,grade,umarks.year,month,umarks.id "
+" order by semester,tp desc,subcode,umarks.year,month";

int hyr=0;
String rollno=rno+"";
hyr=Integer.parseInt(rollno.substring(2,4));


rs = stmt.executeQuery(quer);

while(rs.next())
{
	for(i=1;i<=10;i++)
	{
		report[i-1][count]=rs.getString(i);
	}
	
	try{

if(hyr>=17)
{
  if(report[5][count].equals("O"))
        report[10][count]="10";
  else if(report[5][count].equals("A+"))
        report[10][count]="9";
  else if(report[5][count].equals("A"))
        report[10][count]="8";
  else if(report[5][count].equals("B+"))
        report[10][count]="7";
  else if(report[5][count].equals("B"))
        report[10][count]="6";
  else if(report[5][count].equals("C"))
        report[10][count]="5";
  else 
        report[10][count]="0";
}       
else
{
  if (report[5][count].equals("S"))
        report[10][count]="10";
  else if(report[5][count].equals("A"))
        report[10][count]="9";
  else if(report[5][count].equals("B"))
        report[10][count]="8";
  else if(report[5][count].equals("C"))
        report[10][count]="7";
  else if(report[5][count].equals("D"))
        report[10][count]="6";
  else if(report[5][count].equals("E"))
        report[10][count]="5";
  else 
        report[10][count]="0";
}       

 	
}catch(Exception e){}

	count++;
}
rs.close();

stmt.close();
connMgr.freeConnection("xavier",con);
error= "success"; 
}catch(Exception e){error=(e.toString()+count+i+sql);}
return error; 
}


public String umarksClassSub(String subcode,int dno,int year,int semester,String accyear)
{
int i=0;
count=0;
int m=0;
int y=0;

int sem=1;

m=(semester%2)+1;
if(m==1)
{
y=Integer.parseInt(accyear.substring(8,12));
sem=2;
}
else
{
y=Integer.parseInt(accyear.substring(0,4));
sem=1;
}

try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;

//quer="select t1.rno,t1.name,t1.registerno,isnull(t2.subjectname,0),isnull(t2.credit,0),isnull(t2.grade,'-'),isnull(t2.year,0),isnull(t2.month,0),isnull(t2.subcode,0),t1.registerno from "
//+" (select stud.rollno rno,stud.name,registerno from subjectidentify,stud,student,registerno "
//+" where stud.rollno=student.rollno and stud.rollno=registerno.rollno and subjectidentify.dno=stud.department and subjectidentify.academicyear=stud.academicyear "
if(dno>90)	
quer="select t1.rno,t1.name,t1.registerno,isnull(t1.subjectname,0),isnull(t1.credit,0),isnull(t2.grade,'-'),isnull(t2.year,0),isnull(t2.month,0),isnull(t1.subjectcode,0),t1.registerno from  "
+" (select stud.rollno rno,student.name,registerno,subjectname,credit,subjectcode from subjectidentify,stud,student,registerno,syllabus  "
+" where stud.rollno=student.rollno and stud.rollno=registerno.rollno and subjectidentify.dno=student.section and subjectidentify.academicyear=stud.academicyear "
+" and syllabus.subjectcode='"+subcode+"' and syllabus.id=subjectidentify.id "
+" and semester="+semester+" and stud.academicyear='"+accyear+"' and student.section="+dno+" and stud.year="+year+" and active=1) as t1 "
+" left join "
+" (select max(stud.rollno) rno,max(subjectname) subjectname,max(credit) credit,max(grade) grade,max(umarks.year) year,max(month) month,max(subcode) subcode "
+" from subjectidentify,syllabus,stud,umarks,registerno,student  "
+" where stud.rollno=student.rollno and stud.rollno=registerno.rollno and registerno.registerno=umarks.regno and umarks.subcode=syllabus.subjectcode and subjectidentify.id=syllabus.id "
+" and subjectidentify.dno=student.section and registerno.rollno=stud.rollno "
+" and subjectidentify.academicyear=stud.academicyear "
+" and semester="+semester+" and stud.academicyear='"+accyear+"' and student.section="+dno+" and stud.year="+year+" and subcode='"+subcode+"' and umarks.year="+y+" and umarks.month="+m
+" group by semester,subcode,grade,umarks.year,month,umarks.id) as t2 "
+" on t1.rno=t2.rno "
+" order by t1.rno";
else
quer="select t1.rno,t1.name,t1.registerno,isnull(t1.subjectname,0),isnull(t1.credit,0),isnull(t2.grade,'-'),isnull(t2.year,0),isnull(t2.month,0),isnull(t1.subjectcode,0),t1.registerno from  "
+" (select stud.rollno rno,stud.name,registerno,subjectname,credit,subjectcode from subjectidentify,stud,student,registerno,syllabus  "
+" where stud.rollno=student.rollno and stud.rollno=registerno.rollno and subjectidentify.dno=stud.department and subjectidentify.academicyear=stud.academicyear "
+" and syllabus.subjectcode='"+subcode+"' and syllabus.id=subjectidentify.id "
+" and semester="+semester+" and stud.academicyear='"+accyear+"' and stud.department="+dno+" and stud.year="+year+" and active=1) as t1 "
+" left join "
+" (select max(stud.rollno) rno,max(subjectname) subjectname,max(credit) credit,max(grade) grade,max(umarks.year) year,max(month) month,max(subcode) subcode "
+" from subjectidentify,syllabus,stud,umarks,registerno  "
+" where stud.rollno=registerno.rollno and registerno.registerno=umarks.regno and umarks.subcode=syllabus.subjectcode and subjectidentify.id=syllabus.id "
+" and subjectidentify.dno=stud.department and registerno.rollno=stud.rollno "
+" and subjectidentify.academicyear=stud.academicyear "
+" and semester="+semester+" and stud.academicyear='"+accyear+"' and stud.department="+dno+" and stud.year="+year+" and subcode='"+subcode+"' and umarks.year="+y+" and umarks.month="+m
+" group by semester,subcode,grade,umarks.year,month,umarks.id) as t2 "
+" on t1.rno=t2.rno "
+" order by t1.rno";


/*
select max(stud.rollno) rno,max(name),max(subcode),max(subjectname),max(credit),max(grade),max(syllabus.theoryorpractical) tp,max(umarks.year),max(month),max(registerno) "
+" from subjectidentify,syllabus,stud,umarks,registerno  "
+" where stud.rollno=registerno.rollno and registerno.registerno=umarks.regno and umarks.subcode=syllabus.subjectcode and subjectidentify.id=syllabus.id "
+" and subjectidentify.dno=stud.department and registerno.rollno=stud.rollno and subjectidentify.academicyear=stud.academicyear "
+" and semester="+semester+" and stud.academicyear='"+accyear+"' and dno="+dno+" and stud.year="+year+" and subcode='"+subcode+"' "
+" group by semester,subcode,grade,umarks.year,month,umarks.id order by rno";
*/

int hyr=0;


rs = stmt.executeQuery(quer);

while(rs.next())
{
	for(i=1;i<=10;i++)
	{
		report[i-1][count]=rs.getString(i);
	}
	count++;
}
rs.close();

	for(i=0;i<count;i++)
	{
	
	try{
hyr=Integer.parseInt(report[0][i].substring(2,4));

if(i==0)
{
  try{
        hyr=Integer.parseInt(report[0][i+1].substring(2,4));
  }catch(Exception e){}
}

//if(hyr>=17)
if((hyr>=17) && (hyr-semester>=8))
{
  if(report[5][i].equals("O"))
        report[10][i]="10";
  else if(report[5][i].equals("A+"))
        report[10][i]="9";
  else if(report[5][i].equals("A"))
        report[10][i]="8";
  else if(report[5][i].equals("B+"))
        report[10][i]="7";
  else if(report[5][i].equals("B"))
        report[10][i]="6";
  else if(report[5][i].equals("C"))
        report[10][i]="5";
  else if(report[5][i].equals("-"))
        report[10][i]="1";
//  else if(report[5][i].equals("UA"))
//        report[10][i]="-1";
  else 
        report[10][i]="0";
}       
else
{
  if (report[5][i].equals("S"))
        report[10][i]="10";
  else if(report[5][i].equals("A"))
        report[10][i]="9";
  else if(report[5][i].equals("B"))
        report[10][i]="8";
  else if(report[5][i].equals("C"))
        report[10][i]="7";
  else if(report[5][i].equals("D"))
        report[10][i]="6";
  else if(report[5][i].equals("E"))
        report[10][i]="5";
  else if(report[5][i].equals("-"))
        report[10][i]="1";
//  else if(report[5][i].equals("UA"))
//        report[10][i]="-1";
  else 
        report[10][i]="0";
}       

if (Integer.parseInt(report[10][i])==1)
        report[11][i]="0";
else        
        report[11][i]=(Integer.parseInt(report[10][i])*Integer.parseInt(report[4][i].substring(0,1)))+"";

 	
}catch(Exception e){sql=sql+e.toString();}

}


stmt.close();
connMgr.freeConnection("xavier",con);
//error= y+"success"+m; 
error= y+"success"+m; 
}catch(Exception e){error=(e.toString()+count+i+sql);}
return error; 
}

public String umarksSubStaff(int dno,int semester,String accyear)
{
int a1=0;
count=0;

int sem=1;
int m=0;
int y=0;
m=(semester%2)+1;
if(m==1)
{
y=Integer.parseInt(accyear.substring(8,12));
sem=1;
}
else
{
y=Integer.parseInt(accyear.substring(0,4));
sem=2;
}

try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;

quer="select s.id,semester,si.dno,subjectcode,subjectname,staff.staffid,name,credit,theoryorpractical,abbr from subjectidentify si,syllabus s,subjecthandled sh,staff "
+" where si.id=sh.id and si.id=s.id and s.subjectcode=subcode and staff.staffid=sh.staffid  "
+" and semester="+semester+" and si.dno="+dno+" and academicyear='"+accyear+"' and theoryorpractical in(0,1,3,4,5) and mainorsub='M' "
+" order by theoryorpractical desc";


rs = stmt.executeQuery(quer);



while(rs.next())
{
	for(int i=1;i<=10;i++)
	{
		report[i-1][count]=rs.getString(i);
	}
	count++;
}
rs.close();

int hyr=0;
count1=0;

try{
hyr=Integer.parseInt(accyear.substring(2,4));

if((hyr>=17) && (hyr-semester>=8))
{
  repin[1]="'O'"; 	repin[11]="10";	
  repin[2]="'A+'";  	repin[12]="9";
  repin[3]="'A'"; 	repin[13]="8";
  repin[4]="'B+'";  	repin[14]="7";
  repin[5]="'B'"; 	repin[15]="6";
  repin[6]="'C'"; 	repin[16]="5";
  repin[7]="'U','RA'"; 	repin[17]="0";
  repin[8]="'UA','MP'"; 	repin[18]="0";
//  repin[8]="RA"; 	repin[18]="0";
//  repin[9]="MP"; 	repin[19]="0";
  count1=8;
}       
else
{
  repin[1]="'S'"; 	repin[11]="10";
  repin[2]="'A'";  	repin[12]="9";
  repin[3]="'B'"; 	repin[13]="8";
  repin[4]="'C'";  	repin[14]="7";
  repin[5]="'D'"; 	repin[15]="6";
  repin[6]="'E'";  	repin[16]="5";
  repin[7]="'U','RA'"; 	repin[17]="0";
  repin[8]="'UA"; 	repin[18]="0";
  count1=8;
}       
	
}catch(Exception e){sql=sql+e.toString();}

double totpass=0;
double totstud=0;

double jk=0;

	for(int j=1;j<=count1;j++)
	{
	repin[20+j]="0";
	}

for(int i=0;i<count;i++)
{
totpass=0;
totstud=0;
	for(int j=1;j<count1;j++)
	{
if(dno>90)
quer="select count(*) from (select max(stud.rollno) rno,max(student.name) name,max(subcode) subcode,max(subjectname) subjectname,max(credit) credit,max(grade) grade,max(syllabus.theoryorpractical) tp,max(umarks.year) year,max(month) month,max(registerno) registerno "
+" from subjectidentify,syllabus,stud,umarks,registerno,student  "
+" where stud.rollno=student.rollno and stud.rollno=registerno.rollno and registerno.registerno=umarks.regno and umarks.subcode=syllabus.subjectcode and subjectidentify.id=syllabus.id "
+" and subjectidentify.dno=student.section and registerno.rollno=stud.rollno and subjectidentify.academicyear=stud.academicyear "
+" and semester="+semester+" and stud.academicyear='"+accyear+"' and section="+dno+" and stud.year="+((semester+1)/2)+" and subcode='"+report[3][i]+"' "
+" and umarks.year="+y+" and month="+sem
+" group by semester,subcode,grade,umarks.year,month,umarks.id ) t1 where t1.grade in("+repin[j]+")";
else
quer="select count(*) from (select max(stud.rollno) rno,max(name) name,max(subcode) subcode,max(subjectname) subjectname,max(credit) credit,max(grade) grade,max(syllabus.theoryorpractical) tp,max(umarks.year) year,max(month) month,max(registerno) registerno "
+" from subjectidentify,syllabus,stud,umarks,registerno  "
+" where stud.rollno=registerno.rollno and registerno.registerno=umarks.regno and umarks.subcode=syllabus.subjectcode and subjectidentify.id=syllabus.id "
+" and subjectidentify.dno=stud.department and registerno.rollno=stud.rollno and subjectidentify.academicyear=stud.academicyear "
+" and semester="+semester+" and stud.academicyear='"+accyear+"' and dno="+dno+" and stud.year="+((semester+1)/2)+" and subcode='"+report[3][i]+"' "
+" and umarks.year="+y+" and month="+sem
+" group by semester,subcode,grade,umarks.year,month,umarks.id ) t1 where t1.grade in("+repin[j]+")";

rs = stmt.executeQuery(quer);

	if(rs.next())
{
		report[10+j][i]=rs.getString(1);

}	
else
{
		report[10+j][i]="";

}	
rs.close();
jk=0;
jk=Double.parseDouble(report[10+j][i]);
if(j<count1-1)
{
	totpass+=jk;
	totstud+=jk;
}
if(j==count1-1)
{
	totstud+=jk;
}
repin[20+j]=(int)(Double.parseDouble(repin[20+j])+jk)+"";
	}
	quer="select count(*) from (select max(stud.rollno) rno,max(name) name,max(subcode) subcode,max(subjectname) subjectname,max(credit) credit,max(grade) grade,max(syllabus.theoryorpractical) tp,max(umarks.year) year,max(month) month,max(registerno) registerno "
+" from subjectidentify,syllabus,stud,umarks,registerno  "
+" where stud.rollno=registerno.rollno and registerno.registerno=umarks.regno and umarks.subcode=syllabus.subjectcode and subjectidentify.id=syllabus.id "
+" and subjectidentify.dno=stud.department and registerno.rollno=stud.rollno and subjectidentify.academicyear=stud.academicyear "
+" and semester="+semester+" and stud.academicyear='"+accyear+"' and dno="+dno+" and stud.year="+((semester+1)/2)+" and subcode='"+report[3][i]+"' "
+" and umarks.year="+y+" and month="+sem
+" group by semester,subcode,grade,umarks.year,month,umarks.id ) t1 where t1.grade not in('O','A+','A','B+','B','S','A','B','C','D','E','U','RA')";

rs = stmt.executeQuery(quer);

	if(rs.next())
{
		report[10+count1][i]=rs.getString(1);

}	
else
{
			report[10+count1][i]="";

}
rs.close();

jk=0;
jk=Double.parseDouble(report[10+count1][i]);
repin[20+count1]=(int)(Double.parseDouble(repin[20+count1])+jk)+"";



report[10+count1+1][i]=(Math.round((totpass*10000.0)/totstud))/100.0+"";
}
jk=0;
totpass=0;
totstud=0;

	for(int j=1;j<count1;j++)
	{
	jk=Double.parseDouble(repin[20+j]);

if(j<count1-1)
{
	totpass+=jk;
	totstud+=jk;
}
if(j==count1-1)
{
	totstud+=jk;
}

}

report[10+count1+1][count]=(Math.round((totpass*10000.0)/totstud))/100.0+"";


stmt.close();
connMgr.freeConnection("xavier",con);
error= "success"; 
}catch(Exception e){error=(e.toString()+count+sql);}
return error; 
}



public String umarksSubAllStaff(String dnoin,String tpin,String oddeven,String accyear)
{
int a1=0;
count=0;
int semester=0;
int dno=0;

int sem=1;
int m=0;
int y=0;

String semin="1,3,5,7";
if(oddeven.equals("ODD"))
{
	semin="1,3,5,7";
y=Integer.parseInt(accyear.substring(0,4));
sem=2;
}

else
{
	semin="2,4,6,8";
y=Integer.parseInt(accyear.substring(8,12));
sem=1;
}



try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;
		
quer="select s.id,semester,si.dno,subjectcode,subjectname,staff.staffid,name,credit,theoryorpractical,abbr,sf from subjectidentify si,syllabus s,subjecthandled sh,staff,department d "
+" where si.id=sh.id and si.id=s.id and s.subjectcode=subcode and staff.staffid=sh.staffid and d.dno=si.dno "
+" and semester in ("+semin+") and staff.dno in ("+dnoin+") and academicyear='"+accyear+"' and theoryorpractical in("+tpin+") and mainorsub='M' "
+" order by staff.dno,staff.designation,staff.staffid";

rs = stmt.executeQuery(quer);

while(rs.next())
{
	for(int i=1;i<=11;i++)
	{
		report[i-1][count]=rs.getString(i);
	}
	count++;
}
rs.close();

int hyr=0;
count1=0;

double totpass=0;
double totstud=0;

double jk=0;

int p=0;
int s=0;

hyr=Integer.parseInt(accyear.substring(2,4));


for(int i=0;i<count;i++)
{
totpass=0;
totstud=0;


try{


semester=Integer.parseInt(report[1][i]);

if((hyr>=17) && (hyr-semester>=8))
{
  repin[1]="'O'"; 	repin[11]="10";	
  repin[2]="'A+'";  	repin[12]="9";
  repin[3]="'A'"; 	repin[13]="8";
  repin[4]="'B+'";  	repin[14]="7";
  repin[5]="'B'"; 	repin[15]="6";
  repin[6]="'C'"; 	repin[16]="5";
  repin[7]="'U','RA'"; 	repin[17]="0";
  repin[8]="'UA'"; 	repin[18]="0";
//repin[8]="RA"; 	repin[18]="0";
//  repin[9]="MP"; 	repin[19]="0";
  count1=8;
}       
else
{
  repin[1]="'S'"; 	repin[11]="10";
  repin[2]="'A";  	repin[12]="9";
  repin[3]="'B'"; 	repin[13]="8";
  repin[4]="'C'";  	repin[14]="7";
  repin[5]="'D'"; 	repin[15]="6";
  repin[6]="'E'";  	repin[16]="5";
  repin[7]="'U','RA'"; 	repin[17]="0";
  repin[8]="'UA'"; 	repin[18]="0";
//  repin[9]="RA"; 	repin[19]="0";
  count1=8;
}       



/*

hyr=Integer.parseInt(accyear.substring(2,4));

if((hyr>=17) && (hyr-semester>8))
{
  if(report[5][count].equals("O"))
        report[10][count]="10";
  else if(report[5][count].equals("A+"))
        report[10][count]="9";
  else if(report[5][count].equals("A"))
        report[10][count]="8";
  else if(report[5][count].equals("B+"))
        report[10][count]="7";
  else if(report[5][count].equals("B"))
        report[10][count]="6";
  else if(report[5][count].equals("C"))
        report[10][count]="5";
  else if(report[5][count].equals("-"))
        report[10][count]="1";
  else 
        report[10][count]="0";
}       
else
{
  if (report[5][count].equals("S"))
        report[10][count]="10";
  else if(report[5][count].equals("A"))
        report[10][count]="9";
  else if(report[5][count].equals("B"))
        report[10][count]="8";
  else if(report[5][count].equals("C"))
        report[10][count]="7";
  else if(report[5][count].equals("D"))
        report[10][count]="6";
  else if(report[5][count].equals("E"))
        report[10][count]="5";
  else if(report[5][count].equals("-"))
        report[10][count]="1";
  else 
        report[10][count]="0";
}  
*/	
}catch(Exception e){}

quer=semester+"First"+count1;

	for(int j=1;j<=count1;j++)
	{
	repin[20+j]="0";
	}
try{
semester=Integer.parseInt(report[1][i]);
dno=Integer.parseInt(report[2][i]);
}catch(Exception e){semester=0;dno=0;}

quer=semester+"First"+dno;

	for(int j=1;j<count1;j++)
	{
try{

if(dno>90)	
{
quer="select count(*) from (select max(stud.rollno) rno,max(student.name) name,max(subcode) subcode,max(subjectname) subjectname,max(credit) credit,max(grade) grade,max(syllabus.theoryorpractical) tp,max(umarks.year) year,max(month) month,max(registerno) registerno "
+" from subjectidentify,syllabus,stud,umarks,registerno,student  "
+" where stud.rollno=student.rollno and stud.rollno=registerno.rollno and registerno.registerno=umarks.regno and umarks.subcode=syllabus.subjectcode and subjectidentify.id=syllabus.id "
+" and subjectidentify.dno=student.section and registerno.rollno=stud.rollno and subjectidentify.academicyear=stud.academicyear "
+" and semester="+semester+" and stud.academicyear='"+accyear+"' and dno="+dno+" and stud.year="+((semester+1)/2)+" and subcode='"+report[3][i]+"' "
+" and umarks.year="+y+" and month="+sem 
+" group by semester,subcode,grade,umarks.year,month,umarks.id ) t1 where t1.grade in("+repin[j]+")";
}
else
{
quer="select count(*) from (select max(stud.rollno) rno,max(name) name,max(subcode) subcode,max(subjectname) subjectname,max(credit) credit,max(grade) grade,max(syllabus.theoryorpractical) tp,max(umarks.year) year,max(month) month,max(registerno) registerno "
+" from subjectidentify,syllabus,stud,umarks,registerno  "
+" where stud.rollno=registerno.rollno and registerno.registerno=umarks.regno and umarks.subcode=syllabus.subjectcode and subjectidentify.id=syllabus.id "
+" and subjectidentify.dno=stud.department and registerno.rollno=stud.rollno and subjectidentify.academicyear=stud.academicyear "
+" and semester="+semester+" and stud.academicyear='"+accyear+"' and dno="+dno+" and stud.year="+((semester+1)/2)+" and subcode='"+report[3][i]+"' "
+" and umarks.year="+y+" and month="+sem 
+" group by semester,subcode,grade,umarks.year,month,umarks.id ) t1 where t1.grade in("+repin[j]+")";
}

rs = stmt.executeQuery(quer);

if(rs.next())
{
		report[10+j][i]=rs.getString(1);
}	
else
{
		report[10+j][i]="";
}	

rs.close();
try{
jk=0;

try{
jk=Double.parseDouble(report[10+j][i]);
}catch(Exception e){jk=0;}

if(j<count1-1)
{
	totpass+=jk;
	totstud+=jk;
}
if(j==count1-1)
{
	totstud+=jk;
}
repin[20+j]=(int)(Double.parseDouble(repin[20+j])+jk)+"";
}catch(Exception e){}
}catch(Exception e){}

}
if(dno>90)	
{
	quer="select count(*) from (select max(stud.rollno) rno,max(student.name) name,max(subcode) subcode,max(subjectname) subjectname,max(credit) credit,max(grade) grade,max(syllabus.theoryorpractical) tp,max(umarks.year) year,max(month) month,max(registerno) registerno "
+" from subjectidentify,syllabus,stud,umarks,registerno,student  "
+" where stud.rollno=student.rollno and stud.rollno=registerno.rollno and registerno.registerno=umarks.regno and umarks.subcode=syllabus.subjectcode and subjectidentify.id=syllabus.id "
+" and subjectidentify.dno=student.section and registerno.rollno=stud.rollno and subjectidentify.academicyear=stud.academicyear "
+" and semester="+semester+" and stud.academicyear='"+accyear+"' and dno="+dno+" and stud.year="+((semester+1)/2)+" and subcode='"+report[3][i]+"' "
+" and umarks.year="+y+" and month="+sem 
+" group by semester,subcode,grade,umarks.year,month,umarks.id ) t1 where t1.grade not in('O','A+','A','B+','B','S','A','B','C','D','E','UA')";
}
else
{
	quer="select count(*) from (select max(stud.rollno) rno,max(name) name,max(subcode) subcode,max(subjectname) subjectname,max(credit) credit,max(grade) grade,max(syllabus.theoryorpractical) tp,max(umarks.year) year,max(month) month,max(registerno) registerno "
+" from subjectidentify,syllabus,stud,umarks,registerno  "
+" where stud.rollno=registerno.rollno and registerno.registerno=umarks.regno and umarks.subcode=syllabus.subjectcode and subjectidentify.id=syllabus.id "
+" and subjectidentify.dno=stud.department and registerno.rollno=stud.rollno and subjectidentify.academicyear=stud.academicyear "
+" and semester="+semester+" and stud.academicyear='"+accyear+"' and dno="+dno+" and stud.year="+((semester+1)/2)+" and subcode='"+report[3][i]+"' "
+" and umarks.year="+y+" and month="+sem 
+" group by semester,subcode,grade,umarks.year,month,umarks.id ) t1 where t1.grade not in('O','A+','A','B+','B','S','A','B','C','D','E','U','RA')";
//+" group by semester,subcode,grade,umarks.year,month,umarks.id ) t1 where t1.grade not in('O','A+','A','B+','B','S','A','B','C','D','E')";
	
}
rs = stmt.executeQuery(quer);

	if(rs.next())
{
		report[30][i]=rs.getString(1);
}	
else
{
			report[30][i]="";

}
rs.close();

try{
jk=0;
p=(int)totpass;
s=(int)totstud;
try{
jk=Double.parseDouble(report[30][i]);
}catch(Exception e){jk=0;}

repin[20+count1]=(int)(Double.parseDouble(repin[20+count1])+jk)+"";
if(totpass>0)
report[41][i]=(Math.round((totpass*10000.0)/totstud))/100.0+"";
else
{
report[41][i]="0.0";
}
report[42][i]=p+"";
report[43][i]=s+"";

}catch(Exception e){}

}

try{
jk=0;
totpass=0;
totstud=0;

	for(int j=1;j<count1;j++)
	{
try{
try{
	jk=Double.parseDouble(repin[20+j]);
}catch(Exception e){jk=0;}

if(j<count1-1)
{
	totpass+=jk;
	totstud+=jk;
}
if(j==count1-1)
{
	totstud+=jk;
}
}catch(Exception e){semester=0;dno=0;}

}

report[41][count]=(Math.round((totpass*10000.0)/totstud))/100.0+"";
}catch(Exception e){}


stmt.close();
connMgr.freeConnection("xavier",con);
error= "success"; 
}catch(Exception e){error=(e.toString()+count+sql);}
return error; 
}


public String umarksClass(int dno,int semester,String accyear)
{
int sem=1,ryear=1;
sem=(semester%2)+1;
ryear=Integer.parseInt(accyear.substring(0,4));
if(sem==1) ryear++;
double totstud=0;
double totpass=0;
double totfail=0;

double tstud=0;
double tpass=0;

count=0;
try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;
for(int j=1;j<=100;j++)
{
	count1=0;
tpass=0;
tstud=0;
for(int i=1;i<=4;i++)
{
try{
	totpass=0;
	totfail=0;
	totstud=1;
quer="select count(*) from (select max(stud.rollno) as rno,count(*) as cnt from umarks,stud,registerno where umarks.regno=registerno.registerno and stud.rollno=registerno.rollno "
+" and department="+j+" and  stud.year="+i+" and academicyear='"+accyear+"' and umarks.year="+ryear+" and umarks.month="+sem+" and grade in('U','RA') group by stud.rollno) as q";
rs = stmt.executeQuery(quer);
if(rs.next())
{
	totfail=rs.getInt(1);
}
rs.close();
quer="select count(*) from (select max(stud.rollno) as rno,count(*) as cnt from umarks,stud,registerno where umarks.regno=registerno.registerno and stud.rollno=registerno.rollno "
+" and department="+j+" and  stud.year="+i+" and academicyear='"+accyear+"' and umarks.year="+ryear+" and umarks.month="+sem+" group by stud.rollno) as q";
rs = stmt.executeQuery(quer);
if(rs.next())
{
	totstud=rs.getInt(1);
}
rs.close();

totpass=totstud-totfail;
if(!((totfail==0) && (totstud==1)))
{
  report[0][count]=dno+"";
  report[1][count]=i+"";
  report[2][count]=(int)totstud+"";
  report[3][count]=(int)totpass+"";
  report[4][count]=(Math.round((totpass*10000.0)/totstud))/100.0+"";
  count++;
  count1++;
}
}catch(Exception e){}
}


}


stmt.close();
connMgr.freeConnection("xavier",con);
error= "success"; 
}catch(Exception e){error=(e.toString()+count+sql);}
return error; 
}



public String umarkscgpa(int dno,int semester,String accyear)
{
int i=0;
int hyr=0;
count=0;
count1=0;
	float tc=0;
	float tgp=0;
	float cgpa=0;
	int ta=0;
	int tbl=0;
try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;

quer="select r.rollno,ltrim(rtrim(r.registerno)),s.name from student st,stud s,registerno r where s.rollno=st.rollno and s.rollno=r.rollno "
+" and department="+dno+" and academicyear='"+accyear+"' and year="+((semester+1)/2)+" and active=1 order by s.rollno";

rs = stmt.executeQuery(quer);

while(rs.next())
{
	for(i=1;i<=3;i++)
	{
		report3[i-1][count1]=rs.getString(i);
	}

	count1++;
}
rs.close();

for(int j=0;j<count1;j++)
{

tc=0;
tgp=0;
cgpa=0;
ta=0;
tbl=0;
count=0;

try{
quer="select count(*) from (select * from (select subcode,count(*) cnt from umarks u,registerno r where u.regno=r.registerno "
+" and r.rollno="+report3[0][j]+" group by subcode) as q where q.cnt>1) as p";

rs = stmt.executeQuery(quer);

if(rs.next())
{
	tbl=rs.getInt(1);
}

rs.close();
}catch(Exception e){ tbl=0;}


quer="select max(umarks.id),max(semester),max(subcode),max(subjectname),max(credit),max(grade),max(syllabus.theoryorpractical) tp,max(umarks.year),max(month),max(umarks.id) "
+" from subjectidentify,syllabus,stud,umarks,registerno  "
+" where registerno.registerno=umarks.regno and umarks.subcode=syllabus.subjectcode and subjectidentify.id=syllabus.id "
+" and stud.rollno="+report3[0][j]+" and subjectidentify.dno=stud.department and registerno.rollno=stud.rollno "
+" and subjectidentify.academicyear=stud.academicyear "
+" group by semester,subcode,grade,umarks.year,month,umarks.id "
+" order by semester,tp desc,subcode,umarks.year,month";


rs = stmt.executeQuery(quer);

while(rs.next())
{
	for(i=1;i<=10;i++)
	{
		report[i-1][count]=rs.getString(i);
	}
	
	try{

hyr=Integer.parseInt(accyear.substring(2,4));

if((hyr>=17) && (hyr-semester>8))
{
  if(report[5][count].equals("O"))
        report[10][count]="10";
  else if(report[5][count].equals("A+"))
        report[10][count]="9";
  else if(report[5][count].equals("A"))
        report[10][count]="8";
  else if(report[5][count].equals("B+"))
        report[10][count]="7";
  else if(report[5][count].equals("B"))
        report[10][count]="6";
  else if(report[5][count].equals("C"))
        report[10][count]="5";
  else if(report[5][count].equals("-"))
        report[10][count]="1";
  else 
        report[10][count]="0";
}       
else
{
  if (report[5][count].equals("S"))
        report[10][count]="10";
  else if(report[5][count].equals("A"))
        report[10][count]="9";
  else if(report[5][count].equals("B"))
        report[10][count]="8";
  else if(report[5][count].equals("C"))
        report[10][count]="7";
  else if(report[5][count].equals("D"))
        report[10][count]="6";
  else if(report[5][count].equals("E"))
        report[10][count]="5";
  else if(report[5][count].equals("-"))
        report[10][count]="1";
  else 
        report[10][count]="0";
}       

}catch(Exception e){}

try{
if (Integer.parseInt(report[10][count])==1)
        report[11][count]="0";
else        
        report[11][count]=(Integer.parseInt(report[10][count])*Integer.parseInt(report[4][count].substring(0,1)))+"";
}catch(Exception e){}

	count++;
}
rs.close();

for(i=0;i<count;i++)
{
try{
if(i<count-1)
{
if(report[2][i].equals(report[2][i+1])){ tc=tc;}
else
{
tc=tc+Float.parseFloat(report[4][i]);
tgp=tgp+Float.parseFloat(report[11][i]);
if(Integer.parseInt(report[11][i])==0) {ta++;}
}
}
else
{
tc=tc+Float.parseFloat(report[4][i]);
tgp=tgp+Float.parseFloat(report[11][i]);
if(Integer.parseInt(report[11][i])==0) {ta++;}
}
}catch(Exception e){}
}
cgpa=tgp/tc;

report3[3][j]=ta+"";
report3[4][j]=tbl+"";
report3[5][j]=cgpa+"";
}

count=count1;

stmt.close();
connMgr.freeConnection("xavier",con);
error= "success"; 
}catch(Exception e){error=(e.toString()+count+i+sql);}
return error; 
}


public void setquer(String quer) {this.quer=quer;}  public String getquer() {	return this.quer;}
public void setrep(String[] rep) {this.rep=rep;}  public String[] getrep() {	return this.rep;}
public void setrepin(String[] repin) {this.repin=repin;}  public String[] getrepin() {	return this.repin;}
public void setreport(String[][] report) {this.report=report;}  public String[][] getreport() {	return this.report;}
public void setreport1(String[] report1) {this.report1=report1;}  public String[] getreport1() {	return this.report1;}
public void setreport2(String[] report2) {this.report2=report2;}  public String[] getreport2() {	return this.report2;}
public void setreport3(String[][] report3) {this.report3=report3;}  public String[][] getreport3() {	return this.report3;}
public void setcount(int count) {this.count=count;} public int getcount(){	return this.count;}
public void setcount1(int count1) {this.count1=count1;} public int getcount1(){	return this.count1;}
public void setfname(String fname) { this.fname = fname; } public String getfname() { return this.fname; } 
public void setpname(String pname) { this.pname = pname; } public String getpname() { return this.pname; } 
public void setacademicyear(String academicyear) { this.academicyear = academicyear; } public String getacademicyear() { return this.academicyear; } 



}




	
	