package com.yoloswag.vino;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class ViewLogEntryFragment extends Fragment {
	Entry[] entries;
	
	public ViewLogEntryFragment()
	{	
	}
	@Override
    public void onCreate (Bundle savedInstanceState) 
	 {
	   super.onCreate(savedInstanceState);
	   entries = Entry.getAll();
	   
     }
	
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_view_entry, container, false);
        //ListView dummyTextView = (ListView) rootView.findViewById(R.id.section_label);
        
        //creates seperate view if there no entries
        //rootView1 is the view that would be displayed incase if there are no entries
        if(entries.length == 0)
        {
        	 View rootView1 = inflater.inflate(R.layout.fragment_view_no_entry, container, false);
        	 return rootView1;
        }
        
        
        return rootView;
    }
}
