package com.yoloswag.vino.model;

import java.util.List;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.yoloswag.vino.db.DatabaseManager;

public class Wine 
{
	@DatabaseField(generatedId=true)
	public int id;
	@DatabaseField(dataType=DataType.SERIALIZABLE)
	public Vintage vintage;
	@DatabaseField
	public double alcoholContent;
	@DatabaseField(dataType=DataType.SERIALIZABLE)
	public Name name;
	@DatabaseField(dataType=DataType.SERIALIZABLE)
	public Region region;
	@DatabaseField(dataType=DataType.SERIALIZABLE)
	public Category category;
	@DatabaseField(dataType=DataType.SERIALIZABLE)
	public Varietal varietal;//Varietal varietal;
	@DatabaseField(dataType=DataType.SERIALIZABLE)
	public SweetOrDry sweetOrDry;
	@DatabaseField(dataType=DataType.SERIALIZABLE)
	public ServingTemp servingTemp;
	
	/**  Fake database of Wine (to be deleted later)
	 */
	public static Wine a = new Wine("Castello Banfi", "Italy", "2008", 12.9, "red", "Chianti", "dry", "room temperature" );
	public static Wine b = new Wine("Yellow Tail", "California", "2009", 7.0, "white", "Moscato", "sweet", "chilled");
	public static Wine c = new Wine("Montana", "New Zealand", "2011", 13.4, "white", "Sauvignon Blanc", "dry", "chilled");
	public static Wine d = new Wine("Yellow Tail", "Australia", "2011", 14.8, "red", "Cabernet Sauvignon", "dry", "room temperature");
	public static Wine e = new Wine("Chateau Ste. Michelle", "Florida", "2012", 11.2, "white", "Riesling", "sweet", "chilled");
	
	/**  Default constructor
	 */
	public Wine() 
	{
	}
	
	/**  All-member constructor for fake database
	 */
	public Wine(String name, String region, String vintage, double alcoholContent,
	            String category, String varietal, String sweetOrDry,
		        String servingTemp) 
	{
		this.name           = new Name(name);
		this.region         = new Region(region);
		this.vintage        = new Vintage(vintage);
		this.alcoholContent = alcoholContent;
		this.category       = new Category(category);
		this.varietal       = new Varietal(varietal); //new Varietal(varietal);
		this.sweetOrDry     = new SweetOrDry(sweetOrDry);
		this.servingTemp    = new ServingTemp(servingTemp);
	}
	
	/**  Getter for Entry
	 */
	public static Wine get(Wine wine) 
	{
		return wine;
	}
	
	/**  Returns all wines with their corresponding data
	 */
	public static Wine[] getAll() 
	{	
		//return new Wine[] {a, b, c, d, e};
		// from the database
		List<Wine> wines = DatabaseManager.getInstance().getAllWines();
		return wines.toArray(new Wine[wines.size()]);
	}
	
	/*public Producer getProducer() 
	{
		// TODO actual implementation that works
		return new Producer();
	}*/

	public ServingTemp getServingTemp() {
		return new ServingTemp("CHILLED");
	}

	public SweetOrDry getSweetOrDry() {
		return new SweetOrDry("SWEET");
	}

	public Varietal getVarietal() {
		// would usually load from database
		return new Varietal("CAYETANA");
	}

	public Category getCategory() {
		return new Category("RED");
	}

	public void save() {
        DatabaseManager.getInstance().updateWine(this);
	}
}
