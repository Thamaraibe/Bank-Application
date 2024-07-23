package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Userlogin extends HttpServlet{
	public static int Account_Number=0;
	public void service(HttpServletRequest req ,HttpServletResponse res) throws IOException
	{

     Account_Number=Integer.parseInt(req.getParameter("Account_Number"));
	int password=Integer.parseInt(req.getParameter("password"));
	
	
	
	
	try
      {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/thamarai","root","thamarai@2003");
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery("select * from Customer_Detail");
		int flag=0;
		int accountno=0;
		while(rs.next())
		{
			
			accountno=rs.getInt(1);
			int pin=rs.getInt(10);
			if(Account_Number==accountno && password==pin)
			{
				flag=1;
				break;
			}
		}
		if(flag==1)
		{
			String fullName=(rs.getString(2));
			String address=(rs.getString(3));
			int mobileno=(rs.getInt(4));
			String mail=(rs.getString(5));
			String type=(rs.getString(6));
			int idproof=(rs.getInt(9));
			String dob=(rs.getString(8));
			res.setContentType("text/html");
			PrintWriter out=res.getWriter();
			out.print("<!DOCTYPE html>\r\n"
					+ "<html lang=\"en\">\r\n"
					+ "<head>\r\n"
					+ "    <meta charset=\"UTF-8\">\r\n"
					+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n"
					+ "    <title>User Profile</title>\r\n"
					+ "    <link rel=\"stylesheet\" href=\"Userloginsuccess.css\">\r\n"
					+ "</head>\r\n"
					+ "<body>\r\n"
					+ "   <header>\r\n"
					+ "        <div class=\"container\">\r\n"
					+ "            <div class=\"logo\">\r\n"
					+ "                <img src=\"Banklogo.png\" alt=\"Logo\" height=\"50\">\r\n"
					+ "            </div>\r\n"
					+ "            <nav>\r\n"
					+ "                <ul>\r\n"
					+ "                    <li><a href=\"ChangePIN.html\">Change Pin</a></li>\r\n"
					+ "                    <li><a href=\"Deposit.html\">Deposit</a></li>\r\n"
					+ "                    <li><a href=\"Withdraw.html\">Withdraw</a></li>\r\n"
					+ "                    <li><a href=\"ViewBalance.html\">Check Balance</a></li>\r\n"
					+ "                    <li><a href=\"LastTransaction.html\">Last 10 Transactions</a></li>\r\n"
					+ "                </ul>\r\n"
					+ "            </nav>\r\n"
					+ "        </div>\r\n"
					+ "    </header>\r\n"
					+ "\r\n"
					
					+ "    <div class=\"profile-container\">\r\n"
					+ "        <div class=\"profile-picture\">\r\n"
					+ "            <img src=\"adminlogo.jpg\" alt=\"Profile Picture\">\r\n"
					+ "        </div>\r\n"
					+ "        <div class=\"profile-info\">\r\n"
					+ "            <h1 class=\"name\">");
			out.print(fullName);
			out.print("</h1>\r\n"
					+ "            <p class=\"detail\"><strong>Account No:</strong>");
			out.print(accountno);
			out.print("</p>\r\n"
					+ "            <p class=\"detail\"><strong>Address:</strong>");
			out.print(address);
			out.print("</p>\r\n"
					+ "            <p class=\"detail\"><strong>Email:</strong>");
			out.print(mail);
			out.print("</p>\r\n"
					+ "            <p class=\"detail\"><strong>Phone No:</strong>");
			out.print(mobileno);
			out.print("</p>\r\n"
					+ "            <p class=\"detail\"><strong>Account Type:</strong>");
			out.print(type);
			out.print("</p>\r\n"
					+ "            <p class=\"detail\"><strong>Date of Birth:</strong>");
			out.print(dob);
			out.print(" </div>\r\n"
					+ "    </div>\r\n"
					
					+ "</body>\r\n"
					+ "</html>");
			
			
			
			
			
		
		}
		else
			res.sendRedirect("Userloginfail.html");
		
		}
      catch(Exception e) {System.out.println(e);}
}}
