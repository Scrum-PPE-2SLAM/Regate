import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.sql.*;


public class Bdd {
	private ArrayList<Participant> listeParticipant = new ArrayList<Participant>();
	private ArrayList<Regate> listeRegate = new ArrayList<Regate>();
	private ArrayList<Regate> listeInverseRegate = new ArrayList<Regate>();
	private ArrayList<String> listeType= new ArrayList<String>();
	private ArrayList<String> listeDateRegate= new ArrayList<String>();
	private ArrayList<String> listeLieuDepart= new ArrayList<String>();
	private ArrayList<String> listeLieuArrivee= new ArrayList<String>();
	private ArrayList<Integer> listeDistance= new ArrayList<Integer>();
	private ArrayList<String> listeNomRegate = new ArrayList<String>();
	private ArrayList<String> listeNomRegateInverse = new ArrayList<String>();
	private static String url ="jdbc:mysql://localhost:3306/eole";
	private static String user ="root";
	private static String password = "";
	private static Connection con;
	private static Statement st;
	private static ResultSet rs;
	
	public Bdd() throws SQLException{
		Connexion();
	}

	public void Connexion(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url,user,password);
			System.out.println("connexion établie");
			st = con.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void initialisation() throws SQLException{
		String sql = "SELECT * FROM participant";
		rs = st.executeQuery(sql);
		listeParticipant = new ArrayList<Participant>();
		while (rs.next()){
			Participant Participant = new Participant(Integer.parseInt(rs.getString(1)), rs.getString(2), rs.getString(3), rs.getString(4), Integer.parseInt(rs.getString(5)), Integer.parseInt(rs.getString(6)));
			
			listeParticipant.add(Participant);
		}
		String sql2 = "SELECT * FROM regate";
		rs = st.executeQuery(sql2);
		listeRegate = new ArrayList<Regate>();
		
		while (rs.next()){
			Regate regate = new Regate(rs.getInt(1),rs.getInt(6),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5), rs.getInt(7));
			listeRegate.add(regate);
			listeNomRegate.add(rs.getString(2));
		}
		for (int i = listeRegate.size()-1; i>=0; i--) {
			listeInverseRegate.add(listeRegate.get(i));
			listeNomRegateInverse.add(listeRegate.get(i).getNomRegate());
		}
	
