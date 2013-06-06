/**
 * Filename:    Name.java
 * Team:		VINO
 * Description: 
 * Date:        8 Jun 2013
 **/

package com.yoloswag.vino.model;

import java.io.Serializable;

public class Name implements Serializable 
{
	private static final long serialVersionUID = 1L;
	public String producer;

	// Initializes the String producer in Name with the parameter
	public Name(String producer)
	{
		this.producer = producer;
	}
}
