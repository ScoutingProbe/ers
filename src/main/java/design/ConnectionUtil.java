package design;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.apache.log4j.Logger;

public class ConnectionUtil {
	private Logger logger;
	
	private ConnectionUtil(Logger logger) {
		this.logger = logger;
	}
	public Logger getLogger() {
		return logger;
	}
	public static Connection connect(Logger logger) {
		try{
			Class.forName("oracle.jdbc.OracleDriver");
			String url = System.getProperty("ERS_URL");
			String username = System.getProperty("ERS_USERNAME");
			String password = System.getProperty("ERS_PASSWORD");
			return DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			logger.error(e.getErrorCode());
			logger.error(e.getErrorCode());
			logger.error(e.getMessage());
		} catch (ClassNotFoundException e) {
			logger.error(e.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}	
}