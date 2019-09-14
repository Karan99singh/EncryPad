package com.encryPad;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import com.encryPad.Notepad;

import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Formatter;

public class userSession extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JList<String> filelist;
	private JButton add = new JButton("Create new file");
	private JButton open = new JButton("Open");
	private File filelocation;
	private Formatter format;
	private String pathl;
	private String checkpass;
	
	ArrayList<String> filenames = new ArrayList<String>();
	String[] filenamesarray = {};
	Scanner scan;
	String newline = System.getProperty("line.separator");
	int count = 0;
	
	
	public userSession(String path,String userpass) throws FileNotFoundException {
		super("Welcome");
		setLayout(new FlowLayout());
		pathl = path;
		checkpass = userpass;
		filelocation = new File(String.format("%s\\filenames.txt", path));
		if (filelocation.exists() == true) {
			scan = new Scanner(filelocation);
			if (scan.hasNextLine() == true) {
			while(true) {
				filenames.add(scan.nextLine());
				System.out.println(filenames.get(count));
				count++;
				if (scan.hasNextLine() == false)
					break;
			}}
			scan.close();
			
			filenamesarray = new String[filenames.size()];
			filenames.toArray(filenamesarray);
			filelist = new JList<String>(filenamesarray);
			filelist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			JScrollPane scrollpane = new JScrollPane(filelist);
			filelist.setFixedCellWidth(100);
			filelist.setFixedCellHeight(20);
			filelist.setVisibleRowCount(8);
			add(scrollpane);
		}
		else {
			filelist = new JList<String>(filenamesarray);
			filelist.setVisibleRowCount(8);
			filelist.setFixedCellWidth(100);
			filelist.setFixedCellHeight(20);
			filelist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			JScrollPane scrollpane = new JScrollPane(filelist);
			add(scrollpane);
			}
		
		Listenobj listenobj = new Listenobj();
		add.addActionListener(listenobj);
		open.addActionListener(listenobj);
		add(add);
		add(open);
		}

		public class Listenobj implements ActionListener{
			
			public void actionPerformed(ActionEvent event) {
				if(event.getSource() == add) {
					String temp = JOptionPane.showInputDialog("Enter file name");
					if (filelocation.exists() == true) {}
					else {try {
						format = new Formatter(filelocation);
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					format.close();}
						try {
							
							filenames.add(temp);
							filenamesarray = new String[filenames.size()];
							filenames.toArray(filenamesarray);
							filelist.setListData(filenamesarray);
							filelist.setVisibleRowCount(8);
							filelist.setFixedCellWidth(100);
							filelist.setFixedCellHeight(20);
							format = new Formatter(filelocation);
							for(String item:filenamesarray) {
								format.format("%s"+newline,item);
							}format.close();
							
							format = new Formatter(String.format("%s\\%s.txt",pathl,temp));
							format.close();
							Encrypt.Encrun(pathl, temp, checkpass);
						} catch (FileNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (@SuppressWarnings("hiding") IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					
					
				}
				if(event.getSource() == open) {
					if(filelist.getSelectedValue() != null) {
					try {
						Decrypt.Decrun(checkpass, filelist.getSelectedValue(), pathl);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					Notepad frame = new Notepad(pathl,filelist.getSelectedValue(),checkpass);
					frame.setVisible(true);
				}
				}
			}
		}
		
		
	}

