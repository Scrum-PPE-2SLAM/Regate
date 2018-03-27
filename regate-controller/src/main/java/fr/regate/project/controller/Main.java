package fr.regate.project.controller;

import fr.regate.project.view.*;


public class Main {
	public static void main(String[] args) {

	    LoadView loadView = new LoadView();
	    Window window = loadView.getWindow();
	    LancementRegate lr = loadView.getLr();
		window.setVisible(true);
		ButtonListener bl = new ButtonListener(loadView);
	}
}
