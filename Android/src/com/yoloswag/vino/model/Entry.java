package com.yoloswag.vino.model;
import java.io.Serializable;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.yoloswag.vino.db.DatabaseManager;

@DatabaseTable
public class Entry implements Serializable //Class of objects created in New Entry
{
	private static final long serialVersionUID = 1L;
	@DatabaseField(generatedId=true)
	public int id;
	@DatabaseField
	public String title;
	@DatabaseField
	public String comment;
    @DatabaseField(foreign=true,foreignAutoRefresh=true)
	public Wine wine;
	@DatabaseField
	public String postDate;
	@DatabaseField
	public String uri;
	
	// Constructor for new Entry
	public Entry() {
		
	}

	// Getting all entries
	public static Entry[] getAll() {	
		List<Entry> wines = DatabaseManager.getInstance().getAllEntries();
		return wines.toArray(new Entry[wines.size()]);
	}
	
	// Creating a new entry
	public static Entry create() {
		return new Entry();
	}
	
	// Saving wine
	public void save() {
		// Make sure wine exists
        if(wine != null)
        	wine.save();
        
        // Getting timestamp
        Date today;
        DateFormat dateFormatter;
        dateFormatter = DateFormat.getDateInstance();
        today = new Date();
        postDate = dateFormatter.format(today);
        
        // Updating database
        DatabaseManager.getInstance().updateEntry(this);
	}
	
	// Destroying entry from database
	public void destroy() {
		DatabaseManager.getInstance().deleteEntry(this);
	}
	
	// Getting image 
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
