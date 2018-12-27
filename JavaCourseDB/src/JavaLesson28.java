import javax.swing.*;

import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

public class JavaLesson28 extends JFrame {
	
	JButton button1, button2, button3, button4, button5;
	String outputString = "";
	
	public static void main(String[] args) {
		
		new JavaLesson28();
		
	}
	
	public JavaLesson28() {
		
		// Create the Frame, position it and handle closing it
		
		this.setSize(400, 400);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("The Eighth Frame");
		
		/* CREATA A FLOW LAYOUT DEFAULT
		JPanel thePanel = new JPanel();
		
		// Define the flow layout alignment
		// FlowLayout.RIGHT, FlowLayout.CENTER
		// You can also define the pixels that separate the components
		// FlowLayout(alignment, hor gap, ver gap)
		
		thePanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 30, 30));
		
		button1 = new JButton("Button1");
		thePanel.add(button1);
		
		END FLOW LAYOUT */
		
		
		/* BORDER LAYOUT
		
		JPanel thePanel = new JPanel();
		
		thePanel.setLayout(new BorderLayout());
		
		// Create buttons
		button1 = new JButton("Button 1");
		button2 = new JButton("Button 2");
		button3 = new JButton("Button 3");
		button4 = new JButton("Button 4");
		button5 = new JButton("Button 5"); 
		
		// If you put components in the same space the last one in stays and 
		// everything else goes EX.
		  
		// thePanel.add(button1, BorderLayout.NORTH);
		// thePanel.add(button2, BorderLayout.NORTH);
		// Only button2 shows
		 
		
		thePanel.add(button1, BorderLayout.NORTH);
		thePanel.add(button2, BorderLayout.SOUTH);
		thePanel.add(button3, BorderLayout.WEST);
		thePanel.add(button4, BorderLayout.EAST);
		thePanel.add(button5, BorderLayout.CENTER);
		
		// If you put want more than one component to show up in the same
		// part of a border layout put them in a panel and then add the panel
		// to the border layout panel
		
		JPanel thePanel2 = new JPanel();
		
		thePanel2.add(button1);
		thePanel2.add(button2);
		
		thePanel.add(thePanel2, BorderLayout.NORTH);
		
		END BORDER LAYOUT */
		
		
		// BOX LAYOUT
		
		Box theBox = Box.createHorizontalBox();
		
		button1 = new JButton("Button 1");
		button2 = new JButton("Button 2");
		button3 = new JButton("Button 3");
		button4 = new JButton("Button 4");
		
		// You can also use Box theBox = Box.createVerticalBox();
		theBox.add(Box.createHorizontalStrut(4));
		theBox.add(button1);
		theBox.add(Box.createHorizontalStrut(4));
		theBox.add(button2);
		
		// A rigid area gives you the option to space using
		// horizontal and vertical spacing
		theBox.add(Box.createRigidArea(new Dimension(30, 20)));
		theBox.add(button3);
		
		// When you use a glue you position the component as far apart as possible
		// while remaining on the screen
		// There is also a createVerticalGlue
		
		theBox.add(Box.createHorizontalGlue());
		theBox.add(button4);
		theBox.add(Box.createHorizontalStrut(4));
		
		this.add(theBox);
		this.setVisible(true);

	}

}