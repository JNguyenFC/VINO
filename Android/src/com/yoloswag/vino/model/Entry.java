package com.yoloswag.vino.model;

import java.util.Date;

public class Entry 
{
	protected String title;
	public String comment;
	protected String location;
	public int rating;
	protected Glass glass;
	protected Image image;
	protected Quantity quantity;
	public Wine wine;
	public Date postDate;
	
	/**  Fake database of Entry (to be deleted later)
	 */
	protected static Entry a = new Entry(Wine.a, "France", "yoloswaging it up", 3, 750);
	protected static Entry b = new Entry(Wine.b, "US", "gary", 5, 250);
	protected static Entry c = new Entry(Wine.c, "Canada", "i love justin timberlake", 1, 10);
	protected static Entry d = new Entry(Wine.d, "Germany",  "derp", 4, 100);
	protected static Entry e = new Entry(Wine.e, "Italy", "trolls", 2, 1000);
	
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
		this.rating = rating;
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
