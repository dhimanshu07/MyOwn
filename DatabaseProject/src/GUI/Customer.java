package GUI;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.xdevapi.Table;

import Database_Classes.ConnectDB;
import Misc.DialogMessage;

import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class Customer extends JPanel {
	private JTextField cust_id;
	private JTextField name;
	private JTextField address;
	private JTextField number;
	
	public Customer() {
        initializeUI();    
    }

    private void initializeUI() {
        setPreferredSize(new Dimension(539, 341));
        setLayout(null);

        JTable table = new JTable(25,15);

        // Turn off JTable's auto resize so that JScrollPane will show a
        // horizontal scroll bar.
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        JScrollPane pane = new JScrollPane(table);
        pane.setBounds(0, 154, 539, 187);
        
        JButton btnGenerate = new JButton("Generate");
        btnGenerate.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		try {
					DefaultTableModel tableModel = new DefaultTableModel();
					tableModel = ConnectDB.getEmployeesReport();
					table.setModel(tableModel);
				
        		} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
        btnGenerate.setBounds(433, 10, 96, 21);
        add(btnGenerate);
        add(pane);
        
        JButton btnDelete = new JButton("Click");
       
        btnDelete.setBounds(407, 122, 85, 21);
        add(btnDelete);
        
        cust_id = new JTextField();
        cust_id.setBounds(112, 42, 122, 19);
        add(cust_id);
        cust_id.setColumns(10);
        
        JButton button = new JButton("Back");
        button.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		JComponent comp = (JComponent) e.getSource();
      		  Window win = SwingUtilities.getWindowAncestor(comp);
      		  win.dispose();
      		Menu m = new Menu();
			m.Newscreen();
			setVisible(false);
      		  
        	}
        });
        button.setBounds(10, 10, 85, 21);
        add(button);
        
        JLabel lblCustomerId = new JLabel("Customer ID");
        lblCustomerId.setBounds(10, 45, 81, 14);
        add(lblCustomerId);
        
        JLabel lblCustomerName = new JLabel("Customer Name");
        lblCustomerName.setBounds(10, 85, 96, 14);
        add(lblCustomerName);
        
        name = new JTextField();
        name.setBounds(111, 82, 123, 20);
        add(name);
        name.setColumns(10);
        
        JLabel lblCustomerAddress = new JLabel("Customer Address");
        lblCustomerAddress.setBounds(246, 45, 108, 14);
        add(lblCustomerAddress);
        
        JLabel lblPhoneNumber = new JLabel("Phone Number");
        lblPhoneNumber.setBounds(257, 85, 85, 14);
        add(lblPhoneNumber);
        
        address = new JTextField();
        address.setBounds(364, 42, 144, 20);
        add(address);
        address.setColumns(10);
        
        number = new JTextField();
        number.setBounds(364, 82, 144, 20);
        add(number);
        number.setColumns(10);
        
        JLabel lblClickGenerateTo = new JLabel("Click Generate to see information from Customer Table");
        lblClickGenerateTo.setBounds(105, 13, 329, 14);
        add(lblClickGenerateTo);
        
        JRadioButton rdbtnInsert = new JRadioButton("Insert");
        rdbtnInsert.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		cust_id.setEditable(false);
        		name.setEditable(true);
        		address.setEditable(true);
        		number.setEditable(true);
        	}
        });
        rdbtnInsert.setBounds(23, 121, 109, 23);
        add(rdbtnInsert);
        
        JRadioButton rdbtnUpdate = new JRadioButton("Update");
        rdbtnUpdate.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		cust_id.setEditable(true);
        		name.setEditable(true);
        		address.setEditable(true);
        		number.setEditable(true);
        	}
        });
        rdbtnUpdate.setBounds(134, 121, 109, 23);
        add(rdbtnUpdate);
        
        JRadioButton rdbtnDelete = new JRadioButton("Delete");
        rdbtnDelete.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		cust_id.setEditable(true);
        		name.setEditable(false);
        		address.setEditable(false);
        		number.setEditable(false);
        	}
        });
        rdbtnDelete.setBounds(269, 121, 109, 23);
        add(rdbtnDelete);
        
        ButtonGroup bgroup = new ButtonGroup();
        bgroup.add(rdbtnInsert);
        bgroup.add(rdbtnUpdate);
        bgroup.add(rdbtnDelete);
    
        btnDelete.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
                 int  id = Integer.parseInt(cust_id.getText());
				 String name1 = name.getText();
				String address1 = address.getText();
				String phone = number.getText();
				
				if(rdbtnInsert.isSelected()) {
					int num  = ConnectDB.Insert_Customer(name1, address1, phone);
					if(num>0)
					{
						DialogMessage.showInfoDialog("Inserted");
					}
					else
					{
						DialogMessage.showWarningDialog("ERROR");
					}
				}
				
				if(rdbtnUpdate.isSelected()) {
					int num  = ConnectDB.Update_Customer(name1, address1, phone, id);
					if(num>0)
					{
						DialogMessage.showInfoDialog("Updated");
					}
					else
					{
						DialogMessage.showWarningDialog("ERROR");
					}
				}
				if(rdbtnDelete.isSelected()) {
					int num  = ConnectDB.Delete_Customer(id);
					if(num>0)
					{
						DialogMessage.showInfoDialog("Deleted");
					}
					else
					{
						DialogMessage.showWarningDialog("ERROR");
					}
				}
				cust_id.setText("");;
        		name.setText("");
        		address.setText("");
        		number.setText("");
        	}
        });
    }

    public static void showFrame() {
        JPanel panel = new Customer();
        panel.setOpaque(true);
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setTitle("Employees Report");
        frame.setContentPane(panel);
        frame.setBounds(410, 210, 800, 800);
        frame.pack();
        frame.setVisible(true);
    }

    public static void New() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	Customer.showFrame();
            }
        });
    }
}