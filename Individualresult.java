package sxcce;
import java.sql.*;
import java.util.Date;
public class Individualresult
	{
	int semester=0;
	int year=0;
	int arrno=0;
    String arr="";
	int unyear=0;
	int month=0;
	int c1=0;
	int dno;
	String name="";
	String quer="";
	String registerno="";
	String exam[] = new String[500];
	String subjectcode[] = new String[500];
	String subjectname[] = new String[500];
	String report[][] = new String[30][10000];

	String academicyear="";
	String rollno="";
	int internalmark[] = new int[500];
	int externalmark[] = new int[500];
	
	int sem[] = new int[500];
	int mark[] = new int[500];
	int hours[] = new int[500];
	int thours[] = new int[500];
	int roll[] = new int[500];
	float per[] = new float[500];
	int nofarr[] = new int[500];
	int mat[] = new int[500];
	int phy[] = new int[500];
	int che[] = new int[500];
	int vsem[] = new int[500];
	int visem[] = new int[500];
	int voc1[] = new int[500];
	int voc2[] = new int[500];
	String studname[] = new String[500];
	int count=0;
	int tot=0;
	int subcount=0;
	int nonarrear=0;
	public String regnoselect()
	{
				try
					{
					Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
					Connection con = DriverManager.getConnection("jdbc:odbc:xavier","sxcce","tamil5india6");
					Statement stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery("select min(registerno) from registerno where registerno>'"+registerno+"'");
					if(rs.next())
						{
							registerno=rs.getString(1);
						}
					rs.close();
					stmt.close();
					con.close();
					}catch(Exception e){}
				return registerno;
		
	}
		public String subjectselect()
		   {
			quer="select rollno from registerno where registerno='"+registerno+"'";				
			try
					{
					Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
					Connection con = DriverManager.getConnection("jdbc:odbc:xavier","sxcce","tamil5india6");
					Statement stmt = con.createStatement();
					ResultSet rs;
		   	if(!(month==1 || month==2))	
		   	   {
				quer="select * from resultidentify";				
					rs = stmt.executeQuery(quer);
					if(rs.next())
						{
							year=rs.getInt(1);
							month=rs.getInt(2);
						}
					rs.close();
		   	   }
		   	unyear=year;
		    	if(month==1)academicyear=(year-1)+" -- "+year;
		    	else if(month==2)academicyear=year+" -- "+(year+1);
				quer="select rollno from registerno where registerno='"+registerno+"'";				
					rs = stmt.executeQuery(quer);
					if(rs.next())
						{
							rollno=rs.getString(1);
						}
					rs.close();
					try{
rs = stmt.executeQuery("select registerno from registerno where rollno="+registerno);
   if(rs.next())
      {
      	rollno=registerno;
      	registerno=rs.getString(1);
      }
rs.close();
}catch(Exception e){}
					
quer="select name,department,year from stud where rollno="+rollno+" and academicyear='"+academicyear+"'";
rs = stmt.executeQuery(quer);
   if(rs.next())
      {
      name=rs.getString(1);
      dno=rs.getInt(2);
      year=rs.getInt(3);
      }
rs.close();
if(year==1 && month==1)semester=2;
else if(year==1 && month==2)semester=1;
else if(year==2 && month==1)semester=4;
else if(year==2 && month==2)semester=3;
else if(year==3 && month==1)semester=6;
else if(year==3 && month==2)semester=5;
else if(year==4 && month==1)semester=8;
else if(year==4 && month==2)semester=7;
String years="";
if(month==1)years="universitymarks.year<"+unyear;
else years="universitymarks.year<"+unyear+" or universitymarks.year="+unyear;
//+" and month=1";
quer="select maximummark,passmark,max(subjectname),syllabus.subjectcode, "+
"max(internalmark),max(externalmark),semester from "+
"subjectidentify,syllabus,stud,universitymarks "+
"where subjectidentify.id=syllabus.id and "+
"stud.rollno=universitymarks.rollno   and theoryorpractical<2 and semester<"+semester+
//"stud.academicyear=subjectidentify.academicyear and "+
" and universitymarks.subjectcode=syllabus.subjectcode "+
"and stud.rollno="+rollno+" and subjectidentify.dno=stud.department "+
//"and (stud.academicyear=rtrim(ltrim(str(universitymarks.year))"+
//")+' -- '+rtrim(ltrim(str(universitymarks.year+1))) "+
//"and universitymarks.month=2 or "+
//"stud.academicyear=rtrim(ltrim(str(universitymarks.year-1) "+
//"))+' -- '+rtrim(ltrim(str(universitymarks.year))) "+
//" and universitymarks.month=1) and "+
//"("+years+") "+
//"and subjectidentify.academicyear in (select academicyear from stud where rollno="+rollno+") "+
"group by syllabus.subjectcode,syllabus.maximummark, "+
"syllabus.passmark,semester "+
//"having max(externalmark)<passmark";
"having max(externalmark)<36 order by semester";
					rs = stmt.executeQuery(quer);
					while(rs.next())
						{
							subjectname[subcount]=rs.getString(3);
							subjectcode[subcount]=rs.getString(4);
							sem[subcount]=rs.getInt(7);
							subcount++;
						}
					rs.close();
						nonarrear=subcount;
					
					String quer1 ="select subjectcode,subjectname from syllabus where "+
					"id=(select id from subjectidentify where semester="+semester+" "+
					"and dno="+dno+" and academicyear='"+academicyear+"')";
					rs = stmt.executeQuery(quer1);
					while(rs.next())
						{
							subjectcode[subcount]=rs.getString(1);
							subjectname[subcount]=rs.getString(2);
							sem[subcount]=semester;
						
							subcount++;
						}
						
					rs.close();
					stmt.close();
					con.close();
					}catch(Exception e){return e.toString();}
return quer+"deepan";

		}
		
/*
		public String subjectallselect()
		   {
			quer="select rollno from registerno where registerno='"+registerno+"'";				
			try
					{
					Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
					Connection con = DriverManager.getConnection("jdbc:odbc:xavier","sxcce","tamil5india6");
					Statement stmt = con.createStatement();
					ResultSet rs;
				quer="select rollno from registerno where registerno='"+registerno+"'";				
					rs = stmt.executeQuery(quer);
					if(rs.next())
						{
							rollno=rs.getString(1);
						}
					rs.close();
					try{
rs = stmt.executeQuery("select registerno from registerno where rollno="+registerno);
   if(rs.next())
      {
      	rollno=registerno;
      	registerno=rs.getString(1);
      }
rs.close();
}catch(Exception e){}
					
quer="select name,department,year from stud where rollno="+rollno+" and academicyear in(select academicyear from academicyear)";
rs = stmt.executeQuery(quer);
   if(rs.next())
      {
      name=rs.getString(1);
      dno=rs.getInt(2);
      year=rs.getInt(3);
      }
rs.close();


quer="select maximummark,passmark,max(subjectname),syllabus.subjectcode, "+
"max(internalmark),max(externalmark),semester from  "+
"subjectidentify,syllabus,stud,universitymarks  "+
"where subjectidentify.id=syllabus.id and "+
"stud.rollno=universitymarks.rollno   and theoryorpractical<2 "+
"and universitymarks.subjectcode=syllabus.subjectcode  "+
"and stud.rollno="+rollno+" and subjectidentify.dno=stud.department  "+
"group by semester,syllabus.subjectcode,syllabus.maximummark,  "+
"syllabus.passmark order by subjectidentify.semester";
subcount=0;
					rs = stmt.executeQuery(quer);
					arrno=0;
					arr="";
					while(rs.next())
						{
							subjectname[subcount]=rs.getString(3);
							subjectcode[subcount]=rs.getString(4);
							internalmark[subcount]=rs.getInt(5);
							externalmark[subcount]=rs.getInt(6);
							sem[subcount]=rs.getInt(7);
							if(externalmark[subcount]<36)
							{
							    if(internalmark[subcount]<36)
							    {
								  arrno++;
								  arr=arr+sem[subcount]+":"+subjectcode[subcount]+", ";
								}
							}
							subcount++;
						}
					rs.close();
					stmt.close();
					con.close();
					}catch(Exception e){return e.toString();}
return quer+"deepan";

}
*/		

public String umarkscurrent(int cmonth,int cyear)
{
int i=0;
count=0;
String error="...";
			quer="select rollno from registerno where registerno='"+registerno+"'";				
			try
					{
					Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
					Connection con = DriverManager.getConnection("jdbc:odbc:xavier","sxcce","tamil5india6");
					Statement stmt = con.createStatement();
					ResultSet rs;
					try{
rs = stmt.executeQuery("select registerno from registerno where rollno="+rollno);
   if(rs.next())
      {
//      	rollno=registerno;
      	registerno=rs.getString(1);
      }
rs.close();
}catch(Exception e){}
					
quer="select name,departmentno from student where rollno="+rollno;
rs = stmt.executeQuery(quer);
   if(rs.next())
      {
      name=rs.getString(1);
      dno=rs.getInt(2);
//      year=rs.getInt(3);
      }
rs.close();

	

quer="select max(umarks.id),max(semester),max(subcode),max(subjectname),max(credit),max(grade),max(syllabus.theoryorpractical) tp,max(umarks.year),max(month),max(umarks.id) "
+" from subjectidentify,syllabus,stud,umarks,registerno  "
+" where registerno.registerno=umarks.regno and umarks.subcode=syllabus.subjectcode and subjectidentify.id=syllabus.id "
+" and stud.rollno="+rollno+" and subjectidentify.dno=stud.department and registerno.rollno=stud.rollno "
+" and umarks.month="+cmonth+" and umarks.year="+cyear+" and subjectidentify.academicyear=stud.academicyear "
+" group by semester,subcode,grade,umarks.year,month,umarks.id "
+" order by semester,tp desc,subcode,umarks.year,month";

int hyr=0;
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
  else if(report[5][count].equals("-"))
        report[10][count]="1";
//  else if(report[5][count].equals("UA"))
//        report[10][count]="-1";
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
//  else if(report[5][count].equals("UA"))
//        report[10][count]="-1";
  else 
        report[10][count]="0";
}       

// for one student
if(rollno.equals("501633"))
{
if(Integer.parseInt(report[1][count])>3)
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
//  else if(report[5][count].equals("UA"))
//        report[10][count]="-1";
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
//  else if(report[5][count].equals("UA"))
//        report[10][count]="-1";
  else 
        report[10][count]="0";
}       
}



if (Integer.parseInt(report[10][count])==1)
        report[11][count]="0";
else        
//        report[11][count]=(Integer.parseInt(report[10][count])*Integer.parseInt(report[4][count].substring(0,1)))+"";
        report[11][count]=(Float.parseFloat(report[10][count])*Float.parseFloat(report[4][count]))+"";

  if(report[10][count].equals("0"))
        externalmark[count]=0;
  else
        externalmark[count]=50;


}catch(Exception e){}

	
	count++;
	
}
rs.close();

