package com.yoloswag.vino;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.TextView;

public class FavoritesFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    public static final String ARG_SECTION_NUMBER = "section_number";
    ExpandableListView exv;
    
    public FavoritesFragment() {
    }
    
    
    
    /*public void onCreate(Bundle savedInstanceState)
    {
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.favorites_fragment_lay);
    	ExpandableListView exv = (ExpandableListView)findViewById(R.id.expandableListView1);
    	exv.setAdapter(new ExpandListAdapter(this));
    }*/
    
 
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.favorites_fragment_lay, container, false);
    	exv = (ExpandableListView)rootView.findViewById(R.id.expandableListView1);
		exv.setAdapter(new ExpandListAdapter(this));
        return rootView;
    }
}