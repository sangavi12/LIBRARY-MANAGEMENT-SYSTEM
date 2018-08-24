package libbook;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class util {
	public static Connection getDBConnection()
	{
	  Connection con=null;
	  try {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		con=DriverManager.getConnection("jdbc:oracle:thin:hr/hr@localhost");
	  }
	  catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		  return con;
	}

}


