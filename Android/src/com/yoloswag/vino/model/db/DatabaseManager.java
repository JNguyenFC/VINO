/**
 * Filename:    DatabaseManager.java
 * Team:        VINO
 * Description: 
 * Date:        08 Jun 2013
 **/

package com.yoloswag.vino.model.db;

import com.yoloswag.vino.model.entry.EntryDao;
import com.yoloswag.vino.model.wine.WineDao;

import android.content.Context;

public class DatabaseManager 
{
    static private DatabaseManager instance;

    private DatabaseHelper helper;
    private WineDao wineDao;
    private EntryDao entryDao;
	
    private DatabaseManager(Context ctx) {
        helper = new DatabaseHelper(ctx);
        setWineDao(new WineDao(helper));
        setEntryDao(new EntryDao(helper));
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

	public WineDao getWineDao() {
		return wineDao;
	}

	private void setWineDao(WineDao wineDao) {
		this.wineDao = wineDao;
	}

	public EntryDao getEntryDao() {
		return entryDao;
	}

	private void setEntryDao(EntryDao entryDao) {
		this.entryDao = entryDao;
	}
}