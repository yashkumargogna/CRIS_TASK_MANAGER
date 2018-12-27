package works;

import java.io.Serializable;
import java.util.HashMap;

public class Tasks implements Serializable 
{
	String workname;	
	String work_id;
	String desp;
	String id_rel_to;
	String name_rel_to;
	String module;
	String project;//to be added to db
	String task_of;//to be added to db
	String type;
	String status;
	HashMap<String,Sprint> task_spr;//key here is task id
	HashMap<String,Scrum> spr_scr;//key here is sprint id		
	HashMap<String,Scrum> task_scr;//key here is task id	
	
	
	
	
	
	
	
}
