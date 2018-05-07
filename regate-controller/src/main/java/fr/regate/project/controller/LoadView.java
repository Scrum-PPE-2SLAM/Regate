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
    
    public AjoutParticipant showModifParticipantView(String[] listAllParticipant) {
        window.reinitContentPane();
        mp.createParticipant("MODIFIER PARTICIPANT", "Modifier");

        mp.setCboSelParticipant(listAllParticipant);

        mp.modifParticipant();
        window.revalidateContentPane();
        return ap;
    }

    public AjoutRegate showAddRegateView(String[] listAllParticipant) {
        window.reinitContentPane();
        ar.creationPanelAjoutRegate();
        ar.setCboSelParticipant(listAllParticipant);
        
        ar.creationPanelParticipants();
        ar.creationPanelTitre("AJOUT NOUVELLE REGATE");
        window.revalidateContentPane();
        return ar;
    }

    public Classement showclassementView() {
        window.reinitContentPane();
        cla.createClassement();
        window.reinitContentPane();
        return cla;
    }

    public LancementRegate showRunRegateView() {
        window.reinitContentPane();
        lr.createAll();
        window.revalidateContentPane();
        return lr;
    }

    public ModifRegate showModifRegateView() {
        window.reinitContentPane();
        mr.CreateChangeRegate();
        window.revalidateContentPane();
        return mr;
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
}
