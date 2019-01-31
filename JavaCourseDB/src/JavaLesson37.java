import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.font.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.sql.*;
import java.text.ParseException;
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
	
	static Object[][] databaseResult;
	static Object[] columns = {"First Name", "Last Name", "EmploymentFrom", "Voivodeship"};
	
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
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Test?useSSL=false", "root", "karolkrol83");
			Statement sqlState = conn.createStatement();
			String query = "SELECT firstName, lastName, employmentFrom, voivodeship FROM user2";
			ResultSet rows = sqlState.executeQuery(query);
			Object[] tempRow;
			while (rows.next()) {
				tempRow = new Object[] {rows.getString(1), rows.getString(2), rows.getDate(3), rows.getString(4)};
				dTableModel.addRow(tempRow);
			}
			
		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		table.setFont(new Font("Arial", Font.PLAIN, 12));
		table.setAutoCreateRowSorter(true);
		table.setRowHeight(table.getRowHeight() + 5);
		
		JScrollPane scrollPane = new JScrollPane(table);
		frame.add(scrollPane, BorderLayout.CENTER);
		
		JButton addButton = new JButton("Add record");
		addButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String sFirstName = "", sLastName = "", sEmploymentDate = "", sVoivodeship = "";
				sFirstName = tfFirstName.getText();
				sLastName = tfLastName.getText();
				sEmploymentFrom = tfEmploymentFrom.getText();
				sVoivodeship = tfVoivodeship.getText();
				
				SimpleDateFormat dateFormatter = new SimpleDateFormat("yyy-MM-dd");
				
				try {
					EmploymentFromDate = dateFormatter.parse(sEmploymentDate);
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
				dTableModel.removeRow(table.getSelectedRow());
			}
		});
		
		lFirstName = new JLabel("First Name");
		lLastName = new JLabel("Last Name");
		lEmploymentFrom = new JLabel("Employment from");
		lVoivodeship = new JLabel("Voivodeship");
		
		
		
		frame.setSize(800, 600);
		frame.setVisible(true);
		
	}
}