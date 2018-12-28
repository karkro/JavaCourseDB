import javax.swing.*;
import java.awt.event.*;




public class JavaLesson24 extends JFrame {

	JComboBox favoriteShows;
	JButton button1;
	String InfoOnComponent = "";
	
	
	public static void main(String[] args) {
		
		new JavaLesson24();
	}
	
	public JavaLesson24() {
		
		this.setSize(400, 400);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("My Fourth Frame");
		
		JPanel thePanel = new JPanel();
		
		// Create an array that will be added to the combo box
		String[] shows = {"Breaking Bed", "Life on Mars", "Doctor Who"};
		
		// Create a combo box and add the array of shows
		favoriteShows = new JComboBox(shows);
		
		// Add an item to the combo box
		favoriteShows.addItem("Terminator");
		
		thePanel.add(favoriteShows);
		
		// Create a button
		button1 = new JButton("Get Answer");
		ListenForButton lForButton = new ListenForButton();
		button1.addActionListener(lForButton);
		thePanel.add(button1);
		
		
		this.add(thePanel);
				
		this.setVisible(true);
		
		// Add an item to a combo box at index 1
		favoriteShows.insertItemAt("Dexter", 1);

        // Only show 3 items at a time
		favoriteShows.setMaximumRowCount(3);
		
		// Remove the item named Dexter
		//favoriteShows.removeItem("Dexter");
		
		// Remove the item at index 1
		//favoriteShows.removeItemAt(1);
		
		// Remove all items
		// favoriteShows.removeAllItems();
		
		
	}
	
	
	private class ListenForButton implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == button1) {
				
				favoriteShows.setEditable(true);
				
				// Get item at index 0
				InfoOnComponent += "Item at 0: " + favoriteShows.getItemAt(0) + "\n";
				
				// Get the number of items in the combo box
				InfoOnComponent += "Num of Shows: " + favoriteShows.getItemCount() + "\n";
				
				// Get the index for the selected item
				InfoOnComponent += "Selected ID: " + favoriteShows.getSelectedIndex() + "\n";
				
				// Get the value for the selected item
				InfoOnComponent += "Selected Value: " + favoriteShows.getSelectedItem() + "\n";
				
				// Find out if the values in the combo box are editable
				InfoOnComponent += "Are Editable: " + favoriteShows.isEditable() + "\n";
				
				JOptionPane.showMessageDialog(JavaLesson24.this, InfoOnComponent, "Information", JOptionPane.INFORMATION_MESSAGE);
				
				InfoOnComponent = "";
			}
		}
	}
	

}
