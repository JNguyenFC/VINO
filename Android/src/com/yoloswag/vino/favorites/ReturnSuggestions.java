package com.yoloswag.vino.favorites;

import com.yoloswag.vino.model.Wine;

public class ReturnSuggestions {
	
	Wine[] suggestionsList = null;
	
	Wine[] wines = Wine.getAll();
	
	public Wine[] suggestions()
	{
		int n = wines.length;
		
			for (int i = 0; i < n; ++i)
			{
				for (int j = 0; j < n; ++j)
				{
					if (i != j)
					{
						if (wines[i].category.category.compareToIgnoreCase(wines[j].category.category) != 0);
					}
				}
			}
		return suggestionsList;
	}

}
