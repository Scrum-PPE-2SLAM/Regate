package fr.regate.project.model;


import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.sql.*;


public class RequestBdd {


	private ArrayList<Participant> listParticipant = new ArrayList<Participant>();
	private ArrayList<Regate> listRegate = new ArrayList<Regate>();
	private ArrayList<Ship> listShip = new ArrayList<Ship>();
	
	
	/**
	 * Récupère la liste des participants inscris dans la bdd
	 * 
	 * @return liste de tout les participants
	 * @throws SQLException
	 */
	public ArrayList<Participant> getListParticipant() throws SQLException{
		String requestGetAllParticipant = "SELECT * FROM participant";
		BddConnection.setRs(BddConnection.getSt().executeQuery(requestGetAllParticipant));
		while(BddConnection.getRs().next()) {
			Participant participant = new Participant(BddConnection.getRs().getInt(1), BddConnection.getRs().getString(2),
					BddConnection.getRs().getString(3), BddConnection.getRs().getString(4), BddConnection.getRs().getString(5));
			
			listParticipant.add(participant);
		}
		return listParticipant;
	}

	/**
	 * Récupère la lise des régates dans la bdd
	 * 
	 * @return liste des différentes régates
	 * @throws SQLException
	 */
	public ArrayList<Regate> getListRegate() throws SQLException {
		String requestGetAllRegate = "SELECT * FROM regate";
		BddConnection.setRs(BddConnection.getSt().executeQuery(requestGetAllRegate));
		while(BddConnection.getRs().next()) {
			Regate regate = new Regate(BddConnection.getRs().getInt(1), BddConnection.getRs().getString(2), BddConnection.getRs().getDate(3), BddConnection.getRs().getString(4),
					BddConnection.getRs().getString(5), BddConnection.getRs().getInt(6), BddConnection.getRs().getInt(7));
			
			listRegate.add(regate);
		}
		return listRegate;
	}
	
	/**
	 * Récupère la liste des bateaux dans la bdd
	 * 
	 * @return ArrayList<Ship> 
	 * @throws SQLException
	 */
	public ArrayList<Ship> getListShip() throws SQLException {
		String requestGetAllShip = "SELECT * FROM ship";
		BddConnection.setRs(BddConnection.getSt().executeQuery(requestGetAllShip));
		while(BddConnection.getRs().next()) {
			Ship ship = new Ship(BddConnection.getRs().getInt(1), BddConnection.getRs().getString(2), BddConnection.getRs().getInt(3), BddConnection.getRs().getInt(4));
			
			listShip.add(ship);
		}
		return listShip;
	}
	
	/**
	 * Ajout d'un participant dans la base de données 
	 * 
	 * @param name
	 * @param firstName
	 * @param phone
	 * @param email
	 * @throws SQLException
	 */
	public void reqAddParticipant(String name, String firstName, String phone, String email) throws SQLException {
		PreparedStatement prepare = BddConnection.getCon().prepareStatement("INSERT INTO `eole`.`participant` (`P_NAME`, `P_FIRSTNAME`, `P_PHONE`, `P_EMAIL`)"
				+ "VALUES (?, ?, ?, ?); ");
		prepare.setString (1, name);
	    prepare.setString (2, firstName);
	    prepare.setString (3, phone);
	    prepare.setString (4, email);
		
	    prepare.executeUpdate();
	    System.out.println("request send !");
	}
	
	
	/**
	 * Ajout d'une regate dans la base de données
	 * 
	 * @param nameRegate
	 * @param dateRegate
	 * @param startPlace
	 * @param endPlace
	 * @param distance
	 * @param status
	 * @throws SQLException
	 */
	public void reqAddRegate(String nameRegate, Date dateRegate, String startPlace, String endPlace, int distance, int status) throws SQLException {
		PreparedStatement prepare = BddConnection.getCon().prepareStatement("INSERT INTO `eole`.`regate` (`R_NAME`, `R_DATE `, `R_STARTPLACE`, `R_ENDPLACE`, `R_DISTANCE`, `R_STATUS`)"
				+ "VALUES (?, ?, ?, ?, ?, ?); ");
		prepare.setString (1, nameRegate);
	    prepare.setDate (2, dateRegate);
	    prepare.setString (3, startPlace);
	    prepare.setString (4, endPlace);
	    prepare.setInt (5, distance);
	    prepare.setInt (6, status);
		
	    prepare.executeUpdate();
	    System.out.println("request send !");
	}
	
	/**
	 * Ajout d'un bateau dans la base de données 
	 * 
	 * @param nameShip
	 * @param dateRegate
	 * @param Category
	 * @param rating
	 * @throws SQLException
	 */
	public void reqAddShip(String nameShip, Date dateRegate, int Category, int rating) throws SQLException {
		PreparedStatement prepare = BddConnection.getCon().prepareStatement("INSERT INTO `eole`.`ship` (`S_NAME`, `S_CATEGORY `, `S_RATING`)"
				+ "VALUES (?, ?, ?); ");
		prepare.setString (1, nameShip);
	    prepare.setDate (2, dateRegate);
	    prepare.setInt (5, Category);
	    prepare.setInt (6, rating);
		
	    prepare.executeUpdate();
	    System.out.println("request send !");
	}
	
	/**
	 * Request for link participant/ship/regate in classement table 
	 * 
	 * @param idParticipant
	 * @param idRegate
	 * @param idShip
	 * @param date
	 * @throws SQLException
	 */
	public void reqLinkPartToRegate(int idParticipant, int idRegate, int idShip, Date date) throws SQLException{
		 PreparedStatement prepare = BddConnection.getCon().prepareStatement("INSERT INTO `eole`.`classement` (`S_ID`, `P_ID`, `R_ID`, `I_DATE`)"
		 		+ "VALUES (?, ?, ?, ?); ");
		 prepare.setInt(1, idShip);
		 prepare.setInt(2, idParticipant);
		 prepare.setInt (3, idRegate);
	     prepare.setDate (4, date);
	 
	     prepare.executeUpdate();
	     System.out.println("Request send !");
	}
	
}
