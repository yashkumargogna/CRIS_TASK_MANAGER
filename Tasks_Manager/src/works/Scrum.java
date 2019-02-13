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
	HashSet<Integer> assign_to=new HashSet<Integer>();
	String st_date=" ";
	String tg_date=" ";
	String remarks=" ";
	String sta_changed_by=" ";
	String module_id=" ";
	String project_id=" ";
	String work_catg=" ";

	public String getModule_id() {
		return module_id;
	}
	public void setModule_id(String module_id) {
		this.module_id = module_id;
	}
	public String getProject_id() {
		return project_id;
	}
	public void setProject_id(String project_id) {
		this.project_id = project_id;
	}
	public String getWork_catg() {
		return work_catg;
	}
	public void setWork_catg(String work_catg) {
		this.work_catg = work_catg;
	}
	public String getSta_changed_by() {
		return sta_changed_by;
	}
	public void setSta_changed_by(String sta_changed_by) {
		this.sta_changed_by = sta_changed_by;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
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
	public HashSet<Integer> getAssign_to() {
		return assign_to;
	}
	public void setAssign_to(HashSet<Integer> assign_to) {
		this.assign_to = assign_to;
	}		
	
}
