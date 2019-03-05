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
	Tasks t=new Tasks();
	HashSet<Integer> persons_to_notify=new HashSet<Integer>();
	
	if(action.equalsIgnoreCase("CREATE TASK"))
	{
		t=gson.fromJson(jo.get("task").getAsJsonObject(),Tasks.class);
		persons_to_notify.addAll(t.getIncharge());
		persons_to_notify.addAll(t.getAssign_to());
		persons_to_notify.addAll(SessionHandler.dep_admins.get(dept));
		Iterator<Integer> itr_emp_no=persons_to_notify.iterator();
		while(itr_emp_no.hasNext())
		{
			Integer emp_no=itr_emp_no.next();
			//if(emp_no!=sender_eid)
			//{	
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
			//}		
		}
	}
	
}
}
