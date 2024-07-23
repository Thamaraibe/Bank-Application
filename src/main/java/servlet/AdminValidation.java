package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.*;
//import java.sql.PreparedStatement;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AdminValidation extends HttpServlet{

	public void service(HttpServletRequest req ,HttpServletResponse res) throws IOException
	{
	// TODO Auto-generated method stub
	String Username=req.getParameter("username");
	String Password=req.getParameter("password");
	
	
	
	
	try
      {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/thamarai","root","thamarai@2003");
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery("select * from Admin_Details");
		int flag=0;
		while(rs.next())
		{
			System.out.println(rs.getInt(1)+" "+rs.getString(2));
			String username=rs.getString(3);
			String password=rs.getString(4);
			if(Username.equals(username) && Password.equals(password))
			{
				flag=1;
				break;
			}
		}
		if(flag==1)
			res.sendRedirect("AddCustomer.html");
		else
			res.sendRedirect("Adminloginfail.html");
		
		}
      catch(Exception e) {System.out.println(e);}
	



}
}
