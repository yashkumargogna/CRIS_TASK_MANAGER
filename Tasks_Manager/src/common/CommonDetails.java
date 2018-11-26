package common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;
public class CommonDetails
{
	public static Connection con;
	public static Statement st;
	public static HashMap<String,HashMap<Integer,String>> dep_emp=new HashMap<String,HashMap<Integer,String>>();
	public static HashMap<String,HashMap<Integer,String>> dep_proj=new HashMap<String,HashMap<Integer,String>>();
	public static HashMap<Integer,HashMap<Integer,String>> proj_mod=new HashMap<Integer,HashMap<Integer,String>>();
			static
			{
				try
				{
				DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
				con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","yash");
				st=con.createStatement(); 
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				
			}	
	
		public static void loadData() throws Exception
		{
			ResultSet dep_to_emp=st.executeQuery("select Emp_id,Ename,Dept from cris_emp");
			while(dep_to_emp.next())//resultset name
			{
					if(dep_emp.containsKey(dep_to_emp.getString(3)))//collection name
					{
								HashMap<Integer,String> emp_det=dep_emp.get(dep_to_emp.getString(3));//collection name of value of given key
								if(emp_det.containsKey(dep_to_emp.getInt(1)))
								{}
								else
								{
									emp_det.put(dep_to_emp.getInt(1),dep_to_emp.getString(2));
									dep_emp.put(dep_to_emp.getString(3),emp_det);//value updated of main collection
								}
							
					}
					else
					{
						HashMap<Integer,String> hm=new HashMap<Integer,String>();
						hm.put(dep_to_emp.getInt(1),dep_to_emp.getString(2));
						dep_emp.put(dep_to_emp.getString(3),hm);
						
					}
					
				
			}	
			
			
			
			
			
			
			
			ResultSet dep_to_proj=st.executeQuery("select Project_id,Project_name,Dept from cris_project");
			while(dep_to_proj.next())
			{
					if(dep_proj.containsKey(dep_to_proj.getString(3)))
					{
								HashMap<Integer,String> proj_det=dep_proj.get(dep_to_proj.getString(3));
								if(proj_det.containsKey(dep_to_proj.getInt(1)))
								{}
								else
								{
									proj_det.put(dep_to_proj.getInt(1),dep_to_proj.getString(2));
									dep_proj.put(dep_to_proj.getString(3),proj_det);
								}
							
					}
					else
					{
						HashMap<Integer,String> hm=new HashMap<Integer,String>();
						hm.put(dep_to_proj.getInt(1),dep_to_proj.getString(2));
						dep_proj.put(dep_to_proj.getString(3),hm);
						
					}
					
				
			}	
			
			
			
			
			
			
			ResultSet proj_to_mod=st.executeQuery("select Module_id,Module_name,Project_id from cris_module");
			while(proj_to_mod.next())
			{
					if(proj_mod.containsKey(proj_to_mod.getInt(3)))
					{
								HashMap<Integer,String> mod_det=proj_mod.get(proj_to_mod.getInt(3));
								if(mod_det.containsKey(proj_to_mod.getInt(1)))
								{}
								else
								{
									mod_det.put(proj_to_mod.getInt(1),proj_to_mod.getString(2));
									proj_mod.put(proj_to_mod.getInt(3),mod_det);
								}
							
					}
					else
					{
						HashMap<Integer,String> hm=new HashMap<Integer,String>();
						hm.put(proj_to_mod.getInt(1),proj_to_mod.getString(2));
						proj_mod.put(proj_to_mod.getInt(3),hm);
						
					}
					
				
			}	
		
		}

		public static void main(String args[])
		{
			try
			{
			CommonDetails.loadData();
			HashMap<Integer,String> emp_det1=dep_emp.get("ICMS");
			System.out.println(emp_det1.get(8431)+"   and   "+emp_det1.get(5468));
			
			HashMap<Integer,String> proj_det1=dep_proj.get("ICMS");
			System.out.println(proj_det1.get(2)+"   and   "+proj_det1.get(3));
			HashMap<Integer,String> proj_det2=dep_proj.get("NEW");
			System.out.println(proj_det2.get(4));
			
			HashMap<Integer,String> mod_det1=proj_mod.get(3);
			System.out.println(mod_det1.get(1)+"   and   "+mod_det1.get(2));
			
			
			}
			catch(Exception e){e.printStackTrace();}
		}





}