stmt.close();
					con.close();
//error= "success"+quer+count; 
}catch(Exception e){error=(e.toString()+count+i+quer);}
return error; 
}
	
		
		
public String subjectallselect()
{
int i=0;
count=0;
String error="...";
			quer="select rollno from registerno where registerno='"+registerno+"'";				
			try
					{
					Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
					Connection con = DriverManager.getConnection("jdbc:odbc:xavier","sxcce","tamil5india6");
					Statement stmt = con.createStatement();
					ResultSet rs;
/*					
				quer="select rollno from registerno where registerno='"+registerno+"'";				
					rs = stmt.executeQuery(quer);
					if(rs.next())
						{
							rollno=rs.getString(1);
						}
					rs.close();
*/					
					try{
rs = stmt.executeQuery("select registerno from registerno where rollno="+rollno);
   if(rs.next())
      {
//      	rollno=registerno;
      	registerno=rs.getString(1);
      }
rs.close();
}catch(Exception e){}
					
quer="select name,departmentno from student where rollno="+rollno;
rs = stmt.executeQuery(quer);
   if(rs.next())
      {
      name=rs.getString(1);
      dno=rs.getInt(2);
//      year=rs.getInt(3);
      }
rs.close();

	

quer="select max(umarks.id),max(semester),max(subcode),max(subjectname),max(credit),max(grade),max(syllabus.theoryorpractical) tp,max(umarks.year),max(month),max(umarks.id) "
+" from subjectidentify,syllabus,stud,umarks,registerno  "
+" where registerno.registerno=umarks.regno and umarks.subcode=syllabus.subjectcode and subjectidentify.id=syllabus.id "
+" and stud.rollno="+rollno+" and subjectidentify.dno=stud.department and registerno.rollno=stud.rollno "
+" and subjectidentify.academicyear=stud.academicyear "
+" group by semester,subcode,grade,umarks.year,month,umarks.id "
+" order by semester,tp desc,subcode,umarks.year,month";

int hyr=0;
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
  else if(report[5][count].equals("-"))
        report[10][count]="1";
//  else if(report[5][count].equals("UA"))
//        report[10][count]="-1";
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
//  else if(report[5][count].equals("UA"))
//        report[10][count]="-1";
  else 
        report[10][count]="0";
}       

// for one student
if(rollno.equals("501633"))
{
if(Integer.parseInt(report[1][count])>3)
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
//  else if(report[5][count].equals("UA"))
//        report[10][count]="-1";
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
//  else if(report[5][count].equals("UA"))
//        report[10][count]="-1";
  else 
        report[10][count]="0";
}       
}



