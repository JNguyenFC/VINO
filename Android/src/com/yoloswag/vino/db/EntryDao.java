package com.yoloswag.vino.db;

import java.sql.SQLException;
import java.util.List;

import com.j256.ormlite.dao.Dao;
import com.yoloswag.vino.model.Entry;

public class EntryDao {
	
	private Dao<Entry, Integer> dao;

	public EntryDao(DatabaseHelper helper) {
		try {
			this.dao = helper.getDao(Entry.class);
		} catch (SQLException e) {
			this.dao = null;
		}
	}

    public List<Entry> getAllEntries() {
        try {
            return dao.queryForAll();
        } catch (SQLException e) {
        	
        }
        return null;
    }

	public void updateEntry(Entry entry) {
        try {
        	dao.createOrUpdate(entry);
        } catch (SQLException e) {
        	
        }
	}

	public void deleteEntry(Entry entry) {
        try {
        	dao.delete(entry);
        } catch (SQLException e) {
        	
        }
	}
}
