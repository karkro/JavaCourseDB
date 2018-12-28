// Swing allows you to create Graphical User Interface
// You need the Swing library to create GUI interfaces

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.*;


// You must extend the JFrame class to make a frame

public class JavaLesson20 extends JFrame {

	public static void main(String[] args) {
		
		new JavaLesson20();
	}
	
	public JavaLesson20() {
		
		// Define the size of the frame
		this.setSize(400, 400);
		
		// Opens the frame in the middle of the screen
		// You could also define position based on a component
		// this.setLocationRelativeTo(null)
		
		// Toolkit is the super class for the Abstracat Window Toolkit
		// It allows us to ask question of the OS
		Toolkit tk = Toolkit.getDefaultToolkit();
		
		// A Dimension can hold the width and height of a component
		// getScreenSize returns the size of the screen
		Dimension dim = tk.getScreenSize();
		
		// dim.width returns the width of the screen
		// this.getWidth returns the width of the frame you are making
		int xPos = (dim.width) - (this.getWidth() + 100);
		int yPos = (dim.height / 3) - (this.getHeight() / 2);
		
		// You could also define th x, y position of the frame
		this.setLocation(xPos, yPos);
		
		// Define how the user exits the program
		// This closes when they click the close button
		
		// Define if the user can resize the frame(true by default)
		this.setResizable(false);
		
		// Define how the frame exits (Click the close button)
		// Without this Java will eventually close the app
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Define the title for the frame
		this.setTitle("My First Frame");
		
		// The JPanel contains all of the components for your frame
		JPanel thePanel = new JPanel();
		
		// HOW TO CREATE A LABEL WITH ITS TEXT- ----------------------------------------------------------------
		JLabel label1 = new JLabel("Tell me something");
		
		// How to change the text for the label
		label1.setText("New Text");
		
		// How to create a tool tip for the label
		label1.setToolTipText("Doesn't do anything");
		
		// How to add the label to the panel
		thePanel.add(label1);
		
		
		// HOW TO CREATE A BUTTON ------------------------------------------------------------------------------
		JButton button1 = new JButton("Send");
		
		// How to hide the button border (default true)
		// button1.setBorderPainted(false);
		
		// How to hide the button background (default true)
		// button1.setContentAreaFilled(false);
		
		// How to change the text for the label
		button1.setText("New Button");
		
		// How to create a tool tip for the label
		button1.setToolTipText("It's a button");
		
		// How to add the button to the panel
		thePanel.add(button1);
		
		
		// HOW TO CREATE A TEXTFIELD --------------------------------------------------------------------------
		JTextField textField1 = new JTextField("Type here", 15);
		
		// Change the size of the text field
		textField1.setColumns(10);
		
		// Change the initial value of the text field
		textField1.setText("Type again");
		
		// Change the tool tip for the text field
		textField1.setToolTipText("It's a field");	
		thePanel.add(textField1);
		
		// HOW TO CREATE A TEXT AREA -------------------------------------------------------------------------
		JTextArea textArea1 = new JTextArea(15, 20);
		
		// Set the default text for the text area
		textArea1.setText("Just a whole bunch of text that is important");
		
		// If text doesn't fit on a line, jump to the next
		textArea1.setLineWrap(true);
		
		// Makes sure that words stay intact if a line wrap occurs
		textArea1.setWrapStyleWord(true);
		
		// Gets the number of newlines in the text
		int numOfLines = textArea1.getLineCount();
		
		// Appends text after the current text
		textArea1.append("\nNumber of lines: " + numOfLines);
		
		// ADDS SCROLL BARS TO THE TEXT AREA ----------------------------------------------------------------
		JScrollPane scrollbar1 = new JScrollPane(textArea1, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		// Other options: VERTICAL_SCROLLBAR_ALWAYS, VERTICAL_SCROLLBAR_NEVER
		
		thePanel.add(scrollbar1);
		
		thePanel.add(textArea1);
		
		// Add the panel to the frame
		this.add(thePanel);
		
		// Makes the frame show on the screen
		this.setVisible(true);
		
		// Gives focus to the textfield
		textField1.requestFocus();
		
		// You can also request focus for the text area
		textArea1.requestFocus();

	}
}
