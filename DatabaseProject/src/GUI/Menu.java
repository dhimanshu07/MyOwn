package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JTable;

import Database_Classes.ConnectDB;

//import core.generat_docket;

import javax.swing.JLabel;
import javax.swing.JMenu;

import java.awt.Font;
import java.awt.Color;

public class Menu {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void Newscreen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu window = new Menu();
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
	public Menu() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setForeground(Color.LIGHT_GRAY);
		frame.setBounds(410, 210, 600, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("Customer");
		btnNewButton.setForeground(Color.DARK_GRAY);
		btnNewButton.setBackground(Color.CYAN);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Customer re = new Customer();
				re.New();
				frame.setVisible(false);
				
				
			}
		});
		btnNewButton.setBounds(70, 73, 178, 94);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Order");
		btnNewButton_1.setBackground(Color.CYAN);
		btnNewButton_1.setForeground(Color.BLACK);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Order o = new Order();
				o.New();;
				frame.setVisible(false);
			}
		});
		JMenuBar menuBar = new JMenuBar();
		menuBar.setFont(new Font("Arial Black", Font.BOLD, 15));
		menuBar.setForeground(new Color(255, 0, 0));
		menuBar.setBackground(new Color(135, 206, 250));
		menuBar.setBounds(0, 0, 119, 26);
		frame.getContentPane().add(menuBar);
		
		JMenu mnOptions = new JMenu("Options");
		mnOptions.setForeground(new Color(255, 0, 0));
		mnOptions.setFont(new Font("Arial Black", Font.BOLD, 18));
		menuBar.add(mnOptions);
		
		JMenuItem menu_reset = new JMenuItem("Logout");
		menu_reset.setForeground(new Color(30, 144, 255));
		menu_reset.setFont(new Font("Arial", Font.BOLD, 14));
		mnOptions.add(menu_reset);
		btnNewButton_1.setBounds(332, 72, 178, 97);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Print Order");
		btnNewButton_2.setBackground(Color.CYAN);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    try {
					ConnectDB.generate();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_2.setBounds(196, 225, 189, 94);
		frame.getContentPane().add(btnNewButton_2);
	}
}
