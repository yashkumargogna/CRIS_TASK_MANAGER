package loginservlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.UserDet;


/**
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class Login extends HttpServlet 
{
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
	Connection con;
	Statement st;
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
					
					// TODO Auto-generated method stub
					response.setContentType("text/html");
					PrintWriter out=response.getWriter();
					//out.println("welcome");
					int username=Integer.parseInt(request.getParameter("username"));
					String password=request.getParameter("password");
					try
					{
					
									DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());	
							    	con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","yash");
							    	st=con.createStatement();
							    	ServletContext context=request.getServletContext();
							    	context.setAttribute("conn",con);
							    	// System.out.println(con);
							    	
							    	
								ResultSet user=st.executeQuery("select * from cris_emp where Emp_id="+username);
								if(user.next())
								{
									if(user.getString(4).equals(password))
									{
										HttpSession session=request.getSession();
										UserDet	ud=getUserObj(user);
										session.setAttribute("UserDet",ud);
										if(ud.getRole().equalsIgnoreCase("P.P.E")||ud.getRole().equalsIgnoreCase("GM")||ud.getGrant().equalsIgnoreCase("yes"))
										{	
											
											//request.setAttribute("UserDet",ud);
											RequestDispatcher rd=request.getRequestDispatcher("admin.jsp");
										    rd.forward(request, response);
									
										}
										else 
										{
											RequestDispatcher rd=request.getRequestDispatcher("emp.jsp");
										    rd.forward(request, response);
									
										}
									}
									else
									{
										RequestDispatcher rd=request.getRequestDispatcher("index.html");
									    rd.include(request, response);
									    out.println("INVALID USER OR PASSWORD");
										
									}	
									
								}	
								
								else
									{
										
										RequestDispatcher rd=request.getRequestDispatcher("index.html");
									    rd.include(request, response);
									    out.println("INVALID USER OR PASSWORD");
									}
								
				    }catch(Exception e) {e.printStackTrace(out);}	
	}
	private UserDet getUserObj(ResultSet rs) throws Exception
	{
		UserDet ud=new UserDet();
		ud.setEname(rs.getString(2));
		ud.setEid(Integer.parseInt(rs.getString(1)));
		ud.setRole(rs.getString(5));
		ud.setDept(rs.getString(3));
		ud.setGrant(rs.getString(6));
		return ud;
	}
}
