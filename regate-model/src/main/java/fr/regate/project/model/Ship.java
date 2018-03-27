package fr.regate.project.model;

public class Ship {
	
	private int idShip;
	private String nameShip;
	private int CategoryShip;
	private int Rating;
	
	public Ship(int idShip, String nameShip, int categoryShip, int rating) {
		super();
		this.idShip = idShip;
		this.nameShip = nameShip;
		CategoryShip = categoryShip;
		Rating = rating;
	}

	public int getIdShip() {
		return idShip;
	}

	public void setIdShip(int idShip) {
		this.idShip = idShip;
	}

	public String getNameShip() {
		return nameShip;
	}

	public void setNameShip(String nameShip) {
		this.nameShip = nameShip;
	}

	public int getCategoryShip() {
		return CategoryShip;
	}

	public void setCategoryShip(int categoryShip) {
		CategoryShip = categoryShip;
	}

	public int getRating() {
		return Rating;
	}

	public void setRating(int rating) {
		Rating = rating;
	}
	
	
	

}
