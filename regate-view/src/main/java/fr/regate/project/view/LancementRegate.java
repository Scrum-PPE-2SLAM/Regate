package fr.regate.project.view;

import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.*;

public class LancementRegate extends JFrame
{
    private static final long serialVersionUID = 1L;
    private JTable tableParticipants;
    private JLabel lblChrono;
    private JTextField tfNameRegate, tfStartPoint, tfEndPoint, tfDistance;
    private JTextField tfDate;
    private Window window;
    private SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");


    public LancementRegate(Window window)
    {
        this.window = window;
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
        JPanel panelSelRegate = new JPanel();
        panelSelRegate.setBounds(10, 11, 764, 57);
        panelSelRegate.setLayout(null);
        this.window.add(panelSelRegate);

        JLabel lblSelRegate = new JLabel("Selectionner la régate à lancer : ");
        lblSelRegate.setBounds(190, 14, 220, 14);
        panelSelRegate.add(lblSelRegate);

        this.cboSelRegate = new JComboBox<String>();
        for (int i=0; i<maBdd.getlisteRegate().size(); i++) {
            //if (maBdd.getlisteRegate().get(i).getEtat() == 0)
            cboSelRegate.addItem(maBdd.getlisteRegate().get(i).getNomRegate());
        }
        this.cboSelRegate.setBounds(383, 11, 161, 20);
        panelSelRegate.add(cboSelRegate);

        JButton btnSelect = new JButton("Valider");
        btnSelect.addActionListener(new ActionListener()
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
        this.chrono = new DTimer(this);

        JPanel panelChrono = new JPanel();
        panelChrono.setBorder(new TitledBorder(null, "Chronom\u00E8tre", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panelChrono.setBounds(10, 334, 370, 157);
        panelChrono.setLayout(null);
        this.window.add(panelChrono);

        JButton btnEnd = new JButton("FIN");
        btnEnd.addActionListener(new ActionListener()
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

        btnEnd.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnEnd.setBounds(272, 42, 88, 59);
        panelChrono.add(btnEnd);

        JButton btnReinit = new JButton("Reinitialiser");
        btnReinit.addActionListener(new ActionListener()
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

        btnReinit.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnReinit.setBounds(171, 112, 189, 23);
        btnReinit.setToolTipText("");
        panelChrono.add(btnReinit);

        this.lblChrono = new JLabel("00:00:00");
        this.lblChrono.setFont(new Font("Tahoma", Font.PLAIN, 37));
        this.lblChrono.setBounds(10, 42, 171, 76);
        panelChrono.add(lblChrono);

        JButton btnStart = new JButton("DEPART");
        btnStart.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnStart.addActionListener(new ActionListener()
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
        tfNameRegate.setText(maBdd.getlisteRegate().get(cboSelRegate.getSelectedIndex()).getNomRegate());
        tfStartPoint.setText(maBdd.getlisteRegate().get(cboSelRegate.getSelectedIndex()).getLieuDepart());
        tfEndPoint.setText(maBdd.getlisteRegate().get(cboSelRegate.getSelectedIndex()).getLieuArrive());
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
