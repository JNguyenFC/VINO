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
	public String location;
	@DatabaseField
	public String vintageYear;
	@DatabaseField
	public String color;
	@DatabaseField
	public String smell;
	@DatabaseField
	public String taste;
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
	public static Entry a = new Entry(Wine.a, "fuck you max", "France", "yoloswaging it up", 3);
	public static Entry b = new Entry(Wine.b, "#drank", "US", "gary", 5);
	public static Entry c = new Entry(Wine.c, "#yolo", "Canada", "i love justin timberlake", 1);
	public static Entry d = new Entry(Wine.d, "suq madiq", "Germany",  "derp", 4);
	public static Entry e = new Entry(Wine.e, "liqa madiq", "Italy", "trolls", 2);
	
	public Entry() {
		
	}
	
	//constructor for fake database
	public Entry(Wine wine, String title, String location, String comment, int rating) {
		this.wine = Wine.get(wine);
		this.location = location;
		this.comment = comment;
		this.rating = rating;
		this.title = title;
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
		//stop whineing
        if(wine != null)
        	wine.save();
        DatabaseManager.getInstance().updateEntry(this);
	}
	
	public void destroy() {
		
	}
}
