package fr.regate.project.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.util.ArrayList;

/**
 * Window class create write window and menu for regate application.
 * @author Léo Jullerot <professionnel@leojullerot.fr> and Thomas Galocha <thomas.galocha@hotmail.fr>
 * @version 1.1
 */

public class Window extends JFrame {

	private static final long serialVersionUID = 1L;
	private String name;
	private int width;
	private int length;
	private JPanel contentPane;

    private JMenuBar menuBar;
    private JMenu mnFile;
    private JMenuItem mntmSave;
    private JMenuItem mntmQuit;
    private JMenu mnRegate;
    private JMenuItem mntmNewRegate;
    private JMenuItem mntmModifyRegate;
    private JMenuItem mntmDeleteRgate;
    private JMenuItem mntmRunRgate;
    private JMenu mnParticipants;
    private JMenuItem mntmAddParticipant;
    private JMenuItem mntmModifyParticipant;
    private JMenu mnClassement;
    private JMenuItem mntmClassementRegate;
    private JMenuItem mntmClassementPerCatgorie;
    private JMenu mnHelp;
    private JMenuItem mntmCopiright;
    private JMenuItem mntmContact;

    /**
     * @param name The name of window
     * @param width width of window
     * @param length Length of window
     * Constructor
     */
	public Window(String name, int width, int length) {
		this.name = name;
		this.width = width;
		this.length = length;
	}
		
	public void createWindow() {
		setTitle(name);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, width, length);
		contentPane = new JPanel();
		reinitContentPane();
	}

	public void createMenu() {
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		mnFile = new JMenu("Fichier");
		menuBar.add(mnFile);
		
		mntmSave = new JMenuItem("Enregistrer");
		mnFile.add(mntmSave);
		
		mntmQuit = new JMenuItem("Quitter");
		mnFile.add(mntmQuit);
		
		mnRegate = new JMenu("Régate");
		menuBar.add(mnRegate);
		
		mntmNewRegate = new JMenuItem("Nouvelle régate");
		mnRegate.add(mntmNewRegate);
		
		mntmModifyRegate = new JMenuItem("Modifier régate");
		mnRegate.add(mntmModifyRegate);
		
		mntmDeleteRgate = new JMenuItem("Supprimer régate");
		mnRegate.add(mntmDeleteRgate);
		
		mntmRunRgate = new JMenuItem("Lancer régate");
		mnRegate.add(mntmRunRgate);
		
		mnParticipants = new JMenu("Participants");
		menuBar.add(mnParticipants);
		
		mntmAddParticipant = new JMenuItem("Ajouter participant");
		mnParticipants.add(mntmAddParticipant);
		
		mntmModifyParticipant = new JMenuItem("Modifier participant");
		mnParticipants.add(mntmModifyParticipant);
		
		mnClassement = new JMenu("Classement");
		menuBar.add(mnClassement);

		mntmClassementRegate = new JMenuItem("Classement régate");
		mnClassement.add(mntmClassementRegate);
		
		mntmClassementPerCatgorie = new JMenuItem("Classement par catégorie");
		mnClassement.add(mntmClassementPerCatgorie);
		
		mnHelp = new JMenu("Aide");
		menuBar.add(mnHelp);
		
		mntmCopiright = new JMenuItem("Copiright");
		mnHelp.add(mntmCopiright);
		
		mntmContact = new JMenuItem("Contact");
		mnHelp.add(mntmContact);
	}

	public void reinitContentPane() {
		contentPane.removeAll();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
	}

	public ArrayList<JMenuItem> getMenuItemList() {
	    ArrayList<JMenuItem> listeMenuItem = new ArrayList<JMenuItem>();
        listeMenuItem.add(mntmSave);
        listeMenuItem.add(mntmQuit);
        listeMenuItem.add(mntmNewRegate);
        listeMenuItem.add(mntmModifyRegate);
        listeMenuItem.add(mntmDeleteRgate);
        listeMenuItem.add(mntmRunRgate);
        listeMenuItem.add(mntmAddParticipant);
        listeMenuItem.add(mntmModifyParticipant);
        listeMenuItem.add(mntmClassementRegate);
        listeMenuItem.add(mntmClassementPerCatgorie);
        listeMenuItem.add(mntmCopiright);
        listeMenuItem.add(mntmContact);
	    return listeMenuItem;
    }

    public JMenuItem getMntmSave() {
        return mntmSave;
    }

    public JMenuItem getMntmQuit() {
        return mntmQuit;
    }

    public JMenuItem getMntmNewRegate() {
        return mntmNewRegate;
    }

    public JMenuItem getMntmModifyRegate() {
        return mntmModifyRegate;
    }

    public JMenuItem getMntmDeleteRgate() {
        return mntmDeleteRgate;
    }

    public JMenuItem getMntmRunRgate() {
        return mntmRunRgate;
    }

    public JMenuItem getMntmAddParticipant() {
        return mntmAddParticipant;
    }

    public JMenuItem getMntmModifyParticipant() {
        return mntmModifyParticipant;
    }

    public JMenuItem getMntmClassementRegate() {
        return mntmClassementRegate;
    }

    public JMenuItem getMntmClassementPerCatgorie() {
        return mntmClassementPerCatgorie;
    }

    public JMenuItem getMntmCopiright() {
        return mntmCopiright;
    }

    public JMenuItem getMntmContact() {
        return mntmContact;
    }

    public void revalidateContentPane() {
	    contentPane.revalidate();
    }
}
