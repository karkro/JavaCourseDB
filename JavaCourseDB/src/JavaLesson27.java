import javax.swing.*;
import java.awt.event.*;
import java.awt.Dimension;

//Enumerations are used to store related items together
import java.util.Enumeration;
import javax.swing.tree.*;

public class JavaLesson27 extends JFrame {

	JButton button1;
	String outputString = "";
	
	// A Tree contains nodes that can contain other nodes
	JTree theTree;
	
	// If a node holds other nodes it is called a parent node
	// The nodes inside of a parent node are children nodes
	// Nodes on the same level are called siblings
	DefaultMutableTreeNode documents, work, games, emails;
	
	DefaultMutableTreeNode fileSystem = new DefaultMutableTreeNode("C Drive");
	
	
	public static void main(String[] args) {
		
		new JavaLesson27();
		
	}
	
	public JavaLesson27() {
		
		this.setSize(400, 400);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("The Seventh Frame");
		
		JPanel thePanel = new JPanel();
		
		// Create a button
		button1 = new JButton("Get Answer");
		ListenForButton lForButton = new ListenForButton();
		button1.addActionListener(lForButton);
		thePanel.add(button1);
		
		// Create the JTree by passing it the top tree node
		theTree = new JTree(fileSystem);
		
		// Makes sure only one item can be selected at a time
		// By default you can make multiple selections
		theTree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		
		// Only show 8 rows of the tree at a time
		theTree.setVisibleRowCount(10);
		
		// Add the items to the tree documents, work, games
		// We first add the documents parent node
		documents = addFile("Docs", fileSystem);
		
		// Now we add children nodes to the documents parent node
		addFile("Taxes.exl", documents);
		addFile("Story.txt", documents);
		addFile("Schedule.txt", documents);
		
		// Create the emails node and its children		
		emails = addFile("Emails", fileSystem);
		addFile("CallBob.txt", emails);
		
		// Create the work node and its children
		work = addFile("Work Application", fileSystem);
		addFile("Spreadsheet.xlsx", work);
		addFile("Wordprocessor.exe", work);
		
		// Create the games node and its children
		games = addFile("Games", fileSystem);
		addFile("ChickenInvaders.exe", games);
		addFile("PacMan.exe", games);
		
		// Put the tree in a scroll component
		JScrollPane scrollBox = new JScrollPane(theTree);
		
		// Set the size for the JScrollPane so that everything fits
		Dimension d = scrollBox.getPreferredSize();
		d.width = 200;
		scrollBox.setPreferredSize(d);
		
		thePanel.add(scrollBox);
		
		
		this.add(thePanel);
		this.setVisible(true);

	}
	
	private class ListenForButton implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			
			if (e.getSource() == button1) {
				
				// How to get the selected node
				// Returns the last selected no
				Object treeObject = theTree.getLastSelectedPathComponent();
				
				// Cast the Object into a DefaultMutableTreeNode
				DefaultMutableTreeNode theFile = (DefaultMutableTreeNode) treeObject;
				
				// Returns the object stored in this node and casts it to a string
				String treeNode = (String) theFile.getUserObject();
				
				
				outputString += "The selected Node: " + treeNode + "\n";
				
				// Get the number of children this node has
				outputString += "Number of Children: " + theFile.getChildCount() + "\n";
				
				// Get the number of siblings
				outputString += "Number of siblings: " + theFile.getSiblingCount() + "\n";
				
				// Get the parent of this node
				outputString += "Parent: " + theFile.getParent() + "\n";
				
				// Get the next node
				outputString += "The next Node: " + theFile.getNextNode() + "\n";
				
				// Get the previous node
				outputString += "The previous Node: " + theFile.getPreviousNode() + "\n";
				
				// Get the children for the node
				outputString += "Children of Node: \n";
				
				// children() returns an enumeration that contains all the children
				// This for loop will continue to run as long as there are more elements
				// nextElement() returns the next element in the list
				
				for (Enumeration enumValue = theFile.children(); enumValue.hasMoreElements();) {
					
					outputString += enumValue.nextElement() + "\n";
				}
				
				// Get the path from the root
				outputString += "\n Path from root";
				
				// getPath returns an array of TreeNodes
				TreeNode[] pathNodes = theFile.getPath();
				
				// Cycle through the TreeNodes
				for (TreeNode indivNodes : pathNodes) {
					outputString += indivNodes + "\n";
				}
				
				
				JOptionPane.showMessageDialog(JavaLesson27.this, outputString, "Information", JOptionPane.INFORMATION_MESSAGE);
				outputString = "";
			}
		}
	}
	
	private DefaultMutableTreeNode addFile(String fileName, DefaultMutableTreeNode parentFolder) {
		
		// Creates a new node for the tree
		DefaultMutableTreeNode newFile  = new DefaultMutableTreeNode(fileName);
		
		// Add attaches a name to the node
		parentFolder.add(newFile);
		
		// return the new node
		return newFile;
	}
	
	
}