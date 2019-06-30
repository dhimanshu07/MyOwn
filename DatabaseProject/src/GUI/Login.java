package GUI;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;

import Database_Classes.ConnectDB;
import Misc.DialogMessage;

import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;



public class Login {

	private JFrame frame;
	private JTextField textField;
    private JPasswordField passwordField;
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

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(410, 210, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		textField = new JTextField();
		textField.setBounds(262, 83, 86, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lbl1 = new JLabel("UserName");
		lbl1.setBounds(103, 86, 60, 14);
		frame.getContentPane().add(lbl1);
		JLabel err = new JLabel("");
		err.setBounds(71, 23, 277, 14);
		frame.getContentPane().add(err);
		
		JLabel lNewLabel_1 = new JLabel("Password");
		lNewLabel_1.setBounds(103, 139, 92, 14);
		frame.getContentPane().add(lNewLabel_1);
		
		
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					String uname = textField.getText();
					String password = new String(passwordField.getPassword());
					int num = ConnectDB.Login(uname,password);
				
				if(num==0)
				{
					DialogMessage.showInfoDialog("Employee Login");
					Menu m = new Menu();
					m.Newscreen();
					frame.setVisible(false);
				}
				
				else if(num==2)
				{
					DialogMessage.showInfoDialog("Employee Login");
					Menu m = new Menu();
					m.Newscreen();
					frame.setVisible(false);
				}
				else
				{
					DialogMessage.showWarningDialog("ERROR");
				}
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				} catch (NoSuchAlgorithmException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
					}
		});
		btnNewButton.setBounds(106, 196, 89, 23);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Exit");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnNewButton_1.setBounds(262, 196, 89, 23);
		frame.getContentPane().add(btnNewButton_1);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(262, 136, 86, 20);
		frame.getContentPane().add(passwordField);
		
	
	}
}