if (Integer.parseInt(report[10][count])==1)
        report[11][count]="0";
else        
//        report[11][count]=(Integer.parseInt(report[10][count])*Integer.parseInt(report[4][count].substring(0,1)))+"";

        report[11][count]=(Float.parseFloat(report[10][count])*Float.parseFloat(report[4][count]))+"";


  if(report[10][count].equals("0"))
        externalmark[count]=0;
  else
        externalmark[count]=50;


}catch(Exception e){}

	
	count++;
	
}
rs.close();

stmt.close();
					con.close();
//error= "success"+quer+count; 
}catch(Exception e){error=(e.toString()+count+i+quer);}
return error; 
}
		

public String percent()
{
	count=0;
	int i=0;
	int ajkr=0;
	int ajkm=0;
	int prj=0;
	int ajkint=0;
	int ajkext=0;
			try
					{
			quer="select stud. rollno,stud.name from stud,student where stud.rollno=student.rollno and active=1 and department="+dno+" and year="+year+" and academicyear='"+academicyear+"' order by stud.rollno";
					Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
					Connection con = DriverManager.getConnection("jdbc:odbc:xavier","sxcce","tamil5india6");
					Statement stmt = con.createStatement();
					ResultSet rs;
					
					rs = stmt.executeQuery(quer);
					while(rs.next())
						{
							roll[count]=rs.getInt(1);
							studname[count]=rs.getString(2);
							count++;

						}
					rs.close();
			quer="select student.rollno,name,subject,marks,maxmark from student, qualification where student.rollno=qualification.rollno and student.active=1 and student.rollno in(select rollno from stud where department="+dno+" and year="+year+" and academicyear='"+academicyear+"') and subject='Mathematics' order by (student.rollno)";
					rs = stmt.executeQuery(quer);
					while(rs.next())
						{
							 ajkr=rs.getInt(1);	
							 ajkm=rs.getInt(4);	
						for( i=0;i<count;i++)
						    if(roll[i]==ajkr) mat[i]=ajkm;
						}	rs.close();
			quer="select student.rollno,name,subject,marks,maxmark from student, qualification where student.rollno=qualification.rollno and student.active=1 and student.rollno in(select rollno from stud where department="+dno+" and year="+year+" and academicyear='"+academicyear+"') and subject='Physics' order by (student.rollno)";
					rs = stmt.executeQuery(quer);
					while(rs.next())
						{
							 ajkr=rs.getInt(1);	
							 ajkm=rs.getInt(4);	
						for( i=0;i<count;i++)
						    if(roll[i]==ajkr) phy[i]=ajkm;
						}	rs.close();
			quer="select student.rollno,name,subject,marks,maxmark from student, qualification where student.rollno=qualification.rollno and student.active=1 and student.rollno in(select rollno from stud where department="+dno+" and year="+year+" and academicyear='"+academicyear+"') and subject='Chemistry' order by (student.rollno)";
					rs = stmt.executeQuery(quer);
					while(rs.next())
						{
							 ajkr=rs.getInt(1);	
							 ajkm=rs.getInt(4);	
						for( i=0;i<count;i++)
						    if(roll[i]==ajkr) che[i]=ajkm;
						}	rs.close();
					
			quer="select student.rollno,name,subject,marks,maxmark from student, qualification where student.rollno=qualification.rollno and student.active=1 and student.rollno in(select rollno from stud where department="+dno+" and year="+year+" and academicyear='"+academicyear+"') and subject='V  Sem' order by (student.rollno)";
					rs = stmt.executeQuery(quer);
					while(rs.next())
						{
							 ajkr=rs.getInt(1);	
							 ajkm=rs.getInt(4);	
						for( i=0;i<count;i++)
						    if(roll[i]==ajkr) vsem[i]=ajkm;
						}	rs.close();
			quer="select student.rollno,name,subject,marks,maxmark from student, qualification where student.rollno=qualification.rollno and student.active=1 and student.rollno in(select rollno from stud where department="+dno+" and year="+year+" and academicyear='"+academicyear+"') and subject='VI Sem' order by (student.rollno)";
					rs = stmt.executeQuery(quer);
					while(rs.next())
						{
							 ajkr=rs.getInt(1);	
							 ajkm=rs.getInt(4);	
						for( i=0;i<count;i++)
						    if(roll[i]==ajkr) visem[i]=ajkm;
						}	rs.close();
			quer="select student.rollno,name,subject,marks,maxmark from student, qualification where student.rollno=qualification.rollno and student.active=1 and student.rollno in(select rollno from stud where department="+dno+" and year="+year+" and academicyear='"+academicyear+"') and subject='Practical - I' order by (student.rollno)";
					rs = stmt.executeQuery(quer);
					while(rs.next())
						{
							 ajkr=rs.getInt(1);	
							 ajkm=rs.getInt(4);	
						for( i=0;i<count;i++)
						    if(roll[i]==ajkr) voc1[i]=ajkm;
						}	rs.close();
			quer="select student.rollno,name,subject,marks,maxmark from student, qualification where student.rollno=qualification.rollno and student.active=1 and student.rollno in(select rollno from stud where department="+dno+" and year="+year+" and academicyear='"+academicyear+"') and subject='Practical - II' order by (student.rollno)";
					rs = stmt.executeQuery(quer);
					while(rs.next())
						{
							 ajkr=rs.getInt(1);	
							 ajkm=rs.getInt(4);	
						for( i=0;i<count;i++)
						    if(roll[i]==ajkr) voc2[i]=ajkm;
						}	rs.close();

				}catch(Exception e){studname[count++]="Error:"+e.toString()+i;}
					
for( i=0;i<count;i++)
{
if(c1<1)
{
quer="select maximummark,passmark,max(subjectname),syllabus.subjectcode, "+
"max(internalmark),max(externalmark),semester from  "+
"subjectidentify,syllabus,stud,universitymarks  "+
"where subjectidentify.id=syllabus.id and "+
"stud.rollno=universitymarks.rollno   and theoryorpractical<2 "+
"and universitymarks.subjectcode=syllabus.subjectcode  "+
"and stud.rollno="+roll[i]+" and subjectidentify.dno=stud.department and semester>2 "+
"group by semester,syllabus.subjectcode,syllabus.maximummark,  "+
"syllabus.passmark order by subjectidentify.semester";
}
else
{
quer="select maximummark,passmark,max(subjectname),syllabus.subjectcode, "+
"max(internalmark),max(externalmark),semester from  "+
"subjectidentify,syllabus,stud,universitymarks  "+
"where subjectidentify.id=syllabus.id and "+
"stud.rollno=universitymarks.rollno   and theoryorpractical<2 "+
"and universitymarks.subjectcode=syllabus.subjectcode  "+
"and stud.rollno="+roll[i]+" and subjectidentify.dno=stud.department "+
"group by semester,syllabus.subjectcode,syllabus.maximummark,  "+
"syllabus.passmark order by subjectidentify.semester";
}
prj=0;
try{
					Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
					Connection con = DriverManager.getConnection("jdbc:odbc:xavier","sxcce","tamil5india6");
					Statement stmt = con.createStatement();
					ResultSet rs;
					
					rs = stmt.executeQuery(quer);
					arrno=0;
					arr="";
                    subcount=0;
                    tot=0;
					while(rs.next())
						{
//							subjectname[subcount]=rs.getString(3);
//							subjectcode[subcount]=rs.getString(4);
							internalmark[subcount]=rs.getInt(5);
							externalmark[subcount]=rs.getInt(6);
if (externalmark[subcount]>100) prj++;

//							sem[subcount]=rs.getInt(7);
							tot=tot+internalmark[subcount]+externalmark[subcount];
							if((externalmark[subcount]<36) && (internalmark[subcount]<=20))
							{
								arrno++;
//								arr=arr+subjectcode[subcount]+":"+internalmark[subcount]+externalmark[subcount]+" - ";
//								arr=arr+sem[subcount]+":"+subjectcode[subcount]+", ";
							}
							subcount++;
						}
					rs.close();
quer="select maximummark,passmark,max(subjectname),syllabus.subjectcode, "+
"max(internalmark),max(externalmark),semester from  "+
"subjectidentify,syllabus,stud,universitymarks  "+
"where subjectidentify.id=syllabus.id and "+
"stud.rollno=universitymarks.rollno   and theoryorpractical<2 "+
"and universitymarks.subjectcode=syllabus.subjectcode  "+
"and stud.rollno="+roll[i]+" and subjectidentify.dno=stud.department and semester<=2 "+
"group by semester,syllabus.subjectcode,syllabus.maximummark,  "+
"syllabus.passmark order by subjectidentify.semester";
					rs = stmt.executeQuery(quer);
					ajkint=0;
					ajkext=0;
					while(rs.next())
						{
							ajkint=rs.getInt(5);
							ajkext=rs.getInt(6);
							if((ajkext<36) && (ajkint<=20))
							{
								arrno++;
							}
						}
					rs.close();
					stmt.close();
					con.close();
					}catch(Exception e){return e.toString();}
per[i]=((float)tot)/((float)(subcount+prj));
nofarr[i]=arrno;


}

return quer;
}


