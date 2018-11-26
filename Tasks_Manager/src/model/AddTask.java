package model;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

public class AddTask implements Serializable
{
	int task_id;
	String task_name;
	String task_desp;
	Date st_dt;
	Date tg_dt;
	String task_type;
	int id_related_to;
	String module;
	public int getId_related_to() {
		return id_related_to;
	}
	public void setId_related_to(int id_related_to) {
		this.id_related_to = id_related_to;
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
	String project;
	List<Integer> emp;
	public int getTask_id() {
		return task_id;
	}
	public void setTask_id(int task_id) {
		this.task_id = task_id;
	}
	public String getTask_name() {
		return task_name;
	}
	public void setTask_name(String task_name) {
		this.task_name = task_name;
	}
	public String getTask_desp() {
		return task_desp;
	}
	public void setTask_desp(String task_desp) {
		this.task_desp = task_desp;
	}
	public Date getSt_dt() {
		return st_dt;
	}
	public void setSt_dt(Date st_dt) {
		this.st_dt = st_dt;
	}
	public Date getTg_dt() {
		return tg_dt;
	}
	public void setTg_dt(Date tg_dt) {
		this.tg_dt = tg_dt;
	}
	public String getTask_type() {
		return task_type;
	}
	public void setTask_type(String task_type) {
		this.task_type = task_type;
	}
	public List<Integer> getEmp() {
		return emp;
	}
	public void setEmp(List<Integer> emp) {
		this.emp = emp;
	}
	
}
