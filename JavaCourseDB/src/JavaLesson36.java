import java.awt.BorderLayout;
import java.awt.font.*;
import java.awt.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class JavaLesson36 {

	static Object[][] databaseInfo;
	static Object[] columns = {"Year", "PlayerID", "Name", "TTRC", "Team", "Salary", "CPR", "POS"};
	static ResultSet rows;
	static ResultSetMetaData metaData;
	static DefaultTableModel dTableModel = new DefaultTableModel(databaseInfo, columns) {
		public Class getColumnClass(int column) {
			Class returnValue;
			if ((column >= 0) && (column < getColumnCount())) {
				returnValue = getValueAt(0, column).getClass();
			}
		}
	}
	
	public static void main(String[] args) {
		
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Connection conn = null;
		
		try {
		
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/lahman591", root, karolkrol83);
			Statement sqlState = conn.createStatement();
			
			String selectStuff = "select b.yearID, b.playerID, m.nameLast, m.nameFirst, b.teamID AS Team, s.salary AS Salary, f.POS AS POS FROM Batting b, Master m, Salaries s, TOTBYR t, Fielding f WHERE b.playerID = m.playerID AND t.playerID = m.playerID AND t.yearID = 2010 AND b.yearID = t.yearID AND s.playerID = b.playerID AND s.yearID = b.yearID AND b.playerID = f.playerID AND b.playerID = t.playerID GROUP BY b.playerID";
			
			rows  = sqlState.executeQuery(selectStuff);
			
			// int numOfCol;
			
			// metaData = rows.getMetaData();
			
			// numOfCol = metaData.getColumnCount();
			
			Object[] tempRow;
			
			while (rows.next()) {
				tempRow = new Object[] {rows.getInt(1), rows.getString(2), rows.getString(3), rows.getDouble(4), rows.getString(5), rows.getInt(6), rows.getDouble(7)
			}
			
			
		}
	}
}