public String placement()
{
	count=0;
	int prj=0;
	int ajkint=0;
	int ajkext=0;
			try
					{
			quer="select stud.rollno,ltrim(registerno),studentplacement.name,substring(('000'+ltrim(rtrim(str(day(dateofbirth))))),len('000'+ltrim(rtrim(str(day(dateofbirth)))))-1,2)+'/'+ substring(('000'+ltrim(rtrim(str(month(dateofbirth))))), len('000'+ltrim(rtrim(str(month(dateofbirth)))))-1,2)+'/'+ ltrim(rtrim(str(year(dateofbirth)))), " +
                 "sex,address1,address2,address3,address4,phone,email,ltrim(bemarks),ltrim(hscmarks),ltrim(sslcmarks),project,software,areaofinterest,skills,inplant,extracurr from stud,registerno,studentplacement where stud.rollno=registerno.rollno and stud.rollno=studentplacement.rollno and department="+dno+" and year="+year+" and academicyear='"+academicyear+"' order by stud.rollno";
					Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
					Connection con = DriverManager.getConnection("jdbc:odbc:xavier","sxcce","tamil5india6");
					Statement stmt = con.createStatement();
					ResultSet rs;
					
					rs = stmt.executeQuery(quer);
					while(rs.next())
						{
							roll[count]=rs.getInt(1);
							report[0][count]=rs.getString(2);
							studname[count]=rs.getString(3);
							report[1][count]=rs.getString(4);
							report[2][count]=rs.getString(5);
							report[3][count]=rs.getString(6);
							report[4][count]=rs.getString(7);
							report[5][count]=rs.getString(8);
							report[6][count]=rs.getString(9);
							report[7][count]=rs.getString(10);
							report[8][count]=rs.getString(11);
							report[9][count]=rs.getString(12);
							report[10][count]=rs.getString(13);
							report[11][count]=rs.getString(14);
							report[12][count]=rs.getString(15);
							report[13][count]=rs.getString(16);
							report[14][count]=rs.getString(17);
							report[15][count]=rs.getString(18);
							report[16][count]=rs.getString(19);
							report[17][count]=rs.getString(20);							
							count++;

						}
					rs.close();
				}catch(Exception e){return e.toString()+"Count="+count;}
					
for(int i=0;i<count;i++)
{
if(c1<1)
{
quer="select maximummark,passmark,max(subjectname),syllabus.subjectcode, "+
"max(internalmark),max(externalmark),semester from  "+
"subjectidentify,syllabus,stud,universitymarks  "+
"where subjectidentify.id=syllabus.id and "+
"stud.rollno=universitymarks.rollno   and theoryorpractical<2 "+
"and universitymarks.subjectcode=syllabus.subjectcode  "+
"and stud.rollno="+roll[i]+" and subjectidentify.dno=stud.department and semester>2 "+
"group by semester,syllabus.subjectcode,syllabus.maximummark,  "+
"syllabus.passmark order by subjectidentify.semester";
}
else
{
quer="select maximummark,passmark,max(subjectname),syllabus.subjectcode, "+
"max(internalmark),max(externalmark),semester from  "+
"subjectidentify,syllabus,stud,universitymarks  "+
"where subjectidentify.id=syllabus.id and "+
"stud.rollno=universitymarks.rollno   and theoryorpractical<2 "+
"and universitymarks.subjectcode=syllabus.subjectcode  "+
"and stud.rollno="+roll[i]+" and subjectidentify.dno=stud.department "+
"group by semester,syllabus.subjectcode,syllabus.maximummark,  "+
"syllabus.passmark order by subjectidentify.semester";
}
prj=0;
try{
					Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
					Connection con = DriverManager.getConnection("jdbc:odbc:xavier","sxcce","tamil5india6");
					Statement stmt = con.createStatement();
					ResultSet rs;
					
					rs = stmt.executeQuery(quer);
					arrno=0;
					arr="";
                    subcount=0;
                    tot=0;
					while(rs.next())
						{
//							subjectname[subcount]=rs.getString(3);
//							subjectcode[subcount]=rs.getString(4);
							internalmark[subcount]=rs.getInt(5);
							externalmark[subcount]=rs.getInt(6);
if (externalmark[subcount]>100) prj++;

//							sem[subcount]=rs.getInt(7);
							tot=tot+internalmark[subcount]+externalmark[subcount];
							if((externalmark[subcount]<36) && (internalmark[subcount]<=20))
							{
								arrno++;
//								arr=arr+subjectcode[subcount]+":"+internalmark[subcount]+externalmark[subcount]+" - ";
//								arr=arr+sem[subcount]+":"+subjectcode[subcount]+", ";
							}
							subcount++;
						}
					rs.close();
quer="select maximummark,passmark,max(subjectname),syllabus.subjectcode, "+
"max(internalmark),max(externalmark),semester from  "+
"subjectidentify,syllabus,stud,universitymarks  "+
"where subjectidentify.id=syllabus.id and "+
"stud.rollno=universitymarks.rollno   and theoryorpractical<2 "+
"and universitymarks.subjectcode=syllabus.subjectcode  "+
"and stud.rollno="+roll[i]+" and subjectidentify.dno=stud.department and semester<=2 "+
"group by semester,syllabus.subjectcode,syllabus.maximummark,  "+
"syllabus.passmark order by subjectidentify.semester";
					rs = stmt.executeQuery(quer);
					ajkint=0;
					ajkext=0;
					while(rs.next())
						{
							ajkint=rs.getInt(5);
							ajkext=rs.getInt(6);
							if((ajkext<36) && (ajkint<=20))
							{
								arrno++;
							}
						}
					rs.close();
					stmt.close();
					con.close();
					}catch(Exception e){return e.toString();}
per[i]=((float)tot)/((float)(subcount+prj));
nofarr[i]=arrno;


}
return quer;
}

