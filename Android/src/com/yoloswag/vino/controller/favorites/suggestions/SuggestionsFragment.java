/**
 * Filename:    SuggestionsFragment.java
 * Team:		VINO
 * Description: Displays the ListView that holds the list of wine
 *              recommendations. Sets the adapter for this ListView.
 * Date:        8 Jun 2013
 **/

package com.yoloswag.vino.controller.favorites.suggestions;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.yoloswag.vino.R;
import com.yoloswag.vino.model.wine.Wine;

/** Inflates the View which holds the ListView of wine recommendations and
 *  also sets the adapter for it.
 */
public class SuggestionsFragment extends Fragment
{	 
	 public static final Integer[] images = new Integer[] {R.drawable.wine_vector, 
		 												   R.drawable.wine_vector,
		                                                   R.drawable.wine_vector,
		                                                   R.drawable.wine_vector};

	 ListView listView;
	 List<Wine> rowItems;

	 /** Creates and returns the ListView of wines recommendations
	  */
	 @Override
	 public View onCreateView(LayoutInflater inflater, ViewGroup container,
	            Bundle savedInstanceState) 
	 {
		 // Recommendations drawer
		 rowItems = new ArrayList<Wine>();

		 for (int i = 0; i < 4; ++i)
		 {
			 Wine item = new Wine("", "", "", "", "", "");
			 rowItems.add(item);
		 }
		 
		 View rootView = inflater.inflate(R.layout.suggestions_list, container, false);
		 listView = (ListView)rootView.findViewById(R.id.list);
		 listView.setAdapter(new SuggestionsAdapter(this, R.layout.suggestions, rowItems));

	     return rootView;
	 }
}