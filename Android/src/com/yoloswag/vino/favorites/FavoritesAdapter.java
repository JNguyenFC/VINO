package com.yoloswag.vino.favorites;

import com.yoloswag.vino.model.Entry;

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
	public Object getChild(int groupPosition, int childPosition) 
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) 
	{
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
	public int getChildrenCount(int groupPosition) 
	{
		// TODO Auto-generated method stub
		return recommendationWines[groupPosition].length;
	}

	@Override
	public Object getGroup(int groupPosition) 
	{
		// TODO Auto-generated method stub
		return groupPosition;
	}

	@Override
	public int getGroupCount() 
	{
		// TODO Auto-generated method stub
		return favoriteWines.length;
	}

	@Override
	public long getGroupId(int groupPosition) 
	{
		// TODO Auto-generated method stub
		return groupPosition;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			                 View convertView, ViewGroup parent) 
	{
		Entry[] ratedEntries = sortRatings(favoriteWines);
		
		// TODO Auto-generated method stub
		TextView textview = new TextView(context);
		textview.setText(ratedEntries[groupPosition].wine.vintage + " " + 
						 ratedEntries[groupPosition].wine.name + " " + 
						 ratedEntries[groupPosition].wine.varietal);
		textview.setPadding(50, 20, 20, 20);
		return textview;
	}

	@Override
	public boolean hasStableIds() 
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) 
	{
		// TODO Auto-generated method stub
		return true;
	}
	
	/**  Naive sorting algorithm (bubble sort) for sorting wines by rating
	 */
	private Entry[] sortRatings(Entry[] ratedEntries)
	{	
		int n = ratedEntries.length;
		Entry temp = null;
		
		do
		{
			int counter = 0;
			
			for (int i = 0; i < n-1; ++i)
			{
				if (ratedEntries[i].rating < ratedEntries[i+1].rating)
				{
					temp = ratedEntries[i];
					ratedEntries[i] = ratedEntries[i+1];
					ratedEntries[i+1] = temp;
			    
					++counter;
				}
			}
		
			n = counter;
		
		} while (n > 0);
		
		return ratedEntries;
	}
	
	/**  Naive sorting algorithm (bubble sort) for sorting wines by quantity consumed
	 */
	private Entry[] sortQuantities(Entry[] quantifiedEntries)
	{	
		int n = quantifiedEntries.length;
		Entry temp = null;
		
		do
		{
			int counter = 0;
			
			for (int i = 0; i < n-1; ++i)
			{
				if (quantifiedEntries[i].rating < quantifiedEntries[i+1].rating)
				{
					temp = quantifiedEntries[i];
					quantifiedEntries[i] = quantifiedEntries[i+1];
					quantifiedEntries[i+1] = temp;
			    
					++counter;
				}
			}
		
			n = counter;
		
		} while (n > 0);
		
		return quantifiedEntries;
	}

}
