package com.yoloswag.vino;

public class Wines {
	
	public String name;
	public int year;
	public String grapes;
	public double alcoholContent;
	public boolean vintage;
	ServingTemperature servingTemperature;
	SweetOrDry sweetOrDry;
	WineType wineType;
	Category category;

	public Producer getProducer() {
		// TODO actual implementation that works
		return new Producer();
	}

	public enum ServingTemperature {
		RoomTemperature, Cold
	}

	public enum SweetOrDry {
		Sweet, Dry
	}

	public enum WineType {
		Cabernet_Savignon, White_Zinefindel, Merlot
	}

	public enum Category {
		Sparkling, White, Rose, Red, FortifiedSweet, Spirits 
	}
}