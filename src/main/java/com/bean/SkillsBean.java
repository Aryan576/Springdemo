package com.bean;


import javax.validation.constraints.NotNull;

public class SkillsBean {
	

	int skillid;
	@NotNull(message = "Please Enter Skill")
	String skillname;

	public int getSkillid() {
		return skillid;
	}

	public void setSkillid(int skillid) {
		this.skillid = skillid;
	}

	public String getSkillname() {
		return skillname;
	}

	public void setSkillname(String skillname) {
		this.skillname = skillname;
	}

}
