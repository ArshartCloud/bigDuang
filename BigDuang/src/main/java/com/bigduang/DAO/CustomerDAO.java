package com.bigduang.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import net.sf.json.JSONObject;

@Repository
public class CustomerDAO
{
	@Autowired
    private JdbcTemplate jdb;
    
	public boolean isExisted(String json)
	{
		JSONObject j = JSONObject.fromObject(json);
		Object[] para = new Object[]{j.getString("name"), j.getString("password")};
		String stme = "select name from customer where name = ? and password = ?;";
		//Map<String, Object> m = jdb.queryForMap(stme, para);
		//return !m.isEmpty();
		return !jdb.queryForList(stme, para).isEmpty();
	}
	
	public boolean isNameExisted(String name)
	{
		String stmt = "select * from customer where name = ?;";
		Object[] para = new Object[]{name};
		return !jdb.queryForList(stmt, para).isEmpty();
	}
	
	public void addCustomer(String name, String password)
	{
		String stmt = "insert into customer(name, password) values(?, ?);";
        jdb.update(stmt, new Object[]{name, password});
	}
}