		deconnexion();
		System.out.println("regates : " + listeRegate.size() + "; participants : " + listeParticipant.size());
		
		
	}
	
	public void miseAJour(){
		
		try {
			remove(listeParticipant);
			remove(listeRegate);
			remove(listeDateRegate);
			remove(listeLieuDepart);
			remove(listeLieuArrivee);
			remove(listeDistance);
			remove(listeNomRegate);
			remove(listeInverseRegate);
			remove(listeNomRegateInverse);
			
			String sql = "SELECT * FROM participant";
			rs = st.executeQuery(sql);
			
			while(rs.next()){
				
				Participant Participant = new Participant(Integer.parseInt(rs.getString(1)), rs.getString(2), rs.getString(3), rs.getString(4), Integer.parseInt(rs.getString(5)), Integer.parseInt(rs.getString(6)));
				
				listeParticipant.add(Participant);
				
			}
			
			String sql2 = "SELECT * FROM regate";
			rs = st.executeQuery(sql2);
			
			while (rs.next()){
				
				Regate regate = new Regate(rs.getInt(1),rs.getInt(6),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5), rs.getInt(7));
				listeRegate.add(regate);
				listeNomRegate.add(rs.getString(2));
			}
			for (int i = listeRegate.size()-1; i>=0; i--) {
				listeInverseRegate.add(listeRegate.get(i));
				listeNomRegateInverse.add(listeRegate.get(i).getNomRegate());
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}
	
	public void remove(ArrayList<?> maListe){
		while (maListe.size() > 0){
			maListe.remove(0);
		}
		
	}
		
	public void deconnexion(){
		try {
			con.close();
			st.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("connexion fermé");
	}
		
	public void reqAjoutParticipant(String nomParticipant, String prenomParticipant, String nomBateau, String typeBateau, int rating ) throws SQLException{
		Connexion();
		int id = listeParticipant.size();
		try{
		     PreparedStatement prepare = con.prepareStatement("INSERT INTO `eole`.`participant` (`ID_PARTICIPANT`, `NOM_PARTICIPANT`, `PRENOM_PARTICIPANT`, `NOM_VOILIER`, `CATEGORIE_VOILIER`, `RATING`)VALUES (?, ?, ?, ?, ?, ?); ");
		     prepare.setInt (1, id);
		     prepare.setString (2, nomParticipant);
		     prepare.setString (3, prenomParticipant);
		     prepare.setString (4, nomBateau);
		     prepare.setString (5, typeBateau);
		     prepare.setInt    (6, rating);
		 
		     prepare.executeUpdate();
		     id++;
		     System.out.println("requête envoyé correctement");
		
		} catch (Exception e){
			e.printStackTrace();
		}
		
		miseAJour();
		deconnexion();
	}
	
	public void reqAjoutRegate(String nomRegate, String dateRegate, String lieuDepart, String lieuArrive, int distance, ArrayList<Participant> participantsRegate) throws SQLException{
		
		Connexion();
		 
		 int id = listeRegate.size();
		 try {
			 PreparedStatement prepare = con.prepareStatement("INSERT INTO `eole`.`regate` (`ID_REGATE`, `NOM_REGATE`, `DATE_REGATE`, `LIEU_DEPART`, `LIEU_ARRIVEE`, `DISTANCE`)VALUES (?, ?, ?, ?, ?, ?); ");
			 prepare.setInt(1, id);
			 prepare.setString (2, nomRegate);
		     prepare.setString (3, dateRegate);
		     prepare.setString (4, lieuDepart);
		     prepare.setString (5, lieuArrive);
		     prepare.setInt(6, distance);
		 
		     prepare.executeUpdate();
		     System.out.println(participantsRegate.size());
		     for (int i=0; i < participantsRegate.size(); i++) {
		    	 reqAjoutParticipantReg(participantsRegate.get(i).getIdParticipant(), id);
		     }
		     
		     System.out.println("requête envoye correctement");
		     
		 } catch (SQLException e) {
			e.printStackTrace();
		 }

		miseAJour();
		deconnexion();
	}
	
	public void reqAjoutParticipantReg(int idParticipant, int idRegate) throws SQLException{
		
		 try {
			 PreparedStatement prepare = con.prepareStatement("INSERT INTO `eole`.`classement` (`ID_PARTICIPANT`, `ID_REGATE`, `TEMPS_REEL`, `POSITION`)VALUES (?, ?, ?, ?); ");
			 prepare.setInt(1, idParticipant);
			 prepare.setInt (2, idRegate);
		     prepare.setString (3, null);
		     prepare.setInt (4, -1);
		 
		     prepare.executeUpdate();
		     
		 } catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Des doublons dans les participants ont été détéctés. Ils n'ont pas été pris en compte.", "Attention", JOptionPane.WARNING_MESSAGE);
		 }
	}
	
	public ArrayList<Regate> getlisteRegate(){
		return listeInverseRegate;
	}
	
	public ArrayList<String> getListeType(){
		return listeType;
	}
	
	public ArrayList<Participant> getParticipant(){
		return listeParticipant;
	}
	
	public ArrayList<String> getListeNomRegate() {
		return listeNomRegateInverse;
	}

	public ArrayList<Participant> getParticipantRegate(int idRegate) {
		Connexion();
		String sqlListeParticipantRegate = "SELECT * FROM participant WHERE ID_PARTICIPANT IN (SELECT ID_PARTICIPANT FROM classement WHERE ID_REGATE = " + idRegate + ")";
		try {
			ArrayList<Participant> participantCetteRegate = new ArrayList<Participant>();
			rs = st.executeQuery(sqlListeParticipantRegate);
			
			while (rs.next()) {
				Participant unParticipant = new Participant(Integer.parseInt(rs.getString(1)), rs.getString(2), rs.getString(3), rs.getString(4), Integer.parseInt(rs.getString(5)), Integer.parseInt(rs.getString(6)));
				
				
				participantCetteRegate.add(unParticipant);
			}
			deconnexion();
			return participantCetteRegate;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		deconnexion();
		return null;
		
	}
	
	public ArrayList<ArrayList<String>> getClassementRegate(int idRegate){
		Connexion();
		String sqlListeClassementRegate = "SELECT * FROM classement WHERE ID_REGATE = " + idRegate;
		
		try {
			ArrayList<String> classementParticipant = new ArrayList<String>();
			ArrayList<ArrayList<String>> classement = new ArrayList<ArrayList<String>>();
			
			rs = st.executeQuery(sqlListeClassementRegate);
			
			while (rs.next()){
				classementParticipant= new ArrayList<String>();
				classementParticipant.add(rs.getString(1));
				classementParticipant.add(rs.getString(3));
				classementParticipant.add(rs.getString(4));
				classementParticipant.add(rs.getString(5));

				classement.add(classementParticipant);
			}
			
			deconnexion();
			return classement;
		} catch (SQLException e) {

			e.printStackTrace();
		}
		deconnexion();
		return null;
	}
	
	
	
	public void sqlUpdateClassement(int idRegate, int idParticipant, int position, String temps, String tempsCompens) {
		try  
		{
			Connexion();
			PreparedStatement prepare = con.prepareStatement("UPDATE classement SET TEMPS_REEL =  ?,TEMPS_COMPENSE = ?, POSITION = ? WHERE ID_PARTICIPANT = ? AND ID_REGATE = ? ");


		    prepare.setString(1,temps);
		    prepare.setString(2,tempsCompens);
		    prepare.setInt(3,position);
		    prepare.setInt(4,idParticipant);
		    prepare.setInt(5,idRegate);
		    
		    prepare.executeUpdate();
		    deconnexion();
		 } catch (SQLException e) {
				System.out.println(e);
		 }
	}

	public void sqlUpdateClassement(int idRegate, int idParticipant, int position) {
		try  
		{
			Connexion();
			PreparedStatement prepare = con.prepareStatement("UPDATE classement SET POSITION = ? WHERE ID_PARTICIPANT = ? AND ID_REGATE = ? ");


		    prepare.setInt(1,position);
		    prepare.setInt(2,idParticipant);
		    prepare.setInt(3,idRegate);
		    
		    prepare.executeUpdate();
		    deconnexion();
		 } catch (SQLException e) {
				System.out.println(e);
		 }
	}
	
	public void sqlUpdateRegate(int idRegate, int etat) {
		try {
		Connexion();
		PreparedStatement prepare = con.prepareStatement("UPDATE regate SET ETAT_LANCEMENT = ? WHERE ID_REGATE = ?");


	    prepare.setInt(1,etat);
	    prepare.setInt(2,idRegate);
	    
	    prepare.executeUpdate();
	    deconnexion();
	 } catch (SQLException e) {
			System.out.println(e);
	 }
	}
}
