/**
 * Filename:    SweetOrDry.java
 * Team:		VINO
 * Description: 
 * Date:        8 Jun 2013
 **/

package com.yoloswag.vino.model;

import java.io.Serializable;

public class SweetOrDry implements Serializable 
{
	private static final long serialVersionUID = 1L;
	public String taste;

	// Initializes the String taste in SweetOrDry with the parameter
	public SweetOrDry(String taste)
	{
		this.taste = taste;		
	}
}