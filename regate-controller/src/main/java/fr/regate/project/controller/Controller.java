package fr.regate.project.controller;

import fr.regate.project.view.*;
import fr.regate.project.model.*;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.Hashtable;

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
            JOptionPane.showMessageDialog(null, "le chronomètre Tourne vous ne pouvez pas le lancer.", "Erreur", JOptionPane.ERROR_MESSAGE);
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
        if (chrono.isRunning() && runRegate.regateIsLoad()) {
            JOptionPane.showMessageDialog(null, "Une régate est en cours. Impossible d'en selectionner une autre.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }else {
        	clearJtableRunRegate();
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
        MODIF_REGATE,
        SUPPR_REGATE;
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
                views.showClassementView();
            case RUN_REGATE:
                views.showRunRegateView(getAllRegate());
                break;
            case MODIF_REGATE:
                views.showModifRegateView(this.getAllNameParticipants(), this.getAllShip(), this.getAllRegate());
                break;
            case SUPPR_REGATE:
            	views.showDeletRegateView(getAllRegate());
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
			
			this.refreshManagerInfo();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public void bddLinkRegateToPart() {
    	this.refreshManagerInfo();
    	
    	int pos=0;
    	ArrayList<Integer> mesBateauxId = new ArrayList<Integer>();
    	ArrayList<Integer> mesParticipantsId = new ArrayList<Integer>();
    	Regate maRegate;
    	//Récupère la dernière régate enregistrer dans le manager
    	maRegate = manager.getAllRegates().get(manager.getAllRegates().size()-1);
    	for (int i = 0; i<20; i++) {
			if (views.getAr().getTableParticipants().getValueAt(i, 0) != null) {
				mesParticipantsId.add(Integer.parseInt(views.getAr().getTableParticipants().getValueAt(i, 0).toString()));
				mesBateauxId.add(Integer.parseInt(views.getAr().getTableParticipants().getValueAt(i, 3).toString()));
			}
		}
    	for(int i = 0; i < mesParticipantsId.size(); i++) {
    		try {
    			RequestBdd.reqLinkPartToRegate(mesParticipantsId.get(i), maRegate.getIdRegate(), mesBateauxId.get(i), maRegate.getDateRegate());
    		} catch (SQLException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}	
    	}	
    }
    
    //Même fonctionnement que linkRegateToPart mais permet de modifier une régate en particulier et non la derniere ajouté 
    public void bddUpdateRegateToPart(int idRegate){
    	this.refreshManagerInfo();
    	
    	int pos=0;
    	ArrayList<Integer> mesBateauxId = new ArrayList<Integer>();
    	ArrayList<Integer> mesParticipantsId = new ArrayList<Integer>();
    	
    	for (int i = 0; i<20; i++) {
			if (views.getAr().getTableParticipants().getValueAt(i, 0) != null) {
				mesParticipantsId.add(Integer.parseInt(views.getAr().getTableParticipants().getValueAt(i, 0).toString()));
				mesBateauxId.add(Integer.parseInt(views.getAr().getTableParticipants().getValueAt(i, 3).toString()));
			}
		}
    	for(int i = 0; i < mesParticipantsId.size(); i++) {
    		try {
    			RequestBdd.reqLinkPartToRegate(mesParticipantsId.get(i), idRegate, mesBateauxId.get(i), null);
    		} catch (SQLException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}	
    	}	
    }
    
   public void bddUpdateRegate(){
	   
	   int idRegate = Integer.parseInt(views.getAr().getIdRegate());
	   String nameRegate = views.getAr().getNameRegate();
	   Date dateRegate = null;
	   String startPlace = views.getAr().getPlaceDeparture();
	   String endPlace = views.getAr().getPlaceArrival();
	   int distance = views.getAr().getDistance();
	   int status = 0;
	   	
	   try {
		RequestBdd.reqUpdateRegate(idRegate, nameRegate, startPlace, endPlace, distance, status);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	   bddUpdateRegateToPart(idRegate);
    }
   
   	public void bddDeletRegate() {
   		Regate maRegate = manager.getAllRegates().get(views.getSr().getcboDelRegate().getSelectedIndex());
   		try {
   			int value = JOptionPane.showConfirmDialog(null,"êtes vous sûr de vouloir supprimer " + maRegate.getIdRegate() + " : " + maRegate.getNameRegate() + " de la base de données", "Alerte", JOptionPane.WARNING_MESSAGE, JOptionPane.YES_NO_OPTION);
			if(value == 0) {
				RequestBdd.reqDeletRegate(maRegate.getIdRegate());
				JOptionPane.showMessageDialog(null,"la regate " + maRegate.getNameRegate() + " a bien été supprimé de la base de données", "information", JOptionPane.INFORMATION_MESSAGE);
			}
   		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
   	}
   	
   	public void bddFinishRegate() {
   		ArrayList<Integer> mesParticipantsId = new ArrayList<Integer>();
    	ArrayList<Time> tempsParticipants = new ArrayList<Time>();
    	for (int i = 0; i<20; i++) {
			if (views.getLr().getTableParticipants().getValueAt(i, 0) != null) {
				mesParticipantsId.add(Integer.parseInt(views.getLr().getTableParticipants().getValueAt(i, 0).toString()));
				tempsParticipants.add(Time.valueOf(String.valueOf(views.getLr().getTableParticipants().getValueAt(i, 5))));
			}
		}
    	for(int i = 0; i < mesParticipantsId.size(); i++) {
    		System.out.println(i + " : " +tempsParticipants.get(i).getTime());
			try {
				RequestBdd.reqUpdateTimePart(mesParticipantsId.get(i), tempsParticipants.get(i).getTime());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	if(mesParticipantsId.size() > 0) {
    		try {
				//RequestBdd.reqUpdateRegateFinish(Integer.parseInt(views.getLr().getIdRegate()));
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
    
    public String[] getAllRegate() {
    	this.refreshManagerInfo();
    	ArrayList<String> mesRegates = new ArrayList<String>();
    	for (Regate maRegate : manager.getAllRegates()) {
    		if(maRegate.getStatus() < 1) {    			
    			mesRegates.add(maRegate.getIdRegate() + " : " + maRegate.getNameRegate());
    		}
    	}
    	String[] StringArray = mesRegates.toArray(new String[0]);
    	return StringArray;
    }
    
    public void ajoutParticipantTable(){
    	this.refreshManagerInfo();
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
					if(views.getAr().getTableParticipants().getValueAt(i, 0).toString().contains(String.valueOf(monParticipant.getIdParticipant())) 
							&& views.getAr().getTableParticipants().getValueAt(i, 0).toString().contains(String.valueOf(monParticipant.getIdParticipant()))) {
						verif = true;
					}
				}
			}
			if (verif == false) {
				views.getAr().setTableParticipants(String.valueOf(monParticipant.getIdParticipant()), pos, 0);
				views.getAr().setTableParticipants(monParticipant.getName(), pos, 1);
				views.getAr().setTableParticipants(monParticipant.getFirstName(), pos, 2);
				views.getAr().setTableParticipants(String.valueOf(monBateau.getIdShip()), pos, 3);
				views.getAr().setTableParticipants(monBateau.getNameShip(), pos, 4);
				views.getAr().setTableParticipants(String.valueOf(monBateau.getCategoryShip() ), pos, 5);
				views.getAr().setTableParticipants(String.valueOf(monBateau.getRating()), pos, 6);
			}else {
				JOptionPane.showMessageDialog(null, monParticipant.getName() +" "+ monParticipant.getFirstName() + " est déjà inscrit !", "information", JOptionPane.INFORMATION_MESSAGE);
			}	
		}
    }
    
    public void selRegateToModif() {
    	this.refreshManagerInfo();
    	clearJtable();
    	
    	Regate maRegate = manager.getAllRegates().get(views.getAr().getCboSelRegateToModif().getSelectedIndex());
    	Hashtable<String, String> participantAndShip;
    
    	try {
    		participantAndShip = RequestBdd.getParticipantsInscrit(maRegate.getIdRegate());
    		for (int i= 0; i < participantAndShip.size()/6; i++) {
    			views.getAr().setTableParticipants(String.valueOf(participantAndShip.get("idPart"+i)), i, 0);
    			views.getAr().setTableParticipants(participantAndShip.get("lastName"+i), i, 1);
    			views.getAr().setTableParticipants(participantAndShip.get("firstName"+i), i, 2);
    			views.getAr().setTableParticipants(String.valueOf(participantAndShip.get("idShip"+i)), i, 3);
    			views.getAr().setTableParticipants(participantAndShip.get("nameShip"+i), i, 4);
				views.getAr().setTableParticipants(String.valueOf(participantAndShip.get("categoryShip"+i)), i, 5);
				views.getAr().setTableParticipants(String.valueOf(participantAndShip.get("ratingShip"+i)), i, 6);
    			
    		}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	views.getAr().setIdRegate(String.valueOf(maRegate.getIdRegate()));
    	views.getAr().setNameRegate(maRegate.getNameRegate());
    	views.getAr().setPlaceDeparture(maRegate.getStartPoint());
    	views.getAr().setPlaceArrival(maRegate.getEndPoint());
    	views.getAr().setDistance(String.valueOf(maRegate.getDistance()));
    	
    }
    
    public void selRegate() {
    	this.refreshManagerInfo();
    	clearJtableRunRegate();
    	Regate maRegate = manager.getAllRegates().get(views.getLr().getCboSelRegate().getSelectedIndex());
    	Hashtable<String, String> participantAndShip;
    
    	try {
    		participantAndShip = RequestBdd.getParticipantsInscrit(maRegate.getIdRegate());
    		for (int i= 0; i < participantAndShip.size()/6; i++) {
    			views.getLr().setTableParticipants(String.valueOf(participantAndShip.get("idPart"+i)), i, 0);
    			views.getLr().setTableParticipants(participantAndShip.get("lastName"+i), i, 1);
    			views.getLr().setTableParticipants(participantAndShip.get("nameShip"+i), i, 2);
    			
    		}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	views.getLr().setIdRegate(String.valueOf(maRegate.getIdRegate()));
    	views.getLr().setNameRegate(maRegate.getNameRegate());
    	views.getLr().setPlaceDeparture(maRegate.getStartPoint());
    	views.getLr().setPlaceArrival(maRegate.getEndPoint());
    	views.getLr().setDistance(String.valueOf(maRegate.getDistance()));
    }
    
    public void clearJtable() {
    	
		for (int i = 0; i<20; i++) {
			if (views.getAr().getTableParticipants().getValueAt(i, 0) != null) {
				for(int j = 0; j < 7; j++ ) {
					views.getAr().setTableParticipants(null, i, j);					
				}
			}
    	}
    }
    
    public void clearJtableRunRegate() {
        for (int i = 0; i<20; i++) {
        	views.getLr().setTableParticipants(null, i, 0);
        	views.getLr().setTableParticipants(null, i, 1);
        	views.getLr().setTableParticipants(null, i, 2);
        	views.getLr().setTableParticipants(null, i, 5);
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

    public void completeTabLineLr(Boolean abandon, int row) {
		if ((chrono.isRunning()) && (views.getLr().getTableParticipants().getValueAt(row, 5) == null) &&
				views.getLr().getTableParticipants().getValueAt(row, 0) != null)
		{
			if (abandon)
			{
				views.getLr().getTableParticipants().setValueAt("Abandon",row,5);

			}else
			{
				views.getLr().getTableParticipants().setValueAt(new SimpleDateFormat("HH:mm:ss").format(
						chrono.getTime()*1000- 3.6 * Math.pow(10,6)),row,5);
			}
		}else if  (views.getLr().getTableParticipants().getValueAt(row, 5) != null)
		{
			JOptionPane.showMessageDialog(null, "Ce participant est déjà arrivé ou a abandoné", "Erreur", JOptionPane.ERROR_MESSAGE);
		}else if  (!chrono.isRunning())
		{
			JOptionPane.showMessageDialog(null, "Le chronomètre n'est pas lancé", "Erreur", JOptionPane.ERROR_MESSAGE);
		}
	}
}