public String attendanceallselect()
		   {
			quer="select rollno from registerno where registerno='"+registerno+"'";				
			try
					{
					Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
					Connection con = DriverManager.getConnection("jdbc:odbc:xavier","sxcce","tamil5india6");
					Statement stmt = con.createStatement();
					ResultSet rs;
				quer="select rollno from registerno where registerno='"+registerno+"'";				
					rs = stmt.executeQuery(quer);
					if(rs.next())
						{
							rollno=rs.getString(1);
						}
					rs.close();
					try{
rs = stmt.executeQuery("select registerno from registerno where rollno="+rollno);
   if(rs.next())
      {
//      	rollno=registerno;
      	registerno=rs.getString(1);
      }
rs.close();
}catch(Exception e){}
					
quer="select name,department,year from stud where rollno="+rollno+" and academicyear in(select academicyear from academicyear)";
rs = stmt.executeQuery(quer);
   if(rs.next())
      {
      name=rs.getString(1);
      dno=rs.getInt(2);
      year=rs.getInt(3);
      }
rs.close();

quer="select exam,syllabus.subjectcode,max(subjectname),hours,thours,semester from "
+"examyear,syllabus,stud,markatt "
+"where examyear.examno=markatt.examno and "
+"stud.rollno=markatt.rollno "
+"and markatt.subjectcode=syllabus.subjectcode "
+"and stud.rollno="+rollno+" and examyear.dno=stud.department and exam like '%Exam%' "
+"group by exam,syllabus.subjectcode,hours,thours,semester "
+"order by semester,syllabus.subjectcode,exam";

subcount=0;
					rs = stmt.executeQuery(quer);
					while(rs.next())
						{
							exam[subcount]=rs.getString(1);
							subjectcode[subcount]=rs.getString(2);
							subjectname[subcount]=rs.getString(3);
							hours[subcount]=rs.getInt(4);
							thours[subcount]=rs.getInt(5);
							sem[subcount]=rs.getInt(6);
							subcount++;
						}
					rs.close();
					stmt.close();
					con.close();
					}catch(Exception e){return e.toString();}
return quer+"deepan";

		}

