package sxcce;
import java.sql.*;
public class SubjectView1
	{
		int count=0;
		String subname[] = new String[100];
		String credit[] = new String[100];
		String subcode[] = new String[100];
		int id=0;
		int examno=0;
		String dno="";
		String semester=""; 
		String academicyear="";
        String handledby[] = new String[100];
        int maxstud[] = new int[100];
        int maxpassstud[] = new int[100];
        int maxmark[] = new int[10];
        String maxmarkname[] = new String[100];
        int allexamstud=0;
        int allexampassed=0;
        int rnocount=0;
        int rollno[] = new int[400];
        int year1;
        int month2;
        int year2;
		public void Viewsubject()
			{count=0;
				for(int i=0;i<10;i++)
				   {
				   	subname[i]="";
				   	subcode[i]="";
				   }
				try
					{
					Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
					Connection con = DriverManager.getConnection("jdbc:odbc:xavier","sxcce","tamil5india6");
					Statement stmt=con.createStatement();
					ResultSet ps;
			        ps = stmt.executeQuery("select * from syllabus where theoryorpractical in(1,4,5) and id= (select id from subjectidentify where semester="+semester+" and academicyear='"+academicyear+"' and dno="+dno+")");
					while(ps.next())
					{
						subcode[count]=ps.getString(1);
						subname[count]=ps.getString(2);
						id=ps.getInt(3);
						credit[count]=ps.getString(4);
						count++;
					}
					ps.close();
					stmt.close();
					con.close();
					}catch(Exception e){}
			}
			
				public String ViewsubjectAll(int tsem,int tdno,String tacc)
			{
				String error="...";
				count=0;
				for(int i=0;i<100;i++)
				   {
				   	subname[i]="";
				   	subcode[i]="";
				   }
				try
					{
					Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
					Connection con = DriverManager.getConnection("jdbc:odbc:xavier","sxcce","tamil5india6");
					Statement stmt=con.createStatement();
					ResultSet ps;
			        ps = stmt.executeQuery("select * from syllabus where theoryorpractical in (0,1,3,4,5) and id= (select id from subjectidentify where semester="+tsem+" and academicyear='"+tacc+"' and dno="+tdno+")");
					while(ps.next())
					{
						subcode[count]=ps.getString(1);
						subname[count]=ps.getString(2);
						id=ps.getInt(3);
						credit[count]=ps.getString(4);
						count++;
					}
					ps.close();
					stmt.close();
					con.close();
					}catch(Exception e){error=e.toString();}
					return error;
			}
			
		public String Subject(String scode)
			{
				String sname="No Name";
				try
					{
					Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
					Connection con = DriverManager.getConnection("jdbc:odbc:xavier","sxcce","tamil5india6");
					Statement stmt=con.createStatement();
					ResultSet ps;
			        ps = stmt.executeQuery("select max(id),max(subjectcode),max(subjectname) from syllabus where subjectcode='"+scode+"'");
					if(ps.next())
					{
						sname=ps.getString(3);
					}
					ps.close();
					stmt.close();
					con.close();
					}catch(Exception e){ return sname;}
					return sname;
			}			
			
		public void Viewsubject1()
			{count=0;
				for(int i=0;i<10;i++)
				   {
				   	subname[i]="";
				   	subcode[i]="";
				   }
				try
					{
					Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
					Connection con = DriverManager.getConnection("jdbc:odbc:xavier","sxcce","tamil5india6");
					Statement stmt=con.createStatement();
					ResultSet ps;
			        ps = stmt.executeQuery("select * from syllabus where  theoryorpractical in(1,4,5) and id= (select id from subjectidentify where semester="+semester+" and academicyear='"+academicyear+"' and dno="+dno+")");
					while(ps.next())
					{
						subcode[count]=ps.getString(1);
						subname[count]=ps.getString(2);
						id=ps.getInt(3);
						credit[count]=ps.getString(4);
						count++;
					}
   subname[count]="Library";
   subcode[count]="LIB12";
   count++;
					ps.close();
					stmt.close();
					con.close();
					}catch(Exception e){}
			}
		public void SubjectHandled1()
		    {
		    	String quer="";
				try
					{
					Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
					Connection con = DriverManager.getConnection("jdbc:odbc:xavier","sxcce","tamil5india6");
					Statement stmt=con.createStatement();
					ResultSet ps;
					for(int i=0;i<count;i++)
					  {
					  	handledby[i]="";
quer="select name from staff where staffid in (select staffid from subjecthandled where id="+id+" and subcode='"+subcode[i]+"')";
			        ps = stmt.executeQuery(quer);
			        int hh=0;
					while(ps.next())
					{hh++;
					if(hh>1)handledby[i]=handledby[i]+" &<br>";
						handledby[i]=handledby[i]+ps.getString(1);
					}
					ps.close();
				}
					stmt.close();
					con.close();
					}catch(Exception e){}
					
				
		    }	

		public void SubjectHandled()
		    {
		    	String quer="";
				try
					{
					Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
					Connection con = DriverManager.getConnection("jdbc:odbc:xavier","sxcce","tamil5india6");
					Statement stmt=con.createStatement();
					ResultSet ps;
					if(Integer.parseInt(semester)<3)year1=1;
					else if(Integer.parseInt(semester)<5)year1=2;
					else if(Integer.parseInt(semester)<7)year1=3;
					else year1=4;
					rnocount=0;
					ResultSet rs = stmt.executeQuery("select rollno,name from stud where department="+dno+" and year="+year1+" and stud.academicyear='"+academicyear+"' order by rollno");
					while(rs.next())
						{
						rollno[rnocount]=rs.getInt(1);
						rnocount++;	
						}
					rs.close();

  if(Integer.parseInt(semester)%2==0)
      {
      	month2=1;
      	year2=Integer.parseInt(academicyear.substring(8,12));
      }
   else
      {
      	month2=2;
      	year2=Integer.parseInt(academicyear.substring(0,4));
      }
      
					for(int i=0;i<count;i++)
					  {
					  	handledby[i]="";
quer="select name from staff where staffid in (select staffid from subjecthandled where id="+id+" and subcode='"+subcode[i]+"')";
			        ps = stmt.executeQuery(quer);
			        int hh=0;
					while(ps.next())
					{hh++;
					if(hh>1)handledby[i]=handledby[i]+" &<br>";
						handledby[i]=handledby[i]+ps.getString(1);
					}
					ps.close();

quer="select count(rollno) from universitymarks where internalmark>-1 and externalmark>-1 "+
"and subjectcode='"+subcode[i]+"' and  year="+year2+" and month="+month2+" and "+
"rollno in (select stud.rollno from stud,student where stud.rollno=student.rollno and student.active=1 and department = "+dno+" and year="+year1+" and "+
"academicyear='"+academicyear+"')";
			        ps = stmt.executeQuery(quer);
					if(ps.next())
					{
						maxstud[i]=ps.getInt(1);
					}
					ps.close();

quer="select count(rollno) from universitymarks where internalmark+externalmark>49 and externalmark>35 "+
"and subjectcode='"+subcode[i]+"' and  year="+year2+" and month="+month2+" and "+
"rollno in (select stud.rollno from stud,student where stud.rollno=student.rollno and student.active=1 and department = "+dno+" and year="+year1+" and "+
"academicyear='"+academicyear+"')";
			        ps = stmt.executeQuery(quer);
					if(ps.next())
					{
						maxpassstud[i]=ps.getInt(1);
					}
				    
quer="select max(internalmark+externalmark) from universitymarks where internalmark>-1 and externalmark>35 "+
"and subjectcode='"+subcode[i]+"' and  year="+year2+" and month="+month2+" and "+
"rollno in (select stud.rollno from stud,student where stud.rollno=student.rollno and student.active=1 and department = "+dno+" and year="+year1+" and "+
"academicyear='"+academicyear+"')";
			        ps = stmt.executeQuery(quer);
					if(ps.next())
					{
						maxmark[i]=ps.getInt(1);
					}
					ps.close();

quer="select name from student where rollno in (select rollno from universitymarks where internalmark>-1 and externalmark>35 "+
"and subjectcode='"+subcode[i]+"' and  year="+year2+" and month="+month2+" and internalmark+externalmark="+maxmark[i]+" and "+
"rollno in (select stud.rollno from stud,student where stud.rollno=student.rollno and student.active=1 and department = "+dno+" and year="+year1+" and "+
"academicyear='"+academicyear+"'))";

			         hh=0;
			         maxmarkname[i]="";
			        ps = stmt.executeQuery(quer);
					while(ps.next())
					{hh++;
					if(hh>1)maxmarkname[i]=maxmarkname[i]+",<br>";
						maxmarkname[i]=maxmarkname[i]+ps.getString(1);
					}
					ps.close();
				    }
	String tempsubcode="'0'";
	for(int z=0;z<count;z++)
	  {
	  	tempsubcode=tempsubcode+",'"+subcode[z]+"'";
	  }

for(int i=0;i<rnocount;i++)
{
quer="select count(rollno) from universitymarks where internalmark>-1 and externalmark>-1 "+
"and rollno="+rollno[i]+" and subjectcode in("+tempsubcode+") and  year="+year2+" and month="+month2+" and "+
"rollno in (select stud.rollno from stud,student where stud.rollno=student.rollno and student.active=1 and department = "+dno+" and year="+year1+" and "+
"academicyear='"+academicyear+"')";
			        ps = stmt.executeQuery(quer);
					if(ps.next())
					{
						if(ps.getInt(1)==count)allexamstud++;
					}
                    ps.close();
}
for(int i=0;i<rnocount;i++)
{
quer="select count(rollno) from universitymarks where internalmark+externalmark>49 and internalmark>-1 and externalmark>35 "+
"and rollno="+rollno[i]+" and subjectcode in("+tempsubcode+") and  year="+year2+" and month="+month2+" and "+
"rollno in (select stud.rollno from stud,student where stud.rollno=student.rollno and student.active=1 and department = "+dno+" and year="+year1+" and "+
"academicyear='"+academicyear+"')";
			        ps = stmt.executeQuery(quer);
					if(ps.next())
					{
						if(ps.getInt(1)==count)allexampassed++;
					}
                    ps.close();
}
					stmt.close();
					con.close();
					}catch(Exception e){}
					
				
		    }	
	public String[] getSubname()
	   {
	   	return this.subname;
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
	public void setDno(String dno)
	   {
	   	this.dno=dno;
	   }
	public void setSemester(String semester)
	   {
	   	this.semester=semester;
	   }

	public String getDno()
	   {
	   	return this.dno;
	   }
	public String getSemester()
	   {
	   	return this.semester;
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
	    
	}