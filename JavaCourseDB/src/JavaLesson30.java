import javax.swing.*;
import javax.swing.JSpinner.DateEditor;
import javax.swing.event.*;

import java.awt.*;

import java.util.Calendar;
import java.util.Date;

public class JavaLesson30 extends JFrame {
	
	JLabel nameLabel, streetLabel, sexLabel, stateLabel, dateLabel, ageLabel, optionLabel, aboutLabel;
	JTextField nameText, streetText;
	JRadioButton maleRadio, femaleRadio;
	ButtonGroup sexGroup;
	JComboBox stateList;
	JSpinner dateSpin;
	JSlider ageSlider;
	JCheckBox morningCheck, afterNCheck, eveningCheck;
	JTextArea aboutYou;
	
	public static void main(String[] args) {
		
		new JavaLesson30();
	}
	
	public JavaLesson30() {
		
		// Create the frame, position it and handle closing it
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("RandomLayout");
		
		JPanel thePanel = new JPanel();
		thePanel.setLayout(new GridBagLayout());
		
		nameLabel = new JLabel(" Name: ");
		addComp(thePanel, nameLabel, 0, 0, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);
		
		nameText = new JTextField(30);
		addComp(thePanel, nameText, 1, 0, 2, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);
		
		streetLabel = new JLabel(" Street: ");
		addComp(thePanel, streetLabel, 0, 1, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);
		
		streetText = new JTextField(30);
		addComp(thePanel, streetText, 1, 1, 2, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);
		
		// Create a set of radio buttons and wrap them in a group
		Box sexBox = Box.createVerticalBox();
		maleRadio = new JRadioButton("Male");
		femaleRadio = new JRadioButton("Female");
		ButtonGroup sexGroup = new ButtonGroup();
		sexGroup.add(maleRadio);
		sexGroup.add(femaleRadio);
		sexBox.add(maleRadio);
		sexBox.add(femaleRadio);
		sexBox.setBorder(BorderFactory.createTitledBorder("Sex"));
		addComp(thePanel, sexBox, 3, 0, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);
		
		// Create a flow layout panel and space components 10px apart
		JPanel statePanel = new JPanel();
		statePanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 0));
		
		stateLabel = new JLabel("State:");
		statePanel.add(stateLabel);
		
		// Create a combo box that will hold the state
		String[] states = {"NY", "LA", "PH", "OH", "KK"};
		stateList = new JComboBox(states);
		statePanel.add(stateList);
		
		dateLabel = new JLabel("Appointment Date: ");
		statePanel.add(dateLabel);
		
		Date todaysDate = new Date();
		
		// Create a date spinner and set default to today, no minimum and maximum
		// Increment the days on button presses
		// Can also increment YEAR, MONTH, OR DAY_OF_MONTH
		dateSpin = new JSpinner(new SpinnerDateModel(todaysDate, null, null, Calendar.DAY_OF_MONTH));
		
		// DateEditor is an editor that handles displaying and editing the dates
		JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(dateSpin, "dd/MM/yy");
		dateSpin.setEditor(dateEditor);
		statePanel.add(dateSpin);
		
		ageLabel = new JLabel("Age: 50");
		statePanel.add(ageLabel);
		
		// Creates a slider with a min value of 1 thru 100 and an initial value of 50
		ageSlider = new JSlider(1, 99, 50);
		
		// Create an instance of ListenForEvents to handle of events
		ListenForSlider lForSlider = new ListenForSlider();
		ageSlider.addChangeListener(lForSlider);
		statePanel.add(ageSlider);
		
		addComp(thePanel, statePanel, 1, 2, 4, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);
		
		//create a check box and assign them to a group
		Box optionBox = Box.createVerticalBox();
		morningCheck = new JCheckBox("Morning");
		afterNCheck = new JCheckBox("Afternoon");
		eveningCheck = new JCheckBox("Evening");
		
		optionBox.add(morningCheck);
		optionBox.add(afterNCheck);
		optionBox.add(eveningCheck);
		optionBox.setBorder(BorderFactory.createTitledBorder("Time of Day"));
		addComp(thePanel, optionBox, 1, 3, 2, 1, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE);
		
		// Create a text area that is 6 columns high and 40 long
		aboutYou = new JTextArea(6, 40);
		
		// Set the default text for the text area
		aboutYou.setText("Tell us something about you");
		
		// If text doesn't fit on a line, jump to the next
		aboutYou.setLineWrap(true);
		
		// Makes sure that words stay intact if a line wrap occurs
		aboutYou.setWrapStyleWord(true);
		
		// Adds scroll bar to the text area
		JScrollPane scrollbar1 = new JScrollPane(aboutYou, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		addComp(thePanel, scrollbar1, 2, 3, 3, 1, GridBagConstraints.EAST, GridBagConstraints.NONE);
		
		this.add(thePanel);
		
		// Adjusts the size of the frame to best work for the components
		this.pack();
		this.setVisible(true);
		
		// Define if the user can resize the frame (true by default)
		this.setResizable(false);
		
	}
	
	// Sets the rules for a component destined for a GridBagLayout and then adds it
	private void addComp(JPanel thePanel, JComponent comp, int xPos, int yPos, int compWidth, int compoHeight, int place, int stretch) {
		
		GridBagConstraints gridConstraints = new GridBagConstraints();
		
		gridConstraints.gridx = xPos;
		gridConstraints.gridy = yPos;
		gridConstraints.gridwidth = compWidth;
		gridConstraints.gridheight = compoHeight;
		gridConstraints.weightx = 100;
		gridConstraints.weighty = 100;
		gridConstraints.insets = new Insets(5, 5, 5, 5);
		gridConstraints.anchor = place;
		gridConstraints.fill = stretch;
		
		thePanel.add(comp, gridConstraints);
	}
	
	
	// Implements ActionListener so it can react to events on components
	private class ListenForSlider implements ChangeListener {
		
		// Called when the spinner is changed
		public void stateChanged(ChangeEvent e) {
			
			// Check if the source of the event was the button
			if (e.getSource() == ageSlider) {
				
				// Change the value for the label next to the spinner
				ageLabel.setText("Age: " + ageSlider.getValue());
			}
		}
	}
}