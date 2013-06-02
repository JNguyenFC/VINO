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

	public static Wine[] favoriteWines = sortRatings(Wine.getAll());
	public static int favoriteSize = favoriteWines.length; 



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
		System.out.println("asdasd" + groupPosition);
			sugPos = groupPosition;
			
			LinearLayout linearLayout = new LinearLayout(context);
			linearLayout.setId(345+groupPosition);

			FragmentManager fm = ((FragmentActivity)context).getSupportFragmentManager();
			FragmentTransaction ft = fm.beginTransaction();
			ft.replace(345+groupPosition, new SuggestionsFragment());
			ft.commit();
			
			return linearLayout;
	}

	@Override
	public int getChildrenCount(int groupPosition) 
	{
//		// So that last favorite wine's suggestions are visible
//		if (groupPosition == favoriteWines.length - 1)
//			return 2;
//		else
			return 1;
	}

	@Override
	public Object getGroup(int groupPosition) 
	{
		return groupPosition;
	}

	@Override
	public int getGroupCount() 
	{
		return favoriteWines.length;
	}

	@Override
	public long getGroupId(int groupPosition) 
	{
		return groupPosition;
	}
	
    @Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) 
	{
		// Inflating a View takes the layout XML, creates the View specified
		//   within, and then adds the View to another ViewGroup --
		//   this displays the RatingBar indicator for each favorite Wine
		
		LayoutInflater li = LayoutInflater.from(context);
		View v = li.inflate(R.layout.rating_cell_layout, null);
		
		TextView textview = (TextView) v.findViewById(R.id.favorite_wine);
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

	@Override
	public boolean hasStableIds() 
	{
		return false;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) 
	{
		return true;
	}
	
	static Wine[] sortRatings(Wine[] WineList)
	{
		System.out.println(WineList.length);
		List<Wine> ratedEntries = getRatedWines(WineList);
	
		int n = ratedEntries.size();
		Wine temp = null;
		
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

		System.out.println(sortedWines.length);
		return sortedWines;
	}
	
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