public String internalallselect()
		   {
			quer="select rollno from registerno where registerno='"+registerno+"'";				
			try
					{
					Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
					Connection con = DriverManager.getConnection("jdbc:odbc:xavier","sxcce","tamil5india6");
					Statement stmt = con.createStatement();
					ResultSet rs;
				quer="select rollno from registerno where registerno='"+registerno+"'";				
					rs = stmt.executeQuery(quer);
					if(rs.next())
						{
							rollno=rs.getString(1);
						}
					rs.close();
					try{
rs = stmt.executeQuery("select registerno from registerno where rollno="+rollno);
   if(rs.next())
      {
//      	rollno=registerno;
      	registerno=rs.getString(1);
      }
rs.close();
}catch(Exception e){}
					
quer="select name,department,year from stud where rollno="+rollno+" and academicyear in(select academicyear from academicyear)";
rs = stmt.executeQuery(quer);
   if(rs.next())
      {
      name=rs.getString(1);
      dno=rs.getInt(2);
      year=rs.getInt(3);
      }
rs.close();

quer="select exam,syllabus.subjectcode,max(subjectname),min(mark),semester from "+
"examyear,syllabus,stud,mark "+
"where examyear.examno=mark.examno and "+
"stud.rollno=mark.rollno "+
"and mark.subjectcode=syllabus.subjectcode "+
"and stud.rollno="+rollno+" and examyear.dno=stud.department and exam like '%Exam%' "+
"group by exam,syllabus.subjectcode,semester "+
"order by semester,syllabus.subjectcode,exam ";

subcount=0;
					rs = stmt.executeQuery(quer);
					while(rs.next())
						{
							exam[subcount]=rs.getString(1);
							subjectcode[subcount]=rs.getString(2);
							subjectname[subcount]=rs.getString(3);
							mark[subcount]=rs.getInt(4);
							sem[subcount]=rs.getInt(5);
							subcount++;
						}
					rs.close();
					stmt.close();
					con.close();
					}catch(Exception e){return e.toString();}
return quer+"deepan";

		}


public String classtestallselect()
		   {
			quer="select rollno from registerno where registerno='"+registerno+"'";				
			try
					{
					Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
					Connection con = DriverManager.getConnection("jdbc:odbc:xavier","sxcce","tamil5india6");
					Statement stmt = con.createStatement();
					ResultSet rs;
				quer="select rollno from registerno where registerno='"+registerno+"'";				
					rs = stmt.executeQuery(quer);
					if(rs.next())
						{
							rollno=rs.getString(1);
						}
					rs.close();
					try{
rs = stmt.executeQuery("select registerno from registerno where rollno="+rollno);
   if(rs.next())
      {
//      	rollno=registerno;
      	registerno=rs.getString(1);
      }
rs.close();
}catch(Exception e){}
					
quer="select name,department,year from stud where rollno="+rollno+" and academicyear in(select academicyear from academicyear)";
rs = stmt.executeQuery(quer);
   if(rs.next())
      {
      name=rs.getString(1);
      dno=rs.getInt(2);
      year=rs.getInt(3);
      }
rs.close();

quer="select exam,syllabus.subjectcode,max(subjectname),min(mark),semester from "+
"examyear,syllabus,stud,mark "+
"where examyear.examno=mark.examno and "+
"stud.rollno=mark.rollno "+
"and mark.subjectcode=syllabus.subjectcode "+
"and stud.rollno="+rollno+" and examyear.dno=stud.department and exam like '%Class%' "+
"group by exam,syllabus.subjectcode,semester "+
"order by semester,syllabus.subjectcode,exam ";

subcount=0;
					rs = stmt.executeQuery(quer);
					while(rs.next())
						{
							exam[subcount]=rs.getString(1);
							subjectcode[subcount]=rs.getString(2);
							subjectname[subcount]=rs.getString(3);
							mark[subcount]=rs.getInt(4);
							sem[subcount]=rs.getInt(5);
							subcount++;
						}
					rs.close();
					stmt.close();
					con.close();
					}catch(Exception e){return e.toString();}
return quer+"deepan";

		}

