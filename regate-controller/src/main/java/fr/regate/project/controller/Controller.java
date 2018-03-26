package fr.regate.project.controller;

import fr.regate.project.view.*;

public class Controller {
    private Window window;
    private Accueil accueil;
    private AjoutParticipant ap;
    private AjoutRegate ar;
    private Classement cla;
    private LancementRegate lr;
    private ModifRegate mr;

    public Controller() {
        window = new Window("Regate Manager 2017",800,600);
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
        return lr;
    }

    public ModifRegate getMr() {
        return mr;
    }
}
