import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/delete")
public class DeleteBookDetails extends HttpServlet{
	Connection con=null;

	public void init() throws ServletException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/1eja8","root", "sql123");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		int bookid= Integer.parseInt(req.getParameter("bookid"));

		PreparedStatement pstmt=null;
		
		String query="delete from book_data where book_id=?";

			try {
				pstmt=con.prepareStatement(query);
				pstmt.setInt(1,bookid);
				int count = pstmt.executeUpdate();
				PrintWriter pw =resp.getWriter();
				pw.print("<h1>"+count+" Recourd Deleted");
				
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
	}

}
