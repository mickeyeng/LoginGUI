package sql;

import java.sql.*;
import javax.swing.*;
public class sqlConnection {
	Connection conn = null;
	
	public static Connection dataBaseConnector() {
		try {
			Class.forName("org.sqlite.JDBC");
			Connection conn = DriverManager.getConnection("jdbc:sqlite:LoginForm.sqlite");// path of database file
			return conn;
			
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Connection Sucessfull");
			System.out.println("Connection Successfull");
			return null;
		}
		
	}

}
