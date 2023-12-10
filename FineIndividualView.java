package sxcce;
import java.sql.*;

public class FineIndividualView
	{
	int headcount=0;
	int dno=0;
	int year=0;
	String academicyear="2011 -- 2012";
	int rollno=0;
	String name="";
	String quer="";
	String sex="";
	float fine[] = new float[50];
	float totfine=0;
	int slno[] = new int[50];
	String fname[] = new String[50];
    String fdate="";
    String tdate="";
	float printpagerate=0;
	float paid =0;
	float prndue =0;
	float libfine =0;
	int prndue1 =0;
	float libdue =0;
	int accounts=0;
	int hod=0;
	int maxsubject=0;
	int feedbackentered=0;
	int id=0;
	int library=0;
String feesslno="0";
String feesindividualslno="0";
		public int CheckRollno()
			{int ret=0;
				try
					{
					Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
					Connection con = DriverManager.getConnection("jdbc:odbc:xavier","sxcce","tamil5india6");
					Statement stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery("select stud.name,department,year,sex from stud,student where stud.rollno=student.rollno and stud.rollno="+rollno+" and "+
					"academicyear in (select academicyear from academicyear)");
					if(rs.next())
						{
							ret=1;
							name=rs.getString(1);
							dno=rs.getInt(2);
							year=rs.getInt(3);
							sex=rs.getString(4);
						}
					rs.close();
					rs = stmt.executeQuery("select * from fineviewidentify");
					if(rs.next())
						{
							academicyear=rs.getString(1);
							int sem=rs.getInt(2);
							if(sem==1)
							  {
								if(year==1)year=1;
								else if(year==2)year=3;
								else if(year==3)year=5;
								else year=7;
					   		 }
							else if(sem==2)
							  {
								if(year==1)year=2;
								else if(year==2)year=4;
								else if(year==3)year=6;
								else year=8;
					   		 }
						}
					rs.close();
					stmt.close();
					con.close();
					}catch(Exception e){}
				return ret;	
			}
		public void SelectHeads()
			{
				try
					{
					Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
					Connection con = DriverManager.getConnection("jdbc:odbc:xavier","sxcce","tamil5india6");
					Statement stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery("select name,slno from finestructure,fineidentify where finestructure.finestrno=fineidentify.finestrno and academicyear='"+academicyear+"' and year="+year+" and dno="+dno);
					while(rs.next())
						{
						fname[headcount]=rs.getString(1);
						slno[headcount]=rs.getInt(2);
						headcount++;	
						}
					rs.close();
					stmt.close();
					con.close();
					}catch(Exception e){}
			}
		public void CommonFine()
			{
				for(int i=0;i<headcount;i++)
				   fine[i]=0;
				try
					{
					Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
					Connection con = DriverManager.getConnection("jdbc:odbc:xavier","sxcce","tamil5india6");
					Statement stmt = con.createStatement();
					for(int i=0;i<headcount;i++)
					{
					ResultSet rs = stmt.executeQuery("select amount,boysorgirls from commonfine where slno="+slno[i]);
					if(rs.next())
						{
						float temp=rs.getFloat(1);
						int temp1=rs.getInt(2);
						  	if(sex.equals("Male") && temp1==1)
						  		fine[i]+=temp;
						  	else if(sex.equals("Female") && temp1==2)
						  	    fine[i]+=temp;
						  	else if(temp1==0)
						  	   fine[i]+=temp;      

						}
					rs.close();
					}
					stmt.close();
					con.close();
					}catch(Exception e){}
			}

		public void IndividualFine()
			{
				try
					{
					Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
					Connection con = DriverManager.getConnection("jdbc:odbc:xavier","sxcce","tamil5india6");
					Statement stmt = con.createStatement();
					for(int j=0;j<headcount;j++)
					{
					ResultSet rs = stmt.executeQuery("select amount from individualfine where slno="+slno[j]+" and rollno="+rollno);
					if(rs.next())
						{
							fine[j]+=rs.getFloat(1);
						}
					rs.close();
					}
					stmt.close();
					con.close();
					}catch(Exception e){}
				  for(int j=0;j<headcount;j++)
				     {
				     	totfine+=fine[j];
				     }	
			}

		public void NewFine()
			{
				try
					{
					Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
					Connection con = DriverManager.getConnection("jdbc:odbc:xavier","sxcce","tamil5india6");
					Statement stmt = con.createStatement();
					ResultSet rs;

					rs= stmt.executeQuery("SELECT     head, reason, amount FROM accounts.dbo.dues WHERE rollno ="+rollno);
					while(rs.next())
						{
						slno[headcount]=rs.getInt(1);
						fname[headcount]="Fine or Due ("+rs.getString(2)+")";
//						slno[headcount]=2005+headcount;
						fine[headcount]=rs.getFloat(3);
					    totfine+=fine[headcount];
						headcount++;
						}
					rs.close();
					
					
					rs= stmt.executeQuery("SELECT     head, reason, amount FROM accounts.dbo.busfee WHERE rollno ="+rollno);
					while(rs.next())
						{
						slno[headcount]=rs.getInt(1);
						fname[headcount]="Bus Fee ("+rs.getString(2)+")";
//						slno[headcount]=3000+headcount;
						fine[headcount]=rs.getFloat(3);
					    totfine+=fine[headcount];
						headcount++;
						}
					rs.close();
					
					rs= stmt.executeQuery("SELECT     head, reason, amount FROM accounts.dbo.examfee WHERE rollno ="+rollno);
					while(rs.next())
						{
						slno[headcount]=rs.getInt(1);
						fname[headcount]="Exam Fee ("+rs.getString(2)+")";
//						slno[headcount]=2005+headcount;
						fine[headcount]=rs.getFloat(3);
					    totfine+=fine[headcount];
						headcount++;
						}
					rs.close();

					rs= stmt.executeQuery("SELECT     head, reason, amount FROM accounts.dbo.revalfee WHERE rollno ="+rollno);
					while(rs.next())
						{
						slno[headcount]=rs.getInt(1);
						fname[headcount]="Revaluation fee ("+rs.getString(2)+")";
//						slno[headcount]=2005+headcount;
						fine[headcount]=rs.getFloat(3);
					    totfine+=fine[headcount];
						headcount++;
						}
					rs.close();

					stmt.close();
					con.close();
					}catch(Exception e){}

			}


public void FeeFine()
			{
			int f=0;
			f=Viewfees();

				try
					{
					Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
					Connection con = DriverManager.getConnection("jdbc:odbc:xavier","sxcce","tamil5india6");
					Statement stmt = con.createStatement();
					ResultSet rs;

quer="select sum(amount) from accounts.dbo.feesstructureamount "+
" where fsino="+feesslno;
rs = stmt.executeQuery(quer);
if(rs.next())
{
fine[headcount]=rs.getFloat(1);
}

rs.close();
quer="select sum(amount) from accounts.dbo.feesstructureindividualamount "+
" where fsiino="+feesindividualslno;
rs = stmt.executeQuery(quer);
if(rs.next())
{
fine[headcount]+=rs.getFloat(1);

}
rs.close();

			fname[headcount]="Fees Balance";
			slno[headcount]=2003;
		    totfine+=fine[headcount];
			headcount++;

quer="select sum(amount) from accounts.dbo.payment where head<>999 and rollno="+rollno;
rs = stmt.executeQuery(quer);
if(rs.next())
{
	paid=rs.getFloat(1);
}
rs.close();
			
quer="select sum(amount) from accounts.dbo.payment where head=999 and rollno="+rollno;
rs = stmt.executeQuery(quer);
if(rs.next())
{
	paid=paid-rs.getFloat(1);
}
rs.close();

					stmt.close();
					con.close();
					}catch(Exception e){}
			}

public int Viewfees()
{
String quer="";
int found=0;
try
{

					Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
					Connection con = DriverManager.getConnection("jdbc:odbc:xavier","sxcce","tamil5india6");
					Statement stmt = con.createStatement();


ResultSet rs;
quer="select fslno from accounts.dbo.feeassign where rollno="+rollno;
rs = stmt.executeQuery(quer);
if(rs.next())
{
feesslno=rs.getString(1);
found=1;
}
rs.close();

quer="select slno from accounts.dbo.feesstructureindividualidentify where rollno="+rollno;
rs = stmt.executeQuery(quer);
if(rs.next())
{
feesindividualslno=rs.getString(1);
found=1;
}
rs.close();

			
					stmt.close();
					con.close();
					}catch(Exception e){}

return found; 
}

		public String PrintLib()
			{
				try
					{
					Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
					Connection con = DriverManager.getConnection("jdbc:odbc:xavier","sxcce","tamil5india6");
					Statement stmt = con.createStatement();
                    ResultSet rs;
                    quer="select (ltrim(rtrim(str(month(opening))))+'/'+ltrim(rtrim(str(day(opening))))+'/'+ltrim(rtrim(str(year(opening))))),"+
                     "(ltrim(rtrim(str(month(closing))))+'/'+ltrim(rtrim(str(day(closing))))+'/'+ltrim(rtrim(str(year(closing))))) from attendance.dbo.reopening where dno="+dno+" and academicyear='"+academicyear+"' and semester="+year;
                    rs = stmt.executeQuery(quer);
                    if(rs.next())
                    {
      	              fdate=rs.getString(1);
      	              tdate=rs.getString(2);
                    }
					rs.close();

                    quer="select printpagerate from variables";
                    rs = stmt.executeQuery(quer);
                    if(rs.next())
                    {
      	              printpagerate=rs.getFloat(1);
                    }
					rs.close();
prndue=0;
libfine=0;
//					quer="select sum(noofpages) from printouts where rollno="+rollno+" and date>='"+fdate+"' and date <='"+tdate+"' and printedorregistered=1";
					quer="select sum(noofpages) from printouts where rollno="+rollno+" and printedorregistered=1";
                    rs = stmt.executeQuery(quer);
					if(rs.next())
						{
			//				if (rs.getString(1)!=null )
			//				{
								prndue1=rs.getInt(1);
								
  	     //                   prndue1 =Integer.parseInt(rs.getString(1));
							prndue=((float)prndue1)*printpagerate;
			//			    }
						}
					rs.close();
//					quer="select sum(fine) from library.dbo.libfine where rollno='"+rollno+"' and date>='"+fdate+"' and date <='"+tdate+"'";
					quer="select sum(fine) from library.dbo.libfine where rollno='"+rollno;
                    rs = stmt.executeQuery(quer);
					if(rs.next())
						{
			//				if (rs.getString(1)!=null )
			//				{
							libfine=rs.getFloat(1);
			//			    }
						}
					rs.close();

					stmt.close();
					con.close();
					}catch(Exception e){quer+=" "+e;}
					
						fname[headcount]="Printout Due";
						slno[headcount]=2004;
						fine[headcount]=prndue;
						headcount++;
					    totfine+=prndue;

						fname[headcount]="Library Fine";
						slno[headcount]=2005;
						fine[headcount]=libfine;
						headcount++;
					    totfine+=libfine;
					
quer+="prndue1="+prndue1+"prndue="+prndue+"totfine="+totfine+"libfine="+libfine;
						return quer;
			}

		public void Payment()
			{
				try
					{
					Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
					Connection con = DriverManager.getConnection("jdbc:odbc:xavier","sxcce","tamil5india6");
					Statement stmt = con.createStatement();
					for(int i=0;i<headcount;i++)
					{
					ResultSet rs = stmt.executeQuery("select sum(amount) from payment,fineidentify where payment.rollno="+rollno+" and fineidentify.finestrno=payment.finestrno and year="+year+" and dno="+dno);
					if(rs.next())
						{
						  paid=rs.getFloat(1);
						  if(totfine-paid==0)accounts=1;
						}
					rs.close();
					}
					stmt.close();
					con.close();
					}catch(Exception e){}
			}


			public String nodue()
			   {
			   	
				try
					{
					Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
					Connection con = DriverManager.getConnection("jdbc:odbc:xavier","sxcce","tamil5india6");
					Statement stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery("select id from subjectidentify where semester="+year+" and dno="+dno+" and academicyear='"+academicyear+"'");
					if(rs.next())
						{
							id=rs.getInt(1);
						}
					rs.close();
 quer="select library,hod from nodue where rollno="+rollno+" and id="+id;
	rs = stmt.executeQuery(quer);
	if(rs.next())
	  {
	  	library=rs.getInt(1);
	  	hod=rs.getInt(2);
	  }
	rs.close();
					stmt.close();
					con.close();
					}catch(Exception e){e.toString();}
					return quer+"asdfkj";
			   }
			   

public String Select()
{
	
try
{
// DBConnectionManager connMgr;
// connMgr = DBConnectionManager.getInstance();
Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
Connection con = DriverManager.getConnection("jdbc:odbc:xavier","sxcce","tamil5india6");

//Connection con = connMgr.getConnection("xavier");
Statement stmt = con.createStatement();
quer="select (count(staff.staffid)+1) from subjecthandled,syllabus,staff where "+
"staff.staffid=subjecthandled.staffid and "+
"syllabus.id=subjecthandled.id and subjectcode=subcode "+
"and syllabus.id=(select id from subjectidentify where "+
"semester="+year+" and dno="+dno+" and academicyear='"+academicyear+"')and "+
"subcode <> 'LIB12' and subjecthandled.mainorsub='M' ";

ResultSet rs = stmt.executeQuery(quer);
if(rs.next())
{
	maxsubject=rs.getInt(1);
}
rs.close();
quer="select count(rollno) from feedbackenteredornot where rollno="+rollno+" and id="+id;

rs = stmt.executeQuery(quer);
while(rs.next())
{
	feedbackentered=rs.getInt(1);
}
rs.close();
stmt.close();
con.close();
}catch(Exception e){return e.toString();}
return quer;

}

			public void sethod(int hod)
			{
				this.hod=hod;
			}
			public int gethod()
			{
				return this.hod;
			}


			public void setfeedbackentered(int feedbackentered)
			{
				this.feedbackentered=feedbackentered;
			}
			public int getfeedbackentered()
			{
				return this.feedbackentered;
			}


			public void setmaxsubject(int maxsubject)
			{
				this.maxsubject=maxsubject;
			}
			public int getmaxsubject()
			{
				return this.maxsubject;
			}

			public void setlibrary(int library)
			{
				this.library=library;
			}
			public int getlibrary()
			{
				return this.library;
			}

			public void setaccounts(int accounts)
			{
				this.accounts=accounts;
			}
			public int getaccounts()
			{
				return this.accounts;
			}

			public void setid(int id)
			{
				this.id=id;
			}
			public int getid()
			{
				return this.id;
			}



			public String getName()
			{
				return name;
			}
			public int getHeadcount()
			{
				return headcount;
			}
			public float[] getFine()
			{
				return fine;
			}
			public float getTotfine()
			{
				return totfine;
			}
			public String[] getFname()
			{
				return fname;
			}
			public void setRollno(int rollno)
			{
				this.rollno=rollno;
			}
			public float getPaid()
			{
				return this.paid;
			}
			public float getPrnDue()
			{
				return this.prndue;
			}
	}
	