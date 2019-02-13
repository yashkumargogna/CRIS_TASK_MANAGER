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

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import common.CommonDetails;
import common.DateTime;
import works.Module;
import works.Project;
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
		String action=request.getParameter("action");
		System.out.println(request.getParameter("action"));
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
		
		}catch (Exception e) {
			// TODO: handle exception
		System.out.println(e);	
		}
		
		
		
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
			taskDB.setWork_id(task_id);
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
			
			taskDB.setIncharge(incharge_str);
			taskDB.setAssign_to(hs);
			
			taskDB.setModule_id(request.getParameter("module"));
			taskDB.setProject_id(request.getParameter("project"));
			
			PreparedStatement ps_insert_task=con.prepareStatement("INSERT INTO CRIS_WORKS (WORKNAME,WORK_ID,DESP,ID_RELATED_TO,NAME_RELATED_TO,MODULE,WORK_TYPE,ST_DATE,TG_DATE,STATUS,REMARKS,STA_CHANGED_BY,PROJECT,TASK_OF,DEPT,ASSIGN_TO,INCHARGE,MODULE_ID,PROJECT_ID,WORK_CATG) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			ps_insert_task.setString(1,taskDB.getWorkname());
			ps_insert_task.setString(2,taskDB.getWork_id());
			ps_insert_task.setString(3,taskDB.getDesp());
			ps_insert_task.setString(4,taskDB.getId_rel_to());
			ps_insert_task.setString(5,taskDB.getName_rel_to());
			ps_insert_task.setString(6,taskDB.getModule());
			ps_insert_task.setString(7,taskDB.getType());
			ps_insert_task.setTimestamp(8,new DateTime(taskDB.getSt_date(),"yyyy-MM-dd").getTimeStamp());
			ps_insert_task.setTimestamp(9,new DateTime(taskDB.getTg_date(),"yyyy-MM-dd").getTimeStamp());
			ps_insert_task.setString(10,taskDB.getStatus());
			ps_insert_task.setString(11,taskDB.getRemarks());
			ps_insert_task.setInt(12,Integer.parseInt(taskDB.getSta_changed_by()));
			ps_insert_task.setString(13,taskDB.getProject());
			ps_insert_task.setString(14,taskDB.getTask_of());
			ps_insert_task.setString(15,taskDB.getDept());
			ps_insert_task.setString(16,assign_to_str);//STRING IN DB
			ps_insert_task.setString(17,taskDB.getIncharge());
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
					CommonDetails.dep_tasks.get(taskDB.getDept()).put(taskDB.getWork_id(),taskDB);
				}
				else
				{					
				 	HashMap<String, Tasks> tasks_hm=new HashMap<String,Tasks>();
				 	tasks_hm.put(taskDB.getWork_id(),taskDB);
				 	CommonDetails.dep_tasks.put(taskDB.getDept(),tasks_hm);
				}	
			}
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




