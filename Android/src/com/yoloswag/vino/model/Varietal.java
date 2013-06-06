/**
 * Filename:    Varietal.java
 * Team:		VINO
 * Description: 
 * Date:        8 Jun 2013
 **/

package com.yoloswag.vino.model;

import java.io.Serializable;

public class Varietal implements Serializable
{
	private static final long serialVersionUID = 1L;
	public String varietal_name;

	// Initializes the String varietal_name in Varietal with the parameter
	public Varietal(String varietal)
	{
		this.varietal_name = varietal;		
	}
}