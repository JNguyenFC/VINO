package com.yoloswag.vino;

public class Wine 
{
  protected int vintage;
	protected double alcoholContent;
	protected String name;
	protected String region;
	protected Category category;
	protected Varietal varietal;
	protected SweetOrDry sweetOrDry;
	protected ServingTemp servingTemp;

	/**  Default constructor
	 */
	public Wine() 
	{
	}
	
	/**  All-member constructor for fake database
	 */
	public Wine(String name, String region, int vintage, double alcoholContent,
			        String category, String varietal, String sweetOrDry,
			        String servingTemp) 
	{
		this.name           = name;
		this.region         = region;
		this.vintage        = vintage;
		this.alcoholContent = alcoholContent;
		this.category       = new Category(category);
		this.varietal       = new Varietal(varietal);
		this.sweetOrDry     = new SweetOrDry(sweetOrDry);
		this.servingTemp    = new ServingTemp(servingTemp);
	}
	
	/**  Returns all wines with their corresponding data
	 */
	public static Wine[] getAll() 
	{
		Wine a = new Wine("Castello Banfi", "Italy", 2008, 12.9, "red", 
				              "Chianti", "dry", "room temperature" );
		Wine b = new Wine("Barefoot", "California", 2009, 7.0, "white",
				              "Moscato", "sweet", "chilled");
		Wine c = new Wine("Montana", "New Zealand", 2011, 13.4, "white",
				              "Sauvignon Blanc", "dry", "chilled");
		Wine d = new Wine("Yellow Tail", "Australia", 2011, 14.8, "red",
				              "Cabernet Sauvignon", "dry", "room temperature");
		Wine e = new Wine("Chateau Ste. Michelle", "Florida", 2012, 11.2,
				              "white", "Riesling", "sweet", "chilled");
		
		return new Wine[] {a, b, c, d, e};
	}
	
	public Producer getProducer() 
	{
		// TODO actual implementation that works
		return new Producer();
	}

	/***  Replacing enums with classes
	
	public enum ServingTemperature 
	{
		ROOMTEMP, CHILLED
	}

	public enum SweetOrDry 
	{
		SWEET, DRY
	}

	public enum Varietal 
	{
		BARBERA, CABERNET_FRANC, CABERNET_SAUVIGNON, CAYETANA, CHARDONNAY,
		CHARBONO, CHENIN_BLANC, CHIANTI, CINSAULT, GAMAY, GEWURZTRAMINER,
		GRENACHE, GRUNER_VELTLINER, MACABEO, MALBEC, MERLOT, MOSCATO,
		PINOT_BLANC, PINOT_GRIGIO, PINOT_NOIR, RIESLING, SANGIOVESE, SAUVIGNON_BLANC,
		SYRAH, ZINFANDEL
	}

	public enum Category 
	{
		BLEND, DESSERT, FORTIFIED, FRUIT, RED, ROSE, SPARKLING, WHITE
	}
	
	***/
}
