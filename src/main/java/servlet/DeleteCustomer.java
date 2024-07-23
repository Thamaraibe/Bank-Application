package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DeleteCustomer extends HttpServlet{
	public void service(HttpServletRequest req ,HttpServletResponse res) throws IOException
	{
	// TODO Auto-generated method stub
	int accountno=Integer.parseInt(req.getParameter("accountnumber"));
	System.out.println(accountno);
	
	try
    {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/thamarai","root","thamarai@2003");
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery("select * from Customer_Detail");
		int flag=0;
		PrintWriter out=res.getWriter();
		res.setContentType("text/html");
		while(rs.next())
		{
			
			int accountnumber=rs.getInt(1);
			
			if(accountnumber==accountno)
			{
				flag=1;
				break;
			}
		}
		if(flag==0) 
			res.sendRedirect("Deletefail1.html");
		else if(rs.getInt(7)>0)
			res.sendRedirect("DeleteFail.html");
		else
		{
			
		PreparedStatement stm=con.prepareStatement("call DeleteCustomer(?)");
		stm.setInt(1, accountno);
		stm.execute();
		con.close();
		res.sendRedirect("Deletesucess.html");
		
		
	}}
  catch(Exception e) {System.out.println(e);}
	

}}
