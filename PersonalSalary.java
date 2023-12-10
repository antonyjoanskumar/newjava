package sxcce;
import java.sql.*;

public class PersonalSalary
	{
		int daper=6;
//		int daper=0;
//		int daper=12;
//		int daper=40;
		int esiwages=21000;
//		float esiper=1.75f;
//		float esieper=4.75f;
		float esiper=0.75f;
		float esieper=3.25f;
		String staffid[] = new String[5000];
		int consoldate[] = new int[5000];
		int dayslossofpay[] = new int[5000];
		String accountno[] = new String[5000];
		String bank[] = new String[5000];
		float basic[] = new float[5000];
		float ma[] = new float[5000];
				float bagp=0;
		int scsaper[] = new int[5000];
		float scsa[] = new float[5000];
		int splallow[] = new int[5000];

		float others[] = new float[5000];
		float pp[] = new float[5000];
		float rd[] = new float[5000];
		float lic[] = new float[5000];
		float loan[] = new float[5000];
		float pfper[] = new float[5000];
		float fa[] = new float[5000];
		float esi[] = new float[5000];
		float esie[] = new float[5000];
		float society[] = new float[5000];
		float club[] = new float[5000];
		float spclub[] = new float[5000];
		float saladvance[] = new float[5000];
		float telephone[] = new float[5000];
		float cooptex[] = new float[5000];
		float khadi[] = new float[5000];
		float protax[] = new float[5000];
		float incometax[] = new float[5000];
		float processfee[] = new float[5000];
		float others1[] = new float[5000];
		float loss[] = new float[5000];
		int count=0;
		float da[] = new float[5000];
		float hra[] = new float[5000];
		float gtot[] = new float[5000];
		float percentage5[] = new float[5000];
		float pf[] = new float[5000];
		float netpay[] = new float[5000];
		String scale[] = new String[5000];
		String name[] = new String[5000];
		String basicqual[] = new String[5000];
		String desiredqual[] = new String[5000];
		String researchqual[] = new String[5000];
		String addqual[] = new String[5000];
		String desiredquala[] = new String[5000];
		String researchquala[] = new String[5000];
		String addquala[] = new String[5000];
		Date dob[] = new Date[5000];
		Date appdate[] = new Date[5000];
		String designation[] = new String[5000];
		String dname[] = new String[5000];
		String staffids="";
		int mondays=31;
				String vmonth="";
		String vacademicyear=""; 

   float ff(float num)
      {
      if(num-(float)(int)(num)>.49)
         return((int)(num+1));
      else return((float)(int)num);
      }
		public String View(String month,String academicyear,int todayear)
		    {
		    	vmonth=month;
		    	vacademicyear=academicyear;

		    	String days31[]={"JAN","MAR","MAY","JUL","AUG","OCT","DEC"};
		    	String days30[]={"APR","JUN","SEP","NOV"};
		    	if(month.equals("FEB"))
		    	   {
		    	   	if(todayear%4==0)
		    	   	   mondays=29;
		    	   	else mondays=28;
		    	   }
				        for(int i=0;i<7;i++)
		           if(days31[i].equals(month))mondays=31;   
		        for(int i=0;i<4;i++)
		           if(days30[i].equals(month))mondays=30;   
String ssalary="select staff.staffid,consoldate,dayslossofpay,accountno,bank,basic,ma,others,pp,"+
		"rd,lic,loan,pfper,fa,society,club,spclub,saladvance,telephone,cooptex,khadi,"+
		"protax,incometax,processfee,others1,salarycategory.cname,name,basicqual,desiredqual,"+
		"researchqual,addqual,desiredquala,researchquala,addquala,birthdate,appdate,"+
		"staffdesignation.cname,department.dname,scsa,splallow from staffdesignation,department,salary,"+
		"staff,salarycategory where salary.staffid=staff.staffid and staff.staffid='"+staffids+"' and "+
		"staff.designation=staffdesignation.slno and staff.dno=department.dno "+
		"and salary.scale=salarycategory.slno and salary.dayslossofpay<> "+mondays+" and salary.month='"+month+"' and salary.academicyear='"+academicyear+"' order by salary.accountno";
		    	try
		    	{
					Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
					Connection con = DriverManager.getConnection("jdbc:odbc:xavier","sxcce","tamil5india6");
					Statement stmt = con.createStatement();
					ResultSet rs;
					rs = stmt.executeQuery(ssalary);
					while(rs.next())
						{
						staffid[count]=rs.getString(1);
						consoldate[count] = rs.getInt(2);
						dayslossofpay[count]=rs.getInt(3);
						accountno[count]=rs.getString(4);
						bank[count]=rs.getString(5);
						basic[count]=rs.getFloat(6);
						ma[count]=rs.getFloat(7);
						others[count]=rs.getFloat(8);
						pp[count]=rs.getFloat(9);
						rd[count]=rs.getFloat(10);
						lic[count]=rs.getFloat(11);
						loan[count]=rs.getFloat(12);
						pfper[count]=rs.getFloat(13);
						fa[count]=rs.getFloat(14);
						society[count]=rs.getFloat(15);
						club[count]=rs.getFloat(16);
						spclub[count]=rs.getFloat(17);
						saladvance[count]=rs.getFloat(18);
						telephone[count]=rs.getFloat(19);
						cooptex[count]=rs.getFloat(20);
						khadi[count]=rs.getFloat(21);
						protax[count]=rs.getFloat(22);
						incometax[count]=rs.getFloat(23);
						processfee[count]=rs.getFloat(24);
						others1[count]=rs.getFloat(25);
						
						scale[count]=rs.getString(26);
						name[count]=rs.getString(27);
						basicqual[count]=rs.getString(28);
						desiredqual[count]=rs.getString(29);
						researchqual[count]=rs.getString(30);
						addqual[count]=rs.getString(31);
						desiredquala[count]=rs.getString(32);
						researchquala[count]=rs.getString(33);
						addquala[count]=rs.getString(34);
						dob[count]=rs.getDate(35);
						appdate[count]=rs.getDate(36);
						designation[count]=rs.getString(37);
						dname[count]=rs.getString(38);
						scsaper[count]=rs.getInt(39);
						splallow[count]=rs.getInt(40);
						count++;	
						}
rs.close();
ssalary="select status from salenable where month='"+month+"' and academicyear='"+academicyear+"'";
					rs = stmt.executeQuery(ssalary);
					if(rs.next())
{
		if(rs.getInt(1)==0)
		{
 	  count=0;
		}

}
else
{
	count=0;
}
					rs.close();
					stmt.close();
					con.close();
		    	}catch(Exception e){return e.toString();}
		    	return "succ";
		    }
		public void init()
		{
			for(int i=0;i<1000;i++)
			  {
			   da[i]=0;
			   hra[i]=0;
			   gtot[i]=0;
			   percentage5[i]=0;
			   netpay[i]=0;
			   pf[i]=0;
			   esi[i]=0;
   			   esie[i]=0;

			  }
		}
		public void calculate()
		{

				if(vacademicyear.equals("2023 -- 2024"))		
				{  daper=6; esiwages=21000;	esiper=0.75f; esieper=3.25f;}

				if(vacademicyear.equals("2022 -- 2023"))		
				{  daper=0; esiwages=21000;	esiper=0.75f; esieper=3.25f;}

				if(vacademicyear.equals("2021 -- 2022"))		
				{  daper=0; esiwages=21000;	esiper=0.75f; esieper=3.25f;}
		
				if(vacademicyear.equals("2020 -- 2021"))		
				{  daper=12; esiwages=21000;	esiper=0.75f; esieper=3.25f;}

				if(vacademicyear.equals("2019 -- 2020"))		
				{  daper=12; esiwages=21000;	esiper=0.75f; esieper=3.25f;}

				if(vacademicyear.equals("2018 -- 2019"))		
				{  daper=40; esiwages=21000;	esiper=1.75f; esieper=4.75f;}
	
				if(vacademicyear.equals("2017 -- 2018"))		
				{  daper=40; esiwages=21000; 	esiper=1.75f; esieper=4.75f;}

				if(vacademicyear.equals("2017 -- 2018"))		
				{  daper=40; esiwages=21000;}

				if(vacademicyear.equals("2016 -- 2017"))		
				{  daper=40; esiwages=15000; }

				if(vacademicyear.equals("2015 -- 2016"))		
				{  daper=30; esiwages=15000;}

				if(vacademicyear.equals("2014 -- 2015"))		
				{  daper=30; esiper=0;}

				if(vacademicyear.equals("2013 -- 2014"))		
				{  daper=30; esiper=0;}

				if(vacademicyear.equals("2012 -- 2013"))		
				{  daper=20; esiper=0;}

				if(vacademicyear.equals("2011 -- 2012"))		
				{  daper=10; esiper=0;}

				if(vacademicyear.equals("2010 -- 2011"))		
				{  daper=0; esiper=0;}

				if(vacademicyear.equals("2009 -- 2010"))		
				{  daper=97; esiper=0;}

				if(vacademicyear.equals("2008 -- 2009"))		
				{  daper=97; esiper=0;}

				if(vacademicyear.equals("2007 -- 2008"))		
				{  daper=50; esiper=0;}

				if(vacademicyear.equals("2006 -- 2007"))		
				{  daper=40; esiper=0;}

				if(vacademicyear.equals("2005 -- 2006"))		
				{  daper=38; esiper=0;}

				if(vacademicyear.equals("2004 -- 2005"))		
				{  daper=38; esiper=0;}

				if(vacademicyear.equals("2003 -- 2004"))		
				{  daper=38; esiper=0;}

				if(vacademicyear.equals("2002 -- 2003"))		
				{  daper=38; esiper=0;}

				if(vacademicyear.equals("2001 -- 2002"))		
				{  daper=38; esiper=0;}

				if(((vmonth.equals("JAN")) || (vmonth.equals("FEB")) || (vmonth.equals("MAR"))) && (vacademicyear.equals("2005 -- 2006")))		
				{  daper=40; esiper=0;}

				if(((vmonth.equals("APR")) || (vmonth.equals("MAY")) || (vmonth.equals("JUN"))) && (vacademicyear.equals("2007 -- 2008")))		
				{  daper=40; esiper=0;}

				if(((vmonth.equals("APR")) || (vmonth.equals("MAY")) || (vmonth.equals("JUN"))) && (vacademicyear.equals("2008 -- 2009")))		
				{  daper=50; esiper=0;}

				if(((vmonth.equals("JUL")) || (vmonth.equals("AUG")) ) && (vacademicyear.equals("2008 -- 2009")))		
				{  daper=60; esiper=0;}

				if(((vmonth.equals("APR")) || (vmonth.equals("APR"))) && (vacademicyear.equals("2010 -- 2011")))		
				{  daper=97; esiper=0;}

				if(((vmonth.equals("APR")) || (vmonth.equals("MAY")) || (vmonth.equals("JUN")) || (vmonth.equals("JUL")) || (vmonth.equals("AUG")) || (vmonth.equals("SEP"))) && (vacademicyear.equals("2011 -- 2012")))		
				{  daper=0; esiper=0;}

				if(((vmonth.equals("APR")) || (vmonth.equals("MAY")) || (vmonth.equals("JUN")) || (vmonth.equals("JUL")) || (vmonth.equals("AUG"))) && (vacademicyear.equals("2012 -- 2013")))		
				{  daper=10; esiper=0;}

				if(((vmonth.equals("APR")) || (vmonth.equals("MAY")) || (vmonth.equals("JUN")) || (vmonth.equals("JUL")) || (vmonth.equals("AUG")) || (vmonth.equals("SEP"))) && (vacademicyear.equals("2013 -- 2014")))		
				{  daper=20; esiper=0;}


				if(((vmonth.equals("APR")) || (vmonth.equals("MAY")) || (vmonth.equals("JUN"))) && (vacademicyear.equals("2016 -- 2017")))		
				{  daper=30;}


				if(((vmonth.equals("APR")) || (vmonth.equals("MAY")) || (vmonth.equals("JUN"))) && (vacademicyear.equals("2017 -- 2018")))		
				{ daper=40; esiwages=15000; 	esiper=1.75f; esieper=4.75f;}
				
				if(((vmonth.equals("APR")) || (vmonth.equals("MAY")) || (vmonth.equals("JUN"))  || (vmonth.equals("JUL"))) && (vacademicyear.equals("2019 -- 2020")))		
				{ daper=40; esiwages=21000; 	esiper=1.75f; esieper=4.75f;}

				if ((vmonth.equals("AUG")) && (vacademicyear.equals("2019 -- 2020")))		
				{ daper=40; esiwages=21000; 		esiper=0.75f; esieper=3.25f;}

				if(((vmonth.equals("JAN")) || (vmonth.equals("FEB")) || (vmonth.equals("MAR"))) && (vacademicyear.equals("2020 -- 2021")))		
				{ daper=0; esiwages=21000; 		esiper=0.75f; esieper=3.25f;}

				if(((vmonth.equals("APR")) || (vmonth.equals("MAY")) ) && (vacademicyear.equals("2023 -- 2024")))		
				{ daper=0; esiwages=21000; 		esiper=0.75f; esieper=3.25f;}
				
			for(int i=0;i<count;i++)
			{
			  	if(consoldate[i]>0){

if((vacademicyear.equals("2009 -- 2010"))	|| 	(vacademicyear.equals("2008 -- 2009")) || (vacademicyear.equals("2007 -- 2008")) || (vacademicyear.equals("2006 -- 2007")) || (vacademicyear.equals("2005 -- 2006")) || (vacademicyear.equals("2004 -- 2005")) || (vacademicyear.equals("2003 -- 2004")) || (((vmonth.equals("APR")) || (vmonth.equals("APR"))) && (vacademicyear.equals("2010 -- 2011"))))
{
				if(basic[i]>0 && basic[i]<2850)hra[i]=120;
				else if(basic[i]>2849 && basic[i]<3600)hra[i]=150;
				else if(basic[i]>3599 && basic[i]<4400)hra[i]=180;
				else if(basic[i]>4399 && basic[i]<5000)hra[i]=220;
				else if(basic[i]>4999 && basic[i]<5700)hra[i]=270;
				else if(basic[i]>5699 && basic[i]<6400)hra[i]=320;
				else if(basic[i]>6399 && basic[i]<7400)hra[i]=380;
				else if(basic[i]>7399 && basic[i]<7800)hra[i]=440;
				else if(basic[i]>7799 && basic[i]<8600)hra[i]=500;
				else if(basic[i]>8599 && basic[i]<9300)hra[i]=560;
				else if(basic[i]>9299 && basic[i]<10500)hra[i]=620;
				else if(basic[i]>10499 && basic[i]<10800)hra[i]=680;
				else if(basic[i]>10799)hra[i]=700;
			  		if(staffid[i].equals("TS001"))
				  		da[i]=basic[i]*40/100;
				 	else
				  		da[i]=basic[i]*daper/100;

}
else
{
bagp=basic[i]+others[i];

if( (vacademicyear.equals("2020 -- 2021")) || (vacademicyear.equals("2021 -- 2022")) || (vacademicyear.equals("2022 -- 2023")) || ((((vmonth.equals("SEP"))) || ((vmonth.equals("OCT"))) || ((vmonth.equals("NOV"))) || ((vmonth.equals("DEC"))) || ((vmonth.equals("JAN"))) || ((vmonth.equals("FEB"))) || ((vmonth.equals("MAR")))) && (vacademicyear.equals("2019 -- 2020"))) )
{
				if(bagp>0 && bagp<5300)hra[i]=240;
				else if(bagp>=5300 && bagp<6700)hra[i]=300;
				else if(bagp>=6700 && bagp<8190)hra[i]=360;
				else if(bagp>=8190 && bagp<9300)hra[i]=440;
				else if(bagp>=9300 && bagp<10600)hra[i]=540;
				else if(bagp>=10600 && bagp<11900)hra[i]=640;
				else if(bagp>=11900 && bagp<13770)hra[i]=760;
				else if(bagp>=13770 && bagp<14510)hra[i]=880;
				else if(bagp>=14510 && bagp<16000)hra[i]=1000;
				else if(bagp>=16000 && bagp<17300)hra[i]=1120;
				else if(bagp>=17300 && bagp<19530)hra[i]=1240;
				else if(bagp>=19530 && bagp<20090)hra[i]=1360;
				else if(bagp>=20090)hra[i]=1400;
}
else
{

				if(bagp>0 && bagp<5000)hra[i]=120;
				else if(bagp>=5000 && bagp<8000)hra[i]=150;
				else if(bagp>=8000 && bagp<9000)hra[i]=180;
				else if(bagp>=9000 && bagp<11000)hra[i]=220;
				else if(bagp>=11000 && bagp<14000)hra[i]=270;
				else if(bagp>=14000 && bagp<16000)hra[i]=320;
				else if(bagp>=16000 && bagp<17000)hra[i]=380;
				else if(bagp>=17000 && bagp<18000)hra[i]=440;
				else if(bagp>=18000 && bagp<19000)hra[i]=500;
				else if(bagp>=19000 && bagp<22000)hra[i]=560;
				else if(bagp>=22000 && bagp<23550)hra[i]=620;
//				else if(bagp>10499 && bagp<10800)hra[i]=680;
				else if(bagp>=23550)hra[i]=700;
}

if( Integer.parseInt(vacademicyear.substring(8,12))>2023  )
{
				if(bagp>0 && bagp<5300)hra[i]=240;
				else if(bagp>=5300 && bagp<6700)hra[i]=300;
				else if(bagp>=6700 && bagp<8190)hra[i]=360;
				else if(bagp>=8190 && bagp<9300)hra[i]=440;
				else if(bagp>=9300 && bagp<10600)hra[i]=540;
				else if(bagp>=10600 && bagp<11900)hra[i]=640;
				else if(bagp>=11900 && bagp<13770)hra[i]=760;
				else if(bagp>=13770 && bagp<14510)hra[i]=880;
				else if(bagp>=14510 && bagp<16000)hra[i]=1000;
				else if(bagp>=16000 && bagp<17300)hra[i]=1120;
				else if(bagp>=17300 && bagp<19530)hra[i]=1240;
				else if(bagp>=19530 && bagp<20090)hra[i]=1360;
				else if(bagp>=20090)hra[i]=1400;
}


			  		if(staffid[i].equals("TS001"))
				  		da[i]=bagp*40/100;
				 	else
				  		da[i]=bagp*daper/100;

				if(((vmonth.equals("APR")) || (vmonth.equals("MAY")) || (vmonth.equals("JUN")) || (vmonth.equals("JUL")) || (vmonth.equals("AUG")) || (vmonth.equals("SEP"))) && (vacademicyear.equals("2011 -- 2012")))
				{
		  		if(staffid[i].equals("NS092")||staffid[i].equals("NS093")||staffid[i].equals("NS094")||staffid[i].equals("NS100")||staffid[i].equals("NS101")||staffid[i].equals("NS109"))
				  		da[i]=0;
				}
				else
				{
				if(((vmonth.equals("APR")) || (vmonth.equals("MAY")) || (vmonth.equals("JUN")) || (vmonth.equals("JUL")) || (vmonth.equals("AUG"))) && (vacademicyear.equals("2012 -- 2013")))
				{
		  		if(staffid[i].equals("NS092")||staffid[i].equals("NS093")||staffid[i].equals("NS094")||staffid[i].equals("NS100")||staffid[i].equals("NS101")||staffid[i].equals("NS109"))
				  		da[i]=0;
				}
				}
				  		
				if(((vmonth.equals("APR")) || (vmonth.equals("MAY")) || (vmonth.equals("JUN")) || (vmonth.equals("JUL")) || (vmonth.equals("AUG")) || (vmonth.equals("SEP"))) && (vacademicyear.equals("2017 -- 2018")))		
				  		scsa[i]=bagp*scsaper[i]/100;
				else if (Integer.parseInt(vacademicyear.substring(8,12))<=2017)
				  		scsa[i]=bagp*scsaper[i]/100;
				else
				  		scsa[i]=scsaper[i];

}				


			}
			telephone[i]=ff(telephone[i]);
			lic[i]=ff(lic[i]);
			da[i]=ff(da[i]);
				if(consoldate[i]>0)
					gtot[i]=((basic[i]+da[i]+hra[i]+pp[i]+ma[i]+scsa[i]+splallow[i])/100*consoldate[i]);
				else
					gtot[i]=((basic[i]+da[i]+hra[i]+pp[i]+ma[i]+scsa[i]+splallow[i]));
				if(!(((vmonth.equals("MAY")) || (vmonth.equals("JUN")) || (vmonth.equals("JUL")) || (vmonth.equals("AUG"))) && (vacademicyear.equals("2010 -- 2011"))))
				gtot[i]=gtot[i]+others[i];

				loss[i]=gtot[i]-gtot[i]/mondays*(mondays-dayslossofpay[i]);	
				loss[i]=ff(loss[i]);
				gtot[i]=gtot[i]/mondays*(mondays-dayslossofpay[i]);
				if(((vmonth.equals("MAY")) || (vmonth.equals("JUN")) || (vmonth.equals("JUL")) || (vmonth.equals("AUG"))) && (vacademicyear.equals("2010 -- 2011")))
				gtot[i]=ff(gtot[i]+others[i]);
				else
				gtot[i]=ff(gtot[i]);
				pf[i]=(basic[i]+da[i]+others[i])/mondays*(mondays-dayslossofpay[i])*pfper[i]/100;

//	            if(esiper>0.0) esieper=4.75f; else esieper=0;
	            if(esiper<=0.0) esieper=0;
	            
				if((consoldate[i]>0) && ((basic[i]+da[i]+others[i])<=esiwages))
				{
				     esi[i]=(basic[i]+da[i]+others[i])/mondays*(mondays-dayslossofpay[i])*esiper/100;
				     esie[i]=(basic[i]+da[i]+others[i])/mondays*(mondays-dayslossofpay[i])*esieper/100;
				}
				else
				{
				     esi[i]=0;
				     esie[i]=0;
				}

				if(staffid[i].equals("NT060"))   // Vargheese - Driver
				{
				     esi[i]=(basic[i]+da[i]+others[i])/mondays*(mondays-dayslossofpay[i])*esiper/100;
				     esie[i]=(basic[i]+da[i]+others[i])/mondays*(mondays-dayslossofpay[i])*esieper/100;
				}
								
				if (staffid[i].equals("NS103")) { esi[i]=0;   esie[i]=0; 			}    // Justus Rajan, Civil
				if (staffid[i].equals("NS075")) { esi[i]=0;   esie[i]=0; 			}    // R. Ajitha - H&S
				if (staffid[i].equals("NS137")) { esi[i]=0;   esie[i]=0; 			}    // George Prabhagar Office
				if (staffid[i].equals("NS139")) { esi[i]=0;   esie[i]=0; 			}    // Jose Alexis - Estate Officer

//				pf[i]=(basic[i]+da[i])/mondays*(mondays-dayslossofpay[i])*pfper[i]/100;
if((vacademicyear.equals("2013 -- 2014"))	|| (vacademicyear.equals("2012 -- 2013"))	|| (vacademicyear.equals("2011 -- 2012"))	||(vacademicyear.equals("2010 -- 2011"))	||(vacademicyear.equals("2009 -- 2010"))	||	(vacademicyear.equals("2008 -- 2009")) || (vacademicyear.equals("2007 -- 2008")) || (vacademicyear.equals("2006 -- 2007")) || (vacademicyear.equals("2005 -- 2006")) || (vacademicyear.equals("2004 -- 2005")) || (vacademicyear.equals("2003 -- 2004")) || (((vmonth.equals("APR")) || (vmonth.equals("MAY"))|| (vmonth.equals("JUN"))|| (vmonth.equals("JUL"))|| (vmonth.equals("AUG"))) && (vacademicyear.equals("2014 -- 2015"))))
{
				if(pf[i]>780)pf[i]=780;
}
else
{
				if(pf[i]>1800)pf[i]=1800;
}
//-----pf change --------------
if((vacademicyear.equals("2020 -- 2021")) && ((vmonth.equals("MAY")) || (vmonth.equals("JUN"))|| (vmonth.equals("JUL")) ))
{
					if(pf[i]>1500)pf[i]=1500;
}

				pf[i]=ff(pf[i]);
				esi[i]=ff(esi[i]);
				esie[i]=ff(esie[i]);

			//		pf[i]+=rd[i];
		//		rd[i]=0;
				if(consoldate[i]!=40)
 				percentage5[i]=(float)(gtot[i]*0.5/100);

//				else percentage5[i]=(float)(gtot[i]*2/100);
//				if(staffid[i].charAt(1)=='T')percentage5[i]=(float)(gtot[i]*2/100);
//			    if(((vmonth.equals("DEC")) || (vmonth.equals("JAN")) || (vmonth.equals("FEB")) || (vmonth.equals("MAR")) || (vmonth.equals("APR")) || (vmonth.equals("MAY")) || (vmonth.equals("JUN"))) && (vacademicyear.equals("2004 -- 2005")))		
//						if(staffid[i].charAt(1)=='N')percentage5[i]=(float)(gtot[i]*2/100);
				
			else percentage5[i]=0;
				if(staffid[i].charAt(1)=='T')percentage5[i]=0;
			    if(((vmonth.equals("DEC")) || (vmonth.equals("JAN")) || (vmonth.equals("FEB")) || (vmonth.equals("MAR")) || (vmonth.equals("APR")) || (vmonth.equals("MAY")) || (vmonth.equals("JUN"))) && (vacademicyear.equals("2004 -- 2005")))		
						if(staffid[i].charAt(1)=='N')percentage5[i]=0;


			    if(((vmonth.equals("FEB")) || (vmonth.equals("MAR"))) && (vacademicyear.equals("2004 -- 2005")))		
				spclub[i]=(float)(gtot[i]*2/100);

			    if((vmonth.equals("APR")) && (vacademicyear.equals("2005 -- 2006")))		
				{
				spclub[i]=(float)(gtot[i]*2/100);
				if (staffid[i].equals("TS015")) spclub[i]=0;
				if (staffid[i].equals("NS009")) spclub[i]=0;
				if (staffid[i].equals("NS010")) spclub[i]=0;
				if (staffid[i].equals("NS030")) spclub[i]=0;
				if (staffid[i].equals("NS013")) spclub[i]=0;
				}
			    if((vmonth.equals("MAY")) && (vacademicyear.equals("2005 -- 2006")))		
				{
				spclub[i]=(float)(gtot[i]*1.5/100);
				if (staffid[i].equals("TS015")) spclub[i]=0;
				if (staffid[i].equals("NS009")) spclub[i]=0;
				if (staffid[i].equals("NS010")) spclub[i]=0;
				if (staffid[i].equals("NS030")) spclub[i]=0;
				if (staffid[i].equals("NS013")) spclub[i]=0;
				}

				spclub[i]=ff(spclub[i]);

				percentage5[i]=ff(percentage5[i]);
				netpay[i]=ff(gtot[i]) - ff(lic[i] + pf[i]+ esi[i] + fa[i] + percentage5[i] + society[i] + club[i] + spclub[i] + saladvance[i] + telephone[i] + cooptex[i] + khadi[i] + protax[i] + incometax[i] + processfee[i]+others1[i]+rd[i]+loan[i]);
				netpay[i]=ff(netpay[i]);
			}  	
			
		}
	public String[] getStaffid()
	{
		return this.staffid;
	}
	public int getcount()
	{
		return this.count;
	}
	public float[] getnetpay()
	{
		return this.netpay;
	}
	public float[] gethra()
	{
		return this.hra;
	}
	public float[] getgtot()
	{
		return this.gtot;
	}
	public float[] getbasic()
	{
		return this.basic;
	}
	public float[] getma()
	{
		return this.ma;
	}
	public float[] getothers()
	{
		return this.others;
	}
	public float[] getpp()
	{
		return this.pp;
	}
	public float[] getrd()
	{
		return this.rd;
	}
	public float[] getlic()
	{
		return this.lic;
	}
	public float[] getloan()
	{
		return this.loan;
	}
	public float[] getfa()
	{
		return this.fa;
	}
	public float[] getsociety()
	{
		return this.society;
	}
	public float[] getclub()
	{
		return this.club;
	}
	public float[] getspclub()
	{
		return this.spclub;
	}
	public float[] getsaladvance()
	{
		return this.saladvance;
	}
	public float[] gettelephone()
	{
		return this.telephone;
	}
	public float[] getcooptex()
	{
		return this.cooptex;
	}
	public float[] getkhadi()
	{
		return this.khadi;
	}
	public float[] getLoss()
	{
		return this.loss;
	}
	public float[] getprotax()
	{
		return this.protax;
	}
	public float[] getincometax()
	{
		return this.incometax;
	}
	public float[] getprocessfee()
	{
		return this.processfee;
	}
	public float[] getothers1()
	{
		return this.others1;
	}
	public float[] getpf()
	{
		return this.pf;
	}
	public float[] getda()
	{
		return this.da;
	}
	public float[] getesi()
	{
		return this.esi;
	}
		public float[] getesie()
	{
		return this.esie;
	}
	public float[] getpercentage5()
	{
		return this.percentage5;
	}
	public int[] getconsoldate()
	{
		return this.consoldate;
	}
	public int[] getdayslossofpay()
	{
		return this.dayslossofpay;
	}
	public String[] getaccountno()
	{
		return this.accountno;
	}
	public String[] getbank()
	{
		return this.bank;
	}
	public String[] getscale()
	{
		return this.scale;
	}
	public String[] getname()
	{
		return this.name;
	}
	public String[] getbasicqual()
	{
		return this.basicqual;
	}
	public String[] getdesiredqual()
	{
		return this.desiredqual;
	}
	public String[] getresearchqual()
	{
		return this.researchqual;
	}
	public String[] getaddqual()
	{
		return this.addqual;
	}
	public String[] getdesiredquala()
	{
		return this.desiredquala;
	}
	public String[] getresearchquala()
	{
		return this.researchquala;
	}
	public String[] getaddquala()
	{
		return this.addquala;
	}
	public String[] getdesignation()
	{
		return this.designation;
	}
	public String[] getdname()
	{
		return this.dname;
	}
	public Date[] getdob()
	{
		return this.dob;
	}
	public Date[] getappdate()
	{
		return this.appdate;
	}
	public void setstaffids(String staffids)
	{
		this.staffids=staffids;
	}
	public int[] getsplallow() 	{ 		return this.splallow; 	}
	public int[] getscsaper() 	{ 		return this.scsaper; 	}
	public float[] getscsa() 	{ 		return this.scsa; 	}

	}