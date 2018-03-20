import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class AjoutParticipant extends JFrame
{
	private static final long serialVersionUID = 1L;
	private JLabel lblNomParticipant, lblPrenomParticipant, lblNomDuVoilier, lblCategorieVoilier, lblRating, lblTitle;
	private JTextField tfNomParticipant, tfPrenomParticipant, tfNomDuVoilier, tfCategorieVoilier, tfRating;
	private Window window;
	private JPanel panelNewParticipant, panelTitle;
	private JButton btnEnvoyer;
	private Bdd maBdd;
	
	public AjoutParticipant(Window window, Bdd maBdd)
	{
		this.window = window;
		this.maBdd = maBdd;
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
		
		this.lblNomParticipant = new JLabel("Nom du participant: ");
		this.lblNomParticipant.setBounds(29, 21, 96, 43);
		
		this.lblPrenomParticipant = new JLabel("Prénom : ");
		this.lblPrenomParticipant.setBounds(87, 52, 38, 43);
		
		this.lblNomDuVoilier = new JLabel("Nom du voilier: ");
		this.lblNomDuVoilier.setBounds(46, 92, 79, 43);
		
		this.lblCategorieVoilier = new JLabel("Catgorie du voilier : ");
		this.lblCategorieVoilier.setBounds(51, 130, 74, 43);
		
		this.lblRating = new JLabel("Rating : ");
		this.lblRating.setBounds(68, 165, 57, 43);
		
		this.tfNomParticipant = new JTextField(15); 
		this.tfNomParticipant.setEditable(true);
		this.tfNomParticipant.setBounds(135, 29, 185, 26);
		
		this.tfPrenomParticipant = new JTextField(15);
		this.tfPrenomParticipant.setEditable(true);
		this.tfPrenomParticipant.setBounds(135, 67, 185, 26);
		
		this.tfNomDuVoilier = new JTextField(15);
		this.tfNomDuVoilier.setEditable(true);
		this.tfNomDuVoilier.setBounds(135, 105, 185, 26);
		
		this.tfCategorieVoilier = new JTextField(15);
		this.tfCategorieVoilier.setEditable(true);
		this.tfCategorieVoilier.setBounds(135, 138, 185, 26);
		
		this.tfRating = new JTextField(15);
		this.tfRating.setEditable(true);
		this.tfRating.setBounds(135, 173, 185, 26);
		this.panelNewParticipant.setLayout(null);
		
		this.btnEnvoyer = new JButton("Enregistrer");
		this.btnEnvoyer.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				try 
				{
						maBdd.reqAjoutParticipant(tfNomParticipant.getText(), tfPrenomParticipant.getText() , tfNomDuVoilier.getText(), tfCategorieVoilier.getText() , Integer.parseInt(tfRating.getText()));
				} catch (SQLException e1) {
					e1.printStackTrace();
				}		
			}
		});
		
		this.btnEnvoyer.setFont(new Font("Tahoma", Font.BOLD, 12));
		this.btnEnvoyer.setBounds(150, 220, 120, 20);
		this.panelNewParticipant.add(btnEnvoyer);

		this.panelNewParticipant.add(lblNomParticipant);
		this.panelNewParticipant.add(tfNomParticipant);
		
		this.panelNewParticipant.add(lblPrenomParticipant);
		this.panelNewParticipant.add(tfPrenomParticipant);
		
		this.panelNewParticipant.add(lblNomDuVoilier);
		this.panelNewParticipant.add(tfNomDuVoilier);
		
		this.panelNewParticipant.add(lblCategorieVoilier);
		this.panelNewParticipant.add(tfCategorieVoilier);
		
		this.panelNewParticipant.add(lblRating);
		this.panelNewParticipant.add(tfRating);
	}
}

