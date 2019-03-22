package common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import works.Scrum;
import works.Sprint;
import works.Tasks;

/**
 * Websocket Endpoint implementation class Notify */

@ServerEndpoint("/Notify")

public class Notify {
    
    public Notify() 
    {
        super();
        // TODO Auto-generated constructor stub
    }
    @OnOpen
    public void open(Session session) 
    {
    	
        	System.out.println("web socket on open");
    	Map<String,List<String>> user_loggedin=session.getRequestParameterMap();
    	int eid=Integer.parseInt(user_loggedin.get("eid").get(0));
    	String dept=user_loggedin.get("dept").get(0);
    	String page_type=user_loggedin.get("page_type").get(0);
    	if(SessionHandler.dep_emp_sessions.containsKey(dept))
    	{
    		if(SessionHandler.dep_emp_sessions.get(dept).containsKey(eid))
    		{SessionHandler.dep_emp_sessions.get(dept).get(eid).add(session);}
    		else
    		{
    			ArrayList<Session> al=new ArrayList<Session>();
    			al.add(session);
    			SessionHandler.dep_emp_sessions.get(dept).put(eid,al);}	
    	}
    	else 
    	{
    		ArrayList<Session> al=new ArrayList<Session>();
			al.add(session);
			HashMap<Integer, ArrayList<Session>> hm=new HashMap<Integer, ArrayList<Session>>(); 
    		hm.put(eid,al);
			SessionHandler.dep_emp_sessions.put(dept,hm);
    	}
    	if(page_type.equalsIgnoreCase("admin"))
    	{
    		if(SessionHandler.dep_admins.containsKey(dept))
    		{SessionHandler.dep_admins.get(dept).add(eid);}
    		else
    		{

    			HashSet<Integer> value=new HashSet<Integer>(); 
    			value.add(eid);
    			SessionHandler.dep_admins.put(dept, value);
    		}	
    	}
    	System.out.println(SessionHandler.dep_emp_sessions.toString());
    	System.out.println("-----------------");
    	System.out.println(SessionHandler.dep_admins.toString());
    	System.out.println("--------on open---------");
    	
    	
    }
    @OnClose
    public void close(Session session) {
    	System.out.println("ON CLOSE EVENT:::::::::"+session);
    	System.out.println("web socket on close ----------->");
    try
    {
    	Map<String,List<String>> user_loggedin=session.getRequestParameterMap();
    	int eid=Integer.parseInt(user_loggedin.get("eid").get(0));
    	String dept=user_loggedin.get("dept").get(0);
    	String page_type=user_loggedin.get("page_type").get(0);
    	System.out.println(eid+" "+dept+" "+page_type);
    	int a=SessionHandler.dep_emp_sessions.get(dept).get(eid).size();
    	System.out.println("AFTER A"+a);
    	if(a>1)
    	{	
    		SessionHandler.dep_emp_sessions.get(dept).get(eid).remove(session);	
    	}
    	else
    	{
    		SessionHandler.dep_emp_sessions.get(dept).remove(eid);
    		System.out.println(SessionHandler.dep_emp_sessions.get(dept).containsKey(eid));
    		if(SessionHandler.dep_admins.get(dept).contains(eid))
	    	{	
	    		
	    		SessionHandler.dep_admins.get(dept).remove(eid);
	    		
	    	}
    	}
    	System.out.println("AFTER else");
    	System.out.println(SessionHandler.dep_emp_sessions.toString());
    	System.out.println("-----------------");
    	System.out.println(SessionHandler.dep_admins.toString());
    	System.out.println("--------on close---------");
    }catch(Exception e)
    {System.out.println(e);
    e.printStackTrace();
    }
    }

@OnError
    public void onError(Throwable error) {
	System.out.println(" error:::::::::::::"+error);
	error.printStackTrace();
}

@OnMessage
    public void handleMessage(String message, Session session)
{
	Map<String,List<String>> user_loggedin=session.getRequestParameterMap();
	int sender_eid=Integer.parseInt(user_loggedin.get("eid").get(0));
	String dept=user_loggedin.get("dept").get(0);
	
	
	System.out.println(message);
	Gson gson=new Gson();
	JsonParser jsonParser=new JsonParser();
	JsonElement je=jsonParser.parse(message);
	JsonObject jo=je.getAsJsonObject();
	String action=jo.get("action").getAsString();
	System.out.println("ACTION :::::::- AFTER PARSING :-----"+action);
	
	
	
	
	if(action.equalsIgnoreCase("CREATE TASK"))
	{
		HashSet<Integer> persons_to_notify=new HashSet<Integer>();
		Tasks t=new Tasks();
		t=gson.fromJson(jo.get("task").getAsJsonObject(),Tasks.class);
		persons_to_notify.addAll(t.getIncharge());
		persons_to_notify.addAll(t.getAssign_to());
		persons_to_notify.addAll(SessionHandler.dep_admins.get(dept));
		Iterator<Integer> itr_emp_no=persons_to_notify.iterator();
		while(itr_emp_no.hasNext())
		{
			Integer emp_no=itr_emp_no.next();
					if(SessionHandler.dep_emp_sessions.get(dept).containsKey(emp_no))
					{
						ArrayList<Session> al_ss=SessionHandler.dep_emp_sessions.get(dept).get(emp_no);
						Iterator<Session> iterator_session=al_ss.iterator();
						while(iterator_session.hasNext())
						{
							Session s_send=iterator_session.next();
							if(s_send.isOpen())
							{
									try
									{
										s_send.getBasicRemote().sendText(message);
									}catch(Exception e) {System.out.println(e);}	
							}		
						}
					}
					
		}
	}
	else if(action.equalsIgnoreCase("CREATE SCRUMOFSPRINT"))
	{
		
		HashSet<Integer> persons_to_notify=new HashSet<Integer>();
		Scrum s=new Scrum();
		s=gson.fromJson(jo.get("scrum").getAsJsonObject(),Scrum.class);
		persons_to_notify.addAll(CommonDetails.dep_tasks.get(dept).get(s.getTask_of()).getIncharge());
		persons_to_notify.addAll(CommonDetails.dep_tasks.get(dept).get(s.getTask_of()).getTask_spr().get(s.getId_rel_to()).getIncharge());
		persons_to_notify.addAll(s.getAssign_to());
		persons_to_notify.addAll(SessionHandler.dep_admins.get(dept));
		Iterator<Integer> itr_emp_no=persons_to_notify.iterator();
		while(itr_emp_no.hasNext())
		{
			Integer emp_no=itr_emp_no.next();
					if(SessionHandler.dep_emp_sessions.get(dept).containsKey(emp_no))
					{
						ArrayList<Session> al_ss=SessionHandler.dep_emp_sessions.get(dept).get(emp_no);
						Iterator<Session> iterator_session=al_ss.iterator();
						while(iterator_session.hasNext())
						{
							Session s_send=iterator_session.next();
							if(s_send.isOpen())
							{
									try
									{
										s_send.getBasicRemote().sendText(message);
									}catch(Exception e) {System.out.println(e);}	
							}		
						}
					}

		}	
	
	}
	else if(action.equalsIgnoreCase("CREATE SCRUMOFTASK"))
	{
		Scrum s=new Scrum();
		HashSet<Integer> persons_to_notify=new HashSet<Integer>();
		s=gson.fromJson(jo.get("scrum").getAsJsonObject(),Scrum.class);
		persons_to_notify.addAll(CommonDetails.dep_tasks.get(dept).get(s.getTask_of()).getIncharge());
		
		persons_to_notify.addAll(s.getAssign_to());
		persons_to_notify.addAll(SessionHandler.dep_admins.get(dept));
		Iterator<Integer> itr_emp_no=persons_to_notify.iterator();
		while(itr_emp_no.hasNext())
		{
			Integer emp_no=itr_emp_no.next();
					if(SessionHandler.dep_emp_sessions.get(dept).containsKey(emp_no))
					{
						ArrayList<Session> al_ss=SessionHandler.dep_emp_sessions.get(dept).get(emp_no);
						Iterator<Session> iterator_session=al_ss.iterator();
						while(iterator_session.hasNext())
						{
							Session s_send=iterator_session.next();
							if(s_send.isOpen())
							{
									try
									{
										s_send.getBasicRemote().sendText(message);
									}catch(Exception e) {System.out.println(e);}	
							}		
						}
					}

		}	
	
	}
	else if(action.equalsIgnoreCase("CREATE SPRINT"))
	{
		Sprint s=new Sprint();
		HashSet<Integer> persons_to_notify=new HashSet<Integer>();
		s=gson.fromJson(jo.get("sprint").getAsJsonObject(),Sprint.class);
		persons_to_notify.addAll(CommonDetails.dep_tasks.get(dept).get(s.getTask_of()).getIncharge());
		persons_to_notify.addAll(s.getIncharge());
		persons_to_notify.addAll(s.getAssign_to());
		persons_to_notify.addAll(SessionHandler.dep_admins.get(dept));
		Iterator<Integer> itr_emp_no=persons_to_notify.iterator();
		while(itr_emp_no.hasNext())
		{
			Integer emp_no=itr_emp_no.next();
					if(SessionHandler.dep_emp_sessions.get(dept).containsKey(emp_no))
					{
						ArrayList<Session> al_ss=SessionHandler.dep_emp_sessions.get(dept).get(emp_no);
						Iterator<Session> iterator_session=al_ss.iterator();
						while(iterator_session.hasNext())
						{
							Session s_send=iterator_session.next();
							if(s_send.isOpen())
							{
									try
									{
										s_send.getBasicRemote().sendText(message);
									}catch(Exception e) {System.out.println(e);}	
							}		
						}
					}

		}	
	
	}
	else if(action.equalsIgnoreCase("STATUSCHANGE"))
	{
		HashSet<Integer> persons_to_notify=new HashSet<Integer>();
		System.out.println("STATUSCHANGE");
		String w_id=jo.get("w_id").getAsString().trim();
		if(w_id.startsWith("T="))
		{
			persons_to_notify.addAll(CommonDetails.dep_tasks.get(dept).get(w_id).getAssign_to());
			persons_to_notify.addAll(CommonDetails.dep_tasks.get(dept).get(w_id).getIncharge());
			persons_to_notify.addAll(SessionHandler.dep_admins.get(dept));
		}
		else if(w_id.startsWith("S="))
		{
				String split[]=w_id.split("T=");
				String t_id="T="+split[1];
				String sprint_id=w_id;
				persons_to_notify.addAll(CommonDetails.dep_tasks.get(dept).get(t_id).getIncharge());
				persons_to_notify.addAll(CommonDetails.dep_tasks.get(dept).get(t_id).getTask_spr().get(sprint_id).getIncharge());
				persons_to_notify.addAll(CommonDetails.dep_tasks.get(dept).get(t_id).getTask_spr().get(sprint_id).getAssign_to());
				persons_to_notify.addAll(SessionHandler.dep_admins.get(dept));
		}
		else if(w_id.startsWith("SC="))
		{
			String split[]=w_id.split("T=");
			String t_id="T="+split[1];
			String scrum_id=w_id;
			persons_to_notify.addAll(CommonDetails.dep_tasks.get(dept).get(t_id).getTask_scr().get(scrum_id).getAssign_to());
			persons_to_notify.addAll(CommonDetails.dep_tasks.get(dept).get(t_id).getIncharge());
			persons_to_notify.addAll(SessionHandler.dep_admins.get(dept));
		}
		else if(w_id.startsWith("SR="))
		{				
			String split_for_task[]=w_id.split("T=");
			String t_id="T="+split_for_task[1];
			String scrum_of_sprint_id=w_id;
			String split_for_sprint[]=w_id.split("S=");
			String spr_id="S="+split_for_sprint[1];
			
			persons_to_notify.addAll(CommonDetails.dep_tasks.get(dept).get(t_id).getTask_spr().get(spr_id).getSpr_scr().get(scrum_of_sprint_id).getAssign_to());
			persons_to_notify.addAll(CommonDetails.dep_tasks.get(dept).get(t_id).getTask_spr().get(spr_id).getIncharge());
			persons_to_notify.addAll(CommonDetails.dep_tasks.get(dept).get(t_id).getIncharge());
			persons_to_notify.addAll(SessionHandler.dep_admins.get(dept));
		}
		Iterator<Integer> itr_emp_no=persons_to_notify.iterator();
		while(itr_emp_no.hasNext())
		{
			Integer emp_no=itr_emp_no.next();
					if(SessionHandler.dep_emp_sessions.get(dept).containsKey(emp_no))
					{
						ArrayList<Session> al_ss=SessionHandler.dep_emp_sessions.get(dept).get(emp_no);
						Iterator<Session> iterator_session=al_ss.iterator();
						while(iterator_session.hasNext())
						{
							Session s_send=iterator_session.next();
							if(s_send.isOpen())
							{
									try
									{
										s_send.getBasicRemote().sendText(message);
									}catch(Exception e) {System.out.println(e);}	
							}		
						}
					}

		}	


	}	


}
}
