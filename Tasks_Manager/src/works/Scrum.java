package works;

import java.io.Serializable;
import java.util.HashSet;

public class Scrum implements Serializable  
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
	HashSet<String> assign_to=new HashSet<String>();
	private String getWork_id() {
		return work_id;
	}
	private void setWork_id(String work_id) {
		this.work_id = work_id;
	}
	private String getWorkname() {
		return workname;
	}
	private void setWorkname(String workname) {
		this.workname = workname;
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
	private HashSet<String> getAssign_to() {
		return assign_to;
	}
	private void setAssign_to(HashSet<String> assign_to) {
		this.assign_to = assign_to;
	}		
	
}
