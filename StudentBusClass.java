/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Bus;
import java.sql.*;
import java.util.*;
import java.sql.*;
import SQLdb.SQLbean;
/**
 *
 * @author Edwin
 */
public class StudentBusClass 
{
     private Properties pro;
    private SQLbean sb;
    public StudentBusClass()
    {
        sb=new SQLbean();
        pro=new Properties();
        pro=sb.getConnString();
    }
     


public String Insert(int routeno,int stageno,String studid,String accyear,String sem,String challan,double charge,int buspass)
{
    String msg=null;
       Connection con=null;
    String sql="";
    try
    {
        con=connect();
        Statement stmt = con.createStatement();
	PreparedStatement ps;
	sql="insert into Student_Bus(Route_No,Stage_No,Student_Id,Acc_Year,Sem,Challan_No,Charge,buspass) values(?,?,?,?,?,?,?,?)";
	ps=con.prepareStatement(sql);
	ps.setInt(1,routeno);
	ps.setInt(2,stageno);
	ps.setString(3,studid);
	ps.setString(4,accyear);
        ps.setString(5,sem);
        ps.setString(6,challan);
        ps.setDouble(7, charge);
        ps.setDouble(8, buspass);
	int i = ps.executeUpdate();
	ps.close();
        stmt.close();
	con.close();
    }
    catch(Exception e)
    {
        return("error");
    }
    return("success");	
}


public String TokenInsert(int routeno,int stageno,String studid,String accyear,String sem,String challan,double charge,int buspass,String trips,int nooftrips)
{
    String msg=null;
       Connection con=null;
    String sql="";
    try
    {
        con=connect();
        Statement stmt = con.createStatement();
	PreparedStatement ps;
	sql="insert into Student_Bus_Token(Route_No,Stage_No,Student_Id,Acc_Year,Sem,Challan_No,Charge,bustoken,trips,nooftrips) values(?,?,?,?,?,?,?,?,?,?)";
	ps=con.prepareStatement(sql);
	ps.setInt(1,routeno);
	ps.setInt(2,stageno);
	ps.setString(3,studid);
	ps.setString(4,accyear);
        ps.setString(5,sem);
        ps.setString(6,challan);
        ps.setDouble(7, charge);
        ps.setDouble(8, buspass);
        ps.setString(9,trips);
   	    ps.setInt(10,nooftrips);
	int i = ps.executeUpdate();
	ps.close();
        stmt.close();
	con.close();
    }
    catch(Exception e)
    {
        return("error");
    }
    return("success");	
}




 private Connection connect() 
    {
        
        Connection dbCon=null;
        try
        {
           
        Class.forName(pro.getProperty("dbDriver"));
        dbCon = DriverManager.getConnection(pro.getProperty("dbURL"),pro.getProperty("username"),pro.getProperty("password"));
        
        }
        catch(Exception e)
        {
            
        }
        return dbCon;
    }



}
