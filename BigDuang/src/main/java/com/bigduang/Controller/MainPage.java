package com.bigduang.Controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bigduang.DAO.CinemaDAO;
import com.bigduang.DAO.MovieDAO;

import net.sf.json.JSONObject;

@Controller
@RequestMapping(value="/list")
public class MainPage
{
	@Autowired
	private MovieDAO md;
	
	@Autowired
	private CinemaDAO cd;
	
	@RequestMapping
	public void showMovieList(HttpServletResponse response) throws IOException
	{
		/*OutputStream o = response.getOutputStream();
		File p = new File("/home/zengd/Pictures/test.png");
		FileInputStream fis = new FileInputStream(p);
		byte[] data = new byte[(int)p.length()];
		int l = fis.read(data);
		fis.close();
		response.setContentType("image/png");
		o.write(data);
		o.flush();
		o.close();*/
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		JSONObject res = new JSONObject();
		res.put("movies", md.showMovies());
		res.put("cinema", cd.showCinemas());
		out.print(res.toString());
		
	}
}
