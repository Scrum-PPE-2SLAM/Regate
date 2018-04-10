package fr.regate.project.view;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class AjoutParticipant extends JFrame
{
	private static final long serialVersionUID = 1L;
	private JLabel lblNameParticipant, lblFirstNameParticipant, lblPhoneNumber, lblEmail, lblTitle;
	private JTextField tfNameParticipant, tfFirstNameParticipant, tfPhoneNumber, tfEmail;
	private Window window;
	private JPanel panelNewParticipant, panelTitle;
	private JButton btnSend;
	
	public AjoutParticipant(Window window)
	{
		this.window = window;
		this.btnSend = new JButton("Enregistrer");
	}

	public void createNouveauParticipant()
	{
		this.panelTitle = new JPanel();
		this.panelTitle.setBounds(10, 30, 764, 57);
		this.panelTitle.setLayout(null);
		this.window.add(panelTitle);
		
		this.lblTitle = new JLabel("AJOUT PARTICIPANT");
		this.lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		this.lblTitle.setFont(new Font("Tahoma", Font.BOLD, 40));
		this.lblTitle.setBounds(10, 14, 744, 32);
		this.panelTitle.add(lblTitle);
		
		this.panelNewParticipant = new JPanel();
		this.panelNewParticipant.setBounds(200, 120, 400, 400);
		this.window.add(panelNewParticipant);
		
		this.lblNameParticipant = new JLabel("Nom du participant: ");
		this.lblNameParticipant.setBounds(20, 21, 150, 43);
		
		this.lblFirstNameParticipant = new JLabel("Prénom : ");
		this.lblFirstNameParticipant.setBounds(20, 52, 100, 43);
		
		this.lblPhoneNumber = new JLabel("Téléphone : ");
		this.lblPhoneNumber.setBounds(20, 92, 100, 43);
		
		this.lblEmail = new JLabel("Email : ");
		this.lblEmail.setBounds(20, 130, 150, 43);
		
		
		this.tfNameParticipant = new JTextField(15); 
		this.tfNameParticipant.setEditable(true);
		this.tfNameParticipant.setBounds(135, 29, 185, 26);
		
		this.tfFirstNameParticipant = new JTextField(15);
		this.tfFirstNameParticipant.setEditable(true);
		this.tfFirstNameParticipant.setBounds(135, 67, 185, 26);
		
		this.tfPhoneNumber = new JTextField(15);
		this.tfPhoneNumber.setEditable(true);
		this.tfPhoneNumber.setBounds(135, 105, 185, 26);
		
		this.tfEmail = new JTextField(15);
		this.tfEmail.setEditable(true);
		this.tfEmail.setBounds(135, 138, 185, 26);
		
		this.panelNewParticipant.setLayout(null);
		

		
		this.btnSend.setFont(new Font("Tahoma", Font.BOLD, 12));
		this.btnSend.setBounds(150, 220, 120, 20);
		this.panelNewParticipant.add(btnSend);

		this.panelNewParticipant.add(lblNameParticipant);
		this.panelNewParticipant.add(tfNameParticipant);
		
		this.panelNewParticipant.add(lblFirstNameParticipant);
		this.panelNewParticipant.add(tfFirstNameParticipant);
		
		this.panelNewParticipant.add(lblPhoneNumber);
		this.panelNewParticipant.add(tfPhoneNumber);
		
		this.panelNewParticipant.add(lblEmail);
		this.panelNewParticipant.add(tfEmail);
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
}