public String assignmentallselect()
		   {
			quer="select rollno from registerno where registerno='"+registerno+"'";				
			try
					{
					Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
					Connection con = DriverManager.getConnection("jdbc:odbc:xavier","sxcce","tamil5india6");
					Statement stmt = con.createStatement();
					ResultSet rs;
				quer="select rollno from registerno where registerno='"+registerno+"'";				
					rs = stmt.executeQuery(quer);
					if(rs.next())
						{
							rollno=rs.getString(1);
						}
					rs.close();
					try{
rs = stmt.executeQuery("select registerno from registerno where rollno="+rollno);
   if(rs.next())
      {
//      	rollno=registerno;
      	registerno=rs.getString(1);
      }
rs.close();
}catch(Exception e){}
					
quer="select name,department,year from stud where rollno="+rollno+" and academicyear in(select academicyear from academicyear)";
rs = stmt.executeQuery(quer);
   if(rs.next())
      {
      name=rs.getString(1);
      dno=rs.getInt(2);
      year=rs.getInt(3);
      }
rs.close();

quer="select exam,syllabus.subjectcode,max(subjectname),min(mark),semester from "+
"examyear,syllabus,stud,mark "+
"where examyear.examno=mark.examno and "+
"stud.rollno=mark.rollno "+
"and mark.subjectcode=syllabus.subjectcode "+
"and stud.rollno="+rollno+" and examyear.dno=stud.department and exam like '%Assignment%' "+
"group by exam,syllabus.subjectcode,semester "+
"order by semester,syllabus.subjectcode,exam ";

subcount=0;
					rs = stmt.executeQuery(quer);
					while(rs.next())
						{
							exam[subcount]=rs.getString(1);
							subjectcode[subcount]=rs.getString(2);
							subjectname[subcount]=rs.getString(3);
							mark[subcount]=rs.getInt(4);
							sem[subcount]=rs.getInt(5);
							subcount++;
						}
					rs.close();
					stmt.close();
					con.close();
					}catch(Exception e){return e.toString();}
return quer+"deepan";

		}
		

public String intallselect()
		   {
			quer="select rollno from registerno where registerno='"+registerno+"'";				
			try
					{
					Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
					Connection con = DriverManager.getConnection("jdbc:odbc:xavier","sxcce","tamil5india6");
					Statement stmt = con.createStatement();
					ResultSet rs;
				quer="select rollno from registerno where registerno='"+registerno+"'";				
					rs = stmt.executeQuery(quer);
					if(rs.next())
						{
							rollno=rs.getString(1);
						}
					rs.close();
					try{
rs = stmt.executeQuery("select registerno from registerno where rollno="+rollno);
   if(rs.next())
      {
//      	rollno=registerno;
      	registerno=rs.getString(1);
      }
rs.close();
}catch(Exception e){}
					
quer="select name,department,year from stud where rollno="+rollno+" and academicyear in(select academicyear from academicyear)";
rs = stmt.executeQuery(quer);
   if(rs.next())
      {
      name=rs.getString(1);
      dno=rs.getInt(2);
      year=rs.getInt(3);
      }
rs.close();

quer="select exam,syllabus.subjectcode,max(subjectname),max(marka.mark),max(marka.marka),max(mark.mark),semester  from examyear,syllabus,stud,mark,marka  "
+" where examyear.examno=mark.examno and stud.rollno=mark.rollno and stud.rollno=marka.rollno and mark.subjectcode=syllabus.subjectcode "
+" and mark.examno=marka.examno and mark.subjectcode=marka.subjectcode and stud.rollno="+rollno+" and examyear.dno=stud.department "
+" group by exam,syllabus.subjectcode,semester order by semester,syllabus.subjectcode,exam ";

subcount=0;
					rs = stmt.executeQuery(quer);
					while(rs.next())
						{
							exam[subcount]=rs.getString(1);
							subjectcode[subcount]=rs.getString(2);
							subjectname[subcount]=rs.getString(3);
							internalmark[subcount]=rs.getInt(4);
							externalmark[subcount]=rs.getInt(5);
							mark[subcount]=rs.getInt(6);
							sem[subcount]=rs.getInt(7);
							subcount++;
						}
					rs.close();
					stmt.close();
					con.close();
					}catch(Exception e){return e.toString();}
return quer+"deepan";

		}

		
public String repattper()
		   {
			quer="select rollno from registerno where registerno='"+registerno+"'";				
			try
					{
					Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
					Connection con = DriverManager.getConnection("jdbc:odbc:xavier","sxcce","tamil5india6");
					Statement stmt = con.createStatement();
					ResultSet rs;
				quer="select rollno from registerno where registerno='"+registerno+"'";				
					rs = stmt.executeQuery(quer);
					if(rs.next())
						{
							rollno=rs.getString(1);
						}
					rs.close();
					try{
rs = stmt.executeQuery("select registerno from registerno where rollno="+registerno);
   if(rs.next())
      {
      	rollno=registerno;
      	registerno=rs.getString(1);
      }
rs.close();
}catch(Exception e){}
					
quer="select name,department,year from stud where rollno="+rollno+" and academicyear in(select academicyear from academicyear)";
rs = stmt.executeQuery(quer);
   if(rs.next())
      {
      name=rs.getString(1);
      dno=rs.getInt(2);
      year=rs.getInt(3);
      }
rs.close();

quer="select semester,total,present,absent,od,percentage from attper where rollno="+rollno+" order by semester";

subcount=0;


					rs = stmt.executeQuery(quer);
					while(rs.next())
						{
							sem[subcount]=rs.getInt(1);
							roll[subcount]=rs.getInt(2);
							nofarr[subcount]=rs.getInt(3);
							internalmark[subcount]=rs.getInt(4);
							externalmark[subcount]=rs.getInt(5);
							mark[subcount]=rs.getInt(6);
							subcount++;
						}
					rs.close();
					stmt.close();
					con.close();
					}catch(Exception e){return e.toString();}
return quer+"deepan";

}

