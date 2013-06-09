package com.yoloswag.vino.model.wine;

import java.util.List;

import com.yoloswag.vino.model.db.DatabaseManager;

public class WineManager {

	public static void addOrUpdate(Wine wine) {
		DatabaseManager.getInstance().getWineDao().updateWine(wine);
	}

	public static List<Wine> getAllWines() {
		return DatabaseManager.getInstance().getWineDao().getAllWines();
	}
}
