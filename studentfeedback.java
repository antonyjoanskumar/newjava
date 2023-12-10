package sxcce;
import java.sql.*;
import dd.DBConnectionManager;
public class studentfeedback
	{
		int rollno=0;
		String staffid="";
		int id=0;
		String answers[] = new String[100];
		int noofquestions=0;
		String subjectcode="";
		String semester="";
		int objective[] = new int[100];
/*

public String Insertenteredornot()
{
try
{
 DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
PreparedStatement ps;
ps = con.prepareStatement("insert into feedbackenteredornot values (?,?,?,?,?)");
ps.setInt(1,rollno);
ps.setString(2,staffid);
ps.setInt(3,id);
ps.setString(4,subjectcode);
ps.setString(5,semester);
int j = ps.executeUpdate();
ps.close();
connMgr.freeConnection("xavier",con);
 
}catch(Exception e){return e.toString();}
return("success");
}

public void Insertdata(int ansa,int ansb,int ansc,int ansd,int anse,int found,int i)
{
	try{
DBConnectionManager connMgr;
connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
PreparedStatement ps;

  if(found==0)
     {
	ps = con.prepareStatement("insert into stafffeedback values (?,?,?,?,?,?,?,?,?,?)");
	ps.setInt(1,id);
	ps.setString(2,staffid);
	ps.setInt(3,ansa);
	ps.setInt(4,ansb);
	ps.setInt(5,ansc);
	ps.setInt(6,ansd);
	ps.setInt(7,anse);
	ps.setInt(8,(i+1));
	ps.setString(9,subjectcode);
	ps.setString(10,semester);
	int j = ps.executeUpdate();
	ps.close();
     }
  else 
     {
	ps = con.prepareStatement("update stafffeedback set ansa=?,ansb=?,ansc=?,ansd=?,anse=? where id=? and staffid=? and questionno=? and subjectcode=? and oddeven=?");
	ps.setInt(1,ansa);
	ps.setInt(2,ansb);
	ps.setInt(3,ansc);
	ps.setInt(4,ansd);
	ps.setInt(5,anse);
	ps.setInt(6,id);
	ps.setString(7,staffid);
	ps.setInt(8,(i+1));
	ps.setString(9,subjectcode);
	ps.setString(10,semester);
	int j = ps.executeUpdate();
	ps.close();
     }
connMgr.freeConnection("xavier",con);
}catch(Exception e){}
}

public void Insertnonobjective(int i,String remarks)
{
	try{
DBConnectionManager connMgr;
connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
PreparedStatement ps;
	ps = con.prepareStatement("insert into remarks values (?,?,?,?,?,?,?)");
ps.setInt(1,id);
ps.setString(2,staffid);
ps.setString(3,remarks);
ps.setString(4,subjectcode);
ps.setInt(5,(i+1));
ps.setInt(6,0);
ps.setString(7,semester);
	int j;
	if(remarks.length()>1)
	j = ps.executeUpdate();
	ps.close();
connMgr.freeConnection("xavier",con);
}catch(Exception e){}
}

*/
public String Insert()
{
	String quer="";

try
{
DBConnectionManager connMgr;
connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
Statement stmt = null;
ResultSet rs;
PreparedStatement ps;

for(int i=0;i<noofquestions;i++)
  {int found=0;

try
{
  
if(objective[i]==1){  
  stmt = con.createStatement();
  quer="select * from stafffeedback where id="+id+" and staffid='"+staffid+"'  and subjectcode='"+subjectcode+"' and questionno="+(i+1)+"  and oddeven='"+semester+"'";
  rs = stmt.executeQuery(quer);
int ansa=0,ansb=0,ansc=0,ansd=0,anse=0;
  if(rs.next())
  {
  ansa+=rs.getInt(3);
  ansb+=rs.getInt(4);
  ansc+=rs.getInt(5);
  ansd+=rs.getInt(6);
  anse+=rs.getInt(7);
  found++;
  }
  rs.close();
  stmt.close();
  
  if(Integer.parseInt(answers[i])==1)ansa++;
   else if(Integer.parseInt(answers[i])==2)ansb++;
   else if(Integer.parseInt(answers[i])==3)ansc++;
   else if(Integer.parseInt(answers[i])==4)ansd++;
   else if(Integer.parseInt(answers[i])==5)anse++;
   
   //Insertdata(ansa,ansb,ansc,ansd,anse,found,i);

  if(found==0)
     {
	ps = con.prepareStatement("insert into stafffeedback values (?,?,?,?,?,?,?,?,?,?)");
	ps.setInt(1,id);
	ps.setString(2,staffid);
	ps.setInt(3,ansa);
	ps.setInt(4,ansb);
	ps.setInt(5,ansc);
	ps.setInt(6,ansd);
	ps.setInt(7,anse);
	ps.setInt(8,(i+1));
	ps.setString(9,subjectcode);
	ps.setString(10,semester);
	int j = ps.executeUpdate();
	ps.close();
     }
  else 
     {
	ps = con.prepareStatement("update stafffeedback set ansa=?,ansb=?,ansc=?,ansd=?,anse=? where id=? and staffid=? and questionno=? and subjectcode=? and oddeven=?");
	ps.setInt(1,ansa);
	ps.setInt(2,ansb);
	ps.setInt(3,ansc);
	ps.setInt(4,ansd);
	ps.setInt(5,anse);
	ps.setInt(6,id);
	ps.setString(7,staffid);
	ps.setInt(8,(i+1));
	ps.setString(9,subjectcode);
	ps.setString(10,semester);
	int j = ps.executeUpdate();
	ps.close();
     }   
   
  }
  else
  {
  	//Insertnonobjective(i,answers[i]);
  		ps = con.prepareStatement("insert into remarks values (?,?,?,?,?,?,?)");
ps.setInt(1,id);
ps.setString(2,staffid);
ps.setString(3,answers[i]);
ps.setString(4,subjectcode);
ps.setInt(5,(i+1));
ps.setInt(6,0);
ps.setString(7,semester);
	int j;
	if(answers[i].length()>1)
	j = ps.executeUpdate();
	ps.close();

  }
  }catch(Exception e){quer=e.toString();}

}
try
{
ps = con.prepareStatement("insert into feedbackenteredornot values (?,?,?,?,?)");
ps.setInt(1,rollno);
ps.setString(2,staffid);
ps.setInt(3,id);
ps.setString(4,subjectcode);
ps.setString(5,semester);
int j = ps.executeUpdate();
ps.close();
}catch(Exception e){quer=e.toString();}

connMgr.freeConnection("xavier",con);
}catch(Exception e){return e.toString();}
return("success"+quer+staffid);
}


 public void setanswers(String[] answers) 
{ 
this.answers = answers; 
} 
public String[] getanswers() 
{ 
return this.answers; 
} 

 public void setobjective(int[] objective) 
{ 
this.objective = objective; 
} 
public int[] getobjective() 
{ 
return this.objective; 
} 


public void setid(int id) 
{ 
this.id = id; 
} 
public int getid() 
{ 
return this.id; 
} 
public void setnoofquestions(int noofquestions) 
{ 
this.noofquestions = noofquestions; 
} 
public int getnoofquestions() 
{ 
return this.noofquestions; 
} 
public void setrollno(int rollno) 
{ 
this.rollno = rollno; 
} 
public int getrollno() 
{ 
return this.rollno; 
} 

public void setstaffid(String staffid) 
{ 
this.staffid = staffid; 
} 
public String getstaffid() 
{ 
return this.staffid; 
} 

public void setsemester(String semester) 
{ 
this.semester = semester; 
} 
public String getsemester() 
{ 
return this.semester; 
} 

public void setsubjectcode(String subjectcode) 
{ 
this.subjectcode = subjectcode; 
} 
public String getsubjectcode() 
{ 
return this.subjectcode; 
} 


	}	