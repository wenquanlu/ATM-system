package WinBuilder;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class WinBuilder {
	private int uid = 1;

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WinBuilder window = new WinBuilder();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public WinBuilder() {
		initialize();
	}
	
	public void sendData(String name, String amount) {
		Connection c = null;
	    Statement stmt = null;
	    try {
	         Class.forName("org.postgresql.Driver");
	         c = DriverManager
	            .getConnection("jdbc:postgresql://localhost:5432/luwenquan",
	            "postgres", "");
	         c.setAutoCommit(false);
	         System.out.println("Opened database successfully");
	
	         stmt = c.createStatement();
	         String sql = "INSERT INTO userinfo (ID,NAME,AMOUNT) "
	            + "VALUES (" + Integer.toString(uid) + 
	            ", '" + name + "', " + amount + " );";
	         System.out.println(sql);
	         stmt.executeUpdate(sql);
	
	         stmt.close();
	         c.commit();
	         c.close();
	    } catch (Exception e) {
	       System.err.println( e.getClass().getName()+": "+ e.getMessage() );
	       System.exit(0);
	    }
		
		
		uid ++;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("Submit");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = textField.getText().toString();
				String amount = textField_1.getText().toString();
				sendData(name, amount);
			}
		});
		btnNewButton.setBounds(171, 216, 117, 29);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("Enter your name: ");
		lblNewLabel.setBounds(32, 46, 122, 29);
		frame.getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(147, 47, 130, 26);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Enter deposit amount: ");
		lblNewLabel_1.setBounds(32, 117, 153, 16);
		frame.getContentPane().add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		textField_1.setBounds(182, 112, 130, 26);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
	}
}
