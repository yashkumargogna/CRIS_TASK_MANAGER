package loginservlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.DateTime;
import model.AddTask;

/**
 * Servlet implementation class RegisterTask
 */
@WebServlet("/registerTask")
public class RegisterTask extends HttpServlet
{
	private static final long serialVersionUID = 1L;
    Connection con;
    Statement st;
    public RegisterTask() {
        super();
        // TODO Auto-generated construcbtor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
		try
		
		{
				DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
				con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","yash");
				st=con.createStatement();
				AddTask add_task=new AddTask();
				add_task.setTask_name(request.getParameter("taskname"));
				add_task.setTask_type("TASK");
				add_task.setProject(request.getParameter("project"));
				add_task.setId_related_to(Integer.parseInt(request.getParameter("module")));
				//add_task.setSt_dt(new DateTime(request.getParameter("startDate")).getDate());
				String ins_task="insert into cris_works (workname,work_id,desp,id_related_to,name_related_to,module,work_type,st_date,tg_date,status,remarks) values "; 
				
				
		
		}catch(Exception e) {}		
	
	
	}

}
