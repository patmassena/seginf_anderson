package JavaDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

		public static Connection getConnection() throws SQLException{
			return DriverManager.getConnection("jdbc:mysql://localhost/lab", "root", "newrootpassword");
		}

}
