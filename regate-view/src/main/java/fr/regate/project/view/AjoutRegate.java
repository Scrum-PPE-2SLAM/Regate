package fr.regate.project.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Window;
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


public class AjoutRegate extends JFrame 
{
	private static final long serialVersionUID = 1L;
	private JPanel panelInfoRegate, panelTableParticipant;
	JPanel panelTitreRegate;
	private JLabel lblNameRegate, lblDate, lblPlaceDeparture, lblPlaceArrival, lblDistance, lblSelRegate;
	private JTextField tfNameRegate, tfPlaceDeparture, tfPlaceArrival, tfDistance;
	private Window window;
	private JButton btnSend;
	private JScrollPane scrollPane;
	private JTable tableParticipants;
	private JComboBox<String> cboSelParticipant;

	public AjoutRegate(Window window) 
	{
		this.window = window;
		
		this.btnSend = new JButton("Enregistrer");
	}
	
	public void creationPanelAjoutRegate() 
	{
		this.panelInfoRegate = new JPanel();
		this.panelInfoRegate.setBorder(new TitledBorder(null, "R\u00E9gate", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		this.panelInfoRegate.setBounds(10, 120, 370, 340);
		this.panelInfoRegate.setLayout(null);
		this.window.add(panelInfoRegate);
		
		this.lblNameRegate = new JLabel("Nom de la régate : ");
		this.lblNameRegate.setBounds(29, 70, 116, 43);
		
		this.lblDate = new JLabel("Date : ");
		this.lblDate.setBounds(97, 110, 58, 53);
		
		this.lblPlaceDeparture = new JLabel("Lieu de départ : ");
		this.lblPlaceDeparture.setBounds(40, 150, 99, 43);
		
		this.lblPlaceArrival = new JLabel("Lieu d'arrivée : ");
		this.lblPlaceArrival.setBounds(40, 190, 94, 43);
		
		this.lblDistance = new JLabel("Distance : ");
		this.lblDistance.setBounds(68, 230, 77, 43);
		
		
		this.tfNameRegate = new JTextField(15); 
		this.tfNameRegate.setBounds(145, 80, 185, 26);
		
		this.tfPlaceDeparture = new JTextField(15);
		this.tfPlaceDeparture.setBounds(145, 160, 185, 26);
		
		this.tfPlaceArrival = new JTextField(15);
		this.tfPlaceArrival.setBounds(145, 200, 185, 26);
		
		this.tfDistance = new JTextField(15);
		this.tfDistance.setBounds(145, 240, 185, 26);
		
		//this.cboDate = new JDateChooser();
		//this.cboDate.setBounds(145, 120, 118, 26);
	
		this.btnSend.setFont(new Font("Tahoma", Font.BOLD, 12));
		this.btnSend.setBounds(150, 290, 120, 20);
		
		this.panelInfoRegate.add(btnSend);
		this.panelInfoRegate.add(lblNameRegate);
		this.panelInfoRegate.add(tfNameRegate);
		this.panelInfoRegate.add(lblDate);
		//this.panelInfoRegate.add(cboDate);
		this.panelInfoRegate.add(lblPlaceDeparture);
		this.panelInfoRegate.add(tfPlaceDeparture);
		this.panelInfoRegate.add(lblPlaceArrival);
		this.panelInfoRegate.add(tfPlaceArrival);
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
		this.cboSelParticipant.setBounds(10, 20, 161, 20);
		this.panelTableParticipant.add(cboSelParticipant);
		
		JButton btnAjout = new JButton("Ajout Participant");
		
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
	
	public void setCboSelParticipant(String[] value) {
		cboSelParticipant = new JComboBox<String>(value);
	}
	public JComboBox<String> getCboSelParticipant() {
		return cboSelParticipant;
	}
	
	public JButton getBtnSend() {
		return btnSend;
	}
	public String getNameRegate() {
		return tfNameRegate.getText();
	}
	public String getPlaceDeparture() {
		return tfPlaceDeparture.getText();
	}
	public String getPlaceArrival() {
		return tfPlaceArrival.getText();
	}
	public int getDistance() {
		return Integer.parseInt(tfDistance.getText());
	}
	
	public void setNameRegate(String value) {
		tfNameRegate.setText(value);
	}
	public void setPlaceDeparture(String value) {
		tfPlaceDeparture.setText(value);
	}
	public void setPlaceArrival(String value) {
		tfPlaceArrival.setText(value);
	}
	public void setDistance(String value) {
		tfDistance.setText(value);
	}
	
	public JTable getTableParticipants() {
		return tableParticipants;
	}
	public void setTableParticipants() {
		
	}
	

	
}
