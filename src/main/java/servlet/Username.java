package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Username extends HttpServlet {

	public void service(HttpServletRequest req,HttpServletResponse res) throws IOException
	{
		String name=req.getParameter("fname");
		res.setContentType("text/html");
		PrintWriter out=res.getWriter();
		out.print("Hello "+name);
	}
	
}
