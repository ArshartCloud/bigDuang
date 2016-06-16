package com.bigduang.Controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/seats")
public class Seats
{
	@RequestMapping("/{cid}/{sid}")
    void getSeats(@PathVariable("cid") int cid, @PathVariable("sid") int sid, HttpServletResponse response) throws IOException
    {
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		JSONObject res = new JSONObject();
		
    }
}
