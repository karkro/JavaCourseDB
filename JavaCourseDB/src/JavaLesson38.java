import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.font.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

// Needed to track when the user clicks on a table cell
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

// The API for accessing and processing data stored in a database
import java.sql.*;
import java.text.ParseException;

// Allows you to convert from string to date or vice versa
import java.text.SimpleDateFormat;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JOptionPane;

import javax.swing.table.DefaultTableModel;
import javax.swing.text.DateFormatter;


public class JavaLesson38 extends JFrame {

	static JLabel lFirstName, lLastName, lState, lBirthdate;
	static JTextField tfFirstName, tfLastName, tfState, tfBirthdate;
	static java.util.Date dateBirthdate, sqlBirthdate;
	
	static ResultSet rows;
	
	// Holds row values for the table
	static Object[][] databaseResult;
	
	// Holds column names for the table
	static Object[] columns = {"ID", "Last_Name", "First_Name", "State", "Birthdate"};
	
	// DefaultTableModel defines the methods JTable will use
	 // I'm overriding the getColumnClass
	static DefaultTableModel dTableModel = new DefaultTableModel(databaseResult, columns) {
		public Class getColumnClass(int column) {
			Class returnValue;
			
			// Verifying that the column exists (index > 0 && index < number of columns
			if ((column >= 0) && (column < getColumnCount())) {
				returnValue = getValueAt(0, column).getClass();
			} else {
				
				// Returns the class for the item in the column
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
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?useSSL=false", "root", "root");
			
			// Statement objects executes a SQL query
			// createStatement returns a Statement object
			Statement sqlState = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			
			// This is the query I'm sending to the database
			String query = "SELECT pres_id, last_name, first_name, state, birth FROM president";
			
			// A ResultSet contains a table of data representing the results of the query. 
			// It can not be changed and can only be read in one direction
			rows = sqlState.executeQuery(query);
			
			// Temporarily holds the row results
			Object[] tempRow;
			
			// next is used to iterate through the results of a query
			while (rows.next()) {
				tempRow = new Object[] {rows.getInt(1), rows.getString(2), rows.getString(3), rows.getString(4), rows.getDate(5)};
				
				// Add the row of data to the JTable
				dTableModel.addRow(tempRow);
			}
			
		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		
		// Increase the font size for the cells in the table
		table.setFont(new Font("Arial", Font.PLAIN, 12));
		
		// Increase the size of the cells to allow for bigger fonts
		table.setRowHeight(table.getRowHeight() + 5);
		
		// Allows the user to sort the data
		table.setAutoCreateRowSorter(true);
		
		// Adds the table to a scrollpane
		JScrollPane scrollPane = new JScrollPane(table);
		
		// Adds the scrollpane to the frame
		frame.add(scrollPane, BorderLayout.CENTER);
		
		
		// Creates a button that when pressed executes the code in the method actionPerformed
		JButton addButton = new JButton("Add record");
		addButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String sFirstName = "", sLastName = "", sState = "", sBirthdate;
				
				// getText returns the value in the text field
				sFirstName = tfFirstName.getText();
				sLastName = tfLastName.getText();
				sState = tfState.getText();
				sBirthdate = tfBirthdate.getText();
				
				sqlBirthdate = getADate(sBirthdate);
				
				try {
					
					// Moves the database to the row where data will be placed
					rows.moveToInsertRow();
					
					// Update the values in the database
					rows.updateString("first_Name", sFirstName);
					rows.updateString("last_Name", sLastName);
					rows.updateString("state", sState);
					rows.updateDate("birth", (Date) sqlBirthdate);
					
					// Inserts the changes to the row values in the database
					rows.insertRow();
					
					// Directly updates the values in the database
					rows.updateRow();
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
				int presID = 0;
				
				try {
					
					// Go to the last row inserted and get the id
					rows.last();
					presID = rows.getInt(1);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
				Object[] president = {presID, sLastName, sFirstName, sState, sqlBirthdate};
				
				// Add the row of values to the JTable
				dTableModel.addRow(president);
				
			}
		});
		
		
		JButton delButton = new JButton("Delete record");
		delButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				// Will remove which ever row that is selected
				dTableModel.removeRow(table.getSelectedRow());
				
				try {
					
					// Moves the database to the row currently selected
					// getSelectedRow returns the row number for the selected row
					rows.absolute(table.getSelectedRow());
					
					// Deletes the selected row from the database
					rows.deleteRow();
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		
		
		// Define values for my labels
		lLastName = new JLabel("Last Name");
		lFirstName = new JLabel("First Name");
		lState = new JLabel("State");
		lBirthdate = new JLabel("Birthday");
		
		// Define the size of text fields
		tfLastName = new JTextField(15);
		tfFirstName = new JTextField(15);
		tfState = new JTextField(2);
		
		// Set default text and size for text field
		tfBirthdate = new JTextField("yyyy-MM-dd", 10);
		
		// Create a panel to hold editing buttons and fields
		JPanel mainPanel = new JPanel();
		
		// Put components in the panel
		mainPanel.add(lLastName);
		mainPanel.add(tfLastName);
		mainPanel.add(lFirstName);
		mainPanel.add(tfFirstName);
		mainPanel.add(lState);
		mainPanel.add(tfState);
		mainPanel.add(lBirthdate);
		mainPanel.add(tfBirthdate);
		mainPanel.add(addButton);
		mainPanel.add(delButton);		
		
		// Add the component panel to the frame
		frame.add(mainPanel, BorderLayout.SOUTH);
		
		
		// When the user clicks on a cell they'll be able to change the value
		table.addMouseListener(new MouseAdapter() {
			
			public void mouseReleased(MouseEvent me) {
				
				String value = JOptionPane.showInputDialog(null, "Enter Cell Value: ");
				
				// Makes sure a value is changed only if OK is clicked
				if (value != null) {
					table.setValueAt(value, table.getSelectedRow(), table.getSelectedColumn());
				}
				
				
				try {
					
					// Move to the selected row
					rows.absolute(table.getSelectedRow() + 1);
					
					// Get the name of the selected column
					String updateCol = dTableModel.getColumnName(table.getSelectedColumn());
					
					// Previous to Java 1.7 you couldn't use Strings in a Switch
					// If you get an error here it is because you aren't using Java 1.7
					switch (updateCol) {
					
						// Uses a different update method depending on the data type
						case "birth":
							
							sqlBirthdate = getADate(value);
							rows.updateDate(updateCol, (Date) sqlBirthdate);
							
							rows.updateRow();
							break;
							
						default:
							
							rows.updateString(updateCol, value);
							System.out.println("Current row: " + rows.getRow());
							
							rows.updateRow();
							break;
							
					}
					
				} catch (SQLException e1) {
					
					// Commented out so the user can delete rows
					//e1.printStackTrace();
				}
	
				
			}
		});
		
		frame.setSize(1200, 600);
		frame.setVisible(true);
		
	}
	
	
	
	// Will convert from string to date
	public static java.util.Date getADate(String sDate) {
		SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
		try {
			dateBirthdate = dateFormatter.parse(sDate);
			sqlBirthdate = new java.sql.Date(dateBirthdate.getTime());
		} catch (ParseException pe) {
			pe.printStackTrace();
		}
		return sqlBirthdate;
	}
	
	
}
