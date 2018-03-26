package fr.regate.project.model;


public class Regate 
{
	private int idRegate, distance;
	private String nameRegate, dateRegate, startPoint, endPoint;
	private int status;
	
	public Regate(int idRegate, int distance, String nameRegate, String dateRegate, String startPoint,
				  String endPoint, int status)
	{
		super();
		this.idRegate = idRegate;
		this.distance = distance;
		this.nameRegate = nameRegate;
		this.dateRegate = dateRegate;
		this.startPoint = startPoint;
		this.endPoint = endPoint;
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

	public String getDateRegate() 
	{
		return dateRegate;
	}

	public String getStartPoint()
	{
		return startPoint;
	}

	public String getEndPoint()
	{
		return endPoint;
	}
	
	public int getStatus() {
		return status;
	}
}
