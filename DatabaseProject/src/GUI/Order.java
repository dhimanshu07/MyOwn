package GUI;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.xdevapi.Table;

import Database_Classes.ConnectDB;
import Misc.DialogMessage;

import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class Order extends JPanel {
	private JTextField ord_id;
	private JTextField date2;
	private JTextField cust_id;
	private JTextField descrip;
	private JTextField price1;
	
	public Order() {
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
					tableModel = ConnectDB.getOrderReport();
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
        
        ord_id = new JTextField();
        ord_id.setBounds(112, 42, 122, 19);
        add(ord_id);
        ord_id.setColumns(10);
        
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
        
        JLabel lblCustomerId = new JLabel("Order ID");
        lblCustomerId.setBounds(10, 45, 81, 14);
        add(lblCustomerId);
        
        JLabel lblCustomerName = new JLabel("Customer ID");
        lblCustomerName.setBounds(257, 45, 96, 14);
        add(lblCustomerName);
        
        date2 = new JTextField();
        date2.setBounds(94, 82, 85, 20);
        add(date2);
        date2.setColumns(10);
        
        JLabel date = new JLabel("Order Date");
        date.setBounds(10, 85, 75, 14);
        add(date);
        
        JLabel desc = new JLabel("Order Description");
        desc.setBounds(189, 85, 85, 14);
        add(desc);
        
        cust_id = new JTextField();
        cust_id.setBounds(364, 42, 144, 20);
        add(cust_id);
        cust_id.setColumns(10);
        
        descrip = new JTextField();
        descrip.setBounds(284, 82, 75, 20);
        add(descrip);
        descrip.setColumns(10);
        
        JLabel lblClickGenerateTo = new JLabel("Click Generate to see information from Customer Table");
        lblClickGenerateTo.setBounds(105, 13, 329, 14);
        add(lblClickGenerateTo);
        
        JRadioButton rdbtnInsert = new JRadioButton("Insert");
        rdbtnInsert.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		ord_id.setEditable(false);
        		date2.setEditable(true);
        		cust_id.setEditable(true);
        		descrip.setEditable(true);
        	}
        });
        rdbtnInsert.setBounds(23, 121, 109, 23);
        add(rdbtnInsert);
        
        JRadioButton rdbtnUpdate = new JRadioButton("Update");
        rdbtnUpdate.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		ord_id.setEditable(true);
        		date2.setEditable(true);
        		cust_id.setEditable(true);
        		descrip.setEditable(true);
        	}
        });
        rdbtnUpdate.setBounds(134, 121, 109, 23);
        add(rdbtnUpdate);
        
        JRadioButton rdbtnDelete = new JRadioButton("Delete");
        rdbtnDelete.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		ord_id.setEditable(true);
        		date2.setEditable(false);
        		cust_id.setEditable(false);
        		descrip.setEditable(false);
        	}
        });
        rdbtnDelete.setBounds(269, 121, 109, 23);
        add(rdbtnDelete);
        
        ButtonGroup bgroup = new ButtonGroup();
        bgroup.add(rdbtnInsert);
        bgroup.add(rdbtnUpdate);
        bgroup.add(rdbtnDelete);
        
        JLabel price = new JLabel("Price");
        price.setBounds(369, 85, 46, 14);
        add(price);
        
        price1 = new JTextField();
        price1.setBounds(422, 82, 86, 20);
        add(price1);
        price1.setColumns(10);
    
        btnDelete.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
                 
                 
                 
				 String description = descrip.getText();
				 String date1 = date2.getText();
				
				
				if(rdbtnInsert.isSelected()) {
					
					int cust = Integer.parseInt(cust_id.getText());
					int price = Integer.parseInt(price1.getText());
					int num  = ConnectDB.Insert_Order(date1,description,price,cust);
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
					int  oid = Integer.parseInt(ord_id.getText());
					int cust = Integer.parseInt(cust_id.getText());
					int price = Integer.parseInt(price1.getText());
					int num  = ConnectDB.Update_Order(date1,description,price,cust,oid);
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
					int  oid = Integer.parseInt(ord_id.getText());
					int num  = ConnectDB.Delete_Delete(oid);
					if(num>0)
					{
						DialogMessage.showInfoDialog("Deleted");
					}
					else
					{
						DialogMessage.showWarningDialog("ERROR");
					}
				}
				ord_id.setText("");;
        		date2.setText("");
        		cust_id.setText("");
        		descrip.setText("");
        		price1.setText("");
        	}
        });
    }

    public static void showFrame() {
        JPanel panel = new Order();
        panel.setOpaque(true);
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setTitle("Order Report");
        frame.setContentPane(panel);
        frame.setBounds(410, 210, 800, 800);
        frame.pack();
        frame.setVisible(true);
    }

    public static void New() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	Order.showFrame();
            }
        });
    }
}