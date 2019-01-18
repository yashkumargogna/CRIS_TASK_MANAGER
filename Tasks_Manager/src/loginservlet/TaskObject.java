package loginservlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import common.CommonDetails;

/**
 * Servlet implementation class TaskObject
 */
@WebServlet("/getTask")
public class TaskObject extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TaskObject() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("application/json");
   		String json=new Gson().toJson(CommonDetails.dep_tasks,CommonDetails.dep_tasks.getClass().getGenericSuperclass());
       	response.getWriter().write(json);
       	System.out.println(json);
   			
   			
   		
}
		
	}


