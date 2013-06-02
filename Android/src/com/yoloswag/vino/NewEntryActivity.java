package com.yoloswag.vino;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import com.yoloswag.vino.model.*;
import android.os.Bundle;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;

public class NewEntryActivity extends Activity implements TextWatcher {
	
    AutoCompleteTextView myAutoComplete;
    AutoCompleteTextView myAutoComplete2;
    AutoCompleteTextView myAutoComplete3;

    /*String producerList[]={
  		  "Barefoot", "Charles Shaw", "Chateau Ste. Michelle", "Cupcake",
  		  "Kendall-Jackson", "Skinnygirl", "Sutter Homes", "Woodbridge",
  		  "Yellow Tail"
  		};*/
    
    String categoryList[]={
	  "Dessert", "Red", "Rose", "Sparkling","White"
	};
    String regionList[]={
	  "Australia", "Burgundy", "California", "Canada", "France", "Germany", 
	  "Italy", "Portugal", "Spain"
	};
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_entry);
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
		
		myAutoComplete = (AutoCompleteTextView)findViewById(R.id.producer);
	    myAutoComplete.addTextChangedListener(this);

	    Wine[] wineList = Wine.getAll();
	    String[] producerList = new String[wineList.length];
	    
	    for(int i = 0; i < wineList.length; i++)
	    {
	    	if((i-1)>-1 && producerList[i-1] != wineList[i].name.producer.toString()) {
	    		producerList[i] = wineList[i].name.producer.toString();
	    	}
	    }

	    myAutoComplete.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, producerList));
	    
		myAutoComplete2 = (AutoCompleteTextView)findViewById(R.id.category);
	    myAutoComplete2.addTextChangedListener(this);
	    myAutoComplete2.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, categoryList));
	    
		myAutoComplete3 = (AutoCompleteTextView)findViewById(R.id.region);
	    myAutoComplete3.addTextChangedListener(this);
	    myAutoComplete3.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, regionList));

		Button b = (Button) button;

        b.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg1) {
				// TODO Auto-generated method stub
				Entry e = new Entry();
				EditText title = (EditText)findViewById(R.id.title);
				EditText category = (EditText)findViewById(R.id.category);
				EditText region = (EditText)findViewById(R.id.region);
				EditText varietal = (EditText)findViewById(R.id.varietal);
				EditText vintageYear = (EditText)findViewById(R.id.vintageYear);
				EditText location = (EditText)findViewById(R.id.location);
				EditText comment = (EditText)findViewById(R.id.comments);
				AutoCompleteTextView producer = (AutoCompleteTextView) findViewById(R.id.producer);
				RatingBar rating = (RatingBar)findViewById(R.id.rating);

				//Toast.makeText(getActivity(), e.title, Toast.LENGTH_SHORT).show();

				//save picture
				e.wine = new Wine("", "", "", "", "", "");
				e.title = title.getText().toString();
				e.wine.category = new Category(category.getText().toString());
				e.wine.region = new Region(region.getText().toString());
				e.wine.varietal = new Varietal(varietal.getText().toString());
				e.wine.vintage = new Vintage(vintageYear.getText().toString());
				e.wine.name = new Name(producer.getText().toString());
				e.wine.addRating(rating.getRating());
				e.location = location.getText().toString();
				e.comment = comment.getText().toString();
				//e.wine.rating = (double)rating.getRating();
				e.uri = getFilesDir() + String.valueOf(Entry.getAll().length)+".jpg";
				
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
    
    
    @Override
    public void afterTextChanged(Editable arg0) {
     // TODO Auto-generated method stub

    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count,
      int after) {
     // TODO Auto-generated method stub

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
     // TODO Auto-generated method stub

    }
}
