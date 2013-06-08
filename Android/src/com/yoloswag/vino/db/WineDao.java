package com.yoloswag.vino.db;

import java.sql.SQLException;
import java.util.List;

import com.j256.ormlite.dao.Dao;
import com.yoloswag.vino.model.Wine;

public class WineDao {
	
	private Dao<Wine, Integer> dao;

	public WineDao(DatabaseHelper helper) {
		try {
			this.dao = helper.getDao(Wine.class);
		} catch (SQLException e) {
			this.dao = null;
		}
	}

    public List<Wine> getAllWines() {
        try {
            return dao.queryForAll();
        } catch (SQLException e) {
        	
        }
        return null;
    }

	public void updateWine(Wine entry) {
        try {
        	dao.createOrUpdate(entry);
        } catch (SQLException e) {
        	
        }
	}

	public void deleteWine(Wine entry) {
        try {
        	dao.delete(entry);
        } catch (SQLException e) {
        	
        }
	}
}
