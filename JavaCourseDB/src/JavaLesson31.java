import javax.swing.*;
import java.io.*;

public class JavaLesson31 extends JFrame {

	static String filePath, parentDirectory;
	static File randomDir, randomFile, randomFile2;
	
	public static void main(String[] args) {
		
		// Creates a file object in memory
		randomDir = new File("/Users/karkro/git2/JavaLesson31/");
		
		// Make a directory
		randomDir.mkdir();
		
		// Make a file named random.txt
		randomFile = new File("random.txt");
		
		// Make a file and define where to save it in the file system
		randomFile2 = new File("/Users/karkro/git2/JavaLesson31/random.txt");
		
		// createNewFile and getCanonicalPath have to be called in a try block to catch IOException
		
		try {
			
			// createNewFile creates the file in the file system
			randomFile.createNewFile();
			randomFile2.createNewFile();
			
			// Returns the path for the file
			filePath = randomFile.getCanonicalPath();
		} catch (IOException e) {
			
			// You have to catch the IOException
			e.printStackTrace();
		}
		
		// Check to see if the file exists in the current directory
		if (randomFile.exists()) {
			System.out.println("File exists");
			System.out.println("File Readable " + randomFile.canRead());
			System.out.println("File Writable " + randomFile.canWrite());
			System.out.println("File Location " + filePath);
			System.out.println("File Name " + randomFile.getName());
			
			// Since you created the file without defining a path this returns null
			System.out.println("Parent directory " + randomFile.getParent());
			
			// This returns the parent because it was defined
			parentDirectory = randomFile2.getParent();
			System.out.println("File 2 Parent: " + parentDirectory);
			System.out.println("Is it a Directory " + randomDir.isDirectory());
			
			// list provides a string array containing all the files
			String[] filesInDir = randomDir.list();
			System.out.println("\nFiles in Directory: \n");
			
			// Use the enhanced for loop to cycle through the files
			for (String fileName : filesInDir) {
				System.out.println(fileName);
			}
			System.out.println();
			
			System.out.println("Is a File: " + randomFile.isFile());
			System.out.println("Is Hidden: " + randomFile.isHidden());
			
			// Milliseconds since Jan 1, 1970 when modified
			System.out.println("Last Modified: " + randomFile.lastModified());
			
			// Return the size of file
			System.out.println("File size: " + randomFile.length());
			
			// Change the name of the file
			randomFile2.renameTo(new File("/Users/karkro/git2/JavaLesson31/random2.txt"));
			
			// Output the full path for the file unless the path wasn't defined
			// when the file was created
			
			System.out.println("New name: " + randomFile2.toString());
			
			new JavaLesson31();
			
		} else {
			System.out.println("File doesn't exist");
		}
		
		// You call delete to delete a file
		if (randomFile.delete()) {
			System.out.println("File deleted");
		}
		
		// I could get an array of file objects from the directory
		File[] filesInDir = randomDir.listFiles();
		
		for (File fileName : filesInDir) {
			fileName.delete();
		}
		
		// You can only delete a directory if it is empty
		if (randomDir.delete()) {
			System.out.println("Directory deleted");
		}
		
		
	} // END of main
	
	
	public JavaLesson31() {
		
		// Creates a file chooser at the location specified
		JFileChooser fileChooser = new JFileChooser(randomDir);
		
		// Open the file chooser
		fileChooser.showOpenDialog(this);
	}
	
} // END OF JavaLesson31 CLASS