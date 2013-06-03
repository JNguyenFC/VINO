package com.yoloswag.vino.favorites;


import java.util.ArrayList;
import java.util.List;

import com.yoloswag.vino.R;
import com.yoloswag.vino.R.id;
import com.yoloswag.vino.R.layout;
import com.yoloswag.vino.model.Entry;
import com.yoloswag.vino.model.Wine;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.TextView;

public class FavoritesFragment extends Fragment implements OnGroupExpandListener
{
	Entry[] entries = Entry.getAll();
    ExpandableListView exv;
    public static final Wine[][] suggestionNames = new Wine [FavoritesAdapter.favoriteSize][4];
    private FavoritesAdapter adapter;
    
    public FavoritesFragment() {
    }
    
    @Override
    public void onResume() {
    	super.onResume();
    	Wine[] wines = adapter.sortRatings(Wine.getAll());
    	adapter.favoriteWines = wines;
    	adapter.favoriteSize = wines.length;
    	
    	//Wine[][] suggs = adapter.
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
    	
		 for (int i = 0; i < FavoritesAdapter.favoriteSize; ++i)
		 {
			 suggestionNames[i] = suggestions(FavoritesAdapter.favoriteWines[i]);
		 }
    	
        View rootView = inflater.inflate(R.layout.fragment_favorites, container, false);
    	exv = (ExpandableListView)rootView.findViewById(R.id.expandableListView1);
    	exv.setOnGroupExpandListener((OnGroupExpandListener) this);
    	adapter = new FavoritesAdapter(this);
		exv.setAdapter(adapter);

        return rootView;
    }
    
    /*Instantiate suggestions fragment for the given wine
    public Fragment displaySuggestions(){
    	Fragment SuggestionsFragment = new SuggestionsFragment();
    	return SuggestionsFragment;
    }*/
    
    public void onGroupExpand(int groupPosition) {
	    int len = exv.getCount();

	    for (int i = 0; i < len; i++) {
	        if (i != groupPosition) {
	            exv.collapseGroup(i);
	        }
	    }
	}

    /** Builds list of suggestions for the favorite wine that the user selects
     */
	public Wine[] suggestions(Wine clickedFavorite)
	{
		List<Wine> suggestionsList = new ArrayList<Wine>();
		Wine[] wines = Wine.getAll();

		for (int j = 0; j < wines.length; ++j)
		{
			if (clickedFavorite != wines[j] && wines[j].rating == 0)
			{
				if ((clickedFavorite.category.category.compareToIgnoreCase(wines[j].category.category) == 0)
				   && (clickedFavorite.sweetOrDry.taste.compareToIgnoreCase(wines[j].sweetOrDry.taste) == 0)
				   && (clickedFavorite.varietal.varietal_name.compareToIgnoreCase(wines[j].varietal.varietal_name) == 0))
				{						             
					suggestionsList.add(wines[j]);
				}
						
				else            
				{
					if ((clickedFavorite.category.category.compareToIgnoreCase(wines[j].category.category) == 0)
					   && (clickedFavorite.sweetOrDry.taste.compareToIgnoreCase(wines[j].sweetOrDry.taste) == 0))
					{         
						suggestionsList.add(wines[j]);
					}					
						
					else if ((clickedFavorite.category.category.compareToIgnoreCase(wines[j].category.category) == 0))
					{
						suggestionsList.add(wines[j]);
					}

					else if ((clickedFavorite.sweetOrDry.taste.compareToIgnoreCase(wines[j].sweetOrDry.taste) == 0))
					{
						suggestionsList.add(wines[j]);
					}
					
					else if ((clickedFavorite.varietal.varietal_name.compareToIgnoreCase(wines[j].varietal.varietal_name) == 0))
					{
						suggestionsList.add(wines[j]);
					}
					
					else if ((clickedFavorite.region.region.compareToIgnoreCase(wines[j].region.region) == 0))
					{
						suggestionsList.add(wines[j]);
					}
	
				}
						    
			}
		}
		
		Wine[] suggestedWines = new Wine[4];

		for (int i = 0; i < 4; ++i)
		{
			int pos = (int)(Math.random()*suggestionsList.size());
			suggestedWines[i] = suggestionsList.remove(pos);			
		}

//		Wine[] arr = new Wine[4];
//		arr[0] = Wine.a;
//		arr[1] = Wine.b;
//		arr[2] = Wine.c;
//		arr[3] = Wine.d;
//		return arr;
		
		return suggestedWines;
	}
}