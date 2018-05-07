package fr.regate.project.controller;

import fr.regate.project.view.*;
import fr.regate.project.model.*;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.*;

public class Controller {
    private DTimer chrono;
    private LancementRegate runRegate;
    private LoadView views;
    private Manager manager;

    public Controller(LoadView views) {
        chrono = new DTimer(views.showRunRegateView());
        runRegate = views.showRunRegateView();
        this.views = views;

        manager = Manager.getGestion();
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
        ADD_SHIP,
        MODIF_PARTICIPANT,
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
            case MODIF_PARTICIPANT :
            	views.showModifParticipantView(this.getAllNameParticipants());
            	break;
            case ADD_SHIP :
            	views.showAddShipView();
            	break;
            case ADD_REGATE:
                views.showAddRegateView(this.getAllNameParticipants(), this.getAllShip());
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

            this.refreshManagerInfo();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public void bddAddRegate() {
    	String nameRegate = views.getAr().getNameRegate();
    	Date dateRegate = null;

    	String startPlace = views.getAr().getPlaceDeparture();
    	String endPlace = views.getAr().getPlaceArrival();
    	int distance = views.getAr().getDistance();
    	int status = 0;
    	
    	try {
			RequestBdd.reqAddRegate(nameRegate, dateRegate, startPlace, endPlace, distance, status);
			JOptionPane.showMessageDialog(null, nameRegate + " à bien été ajouté a la base de données", "information", JOptionPane.INFORMATION_MESSAGE);
			
			// Refresh all the text field
			views.getAr().setNameRegate("");
			views.getAr().setPlaceDeparture("");
			views.getAr().setPlaceArrival("");
			views.getAr().setDistance("");

			this.refreshManagerInfo();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    
    public void bddAddShip() {
    	String nameShip = views.getAb().getNameShip();
    	int categoryShip = Integer.parseInt(views.getAb().getCategory());
    	int rating = Integer.parseInt(views.getAb().getRating());
    	
    	try {
			RequestBdd.reqAddShip(nameShip, categoryShip, rating);
			JOptionPane.showMessageDialog(null,"le bateau " + nameShip + " à bien été ajouté a la base de données", "information", JOptionPane.INFORMATION_MESSAGE);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public String[] getAllNameParticipants() {
        this.refreshManagerInfo();
    	ArrayList<String> mesParticipants = new ArrayList<String>();
    	for(Participant monParticipant : manager.getAllParticipants()) {
            mesParticipants.add(monParticipant.getIdParticipant() + " : " + monParticipant.getName() + " " + monParticipant.getFirstName());
        }
    		String[] stringArray = mesParticipants.toArray(new String[0]);
    	return stringArray;
    }
    
    public String[] getAllShip() {
    	this.refreshManagerInfo();
    	ArrayList<String> mesBateaux = new ArrayList<String>();
    	for (Ship monBateau : manager.getAllShip()) {
    		mesBateaux.add(monBateau.getIdShip() + " : " + monBateau.getNameShip()); 
    	}
    	String[] StringArray = mesBateaux.toArray(new String[0]);
    	return StringArray;
    }
    
    public void ajoutParticipantTable(){
		Participant monParticipant;
    	Ship monBateau;
		Boolean verif = false;
		if (views.getAr().getTableParticipants().getValueAt(19, 0) == null) {
			int pos = 0;
			monParticipant = manager.getAllParticipants().get(views.getAr().getCboSelParticipant().getSelectedIndex());
			monBateau = manager.getAllShip().get(views.getAr().getCboSelShip().getSelectedIndex());
			for (int i = 0; i<20; i++) {
				if (views.getAr().getTableParticipants().getValueAt(i, 0) != null) {
					pos += 1;
					// Compare if the participant is already registered in the regate
					if(views.getAr().getTableParticipants().getValueAt(i, 0).toString().contains(monParticipant.getName()) 
							&& views.getAr().getTableParticipants().getValueAt(i, 1).toString().contains(monParticipant.getFirstName())) {
						verif = true;
					}
				}
			}
			if (verif == false) {
				views.getAr().setTableParticipants(monParticipant.getName(), pos, 0);
				views.getAr().setTableParticipants(monParticipant.getFirstName(), pos, 1);
				views.getAr().setTableParticipants(monBateau.getNameShip(), pos, 2);
				views.getAr().setTableParticipants(String.valueOf(monBateau.getCategoryShip()), pos, 3);
				views.getAr().setTableParticipants(String.valueOf(monBateau.getRating()), pos, 4);
			}else {
				JOptionPane.showMessageDialog(null, monParticipant.getName() +" "+ monParticipant.getFirstName() + " est déjà inscrit !", "information", JOptionPane.INFORMATION_MESSAGE);
			}	
		}
    }

    public void refreshManagerInfo() {
        try {
            manager.setAllParticipants(RequestBdd.getListParticipant());
            manager.setAllRegates(RequestBdd.getListRegate());
            manager.setAllShip(RequestBdd.getListShip());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
