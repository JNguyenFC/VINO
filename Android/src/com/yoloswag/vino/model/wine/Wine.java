/**
 * Filename:    Wine.java
 * Team:		VINO
 * Description: 
 * Date:        8 Jun 2013
 **/

package com.yoloswag.vino.model.wine;

import java.io.Serializable;
import java.util.List;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;

public class Wine implements Serializable
{
	private static final long serialVersionUID = 10L;
	@DatabaseField(generatedId=true)
	public int id;
	@DatabaseField(dataType=DataType.SERIALIZABLE)
	public String vintage;
	@DatabaseField(dataType=DataType.SERIALIZABLE)
	public String name;
	@DatabaseField(dataType=DataType.SERIALIZABLE)
	public String region;
	@DatabaseField(dataType=DataType.SERIALIZABLE)
	public String category;
	@DatabaseField(dataType=DataType.SERIALIZABLE)
	public String varietal;
	@DatabaseField(dataType=DataType.SERIALIZABLE)
	public String sweetOrDry;
	@DatabaseField
	public double rating;   // Single rating for a Wine
	@DatabaseField
	public double ratings;  // Average rating for a Wine
		
	/**  All-member constructor for database
	 */
	public Wine() {
		
	}
	
	public Wine(String name, String varietal, String category, String region,
	            String sweetOrDry, String vintage) {
		this.name           = name;
		this.varietal       = varietal; 
		this.category       = category;
		this.region         = region;
		this.sweetOrDry     = sweetOrDry;
		this.vintage        = vintage;
		this.rating = 0;
		this.ratings = 0;
	}
	
	/** Adds a rating to the given Wine
	 */
	public void addRating(double newRating) {
		this.rating = (rating * ratings + newRating) / ++ratings;
	}
	
	/**  Gets all wines with their corresponding data from the database
	 */
	public static Wine[] getAll() {	
		List<Wine> wines = WineAction.getAllWines();
		return wines.toArray(new Wine[wines.size()]);
	}

	public void save() {
		WineAction.updateWine(this);
	}
}
