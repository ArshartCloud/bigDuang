package com.bigduang.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.RowMapper;
import com.bigduang.VO.*;

import net.sf.json.JSONArray;

@Repository
public class CinemaDAO
{
	@Autowired
	private JdbcTemplate jdb;
	
	public String showCinemas()
	{
		String sql = "select * from cinema limit 10";
		List<CinemaVO> lc = jdb.query(sql, new RowMapper<CinemaVO>() {

			public CinemaVO mapRow(ResultSet res, int arg1) throws SQLException
			{
				CinemaVO c = new CinemaVO();
				c.setId(res.getInt("id"));
				c.setName(res.getString("name"));
				c.setAddress(res.getString("address"));
				c.setCity(res.getString("city"));
				return c;
			}
			
		});
		return JSONArray.fromObject(lc).toString();
	}
	
	public String getCinemasViaMid(int mid)
	{
		String sql = "select distinct cinema.id, cinema.name, cinema.address, cinema.city "
				+ "from showtime,  movie, cinema " + "where movie.id = ? and showtime.mid = movie.id and showtime.sid = cinema.id;";
		Object[] para = new Object[]{mid};
		List<CinemaVO> lc = jdb.query(sql, para, new RowMapper<CinemaVO>() {

			public CinemaVO mapRow(ResultSet res, int arg1) throws SQLException
			{
				CinemaVO c = new CinemaVO();
				c.setAddress(res.getString("address"));
				c.setCity(res.getString("city"));
				c.setId(res.getInt("id"));
				c.setName(res.getString("name"));
				return c;
			}
			
		});
		return JSONArray.fromObject(lc).toString();
	}
}
