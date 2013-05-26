package com.yoloswag.vino.viewentries;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import com.yoloswag.vino.R;
import com.yoloswag.vino.model.Entry;

public class ViewLogEntryAdapter implements ListAdapter {
	private Context context;
	private Entry[] entries = Entry.getAll();
	
	public ViewLogEntryAdapter(Context cont, Entry[] ent)
	{
		context = cont;
		entries = ent;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return entries.length;
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getItemViewType(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		LayoutInflater li = LayoutInflater.from(context);
		View v = li.inflate(R.layout.image_cell_layout, null);
		
		ImageView iv = (ImageView) v.findViewById(R.id.entry_image);

		// Sets photo to be displayed to fill the screen relative to any phone
		RelativeLayout layout = (RelativeLayout)v.findViewById(R.id.rootlayout);
		iv.setLayoutParams(new RelativeLayout.LayoutParams(arg2.getWidth(), arg2.getHeight()));
		
        // Dynamically change log entry photos depending on the photo for the entry
		// -- This is the hard-coded version
		switch (arg0)
		{
			case 0: iv.setImageResource(R.drawable.vino1);
				break;
			case 1: iv.setImageResource(R.drawable.vino3);
				break;
			case 2: iv.setImageResource(R.drawable.vino2);
				break;
			case 3: iv.setImageResource(R.drawable.vino4);
				break;
			default:
				break;
		}
		
		// Dynamically change white on black text captions on top of photos
		TextView textview_vintage = (TextView) v.findViewById(R.id.vintage);
		textview_vintage.setText(entries[arg0].wine.vintage.year);
		textview_vintage.setTextSize(34);
		
		TextView textview_producer = (TextView) v.findViewById(R.id.producer_name);
		textview_producer.setText(entries[arg0].wine.name.producer);
		textview_producer.setTextSize(34);
		
		TextView textview_varietal = (TextView) v.findViewById(R.id.varietal_name);
		textview_varietal.setText(entries[arg0].wine.varietal.varietal_name);
		textview_varietal.setTextSize(34);
		
		TextView textview_entry_comment = (TextView) v.findViewById(R.id.entry_comment);
		textview_entry_comment.setText(entries[arg0].comment);
		
		TextView textview_entry_details = (TextView) v.findViewById(R.id.entry_details);
		textview_entry_details.setText("¥ Maker: " + entries[arg0].wine.name.producer + "\n" +
                "¥ Varietal: " + entries[arg0].wine.varietal.varietal_name + "\n" +
				"¥ Vintage: " + entries[arg0].wine.vintage.year + "\n" +
                "¥ Region: " + entries[arg0].wine.region.region + "\n"+
                "¥ Category: " + entries[arg0].wine.category.category + "\n" +
                "¥ Rating: " + entries[arg0].rating
                );
		textview_entry_details.setLayoutParams(new RelativeLayout.LayoutParams(arg2.getWidth(), arg2.getHeight()));

        View.OnClickListener handler = new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				RelativeLayout vG = (RelativeLayout)v.getParent();
				View elem = vG.findViewById(R.id.entry_details);
				View elem2 = vG.findViewById(R.id.entry_comment);
				TextView textview_vintage = (TextView) vG.findViewById(R.id.vintage);
				TextView textview_producer = (TextView) vG.findViewById(R.id.producer_name);
				TextView textview_varietal = (TextView) vG.findViewById(R.id.varietal_name);
				if(v == vG.findViewById(R.id.entry_image))
				 {
					elem.setVisibility(RelativeLayout.VISIBLE);
					elem2.setVisibility(RelativeLayout.VISIBLE);
					textview_vintage.setVisibility(RelativeLayout.GONE);
					textview_producer.setVisibility(RelativeLayout.GONE);
					textview_varietal.setVisibility(RelativeLayout.GONE);
				 }
				if(v == elem || v == elem2)
				{
					elem.setVisibility(RelativeLayout.INVISIBLE);
					elem2.setVisibility(RelativeLayout.INVISIBLE);
					textview_vintage.setVisibility(RelativeLayout.VISIBLE);
					textview_producer.setVisibility(RelativeLayout.VISIBLE);
					textview_varietal.setVisibility(RelativeLayout.VISIBLE);
					
				}
			}
		}; 
		
		//when click on image make entry descirption appear
		iv.setOnClickListener(handler);
		textview_entry_details.setOnClickListener(handler);
		textview_entry_comment.setOnClickListener(handler);
		return v;
		
		
		
		/*
		//t3 is going to hold the image and the comments
		RelativeLayout container = new RelativeLayout(context);
		ImageView wine = new ImageView(context);
		//RelativeLayout containerText = new RelativeLayout(context);
		TextView info = new TextView(context);
		View.OnClickListener handler = new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				RelativeLayout vG = (RelativeLayout)v;
				View elem = vG.findViewById(400);
				//if(elem.VISIBLE == RelativeLayout.VISIBLE)
					elem.setVisibility(RelativeLayout.VISIBLE);
				//if(elem.VISIBLE == RelativeLayout.INVISIBLE)
					//elem.setVisibility(RelativeLayout.INVISIBLE);
			}
		}; 
		wine.setImageResource(R.drawable.classicgary);
		wine.setAdjustViewBounds(true);
		//wine.setScale
		info.setText(entries[arg0].title);
		info.setPadding(70, 20, 20, 20);
		info.setId(400);
		//containerText.addView(info);
		info.setVisibility(RelativeLayout.INVISIBLE);
		container.addView(wine);
     	container.addView(info);
     	container.setOnClickListener(handler);
		//t3.addView(t1);
		return container;
	*/
	}

	@Override
	public int getViewTypeCount() {
		// TODO Auto-generated method stub
		return entries.length;
	}

	@Override
	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void registerDataSetObserver(DataSetObserver arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void unregisterDataSetObserver(DataSetObserver arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean areAllItemsEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEnabled(int arg0) {
		// TODO Auto-generated method stub
		return false;
	}
	


}
