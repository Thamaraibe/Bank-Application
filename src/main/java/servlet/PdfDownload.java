package servlet;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Cell;

public class PdfDownload extends HttpServlet {
    public void service(HttpServletRequest req, HttpServletResponse res) throws IOException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/thamarai", "root", "thamarai@2003");
            Statement s = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = s.executeQuery("select * from Transaction");
            rs.afterLast();
            int count = 0;

            // Set the content type to PDF
            res.setContentType("application/pdf");
            res.setHeader("Content-Disposition", "attachment; filename=\"Last10Transactions.pdf\"");
            OutputStream out = res.getOutputStream();

            PdfWriter writer = new PdfWriter(out);
            PdfDocument pdfDoc = new PdfDocument(writer);
            Document document = new Document(pdfDoc);

            // Add title
            document.add(new Paragraph("Last 10 Transactions"));

            // Add table with 4 columns
            Table table = new Table(new float[]{3, 3, 3, 3});
            table.addHeaderCell(new Cell().add(new Paragraph("Account Number")));
            table.addHeaderCell(new Cell().add(new Paragraph("Date")));
            table.addHeaderCell(new Cell().add(new Paragraph("Deposit/Withdraw")));
            table.addHeaderCell(new Cell().add(new Paragraph("Amount")));

            while (rs.previous()) {
                int accountNo = rs.getInt(1);
                
                String date = rs.getString(2);
                String type = rs.getString(3);
                int amount = rs.getInt(4);
                if(accountNo==Userlogin.Account_Number) {
                table.addCell(new Cell().add(new Paragraph(String.valueOf(accountNo))));
                table.addCell(new Cell().add(new Paragraph(date)));
                table.addCell(new Cell().add(new Paragraph(type)));
                table.addCell(new Cell().add(new Paragraph(String.valueOf(amount))));

                count++;}
                if (count == 10)
                    break;
            }

            document.add(table);
            document.close();
            con.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

