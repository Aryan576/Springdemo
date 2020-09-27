package com.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bean.ResponseBean;
import com.bean.SkillsBean;

import com.dao.SkillDao;

@RestController
public class SkillController {
	@Autowired
	SkillDao skilldao;

	@PostMapping("addskill")
	public ResponseBean<SkillsBean> addskill(@Valid @RequestBody SkillsBean skillbean) {
		skilldao.addskill(skillbean);
		ResponseBean<SkillsBean> responseBean = new ResponseBean<>();
		responseBean.setData(skillbean);
		responseBean.setMsg("Data Inserted");
		responseBean.setStatus(201);
		return responseBean;
	}

	@GetMapping("skills")
	public ResponseBean<List<SkillsBean>> skills() {

		ResponseBean<List<SkillsBean>> responseBean = new ResponseBean<>();

		List<SkillsBean> skillsbeans = skilldao.listskills();
		responseBean.setData(skillsbeans);
		responseBean.setMsg("All Skills");
		responseBean.setStatus(201);
		return responseBean;
	}

	@DeleteMapping("/skill/{skillid}")
	public ResponseBean<SkillsBean> deleteskill(@PathVariable("skillid") int skillid) {

		ResponseBean<SkillsBean> responseBean = new ResponseBean<>();
		
		SkillsBean skillBean = skilldao.deleteskill(skillid);
		
		responseBean.setData(skillBean);
		if (skillBean != null) {
			responseBean.setMsg("Skill Removed");
		} else {
			responseBean.setMsg("Skill Not Found");
		}
		responseBean.setStatus(200);

		return responseBean;

	}

	@PutMapping("/updateskill")
	public ResponseBean<SkillsBean> updateSkill(SkillsBean skillBean) {
		skilldao.updateSkill(skillBean);

		ResponseBean<SkillsBean> responseBean = new ResponseBean<>();

		responseBean.setData(skillBean);
		responseBean.setMsg("Skill Updated");
		responseBean.setStatus(201);
		return responseBean;
	}

}
