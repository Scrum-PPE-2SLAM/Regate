package fr.regate.project.controller;

import fr.regate.project.view.*;

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

        lr.createAll();
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
        return lr;
    }

    public ModifRegate getMr() {
        return mr;
    }
}
