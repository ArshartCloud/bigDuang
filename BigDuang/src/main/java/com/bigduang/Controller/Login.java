package com.bigduang.Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bigduang.DAO.CustomerDAO;

import net.sf.json.JSONObject;

@Controller
@RequestMapping(value="/login", method=RequestMethod.POST)
public class Login
{	
	@Autowired
	private CustomerDAO customerInfo;
	
	@RequestMapping
	void login(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		request.setCharacterEncoding("UTF-8");
		JSONObject res = new JSONObject();
		StringBuffer requestData = new StringBuffer();
		String line = null;
		try
		{
			BufferedReader reader = request.getReader();
			while ((line = reader.readLine()) != null)
			{
				requestData.append(line);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		if (customerInfo.isExisted(requestData.toString()))
		{
			res.put("loginAble", true);
		}
		else
		{
			res.put("loginAble", false);
		}
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		out.print(res.toString());
	}
}
