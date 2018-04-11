package fr.regate.project.view;

import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class AjoutParticipant extends JFrame
{
	private static final long serialVersionUID = 1L;
	private JLabel lblNameParticipant, lblFirstNameParticipant, lblPhoneNumber, lblEmail, lblTitle, lblSelectParticipant;
	private JTextField tfNameParticipant, tfFirstNameParticipant, tfPhoneNumber, tfEmail;
	private Window window;
	private JPanel panelNewParticipant, panelTitle;
	private JButton btnSend;
	private JComboBox<String> cboSelParticipant;
	
	public AjoutParticipant(Window window)
	{
		this.window = window;
		this.btnSend = new JButton();
	}

	public void createParticipant(String title, String buttonName)
	{
		this.panelTitle = new JPanel();
		this.panelTitle.setBounds(10, 30, 764, 57);
		this.panelTitle.setLayout(null);
		this.window.add(panelTitle);
		
		this.lblTitle = new JLabel(title);
		this.lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		this.lblTitle.setFont(new Font("Tahoma", Font.BOLD, 40));
		this.lblTitle.setBounds(10, 14, 744, 32);
		this.panelTitle.add(lblTitle);
		
		this.panelNewParticipant = new JPanel();
		this.panelNewParticipant.setBounds(200, 120, 400, 400);
		this.window.add(panelNewParticipant);
		
		this.lblNameParticipant = new JLabel("Nom du participant: ");
		this.lblNameParticipant.setBounds(20, 41, 150, 43);
		
		this.lblFirstNameParticipant = new JLabel("Prénom : ");
		this.lblFirstNameParticipant.setBounds(20, 72, 100, 43);
		
		this.lblPhoneNumber = new JLabel("Téléphone : ");
		this.lblPhoneNumber.setBounds(20, 112, 100, 43);
		
		this.lblEmail = new JLabel("Email : ");
		this.lblEmail.setBounds(20, 150, 150, 43);
		
		
		this.tfNameParticipant = new JTextField(15); 
		this.tfNameParticipant.setEditable(true);
		this.tfNameParticipant.setBounds(135, 49, 185, 26);
		
		this.tfFirstNameParticipant = new JTextField(15);
		this.tfFirstNameParticipant.setEditable(true);
		this.tfFirstNameParticipant.setBounds(135, 87, 185, 26);
		
		this.tfPhoneNumber = new JTextField(15);
		this.tfPhoneNumber.setEditable(true);
		this.tfPhoneNumber.setBounds(135, 125, 185, 26);
		
		this.tfEmail = new JTextField(15);
		this.tfEmail.setEditable(true);
		this.tfEmail.setBounds(135, 158, 185, 26);
		
		this.panelNewParticipant.setLayout(null);
		

		
		this.btnSend.setFont(new Font("Tahoma", Font.BOLD, 12));
		this.btnSend.setBounds(150, 220, 120, 20);
		this.panelNewParticipant.add(btnSend);
		btnSend.setText(buttonName);

		this.panelNewParticipant.add(lblNameParticipant);
		this.panelNewParticipant.add(tfNameParticipant);
		
		this.panelNewParticipant.add(lblFirstNameParticipant);
		this.panelNewParticipant.add(tfFirstNameParticipant);
		
		this.panelNewParticipant.add(lblPhoneNumber);
		this.panelNewParticipant.add(tfPhoneNumber);
		
		this.panelNewParticipant.add(lblEmail);
		this.panelNewParticipant.add(tfEmail);
	}

	public void modifParticipant() {
		this.lblSelectParticipant = new JLabel("Selection participant : ");
		this.lblSelectParticipant.setBounds(5, 8, 180, 43);
		this.panelNewParticipant.add(lblSelectParticipant);
		
		this.cboSelParticipant.setBounds(135, 20, 185, 20);
		this.panelNewParticipant.add(cboSelParticipant);
	}
	
	public JButton getBtnSend() {
		return btnSend;
	}
	
	public String getNameParticipant() {
		return tfNameParticipant.getText();
	}
	
	public String getFirstName() {
		return tfFirstNameParticipant.getText();
	}
	
	public String getPhoneNumber() {
		return tfPhoneNumber.getText();
	}
	
	public String getEmail() {
		return tfEmail.getText();
	}
	
	public void setNameParticipant(String nameParticipant) {
		tfNameParticipant.setText(nameParticipant);
	}
	
	public void setFirstName(String firstName) {
		tfFirstNameParticipant.setText(firstName);
	}
	
	public void setPhoneNumber(String phoneNumber) {
		tfPhoneNumber.setText(phoneNumber);
	}
	
	public void setEmail(String email) {
		tfEmail.setText(email);
	}

	public void setCboSelParticipant(String[] value) {
		cboSelParticipant = new JComboBox<String>(value);
	}
	
}

