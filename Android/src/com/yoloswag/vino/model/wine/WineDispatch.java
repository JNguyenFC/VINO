package com.yoloswag.vino.model.wine;

import java.util.List;


public class WineDispatch {

	public static void addOrUpdate(Wine wine) {
		WineManager.addOrUpdate(wine);
	}

	public static List<Wine> getAllWines() {
		// TODO Auto-generated method stub
		return WineManager.getAllWines();
	}

}
