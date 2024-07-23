package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Deposit extends HttpServlet
{
	public void service(HttpServletRequest req ,HttpServletResponse res) throws IOException
	{
		int AccountNo=Integer.parseInt(req.getParameter("accountnumber"));
		int amount=Integer.parseInt(req.getParameter("amount"));
	    String todaydate=(java.time.LocalDate.now())+"";  
		try
	      {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/thamarai","root","thamarai@2003");
			Statement stm=con.createStatement();
			ResultSet rs=stm.executeQuery("select * from Customer_Detail");
			int flag=0;
			int balance=0;
			while(rs.next())
			{
				if(rs.getInt(1)==AccountNo)
				{
					 balance=rs.getInt(7);
					flag=1;
					break;
				}
			}
			balance+=amount;
			if(flag==0)
				res.sendRedirect("Depositfail.html");
			else {
			PreparedStatement st=con.prepareStatement("call Deposit(?,?,?,?)");
			st.setInt(1, AccountNo);
			st.setString(2,todaydate);
			st.setString(3,"Deposit");
			st.setInt(4,amount);
			st.execute();
			PreparedStatement st1=con.prepareStatement("call UpdateBalance(?,?)");
			st1.setInt(1, AccountNo);
			st1.setInt(2, balance);
			st1.execute();
			res.sendRedirect("Depositsuccess.html");
			con.close();
			}}
	      catch(Exception e) {System.out.println(e);}

}}
