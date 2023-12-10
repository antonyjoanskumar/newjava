package sxcce;
import java.sql.*;
public class FineClassView
	{
	int count=0,headcount=0;
	int dno=0;
	int year=0;
				int yyear=0;
	String academicyear="";
	int rollno[] = new int[200];
	String name[]= new String[200];
	float fine[][] = new float[200][50];
	float totfine[] = new float[200];
	int slno[] = new int[50];
	String quer="";
	String sex[] = new String[200];
	String fname[] = new String[50];
	float paid[] = new float[200];
	int library[] = new int[200];
	int hod[] = new int[200];
	float balance[] = new float[200];
	int accounts[] = new int[200];
	int id=0;
	float printpagerate=0;
	float prndue =0;
	float libfine =0;
	int prndue1 =0;
	float libdue =0;
    String fdate="";
    String tdate="";
    int j=0;
String feesslno="0";
String feesindividualslno="0";
		public int[] getRollnoAll()
			{
				count=0;
				try
					{
					Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
					Connection con = DriverManager.getConnection("jdbc:odbc:xavier","sxcce","tamil5india6");
					Statement stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery("select stud.rollno,stud.name,sex from stud,student where stud.rollno=student.rollno and active=1 and academicyear in(select academicyear from academicyear) order by stud.rollno");
					while(rs.next())
						{
						rollno[count]=rs.getInt(1);
						name[count]=rs.getString(2);
						sex[count]=rs.getString(3);
						count++;	
						}
					rs.close();
					stmt.close();
					con.close();
					}catch(Exception e){}
				return rollno;
			}



		public int[] getRollno()
			{
				count=0;
				if(year<3)yyear=1;
				else if(year<5)yyear=2;
				else if(year<7)yyear=3;
				else yyear=4;
				try
					{
					Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
					Connection con = DriverManager.getConnection("jdbc:odbc:xavier","sxcce","tamil5india6");
					Statement stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery("select stud.rollno,stud.name,sex from stud,student where stud.rollno=student.rollno and department="+dno+" and year="+yyear+" and active=1 and academicyear='"+academicyear+"' order by stud.rollno");
					while(rs.next())
						{
						rollno[count]=rs.getInt(1);
						name[count]=rs.getString(2);
						sex[count]=rs.getString(3);
						count++;	
						}
					rs.close();
					stmt.close();
					con.close();
					}catch(Exception e){}
				return rollno;
			}
			
		public int[] OneRollno(int rno)
			{
				count=0;
				try
					{
					Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
					Connection con = DriverManager.getConnection("jdbc:odbc:xavier","sxcce","tamil5india6");
					Statement stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery("select stud.rollno,stud.name,sex,year,department,academicyear from stud,student where stud.rollno=student.rollno and academicyear in(select academicyear from academicyear) and stud.rollno="+rno);
					while(rs.next())
						{
						rollno[count]=rs.getInt(1);
						name[count]=rs.getString(2);
						sex[count]=rs.getString(3);
						yyear=rs.getInt(4);
						dno=rs.getInt(5);
						academicyear=rs.getString(6);
						count++;	
						}
					year=yyear;
					rs.close();
					stmt.close();
					con.close();
					}catch(Exception e){}
				return rollno;
			}

		public void SelectHeads()
			{
				headcount=0;
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
				for(int i=0;i<count;i++)
				  {totfine[i]=0;
				  	for(int j=0;j<headcount;j++)
				  	   {
				  	   	fine[i][j]=0;
				  	   }
				  }
				try
					{
					Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
					Connection con = DriverManager.getConnection("jdbc:odbc:xavier","sxcce","tamil5india6");
					Statement stmt = con.createStatement();
					for(int i=0;i<headcount;i++)
					{
					ResultSet rs = stmt.executeQuery("select amount,boysorgirls from commonfine where slno="+slno[i]);
					while(rs.next())
						{
						float temp=rs.getFloat(1);
						int temp1=rs.getInt(2);
						for(int j=0;j<count;j++)
						  {
						  	if(sex[j].equals("Male") && temp1==1)
						  		fine[j][i]+=temp;
						  	else if(sex[j].equals("Female") && temp1==2)
						  	    fine[j][i]+=temp;
						  	else if(temp1==0)
						  	   fine[j][i]+=temp;      
							}
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
					for(int i=0;i<count;i++)
					for(int j=0;j<headcount;j++)
					{
					ResultSet rs = stmt.executeQuery("select amount from individualfine where slno="+slno[j]+" and rollno="+rollno[i]);
					if(rs.next())
						{
							fine[i][j]+=rs.getFloat(1);
						}
					rs.close();
					}
					stmt.close();
					con.close();
					}catch(Exception e){}
				for(int i=0;i<count;i++)
				  for(int j=0;j<headcount;j++)
				     {
				     	totfine[i]+=fine[i][j];
				     }	
			}

		public String NewFine()
			{
				try
					{
					Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
					Connection con = DriverManager.getConnection("jdbc:odbc:xavier","sxcce","tamil5india6");
					Statement stmt = con.createStatement();
					ResultSet rs;
						fname[headcount]="Fine or Due";
						slno[headcount]=2006;
     			for(int i=0;i<count;i++)
	     			{


					rs= stmt.executeQuery("SELECT     sum(amount) FROM accounts.dbo.dues WHERE rollno ="+rollno[i]);
					if(rs.next())
						{
						fine[i][headcount]+=rs.getFloat(1);
						}
					rs.close();
					
					rs= stmt.executeQuery("SELECT     sum(amount) FROM accounts.dbo.busfee WHERE rollno ="+rollno[i]);
					if(rs.next())
						{
						fine[i][headcount]+=rs.getFloat(1);
//					    totfine[i]+=fine[i][headcount];
						}
					rs.close();
					
					rs= stmt.executeQuery("SELECT     sum(amount) FROM accounts.dbo.examfee WHERE rollno ="+rollno[i]);
					if(rs.next())
						{
						fine[i][headcount]+=rs.getFloat(1);
//					    totfine[i]+=fine[i][headcount];
						}
					rs.close();
					
					rs= stmt.executeQuery("SELECT     sum(amount) FROM accounts.dbo.revalfee WHERE rollno ="+rollno[i]);
					if(rs.next())
						{
						fine[i][headcount]+=rs.getFloat(1);
//					    totfine[i]+=fine[i][headcount];
						}
					rs.close();
					    totfine[i]+=fine[i][headcount];

				    }
					headcount++;
					stmt.close();
					con.close();
					}catch(Exception e){return "err="+e.toString();}
					return("suc");
			}

		public String NewFineTmp()
			{
				try
					{
					Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
					Connection con = DriverManager.getConnection("jdbc:odbc:xavier","sxcce","tamil5india6");
					Statement stmt = con.createStatement();
					ResultSet rs;
						fname[headcount]="Fine or Due";
						slno[headcount]=2006;
     			for(int i=0;i<count;i++)
	     			{


					rs= stmt.executeQuery("SELECT     sum(amount) FROM accounts.dbo.dues WHERE rollno ="+rollno[i]);
					if(rs.next())
						{
						fine[i][headcount]+=rs.getFloat(1);
						}
					rs.close();
					
//					rs= stmt.executeQuery("SELECT sum(amount) FROM accounts.dbo.busfee WHERE rollno ="+rollno[i]+" and substring(reason,1,4) not in('2022')");

					rs= stmt.executeQuery("SELECT     sum(amount) FROM accounts.dbo.busfee WHERE rollno ="+rollno[i]+" ");
					if(rs.next())
						{
						fine[i][headcount]+=rs.getFloat(1);
//					    totfine[i]+=fine[i][headcount];
						}
					rs.close();
					
					rs= stmt.executeQuery("SELECT     sum(amount) FROM accounts.dbo.examfee WHERE rollno ="+rollno[i]);
					if(rs.next())
						{
						fine[i][headcount]+=rs.getFloat(1);
//					    totfine[i]+=fine[i][headcount];
						}
					rs.close();
					
					rs= stmt.executeQuery("SELECT     sum(amount) FROM accounts.dbo.revalfee WHERE rollno ="+rollno[i]);
					if(rs.next())
						{
						fine[i][headcount]+=rs.getFloat(1);
//					    totfine[i]+=fine[i][headcount];
						}
					rs.close();
					    totfine[i]+=fine[i][headcount];

				    }
					headcount++;
					stmt.close();
					con.close();
					}catch(Exception e){return "err="+e.toString();}
					return("suc");
			}


		public String NewFineTmp1()
			{
				try
					{
					Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
					Connection con = DriverManager.getConnection("jdbc:odbc:xavier","sxcce","tamil5india6");
					Statement stmt = con.createStatement();
					ResultSet rs;
						fname[headcount]="Fine or Due";
						slno[headcount]=2006;
     			for(int i=0;i<count;i++)
	     			{


					rs= stmt.executeQuery("SELECT     sum(amount) FROM accounts.dbo.dues WHERE rollno ="+rollno[i]);
					if(rs.next())
						{
						fine[i][headcount]+=rs.getFloat(1);
						}
					rs.close();
					
					rs= stmt.executeQuery("SELECT sum(amount) FROM accounts.dbo.busfee WHERE rollno ="+rollno[i]+" and substring(reason,1,4) not in('2023')");

//					rs= stmt.executeQuery("SELECT     sum(amount) FROM accounts.dbo.busfee WHERE rollno ="+rollno[i]+" ");
					if(rs.next())
						{
						fine[i][headcount]+=rs.getFloat(1);
//					    totfine[i]+=fine[i][headcount];
						}
					rs.close();
					
					rs= stmt.executeQuery("SELECT     sum(amount) FROM accounts.dbo.examfee WHERE rollno ="+rollno[i]);
					if(rs.next())
						{
						fine[i][headcount]+=rs.getFloat(1);
//					    totfine[i]+=fine[i][headcount];
						}
					rs.close();
					
					rs= stmt.executeQuery("SELECT     sum(amount) FROM accounts.dbo.revalfee WHERE rollno ="+rollno[i]);
					if(rs.next())
						{
						fine[i][headcount]+=rs.getFloat(1);
//					    totfine[i]+=fine[i][headcount];
						}
					rs.close();
					    totfine[i]+=fine[i][headcount];

				    }
					headcount++;
					stmt.close();
					con.close();
					}catch(Exception e){return "err="+e.toString();}
					return("suc");
			}




public void FeeFine()
			{
			int f=0;
				try
					{
					Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
					Connection con = DriverManager.getConnection("jdbc:odbc:xavier","sxcce","tamil5india6");
					Statement stmt = con.createStatement();
					ResultSet rs;

   			for(j=0;j<count;j++)
			{
  			 feesslno="0";
			feesindividualslno="0";

  			 f=Viewfees();


quer="select sum(amount) from accounts.dbo.feesstructureamount where fsino="+feesslno;

if(yyear==1) quer="select sum(amount) from accounts.dbo.feesstructureamount where fsino="+feesslno+" and fshno not in(140,155,156,157,159,160,161,1010,1002,1006,1011,1003,1007,1012,1004,1008,1014,1015,1016,117,151,120,147,123,126,129,118,152,121,148,124,127,131,119,153,122,149,125,128,130)";

if(yyear==2) quer="select sum(amount) from accounts.dbo.feesstructureamount where fsino="+feesslno+" and fshno not in(140,156,157,160,161,1011,1003,1007,1012,1004,1008,1015,1016,118,152,121,148,124,127,131,119,153,122,149,125,128,130)";

if(yyear==3) quer="select sum(amount) from accounts.dbo.feesstructureamount where fsino="+feesslno+"  and fshno not in(140,157,161,1012,1004,1008,1016,119,153,122,149,125,128,130)";

if(yyear==4) quer="select sum(amount) from accounts.dbo.feesstructureamount where fsino="+feesslno;


rs = stmt.executeQuery(quer);
if(rs.next())
{
fine[j][headcount]=rs.getFloat(1);
}

rs.close();


quer="SELECT     SUM(amount) FROM accounts.dbo.feesstructureindividualamount,accounts.dbo.feesstructureindividualidentify WHERE   fsiino=slno and rollno="+rollno[j];



if(yyear==1) quer="SELECT     SUM(amount) FROM accounts.dbo.feesstructureindividualamount,accounts.dbo.feesstructureindividualidentify WHERE   fsiino=slno and rollno="+rollno[j]+" and fshno not in(140,155,156,157,159,160,161,1010,1002,1006,1011,1003,1007,1012,1004,1008,1014,1015,1016,117,151,120,147,123,126,129,118,152,121,148,124,127,131,119,153,122,149,125,128,130)";

//if(yyear==1) quer="SELECT     SUM(amount) FROM accounts.dbo.feesstructureindividualamount,accounts.dbo.feesstructureindividualidentify WHERE   fsiino=slno and rollno="+rollno[j]+" and fshno not in(117,151,120,147,123,126,129,118,152,121,148,124,127,131,119,153,122,149,125,128,130)";

if(yyear==2) quer="SELECT     SUM(amount) FROM accounts.dbo.feesstructureindividualamount,accounts.dbo.feesstructureindividualidentify WHERE   fsiino=slno and rollno="+rollno[j]+" and fshno not in(140,156,157,160,161,1011,1003,1007,1012,1004,1008,1015,1016,118,152,121,148,124,127,131,119,153,122,149,125,128,130)";

//if(yyear==2) quer="SELECT     SUM(amount) FROM accounts.dbo.feesstructureindividualamount,accounts.dbo.feesstructureindividualidentify WHERE   fsiino=slno and rollno="+rollno[j]+" and fshno not in(118,152,121,148,124,127,131,119,153,122,149,125,128,130)";

if(yyear==3) quer="SELECT     SUM(amount) FROM accounts.dbo.feesstructureindividualamount,accounts.dbo.feesstructureindividualidentify WHERE   fsiino=slno and rollno="+rollno[j]+" and fshno not in(140,157,161,1012,1004,1008,1016,119,153,122,149,125,128,130)";

//if(yyear==3) quer="SELECT     SUM(amount) FROM accounts.dbo.feesstructureindividualamount,accounts.dbo.feesstructureindividualidentify WHERE   fsiino=slno and rollno="+rollno[j]+" and fshno not in(119,153,122,149,125,128,130)";

if(yyear==4) quer="SELECT     SUM(amount) FROM accounts.dbo.feesstructureindividualamount,accounts.dbo.feesstructureindividualidentify WHERE   fsiino=slno and rollno="+rollno[j];


//quer="SELECT     SUM(amount) FROM accounts.dbo.feesstructureindividualamount,accounts.dbo.feesstructureindividualidentify WHERE   fsiino=slno and rollno="+rollno[j];
//quer="select sum(amount) from accounts.dbo.feesstructureindividualamount "+
//" where fsiino="+feesindividualslno;
rs = stmt.executeQuery(quer);
if(rs.next())
{
fine[j][headcount]+=rs.getFloat(1);

}
rs.close();

//			slno[headcount]=2003;
		    totfine[j]+=fine[j][headcount];

//quer="select sum(amount) from accounts.dbo.payment where head<>999 and rollno="+rollno[j];
quer="select sum(amount) from accounts.dbo.payment where rollno="+rollno[j];
rs = stmt.executeQuery(quer);
if(rs.next())
{
	paid[j]=rs.getFloat(1);
}
rs.close();
/*
quer="select sum(amount) from accounts.dbo.payment where head=999 and rollno="+rollno[j];
rs = stmt.executeQuery(quer);
if(rs.next())
{
	paid[j]=paid[j]-rs.getFloat(1);
}
rs.close();
*/
quer="select sum(amount) from accounts.dbo.refund where rollno="+rollno[j];
rs = stmt.executeQuery(quer);
if(rs.next())
{
	paid[j]=paid[j]-rs.getFloat(1);
}
rs.close();


}
			fname[headcount]="Fees Balance";
			headcount++;
			
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
quer="select fslno from accounts.dbo.feeassign where rollno="+rollno[j];
rs = stmt.executeQuery(quer);
if(rs.next())
{
feesslno=rs.getString(1);
found=1;
}
rs.close();

quer="select slno from accounts.dbo.feesstructureindividualidentify where rollno="+rollno[j];
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

					for(int i=0;i<count;i++)
					{
					quer="select sum(noofpages) from printouts where rollno="+rollno[i]+" and printedorregistered=1";
                    rs = stmt.executeQuery(quer);
                     prndue=0;
					if(rs.next())
						{
//							if(rs.getString(1)!=null)
//							{
  	                        prndue1 =rs.getInt(1);
//  	                        prndue1 =Integer.parseInt(rs.getString(1));
							prndue=((float)prndue1)*3;
//							prndue=((float)prndue1)*printpagerate;
						}
//						}
 					 rs.close();
					fine[i][headcount]+=prndue;
					totfine[i]+=prndue;
	                }

						fname[headcount]="Printout Due";
						slno[headcount]=2004;
	          		headcount++;					


					for(int i=0;i<count;i++)
					{
					quer="select sum(fine) from library.dbo.libfine where rollno='"+rollno[i]+"'";
                    rs = stmt.executeQuery(quer);
					if(rs.next())
						{
							libfine=rs.getFloat(1);
						}
				
					
 					 rs.close();
					fine[i][headcount]+=libfine;
					totfine[i]+=libfine;
	                }
						fname[headcount]="Library Fine";
						slno[headcount]=2005;
	          		headcount++;					



					stmt.close();
					con.close();

					}catch(Exception e){quer+="ok"+e;}

quer+="fdate="+fdate+"prndue1="+prndue1+"prndue="+prndue+"totfine="+totfine+"printpagerate="+printpagerate;
						return quer;
			}

			
		public void Payment()
			{
				try
					{
					Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
					Connection con = DriverManager.getConnection("jdbc:odbc:xavier","sxcce","tamil5india6");
					Statement stmt = con.createStatement();
					for(int i=0;i<count;i++)
					{
//					ResultSet rs = stmt.executeQuery("select sum(amount) from payment,fineidentify where payment.rollno="+rollno[i]+" and fineidentify.finestrno=payment.finestrno and year="+year+" and dno="+dno+" and academicyear='"+academicyear+"'");
					ResultSet rs = stmt.executeQuery("select sum(amount) from payment where payment.rollno="+rollno[i]);
					accounts[i]=0;
//					if(rs.next())
						{
//							paid[i]=rs.getFloat(1);
							balance[i]=totfine[i]-paid[i];
							if(balance[i]<=0)accounts[i]=1;else accounts[i]=0;
						}
					rs.close();
					}
					stmt.close();
					con.close();
					}catch(Exception e){}
			}
			public String nodue()
			   {
			   	String quer="";
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
					for(int i=0;i<count;i++)
					{
 quer="select library,hod from nodue where rollno="+rollno[i]+" and id="+id;
	rs = stmt.executeQuery(quer);
	if(rs.next())
	  {
	  	library[i]=rs.getInt(1);
	  	hod[i]=rs.getInt(2);
	  }
	rs.close();
					}
					stmt.close();
					con.close();
					}catch(Exception e){e.toString();}
					return quer+"asdfkj";
			   }
			
			public void sethod(int hod[])
			{
				this.hod=hod;
			}
			public int[] gethod()
			{
				return this.hod;
			}
			
			public void setaccounts(int accounts[])
			{
				this.accounts=accounts;
			}
			public int[] getaccounts()
			{
				return this.accounts;
			}
			
            public float[] getbalance()
            {
            	return this.balance;
            }
			public void setlibrary(int library[])
			{
				this.library=library;
			}
			public int[] getlibrary()
			{
				return this.library;
			}


			public String[] getName()
			{
				return name;
			}
			public int getCount()
			{
				return count;
			}
			public int getHeadcount()
			{
				return headcount;
			}
			public float[][] getFine()
			{
				return fine;
			}
			public float[] getTotfine()
			{
				return totfine;
			}
			public String[] getFname()
			{
				return fname;
			}
			public void setDno(int dno)
			{
				this.dno=dno;	
			}
			public void setYear(int year)
			{
				this.year=year;	
			}
			public void setAcademicyear(String academicyear)
			{
				this.academicyear=academicyear;
			}
			public int getYear()
			{
				return this.year;
			}
			public int getDno()
			{
				return this.dno;
			}
			public float[] getPaid()
			{
				return this.paid;
			}
			
			public int getid()
			{
				return this.id;
			}
	}