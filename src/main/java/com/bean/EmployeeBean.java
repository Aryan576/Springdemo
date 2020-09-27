package com.bean;

import java.util.ArrayList;

public class EmployeeBean {

	int employeeid;
	String ename;
	int salary;

	ArrayList<SkillsBean> skills = new ArrayList<>();

	public int getEmployeeid() {
		return employeeid;
	}

	public void setEmployeeid(int employeeid) {
		this.employeeid = employeeid;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public ArrayList<SkillsBean> getSkills() {
		return skills;
	}

	public void setSkills(ArrayList<SkillsBean> skills) {
		this.skills = skills;
	}

}
