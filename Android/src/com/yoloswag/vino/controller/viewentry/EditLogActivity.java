/**
 * Filename:    EditLogActivity.java
 * Team:		VINO
 * Description:
 * Date:        8 Jun 2013
 **/

package com.yoloswag.vino.controller.viewentry;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.CompoundButton.OnCheckedChangeListener;

import com.yoloswag.vino.R;
import com.yoloswag.vino.model.entry.Entry;
import com.yoloswag.vino.view.FontUtil;

public class EditLogActivity extends Activity 
{
	/** Called when the activity starts
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_entry);
		
		// Entry fields
		EditText title = (EditText)findViewById(R.id.title);
		EditText producer = (EditText)findViewById(R.id.producer);
		EditText category = (EditText)findViewById(R.id.category);
		EditText region = (EditText)findViewById(R.id.region);
		EditText varietal = (EditText)findViewById(R.id.varietal);
		EditText vintageYear = (EditText)findViewById(R.id.vintageYear);
		EditText comment = (EditText)findViewById(R.id.comments);
		RatingBar rating = (RatingBar)findViewById(R.id.rating);
		
		
		final RadioButton dry = (RadioButton)findViewById(R.id.dryCheck);
        final RadioButton sweet = (RadioButton)findViewById(R.id.sweetCheck);
		
        OnCheckedChangeListener checkListener = new OnCheckedChangeListener() 
        {
        	public void onCheckedChanged(CompoundButton arg0, boolean isChecked) 
        	{
        		if (isChecked)
        		{
        			switch(arg0.getId())
        			{
        		    	case R.id.dryCheck:
        		    		dry.setChecked(true);
        		    		sweet.setChecked(false);
        		    		break;
        		    	case R.id.sweetCheck:
        		    		sweet.setChecked(true);
        		    		dry.setChecked(false);
        		    		break;
        		    }	
        		}
        	}
        };
        
		dry.setOnCheckedChangeListener(checkListener);
        sweet.setOnCheckedChangeListener(checkListener); 
		
        final Intent info = this.getIntent();
		title.setText(info.getStringExtra("title"));
		producer.setText(info.getStringExtra("producer"));
		vintageYear.setText(info.getStringExtra("vintage"));
		category.setText(info.getStringExtra("category"));
		region.setText(info.getStringExtra("region"));
		comment.setText(info.getStringExtra("comment"));
		rating.setRating(info.getFloatExtra("rating", 0));
		varietal.setText(info.getStringExtra("varietal"));
		
		View button = this.findViewById(R.id.new_entry_button);
      
	    FileInputStream in;
		try 
		{
			String name = this.getFilesDir() + String.valueOf(Entry.getAll().length)+".jpg";
			in = new FileInputStream(name);
		    Bitmap bitmap = BitmapFactory.decodeStream(in);

			ImageView imageView = (ImageView)this.findViewById(R.id.image);
			imageView.setImageBitmap(bitmap);
		} catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}       

		// Set Submit button in edit Entry
		Button b = (Button) button;
        b.setOnClickListener(new OnClickListener() 
        {
			@Override
			public void onClick(View arg1) 
			{
				// Entry fields
				Entry e = (Entry)info.getSerializableExtra("entry");
				EditText title = (EditText)findViewById(R.id.title);
				EditText name = (EditText) findViewById(R.id.producer);
				EditText category = (EditText)findViewById(R.id.category);
				EditText region = (EditText)findViewById(R.id.region);
				EditText varietal = (EditText)findViewById(R.id.varietal);
				EditText vintageYear = (EditText)findViewById(R.id.vintageYear);
				EditText comment = (EditText)findViewById(R.id.comments);
				RatingBar rating = (RatingBar)findViewById(R.id.rating);
				
				// Entry and Wine details to be saved upon submitting edit
				e.title = title.getText().toString().trim();
				e.wine.category = category.getText().toString().trim();
				e.wine.name = name.getText().toString().trim();
				e.wine.region = region.getText().toString().trim();
				e.wine.varietal = varietal.getText().toString().trim();
				e.wine.vintage = vintageYear.getText().toString().trim();
				e.comment = comment.getText().toString().trim();
				e.wine.addRating(rating.getRating());
				if (dry.isChecked())
					e.wine.sweetOrDry = "Dry";
				else if (sweet.isChecked())
					e.wine.sweetOrDry = "Sweet";
				
				e.wine.save();
				e.save();
								
				finish();
			}
        });

        final ViewGroup mContainer = (ViewGroup) findViewById(android.R.id.content).getRootView();
        FontUtil.setFont(mContainer, this);
	}
}
