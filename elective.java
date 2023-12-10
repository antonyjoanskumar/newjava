package sxcce;
import java.sql.*;
import java.util.StringTokenizer;
import com.jspsmart.upload.*;
import java.util.Date;
import java.io.*;
import dd.DBConnectionManager;

public class elective
	{
				String rep[][] = new String[20][100];
				String report[][] = new String[20][100];
				String report1[] = new String[100];
				String report2[] = new String[100];
				String subcode[] = new String[100];
				String subname[] = new String[100];
				
String str1="";
int count;
int tid;
String sql="";	
String error="";			
String error1="";	
String academicyear="";		

public String ElectiveView(int dno, int semester, String academicyear1)
{
int i=0;
	count=0;
int scount=0;
int yr=(semester+1)/2;
try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;
sql="select subjectcode,subjectname from syllabus where id in(select id from subjectidentify where semester="+semester+" and dno="+dno+" and academicyear='"+academicyear1+"') and theoryorpractical=4";
rs = stmt.executeQuery(sql);

while(rs.next())
{
	subcode[scount]=rs.getString(1);
	subname[scount]=rs.getString(2);
	scount++;
}
rs.close();

sql="select p.rollno,name,department,year,academicyear,isnull(registerno,0) from (select stud.rollno,stud.name,department,year,academicyear from stud,student "
+" where stud.rollno=student.rollno and academicyear='"+academicyear1+"' and year="+yr+" and departmentno="+dno+" and active=1 ) p "
+" left outer join (select rollno,registerno from registerno) q on p.rollno=q.rollno order by p.rollno";

//sql="select stud.rollno,stud.name,department,year,academicyear,registerno from stud,student,registerno where stud.rollno=registerno.rollno and stud.rollno=student.rollno and academicyear='"+academicyear1+"' and year="+yr+" and departmentno="+dno+" and active=1 order by stud.rollno";
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

for(i=0;i<count;i++)
{
report[6][i]="";
report[7][i]="";

for(int j=0;j<scount;j++)
{

	sql="select id,rollno,subcode from subjectelective where id in(select id from subjectidentify where semester="+semester+" and dno="+dno+" and academicyear='"+academicyear1+"') and rollno="+report[0][i]+" and subcode='"+subcode[j]+"'";
rs = stmt.executeQuery(sql);

if(rs.next())
{
report[6][i]=report[6][i]+"<option value="+ subcode[j]+" selected>"+subname[j]+"</option>";
report[7][i]=report[7][i]+" "+ subcode[j]+"-"+subname[j];
}
else
report[6][i]=report[6][i]+"<option value="+ subcode[j]+">"+subname[j]+"</option>";
rs.close();

}		
}


stmt.close();
connMgr.freeConnection("xavier",con);
error= "success"; 
}catch(Exception e){error=(e.toString()+count+i+sql);}
return error; 
}



public String ElectiveInsert(int rollno, int dno,int semester, String academicyear1,String subcode)
{
String sql="";
try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  			Statement stmt = con.createStatement();
			ResultSet rs;
int cid=0;
			PreparedStatement ps;

sql="select id from subjectidentify where semester="+semester+" and dno="+dno+" and academicyear='"+academicyear1+"'";
rs = stmt.executeQuery(sql);
if(rs.next())
 cid=rs.getInt(1);
rs.close();
					
/*
 *                    sql="delete from subjectelective where rollno="+rollno+" and id="+cid;
					ps=con.prepareStatement(sql);
					int k = ps.executeUpdate();
					ps.close();
*/					
                    sql="insert into subjectelective values(?,?,?)";
					ps=con.prepareStatement(sql);
					ps.setInt(1,cid);
					ps.setInt(2,rollno);
					ps.setString(3,subcode);
					int j = ps.executeUpdate();
					ps.close();
     
                    stmt.close();
					connMgr.freeConnection("xavier",con);
					error1="...";
					}catch(Exception e){error1=(e.toString());}

return error1;	

}


public String ElectiveDelete(int rollno, int dno,int semester, String academicyear1)
{
String sql="";
try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  			Statement stmt = con.createStatement();
			ResultSet rs;
int cid=0;
			PreparedStatement ps;

sql="select id from subjectidentify where semester="+semester+" and dno="+dno+" and academicyear='"+academicyear1+"'";
rs = stmt.executeQuery(sql);
if(rs.next())
 cid=rs.getInt(1);
rs.close();
					
                    sql="delete from subjectelective where rollno="+rollno+" and id="+cid;
					ps=con.prepareStatement(sql);
					int k = ps.executeUpdate();
					ps.close();
				
                    stmt.close();
					connMgr.freeConnection("xavier",con);
					error1="...";
					}catch(Exception e){error1=(e.toString());}

return error1;	

}

/*
public String ElectiveUpdate(int rollno, int uid, String staffid, String academicyear1)
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
					
                    sql="delete from tutor where rollno="+rollno+" and tid="+uid;
					ps=con.prepareStatement(sql);
					int k = ps.executeUpdate();
					ps.close();
					
                    sql="insert into tutor values(?,?,?,?)";
					ps=con.prepareStatement(sql);
					ps.setInt(1,rollno);
					ps.setInt(2,uid);
					ps.setString(3,staffid);
					ps.setString(4,academicyear1);
					int j = ps.executeUpdate();
					ps.close();
     
                    stmt.close();
					connMgr.freeConnection("xavier",con);
					error1="...";
					}catch(Exception e){error1=(e.toString());}

return error1;	

}

public String MenteesView(String staffid)
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
sql="select tutor.rollno,name from tutor,student where tutor.rollno=student.rollno and staffid='"+staffid+"' and academicyear in (select academicyear from academicyear) order by tutor.rollno";

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
error= "success"; 
}catch(Exception e){error=(e.toString()+count+i+sql);}
return error; 
}
*/


public void setrep(String[][] rep) {this.rep=rep;}  public String[][] getrep() {	return this.rep;}
public void setreport(String[][] report) {this.report=report;}  public String[][] getreport() {	return this.report;}
public void setreport1(String[] report1) {this.report1=report1;}  public String[] getreport1() {	return this.report1;}
public void setreport2(String[] report2) {this.report2=report2;}  public String[] getreport2() {	return this.report2;}
public void setcount(int count) {this.count=count;} public int getcount(){	return this.count;}


}




	
	