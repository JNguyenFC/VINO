package com.yoloswag.vino.favorites;

import java.util.ArrayList;
import java.util.List;

import com.yoloswag.vino.model.Wine;

public class ReturnSuggestions {
	
	List<Wine> suggestionsList = new ArrayList<Wine>();
	
	Wine[] wines = Wine.getAll();
	
	public Wine[] suggestions(Wine clickedFavorite)
	{
		int n = wines.length;
		int counter = 0;

				for (int j = 0; j < n; ++j)
				{
					if (clickedFavorite != wines[j] && wines[j].rating == 0)
					{
						if ((clickedFavorite.category.category.compareToIgnoreCase(wines[j].category.category) != 0)
							&& (clickedFavorite.sweetOrDry.taste.compareToIgnoreCase(wines[j].sweetOrDry.taste) != 0)
						    && (clickedFavorite.varietal.varietal_name.compareToIgnoreCase(wines[j].varietal.varietal_name) != 0))
						{
						             suggestionsList.add(wines[j]);
						             ++counter;
						}
						
						             
						else if((clickedFavorite.category.category.compareToIgnoreCase(wines[j].category.category) != 0)
						       && (clickedFavorite.sweetOrDry.taste.compareToIgnoreCase(wines[j].sweetOrDry.taste) != 0)
						       && (counter < 4))
						{
							         suggestionsList.add(wines[j]);
							         ++counter;
						}
						    
					}
				}
			
		Wine[] suggestedWines = suggestionsList.toArray(new Wine[suggestionsList.size()]);
		return suggestedWines;
	}

}
