package com.bigduang.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class SeatDAO
{
	@Autowired
    private JdbcTemplate jdb;
	
	/*public String getSeatsViaSid(int sid)
	{
		String stmt = "select seat.id from seat and showtime where";
	}*/
}
