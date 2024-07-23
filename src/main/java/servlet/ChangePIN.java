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

public class ChangePIN extends HttpServlet {
	public void service(HttpServletRequest req ,HttpServletResponse res) throws IOException
	{
		int AccountNo=Integer.parseInt(req.getParameter("accountnumber"));
		int newpin=Integer.parseInt(req.getParameter("password"));
		int confirmpin=Integer.parseInt(req.getParameter("confirmpassword"));
		System.out.println(AccountNo);
		try
	      {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/thamarai","root","thamarai@2003");
			Statement stm=con.createStatement();
			ResultSet rs=stm.executeQuery("select * from Customer_Detail");
			int flag=0;
			while(rs.next())
			{
				if(rs.getInt(1)==AccountNo)
				{
					flag=1;
					break;
				}
			}
			if(flag==0)
				res.sendRedirect("ChangePINfail1.html");
			else if(newpin!=confirmpin)
				res.sendRedirect("ChangePINfail.html");
			else {
			PreparedStatement st=con.prepareStatement("call ChangePIN(?,?)");
			st.setInt(1, AccountNo);
			st.setInt(2,newpin);
			st.execute();
			res.sendRedirect("ChangePINsuccess.html");
			con.close();
			}}
	      catch(Exception e) {System.out.println(e);}
		
		
		
		
		
		
	}

}
