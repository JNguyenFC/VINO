package com.yoloswag.vino;

public class Quantity 
{
	protected int quantity;
	
	public Quantity(int quantity)
	{
		this.quantity = quantity;		
	}
	
	/**  Returns array of ratings of wines from our fake database of Entry
	 */
	public static Quantity[] getAll() 
	{
		return new Quantity[] { Entry.a.quantity,
							    Entry.b.quantity,
							    Entry.c.quantity,
							    Entry.d.quantity,
							    Entry.e.quantity };
	}
}
