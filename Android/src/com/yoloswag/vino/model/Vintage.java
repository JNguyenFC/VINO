/**
 * Filename:    Vintage.java
 * Team:		VINO
 * Description: 
 * Date:        8 Jun 2013
 **/

package com.yoloswag.vino.model;

import java.io.Serializable;

public class Vintage implements Serializable 
{
	private static final long serialVersionUID = 1L;
	public String year;

	// Initializes the String year in Vintage with the parameter
	public Vintage(String vintage)
	{
		this.year = vintage;
	}
}
