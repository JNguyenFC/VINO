package com.yoloswag.vino.db;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.yoloswag.vino.model.Entry;
import com.yoloswag.vino.model.Wine;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {
    // name of the database file for your application -- change to something appropriate for your app
    private static final String DATABASE_NAME = "VxIxNxOdDxB.sqlite";

    // any time you make changes to your database objects, you may have to increase the database version
    private static final int DATABASE_VERSION = 1;

    // the DAO object we use to access the SimpleData table
    private Dao<Entry, Integer> entryDao = null;
    private Dao<Wine, Integer> wineDao = null;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database,ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, Entry.class);
            TableUtils.createTable(connectionSource, Wine.class);
        } catch (SQLException e) {
            Log.e(DatabaseHelper.class.getName(), "Can't create database", e);
            throw new RuntimeException(e);
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
        Wine a = new Wine("Castello Banfi", "Italy", "2008", 12.9, "red", "Chianti", "dry");
        new Wine("Chardonnay", "USA","2013",3,"white","Barefoot","dry").save();
        a.save();
    	Entry b = new Entry(Wine.a, "title1", "France", "yoloswaging it up", 3);
    	b.save();
    	Entry.a.save();
    	Entry.b.save();
    	Entry.c.save();
    	Entry.d.save();
    	Entry.e.save();
    	Entry e = new Entry(Wine.c, "title2", "New Zealand", "hi", 1);
    	e.save();
    	Entry d = new Entry(Wine.d, "title3", "California", "WHISTLE GOES WOO WOO", 5);
    	d.save();
    	Entry c = new Entry(Wine.a, "title4", "France", "If you see this it doesn't work ]:", 3);
    	c.save();
    	c.comment = "If you see this then it works [:";
    	c.save();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db,ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            List<String> allSql = new ArrayList<String>(); 
            switch(oldVersion) 
            {
              case 1: 
                  //allSql.add("alter table AdData add column `new_col` VARCHAR");
                  //allSql.add("alter table AdData add column `new_col2` VARCHAR");
            }
            for (String sql : allSql) {
                db.execSQL(sql);
            }
        } catch (SQLException e) {
            Log.e(DatabaseHelper.class.getName(), "exception during onUpgrade", e);
            throw new RuntimeException(e);
        }
        
    }

    public Dao<Entry, Integer> getEntryDao() {
        if (null == entryDao) {
            try {
                entryDao = getDao(Entry.class);
            }catch (java.sql.SQLException e) {
                e.printStackTrace();
            }
        }
        return entryDao;
    }

    public Dao<Wine, Integer> getWineDao() {
        if (null == wineDao) {
            try {
                wineDao = getDao(Wine.class);
            }catch (java.sql.SQLException e) {
                e.printStackTrace();
            }
        }
        return wineDao;
    }

}
