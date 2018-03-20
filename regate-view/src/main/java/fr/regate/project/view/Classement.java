import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

public class Classement extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private Window window;
	private JPanel panelSelRegate;
	private JLabel lblSelRegate;
	private JTable tableClassement;
	private JComboBox<String> cboSelRegate;
	private Bdd maBdd;
	private JButton btnSelectionner;
	private ArrayList<Participant> lesParticipants; 
	private ArrayList<ArrayList<String>> leClassement;
	
	public Classement(Window window, Bdd maBdd){
		
		this.window = window;
		this.maBdd = maBdd;
	}
	
	public void createClassement(){	
		JPanel paneltableClassement = new JPanel();
		paneltableClassement.setBorder(new TitledBorder(null, "Classement", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		paneltableClassement.setBounds(10, 91, 765, 400);
		window.add(paneltableClassement);
		paneltableClassement.setLayout(null);
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 20, 700, 357);
		paneltableClassement.add(scrollPane);
		
		
		tableClassement = new JTable(20,5);
		tableClassement.setBorder(new LineBorder(new Color(0, 0, 0)));
		scrollPane.setViewportView(tableClassement);
		tableClassement.setFillsViewportHeight(true);
		tableClassement.setModel(new DefaultTableModel(
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
			new String[] {"Position", "Nom", "Prenom", "Temp r\u00E9el", "Temp compens\u00E9"}
		) {

			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		
		tableClassement.getColumnModel().getColumn(0).setPreferredWidth(1);
		tableClassement.getColumnModel().getColumn(1).setPreferredWidth(70);
		tableClassement.getColumnModel().getColumn(2).setPreferredWidth(70);
		tableClassement.getColumnModel().getColumn(3).setPreferredWidth(70);
		tableClassement.getColumnModel().getColumn(4).setPreferredWidth(70);
	}
	
	public void createPanelSelRegate(String categorie) {
			
			panelSelRegate = new JPanel();
			panelSelRegate.setBounds(10, 11, 764, 57);
			window.add(panelSelRegate);
			panelSelRegate.setLayout(null);
			
			lblSelRegate = new JLabel("Classement "+ categorie + ": ");
			lblSelRegate.setBounds(217, 14, 156, 14);
			panelSelRegate.add(lblSelRegate);
			
			cboSelRegate = new JComboBox<String>(maBdd.getListeNomRegate().toArray(new String[0]));
			cboSelRegate.setBounds(383, 11, 161, 20);
			panelSelRegate.add(cboSelRegate);
			
			this.btnSelectionner = new JButton("Valider");
			this.btnSelectionner.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					for (int i=0; i<20; i++) {
						tableClassement.setValueAt("",i,0);
						tableClassement.setValueAt("",i,1);
						tableClassement.setValueAt("",i,2);
						tableClassement.setValueAt("",i,3);
						tableClassement.setValueAt("",i,4);
					}
					ajoutClassement();
				}
			});
			this.btnSelectionner.setFont(new Font("Tahoma", Font.BOLD, 12));
			this.btnSelectionner.setBounds(550, 11, 100, 20);
			this.panelSelRegate.add(btnSelectionner);
		}
	
	
	public void ajoutClassement(){
		
int pos = 0;
		lesParticipants = new ArrayList<Participant>();
		lesParticipants = maBdd.getParticipantRegate(maBdd.getlisteRegate().get(cboSelRegate.getSelectedIndex()).getIdRegate());
		leClassement = new ArrayList<ArrayList<String>>();
		leClassement = maBdd.getClassementRegate(maBdd.getlisteRegate().get(cboSelRegate.getSelectedIndex()).getIdRegate());
		for (int i=0; i <= lesParticipants.size(); i++) {
			for (int j=0; j < lesParticipants.size(); j++) {
				if (Integer.parseInt(leClassement.get(j).get(2)) == i+1) {
					tableClassement.setValueAt(lesParticipants.get(j).getNom(), i, 1);
					tableClassement.setValueAt(lesParticipants.get(j).getPrenom(), i, 2);
					
					tableClassement.setValueAt(leClassement.get(j).get(1), i, 3);
					tableClassement.setValueAt(leClassement.get(j).get(3), i, 4);
						
					tableClassement.setValueAt(leClassement.get(j).get(2), i, 0);
					pos +=1;
					}
				}
			}
		
		for (int i = 0; i< lesParticipants.size(); i++) {
			if (Integer.parseInt(leClassement.get(i).get(2)) == 0) {
				tableClassement.setValueAt(lesParticipants.get(i).getNom(), pos, 1);
				tableClassement.setValueAt(lesParticipants.get(i).getPrenom(), pos, 2);
				
				tableClassement.setValueAt(leClassement.get(i).get(1), pos, 3);
				tableClassement.setValueAt(leClassement.get(i).get(3), pos, 4);
					
				tableClassement.setValueAt("Abandon", pos, 0);
				pos += 1;
				
			}
		}
	}
}

