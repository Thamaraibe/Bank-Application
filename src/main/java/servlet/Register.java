package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Register extends HttpServlet {

	
		public void service(HttpServletRequest req ,HttpServletResponse res) throws IOException
		{
		// TODO Auto-generated method stub
		String fname=(req.getParameter("fname"));
		String sname=(req.getParameter("sname"));
		String addr=(req.getParameter("addr"));
		String mail=(req.getParameter("mail"));
		String num=(req.getParameter("num"));
		//int n2=Integer.parseInt(req.getParameter("num2"));
		int max=1000;
		int min=9999;  
		int pin = (int)(Math.random()*(max-min+1)+min);
	
		
		try
	      {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/thamarai","root","thamarai@2003");
			PreparedStatement st=con.prepareStatement("call Insert_User_Detail(?,?,?,?,?,?)");
			st.setString(1,fname);
			st.setString(2,sname);
			st.setString(3,addr);
			st.setString(4,mail);
			st.setString(5,num);
			st.setInt(6, pin);
			st.execute();
			con.close();
			}
	      catch(Exception e) {System.out.println(e);}
		res.setContentType("text/html");
		PrintWriter out=res.getWriter();
		out.print("Sucessfully added");

	

}}
