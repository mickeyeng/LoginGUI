package sql;

import java.awt.EventQueue;
import java.sql.*;
import javax.swing.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	Connection connection = null;
	JTextField textField;
	JPasswordField passwordField;
	
	
	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
		connection = sqlConnection.dataBaseConnector();
		
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 554, 387);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel usernamebtn = new JLabel("Username");
		usernamebtn.setBounds(69, 130, 108, 16);
		frame.getContentPane().add(usernamebtn);
		
		JLabel passwordbtn = new JLabel("Password");
		passwordbtn.setBounds(69, 171, 92, 16);
		frame.getContentPane().add(passwordbtn);
		
		textField = new JTextField();
		textField.setBounds(159, 125, 198, 26);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton loginbtn = new JButton("Login");
		loginbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					String query = "select * from Login where username=? and password=?";
					// pass the query to the prepared statement
					PreparedStatement pst;
					pst = connection.prepareStatement(query);
					pst.setString(1, textField.getText()); // 1 is username value
					pst.setString(2, passwordField.getText()); // 2 is password value It will mask password field can't get text
					
					
					// once query is passed it will store result in rs and the result for rs will increase. if we get one result it will match the username and password and will be correc 
					ResultSet rs = pst.executeQuery(); // when the query is excuted the result will be transfered to the rs object
					int count = 0;
					while(rs.next()) { 
						count ++;
			
					}
					if (count == 1 ) {
						JOptionPane.showMessageDialog(null, "Username and Password is correct");
					} else if (count >1 ) {
						JOptionPane.showMessageDialog(null, "Duplicate Username and Password");
					} else {
						JOptionPane.showMessageDialog(null, "Username and Passwprd is not correct... Please try Again");
					}
					
					rs.close(); // close the connection with db
					pst.close(); 
					
				} catch(Exception ex) {
					ex.printStackTrace();
					
				}
			}
		});
		loginbtn.setBounds(159, 232, 166, 60);
		frame.getContentPane().add(loginbtn);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(159, 171, 197, 26);
		frame.getContentPane().add(passwordField);
	}
}
