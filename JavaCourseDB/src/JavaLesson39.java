import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;


public class JavaLesson39 extends JFrame implements HyperlinkListener, ActionListener {

	public static void main(String[] args) {
		
		new JavaLesson39("file:///xampp/htdocs/ujk/index_old.htm");
		
	}
	
	String defaultURL;
	JPanel toolPanel = new JPanel();
	JTextField theURL = new JTextField(25);
	
	JEditorPane htmlPage;
	
	public JavaLesson39(String defaultURL) {
		
		JFrame frame = new JFrame("Java Browser");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.defaultURL = defaultURL;
		theURL.addActionListener(this);
		theURL.setText(defaultURL);
		toolPanel.add(theURL);
		frame.add(toolPanel, BorderLayout.NORTH);
		
		try {
			htmlPage = new JEditorPane(defaultURL);
			htmlPage.addHyperlinkListener(this);
			htmlPage.setEditable(false);
			JScrollPane scroller = new JScrollPane(htmlPage);
			frame.add(scroller, BorderLayout.CENTER);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		frame.setSize(1200, 800);
		frame.setVisible(true);
	}



	@Override
	public void hyperlinkUpdate(HyperlinkEvent e) {
		// TODO Auto-generated method stub
		
		if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
			
			try {
				htmlPage.setPage(e.getURL());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			theURL.setText(e.getURL().toExternalForm());
			
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		String pageURL = "";
		if (e.getSource() == theURL) {
			pageURL = theURL.getText();
		} else {
			JOptionPane.showMessageDialog(JavaLesson39.this, "Please enter a web address", "Error", JOptionPane.ERROR_MESSAGE);
		}
		
		try {
			htmlPage.setPage(new URL(pageURL));
		} catch (MalformedURLException | IOException e1) {
			JOptionPane.showMessageDialog(JavaLesson39.this, "Please use http://", "Error", JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
}
