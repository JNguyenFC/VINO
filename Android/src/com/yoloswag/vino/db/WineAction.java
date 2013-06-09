package com.yoloswag.vino.db;

import java.util.List;

import com.yoloswag.vino.model.Wine;

public class WineAction {

	public static void addWine(Wine wine) {
		WineDispatch.addOrUpdate(wine);
	}


	public static void updateWine(Wine wine) {
		WineDispatch.addOrUpdate(wine);
	}

	public static List<Wine> getAllWines() {
		// TODO Auto-generated method stub
		return WineDispatch.getAllWines();
	}
}
