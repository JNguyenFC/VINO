/**
 * Filename:    FavoritesFragment.java
 * Team:		VINO
 * Description: 
 * Date:        8 Jun 2013
 **/

package com.yoloswag.vino.controller.favorites;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnGroupExpandListener;

import com.yoloswag.vino.R;
import com.yoloswag.vino.model.entry.Entry;
import com.yoloswag.vino.model.wine.WineSuggestions;
import com.yoloswag.vino.model.wine.Wine;

public class FavoritesFragment extends Fragment implements OnGroupExpandListener
{
	Entry[] entries = Entry.getAll();
	ExpandableListView exv;
	public static Wine[][] suggestionNames = new Wine [FavoritesAdapter.favoriteSize][4];
	private FavoritesAdapter adapter;
	public static WineSuggestions sList = new WineSuggestions();

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
		Wine[] wines = FavoritesAdapter.sortRatings(Wine.getAll());
		FavoritesAdapter.favoriteWines = wines;
		FavoritesAdapter.favoriteSize = wines.length;
		adapter.notifyDataSetChanged();

		suggestionNames = new Wine [FavoritesAdapter.favoriteSize][4];
		for (int i = 0; i < FavoritesAdapter.favoriteSize; ++i) 
			suggestionNames[i] = sList.suggestions(FavoritesAdapter.favoriteWines[i]);
	}

	/** Creates and returns the ExpandableListView of favorite wines
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
		   Bundle savedInstanceState) 
	{
		

		View rootView = inflater.inflate(R.layout.fragment_favorites, container, false);
		exv = (ExpandableListView)rootView.findViewById(R.id.expandableListView1);
		exv.setOnGroupExpandListener((OnGroupExpandListener) this);
		adapter = new FavoritesAdapter(this);
		exv.setAdapter(adapter);
		
		for (int i = 0; i < FavoritesAdapter.favoriteSize; ++i) 
			suggestionNames[i] = sList.suggestions(FavoritesAdapter.favoriteWines[i]);

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


}