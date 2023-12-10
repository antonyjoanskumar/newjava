package sxcce;
import java.sql.*;
public class ChangeStudent
{


int count=0;


public String ChangeStrField(String rollno,String field, String value)
{
String quer="";
try
{
Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
Connection con = DriverManager.getConnection("jdbc:odbc:xavier","sxcce","tamil5india6");
quer="update student set "+field+"='"+value+"' where rollno="+rollno;
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