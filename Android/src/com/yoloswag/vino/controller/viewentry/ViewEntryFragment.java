/**
 * Filename:    DiaryFragment.java
 * Team:		VINO
 * Description: 
 * Date:        8 Jun 2013
 **/

package com.yoloswag.vino.controller.viewentry;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

import com.yoloswag.vino.R;
import com.yoloswag.vino.model.entry.Entry;

import android.os.Bundle;
import android.support.v4.app.*;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class ViewEntryFragment extends Fragment {
	ViewEntryAdapter adapter;
	Entry[] entries;

	/** Get all entries from the database when fragment is created
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		entries = Entry.getAll();
	}

	/** Creates and returns the ListView for the entries
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		ArrayList<Entry> lst = new ArrayList<Entry>();
		View rootView = inflater.inflate(R.layout.fragment_diary_view_all_entries, container, false);

		ListView list = (ListView) rootView.findViewById(R.id.viewLogL);

		List<Entry> lit = Arrays.asList(Entry.getAll());
		Collections.reverse(lit);
		lst.addAll(lit);
		
		adapter = new ViewEntryAdapter(getActivity(), 0, lst, this);
		list.setAdapter(adapter);

		return rootView;
	}

	/** Initializes newly added entries and their recommendations so that
	 *  they are viewable in the Favorites tab
	 */
	@Override
	public void onResume() {
		super.onResume();
		if (adapter != null)
			updateData();
	}

	/** Update list of all entries
	 */
	public void updateData() {
		adapter.clear();

		List<Entry> lit = Arrays.asList(Entry.getAll());
		Collections.reverse(lit);

		for (Entry object : lit) {
			adapter.insert(object, adapter.getCount());
		}

		adapter.notifyDataSetChanged();
	}

	public void refresh() {
		if (adapter != null)
			updateData();
	}
}
