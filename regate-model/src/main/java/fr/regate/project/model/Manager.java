package fr.regate.project.model;

import java.util.ArrayList;

public class Manager {
    private Manager() {}
    ArrayList<Participant> allParticipants;
    ArrayList<Regate> allRegates;
    ArrayList<Regate> runRegates;
    ArrayList<Ship> allShip;


    private static class GestionHolder{
        private final static Manager INSTANCE = new Manager();
    }

    public static Manager getGestion() {
        return GestionHolder.INSTANCE;
    }

    public ArrayList<Participant> getAllParticipants() {
        return allParticipants;
    }

    public void setAllParticipants(ArrayList<Participant> allParticipants) {
        this.allParticipants = allParticipants;
    }

    public ArrayList<Regate> getAllRegates() {
        return allRegates;
    }
    
    public ArrayList<Regate> getRunRegates(){
    	return runRegates;
    }

    public void setRunRegates(ArrayList<Regate> runRegates) {
    	this.runRegates = runRegates;
    }
    public void setAllRegates(ArrayList<Regate> allRegates) {
        this.allRegates = allRegates;
    }
    
    public ArrayList<Ship> getAllShip(){
    	return allShip;
    }
    
    public void setAllShip(ArrayList<Ship> allShip) {
    	this.allShip = allShip;
    }
}


