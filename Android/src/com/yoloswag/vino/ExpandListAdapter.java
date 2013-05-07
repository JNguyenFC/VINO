package com.yoloswag.vino;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

/**
 * Custom Adapter for favorites wine list and recommendations. 
 * NOTE: still have to figure out how to connect to FavoritesFragment.java
 */
public class ExpandListAdapter extends BaseExpandableListAdapter {

	private Context context;
	//Array of favorite wines list to display 
	String favoriteWines[]  = {"2008 YellowTail Muscato",  
							"2006 Montes Classic Cabernet Sauvignon", 
							"2011 Dr. Loosen Dr. L Riesling",
							"2010 Sutter Home White Zinfandel"};
	
	//Temporary array of wine suggestions
	String recommendationWines[][] = {{"Suggestion 1", "Suggestion 2", "Suggestion 3"},
									{"Suggestion 1", "Suggestion 2", "Suggestion 3"},
									{"Suggestion 1", "Suggestion 2", "Suggestion 3"},
									{"Suggestion 1", "Suggestion 2", "Suggestion 3"}};
	
	
	public ExpandListAdapter(Context context)
	{
		this.context = context;
	}
	
	/*public ExpandListAdapter(FavoritesFragment favoritesFragment) {
		this.context = context;
	}*/

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
	public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView,
			ViewGroup parent) {
		// TODO Auto-generated method stub
		TextView textview = new TextView(context);
		textview.setText(recommendationWines[groupPosition][childPosition]);
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
	public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		TextView textview = new TextView(context);
		textview.setText(favoriteWines[groupPosition]);
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
