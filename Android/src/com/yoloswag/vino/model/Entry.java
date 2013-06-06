/**
 * Filename:    Entry.java
 * Team:		VINO
 * Description: 
 * Date:        8 Jun 2013
 **/

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

	// Default constructor
	public Entry(){
	}
	
	/** Retrieves all entries currently in the user's database
	 */
	public static Entry[] getAll() 
	{	
		List<Entry> wines = DatabaseManager.getInstance().getAllEntries();
		return wines.toArray(new Entry[wines.size()]);
	}
	
	/** Creates a new Entry
	 */
	public static Entry create() 
	{
		return new Entry();
	}
	
	/** Saves Wine in the given Entry to the database
	 */
	public void save() 
	{
        if(wine != null)
        	wine.save();
        
        // Gets timestamp of Entry submission       
        Date today;
        DateFormat dateFormatter;
        dateFormatter = DateFormat.getDateInstance();
        today = new Date();
        postDate = dateFormatter.format(today);

        // Updates Entry in database
        DatabaseManager.getInstance().updateEntry(this);
	}
	
	/** Deletes Entry from database
	 */
	public void destroy() 
	{
		DatabaseManager.getInstance().deleteEntry(this);
	}
	
	// Gets photo
	public Bitmap getImage() 
	{
	    FileInputStream in;
		try {
			String name = uri;
			in = new FileInputStream(name);
		    Bitmap bitmap = BitmapFactory.decodeStream(in);
		    
		    return bitmap;
		} catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
		return null;
	}
}
