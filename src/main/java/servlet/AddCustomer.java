package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AddCustomer extends HttpServlet {
	public void service(HttpServletRequest req ,HttpServletResponse res) throws IOException
	{
	// TODO Auto-generated method stub
	String fullName=(req.getParameter("fullName"));
	String address=(req.getParameter("address"));
	String mobileNo=(req.getParameter("mobileNo"));
	String email=(req.getParameter("email"));
	String accountType=(req.getParameter("accountType"));
	String dob=(req.getParameter("dob"));
	String idProof=req.getParameter("idProof");
	int max=1000;
	int min=9999;  
	int pin = (int)(Math.random()*(max-min+1)+min);
	int max1=100000000;
	int min1=999999999;  
	int accountno = (int)(Math.random()*(max1-min1+1)+min1);
    int balance=1000;
	try
      {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/thamarai","root","thamarai@2003");
		PreparedStatement st=con.prepareStatement("call Insert_User_Detail(?,?,?,?,?,?,?,?,?,?)");
		st.setInt(1, accountno);
		st.setString(2,fullName);
		st.setString(3,address);
		st.setString(4,mobileNo);
		st.setString(5,email);
		st.setString(6,accountType);
		st.setInt(7,balance);
		st.setString(8,dob );
		st.setString(9,idProof);
		st.setInt(10,pin);
		st.execute();
		con.close();
		}
      catch(Exception e) {System.out.println(e);}
	res.setContentType("text/html");
	PrintWriter out=res.getWriter();
	out.print("<!DOCTYPE html>\r\n"
			+ "<html lang=\"en\">\r\n"
			+ "<head>\r\n"
			+ "    <meta charset=\"UTF-8\">\r\n"
			+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n"
			+ "    <title>Account Information</title>\r\n"
			+ "    <link rel=\"stylesheet\" href=\"Registersuccess.css\">\r\n"
			+ "</head>\r\n"
			+ "<body>\r\n"
			+ "    <div class=\"account-info\">\r\n"
			+ "        <h1>Account Information</h1>\r\n"
			+ "        <div class=\"info\">\r\n"
			+ "            <label for=\"account-number\">Account Number:</label>\r\n"
			+ "            <span id=\"account-number\">");
	out.print(accountno);
	out.print("</span>\r\n"
			+ "        </div>\r\n"
			+ "        <div class=\"info\">\r\n"
			+ "            <label for=\"pin-number\">PIN Number:</label>\r\n"
			+ "            <span id=\"pin-number\">");
	out.print(pin);
	out.print("</span>\r\n"
			+ "        </div>\r\n"
			+ "    </div>\r\n"
			+ "</body>\r\n"
			+ "</html>\r\n"
			+ "");

}}
