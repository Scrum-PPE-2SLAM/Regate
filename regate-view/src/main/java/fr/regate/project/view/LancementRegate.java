import java.awt.Color;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

public class LancementRegate extends JFrame 
{
	private static final long serialVersionUID = 1L;
	private JTable tableParticipants;
	private JPanel panelSelRegate, panelChrono, panelInfoRegate, panelTableParticipant;
	private JLabel lblSelRegate, lblNomRegate, lblDate, lblLieuDepart, lblLieuArrivee, lblDistance, lblChrono;
	private JTextField tfNomRegate, tfLieuDepart, tfLieuArrivee, tfDistance;
	private JComboBox<String> cboSelRegate;
	private JTextField tfDate;
	private DTimer chrono;
	private Window window;
	private JButton btnFin, btnReinit, btnDepart, btnSelectionner;
	private JScrollPane scrollPane;
	private ArrayList<Participant> lesParticipants;
	private SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
	private Bdd maBdd;
	private Regate regateCourante;
	

	public LancementRegate(Window window, Bdd maBdd) 
	{
		this.window = window;
		this.maBdd = maBdd;
	}
	
	public void createAll()
	{
		creationPanelSelRegate();
		creationPanelInfoRegate();
		creationPanelChrono();
		creationPanelTableau();
	}
	
