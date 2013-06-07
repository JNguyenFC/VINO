/**
 * Filename:    EditLogActivity.java
 * Team:		VINO
 * Description:
 * Date:        8 Jun 2013
 **/

package com.yoloswag.vino.viewentries;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;

import com.yoloswag.vino.R;
import com.yoloswag.vino.model.Category;
import com.yoloswag.vino.model.Entry;
import com.yoloswag.vino.model.Name;
import com.yoloswag.vino.model.Region;
import com.yoloswag.vino.model.Varietal;
import com.yoloswag.vino.model.Vintage;

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
		
		final Intent info = this.getIntent();
		title.setText(info.getStringExtra("title"));
		producer.setText(info.getStringExtra("producer"));
		vintageYear.setText(info.getStringExtra("vintage"));
		category.setText(info.getStringExtra("category"));
		region.setText(info.getStringExtra("region"));
		comment.setText(info.getStringExtra("comment"));
		rating.setRating(info.getFloatExtra("rating", 3));
		varietal.setText(info.getStringExtra("varietal"));
		info.removeExtra("title");
		
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
				e.title = title.getText().toString();
				e.wine.category = new Category(category.getText().toString());
				e.wine.name = new Name(name.getText().toString());
				e.wine.region = new Region(region.getText().toString());
				e.wine.varietal = new Varietal(varietal.getText().toString());
				e.wine.vintage = new Vintage(vintageYear.getText().toString());
				e.comment = comment.getText().toString();
				e.wine.addRating(rating.getRating());
				
				e.wine.save();
				e.save();
								
				finish();
			}
        });
	}
	
	/** The activity is about to become visible
	 */
	@Override
    protected void onStart() 
	{
        super.onStart();
    }
	
	/** The activity is now "resumed"
	 */
    @Override
    protected void onResume() 
    {
        super.onResume();
    }
    
    /** This activity is about to be "paused" while another takes focus
     */
    @Override
    protected void onPause() 
    {
        super.onPause();
    }
    
    /** The activity is now "stopped" and is no longer visible
     */
    @Override
    protected void onStop() 
    {
        super.onStop();
    }
    
    /** The activity is about to be terminated
     */
    @Override
    protected void onDestroy() 
    {
        super.onDestroy();
    }
}
