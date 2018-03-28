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
	private JLabel lblNameParticipant, lblFirstNameParticipant, lblNameShip, lblCategoryShip, lblRating, lblTitle;
	private JTextField tfNameParticipant, tfFirstNameParticipant, tfNameShip, tfCategoryShip, tfRating;
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
		this.lblNameParticipant.setBounds(29, 21, 96, 43);
		
		this.lblFirstNameParticipant = new JLabel("Prénom : ");
		this.lblFirstNameParticipant.setBounds(87, 52, 38, 43);
		
		this.lblNameShip = new JLabel("Nom du voilier: ");
		this.lblNameShip.setBounds(46, 92, 79, 43);
		
		this.lblCategoryShip = new JLabel("Catgorie du voilier : ");
		this.lblCategoryShip.setBounds(51, 130, 74, 43);
		
		this.lblRating = new JLabel("Rating : ");
		this.lblRating.setBounds(68, 165, 57, 43);
		
		this.tfNameParticipant = new JTextField(15); 
		this.tfNameParticipant.setEditable(true);
		this.tfNameParticipant.setBounds(135, 29, 185, 26);
		
		this.tfFirstNameParticipant = new JTextField(15);
		this.tfFirstNameParticipant.setEditable(true);
		this.tfFirstNameParticipant.setBounds(135, 67, 185, 26);
		
		this.tfNameShip = new JTextField(15);
		this.tfNameShip.setEditable(true);
		this.tfNameShip.setBounds(135, 105, 185, 26);
		
		this.tfCategoryShip = new JTextField(15);
		this.tfCategoryShip.setEditable(true);
		this.tfCategoryShip.setBounds(135, 138, 185, 26);
		
		this.tfRating = new JTextField(15);
		this.tfRating.setEditable(true);
		this.tfRating.setBounds(135, 173, 185, 26);
		this.panelNewParticipant.setLayout(null);
		

		
		this.btnSend.setFont(new Font("Tahoma", Font.BOLD, 12));
		this.btnSend.setBounds(150, 220, 120, 20);
		this.panelNewParticipant.add(btnSend);

		this.panelNewParticipant.add(lblNameParticipant);
		this.panelNewParticipant.add(tfNameParticipant);
		
		this.panelNewParticipant.add(lblFirstNameParticipant);
		this.panelNewParticipant.add(tfFirstNameParticipant);
		
		this.panelNewParticipant.add(lblNameShip);
		this.panelNewParticipant.add(tfNameShip);
		
		this.panelNewParticipant.add(lblCategoryShip);
		this.panelNewParticipant.add(tfCategoryShip);
		
		this.panelNewParticipant.add(lblRating);
		this.panelNewParticipant.add(tfRating);
	}

	public JButton getBtnSend() {
		return btnSend;
	}
}

