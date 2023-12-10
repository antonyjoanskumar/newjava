package sxcce;
import java.sql.*;
import dd.DBConnectionManager;
public class AttMarkInsert
	{
		String subjectcode="";
		String exam="";
		int examno=0;
		String examyear="";
		String staffid[]=new String[100];
		int semester=0;
		int hours[] = new int[200];
		int thours[] = new int[200];
		int rollno[] = new int[200];
		int count=0;
		int previous=0;
		int exset=0;
		int dno=0;
		public void Checkexamno()
		    {
	DBConnectionManager connMgr=null; Connection con =null;
	try
	{
                       connMgr = DBConnectionManager.getInstance(); 
                       con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;
					rs = stmt.executeQuery("select examno from examyear where year='"+examyear+"' and exam='"+exam+"' and semester="+semester+" and dno="+dno);
					if(rs.next())
						{
							examno=rs.getInt(1);
							previous=1;
						}
					rs.close();
					stmt.close();
					}catch(Exception e){}
finally{connMgr.freeConnection("xavier",con);}
}

public void Examno()
{
	DBConnectionManager connMgr=null; Connection con =null;
	try
	{
                       connMgr = DBConnectionManager.getInstance(); 
                       con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;
		    	if(previous==0)
		    		{
						rs = stmt.executeQuery("select max(examno) from examyear");
						if(rs.next())
							{
								examno=rs.getInt(1);
								examno++;
							}
						else examno=1;	
						rs.close();
		    		}
						stmt.close();
					}catch(Exception e){}
finally{connMgr.freeConnection("xavier",con);}
}
public void InsertExam()
{
	DBConnectionManager connMgr=null; Connection con =null;
	try
	{
                       connMgr = DBConnectionManager.getInstance(); 
                       con = connMgr.getConnection("xavier");
		    	if(previous==0)
		    	{
					PreparedStatement ps;
					ps = con.prepareStatement("insert into examyear values(?,?,?,?,?)");
					ps.setString(1,examyear);
					ps.setString(2,exam);
					ps.setInt(3,semester);
					ps.setInt(4,examno);
					ps.setInt(5,dno);
					int i=ps.executeUpdate();
					ps.close();
		    	}
					}catch(Exception e){}
finally{connMgr.freeConnection("xavier",con);}
}
		public void DeleteAttMark()
			{
	DBConnectionManager connMgr=null; Connection con =null;
	try
	{
                       connMgr = DBConnectionManager.getInstance(); 
                       con = connMgr.getConnection("xavier");
					PreparedStatement ps;
					ps = con.prepareStatement("delete from markatt where examno=? and subjectcode=?");
					ps.setInt(1,examno);
					ps.setString(2,subjectcode);
					int i=ps.executeUpdate();
					ps.close();
					
					}
					catch(Exception e){}
finally{connMgr.freeConnection("xavier",con);}
}
		public void InsertAttMark()
			{
	DBConnectionManager connMgr=null; Connection con =null;
	try
	{
                       connMgr = DBConnectionManager.getInstance(); 
                       con = connMgr.getConnection("xavier");
					PreparedStatement ps;
					for(int i=0;i<count;i++)
					{
						ps = con.prepareStatement("insert into markatt values(?,?,?,?,?)");
						ps.setInt(1,rollno[i]);
						ps.setInt(2,hours[i]);
						ps.setInt(3,thours[i]);
						ps.setString(4,subjectcode);
						ps.setInt(5,examno);
						int j=ps.executeUpdate();
					ps.close();
					}
					}catch(Exception e){}
finally{connMgr.freeConnection("xavier",con);}
}


public void InsertLogin(String date,String login)
			{
			int j;

	DBConnectionManager connMgr=null; Connection con =null;
	try
	{
                       connMgr = DBConnectionManager.getInstance(); 
                       con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;
					    PreparedStatement ps;
					    
						ps = con.prepareStatement("insert into markattlogin values(?,?,?,?)");
						ps.setInt(1,examno);
						ps.setString(2,subjectcode);
						ps.setString(3,date);
						ps.setString(4,login);
						j=ps.executeUpdate();
					    ps.close();
					    
					rs = stmt.executeQuery("select examno from examdate where examno="+examno+" and subcode='"+subjectcode+"'");
					if(rs.next())
						{
							examno=rs.getInt(1);
							exset=1;
						}
					rs.close();
					stmt.close();
					if(exset==1)
					{
						ps = con.prepareStatement("update examdate set status='C' where examno=? and subcode=?");
						ps.setInt(1,examno);
						ps.setString(2,subjectcode);
						j=ps.executeUpdate();
					    ps.close();
				    }					
				    	else
				    	{
				        ps = con.prepareStatement("insert into examdate values(?,?,?,?)");
						ps.setInt(1,examno);
						ps.setString(2,subjectcode);
						ps.setString(3,date);
						ps.setString(4,"C");
						j=ps.executeUpdate();
					    ps.close();
				    	}
					}
					catch(Exception e){}
finally{connMgr.freeConnection("xavier",con);}
}


	public void setSubjectcode(String subjectcode)
	{
		this.subjectcode=subjectcode;
	}

	public void setExam(String exam)
	{
		this.exam=exam;
	}

	public void setExamyear(String examyear)
	{
		this.examyear=examyear;
	}

	public void setSemester(int semester)
	{
		this.semester=semester;
	}
	public void setCount(int count)
	{
		this.count=count;
	}
	public void setRollno(int rollno[])
	{
		this.rollno=rollno;
	}
	public void setHours(int hours[])
	{
		this.hours=hours;
	}
	public void setThours(int thours[])
	{
		this.thours=thours;
	}

	public void setDno(int dno)
	{
		this.dno=dno;
	}
	
		public String[] getStaffid()
	   {
	   	return this.staffid;
	   }

	}
	