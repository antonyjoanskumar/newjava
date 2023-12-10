package sxcce;
import java.sql.*;
import java.util.StringTokenizer;
import dd.DBConnectionManager;

public class salaryreports
	{
				String report[][] = new String[25][3000];
                String fdate=" ";
                String tdate=" ";
                String staffid;
String d1="";
String m1="";
String y1="";
String fdate1="";
String tdate1="";
int count;
String sql="";	
String ex="";	
String error="";
String error1="";
float fk;
			

public String report1(String fdate2,String tdate2)
{
int i=0;
	count=0;
			StringTokenizer st = new StringTokenizer(fdate2,"/");
		    d1=st.nextToken();
		    m1=st.nextToken();
		    y1=st.nextToken();
			fdate=m1+"/"+d1+"/"+y1;
			StringTokenizer st1 = new StringTokenizer(tdate2,"/");
		    d1=st1.nextToken();
		    m1=st1.nextToken();
		    y1=st1.nextToken();
			tdate=m1+"/"+d1+"/"+y1;

				DBConnectionManager connMgr=null; Connection con =null;
				try
					{
                       connMgr = DBConnectionManager.getInstance(); 
                       con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
	
				    ResultSet rs;
sql="select rtrim(max(month))+'-'+ltrim(str(year(max(saldate)))),sum(salary.basic),sum(others),sum(da),sum(hra),sum(ma),sum(pp) from salary,pf where rtrim(salary.month)+rtrim(salary.academicyear)=rtrim(pfmon)+rtrim(pf.academicyear)  and pf.staffid=salary.staffid "
+"and saldate between '"+fdate+"' and '"+tdate+"' group by saldate  order by max(saldate)";
rs = stmt.executeQuery(sql);
while(rs.next())
{
	for(i=1;i<=7;i++)
	{
		report[i-1][count]=rs.getString(i);
	}
	count++;
}
rs.close();

stmt.close();
error="success";
}catch(Exception e){error=(e.toString()+count+i+sql);}
finally{connMgr.freeConnection("xavier",con);

return error;
} 
}



public String report2(String fdate2,String tdate2,String astaffid)
{
int i=0;
	count=0;
			StringTokenizer st = new StringTokenizer(fdate2,"/");
		    d1=st.nextToken();
		    m1=st.nextToken();
		    y1=st.nextToken();
			fdate=m1+"/"+d1+"/"+y1;
			StringTokenizer st1 = new StringTokenizer(tdate2,"/");
		    d1=st1.nextToken();
		    m1=st1.nextToken();
		    y1=st1.nextToken();
			tdate=m1+"/"+d1+"/"+y1;

				DBConnectionManager connMgr=null; Connection con =null;
				try
					{
                       connMgr = DBConnectionManager.getInstance(); 
                       con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
	
				    ResultSet rs;
sql="select staff.name,rtrim((month))+'-'+ltrim(str(year((saldate)))),salary.basic,others,da,(salary.basic+others+da) tot1,hra,((salary.basic+others)*scsa)/100 scsa,ma,pp,(salary.basic+others+da+(((salary.basic+others)*scsa)/100)+hra+ma+pp+splallow),"
+"pf,pf.lic,protax,incometax,splallow,dayslossofpay,scsa,(salary.basic+others+da+scsa+hra+ma+pp+splallow) from staff,salary,pf where staff.staffid=salary.staffid and "
+"rtrim(salary.month)+rtrim(salary.academicyear)=rtrim(pfmon)+rtrim(pf.academicyear) and pf.staffid=salary.staffid and salary.staffid='"+astaffid+"' "
+"and saldate between '"+fdate+"' and '"+tdate+"' order by saldate";

//select rtrim(max(month))+'-'+ltrim(str(year(max(saldate)))),sum(salary.basic),sum(others),sum(da),sum(hra),sum(ma),sum(pp) from salary,pf where rtrim(salary.month)+rtrim(salary.academicyear)=rtrim(pfmon)+rtrim(pf.academicyear)  and pf.staffid=salary.staffid "
//+"and saldate between '"+fdate+"' and '"+tdate+"' group by saldate  order by max(saldate)";

rs = stmt.executeQuery(sql);
while(rs.next())
{
	for(i=1;i<=19;i++)
	{
		report[i-1][count]=rs.getString(i);
	}
	if(Float.valueOf(report[7][count]) >10000)
	{
		report[7][count]=report[17][count];
		report[10][count]=report[18][count];
	}
	
	count++;
}
rs.close();

stmt.close();
error="success";
}catch(Exception e){error=(e.toString()+count+i+sql);}
finally{connMgr.freeConnection("xavier",con);

return error;
} 
}

public void setfdate(String fdate) {this.fdate=fdate;} public String getfdate() {return this.fdate;}
public void settdate(String tdate) {this.tdate=tdate;} public String gettdate() {return this.tdate;}
public void setstaffid(String staffid) {this.staffid=staffid;} public String getstaffid() {return this.staffid;}

public void setreport(String[][] report) {this.report=report;}  public String[][] getreport() {	return this.report;}
public void setcount(int count) {this.count=count;} public int getcount(){	return this.count;}


}




	
	