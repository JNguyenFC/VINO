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
	
	public Entry() {
	}
	
	//constructor for fake database
	public Entry(Wine wine, String location, int rating, String comment){
		this.wine = Wine.get(wine);
		this.location = location;
		this.rating = rating;
		this.comment = comment;
	}

	public static Entry[] getAll(){
		
		Entry a = new Entry(Wine.a, "France", 5, "yoloswaging it up");
		Entry b = new Entry(Wine.b, "US", 4, "gary");
		Entry c = new Entry(Wine.c, "Canada", 3, "gillespie");
		Entry d = new Entry(Wine.d, "Germany", 2, "derp");
		Entry e = new Entry(Wine.e, "Italy", 1, "trolls");
		
		return new Entry[] { a, b, c, d, e };
	}
	
	public static Entry create() {
		return new Entry();
	}
	
	public void save(){
		
	}
	
	public void destroy(){
		
	}

}
