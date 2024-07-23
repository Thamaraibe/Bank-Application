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

public class ViewCustomerDetails extends HttpServlet{
	
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
				String fullName=(rs.getString(2));
				String address=(rs.getString(3));
				int mobileno=((rs.getInt(4)));
				String mail=(rs.getString(5));
				String accounttype=(rs.getString(6));
				String dob=(rs.getString(8));
				int idproof=(rs.getInt(9));
				out.print("<!DOCTYPE html>\r\n"
						+"<html lang=\"en\">\r\n"
						+ "<head>\r\n"
						+ "    <meta charset=\"UTF-8\">\r\n"
						+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n"
						+ "    <title>View User Details</title>\r\n"
						+ "    <link rel=\"stylesheet\" href=\"viewstyle.css\">\r\n"
						+ "</head>\r\n"
						+ "<body>\r\n"
						+ "   \r\n"
						+ "    <h2>User Details</h2>\r\n"
						+ "    <table>\r\n"
						+ "        <tr>\r\n"
						+ "            <th>Account Number</th>\r\n"
						+ "            <td>");
				out.print(accountnumber);
				out.print("</td>\r\n"
						+ "        </tr>\r\n"
						+ "        <tr>\r\n"
						+ "            <th>Full Name</th>\r\n"
						+ "            <td>");
				
				out.print(fullName);
				out.print("</td>\r\n"
						+ "        </tr>\r\n"
						+ "        <tr>\r\n"
						+ "            <th>Address</th>\r\n"
						+ "            <td>");
				out.print(address);
				out.print("</td>\r\n"
						+ "        </tr>\r\n"
						+ "        <tr>\r\n"
						+ "            <th>Mobile Number</th>\r\n"
						+ "            <td>");
				out.print(mobileno);
				out.print("</td>\r\n"
						+ "        </tr>\r\n"
						+ "        <tr>\r\n"
						+ "            <th>Email</th>\r\n"
						+ "            <td>");
				out.print(mail);
				out.print("</td>\r\n"
						+ "        </tr>\r\n"
						+ "        <tr>\r\n"
						+ "            <th>Account Type</th>\r\n"
						+ "            <td>");
				out.print(accounttype);
				out.print("</td>\r\n"
						+ "        </tr>\r\n"
						+ "        <tr>\r\n"
						+ "            <th>Date of Birth</th>\r\n"
						+ "            <td>");
				out.print(dob);
				out.print("</td>\r\n"
						+ "        </tr>\r\n"
						+ "        <tr>\r\n"
						+ "            <th>ID Proof</th>\r\n"
						+ "            <td>");
				out.print(idproof);
				
				out.print("</td></tr></table></html></body>");
				break;
			}
		}
		if(flag==0) 
				res.sendRedirect("Deletefail1.html");
		
		
	}
  catch(Exception e) {System.out.println(e);}

}}
