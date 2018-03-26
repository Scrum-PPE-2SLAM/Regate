package fr.regate.project.controller;

import fr.regate.project.view.*;


public class Main {
	public static void main(String[] args) {

	    Controller controller = new Controller();
	    Window window = controller.getWindow();
	    LancementRegate lr = controller.getLr();
		window.createWindow();
		window.createMenu();
		lr.createAll();
		window.setVisible(true);
		ButtonListener bl = new ButtonListener(lr);

	}
}
