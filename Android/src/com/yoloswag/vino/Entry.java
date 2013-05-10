package com.yoloswag.vino;

import java.util.Date;

public class Entry 
{
	protected String title;
	protected String comment;
	protected String location;
	protected Rating rating;
	protected Glass glass;
	protected Image image;
	protected Quantity quantity;
	public Date postDate;

	/**  Fake database of Entry (to be deleted later)
	 */
	protected static Entry a = new Entry(Wine.a, "France", "yoloswaging it up", 5, 750);
	protected static Entry b = new Entry(Wine.b, "US", "gary", 4, 250);
	protected static Entry c = new Entry(Wine.c, "Canada", "i love justin timberlake", 3, 10);
	protected static Entry d = new Entry(Wine.d, "Germany",  "derp", 2, 100);
	protected static Entry e = new Entry(Wine.e, "Italy", "trolls", 1, 1000);
	
	//wine class has all the details
	protected Wine wine;
	
	public Entry() 
	{
	}
	
	//constructor for fake database
	public Entry(Wine wine, String location, String comment, int rating,
			     int quantity)
	{
		this.wine = Wine.get(wine);
		this.location = location;
		this.comment = comment;
		this.rating = new Rating(rating);
		this.quantity = new Quantity(quantity);
	}

	public static Entry[] getAll() 
	{	
		return new Entry[] { a, b, c, d, e };
	}
	
	public static Entry create() 
	{
		return new Entry();
	}
	
	public void save()
	{
		
	}
	
	public void destroy()
	{
		
	}
}
