package com.yoloswag.vino.model;

public class Vintage {

		public String year;
		
		public Vintage(String vintage)
		{
			this.year = vintage;
		}
		
		public static Vintage[]getAll()
		{
			return new Vintage[]{new Vintage("1990"),
								new Vintage("1975"),
								new Vintage("2011"),
								new Vintage("2001"),
								new Vintage("1995"),
								new Vintage("1977"),
								new Vintage("1989"),
								};
		}

}
