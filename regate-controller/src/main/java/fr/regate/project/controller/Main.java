package fr.regate.project.controller;

import java.sql.SQLException;

import fr.regate.project.model.BddConnection;
import fr.regate.project.model.RequestBdd;
import fr.regate.project.view.*;


public class Main {
	public static void main(String[] args) {
		final String URL_BDD = "jdbc:mysql://localhost:3306/eole";
		final String USER_BDD = "root";
		final String PASSWORD_BDD = "";
		

	    Controller controller = new Controller();
	    Window window = controller.getWindow();
	    LancementRegate lr = controller.getLr();
		window.createWindow();
		window.createMenu();
		lr.createAll();
		window.setVisible(true);
		ButtonListener bl = new ButtonListener(lr);
		
		BddConnection BddCo = new BddConnection(URL_BDD, USER_BDD, PASSWORD_BDD);
		
		
		RequestBdd request = new RequestBdd();
		BddCo.Connexion();
		
		try {
			request.getListParticipant();
			request.reqAddParticipant("Jullerot", "Léo", "060606", "leo.jullerot@gmail.com");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		BddCo.CloseConnection();
		

	}
}
