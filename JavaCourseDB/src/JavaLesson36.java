import java.awt.BorderLayout;
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
	static Object[] columns = {"Year", "PlayerID", "Name", "TTRC", "Team", "Salary", "CPR", "POS"};
	
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
			conn = DriverManager.getConnection("jdbc:mysql://localhost/lahman591", root, karolkrol83);
			
			// Statement objects executes a SQL query
			// createStatement returns a Statement object
			Statement sqlState = conn.createStatement();
			
			// This is the query I'm sending to the database
			String selectStuff = "select b.yearID, b.playerID, m.nameLast, m.nameFirst, b.teamID AS Team, s.salary AS Salary, f.POS AS POS FROM Batting b, Master m, Salaries s, TOTBYR t, Fielding f WHERE b.playerID = m.playerID AND t.playerID = m.playerID AND t.yearID = 2010 AND b.yearID = t.yearID AND s.playerID = b.playerID AND s.yearID = b.yearID AND b.playerID = f.playerID AND b.playerID = t.playerID GROUP BY b.playerID";
			
			
			// A ResultSet contains a table of data representing the result
			// of the query. It can not be changed and can only be read in the direction
			
		
			int numOfCol;
			 
			// Retrieves the number, types and properties of the Query Results
			 
			metaData = rows.getMetaData();
			 
			
			
			Object[] tempRow;
			
			while (rows.next()) {
				tempRow = new Object[] {rows.getInt(1), rows.getString(2), rows.getString(3), rows.getDouble(4), rows.getString(5), rows.getInt(6), rows.getDouble(7)
			}
			
			
		}
	}
}
