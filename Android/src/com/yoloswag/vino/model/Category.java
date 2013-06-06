/**
 * Filename:    SuggestionsAdapter.java
 * Team:		VINO
 * Description: 
 * Date:        8 Jun 2013
 **/

package com.yoloswag.vino.model;

import java.io.Serializable;

public class Category implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	public String category;

	// Initializes the String category in Category with the parameter
	public Category(String category)
	{
		this.category = category;		
	}
}