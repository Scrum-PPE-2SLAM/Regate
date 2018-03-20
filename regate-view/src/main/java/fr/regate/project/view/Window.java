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
	private ArrayList<String> listeRegate;
	private ArrayList<String> listeType;
	private ArrayList<ArrayList<Participant>> listeParticipant;
	private Bdd maBdd;

	public Window(String name, int height, int length, ArrayList<String> listeRegate, ArrayList<Participant> listeParticipant, ArrayList<String> listeType, Bdd maBdd ) {
		this.name = name;
		this.height = height;
		this.length = length;
		this.listeRegate = listeRegate;
		this.listeType = listeType;
		this.maBdd = maBdd;
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
		
		JMenu mnFichier = new JMenu("Fichier");
		menuBar.add(mnFichier);
		
		JMenuItem mntmEnregistrer = new JMenuItem("Enregistrer");
		mnFichier.add(mntmEnregistrer);
		
		JMenuItem mntmQuitter = new JMenuItem("Quitter");
		mntmQuitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		mnFichier.add(mntmQuitter);
		
		JMenu mnRgate = new JMenu("Régate");
		menuBar.add(mnRgate);
		
		JMenuItem mntmNouvelleRgate = new JMenuItem("Nouvelle régate");
		mntmNouvelleRgate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ajoutRegate();
			}
		});
		mnRgate.add(mntmNouvelleRgate);
		
		JMenuItem mntmModifierRgate = new JMenuItem("Modifier régate");
		mntmModifierRgate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				modifierRegate();
			}
		});
		mnRgate.add(mntmModifierRgate);
		
		JMenuItem mntmSupprimerRgate = new JMenuItem("Supprimer régate");
		mntmSupprimerRgate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lancementRegate2();
			}
		});
		mnRgate.add(mntmSupprimerRgate);
		
		JMenuItem mntmLancerRgate = new JMenuItem("Lancer régate");
		mntmLancerRgate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lancementRegate();
			}
		});
		mnRgate.add(mntmLancerRgate);
		
		JMenu mnParticipants = new JMenu("Participants");
		menuBar.add(mnParticipants);
		
		JMenuItem mntmAjouterParticipant = new JMenuItem("Ajouter participant");
		mntmAjouterParticipant.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AjouterParticipant();
			}
		});
		mnParticipants.add(mntmAjouterParticipant);
		
		JMenuItem mntmModifierParticipant = new JMenuItem("Modifier participant");
		mnParticipants.add(mntmModifierParticipant);
		
		JMenu mnClassement = new JMenu("Classement");
		
		menuBar.add(mnClassement);
		
		
		JMenuItem mntmClassementRgate = new JMenuItem("Classement régate");
		mntmClassementRgate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				classement();
			}
		});
		mnClassement.add(mntmClassementRgate);
		
		JMenuItem mntmClassementParCatgorie = new JMenuItem("Classement par catégorie");
		mntmClassementParCatgorie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				classementParCategorie();
			}
		});
		mnClassement.add(mntmClassementParCatgorie);
		
		JMenu mnAide = new JMenu("Aide");
		menuBar.add(mnAide);
		
		JMenuItem mntmCopiright = new JMenuItem("Copiright");
		mnAide.add(mntmCopiright);
		
		JMenuItem mntmContact = new JMenuItem("Contact");
		mnAide.add(mntmContact);
		
		
		
		
	}

	
	public void reinitContentPane() {
		contentPane.removeAll();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
	}
	
	public void lancementRegate() {
		reinitContentPane();
		LancementRegate lancementRegate = new LancementRegate(this, maBdd);
		lancementRegate.createAll();
		contentPane.revalidate();
	}
	
	public void classement() {
		reinitContentPane();
		Classement classement = new Classement(this, maBdd);
		classement.createClassement();
		classement.createPanelSelRegate("Général");
		contentPane.revalidate();
	}
	
	public void classementParCategorie(){
		reinitContentPane();
		Classement classement = new Classement(this, maBdd);
		classement.createClassement();
		classement.createPanelSelRegate("Par catégorie");
		contentPane.revalidate();
	}
	
	public void lancementRegate2() {
		reinitContentPane();
		LancementRegate lancementRegate = new LancementRegate(this, maBdd);
		lancementRegate.creationPanelSelRegate();
		lancementRegate.creationPanelInfoRegate();
		contentPane.revalidate();
	}
	
	public ArrayList<String> getListeRegate(){
		return listeRegate;
	}
	public ArrayList<String> getListeType(){
		return listeType;
	}
	public ArrayList<ArrayList<Participant>> getListeParticipant(){
		return listeParticipant;
	}
	
	public void ajoutRegate() {
		reinitContentPane();
		AjoutRegate ajoutRegate = new AjoutRegate(this, maBdd);
		ajoutRegate.creationPanelAjoutRegate();
		ajoutRegate.creationPanelParticipants();
		ajoutRegate.creationPanelTitre("AJOUT NOUVELLE REGATE ");
		contentPane.revalidate();
	}
	public void ajouteAccueil(){
		reinitContentPane();
		Accueil accueil = new Accueil(this);
		accueil.createAccueil();
		contentPane.revalidate();
	}
	public void AjouterParticipant(){
		reinitContentPane();
		AjoutParticipant nouveauParticipant = new AjoutParticipant(this, maBdd);
		nouveauParticipant.createNouveauParticipant();
		contentPane.revalidate();
	}
	
	public void modifierRegate() {
		reinitContentPane();
		ModifRegate modifRegate = new ModifRegate(this, maBdd);
		modifRegate.ajoutCombo(listeRegate.toArray(new String[0]));
		contentPane.revalidate();
	}

}
