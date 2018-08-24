package libbookadmin;
import libbook.util;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class admin2
 */
public class admin2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public admin2() {
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
		  String tb=request.getParameter("t");   
		  int sel=Integer.parseInt(request.getParameter("t1"));  
		  
		  try
		  {
			  Connection con=util.getDBConnection();
		  switch(sel)
			{ 
		  
			case 1://Insert a row
				SimpleDateFormat  sdf=new SimpleDateFormat("dd.MM.yyyy");
				Calendar cal = Calendar.getInstance();
				String BOOK_NAME=request.getParameter("t2");
				String AUTHOR_NAME=request.getParameter("t3");
				int BOOK_ACC_NO=Integer.parseInt(request.getParameter("t4"));
				String STUDENT_NAME =request.getParameter("t5");
				int REG_NUMBER=Integer.parseInt(request.getParameter("t6"));
				//String  BOOK_TAKEN_DATE=request.getParameter("t7");
				String  BOOK_TAKEN_DATE=sdf.format(cal.getTime());
				cal.add(Calendar.DAY_OF_MONTH, 15);
				//long millis=System.currentTimeMillis();
				//java.sql.Date date=new java.sql.Date(millis);
				//String BOOK_RETURN_DATE=request.getParameter("t8");
				String BOOK_RETURN_DATE=sdf.format(cal.getTime());
				int NO_OF_TIME_EXTEND=Integer.parseInt(request.getParameter("t9"));
				//SimpleDateFormat  sdf=new SimpleDateFormat("dd.MM.yyyy");
				java.util.Date  sdf1=sdf.parse(BOOK_TAKEN_DATE);
				java.sql.Date  sdf2=new java.sql.Date (sdf1.getTime());
				SimpleDateFormat sdf3=new SimpleDateFormat("dd.MM.yyyy");
				java.util.Date  sdf4=sdf3.parse(BOOK_RETURN_DATE);
				java.sql.Date sdf5=new java.sql.Date (sdf4.getTime());
				PreparedStatement pi2=con.prepareStatement("select * from "+tb+" where book_name=? and author_name=? and reg_number=?");
				pi2.setString(1,BOOK_NAME);
				pi2.setString(2,AUTHOR_NAME);
				pi2.setInt(3,REG_NUMBER);
				int result=pi2.executeUpdate();
				if(result==0)
				{
				PreparedStatement pi1=con.prepareStatement("insert into "+tb+" values(?,?,?,?,?,?,?,?)");
				pi1.setString(1,BOOK_NAME);
				pi1.setString(2,AUTHOR_NAME);
				pi1.setInt(3,BOOK_ACC_NO); 
				pi1.setString(4,STUDENT_NAME);
				pi1.setInt(5,REG_NUMBER);
				pi1.setDate(6,(Date) sdf2); 
				pi1.setDate(7,(Date) sdf5); 
				pi1.setInt(8,NO_OF_TIME_EXTEND); 
				pi1.executeUpdate();  
				pw.print("<h1>You are successfully Inserted a row...<h1>");  
				pw.print("<h1>Table data after insertion..<h1>");  
				pi1=con.prepareStatement("select * from "+tb); 
				ResultSet rs1=pi1.executeQuery();
				pw.println("<table border=1>");
				pw.println("<tr><th>BOOK_NAME</th><th>AUTHOR_NAME</th><th>BOOK_ACC_NO</th><th>STUDENT_NAME</th><th>REG_NUMBER</th><th>BOOK_TAKEN_DATE</th><th>BOOK_RETURN_DATE</th><th>NO_OF_TIME_EXTEND</th></tr>");
				while(rs1.next())
		         {
		           pw.println("<tr><td>"+rs1.getString(1)+"</td><td>"+rs1.getString(2)+"</td><td>"+rs1.getInt(3)+"</td><td>"+rs1.getString(4)+"</td><td>"+rs1.getInt(5)+"</td><td>"+rs1.getDate(6)+"</td><td>"+rs1.getDate(7)+"</td><td>"+rs1.getInt(8)+"</td></tr>");
		          
		         }
				 pw.println("</table>");
				}
				else
				{
					response.sendRedirect("prog16.html");
				}
				 break;
			case 2:
				int REGISTER_NUMBER2=Integer.parseInt(request.getParameter("t10"));
				  if(REGISTER_NUMBER2==999)
				  { PreparedStatement pi3=con.prepareStatement("select * from "+tb);
				    ResultSet rs3=pi3.executeQuery();
				    pw.println("<table border=1>");
				    pw.println("<tr><th>BOOK_NAME</th><th>AUTHOR_NAME</th><th>BOOK_ACC_NO</th><th>STUDENT_NAME</th><th>REG_NUMBER</th><th>BOOK_TAKEN_DATE</th><th>BOOK_RETURN_DATE</th><th>NO_OF_TIME_EXTEND</th></tr>");
					while(rs3.next())
			         {
			           pw.println("<tr><td>"+rs3.getString(1)+"</td><td>"+rs3.getString(2)+"</td><td>"+rs3.getInt(3)+"</td><td>"+rs3.getString(4)+"</td><td>"+rs3.getInt(5)+"</td><td>"+rs3.getDate(6)+"</td><td>"+rs3.getDate(7)+"</td><td>"+rs3.getInt(8)+"</td></tr>");
			          
			         }
					 pw.println("</table>");
				  }
				  else
				  {
					  PreparedStatement pi4=con.prepareStatement("select * from "+tb+" where reg_number=?");
						pi4.setInt(1,REGISTER_NUMBER2);
						pi4.execute();
						ResultSet rs4=pi4.executeQuery();
				        pw.println("<table border=1>");
				        pw.println("<tr><th>BOOK_NAME</th><th>AUTHOR_NAME</th><th>BOOK_ACC_NO</th><th>STUDENT_NAME</th><th>REG_NUMBER</th><th>BOOK_TAKEN_DATE</th><th>BOOK_RETURN_DATE</th><th>NO_OF_TIME_EXTEND</th></tr>");
						while(rs4.next())
				         {
				           pw.println("<tr><td>"+rs4.getString(1)+"</td><td>"+rs4.getString(2)+"</td><td>"+rs4.getInt(3)+"</td><td>"+rs4.getString(4)+"</td><td>"+rs4.getInt(5)+"</td><td>"+rs4.getDate(6)+"</td><td>"+rs4.getDate(7)+"</td><td>"+rs4.getInt(8)+"</td></tr>");
				          
				         }
						 pw.println("</table>");
				        
				  }
				  break;
			case 3:
				int REGISTER_NUMBER3=Integer.parseInt(request.getParameter("t11"));
				int BOOK_ACC_NO3=Integer.parseInt(request.getParameter("t12"));
				PreparedStatement pi5=con.prepareStatement("delete from "+tb+" where reg_number=? and book_acc_no=?");
				pi5.setInt(2,BOOK_ACC_NO3);
				pi5.setInt(1,REGISTER_NUMBER3);  
				pi5.executeUpdate();
				pi5=con.prepareStatement("select * from "+tb); 
			    ResultSet rs5=pi5.executeQuery();
			   
	            pw.print("<h2>deleted successfull.....</h2>");
			    pw.print("<h1>Table data after delete..<h1>");  
				pw.println("<table border=1>");
				pw.println("<tr><th>BOOK_NAME</th><th>AUTHOR_NAME</th><th>BOOK_ACC_NO</th><th>STUDENT_NAME</th><th>REG_NUMBER</th><th>BOOK_TAKEN_DATE</th><th>BOOK_RETURN_DATE</th><th>NO_OF_TIME_EXTEND</th></tr>");
				while(rs5.next())
		         {
		           pw.println("<tr><td>"+rs5.getString(1)+"</td><td>"+rs5.getString(2)+"</td><td>"+rs5.getInt(3)+"</td><td>"+rs5.getString(4)+"</td><td>"+rs5.getInt(5)+"</td><td>"+rs5.getDate(6)+"</td><td>"+rs5.getDate(7)+"</td><td>"+rs5.getInt(8)+"</td></tr>");
		          
		         }
				 pw.println("</table>");
		        
			}
		con.close();	

		
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
