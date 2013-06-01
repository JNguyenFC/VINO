package com.yoloswag.vino.favorites;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.yoloswag.vino.R;
import com.yoloswag.vino.model.Entry;
import com.yoloswag.vino.model.Wine;

/**
 * Custom Adapter for favorites wine list and recommendations. 
 */
public class FavoritesAdapter extends BaseExpandableListAdapter 
{

	private Activity context;
	public static int sugPos;

	Wine[] favoriteWines = sortRatings(Wine.getAll());
	//Entry[] favoriteWines = sortRatings(Entry.getAll());

	/*// Temporary array of wine suggestions
	String recommendedWines[][] = { {"Suggestion 1", "Suggestion 2", "Suggestion 3"},
			{"Suggestion 1", "Suggestion 2", "Suggestion 3"},
			{"Suggestion 1", "Suggestion 2", "Suggestion 3"},
			{"Suggestion 1", "Suggestion 2", "Suggestion 3"},
			{"Suggestion 1", "Suggestion 2", "Suggestion 3"} }; */


	public FavoritesAdapter(FavoritesFragment favoritesFragment)
	{
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
			sugPos = groupPosition;
			
			LinearLayout linearLayout = new LinearLayout(context);
			linearLayout.setId(345+groupPosition);

			FragmentManager fm = ((FragmentActivity)context).getSupportFragmentManager();
			FragmentTransaction ft = fm.beginTransaction();
			ft.replace(345+groupPosition, new SuggestionsFragment());
			ft.commit();
			
			return linearLayout;
//		}
//		// TODO Auto-generated method stub
//		TextView textview = new TextView(context);
//		textview.setText(recommendedWines[groupPosition][childPosition]);
//		textview.setPadding(70, 0, 0, 0);
//		return textview;
	}

	@Override
	public int getChildrenCount(int groupPosition) 
	{
		// TODO Auto-generated method stub
//		return recommendedWines[groupPosition].length;
		//if (groupPosition == favoriteWines.length - 1)
		//	return 2;
		//else
			return 1;
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
		//return Wine.getAll().length;
	}

	@Override
	public long getGroupId(int groupPosition) 
	{
		// TODO Auto-generated method stub
		return groupPosition;
	}

	/* @Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) 
	{
		// Inflating a View takes the layout XML, creates the View specified
		//   within, and then adds the View to another ViewGroup --
		//   this displays the RatingBar indicator for each favorite Wine
		LayoutInflater li = LayoutInflater.from(context);
		View v = li.inflate(R.layout.rating_cell_layout, null);
		
		// Display favorite Wine vintage, producer, and varietal dynamically
		TextView textview = (TextView) v.findViewById(R.id.favorite_wine);
		textview.setText(favoriteWines[groupPosition].wine.vintage.year + 
				" " + favoriteWines[groupPosition].wine.name.producer + " " + 
				favoriteWines[groupPosition].wine.varietal.varietal_name +
				", " + favoriteWines[groupPosition].wine.region.region);
		
		// Customize RatingBar
		RatingBar bar = (RatingBar) v.findViewById(R.id.wineRatingBar);
		//RatingBar bar = (RatingBar) v.findViewById(R.id.wineRatingBar);
		bar.setRating((float)favoriteWines[groupPosition].rating);
		bar.setIsIndicator(true);
		bar.setPadding(0, 20, 0, 20);
		
		return v;
	} */
	
	
	// GETTING INFO FROM WINE CLASS INSTEAD OF ENTRY
    @Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) 
	{
		// Inflating a View takes the layout XML, creates the View specified
		//   within, and then adds the View to another ViewGroup --
		//   this displays the RatingBar indicator for each favorite Wine
		
		//favoriteWines = Wine.getAll();
System.out.println("groupPosition: " + groupPosition);
		
		LayoutInflater li = LayoutInflater.from(context);
		View v = li.inflate(R.layout.rating_cell_layout, null);
		
		//System.out.println("getting " + groupPosition + " " + favoriteWines.length);
		//groupPosition = 1;
		// Display favorite Wine vintage, producer, and varietal dynamically
		TextView textview = (TextView) v.findViewById(R.id.favorite_wine);
		textview.setText(favoriteWines[groupPosition].vintage.year + 
				" " + favoriteWines[groupPosition].name.producer + " " + 
				favoriteWines[groupPosition].varietal.varietal_name +
				", " + favoriteWines[groupPosition].region.region);
		
		// Customize RatingBar
		RatingBar bar = (RatingBar) v.findViewById(R.id.wineRatingBar);
		//RatingBar bar = (RatingBar) v.findViewById(R.id.wineRatingBar);
		bar.setRating((float)favoriteWines[groupPosition].rating);
		bar.setIsIndicator(true);
		bar.setPadding(0, 20, 0, 20);
		
		return v;
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
/*	private Entry[] sortRatings(Entry[] ratedEntries)
	{	
		int n = ratedEntries.length;
		Entry temp = null;
		
		int counter = 0;
		do
		{
			counter = 0;

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

		}while (counter > 0);
		
		return ratedEntries;
	} */
	
	
	 private Wine[] sortRatings(Wine[] WineList)
	{	
		List<Wine> ratedEntries = getRatedWines(WineList);

		
		int n = ratedEntries.size();
System.out.println("ratedEntries.size(): " + ratedEntries.size());
System.out.println("n: " + n);
		Wine temp = null;
		Wine current = null;
		Wine next = null;
		
		int counter = 0;
		do
		{
			counter = 0;

			for (int i = 0; i < n-1; ++i)
			{
System.out.println("i: " + i);
				current = ratedEntries.get(i);
				next = ratedEntries.get(i+1);
				if (current.rating < next.rating)
				{
					temp = current;
					current = next;
					next = temp;

					++counter;
				}
			}

		}while (counter > 0);
		
		Wine[] sortedWines = ratedEntries.toArray(new Wine[ratedEntries.size()]);
		
		return sortedWines;
	}
	
	private List<Wine> getRatedWines (Wine[] allWines)
	{
		int n = allWines.length;
System.out.println("allWines.length: " + allWines.length);
		List<Wine> ratedWines = new ArrayList<Wine>();
		
		for (int i = 0; i < n-1; ++i)
			if (allWines[i].rating != 0)
			{
System.out.println("IF STATEMENT ENTERED");
				ratedWines.add(allWines[i]);
			}
		
System.out.println("ratedWines size: " + ratedWines.size());
		
		return ratedWines;
	} 
	
	
}
