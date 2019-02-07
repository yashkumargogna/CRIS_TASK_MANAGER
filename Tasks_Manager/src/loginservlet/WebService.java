package loginservlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import common.CommonDetails;

@WebServlet("/getModule")
public class WebService extends HttpServlet {
	
       	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException    	
    {
       		try
       		{
		       		response.setContentType("application/json");
		       		String projectid=(request.getParameter("p_id"));
		       		if(CommonDetails.proj_mod.containsKey(projectid))
		       		{
		       		String json=new Gson().toJson(CommonDetails.proj_mod.get(projectid),CommonDetails.proj_mod.get(projectid).getClass().getGenericSuperclass());
		       		response.getWriter().write(json);
		       		}
		       		else
		       		{
		          		String json=new Gson().toJson(" ");
		           		response.getWriter().write(json);
		     	
		       			
		       		}	
       		}catch (Exception e) {
				// TODO: handle exception
       			String json=new Gson().toJson(" ");
           		response.getWriter().write(json);
     	
			}  		
	}

}

//,CommonDetails.proj_mod.get(projectid).getClass().getGenericSuperclass()