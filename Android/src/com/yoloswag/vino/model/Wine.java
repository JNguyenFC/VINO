/**
 * Filename:    Wine.java
 * Team:		VINO
 * Description: 
 * Date:        8 Jun 2013
 **/

package com.yoloswag.vino.model;

import java.io.Serializable;
import java.util.List;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.yoloswag.vino.db.DatabaseManager;

public class Wine implements Serializable
{
	private static final long serialVersionUID = 10L;
	@DatabaseField(generatedId=true)
	public int id;
	@DatabaseField(dataType=DataType.SERIALIZABLE)
	public Vintage vintage;
	@DatabaseField(dataType=DataType.SERIALIZABLE)
	public Name name;
	@DatabaseField(dataType=DataType.SERIALIZABLE)
	public Region region;
	@DatabaseField(dataType=DataType.SERIALIZABLE)
	public Category category;
	@DatabaseField(dataType=DataType.SERIALIZABLE)
	public Varietal varietal;
	@DatabaseField(dataType=DataType.SERIALIZABLE)
	public SweetOrDry sweetOrDry;
	@DatabaseField
	public double rating;   // Single rating for a Wine
	@DatabaseField
	public double ratings;  // Average rating for a Wine
	
	/**  Default constructor
	 */
	public Wine() 
	{
	}
	
	//TODO: fix vintage
	/**  All-member constructor for database
	 */
	public Wine(String name, String varietal, String category, String region,
	            String sweetOrDry, String vintage)
	{
		this.name           = new Name(name);
		this.varietal       = new Varietal(varietal); 
		this.category       = new Category(category);
		this.region         = new Region(region);
		this.sweetOrDry     = new SweetOrDry(sweetOrDry);
		this.vintage        = new Vintage(vintage);
		this.rating = 0;
		this.ratings = 0;
	}
	
	/** Adds a rating to the given Wine
	 */
	public void addRating(double newRating) 
	{
		this.rating = (rating * ratings + newRating) / ++ratings;
	}
	
	/**  Getter for Entry
	 */
	public static Wine get(Wine wine) 
	{
		return wine;
	}
	
	/**  Gets all wines with their corresponding data from the database
	 */
	public static Wine[] getAll() 
	{	
		List<Wine> wines = DatabaseManager.getInstance().getAllWines();
		return wines.toArray(new Wine[wines.size()]);
	}

	/** Saves updated Wine into database
	 */
	public void save() 
	{
        DatabaseManager.getInstance().updateWine(this);
	}
}
