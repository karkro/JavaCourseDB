import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.font.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;


import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;

import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class Query02 extends JFrame {

	static JLabel lFirstName, lLastName, lState, lBirth;
	static JTextField tfFirstName, tfLastName, tfState, tfBirth;
	static java.util.Date dateBirth, sqlBirth;
	
	static Object[][] databaseResult;
	static Object[] columns = {"Last Name", "First Name", "State", "Day of birth"};
	
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
			String query = "SELECT first_Name, last_Name, state, birth FROM president";
			ResultSet rows = sqlState.executeQuery(query);
			Object[] tempRow;
			while (rows.next()) {
				tempRow = new Object[] {rows.getString(2), rows.getString(1), rows.getString(3), rows.getDate(4)};
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
		frame.add(scrollPane);

		
		JButton addButton = new JButton("Add record");
		addButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				String sFirstName = "", sLastName = "", sState = "", sBirth = "";
				
				sFirstName = tfFirstName.getText();
				sLastName = tfLastName.getText();
				sState = tfState.getText();
				sBirth = tfBirth.getText();
				
				SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
				
				try {
					dateBirth = dateFormatter.parse(sBirth);
					sqlBirth = new java.sql.Date(dateBirth.getTime());
					
				} catch (ParseException pe) {
					pe.printStackTrace();
				}
				
				Object[] user = {sLastName, sFirstName, sState, sqlBirth};
				dTableModel.addRow(user);
			}
		});
		
		
		JButton delButton = new JButton("Delete Record");
		delButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dTableModel.removeRow(table.getSelectedRow());
			}
		});
		
		lLastName = new JLabel("Last Name");
		lFirstName = new JLabel("First Name");
		lState = new JLabel("State");
		lBirth = new JLabel("Day of Birth");
		tfLastName = new JTextField(15);
		tfFirstName = new JTextField(15);
		tfState = new JTextField(15);
		tfBirth = new JTextField("yyyy-MM-dd", 10);
		
		JPanel mainPanel = new JPanel();
		mainPanel.add(lLastName);
		mainPanel.add(tfLastName);
		mainPanel.add(lFirstName);
		mainPanel.add(tfFirstName);
		mainPanel.add(lState);
		mainPanel.add(tfState);
		mainPanel.add(lBirth);
		mainPanel.add(tfBirth);
		mainPanel.add(addButton);
		mainPanel.add(delButton);
		
		frame.add(mainPanel, BorderLayout.SOUTH);
		
		frame.setSize(1200, 500);
		frame.setVisible(true);
		
	}
	
	
}
