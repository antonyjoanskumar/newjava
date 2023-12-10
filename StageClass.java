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
public class StageClass 
{
    private Properties pro;
    private SQLbean sb;
    public StageClass() 
    {
         sb=new SQLbean();
        pro=new Properties();
        pro=sb.getConnString();
    }
    


public String Insert(int routeno,int stageno,String location,double charge)
{
   String msg=null;
       Connection con=null;
    try
    {
        con=connect();
        Statement stmt = con.createStatement();
	PreparedStatement ps;
	String sql="insert into Stage(Route_No,Stage_No,Location,charge) values(?,?,?,?)";
	ps=con.prepareStatement(sql);
	ps.setInt(1,routeno);
	ps.setInt(2,stageno);
	ps.setString(3,location);
	ps.setDouble(4,charge);
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
