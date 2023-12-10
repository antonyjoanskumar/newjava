package sxcce;
import java.sql.*;
import dd.DBConnectionManager;
public class MarkInsert
	{
		String subjectcode="";
		String exam="";
		int examno=0;
		String examyear="";
		String staffid[]=new String[100];
		int semester=0;
		int mark[] = new int[200];
		int marka[] = new int[200];
		int rollno[] = new int[200];
		int qid[] = new int[200];
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
		public void DeleteMark()
			{
	DBConnectionManager connMgr=null; Connection con =null;
	try
	{
                       connMgr = DBConnectionManager.getInstance(); 
                       con = connMgr.getConnection("xavier");
					PreparedStatement ps;
					ps = con.prepareStatement("delete from mark where examno=? and subjectcode=?");
					ps.setInt(1,examno);
					ps.setString(2,subjectcode);
					int i=ps.executeUpdate();
					ps.close();
					
					}
					catch(Exception e){}
finally{connMgr.freeConnection("xavier",con);}
}

public void InsertMark()
{
	DBConnectionManager connMgr=null; Connection con =null;
	try
	{
                       connMgr = DBConnectionManager.getInstance(); 
                       con = connMgr.getConnection("xavier");
					PreparedStatement ps;
					for(int i=0;i<count;i++)
					{
						ps = con.prepareStatement("insert into mark values(?,?,?,?)");
						ps.setInt(1,rollno[i]);
						ps.setInt(2,mark[i]);
						ps.setString(3,subjectcode);
						ps.setInt(4,examno);
						int j=ps.executeUpdate();
					ps.close();
					}
					}catch(Exception e){}
finally{connMgr.freeConnection("xavier",con);}
}

public void InsertMarka()
{
	int m=0;
	DBConnectionManager connMgr=null; Connection con =null;
	try
	{
                       connMgr = DBConnectionManager.getInstance(); 
                       con = connMgr.getConnection("xavier");
					PreparedStatement ps;
					for(int i=0;i<count;i++)
					{
						ps = con.prepareStatement("insert into marka values(?,?,?,?,?)");
						ps.setInt(1,rollno[i]);
						ps.setInt(2,mark[i]);
						ps.setInt(3,marka[i]);
						ps.setString(4,subjectcode);
						ps.setInt(5,examno);
						int j=ps.executeUpdate();
						ps.close();

						m=mark[i]+marka[i];	
						if(m>100) m=100;
						if(mark[i]==-1 || marka[i]==-1) m=-1;
						if(mark[i]==-2 || marka[i]==-2) m=-2;
						
						ps = con.prepareStatement("insert into mark values(?,?,?,?)");
						ps.setInt(1,rollno[i]);
						ps.setInt(2,m);
						ps.setString(3,subjectcode);
						ps.setInt(4,examno);
						j=ps.executeUpdate();
					ps.close();
					
					}
					}catch(Exception e){}
finally{connMgr.freeConnection("xavier",con);}
}


public void UpdateMark()
{
	DBConnectionManager connMgr=null; Connection con =null;
	try
	{
                       connMgr = DBConnectionManager.getInstance(); 
                       con = connMgr.getConnection("xavier");
					PreparedStatement ps;
					for(int i=0;i<count;i++)
					{
						ps = con.prepareStatement("update mark set mark=? where rollno=? and subjectcode=? and examno=? ");
						ps.setInt(1,mark[i]);
						ps.setInt(2,rollno[i]);
						ps.setString(3,subjectcode);
						ps.setInt(4,examno);
						int j=ps.executeUpdate();
					ps.close();
					}
					}catch(Exception e){}
finally{connMgr.freeConnection("xavier",con);}
}

public void UpdateMarka()
{
	int m=0;
	DBConnectionManager connMgr=null; Connection con =null;
	try
	{
                       connMgr = DBConnectionManager.getInstance(); 
                       con = connMgr.getConnection("xavier");
					PreparedStatement ps;
					for(int i=0;i<count;i++)
					{
						ps = con.prepareStatement("update marka set mark=?, marka=? where rollno=? and subjectcode=? and examno=? ");
						ps.setInt(1,mark[i]);
						ps.setInt(2,marka[i]);
						ps.setInt(3,rollno[i]);
						ps.setString(4,subjectcode);
						ps.setInt(5,examno);
						int j=ps.executeUpdate();
						ps.close();

						m=mark[i]+marka[i];	
						if(m>100) m=100;
						if(mark[i]==-1 || marka[i]==-1) m=-1;
						if(mark[i]==-2 || marka[i]==-2) m=-2;

						ps = con.prepareStatement("update mark set mark=? where rollno=? and subjectcode=? and examno=? ");
						ps.setInt(1,m);
						ps.setInt(2,rollno[i]);
						ps.setString(3,subjectcode);
						ps.setInt(4,examno);
						j=ps.executeUpdate();
						ps.close();
					}
					}catch(Exception e){}
finally{connMgr.freeConnection("xavier",con);}
}

public String InsertMarkcopo(int rno,int totmarks,int addmarks)
{
	int m=0;
	int j=0;
	String err="suc";
	DBConnectionManager connMgr=null; Connection con =null;
	try
	{
                       connMgr = DBConnectionManager.getInstance(); 
                       con = connMgr.getConnection("xavier");
					PreparedStatement ps;
					for(int i=0;i<count;i++)
					{
	try
	{
						ps = con.prepareStatement("insert into markcopo(qid,rollno,marks) values(?,?,?)");
						ps.setInt(1,qid[i]);
						ps.setInt(2,rollno[i]);
						ps.setInt(3,mark[i]);
						j=ps.executeUpdate();
					ps.close();
	}catch(Exception e){err=e.toString();}
					
					}
	try
	{

						ps = con.prepareStatement("insert into marka values(?,?,?,?,?)");
						ps.setInt(1,rno);
						ps.setInt(2,totmarks);
						ps.setInt(3,addmarks);
						ps.setString(4,subjectcode);
						ps.setInt(5,examno);
						j=ps.executeUpdate();
						ps.close();
	}catch(Exception e){err=e.toString();}

						m=totmarks+addmarks;	
						if(m>100) m=100;
						if(totmarks==-1 || addmarks==-1) m=-1;
						if(totmarks==-2 || addmarks==-2) m=-2;
	try
	{
						
						ps = con.prepareStatement("insert into mark values(?,?,?,?)");
						ps.setInt(1,rno);
						ps.setInt(2,m);
						ps.setString(3,subjectcode);
						ps.setInt(4,examno);
						j=ps.executeUpdate();
					ps.close();
	}catch(Exception e){err=e.toString();}
					
					}catch(Exception e){err=e.toString();}
finally{connMgr.freeConnection("xavier",con);}
return(err);
}

public String UpdateMarkcopo(int rno,int totmarks,int addmarks)
{
	int m=0;
		int j=0;

		String err="suc";

	DBConnectionManager connMgr=null; Connection con =null;
	try
	{
                       connMgr = DBConnectionManager.getInstance(); 
                       con = connMgr.getConnection("xavier");
					PreparedStatement ps;
					for(int i=0;i<count;i++)
					{
	try
	{
						ps = con.prepareStatement("update markcopo set marks=? where rollno=? and qid=? ");
						ps.setInt(1,mark[i]);
						ps.setInt(2,rollno[i]);
						ps.setInt(3,qid[i]);
						j=ps.executeUpdate();
						ps.close();
	}catch(Exception e){err=e.toString();}
						
//						if(mark[i]>0) totmark+=mark[i];
					}
//						if(marka[0]>0) addmarks=marka[0];
//						rno=rollno[0];
	try
	{

						ps = con.prepareStatement("update marka set mark=?, marka=? where rollno=? and subjectcode=? and examno=? ");
						ps.setInt(1,totmarks);
						ps.setInt(2,addmarks);
						ps.setInt(3,rno);
						ps.setString(4,subjectcode);
						ps.setInt(5,examno);
						j=ps.executeUpdate();
						ps.close();
	}catch(Exception e){err=e.toString();}

						m=totmarks+addmarks;	
						if(m>100) m=100;
						if(totmarks==-1 || addmarks==-1) m=-1;
						if(totmarks==-2 || addmarks==-2) m=-2;
	try
	{

						ps = con.prepareStatement("update mark set mark=? where rollno=? and subjectcode=? and examno=? ");
						ps.setInt(1,m);
						ps.setInt(2,rno);
						ps.setString(3,subjectcode);
						ps.setInt(4,examno);
						j=ps.executeUpdate();
						ps.close();
	}catch(Exception e){err=e.toString();}
						
					}catch(Exception e){err=e.toString();}
finally{connMgr.freeConnection("xavier",con);}
return(err);
}

public void InsertLogin(String date,String login)
			{
			int j;
String status="E";
	DBConnectionManager connMgr=null; Connection con =null;
	try
	{
                       connMgr = DBConnectionManager.getInstance(); 
                       con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;
					    PreparedStatement ps;
	try
	{
					    
						ps = con.prepareStatement("insert into marklogin values(?,?,?,?)");
						ps.setInt(1,examno);
						ps.setString(2,subjectcode);
						ps.setString(3,date);
						ps.setString(4,login);
						j=ps.executeUpdate();
					    ps.close();
					}catch(Exception e){}
					    
					rs = stmt.executeQuery("select examno,status from examdate where examno="+examno+" and subcode='"+subjectcode+"'");
					if(rs.next())
						{
							examno=rs.getInt(1);
							status=rs.getString(2);
							exset=1;
						}
					rs.close();
					stmt.close();
					if(exset==1)
					{
						ps = con.prepareStatement("update examdate set status='"+status+"' where examno=? and subcode=?");
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
						ps.setString(4,"E");
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
	public void setQid(int qid[])
	{
		this.qid=qid;
	}
	public void setMark(int mark[])
	{
		this.mark=mark;
	}
	public void setMarka(int marka[])
	{
		this.marka=marka;
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
	