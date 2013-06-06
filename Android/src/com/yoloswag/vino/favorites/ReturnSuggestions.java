package com.yoloswag.vino.favorites;

import java.util.ArrayList;
import java.util.List;

import com.yoloswag.vino.model.Wine;

public class ReturnSuggestions {
	//TODO: probably move this out of the fragment lol?
	/** Builds list of recommendations for the favorite wine that the user selects
	 */
	public Wine[] suggestions(Wine clickedFavorite)
	{
		List<Wine> suggestionsList = new ArrayList<Wine>();
		Wine[] wines = Wine.getAll();

		// Ensures that there are at least 4 wines in the list of recommendations
		int counter = 0;

		// Loops through all of the wines in the database, only recommending
		// wines that the user has not yet tasted
		for (int j = 0; j < wines.length; ++j)
		{
			if (clickedFavorite != wines[j] && wines[j].rating == 0)
			{
				// Gives priority recommendations based on wine category, taste, and varietal; second priority
				// recommendations go to wines based on category and taste
				if ((clickedFavorite.category.category.compareToIgnoreCase(wines[j].category.category) == 0)
						&& (clickedFavorite.sweetOrDry.taste.compareToIgnoreCase(wines[j].sweetOrDry.taste) == 0)
						&& (clickedFavorite.varietal.varietal_name.compareToIgnoreCase(wines[j].varietal.varietal_name) == 0))
				{						             
					suggestionsList.add(wines[j]);
					++counter;
				}

				else if ((clickedFavorite.category.category.compareToIgnoreCase(wines[j].category.category) == 0)
						&& (clickedFavorite.sweetOrDry.taste.compareToIgnoreCase(wines[j].sweetOrDry.taste) == 0))
				{         
					suggestionsList.add(wines[j]);
					++counter;
				}

				// If 4 wine recommendations have yet to be curated and more than half of the wine database
				// has already been traversed, start adding wines to the list of recommendations based on
				// taste, then category, then varietal, then region; if none of those match, then add any
				else if (counter < 4 && j > (wines.length/2))
				{		
					if ((clickedFavorite.sweetOrDry.taste.compareToIgnoreCase(wines[j].sweetOrDry.taste) == 0))
					{
						suggestionsList.add(wines[j]);
						++counter;
					}
					
					else if ((clickedFavorite.category.category.compareToIgnoreCase(wines[j].category.category) == 0))
					{
						suggestionsList.add(wines[j]);
						++counter;
					}
					
					else if ((clickedFavorite.varietal.varietal_name.compareToIgnoreCase(wines[j].varietal.varietal_name) == 0))
					{
						suggestionsList.add(wines[j]);
						++counter;
					}

					else if ((clickedFavorite.region.region.compareToIgnoreCase(wines[j].region.region) == 0))
					{
						suggestionsList.add(wines[j]);
						++counter;
					}

					else
					{
						suggestionsList.add(wines[j]);
						++counter;
					}
				}
			}
		}

		Wine[] suggestedWines = new Wine[4];

		// Adds 4 recommendations randomly chosen from the list of recommendations
		for (int i = 0; i < 4; ++i)
		{
			int pos = (int)(Math.random()*suggestionsList.size());
			if (suggestionsList.size() > pos)
				suggestedWines[i] = suggestionsList.remove(pos);			
		}
		return suggestedWines;
	}
}
