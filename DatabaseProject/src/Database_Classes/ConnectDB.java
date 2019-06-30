package Database_Classes;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;

import javax.swing.table.DefaultTableModel;

public class ConnectDB {

	public static int Insert_item(String a,String b,int c,int d)
	{
	Connection conn = MySQLAccess.getConnection();
	PreparedStatement st = null;
	ResultSet rs = null;
	int num = 0;
	try
	{
		String sql = "insert into items(Item_Name,Item_Type,Item_Price,Item_Quantity) values(?,?,?,?)";
		st = conn.prepareStatement(sql);
		// index starts from 1
		st.setString(1, a);
		st.setString(2, b);
		st.setInt(3, c);
		st.setInt(4, d);
		// execute and return number of rows that take effect
		num = st.executeUpdate();
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	finally
	{
		MySQLAccess.release(conn, st, rs);
	}
	return num;
	}
	
	public static boolean generate() throws SQLException, IOException {
		
		Connection conn = MySQLAccess.getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		
		String sql = "SELECT orders.order_date, customer.firstName,customer.address,customer.phone_no, orders.order_id,"
				+ " orders.order_description,orders.order_price FROM Orders"
				+ " INNER JOIN customer  ON orders.customer_id=customer.customer_id";
		
	   st = conn.prepareStatement(sql);
	   rs = st.executeQuery();
	   ArrayList odate = new ArrayList();
		ArrayList name = new ArrayList();
		ArrayList address = new ArrayList();
		ArrayList num = new ArrayList();
		ArrayList orid = new ArrayList();
		ArrayList desc = new ArrayList();
		ArrayList price = new ArrayList();
		while (rs.next()) {
			String date = rs.getString("order_date");
			String fname = rs.getString("firstName");
			String addre = rs.getString("address");
			String ohnum = rs.getString("phone_no");
			String oid = rs.getString("order_id");
			String orderdes = rs.getString("order_description");
			String oprice = rs.getString("order_price");

			odate.add(date);
			name.add(fname);
			address.add(addre);
			num.add(ohnum);
			orid.add(oid);
			desc.add(orderdes);
			price.add(oprice);
		}

		for (int i = 0; i < num.size(); i++) {
			String orderNumber = (String) orid.get(i);
			String filePath = String.format("docket_%s.csv", orderNumber);
			BufferedWriter writer = new BufferedWriter(new FileWriter(new File(filePath)));
			String timeStamp = new SimpleDateFormat("yyyy:MM:dd").format(Calendar.getInstance().getTime());

			writer.write("Order date: " + odate.get(i));
			writer.newLine();
			writer.write("Dispatch date: " + timeStamp);
			writer.newLine();
			writer.newLine();
			writer.write("Customer Name: " + name.get(i));
			writer.newLine();
			writer.write("Customer Address: " + address.get(i));
			writer.newLine();
			writer.write(String.format("Customer Phone Number: %s", num.get(i)));
			writer.newLine();
			String orderLine = String.format("Order ID: %s | Items: %s | Price: %s", orid.get(i), desc.get(i), price.get(i));
			writer.write(orderLine);
			writer.close();
		}
		
		return true;

		
	}
	
	
	public static DefaultTableModel getOrderReport() throws SQLException {
		// TODO Auto-generated method stub
		Connection conn = MySQLAccess.getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		
	   String sql = "SELECT * FROM orders";
	st = conn.prepareStatement(sql);
	rs = st.executeQuery();
	
	DefaultTableModel model = new DefaultTableModel();
	model.setColumnIdentifiers(getItemHeading());
	while (rs.next()) { 
		    int id = rs.getInt("order_id");
		    int  d = rs.getInt("customer_id");
		    String e = rs.getString("order_date");
		    String g = rs.getString("order_description");
		    int f = rs.getInt("order_price");
		    model.addRow(new Object[]{id,d,e, g,f});
	}
	return model;
	}
	
	public static String[] getItemHeading() throws SQLException {
		// TODO Auto-generated method stub
		Connection conn = MySQLAccess.getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		
	   String sql = "SHOW COLUMNS FROM Newsagency_DB.orders";
	   
	st = conn.prepareStatement(sql);
	rs = st.executeQuery();
	ArrayList<String> types = new ArrayList<String>();

	while (rs.next()) { 
	    types.add(rs.getString(1));
	}
	
	String[] type = new String[types.size()];
	return type = types.toArray(type); }

		
	public static DefaultTableModel getEmployeesReport() throws SQLException {
		// TODO Auto-generated method stub
		Connection conn = MySQLAccess.getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		
	   String sql = "SELECT Customers_id,name,address,phonenum FROM Customers";
	st = conn.prepareStatement(sql);
	rs = st.executeQuery();
	/*new String[]{"Class Name", "Home work", "Due Date","H"}, 0*/
	DefaultTableModel model = new DefaultTableModel();
	model.setColumnIdentifiers(getEmployeesHeading());
	while (rs.next()) { 
		 int id = rs.getInt("Customers_id");
		 String d = rs.getString("name");
		 String f = rs.getString("phonenum");
		 String g = rs.getString("address");
		 
		    model.addRow(new Object[]{id,d, f,g});
	}
	return model;
	}

	public void genrate() {
		
	}

	public static String[] getEmployeesHeading() throws SQLException {
		// TODO Auto-generated method stub
		Connection conn = MySQLAccess.getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		
		String sql = "SHOW COLUMNS FROM Newsagency_DB.customers";
		
	st = conn.prepareStatement(sql);
	rs = st.executeQuery();
	ArrayList<String> types = new ArrayList<String>();

	while (rs.next()) { 
	    types.add(rs.getString(1));
	}
	
	String[] type = new String[types.size()];
	return type = types.toArray(type); }

	
	
	public static int Login(String a,String b) throws SQLException, NoSuchAlgorithmException
	{
		
			Connection conn = MySQLAccess.getConnection();
			PreparedStatement st = null;
			ResultSet rs = null;
			
	
		//String sql = "insert into agent(name,contact,address) values('Saksham','77','Apt no. 15')";

		   String sql = "select username,user_password,user_job from users";
		st = conn.prepareStatement(sql);
		rs = st.executeQuery();
		     
		int num = 1;
		      //STEP 5: Extract data from result set
		      while(rs.next()){
		         //Retrieve by column name
		          String id  = rs.getString("username");
		         String pwd = rs.getString("user_password");
		       // String pwds = decodePwd(pwd);
		          int type = rs.getInt("user_job");
		          
		          if(a.equals(id)&&b.equals(pwd))
		          {
		        	  if(type==0)
		        	  {
		        	num = 0;  
		          }
		        	  else if(type==1)
		        	  {
		        		  num=2;
		        	  }
		          }
		
		      }
			return num;
		}

	
	public static int Insert_Customer(String a,String b,String d)
	{
	Connection conn = MySQLAccess.getConnection();
	PreparedStatement st = null;
	ResultSet rs = null;
	int num = 0;
	try
	{
		String sql = "insert into customers(name,address,phonenum) values(?,?,?)";
		st = conn.prepareStatement(sql);
		// index starts from 1
		st.setString(1, a);
		st.setString(2, b);
		st.setString(3, d);
		// execute and return number of rows that take effect
		num = st.executeUpdate();
	}
	catch(Exception e1)
	{
		e1.printStackTrace();
	}
	finally
	{
		MySQLAccess.release(conn, st, rs);
	}
	return num;
	}
	
	public static int Update_Customer(String a,String b,String d,int id)
	{
	Connection conn = MySQLAccess.getConnection();
	PreparedStatement st = null;
	ResultSet rs = null;
	int num = 0;
	try
	{
		String sql = "update customers set name = ?,address = ? ,phonenum = ? where Customers_id = ?";
		st = conn.prepareStatement(sql);
		// index starts from 1
		st.setString(1, a);
		st.setString(2, b);
		st.setString(3, d);
		st.setInt(4, id);
		// execute and return number of rows that take effect
		num = st.executeUpdate();
	}
	catch(Exception e1)
	{
		e1.printStackTrace();
	}
	finally
	{
		MySQLAccess.release(conn, st, rs);
	}
	return num;
	}

	public static int Delete_Customer(int id)
	{
	Connection conn = MySQLAccess.getConnection();
	PreparedStatement st = null;
	ResultSet rs = null;
	int num = 0;
	try
	{
		String sql = "delete from customers where Customers_id = ?";
		st = conn.prepareStatement(sql);
		// index starts from 1
		
		st.setInt(1, id);
		// execute and return number of rows that take effect
		num = st.executeUpdate();
	}
	catch(Exception e1)
	{
		e1.printStackTrace();
	}
	finally
	{
		MySQLAccess.release(conn, st, rs);
	}
	return num;
	}


	public static int Insert_Order(String a,String b,int d,int cid)
	{
	Connection conn = MySQLAccess.getConnection();
	PreparedStatement st = null;
	ResultSet rs = null;
	int num = 0;
	try
	{
		String sql = "insert into orders(customer_id,order_date,order_description,order_price) values(?,?,?,?)";
		st = conn.prepareStatement(sql);
		// index starts from 1
		st.setString(2, a);
		st.setString(3, b);
		st.setInt(4, d);
		st.setInt(1, cid);
		// execute and return number of rows that take effect
		num = st.executeUpdate();
	}
	catch(Exception e1)
	{
		e1.printStackTrace();
	}
	finally
	{
		MySQLAccess.release(conn, st, rs);
	}
	return num;
	}
	
	public static int Update_Order(String a,String b,int d,int cid,int oid)
	{
	Connection conn = MySQLAccess.getConnection();
	PreparedStatement st = null;
	ResultSet rs = null;
	int num = 0;
	try
	{
		String sql = "update customers set customer_id = ?,order_date = ? ,order_description = ?,order_price = ?  where order_id = ?";
		st = conn.prepareStatement(sql);
		// index starts from 1
		st.setInt(1, cid);
		st.setString(3, a);
		st.setString(3, b);
		st.setInt(4, d);
		st.setInt(5, oid);
		// execute and return number of rows that take effect
		num = st.executeUpdate();
	}
	catch(Exception e1)
	{
		e1.printStackTrace();
	}
	finally
	{
		MySQLAccess.release(conn, st, rs);
	}
	return num;
	}

	public static int Delete_Delete(int id)
	{
	Connection conn = MySQLAccess.getConnection();
	PreparedStatement st = null;
	ResultSet rs = null;
	int num = 0;
	try
	{
		String sql = "delete from orders where order_id = ?";
		st = conn.prepareStatement(sql);
		// index starts from 1
		
		st.setInt(1, id);
		// execute and return number of rows that take effect
		num = st.executeUpdate();
	}
	catch(Exception e1)
	{
		e1.printStackTrace();
	}
	finally
	{
		MySQLAccess.release(conn, st, rs);
	}
	return num;
	}

		
			
	
}



