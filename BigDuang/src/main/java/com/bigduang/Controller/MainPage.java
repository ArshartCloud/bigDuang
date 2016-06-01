package com.bigduang.Controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/list")
public class MainPage
{
	@Autowired
	private JdbcTemplate jdb;
	
	@RequestMapping
	public void showMovieList(HttpServletResponse response) throws IOException
	{
		OutputStream o = response.getOutputStream();
		File p = new File("/home/zengd/Pictures/test.png");
		FileInputStream fis = new FileInputStream(p);
		byte[] data = new byte[(int)p.length()];
		int l = fis.read(data);
		fis.close();
		response.setContentType("image/png");
		o.write(data);
		o.flush();
		o.close();
	}
}
