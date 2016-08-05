package com.jabava.dao.common;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.jabava.core.SpringJdbcDao;

@Repository
public class InitTemplateDao extends SpringJdbcDao {
	public List<Map<String, Object>> getResultBySql(String sql) throws Exception{
		List<Map<String, Object>> list = this.getJdbcTemplate().queryForList(sql);
		return list;
	}
}
