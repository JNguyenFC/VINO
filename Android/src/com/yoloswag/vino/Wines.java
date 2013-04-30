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
		BARBERA, CABERNET_FRANC, CABERNET_SAUVIGNON, CAYETANA, CHARDONNAY,
		CHARBONO, CHENIN_BLANC, CHIANTI, CINSAULT, GAMAY, GEWURZTRAMINER,
		GRENACHE, GRUNER_VELTLINER, MACABEO, MALBEC, MERLOT, MOSCATO,
		PINOT_BLANC, PINOT_GRIGIO, PINOT_NOIR, RIESLING, SANGIOVESE, SAUVIGNON_BLANC,
		SYRAH, ZINFANDEL
	}

	public enum Category {
		BLEND, DESSERT, FORTIFIED, FRUIT, RED, ROSE, SPARKLING, WHITE
	}
	
}