public class Participant 
{
	private int idParticipant, tempsRealisé, catégorieVoilier, rating;
	private String nom, prenom, nomVoilier;
	
	
	public Participant(int idParticipant,  String nom, String prenom, String nomVoilier, int catégorieVoilier, int rating) 
	{
		this.idParticipant = idParticipant;
		this.catégorieVoilier = catégorieVoilier;
		this.nom = nom;
		this.prenom = prenom;
		this.nomVoilier = nomVoilier;
		this.rating = rating;
	}

	public int getTempsRealisé() {
		return tempsRealisé;
	}

	public void setTempsRealisé(int tempsRealisé) {
		this.tempsRealisé = tempsRealisé;
	}

	public int getIdParticipant() {
		return idParticipant;
	}

	public int getCatégorieVoilier() {
		return catégorieVoilier;
	}

	public String getNom() {
		return nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public String getnomVoilier() {
		return nomVoilier;
	}
	
	public int getRating() {
		return rating;
	}
}
