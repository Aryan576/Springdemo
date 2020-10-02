package com.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bean.EmployeeBean;
import com.bean.ResponseBean;
import com.bean.SkillsBean;
import com.dao.EmployeeDao;
@CrossOrigin
@RestController
public class EmployeeController {
	@Autowired
	EmployeeDao dao;

	@PostMapping("addemp")
	public ResponseBean<EmployeeBean> AddEmp(@RequestBody EmployeeBean empBean) {

		ResponseBean<EmployeeBean> response = new ResponseBean<>();

		int empid = dao.addemp(empBean);
		for (SkillsBean sb : empBean.getSkills()) {
			dao.addSkillForEmployee(empid, sb.getSkillid());
			System.out.println(sb.getSkillid());
			System.out.println(sb.getSkillname());

		}
		empBean.setEmployeeid(empid);
		response.setData(empBean);
		response.setMsg("Employee Added");
		response.setStatus(200);

		return response;
	}

	@GetMapping("allemp")
	public ResponseBean<List<EmployeeBean>> listEmp() {

		ResponseBean<List<EmployeeBean>> response = new ResponseBean<>();
		List<EmployeeBean> empBean = dao.listemp();
		for (int i = 0; i < empBean.size(); i++) {
			EmployeeBean eb = empBean.get(i);
			List<SkillsBean> skill = dao.getskillofemployee(eb.getEmployeeid());

			eb.setSkills((ArrayList<SkillsBean>) skill);

		}
		response.setData(empBean);
		response.setMsg("List of Employees");
		response.setStatus(200);

		return response;
	}

	@DeleteMapping("/emp/{employeeid}")
	public ResponseBean<EmployeeBean> deleteemp(@PathVariable("employeeid") int employeeid) {
		ResponseBean<EmployeeBean> response = new ResponseBean<>();
		dao.deleteskillforemployee(employeeid);
		EmployeeBean empBean = dao.deleteEmp(employeeid);
		response.setData(empBean);

		if (empBean != null) {
			response.setMsg("Employee Deleted Successfuly");
		} else {

			response.setMsg("Employee Not Found!");
		}
		response.setStatus(201);
		return response;
	}

	@PutMapping("/updateEmp")
	public ResponseBean<EmployeeBean> updateEmp(@RequestBody EmployeeBean empBean) {

		ResponseBean<EmployeeBean> response = new ResponseBean<>();
		dao.updateEmp(empBean);
		dao.deleteskillforemployee(empBean.getEmployeeid());
		for (SkillsBean sb : empBean.getSkills()) {
			dao.addSkillForEmployee(empBean.getEmployeeid(), sb.getSkillid());
		}
		response.setData(empBean);
		response.setMsg("Employee Updated");
		response.setStatus(200);

		return response;
	}

}
