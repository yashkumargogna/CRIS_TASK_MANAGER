package common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import javax.websocket.Session;

public class SessionHandler
{
	public static HashMap<String,HashMap<Integer,ArrayList<Session>>> dep_emp_sessions=new HashMap<String,HashMap<Integer,ArrayList<Session>>>(); 
	public static HashMap<String,HashSet<Integer>> dep_admins=new HashMap<String,HashSet<Integer>>();
	public static HashMap<String, HashMap<Integer, ArrayList<Session>>> getDep_emp_sessions() {
		return dep_emp_sessions;
	}
	public static void setDep_emp_sessions(HashMap<String, HashMap<Integer, ArrayList<Session>>> dep_emp_sessions) {
		SessionHandler.dep_emp_sessions = dep_emp_sessions;
	}
	public static HashMap<String, HashSet<Integer>> getDep_admins() {
		return dep_admins;
	}
	public static void setDep_admins(HashMap<String, HashSet<Integer>> dep_admins) {
		SessionHandler.dep_admins = dep_admins;
	}
}
