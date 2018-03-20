import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class ModifRegate extends JFrame 
{

	private static final long serialVersionUID = 1L;
	private Window window;
	private Bdd maBdd;
	private JLabel lblSelRegate;
	private JComboBox<String> cboSelRegate;
	private AjoutRegate ajoutRegate;
	
	public ModifRegate(Window window, Bdd maBdd) 
	{
		this.window = window;
		this.maBdd = maBdd;
	}
	
	public void ajoutFenetre() 
	{
		this.ajoutRegate = new AjoutRegate(window, maBdd);
		this.ajoutRegate.creationPanelAjoutRegate();
		this.ajoutRegate.creationPanelParticipants();
		this.ajoutRegate.creationPanelTitre("MODIFICATION REGATE");
	}
	
	public void ajoutCombo(String[] listeRegate) 
	{
		this.lblSelRegate = new JLabel("Selectionner la régate à modifier : ");
		this.lblSelRegate.setBounds(175, 68, 380, 14);
		this.ajoutRegate.panelTitreRegate.add(lblSelRegate);
		
		this.cboSelRegate = new JComboBox<String>(listeRegate);
		this.cboSelRegate.setBounds(383, 65, 161, 20);
		this.ajoutRegate.panelTitreRegate.add(cboSelRegate);
	}
	
}
