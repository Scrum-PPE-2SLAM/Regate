package fr.regate.project.controller;

import fr.regate.project.view.*;

import java.util.Hashtable;

public class LoadView {
    private Window window;
    private Accueil accueil;
    private AjoutParticipant ap;
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
        ar = new AjoutRegate(window);
        cla = new Classement(window);
        lr = new LancementRegate(window);
        mr = new ModifRegate(window);

    }

    public Window getWindow() {
        return window;
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
        window.reinitContentPane();
        lr.createAll();
        window.revalidateContentPane();
        return lr;
    }

    public Hashtable getAllViews() {
        Hashtable listeView=new Hashtable();
        listeView.put("Window",window);
        listeView.put("Accueil",accueil);
        listeView.put("AddParticipant",ap);
        listeView.put("AddRegate",ar);
        listeView.put("Classement",cla);
        listeView.put("RunRegate",lr);
        listeView.put("ModifRegate",mr);

        return listeView;
    }

    public ModifRegate getMr() {
        return mr;
    }
   
}
