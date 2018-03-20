import java.sql.SQLException;

public class Main {
	public static void main(String[] args) throws SQLException 
	{
		Bdd maBdd = new Bdd();
		maBdd.initialisation();
		
		Window window = new Window("Regate Manager 2017",800,600, maBdd.getListeNomRegate(), maBdd.getParticipant(), maBdd.getListeType(), maBdd);
		window.createWindow();
		window.createMenu();
		window.ajouteAccueil();
		window.setVisible(true);
	}
}
