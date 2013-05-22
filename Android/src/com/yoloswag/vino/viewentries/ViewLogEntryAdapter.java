package com.yoloswag.vino.viewentries;

import com.yoloswag.vino.R;
import com.yoloswag.vino.R.drawable;
import com.yoloswag.vino.model.Entry;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ViewLogEntryAdapter extends BaseExpandableListAdapter 
{
	private Context context;
	private Entry[] entries;
	
	public ViewLogEntryAdapter(Context cont, Entry[] ent)
	{
		context = cont;
		entries = ent;
	}
	
	@Override
	public Object getChild(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getChildId(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getChildView(int group, int arg1, boolean arg2, View arg3,
			ViewGroup arg4) 
	{
		// TODO Auto-generated method stub
		//t3 is going to hold the image and the comments
		LinearLayout t3 = new LinearLayout(context);
		//going to be the comments of the user
		TextView t1 = new TextView(context);
		//image of wine
		ImageView t2 = new ImageView(context);
		t1.setText(entries[group].comment);
		t2.setImageResource(R.drawable.classicgary);
		t3.addView(t2);
		t3.addView(t1);
		return t3;
	}

	@Override
	public int getChildrenCount(int arg0) {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public Object getGroup(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getGroupCount() {
		// TODO Auto-generated method stub
		return entries.length;
	}

	@Override
	public long getGroupId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getGroupView(int group, boolean arg1, View arg2, ViewGroup parent) {
		// TODO Auto-generated method stub
		//t1 is going to appear first in the list
//		System.out.println("xdgfgdf");
//		System.out.println(entries[group] == null);
//		System.out.println(entries[group].wine == null);
//		System.out.println("x"+entries[group].wine.name.producer);
		TextView title = new TextView(context);
//		System.out.println(entries[group].wine.name.producer);
//		title.setText("Title: "+entries[group].wine.name.producer);
		title.setText("Title: "+entries[group].comment);
		return title;
	}

	@Override
	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isChildSelectable(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return false;
	}

}
