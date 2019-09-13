package com.encryPad;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.Scanner;
public class textLogin extends JFrame {
	
	private JLabel username = new JLabel("User Name");
	private JLabel password = new JLabel("Password");
	
	private JTextField usernametext = new JTextField("Enter your name");
	private JPasswordField passwordtext = new JPasswordField("Defaultpass");
	
	private JButton login = new JButton("Login");
	private JButton register = new JButton("Register");
	textRegister guiobj2;
	
	String checkname;
	String checkpass;
	File file;
	Scanner scan;
	
	public textLogin() {
		super("Enter a Title");
		add(username);
		add(usernametext);
		add(password);
		add(passwordtext);
		add(login);
		add(register);
		
		setLayout(new GridLayout(3,2,3,1));
		
		register.addActionListener(new Listenobj());
		login.addActionListener(new Listenobj());
		this.setSize(480,320);
		this.setVisible(true);
	}
	
	
		public void visible(boolean arg) {
			this.setVisible(arg);
			guiobj2 = new textRegister();
			}
		
		
		
		public class Listenobj implements ActionListener{
			public void actionPerformed(ActionEvent event) {
				if(event.getSource()==register) {
					visible(false);
				}
				if(event.getSource()==login) {
					checkname = usernametext.getText();		
					file = new File(String.format("C:\\users\\Karan Singh\\Documents\\%s",checkname));
					if (file.exists()==true){
						try {
							scan = new Scanner(new File(String.format("%s\\pass.txt",file.getPath())));
						} catch (FileNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						checkpass = scan.nextLine();
						System.out.println(checkpass);
						System.out.println(String.valueOf(passwordtext.getPassword()));
						if(checkpass.contentEquals(String.valueOf(passwordtext.getPassword()))==true) {
							try {
								userSession START = new userSession(file.getPath());
								START.setSize(480,320);
								START.setVisible(true);
							} catch (FileNotFoundException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
					else 
						JOptionPane.showMessageDialog(null,"User dont exist");
					
					}
				}
			}




}

		


		