public String Insert()		
{
try
{
Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
Connection con = DriverManager.getConnection("jdbc:odbc:xavier","sxcce","tamil5india6");
PreparedStatement ps;
for(int i=0;i<subcount;i++)
{
ps = con.prepareStatement("delete from universitymarks where rollno=? and year=? and month=? and subjectcode=?");
ps.setString(1,rollno);
ps.setInt(2,year);
ps.setInt(3,month);
ps.setString(4,subjectcode[i]);
int j = ps.executeUpdate();
ps.close();
ps = con.prepareStatement("insert into universitymarks values(?,?,?,?,?,?)");
ps.setString(1,rollno);
ps.setString(2,subjectcode[i]);
ps.setInt(3,internalmark[i]);
ps.setInt(4,externalmark[i]);
ps.setInt(5,year);
ps.setInt(6,month);
j = ps.executeUpdate();
ps.close();
}
con.close();
}catch(Exception e){return "Error !!"+e.toString();}
return("Successfully Updated !");
}
   	

public String Delete()		
{
try
{
Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
Connection con = DriverManager.getConnection("jdbc:odbc:xavier","sxcce","tamil5india6");
PreparedStatement ps;
ps = con.prepareStatement("delete from universitymarks where rollno=? and year=? and month=?");
ps.setString(1,rollno);
ps.setInt(2,year);
ps.setInt(3,month);
int j = ps.executeUpdate();
ps.close();
con.close();
}catch(Exception e){return "Error !!"+e.toString();}
return("Successfully Deleted !");
}
   	

			
public int View()
{
int found=0;
try
{
Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
Connection con = DriverManager.getConnection("jdbc:odbc:xavier","sxcce","tamil5india6");
Statement stmt = con.createStatement();
ResultSet rs;
  for(int j=0;j<subcount;j++)
    {
rs = stmt.executeQuery("select internalmark,externalmark from universitymarks where rollno="+rollno+" and subjectcode='"+subjectcode[j]+"' and year="+unyear+" and month="+month);
if(rs.next())
{
	internalmark[j]=rs.getInt(1);
	externalmark[j]=rs.getInt(2);
	mark[j]=0;
	if(internalmark[j]>0)mark[j]+=internalmark[j];
	if(externalmark[j]>0)mark[j]+=externalmark[j];
found=1;
}
rs.close();
}
stmt.close();
con.close();
}catch(Exception e){return -10;}
return found; 
}
		public String getname()
		   {
		   	return this.name;
		   }
		public String[][] getreport()
		   {
		   	return this.report;
		   }
		public void setsubjectcode(String subjectcode[])
		{
			this.subjectcode=subjectcode;
		}
		public String[] getsubjectcode()
		   {
		   	return this.subjectcode;
		   }
		public String[] getsubjectname()
		   {
		   	return this.subjectname;
		   }
		public String[] getstudname()
		   {
		   	return this.studname;
		   }
		public String[] getexam()
		   {
		   	return this.exam;
		   }
		public int getcount()
		   {
		   	return count;
		   }   
		public int getnonarrear()
		   {
		   	return nonarrear;
		   }   
		public void setsubcount(int subcount)
		{
			this.subcount=subcount;
			}   
		public int getsubcount()
		   {
		   	return subcount;
		   }   
	public void setdno(int dno) 
	{ 
	this.dno = dno; 
	} 
	public void setc1(int c1) 
	{ 
	this.c1 = c1; 
	} 
	public int getdno() 
	{ 
	return this.dno; 
	} 
	public void setmonth(int month) 
	{ 
	this.month = month; 
	} 
	public int getmonth() 
	{ 
	return this.month; 
	} 
	public void setrollno(String rollno) 
	{ 
	this.rollno = rollno; 
	} 

	public void setregisterno(String registerno) 
	{ 
	this.registerno = registerno; 
	} 
	public String getregisterno() 
	{ 
	return this.registerno; 
	} 

	public void setsemester(int semester) 
	{ 
	this.semester = semester; 
	} 
	public int getsemester() 
	{ 
	return this.semester; 
	} 
	public void setyear(int year) 
	{ 
	this.year = year; 
	} 
	public int getyear() 
	{ 
	return this.year; 
	}

	public int getunyear() 
	{ 
	return this.unyear; 
	}

	public int getarrno() 
	{ 
	return this.arrno; 
	}

	public String getarr()
	{
		return this.arr;
	} 
	public String getrollno()
	{
		return this.rollno;
	} 
		public int[] getinternalmark()
		   {
		   	return this.internalmark;
		   }
		public void setinternalmark(int[] internalmark)
		   {
		   	this.internalmark=internalmark;
		   }
		public int[]getexternalmark()
		   {
		   	return this.externalmark;
		   }
		public void setexternalmark(int[]externalmark)
		   {
		   	this.externalmark=externalmark;
		   }
		public int[] getmark()
		   {
		   	return this.mark;
		   }
		public int[] gethours()
		   {
		   	return this.hours;
		   }
		public int[] getthours()
		   {
		   	return this.thours;
		   }
		public float[] getper()
		   {
		   	return this.per;
		   }
		public int[] getnofarr()
		   {
		   	return this.nofarr;
		   }
		public int[] getmat()
		   {
		   	return this.mat;
		   }
		public int[] getphy()
		   {
		   	return this.phy;
		   }
		public int[] getche()
		   {
		   	return this.che;
		   }
		public int[] getvsem()
		   {
		   	return this.vsem;
		   }
		public int[] getvisem()
		   {
		   	return this.visem;
		   }
		public int[] getvoc1()
		   {
		   	return this.voc1;
		   }
		public int[] getvoc2()
		   {
		   	return this.voc2;
		   }
		public int[] getroll()
		   {
		   	return this.roll;
		   }
		public int[] getsem()
		   {
		   	return this.sem;
		   }
		public void setmark(int[] mark)
		   {
		   	this.mark=mark;
		   }
		public String getacademicyear()
		{
			return this.academicyear;
		}
	public void setacademicyear(String academicyear) 
	{ 
	this.academicyear = academicyear; 
	} 
		
		public String getquer()
		{
			return this.quer;
		}

	}
	