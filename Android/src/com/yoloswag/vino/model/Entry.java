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
public class Entry implements Serializable
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

	// Empty constructor
	public Entry(){
	}
	
	// getting all entries
	public static Entry[] getAll() {	
		List<Entry> wines = DatabaseManager.getInstance().getAllEntries();
		return wines.toArray(new Entry[wines.size()]);
	}
	
	// creating new entry
	public static Entry create() {
		return new Entry();
	}
	
	// saving entry
	public void save() {
		//stop whineing
        if(wine != null)
        	wine.save();
        
        // getting timestamp        
        Date today;
        DateFormat dateFormatter;
        dateFormatter = DateFormat.getDateInstance();
        today = new Date();
        postDate = dateFormatter.format(today);

        DatabaseManager.getInstance().updateEntry(this);
	}
	
	// destroying entry
	public void destroy() {
		DatabaseManager.getInstance().deleteEntry(this);
	}
	
	// getting image
	public Bitmap getImage() {
	    FileInputStream in;
		try {
			String name = uri;
			in = new FileInputStream(name);
		    Bitmap bitmap = BitmapFactory.decodeStream(in);
		    return bitmap;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
}
