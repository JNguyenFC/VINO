package com.yoloswag.vino.favorites;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.yoloswag.vino.R;
import com.yoloswag.vino.model.Wine;

public class SuggestionsAdapter extends ArrayAdapter<RowItem> 
{
    private Wine[] names;
	Context context;
	
	public SuggestionsAdapter(SuggestionsFragment suggestionsFragment, int resourceId, List<RowItem> list) 
	{
		super(suggestionsFragment.getActivity(), resourceId, list);
		this.context = suggestionsFragment.getActivity();
//System.out.println("CREATING SETADAPTER");
	}

	private class ViewHolder
	{
		ImageView imageView;
		TextView txtName;
		//TextView txtVarietal;
		//TextView textVintage;
	}
	
	public View getView(int position, View convertView, ViewGroup parent)
	{
		//ViewHolder holder = null;
		//RowItem rowItem = getItem(position);
		
		names = suggestions(FavoritesAdapter.favoriteWines[FavoritesAdapter.sugPos]);
		
		LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
		
		if(convertView == null)
		{
			convertView = mInflater.inflate(R.layout.suggestions, null);

			// Since only 3 lists of suggestions in fake suggestions [][]
			//int j = FavoritesAdapter.sugPos;
			
			ViewHolder holder = new ViewHolder();
			holder.txtName = (TextView) convertView.findViewById(R.id.name1);
			holder.imageView = (ImageView) convertView.findViewById(R.id.icon1);
			holder.imageView.setImageResource(SuggestionsFragment.images[0]);
			holder.txtName.setText(names[0].name.producer +
					" " + names[0].varietal.varietal_name);
			
			ViewHolder holder2 = new ViewHolder();
			holder2.txtName = (TextView) convertView.findViewById(R.id.name2);
			holder2.imageView = (ImageView) convertView.findViewById(R.id.icon2);
			holder2.imageView.setImageResource(SuggestionsFragment.images[1]);
			holder2.txtName.setText(names[1].name.producer +
					" " + names[1].varietal.varietal_name);
			
			ViewHolder holder3 = new ViewHolder();
			holder3.txtName = (TextView) convertView.findViewById(R.id.name3);
			holder3.imageView = (ImageView) convertView.findViewById(R.id.icon3);
			holder3.imageView.setImageResource(SuggestionsFragment.images[2]);
			holder3.txtName.setText(names[2].name.producer +
					" " + names[2].varietal.varietal_name);
			
			ViewHolder holder4 = new ViewHolder();
			holder4.txtName = (TextView) convertView.findViewById(R.id.name4);
			holder4.imageView = (ImageView) convertView.findViewById(R.id.icon4);
			holder4.imageView.setImageResource(SuggestionsFragment.images[3]);
			holder4.txtName.setText(names[3].name.producer +
					" " + names[3].varietal.varietal_name);
			
			convertView.setTag(holder);
		}
		/*else
			holder = (ViewHolder) convertView.getTag();
		holder.txtName.setText(rowItem.getName());
		holder.imageView.setImageResource(rowItem.getImageID());*/
		
		return convertView;
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
			suggestedWines[i] = tempArray[(int)Math.random()];
		}
		
		return suggestedWines;
	}
}
