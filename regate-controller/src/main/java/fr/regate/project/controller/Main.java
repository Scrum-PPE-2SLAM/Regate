package fr.regate.project.controller;

import fr.regate.project.view.*;

public class Main {
	public static void main(String[] args) {
		
		Window window = new Window("Regate Manager 2017",800,600);
		LancementRegate lr = new LancementRegate(window);
		window.createWindow();
		window.createMenu();
		lr.createAll();
		window.setVisible(true);
	}
}
