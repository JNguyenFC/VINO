/**
 * Filename:    FavoritesAdapter.java
 * Team:		VINO
 * Description: 
 * Date:        8 Jun 2013
 **/

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
import com.yoloswag.vino.model.Wine;

/**
 * Adapter to bridge FavoritesFragment and its underlying data to generate
 * list of favorite wines and recommendations. 
 */
public class FavoritesAdapter extends BaseExpandableListAdapter 
{
	private Activity context;

	public static Wine[] favoriteWines = sortRatings(Wine.getAll());
	public static int sugPos;
	public static int favoriteSize = favoriteWines.length; 

	// Constructs a FavoritesAdapter for the specified FavoritesFragment
	public FavoritesAdapter(FavoritesFragment favoritesFragment)
	{
		this.context = favoritesFragment.getActivity();
	}

	/** Gets the data associated with the given child within the given group
	 */
	@Override
	public Object getChild(int groupPosition, int childPosition) 
	{
		return null;
	}

	/** Gets the ID for the given child within the given group 
	 */
	@Override
	public long getChildId(int groupPosition, int childPosition) 
	{
		return 0;
	}

	/** Gets a View that displays the recommendations for each favorite wine
	 */
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
	}

	/** Gets the number of recommendations drawers for each favorite wine
	 */
	@Override
	public int getChildrenCount(int groupPosition) 
	{
		// So that last favorite wine's suggestions are visible
		if (groupPosition == favoriteWines.length - 1)
			return 2;
		else
			return 1;
	}

	/** Gets the group position of the specified favorite wine
	 */
	@Override
	public Object getGroup(int groupPosition) 
	{
		return groupPosition;
	}

	/** Gets the number of favorite wines to be displayed
	 */
	@Override
	public int getGroupCount() 
	{
		return favoriteWines.length;
	}

	/** Gets the ID for the favorite wine at the given position
	 */
	@Override
	public long getGroupId(int groupPosition) 
	{
		return groupPosition;
	}

	/** Gets the View that displays the list of favorite wines
	 */
    @Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) 
	{
		LayoutInflater li = LayoutInflater.from(context);
		View v = li.inflate(R.layout.fragment_favorites_cell, null);

		TextView textview = (TextView) v.findViewById(R.id.favorite_wine);
		//Typeface typeface =Typeface.createFromAsset(getAssets(),"fonts/Roboto-Light.ttf");
		//textview.setTypeface(typeface);
		textview.setTextSize(18);
		textview.setText(favoriteWines[groupPosition].vintage.year + 
				" " + favoriteWines[groupPosition].name.producer + " " + 
				favoriteWines[groupPosition].varietal.varietal_name +
				", " + favoriteWines[groupPosition].region.region);

		// Customize RatingBar
		RatingBar bar = (RatingBar) v.findViewById(R.id.wineRatingBar);
		bar.setRating((float)favoriteWines[groupPosition].rating);
		bar.setIsIndicator(true);
		bar.setPadding(0, 20, 0, 20);

		return v;
	} 

    /** Indicates whether the child and group IDs are stable across changes to
     * the underlying data.
     */
	@Override
	public boolean hasStableIds() 
	{
		return false;
	}

	//TODO: should be false?
	/** Specifies whether the recommendations drawer is selectable
	 */
	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) 
	{
		return true;
	}

	/** (Bubble) Sorts list of favorite wines by rating (highest to lowest)
	 */
	static Wine[] sortRatings(Wine[] WineList)
	{
		Wine temp = null;
		List<Wine> ratedEntries = getRatedWines(WineList);
		int n = ratedEntries.size();

		int counter = 0;
		do
		{
			counter = 0;
			for (int i = 0; i < n-1; ++i)
			{
				if (ratedEntries.get(i).rating < ratedEntries.get(i+1).rating)
				{
					temp = ratedEntries.get(i);
					ratedEntries.set(i, ratedEntries.get(i+1));
					ratedEntries.set(i+1, temp);

					++counter;
				}
			}

		}while (counter > 0);

		Wine[] sortedWines = ratedEntries.toArray(new Wine[ratedEntries.size()]);

		return sortedWines;
	}

	/** Gets list of rated wines from all wines in database
	 */
	private static List<Wine> getRatedWines (Wine[] allWines)
	{
		int n = allWines.length;
		List<Wine> ratedWines = new ArrayList<Wine>();

		for (int i = 0; i < n; ++i)
			if (allWines[i].rating != 0)
				ratedWines.add(allWines[i]);

		return ratedWines;
	} 
}