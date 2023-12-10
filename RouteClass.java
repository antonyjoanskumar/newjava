
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Bus;
import java.util.*;
import java.sql.*;
import SQLdb.SQLbean;
/**
 *
 * @author Edwin
 */
public class RouteClass 
{
    private Properties pro;
    private SQLbean sb;
    public RouteClass() 
    {
        sb=new SQLbean();
        pro=new Properties();
        pro=sb.getConnString();
    }
    

public String Insert(int routeno,int busno,String regno,String route,String driver,String driverph,String manager,String managerph)
{
   String msg=null;
       Connection con=null;
       String sql="one";
    try
    {
        con=connect();
        Statement stmt = con.createStatement();
	PreparedStatement ps;
	sql="insert into Route(Route_No,Bus_No,Reg_No,Route,Driver_Name,Driver_Ph,Man_Name,Man_Ph) values(?,?,?,?,?,?,?,?)";
	ps=con.prepareStatement(sql);
	ps.setInt(1,routeno);
	ps.setInt(2,busno);
	ps.setString(3,regno);
	ps.setString(4,route);
	ps.setString(5,driver);
        ps.setString(6,driverph);
        ps.setString(7,manager);
        ps.setString(8,managerph);
	int i = ps.executeUpdate();
	ps.close();
        stmt.close();
	con.close();
    }
    catch(Exception e)
    {
        System.out.println(e.toString());
        return("error"+sql+e.toString());
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
