package com.yoloswag.vino.favorites;


import com.yoloswag.vino.R;
import com.yoloswag.vino.R.id;
import com.yoloswag.vino.R.layout;
import com.yoloswag.vino.model.Entry;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.TextView;

public class FavoritesFragment extends Fragment 
{
	Entry[] entries = Entry.getAll();
    ExpandableListView exv;
    
    public FavoritesFragment() 
    {
    }
    
    
 
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.favorites_fragment_lay, container, false);
    	exv = (ExpandableListView)rootView.findViewById(R.id.expandableListView1);
		exv.setAdapter(new FavoritesAdapter(this));
        return rootView;
    }
}