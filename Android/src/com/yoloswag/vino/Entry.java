package com.yoloswag.vino;

import java.util.Date;

public class Entry {
	
	protected String title;
	protected String comment;
	protected String location;
	protected int rating;
	protected Glass glass;
	protected Image image;
	public Date postDate;
	int mlConsumed;
	
	//wine class has all the details
	protected Wine wine;
	
	//constructor for fake databaase
	public Entry(Wine wine, String location, int rating, String comment){
		this.wine = new Wine();
		this.location = location;
		this.rating = rating;
		this.comment = comment;
	}

	public static Entry[] getAll(){
		
		Entry a = new Entry(new Wine(), "France", 5, "yoloswaging it up");
		Entry b = new Entry(new Wine(), "US", 4, "gary");
		Entry c = new Entry(new Wine(), "Canada", 3, "gillespie");
		Entry d = new Entry(new Wine(), "Germany", 2, "derp");
		Entry e = new Entry(new Wine(), "Italy", 1, "trolls");
		
		return new Entry[] { a, b, c, d, e };
	}
	
	public void save(){
		
	}
	
	public void destroy(){
		
	}

}