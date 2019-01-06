import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.font.*;
import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class JavaLesson36 {

	// Used to hold the column data for each player
	static Object[][] databaseInfo;
	
	// The column titles for the JTable
	static Object[] columns = {"id", "Nazwa"};
	
	// A ResultSet contains a table of data filled with the result of the query
	static ResultSet rows;
	
	// ResultSetMetaData contains information on the data returned by the query
	static ResultSetMetaData metaData;
	
	// DefaultTableModel defines the methods JTable will use
	// I'm overriding the getColumnClass
	static DefaultTableModel dTableModel = new DefaultTableModel(databaseInfo, columns) {
		public Class getColumnClass(int column) {
			Class returnValue;
			
			// Verifying that the column exists (index > 0 && index < number of columns)
			if ((column >= 0) && (column < getColumnCount())) {
				returnValue = getValueAt(0, column).getClass();
			} else {
				
				// Returns the class for the item in the column
				returnValue = Object.class;
			}
			
			return returnValue;
		}
	};
	
	public static void main(String[] args) {
		
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Connection conn = null;
		
		try {
		
			// The driver allows you to query the database with Java
			// forName dynamically loads the class for you
			Class.forName("com.mysql.jdbc.Driver");
			
			// DriverManager is used to handle a set of JDBC drivers
			// getConnection establishes a connection to the database
			// You must also pass the userid and password for the database
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Test?useSSL=false", "root", "karolkrol83");
			
			// Statement objects executes a SQL query
			// createStatement returns a Statement object
			Statement sqlState = conn.createStatement();
			
			// This is the query I'm sending to the database
			String selectStuff = "SELECT g.id, g.nazwa FROM grupa_krwi g;";
						
			// A ResultSet contains a table of data representing the result
			// of the query. It can not be changed and can only be read in the direction
			
			rows = sqlState.executeQuery(selectStuff);
			
			// Temporarily holds the row results
			Object[] tempRow;
			
			// next is used to iterate through the results of a query
			
			while (rows.next()) {
				
				// Gets the column values based on class type expected
				tempRow = new Object[] {rows.getInt(1), rows.getString(2)};
			
				// Adds the row of data to the end of the model
				dTableModel.addRow(tempRow);
			
			}
			
		} catch (SQLException ex) {
		
			// String describing the error
			System.out.println("SQLException: " + ex.getMessage());
			
			// Vendor specific error code
			System.out.println("VendorError: " + ex.getErrorCode());
		
		} catch (ClassNotFoundException e) {
			
			// Executes if the driver can't be found 
			e.printStackTrace();
		}
	
		// Create a JTable using the custom DefaultTableModel
		JTable table = new JTable(dTableModel);
		
		// Increase the font size for the cells in the table
		table.setFont(new Font("Serif", Font.PLAIN, 20));
		
		// Increase the size of the cells to allow for bigger fonts
		table.setRowHeight(table.getRowHeight() + 10);
		
		// Allows the user to sort the data
		table.setAutoCreateRowSorter(true);

		// If you want to right justify column
		// TableColumn tc = table.getColumn("TTRC");
		// RightTableCellRendered rightRendered = new RightTableCellRendered();
		// tc.setCellRendered(rightRendered);
		
		// Disable auto resizing
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		// Set the width for the columns
		TableColumn col1 = table.getColumnModel().getColumn(0);
		col1.setPreferredWidth(150);
		
		TableColumn col2 = table.getColumnModel().getColumn(1);
		col2.setPreferredWidth(260);
		
		
		// Change justification of column to Center
		
		TableColumn tc = table.getColumn("id");
		CenterTableCellRenderer centerRenderer = new CenterTableCellRenderer();
		tc.setCellRenderer(centerRenderer);
		
		tc = table.getColumn("Nazwa");
		centerRenderer = new CenterTableCellRenderer();
		tc.setCellRenderer(centerRenderer);
		
		JScrollPane scrollPane = new JScrollPane(table);
		frame.add(scrollPane, BorderLayout.CENTER);
		frame.setSize(800, 500);
		frame.setVisible(true);
				
	}
	
}


// How to change justification to the right

class RightTableCellRenderer extends DefaultTableCellRenderer {
	
	public RightTableCellRenderer() {
		
		setHorizontalAlignment(JLabel.RIGHT);
	}
}


// Change justification to the center

class CenterTableCellRenderer extends DefaultTableCellRenderer {
	
	public CenterTableCellRenderer() {
		
		setHorizontalAlignment(JLabel.CENTER);
	}
}
