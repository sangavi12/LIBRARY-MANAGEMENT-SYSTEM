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
 * Servlet implementation class admin1
 */
public class admin1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public admin1() {
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
				String STUDENT_NAME=request.getParameter("t2");
				int REG_NO=Integer.parseInt(request.getParameter("t3"));
				String DEP=request.getParameter("t4");
				String YEAR =request.getParameter("t5");
				String USERNAME=request.getParameter("t6");
				String PASSWORD =request.getParameter("t7");
				String EMAILID=request.getParameter("t8");
				String PHONE_NO=request.getParameter("t9");
				PreparedStatement pi1=con.prepareStatement("insert into "+tb+" values(?,?,?,?,?,?,?,?)");
				pi1.setString(1,STUDENT_NAME); 
				pi1.setInt(2,REG_NO); 
				pi1.setString(3,DEP); 
				pi1.setString(4,YEAR.toString() ); 
				pi1.setString(5,USERNAME.toString()); 
				pi1.setString(6,PASSWORD.toString()); 
				pi1.setString(7,EMAILID.toString()); 
				pi1.setString(8,PHONE_NO.toString()); 
				pi1.executeUpdate();  
				pw.print("<h1>You are successfully Inserted a row...<h1>");  
				pw.print("<h1>Table data after insertion..<h1>");  
				pi1=con.prepareStatement("select * from "+tb); 
				ResultSet rs1=pi1.executeQuery("Select * from "+tb);
				pw.println("<table border=1>");
				pw.println("<tr><th>STUDENT_NAME</th><th>REG_NO</th><th>DEP</th><th>YEAR</th><th>USERNAME</th><th>PASSWORD</th><th>EMAILID</th><th>PHONE_NO</th></tr>");
				while(rs1.next())
		         {
		           pw.println("<tr><td>"+rs1.getString(1)+"</td><td>"+rs1.getInt(2)+"</td><td>"+rs1.getString(3)+"</td><td>"+rs1.getString(4)+"</td><td>"+rs1.getString(5)+"</td><td>"+rs1.getString(6)+"</td><td>"+rs1.getString(7)+"</td><td>"+rs1.getString(8)+"</td></tr>");
		          
		         }
				 pw.println("</table>");
				 break;
			case 2://Update a row
     
				int REGISTER_NUMBER1=Integer.parseInt(request.getParameter("t11"));
				String EMAILID1=request.getParameter("t12");
				
				 PreparedStatement pi2=con.prepareStatement("update "+tb+" set emailid =? where reg_no=?");
				 pi2.setString(1,EMAILID1.toString()); 
           pi2.setInt(2,REGISTER_NUMBER1); 
           pi2.executeUpdate();
           pi2=con.prepareStatement("select * from "+tb); 
           ResultSet rs2=pi2.executeQuery();
           pw.print("<h1>Table data after Update..<h1>");  
           pw.println("<table border=1>");
pw.println("<tr><th>STUDENT_NAME</th><th>REG_NO</th><th>DEP</th><th>YEAR</th><th>USERNAME</th><th>PASSWORD</th><th>EMAILID</th><th>PHONE_NO</th></tr>");
				while(rs2.next())
		         {
		           pw.println("<tr><td>"+rs2.getString(1)+"</td><td>"+rs2.getInt(2)+"</td><td>"+rs2.getString(3)+"</td><td>"+rs2.getString(4)+"</td><td>"+rs2.getString(5)+"</td><td>"+rs2.getString(6)+"</td><td>"+rs2.getString(7)+"</td><td>"+rs2.getString(8)+"</td></tr>");
		          
		         }
           
		     pw.println("</table>");
		     break;
			case 3: //Select a row
			 int REGISTER_NUMBER2=Integer.parseInt(request.getParameter("t13"));
			  if(REGISTER_NUMBER2==999)
			  { PreparedStatement pi3=con.prepareStatement("select * from "+tb);
			    ResultSet rs3=pi3.executeQuery();
			    pw.println("<table border=1>");
pw.println("<tr><th>STUDENT_NAME</th><th>REG_NO</th><th>DEP</th><th>YEAR</th><th>USERNAME</th><th>PASSWORD</th><th>EMAILID</th><th>PHONE_NO</th></tr>");
				while(rs3.next())
		         {
		           pw.println("<tr><td>"+rs3.getString(1)+"</td><td>"+rs3.getInt(2)+"</td><td>"+rs3.getString(3)+"</td><td>"+rs3.getString(4)+"</td><td>"+rs3.getString(5)+"</td><td>"+rs3.getString(6)+"</td><td>"+rs3.getString(7)+"</td><td>"+rs3.getString(8)+"</td></tr>");
			    
		    }
		     pw.println("</table>");
			  }
			  else
			  {
				  PreparedStatement pi4=con.prepareStatement("select * from "+tb+" where reg_no=?");
					pi4.setInt(1,REGISTER_NUMBER2);
					pi4.execute();
					ResultSet rs4=pi4.executeQuery();
			        pw.println("<table border=1>");
			        
	     		    
pw.println("<tr><th>STUDENT_NAME</th><th>REG_NO</th><th>DEP</th><th>YEAR</th><th>USERNAME</th><th>PASSWORD</th><th>EMAILID</th><th>PHONE_NO</th></tr>");
				while(rs4.next())
		         {
		           pw.println("<tr><td>"+rs4.getString(1)+"</td><td>"+rs4.getInt(2)+"</td><td>"+rs4.getString(3)+"</td><td>"+rs4.getString(4)+"</td><td>"+rs4.getString(5)+"</td><td>"+rs4.getString(6)+"</td><td>"+rs4.getString(7)+"</td><td>"+rs4.getString(8)+"</td></tr>");
		
			  }
			  
		     pw.println("</table>");

			  }
		   
			break;
			case 4: //Delete rows      
				int REGISTER_NUMBER3=Integer.parseInt(request.getParameter("t14"));
			PreparedStatement pi5=con.prepareStatement("delete from "+tb+" where reg_no=?");
			pi5.setInt(1,REGISTER_NUMBER3);  
			pi5.executeUpdate();
			pi5=con.prepareStatement("select * from "+tb); 
		    ResultSet rs5=pi5.executeQuery();
		   
      pw.print("<h2>deleted successfull.....</h2>");
		    pw.print("<h1>Table data after delete..<h1>");  
			pw.println("<table border=1>");
			pw.println("<tr><th>STUDENT_NAME</th><th>REG_NO</th><th>DEP</th><th>YEAR</th><th>USERNAME</th><th>PASSWORD</th><th>EMAILID</th><th>PHONE_NO</th></tr>");

			while(rs5.next())
	         {
	           pw.println("<tr><td>"+rs5.getString(1)+"</td><td>"+rs5.getInt(2)+"</td><td>"+rs5.getString(3)+"</td><td>"+rs5.getString(4)+"</td><td>"+rs5.getString(5)+"</td><td>"+rs5.getString(6)+"</td><td>"+rs5.getString(7)+"</td><td>"+rs5.getString(8)+"</td></tr>");
	
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
