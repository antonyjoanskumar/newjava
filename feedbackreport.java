package sxcce;
import java.sql.*;
import dd.DBConnectionManager;
public class feedbackreport
	{
String rep[] = new String[1000];
String repin[] = new String[30];
String report[][] = new String[30][10000];
String report3[][] = new String[30][10000];
String report1[] = new String[1000];
String report2[] = new String[1000];

		String sstaffid[] = new String[10000];
		String sname[] = new String[10000];
		String sdesignation[] = new String[10000];
		int sdno[] = new int[10000];
		String sdname[] = new String[10000];
		String oddeven="";
		int sdcount=0;
		int sscount=0;
		int dno=0;
		int id[]= new int[10000];
		String remarks[][] = new String[50][2000];
		int objective[] = new int[100];
		String staffid="";
		String staffname="";
		String subjectcode[] = new String[10000];
		String subjectname[] = new String[10000];
		String allstaffname[] = new String[10000];
		int semesterno[] = new int[10000];
		String dname[] = new String[10000];
		int count=0;
		String sem="";
		int semester=0;
		String academicyear="";
		int noofa[]=new int[50];
		int noofb[]=new int[50];
		int noofc[]=new int[50];
		int noofd[]=new int[50];
		int noofe[]=new int[50];
		int abcde[] = new int[50];
		int maxabcde[] = new int[50];
		int tota=0;
		int totb=0;
		int totc=0;
		int totd=0;
		int tote=0;
		int totabcde=0;
		String insubject="";
		String inid="";
		String orderdno="";
		int questionno[] = new int[50];
		float spercentage[] = new float[10000];
		int noofsubjects=0;
		
		int semesternos=0;		

public String mentorreport()
{
	if(oddeven.equals("EVEN"))
	  sem="(2,4,6,8)";
	else sem="(1,3,5,7)";

	String quer="";
	if(orderdno.equals("0"))orderdno="1=1";
	else orderdno="staff.dno="+orderdno;
try
{
 DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
Statement stmt = con.createStatement();
quer="select name,staff.staffid,dname,(sum(ansb)*25+sum(ansc)*50+ "+
"sum(ansd)*75+sum(anse)*100.0)/sum(ansa+ansb+ansc+ansd+anse) "+
"from stafffeedback,staff,department where staff.staffid=stafffeedback.staffid "+
"and subjectcode='Mentor' "+
"and department.dno=staff.dno and "+orderdno+" and oddeven='"+oddeven+"' and id in "+
"(select id from subjectidentify where academicyear='"+academicyear+"' "+
"and semester in"+sem+")group by "+
"staff.staffid,name,dname order by "+
"(sum(ansb)*25+sum(ansc)*50+sum(ansd)*75+sum(anse)*100.0)/ "+
"sum(ansa+ansb+ansc+ansd+anse) desc ";


ResultSet rs = stmt.executeQuery(quer);
while(rs.next())
{
	sname[sscount]=rs.getString(1);
	sstaffid[sscount]=rs.getString(2);
	sdname[sscount]=rs.getString(3);
	spercentage[sscount]=rs.getFloat(4);
   sscount++;	
}
rs.close();
stmt.close();
connMgr.freeConnection("xavier",con);
}catch(Exception e){return quer+e.toString();}
return quer;
}


public String allreport()
{
	if((semester==2) || (semester==4))
	  sem="(2,4,6,8)";
	else sem="(1,3,5,7)";
	String quer="";
	if(orderdno.equals("0"))orderdno="1=1";
	else orderdno="staff.dno="+orderdno;
try
{
 DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
Statement stmt = con.createStatement();

/*
quer="select t1.name,t1.sid,t1.sf,isnull(t1.op,0),isnull(t2.ep,0),isnull(hp,0),isnull(pp,0), "
+" (ISNULL(t1.op,0)+ISNULL(t2.ep,0)+ISNULL(t3.hp,0)+ISNULL(t4.pp,0)) /(ISNULL(SIGN(t1.op),0)+ISNULL(SIGN(t2.ep),0)+ISNULL(SIGN(t3.hp),0)+ISNULL(SIGN(t4.pp),0)) as per from "
+" (select name,staff.staffid as sid,sf,(sum(ansb)*25+sum(ansc)*50+sum(ansd)*75+sum(anse)*100.0)/sum(ansa+ansb+ansc+ansd+anse) as op from stafffeedback,staff,department where staff.staffid=stafffeedback.staffid "
+" and subjectcode in (select subjectcode from syllabus where theoryorpractical in (1,4) and id in (select id from subjectidentify where academicyear='"+academicyear+"' and semester in (1,3,5,7))) "
+" and department.dno=staff.dno and "+orderdno+" and oddeven='ODD' and id in (select id from subjectidentify where academicyear='"+academicyear+"' and semester in (1,3,5,7))group by staff.staffid,name,sf) as t1 "
+" left join ( "
+" select name,staff.staffid as sid,dname,(sum(ansb)*25+sum(ansc)*50+sum(ansd)*75+sum(anse)*100.0)/sum(ansa+ansb+ansc+ansd+anse) as ep from stafffeedback,staff,department where staff.staffid=stafffeedback.staffid "
+" and subjectcode in (select subjectcode from syllabus where theoryorpractical in (1,4) and id in (select id from subjectidentify where academicyear='"+academicyear+"' and semester in (2,4,6,8))) "
+" and department.dno=staff.dno and "+orderdno+" and oddeven='EVEN' and id in (select id from subjectidentify where academicyear='"+academicyear+"' and semester in (2,4,6,8))group by staff.staffid,name,dname ) as t2 "
+" on t1.sid=t2.sid "
+" left join ( "
+" select name,staff.staffid as sid,dname,(sum(ansb)*25+sum(ansc)*50+sum(ansd)*75+sum(anse)*100.0)/sum(ansa+ansb+ansc+ansd+anse) as hp from stafffeedback,staff,department where staff.staffid=stafffeedback.staffid "
+" and subjectcode='"+academicyear+"' and department.dno=staff.dno and "+orderdno+" and oddeven='EVEN' and id=14 group by staff.staffid,name,dname ) as t3 "
+" on t1.sid=t3.sid "
+" left join ( "
+" select name,staff.staffid as sid,dname,(sum(ansb)*25+sum(ansc)*50+sum(ansd)*75+sum(anse)*100.0)/sum(ansa+ansb+ansc+ansd+anse) as pp from stafffeedback,staff,department where staff.staffid=stafffeedback.staffid "
+" and subjectcode='"+academicyear+"' and department.dno=staff.dno and "+orderdno+" and oddeven='EVEN' and id=15 group by staff.staffid,name,dname ) as t4 "
+" on t1.sid=t4.sid "
+" order by per desc"; 
*/
quer="select tt1.name,tt1.sid,tt1.sf,isnull(t1.op,0),isnull(t2.ep,0),isnull(hp,0),isnull(pp,0), "
+" (ISNULL(t1.op,0)+ISNULL(t2.ep,0)+ISNULL(t3.hp,0)+ISNULL(t4.pp,0)) /dbo.IsZero((ISNULL(SIGN(t1.op),0)+ISNULL(SIGN(t2.ep),0)+ISNULL(SIGN(t3.hp),0)+ISNULL(SIGN(t4.pp),0)),1) as per from "
+" (select name,staff.staffid as sid,sf from staff,department where department.dno=staff.dno and "+orderdno+" and active=1 and category in('Teaching')) as tt1 "
+" left join ( "
+" select name,staff.staffid as sid,sf,isnull((sum(ansb)*25+sum(ansc)*50+sum(ansd)*75+sum(anse)*100.0),0)/isnull(sum(ansa+ansb+ansc+ansd+anse),0) as op from stafffeedback,staff,department where staff.staffid=stafffeedback.staffid  "
+" and subjectcode in (select subjectcode from syllabus where theoryorpractical in (1,4,5) and id in (select id from subjectidentify where academicyear='"+academicyear+"' and semester in (1,3,5,7))) "
+" and department.dno=staff.dno and "+orderdno+" and oddeven='ODD' and id in (select id from subjectidentify where academicyear='"+academicyear+"' and semester in (1,3,5,7))group by staff.staffid,name,sf) as t1 "
+" on tt1.sid=t1.sid "
+" left join ( "
+" select name,staff.staffid as sid,dname,isnull((sum(ansb)*25+sum(ansc)*50+sum(ansd)*75+sum(anse)*100.0),0)/isnull(sum(ansa+ansb+ansc+ansd+anse),0) as ep from stafffeedback,staff,department where staff.staffid=stafffeedback.staffid "
+" and subjectcode in (select subjectcode from syllabus where theoryorpractical in (1,4,5) and id in (select id from subjectidentify where academicyear='"+academicyear+"' and semester in (2,4,6,8))) "
+" and department.dno=staff.dno and "+orderdno+" and oddeven='EVEN' and id in (select id from subjectidentify where academicyear='"+academicyear+"' and semester in (2,4,6,8))group by staff.staffid,name,dname ) as t2 "
+" on tt1.sid=t2.sid "
+" left join ( "
+" select name,staff.staffid as sid,dname,isnull((sum(ansb)*25+sum(ansc)*50+sum(ansd)*75+sum(anse)*100.0),0)/isnull(sum(ansa+ansb+ansc+ansd+anse),0) as hp from stafffeedback,staff,department where staff.staffid=stafffeedback.staffid "
+" and subjectcode='"+academicyear+"' and department.dno=staff.dno and "+orderdno+" and oddeven='EVEN' and id=14 group by staff.staffid,name,dname ) as t3 "
+" on tt1.sid=t3.sid "
+" left join ( "
+" select name,staff.staffid as sid,dname,isnull((sum(ansb)*25+sum(ansc)*50+sum(ansd)*75+sum(anse)*100.0),0)/isnull(sum(ansa+ansb+ansc+ansd+anse),0) as pp from stafffeedback,staff,department where staff.staffid=stafffeedback.staffid "
+" and subjectcode='"+academicyear+"' and department.dno=staff.dno and "+orderdno+" and oddeven='EVEN' and id=15 group by staff.staffid,name,dname ) as t4 "
+" on tt1.sid=t4.sid "
+" order by per desc";



sscount=0;
ResultSet rs = stmt.executeQuery(quer);
while(rs.next())
{
	for (int i=1;i<=8;i++)
	{
  	  report[i-1][sscount]=rs.getString(i);
	}
     sscount++;	
}
rs.close();

stmt.close();
connMgr.freeConnection("xavier",con);
}catch(Exception e){return quer+e.toString();}
return quer;

}

public String finalreport()
{
	if((semester==2) || (semester==4))
	  sem="(2,4,6,8)";
	else sem="(1,3,5,7)";
	String quer="";
	if(orderdno.equals("0"))orderdno="1=1";
	else orderdno="staff.dno="+orderdno;
try
{
 DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
Statement stmt = con.createStatement();
quer="select name,staff.staffid,dname,(sum(ansb)*25+sum(ansc)*50+ "+
"sum(ansd)*75+sum(anse)*100.0)/sum(ansa+ansb+ansc+ansd+anse) "+
"from stafffeedback,staff,department where staff.staffid=stafffeedback.staffid "+
"and subjectcode in (select subjectcode from syllabus where theoryorpractical in (1,4,5) and id in (select id from subjectidentify where academicyear='"+academicyear+"' and semester in"+sem+")) "+
"and department.dno=staff.dno and "+orderdno+" and oddeven='"+oddeven+"' and id in "+
"(select id from subjectidentify where academicyear='"+academicyear+"' "+
"and semester in"+sem+")group by "+
"staff.staffid,name,dname order by "+
"(sum(ansb)*25+sum(ansc)*50+sum(ansd)*75+sum(anse)*100.0)/ "+
"sum(ansa+ansb+ansc+ansd+anse) desc ";


ResultSet rs = stmt.executeQuery(quer);
while(rs.next())
{
	sname[sscount]=rs.getString(1);
	sstaffid[sscount]=rs.getString(2);
	sdname[sscount]=rs.getString(3);
	spercentage[sscount]=rs.getFloat(4);
   sscount++;	
}
rs.close();
stmt.close();
connMgr.freeConnection("xavier",con);
}catch(Exception e){return quer+e.toString();}
return quer;

}

public String finalreportugpg(int ugpg)
{
	if((semester==2) || (semester==4))
	  sem="(2,4,6,8)";
	else sem="(1,3,5,7)";
	String quer="";
	if(orderdno.equals("0"))orderdno="1=1";
	else orderdno="staff.dno="+orderdno;
try
{
 DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
Statement stmt = con.createStatement();

if(ugpg==0)
quer="select name,staff.staffid,dname,(sum(ansb)*25+sum(ansc)*50+ "+
"sum(ansd)*75+sum(anse)*100.0)/sum(ansa+ansb+ansc+ansd+anse) "+
"from stafffeedback,staff,department where staff.staffid=stafffeedback.staffid "+
"and subjectcode in (select subjectcode from syllabus where theoryorpractical in (1,4,5) and id in (select id from subjectidentify where academicyear='"+academicyear+"' and semester in"+sem+")) "+
"and department.dno=staff.dno and "+orderdno+" and oddeven='"+oddeven+"' and id in "+
"(select id from subjectidentify where academicyear='"+academicyear+"' "+
"and semester in "+sem+"  and dno in (1,2,3,4,5,8,14,17,27))group by "+
"staff.staffid,name,dname order by "+
"(sum(ansb)*25+sum(ansc)*50+sum(ansd)*75+sum(anse)*100.0)/ "+
"sum(ansa+ansb+ansc+ansd+anse) desc ";
else if(ugpg==1)
quer="select name,staff.staffid,dname,(sum(ansb)*25+sum(ansc)*50+ "+
"sum(ansd)*75+sum(anse)*100.0)/sum(ansa+ansb+ansc+ansd+anse) "+
"from stafffeedback,staff,department where staff.staffid=stafffeedback.staffid "+
"and subjectcode in (select subjectcode from syllabus where theoryorpractical in (1,4,5) and id in (select id from subjectidentify where academicyear='"+academicyear+"' and semester in"+sem+")) "+
"and department.dno=staff.dno and "+orderdno+" and oddeven='"+oddeven+"' and id in "+
"(select id from subjectidentify where academicyear='"+academicyear+"' "+
"and semester in "+sem+"  and dno not in (1,2,3,4,5,8,14,17,27))group by "+
"staff.staffid,name,dname order by "+
"(sum(ansb)*25+sum(ansc)*50+sum(ansd)*75+sum(anse)*100.0)/ "+
"sum(ansa+ansb+ansc+ansd+anse) desc ";
else 
quer="select max(question),stafffeedback.questionno,max(dname),(sum(stafffeedback.ansb)*25+sum(stafffeedback.ansc)*50+ "+
"sum(stafffeedback.ansd)*75+sum(stafffeedback.anse)*100.0)/sum(stafffeedback.ansa+stafffeedback.ansb+stafffeedback.ansc+stafffeedback.ansd+stafffeedback.anse) as per   "+
"from stafffeedback,staff,department,feedbackquestion  where staff.staffid=stafffeedback.staffid "+
"and subjectcode in (select subjectcode from syllabus where theoryorpractical in (1,4,5) and id in (select id from subjectidentify where academicyear='"+academicyear+"' and semester in"+sem+")) "+
"and academicyear='"+academicyear+"' and semester='"+oddeven+"' and feedbackquestion.category=1 and objective=1 "+
"and department.dno=staff.dno and  "+orderdno+" and oddeven='"+oddeven+"' and id in "+
"(select id from subjectidentify where academicyear='"+academicyear+"' "+
"and semester in "+sem+" )group by stafffeedback.questionno,id,staff.staffid order by stafffeedback.questionno";




ResultSet rs = stmt.executeQuery(quer);
while(rs.next())
{
	sname[sscount]=rs.getString(1);
	sstaffid[sscount]=rs.getString(2);
	sdname[sscount]=rs.getString(3);
	spercentage[sscount]=rs.getFloat(4);
   sscount++;	
}
rs.close();
stmt.close();
connMgr.freeConnection("xavier",con);
}catch(Exception e){return quer+e.toString();}
return quer;

}


public String mentoroverallreport()
{
	if((semester==2) || (semester==4))
	  sem="(2,4,6,8)";
	else sem="(1,3,5,7)";
	String quer="";
	if(orderdno.equals("0"))orderdno="1=1";
	else orderdno="staff.dno="+orderdno;
try
{
 DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
Statement stmt = con.createStatement();
quer="select name,staff.staffid,dname,(sum(ansb)*25+sum(ansc)*50+ "+
"sum(ansd)*75+sum(anse)*100.0)/sum(ansa+ansb+ansc+ansd+anse) "+
"from stafffeedback,staff,department where staff.staffid=stafffeedback.staffid "+
"and subjectcode='mentor' "+
"and department.dno=staff.dno and "+orderdno+" and oddeven='"+oddeven+"' and id in "+
"(select id from subjectidentify where academicyear='"+academicyear+"' "+
"and semester in"+sem+")group by "+
"staff.staffid,name,dname order by "+
"(sum(ansb)*25+sum(ansc)*50+sum(ansd)*75+sum(anse)*100.0)/ "+
"sum(ansa+ansb+ansc+ansd+anse) desc ";


ResultSet rs = stmt.executeQuery(quer);
while(rs.next())
{
	sname[sscount]=rs.getString(1);
	sstaffid[sscount]=rs.getString(2);
	sdname[sscount]=rs.getString(3);
	spercentage[sscount]=rs.getFloat(4);
   sscount++;	
}
rs.close();
stmt.close();
connMgr.freeConnection("xavier",con);
}catch(Exception e){return quer+e.toString();}
return quer;

}


public String staffSelect(int theoryorpractical)
{
	if(semester==2)
	{
	  sem="(2,4,6,8)";
	  oddeven="EVEN";
	}
	else
	{
		 sem="(1,3,5,7)";
	  oddeven="ODD";
	}
	String quer="asdf";
		String tp="(1)";

try
{
 DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
Statement stmt = con.createStatement();
if(theoryorpractical==1)
tp="(1,4,5)";
else
tp="("+theoryorpractical+")";

quer="select staffid,name,cname from staff,staffdesignation where staffid in "+
"(select staffid from subjecthandled where id in "+
"(select subjectidentify.id from subjectidentify,syllabus where semester in "+sem+" "+
"and subcode=subjectcode and subjectidentify.id=syllabus.id "+
"and theoryorpractical in "+tp+") and mainorsub='M') "+
"and dno="+dno+" and active=1 and staff.designation = staffdesignation.slno order by staffid";

if(theoryorpractical==6)
quer="select staffid,name,cname from staff,staffdesignation where staffid in "
+"(select staffid from tutor where academicyear='"+academicyear+"' ) "
+"and dno="+dno+" and active=1 and staff.designation = staffdesignation.slno order by staffid";

if ((theoryorpractical==14) ||(theoryorpractical==15))
quer="select staffid,name,cname from staff,staffdesignation where staffid in "
+"(select staffid from feedbackenteredornot where id="+theoryorpractical+" and oddeven='"+oddeven+"' and subjectcode='"+academicyear+"')"
+"and dno="+dno+" and active=1 and staff.designation = staffdesignation.slno order by designation,staffid";

if (theoryorpractical==16) 
quer="select staffid,name,cname from staff,staffdesignation where staffid in "
+" (select h.staffid from feedbackenteredornot f,subjectidentify s,subjecthandled h where s.id=f.id and s.id=h.id "
+" and mainorsub='M' and oddeven='"+oddeven+"' and subjectcode='First' and academicyear='"+academicyear+"') "
+" and dno="+dno+" and active=1 and staff.designation = staffdesignation.slno order by designation,staffid";

if (theoryorpractical==17) 
quer="select staffid,name,cname from staff,staffdesignation where staffid in "
+" (select h.staffid from feedbackenteredornot f,subjectidentify s,subjecthandled h where s.id=f.id and s.id=h.id "
+" and mainorsub='M' and oddeven='"+oddeven+"' and f.staffid='COEXS' and academicyear='"+academicyear+"') "
+" and dno="+dno+" and active=1 and staff.designation = staffdesignation.slno order by designation,staffid";

ResultSet rs = stmt.executeQuery(quer);
while(rs.next())
{
	sstaffid[sscount]=rs.getString(1);
	sname[sscount]=rs.getString(2);
	sdesignation[sscount]=rs.getString(3);
   sscount++;	
}
rs.close();
stmt.close();
connMgr.freeConnection("xavier",con);
}catch(Exception e){return e.toString()+quer;}
return quer;

}



public String deptSelect(int theoryorpractical)
{
	if(semester==2)
	  sem="(2,4,6,8)";
	else sem="(1,3,5,7)";
	String quer="";
try
{
 DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
Statement stmt = con.createStatement();
quer="select * from department where dno in(select distinct dno from staff where staffid in "+
"(select distinct staffid from stafffeedback)) order by dno";

ResultSet rs = stmt.executeQuery(quer);
while(rs.next())
{
	sdno[sdcount]=rs.getInt(1);
	sdname[sdcount]=rs.getString(2);
   sdcount++;	
}
rs.close();


stmt.close();
connMgr.freeConnection("xavier",con);
}catch(Exception e){return e.toString();}
return quer;

}
		

		
public String Select(int theoryorpractical)
{
	if(semester==2)
	  sem="(2,4,6,8)";
	else sem="(1,3,5,7)";
	String quer="";
	String tp="(1)";
try
{
 DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
Statement stmt = con.createStatement();
if(theoryorpractical==1)
tp="(1,4,5)";
else
tp="("+theoryorpractical+")";

if(theoryorpractical<5)

quer="select staffid,subjectcode,subjectname,syllabus.id,semester, "+
"department.dname from subjecthandled,syllabus,subjectidentify,department "+
"where syllabus.id=subjecthandled.id and subjecthandled.mainorsub='M' and subjectidentify.dno=department.dno "+
"and subjectcode=subcode and syllabus.id=subjectidentify.id "+
"and academicyear='"+academicyear+"' and "+
"semester in"+sem+" and staffid='"+staffid+"' "+
"and syllabus.theoryorpractical in "+tp+" order by semester,department.dno";
else
quer="select staffid,subjectcode,subjectname,syllabus.id,semester, "+
"department.dname from subjecthandled,syllabus,subjectidentify,department "+
"where syllabus.id=subjecthandled.id and subjecthandled.mainorsub='M' and subjectidentify.dno=department.dno "+
"and subjectcode=subcode and syllabus.id=subjectidentify.id "+
"and academicyear='"+academicyear+"' and "+
"semester in"+sem+" and staffid='"+staffid+"' "+
"order by semester,department.dno";

ResultSet rs = stmt.executeQuery(quer);
while(rs.next())
{
   subjectcode[count]=rs.getString(2);
   subjectname[count]=rs.getString(3);
   id[count]=rs.getInt(4);
   semesterno[count]=rs.getInt(5);
   dname[count]=rs.getString(6);
   count++;	
}
rs.close();
stmt.close();
connMgr.freeConnection("xavier",con);
}catch(Exception e){return e.toString();}
return quer;

}


public String Select2()
{
	String quer="";
int hh=0;try
{
 DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
Statement stmt = con.createStatement();
quer="select syllabus.subjectcode,subjectname,id,"+semesternos+" "+
"from syllabus where id in (select id from subjectidentify where semester="+semesternos+" "+
"and dno="+dno+" and academicyear='"+academicyear+"') and theoryorpractical in(1,4,5)";


ResultSet rs = stmt.executeQuery(quer);

while(rs.next())
{hh=0;
   subjectcode[count]=rs.getString(1);
   subjectname[count]=rs.getString(2);
   id[count]=rs.getInt(3);
   semesterno[count]=rs.getInt(4);
   count++;	
}
rs.close();
stmt.close();
connMgr.freeConnection("xavier",con);
}catch(Exception e){return hh+quer+e.toString();}
return quer;

}


public String Select3()
{
	String quer="";
int hh=0;try
{
 DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
Statement stmt = con.createStatement();
quer="select syllabus.subjectcode,subjectname,id,"+semesternos+" "+
"from syllabus where id in (select id from subjectidentify where academicyear='"+academicyear+"') and theoryorpractical in(1,4,5)";


ResultSet rs = stmt.executeQuery(quer);

while(rs.next())
{hh=0;
   subjectcode[count]=rs.getString(1);
   subjectname[count]=rs.getString(2);
   id[count]=rs.getInt(3);
   semesterno[count]=rs.getInt(4);
   count++;	
}
rs.close();
stmt.close();
connMgr.freeConnection("xavier",con);
}catch(Exception e){return hh+quer+e.toString();}
return quer;

}


public String Select1(int theoryorpractical)
{
	if(semester==2)
	  sem="(2,4,6,8)";
	else sem="(1,3,5,7)";
	String quer="";
try
{
 DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
Statement stmt = con.createStatement();
quer="select id, semester,dname from subjectidentify,department "+
"where academicyear='"+academicyear+"' and department.dno=subjectidentify.dno and "+
"semester in"+sem+" order by subjectidentify.dno,semester";

if(theoryorpractical==13)
quer="select cellid,"+semester+",cellname from cells order by cellid";

ResultSet rs = stmt.executeQuery(quer);
while(rs.next())
{
   subjectcode[count]="Cells";
   subjectname[count]="Administration";
   id[count]=rs.getInt(1);
   semesterno[count]=rs.getInt(2);
   dname[count]=rs.getString(3);
   count++;	
}
rs.close();
stmt.close();
connMgr.freeConnection("xavier",con);
}catch(Exception e){return e.toString();}
return quer;

}



public String Report()
{
	String quer="";
	if(semester==1)sem="ODD";
	else sem="EVEN";
    String sem1="(2,4,6,8)";

	if(oddeven.equals("EVEN"))
	  sem1="(2,4,6,8)";
	else sem1="(1,3,5,7)";

try
{
 DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
Statement stmt = con.createStatement();
ResultSet rs;
for(int i=0;i<count;i++)
{
	if(objective[i]==1)
	{
quer="select questionno,staffid,sum(ansa),sum(ansb),sum(ansc),sum(ansd),sum(anse) "+
"from stafffeedback where staffid='"+staffid+"' and questionno="+questionno[i]+" and "+
"id in "+inid+" and subjectcode in"+insubject+" and oddeven='"+oddeven+"' "+
"group by questionno,staffid order by questionno";

if(insubject.equalsIgnoreCase("('Mentor')"))
quer="select questionno,staffid,sum(ansa),sum(ansb),sum(ansc),sum(ansd),sum(anse) "+
"from stafffeedback where staffid='"+staffid+"' and questionno="+questionno[i]+" and "+
" subjectcode in "+insubject+" and oddeven='"+oddeven+"' "+
" and id in (select id from subjectidentify where academicyear='"+academicyear+"' and semester in "+sem1+")"+
"group by questionno,staffid order by questionno";

if(insubject.equalsIgnoreCase("('Cells')"))
quer="select questionno,max(staffid),sum(ansa),sum(ansb),sum(ansc),sum(ansd),sum(anse) "+
"from stafffeedback where staffid in "+inid+"  and questionno="+questionno[i]+" and "+
" subjectcode in "+insubject+" and oddeven='"+oddeven+"' "+
"group by questionno order by questionno";

if (insubject.equalsIgnoreCase("('HOD-Feedback')")) 
quer="select questionno,max(staffid),sum(ansa),sum(ansb),sum(ansc),sum(ansd),sum(anse) "+
"from stafffeedback where staffid='"+staffid+"'  and questionno="+questionno[i]+" and "+
" subjectcode='"+academicyear+"' and id=14 and oddeven='"+oddeven+"' "+
"group by questionno order by questionno";

if (insubject.equalsIgnoreCase("('Principal-Feedback')")) 
quer="select questionno,max(staffid),sum(ansa),sum(ansb),sum(ansc),sum(ansd),sum(anse) "+
"from stafffeedback where staffid='"+staffid+"'  and questionno="+questionno[i]+" and "+
" subjectcode='"+academicyear+"' and id=15 and oddeven='"+oddeven+"' "+
"group by questionno order by questionno";

rs = stmt.executeQuery(quer);
if(rs.next())
{
   questionno[i]=rs.getInt(1);
   staffid=rs.getString(2);
   noofa[i]=rs.getInt(3);
   noofb[i]=rs.getInt(4);
   noofc[i]=rs.getInt(5);
   noofd[i]=rs.getInt(6);
   noofe[i]=rs.getInt(7);
   tota+=noofa[i];
   totb+=noofb[i];
   totc+=noofc[i];
   totd+=noofd[i];
   tote+=noofe[i];
   abcde[i]=noofa[i]+noofb[i]+noofc[i]+noofd[i]+noofe[i];
   totabcde+=abcde[i];
   maxabcde[i]=noofa[i];
   if(maxabcde[i]<noofb[i])
    maxabcde[i]=noofb[i];
   if(maxabcde[i]<noofc[i])
    maxabcde[i]=noofc[i];
   if(maxabcde[i]<noofd[i])
    maxabcde[i]=noofd[i];
   if(maxabcde[i]<noofe[i])
    maxabcde[i]=noofe[i];
}
rs.close();
}

else 
{
//quer="select ltrim(rtrim(remarks)) +' {'+ ltrim(rtrim(str(count(*))))+')',max(questionno) from remarks where staffid='"+staffid+"'  and oddeven='"+oddeven+"'  and questionno="+questionno[i]+" and subjectcode in "+insubject+" and id in "+inid+"";

quer="select ltrim(rtrim(remarks)) +' {'+ ltrim(rtrim(str(count(*))))+')',max(questionno) from remarks where staffid='"+staffid+"'  and oddeven='"+oddeven+"'  and questionno="+questionno[i]+" and subjectcode in "+insubject+" and id in "+inid+"  group by remarks order by count(*) desc";
if(insubject.equalsIgnoreCase("('Mentor')"))
quer="select ltrim(rtrim(remarks)) +' {'+ ltrim(rtrim(str(count(*))))+')',max(questionno) from remarks where staffid='"+staffid+"'  and oddeven='"+oddeven+"'  and questionno="+questionno[i]+" and subjectcode in "+insubject +
" and id in (select id from subjectidentify where academicyear='"+academicyear+"' and semester in "+sem1+") "+
" group by remarks order by count(*) desc";
if(insubject.equalsIgnoreCase("('Cells')"))
quer="select ltrim(rtrim(remarks)) +' {'+ ltrim(rtrim(str(count(*))))+')',max(questionno) from remarks where staffid in "+inid+"  and oddeven='"+oddeven+"'  and questionno="+questionno[i]+" and subjectcode in "+insubject +"  group by remarks order by count(*) desc";

rs = stmt.executeQuery(quer);
int cc=0;
while(rs.next())
{
	remarks[i][cc++]=rs.getString(1);
   questionno[i]=rs.getInt(2);
   
}
rs.close();
}
}



quer="select name from staff where staffid='"+staffid+"'";

rs = stmt.executeQuery(quer);
if(rs.next())
{
	staffname=rs.getString(1);
}
rs.close();

for(int i=0;i<noofsubjects;i++)
{
quer="select subjectname from syllabus where subjectcode='"+subjectcode[i]+"' and id="+id[i]+"";
if(insubject.equalsIgnoreCase("('Cells')"))
quer="select cellname from cells where cellid="+id[i]+"";

rs = stmt.executeQuery(quer);
if(rs.next())
{
	subjectname[i]=rs.getString(1);
}
rs.close();
quer="select department.dname,semester from subjectidentify,department where id="+id[i]+" and "+
"department.dno=subjectidentify.dno";

rs = stmt.executeQuery(quer);
if(rs.next())
{
	dname[i]=rs.getString(1);
	semesterno[i]=rs.getInt(2);
}
rs.close();


allstaffname[i]="";
quer="select name from staff where staffid in(select staffid from subjecthandled where id="+id[i]+" and subcode='"+subjectcode[i]+"' and mainorsub='S')";
rs = stmt.executeQuery(quer);
while(rs.next())
{
	allstaffname[i]=allstaffname[i]+" , "+rs.getString(1);
}
rs.close();
   	quer="select name from staff where staffid in(select staffid from subjecthandled where id="+id[i]+" and subcode='"+subjectcode[i]+"' and mainorsub='Z')";
rs = stmt.executeQuery(quer);
while(rs.next())
{
	allstaffname[i]=allstaffname[i]+" , "+rs.getString(1);
}
rs.close();

}
stmt.close();
connMgr.freeConnection("xavier",con);
}catch(Exception e){return e.toString();}
return quer;

}


public String viewstaffname(String sfid)
{
	String quer="";
	String sfname="";
try
{
 DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
Statement stmt = con.createStatement();
ResultSet rs;

quer="select name from staff where staffid='"+sfid+"'";

rs = stmt.executeQuery(quer);
if(rs.next())
{
	sfname=rs.getString(1);
}
rs.close();
stmt.close();
connMgr.freeConnection("xavier",con);
}catch(Exception e){return "";}
return sfname;

}



public String Reportgeneral()
{
	String quer="";
	if(semester==1)sem="ODD";
	else sem="EVEN";
try
{
 DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
Statement stmt = con.createStatement();
ResultSet rs;
for(int i=0;i<count;i++)
{
	if(objective[i]==1)
	{
quer="select questionno,'a',sum(ansa),sum(ansb),sum(ansc),sum(ansd),sum(anse) "+
"from generalfeedback where quesname in (select quesname from generalfeedbackquestionselect) and questionno="+questionno[i]+" and "+
"id in "+inid+" "+
"group by questionno order by questionno";

rs = stmt.executeQuery(quer);
if(rs.next())
{
   questionno[i]=rs.getInt(1);
   staffid=rs.getString(2);
   noofa[i]=rs.getInt(3);
   noofb[i]=rs.getInt(4);
   noofc[i]=rs.getInt(5);
   noofd[i]=rs.getInt(6);
   noofe[i]=rs.getInt(7);
   tota+=noofa[i];
   totb+=noofb[i];
   totc+=noofc[i];
   totd+=noofd[i];
   tote+=noofe[i];
   abcde[i]=noofa[i]+noofb[i]+noofc[i]+noofd[i]+noofe[i];
   totabcde+=abcde[i];
   maxabcde[i]=noofa[i];
   if(maxabcde[i]<noofb[i])
    maxabcde[i]=noofb[i];
   if(maxabcde[i]<noofc[i])
    maxabcde[i]=noofc[i];
   if(maxabcde[i]<noofd[i])
    maxabcde[i]=noofd[i];
   if(maxabcde[i]<noofe[i])
    maxabcde[i]=noofe[i];
}
rs.close();
}

else 
{
quer="select * from generalremarks where quesname in (select quesname from generalfeedbackquestionselect)  and questionno="+questionno[i]+" and id in "+inid+"";

rs = stmt.executeQuery(quer);
int cc=0;
while(rs.next())
{
	remarks[i][cc++]=rs.getString(3);
   questionno[i]=rs.getInt(4);
   
}
rs.close();
}
}
for(int i=0;i<noofsubjects;i++)
{

quer="select department.dname,semester from subjectidentify,department where id="+id[i]+" and "+
"department.dno=subjectidentify.dno";

rs = stmt.executeQuery(quer);
if(rs.next())
{
	dname[i]=rs.getString(1);
	semesterno[i]=rs.getInt(2);
}
rs.close();
}

stmt.close();
connMgr.freeConnection("xavier",con);
}catch(Exception e){return e.toString();}
return quer;

}



public void setacademicyear(String academicyear) 
{ 
this.academicyear = academicyear; 
} 
public String getacademicyear() 
{ 
return this.academicyear; 
} 
public void setsem(String sem) 
{ 
this.sem = sem; 
} 
public String getsem() 
{ 
return this.sem; 
} 

public void setstaffid(String staffid) 
{ 
this.staffid = staffid; 
} 
public String getstaffid() 
{ 
return this.staffid; 
} 


public void setinid(String inid) 
{ 
this.inid = inid; 
} 
public String getinid() 
{ 
return this.inid; 
} 


public void setinsubject(String insubject) 
{ 
this.insubject = insubject; 
} 
public String getinsubject() 
{ 
return this.insubject; 
} 

public void setstaffname(String staffname) 
{ 
this.staffname = staffname; 
} 
public String getstaffname() 
{ 
return this.staffname; 
} 

public void setorderdno(String orderdno) 
{ 
this.orderdno = orderdno; 
} 
public String getorderdno() 
{ 
return this.orderdno; 
} 

public void setsemester(int semester) 
{ 
this.semester = semester; 
} 
public int getsemester() 
{ 
return this.semester; 
}

public void setoddeven(String oddeven) 
{ 
this.oddeven = oddeven; 
} 

public String getoddeven() 
{ 
return this.oddeven; 
}


public void setsemesternos(int semesternos) 
{ 
this.semesternos = semesternos; 
} 
public int getsemesternos() 
{ 
return this.semesternos; 
}



public void setcount(int count) 
{ 
this.count = count; 
} 
public int getcount() 
{ 
return this.count; 
}

public void setdno(int dno) 
{ 
this.dno = dno; 
} 
public int getdno() 
{ 
return this.dno; 
}

public void setsscount(int sscount) 
{ 
this.sscount = sscount; 
} 
public int getsscount() 
{ 
return this.sscount; 
}

public void setsdcount(int sdcount) 
{ 
this.sdcount = sdcount; 
} 
public int getsdcount() 
{ 
return this.sdcount; 
}



public void settota(int tota) 
{ 
this.tota = tota; 
} 
public int gettota() 
{ 
return this.tota; 
}

public void settotb(int totb) 
{ 
this.totb = totb; 
} 
public int gettotb() 
{ 
return this.totb; 
}

public void settotc(int totc) 
{ 
this.totc = totc; 
} 
public int gettotc() 
{ 
return this.totc; 
}

public void settotd(int totd) 
{ 
this.totd = totd; 
} 
public int gettotd() 
{ 
return this.totd; 
}

public void settote(int tote) 
{ 
this.tote = tote; 
} 
public int gettote() 
{ 
return this.tote; 
}

public void settotabcde(int totabcde) 
{ 
this.totabcde = totabcde; 
} 
public int gettotabcde() 
{ 
return this.totabcde; 
}

public void setnoofsubjects(int noofsubjects) 
{ 
this.noofsubjects = noofsubjects; 
} 
public int getnoofsubjects() 
{ 
return this.noofsubjects; 
}


public void setsemesterno(int[] semesterno) 
{ 
this.semesterno = semesterno; 
} 
public int[] getsemesterno() 
{ 
return this.semesterno; 
}

public void setquestionno(int[] questionno) 
{ 
this.questionno = questionno; 
} 
public int[] getquestionno() 
{ 
return this.questionno; 
}

public void setnoofa(int[] noofa) 
{ 
this.noofa = noofa; 
} 
public int[] getnoofa() 
{ 
return this.noofa; 
}

public void setnoofb(int[] noofb) 
{ 
this.noofb = noofb; 
} 
public int[] getnoofb() 
{ 
return this.noofb; 
}

public void setnoofc(int[] noofc) 
{ 
this.noofc = noofc; 
} 
public int[] getnoofc() 
{ 
return this.noofc; 
}

public void setnoofd(int[] noofd) 
{ 
this.noofd = noofd; 
} 
public int[] getnoofd() 
{ 
return this.noofd; 
}

public void setnoofe(int[] noofe) 
{ 
this.noofe = noofe; 
} 
public int[] getnoofe() 
{ 
return this.noofe; 
}
public void setobjective(int[] objective) 
{ 
this.objective = objective; 
} 
public int[] getobjective() 
{ 
return this.objective; 
}

public void setmaxabcde(int[] maxabcde) 
{ 
this.maxabcde = maxabcde; 
} 
public int[] getmaxabcde() 
{ 
return this.maxabcde; 
}

public void setabcde(int[] abcde) 
{ 
this.abcde = abcde; 
} 
public int[] getabcde() 
{ 
return this.abcde; 
}



public void setid(int[] id) 
{ 
this.id = id; 
} 
public int[] getid() 
{ 
return this.id; 
}

public void setsdno(int[] sdno) 
{ 
this.sdno = sdno; 
} 
public int[] getsdno() 
{ 
return this.sdno; 
}

public void setsname(String[] sname) 
{ 
this.sname = sname; 
} 
public String[] getsname() 
{ 
return this.sname; 
}


public void setsstaffid(String[] sstaffid) 
{ 
this.sstaffid = sstaffid; 
} 
public String[] getsstaffid() 
{ 
return this.sstaffid; 
}


public void setsdesignation(String[] sdesignation) 
{ 
this.sdesignation = sdesignation; 
} 
public String[] getsdesignation() 
{ 
return this.sdesignation; 
}


public void setsdname(String[] sdname) 
{ 
this.sdname = sdname; 
} 
public String[] getsdname() 
{ 
return this.sdname; 
}

public void setsubjectname(String[] subjectname) 
{ 
this.subjectname = subjectname; 
} 
public String[] getsubjectname() 
{ 
return this.subjectname; 
}


public void setsubjectcode(String[] subjectcode) 
{ 
this.subjectcode = subjectcode; 
} 
public String[] getsubjectcode() 
{ 
return this.subjectcode; 
}


public void setdname(String[] dname) 
{ 
this.dname = dname; 
} 
public String[] getdname() 
{ 
return this.dname; 
}

public void setallstaffname(String[] allstaffname) 
{ 
this.allstaffname = allstaffname; 
} 
public String[] getallstaffname() 
{ 
return this.allstaffname; 
}

public void setremarks(String[][] remarks) 
{ 
this.remarks = remarks; 
} 
public String[][] getremarks() 
{ 
return this.remarks; 
}

public void setspercentage(float[] spercentage) 
{ 
this.spercentage = spercentage; 
} 
public float[] getspercentage() 
{ 
return this.spercentage; 
}

public void setrep(String[] rep) {this.rep=rep;}  public String[] getrep() {	return this.rep;}
public void setrepin(String[] repin) {this.repin=repin;}  public String[] getrepin() {	return this.repin;}
public void setreport(String[][] report) {this.report=report;}  public String[][] getreport() {	return this.report;}
public void setreport3(String[][] report3) {this.report3=report3;}  public String[][] getreport3() {	return this.report3;}
public void setreport1(String[] report1) {this.report1=report1;}  public String[] getreport1() {	return this.report1;}
public void setreport2(String[] report2) {this.report2=report2;}  public String[] getreport2() {	return this.report2;}

}
	