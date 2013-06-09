package com.yoloswag.vino.db;

import java.util.List;

import com.yoloswag.vino.model.Wine;

public class WineManager {

	public static void addOrUpdate(Wine wine) {
		DatabaseManager.getInstance().getWineDao().updateWine(wine);
	}

	public static List<Wine> getAllWines() {
		return DatabaseManager.getInstance().getWineDao().getAllWines();
	}
}
