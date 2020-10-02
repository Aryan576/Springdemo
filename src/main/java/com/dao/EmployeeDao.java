package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.bean.EmployeeBean;
import com.bean.SkillsBean;
@CrossOrigin
@Repository
public class EmployeeDao {

	@Autowired
	JdbcTemplate stmt;

	public int addemp(EmployeeBean empBean) {
		KeyHolder keyholder = new GeneratedKeyHolder();
		String qry = "insert into employee (ename,salary) values(?,?)";

		stmt.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				// TODO Auto-generated method stub

				PreparedStatement pstmt = con.prepareStatement(qry, Statement.RETURN_GENERATED_KEYS);
				pstmt.setString(1, empBean.getEname());
				pstmt.setInt(2, empBean.getSalary());
				return pstmt;
			}
		}, keyholder);

		int empid = (Integer) keyholder.getKeys().get("employeeid");
		return empid;
	}

	public void addSkillForEmployee(int empid, int skillid) {
		stmt.update("insert into empskills (empid,skillid) values(?,?)", empid, skillid);

	}

	public List<EmployeeBean> listemp() {

		List<EmployeeBean> empBean = stmt.query("select * from employee",
				BeanPropertyRowMapper.newInstance(EmployeeBean.class));

		return empBean;

	}

	public EmployeeBean getEmployeeById(int employeeid) {
		EmployeeBean empBean = null;

		try {

			empBean = stmt.queryForObject("select * from employee where employeeid = ? ", new Object[] { employeeid },
					BeanPropertyRowMapper.newInstance(EmployeeBean.class));

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Employee NotFound" + e.getMessage());
		}

		return empBean;
	}

	public EmployeeBean deleteEmp(int employeeid) {
		EmployeeBean empBean = null;
		empBean = getEmployeeById(employeeid);
		if (empBean != null) {

			stmt.update("delete from employee where employeeid =? ", employeeid);
		}
		return empBean;
	}

	public EmployeeBean updateEmp(EmployeeBean empBean) {

		stmt.update("update employee set ename=?,salary=? where employeeid=?", empBean.getEname(), empBean.getSalary(),
				empBean.getEmployeeid());
		return empBean;
	}

	public void deleteskillforemployee(int employeeid) {
		// TODO Auto-generated method stub
		stmt.update("delete from empskills where empid=?", employeeid);

	}

	public List<SkillsBean> getskillofemployee(int employeeid) {
		// TODO Auto-generated method stub

		List<SkillsBean> skills = stmt.query("select s.skillname,s.skillid from skills s ,empskills es where es.empid ="+employeeid+" and s.skillid= es.skillid", BeanPropertyRowMapper.newInstance(SkillsBean.class));
		return skills;
	}

}
