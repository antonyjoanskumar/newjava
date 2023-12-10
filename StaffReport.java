package sxcce;
import java.sql.*;
import java.util.Date;
public class StaffReport
	{
		String report[][] = new String[50][1500];
String dno=""; 
String teachornonteach=""; 
String category="";
String quer="";
		String order="";
		int count=0;
		Date today = new Date();
		String text="";
		public String report1()
		   {

quer="select name,sex,substring(('000'+ltrim(rtrim(str(day(birthdate))))), "+
"len('000'+ltrim(rtrim(str(day(birthdate)))))-1,2)+'/'+ "+
"substring(('000'+ltrim(rtrim(str(month(birthdate))))), "+
"len('000'+ltrim(rtrim(str(month(birthdate)))))-1,2)+'/'+ "+
"ltrim(rtrim(str(year(birthdate)))),religion,community, "+
"spousename,spouseoccupation,rtrim(address1)+'<br>'+rtrim(address2)+ "+
"'<br>'+address3+'<br>'+rtrim(district)+'<br>'+rtrim(state)+'-'+ "+
"pincode,phone,cell,basicqual,desiredqual,researchqual,addqual,"+
"desiredquala,researchquala,addquala,dno,substring(('000'+ltrim(rtrim(str(day(appdate))))), "+
"len('000'+ltrim(rtrim(str(day(appdate)))))-1,2)+'/'+ "+
"substring(('000'+ltrim(rtrim(str(month(appdate))))), "+
"len('000'+ltrim(rtrim(str(month(appdate)))))-1,2)+'/'+ "+
"ltrim(rtrim(str(year(appdate)))) from staff where active=1 and "+
"staffid like 'ts%' order by designation,staffid";
				try
					{
					Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
					Connection con = DriverManager.getConnection("jdbc:odbc:xavier","sxcce","tamil5india6");
					Statement stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery(quer);
					while(rs.next())
					  {
					  	report[0][count]=(count+1)+"";
					  	for(int i=1;i<20;i++)
					  	   report[i][count]=rs.getString(i);
					  	count++;   
					  }
					rs.close();
					stmt.close();
					con.close();

	for(int i=0;i<count;i++)				
      if(report[11][i].length()<1)report[11][i]="";
	for(int i=0;i<count;i++)				
 	for(int j=12;j<18;j++)				
      if(report[j][i].length()>1)report[11][i]+=","+report[j][i];
					

				}catch(Exception e){return e.toString()+quer;}
			return "success";


		   }









		public String report2()
		   {

quer="select staffid,staffdesignation.cname,substring(('000'+ltrim(rtrim(str(day(appdate))))), "+
"len('000'+ltrim(rtrim(str(day(appdate)))))-1,2)+'/'+  "+
"substring(('000'+ltrim(rtrim(str(month(appdate))))), "+
"len('000'+ltrim(rtrim(str(month(appdate)))))-1,2)+'/'+  "+
"ltrim(rtrim(str(year(appdate)))) from staff,staffdesignation where active=1 and "+
"staffid like 'ts%' and staffdesignation.slno=designation and staffid not in ('ts069')"+
 "order by designation,staffid ";
				try
					{
					Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
					Connection con = DriverManager.getConnection("jdbc:odbc:xavier","sxcce","tamil5india6");
					Statement stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery(quer);
					while(rs.next())
					  {
					  	report[0][count]=(count+1)+"";
					  	for(int i=1;i<4;i++)
					  	   report[i][count]=rs.getString(i);
					  	count++;   
					  }
					rs.close();
					stmt.close();
					con.close();

					

				}catch(Exception e){return e.toString()+quer;}
			return "success";


		   }









		public String Report()
			{
				if(dno.length()>2 && teachornonteach.length()>2)
				    text=" and "+dno+" and "+teachornonteach;
				else if(dno.length()>2)
				     text=" and "+dno;
				else if(teachornonteach.length()>2)
				     text=" and "+teachornonteach;
				     
				text=text+" ";   
				order=order+",serialno";
quer="select staff.staffid,name,basicqual,desiredqual,researchqual,addqual,"+
"desiredquala,researchquala,addquala,staffdesignation.cname,"+
"department.sf,enggexp,enggexpmonth,otherexp,otherexpmonth,"+
"rtrim(address1)+'<br>'+rtrim(address2)+'<br>'+address3+'<br>'+rtrim(district)+'<br>'+rtrim(state)+'-'+pincode,"+
"sex,day(birthdate),month(birthdate),year(birthdate),day(appdate),"+
"month(appdate),year(appdate),category,staffvicariate.cname,parish,"+
"areacode,phone,cell,email,religion,community,caste,"+
"maritalstatus,noofchildren,mothertongue,state,nationality,"+
"extracurricularactivities,specialawards,day(dateadd(yyyy,58,birthdate)),month(dateadd(yyyy,58,birthdate)),year(dateadd(yyyy,58,birthdate)),parentname,parentorhusband from staff,serialno,"+
//"staffdesignation,department,staffvicariate where "+
"staffdesignation,department,staffvicariate where active=1 and "+
"staff.designation=staffdesignation.slno and department.dno=staff.dno and staff.staffid=serialno.staffid and "+
"staffvicariate.slno=staff.vicariateno "+category+" "+text+order;
				try
					{
					Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
					Connection con = DriverManager.getConnection("jdbc:odbc:xavier","sxcce","tamil5india6");
					Statement stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery(quer);
					while(rs.next())
					  {
					  	report[0][count]=(count+1)+"";
					  	for(int i=1;i<46;i++)
					  	   report[i][count]=rs.getString(i);
					  	count++;   
					  }
					rs.close();
					stmt.close();
					con.close();
	for(int i=0;i<count;i++)				
      if(report[3][i].length()<1)report[3][i]="";
	for(int i=0;i<count;i++)				
 	for(int j=4;j<10;j++)				
      if(report[j][i].length()>1)report[3][i]+=","+report[j][i];
    for(int i=0;i<count;i++)
      {
      	report[4][i]=report[10][i];
      	report[5][i]=report[11][i];
      }
    for(int i=0;i<count;i++)
      {
      int expyear=0;
      int expmonth=0;
      expyear=today.getYear()+1900-Integer.parseInt(report[23][i]);	
      expmonth=today.getMonth()+1-Integer.parseInt(report[22][i]);
	  if(expmonth<0){expmonth=expmonth+12;expyear=expyear-1;}
      
      
	report[47][i]=expyear+" Year(s) and "+expmonth+" Month(s)";      
      
  		expmonth+=Integer.parseInt(report[13][i]);
  		expyear+=Integer.parseInt(report[12][i]);
  		if(expmonth>11){expyear++;expmonth-=12;}
	
  		expmonth+=Integer.parseInt(report[15][i]);
  		expyear+=Integer.parseInt(report[14][i]);
  		if(expmonth>11){expyear++;expmonth-=12;}
  		report[6][i]=expyear+" Year(s) and "+expmonth+" Month(s)";
  		
  	expyear-=today.getYear()+1900-Integer.parseInt(report[23][i]);
  	expmonth-=today.getMonth()+1-Integer.parseInt(report[22][i]);
  		  if(expmonth<0){expmonth=expmonth+12;expyear=expyear-1;}
  		if(expmonth>11){expyear++;expmonth-=12;}

  		report[48][i]=expyear+" Year(s) and "+expmonth+" Month(s)";
  		  
      }
      	for(int i=0;i<count;i++)
      	{
      		report[7][i]=report[16][i];
      		report[8][i]=report[17][i];
      		if(report[18][i].length()<2)report[18][i]="0"+report[18][i];
      		if(report[19][i].length()<2)report[19][i]="0"+report[19][i];
      		if(report[21][i].length()<2)report[21][i]="0"+report[21][i];
      		if(report[22][i].length()<2)report[22][i]="0"+report[22][i];
      		report[9][i]=report[18][i]+"/"+report[19][i]+"/"+report[20][i];
      		report[10][i]=report[21][i]+"/"+report[22][i]+"/"+report[23][i];
      		report[11][i]=report[24][i];
      		report[12][i]=report[25][i];
      		report[13][i]=report[26][i];
      		if(report[27][i].length()<2)report[27][i]="";
      		if(report[28][i].length()<2)report[28][i]="";
      		report[14][i]=report[27][i]+" - "+report[28][i];
      		if(report[41][i].length()<2)report[41][i]="0"+report[41][i];
      		if(report[42][i].length()<2)report[42][i]="0"+report[42][i];
      		report[32][i]=report[41][i]+"/"+report[42][i]+"/"+report[43][i];
      	}
      	for(int i=15;i<27;i++)
      	for(int j=0;j<count;j++)
      	{
      		report[i][j]=report[i+14][j];
      	}
      	for(int i=0;i<count;i++)
      	   {
      	   	report[30][i]=report[47][i];
      	   	report[31][i]=report[48][i];
      	   	}    
      	{
					Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
					con = DriverManager.getConnection("jdbc:odbc:xavier","sxcce","tamil5india6");
					stmt = con.createStatement();
					for(int i=0;i<count;i++){
						quer="select max(salarycategory.cname),max(basic),max(others) from salary, "+
						"salarycategory where staffid='"+report[1][i]+"' and "+
						"salarycategory.slno=salary.scale and academicyear=(select academicyear from academicyear) "+
						"and salary.month=(select max(month) from salary where academicyear=(select academicyear from academicyear))";

					rs = stmt.executeQuery(quer);
					if(rs.next())
					  {
					  	report[27][i]=rs.getString(1);
					  	report[28][i]=rs.getString(2);
					  	report[29][i]=rs.getString(3);
					  }
					rs.close();
				}
					stmt.close();
					con.close();
				}
  }catch(Exception e){return quer;}
  return quer;
			}
			

public String Rep()
			{
				if(dno.length()>2 && teachornonteach.length()>2)
				    text=" and "+dno+" and "+teachornonteach;
				else if(dno.length()>2)
				     text=" and "+dno;
				else if(teachornonteach.length()>2)
				     text=" and "+teachornonteach;
				     
				text=text+" ";   
				order=order+",serialno";
quer="select staff.staffid,name,basicqual,desiredqual,researchqual,addqual,"+
"desiredquala,researchquala,addquala,staffdesignation.cname,"+
"department.sf,enggexp,enggexpmonth,otherexp,otherexpmonth,"+
"rtrim(address1)+'<br>'+rtrim(address2)+'<br>'+address3+'<br>'+rtrim(district)+'<br>'+rtrim(state)+'-'+pincode,"+
"sex,day(birthdate),month(birthdate),year(birthdate),day(appdate),"+
"month(appdate),year(appdate),category,staffvicariate.cname,parish,"+
"areacode,phone,cell,email,religion,community,caste,"+
"maritalstatus,noofchildren,mothertongue,state,nationality,"+
"extracurricularactivities,specialawards,day(dateadd(yyyy,58,birthdate)),month(dateadd(yyyy,58,birthdate)),year(dateadd(yyyy,58,birthdate)),parentname,parentorhusband from staff,serialno,"+
//"staffdesignation,department,staffvicariate where "+
"staffdesignation,department,staffvicariate where active=1 and "+
"staff.designation=staffdesignation.slno and department.dno=staff.dno and staff.staffid=serialno.staffid and "+
"staffvicariate.slno=staff.vicariateno "+category+" "+text+order;
				try
					{
					Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
					Connection con = DriverManager.getConnection("jdbc:odbc:xavier","sxcce","tamil5india6");
					Statement stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery(quer);
					while(rs.next())
					  {
					  	report[0][count]=(count+1)+"";
					  	for(int i=1;i<46;i++)
					  	   report[i][count]=rs.getString(i);
					  	count++;   
					  }
					rs.close();
					stmt.close();
					con.close();
	for(int i=0;i<count;i++)				
      if(report[3][i].length()<1)report[3][i]="";
	for(int i=0;i<count;i++)				
 	for(int j=4;j<10;j++)				
      if(report[j][i].length()>1)report[3][i]+=","+report[j][i];
    for(int i=0;i<count;i++)
      {
      	report[4][i]=report[10][i];
      	report[5][i]=report[11][i];
      }
    for(int i=0;i<count;i++)
      {
      int expyear=0;
      int expmonth=0;
      expyear=today.getYear()+1900-Integer.parseInt(report[23][i]);	
      expmonth=today.getMonth()+1-Integer.parseInt(report[22][i]);
	  if(expmonth<0){expmonth=expmonth+12;expyear=expyear-1;}
      
      
	report[47][i]=expyear+" Year(s) and "+expmonth+" Month(s)";      
      
  		expmonth+=Integer.parseInt(report[13][i]);
  		expyear+=Integer.parseInt(report[12][i]);
  		if(expmonth>11){expyear++;expmonth-=12;}
	
  		expmonth+=Integer.parseInt(report[15][i]);
  		expyear+=Integer.parseInt(report[14][i]);
  		if(expmonth>11){expyear++;expmonth-=12;}
  		report[6][i]=expyear+" Year(s) and "+expmonth+" Month(s)";
  		
  	expyear-=today.getYear()+1900-Integer.parseInt(report[23][i]);
  	expmonth-=today.getMonth()+1-Integer.parseInt(report[22][i]);
  		  if(expmonth<0){expmonth=expmonth+12;expyear=expyear-1;}
  		if(expmonth>11){expyear++;expmonth-=12;}

  		report[48][i]=expyear+" Year(s) and "+expmonth+" Month(s)";
  		  
      }
      	for(int i=0;i<count;i++)
      	{
      		report[7][i]=report[16][i];
      		report[8][i]=report[17][i];
      		if(report[18][i].length()<2)report[18][i]="0"+report[18][i];
      		if(report[19][i].length()<2)report[19][i]="0"+report[19][i];
      		if(report[21][i].length()<2)report[21][i]="0"+report[21][i];
      		if(report[22][i].length()<2)report[22][i]="0"+report[22][i];
      		report[9][i]=report[18][i]+"/"+report[19][i]+"/"+report[20][i];
      		report[10][i]=report[21][i]+"/"+report[22][i]+"/"+report[23][i];
      		report[11][i]=report[24][i];
      		report[12][i]=report[25][i];
      		report[13][i]=report[26][i];
      		if(report[27][i].length()<2)report[27][i]="";
      		if(report[28][i].length()<2)report[28][i]="";
      		report[14][i]=report[27][i]+" - "+report[28][i];
      		if(report[41][i].length()<2)report[41][i]="0"+report[41][i];
      		if(report[42][i].length()<2)report[42][i]="0"+report[42][i];
//      		report[32][i]=report[41][i]+"/"+report[42][i]+"/"+report[43][i];
//      		report[33][i]=report[44][i];
//      		report[34][i]=report[45][i];
      		
      	}
      	for(int i=15;i<27;i++)
      	for(int j=0;j<count;j++)
      	{
      		report[i][j]=report[i+14][j];
      	}
      	for(int i=0;i<count;i++)
      	   {
      	   	report[30][i]=report[47][i];
      	   	report[31][i]=report[48][i];
      	   	}    
      	{
					Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
					con = DriverManager.getConnection("jdbc:odbc:xavier","sxcce","tamil5india6");
					stmt = con.createStatement();
					for(int i=0;i<count;i++){
						quer="select max(salarycategory.cname),max(basic),max(others) from salary, "+
						"salarycategory where staffid='"+report[1][i]+"' and "+
						"salarycategory.slno=salary.scale and academicyear=(select academicyear from academicyear) "+
						"and saldate >(select dateadd(dd,-1,max(saldate)) from salary where staffid='"+report[1][i]+"')"; 
//						"and salary.month=(select max(month) from salary where academicyear=(select academicyear from academicyear))";

					rs = stmt.executeQuery(quer);
					if(rs.next())
					  {
					  	report[27][i]=rs.getString(1);
					  	report[28][i]=rs.getString(2);
					  	report[29][i]=rs.getString(3);
					  }
					rs.close();
				}
					stmt.close();
					con.close();
				}
  }catch(Exception e){return quer;}
  return quer;
			}
			

public String Rep1(int active)
			{
				if(dno.length()>2 && teachornonteach.length()>2)
				    text=" and "+dno+" and "+teachornonteach;
				else if(dno.length()>2)
				     text=" and "+dno;
				else if(teachornonteach.length()>2)
				     text=" and "+teachornonteach;
				     
				text=text+" ";   
				order=order+",serialno";
quer="select staff.staffid,name,basicqual,desiredqual,researchqual,addqual,"+
"desiredquala,researchquala,addquala,staffdesignation.cname,"+
"department.sf,enggexp,enggexpmonth,otherexp,otherexpmonth,"+
"rtrim(address1)+'<br>'+rtrim(address2)+'<br>'+address3+'<br>'+rtrim(district)+'<br>'+rtrim(state)+'-'+pincode,"+
"sex,day(birthdate),month(birthdate),year(birthdate),day(appdate),"+
"month(appdate),year(appdate),category,staffvicariate.cname,parish,"+
"areacode,phone,cell,email,religion,community,caste,"+
"maritalstatus,noofchildren,mothertongue,state,nationality,"+
"extracurricularactivities,specialawards,convert(varchar, discontinuedate, 103),day(dateadd(yyyy,58,birthdate)),month(dateadd(yyyy,58,birthdate)),year(dateadd(yyyy,58,birthdate)),parentname,parentorhusband from staff,serialno,"+
"staffdesignation,department,staffvicariate where active="+active+" and "+
"staff.designation=staffdesignation.slno and department.dno=staff.dno and staff.staffid=serialno.staffid and "+
"staffvicariate.slno=staff.vicariateno "+category+" "+text+order;
				try
					{
					Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
					Connection con = DriverManager.getConnection("jdbc:odbc:xavier","sxcce","tamil5india6");
					Statement stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery(quer);
					while(rs.next())
					  {
					  	report[0][count]=(count+1)+"";
					  	for(int i=1;i<=46;i++)
					  	   report[i][count]=rs.getString(i);
					  	count++;   
					  }
					rs.close();
					stmt.close();
					con.close();
	for(int i=0;i<count;i++)				
      if(report[3][i].length()<1)report[3][i]="";
	for(int i=0;i<count;i++)				
 	for(int j=4;j<10;j++)				
      if(report[j][i].length()>1)report[3][i]+=","+report[j][i];
    for(int i=0;i<count;i++)
      {
      	report[4][i]=report[10][i];
      	report[5][i]=report[11][i];
      }
    for(int i=0;i<count;i++)
      {
      int expyear=0;
      int expmonth=0;
      expyear=today.getYear()+1900-Integer.parseInt(report[23][i]);	
      expmonth=today.getMonth()+1-Integer.parseInt(report[22][i]);
	  if(expmonth<0){expmonth=expmonth+12;expyear=expyear-1;}
      
      
	report[47][i]=expyear+" Year(s) and "+expmonth+" Month(s)";      
      
  		expmonth+=Integer.parseInt(report[13][i]);
  		expyear+=Integer.parseInt(report[12][i]);
  		if(expmonth>11){expyear++;expmonth-=12;}
	
  		expmonth+=Integer.parseInt(report[15][i]);
  		expyear+=Integer.parseInt(report[14][i]);
  		if(expmonth>11){expyear++;expmonth-=12;}
  		report[6][i]=expyear+" Year(s) and "+expmonth+" Month(s)";
  		
  	expyear-=today.getYear()+1900-Integer.parseInt(report[23][i]);
  	expmonth-=today.getMonth()+1-Integer.parseInt(report[22][i]);
  		  if(expmonth<0){expmonth=expmonth+12;expyear=expyear-1;}
  		if(expmonth>11){expyear++;expmonth-=12;}

  		report[48][i]=expyear+" Year(s) and "+expmonth+" Month(s)";
  		  
      }
      	for(int i=0;i<count;i++)
      	{
      		report[7][i]=report[16][i];
      		report[8][i]=report[17][i];
      		if(report[18][i].length()<2)report[18][i]="0"+report[18][i];
      		if(report[19][i].length()<2)report[19][i]="0"+report[19][i];
      		if(report[21][i].length()<2)report[21][i]="0"+report[21][i];
      		if(report[22][i].length()<2)report[22][i]="0"+report[22][i];
      		report[9][i]=report[18][i]+"/"+report[19][i]+"/"+report[20][i];
      		report[10][i]=report[21][i]+"/"+report[22][i]+"/"+report[23][i];
      		report[11][i]=report[24][i];
      		report[12][i]=report[25][i];
      		report[13][i]=report[26][i];
      		if(report[27][i].length()<2)report[27][i]="";
      		if(report[28][i].length()<2)report[28][i]="";
      		report[14][i]=report[27][i]+" - "+report[28][i];
      		if(report[41][i].length()<2)report[41][i]="0"+report[41][i];
      		if(report[42][i].length()<2)report[42][i]="0"+report[42][i];
      		report[32][i]=report[41][i];
      		report[33][i]=report[42][i]+"/"+report[43][i]+"/"+report[44][i];
      		report[34][i]=report[45][i];
      		report[35][i]=report[46][i];
      		
      	}
      	for(int i=15;i<27;i++)
      	for(int j=0;j<count;j++)
      	{
      		report[i][j]=report[i+14][j];
      	}
      	for(int i=0;i<count;i++)
      	   {
      	   	report[30][i]=report[47][i];
      	   	report[31][i]=report[48][i];
      	   	}    
      	{
					Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
					con = DriverManager.getConnection("jdbc:odbc:xavier","sxcce","tamil5india6");
					stmt = con.createStatement();
					for(int i=0;i<count;i++){
						quer="select max(salarycategory.cname),max(basic),max(others) from salary, "+
						"salarycategory where staffid='"+report[1][i]+"' and "+
						"salarycategory.slno=salary.scale and academicyear=(select academicyear from academicyear) "+
						"and saldate >(select dateadd(dd,-1,max(saldate)) from salary where staffid='"+report[1][i]+"')"; 
//						"and salary.month=(select max(month) from salary where academicyear=(select academicyear from academicyear))";

					rs = stmt.executeQuery(quer);
					if(rs.next())
					  {
					  	report[27][i]=rs.getString(1);
					  	report[28][i]=rs.getString(2);
					  	report[29][i]=rs.getString(3);
					  }
					rs.close();
				}
					stmt.close();
					con.close();
				}
  }catch(Exception e){return quer;}
  return quer;
			}
						
public void setorder(String order) 
{ 
this.order = order; 
} 
public String getorder()
{
	return this.order;
}
public String[][] getreport()
{
	return this.report;
}
public int getcount()
{
	return this.count;
}
public void setteachornonteach(String teachornonteach) 
{ 
this.teachornonteach = teachornonteach; 
} 
public String getteachornonteach() 
{ 
return this.teachornonteach; 
} 
public String getdno()
{
	return this.dno;
}
public void setdno(String dno)
{
	this.dno=dno;
}
public String getquer()
{
	return this.quer;
}


public void setcategory(String category) 
{ 
this.category = category; 
} 
public String getcategory() 
{ 
return this.category; 
} 



}