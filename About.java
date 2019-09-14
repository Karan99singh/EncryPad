package com.encryPad;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class About extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1378886944522754631L;
	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			About dialog = new About();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public About() {
		setBounds(120, 120, 480, 320);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblThisProjectWas = new JLabel("EncryPad v0.1 by Manas Khosla and Karandeep Singh");
			lblThisProjectWas.setFont(new Font("Segoe UI", Font.PLAIN, 14));
			lblThisProjectWas.setBounds(10, 10, 480, 57);
			contentPanel.add(lblThisProjectWas);
		}
		
		JTextPane txtpnYouCanFollow = new JTextPane();
		txtpnYouCanFollow.setEditable(false);
		txtpnYouCanFollow.setText("You can follow us at\r\n\r\nGithub:\r\n@manaskhosla\r\n@karan99singh\r\nLinkedIn:\r\n@manaskhosla");
		txtpnYouCanFollow.setBounds(10, 78, 414, 139);
		contentPanel.add(txtpnYouCanFollow);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}
}
