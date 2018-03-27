package fr.regate.project.model;
public class Participant 
{
	private int idParticipant, tempsRealisé;
	private String name, firstName, phone, email;
	
	
	public Participant(int idParticipant,  String name, String firstName, String phone, String email) {
		this.idParticipant = idParticipant;
		this.name = name;
		this.firstName = firstName;
		this.phone = phone;
		this.email = email;
	}

	public int getIdParticipant() {
		return idParticipant;
	}

	public String getName() {
		return name;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getPhone() {
		return phone;
	}

	public String getEmail() {
		return email;
	}

	public int getTempsRealisé() {
		return tempsRealisé;
	}

	public void setTempsRealisé(int tempsRealisé) {
		this.tempsRealisé = tempsRealisé;
	}

}
