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

public class ViewBalance extends HttpServlet{
	public void service(HttpServletRequest req ,HttpServletResponse res) throws IOException
	{
	// TODO Auto-generated method stub
	int accountno=Integer.parseInt(req.getParameter("accountnumber"));
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
				int balance=rs.getInt(7);
				out.print("<!DOCTYPE html>\r\n"
						+ "<html lang=\"en\">\r\n"
						+ "<head>\r\n"
						+ "    <meta charset=\"UTF-8\">\r\n"
						+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n"
						+ "    <title>Balance Display</title>\r\n"
						+ "    <style>\r\n"
						+ "        body {\r\n"
						+ "            font-family: Arial, sans-serif;\r\n"
						+ "            display: flex;\r\n"
						+ "            justify-content: center;\r\n"
						+ "            align-items: center;\r\n"
						+ "            height: 100vh;\r\n"
						+ "            margin: 0;\r\n"
						+ "            background-color: #f4f4f4;\r\n"
						+ "        }\r\n"
						+ "        .balance-container {\r\n"
						+ "            background: white;\r\n"
						+ "            padding: 20px;\r\n"
						+ "            border-radius: 10px;\r\n"
						+ "            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);\r\n"
						+ "            text-align: center;\r\n"
						+ "        }\r\n"
						+ "        .balance-container h1 {\r\n"
						+ "            margin: 0;\r\n"
						+ "            font-size: 24px;\r\n"
						+ "            color: #333;\r\n"
						+ "        }\r\n"
						+ "        .balance-container .balance {\r\n"
						+ "            margin-top: 10px;\r\n"
						+ "            font-size: 36px;\r\n"
						+ "            color: #28a745;\r\n"
						+ "        }\r\n"
						+ "    </style>\r\n"
						+ "</head>\r\n"
						+ "<body>\r\n"
						+ "    <div class=\"balance-container\">\r\n"
						+ "        <h1>Your Balance</h1>\r\n"
						+ "        <div class=\"balance\">Rs. \r\n");
				out.print(balance);
				out.print("</div>\r\n"
						+ "    </div>\r\n"
						+ "</body>\r\n"
						+ "</html>\r\n");
				break;
			}
		}
		if(flag==0) 
				res.sendRedirect("Deletefail1.html");
		
		
	}
  catch(Exception e) {System.out.println(e);}

}

}
