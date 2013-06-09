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
import java.util.List;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.yoloswag.vino.EntryAction;

@DatabaseTable
public class Entry implements Serializable {
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
	public Entry() {
		
	}
	
	/** Retrieves all entries currently in the user's database
	 */
	public static Entry[] getAll() {	
		List<Entry> wines = EntryAction.getAllEntries();
		return wines.toArray(new Entry[wines.size()]);
	}
	
	// Gets photo
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

	public void destroy() {
		EntryAction.destroy(this);
	}

	public void save() {
		EntryAction.save(this);
	}
}
