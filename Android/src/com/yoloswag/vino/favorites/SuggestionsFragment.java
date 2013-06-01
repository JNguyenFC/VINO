package com.yoloswag.vino.favorites;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.yoloswag.vino.R;
import com.yoloswag.vino.model.Wine;

public class SuggestionsFragment extends Fragment
{
	
	 //public boolean OPEN = false;
	 

	 public static final Wine[][] names = new Wine [FavoritesAdapter.favoriteSize][4];
			                           /* new Wine[][] { {Wine.a, Wine.b, Wine.c, Wine.d},
		 											     {Wine.d, Wine.c, Wine.b, Wine.a},
	 												     {Wine.c, Wine.d, Wine.a, Wine.b} };
	 /*public static final Wine[] names2 = new Wine[] {Wine.d, Wine.c, Wine.b, Wine.a};
	 public static final Wine[] names3 = new Wine[] {Wine.c, Wine.d, Wine.a, Wine.b};*/
	 
	 public static final Integer[] images = new Integer[] {R.drawable.wine_vector, R.drawable.wine_vector,
		                                                   R.drawable.wine_vector,
		                                                   R.drawable.wine_vector};
	 
	 //public static Wine[] names = null;
	 
	 ListView listView;
	 List<RowItem> rowItems;
	
	 @Override
	    public View onCreateView(LayoutInflater inflater, ViewGroup container,
	            Bundle savedInstanceState) {
		
		 for (int i = 0; i < FavoritesAdapter.favoriteSize; ++i)
		 {
			 names[i] = suggestions(FavoritesAdapter.favoriteWines[i]);
		 }
		
	        
		 rowItems = new ArrayList<RowItem>();

		 
		 for (int i = 0; i < 4; ++i)
		 {
			 RowItem item = new RowItem();
			 rowItems.add(item);
		 }
		 
		 View rootView = inflater.inflate(R.layout.suggestions_list, container, false);
		 listView = (ListView)rootView.findViewById(R.id.list);
		 listView.setAdapter(new SuggestionsAdapter(this, R.layout.suggestions, rowItems));
		 
System.out.println("AFTER SETADAPTER");
	     return rootView;
	    }
	 
		List<Wine> suggestionsList = new ArrayList<Wine>();
		
		Wine[] wines = Wine.getAll();
		
		public Wine[] suggestions(Wine clickedFavorite)
		{
			int n = wines.length;
			int counter = 0;

					for (int j = 0; j < n; ++j)
					{
						if (clickedFavorite != wines[j] && wines[j].rating == 0)
						{
							if ((clickedFavorite.category.category.compareToIgnoreCase(wines[j].category.category) == 0)
								&& (clickedFavorite.sweetOrDry.taste.compareToIgnoreCase(wines[j].sweetOrDry.taste) == 0)
							    && (clickedFavorite.varietal.varietal_name.compareToIgnoreCase(wines[j].varietal.varietal_name) != 0))
							{
							             suggestionsList.add(wines[j]);
							             ++counter;
							}
							
							             
							else if((clickedFavorite.category.category.compareToIgnoreCase(wines[j].category.category) == 0)
							       && (clickedFavorite.sweetOrDry.taste.compareToIgnoreCase(wines[j].sweetOrDry.taste) == 0)
							       && (counter < 4))
							{
								         suggestionsList.add(wines[j]);
								         ++counter;
							}
							    
						}
					}
				
			Wine[] tempArray = suggestionsList.toArray(new Wine[suggestionsList.size()]);
			
			Wine[] suggestedWines = new Wine[4];
			
			for(int i = 0; i < 4; ++i)
			{
				suggestedWines[i] = tempArray[(int)(Math.random()*suggestionsList.size())];
			}
			
			return suggestedWines;
		}
	 
}
