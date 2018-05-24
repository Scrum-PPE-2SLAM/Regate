package fr.regate.project.controller;

import java.sql.SQLException;

import fr.regate.project.model.RequestBdd;
import fr.regate.project.view.*;

public class LoadView {
    private Window window;
    private Accueil accueil;
    private AjoutParticipant ap, mp;
    private AjoutRegate ar;
    private Classement cla;
    private LancementRegate lr;
    private ModifRegate mr;
    private AjouterBateau ab;
    private SupprimerRegate sr;

    public LoadView() {
        window = new Window("Regate Manager 2017",800,600);

        window.createWindow();
        window.createMenu();

        accueil = new Accueil(window);
        ap = new AjoutParticipant(window);
        mp = new AjoutParticipant(window);
        ar = new AjoutRegate(window);
        cla = new Classement(window);
        lr = new LancementRegate(window);
        mr = new ModifRegate(window);
        ab = new AjouterBateau(window);
        sr = new SupprimerRegate(window);
    }

    public Window getWindow() {
        return window;
    }

    public Accueil showAccueilView() {
        window.reinitContentPane();
        accueil.createAccueil();
        window.revalidateContentPane();
        return accueil;
    }

    public AjoutParticipant showAddParticipantView() {
        window.reinitContentPane();
        ap.createParticipant("AJOUT PARTICIPANT", "Enregistrer");
        window.revalidateContentPane();
        return ap;
    }
    
    public AjouterBateau showAddShipView() {
    	window.reinitContentPane();
        ab.createShip("AJOUT BATEAU", "Enregistrer");
        window.revalidateContentPane();
        return ab;
    }
    
    public AjoutParticipant showModifParticipantView(String[] listAllParticipant) {
        window.reinitContentPane();
        mp.createParticipant("MODIFIER PARTICIPANT", "Modifier");
        mp.setCboSelParticipant(listAllParticipant);
        mp.modifParticipant();
        window.revalidateContentPane();
        return ap;
    }

    public AjoutRegate showAddRegateView(String[] listAllParticipant, String[] listAllShip) {
        window.reinitContentPane();
        ar.creationPanelAjoutRegate();
        ar.setCboSelParticipant(listAllParticipant);
        ar.setCboSelShip(listAllShip);
        ar.creationPanelParticipants();
        ar.creationPanelTitre("AJOUT NOUVELLE REGATE");
        ar.createBtnSendNewRegate();
        window.revalidateContentPane();
        return ar;
    }
    
    public ModifRegate showModifRegateView(String[] listAllParticipant, String[] listAllShip, String[] listeRegate) {
        window.reinitContentPane();
        ar.creationPanelAjoutRegate();
        ar.setCboSelParticipant(listAllParticipant);
        ar.setCboSelShip(listAllShip);
        ar.creationPanelParticipants();
        ar.creationPanelTitre("MODIFIER REGATE");
        ar.modifRegate(listeRegate);
        ar.createBtnSendModifRegate();
        window.revalidateContentPane();
        return mr;
    }
    
    public SupprimerRegate showDeletRegateView(String[] listeRegate) {
    	window.reinitContentPane();
    	sr.creationPanelTitre("SUPPRIMER REGATE");
    	sr.ajoutComboDelet(listeRegate);
    	window.revalidateContentPane();
    	return sr;
    }
   
    public Classement showClassementView() {
        window.reinitContentPane();
        cla.createPanelSelRegate("CLASSEMENT");
        cla.createClassement();
        window.revalidateContentPane();
        return cla;
    }

    public LancementRegate showRunRegateView() {
        window.reinitContentPane();
        lr.createAll();
        window.revalidateContentPane();
        return lr;
    }
    
    public LancementRegate showRunRegateView(String[] listeRegate) {
        window.reinitContentPane();
        lr.createAll();
        lr.setCboSelRegate(listeRegate);
        window.revalidateContentPane();
        return lr;
    }

   

    public Accueil getAccueil() {
        return accueil;
    }

    public AjoutParticipant getAp() {
        return ap;
    }

    public AjoutRegate getAr() {
        return ar;
    }

    public Classement getCla() {
        return cla;
    }

    public LancementRegate getLr() {
        return lr;
    }

    public ModifRegate getMr() {
        return mr;
    }
    
    public AjouterBateau getAb() {
    	return ab;
    }
    
    public SupprimerRegate getSr() {
    	return sr;
    }
}
