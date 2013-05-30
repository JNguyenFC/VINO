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
		
		{
			// Here is your example @Jasmine
			// (Some of these details are made up)
			// The wine we are inserting for this example is:
			// Name				: Barefoot Chardonnay
			// Region			: USA
			// Vintage			: 1940
			// Category			: White
			// Varietal			: Chardonnay
			// Sweet/Dry		: Dry
			// Producer/Winery	: Barefoot

			String region = "USA";
			String vintage = "1940";
			String category = "White";
			String varietal = "Chardonnay";
			String sweetdry = "Dry";
			String producer = "Barefoot";

			// Create the wine
			Wine example = new Wine(producer, varietal, category, region, sweetdry, vintage);

			// Save the wine to the db
			example.save();
			
			
			Wine barefoot1  = new Wine("Barefoot", "Chardonnay", "White", "California", "Dry", "1900");
			Wine barefoot2  = new Wine("Barefoot", "Cabernet Sauvignon", "Red", "California", "Dry", "1900");
			Wine barefoot3  = new Wine("Barefoot", "Impression, Red Blend", "Red", "California", "Dry", "1900");
			Wine barefoot4  = new Wine("Barefoot", "Merlot", "Red", "California", "Dry", "1900");
			Wine barefoot5  = new Wine("Barefoot", "Moscato", "White", "California", "Sweet", "1900");
			Wine barefoot6  = new Wine("Barefoot", "Pinot Grigio", "White", "California", "Dry", "1900");
			Wine barefoot7  = new Wine("Barefoot", "Pinot Noir", "Red", "California", "Dry", "1900");
			Wine barefoot8  = new Wine("Barefoot", "Riesling", "White", "California", "Sweet", "1900");
			Wine barefoot9  = new Wine("Barefoot", "Sauvignon Blanc", "White", "California", "Dry", "1900");
			Wine barefoot10 = new Wine("Barefoot", "Shiraz", "Red", "California", "Dry", "1900");
			Wine barefoot11 = new Wine("Barefoot", "White Zinfandel", "Rose", "California", "Sweet", "1900");
			Wine barefoot12 = new Wine("Barefoot", "Zinfandel", "White", "California", "Dry", "1900");
			barefoot1.save(); barefoot2.save(); barefoot3.save(); barefoot4.save(); barefoot5.save(); barefoot6.save();
			barefoot7.save(); barefoot8.save(); barefoot9.save(); barefoot10.save(); barefoot11.save(); barefoot12.save();

			Wine chuckShaw1 = new Wine("Charles Shaw", "Cabernet Sauvignon", "Red", "California", "Dry", "1900");
			Wine chuckShaw2 = new Wine("Charles Shaw", "Chardonnay", "White", "California", "Dry", "1900");
			Wine chuckShaw3 = new Wine("Charles Shaw", "Merlot", "Red", "California", "Dry", "1900");
			Wine chuckShaw4 = new Wine("Charles Shaw", "Pinot Grigio", "White", "California", "Dry", "1900");
			Wine chuckShaw5 = new Wine("Charles Shaw", "Sauvignon Blanc", "White", "California", "Dry", "1900");
			Wine chuckShaw6 = new Wine("Charles Shaw", "Shiraz", "Red", "California", "Dry", "1900");
			Wine chuckShaw7 = new Wine("Charles Shaw", "White Zinfandel", "Rose", "California", "Sweet", "1900");
			chuckShaw1.save(); chuckShaw2.save(); chuckShaw3.save(); chuckShaw4.save(); chuckShaw5.save(); 
			chuckShaw6.save(); chuckShaw7.save(); 
			
			Wine sutter1  = new Wine("Sutter Homes", "Cabernet Sauvignon", "Red", "California", "Dry", "1900");
			Wine sutter2  = new Wine("Sutter Homes", "Chardonnay", "White", "California", "Dry", "1900");
			Wine sutter3  = new Wine("Sutter Homes", "Chenin Blanc", "White", "California", "Dry", "1900");
			Wine sutter4  = new Wine("Sutter Homes", "Gewurztraminer", "White", "California", "Dry", "1900");
			Wine sutter5  = new Wine("Sutter Homes", "Merlot", "Red", "California", "Dry", "1900");
			Wine sutter6  = new Wine("Sutter Homes", "Moscato", "White", "California", "Sweet", "1900");
			Wine sutter7  = new Wine("Sutter Homes", "Pink Moscato", "Rose", "California", "Sweet", "1900");
			Wine sutter8  = new Wine("Sutter Homes", "Pink Pinot Grigio", "Rose", "California", "Dry", "1900");
			Wine sutter9  = new Wine("Sutter Homes", "Pinot Grigio", "White", "California", "Dry", "1900");
			Wine sutter10 = new Wine("Sutter Homes", "Pinot Noir", "Red", "California", "Dry", "1900");
			Wine sutter11 = new Wine("Sutter Homes", "Red Moscato", "Red", "California", "Sweet", "1900");
			Wine sutter12 = new Wine("Sutter Homes", "Riesling", "White", "California", "Sweet", "1900");
			Wine sutter13 = new Wine("Sutter Homes", "Sauvignon Blanc", "White", "California", "Dry", "1900");
			Wine sutter14 = new Wine("Sutter Homes", "Sweet Red", "Red", "California", "Sweet", "1900");
			Wine sutter15 = new Wine("Sutter Homes", "Sweet White", "White", "California", "Sweet", "1900");
			Wine sutter16 = new Wine("Sutter Homes", "White Merlot", "Rose", "California", "Dry", "1900");
			Wine sutter17 = new Wine("Sutter Homes", "White Zinfandel", "Rose", "California", "Sweet", "1900");
			Wine sutter18 = new Wine("Sutter Homes", "Zinfandel", "Red", "California", "Dry", "1900");
			sutter1.save(); sutter2.save(); sutter3.save(); sutter4.save(); sutter5.save(); sutter6.save(); 
			sutter7.save(); sutter8.save(); sutter9.save(); sutter10.save(); sutter11.save(); sutter12.save(); 
			sutter13.save(); sutter14.save(); sutter15.save(); sutter16.save(); sutter17.save(); sutter18.save(); 
			
			Wine yt1  = new Wine("Yellow Tail", "Cabernet Sauvignon", "Red", "Australia", "Dry", "1900");
			Wine yt2  = new Wine("Yellow Tail", "Cabernet-Merlot", "Red", "Australia", "Dry", "1900");
			Wine yt3  = new Wine("Yellow Tail", "Chardonnay", "White", "Australia", "Dry", "1900");
			Wine yt4  = new Wine("Yellow Tail", "Merlot", "Red", "Australia", "Dry", "1900");
			Wine yt5  = new Wine("Yellow Tail", "Pinot Grigio", "White", "Australia", "Dry", "1900");
			Wine yt6  = new Wine("Yellow Tail", "Pinot Noir", "Red", "Australia", "Dry", "1900");
			Wine yt7  = new Wine("Yellow Tail", "Riesling", "White", "Australia", "Sweet", "1900");
			Wine yt8  = new Wine("Yellow Tail", "Sauvignon Blanc", "White", "Australia", "Dry", "1900");
			Wine yt9  = new Wine("Yellow Tail", "Shiraz", "Red", "Australia", "Dry", "1900");
			Wine yt10 = new Wine("Yellow Tail", "Shiraz-Cabernet", "Red", "Australia", "Dry", "1900");
			Wine yt11 = new Wine("Yellow Tail", "Shiraz-Grenache", "Red", "Australia", "Dry", "1900");
			yt1.save(); yt2.save(); yt3.save(); yt4.save(); yt5.save(); yt6.save(); yt7.save(); 
			yt8.save(); yt9.save(); yt10.save(); yt11.save(); 
		}

		//Wine a = new Wine("Castello Banfi", "Italy", "2008", 12.9, "red", "Chianti", "dry");
		//new Wine("Chardonnay", "USA","2013",3,"white","Barefoot","dry").save();
		//a.save();
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
