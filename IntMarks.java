package sxcce;
import java.sql.*;
import dd.DBConnectionManager;
import java.util.StringTokenizer;

public class IntMarks
	{
		int count=0;
		int count1=0;
		String report[][] = new String[25][3000];
		String report1[][] = new String[25][3000];
		String subname[] = new String[25];
		String abbr[] = new String[25];
		String credit[] = new String[30];
		String subcode[] = new String[25];
		int theoryorpractical[] = new int[30];
		int maximummark[] = new int[30];
		int passmark[] = new int[30];
		int semester[] = new int[30];
		int dno[] = new int[30];
		int year[] = new int[30];
		int id=0;
		int examno=0;
		String academicyear="";
        String handledby[] = new String[30];
        int maxstud[] = new int[30];
        int maxpassstud[] = new int[30];
        int maxmark[] = new int[30];
        String maxmarkname[] = new String[30];
        int allexamstud=0;
        int allexampassed=0;
        int rnocount=0;
        int rollno[] = new int[400];
        String sql="";
        String error="";

public void ViewIndSubject(String staffid)
			{count=0;
				for(int i=0;i<15;i++)
				   {
				   	subname[i]="";
				   	subcode[i]="";
				   }
				   
				   DBConnectionManager connMgr=null; Connection con =null;
				try
					{
                    connMgr = DBConnectionManager.getInstance(); 
                    con = connMgr.getConnection("xavier");
 			        Statement stmt = con.createStatement();
					ResultSet ps;

			        ps = stmt.executeQuery("select subjecthandled.subcode,syllabus.subjectname,subjecthandled.id,credit,theoryorpractical,maximummark,passmark,abbr, "
+" semester,dno,academicyear,subjecthandled.staffid from subjecthandled,subjectidentify,syllabus where "
+" subjecthandled.id=subjectidentify.id and subjecthandled.id=syllabus.id and subjecthandled.subcode=subjectcode and mainorsub='M' "
+" and staffid='"+staffid+"' and academicyear in (select academicyear from academicyear)  and (semester%2) in (select (semester%2) from academicyear)");
					while(ps.next())
					{
						subcode[count]=ps.getString(1);
						subname[count]=ps.getString(2);
						id=ps.getInt(3);
						credit[count]=ps.getString(4);
						theoryorpractical[count]=ps.getInt(5);
						maximummark[count]=ps.getInt(6);
						passmark[count]=ps.getInt(7);
						abbr[count]=ps.getString(8);
						semester[count]=ps.getInt(9);
						dno[count]=ps.getInt(10);
						year[count]=(semester[count]+1)/2;
						count++;
					}
					ps.close();
					stmt.close();
					}catch(Exception e){}
finally{connMgr.freeConnection("xavier",con);}
			}
			

public void ViewIndSubjects(String staffid,String accyear,String oddeven)
			{
int semno=1;
if(oddeven.equals("ODD"))
semno=1;
else
semno=2;
 				
				count=0;
				for(int i=0;i<15;i++)
				   {
				   	subname[i]="";
				   	subcode[i]="";
				   }
				   
				   DBConnectionManager connMgr=null; Connection con =null;
				try
					{
                    connMgr = DBConnectionManager.getInstance(); 
                    con = connMgr.getConnection("xavier");
 			        Statement stmt = con.createStatement();
					ResultSet ps;
			        ps = stmt.executeQuery("select subjecthandled.subcode,syllabus.subjectname,subjecthandled.id,credit,theoryorpractical,maximummark,passmark,abbr, "
+" semester,dno,academicyear,subjecthandled.staffid from subjecthandled,subjectidentify,syllabus where "
+" subjecthandled.id=subjectidentify.id and subjecthandled.id=syllabus.id and subjecthandled.subcode=subjectcode and mainorsub='M' "
+" and staffid='"+staffid+"' and academicyear='"+accyear+"' and (semester%2)="+ (semno%2));

					while(ps.next())
					{
						subcode[count]=ps.getString(1);
						subname[count]=ps.getString(2);
						id=ps.getInt(3);
						credit[count]=ps.getString(4);
						theoryorpractical[count]=ps.getInt(5);
						maximummark[count]=ps.getInt(6);
						passmark[count]=ps.getInt(7);
						abbr[count]=ps.getString(8);
						semester[count]=ps.getInt(9);
						dno[count]=ps.getInt(10);
						year[count]=(semester[count]+1)/2;
						count++;
					}
					ps.close();
					stmt.close();
					}catch(Exception e){}
finally{connMgr.freeConnection("xavier",con);}
			}

public void ViewIndSubjectsDept(String staffid,String accyear,String oddeven)
			{
int semno=1;
if(oddeven.equals("ODD"))
semno=1;
else
semno=2;
 				
				count=0;
				for(int i=0;i<15;i++)
				   {
				   	subname[i]="";
				   	subcode[i]="";
				   }
				   
				   DBConnectionManager connMgr=null; Connection con =null;
				try
					{
                    connMgr = DBConnectionManager.getInstance(); 
                    con = connMgr.getConnection("xavier");
 			        Statement stmt = con.createStatement();
					ResultSet ps;
			        ps = stmt.executeQuery("select subjecthandled.subcode,rtrim(syllabus.subjectname)+'('+sf+')',subjecthandled.id,credit,theoryorpractical,maximummark,passmark,abbr, "
+" semester,si.dno,academicyear,subjecthandled.staffid from subjecthandled,subjectidentify si,syllabus,department where department.dno=si.dno and "
+" subjecthandled.id=si.id and subjecthandled.id=syllabus.id and subjecthandled.subcode=subjectcode and mainorsub='M' "
+" and staffid='"+staffid+"' and academicyear='"+accyear+"' and (si.semester%2)="+ (semno%2));

					while(ps.next())
					{
						subcode[count]=ps.getString(1);
						subname[count]=ps.getString(2);
						id=ps.getInt(3);
						credit[count]=ps.getString(4);
						theoryorpractical[count]=ps.getInt(5);
						maximummark[count]=ps.getInt(6);
						passmark[count]=ps.getInt(7);
						abbr[count]=ps.getString(8);
						semester[count]=ps.getInt(9);
						dno[count]=ps.getInt(10);
						year[count]=(semester[count]+1)/2;
						count++;
					}
					ps.close();
					stmt.close();
					}catch(Exception e){}
finally{connMgr.freeConnection("xavier",con);}
			}
						
public String exdate(String exyear,String ex,int sem,int dno1)
{
    int i=0,previous=0;
	count=0;
	examno=1;
	DBConnectionManager connMgr=null; Connection con =null;
	try
	{
                       connMgr = DBConnectionManager.getInstance(); 
                       con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;

                    sql="select examno from examyear where year='"+exyear+"' and exam='"+ex+"' and semester="+sem+" and dno="+dno1;
					rs = stmt.executeQuery(sql);
					if(rs.next()) {	examno=rs.getInt(1); previous=1; }
					rs.close();
		    	    if(previous==0){
						rs = stmt.executeQuery("select max(examno) from examyear");
						if(rs.next()) { examno=rs.getInt(1); examno++; }
						rs.close();
					    PreparedStatement ps;
					    ps = con.prepareStatement("insert into examyear values(?,?,?,?,?)");
					    ps.setString(1,exyear);
					    ps.setString(2,ex);
					    ps.setInt(3,sem);
					    ps.setInt(4,examno);
					    ps.setInt(5,dno1);
					    i=ps.executeUpdate();
					    ps.close();
  	    	        }

sql="select subjecthandled.subcode,syllabus.subjectname,subjecthandled.id,credit,theoryorpractical,maximummark,passmark,abbr, "
+" semester,subjectidentify.dno,academicyear,subjecthandled.staffid,staff.name from subjecthandled,subjectidentify,syllabus,staff where staff.staffid=subjecthandled.staffid and "
+" subjecthandled.id=subjectidentify.id and subjecthandled.id=syllabus.id and subjecthandled.subcode=subjectcode and mainorsub='M' and theoryorpractical in(1,4,5) " 
+" and subjectidentify.dno="+dno1+" and semester="+sem+" and academicyear='"+exyear+"'";
 rs = stmt.executeQuery(sql);

while(rs.next())
{
	for(i=1;i<=13;i++)
	{
		report[i-1][count]=rs.getString(i);
	}
	report[14][count]=examno+"";
	count++;
}
rs.close();

	for(i=0;i<count;i++)
	{
		rs = stmt.executeQuery("select ltrim(str(day(date)))+'/'+ltrim(str(month(date)))+'/'+ltrim(str(year(date))),status,qset from examdate where examno="+examno+" and subcode='"+report[0][i]+"'");
		if(rs.next()) { report[15][i]=rs.getString(1); report[16][i]=rs.getString(2); report[17][i]=rs.getString(3);  }
		else { report[15][i]=""; report[16][i]="";  report[17][i]="1"; }
    	rs.close();
		rs = stmt.executeQuery("select ltrim(rtrim(fname1)) from questionupload  where examno="+examno+" and  qset="+report[17][i]+" and subcode='"+report[0][i]+"'");
		if(rs.next()) { report[18][i]=rs.getString(1);  }
		else { report[18][i]=""; }
    	rs.close();
		rs = stmt.executeQuery("select ltrim(rtrim(fname1)) from questionupload  where examno="+examno+" and  qset=1 and subcode='"+report[0][i]+"'");
		if(rs.next()) { report[19][i]=rs.getString(1);  }
		else { report[19][i]=""; }
    	rs.close();
		rs = stmt.executeQuery("select ltrim(rtrim(fname1)) from questionupload  where examno="+examno+" and  qset=2 and subcode='"+report[0][i]+"'");
		if(rs.next()) { report[20][i]=rs.getString(1);  }
		else { report[20][i]=""; }
    	rs.close();
	}
stmt.close();
error= "success."; 
}catch(Exception e){error=(e.toString()+count+i+sql);}
finally{connMgr.freeConnection("xavier",con);
return error; 
}
}

public String exdate1(String exyear,String ex,int sem,int dno1,int t)
{
    int i=0,previous=0;
	count=0;
	examno=1;
	String tp="1,4,5";
	if(t==1)
		 tp="1,4,5";
    else
    	 tp="0,3,5";
	DBConnectionManager connMgr=null; Connection con =null;
	try
	{
                       connMgr = DBConnectionManager.getInstance(); 
                       con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;

                    sql="select examno from examyear where year='"+exyear+"' and exam='"+ex+"' and semester="+sem+" and dno="+dno1;
					rs = stmt.executeQuery(sql);
					if(rs.next()) {	examno=rs.getInt(1); previous=1; }
					rs.close();
		    	    if(previous==0){
						rs = stmt.executeQuery("select max(examno) from examyear");
						if(rs.next()) { examno=rs.getInt(1); examno++; }
						rs.close();
					    PreparedStatement ps;
					    ps = con.prepareStatement("insert into examyear values(?,?,?,?,?)");
					    ps.setString(1,exyear);
					    ps.setString(2,ex);
					    ps.setInt(3,sem);
					    ps.setInt(4,examno);
					    ps.setInt(5,dno1);
					    i=ps.executeUpdate();
					    ps.close();
  	    	        }

sql="select subjecthandled.subcode,syllabus.subjectname,subjecthandled.id,credit,theoryorpractical,maximummark,passmark,abbr, "
+" semester,subjectidentify.dno,academicyear,subjecthandled.staffid,staff.name from subjecthandled,subjectidentify,syllabus,staff where staff.staffid=subjecthandled.staffid and "
+" subjecthandled.id=subjectidentify.id and subjecthandled.id=syllabus.id and subjecthandled.subcode=subjectcode and mainorsub='M' and theoryorpractical in("+tp+") " 
+" and subjectidentify.dno="+dno1+" and semester="+sem+" and academicyear='"+exyear+"'";
 rs = stmt.executeQuery(sql);

while(rs.next())
{
	for(i=1;i<=13;i++)
	{
		report[i-1][count]=rs.getString(i);
	}
	report[14][count]=examno+"";
	count++;
}
rs.close();

	for(i=0;i<count;i++)
	{
		rs = stmt.executeQuery("select ltrim(str(day(date)))+'/'+ltrim(str(month(date)))+'/'+ltrim(str(year(date))),status from examdate where examno="+examno+" and subcode='"+report[0][i]+"'");
		if(rs.next()) { report[15][i]=rs.getString(1); report[16][i]=rs.getString(2);  }
		else { report[15][i]=""; report[16][i]="";  }
    	rs.close();
	}
stmt.close();
error= "success."; 
}catch(Exception e){error=(e.toString()+count+i+sql);}
finally{connMgr.freeConnection("xavier",con);
return error; 
}
}
public String exdateInsert(int ccount)
{
    int j=0,i=0,previous=0;
	count=0;
	examno=Integer.parseInt(report[0][0]);
	int exset=0;
	String d1="";
    String m1="";
    String y1="";
    int i1=0;
StringTokenizer st2;
String date1="";
error="";
	DBConnectionManager connMgr=null; Connection con =null;
	try
	{
                       connMgr = DBConnectionManager.getInstance(); 
                       con = connMgr.getConnection("xavier");
  					    Statement stmt;
					    ResultSet rs;
					    PreparedStatement ps;
for(int k=0;k<ccount;k++)
{
try{
		st2 = new StringTokenizer(report[2][k],"/");
		 d1=st2.nextToken();
		 m1=st2.nextToken();
		 y1=st2.nextToken();
	     date1=m1+"/"+d1+"/"+y1;
}catch(Exception e){error=error+e.toString();}
try{
	i1=Integer.parseInt(report[4][k]);
}catch(Exception e){i1=0;}

                    exset=0;
try{
                    sql="select examno from examdate where examno="+examno+" and subcode='"+report[1][k]+"'";
                    stmt= con.createStatement();
					rs = stmt.executeQuery(sql);
					if(rs.next())
					{
							examno=rs.getInt(1);
							exset=1;
					}
					rs.close();
					stmt.close();
					if(exset==1)
					{
                        sql="update examdate set date=?, status=?, qset=? where examno=? and subcode=?";
						ps = con.prepareStatement(sql);
						ps.setString(1,date1);
						ps.setString(2,report[3][k]);
						ps.setInt(3,i1);
						ps.setInt(4,examno);
						ps.setString(5,report[1][k]);
						j=ps.executeUpdate();
					    ps.close();
				    }					
				    	else
				    {
                        sql="insert into examdate values(?,?,?,?,?)";
				        ps = con.prepareStatement(sql);
						ps.setInt(1,examno);
						ps.setString(2,report[1][k]);
						ps.setString(3,date1);
						ps.setString(4,report[3][k]);
						ps.setInt(5,i1);
						j=ps.executeUpdate();
					    ps.close();
				    }
}catch(Exception e){error=error+e.toString();}

				    try{
                        sql="insert into questionupload(examno,qset,subcode) values(?,1,?)";
				        ps = con.prepareStatement(sql);
						ps.setInt(1,examno);
						ps.setString(2,report[1][k]);
						j=ps.executeUpdate();
					    ps.close();
}catch(Exception e){error=error+e.toString();}
				    try{
                        sql="insert into questionupload(examno,qset,subcode) values(?,2,?)";
				        ps = con.prepareStatement(sql);
						ps.setInt(1,examno);
						ps.setString(2,report[1][k]);
						j=ps.executeUpdate();
					    ps.close();
}catch(Exception e){error=error+e.toString();}

}
error= error+"success"; 
}catch(Exception e){error=error+(e.toString()+count+i+sql);}
finally{connMgr.freeConnection("xavier",con);
return error; 
}
}

public String questionselect(String scode,int eno)
{

   int j=0,i=0,previous=0;
	count=0;
	DBConnectionManager connMgr=null; Connection con =null;
	try
	{
                       connMgr = DBConnectionManager.getInstance(); 
                       con = connMgr.getConnection("xavier");
  					    Statement stmt;
					    ResultSet rs;
					    PreparedStatement ps;

                    sql="select qu.id,ltrim(rtrim(subjectcode)),ltrim(rtrim(subjectname)),ltrim(rtrim(exam)),si.semester,sf,si.dno,qu.examno,qset,fname1,year "
+" from syllabus sy,subjectidentify si,examyear ey,questionupload qu,department d "
+" where sy.id=si.id and ey.semester=si.semester and ey.dno=si.dno and sy.subjectcode=qu.subcode "
+" and d.dno=si.dno and si.academicyear=year and qu.examno=ey.examno and qu.subcode='"+scode+"' and qu.examno="+eno+" order by subjectcode,qset";
                    stmt= con.createStatement();
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
					stmt.close();

error= "success"; 
}catch(Exception e){error=(e.toString()+count+i+sql);}
finally{connMgr.freeConnection("xavier",con);
return error; 
}
}



public String exdateStatus(String ayear, String aexam, String astatus)
{
    int i=0;
	count=0;
	DBConnectionManager connMgr=null; Connection con =null;
	try
	{
                       connMgr = DBConnectionManager.getInstance(); 
                       con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;
sql="select sf,examyear.semester,subjecthandled.subcode,syllabus.subjectname,subjecthandled.staffid,staff.name,ltrim(str(day(date)))+'/'+ltrim(str(month(date)))+'/'+ltrim(str(year(date))),status "
+" from examdate,subjecthandled,subjectidentify,syllabus,staff,examyear,department where department.dno=subjectidentify.dno and staff.staffid=subjecthandled.staffid and "
+" subjecthandled.id=subjectidentify.id and subjecthandled.id=syllabus.id and subjecthandled.subcode=subjectcode and mainorsub='M' and theoryorpractical in(1,4,5)  and "
+" examyear.examno=examdate.examno and examyear.dno=subjectidentify.dno and examyear.semester=subjectidentify.semester and examdate.subcode=subjecthandled.subcode  "
+" and academicyear='"+ayear+"' and year='"+ayear+"' and exam='"+aexam+"'  and  status like'%"+astatus+"%' and date < getdate()-4 order by subjectidentify.dno,dname,examyear.semester";


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
error= "success"; 
}catch(Exception e){error=(e.toString()+count+i+sql);}
finally{connMgr.freeConnection("xavier",con);
return error; 
}
}

public String exdetails(String ayear, String aexam, int adno,int a)
{

	String m="mark";
	if(a==0) m="mark";
	else m="marka";

    int i=0;
	count=0;
	DBConnectionManager connMgr=null; Connection con =null;
	try
	{
                       connMgr = DBConnectionManager.getInstance(); 
                       con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;
sql="select examdate.examno,sf,examyear.semester,subjecthandled.subcode,syllabus.subjectname,subjecthandled.staffid,staff.name,ltrim(str(day(date)))+'/'+ltrim(str(month(date)))+'/'+ltrim(str(year(date))),status "
+" from examdate,subjecthandled,subjectidentify,syllabus,staff,examyear,department where department.dno=subjectidentify.dno and staff.staffid=subjecthandled.staffid and "
+" subjecthandled.id=subjectidentify.id and subjecthandled.id=syllabus.id and subjecthandled.subcode=subjectcode and mainorsub='M' and theoryorpractical in (1,4,5)  and "
+" examyear.examno=examdate.examno and examyear.dno=subjectidentify.dno and examyear.semester=subjectidentify.semester and examdate.subcode=subjecthandled.subcode  "
+" and examyear.year=academicyear and academicyear='"+ayear+"' and exam='"+aexam+"'  and  examyear.dno="+adno+" order by subjectidentify.dno,examyear.semester,examdate.subcode";


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
for(int j=0;j<count;j++)
{
report[9][j]="0";
report[10][j]="0";
report[11][j]="0";
report[12][j]="0";
report[13][j]="0";
report[14][j]="0";

sql="select count(*)+' ' from "+m+" where mark< 25  and examno="+report[0][j]+" and subjectcode='"+report[3][j]+"'";
rs = stmt.executeQuery(sql); while(rs.next()){ report[9][j]=rs.getString(1);} rs.close();

sql="select count(*)+' ' from "+m+" where mark >= 25 and mark< 40  and examno="+report[0][j]+" and subjectcode='"+report[3][j]+"'";
rs = stmt.executeQuery(sql); while(rs.next()){ report[10][j]=rs.getString(1);} rs.close();

sql="select count(*)+' ' from "+m+" where mark >= 40 and mark< 50  and examno="+report[0][j]+" and subjectcode='"+report[3][j]+"'";
rs = stmt.executeQuery(sql); while(rs.next()){ report[11][j]=rs.getString(1);} rs.close();

sql="select count(*)+' ' from "+m+" where mark >= 50 and mark< 80  and examno="+report[0][j]+" and subjectcode='"+report[3][j]+"'";
rs = stmt.executeQuery(sql); while(rs.next()){ report[12][j]=rs.getString(1);} rs.close();

sql="select count(*)+' ' from "+m+" where mark >= 80 and mark<= 100  and examno="+report[0][j]+" and subjectcode='"+report[3][j]+"'";
rs = stmt.executeQuery(sql); while(rs.next()){ report[13][j]=rs.getString(1);} rs.close();

sql="select count(*)+' ' from "+m+" where examno="+report[0][j]+" and subjectcode='"+report[3][j]+"'";
rs = stmt.executeQuery(sql); while(rs.next()){ report[14][j]=rs.getString(1);} rs.close();

}
stmt.close();
error= "success"; 
}catch(Exception e){error=(e.toString()+count+i+sql);}
finally{connMgr.freeConnection("xavier",con);
return error; 
}
}

public String exdetails1(String ayear, String aexam, String adnoin, String asemester, int a)
{
	String m="mark";
	
	if(a==0) m="mark";
	else m="marka";
	
    int i=0;
	count=0;
	DBConnectionManager connMgr=null; Connection con =null;
	try
	{
                       connMgr = DBConnectionManager.getInstance(); 
                       con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;
sql="select examdate.examno,sf,examyear.semester,subjecthandled.subcode,syllabus.subjectname,subjecthandled.staffid,staff.name,ltrim(str(day(date)))+'/'+ltrim(str(month(date)))+'/'+ltrim(str(year(date))),status "
+" from examdate,subjecthandled,subjectidentify,syllabus,staff,examyear,department where department.dno=subjectidentify.dno and staff.staffid=subjecthandled.staffid and "
+" subjecthandled.id=subjectidentify.id and subjecthandled.id=syllabus.id and subjecthandled.subcode=subjectcode and mainorsub='M' and theoryorpractical in (1,4,5)  and "
+" examyear.examno=examdate.examno and examyear.dno=subjectidentify.dno and examyear.semester=subjectidentify.semester and examdate.subcode=subjecthandled.subcode  "
+" and examyear.year=academicyear and academicyear='"+ayear+"' and exam='"+aexam+"' and examyear.semester in("+asemester+")  and  examyear.dno in("+adnoin+") order by subjectidentify.dno,examyear.semester,examdate.subcode";


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
for(int j=0;j<count;j++)
{
report[9][j]="0";
report[10][j]="0";
report[11][j]="0";
report[12][j]="0";
report[13][j]="0";
report[14][j]="0";
report[15][j]="0";
report[16][j]="0";
report[17][j]="0";
report[18][j]="0";
report[19][j]="0";
report[20][j]="0";
report[21][j]="0";
report[22][j]="0";

try	{
sql="select count(*)+' ' from "+m+" where examno="+report[0][j]+" and subjectcode='"+report[3][j]+"'";
rs = stmt.executeQuery(sql); while(rs.next()){ report[9][j]=rs.getString(1);} rs.close();
}catch(Exception e){}

try	{
sql="select count(*)+' ' from "+m+" where mark>= 50 and examno="+report[0][j]+" and subjectcode='"+report[3][j]+"'";
rs = stmt.executeQuery(sql); while(rs.next()){ report[10][j]=rs.getString(1);} rs.close();
}catch(Exception e){}

try	{
sql="select isnull(cast(cast(round(((sum(mark)*1.0)/count(*)),2) as decimal(10,2)) as varchar),0)+' ' from "+m+" where mark>=0  and examno="+report[0][j]+" and subjectcode='"+report[3][j]+"'";
rs = stmt.executeQuery(sql); while(rs.next()){ report[11][j]=rs.getString(1);} rs.close();
}catch(Exception e){}

try	{
sql="select isnull((max(mark)-min(mark)),0)+' ' from "+m+" where mark> 0 and examno="+report[0][j]+" and subjectcode='"+report[3][j]+"'";
rs = stmt.executeQuery(sql); while(rs.next()){ report[12][j]=rs.getString(1);} rs.close();
}catch(Exception e){}

try	{
sql="WITH SkewCTE AS ( SELECT SUM(1.0*mark) AS rx,  SUM(POWER(1.0*mark,2)) AS rx2,  SUM(POWER(1.0*mark,3)) AS rx3,  COUNT(1.0*mark) AS rn,  STDEV(1.0*mark) AS stdv,  AVG(1.0*mark) AS av FROM "+m+" where mark >0  and examno="+report[0][j]+" and subjectcode='"+report[3][j]+"' ) SELECT    isnull(cast(cast(((rx3 - 3*rx2*av + 3*rx*av*av - rn*av*av*av)    / (stdv*stdv*stdv) * rn / (rn-1) / (rn-2)) as decimal(10,2)) as varchar),0)+' ' AS Skewness FROM SkewCTE";
rs = stmt.executeQuery(sql); while(rs.next()){ report[13][j]=rs.getString(1);} rs.close();
}catch(Exception e){}

try	{
sql="select count(*)+' ' from "+m+" where mark < 0  and examno="+report[0][j]+" and subjectcode='"+report[3][j]+"'";
rs = stmt.executeQuery(sql); while(rs.next()){ report[14][j]=rs.getString(1);} rs.close();
}catch(Exception e){}

try	{
sql="select count(*)+' ' from "+m+" where mark >= 0 and mark< 25  and examno="+report[0][j]+" and subjectcode='"+report[3][j]+"'";
rs = stmt.executeQuery(sql); while(rs.next()){ report[15][j]=rs.getString(1);} rs.close();
}catch(Exception e){}

try	{
sql="select count(*)+' ' from "+m+" where mark >= 25 and mark< 40  and examno="+report[0][j]+" and subjectcode='"+report[3][j]+"'";
rs = stmt.executeQuery(sql); while(rs.next()){ report[16][j]=rs.getString(1);} rs.close();
}catch(Exception e){}

try	{
sql="select count(*)+' ' from "+m+" where mark >= 40 and mark< 50  and examno="+report[0][j]+" and subjectcode='"+report[3][j]+"'";
rs = stmt.executeQuery(sql); while(rs.next()){ report[17][j]=rs.getString(1);} rs.close();
}catch(Exception e){}

try	{
sql="select count(*)+' ' from "+m+" where mark >= 50 and mark< 60  and examno="+report[0][j]+" and subjectcode='"+report[3][j]+"'";
rs = stmt.executeQuery(sql); while(rs.next()){ report[18][j]=rs.getString(1);} rs.close();
}catch(Exception e){}

try	{
sql="select count(*)+' ' from "+m+" where mark >= 60 and mark< 70  and examno="+report[0][j]+" and subjectcode='"+report[3][j]+"'";
rs = stmt.executeQuery(sql); while(rs.next()){ report[19][j]=rs.getString(1);} rs.close();
}catch(Exception e){}

try	{
sql="select count(*)+' ' from "+m+" where mark >= 70 and mark< 90  and examno="+report[0][j]+" and subjectcode='"+report[3][j]+"'";
rs = stmt.executeQuery(sql); while(rs.next()){ report[20][j]=rs.getString(1);} rs.close();
}catch(Exception e){}

try	{
sql="select count(*)+' ' from "+m+" where mark >= 90 and mark< 100  and examno="+report[0][j]+" and subjectcode='"+report[3][j]+"'";
rs = stmt.executeQuery(sql); while(rs.next()){ report[21][j]=rs.getString(1);} rs.close();
}catch(Exception e){}

try	{
sql="select count(*)+' ' from "+m+" where mark = 100  and examno="+report[0][j]+" and subjectcode='"+report[3][j]+"'";
rs = stmt.executeQuery(sql); while(rs.next()){ report[22][j]=rs.getString(1);} rs.close();
}catch(Exception e){}


}
stmt.close();
error= "success"; 
}catch(Exception e){error=(e.toString()+count+i+sql);}
finally{connMgr.freeConnection("xavier",con);
return error; 
}
}

public String AttView(String ayear, int dno1, int sem1)
{
    int i=0;
	count=0;
	DBConnectionManager connMgr=null; Connection con =null;
	try
	{
                       connMgr = DBConnectionManager.getInstance(); 
                       con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;
sql="select st.rollno,st.name,ISNULL(mark, 0)as mark from (select stud.rollno,stud.name,year from "
+" stud,student where stud.rollno=student.rollno and stud.academicyear='"+ayear+"' and stud.department="+dno1+" and year=((("+sem1+"+1)/2)) and active=1) st"
+" LEFT JOIN (select rollno, mark from attendancemark where academicyear='"+ayear+"' and semester="+sem1+") att on st.rollno=att.rollno  order by st.rollno";


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
error= "success"; 
}catch(Exception e){error=(e.toString()+count+i+sql);}
finally{connMgr.freeConnection("xavier",con);
return error; 
}
}

public String AttInsert(String academicyear1, int sem1, int ccount)
{
    int j=0,i=0,rollno1=0,mark1=0;
	count=0;
	DBConnectionManager connMgr=null; Connection con =null;
	try
	{
                       connMgr = DBConnectionManager.getInstance(); 
                       con = connMgr.getConnection("xavier");
  					    Statement stmt;
					    ResultSet rs;
					    PreparedStatement ps;
for(int k=0;k<ccount;k++)
{
	rollno1=Integer.parseInt(report[0][k]);
	mark1=Integer.parseInt(report[1][k]);
                        sql="delete attendancemark where semester=? and academicyear=? and rollno=?";
						ps = con.prepareStatement(sql);
						ps.setInt(1,sem1);
						ps.setString(2,academicyear1);
						ps.setInt(3,rollno1);
						j=ps.executeUpdate();
					    ps.close();

                        sql="insert into attendancemark(rollno,academicyear,semester,mark) values(?,?,?,?)";
				        ps = con.prepareStatement(sql);
						ps.setInt(1,rollno1);
						ps.setString(2,academicyear1);
						ps.setInt(3,sem1);
						ps.setInt(4,mark1);
						j=ps.executeUpdate();
					    ps.close();
}
error= "success"; 
}catch(Exception e){error=(e.toString()+count+i+sql);}
finally{connMgr.freeConnection("xavier",con);
return error; 
}
}

public String IntMarksView(String ayear,String scode, int dno1, int sem1, int yr1, int booster)
{
    int i=0,min=0,m1=0,m2=0,m3=0,m4=0,best=booster;
	count=0;
	DBConnectionManager connMgr=null; Connection con =null;
	try
	{
    connMgr = DBConnectionManager.getInstance(); 
    con = connMgr.getConnection("xavier");
  	Statement stmt = con.createStatement();
	ResultSet rs;
sql="select st.rollno,ISNULL(ltrim(rtrim(rno.registerno)),0),ltrim(rtrim(st.name)),ISNULL(I1.mark,0),ISNULL(I2.mark,0),ISNULL(I3.mark,0),ISNULL(I4.mark,0),ISNULL(att.mark, 0)as amark "
+ " from (select stud.rollno,stud.name,year from stud,student where stud.rollno=student.rollno and stud.academicyear='"+ayear+"' and stud.department="+dno1+" and year="+yr1+" and active=1) st "
+ " LEFT outer JOIN (select rollno, mark from attendancemark where academicyear='"+ayear+"' and semester="+sem1+") att on st.rollno=att.rollno "
+ " left outer join (SELECT registerno.rollno,registerno FROM registerno) as rno on st.rollno = rno.rollno "
+ " left outer join (SELECT mark.rollno, mark.mark FROM mark, examyear WHERE examyear.examno=mark.examno and " 
+ " examyear.semester="+sem1+" and examyear.exam='Internal Exam I' and examyear.year='"+ayear+"' and mark.subjectcode='"+scode+"') as I1 on st.rollno = I1.rollno "
+ " left outer join (SELECT mark.rollno, mark.mark FROM mark, examyear WHERE examyear.examno=mark.examno and "
+ " examyear.semester="+sem1+" and examyear.exam='Internal Exam II' and examyear.year='"+ayear+"' and mark.subjectcode='"+scode+"') as I2 on st.rollno = I2.rollno "
+ " left outer join (SELECT mark.rollno, mark.mark FROM mark, examyear WHERE examyear.examno=mark.examno and "
+ " examyear.semester="+sem1+" and examyear.exam='Internal Exam III' and examyear.year='"+ayear+"' and mark.subjectcode='"+scode+"') as I3 on st.rollno = I3.rollno "
+ " left outer join (SELECT mark.rollno, mark.mark FROM mark, examyear WHERE examyear.examno=mark.examno and "
+ " examyear.semester="+sem1+" and examyear.exam='Model Exam I' and examyear.year='"+ayear+"' and mark.subjectcode='"+scode+"') as I4 on st.rollno = I4.rollno order by rno.registerno ";

 rs = stmt.executeQuery(sql);

while(rs.next())
{
best=0;
	for(i=1;i<=8;i++)
	{
		report[i-1][count]=rs.getString(i);
	}
	m1=Integer.parseInt(report[3][count]);
	m2=Integer.parseInt(report[4][count]);
	m3=Integer.parseInt(report[5][count]);
	m4=Integer.parseInt(report[6][count]);
	min=m1;
	if(min<=m2) best=best+m2; else {best=best+min; min=m2; }
	if(min<=m3) best=best+m3; else {best=best+min; min=m3; }
	if(min<=m4) best=best+m4; else {best=best+min; min=m4; }
	    best=best/4;
	    best=best+booster;
	    if(best>75) best=75;
		report[8][count]=best+"";
		report[9][count]=((Integer.parseInt(report[7][count])*5)+best)+"";
		report[10][count]=((int)(((Integer.parseInt(report[7][count])*5)+best)/5))+"";
	count++;
}
rs.close();
stmt.close();
error= "success"; 
}catch(Exception e){error=(e.toString()+count+i+sql);}
finally{connMgr.freeConnection("xavier",con);
return error; 
}
}

public String IntMarksViewNew(String ayear,String scode, int dno1, int sem1, int yr1, int booster)
{
    int i=0,min=0,m1=0,m2=0,m3=0,m4=0,m5=0,m6=0,m7=0,m8=0,m9=0,best=booster;
	count=0;
	DBConnectionManager connMgr=null; Connection con =null;
	try
	{
    connMgr = DBConnectionManager.getInstance(); 
    con = connMgr.getConnection("xavier");
  	Statement stmt = con.createStatement();
	ResultSet rs;
sql="select st.rollno,ISNULL(ltrim(rtrim(rno.registerno)),0),ltrim(rtrim(st.name)),ISNULL(I1.mark,0),ISNULL(I2.mark,0),ISNULL(I3.mark,0),ISNULL(I4.mark,0),"
+ " ISNULL(I5.mark,0),ISNULL(I6.mark,0),ISNULL(I7.mark,0),ISNULL(I8.mark,0),ISNULL(I9.mark,0),ISNULL(att.mark, 0)as amark "
+ " from (select stud.rollno,stud.name,year from stud,student where stud.rollno=student.rollno and stud.academicyear='"+ayear+"' and stud.department="+dno1+" and year="+yr1+" and active=1) st "
+ " LEFT outer JOIN (select rollno, mark from attendancemark where academicyear='"+ayear+"' and semester="+sem1+") att on st.rollno=att.rollno "
+ " left outer join (SELECT registerno.rollno,registerno FROM registerno) as rno on st.rollno = rno.rollno "
+ " left outer join (SELECT mark.rollno, mark.mark FROM mark, examyear WHERE examyear.examno=mark.examno and " 
+ " examyear.semester="+sem1+" and examyear.exam='Class Test 1' and examyear.year='"+ayear+"' and mark.subjectcode='"+scode+"') as I1 on st.rollno = I1.rollno "
+ " left outer join (SELECT mark.rollno, mark.mark FROM mark, examyear WHERE examyear.examno=mark.examno and " 
+ " examyear.semester="+sem1+" and examyear.exam='Class Test 2' and examyear.year='"+ayear+"' and mark.subjectcode='"+scode+"') as I2 on st.rollno = I2.rollno "
+ " left outer join (SELECT mark.rollno, mark.mark FROM mark, examyear WHERE examyear.examno=mark.examno and " 
+ " examyear.semester="+sem1+" and examyear.exam='Class Test 3' and examyear.year='"+ayear+"' and mark.subjectcode='"+scode+"') as I3 on st.rollno = I3.rollno "
+ " left outer join (SELECT mark.rollno, mark.mark FROM mark, examyear WHERE examyear.examno=mark.examno and " 
+ " examyear.semester="+sem1+" and examyear.exam='Class Test 4' and examyear.year='"+ayear+"' and mark.subjectcode='"+scode+"') as I4 on st.rollno = I4.rollno "
+ " left outer join (SELECT mark.rollno, mark.mark FROM mark, examyear WHERE examyear.examno=mark.examno and " 
+ " examyear.semester="+sem1+" and examyear.exam='Class Test 5' and examyear.year='"+ayear+"' and mark.subjectcode='"+scode+"') as I5 on st.rollno = I5.rollno "
+ " left outer join (SELECT mark.rollno, mark.mark FROM mark, examyear WHERE examyear.examno=mark.examno and " 
+ " examyear.semester="+sem1+" and examyear.exam='Internal Exam I' and examyear.year='"+ayear+"' and mark.subjectcode='"+scode+"') as I6 on st.rollno = I6.rollno "
+ " left outer join (SELECT mark.rollno, mark.mark FROM mark, examyear WHERE examyear.examno=mark.examno and "
+ " examyear.semester="+sem1+" and examyear.exam='Internal Exam II' and examyear.year='"+ayear+"' and mark.subjectcode='"+scode+"') as I7 on st.rollno = I7.rollno "
+ " left outer join (SELECT mark.rollno, mark.mark FROM mark, examyear WHERE examyear.examno=mark.examno and "
+ " examyear.semester="+sem1+" and examyear.exam='Internal Exam III' and examyear.year='"+ayear+"' and mark.subjectcode='"+scode+"') as I8 on st.rollno = I8.rollno "
+ " left outer join (SELECT mark.rollno, mark.mark FROM mark, examyear WHERE examyear.examno=mark.examno and "
+ " examyear.semester="+sem1+" and examyear.exam='Model Exam' and examyear.year='"+ayear+"' and mark.subjectcode='"+scode+"') as I9 on st.rollno = I9.rollno order by rno.registerno ";

 rs = stmt.executeQuery(sql);

while(rs.next())
{
best=0;
	for(i=1;i<=13;i++)
	{
		report[i-1][count]=rs.getString(i);
	}
	m1=Integer.parseInt(report[3][count]);
	m2=Integer.parseInt(report[4][count]);
	m3=Integer.parseInt(report[5][count]);
	m4=Integer.parseInt(report[6][count]);
	m4=Integer.parseInt(report[7][count]);
	m4=Integer.parseInt(report[8][count]);
	m4=Integer.parseInt(report[9][count]);
	m4=Integer.parseInt(report[10][count]);
	m4=Integer.parseInt(report[11][count]);
	min=m1;
	if(min<=m2) best=best+m2; else {best=best+min; min=m2; }
	if(min<=m3) best=best+m3; else {best=best+min; min=m3; }
	if(min<=m4) best=best+m4; else {best=best+min; min=m4; }
	if(min<=m5) best=best+m5; else {best=best+min; min=m5; }
	if(min<=m6) best=best+m6; else {best=best+min; min=m6; }
	if(min<=m7) best=best+m7; else {best=best+min; min=m7; }
	if(min<=m8) best=best+m8; else {best=best+min; min=m8; }
	if(min<=m9) best=best+m9; else {best=best+min; min=m9; }
	    best=best/9;
	    best=best+booster;
	    if(best>75) best=75;
		report[18][count]=best+"";
		report[19][count]=((Integer.parseInt(report[7][count])*5)+best)+"";
		report[20][count]=((int)(((Integer.parseInt(report[7][count])*5)+best)/5))+"";
	count++;
}
rs.close();
stmt.close();
error= "success"; 
}catch(Exception e){error=(e.toString()+count+i+sql);}
finally{connMgr.freeConnection("xavier",con);
return error; 
}
}


public String Int100MarksView(String ayear,String scode, int dno1, int sem1, int yr1, int booster,String iexam)
{
    int i=0,m1=0;
	count=0;
	DBConnectionManager connMgr=null; Connection con =null;
	try
	{
    connMgr = DBConnectionManager.getInstance(); 
    con = connMgr.getConnection("xavier");
  	Statement stmt = con.createStatement();
	ResultSet rs;
sql="select st.rollno,ISNULL(ltrim(rtrim(rno.registerno)),0),ltrim(rtrim(st.name)),ISNULL(I1.mark,0) "
+ " from (select stud.rollno,stud.name,year from stud,student where stud.rollno=student.rollno and stud.academicyear='"+ayear+"' and stud.department="+dno1+" and year="+yr1+" and active=1) st "
+ " left outer join (SELECT registerno.rollno,registerno FROM registerno) as rno on st.rollno = rno.rollno "
+ " left outer join (SELECT mark.rollno, mark.mark FROM mark, examyear WHERE examyear.examno=mark.examno and " 
+ " examyear.semester="+sem1+" and examyear.exam='"+iexam+"' and examyear.year='"+ayear+"' and mark.subjectcode='"+scode+"') as I1 on st.rollno = I1.rollno order by rno.registerno ";

 rs = stmt.executeQuery(sql);

while(rs.next())
{
	for(i=1;i<=4;i++)
	{
		report[i-1][count]=rs.getString(i);
	}
	m1=Integer.parseInt(report[3][count]);
    m1=m1+booster;
	if(m1>100) m1=100;
		report[10][count]=m1+"";
	count++;
}
rs.close();
stmt.close();
error= "success"; 
}catch(Exception e){error=(e.toString()+count+i+sql);}
finally{connMgr.freeConnection("xavier",con);
return error; 
}
}

public String MarksView(String ayear,String scode, int dno1, int sem1, int yr1,String exam)
{
    int i=0;
	count=0;
	DBConnectionManager connMgr=null; Connection con =null;
	try
	{
    connMgr = DBConnectionManager.getInstance(); 
    con = connMgr.getConnection("xavier");
  	Statement stmt = con.createStatement();
	ResultSet rs;
sql="select st.rollno,ISNULL(ltrim(rtrim(rno.registerno)),0),ltrim(rtrim(st.name)),ISNULL(I1.mark,0) "
+ " from (select stud.rollno,stud.name,year from stud,student where stud.rollno=student.rollno and stud.academicyear='"+ayear+"' and stud.department="+dno1+" and year="+yr1+" and active=1) st "
+ " left outer join (SELECT registerno.rollno,registerno FROM registerno) as rno on st.rollno = rno.rollno "
+ " left outer join (SELECT mark.rollno, mark.mark FROM mark, examyear WHERE examyear.examno=mark.examno and " 
+ " examyear.semester="+sem1+" and examyear.exam='"+exam+"' and examyear.year='"+ayear+"' and mark.subjectcode='"+scode+"') as I1 on st.rollno = I1.rollno "
+ " order by st.rollno ";

rs = stmt.executeQuery(sql);

while(rs.next())
{
	for(i=1;i<=4;i++)
	{
		report[i-1][count]=rs.getString(i);
	}
	count++;
}
rs.close();


sql="SELECT substring(('000'+ltrim(rtrim(str(day(date))))),len('000'+ltrim(rtrim(str(day(date)))))-1,2)+'/'+substring(('000'+ltrim(rtrim(str(month(date))))),len('000'+ltrim(rtrim(str(month(date)))))-1,2)+'/'+ltrim(rtrim(str(year(date)))),marklogin.staffid,name "
+" FROM staff,marklogin, examyear WHERE examyear.examno=marklogin.examno "
+" and staff.staffid=marklogin.staffid and examyear.semester="+sem1+" and examyear.exam='"+exam+"' and marklogin.subjectcode='"+scode+"' "
+"  and examyear.year='"+ayear+"' and examyear.dno="+dno1+" ORDER BY date";

rs = stmt.executeQuery(sql);

count1=0;

while(rs.next())
{
	for(i=1;i<=3;i++)
	{
		report1[i-1][count1]=rs.getString(i);
	}
	count1++;
}
rs.close();

 	
stmt.close();
error= "success"; 
}catch(Exception e){error=(e.toString()+count+i+sql);}
finally{connMgr.freeConnection("xavier",con);
return error; 
}
}



	public String[] getSubname()
	   {
	   	return this.subname;
	   }

	public String[] getAbbr()
	   {
	   	return this.abbr;
	   }
	public String[] getCredit()
	   {
	   	return this.credit;
	   }
	public String[] getSubcode()
	   {
	   	return this.subcode;
	   }
	public String[] getHandledby()
	   {
	   	return this.handledby;
	   }

	public String[] getmaxmarkname()
	   {
	   	return this.maxmarkname;
	   }
	public int[] getmaxstud()
	   {
	   	return this.maxstud;
	   }
	public int[] getmaxpassstud()
	   {
	   	return this.maxpassstud;
	   }
	public int[] getmaxmark()
	   {
	   	return this.maxmark;
	   }
	public int getCount()
	   {
	   	return this.count;
	   }
	public int getCount1()
	   {
	   	return this.count1;
	   }

	public int[] getsemester()
	   {
	   	return this.semester;
	   }
	public int[] getdno()
	   {
	   	return this.dno;
	   }
	public int[] getyear()
	   {
	   	return this.year;
	   }
	 public void setAcademicyear(String academicyear)
	   {
	   	this.academicyear=academicyear;
	   }
	 public int getId()
	    {
	    	return this.id;
	    }
	 public int getallexamstud()
	    {
	    	return this.allexamstud;
	    }
	 public int getallexampassed()
	    {
	    	return this.allexampassed;
	    }
	 public int getrnocount()
	    {
	    	return rnocount;
	    }
	    
	public int[] gettheoryorpractical()
	   {
	   	return this.theoryorpractical;
	   }

	public int[] getmaximummark()
	   {
	   	return this.maximummark;
	   }


	public int[] getpassmark()
	   {
	   	return this.passmark;
	   }
   public void setreport(String[][] report) {this.report=report;}  public String[][] getreport() {	return this.report;}
   public void setreport1(String[][] report1) {this.report1=report1;}  public String[][] getreport1() {	return this.report1;}
}
