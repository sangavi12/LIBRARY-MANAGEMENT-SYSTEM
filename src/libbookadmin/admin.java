package libbookadmin;
import libbook.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class admin
 */
public class admin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public admin() {
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
				String BOOK_NAME=request.getParameter("t2");
				String AUTHOR_NAME=request.getParameter("t3");
				String PUBLISHER=request.getParameter("t4");
				int EDITION =Integer.parseInt(request.getParameter("t5"));
				int BOOK_ACC_NO=Integer.parseInt(request.getParameter("t6"));
				int ISBN =Integer.parseInt(request.getParameter("t7"));
				int TOTAL_BOOK=Integer.parseInt(request.getParameter("t8"));
				int RACK_NO=Integer.parseInt(request.getParameter("t9"));
				int PRICE=Integer.parseInt(request.getParameter("t10"));
				PreparedStatement pi1=con.prepareStatement("insert into "+tb+" values(?,?,?,?,?,?,?,?,?)");
				pi1.setString(1,BOOK_NAME); 
				pi1.setString(2,AUTHOR_NAME); 
				pi1.setString(3,PUBLISHER); 
				pi1.setInt(4,EDITION); 
				pi1.setInt(5,BOOK_ACC_NO); 
				pi1.setInt(6,ISBN); 
				pi1.setInt(7,TOTAL_BOOK); 
				pi1.setInt(8,RACK_NO); 
				pi1.setInt(9,PRICE); 
				pi1.executeUpdate();  
				pw.print("<h1>You are successfully Inserted a row...<h1>");  
				pw.print("<h1>Table data after insertion..<h1>");  
				pi1=con.prepareStatement("select * from "+tb); 
				ResultSet rs1=pi1.executeQuery("Select * from "+tb);
				pw.println("<table border=1>");
				pw.println("<tr><th>BOOK_NAME</th><th>AUTHOR_NAME</th><th>PUBLISHER</th><th>EDITION</th><th>BOOK_ACC_NO</th><th>ISBN</th><th>TOTAL_BOOK</th><th>RACK_NO</th><th>PRICE</th></tr>");
				while(rs1.next())
		         {
		           pw.println("<tr><td>"+rs1.getString(1)+"</td><td>"+rs1.getString(2)+"</td><td>"+rs1.getString(3)+"</td><td>"+rs1.getInt(4)+"</td><td>"+rs1.getInt(5)+"</td><td>"+rs1.getInt(6)+"</td><td>"+rs1.getInt(7)+"</td><td>"+rs1.getInt(8)+"</td><td>"+rs1.getInt(9)+"</td></tr>");
		          
		         }
				 pw.println("</table>");
				 break;
			case 2://Update a row
       
				int BOOK_ACC_NO2=Integer.parseInt(request.getParameter("t11"));
				int RACK_NO2=Integer.parseInt(request.getParameter("t12"));
				
