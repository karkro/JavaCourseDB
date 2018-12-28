import javax.swing.*;

import java.awt.event.*;


public class JavaLesson25 extends JFrame {

	JButton button1;
	String infoOnComponent;
	JList favoriteMovies, favoriteColors;
	DefaultListModel defListModel = new DefaultListModel();
	JScrollPane scroller;
	
	public static void main(String[] args) {
		
		new JavaLesson25();
		
	}
	
	public JavaLesson25() {
		
		this.setSize(400, 400);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("My Fifth Frame");
		
		JPanel thePanel = new JPanel();
		
		// Create a button
		button1 = new JButton("Get Answer");
		ListenForButton lForButton = new ListenForButton();
		button1.addActionListener(lForButton);
		thePanel.add(button1);
		
		String[] movies = {"Matrix", "Minority Report", "Django"};
		
		// Create a ListBox
		favoriteMovies = new JList(movies);
		
		// Define a height and width of each cell
		favoriteMovies.setFixedCellHeight(30);
		favoriteMovies.setFixedCellWidth(150);
		
		// Define how many selections can be made
		// MULTIPLE_INTERVAL_SELECTION: Select what ever you want
		// SINGLE SELECTION: Select only one
		// SINGLE_INTERVAL_SELECTION: Select as many as you want if in order
		favoriteMovies.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		
		// All the methods for lists
			/*
			 * getSelectedIndex() returns the index for the first selected item
			 * getSelectedIndexes() returns every selection in a list
			 * getSelectedValue() returns the value of the first selected
			 * getSelectedValues() returns an array of all values
			 * isSelectedIndex() returns true if index is selected
			 */
		
		// You can't change items in a list unless you store the items ina a DefaultListModel
		String[] colors = {"Black", "Blue", "Red", "Orange", "White"};
		
		// How to load a String array into DefaultListModel
		for (String color : colors) {
			defListModel.addElement(color);
		}
		
		// Add item named Purple to index number 2
		defListModel.add(2, "Purple");
		
		// Create a List Box filled with items in the DefaultListModel
		favoriteColors = new JList(defListModel);
		
		// ONly display 4 items at a time
		favoriteColors.setVisibleRowCount(4);
		
		// Create a ScrollBar panel to hold the list box
		scroller = new JScrollPane(favoriteColors, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		// Define the height and width of each cell
		favoriteColors.setFixedCellHeight(30);
		favoriteColors.setFixedCellWidth(150);
		
		thePanel.add(favoriteMovies);
		
		// You add the scroll bar to the container, not the list
		thePanel.add(scroller);

		this.add(thePanel);
		this.setVisible(true);		
		
	}
	
	
	private class ListenForButton implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			
			if (e.getSource() == button1) {
				
				// Contains returns true if the item is in the list
				if (defListModel.contains("Black")) infoOnComponent += "\nBlack is here\n";
				
				// Check if the list isn't empty 
				if (!defListModel.isEmpty()) infoOnComponent += "Isn't empty\n";
				
				// Return the number of items in the DefaultListModel
				infoOnComponent += "Elements in the list " + defListModel.size() + "\n";
				
				// Return the last element in the list
				infoOnComponent += "Last element: " + defListModel.lastElement() + "\n";
				
				// Return the first element in the list
				infoOnComponent += "First element: " + defListModel.firstElement() + "\n";
				
				// Return the item in index 1
				infoOnComponent += "In index 1: " + defListModel.get(1) + "\n\n";
				
				// Remove the item in index 0
				defListModel.remove(0);
				
				// Remove the item named Short
				defListModel.removeElement("Short");
				
				// Create an array filled with the list items
				Object[] arrayOfList = defListModel.toArray();
				
				// Iterate through the array
				for (Object color : arrayOfList) {
					infoOnComponent += color + "\n";
				}
				JOptionPane.showMessageDialog(JavaLesson25.this, infoOnComponent, "Information", JOptionPane.INFORMATION_MESSAGE);
				infoOnComponent = "";
			}
		}
	}
	
	
	
	
	
}
