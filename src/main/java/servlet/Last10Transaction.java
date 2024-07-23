package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Last10Transaction extends HttpServlet{
	public void service(HttpServletRequest req ,HttpServletResponse res) throws IOException
	{   
	
	int count=0;
	
		try
		
	      {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/thamarai","root","thamarai@2003");
			Statement s = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet rs=s.executeQuery("select * from Transaction");
			rs.afterLast();
			int flag=0;
			int balance=0;
		    count=0;
		    res.setContentType("text/html");
			PrintWriter out=res.getWriter();
			out.print("<!DOCTYPE html>\r\n"
					+ "<html lang=\"en\">\r\n"
					+ "<head>\r\n"
					+ "    <meta charset=\"UTF-8\">\r\n"
					+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n"
					+ "    <title>Last 10 Transactions</title>\r\n"
					+ "    <link rel=\"stylesheet\" href=\"s.css\">\r\n"
					+ "</head>\r\n"
					+ "<body>\r\n"
					+ "<form action =\"PdfDownload\" method=\"post\">\r\n"
					+ "    <header>\r\n"
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
					+ "    <div class=\"transactions-container\">\r\n"
					+ "        <h1>Last 10 Transactions</h1>\r\n"
					+ "        <table class=\"transactions-table\">\r\n"
					+ "            <thead>\r\n"
					+ "                <tr>\r\n"
					+"<th>Account Number</th>\r\n"
					+ "                    <th>Date</th>\r\n"
					+ "                    <th>Withdraw/Deposit</th>\r\n"
					
					+ "                    <th>Amount</th>\r\n"
					+ "                </tr>\r\n"
					+ "            </thead>\r\n"
					+ "            <tbody>");
			while(rs.previous())
			{
				int AccountNo=(rs.getInt(1));
				if(AccountNo==Userlogin.Account_Number) {
				String Date=(rs.getString(2));
				String Type=(rs.getString(3));
				int Amount=(rs.getInt(4));
				out.print("<tr>\r\n"
						+ "                    <td>");
				out.print(AccountNo);
				out.print("</td>\r\n"
						+ "                    <td>");
				out.print(Date);
				out.print("</td>\r\n"
						+ "                    <td>");
				out.print(Type);
				out.print("</td>\r\n"
						+ "                    <td>");
				out.print(Amount);
				out.print("</td>\r\n"
						+ "                </tr>");
				count++;}
				if(count==10)
					break;
			}
			
			
			
				
				
			
			out.print("</tbody>\r\n"
					+ "        </table>\r\n"
					+ "    </div>\r\n");
			out.print("       <center>\r\n"
					+ "      <button >Download as PDF</button> </center>\r\n"
					+ "      </form>\r\n"
					+ "  </body>\r\n"
					+ "</html>");
			con.close();
			
			}
		
		
	      catch(Exception e) {System.out.println(e);}

}}
