package com.bigduang.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class CustomerDAO
{
	@Autowired
    private JdbcTemplate jdb;
    
	public boolean isExisted(String name, String password)
	{
		return true;
	}
	
	public void addCustomer(String name, String password)
	{
		
	}
}
