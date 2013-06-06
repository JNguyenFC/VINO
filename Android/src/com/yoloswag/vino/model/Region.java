/**
 * Filename:    Region.java
 * Team:		VINO
 * Description: 
 * Date:        8 Jun 2013
 **/

package com.yoloswag.vino.model;

import java.io.Serializable;

public class Region implements Serializable 
{
	private static final long serialVersionUID = 1L;
	public String region;
	
	// Initializes the String region in Region with the parameter
	public Region(String region)
	{
		this.region = region;		
	}
}
