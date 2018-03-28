package fr.regate.project.controller;

import java.sql.SQLException;

import fr.regate.project.model.BddConnection;
import fr.regate.project.model.RequestBdd;
import fr.regate.project.view.*;


public class Main {
	public static void main(String[] args) {
		final String URL_BDD = "jdbc:mysql://localhost:3306/eole";
		final String USER_BDD = "user";
		final String PASSWORD_BDD = "password";
		

	    LoadView loadView = new LoadView();
	    Window window = loadView.getWindow();
	    LancementRegate lr = loadView.getLr();
		window.setVisible(true);
		ButtonListener bl = new ButtonListener(loadView);
	}
}
