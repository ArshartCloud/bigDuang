package com.bigduang.DAO;



import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.bigduang.VO.MovieVO;

import net.sf.json.JSONArray;

@Repository
public class MovieDAO
{
	@Autowired
    private JdbcTemplate jdb;
	
	public String showMovies()
	{
		//JSONArray arr = new JSONArray();
		String sql = "select * from movie limit 10";
		List<MovieVO> lm = jdb.query(sql, new RowMapper<MovieVO>() {
			public MovieVO mapRow(ResultSet arg0, int arg1) throws SQLException
			{
				MovieVO m = new MovieVO();
				m.setId(arg0.getInt("id"));
				m.setImg(arg0.getString("image"));
				m.setName(arg0.getString("name"));
				return m;
			}
			
		});
		return JSONArray.fromObject(lm).toString();
	}
	
	public String getMoviesViaCid(int cid)
	{
		String sql = "select distinct movie.id, movie.name, movie.image "
				+ "from showtime,  movie, cinema " + "where cinema.id = ? and showtime.mid = movie.id and showtime.sid = cinema.id;";
		Object[] para = new Object[]{cid};
		List<MovieVO> lm = jdb.query(sql, para, new RowMapper<MovieVO>(){

			public MovieVO mapRow(ResultSet res, int arg1) throws SQLException
			{
				MovieVO m = new MovieVO();
				m.setId(res.getInt("id"));
				m.setImg(res.getString("image"));
				m.setName(res.getString("name"));
				return m;
			}
			
		});
		return JSONArray.fromObject(lm).toString();
	}
}
