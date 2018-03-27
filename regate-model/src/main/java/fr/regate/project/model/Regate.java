package fr.regate.project.model;

import java.sql.Date;

public class Regate 
{
	private int idRegate, distance;
	private String nameRegate, startPlace, endPlace;
	private int status;
	private Date dateRegate;
	
	public Regate(int idRegate, String nameRegate, Date dateRegate, String startPlace,
				  String endPlace, int distance, int status)
	{
		super();
		this.idRegate = idRegate;
		this.nameRegate = nameRegate;
		this.dateRegate = dateRegate;
		this.startPlace = startPlace;
		this.endPlace = endPlace;
		this.distance = distance;
		this.status = status;
	}

	public int getIdRegate() 
	{
		return idRegate;
	}

	public int getDistance() 
	{
		return distance;
	}

	public String getNameRegate()
	{
		return nameRegate;
	}

	public Date getDateRegate() 
	{
		return dateRegate;
	}

	public String getStartPoint()
	{
		return startPlace;
	}

	public String getEndPoint()
	{
		return endPlace;
	}
	
	public int getStatus() {
		return status;
	}
}
