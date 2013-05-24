package com.yoloswag.vino.favorites;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.yoloswag.vino.R;

public class SuggestionsAdapter extends ArrayAdapter<RowItem> {

	Context context;
	
	public SuggestionsAdapter(SuggestionsFragment suggestionsFragment, int resourceId,
            List<RowItem> items) {
		super(suggestionsFragment.getActivity(), resourceId, items);
		this.context = suggestionsFragment.getActivity();
	}

	private class ViewHolder{
		ImageView imageView;
		TextView txtName;
		//TextView txtVarietal;
		//TextView textVintage;
	}
	
	public View getView(int position, View convertView, ViewGroup parent){
		ViewHolder holder = null;
		RowItem rowItem = getItem(position);
		
		LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
		if(convertView == null)
		{
			convertView = mInflater.inflate(R.layout.suggestions, null);
			holder = new ViewHolder();
			holder.txtName = (TextView) convertView.findViewById(R.id.name);
			holder.imageView = (ImageView) convertView.findViewById(R.id.icon);
			convertView.setTag(holder);
		}
		else
			holder = (ViewHolder) convertView.getTag();
		holder.txtName.setText(rowItem.getName());
		holder.imageView.setImageResource(rowItem.getImageID());
		
		return convertView;
	}
}
