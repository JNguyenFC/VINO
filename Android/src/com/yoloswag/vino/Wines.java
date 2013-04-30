package com.yoloswag.vino;

public class Wines {
	
	public int year;
	public String name;
	public String grapes;
	public boolean vintage;
	public double alcoholContent;
	Category category;
	Varietal varietal;
	SweetOrDry sweetOrDry;
	ServingTemperature servingTemperature;

	public Producer getProducer() {
		// TODO actual implementation that works
		return new Producer();
	}

	public enum ServingTemperature {
		ROOMTEMP, CHILLED
	}

	public enum SweetOrDry {
		SWEET, DRY
	}

	public enum Varietal {
		CABERNET_SAUVIGNON, CHARDONNAY, CHIANTI, MERLOT, PINOT_GRIGIO,
		PINOT_NOIR, RIESLING, SANGIOVESE, SAUVIGNON_BLANC, SYRAH,
		WHITE_ZINFANDEL
	}

	public enum Category {
		BLEND, DESSERT, FORTIFIED, FRUIT, RED, ROSE, SPARKLING, WHITE
	}
	
}