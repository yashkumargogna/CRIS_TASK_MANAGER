package works;

import java.io.Serializable;
import java.util.HashMap;

public class Tasks implements Serializable 
{
	String work_id=" ";
	String workname=" ";	
	String desp=" ";
	String id_rel_to=" ";
	String name_rel_to=" ";
	String module=" ";
	String project=" ";//to be added to db
	String task_of=" ";//to be added to db
	String type=" ";
	String status=" ";
	String dept=" ";//to be added to db
	//HashMap<sprintid ,sprint obj having all details of scrum> task_spr;//key here is task id
	HashMap<String,Sprint> task_spr=new HashMap<String,Sprint>();
	//HashMap<scrum id,scrumobj> spr_scr;//key here is sprint id		
	HashMap<String,Scrum> task_scr=new HashMap<String,Scrum>();
	private String getWorkname() {
		return workname;
	}
	private void setWorkname(String workname) {
		this.workname = workname;
	}
	private String getWork_id() {
		return work_id;
	}
	private void setWork_id(String work_id) {
		this.work_id = work_id;
	}
	private String getDesp() {
		return desp;
	}
	private void setDesp(String desp) {
		this.desp = desp;
	}
	private String getId_rel_to() {
		return id_rel_to;
	}
	private void setId_rel_to(String id_rel_to) {
		this.id_rel_to = id_rel_to;
	}
	private String getName_rel_to() {
		return name_rel_to;
	}
	private void setName_rel_to(String name_rel_to) {
		this.name_rel_to = name_rel_to;
	}
	private String getModule() {
		return module;
	}
	private void setModule(String module) {
		this.module = module;
	}
	private String getProject() {
		return project;
	}
	private void setProject(String project) {
		this.project = project;
	}
	private String getTask_of() {
		return task_of;
	}
	private void setTask_of(String task_of) {
		this.task_of = task_of;
	}
	private String getType() {
		return type;
	}
	private void setType(String type) {
		this.type = type;
	}
	private String getStatus() {
		return status;
	}
	private void setStatus(String status) {
		this.status = status;
	}
	private HashMap<String, Sprint> getTask_spr() {
		return task_spr;
	}
	private void setTask_spr(HashMap<String, Sprint> task_spr) {
		this.task_spr = task_spr;
	}
	private HashMap<String, Scrum> getTask_scr() {
		return task_scr;
	}
	private void setTask_scr(HashMap<String, Scrum> task_scr) {
		this.task_scr = task_scr;
	}
	
}
