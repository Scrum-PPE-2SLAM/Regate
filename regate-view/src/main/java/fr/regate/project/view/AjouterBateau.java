package fr.regate.project.view;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class AjouterBateau extends JFrame
{
	private static final long serialVersionUID = 1L;
	private JLabel lblNameShip, lblCategory, lblRating;
	private JTextField tfNameShip, tfCategory, tfRating;
	private Window window;
	private JPanel panelNewShip, panelTitle;
	private JButton btnSend;
	
	public AjouterBateau(Window window)
	{
		this.window = window;
		this.btnSend = new JButton();
	}

	public void createShip(String title, String buttonName)
	{
		this.panelTitle = new JPanel();
		this.panelTitle.setBounds(10, 30, 764, 57);
		this.panelTitle.setLayout(null);
		this.window.add(panelTitle);
		
		this.lblRating = new JLabel(title);
		this.lblRating.setHorizontalAlignment(SwingConstants.CENTER);
		this.lblRating.setFont(new Font("Tahoma", Font.BOLD, 40));
		this.lblRating.setBounds(10, 14, 744, 32);
		this.panelTitle.add(lblRating);
		
		this.panelNewShip = new JPanel();
		this.panelNewShip.setBounds(200, 120, 400, 400);
		this.window.add(panelNewShip);
		
		this.lblNameShip = new JLabel("Nom du Bateau: ");
		this.lblNameShip.setBounds(20, 41, 150, 43);
		
		this.lblCategory = new JLabel("Categorie : ");
		this.lblCategory.setBounds(20, 75, 100, 43);
		
		this.lblRating = new JLabel("Rating : ");
		this.lblRating.setBounds(20, 112, 100, 43);
		
		
		this.tfNameShip = new JTextField(15); 
		this.tfNameShip.setEditable(true);
		this.tfNameShip.setBounds(135, 49, 185, 26);
		
		this.tfCategory = new JTextField(15);
		this.tfCategory.setEditable(true);
		this.tfCategory.setBounds(135, 87, 185, 26);
		
		this.tfRating = new JTextField(15);
		this.tfRating.setEditable(true);
		this.tfRating.setBounds(135, 125, 185, 26);
	
		this.panelNewShip.setLayout(null);
		
		this.btnSend.setFont(new Font("Tahoma", Font.BOLD, 12));
		this.btnSend.setBounds(150, 220, 120, 20);
		this.panelNewShip.add(btnSend);
		btnSend.setText(buttonName);

		this.panelNewShip.add(tfNameShip);
		this.panelNewShip.add(lblNameShip);
		this.panelNewShip.add(tfCategory);
		this.panelNewShip.add(lblCategory);
		this.panelNewShip.add(tfRating);
		this.panelNewShip.add(lblRating);
	}
	
	public JButton getBtnSend() {
		return btnSend;
	}
	
	public String getNameShip() {
		return tfNameShip.getText();
	}
	
	public String getCategory() {
		return tfCategory.getText();
	}
	
	public String getRating() {
		return tfRating.getText();
	}
	
	public void setNameShip(String nameParticipant) {
		tfNameShip.setText(nameParticipant);
	}
	
	public void setCategory(String firstName) {
		tfCategory.setText(firstName);
	}
	
	public void setRating(String phoneNumber) {
		tfRating.setText(phoneNumber);
	}
}