	public void creationPanelSelRegate() 
	{
		this.panelSelRegate = new JPanel();
		this.panelSelRegate.setBounds(10, 11, 764, 57);
		this.panelSelRegate.setLayout(null);
		this.window.add(panelSelRegate);
		
		this.lblSelRegate = new JLabel("Selectionner la régate à lancer : ");
		this.lblSelRegate.setBounds(190, 14, 220, 14);
		this.panelSelRegate.add(lblSelRegate);
		
		this.cboSelRegate = new JComboBox<String>();
		for (int i=0; i<maBdd.getlisteRegate().size(); i++) {
			//if (maBdd.getlisteRegate().get(i).getEtat() == 0)
				cboSelRegate.addItem(maBdd.getlisteRegate().get(i).getNomRegate());
		}
		this.cboSelRegate.setBounds(383, 11, 161, 20);
		this.panelSelRegate.add(cboSelRegate);
		
		this.btnSelectionner = new JButton("Valider");
		this.btnSelectionner.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				if (chrono.isRunning() || tableParticipants.getValueAt(0, 4) != null) {
					JOptionPane.showMessageDialog(null, "Une régate est en cours. Impossible d'en selectionner une autre.", "Erreur", JOptionPane.ERROR_MESSAGE);
				}else {
					for (int i = 0; i<20; i++) {
						tableParticipants.setValueAt(null, i, 0);
						tableParticipants.setValueAt(null, i, 1);
						tableParticipants.setValueAt(null, i, 4);
					}
					regateCourante = maBdd.getlisteRegate().get(cboSelRegate.getSelectedIndex());
					chargerInfoRegate();
					ajoutParticipantsTableau();
					window.repaint();
				}
				
			}
		});
		
		this.btnSelectionner.setFont(new Font("Tahoma", Font.BOLD, 12));
		this.btnSelectionner.setBounds(550, 11, 100, 20);
		this.panelSelRegate.add(btnSelectionner);
	}
	
	public void creationPanelInfoRegate() 
	{
		this.panelInfoRegate = new JPanel();
		this.panelInfoRegate.setBorder(new TitledBorder(null, "R\u00E9gate", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		this.panelInfoRegate.setBounds(10, 79, 370, 234);
		this.window.add(panelInfoRegate);
		this.panelInfoRegate.setLayout(null);
		
		this.lblNomRegate = new JLabel("Nom de la régate : ");
		this.lblNomRegate.setBounds(20, 21, 150, 43);
		
		this.lblDate = new JLabel("Date : ");
		this.lblDate.setBounds(20, 52, 150, 43);
		
		this.lblLieuDepart = new JLabel("Lieu de départ : ");
		this.lblLieuDepart.setBounds(20, 92, 150, 43);
		
		this.lblLieuArrivee = new JLabel("Lieu d'arrivée : ");
		this.lblLieuArrivee.setBounds(20, 130, 150, 43);
		
		this.lblDistance = new JLabel("Distance : ");
		this.lblDistance.setBounds(20, 165, 150, 43);
		
		this.tfNomRegate = new JTextField(15); 
		this.tfNomRegate.setEditable(false);
		this.tfNomRegate.setBounds(160, 29, 185, 26);
		
		this.tfLieuDepart = new JTextField(15);
		this.tfLieuDepart.setEditable(false);
		this.tfLieuDepart.setBounds(160, 100, 185, 26);
		
		this.tfLieuArrivee = new JTextField(15);
		this.tfLieuArrivee.setEditable(false);
		this.tfLieuArrivee.setBounds(160, 138, 185, 26);
		
		this.tfDistance = new JTextField(15);
		this.tfDistance.setEditable(false);
		this.tfDistance.setBounds(160, 173, 185, 26);
		
		this.tfDate = new JTextField();
		this.tfDate.setBounds(160, 64, 118, 26);
		this.tfDate.setEditable(false);
		
		this.panelInfoRegate.add(lblNomRegate);
		this.panelInfoRegate.add(tfNomRegate);
		this.panelInfoRegate.add(lblDate);
		this.panelInfoRegate.add(tfDate);
		this.panelInfoRegate.add(lblLieuDepart);
		this.panelInfoRegate.add(tfLieuDepart);
		this.panelInfoRegate.add(lblLieuArrivee);
		this.panelInfoRegate.add(tfLieuArrivee);
		this.panelInfoRegate.add(lblDistance);
		this.panelInfoRegate.add(tfDistance);
	}
	
	public void creationPanelChrono() 
	{
		this.chrono = new DTimer(this);
		
		this.panelChrono = new JPanel();
		this.panelChrono.setBorder(new TitledBorder(null, "Chronom\u00E8tre", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		this.panelChrono.setBounds(10, 334, 370, 157);
		this.panelChrono.setLayout(null);
		this.window.add(panelChrono);
		
		this.btnFin = new JButton("FIN");
		this.btnFin.addActionListener(new ActionListener() 	
		{
			public void actionPerformed(ActionEvent e) 
			{
				boolean dejaPose = false;
				if (chrono.isRunning()) {
					for (int i=0; i<lesParticipants.size(); i++) {
						if (tableParticipants.getValueAt(i, 4) == null) {
							if (dejaPose == false) {
								int option = JOptionPane.showConfirmDialog(null, "Des participants ne sont pas enregistrés comme arriver. Les déclarer en abandon?", "Reinitialisation chronomètre", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
								dejaPose = true;
								if(option != JOptionPane.OK_OPTION){
									break;
								}
							}
							tableParticipants.setValueAt("Abandon", i, 4);
						}
					}
					chrono.stopDTimer();
					sauvegardeClassement(-1);
				}else {
					JOptionPane.showMessageDialog(null, "le chronomètre ne tourne pas!", "Erreur", JOptionPane.ERROR_MESSAGE);	
				}
			}
		});
		
		this.btnFin.setFont(new Font("Tahoma", Font.BOLD, 12));
		this.btnFin.setBounds(272, 42, 88, 59);
		this.panelChrono.add(btnFin);
		
		this.btnReinit = new JButton("Reinitialiser");
		this.btnReinit.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
			{
				if (chrono.isRunning()) {
					JOptionPane.showMessageDialog(null, "le chronomètre tourne! impossible de le réinitialiser.", "Erreur", JOptionPane.ERROR_MESSAGE);
				}else {
					int option = JOptionPane.showConfirmDialog(null, "Etes-vous sur de vouloir réinitialiser le chronomètre?", "Reinitialisation chronomètre", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
								
					if(option == JOptionPane.OK_OPTION){
						chrono.reinitDTimer();
						lblChrono.setText("00:00:00");
					}
				}
			}
		});
		
		this.btnReinit.setFont(new Font("Tahoma", Font.BOLD, 12));
		this.btnReinit.setBounds(171, 112, 189, 23);
		this.btnReinit.setToolTipText("");
		this.panelChrono.add(btnReinit);
		
		this.lblChrono = new JLabel("00:00:00");
		this.lblChrono.setFont(new Font("Tahoma", Font.PLAIN, 37));
		this.lblChrono.setBounds(10, 42, 171, 76);
		this.panelChrono.add(lblChrono);	
		
		this.btnDepart = new JButton("DEPART");
		this.btnDepart.setFont(new Font("Tahoma", Font.BOLD, 12));
		this.btnDepart.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				System.out.println(lblChrono.getText());
				if (tableParticipants.getValueAt(0, 0) != null && lblChrono.getText() == "00:00:00") {
					chrono.startDTimer();
				}else if (lblChrono.getText() != "00:00:00") {
					JOptionPane.showMessageDialog(null, "le chronomètre n'est pas à 0. Veuillez le réinitialiser.", "Erreur", JOptionPane.ERROR_MESSAGE);
				}else {
					JOptionPane.showMessageDialog(null, "Aucune régate n'est selectionné ou aucun participant n'est renseigné.", "Erreur", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		this.btnDepart.setBounds(174, 42, 88, 59);
		this.panelChrono.add(btnDepart);
	}
	
	public void creationPanelTableau() 
	{	
		this.panelTableParticipant = new JPanel();
		this.panelTableParticipant.setBorder(new TitledBorder(null, "Participants", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		this.panelTableParticipant.setBounds(404, 79, 370, 412);
		this.panelTableParticipant.setLayout(null);
		this.window.add(panelTableParticipant);
		
		this.scrollPane = new JScrollPane();
		this.scrollPane.setBounds(10, 20, 350, 383);
		this.panelTableParticipant.add(scrollPane);
		
		this.tableParticipants = new JTable(20,5);
		this.tableParticipants.setBorder(new LineBorder(new Color(0, 0, 0)));
		this.scrollPane.setViewportView(tableParticipants);
		this.tableParticipants.setFillsViewportHeight(true);
		this.tableParticipants.setModel(new DefaultTableModel(new Object[][] 
				{
				{null, null, "✔", "✘", null},
				{null, null, "✔", "✘", null},
				{null, null, "✔", "✘", null},
				{null, null, "✔", "✘", null},
				{null, null, "✔", "✘", null},
				{null, null, "✔", "✘", null},
				{null, null, "✔", "✘", null},
				{null, null, "✔", "✘", null},
				{null, null, "✔", "✘", null},
				{null, null, "✔", "✘", null},
				{null, null, "✔", "✘", null},
				{null, null, "✔", "✘", null},
				{null, null, "✔", "✘", null},
				{null, null, "✔", "✘", null},
				{null, null, "✔", "✘", null},
				{null, null, "✔", "✘", null},
				{null, null, "✔", "✘", null},
				{null, null, "✔", "✘", null},
				{null, null, "✔", "✘", null},
				{null, null, "✔", "✘", null},
			},new String[] {"Participant", "Voilier", "Arriv\u00E9e", "Abandon", "Temps"}) 
		{
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] {false, false, true, true, false};
			
			public boolean isCellEditable(int row, int column) 
			{
				return columnEditables[column];
			}
		});
		
		tableParticipants.getColumn("Arriv\u00E9e").setCellRenderer(new ButtonRenderer());
		tableParticipants.getColumn("Arriv\u00E9e").setCellEditor(new ButtonEditor(new JCheckBox(), this));
		tableParticipants.getColumn("Abandon").setCellRenderer(new ButtonRenderer());
		tableParticipants.getColumn("Abandon").setCellEditor(new ButtonEditor(new JCheckBox(), this));
		
		this.tableParticipants.getColumnModel().getColumn(2).setPreferredWidth(40);
		this.tableParticipants.getColumnModel().getColumn(3).setPreferredWidth(40);
		this.tableParticipants.getColumnModel().getColumn(4).setPreferredWidth(40);
		this.tableParticipants.getColumnModel().getColumn(1).setPreferredWidth(40);
		this.tableParticipants.setRowHeight(18);
	}
	
	public void setChrono(int timeCount) 
	{
		this.lblChrono.setText(df.format(timeCount - 3.6 * Math.pow(10,6)));
	}
	
	public void setTime(boolean arrivee) 
	{
		int ligne = tableParticipants.getSelectedRow();
			if ((chrono.isRunning()) && (tableParticipants.getValueAt(ligne, 4) == null) && tableParticipants.getValueAt(ligne, 0) != null) 
			{
				if (arrivee) 
				{
					tableParticipants.setValueAt(df.format(chrono.getTime() - 3.6 * Math.pow(10,6)), ligne, 4);
				}else 
				{
					tableParticipants.setValueAt("Abandon", ligne, 4);
				}
				}else if  (tableParticipants.getValueAt(ligne, 4) != null)
				{
					JOptionPane.showMessageDialog(null, "Ce participant est déjà arrivé ou a abandoné", "Erreur", JOptionPane.ERROR_MESSAGE);
				}else if  (!chrono.isRunning())
				{
					JOptionPane.showMessageDialog(null, "Le chronomètre n'est pas lancé", "Erreur", JOptionPane.ERROR_MESSAGE);
				}
			}
	
	public void chargerInfoRegate() 
	{
		tfNomRegate.setText(maBdd.getlisteRegate().get(cboSelRegate.getSelectedIndex()).getNomRegate()); 
		tfLieuDepart.setText(maBdd.getlisteRegate().get(cboSelRegate.getSelectedIndex()).getLieuDepart()); 
		tfLieuArrivee.setText(maBdd.getlisteRegate().get(cboSelRegate.getSelectedIndex()).getLieuArrive()); 
		tfDistance.setText(Integer.toString(maBdd.getlisteRegate().get(cboSelRegate.getSelectedIndex()).getDistance())); 
		tfDate.setText(maBdd.getlisteRegate().get(cboSelRegate.getSelectedIndex()).getDateRegate()); 
	}
	
	public void ajoutParticipantsTableau() 
	{
		lesParticipants = new ArrayList<Participant>();
		lesParticipants = maBdd.getParticipantRegate(regateCourante.getIdRegate());
		System.out.println(lesParticipants.size());
		for (int i=0; i < lesParticipants.size(); i++) 
		{
			tableParticipants.setValueAt(lesParticipants.get(i).getNom() + " " + lesParticipants.get(i).getPrenom(), i, 0);
			tableParticipants.setValueAt(lesParticipants.get(i).getnomVoilier(), i, 1);
			
		}
		regateCourante = maBdd.getlisteRegate().get(cboSelRegate.getSelectedIndex());
	}
	
	public void sauvegardeClassement(int pos) 
	{
		int idRegate = cboSelRegate.getSelectedIndex();
		
		
		for (int i=0; i<lesParticipants.size(); i++) 
		{
			int idParticipant = lesParticipants.get(i).getIdParticipant();
			String temps = (String)tableParticipants.getValueAt(i, 4);
			String tempsCompense;
			if (temps == "Abandon") {
				tempsCompense = "Abandon";
				
			}else {
				tempsCompense = calculTempsCompense(temps, lesParticipants.get(i).getRating(), regateCourante.getDistance());	
			}
			maBdd.sqlUpdateClassement(idRegate, idParticipant, pos, temps,tempsCompense);
			maBdd.sqlUpdateRegate(regateCourante.getIdRegate(), 1);
		}
		classementDesParticipants(idRegate);
	}
	
	public String calculTempsCompense(String temps, int rating, int distance) 
	{
		long secondes, secondesCompense;
		try {
			if (temps != "Abandon") {
			secondes = (long)((double) (df.parse(temps).getTime()/1000)+3600);
			secondesCompense = (long) (secondes + (5143 / (Math.sqrt(rating)+3.5)*distance));
			Date tempsCompense = new Date(secondesCompense*1000 - (long)(3.6 * Math.pow(10,6)));
			return df.format(tempsCompense);
			}else {
				return "Abandon";
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void classementDesParticipants(int idRegate) {
		ArrayList<ArrayList<String>> listeParticipant = maBdd.getClassementRegate(idRegate);
		for (int i = 0; i < listeParticipant.size(); i++) {
			for (int j = 0; j < listeParticipant.size(); j++) {
				// personne n'a rien vu
				try {
					if (listeParticipant.get(j).get(3).equals("Abandon") || listeParticipant.get(i).get(3).equals("Abandon")) {
							ArrayList<String> temporaire = listeParticipant.get(i);
							listeParticipant.set(i, listeParticipant.get(j));
							listeParticipant.set(j, temporaire);
					}else {
						System.out.println(listeParticipant.get(j).get(3));
						if ((long)((double) (df.parse(listeParticipant.get(i).get(3)).getTime()/1000)+3600) < (long)((double) (df.parse(listeParticipant.get(j).get(3))).getTime()/1000)+3600) {
							ArrayList<String> temporaire = listeParticipant.get(i);
							listeParticipant.set(i, listeParticipant.get(j));
							listeParticipant.set(j, temporaire);
						}
					}
				} catch (ParseException e) {
					e.printStackTrace();
				}
				
			}
		}
		int nbrAbandon = 0;
		for (int i = 0; i < listeParticipant.size(); i++) {
			if (listeParticipant.get(i).get(3).equals("Abandon")) {
				maBdd.sqlUpdateClassement(idRegate, Integer.parseInt(listeParticipant.get(i).get(0)), 0);
				nbrAbandon +=1;
			}else {
			maBdd.sqlUpdateClassement(idRegate, Integer.parseInt(listeParticipant.get(i).get(0)), i+1-nbrAbandon);
		}
		}
		System.out.println(listeParticipant.get(1).get(0));
	}
}