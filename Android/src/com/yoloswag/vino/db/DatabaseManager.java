/**
 * Filename:    DatabaseManager.java
 * Team:        VINO
 * Description: 
 * Date:        08 Jun 2013
 **/

package com.yoloswag.vino.db;

import java.util.List;

import com.yoloswag.vino.model.Entry;
import com.yoloswag.vino.model.Wine;

import android.content.Context;

public class DatabaseManager 
{
    static private DatabaseManager instance;

    private DatabaseHelper helper;
	private WineDao wineDao;
	private EntryDao entryDao;
	
    private DatabaseManager(Context ctx) {
        helper = new DatabaseHelper(ctx);
        wineDao = new WineDao(helper);
        entryDao = new EntryDao(helper);
    }

    static public void init(Context ctx) {
        if (null==instance) {
            instance = new DatabaseManager(ctx);
        }
    }

    /** Gets a DatabaseManager object
     */
    static public DatabaseManager getInstance() 
    {
        return instance;
    }

    public List<Wine> getAllWines() {
    	return wineDao.getAllWines();
    }

	public void updateWine(Wine wine) {
		wineDao.updateWine(wine);
	}

	public void deleteWine(Wine wine) {
		wineDao.deleteWine(wine);
	}

    public List<Entry> getAllEntries() {
    	return entryDao.getAllEntries();
    }

	public void updateEntry(Entry entry) {
		entryDao.updateEntry(entry);
	}

	public void deleteEntry(Entry entry) {
		entryDao.deleteEntry(entry);
	}
}