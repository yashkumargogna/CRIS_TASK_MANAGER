package works;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

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
	String st_date=" ";
	String tg_date=" ";
	String remarks=" ";
	String sta_changed_by=" ";
	String incharge=" ";
	HashSet<String> assign_to=new HashSet<String>();
	//HashMap<sprintid ,sprint obj having all details of scrum> task_spr;//key here is task id
	HashMap<String,Sprint> task_spr=new HashMap<String,Sprint>();
	//HashMap<scrum id,scrumobj> spr_scr;//key here is sprint id		
	HashMap<String,Scrum> task_scr=new HashMap<String,Scrum>();
	public String getIncharge() {
		return incharge;
	}
	public void setIncharge(String incharge) {
		this.incharge = incharge;
	}
	public HashSet<String> getAssign_to() {
		return assign_to;
	}
	public void setAssign_to(HashSet<String> assign_to) {
		this.assign_to = assign_to;
	}
	public String getSta_changed_by() {
		return sta_changed_by;
	}
	public void setSta_changed_by(String sta_changed_by) {
		this.sta_changed_by = sta_changed_by;
	}
	public String getSt_date() {
		return st_date;
	}
	public void setSt_date(String st_date) {
		this.st_date = st_date;
	}
	public String getTg_date() {
		return tg_date;
	}
	public void setTg_date(String tg_date) {
		this.tg_date = tg_date;
	}

	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getWork_id() {
		return work_id;
	}
	public void setWork_id(String work_id) {
		this.work_id = work_id;
	}
	public String getWorkname() {
		return workname;
	}
	public void setWorkname(String workname) {
		this.workname = workname;
	}
	public String getDesp() {
		return desp;
	}
	public void setDesp(String desp) {
		this.desp = desp;
	}
	public String getId_rel_to() {
		return id_rel_to;
	}
	public void setId_rel_to(String id_rel_to) {
		this.id_rel_to = id_rel_to;
	}
	public String getName_rel_to() {
		return name_rel_to;
	}
	public void setName_rel_to(String name_rel_to) {
		this.name_rel_to = name_rel_to;
	}
	public String getModule() {
		return module;
	}
	public void setModule(String module) {
		this.module = module;
	}
	public String getProject() {
		return project;
	}
	public void setProject(String project) {
		this.project = project;
	}
	public String getTask_of() {
		return task_of;
	}
	public void setTask_of(String task_of) {
		this.task_of = task_of;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public HashMap<String, Sprint> getTask_spr() {
		return task_spr;
	}
	public void setTask_spr(HashMap<String, Sprint> task_spr) {
		this.task_spr = task_spr;
	}
	public HashMap<String, Scrum> getTask_scr() {
		return task_scr;
	}
	public void setTask_scr(HashMap<String, Scrum> task_scr) {
		this.task_scr = task_scr;
	}
	
	
		
}
