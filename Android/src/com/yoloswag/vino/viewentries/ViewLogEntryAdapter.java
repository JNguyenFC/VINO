package com.yoloswag.vino;

import android.content.Context;
import android.database.DataSetObserver;
import android.text.Layout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

public class ViewLogEntryAdapter implements ListAdapter {
	private Context context;
	private Entry[] entries;
	
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
		//t3 is going to hold the image and the comments
		LinearLayout container = new LinearLayout(context);
		ImageView wine = new ImageView(context);
		//LinearLayout containerText = new LinearLayout(context);
		TextView info = new TextView(context);
		View.OnClickListener handler = new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				LinearLayout vG = (LinearLayout)v;
				View elem = vG.findViewById(400);
				//if(elem.VISIBLE == LinearLayout.VISIBLE)
					elem.setVisibility(LinearLayout.VISIBLE);
				//if(elem.VISIBLE == LinearLayout.INVISIBLE)
					//elem.setVisibility(LinearLayout.INVISIBLE);
				
			}
		}; 
		wine.setImageResource(R.drawable.classicgary);
		info.setText("test 124");
		info.setId(400);
		//containerText.addView(info);
		info.setVisibility(LinearLayout.INVISIBLE);
		container.addView(wine);
     	container.addView(info);
     	container.setOnClickListener(handler);
		//t3.addView(t1);
		return container;
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
