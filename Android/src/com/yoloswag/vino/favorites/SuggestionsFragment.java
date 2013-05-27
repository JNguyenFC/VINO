package com.yoloswag.vino.favorites;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ListView;

import com.yoloswag.vino.R;
import com.yoloswag.vino.favorites.*;
import com.yoloswag.vino.model.Wine;

public class SuggestionsFragment extends Fragment {

	 public static final Wine[] names = new Wine[] {Wine.a, Wine.b, Wine.c, Wine.d};
	 
	 public static final Integer[] images = new Integer[] {R.drawable.wineicon, R.drawable.wine,
		                                                   R.drawable.sauvignon_blanc,
		                                                   R.drawable.classicgary};
	 
	 ListView listView;
	 List<RowItem> rowItems;
	
	 @Override
	    public View onCreateView(LayoutInflater inflater, ViewGroup container,
	            Bundle savedInstanceState) {
		

	        
		 rowItems = new ArrayList<RowItem>();
		 for (int i = 0; i < 4; ++i)
		 {
			// RowItem item = new RowItem(images[i], names[i]);
			 RowItem item = new RowItem();
			 rowItems.add(item);
		 }
		 
		 View rootView = inflater.inflate(R.layout.suggestions_list, container, false);
		 listView = (ListView)rootView.findViewById(R.id.list);
		 listView.setAdapter(new SuggestionsAdapter(this, R.layout.suggestions, rowItems));
	        
	     return rootView;
	    }
	 
}
