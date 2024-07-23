package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ModifyCustomerDetails extends HttpServlet{
	public void service(HttpServletRequest req ,HttpServletResponse res) throws IOException
	{
	// TODO Auto-generated method stub
	int modifyAccountNo=Integer.parseInt(req.getParameter("modifyAccountNo"));
	String newFullName=(req.getParameter("newFullName"));
	String newAddress=(req.getParameter("newAddress"));
	int newMobileNo=Integer.parseInt(req.getParameter("newMobileNo"));
	String newEmail=(req.getParameter("newEmail"));
	String newAccountType=(req.getParameter("newAccountType"));
	String newDob=req.getParameter("newDob");
	int newIdProof=Integer.parseInt(req.getParameter("newIdProof"));
	try
      {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/thamarai","root","thamarai@2003");
		Statement stm=con.createStatement();
		ResultSet rs=stm.executeQuery("select * from Customer_Detail");
		int flag=0;
		while(rs.next())
		{
			if(rs.getInt(1)==modifyAccountNo)
			{
				flag=1;
				break;
			}
		}
		if(flag==0)
			res.sendRedirect("Deletefail1.html");
		else {
		PreparedStatement st=con.prepareStatement("call ModifyCustomerDetails(?,?,?,?,?,?,?,?)");
		st.setInt(1, modifyAccountNo);
		st.setString(2,newFullName);
		st.setString(3,newAddress);
		st.setInt(4,newMobileNo);
		st.setString(5,newEmail);
		st.setString(6,newAccountType);
		st.setString(7,newDob);
		st.setInt(8,newIdProof );
		st.execute();
		res.sendRedirect("Modifysuccess.html");
		con.close();
		}}
      catch(Exception e) {System.out.println(e);}
	
	
}}
