package loginservlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.CommonDetails;

/**
 * Servlet implementation class LoadCache
 */
@WebServlet("/loadCache")
public class LoadCache extends HttpServlet {
		protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try
		{
			CommonDetails.loadData();
		//	RequestDispatcher rd=request.getRequestDispatcher("index.html");
			//rd.forward(request, response);
		}catch(Exception e) {e.printStackTrace();}
	}

}