				 PreparedStatement pi2=con.prepareStatement("update "+tb+" set rack_no=? where book_acc_no=?");
				 pi2.setInt(1,RACK_NO2); 
             pi2.setInt(2,BOOK_ACC_NO2); 
             pi2.executeUpdate();
             pi2=con.prepareStatement("select * from "+tb); 
             ResultSet rs2=pi2.executeQuery();
             pw.print("<h1>Table data after Update..<h1>");  
             pw.println("<table border=1>");
             pw.println("<tr><th>BOOK_NAME</th><th>AUTHOR_NAME</th><th>PUBLISHER</th><th>EDITION</th><th>BOOK_ACC_NO</th><th>ISBN</th><th>TOTAL_BOOK</th><th>RACK_NO</th><th>PRICE</th></tr>");
 			while(rs2.next())
 		    {
 			pw.println("<tr><td>"+rs2.getString(1)+"</td><td>"+rs2.getString(2)+"</td><td>"+rs2.getString(3)+"</td><td>"+rs2.getInt(4)+"</td><td>"+rs2.getInt(5)+"</td><td>"+rs2.getInt(6)+"</td><td>"+rs2.getInt(7)+"</td><td>"+rs2.getInt(8)+"</td><td>"+rs2.getInt(9)+"</td></tr>");
 		          
 		    }
 		     pw.println("</table>");
 		     break;
			case 3: //Select a row
			 int book_acc_no3=Integer.parseInt(request.getParameter("t13"));
			  if(book_acc_no3==999)
			  { PreparedStatement pi3=con.prepareStatement("select * from "+tb);
			    ResultSet rs3=pi3.executeQuery();
			    pw.println("<table border=1>");
			    pw.println("<tr><th>BOOK_NAME</th><th>AUTHOR_NAME</th><th>PUBLISHER</th><th>EDITION</th><th>BOOK_ACC_NO</th><th>ISBN</th><th>TOTAL_BOOK</th><th>RACK_NO</th><th>PRICE</th></tr>");
 			while(rs3.next())
 		    {
 			pw.println("<tr><td>"+rs3.getString(1)+"</td><td>"+rs3.getString(2)+"</td><td>"+rs3.getString(3)+"</td><td>"+rs3.getInt(4)+"</td><td>"+rs3.getInt(5)+"</td><td>"+rs3.getInt(6)+"</td><td>"+rs3.getInt(7)+"</td><td>"+rs3.getInt(8)+"</td><td>"+rs3.getInt(9)+"</td></tr>");
 		          
 		    }
 		     pw.println("</table>");
 		     
			  }
			  else
			  {
				  PreparedStatement pi4=con.prepareStatement("select * from "+tb+" where book_acc_no=?");
					pi4.setInt(1,book_acc_no3);
					pi4.execute();
					ResultSet rs4=pi4.executeQuery();
			        pw.println("<table border=1>");
			        pw.println("<tr><th>BOOK_NAME</th><th>AUTHOR_NAME</th><th>PUBLISHER</th><th>EDITION</th><th>BOOK_ACC_NO</th><th>ISBN</th><th>TOTAL_BOOK</th><th>RACK_NO</th><th>PRICE</th></tr>");
	     			while(rs4.next())
	     		    {
	     			pw.println("<tr><td>"+rs4.getString(1)+"</td><td>"+rs4.getString(2)+"</td><td>"+rs4.getString(3)+"</td><td>"+rs4.getInt(4)+"</td><td>"+rs4.getInt(5)+"</td><td>"+rs4.getInt(6)+"</td><td>"+rs4.getInt(7)+"</td><td>"+rs4.getInt(8)+"</td><td>"+rs4.getInt(9)+"</td></tr>");
	     		          
	     		    }
	     		     pw.println("</table>");
			  }
			
		   
			break;
			case 4: //Delete rows      
				int BOOK_ACC_NO3=Integer.parseInt(request.getParameter("t14"));
			PreparedStatement pi5=con.prepareStatement("delete from "+tb+" where book_acc_no=?");
			
			pi5.setInt(1,BOOK_ACC_NO3);  
			pi5.executeUpdate();
			pi5=con.prepareStatement("select * from "+tb); 
		    ResultSet rs3=pi5.executeQuery();
		   
        pw.print("<h2>deleted successfull.....</h2>");
		    pw.print("<h1>Table data after delete..<h1>");  
			pw.println("<table border=1>");
			pw.println("<tr><th>BOOK_NAME</th><th>AUTHOR_NAME</th><th>PUBLISHER</th><th>EDITION</th><th>BOOK_ACC_NO</th><th>ISBN</th><th>TOTAL_BOOK</th><th>RACK_NO</th><th>PRICE</th></tr>");
			while(rs3.next())
		    {
			pw.println("<tr><td>"+rs3.getString(1)+"</td><td>"+rs3.getString(2)+"</td><td>"+rs3.getString(3)+"</td><td>"+rs3.getInt(4)+"</td><td>"+rs3.getInt(5)+"</td><td>"+rs3.getInt(6)+"</td><td>"+rs3.getInt(7)+"</td><td>"+rs3.getInt(8)+"</td><td>"+rs3.getInt(9)+"</td></tr>");
		          
		    }
		     pw.println("</table>");
		     break;
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
