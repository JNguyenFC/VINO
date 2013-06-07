/**
 * Filename:    DiaryFragment.java
 * Team:		VINO
 * Description: 
 * Date:        8 Jun 2013
 **/

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
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

@TouchThisAndIWillFuckingKillYou
public class DiaryFragment extends Fragment 
{
	DiaryAdapter adapter;
	Entry[] entries;

	/** Get all entries from the database when fragment is created
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		entries = Entry.getAll();
	}

	/** Creates and returns the ListView for the entries
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		//		if(entries.length == 0) {
		//			View rootView1 = inflater.inflate(R.layout.fragment_view_no_entry, container, false);
		//			return rootView1;
		//		}

		ArrayList<Entry> lst = new ArrayList<Entry>();
		View rootView = inflater.inflate(R.layout.fragment_diary, container, false);

//		// Tutorial button
//		View button = ((View) rootView).findViewById(R.id.view_entry_button);
//		Button b = (Button) button;
//		b.setOnClickListener(new OnClickListener() 
//		{
//			@Override
//			public void onClick(View arg1) 
//			{
//				//  toast - Click on New Entry
//				View toastView = getActivity().getLayoutInflater().inflate(R.layout.toast, (ViewGroup)getActivity().findViewById(R.id.toastLayout));
//				ImageView imageView = (ImageView) toastView.findViewById(R.id.garytoast);
//				imageView.setImageResource(R.drawable.gary_vector);
//				TextView textView = (TextView) toastView.findViewById(R.id.text);
//				textView.setText("Click on the New Entry Tab and take a picture, fill out the details, " +
//						"click Submit. View your Log Entries in the View Entries tab. See your highest rated wines in the Favorites tab!");
//				Toast toast = new Toast(getActivity().getApplicationContext());
//				toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
//				toast.setDuration(Toast.LENGTH_LONG);
//				toast.setView(toastView);
//				toast.show();
//			}
//		});

		ListView list = (ListView) rootView.findViewById(R.id.viewLogL);
		list.setFastScrollEnabled(true);

		List<Entry> lit = Arrays.asList(Entry.getAll());
		Collections.reverse(lit);
		lst.addAll(lit);
		
		adapter = new DiaryAdapter(getActivity(), 0, lst, this);
		list.setAdapter(adapter);



		return rootView;
	}

	/** Initializes newly added entries and their recommendations so that
	 *  they are viewable in the Favorites tab
	 */
	@Override
	public void onResume() 
	{
		super.onResume();
		if (adapter != null)
			updateData();
	}

	/** Update list of all entries
	 */
	public void updateData() 
	{
		adapter.clear();

		//ArrayList<Entry> lst = new ArrayList<Entry>();
		List<Entry> lit = Arrays.asList(Entry.getAll());
		Collections.reverse(lit);

		for (Entry object : lit	) 
		{
			adapter.insert(object, adapter.getCount());
		}

		adapter.notifyDataSetChanged();
	}
}
