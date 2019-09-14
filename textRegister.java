package com.encryPad;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Formatter;

import javax.swing.*;
//import java.io.*;
public class textRegister extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel username = new JLabel("User Name");
	private JLabel password = new JLabel("Password");
	private JLabel confirmpassword = new JLabel("ReEnter Password");
	

	private JTextField usernametext = new JTextField("Enter your name");
	private JPasswordField passwordtext = new JPasswordField("Defaultpass");
	private JPasswordField confirmpasswordtext = new JPasswordField("Defaultpass");
	
	private JButton save = new JButton("Save");
	private JButton back = new JButton("Back");
	
	textLogin guiobj;
	File file;
	String checkname;
	String checkpass;
	Formatter format;
	
	public textRegister() {
		super("Enter a Title");
		add(username);
		add(usernametext);
		add(password);
		add(passwordtext);
		add(confirmpassword);
		add(confirmpasswordtext);
		add(save);
		add(back);
		
		back.addActionListener(new listenobj());
		save.addActionListener(new listenobj());
		setLayout(new GridLayout(4,2,3,1));
		this.setSize(480,320);
		this.setVisible(true);
		}
	public void visible(boolean arg) {
		this.setVisible(arg);
		guiobj = new textLogin();
	}

	public class listenobj implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			if(event.getSource()==back) {
				visible(false);}
			
		if(event.getSource()==save) {
			checkname = usernametext.getText();
			checkpass = String.valueOf(passwordtext.getPassword());
			
			file = new File(String.format("C:\\users\\Karan Singh\\Documents\\%s",checkname));
			
			if (file.exists()==true){
				JOptionPane.showMessageDialog(null,"User already exist");	
			}
			else {
				file.mkdirs();
			try {
				format = new Formatter(String.format("%s\\pass.txt",file.getPath()));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		
			}
				format.format(checkpass);
				format.close();
				try {
					Encrypt.Encrun(file.getPath(),"pass",textMain.Defaultkey);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				visible(false);
			
			}}
	
}}}
