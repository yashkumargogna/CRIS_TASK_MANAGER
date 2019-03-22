package insertServlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import common.CommonDetails;
import common.DateTime;
import model.UserDet;
import works.Module;
import works.Project;
import works.Scrum;
import works.Sprint;
import works.TaskDB;
import works.Tasks;

/**
 * Servlet implementation class InsertData
 */
@WebServlet("/insertData")
public class InsertData extends HttpServlet
{
	private static final long serialVersionUID = 1L;
       
    
	Connection con;
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		try
		{
		// TODO Auto-generated method stub
		String action=request.getParameter("doaction");
		System.out.println(request.getParameter("doaction"));
		if(action.equalsIgnoreCase("PROJECT"))
		{
			System.out.println(action+" inside if"+"\n");
			createProject(request,response);
			
		}	
		else if(action.equalsIgnoreCase("MODULE"))
		{
			System.out.println(action+" inside if"+"\n");
			createModule(request,response);
			
		}
		else if(action.equalsIgnoreCase("TASK"))
		{
			System.out.println(action+" inside if"+"\n");
			createTask(request,response);
			
		}
		else if(action.equalsIgnoreCase("SCRUMOFTASK"))
		{
			System.out.println(action+" inside if"+"\n");
			createScrumOfTask(request,response);
			
		}
		else if(action.equalsIgnoreCase("SCRUMOFSPRINT"))
		{
			System.out.println(action+" inside if"+"\n");
			createScrumOfSprint(request,response);
			
		}
		else if(action.equalsIgnoreCase("SPRINT"))
		{
			System.out.println(action+" inside if"+"\n");
			createSprint(request,response);
			
		}
		else if(action.equalsIgnoreCase("CHANGESTATUS"))
		{
			System.out.println(action+" inside if"+"\n");
			changeStatus(request,response);
			
		}
		}catch (Exception e) {
			// TODO: handle exception
		System.out.println(e);	
		}
		
		
		
	}

	private void changeStatus(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  
	{
		// TODO Auto-generated method stub
		String action=request.getParameter("doaction");
		String w_id=request.getParameter("work_id").trim();
		String status=request.getParameter("status");
		String remarks= request.getParameter("remarks");
		
		response.setContentType("application/json");
		Writer wr = response.getWriter();
		
		UserDet ud=(UserDet)request.getSession().getAttribute("UserDet");
		try
		{
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());	
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","yash");
			PreparedStatement ps_update_task=con.prepareStatement("UPDATE CRIS_WORKS SET STATUS=? WHERE WORK_ID=?");
			ps_update_task.setString(1,status);
			ps_update_task.setString(2,w_id);
			int i=0;
			i=ps_update_task.executeUpdate();
		if(i>0)
		{
			if(w_id.startsWith("T="))
				{
					CommonDetails.dep_tasks.get(ud.getDept()).get(w_id).setStatus(status);	
				}
				else if(w_id.startsWith("S="))
				{
						String split[]=w_id.split("T=");
						String t_id="T="+split[1];
						String sprint_id=w_id;
						CommonDetails.dep_tasks.get(ud.getDept()).get(t_id).getTask_spr().get(sprint_id).setStatus(status);
				}
				else if(w_id.startsWith("SC="))
				{
					String split[]=w_id.split("T=");
					String t_id="T="+split[1];
					String scrum_id=w_id;
					CommonDetails.dep_tasks.get(ud.getDept()).get(t_id).getTask_scr().get(scrum_id).setStatus(status);
				}
				else if(w_id.startsWith("SR="))
				{				
					String split_for_task[]=w_id.split("T=");
					String t_id="T="+split_for_task[1];
					String scrum_of_sprint_id=w_id;
					String split_for_sprint[]=w_id.split("S=");
					String spr_id="S="+split_for_sprint[1];
					
					CommonDetails.dep_tasks.get(ud.getDept()).get(t_id).getTask_spr().get(spr_id).getSpr_scr().get(scrum_of_sprint_id).setStatus(status);
				}
		}	
		wr.write("{\"success\":true,\"action\":\"STATUSCHANGE\",\"w_id\":\""+w_id+"\""+",\"status\":\""+status+"\"}");
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println(e);
			wr.write("{\"success\":false,\"err\":\"Some DB Error. Please try again in few minutes!\"}");

		}

	}

	private void createScrumOfSprint(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		Writer wr = response.getWriter();
		wr.write("<html><body><script>");
		wr.write("var resp={");
		
		try
		{
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());	
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","yash");
			String task_id_br_arr[]=request.getParameter("id_rel_to").trim().split("T=");
			String task_id_extracted="T="+task_id_br_arr[1];
			System.out.println("ct start 0 fro check NEW");
			System.out.println("ct start 1 for check");
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery("select CRIS_WORK_ID.nextval from dual");
			int seq_val=0;
			
			System.out.println("ct start"+seq_val);
			while(rs.next())
			{
				seq_val=(rs.getInt(1));
				System.out.println(seq_val);
			}
			
			String task_id="SR="+seq_val+"-"+request.getParameter("id_rel_to").trim();//scrum of task id start with SC=
			System.out.println(task_id);
			Scrum taskDB=new Scrum();
			taskDB.setWork_id(task_id);
			taskDB.setWorkname(request.getParameter("taskname"));
			taskDB.setDesp(request.getParameter("taskDesc"));
			taskDB.setId_rel_to(request.getParameter("id_rel_to").trim());
			taskDB.setName_rel_to(CommonDetails.dep_tasks.get(request.getParameter("dept")).get(task_id_extracted.trim()).getWorkname());
			
			taskDB.setModule(CommonDetails.proj_mod.get(request.getParameter("project")).get(request.getParameter("module")));
			taskDB.setType(request.getParameter("work_type"));
			taskDB.setWork_catg(request.getParameter("work_catg"));
			taskDB.setSt_date(request.getParameter("startDate"));
			taskDB.setTg_date(request.getParameter("targetDate"));
			taskDB.setStatus(request.getParameter("status"));
			taskDB.setRemarks(request.getParameter("remarks"));
			taskDB.setSta_changed_by(request.getParameter("empID"));
			taskDB.setProject(CommonDetails.dep_proj.get(request.getParameter("dept")).get(request.getParameter("project")));
			taskDB.setTask_of(task_id_extracted);//////////////////TO BE EDITED WE TAKE OUT TASK ID OUT OF SPRINT ID
			taskDB.setDept(request.getParameter("dept"));
			String assign_to[]=request.getParameterValues("assign_to");
			HashSet<Integer> hs=new HashSet<Integer>();
			String assign_to_str=" ";
			
			
			for(int i=0;i<assign_to.length;i++)
			{	
				if(i==0)
				{	
					assign_to_str=assign_to[i];
				}
				else
				{
					assign_to_str=assign_to_str+","+assign_to[i];
					
				}	
				hs.add(Integer.parseInt(assign_to[i]));
						
			}
			taskDB.setAssign_to(hs);
			
			taskDB.setModule_id(request.getParameter("module"));
			taskDB.setProject_id(request.getParameter("project"));
			
			PreparedStatement ps_insert_task=con.prepareStatement("INSERT INTO CRIS_WORKS (WORKNAME,WORK_ID,DESP,ID_RELATED_TO,NAME_RELATED_TO,MODULE,WORK_TYPE,ST_DATE,TG_DATE,STATUS,REMARKS,STA_CHANGED_BY,PROJECT,TASK_OF,DEPT,ASSIGN_TO,INCHARGE,MODULE_ID,PROJECT_ID,WORK_CATG) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			ps_insert_task.setString(1,taskDB.getWorkname());
			ps_insert_task.setString(2,taskDB.getWork_id());
			ps_insert_task.setString(3,taskDB.getDesp());
			ps_insert_task.setString(4,taskDB.getId_rel_to().trim());
			ps_insert_task.setString(5,taskDB.getName_rel_to());
			ps_insert_task.setString(6,taskDB.getModule());
			ps_insert_task.setString(7,taskDB.getType());
			ps_insert_task.setTimestamp(8,new DateTime(taskDB.getSt_date(),"yyyy-MM-dd").getTimeStamp());
			ps_insert_task.setTimestamp(9,new DateTime(taskDB.getTg_date(),"yyyy-MM-dd").getTimeStamp());
			ps_insert_task.setString(10,taskDB.getStatus());
			ps_insert_task.setString(11,taskDB.getRemarks());
			ps_insert_task.setInt(12,Integer.parseInt(taskDB.getSta_changed_by()));
			ps_insert_task.setString(13,taskDB.getProject());
			ps_insert_task.setString(14,taskDB.getTask_of().trim());
			ps_insert_task.setString(15,taskDB.getDept());
			ps_insert_task.setString(16,assign_to_str);//STRING IN DB BUT HASH SET IN CACHE
			ps_insert_task.setString(17," ");
			ps_insert_task.setString(18,taskDB.getModule_id());
			ps_insert_task.setString(19,taskDB.getProject_id());
			ps_insert_task.setString(20,taskDB.getWork_catg());
			
			
			int i=ps_insert_task.executeUpdate();
	
			
			
			System.out.println(i);
			boolean ins_res=false;
			if(i>0)
			{ins_res=true;}	
			System.out.println(ins_res);
			if(ins_res==true)
			{
				if(CommonDetails.dep_tasks.containsKey(taskDB.getDept()))
				{
					if(CommonDetails.dep_tasks.get(taskDB.getDept()).containsKey(taskDB.getTask_of()))
					{
						CommonDetails.dep_tasks.get(taskDB.getDept()).get(taskDB.getTask_of().trim()).getTask_spr().get(taskDB.getId_rel_to().trim()).getSpr_scr().put(taskDB.getWork_id().trim(), taskDB);
					}
				}
					
			}
			
			PreparedStatement ps_insert_batch=con.prepareStatement("INSERT INTO CRIS_WORK_TO (WORK_ID,EMP_ID,ASSG_DATE) VALUES (?,?,?)");
			HashSet<Integer> batch_data=taskDB.getAssign_to();
			Iterator<Integer> bt=batch_data.iterator();
			while(bt.hasNext())
			{
				ps_insert_batch.setString(1,taskDB.getWork_id().trim());
				ps_insert_batch.setInt(2,bt.next());
				ps_insert_batch.setTimestamp(3,new DateTime(taskDB.getSt_date(),"yyyy-MM-dd").getTimeStamp());
				ps_insert_batch.addBatch();
			}	
			int array_batch[]=ps_insert_batch.executeBatch();
			//System.out.println(array_batch[0]+"   "+array_batch[1]);
			wr.write("\"success\":true,\"inserted\":\"scrumofsprint\",\"scrum\":"+new Gson().toJson(taskDB));
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println(e);
			wr.write("\"success\":false,\"err\":\"Some DB Error. Please try agin in few minutes!\"");
		}	
		wr.write("}; var win = opener || parent; win.showProjectAddResp(resp);");
		wr.write("</script></body></html>");
		wr.close();

		

			
	}

	private void createSprint(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		Writer wr = response.getWriter();
		wr.write("<html><body><script>");
		wr.write("var resp={");
		try
		{
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());	
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","yash");

			System.out.println("ct start 0 fro check NEW");
			System.out.println("ct start 1 for check");
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery("select CRIS_WORK_ID.nextval from dual");
			int seq_val=0;
			
			System.out.println("ct start"+seq_val);
			while(rs.next())
			{
				seq_val=(rs.getInt(1));
				System.out.println(seq_val);
			}
			
			String task_id="S="+seq_val+"-"+request.getParameter("id_rel_to").trim();//scrum of task id start with SC=
			System.out.println(task_id);
			Sprint taskDB=new Sprint();
			taskDB.setWork_id(task_id.trim());
			taskDB.setWorkname(request.getParameter("taskname"));
			taskDB.setDesp(request.getParameter("taskDesc"));
			taskDB.setId_rel_to(request.getParameter("id_rel_to").trim());
			taskDB.setName_rel_to(CommonDetails.dep_tasks.get(request.getParameter("dept")).get(request.getParameter("id_rel_to").trim()).getWorkname());
			
			taskDB.setModule(CommonDetails.proj_mod.get(request.getParameter("project")).get(request.getParameter("module")));
			taskDB.setType(request.getParameter("work_type"));
			taskDB.setWork_catg(request.getParameter("work_catg"));
			taskDB.setSt_date(request.getParameter("startDate"));
			taskDB.setTg_date(request.getParameter("targetDate"));
			taskDB.setStatus(request.getParameter("status"));
			taskDB.setRemarks(request.getParameter("remarks"));
			taskDB.setSta_changed_by(request.getParameter("empID"));
			taskDB.setProject(CommonDetails.dep_proj.get(request.getParameter("dept")).get(request.getParameter("project")));
			taskDB.setTask_of(request.getParameter("id_rel_to").trim());
			taskDB.setDept(request.getParameter("dept"));
			String incharge[]=request.getParameterValues("incharge");
			String assign_to[]=request.getParameterValues("assign_to");
			HashSet<Integer> hs=new HashSet<Integer>();
			HashSet<Integer> hs_incharge=new HashSet<Integer>();
			String incharge_str=" ";
			String assign_to_str=" ";
			for(int i=0;i<incharge.length;i++)
			{	
				if(i==0)
				{	
					incharge_str=incharge[i];
				}
				else
				{
					incharge_str=incharge_str+","+incharge[i];
					
				}	
				hs.add(Integer.parseInt(incharge[i]));
				hs_incharge.add(Integer.parseInt(incharge[i]));
						
			}
			
			for(int i=0;i<assign_to.length;i++)
			{	
				if(i==0)
				{	
					assign_to_str=assign_to[i];
				}
				else
				{
					assign_to_str=assign_to_str+","+assign_to[i];
					
				}	
				hs.add(Integer.parseInt(assign_to[i]));
						
			}
			
			taskDB.setIncharge(hs_incharge);
			taskDB.setAssign_to(hs);
			
			taskDB.setModule_id(request.getParameter("module"));
			taskDB.setProject_id(request.getParameter("project"));
			
			PreparedStatement ps_insert_task=con.prepareStatement("INSERT INTO CRIS_WORKS (WORKNAME,WORK_ID,DESP,ID_RELATED_TO,NAME_RELATED_TO,MODULE,WORK_TYPE,ST_DATE,TG_DATE,STATUS,REMARKS,STA_CHANGED_BY,PROJECT,TASK_OF,DEPT,ASSIGN_TO,INCHARGE,MODULE_ID,PROJECT_ID,WORK_CATG) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			ps_insert_task.setString(1,taskDB.getWorkname());
			ps_insert_task.setString(2,taskDB.getWork_id().trim());
			ps_insert_task.setString(3,taskDB.getDesp());
			ps_insert_task.setString(4,taskDB.getId_rel_to().trim());
			ps_insert_task.setString(5,taskDB.getName_rel_to());
			ps_insert_task.setString(6,taskDB.getModule());
			ps_insert_task.setString(7,taskDB.getType());
			ps_insert_task.setTimestamp(8,new DateTime(taskDB.getSt_date(),"yyyy-MM-dd").getTimeStamp());
			ps_insert_task.setTimestamp(9,new DateTime(taskDB.getTg_date(),"yyyy-MM-dd").getTimeStamp());
			ps_insert_task.setString(10,taskDB.getStatus());
			ps_insert_task.setString(11,taskDB.getRemarks());
			ps_insert_task.setInt(12,Integer.parseInt(taskDB.getSta_changed_by()));
			ps_insert_task.setString(13,taskDB.getProject());
			ps_insert_task.setString(14,taskDB.getTask_of().trim());
			ps_insert_task.setString(15,taskDB.getDept());
			ps_insert_task.setString(16,assign_to_str);//STRING IN DB BUT HASH SET IN CACHE
			ps_insert_task.setString(17,incharge_str);
			ps_insert_task.setString(18,taskDB.getModule_id());
			ps_insert_task.setString(19,taskDB.getProject_id());
			ps_insert_task.setString(20,taskDB.getWork_catg());
			
			
			int i=ps_insert_task.executeUpdate();
	
			
			
			System.out.println(i);
			boolean ins_res=false;
			if(i>0)
			{ins_res=true;}	
			System.out.println(ins_res);
			if(ins_res==true)
			{
				if(CommonDetails.dep_tasks.containsKey(taskDB.getDept()))
				{
					if(CommonDetails.dep_tasks.get(taskDB.getDept()).containsKey(taskDB.getId_rel_to().trim()))
					{
						CommonDetails.dep_tasks.get(taskDB.getDept()).get(taskDB.getId_rel_to().trim()).getTask_spr().put(taskDB.getWork_id().trim(),taskDB);
					}
				}	
			}
			
			PreparedStatement ps_insert_batch=con.prepareStatement("INSERT INTO CRIS_WORK_TO (WORK_ID,EMP_ID,ASSG_DATE) VALUES (?,?,?)");
			HashSet<Integer> batch_data=taskDB.getAssign_to();
			Iterator<Integer> bt=batch_data.iterator();
			while(bt.hasNext())
			{
				ps_insert_batch.setString(1,taskDB.getWork_id().trim());
				ps_insert_batch.setInt(2,bt.next());
				ps_insert_batch.setTimestamp(3,new DateTime(taskDB.getSt_date(),"yyyy-MM-dd").getTimeStamp());
				ps_insert_batch.addBatch();
			}	
			int array_batch[]=ps_insert_batch.executeBatch();
			//System.out.println(array_batch[0]+"   "+array_batch[1]);
			wr.write("\"success\":true,\"inserted\":\"sprint\",\"sprint\":"+new Gson().toJson(taskDB));
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println(e);
			wr.write("\"success\":false,\"err\":\"Some DB Error. Please try agin in few minutes!\"");
		}	
		wr.write("}; var win = opener || parent; win.showProjectAddResp(resp);");
		wr.write("</script></body></html>");
		wr.close();

		
	}

	private void createScrumOfTask(HttpServletRequest request, HttpServletResponse response) throws IOException 
	{
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		Writer wr = response.getWriter();
		wr.write("<html><body><script>");
		wr.write("var resp={");
		try
		{
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());	
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","yash");

			System.out.println("ct start 0 fro check NEW");
			System.out.println("ct start 1 for check");
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery("select CRIS_WORK_ID.nextval from dual");
			int seq_val=0;
			
			System.out.println("ct start"+seq_val);
			while(rs.next())
			{
				seq_val=(rs.getInt(1));
				System.out.println(seq_val);
			}
			
			String task_id="SC="+seq_val+"-"+request.getParameter("id_rel_to").trim();//scrum of task id start with SC=
			System.out.println(task_id);
			Scrum taskDB=new Scrum();
			taskDB.setWork_id(task_id.trim());
			taskDB.setWorkname(request.getParameter("taskname"));
			taskDB.setDesp(request.getParameter("taskDesc"));
			taskDB.setId_rel_to(request.getParameter("id_rel_to").trim());
			taskDB.setName_rel_to(CommonDetails.dep_tasks.get(request.getParameter("dept")).get(request.getParameter("id_rel_to").trim()).getWorkname());
			
			taskDB.setModule(CommonDetails.proj_mod.get(request.getParameter("project")).get(request.getParameter("module")));
			taskDB.setType(request.getParameter("work_type"));
			taskDB.setWork_catg(request.getParameter("work_catg"));
			taskDB.setSt_date(request.getParameter("startDate"));
			taskDB.setTg_date(request.getParameter("targetDate"));
			taskDB.setStatus(request.getParameter("status"));
			taskDB.setRemarks(request.getParameter("remarks"));
			taskDB.setSta_changed_by(request.getParameter("empID"));
			taskDB.setProject(CommonDetails.dep_proj.get(request.getParameter("dept")).get(request.getParameter("project")));
			taskDB.setTask_of(request.getParameter("id_rel_to").trim());
			taskDB.setDept(request.getParameter("dept"));
			String assign_to[]=request.getParameterValues("assign_to");
			HashSet<Integer> hs=new HashSet<Integer>();
			String assign_to_str=" ";
			
			
			for(int i=0;i<assign_to.length;i++)
			{	
				if(i==0)
				{	
					assign_to_str=assign_to[i];
				}
				else
				{
					assign_to_str=assign_to_str+","+assign_to[i];
					
				}	
				hs.add(Integer.parseInt(assign_to[i]));
						
			}
			taskDB.setAssign_to(hs);
			
			taskDB.setModule_id(request.getParameter("module"));
			taskDB.setProject_id(request.getParameter("project"));
			
			PreparedStatement ps_insert_task=con.prepareStatement("INSERT INTO CRIS_WORKS (WORKNAME,WORK_ID,DESP,ID_RELATED_TO,NAME_RELATED_TO,MODULE,WORK_TYPE,ST_DATE,TG_DATE,STATUS,REMARKS,STA_CHANGED_BY,PROJECT,TASK_OF,DEPT,ASSIGN_TO,INCHARGE,MODULE_ID,PROJECT_ID,WORK_CATG) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			ps_insert_task.setString(1,taskDB.getWorkname());
			ps_insert_task.setString(2,taskDB.getWork_id().trim());
			ps_insert_task.setString(3,taskDB.getDesp());
			ps_insert_task.setString(4,taskDB.getId_rel_to().trim());
			ps_insert_task.setString(5,taskDB.getName_rel_to());
			ps_insert_task.setString(6,taskDB.getModule());
			ps_insert_task.setString(7,taskDB.getType());
			ps_insert_task.setTimestamp(8,new DateTime(taskDB.getSt_date(),"yyyy-MM-dd").getTimeStamp());
			ps_insert_task.setTimestamp(9,new DateTime(taskDB.getTg_date(),"yyyy-MM-dd").getTimeStamp());
			ps_insert_task.setString(10,taskDB.getStatus());
			ps_insert_task.setString(11,taskDB.getRemarks());
			ps_insert_task.setInt(12,Integer.parseInt(taskDB.getSta_changed_by()));
			ps_insert_task.setString(13,taskDB.getProject());
			ps_insert_task.setString(14,taskDB.getTask_of().trim());
			ps_insert_task.setString(15,taskDB.getDept());
			ps_insert_task.setString(16,assign_to_str);//STRING IN DB BUT HASH SET IN CACHE
			ps_insert_task.setString(17," ");
			ps_insert_task.setString(18,taskDB.getModule_id());
			ps_insert_task.setString(19,taskDB.getProject_id());
			ps_insert_task.setString(20,taskDB.getWork_catg());
			
			
			int i=ps_insert_task.executeUpdate();
	
			
			
			System.out.println(i);
			boolean ins_res=false;
			if(i>0)
			{ins_res=true;}	
			System.out.println(ins_res);
			if(ins_res==true)
			{
				if(CommonDetails.dep_tasks.containsKey(taskDB.getDept()))
				{
					if(CommonDetails.dep_tasks.get(taskDB.getDept()).containsKey(taskDB.getTask_of().trim()))
					{
						CommonDetails.dep_tasks.get(taskDB.getDept()).get(taskDB.getTask_of().trim()).getTask_scr().put(taskDB.getWork_id().trim(),taskDB);
					}
				}
					
			}
			
			PreparedStatement ps_insert_batch=con.prepareStatement("INSERT INTO CRIS_WORK_TO (WORK_ID,EMP_ID,ASSG_DATE) VALUES (?,?,?)");
			HashSet<Integer> batch_data=taskDB.getAssign_to();
			Iterator<Integer> bt=batch_data.iterator();
			while(bt.hasNext())
			{
				ps_insert_batch.setString(1,taskDB.getWork_id().trim());
				ps_insert_batch.setInt(2,bt.next());
				ps_insert_batch.setTimestamp(3,new DateTime(taskDB.getSt_date(),"yyyy-MM-dd").getTimeStamp());
				ps_insert_batch.addBatch();
			}	
			int array_batch[]=ps_insert_batch.executeBatch();
			//System.out.println(array_batch[0]+"   "+array_batch[1]);
			wr.write("\"success\":true,\"inserted\":\"scrumoftask\",\"scrum\":"+new Gson().toJson(taskDB));
		}
		catch(Exception e) {
			e.printStackTrace();
			wr.write("\"success\":false,\"err\":\"Some DB Error. Please try agin in few minutes!\"");
		}	
		wr.write("}; var win = opener || parent; win.showProjectAddResp(resp);");
		wr.write("</script></body></html>");
		wr.close();

		

		
		
		
	}

	private void createTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
		
		response.setContentType("text/html");
		Writer wr = response.getWriter();
		wr.write("<html><body><script>");
		wr.write("var resp={");
		try
		{
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());	
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","yash");

			System.out.println("ct start 0 fro check NEW");
			System.out.println("ct start 1 for check");
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery("select CRIS_WORK_ID.nextval from dual");
			int seq_val=0;
			
			System.out.println("ct start"+seq_val);
			while(rs.next())
			{
				seq_val=(rs.getInt(1));
				System.out.println(seq_val);
			}
			String task_id="T="+seq_val+"-"+request.getParameter("module");
			System.out.println(task_id);
			Tasks taskDB=new Tasks();
			taskDB.setWork_id(task_id.trim());
			taskDB.setWorkname(request.getParameter("taskname"));
			taskDB.setDesp(request.getParameter("taskDesc"));
			taskDB.setId_rel_to(request.getParameter("module"));
			taskDB.setName_rel_to(CommonDetails.proj_mod.get(request.getParameter("project")).get(request.getParameter("module")));
			
			taskDB.setModule(CommonDetails.proj_mod.get(request.getParameter("project")).get(request.getParameter("module")));
			taskDB.setType(request.getParameter("work_type"));
			taskDB.setWork_catg(request.getParameter("work_catg"));
			taskDB.setSt_date(request.getParameter("startDate"));
			taskDB.setTg_date(request.getParameter("targetDate"));
			taskDB.setStatus(request.getParameter("status"));
			taskDB.setRemarks(request.getParameter("remarks"));
			taskDB.setSta_changed_by(request.getParameter("empID"));
			taskDB.setProject(CommonDetails.dep_proj.get(request.getParameter("dept")).get(request.getParameter("project")));
			taskDB.setTask_of(task_id);
			taskDB.setDept(request.getParameter("dept"));
			String incharge[]=request.getParameterValues("incharge");
			String assign_to[]=request.getParameterValues("assign_to");
			HashSet<Integer> hs=new HashSet<Integer>();
			HashSet<Integer> hs_incharge=new HashSet<Integer>();
			String incharge_str=" ";
			String assign_to_str=" ";
			for(int i=0;i<incharge.length;i++)
			{	
				if(i==0)
				{	
					incharge_str=incharge[i];
				}
				else
				{
					incharge_str=incharge_str+","+incharge[i];
					
				}	
				hs.add(Integer.parseInt(incharge[i]));
				hs_incharge.add(Integer.parseInt(incharge[i]));
						
			}
			
			for(int i=0;i<assign_to.length;i++)
			{	
				if(i==0)
				{	
					assign_to_str=assign_to[i];
				}
				else
				{
					assign_to_str=assign_to_str+","+assign_to[i];
					
				}	
				hs.add(Integer.parseInt(assign_to[i]));
						
			}
			
			taskDB.setIncharge(hs_incharge);
			taskDB.setAssign_to(hs);
			
			taskDB.setModule_id(request.getParameter("module"));
			taskDB.setProject_id(request.getParameter("project"));
			
			PreparedStatement ps_insert_task=con.prepareStatement("INSERT INTO CRIS_WORKS (WORKNAME,WORK_ID,DESP,ID_RELATED_TO,NAME_RELATED_TO,MODULE,WORK_TYPE,ST_DATE,TG_DATE,STATUS,REMARKS,STA_CHANGED_BY,PROJECT,TASK_OF,DEPT,ASSIGN_TO,INCHARGE,MODULE_ID,PROJECT_ID,WORK_CATG) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			ps_insert_task.setString(1,taskDB.getWorkname());
			ps_insert_task.setString(2,taskDB.getWork_id().trim());
			ps_insert_task.setString(3,taskDB.getDesp());
			ps_insert_task.setString(4,taskDB.getId_rel_to().trim());
			ps_insert_task.setString(5,taskDB.getName_rel_to());
			ps_insert_task.setString(6,taskDB.getModule());
			ps_insert_task.setString(7,taskDB.getType());
			ps_insert_task.setTimestamp(8,new DateTime(taskDB.getSt_date(),"yyyy-MM-dd").getTimeStamp());
			ps_insert_task.setTimestamp(9,new DateTime(taskDB.getTg_date(),"yyyy-MM-dd").getTimeStamp());
			ps_insert_task.setString(10,taskDB.getStatus());
			ps_insert_task.setString(11,taskDB.getRemarks());
			ps_insert_task.setInt(12,Integer.parseInt(taskDB.getSta_changed_by()));
			ps_insert_task.setString(13,taskDB.getProject());
			ps_insert_task.setString(14,taskDB.getTask_of().trim());
			ps_insert_task.setString(15,taskDB.getDept());
			ps_insert_task.setString(16,assign_to_str);//STRING IN DB BUT HASH SET IN CACHE
			ps_insert_task.setString(17,incharge_str);
			ps_insert_task.setString(18,taskDB.getModule_id());
			ps_insert_task.setString(19,taskDB.getProject_id());
			ps_insert_task.setString(20,taskDB.getWork_catg());
			
			
			int i=ps_insert_task.executeUpdate();
	
			
			
			System.out.println(i);
			boolean ins_res=false;
			if(i>0)
			{ins_res=true;}	
			System.out.println(ins_res);
			if(ins_res==true)
			{
				if(CommonDetails.dep_tasks.containsKey(taskDB.getDept()))
				{
					CommonDetails.dep_tasks.get(taskDB.getDept()).put(taskDB.getWork_id().trim(),taskDB);
				}
				else
				{					
				 	LinkedHashMap<String, Tasks> tasks_hm=new LinkedHashMap<String,Tasks>();
				 	tasks_hm.put(taskDB.getWork_id().trim(),taskDB);
				 	CommonDetails.dep_tasks.put(taskDB.getDept(),tasks_hm);
				}	
			}
			
			PreparedStatement ps_insert_batch=con.prepareStatement("INSERT INTO CRIS_WORK_TO (WORK_ID,EMP_ID,ASSG_DATE) VALUES (?,?,?)");
			HashSet<Integer> batch_data=taskDB.getAssign_to();
			Iterator<Integer> bt=batch_data.iterator();
			while(bt.hasNext())
			{
				ps_insert_batch.setString(1,taskDB.getWork_id().trim());
				ps_insert_batch.setInt(2,bt.next());
				ps_insert_batch.setTimestamp(3,new DateTime(taskDB.getSt_date(),"yyyy-MM-dd").getTimeStamp());
				ps_insert_batch.addBatch();
			}	
			int array_batch[]=ps_insert_batch.executeBatch();
			//System.out.println(array_batch[0]+"   "+array_batch[1]);
			wr.write("\"success\":true,\"inserted\":\"task\",\"task\":"+new Gson().toJson(taskDB));
		}
		catch(Exception e) {
			e.printStackTrace();
			wr.write("\"success\":false,\"err\":\"Some DB Error. Please try agin in few minutes!\"");
		}	
		wr.write("}; var win = opener || parent; win.showProjectAddResp(resp);");
		wr.write("</script></body></html>");
		wr.close();

		

		
				
			
		
		
	}

	private void createModule(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		Writer wr = response.getWriter();
		wr.write("<html><body><script>");
		wr.write("var resp={");
		try
		{
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());	
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","yash");

			System.out.println("cp start 0 fro check NEW");
			System.out.println("cp start 1 for check");
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery("select cris_module_id.nextval from dual");
			int seq_val=0;
			System.out.println("cp start"+seq_val);
			while(rs.next())
			{
				seq_val=(rs.getInt(1));
				System.out.println(seq_val);
			}
			String mod_id="M="+seq_val+"-"+request.getParameter("project");
			System.out.println(mod_id);
			Module module=new Module();
			module.setMod_id(mod_id);
			module.setMod_name(request.getParameter("mod_name"));
			module.setMod_desp(request.getParameter("mod_desp"));
			module.setProj_rel(request.getParameter("project"));
			
			PreparedStatement ps_insert_module=con.prepareStatement("INSERT INTO CRIS_MODULE (MODULE_ID,MODULE_NAME,DESCRIPTION,PROJECT_ID) VALUES(?,?,?,?)");
			ps_insert_module.setString(1,module.getMod_id());
			ps_insert_module.setString(2,module.getMod_name());
			ps_insert_module.setString(3,module.getMod_desp());
			ps_insert_module.setString(4,module.getProj_rel());
			int i=ps_insert_module.executeUpdate();
			System.out.println(i);
			boolean ins_res=false;
			if(i>0)
			{ins_res=true;}	
			System.out.println(ins_res);
			if(ins_res==true)
			{
				if(CommonDetails.proj_mod.containsKey(module.getProj_rel()))
				{
					CommonDetails.proj_mod.get(module.getProj_rel()).put(module.getMod_id(),module.getMod_name());
				}
				else
				{					
				 	HashMap<String, String> mod_hm=new HashMap<String, String>();
				 	mod_hm.put(module.getMod_id(),module.getMod_name());
				 	CommonDetails.proj_mod.put(module.getProj_rel(),mod_hm);
				}	
			}
			wr.write("\"success\":true,\"inserted\":\"module\",\"module\":"+new Gson().toJson(module));
		}
		catch(Exception e) {
			e.printStackTrace();
			wr.write("\"success\":false,\"err\":\"Some DB Error. Please try agin in few minutes!\"");
		}	
		wr.write("}; var win = opener || parent; win.showProjectAddResp(resp);");
		wr.write("</script></body></html>");
		wr.close();

		
		
	}

	public void createProject(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		Writer wr = response.getWriter();
		wr.write("<html><body><script>");
		wr.write("var resp={");
		try
		{
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());	
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","yash");

			System.out.println("cp start 0 fro check NEW");
			System.out.println("cp start 1 for check");
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery("select cris_project_id.nextval from dual");
			int seq_val=0;
			System.out.println("cp start"+seq_val);
			while(rs.next())
			{
				seq_val=(rs.getInt(1));
				System.out.println(seq_val);
			}
			String p_id="P="+seq_val+"-"+request.getParameter("dept");
			System.out.println(p_id);
			Project project=new Project();
			project.setP_id(p_id);
			project.setP_name(request.getParameter("project_name"));
			project.setDept(request.getParameter("dept"));
			project.setDesp(request.getParameter("project_desc"));
			System.out.println(request.getParameter("project_desc"));
			PreparedStatement ps_insert_project=con.prepareStatement("INSERT INTO CRIS_PROJECT (PROJECT_ID,PROJECT_NAME, DESCRIPTION,DEPT) VALUES(?,?,?,?)");
			ps_insert_project.setString(1,project.getP_id());
			ps_insert_project.setString(2,project.getP_name());
			ps_insert_project.setString(3,project.getDesp());
			ps_insert_project.setString(4,project.getDept());
			int i=ps_insert_project.executeUpdate();
			System.out.println(i);
			boolean ins_res=false;
			if(i>0)
			{ins_res=true;}	
			System.out.println(ins_res);
			if(ins_res==true)
			{
				if(CommonDetails.dep_proj.containsKey(project.getDept()))
				{
					CommonDetails.dep_proj.get(project.getDept()).put(project.getP_id(), project.getP_name());
				}
				else
				{					
				 	HashMap<String, String> proj_hm=new HashMap<String, String>();
				 	proj_hm.put(project.getP_id(),project.getP_name());
				 	CommonDetails.dep_proj.put(project.getDept(),proj_hm);
				}	
			}
			wr.write("\"success\":true,\"inserted\":\"project\",\"project\":"+new Gson().toJson(project));
		}
		catch(Exception e) {
			e.printStackTrace();
			wr.write("\"success\":false,\"err\":\"Some DB Error. Please try agin in few minutes!\"");
		}	
		wr.write("}; var win = opener || parent; win.showProjectAddResp(resp);");
		wr.write("</script></body></html>");
		wr.close();
	}

}
//




