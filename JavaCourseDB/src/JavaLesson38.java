import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.font.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.sql.*;
import java.text.ParseException;
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
	static Object[][] databaseResult;
	static Object[] columns = {"ID", "Last Name", "First Name", "State", "Birthdate"};
	
	static DefaultTableModel dTableModel = new DefaultTableModel(databaseResult, columns) {
		public Class getColumnClass(int column) {
			Class returnValue;
			if ((column >= 0) && (column < getColumnCount())) {
				returnValue = getValueAt(0, column).getClass();
			} else {
				returnValue = Object.class;
			}
			return returnValue;
		}
	};
	
	static JTable table = new JTable(dTableModel);
	
	public static void main(String[] args) {
		
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Connection conn = null;
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Test?useSSL=false", "karkro", "karolkrol83");
			Statement sqlState = conn.createStatement();
			String query = "SELECT id_pres, last_Name, first_Name, state, birth FROM president";
			rows = sqlState.executeQuery(query);
			Object[] tempRow;
			while (rows.next()) {
				tempRow = new Object[] {rows.getInt(1), rows.getString(2), rows.getString(3), rows.getString(4), rows.getDate(5)};
				dTableModel.addRow(tempRow);
			}
			
			
		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		table.setFont(new Font("Arial", Font.PLAIN, 12));
		table.setRowHeight(table.getRowHeight() + 5);
		table.setAutoCreateRowSorter(true);
		JScrollPane scrollPane = new JScrollPane(table);
		frame.add(scrollPane, BorderLayout.CENTER);
		
		JButton addButton = new JButton("Add record");
		addButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String sFirstName = "", sLastName = "", sState = "", sBirthdate;
				
				sFirstName = tfFirstName.getText();
				sLastName = tfLastName.getText();
				sState = tfState.getText();
				sBirthdate = tfBirthdate.getText();
				
				
				
				
				
			}
		});
		
		
		
		
		
		frame.setSize(1200, 600);
		frame.setVisible(true);
		
		
		
		
		
		
	}
	
	
	public static java.util.Date getAData(String sDate) {
		
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
