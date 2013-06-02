package com.yoloswag.vino.model;
import java.io.Serializable;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Date;
import java.util.List;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.yoloswag.vino.db.DatabaseManager;

@DatabaseTable
public class Entry implements Serializable
{
	@DatabaseField(generatedId=true)
	public int id;
	@DatabaseField
	public String title;
	@DatabaseField
	public String comment;
	@DatabaseField
	public String location;
	//@DatabaseField
	//protected Image image;
	//protected Image image;
    @DatabaseField(foreign=true,foreignAutoRefresh=true)
	public Wine wine;
	@DatabaseField
	public Date postDate;
	@DatabaseField
	public String uri;
	
	/**  Fake database of Entry (to be deleted later) */
	 
	public static Entry a = new Entry(Wine.a, "fuck you max", "France", "To all the ladies in the place with style and grace, allow me to lace these lyrical douches in your bushes. Who rock grooves and make moves with all the mommies? The back of the club, sippin' Moet, is where you'll find me. The back of the club, mackin' hoes, my crew's behind me; mad question askin', blunt passin', music blastin' but I just can't quit.");
	public static Entry b = new Entry(Wine.b, "#drank", "US", "gary");
	public static Entry c = new Entry(Wine.c, "#yolo", "Canada", "i love justin timberlake");
	public static Entry d = new Entry(Wine.d, "suq madiq", "Germany",  "derp");
	public static Entry e = new Entry(Wine.e, "liqa madiq", "Italy", "trolls");
	
	public Entry() {
		
	}
	
	//constructor for fake database
	public Entry(Wine wine, String title, String location, String comment) {
		this.wine = Wine.get(wine);
		this.location = location;
		this.comment = comment;
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
		DatabaseManager.getInstance().deleteEntry(this);
	}
	
	public Bitmap getImage() {
	    FileInputStream in;
		try {
			String name = uri;
			in = new FileInputStream(name);
		    Bitmap bitmap = BitmapFactory.decodeStream(in);
		    return bitmap;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
