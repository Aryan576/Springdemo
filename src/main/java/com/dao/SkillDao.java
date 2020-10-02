package com.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.bean.SkillsBean;
@CrossOrigin
@Repository
public class SkillDao {
	@Autowired
	JdbcTemplate stmt;

	public void addskill(SkillsBean skillbean) {

		stmt.update("insert into skills (skillname) values (?) ", skillbean.getSkillname());

	}

	public List<SkillsBean> listskills() {

		List<SkillsBean> skillbean = stmt.query("select * from skills", new SkillsRowMapper());
		return skillbean;

	}

	class SkillsRowMapper implements RowMapper<SkillsBean> {

		public SkillsBean mapRow(ResultSet rs, int rowNum) throws SQLException {
			// TODO Auto-generated method stub
			SkillsBean skillBean = new SkillsBean();
			skillBean.setSkillid(rs.getInt("skillid"));

			skillBean.setSkillname(rs.getString("skillname"));

			return skillBean;
		}

	}

	public SkillsBean deleteskill(int skillid) {
		// TODO Auto-generated method stub

		SkillsBean skillBean = null;
		skillBean = getSkillById(skillid);
		if (skillBean != null) {
			stmt.update("delete from skills where skillid=?", skillid);
		}

		return skillBean;
	}

	public SkillsBean getSkillById(int skillid) {

		SkillsBean skillBean = null;
		try {
			skillBean = stmt.queryForObject("select * from skills where skillid=?", new Object[] { skillid },
					BeanPropertyRowMapper.newInstance(SkillsBean.class));

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("SkillNot Found" + e.getMessage());
		}

		return skillBean;
	}

	public SkillsBean updateSkill(SkillsBean skillBean) {

		stmt.update("update skills set skillname=? where skillid =? ", skillBean.getSkillname(),
				skillBean.getSkillid());
		return skillBean;
	}

}
