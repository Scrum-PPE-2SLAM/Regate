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
    private JTextField tfNameRegate, tfStartPoint, tfEndPoint, tfDistance;
    private JTextField tfDate;
    private Window window;
    private JButton btnSelect, btnEnd, btnReinit, btnStart;

    public LancementRegate(Window window)
    {
        this.window = window;
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
        JPanel panelSelRegate = new JPanel();
        panelSelRegate.setBounds(10, 11, 764, 57);
        panelSelRegate.setLayout(null);
        this.window.add(panelSelRegate);

        JLabel lblSelRegate = new JLabel("Selectionner la régate à lancer : ");
        lblSelRegate.setBounds(190, 14, 220, 14);
        panelSelRegate.add(lblSelRegate);

        JComboBox<String> cboSelRegate = new JComboBox<String>();
        cboSelRegate.setBounds(383, 11, 161, 20);
        panelSelRegate.add(cboSelRegate);

        btnSelect = new JButton("Valider");

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

        JLabel lblNameRegate = new JLabel("Nom de la régate : ");
        lblNameRegate.setBounds(20, 21, 150, 43);

        JLabel lblDate = new JLabel("Date : ");
        lblDate.setBounds(20, 52, 150, 43);

        JLabel lbStartPoint = new JLabel("Lieu de départ : ");
        lbStartPoint.setBounds(20, 92, 150, 43);

        JLabel lblEndPoint = new JLabel("Lieu d'arrivée : ");
        lblEndPoint.setBounds(20, 130, 150, 43);

        JLabel lblDistance = new JLabel("Distance : ");
        lblDistance.setBounds(20, 165, 150, 43);

        this.tfNameRegate = new JTextField(15);
        this.tfNameRegate.setEditable(false);
        this.tfNameRegate.setBounds(160, 29, 185, 26);

        this.tfStartPoint = new JTextField(15);
        this.tfStartPoint.setEditable(false);
        this.tfStartPoint.setBounds(160, 100, 185, 26);

        this.tfEndPoint = new JTextField(15);
        this.tfEndPoint.setEditable(false);
        this.tfEndPoint.setBounds(160, 138, 185, 26);

        this.tfDistance = new JTextField(15);
        this.tfDistance.setEditable(false);
        this.tfDistance.setBounds(160, 173, 185, 26);

        this.tfDate = new JTextField();
        this.tfDate.setBounds(160, 64, 118, 26);
        this.tfDate.setEditable(false);

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

        btnEnd = new JButton("FIN");

        btnEnd.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnEnd.setBounds(272, 42, 88, 59);
        panelChrono.add(btnEnd);

        btnReinit = new JButton("Reinitialiser");

        btnReinit.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnReinit.setBounds(171, 112, 189, 23);
        btnReinit.setToolTipText("");
        panelChrono.add(btnReinit);

        this.lblChrono = new JLabel("00:00:00");
        this.lblChrono.setFont(new Font("Tahoma", Font.PLAIN, 37));
        this.lblChrono.setBounds(10, 42, 171, 76);
        panelChrono.add(lblChrono);

        btnStart = new JButton("DEPART");
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
    public boolean regateIsLoad() {
        for (int i = 0; i < 20; i++) {
            if (tableParticipants.getValueAt(i, 4) != null)
                return true;
        }
        return false;
    }

    public void reinitTab() {
        for (int i = 0; i<20; i++) {
            tableParticipants.setValueAt(null, i, 0);
            tableParticipants.setValueAt(null, i, 1);
            tableParticipants.setValueAt(null, i, 4);
        }
    }

    public void updateChrono(int timeCount, SimpleDateFormat df)
    {
        this.lblChrono.setText(df.format(timeCount - 3.6 * Math.pow(10,6)));
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
}
