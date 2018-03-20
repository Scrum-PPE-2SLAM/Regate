package fr.regate.project.view;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.border.EmptyBorder;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;


public class Window extends JFrame {

	private static final long serialVersionUID = 1L;
	private String name;
	private int height;
	private int length;
	private JPanel contentPane;

	public Window(String name, int height, int length, ArrayList<String> listRegate, ArrayList<String> listType) {
		this.name = name;
		this.height = height;
		this.length = length;
	}
		
	public void createWindow() {
		setTitle(name);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, height, length);
		contentPane = new JPanel();
		reinitContentPane();
	}
	
	public void createMenu() {
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("Fichier");
		menuBar.add(mnFile);
		
		JMenuItem mntmSave = new JMenuItem("Enregistrer");
		mnFile.add(mntmSave);
		
		JMenuItem mntmQuit = new JMenuItem("Quitter");
		mnFile.add(mntmQuit);
		
		JMenu mnRegate = new JMenu("Régate");
		menuBar.add(mnRegate);
		
		JMenuItem mntmNewRegate = new JMenuItem("Nouvelle régate");
		mnRegate.add(mntmNewRegate);
		
		JMenuItem mntmModifyRegate = new JMenuItem("Modifier régate");
		mnRegate.add(mntmModifyRegate);
		
		JMenuItem mntmDeleteRgate = new JMenuItem("Supprimer régate");
		mnRegate.add(mntmDeleteRgate);
		
		JMenuItem mntmRunRgate = new JMenuItem("Lancer régate");
		mnRegate.add(mntmRunRgate);
		
		JMenu mnParticipants = new JMenu("Participants");
		menuBar.add(mnParticipants);
		
		JMenuItem mntmAddParticipant = new JMenuItem("Ajouter participant");
		mnParticipants.add(mntmAddParticipant);
		
		JMenuItem mntmModifyParticipant = new JMenuItem("Modifier participant");
		mnParticipants.add(mntmModifyParticipant);
		
		JMenu mnClassement = new JMenu("Classement");
		
		menuBar.add(mnClassement);
		
		JMenuItem mntmClassementRegate = new JMenuItem("Classement régate");
		mnClassement.add(mntmClassementRegate);
		
		JMenuItem mntmClassementPerCatgorie = new JMenuItem("Classement par catégorie");
		mnClassement.add(mntmClassementPerCatgorie);
		
		JMenu mnHelp = new JMenu("Aide");
		menuBar.add(mnHelp);
		
		JMenuItem mntmCopiright = new JMenuItem("Copiright");
		mnHelp.add(mntmCopiright);
		
		JMenuItem mntmContact = new JMenuItem("Contact");
		mnHelp.add(mntmContact);
	}

	private void reinitContentPane() {
		contentPane.removeAll();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
	}
}
