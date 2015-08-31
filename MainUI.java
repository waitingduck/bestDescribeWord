import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;


public class MainUI extends JFrame{
	private JFrame frame;
	private JLabel label;
	private JTextArea resultTextArea;  
	private JTextArea URLTextArea;
	private JTextArea topTextArea;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainUI window = new MainUI();
					window.frame.setVisible(true);
				} 
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public MainUI() {
		initialize();
	}
	
	private void initialize(){
		frame = new JFrame("Web crawler");
		frame.setBounds(0, 0, 600, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		
		
		
		URLTextArea = new JTextArea("Input your target website URL here.");
		URLTextArea.setEditable(true);
		JScrollPane scrollURL = new JScrollPane(URLTextArea);
		scrollURL.setBounds(340, 10, 230, 40);
		frame.getContentPane().add(scrollURL);
		
		URLTextArea.addMouseListener(new MouseListener(){
			@Override
			public void mouseClicked(MouseEvent e) {
				URLTextArea.setText("");
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		resultTextArea = new JTextArea("The best word to describe your target webstie.");
		resultTextArea.setEditable(false);
		JScrollPane scrollResult = new JScrollPane(resultTextArea);
		scrollResult.setBounds(10, 10, 300, 450);
		frame.getContentPane().add(scrollResult);
		
		topTextArea = new JTextArea("The top three words will be list here with\nscore.");
		topTextArea.setEditable(false);
		JScrollPane scrollTop = new JScrollPane(topTextArea);
		scrollTop.setBounds(340, 170, 230, 290);
		frame.getContentPane().add(scrollTop);
		
		JButton submitButton = new JButton("Submit");
		submitButton.setBounds(340, 60, 230, 40);
		submitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	resultTextArea.setText("");
            	topTextArea.setText("");
            	new Controller().start(URLTextArea.getText(),resultTextArea,topTextArea);
            	System.out.println();
            }
        });
		frame.getContentPane().add(submitButton);
		
		JButton clearButton = new JButton("Clear");
		clearButton.setBounds(340, 110, 230, 40);
		clearButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	resultTextArea.setText("The best word to describe your target webstie");
            	URLTextArea.setText("Input your target website URL here");
            	topTextArea.setText("The top three words will be list here with\nscore.");
            }
        });
		frame.getContentPane().add(clearButton);
	}
	
}
