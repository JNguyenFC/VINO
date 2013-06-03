package com.yoloswag.vino.viewentries;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

import com.yoloswag.vino.R;
import com.yoloswag.vino.model.Entry;
import com.yoloswag.vino.warning.TouchThisAndIWillFuckingKillYou;

import android.os.Bundle;
import android.support.v4.app.*;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

@TouchThisAndIWillFuckingKillYou
public class DiaryFragment extends Fragment {
	DiaryAdapter adapter;

	@Override
	public void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
//		if(entries.length == 0) {
//			View rootView1 = inflater.inflate(R.layout.fragment_view_no_entry, container, false);
//			return rootView1;
//		}

		View rootView = inflater.inflate(R.layout.fragment_diary, container, false);
		
		ListView list = (ListView) rootView.findViewById(R.id.viewLogL);
		list.setFastScrollEnabled(true);
		ArrayList<Entry> lst = new ArrayList<Entry>();
		List<Entry> lit = Arrays.asList(Entry.getAll());
		Collections.reverse(lit);
		lst.addAll(lit);
		adapter = new DiaryAdapter(getActivity(), 0, lst, this);
		list.setAdapter(adapter);
		
		return rootView;
	}
	
	@Override
	public void onResume() {
		super.onResume();
		if(adapter != null)
			updateData();
	}

	public void updateData() {
		adapter.clear();

		ArrayList<Entry> lst = new ArrayList<Entry>();
		List<Entry> lit = Arrays.asList(Entry.getAll());
		Collections.reverse(lit);

		for (Entry object : lit	) {
			adapter.insert(object, adapter.getCount());
		}

	    adapter.notifyDataSetChanged();
	}
}
