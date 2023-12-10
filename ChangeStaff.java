package sxcce;
import java.sql.*;
public class ChangeStaff
{


int count=0;


public String ChangeStrField(String staffid,String field, String value)
{
String quer="";
try
{
Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
Connection con = DriverManager.getConnection("jdbc:odbc:xavier","sxcce","tamil5india6");
quer="update staff set "+field+"='"+value+"' where staffid='"+staffid+"'";
PreparedStatement ps = con.prepareStatement(quer);
//ps.setString(1,field);
//ps.setString(2,value);
//ps.setString(3,rollno);
int i = ps.executeUpdate();
ps.close();
con.close();
}catch(Exception e){return e.toString()+quer;}
return "suc";
}

public String ChangeIPField(String staffid,String field, String value)
{
String quer="";
try
{
Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
Connection con = DriverManager.getConnection("jdbc:odbc:xavier","sxcce","tamil5india6");
quer="update staffip set "+field+"='"+value+"' where staffid='"+staffid+"'";
PreparedStatement ps = con.prepareStatement(quer);
//ps.setString(1,field);
//ps.setString(2,value);
//ps.setString(3,rollno);
int i = ps.executeUpdate();
ps.close();
con.close();
}catch(Exception e){return e.toString()+quer;}
return "suc";
}


}