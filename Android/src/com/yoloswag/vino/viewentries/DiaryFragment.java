package com.yoloswag.vino.viewentries;

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
	Entry[] entries;

	@Override
	public void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		entries = Entry.getAll();
	}

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		if(entries.length == 0) {
			View rootView1 = inflater.inflate(R.layout.fragment_view_no_entry, container, false);
			return rootView1;
		}

		View rootView = inflater.inflate(R.layout.fragment_diary, container, false);
		
		ListView list = (ListView) rootView.findViewById(R.id.viewLogL);
		list.setFastScrollEnabled(true);
		list.setAdapter(new DiaryAdapter(getActivity(), 0, entries));

		return rootView;
	}
}
