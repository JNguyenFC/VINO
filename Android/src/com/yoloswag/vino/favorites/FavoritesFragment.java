/**
 * Filename:    FavoritesFragment.java
 * Team:		VINO
 * Description: 
 * Date:        8 Jun 2013
 **/

package com.yoloswag.vino.favorites;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnGroupExpandListener;

import com.yoloswag.vino.R;
import com.yoloswag.vino.model.Entry;
import com.yoloswag.vino.model.Wine;

public class FavoritesFragment extends Fragment implements OnGroupExpandListener
{
	Entry[] entries = Entry.getAll();
	ExpandableListView exv;
	public static Wine[][] suggestionNames = new Wine [FavoritesAdapter.favoriteSize][4];
	private FavoritesAdapter adapter;

	// Default constructor
	public FavoritesFragment() 
	{
	}

	//TODO: change access to static using FavoritesAdapter.sortRatings()?
	/** Initializes newly added entries and their recommendations so that
	 *  they are viewable in the Favorites tab
	 */
	@Override
	public void onResume() 
	{
		super.onResume();
		Wine[] wines = adapter.sortRatings(Wine.getAll());
		adapter.favoriteWines = wines;
		adapter.favoriteSize = wines.length;
		adapter.notifyDataSetChanged();

		suggestionNames = new Wine [FavoritesAdapter.favoriteSize][4];
		for (int i = 0; i < FavoritesAdapter.favoriteSize; ++i) 
			suggestionNames[i] = suggestions(FavoritesAdapter.favoriteWines[i]);
	}

	/** Creates and returns the ExpandableListView of favorite wines
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
		   Bundle savedInstanceState) 
	{
		for (int i = 0; i < FavoritesAdapter.favoriteSize; ++i) 
			suggestionNames[i] = suggestions(FavoritesAdapter.favoriteWines[i]);

		View rootView = inflater.inflate(R.layout.fragment_favorites, container, false);
		exv = (ExpandableListView)rootView.findViewById(R.id.expandableListView1);
		exv.setOnGroupExpandListener((OnGroupExpandListener) this);
		adapter = new FavoritesAdapter(this);
		exv.setAdapter(adapter);

		return rootView;
	}

	/** Callback method for when a favorite wine in the ExpandableListView
	 *  has been expanded
	 */
	public void onGroupExpand(int groupPosition) 
	{
		int len = exv.getCount();

		for (int i = 0; i < len; i++) 
		{
			if (i != groupPosition) 
			{
				exv.collapseGroup(i);
			}
		}
	}

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