package com.bigduang.Controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/img")
public class ImageTransfer
{
	@RequestMapping("/{path}")
	void TransferImg(@PathVariable String path, HttpServletResponse response) throws IOException
	{
		String prefix = "/root/Image/";
		OutputStream out = response.getOutputStream();
		File p = new File(prefix + path + ".jpg");
		FileInputStream fis = new FileInputStream(p);
		byte[] data = new byte[(int)p.length()];
		fis.read(data);
		fis.close();
		response.setContentType("image/jpg");
		out.write(data);
		out.flush();
		out.close();
	}
}
