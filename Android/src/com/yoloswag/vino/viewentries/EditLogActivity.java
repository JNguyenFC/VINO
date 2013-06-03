package com.yoloswag.vino.viewentries;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import com.yoloswag.vino.R;
import com.yoloswag.vino.model.Category;
import com.yoloswag.vino.model.Entry;
import com.yoloswag.vino.model.Region;
import com.yoloswag.vino.model.Varietal;
import com.yoloswag.vino.model.Vintage;
import com.yoloswag.vino.model.Wine;

import com.yoloswag.vino.ViewLogActivity;

import com.yoloswag.vino.warning.TouchThisAndIWillFuckingKillYou;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;

public class EditLogActivity extends Activity {
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_entry);
		
		EditText title = (EditText)findViewById(R.id.title);
		EditText category = (EditText)findViewById(R.id.category);
		EditText region = (EditText)findViewById(R.id.region);
		EditText varietal = (EditText)findViewById(R.id.varietal);
		EditText vintageYear = (EditText)findViewById(R.id.vintageYear);
		EditText comment = (EditText)findViewById(R.id.comments);
		RatingBar rating = (RatingBar)findViewById(R.id.rating);
		
		
		final Intent info = this.getIntent();
		title.setText(info.getStringExtra("title"));
		//producer.setText(info.getStringExtra("producer"));
		vintageYear.setText(info.getStringExtra("vintage"));
		category.setText(info.getStringExtra("category"));
		region.setText(info.getStringExtra("region"));
		comment.setText(info.getStringExtra("comment"));
		rating.setRating(info.getFloatExtra("rating", 3));
		varietal.setText(info.getStringExtra("varietal"));
		info.removeExtra("title");
		
		//final View rootView = inflater.inflate(R.layout.fragment_new_entry, container, false);
        View button = this.findViewById(R.id.new_entry_button);
      
	    FileInputStream in;
		try {
			String name = this.getFilesDir() + String.valueOf(Entry.getAll().length)+".jpg";
			in = new FileInputStream(name);
		    Bitmap bitmap = BitmapFactory.decodeStream(in);

			ImageView imageView = (ImageView)this.findViewById(R.id.image);
			imageView.setImageBitmap(bitmap);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}       

		Button b = (Button) button;

        b.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg1) {
				// TODO Auto-generated method stub
				
				Entry e = (Entry)info.getSerializableExtra("entry");
				EditText title = (EditText)findViewById(R.id.title);
				EditText producer = (EditText) findViewById(R.id.producer);
				EditText category = (EditText)findViewById(R.id.category);
				EditText region = (EditText)findViewById(R.id.region);
				EditText varietal = (EditText)findViewById(R.id.varietal);
				EditText vintageYear = (EditText)findViewById(R.id.vintageYear);
				EditText comment = (EditText)findViewById(R.id.comments);
				//EditText sweetordry = (EditText) findViewById(R.id.sweetordry);
				RatingBar rating = (RatingBar)findViewById(R.id.rating);

				//Toast.makeText(getActivity(), e.title, Toast.LENGTH_SHORT).show();

				//save picture
				//e.wine = new Wine("", "", "", "", "", "");
				
				e.title = title.getText().toString();
				e.wine.category = new Category(category.getText().toString());
				e.wine.region = new Region(region.getText().toString());
				e.wine.varietal = new Varietal(varietal.getText().toString());
				e.wine.vintage = new Vintage(vintageYear.getText().toString());
				//e.wine.sweetOrDry = 
				e.comment = comment.getText().toString();
				e.wine.addRating(rating.getRating());
				e.uri = getFilesDir() + String.valueOf(Entry.getAll().length)+".jpg";
				
				e.wine.save();
				e.save();
								
				finish();
			}
        });

		//DatabaseManager.init(this);
	}

	/*@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.new_entry, menu);
		return true;
	}
	

    /** Checking if the intent is even available */
   /* public static boolean isIntentAvailable(Context context, String action) 
    {
        final PackageManager packageManager = context.getPackageManager();
        final Intent intent = new Intent(action);
        List<ResolveInfo> list =
                packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
        return list.size() > 0;
    }
    */
	
	@Override
    protected void onStart() {
        super.onStart();
        // The activity is about to become visible.
    }
    @Override
    protected void onResume() {
        super.onResume();
        // The activity has become visible (it is now "resumed").
    }
    @Override
    protected void onPause() {
        super.onPause();
        // Another activity is taking focus (this activity is about to be "paused").
    }
    @Override
    protected void onStop() {
        super.onStop();
        // The activity is no longer visible (it is now "stopped")
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
/*        // The activity is about to be destroyed.
        // Calling ViewLogEntryFragment
        Fragment fragment = new ViewLogEntryFragment();
		FragmentTransaction transaction = fragment.getFragmentManager().beginTransaction();

		transaction.replace(R.id.fragment_view_log_entry, fragment);
		transaction.addToBackStack(null);
		transaction.commit();*/
    }
}
