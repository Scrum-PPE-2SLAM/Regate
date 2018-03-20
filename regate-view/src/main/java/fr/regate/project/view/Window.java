package fr.regate.project.view;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		mntmQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		mnFile.add(mntmQuit);
		
		JMenu mnRegate = new JMenu("Régate");
		menuBar.add(mnRegate);
		
		JMenuItem mntmNewRegate = new JMenuItem("Nouvelle régate");
		mntmNewRegate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addRegate();
			}
		});
		mnRegate.add(mntmNewRegate);
		
		JMenuItem mntmModifyRegate = new JMenuItem("Modifier régate");
		mntmModifyRegate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				modifyRegate();
			}
		});
		mnRegate.add(mntmModifyRegate);
		
		JMenuItem mntmDeleteRgate = new JMenuItem("Supprimer régate");
		mntmDeleteRgate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				runRegate();
			}
		});
		mnRegate.add(mntmDeleteRgate);
		
		JMenuItem mntmRunRgate = new JMenuItem("Lancer régate");
		mntmRunRgate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				runRegate();
			}
		});
		mnRegate.add(mntmRunRgate);
		
		JMenu mnParticipants = new JMenu("Participants");
		menuBar.add(mnParticipants);
		
		JMenuItem mntmAddParticipant = new JMenuItem("Ajouter participant");
		mntmAddParticipant.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AddParticipant();
			}
		});
		mnParticipants.add(mntmAddParticipant);
		
		JMenuItem mntmModifyParticipant = new JMenuItem("Modifier participant");
		mnParticipants.add(mntmModifyParticipant);
		
		JMenu mnClassement = new JMenu("Classement");
		
		menuBar.add(mnClassement);
		
		
		JMenuItem mntmClassementRegate = new JMenuItem("Classement régate");
		mntmClassementRegate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				classement();
			}
		});
		mnClassement.add(mntmClassementRegate);
		
		JMenuItem mntmClassementPerCatgorie = new JMenuItem("Classement par catégorie");
		mntmClassementPerCatgorie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				classementPerCategorie();
			}
		});
		mnClassement.add(mntmClassementPerCatgorie);
		
		JMenu mnHelp = new JMenu("Aide");
		menuBar.add(mnHelp);
		
		JMenuItem mntmCopiright = new JMenuItem("Copiright");
		mnHelp.add(mntmCopiright);
		
		JMenuItem mntmContact = new JMenuItem("Contact");
		mnHelp.add(mntmContact);
	}

	
	public void reinitContentPane() {
		contentPane.removeAll();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
	}
	
	public void runRegate() {
		reinitContentPane();
		LancementRegate runRegate = new LancementRegate(this);
		runRegate.createAll();
		contentPane.revalidate();
	}
	
	public void classement() {
		reinitContentPane();
		Classement classement = new Classement(this);
		classement.createClassement();
		classement.createPanelSelRegate("Général");
		contentPane.revalidate();
	}
	
	public void classementPerCategorie(){
		reinitContentPane();
		Classement classement = new Classement(this);
		classement.createClassement();
		classement.createPanelSelRegate("Par catégorie");
		contentPane.revalidate();
	}
	
	public void addRegate() {
		reinitContentPane();
		AjoutRegate addRegate = new AjoutRegate(this);
		addRegate.creationPanelAjoutRegate();
		addRegate.creationPanelParticipants();
		addRegate.creationPanelTitre("AJOUT NOUVELLE REGATE ");
		contentPane.revalidate();
	}

	public void AddParticipant(){
		reinitContentPane();
		AjoutParticipant addParticipant = new AjoutParticipant(this);
		addParticipant.createNouveauParticipant();
		contentPane.revalidate();
	}
	
	public void modifyRegate() {
		reinitContentPane();
		ModifRegate modifRegate = new ModifRegate(this);
		modifRegate.ajoutCombo(listRegate.toArray(new String[0]));
		contentPane.revalidate();
	}
}
