package com.yoloswag.vino.model.wine;

import java.util.List;


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
