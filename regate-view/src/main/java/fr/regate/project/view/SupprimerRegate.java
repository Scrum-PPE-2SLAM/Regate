package fr.regate.project.view;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class SupprimerRegate extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private Window window;
	private JPanel panelDeletRegate;
	private JLabel lblSelRegate, lblTitleRegate;
	private JComboBox<String> cboSelRegate;
	private JButton btnDelRegate;
	
	public SupprimerRegate(Window window) 
	{
		this.window = window;
		btnDelRegate = new JButton("Supprimer");
	}
	
	public void creationPanelTitre(String titre) 
	{
		this.panelDeletRegate = new JPanel();
		this.panelDeletRegate.setBounds(10, 11, 764, 600);
		this.panelDeletRegate.setLayout(null);
		this.window.add(panelDeletRegate);
		
		this.lblTitleRegate = new JLabel(titre);
		this.lblTitleRegate.setHorizontalAlignment(SwingConstants.CENTER);
		this.lblTitleRegate.setFont(new Font("Tahoma", Font.BOLD, 40));
		this.lblTitleRegate.setBounds(10, 14, 744, 32);
		this.panelDeletRegate.add(lblTitleRegate);
	}
	
	public void ajoutComboDelet(String[] listeRegate) 
	{
		this.lblSelRegate = new JLabel("Selectionner la régate à Supprimer : ");
		this.lblSelRegate.setBounds(175, 68, 380, 14);
		this.panelDeletRegate.add(lblSelRegate);
		
		this.cboSelRegate = new JComboBox<String>(listeRegate);
		this.cboSelRegate.setBounds(383, 65, 161, 20);
		this.panelDeletRegate.add(cboSelRegate);
		
		btnDelRegate.setBounds(550, 65, 120, 20);
		panelDeletRegate.add(btnDelRegate);
		
	}
	
	public JButton getBtnDelRegate() {
		return btnDelRegate;
	}
	
	public JComboBox<String> getcboDelRegate() {
		return cboSelRegate;
	}

}
