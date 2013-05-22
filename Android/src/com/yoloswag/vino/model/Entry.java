package com.yoloswag.vino.model;

import java.util.Date;
import java.util.List;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.yoloswag.vino.db.DatabaseManager;

@DatabaseTable
public class Entry 
{
	@DatabaseField(generatedId=true)
	public int id;
	@DatabaseField
	public String title;
	@DatabaseField
	public String comment;
	@DatabaseField
	protected String location;
	@DatabaseField
	public int rating;
	//@DatabaseField
	//protected Image image;
	//protected Image image;
    @DatabaseField(foreign=true,foreignAutoRefresh=true)
	public Wine wine;
	@DatabaseField
	public Date postDate;
	
	/**  Fake database of Entry (to be deleted later)
	 */
	protected static Entry a = new Entry(Wine.a, "France", "yoloswaging it up", 3);
	protected static Entry b = new Entry(Wine.b, "US", "gary", 5);
	protected static Entry c = new Entry(Wine.c, "Canada", "i love justin timberlake", 1);
	protected static Entry d = new Entry(Wine.d, "Germany",  "derp", 4);
	protected static Entry e = new Entry(Wine.e, "Italy", "trolls", 2);
	
	public Entry() {
		
	}
	
	//constructor for fake database
	public Entry(Wine wine, String location, String comment, int rating) {
		this.wine = Wine.get(wine);
		this.location = location;
		this.comment = comment;
		this.rating = rating;
	}

	public static Entry[] getAll() {	
		//return new Entry[] { a, b, c, d, e };
		//real shit nigga
		List<Entry> wines = DatabaseManager.getInstance().getAllEntries();
		return wines.toArray(new Entry[wines.size()]);
	}
	
	public static Entry create() {
		return new Entry();
	}
	
	public void save() {
        wine.save();
        DatabaseManager.getInstance().updateEntry(this);
	}
	
	public void destroy() {
		
	}
}
