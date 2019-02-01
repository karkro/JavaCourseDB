import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.font.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

// The API for accessing and processing data stored in a database
import java.sql.*;
import java.text.ParseException;

// Allows you to convert from string to date or vice versa
import java.text.SimpleDateFormat;

import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;

public class JavaLesson37 extends JFrame {

	static JLabel lFirstName, lLastName, lVoivodeship, lEmploymentFrom;
	static JTextField tfFirstName, tfLastName, tfVoivodeship, tfEmploymentFrom;
	static java.util.Date EmploymentFromDate, sqlEmploymentFrom;
	
	// Holds row values for the table
	static Object[][] databaseResult;
	
	// Holds column names for the table
	static Object[] columns = {"First Name", "Last Name", "EmploymentFrom", "Voivodeship"};
	
	// DefaultTableModel defines the methods JTable will use 
	// I'm overriding the getColumnClass
	static DefaultTableModel dTableModel = new DefaultTableModel(databaseResult, columns) {
		public Class getColumnClass(int column) {
			
			Class returnValue;
			
			// Verifying that column exists
			if ((column >= 0) && (column < getColumnCount())) {
				returnValue = getValueAt(0, column).getClass();
			} else {
				// Return the class for the item in the column
				returnValue = Object.class;
			}
			
			return returnValue;
		}
	};
	
	// Create a JTable using the custom DefaultTableModel
	static JTable table = new JTable(dTableModel);
	
	
	
	public static void main(String[] args) {
	
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// A connection object is used to provide access to a database
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
			String query = "SELECT firstName, lastName, employmentFrom, voivodeship FROM user";
			
			// A ResultSet contains a table of data representing the result of the query. It can not be changed and can only be read in one direction
			ResultSet rows = sqlState.executeQuery(query);
			
			// Temporarily holds the row results
			Object[] tempRow;
			
			// next is used to iterate through the results of a query
			while (rows.next()) {
				tempRow = new Object[] {rows.getString(1), rows.getString(2), rows.getDate(3), rows.getString(4)};
				dTableModel.addRow(tempRow);
			}
			
		} catch (SQLException ex) {
			
			// String describing the error
			System.out.println("SQLException: " + ex.getMessage());
			
		} catch (ClassNotFoundException e) {
			
			// Executes if the driver can't be found
			e.printStackTrace();
		}
		
		// Increase the font size for the cells in the table
		table.setFont(new Font("Arial", Font.PLAIN, 12));
		
		// Allows the user to sort the data
		table.setAutoCreateRowSorter(true);
		
		// Increase the size of the cells to allow for bigger fonts
		table.setRowHeight(table.getRowHeight() + 5);
		
		// Adds the table to a scrollPane
		JScrollPane scrollPane = new JScrollPane(table);
		
		// Adds the scrollPane to the frame
		frame.add(scrollPane, BorderLayout.CENTER);
		
		// Creates a button that when pressed executes the code in the method actionPerformed
		JButton addButton = new JButton("Add record");
		addButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String sFirstName = "", sLastName = "", sEmploymentFrom = "", sVoivodeship = "";
				
				// getText returns the value in the textField
				sFirstName = tfFirstName.getText();
				sLastName = tfLastName.getText();
				sEmploymentFrom = tfEmploymentFrom.getText();
				sVoivodeship = tfVoivodeship.getText();
				
				// Will convert from string to date
				SimpleDateFormat dateFormatter = new SimpleDateFormat("yyy-MM-dd");
				
				try {
					EmploymentFromDate = dateFormatter.parse(sEmploymentFrom);
					sqlEmploymentFrom = new java.sql.Date(EmploymentFromDate.getTime());
				
				} catch (ParseException pe) {
					pe.printStackTrace();
				}
				
				Object[] user = {sFirstName, sLastName, sqlEmploymentFrom, sVoivodeship};
				dTableModel.addRow(user);
				
			}
		});
		
		JButton delButton = new JButton("Delete record");
		delButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
				// Will remove which ever row that is selected
				dTableModel.removeRow(table.getSelectedRow());
			}
		});
		
		// Defines values for my labels
		lFirstName = new JLabel("First Name");
		lLastName = new JLabel("Last Name");
		lEmploymentFrom = new JLabel("Employment from");
		lVoivodeship = new JLabel("Voivodeship");
		
		// Define the size of the text fields
		tfFirstName = new JTextField(15);
		tfLastName = new JTextField(15);
		tfVoivodeship = new JTextField(15);
		
		// Set default text and size for the text field
		tfEmploymentFrom = new JTextField("yyyy-MM-dd", 10);
		
		// Create a panel to hold editing buttons and fields
		JPanel mainPanel = new JPanel();
		
		
		// Put components in the panel
		mainPanel.add(lFirstName);
		mainPanel.add(tfFirstName);
		mainPanel.add(lLastName);
		mainPanel.add(tfLastName);
		mainPanel.add(lEmploymentFrom);
		mainPanel.add(tfEmploymentFrom);
		mainPanel.add(lVoivodeship);
		mainPanel.add(tfVoivodeship);
		mainPanel.add(addButton);
		mainPanel.add(delButton);
		
		// Add the component panel to the frame
		frame.add(mainPanel, BorderLayout.SOUTH);
		frame.setSize(1200, 500);
		frame.setVisible(true);
		
	}
}
