/**
 * Filename:    DatabaseManager.java
 * Team:        VINO
 * Description: 
 * Date:        08 Jun 2013
 **/

package com.yoloswag.vino.db;

import java.sql.SQLException;
import java.util.List;

import com.yoloswag.vino.model.Entry;
import com.yoloswag.vino.model.Wine;

import android.content.Context;

public class DatabaseManager 
{
    static private DatabaseManager instance;

    /** Creates a new DatabaseManager in this Context
     */
    static public void init(Context ctx) 
    {
        if (null == instance) 
        {
            instance = new DatabaseManager(ctx);
        }
    }

    /** Gets a DatabaseManager object
     */
    static public DatabaseManager getInstance() 
    {
        return instance;
    }

    /** Constructor for a DatabaseManager initialized to helper
     */
    private DatabaseHelper helper;
    private DatabaseManager(Context ctx) 
    {
        helper = new DatabaseHelper(ctx);
    }

    /** Gets a helper (DatabaseManager)
     */
    private DatabaseHelper getHelper() 
    {
        return helper;
    }

    /** Gets all entries from user's database
     */
    public List<Entry> getAllEntries() 
    {
        List<Entry> entries = null;
        try 
        {
            entries = getHelper().getEntryDao().queryForAll();
        } catch (SQLException e) 
        {
            e.printStackTrace();
        }
        return entries;
    }

    /** Gets all wines from the user's database
     */
    public List<Wine> getAllWines() 
    {
        List<Wine> entries = null;
        try 
        {
            entries = getHelper().getWineDao().queryForAll();
        } catch (SQLException e) 
        {
            e.printStackTrace();
        }
        return entries;
    }

    /** Updates log entry in database after user edits
     */
	public void updateEntry(Entry entry) 
	{
        try 
        {
            getHelper().getEntryDao().createOrUpdate(entry);
        } catch (SQLException e) 
        {
            e.printStackTrace();
        }
	}

	/** Updates wine details in database after user edits in log entry
	 */
	public void updateWine(Wine wine) 
	{
        try 
        {
            getHelper().getWineDao().createOrUpdate(wine);
        } catch (SQLException e) 
        {
            e.printStackTrace();
        }
	}

	/** Deletes Entry from database
	 */
	public void deleteEntry(Entry entry) 
	{
        try 
        {
            getHelper().getEntryDao().delete(entry);
        } catch (SQLException e) 
        {
            e.printStackTrace();
        }
	}
}