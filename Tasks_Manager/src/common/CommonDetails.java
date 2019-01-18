package common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

import works.Scrum;
import works.Sprint;
import works.Tasks;
public class CommonDetails
{
	public static Connection con;
	public static Statement st;
	public static HashMap<String,HashMap<Integer,String>> dep_emp=new HashMap<String,HashMap<Integer,String>>();//here department (dept) is key. <dept->(emp_id,emp_name)>
	public static HashMap<String,HashMap<Integer,String>> dep_proj=new HashMap<String,HashMap<Integer,String>>();//here department is key and <dept->(project id,project_name)> 
	public static HashMap<Integer,HashMap<Integer,String>> proj_mod=new HashMap<Integer,HashMap<Integer,String>>();//here project id is key and <p_id->(module_id,module_name)>
	public static HashMap<String,HashMap<String,Tasks>> dep_tasks=new HashMap<String,HashMap<String,Tasks>>();	//key is department name and hashmap (key is task id and value is tasks object)
	
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
			
			
			//------------------------------LOADING WORK DETAILS-----------------------------------------
//json sample string="{"ICMS":{"t":{"work_id":" ","workname":"task","desp":" ","id_rel_to":" ","name_rel_to":" ","module":" ","project":" ","task_of":" ","type":" ","status":" ","dept":" ","st_date":" ","tg_date":" ","task_spr":{"ttosp":{"work_id":" ","workname":"sprint","desp":" ","id_rel_to":" ","name_rel_to":" ","module":" ","project":" ","task_of":" ","type":" ","status":" ","spr_scr":{"scofsp":{"work_id":" ","workname":"scrum of sprint","desp":" ","id_rel_to":" ","name_rel_to":" ","module":" ","project":" ","task_of":" ","type":" ","status":" ","dept":" ","assign_to":[],"st_date":" ","tg_date":" ","remarks":" ","sta_changed_by":" "}},"assign_to":[],"dept":" ","sta_changed_by":" ","st_date":" ","tg_date":" ","incharge":" ","remarks":" "}},"task_scr":{"sot":{"work_id":" ","workname":"scrum check","desp":" ","id_rel_to":" ","name_rel_to":" ","module":" ","project":" ","task_of":" ","type":" ","status":" ","dept":" ","assign_to":[],"st_date":" ","tg_date":" ","remarks":" ","sta_changed_by":" "}},"remarks":" ","sta_changed_by":" ","incharge":" ","assign_to":[]}}}";			
			Set<String> departments=dep_emp.keySet();
			Iterator<String> depts=departments.iterator();
			while(depts.hasNext())
			{
					String dept=depts.next();
					System.out.println(dept);
					ResultSet dep_to_tasks=st.executeQuery("select * from cris_works where work_type=\'TASK\' and dept=\'"+dept+"\'");
					
					
/* ------->   1. SETTING TASKS   */						
						while(dep_to_tasks.next())
						{		
							//setting tasks according to departments
							if(dep_tasks.containsKey(dept))
							{
								HashMap<String,Tasks> task_details=dep_tasks.get(dept);
								Tasks tasks=new Tasks();
								tasks.setWorkname(dep_to_tasks.getString(1));
								tasks.setDesp(dep_to_tasks.getString(3));
								tasks.setId_rel_to(dep_to_tasks.getString(4));
								tasks.setName_rel_to(dep_to_tasks.getString(5));
								tasks.setModule(dep_to_tasks.getString(6));
								tasks.setType(dep_to_tasks.getString(7));
								tasks.setSt_date(dep_to_tasks.getDate(8).toString());//		start date and end date to be processed accordingly
								tasks.setTg_date(dep_to_tasks.getDate(9).toString());	//	start date and end date to be processed accordingly
								tasks.setStatus(dep_to_tasks.getString(10));
								tasks.setRemarks(dep_to_tasks.getString(11));
								tasks.setSta_changed_by(dep_to_tasks.getString(12));
								tasks.setProject(dep_to_tasks.getString(13));
								tasks.setTask_of(dep_to_tasks.getString(14));
								tasks.setDept(dep_to_tasks.getString(15));
								tasks.setAssign_to(str_to_set(dep_to_tasks.getString(16)));
								tasks.setIncharge(dep_to_tasks.getString(17));
								task_details.put(dep_to_tasks.getString(2), tasks);
								
							}
							else
							{
								Tasks tasks=new Tasks();
								tasks.setWorkname(dep_to_tasks.getString(1));
								tasks.setDesp(dep_to_tasks.getString(3));
								tasks.setId_rel_to(dep_to_tasks.getString(4));
								tasks.setName_rel_to(dep_to_tasks.getString(5));
								tasks.setModule(dep_to_tasks.getString(6));
								tasks.setType(dep_to_tasks.getString(7));
								tasks.setSt_date(dep_to_tasks.getDate(8).toString());//		start date and end date to be processed accordingly
								tasks.setTg_date(dep_to_tasks.getDate(9).toString());	//	start date and end date to be processed accordingly
								tasks.setStatus(dep_to_tasks.getString(10));
								tasks.setRemarks(dep_to_tasks.getString(11));
								tasks.setSta_changed_by(dep_to_tasks.getString(12));
								tasks.setProject(dep_to_tasks.getString(13));
								tasks.setTask_of(dep_to_tasks.getString(14));
								tasks.setDept(dep_to_tasks.getString(15));
								tasks.setAssign_to(str_to_set(dep_to_tasks.getString(16)));
								tasks.setIncharge(dep_to_tasks.getString(17));
								HashMap<String,Tasks> task_details=new HashMap<String,Tasks>();
								task_details.put(dep_to_tasks.getString(2), tasks);
								dep_tasks.put(dept,task_details);
							
								
							}
							
						}
/* ------->   2. SETTING SPRINTS   */
						ResultSet dep_to_sprints=st.executeQuery("select * from cris_works where work_type=\'SPRINT\' and dept=\'"+dept+"\'");
						while(dep_to_sprints.next())
						{
							HashMap<String,Tasks> task_details=dep_tasks.get(dept);
							Sprint sprint=new Sprint();
							sprint.setWorkname(dep_to_sprints.getString(1));
							sprint.setDesp(dep_to_sprints.getString(3));
							sprint.setId_rel_to(dep_to_sprints.getString(4));
							sprint.setName_rel_to(dep_to_sprints.getString(5));
							sprint.setModule(dep_to_sprints.getString(6));
							sprint.setType(dep_to_sprints.getString(7));
							sprint.setSt_date(dep_to_sprints.getDate(8).toString());
							sprint.setTg_date(dep_to_sprints.getDate(9).toString());
							sprint.setStatus(dep_to_sprints.getString(10));
							sprint.setRemarks(dep_to_sprints.getString(11));
							sprint.setSta_changed_by(dep_to_sprints.getString(12));
							sprint.setProject(dep_to_sprints.getString(13));
							sprint.setTask_of(dep_to_sprints.getString(14));
							sprint.setDept(dep_to_sprints.getString(15));
							sprint.setAssign_to(str_to_set(dep_to_sprints.getString(16)));
							sprint.setIncharge(dep_to_sprints.getString(17));
							Tasks t=task_details.get(dep_to_sprints.getString(4));
							HashMap<String,Sprint> hm=t.getTask_spr();
							hm.put(dep_to_sprints.getString(2),sprint);
							
							t.setTask_spr(hm);
							task_details.put(dep_to_sprints.getString(4),t);
							dep_tasks.put(dept, task_details);
						}
						
/* ------->   3. SETTING SCRUMS   */						
						ResultSet dep_to_scrums=st.executeQuery("select * from cris_works where work_type=\'SCRUM\' and dept=\'"+dept+"\'");
						while(dep_to_scrums.next())
						{
							Scrum scrum=new Scrum();
							scrum.setWorkname(dep_to_scrums.getString(1));
							scrum.setDesp(dep_to_scrums.getString(3));
							scrum.setId_rel_to(dep_to_scrums.getString(4));
							scrum.setName_rel_to(dep_to_scrums.getString(5));
							scrum.setModule(dep_to_scrums.getString(6));
							scrum.setType(dep_to_scrums.getString(7));
							scrum.setSt_date(dep_to_scrums.getDate(8).toString());
							scrum.setTg_date(dep_to_scrums.getDate(9).toString());
							scrum.setStatus(dep_to_scrums.getString(10));
							scrum.setRemarks(dep_to_scrums.getString(11));
							scrum.setSta_changed_by(dep_to_scrums.getString(12));
							scrum.setProject(dep_to_scrums.getString(13));
							scrum.setTask_of(dep_to_scrums.getString(14));
							scrum.setDept(dep_to_scrums.getString(15));
							scrum.setAssign_to(str_to_set(dep_to_scrums.getString(16)));
							
							if(dep_to_scrums.getString(4).equals(dep_to_scrums.getString(14)))
							{
								HashMap<String,Tasks> task_details=dep_tasks.get(dept);
								Tasks t=task_details.get(dep_to_scrums.getString(4));
								HashMap<String, Scrum> shm=t.getTask_scr();
								shm.put(dep_to_scrums.getString(2),scrum);
								t.setTask_scr(shm);
								task_details.put(dep_to_scrums.getString(4),t);
								dep_tasks.put(dept,task_details);
								
							}
							else	
							{
								HashMap<String,Tasks> task_details=dep_tasks.get(dept);
								
								Tasks t=task_details.get(dep_to_scrums.getString(14));
								
								HashMap<String,Sprint> sphm=t.getTask_spr();
								
								Sprint sprint=sphm.get(dep_to_scrums.getString(4));
								HashMap<String, Scrum> scrums_of_sprints=sprint.getSpr_scr();
								scrums_of_sprints.put(dep_to_scrums.getString(2),scrum);
								sprint.setSpr_scr(scrums_of_sprints);
								
								sphm.put(dep_to_scrums.getString(4),sprint);
								t.setTask_spr(sphm);
								
								task_details.put(dep_to_scrums.getString(14),t);
								
								dep_tasks.put(dept, task_details); 	
								
							}	
							
							
								
							
						}
						
						
						
						
						
						
						
						
						
					 	
					
			}
			
		
		

		
		

		}

		private static HashSet<String> str_to_set(String ids) 
		{
			HashSet<String> hs=new HashSet<String>();
			if(ids.contains(","))
			{	
				String e_id[]=ids.split(",");
				for(int i=0;i<e_id.length;i++)
				{
					hs.add(e_id[i]); 
				}
			}
			else
			{
				hs.add(ids);
			}
			return hs;
		}	

}
