public String QuestionPrintInt(String exam,int unita,int unitb,String subcode,String accyear,String oddeven,int parta,int partb,int partc)
{
int i=0;
int k=0;
count=0;
int f1=0;
int f2=0;
int f3=0;
int qno=0;
int p=0;
int u=0;
sql="...";	
try
{
DBConnectionManager connMgr;
 connMgr = DBConnectionManager.getInstance();
Connection con = connMgr.getConnection("xavier");
  					    Statement stmt = con.createStatement();
					    ResultSet rs;
						PreparedStatement ps;

sql="select * from qbank where unit="+unita+" and exam1 is not null and subcode='"+subcode+"' and oddeven='"+oddeven+"' order by part,qno,sdiv";
//sql="select * from qbank where unit="+unita+" and exam1 is not null and subcode='"+subcode+"' and academicyear='"+accyear+"' and oddeven='"+oddeven+"' order by part,qno,sdiv";
rs = stmt.executeQuery(sql);
if(rs.next()) f1=1;
rs.close();

String semin="(1,3,5,7)";
if(oddeven.equals("ODD")) semin="(1,3,5,7)";
else
 semin="(2,4,6,8)";

sql="select semester,dname,sf,subjectcode,subjectname,abbr from syllabus s,subjectidentify si,department d where d.dno=si.dno and s.id=si.id and subjectcode='"+subcode+"' and academicyear='"+accyear+"' and semester in "+semin;
rs = stmt.executeQuery(sql);

count1=0;
while(rs.next())
{
	for(i=1;i<=6;i++)
	{
		report3[i-1][count1]=rs.getString(i);
	}
	count1++;
}
rs.close();

sql="select si.semester,dname,sf,subjectcode,subjectname,abbr,examno from syllabus s,subjectidentify si,department d,examyear ey where ey.year=academicyear "
+" and ey.semester=si.semester and ey.dno=si.dno and  d.dno=si.dno and s.id=si.id and subjectcode='"+subcode+"' and academicyear='"+accyear+"' and ey.exam='"+exam+"' and si.semester in "+semin;
rs = stmt.executeQuery(sql);

count2=0;
while(rs.next())
{
	for(i=1;i<=7;i++)
	{
		report4[i-1][count2]=rs.getString(i);
	}
	count2++;
}
rs.close();

if(count1!=count2) f3=1;

if((f1==0) && (f3==0))
{


//for(i=1;i<=parta;i++)
for(i=1;i<=parta/2;i++)
{
f2=0;
sql="select top 1 qno,sdiv,qid,maxmarks,clevel,co from qbank where unit="+unita+" and subcode='"+subcode+"' and part=1 and exam1 is null and oddeven='"+oddeven+"' order by NEWID()"; 
//sql="select top 1 qno,sdiv,qid,maxmarks,clevel,co from qbank where unit="+unita+" and subcode='"+subcode+"' and part=1 and exam1 is null and academicyear='"+accyear+"' and oddeven='"+oddeven+"' order by NEWID()"; 
rs = stmt.executeQuery(sql);
if(rs.next()) {
    qno=Integer.parseInt(rs.getString(1));
	for(k=2;k<=6;k++)
	{
		report1[k]=rs.getString(k);
	}
//	u=Integer.parseInt(rs.getString(7));
    f2=1; 
}
rs.close();
if(f2==1)
{
sql="update qbank set exam1=?, exam1qno=?, exam1opt=0 where unit="+unita+" and qno=? and subcode=? and part=1 and oddeven=?";
					ps=con.prepareStatement(sql);
					ps.setString(1,unita+"");
					ps.setInt(2,i);
					ps.setInt(3,qno);
					ps.setString(4,	subcode);
					ps.setString(5,	oddeven);
					p = ps.executeUpdate();
					ps.close();
for(k=0;k<count2;k++)
{
sql="insert into markquestion (examno,qno,part,opt,sdiv,question,maxmarks,clevel,co,subcode,choice) values(?,?,?,?,?,?,?,?,?,?,1)";
					ps=con.prepareStatement(sql);
					ps.setString(1,report4[6][k]+"");
					ps.setInt(2,i);
					ps.setInt(3,1);
					ps.setInt(4,0);
					ps.setString(5,	report1[2]);
					ps.setString(6,	report1[3]);
					ps.setString(7,	report1[4]);
					ps.setString(8,	report1[5]);
					ps.setString(9,	report1[6]);
					ps.setString(10, subcode);
					p = ps.executeUpdate();
					ps.close();
}
}
}

for(i=(parta/2)+1;i<=parta;i++)
{
f2=0;
sql="select top 1 qno,sdiv,qid,maxmarks,clevel,co from qbank where unit="+unitb+" and subcode='"+subcode+"' and part=1 and exam1 is null and oddeven='"+oddeven+"' order by NEWID()"; 
//sql="select top 1 qno,sdiv,qid,maxmarks,clevel,co from qbank where unit="+unitb+" and subcode='"+subcode+"' and part=1 and exam1 is null and academicyear='"+accyear+"' and oddeven='"+oddeven+"' order by NEWID()"; 
rs = stmt.executeQuery(sql);
if(rs.next()) {
    qno=Integer.parseInt(rs.getString(1));
	for(k=2;k<=6;k++)
	{
		report1[k]=rs.getString(k);
	}
//	u=Integer.parseInt(rs.getString(7));
    f2=1; 
}
rs.close();
if(f2==1)
{
sql="update qbank set exam1=?, exam1qno=?, exam1opt=0 where unit="+unitb+" and qno=? and subcode=? and part=1 and oddeven=?";
					ps=con.prepareStatement(sql);
					ps.setString(1,unitb+"");
					ps.setInt(2,i);
					ps.setInt(3,qno);
					ps.setString(4,	subcode);
					ps.setString(5,	oddeven);
					p = ps.executeUpdate();
					ps.close();
for(k=0;k<count2;k++)
{
sql="insert into markquestion (examno,qno,part,opt,sdiv,question,maxmarks,clevel,co,subcode,choice) values(?,?,?,?,?,?,?,?,?,?,1)";
					ps=con.prepareStatement(sql);
					ps.setString(1,report4[6][k]+"");
					ps.setInt(2,i);
					ps.setInt(3,1);
					ps.setInt(4,0);
					ps.setString(5,	report1[2]);
					ps.setString(6,	report1[3]);
					ps.setString(7,	report1[4]);
					ps.setString(8,	report1[5]);
					ps.setString(9,	report1[6]);
					ps.setString(10, subcode);
					p = ps.executeUpdate();
					ps.close();
}
}
}



for(i=1;i<=partb/2;i++)
{
f2=0;
//sql="select top 1 qno,sdiv,qid,maxmarks,clevel,co from qbank where unit="+unit+" and subcode='"+subcode+"' and part=2 and exam1 is null and academicyear='"+accyear+"' and oddeven='"+oddeven+"' order by NEWID()"; 

sql=" select top 1 qno,sdiv,qid,maxmarks,clevel,co from qbank where unit="+unita+" and subcode='"+subcode+"' "
+" and part=2 and exam1 is null and oddeven='"+oddeven+"' " 
//+" and part=2 and exam1 is null and academicyear='"+accyear+"' and oddeven='"+oddeven+"' " 
+" and qno <= (select max(qno)/2 from qbank where unit="+unita+" and subcode='"+subcode+"' "
+" and part=2 and exam1 is null and oddeven='"+oddeven+"') "
//+" and part=2 and exam1 is null and academicyear='"+accyear+"' and oddeven='"+oddeven+"') "
+" order by NEWID()";

rs = stmt.executeQuery(sql);
if(rs.next()) {
    qno=Integer.parseInt(rs.getString(1));
	for(k=2;k<=6;k++)
	{
		report1[k]=rs.getString(k);
	}
	 f2=1; 
}
rs.close();
if(f2==1)
{
sql="update qbank set exam1=?, exam1qno=?, exam1opt=1 where unit="+unita+" and qno=? and subcode=? and part=2 and oddeven=?";
					ps=con.prepareStatement(sql);
					ps.setString(1,unita+"");
					ps.setInt(2,i+parta);
					ps.setInt(3,qno);
					ps.setString(4,	subcode);
					ps.setString(5,	oddeven);
					p = ps.executeUpdate();
					ps.close();
for(k=0;k<count2;k++)
{
sql="insert into markquestion (examno,qno,part,opt,sdiv,question,maxmarks,clevel,co,subcode,choice) values(?,?,?,?,?,?,?,?,?,?,1)";
					ps=con.prepareStatement(sql);
					ps.setString(1,report4[6][k]+"");
					ps.setInt(2,i+parta);
					ps.setInt(3,2);
					ps.setInt(4,1);
					ps.setString(5,	report1[2]);
					ps.setString(6,	report1[3]);
					ps.setString(7,	report1[4]);
					ps.setString(8,	report1[5]);
					ps.setString(9,	report1[6]);
					ps.setString(10, subcode);
					p = ps.executeUpdate();
					ps.close();
}

}
f2=0;
//sql="select top 1 qno,sdiv,qid,maxmarks,clevel,co from qbank where unit="+unita+" and subcode='"+subcode+"' and part=2 and exam1 is null and academicyear='"+accyear+"' and oddeven='"+oddeven+"' order by NEWID()"; 

sql=" select top 1 qno,sdiv,qid,maxmarks,clevel,co from qbank where unit="+unita+" and subcode='"+subcode+"' "
+" and part=2 and exam1 is null and oddeven='"+oddeven+"' " 
//+" and part=2 and exam1 is null and academicyear='"+accyear+"' and oddeven='"+oddeven+"' " 
+" and qno > (select max(qno)/2 from qbank where unit="+unita+" and subcode='"+subcode+"' "
+" and part=2 and exam1 is null and oddeven='"+oddeven+"') "
//+" and part=2 and exam1 is null and academicyear='"+accyear+"' and oddeven='"+oddeven+"') "
+" order by NEWID()";

rs = stmt.executeQuery(sql);
if(rs.next()) {
    qno=Integer.parseInt(rs.getString(1));
	for(k=2;k<=6;k++)
	{
		report1[k]=rs.getString(k);
	}
	 f2=1; 
}
rs.close();
if(f2==1)
{
sql="update qbank set exam1=?, exam1qno=?, exam1opt=2 where unit="+unita+" and qno=? and subcode=? and part=2 and oddeven=?";
					ps=con.prepareStatement(sql);
					ps.setString(1,unita+"");
					ps.setInt(2,i+parta);
					ps.setInt(3,qno);
					ps.setString(4,	subcode);
					ps.setString(5,	oddeven);
					p = ps.executeUpdate();
					ps.close();
for(k=0;k<count2;k++)
{
sql="insert into markquestion (examno,qno,part,opt,sdiv,question,maxmarks,clevel,co,subcode,choice) values(?,?,?,?,?,?,?,?,?,?,1)";
					ps=con.prepareStatement(sql);
					ps.setString(1,report4[6][k]+"");
					ps.setInt(2,i+parta);
					ps.setInt(3,2);
					ps.setInt(4,2);
					ps.setString(5,	report1[2]);
					ps.setString(6,	report1[3]);
					ps.setString(7,	report1[4]);
					ps.setString(8,	report1[5]);
					ps.setString(9,	report1[6]);
					ps.setString(10, subcode);
					p = ps.executeUpdate();
					ps.close();
}
					
}
}

for(i=(partb/2)+1;i<=partb;i++)
{
f2=0;
//sql="select top 1 qno,sdiv,qid,maxmarks,clevel,co from qbank where unit="+unit+" and subcode='"+subcode+"' and part=2 and exam1 is null and academicyear='"+accyear+"' and oddeven='"+oddeven+"' order by NEWID()"; 

sql=" select top 1 qno,sdiv,qid,maxmarks,clevel,co from qbank where unit="+unitb+" and subcode='"+subcode+"' "
+" and part=2 and exam1 is null and oddeven='"+oddeven+"' " 
//+" and part=2 and exam1 is null and academicyear='"+accyear+"' and oddeven='"+oddeven+"' " 
+" and qno <= (select max(qno)/2 from qbank where unit="+unitb+" and subcode='"+subcode+"' "
+" and part=2 and exam1 is null and oddeven='"+oddeven+"') "
//+" and part=2 and exam1 is null and academicyear='"+accyear+"' and oddeven='"+oddeven+"') "
+" order by NEWID()";

rs = stmt.executeQuery(sql);
if(rs.next()) {
    qno=Integer.parseInt(rs.getString(1));
	for(k=2;k<=6;k++)
	{
		report1[k]=rs.getString(k);
	}
	 f2=1; 
}
rs.close();
if(f2==1)
{
sql="update qbank set exam1=?, exam1qno=?, exam1opt=1 where unit="+unitb+" and qno=? and subcode=? and part=2 and oddeven=?";
					ps=con.prepareStatement(sql);
					ps.setString(1,unitb+"");
					ps.setInt(2,i+parta);
					ps.setInt(3,qno);
					ps.setString(4,	subcode);
					ps.setString(5,	oddeven);
					p = ps.executeUpdate();
					ps.close();
for(k=0;k<count2;k++)
{
sql="insert into markquestion (examno,qno,part,opt,sdiv,question,maxmarks,clevel,co,subcode,choice) values(?,?,?,?,?,?,?,?,?,?,1)";
					ps=con.prepareStatement(sql);
					ps.setString(1,report4[6][k]+"");
					ps.setInt(2,i+parta);
					ps.setInt(3,2);
					ps.setInt(4,1);
					ps.setString(5,	report1[2]);
					ps.setString(6,	report1[3]);
					ps.setString(7,	report1[4]);
					ps.setString(8,	report1[5]);
					ps.setString(9,	report1[6]);
					ps.setString(10, subcode);
					p = ps.executeUpdate();
					ps.close();
}

}
f2=0;
//sql="select top 1 qno,sdiv,qid,maxmarks,clevel,co from qbank where unit="+unita+" and subcode='"+subcode+"' and part=2 and exam1 is null and academicyear='"+accyear+"' and oddeven='"+oddeven+"' order by NEWID()"; 

sql=" select top 1 qno,sdiv,qid,maxmarks,clevel,co from qbank where unit="+unitb+" and subcode='"+subcode+"' "
+" and part=2 and exam1 is null and oddeven='"+oddeven+"' " 
//+" and part=2 and exam1 is null and academicyear='"+accyear+"' and oddeven='"+oddeven+"' " 
+" and qno > (select max(qno)/2 from qbank where unit="+unitb+" and subcode='"+subcode+"' "
+" and part=2 and exam1 is null and oddeven='"+oddeven+"') "
//+" and part=2 and exam1 is null and academicyear='"+accyear+"' and oddeven='"+oddeven+"') "
+" order by NEWID()";

rs = stmt.executeQuery(sql);
if(rs.next()) {
    qno=Integer.parseInt(rs.getString(1));
	for(k=2;k<=6;k++)
	{
		report1[k]=rs.getString(k);
	}
	 f2=1; 
}
rs.close();
if(f2==1)
{
sql="update qbank set exam1=?, exam1qno=?, exam1opt=2 where unit="+unitb+" and qno=? and subcode=? and part=2 and oddeven=?";
					ps=con.prepareStatement(sql);
					ps.setString(1,unitb+"");
					ps.setInt(2,i+parta);
					ps.setInt(3,qno);
					ps.setString(4,	subcode);
					ps.setString(5,	oddeven);
					p = ps.executeUpdate();
					ps.close();
for(k=0;k<count2;k++)
{
sql="insert into markquestion (examno,qno,part,opt,sdiv,question,maxmarks,clevel,co,subcode,choice) values(?,?,?,?,?,?,?,?,?,?,1)";
					ps=con.prepareStatement(sql);
					ps.setString(1,report4[6][k]+"");
					ps.setInt(2,i+parta);
					ps.setInt(3,2);
					ps.setInt(4,2);
					ps.setString(5,	report1[2]);
					ps.setString(6,	report1[3]);
					ps.setString(7,	report1[4]);
					ps.setString(8,	report1[5]);
					ps.setString(9,	report1[6]);
					ps.setString(10, subcode);
					p = ps.executeUpdate();
					ps.close();
}
					
}
}

for(i=1;i<=partc;i++)
{
f2=0;
sql="select top 1 qno,sdiv,qid,maxmarks,clevel,co from qbank where unit="+unita+" and subcode='"+subcode+"' and part=3 and exam1 is null and oddeven='"+oddeven+"' order by NEWID()"; 
//sql="select top 1 qno,sdiv,qid,maxmarks,clevel,co from qbank where unit="+unita+" and subcode='"+subcode+"' and part=3 and exam1 is null and academicyear='"+accyear+"' and oddeven='"+oddeven+"' order by NEWID()"; 
rs = stmt.executeQuery(sql);
if(rs.next()) {
    qno=Integer.parseInt(rs.getString(1));
	for(k=2;k<=6;k++)
	{
		report1[k]=rs.getString(k);
	}
	 f2=1; 
}
rs.close();
if(f2==1)
{
sql="update qbank set exam1=?, exam1qno=?, exam1opt=1 where unit="+unita+" and qno=? and subcode=? and part=3 and oddeven=?";
					ps=con.prepareStatement(sql);
					ps.setString(1,unita+"");
					ps.setInt(2,i+parta+partb);
					ps.setInt(3,qno);
					ps.setString(4,	subcode);
					ps.setString(5,	oddeven);
					p = ps.executeUpdate();
					ps.close();
for(k=0;k<count2;k++)
{
sql="insert into markquestion (examno,qno,part,opt,sdiv,question,maxmarks,clevel,co,subcode,choice) values(?,?,?,?,?,?,?,?,?,?,1)";
					ps=con.prepareStatement(sql);
					ps.setString(1,report4[6][k]+"");
					ps.setInt(2,i+parta+partb);
					ps.setInt(3,3);
					ps.setInt(4,1);
					ps.setString(5,	report1[2]);
					ps.setString(6,	report1[3]);
					ps.setString(7,	report1[4]);
					ps.setString(8,	report1[5]);
					ps.setString(9,	report1[6]);
					ps.setString(10, subcode);
					p = ps.executeUpdate();
					ps.close();
}
					
}
f2=0;
sql="select top 1 qno,sdiv,qid,maxmarks,clevel,co from qbank where unit="+unitb+" and subcode='"+subcode+"' and part=3 and exam1 is null and oddeven='"+oddeven+"' order by NEWID()"; 
//sql="select top 1 qno,sdiv,qid,maxmarks,clevel,co from qbank where unit="+unitb+" and subcode='"+subcode+"' and part=3 and exam1 is null and academicyear='"+accyear+"' and oddeven='"+oddeven+"' order by NEWID()"; 
rs = stmt.executeQuery(sql);
if(rs.next()) {
    qno=Integer.parseInt(rs.getString(1));
	for(k=2;k<=6;k++)
	{
		report1[k]=rs.getString(k);
	}
	 f2=1; 
}
rs.close();
if(f2==1)
{
sql="update qbank set exam1=?, exam1qno=?, exam1opt=2 where unit="+unitb+" and qno=? and subcode=? and part=3 and oddeven=?";
					ps=con.prepareStatement(sql);
					ps.setString(1,unitb+"");
					ps.setInt(2,i+parta+partb);
					ps.setInt(3,qno);
					ps.setString(4,	subcode);
					ps.setString(5,	oddeven);
					p = ps.executeUpdate();
					ps.close();
for(k=0;k<count2;k++)
{
sql="insert into markquestion (examno,qno,part,opt,sdiv,question,maxmarks,clevel,co,subcode,choice) values(?,?,?,?,?,?,?,?,?,?,1)";
					ps=con.prepareStatement(sql);
					ps.setString(1,report4[6][k]+"");
					ps.setInt(2,i+parta+partb);
					ps.setInt(3,3);
					ps.setInt(4,2);
					ps.setString(5,	report1[2]);
					ps.setString(6,	report1[3]);
					ps.setString(7,	report1[4]);
					ps.setString(8,	report1[5]);
					ps.setString(9,	report1[6]);
					ps.setString(10, subcode);
					p = ps.executeUpdate();
					ps.close();
}
}
}
}

sql="select * from qbank where unit in ("+unita+","+unitb+") and exam1 in ("+unita+","+unitb+") and subcode='"+subcode+"' and oddeven='"+oddeven+"' order by part,exam1qno,exam1opt";
//sql="select * from qbank where unit in ("+unita+","+unitb+") and exam1 in ("+unita+","+unitb+") and subcode='"+subcode+"' and academicyear='"+accyear+"' and oddeven='"+oddeven+"' order by part,exam1qno,exam1opt";
rs = stmt.executeQuery(sql);

while(rs.next())
{
	for(i=1;i<=20;i++)
	{
		report[i-1][count]=rs.getString(i);
	}
	count++;
}
rs.close();



stmt.close();
connMgr.freeConnection("xavier",con);

error= "success"; 
if(f3==1) error="T";
}catch(Exception e){error=(e.toString()+count+i+sql);}
return error; 
}
