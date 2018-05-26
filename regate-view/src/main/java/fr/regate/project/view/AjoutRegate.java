package fr.regate.project.view;

import com.toedter.calendar.JDateChooser;

import java.awt.Color;
import java.awt.Font;
import java.awt.Window;
import java.util.ArrayList;
import java.util.Date;
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
	private JLabel lblNameRegate, lblDate, lblPlaceDeparture, lblPlaceArrival, lblDistance, lblSelRegate, lblIdRegate;
	private JTextField tfNameRegate, tfPlaceDeparture, tfPlaceArrival, tfDistance, tfIdRegate;
	private Window window;
	private JButton btnSendNewRegate, btnSendModifRegate, btnAjoutParticipant, btnSelRegate;
	private JScrollPane scrollPane;
	private JTable tableParticipants;
	private JComboBox<String> cboSelParticipant, cboSelShip, cboSelRegate;
	private JDateChooser dateChooser;

	public AjoutRegate(Window window) 
	{
		this.window = window;
		btnSendModifRegate = new JButton("Enregistrer");
		this.btnSendNewRegate = new JButton("Enregistrer");
		btnAjoutParticipant  = new JButton("Ajouter");
		btnSelRegate = new JButton("Selectionner");
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


		this.dateChooser = new JDateChooser();
		this.dateChooser.setBounds(145, 120, 185, 26);


		
		this.tfPlaceDeparture = new JTextField(15);
		this.tfPlaceDeparture.setBounds(145, 160, 185, 26);
		
		this.tfPlaceArrival = new JTextField(15);
		this.tfPlaceArrival.setBounds(145, 200, 185, 26);
		
		this.tfDistance = new JTextField(15);
		this.tfDistance.setBounds(145, 240, 185, 26);
		
		this.panelInfoRegate.add(lblNameRegate);
		this.panelInfoRegate.add(tfNameRegate);
		this.panelInfoRegate.add(lblDate);
		this.panelInfoRegate.add(lblPlaceDeparture);
		this.panelInfoRegate.add(tfPlaceDeparture);
		this.panelInfoRegate.add(lblPlaceArrival);
		this.panelInfoRegate.add(tfPlaceArrival);
		this.panelInfoRegate.add(lblDistance);
		this.panelInfoRegate.add(tfDistance);
		this.panelInfoRegate.add(dateChooser);
	}
	
	public void createBtnSendNewRegate() {
		this.btnSendNewRegate.setFont(new Font("Tahoma", Font.BOLD, 12));
		this.btnSendNewRegate.setBounds(300, 475, 120, 20);
		
		this.panelTitreRegate.add(btnSendNewRegate);
	}
	
	public void createBtnSendModifRegate() {
		
		btnSendModifRegate.setFont(new Font("Tahoma", Font.BOLD, 12));
		this.btnSendModifRegate.setBounds(300, 475, 120, 20);
		panelTitreRegate.add(btnSendModifRegate);
		
	}
	
	public void creationPanelParticipants() {
		
		this.panelTableParticipant = new JPanel();
		this.panelTableParticipant.setBorder(new TitledBorder(null, "Participants", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		this.panelTableParticipant.setBounds(404, 120, 370, 340);
		this.panelTableParticipant.setLayout(null);
		this.window.add(panelTableParticipant);
		this.cboSelParticipant.setBounds(10, 20, 161, 20);
		this.panelTableParticipant.add(cboSelParticipant);
		
		this.cboSelShip.setBounds(180, 20, 161, 20);
		this.panelTableParticipant.add(cboSelShip);
		
		
		btnAjoutParticipant.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnAjoutParticipant.setBounds(100, 50, 120, 20);
		this.panelTableParticipant.add(btnAjoutParticipant);

		this.scrollPane = new JScrollPane();
		this.scrollPane.setBounds(10, 80, 350, 285);
		this.panelTableParticipant.add(scrollPane);
		
		this.tableParticipants = new JTable(20,5);
		this.tableParticipants.setBorder(new LineBorder(new Color(0, 0, 0)));
		this.scrollPane.setViewportView(tableParticipants);
		this.tableParticipants.setFillsViewportHeight(true);
		this.tableParticipants.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
			},
			new String[] {"idP", "Nom", "Prénom", "idB", "Voilier", "Catégorie", "Rating"}
		) {

			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] {
				false, true, true, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		
		this.tableParticipants.getColumnModel().getColumn(0).setPreferredWidth(0);
		this.tableParticipants.getColumnModel().getColumn(1).setPreferredWidth(50);
		this.tableParticipants.getColumnModel().getColumn(2).setPreferredWidth(50);
		this.tableParticipants.getColumnModel().getColumn(3).setPreferredWidth(0);
		this.tableParticipants.getColumnModel().getColumn(4).setPreferredWidth(50);
		this.tableParticipants.getColumnModel().getColumn(5).setPreferredWidth(20);
		this.tableParticipants.getColumnModel().getColumn(6).setPreferredWidth(20);
		
		
		this.tableParticipants.setRowHeight(18);
	}
	
	public void creationPanelTitre(String titre) 
	{
		this.panelTitreRegate = new JPanel();
		this.panelTitreRegate.setBounds(10, 11, 764, 600);
		this.panelTitreRegate.setLayout(null);
		this.window.add(panelTitreRegate);
		
		this.lblSelRegate = new JLabel(titre);
		this.lblSelRegate.setHorizontalAlignment(SwingConstants.CENTER);
		this.lblSelRegate.setFont(new Font("Tahoma", Font.BOLD, 40));
		this.lblSelRegate.setBounds(10, 14, 744, 32);
		this.panelTitreRegate.add(lblSelRegate);
	}
	
	public void modifRegate(String[] listeRegate) 
	{
		this.lblSelRegate = new JLabel("Selectionner la régate à modifier : ");
		this.lblSelRegate.setBounds(175, 68, 380, 14);
		this.panelTitreRegate.add(lblSelRegate);
		
		this.cboSelRegate = new JComboBox<String>(listeRegate);
		this.cboSelRegate.setBounds(383, 65, 161, 20);
		this.panelTitreRegate.add(cboSelRegate);
		
		this.lblIdRegate = new JLabel("ID de la régate : ");
		this.lblIdRegate.setBounds(29, 30, 116, 43);
		
		this.tfIdRegate = new JTextField(15); 
		this.tfIdRegate.setBounds(145, 40, 185, 26);
		tfIdRegate.setEditable(false);
		
		panelInfoRegate.add(lblIdRegate);
		panelInfoRegate.add(tfIdRegate);
		
		btnSelRegate.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnSelRegate.setBounds(550, 65, 120, 20);
		this.panelTitreRegate.add(btnSelRegate);
		
	}
	public JButton getBtnSelRegate(){
		return btnSelRegate;
	}
	
	
	
	public void setCboSelParticipant(String[] value) {
		cboSelParticipant = new JComboBox<String>(value);
	}
	public JComboBox<String> getCboSelParticipant() {
		return cboSelParticipant;
	}
	
	public JComboBox<String> getCboSelShip(){
		return cboSelShip;
	}
	
	public JComboBox<String> getCboSelRegateToModif(){
		return cboSelRegate;
	}
	public void setCboSelShip(String[] value) {
		cboSelShip = new JComboBox<String>(value);
	}
	
	public JButton getBtnSendAjoutRegate() {
		return btnSendNewRegate;
	}
	
	public JButton getBtnSendModifRegate() {
		return btnSendModifRegate;
	}
	public JButton getBtnAddParticipant() {
		return btnAjoutParticipant;
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

	public Date getDateRegate() {
		return dateChooser.getDate();
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
	
	public String getIdRegate() {
		return tfIdRegate.getText();
	}
	
	public void setIdRegate(String value) {
		tfIdRegate.setText(value);
	}
	
	public JTable getTableParticipants() {
		return tableParticipants;
	}
	public void setTableParticipants(String monString, int row, int column) {
		tableParticipants.setValueAt(monString, row, column);
	}

	public void setDate(Date date) {
		dateChooser.setDate(date);

	}
	

	
}
