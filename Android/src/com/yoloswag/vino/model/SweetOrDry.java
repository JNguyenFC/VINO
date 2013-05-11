package com.yoloswag.vino.model;

public class SweetOrDry {
	protected String taste;
	
	public SweetOrDry(String taste){
		this.taste = taste;		
	}
	
	//returns a SweetOrDry array containing arbitrary data
	//so we can begin implementing our other methods
	public static SweetOrDry[] getAll() {
		return  new SweetOrDry[] { 
				                     new SweetOrDry("SWEET"), 
				                     new SweetOrDry("DRY")
		                       };
	}
}