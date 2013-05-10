package com.yoloswag.vino;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

/**
 * Custom Adapter for favorites wine list and recommendations. 
 */
public class FavoritesAdapter extends BaseExpandableListAdapter 
{

	private Context context;
	private FavoritesFragment favoritesFragment;
	
	Entry favoriteWines[] = Entry.getAll();
	
	private Entry[] sortRatings(Entry[] ratedEntries)
	{
		int n = ratedEntries.length;
		int temp = 0;
		
		do
		{
			int counter = 0;
			
			for (int i = 0; i < n; ++i)
			{
				if (ratedEntries[i].rating < ratedEntries[i+1].rating)
				{
					temp = ratedEntries[i].rating;
					ratedEntries[i].rating = ratedEntries[i+1].rating;
					ratedEntries[i+1].rating = temp;
			    
					counter = i;
				}	
			}
		
			n = counter;
		
		} while (n > 0);
		
		return ratedEntries;
	}
	
	/*
	//Array of favorite wines list to display 
	String favoriteWines[]  = { "2008 YellowTail Muscato",  
							    "2006 Montes Classic Cabernet Sauvignon", 
							    "2011 Dr. Loosen Dr. L Riesling",
							    "2010 Sutter Home White Zinfandel" };
	*/
	
	//Temporary array of wine suggestions
	String recommendationWines[][] = { {"Suggestion 1", "Suggestion 2", "Suggestion 3"},
									   {"Suggestion 1", "Suggestion 2", "Suggestion 3"},
									   {"Suggestion 1", "Suggestion 2", "Suggestion 3"},
									   {"Suggestion 1", "Suggestion 2", "Suggestion 3"},
									   {"Suggestion 1", "Suggestion 2", "Suggestion 3"} };
	
	
	public FavoritesAdapter(FavoritesFragment favoritesFragment)
	{
		this.favoritesFragment = favoritesFragment;
		this.context = favoritesFragment.getActivity();
	}
	

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) 
	{
		// TODO Auto-generated method stub
		TextView textview = new TextView(context);
		textview.setText(recommendationWines[groupPosition][childPosition]);
		textview.setPadding(70, 0, 0, 0);
		return textview;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		// TODO Auto-generated method stub
		return recommendationWines[groupPosition].length;
	}

	@Override
	public Object getGroup(int groupPosition) {
		// TODO Auto-generated method stub
		return groupPosition;
	}

	@Override
	public int getGroupCount() {
		// TODO Auto-generated method stub
		return favoriteWines.length;
	}

	@Override
	public long getGroupId(int groupPosition) {
		// TODO Auto-generated method stub
		return groupPosition;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			                 View convertView, ViewGroup parent) 
	{
		// TODO Auto-generated method stub
		TextView textview = new TextView(context);
		textview.setText(favoriteWines[groupPosition].wine.vintage + " " + 
						 favoriteWines[groupPosition].wine.name + " " + 
						 favoriteWines[groupPosition].wine.varietal);
		textview.setPadding(50, 20, 20, 20);
		return textview;
	}

	@Override
	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return true;
	}

}
