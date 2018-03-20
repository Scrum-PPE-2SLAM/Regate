import java.awt.Color;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

public class AjoutRegate extends JFrame 
{
	private static final long serialVersionUID = 1L;
	private JPanel panelInfoRegate, panelTableParticipant;
	JPanel panelTitreRegate;
	private JLabel lblNomRegate, lblDate, lblLieuDepart, lblLieuArrivee, lblDistance, lblSelRegate;
	private JTextField tfNomRegate, tfLieuDepart, tfLieuArrivee, tfDistance;
	private JDateChooser cboDate;
	private Window window;
	private JButton btnEnvoyer;
	private Bdd maBdd;
	private JScrollPane scrollPane;
	private JTable tableParticipants;
	private JComboBox<String> cboSelParticipant;
	private ArrayList<Participant> listeParticipantRegate= new ArrayList<Participant>();

	public AjoutRegate(Window window, Bdd maBdd) 
	{
		this.window = window;
		this.maBdd = maBdd;
	}
	
	public void creationPanelAjoutRegate() 
	{
		this.panelInfoRegate = new JPanel();
		this.panelInfoRegate.setBorder(new TitledBorder(null, "R\u00E9gate", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		this.panelInfoRegate.setBounds(10, 120, 370, 340);
		this.panelInfoRegate.setLayout(null);
		this.window.add(panelInfoRegate);
		
		this.lblNomRegate = new JLabel("Nom de la régate : ");
		this.lblNomRegate.setBounds(29, 70, 116, 43);
		
		this.lblDate = new JLabel("Date : ");
		this.lblDate.setBounds(97, 110, 58, 53);
		
		this.lblLieuDepart = new JLabel("Lieu de départ : ");
		this.lblLieuDepart.setBounds(40, 150, 99, 43);
		
		this.lblLieuArrivee = new JLabel("Lieu d'arrivée : ");
		this.lblLieuArrivee.setBounds(40, 190, 94, 43);
		
		this.lblDistance = new JLabel("Distance : ");
		this.lblDistance.setBounds(68, 230, 77, 43);
		
		
		this.tfNomRegate = new JTextField(15); 
		this.tfNomRegate.setBounds(145, 80, 185, 26);
		
		this.tfLieuDepart = new JTextField(15);
		this.tfLieuDepart.setBounds(145, 160, 185, 26);
		
		this.tfLieuArrivee = new JTextField(15);
		this.tfLieuArrivee.setBounds(145, 200, 185, 26);
		
		this.tfDistance = new JTextField(15);
		this.tfDistance.setBounds(145, 240, 185, 26);
		
		this.cboDate = new JDateChooser();
		this.cboDate.setBounds(145, 120, 118, 26);
	
		this.btnEnvoyer = new JButton("Enregistrer");
		this.btnEnvoyer.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				try 
				{
					String maDateString;
					SimpleDateFormat formatter = new SimpleDateFormat ("dd/MM/yyyy" );
					
					maDateString = formatter.format(cboDate.getDate());
					maBdd.reqAjoutRegate(tfNomRegate.getText(), maDateString, tfLieuDepart.getText(), tfLieuArrivee.getText() , Integer.parseInt(tfDistance.getText()), listeParticipantRegate);
				} catch (SQLException e1) {}		
			}
		});
		
		this.btnEnvoyer.setFont(new Font("Tahoma", Font.BOLD, 12));
		this.btnEnvoyer.setBounds(150, 290, 120, 20);
		this.panelInfoRegate.add(btnEnvoyer);
		
		this.panelInfoRegate.add(lblNomRegate);
		this.panelInfoRegate.add(tfNomRegate);
		this.panelInfoRegate.add(lblDate);
		this.panelInfoRegate.add(cboDate);
		this.panelInfoRegate.add(lblLieuDepart);
		this.panelInfoRegate.add(tfLieuDepart);
		this.panelInfoRegate.add(lblLieuArrivee);
		this.panelInfoRegate.add(tfLieuArrivee);
		this.panelInfoRegate.add(lblDistance);
		this.panelInfoRegate.add(tfDistance);		
	}
	
	public void creationPanelParticipants() 
	{	
		ArrayList<String> listeCombo = new ArrayList<String>();
		
		this.panelTableParticipant = new JPanel();
		this.panelTableParticipant.setBorder(new TitledBorder(null, "Participants", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		this.panelTableParticipant.setBounds(404, 120, 370, 340);
		this.panelTableParticipant.setLayout(null);
		this.window.add(panelTableParticipant);
		
		for (int i=0; i<maBdd.getParticipant().size(); i++)
		{
			listeCombo.add(maBdd.getParticipant().get(i).getNom() + " " + maBdd.getParticipant().get(i).getPrenom() + ", " + maBdd.getParticipant().get(i).getnomVoilier());
		}
		
		this.cboSelParticipant = new JComboBox<String>(listeCombo.toArray(new String[0]));
		this.cboSelParticipant.setBounds(10, 20, 161, 20);
		this.panelTableParticipant.add(cboSelParticipant);
		
		JButton btnAjout = new JButton("Ajout Participant");
		btnAjout.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				ajoutParticipantTable();
			}
		});
		
		btnAjout.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnAjout.setBounds(180, 20, 120, 20);
		this.panelTableParticipant.add(btnAjout);

		this.scrollPane = new JScrollPane();
		this.scrollPane.setBounds(10, 50, 350, 285);
		this.panelTableParticipant.add(scrollPane);
		
		this.tableParticipants = new JTable(20,5);
		this.tableParticipants.setBorder(new LineBorder(new Color(0, 0, 0)));
		this.scrollPane.setViewportView(tableParticipants);
		this.tableParticipants.setFillsViewportHeight(true);
		this.tableParticipants.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
			},
			new String[] {"Nom", "Prénom", "Voilier", "Catégorie", "Rating"}
		) {

			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] {
				false, true, true, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		
		this.tableParticipants.getColumnModel().getColumn(1).setPreferredWidth(45);
		this.tableParticipants.getColumnModel().getColumn(2).setPreferredWidth(45);
		this.tableParticipants.setRowHeight(18);
	}
	
	public void creationPanelTitre(String titre) 
	{
		this.panelTitreRegate = new JPanel();
		this.panelTitreRegate.setBounds(10, 11, 764, 119);
		this.panelTitreRegate.setLayout(null);
		this.window.add(panelTitreRegate);
		
		this.lblSelRegate = new JLabel(titre);
		this.lblSelRegate.setHorizontalAlignment(SwingConstants.CENTER);
		this.lblSelRegate.setFont(new Font("Tahoma", Font.BOLD, 40));
		this.lblSelRegate.setBounds(10, 14, 744, 32);
		this.panelTitreRegate.add(lblSelRegate);
	}
	
	public void ajoutParticipantTable()
	{
		Participant list;
		
		if (tableParticipants.getValueAt(19, 0) == null) 
		{
			int pos = 0;
			for (int i = 0; i<20; i++) 
			{
				if (tableParticipants.getValueAt(i, 0) != null) 
				{
					pos += 1;
				}
			}
				list = maBdd.getParticipant().get(cboSelParticipant.getSelectedIndex());
				listeParticipantRegate.add(list);
				tableParticipants.setValueAt(list.getNom(), pos, 0);
				tableParticipants.setValueAt(list.getPrenom(), pos, 1);
				tableParticipants.setValueAt(list.getnomVoilier(), pos, 2);
				tableParticipants.setValueAt(list.getCatégorieVoilier(), pos, 3);
				tableParticipants.setValueAt(list.getRating(), pos, 4);
		}
	}
	
}
