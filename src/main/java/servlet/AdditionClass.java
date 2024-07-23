package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AdditionClass extends HttpServlet{
	public void service(HttpServletRequest req ,HttpServletResponse res) throws IOException
	{
		String n1=(req.getParameter("num1"));
		int n2=Integer.parseInt(req.getParameter("num2"));
	
		
		try
	      {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/thamarai","root","thamarai@2003");
			PreparedStatement st=con.prepareStatement("call new_procedure(?,?)");
			st.setString(1,n1);
			st.setInt(2,n2);
			st.execute();
			con.close();
			}
	      catch(Exception e) {System.out.println(e);}
		res.setContentType("text/html");
		PrintWriter out=res.getWriter();
		out.print("Sucessfully added");
	}
}
