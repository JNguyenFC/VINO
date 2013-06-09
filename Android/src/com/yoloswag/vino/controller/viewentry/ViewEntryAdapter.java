/**
 * Filename:    DiaryAdapter.java
 * Team:		VINO
 * Description: 
 * Date:        8 Jun 2013
 **/

package com.yoloswag.vino.controller.viewentry;

import java.util.ArrayList;

import com.yoloswag.vino.model.entry.Entry;
import com.yoloswag.vino.view.viewentry.ViewEntryItemView;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class ViewEntryAdapter extends ArrayAdapter<Entry> {
	
	private ViewEntryFragment diary;

	// Constructor for DiaryAdapter
	public ViewEntryAdapter(Context context, int resource, ArrayList<Entry> lst, ViewEntryFragment diary) {
		super(context, resource, lst);
		this.diary = diary;
	}
	
	/** Fetches and inflates data for entry
	 */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewEntryItemView diaryItemView;
        if (convertView == null) 
        	diaryItemView = ViewEntryItemView.build(getContext());
        else 
        	diaryItemView = ViewEntryItemView.build(getContext());
        
        diaryItemView.bind(getItem(position), parent, diary);

        return diaryItemView;
    }
}
