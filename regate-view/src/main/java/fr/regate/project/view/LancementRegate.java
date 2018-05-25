package fr.regate.project.view;

import java.awt.*;
import java.awt.Window;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

public class LancementRegate extends JFrame
{
    private static final long serialVersionUID = 1L;
    private JTable tableParticipants;
    private JLabel lblChrono;
    private JTextField tfNameRegate, tfStartPoint, tfEndPoint, tfDistance, tfIdRegate;
    private JTextField tfDate;
    private Window window;
    private JButton btnSelect, btnEnd, btnReinit, btnStart;
    private JComboBox<String> cboSelRegate;
    private JPanel panelSelRegate;
    
    public LancementRegate(Window window)
    {
        this.window = window;
        this.btnStart = new JButton("DEPART");
        this.btnSelect = new JButton("Valider");
        this.btnEnd = new JButton("FIN");
        this.btnReinit = new JButton("Reinitialiser");
        
    }

    public void createAll()
    {
        createPanelSelRegate();
        creationPanelInfoRegate();
        creationPanelChrono();
        creationPanelTableau();
    }

    public void createPanelSelRegate()
    {
        panelSelRegate = new JPanel();
        panelSelRegate.setBounds(10, 11, 764, 57);
        panelSelRegate.setLayout(null);
        this.window.add(panelSelRegate);

        JLabel lblSelRegate = new JLabel("Selectionner la régate à lancer : ");
        lblSelRegate.setBounds(190, 14, 220, 14);
        panelSelRegate.add(lblSelRegate);

        
        

        btnSelect.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnSelect.setBounds(550, 11, 100, 20);
        panelSelRegate.add(btnSelect);
    }

