package com.encryPad;

import java.awt.BorderLayout;
//import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JDialog;
//import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.SystemColor;

public class Notepad extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private File path;
	private JDialog about = new About();

	/**
	 * Launch the application.
	 */
	//public static void main(String[] args) {
		//EventQueue.invokeLater(new Runnable() {
			//public void run() {
				//try {
					//Notepad frame = new Notepad();
					//frame.setVisible(true);
				//} catch (Exception e) {
					//e.printStackTrace();
				//}
			//}
		//});
	//}

	/**
	 * Create the frame.
	 */
	public Notepad(String filepath) {
		setTitle("EncryPad");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		path = new File(filepath);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(SystemColor.window);
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem fileNew = new JMenuItem("New");
		mnFile.add(fileNew);
		
		//JMenuItem fileOpen = new JMenuItem("Open..");
		//mnFile.add(fileOpen);
		
		JMenuItem fileSaveAs = new JMenuItem("Save");
		mnFile.add(fileSaveAs);
		
		JMenuItem fileExit = new JMenuItem("Exit");
		mnFile.add(fileExit);
		
		JMenu mnAbout = new JMenu("About");
		menuBar.add(mnAbout);
		
		JMenuItem abAbout = new JMenuItem("About EncryPad");
		mnAbout.add(abAbout);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(1, 0, 0, 0));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		TextArea textArea = new TextArea();
		textArea.setFont(null);
		contentPane.add(textArea, BorderLayout.CENTER);
		
		try {
    		BufferedReader in = new BufferedReader(new FileReader(path));
    	    String str;
    	    try {
				while ((str = in.readLine()) != null) {
				    textArea.append(str + "\n");
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
	//	}
    //}
//}

}
		
		/*
		 * Defining the button functions on MenuBar
		 */
		
		//New Button from File
		fileNew.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				textArea.replaceRange("", 0, textArea.getText().length());
			}
			
		});
		
		//Open Button from File
		//fileOpen.addActionListener(new ActionListener() {

			//@Override
			//public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//JFileChooser chooser = new JFileChooser();
				//int returnVal = chooser.showOpenDialog(getParent());
			    //if(returnVal == JFileChooser.APPROVE_OPTION) {
			    //	try {
			    	//	BufferedReader in = new BufferedReader(new FileReader(filepath));
			    	  //  String str;
			    	   // try {
						//	while ((str = in.readLine()) != null) {
							//    textArea.append(str + "\n");
							//}
						//} catch (IOException e1) {
							// TODO Auto-generated catch block
							//e1.printStackTrace();
						//}
					//} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						//e1.printStackTrace();
				//	}
			    //}
			//}
			
	//	}
		
		//SaveAs Button from File
		fileSaveAs.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				//JFileChooser chooser = new JFileChooser();
				//chooser.setApproveButtonText("Save");
				//int returnVal = chooser.showOpenDialog(getParent());
			   // if(returnVal == JFileChooser.APPROVE_OPTION) {
			    	try {
			    		FileWriter out = new FileWriter(path);
			    		out.write(textArea.getText());
			    		out.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
			    //}
			//}
			
		}
	});
		

		//Exit Button from File
		fileExit.addActionListener(e -> System.exit(0));
		
		//AboutEncryPad Button from About
		abAbout.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				about.setVisible(true);
			}
			
		});
		
	}

}
