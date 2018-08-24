package libbookuser;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import libbook.util;

/**
 * Servlet implementation class searchbook
 */
public class searchbook extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public searchbook() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		PrintWriter pw=response.getWriter();
		  response.setContentType("text/html");
		  Connection con=util.getDBConnection();
		  String book_name=request.getParameter("bookname");  
		  String author_name=request.getParameter("authorname");
		  try {
			  int number=0;
			  PreparedStatement val=con.prepareStatement("select TOTAL_BOOK from LIBRARY_BOOKS_INFOMATION where book_name=? and author_name=?");
			  val.setString(1,book_name);
			  val.setString(2,author_name);
			  ResultSet rs=val.executeQuery();
			  while(rs.next())
			  {
				  number=(int)rs.getInt("TOTAL_BOOK");
			  }
			PreparedStatement val1=con.prepareStatement("select * from  LIBRARY_BOOKTAKEN_DETAIL where book_name=? and author_name=?");
			val1.setString(1,book_name);
			val1.setString(2,author_name);
			int num=val1.executeUpdate();
			if(num<number)
			{
			PreparedStatement ps=con.prepareStatement("select*from LIBRARY_BOOKS_INFOMATION where book_name=? and author_name=?");
			ps.setString(1,book_name);
			ps.setString(2, author_name);
			ResultSet rs2=ps.executeQuery();
			pw.println("<table border=1>");
        pw.println("<tr><th>BOOK_NAME</th><th>AUTHOR_NAME</th><th>PUBLISHER</th><th>EDITION</th><th>BOOK_ACC_NO</th><th>ISBN</th><th>TOTAL_BOOK</th><th>RACK_NO</th><th>PRICE</th></tr>");
			while(rs2.next())
		    {
			pw.println("<tr><td>"+rs2.getString(1)+"</td><td>"+rs2.getString(2)+"</td><td>"+rs2.getString(3)+"</td><td>"+rs2.getInt(4)+"</td><td>"+rs2.getInt(5)+"</td><td>"+rs2.getInt(6)+"</td><td>"+rs2.getInt(7)+"</td><td>"+rs2.getInt(8)+"</td><td>"+rs2.getInt(9)+"</td></tr>");
		          
		    }
		     pw.println("</table>");
			}
			else
			{
				response.sendRedirect("prog13.html");  	
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
	}

}
