package com.yoloswag.vino.db;

import java.util.List;

import com.yoloswag.vino.model.Wine;

public class WineDispatch {

	public static void addOrUpdate(Wine wine) {
		WineManager.addOrUpdate(wine);
	}

	public static List<Wine> getAllWines() {
		// TODO Auto-generated method stub
		return WineManager.getAllWines();
	}

}
