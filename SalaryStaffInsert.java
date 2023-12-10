package sxcce;
import java.sql.*;
import java.util.Date;
public class SalaryStaffInsert

{
String academicyear=""; 
String accountno=""; 
String bank=""; 
String basic=""; 
String club=""; 
String consoldate=""; 
String cooptex=""; 
String dayslossofpay=""; 
String fa=""; 
String incometax=""; 
String khadi=""; 
String lic=""; 
String loan=""; 
String ma=""; 
String month=""; 
String others=""; 
String others1=""; 
String pfper=""; 
String pp=""; 
String processfee=""; 
String protax=""; 
String rd=""; 
String saladvance=""; 
String scale=""; 
String society=""; 
String spclub=""; 
String staffid=""; 
String telephone="";
String name="";
String designation="";
String matchname[] = new String[500];
String matchstaffid[] = new String[500];
int count=0;
String saldate="";
String mm="01";
String scsa="0";
String splallow="0";

public String Insert()
{
Date today=new Date();
	
     if(month.equals("JAN"))  	mm="01";
     else if(month.equals("FEB")) 	mm="02";
     else if(month.equals("MAR"))   mm="03";
     else if(month.equals("APR")) 	mm="04";
     else if(month.equals("MAY"))  	mm="05";
     else if(month.equals("JUN")) 	mm="06";
     else if(month.equals("JUL"))  	mm="07";
     else if(month.equals("AUG"))  	mm="08";
     else if(month.equals("SEP")) 	mm="09";
     else if(month.equals("OCT")) 	mm="10";
     else if(month.equals("NOV")) 	mm="11";
     else if(month.equals("DEC")) 	mm="12";
     saldate=mm+"/25/"+(today.getYear()+1900);
 //    saldate="10/25/2010";
	
try
{
Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
Connection con = DriverManager.getConnection("jdbc:odbc:xavier","sxcce","tamil5india6");
PreparedStatement ps = con.prepareStatement("insert into salary values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

ps.setString(1,staffid);
ps.setString(2,consoldate);
ps.setString(3,dayslossofpay);
ps.setString(4,accountno);
ps.setString(5,bank);
ps.setString(6,basic);
ps.setString(7,ma);
ps.setString(8,others);
ps.setString(9,pp);
ps.setString(10,rd);
ps.setString(11,lic);
ps.setString(12,loan);
ps.setString(13,pfper);
ps.setString(14,fa);
ps.setString(15,society);
ps.setString(16,club);
ps.setString(17,spclub);
ps.setString(18,saladvance);
ps.setString(19,telephone);
ps.setString(20,cooptex);
ps.setString(21,khadi);
ps.setString(22,protax);
ps.setString(23,incometax);
ps.setString(24,processfee);
ps.setString(25,others1);
ps.setString(26,scale);
ps.setString(27,month);
ps.setString(28,academicyear);
ps.setString(29,saldate);
ps.setString(30,scsa);
ps.setString(31,splallow);
int i = ps.executeUpdate();
ps.close();
con.close();
}catch(Exception e){return e.toString();}
return("success");
}
public String Delete()
{
try
{
Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
Connection con = DriverManager.getConnection("jdbc:odbc:xavier","sxcce","tamil5india6");
PreparedStatement ps = con.prepareStatement("delete from salary where staffid=? and month =? and academicyear=?");
ps.setString(1,staffid); 
ps.setString(2,month);
ps.setString(3,academicyear);
int i = ps.executeUpdate();
ps.close();
con.close();
}catch(Exception e){return e.toString();}
return("success");
}
public int View()
{
int found=0;
try
{
Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
Connection con = DriverManager.getConnection("jdbc:odbc:xavier","sxcce","tamil5india6");
Statement stmt = con.createStatement();
ResultSet rs = stmt.executeQuery("select * from salary where staffid='"+staffid+"' and month ='"+month+"' and academicyear='"+academicyear+"'");if(rs.next())
{
staffid=rs.getString(1);
consoldate=rs.getString(2);
dayslossofpay=rs.getString(3);
accountno=rs.getString(4);
bank=rs.getString(5);
basic=rs.getString(6);
ma=rs.getString(7);
others=rs.getString(8);
pp=rs.getString(9);
rd=rs.getString(10);
lic=rs.getString(11);
loan=rs.getString(12);
pfper=rs.getString(13);
fa=rs.getString(14);
society=rs.getString(15);
club=rs.getString(16);
spclub=rs.getString(17);
saladvance=rs.getString(18);
telephone=rs.getString(19);
cooptex=rs.getString(20);
khadi=rs.getString(21);
protax=rs.getString(22);
incometax=rs.getString(23);
processfee=rs.getString(24);
others1=rs.getString(25);
scale=rs.getString(26);
month=rs.getString(27);
academicyear=rs.getString(28);
found=1;
}
rs.close();
{
  rs = stmt.executeQuery("select name,staffdesignation.cname from staff,staffdesignation where staff.designation=staffdesignation.slno and staffid='"+staffid+"'");
  if(rs.next())
    {
    	name=rs.getString(1);
    	designation=rs.getString(2);
    }
  rs.close();  
  rs = stmt.executeQuery("select cname from salarycategory where slno="+scale);
  if(rs.next())
    {
    	scale=rs.getString(1);
    }
  rs.close();  
  }	
stmt.close();
con.close();
}catch(Exception e){}
return found; 
}



public int MatchView(int select)
{
int found=0;
String quer;
if(select==1)
   {
   	quer="select staffid,name from staff where staffid='"+staffid+"'";
   }
   else {
quer="select staff.staffid,name from staff,salary where staff.staffid=salary.staffid and "+
"(salary.staffid='"+staffid+"') and salary.month='"+month+"' and "+
"salary.academicyear='"+academicyear+"'";
}
try
{
Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
Connection con = DriverManager.getConnection("jdbc:odbc:xavier","sxcce","tamil5india6");
Statement stmt = con.createStatement();
ResultSet rs = stmt.executeQuery(quer);
while(rs.next())
{
matchstaffid[count]=rs.getString(1);
matchname[count]=rs.getString(2);
count++;
found=1;
}
rs.close();
if(count==0)
   {
if(select==1)
   {
   	quer="select staffid,name from staff where staffid='"+staffid+"' or name like '"+name+"%'";
   }
   else {
   	quer="select staff.staffid,name from staff,salary where staff.staffid=salary.staffid and "+
 "(salary.staffid='"+staffid+"' or staff.name like '"+name+"%') and salary.month='"+month+"' and "+
 "salary.academicyear='"+academicyear+"'";}
rs = stmt.executeQuery(quer);
while(rs.next())
{
matchstaffid[count]=rs.getString(1);
matchname[count]=rs.getString(2);
count++;
found=1;
}
rs.close();
if(select==1)
   {
   	quer="select staffid,name from staff where staffid='"+staffid+"' or name like '%"+name+"%' and staff.name not in(select name from staff where name like '"+name+"%')";
   }
   else {
   	quer="select staff.staffid,name from staff,salary where staff.staffid=salary.staffid and "+
 "(salary.staffid='"+staffid+"' or staff.name like '%"+name+"%' and staff.name not in(select name from staff where name like '"+name+"%')) and salary.month='"+month+"' and "+
 "salary.academicyear='"+academicyear+"'";}
rs = stmt.executeQuery(quer);
while(rs.next())
{
matchstaffid[count]=rs.getString(1);
matchname[count]=rs.getString(2);
count++;
found=1;
}
rs.close();   }
stmt.close();
con.close();
}catch(Exception e){}
return found; 
}


public int MatchView1(int select)
{
int found=0;
String quer;
if(select==1)
   {
   	quer="select staffid,name from staff where staffid='"+staffid+"'";
   }
   else {
quer="select staff.staffid,name from staff,salary where staff.staffid=salary.staffid and "+
"(salary.staffid='"+staffid+"') and "+
"salary.academicyear='"+academicyear+"'";
}
try
{
Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
Connection con = DriverManager.getConnection("jdbc:odbc:xavier","sxcce","tamil5india6");
Statement stmt = con.createStatement();
ResultSet rs = stmt.executeQuery(quer);
while(rs.next())
{
matchstaffid[count]=rs.getString(1);
matchname[count]=rs.getString(2);
count++;
found=1;
}
rs.close();
if(count==0)
   {
if(select==1)
   {
   	quer="select staffid,name from staff where staffid='"+staffid+"' or name like '"+name+"%'";
   }
   else {
   	quer="select staff.staffid,name from staff,salary where staff.staffid=salary.staffid and "+
 "(salary.staffid='"+staffid+"' or staff.name like '"+name+"%')and "+
 "salary.academicyear='"+academicyear+"'";}
rs = stmt.executeQuery(quer);
while(rs.next())
{
matchstaffid[count]=rs.getString(1);
matchname[count]=rs.getString(2);
count++;
found=1;
}
rs.close();
if(select==1)
   {
   	quer="select staffid,name from staff where staffid='"+staffid+"' or name like '%"+name+"%' and staff.name not in(select name from staff where name like '"+name+"%')";
   }
   else {
   	quer="select staff.staffid,name from staff,salary where staff.staffid=salary.staffid and "+
 "(salary.staffid='"+staffid+"' or staff.name like '%"+name+"%' and staff.name not in(select name from staff where name like '"+name+"%')) and "+
 "salary.academicyear='"+academicyear+"'";}
rs = stmt.executeQuery(quer);
while(rs.next())
{
matchstaffid[count]=rs.getString(1);
matchname[count]=rs.getString(2);
count++;
found=1;
}
rs.close();   }
stmt.close();
con.close();
}catch(Exception e){}
return found; 
}









 public void setacademicyear(String academicyear) 
{ 
this.academicyear = academicyear; 
} 
public String getacademicyear() 
{ 
return this.academicyear; 
} 
public void setaccountno(String accountno) 
{ 
this.accountno = accountno; 
} 
public String getaccountno() 
{ 
return this.accountno; 
} 
public void setbank(String bank) 
{ 
this.bank = bank; 
} 
public String getbank() 
{ 
return this.bank; 
} 
public void setbasic(String basic) 
{ 
this.basic = basic; 
} 
public String getbasic() 
{ 
return this.basic; 
} 
public void setclub(String club) 
{ 
this.club = club; 
} 
public String getclub() 
{ 
return this.club; 
} 
public void setconsoldate(String consoldate) 
{ 
this.consoldate = consoldate; 
} 
public String getconsoldate() 
{ 
return this.consoldate; 
} 
public void setcooptex(String cooptex) 
{ 
this.cooptex = cooptex; 
} 
public String getcooptex() 
{ 
return this.cooptex; 
} 
public void setdayslossofpay(String dayslossofpay) 
{ 
this.dayslossofpay = dayslossofpay; 
} 
public String getdayslossofpay() 
{ 
return this.dayslossofpay; 
} 
public void setfa(String fa) 
{ 
this.fa = fa; 
} 
public String getfa() 
{ 
return this.fa; 
} 
public void setincometax(String incometax) 
{ 
this.incometax = incometax; 
} 
public String getincometax() 
{ 
return this.incometax; 
} 
public void setkhadi(String khadi) 
{ 
this.khadi = khadi; 
} 
public String getkhadi() 
{ 
return this.khadi; 
} 
public void setlic(String lic) 
{ 
this.lic = lic; 
} 
public String getlic() 
{ 
return this.lic; 
} 
public void setloan(String loan) 
{ 
this.loan = loan; 
} 
public String getloan() 
{ 
return this.loan; 
} 
public void setma(String ma) 
{ 
this.ma = ma; 
} 
public String getma() 
{ 
return this.ma; 
} 
public void setmonth(String month) 
{ 
this.month = month; 
} 
public String getmonth() 
{ 
return this.month; 
} 
public void setothers(String others) 
{ 
this.others = others; 
} 
public String getothers() 
{ 
return this.others; 
} 
public void setothers1(String others1) 
{ 
this.others1 = others1; 
} 
public String getothers1() 
{ 
return this.others1; 
} 
public void setpfper(String pfper) 
{ 
this.pfper = pfper; 
} 
public String getpfper() 
{ 
return this.pfper; 
} 
public void setpp(String pp) 
{ 
this.pp = pp; 
} 
public String getpp() 
{ 
return this.pp; 
} 
public void setprocessfee(String processfee) 
{ 
this.processfee = processfee; 
} 
public String getprocessfee() 
{ 
return this.processfee; 
} 
public void setprotax(String protax) 
{ 
this.protax = protax; 
} 
public String getprotax() 
{ 
return this.protax; 
} 
public void setrd(String rd) 
{ 
this.rd = rd; 
} 
public String getrd() 
{ 
return this.rd; 
} 
public void setsaladvance(String saladvance) 
{ 
this.saladvance = saladvance; 
} 
public String getsaladvance() 
{ 
return this.saladvance; 
} 
public void setscale(String scale) 
{ 
this.scale = scale; 
} 
public String getscale() 
{ 
return this.scale; 
} 
public void setsociety(String society) 
{ 
this.society = society; 
} 
public String getsociety() 
{ 
return this.society; 
} 
public void setspclub(String spclub) 
{ 
this.spclub = spclub; 
} 
public String getspclub() 
{ 
return this.spclub; 
} 
public void setstaffid(String staffid) 
{ 
this.staffid = staffid; 
} 
public String getstaffid() 
{ 
return this.staffid; 
} 
public void settelephone(String telephone) 
{ 
this.telephone = telephone; 
} 
public String gettelephone() 
{ 
return this.telephone; 
} 
public void setname(String name) 
{ 
this.name = name; 
} 
public String getname() 
{ 
return this.name; 
}
public void setdesignation(String designation) 
{ 
this.designation = designation; 
} 
public String getdesignation() 
{ 
return this.designation; 
}
public String[] getmatchname()
{
	return this.matchname;
} 
public String[] getmatchstaffid()
{
	return this.matchstaffid;
}
public int getcount()
{
	return this.count;
}
}