    public void creationPanelInfoRegate()
    {
        JPanel panelInfoRegate = new JPanel();
        panelInfoRegate.setBorder(new TitledBorder(null, "R\u00E9gate", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panelInfoRegate.setBounds(10, 79, 370, 234);

        this.window.add(panelInfoRegate);

        panelInfoRegate.setLayout(null);

        JLabel lblIdRegate = new JLabel("ID régate : ");
        lblIdRegate.setBounds(20, 11, 150, 43);
        
        JLabel lblNameRegate = new JLabel("Nom de la régate : ");
        lblNameRegate.setBounds(20, 45, 150, 43);

        JLabel lblDate = new JLabel("Date : ");
        lblDate.setBounds(20, 80, 150, 43);

        JLabel lbStartPoint = new JLabel("Lieu de départ : ");
        lbStartPoint.setBounds(20, 110, 150, 43);

        JLabel lblEndPoint = new JLabel("Lieu d'arrivée : ");
        lblEndPoint.setBounds(20, 140, 150, 43);

        JLabel lblDistance = new JLabel("Distance : ");
        lblDistance.setBounds(20, 170, 150, 43);

        tfIdRegate = new JTextField(15);
        tfIdRegate.setEditable(false);
        tfIdRegate.setBounds(160, 20, 185, 26);
        
        this.tfNameRegate = new JTextField(15);
        this.tfNameRegate.setEditable(false);
        this.tfNameRegate.setBounds(160, 55, 185, 26);

        this.tfStartPoint = new JTextField(15);
        this.tfStartPoint.setEditable(false);
        this.tfStartPoint.setBounds(160, 120, 185, 26);

        this.tfEndPoint = new JTextField(15);
        this.tfEndPoint.setEditable(false);
        this.tfEndPoint.setBounds(160, 150, 185, 26);

        this.tfDistance = new JTextField(15);
        this.tfDistance.setEditable(false);
        this.tfDistance.setBounds(160, 180, 185, 26);

        this.tfDate = new JTextField();
        this.tfDate.setBounds(160, 91, 118, 26);
        this.tfDate.setEditable(false);

        panelInfoRegate.add(lblIdRegate);
        panelInfoRegate.add(tfIdRegate);
        panelInfoRegate.add(lblNameRegate);
        panelInfoRegate.add(tfNameRegate);
        panelInfoRegate.add(lblDate);
        panelInfoRegate.add(tfDate);
        panelInfoRegate.add(lbStartPoint);
        panelInfoRegate.add(tfStartPoint);
        panelInfoRegate.add(lblEndPoint);
        panelInfoRegate.add(tfEndPoint);
        panelInfoRegate.add(lblDistance);
        panelInfoRegate.add(tfDistance);
    }

    public void creationPanelChrono()
    {
        JPanel panelChrono = new JPanel();
        panelChrono.setBorder(new TitledBorder(null, "Chronom\u00E8tre", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panelChrono.setBounds(10, 334, 370, 157);
        panelChrono.setLayout(null);
        this.window.add(panelChrono);



        btnEnd.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnEnd.setBounds(272, 42, 88, 59);
        panelChrono.add(btnEnd);



        btnReinit.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnReinit.setBounds(171, 112, 189, 23);
        btnReinit.setToolTipText("");
        panelChrono.add(btnReinit);

        this.lblChrono = new JLabel("00:00:00");
        this.lblChrono.setFont(new Font("Tahoma", Font.PLAIN, 37));
        this.lblChrono.setBounds(10, 42, 171, 76);
        panelChrono.add(lblChrono);


        btnStart.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnStart.setBounds(174, 42, 88, 59);
        panelChrono.add(btnStart);
    }

    public void creationPanelTableau()
    {
        JPanel panelTableParticipant = new JPanel();
        panelTableParticipant.setBorder(new TitledBorder(null, "Participants", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panelTableParticipant.setBounds(404, 79, 370, 412);
        panelTableParticipant.setLayout(null);
        this.window.add(panelTableParticipant);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 20, 350, 383);
        panelTableParticipant.add(scrollPane);

        this.tableParticipants = new JTable(20,5);
        this.tableParticipants.setBorder(new LineBorder(new Color(0, 0, 0)));
        scrollPane.setViewportView(tableParticipants);
        //this.tableParticipants.setFillsViewportHeight(true);
        this.tableParticipants.setModel(new DefaultTableModel(new Object[][]
                {
                        {null, null, null, "✔", "✘", null},
                        {null, null, null, "✔", "✘", null},
                        {null, null, null, "✔", "✘", null},
                        {null, null, null, "✔", "✘", null},
                        {null, null, null, "✔", "✘", null},
                        {null, null, null, "✔", "✘", null},
                        {null, null, null, "✔", "✘", null},
                        {null, null, null, "✔", "✘", null},
                        {null, null, null, "✔", "✘", null},
                        {null, null, null, "✔", "✘", null},
                        {null, null, null, "✔", "✘", null},
                        {null, null, null, "✔", "✘", null},
                        {null, null, null, "✔", "✘", null},
                        {null, null, null, "✔", "✘", null},
                        {null, null, null, "✔", "✘", null},
                        {null, null, null, "✔", "✘", null},
                        {null, null, null, "✔", "✘", null},
                        {null, null, null, "✔", "✘", null},
                        {null, null, null, "✔", "✘", null},
                        {null, null, null, "✔", "✘", null},
                },new String[] {"ID","Participant", "Voilier", "Arriv\u00E9e", "Abandon", "Temps"})
        {
            private static final long serialVersionUID = 1L;
            boolean[] columnEditables = new boolean[] {false, false, false, true, true, false};

            public boolean isCellEditable(int row, int column)
            {
                return columnEditables[column];
            }
        });


        this.tableParticipants.getColumnModel().getColumn(0).setPreferredWidth(5);
        this.tableParticipants.getColumnModel().getColumn(1).setPreferredWidth(30);
        this.tableParticipants.getColumnModel().getColumn(2).setPreferredWidth(30);
        this.tableParticipants.getColumnModel().getColumn(3).setPreferredWidth(20);
        this.tableParticipants.getColumnModel().getColumn(4).setPreferredWidth(20);
        this.tableParticipants.getColumnModel().getColumn(5).setPreferredWidth(30);
        this.tableParticipants.setRowHeight(18);
    }
    public boolean regateIsLoad() {
        for (int i = 0; i < 20; i++) {
            if (tableParticipants.getValueAt(i, 4) != null)
                return true;
        }
        return false;
    }



    public void updateChrono(int timeCount, SimpleDateFormat df)
    {
        this.lblChrono.setText(df.format(timeCount - 3.6 * Math.pow(10,6)));
    }
    
    public void setCboSelRegate(String[] listeRegate) {
    	cboSelRegate = new JComboBox<String>(listeRegate);
    	cboSelRegate.setBounds(383, 11, 161, 20);
        panelSelRegate.add(cboSelRegate);
    }
    
    public JComboBox<String> getCboSelRegate(){
    	return cboSelRegate;
    }

    public JButton getBtnSelect() {
        return btnSelect;
    }

    public JButton getBtnEnd() {
        return btnEnd;
    }

    public JButton getBtnReinit() {
        return btnReinit;
    }

    public JButton getBtnStart() {
        return btnStart;
    }

    public ArrayList<JButton> getAllBtn() {
        ArrayList<JButton> btnList = new ArrayList<JButton>();
        btnList.add(btnEnd);
        btnList.add(btnReinit);
        btnList.add(btnSelect);
        btnList.add(btnStart);
        return btnList;
    }

    public JLabel getLblChrono() {
        return lblChrono;
    }

    public void setLblChrono(String chrono) {
        lblChrono.setText(chrono);
    }
    
    public void setIdRegate(String idRegate) {
    	tfIdRegate.setText(idRegate);
    }
    
    public String getIdRegate() {
    	return tfIdRegate.getText();
    }
    public void setNameRegate(String nameRegate) {
    	tfNameRegate.setText(nameRegate);
    }
    public void setPlaceDeparture(String placeDeparture) {
    	tfStartPoint.setText(placeDeparture);
    }
    public void setPlaceArrival(String endPlace) {
    	tfEndPoint.setText(endPlace);
    }
    public void setDistance(String distance) {
    	tfDistance.setText(distance);
    }
    
    public JTable getTableParticipants() {
		return tableParticipants;
	}
	public void setTableParticipants(String monString, int row, int column) {
		tableParticipants.setValueAt(monString, row, column);
	}
}
