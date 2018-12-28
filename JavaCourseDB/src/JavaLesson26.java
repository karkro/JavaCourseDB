// Editor for JSpinner that allows easy incrementing of dates
import javax.swing.*;

import java.awt.event.*;

// Object that allows me to use height and width units
import java.awt.Dimension;

// Used to get todays date to use with Calendar
import java.util.Date;

// Editor for JSpinner that allows easy incrementing of dates
import javax.swing.SpinnerDateModel;

// Calendar provides methods that make it easy to work with dates
import java.util.Calendar;

public class JavaLesson26 extends JFrame {

	JButton button1;
	JSpinner spinner1, spinner2, spinner3, spinner4;
	String outputString = "";
	
	public static void main(String[] args) {
		
		new JavaLesson26();
	}
	
	public JavaLesson26() {
		
		this.setSize(400, 400);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("The Sixth Frame");
		
		JPanel thePanel = new JPanel();
		
		button1 = new JButton("Get Answer");
		ListenForButton lForButton = new ListenForButton();
		button1.addActionListener(lForButton);
		thePanel.add(button1);
		
		// Create a basic 1-9 number spinner
		spinner1 = new JSpinner();
		thePanel.add(spinner1);
		
		// Create a spinner with initial number, starting number, max number, increment with each click
		spinner2 = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));
		thePanel.add(spinner2);
		
		// Create a spinner using default values
		String[] weekdays = {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};
		spinner3 = new JSpinner(new SpinnerListModel(weekdays));
		
		// Set the size for the spinner so that everything fits
		Dimension d = spinner3.getPreferredSize();
		d.width = 80;
		spinner3.setPreferredSize(d);
		thePanel.add(spinner3);
		
		// Get todays date
		Date todaysDate = new Date();
		
		// Create a date spinner and set default to today, no minimum or max
		// Increment the days on button presses
		// Can also increment Year, Month or day_of_month
		spinner4 = new JSpinner(new SpinnerDateModel(todaysDate, null, null, Calendar.DAY_OF_MONTH));
		
		// DateEditor is an editor that handles displaying and editing the dates
		JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(spinner4, "dd.MM.yy");
		spinner4.setEditor(dateEditor);
		thePanel.add(spinner4);
		
		// You can add a change listener with Spinners as well
		// ListenForSpinner lForSpinner = new ListenForSpinner();
		// Tell Java that you want to be alerted when an event occurs on the spinner
		// spinner4.addChangeListener(lForSpinner);
		
		this.add(thePanel);
		this.setVisible(true);
		
	}
	
	private class ListenForButton implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == button1) {
				
				outputString += "Spinner 1 value: " + spinner1.getValue() + "\n";
				outputString += "Spinner 2 value: " + spinner2.getValue() + "\n";
				outputString += "Spinner 3 value: " + spinner3.getValue() + "\n";
				outputString += "Spinner 4 value: " + spinner4.getValue() + "\n";
				
				JOptionPane.showMessageDialog(JavaLesson26.this, outputString, "Information", JOptionPane.INFORMATION_MESSAGE);
				outputString = "";
			}
		}
	}
	
	
	
}
