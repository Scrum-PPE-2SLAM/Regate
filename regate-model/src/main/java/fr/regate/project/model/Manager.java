package fr.regate.project.model;

import java.util.ArrayList;

public class Manager {
    private Manager() {}
    ArrayList<Participant> allParticipants;
    ArrayList<Regate> allRegates;


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

    public void setAllRegates(ArrayList<Regate> allRegates) {
        this.allRegates = allRegates;
    }
}


