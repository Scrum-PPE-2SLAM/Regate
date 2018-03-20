package fr.regate.project.view;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class ModifRegate extends JFrame 
{

	private static final long serialVersionUID = 1L;
	private Window window;
	private JLabel lblSelRegate;
	private JComboBox<String> cboSelRegate;
	private AjoutRegate addRegate;
	
	public ModifRegate(Window window) 
	{
		this.window = window;
	}
	
	public void ajoutFenetre() 
	{
		this.addRegate = new AjoutRegate(window);
		this.addRegate.creationPanelAjoutRegate();
		this.addRegate.creationPanelParticipants();
		this.addRegate.creationPanelTitre("MODIFICATION REGATE");
	}
	
	public void ajoutCombo(String[] listeRegate) 
	{
		this.lblSelRegate = new JLabel("Selectionner la régate à modifier : ");
		this.lblSelRegate.setBounds(175, 68, 380, 14);
		this.addRegate.panelTitreRegate.add(lblSelRegate);
		
		this.cboSelRegate = new JComboBox<String>(listeRegate);
		this.cboSelRegate.setBounds(383, 65, 161, 20);
		this.addRegate.panelTitreRegate.add(cboSelRegate);
	}
	
}
