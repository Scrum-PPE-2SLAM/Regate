package fr.regate.project.controller;

import fr.regate.project.view.*;
import fr.regate.project.model.*;

import java.sql.SQLException;

import javax.swing.*;

public class Controller {
    private DTimer chrono;
    private LancementRegate runRegate;
    LoadView views;
    

    public Controller(LoadView views) {
        chrono = new DTimer(views.showRunRegateView());
        runRegate = views.showRunRegateView();
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

    private enum ViewName {
        ACCUEIL,
        ADD_PARTICIPANT,
        ADD_REGATE,
        CLASSEMENT,
        RUN_REGATE,
        MODIF_REGATE;
    }

    public void showView(String viewName) {
        switch (ViewName.valueOf(viewName)) {
            case ACCUEIL:
                views.showAccueilView();
            case ADD_PARTICIPANT:
                views.showAddParticipantView();
                break;
            case ADD_REGATE:
                views.showAddRegateView();
                break;
            case CLASSEMENT:
                views.showclassementView();
            case RUN_REGATE:
                views.showRunRegateView();
                break;
            case MODIF_REGATE:
                views.showModifRegateView();
                break;
        }
    }
    
    public void bddAddParticipant() {
    	String nameParticipant = views.getAp().getNameParticipant();
    	String firstNameParticipant = views.getAp().getFirstName();
    	String phoneNumber = views.getAp().getPhoneNumber();
    	String email = views.getAp().getEmail();
    	
    	try {
			RequestBdd.reqAddParticipant(nameParticipant, firstNameParticipant, phoneNumber, email);
			JOptionPane.showMessageDialog(null, nameParticipant + " " + firstNameParticipant + " à bien été ajouté a la base de données", "information", JOptionPane.INFORMATION_MESSAGE);
			
			// Refresh all the text field
			views.getAp().setNameParticipant("");
			views.getAp().setFirstName("");
			views.getAp().setPhoneNumber("");
			views.getAp().setEmail("");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
    }
}
