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
}
