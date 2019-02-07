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

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import common.CommonDetails;
import works.Module;
import works.Project;

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
		if(action.equalsIgnoreCase("MODULE"))
		{
			System.out.println(action+" inside if"+"\n");
			createModule(request,response);
			
		}
		}catch (Exception e) {
			// TODO: handle exception
		System.out.println(e);	
		}
		
		
		
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
