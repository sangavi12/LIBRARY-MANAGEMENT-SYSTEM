package libbookuser;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import libbook.util;

/**
 * Servlet implementation class extendpage
 */
public class extendpage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public extendpage() {
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
		  int REGISTER_NO=Integer.parseInt(request.getParameter("t1"));
		  String BOOK_NAME=request.getParameter("t2");
		String AUTHOR_NAME=request.getParameter("t3");
		int BOOK_ACC_NO=Integer.parseInt(request.getParameter("t4"));
		String BOOK_RETURN_DATE=request.getParameter("t6");
		String BOOK_ISSUE_DATE=request.getParameter("t5");
		int REG_NO=Integer.parseInt(request.getParameter("t1"));
		int flag=0;
			try
			{
				int rs4=0;
				Connection con=util.getDBConnection();
				PreparedStatement ps=con.prepareStatement("select * from LIBRARY_BOOKTAKEN_DETAIL where REG_NUMBER=? and BOOK_NAME=? and AUTHOR_NAME=? and BOOK_ACC_NO=?");
				ps.setInt(1,REGISTER_NO);
				ps.setString(2,BOOK_NAME);
				ps.setString(3,AUTHOR_NAME);
				ps.setInt(4,BOOK_ACC_NO);
				rs4=ps.executeUpdate();
			if(rs4==1)
			{
				int total_book = 0;
				PreparedStatement ps1=con.prepareStatement("select * from LIBRARY_BOOKTAKEN_DETAIL where REG_NUMBER=? and BOOK_NAME=?");
				ps1.setInt(1,REG_NO);
				ps1.setString(2,BOOK_NAME);
				ResultSet rs5=ps1.executeQuery();
				while(rs5.next())
				 {
					 total_book=(int)rs5.getInt("NO_OF_TIME_EXTEND");
				 }
				if(total_book==0)
				{
					Calendar cal = Calendar.getInstance();
					SimpleDateFormat  sdf=new SimpleDateFormat("dd.MM.yyyy");
					String  today=sdf.format(cal.getTime());
					Date d1=sdf.parse(today);
					Date d2=sdf.parse(BOOK_RETURN_DATE);
					if(d1.before(d2)||d1.equals(d2))
					{
					 PreparedStatement pi2=con.prepareStatement("update LIBRARY_BOOKTAKEN_DETAIL set NO_OF_TIME_EXTEND=1 where REG_NUMBER=? and BOOK_NAME=? and AUTHOR_NAME=? and BOOK_ACC_NO=?");
					 pi2.setInt(1,REGISTER_NO);
						pi2.setString(2,BOOK_NAME);
						pi2.setString(3,AUTHOR_NAME);
						pi2.setInt(4,BOOK_ACC_NO);
					    pi2.executeUpdate();
	                 cal.add(Calendar.DAY_OF_MONTH,15);
	                 String date1=sdf.format(cal.getTime());
	                 java.util.Date  sdf1=sdf.parse(date1);
	 				java.sql.Date  sdf2=new java.sql.Date (sdf1.getTime());
					PreparedStatement pi3=con.prepareStatement("update LIBRARY_BOOKTAKEN_DETAIL set BOOK_RETURN_DATE=? where REG_NUMBER=? and BOOK_NAME=? and AUTHOR_NAME=? and BOOK_ACC_NO=?");
					pi3.setDate(1,sdf2);
					pi3.setInt(2,REGISTER_NO);
					pi3.setString(3,BOOK_NAME);
					pi3.setString(4,AUTHOR_NAME);
					pi3.setInt(5,BOOK_ACC_NO);
					pi3.executeUpdate();
					pw.print("<center>");
					pw.print("<h2>"+"THE BOOK EXTENDED DATE "+sdf2+"</h2>");
					pw.print("</center>");
				}
				else
				{
					response.sendRedirect("prog17.html"); 
				}
				
			}
			else
			{
				response.sendRedirect("prog18.html"); 
			}}
			else
			{
				response.sendRedirect("prog15.html"); 
			}
			}
		  catch (Exception e)
		   {  e.printStackTrace();   
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
