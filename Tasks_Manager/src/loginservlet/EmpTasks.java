package loginservlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.CommonDetails;
import model.UserDet;
import works.Scrum;
import works.Sprint;
import works.TaskDB;
import works.Tasks;

/**
 * Servlet implementation class EmpTasks
 */
@WebServlet("/empTasks")
public class EmpTasks extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmpTasks() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		LinkedHashMap<String,Tasks> tasks_incharge=new LinkedHashMap<String,Tasks>();
		LinkedHashMap<String,Sprint> sprints_incharge=new LinkedHashMap<String,Sprint>();
		LinkedHashMap<String,TaskDB> all_tasks=new LinkedHashMap<String,TaskDB>();
		
		// TODO Auto-generated method stub
		try
		{
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());	
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","yash");
			Statement st=con.createStatement()	;
			UserDet ud=(UserDet)request.getSession(false).getAttribute("UserDet");
			int eid=ud.getEid();
			ResultSet rs=st.executeQuery("SELECT WORK_ID,EMP_ID,ASSG_DATE FROM CRIS_WORK_TO WHERE EMP_ID="+eid);
			while(rs.next())
			{
				String w_id=rs.getString(1);
				if(w_id.startsWith("T="))
				{
							if(CommonDetails.dep_tasks.get(ud.getDept()).get(w_id).getIncharge().contains(eid))
							{
								tasks_incharge.put(w_id,CommonDetails.dep_tasks.get(ud.getDept()).get(w_id));
								
							}
							TaskDB taskDB=empTask(w_id,ud.getDept());
							all_tasks.put(w_id, taskDB);
														
				}	
				else if(w_id.startsWith("S="))
				{
						String split[]=w_id.split("T=");
						String t_id="T="+split[1];
						String sprint_id=w_id;
						if(CommonDetails.dep_tasks.get(ud.getDept()).get(t_id).getIncharge().contains(eid))
						{
							if(tasks_incharge.containsKey(t_id))
							{
								
							}
							else
							{
								tasks_incharge.put(t_id,CommonDetails.dep_tasks.get(ud.getDept()).get(t_id));
							}	
						}
						else if(CommonDetails.dep_tasks.get(ud.getDept()).get(t_id).getTask_spr().get(sprint_id).getIncharge().contains(eid))
						{
							sprints_incharge.put(sprint_id,CommonDetails.dep_tasks.get(ud.getDept()).get(t_id).getTask_spr().get(sprint_id));
						}	
							
						TaskDB taskDB=empSprint(t_id,sprint_id,ud.getDept());
						all_tasks.put(w_id, taskDB);
						
						
				}	
				else if(w_id.startsWith("SC="))
				{
					String split[]=w_id.split("T=");
					String t_id="T="+split[1];
					String scrum_id=w_id;
					if(CommonDetails.dep_tasks.get(ud.getDept()).get(t_id).getIncharge().contains(eid))
					{
						if(tasks_incharge.containsKey(t_id))
						{
							
						}
						else
						{
							tasks_incharge.put(t_id,CommonDetails.dep_tasks.get(ud.getDept()).get(t_id));
						}	
					}
						
					TaskDB taskDB=empscrum(t_id,scrum_id,ud.getDept());
					all_tasks.put(w_id, taskDB);
				}
				else if(w_id.startsWith("SR="))
				{				
					String split_for_task[]=w_id.split("T=");
					String t_id="T="+split_for_task[1];
					String scrum_of_sprint_id=w_id;
					String split_for_sprint[]=w_id.split("S=");
					String spr_id="S="+split_for_sprint[1];
					
					if(CommonDetails.dep_tasks.get(ud.getDept()).get(t_id).getIncharge().contains(eid))
					{
						if(tasks_incharge.containsKey(t_id))
						{
							
						}
						else
						{
							tasks_incharge.put(t_id,CommonDetails.dep_tasks.get(ud.getDept()).get(t_id));
						}
					}
					else if(CommonDetails.dep_tasks.get(ud.getDept()).get(t_id).getTask_spr().get(spr_id).getIncharge().contains(eid))
					{
						if(sprints_incharge.containsKey(spr_id))
						{	
							
						}
						else
						{
							sprints_incharge.put(spr_id,CommonDetails.dep_tasks.get(ud.getDept()).get(t_id).getTask_spr().get(spr_id));
						}	
					}
					
					TaskDB taskDB=empScrumOfSprint(t_id,spr_id,scrum_of_sprint_id,ud.getDept());
					all_tasks.put(w_id, taskDB);
				}
				
				
			}
			request.setAttribute("tasks_incharge",tasks_incharge);
			request.setAttribute("sprints_incharge",sprints_incharge);
			request.setAttribute("all_tasks",all_tasks);
			RequestDispatcher rd=request.getRequestDispatcher("emp.jsp");
			rd.forward(request, response);
		}catch (Exception e) {
		// TODO: handle exception
			System.out.println(e);
			e.printStackTrace();
	}
		

	}

	private TaskDB empScrumOfSprint(String t_id, String spr_id, String scrum_of_sprint_id, String dept) 
	{
		// TODO Auto-generated method stub
		Scrum t=CommonDetails.dep_tasks.get(dept).get(t_id).getTask_spr().get(spr_id).getSpr_scr().get(scrum_of_sprint_id);
		TaskDB t_show=new TaskDB();
		t_show.setWorkname(t.getWorkname());
		t_show.setWork_id(t.getWork_id());
		t_show.setWork_catg(t.getWork_catg());
		t_show.setType(t.getType());
		t_show.setTg_date(t.getTg_date());
		t_show.setTask_of(t.getTask_of());
		t_show.setStatus(t.getStatus());
		t_show.setSta_changed_by(t.getSta_changed_by());
		t_show.setSt_date(t.getSt_date());
		t_show.setRemarks(t.getRemarks());
		t_show.setProject_id(t.getProject_id());
		t_show.setProject(t.getProject());
		t_show.setName_rel_to(t.getName_rel_to());
		t_show.setModule_id(t.getModule_id());
		t_show.setModule(t.getModule());
		//t_show.setIncharge(t.getIncharge()); ----------NO INCHARGE IN SCRUM
		t_show.setId_rel_to(t.getId_rel_to());
		t_show.setDesp(t.getDesp());
		t_show.setDept(t.getDept());
		t_show.setAssign_to(t.getAssign_to());
		return t_show;

	}

	private TaskDB empscrum(String t_id, String scrum_id, String dept) 
	{
		// TODO Auto-generated method stub
		Scrum t=CommonDetails.dep_tasks.get(dept).get(t_id).getTask_scr().get(scrum_id);
		TaskDB t_show=new TaskDB();
		t_show.setWorkname(t.getWorkname());
		t_show.setWork_id(t.getWork_id());
		t_show.setWork_catg(t.getWork_catg());
		t_show.setType(t.getType());
		t_show.setTg_date(t.getTg_date());
		t_show.setTask_of(t.getTask_of());
		t_show.setStatus(t.getStatus());
		t_show.setSta_changed_by(t.getSta_changed_by());
		t_show.setSt_date(t.getSt_date());
		t_show.setRemarks(t.getRemarks());
		t_show.setProject_id(t.getProject_id());
		t_show.setProject(t.getProject());
		t_show.setName_rel_to(t.getName_rel_to());
		t_show.setModule_id(t.getModule_id());
		t_show.setModule(t.getModule());
		//t_show.setIncharge(t.getIncharge()); ----------NO INCHARGE IN SCRUM
		t_show.setId_rel_to(t.getId_rel_to());
		t_show.setDesp(t.getDesp());
		t_show.setDept(t.getDept());
		t_show.setAssign_to(t.getAssign_to());
		return t_show;

	}

	private TaskDB empSprint(String t_id, String sprint_id, String dept)
	{
		// TODO Auto-generated method stub
		Sprint t=CommonDetails.dep_tasks.get(dept).get(t_id).getTask_spr().get(sprint_id);
		TaskDB t_show=new TaskDB();
		t_show.setWorkname(t.getWorkname());
		t_show.setWork_id(t.getWork_id());
		t_show.setWork_catg(t.getWork_catg());
		t_show.setType(t.getType());
		t_show.setTg_date(t.getTg_date());
		t_show.setTask_of(t.getTask_of());
		t_show.setStatus(t.getStatus());
		t_show.setSta_changed_by(t.getSta_changed_by());
		t_show.setSt_date(t.getSt_date());
		t_show.setRemarks(t.getRemarks());
		t_show.setProject_id(t.getProject_id());
		t_show.setProject(t.getProject());
		t_show.setName_rel_to(t.getName_rel_to());
		t_show.setModule_id(t.getModule_id());
		t_show.setModule(t.getModule());
		t_show.setIncharge(t.getIncharge());
		t_show.setId_rel_to(t.getId_rel_to());
		t_show.setDesp(t.getDesp());
		t_show.setDept(t.getDept());
		t_show.setAssign_to(t.getAssign_to());
		return t_show;

	}

	private TaskDB empTask(String w_id, String dept)
	{
		// TODO Auto-generated method stub
		
		
		Tasks t=CommonDetails.dep_tasks.get(dept).get(w_id);
		TaskDB t_show=new TaskDB();
		t_show.setWorkname(t.getWorkname());
		t_show.setWork_id(t.getWork_id());
		t_show.setWork_catg(t.getWork_catg());
		t_show.setType(t.getType());
		t_show.setTg_date(t.getTg_date());
		t_show.setTask_of(t.getTask_of());
		t_show.setStatus(t.getStatus());
		t_show.setSta_changed_by(t.getSta_changed_by());
		t_show.setSt_date(t.getSt_date());
		t_show.setRemarks(t.getRemarks());
		t_show.setProject_id(t.getProject_id());
		t_show.setProject(t.getProject());
		t_show.setName_rel_to(t.getName_rel_to());
		t_show.setModule_id(t.getModule_id());
		t_show.setModule(t.getModule());
		t_show.setIncharge(t.getIncharge());
		t_show.setId_rel_to(t.getId_rel_to());
		t_show.setDesp(t.getDesp());
		t_show.setDept(t.getDept());
		t_show.setAssign_to(t.getAssign_to());
		return t_show;
	}
}	
	
	
