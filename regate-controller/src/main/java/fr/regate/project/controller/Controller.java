package fr.regate.project.controller;

import fr.regate.project.view.*;

import javax.swing.*;

public class Controller {
    private DTimer chrono;
    private LancementRegate runRegate;
    LoadView views;

    public Controller(LoadView views) {
        chrono = new DTimer(views.getLr());
        runRegate = views.getLr();
        this.views = views;
    }

    public void runRegRunChrono() {
        if (chrono.isRunning()) {
            JOptionPane.showMessageDialog(null, "le chronomètre nTourne vous ne pouvez pas le lancer.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }else if (runRegate.getLblChrono().getText() != "00:00:00") {
            JOptionPane.showMessageDialog(null, "le chronomètre n'est pas à 0. Veuillez le réinitialiser.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }else {
            chrono.startDTimer();
        }
    }

    public void runRegStopChrono() {
        if (chrono.isRunning()) {
            chrono.stopDTimer();
        } else {
            JOptionPane.showMessageDialog(null, "le chronomètre ne tourne pas!", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void runRegReinitChrono() {
        if (chrono.isRunning()) {
            JOptionPane.showMessageDialog(null, "le chronomètre tourne! impossible de le réinitialiser.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }else {
            int option = JOptionPane.showConfirmDialog(null, "Etes-vous sur de vouloir réinitialiser le chronomètre?", "Reinitialisation chronomètre", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

            if(option == JOptionPane.OK_OPTION){
                chrono.reinitDTimer();
                runRegate.setLblChrono("00:00:00");
            }
        }
    }

    public void runRegValidate() {
        if (chrono.isRunning() || runRegate.regateIsLoad()) {
            JOptionPane.showMessageDialog(null, "Une régate est en cours. Impossible d'en selectionner une autre.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }else {
            runRegate.reinitTab();
        }
    }

    public void printNewRegateView() {
        views.getWindow().reinitContentPane();
        AjoutRegate ajoutRegate = views.getAr();
        ajoutRegate.creationPanelAjoutRegate();
        ajoutRegate.creationPanelParticipants();
        ajoutRegate.creationPanelTitre("AJOUT NOUVELLE REGATE ");
        views.getWindow().revalidateContentPane();
    }
}
