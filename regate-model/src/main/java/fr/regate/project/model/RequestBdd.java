package fr.regate.project.model;


import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.sql.*;


public class RequestBdd {


	private ArrayList<Participant> listParticipant = new ArrayList<Participant>();
	private ArrayList<Regate> listRegate = new ArrayList<Regate>();
	
	
	/**
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
	
//	public void reqLinkPartToRegate(int idParticipant, int idRegate) {
//		 PreparedStatement prepare = BddConnection.getCon().prepareStatement("INSERT INTO `eole`.`classement` (`ID_PARTICIPANT`, `ID_REGATE`, `TEMPS_REEL`, `POSITION`)VALUES (?, ?, ?, ?); ");
//		 prepare.setInt(1, idParticipant);
//		 prepare.setInt (2, idRegate);
//	     prepare.setString (3, null);
//	     prepare.setInt (4, -1);
//	 
//	     prepare.executeUpdate();
//	}
	
}
