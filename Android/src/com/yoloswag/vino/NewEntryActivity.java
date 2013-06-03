package com.yoloswag.vino;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import java.util.Calendar;

import com.yoloswag.vino.model.*;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.*;
import android.widget.CompoundButton.OnCheckedChangeListener;

import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Toast;

public class NewEntryActivity extends Activity implements TextWatcher {

	AutoCompleteTextView myAutoComplete;
	AutoCompleteTextView myAutoComplete2;
	AutoCompleteTextView myAutoComplete3;

	/*
	 * String producerList[]={ "Barefoot", "Charles Shaw",
	 * "Chateau Ste. Michelle", "Cupcake", "Kendall-Jackson", "Skinnygirl",
	 * "Sutter Homes", "Woodbridge", "Yellow Tail" };
	 */

	String categoryList[] = { "Dessert", "Red", "Rose", "Sparkling", "White" };

	/*
	 * String regionList[]={ "Australia", "Burgundy", "California", "Canada",
	 * "France", "Germany", "Italy", "Portugal", "Spain" };
	 */

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_entry);
		View button = this.findViewById(R.id.new_entry_button);

        EditText editText = (EditText)this.findViewById(R.id.title);
        editText.requestFocus(); 
        editText.setSelection(0); 
        
        // Preview of captured image
	    FileInputStream in;
		try {
			String name = this.getFilesDir()
					+ String.valueOf(Entry.getAll().length) + ".jpg";
			in = new FileInputStream(name);
			Bitmap bitmap = BitmapFactory.decodeStream(in);

			ImageView imageView = (ImageView) this.findViewById(R.id.image);
			imageView.setImageBitmap(bitmap);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}        
		
		// Autocomplete for producer
		myAutoComplete = (AutoCompleteTextView)findViewById(R.id.producer);
	    myAutoComplete.addTextChangedListener(this);
	    Wine[] wineList = Wine.getAll();
	    String[] producerList = new String[wineList.length];
	    String[] categoryList = new String[wineList.length];
	    String[] regionList = new String[wineList.length];
	    for(int i = 0, j = 0, l = 0; i < wineList.length; i++)
	    {
	    	if(i == 0 || ((j-1)>-1 && producerList[j-1].equals(wineList[i].name.producer.toString()))) {
	    		producerList[j] = wineList[i].name.producer.toString();
	    		
	    		System.out.println("first check j-1 > -1? " + ((j-1)>-1));
	    		if(j!= 0){
	    		System.out.println("producer? " + (producerList[j-1] ));
	    		System.out.println("second check? " + producerList[j-1] != wineList[i].name.producer.toString());
	    		}
	    		
	    	    System.out.println("wine? " + (wineList[i].name.producer.toString()));
	    		System.out.println("winelist: " + "i:" + i + " " + wineList[i].name.producer.toString());
	    		System.out.println("producerlist: " + "j: " + j + producerList[j]);
	    		j++;
	    	}
	    	/*
	    	if(i == 0 || (k-1)>-1 && categoryList[k-1] != wineList[i].category.category.toString()) {
	    		categoryList[k] = wineList[i].category.category.toString();
	    		k++;
	    	}*/
	    	if(i == 0 || ((l-1)>-1 && regionList[l-1] != wineList[i].region.region.toString())) {
	    		regionList[l] = wineList[i].region.region.toString();
	    		l++;
	    	}
	    }
	    myAutoComplete.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, producerList));
	    
	    // Autocomplete for category
		myAutoComplete2 = (AutoCompleteTextView)findViewById(R.id.category);
	    myAutoComplete2.addTextChangedListener(this);
	    myAutoComplete2.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, categoryList));
	   
	    // Autocomplete for region
		myAutoComplete3 = (AutoCompleteTextView)findViewById(R.id.region);
	    myAutoComplete3.addTextChangedListener(this);
	    myAutoComplete3.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, regionList));

		// Single-check check boxes for sweet and dry qualities of wine
		final CheckBox dry = (CheckBox) findViewById(R.id.dryCheck);
		final CheckBox sweet = (CheckBox) findViewById(R.id.sweetCheck);

		OnCheckedChangeListener checkListener = new OnCheckedChangeListener() {
			public void onCheckedChanged(CompoundButton arg0, boolean isChecked) {
				if (isChecked) {
					switch (arg0.getId()) {
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

		// Submit button
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
				//EditText location = (EditText)findViewById(R.id.location);
				EditText comment = (EditText)findViewById(R.id.comments);
				AutoCompleteTextView producer = (AutoCompleteTextView) findViewById(R.id.producer);
				RatingBar rating = (RatingBar) findViewById(R.id.rating);

				// display toast if a producer has not been entered
				if (producer.getText().toString().matches("")) {
					View toastView = getLayoutInflater()
							.inflate(R.layout.toast, (ViewGroup)findViewById(R.id.toastLayout));
					ImageView imageView = (ImageView) toastView
							.findViewById(R.id.garytoast);

					imageView.setImageResource(R.drawable.gary_vector);

					TextView textView = (TextView) toastView
							.findViewById(R.id.text);

					textView.setText("Hey!! You need to acknowledge the producers!");
					Toast toast = new Toast(NewEntryActivity.this);
					toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
					toast.setDuration(Toast.LENGTH_SHORT);
					toast.setView(toastView);
					toast.show();
					return;
				}

				// check if a wine varietal has been entered
				if (varietal.getText().toString().matches("")) {
					View toastView = getLayoutInflater()
							.inflate(R.layout.toast, (ViewGroup)findViewById(R.id.toastLayout));
					ImageView imageView = (ImageView) toastView
							.findViewById(R.id.garytoast);

					imageView.setImageResource(R.drawable.gary_vector);

					TextView textView = (TextView) toastView
							.findViewById(R.id.text);

					textView.setText("Whatareya drinking?");
					Toast toast = new Toast(NewEntryActivity.this);
					toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
					toast.setDuration(Toast.LENGTH_SHORT);
					toast.setView(toastView);
					toast.show();
					return;
				
				}

				// check if region has been entered
				if (region.getText().toString().matches("")) {
					View toastView = getLayoutInflater()
							.inflate(R.layout.toast, (ViewGroup)findViewById(R.id.toastLayout));
					ImageView imageView = (ImageView) toastView
							.findViewById(R.id.garytoast);

					imageView.setImageResource(R.drawable.gary_vector);

					TextView textView = (TextView) toastView
							.findViewById(R.id.text);

					textView.setText("Hey, where's the wine from huh??");
					Toast toast = new Toast(NewEntryActivity.this);
					toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
					toast.setDuration(Toast.LENGTH_SHORT);
					toast.setView(toastView);
					toast.show();
					return;
					
				}

				// check if a vintage year has been entered
				if (vintageYear.getText().toString().matches("")) {
					View toastView = getLayoutInflater()
							.inflate(R.layout.toast, (ViewGroup)findViewById(R.id.toastLayout));
					ImageView imageView = (ImageView) toastView
							.findViewById(R.id.garytoast);

					imageView.setImageResource(R.drawable.gary_vector);

					TextView textView = (TextView) toastView
							.findViewById(R.id.text);

					textView.setText("Nope sorry! You need to enter the vintage year!");
					Toast toast = new Toast(NewEntryActivity.this);
					toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
					toast.setDuration(Toast.LENGTH_SHORT);
					toast.setView(toastView);
					toast.show();
					return;
					
				}

				// check if year has occurred yet
				if (vintageYear.getText().toString().matches("Drinking wine from the future eh?")) {
					View toastView = getLayoutInflater()
							.inflate(R.layout.toast, (ViewGroup)findViewById(R.id.toastLayout));
					ImageView imageView = (ImageView) toastView
							.findViewById(R.id.garytoast);

					imageView.setImageResource(R.drawable.gary_vector);

					TextView textView = (TextView) toastView
							.findViewById(R.id.text);

					textView.setText("");
					Toast toast = new Toast(NewEntryActivity.this);
					toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
					toast.setDuration(Toast.LENGTH_SHORT);
					toast.setView(toastView);
					toast.show();
					return;
				}

				// Toast.makeText(getActivity(), e.title,
				// Toast.LENGTH_SHORT).show();

				// Entry details
				e.wine = new Wine("", "", "", "", "", "");
				e.title = title.getText().toString();
				e.uri = getFilesDir() + String.valueOf(Entry.getAll().length)
						+ ".jpg";
				e.comment = comment.getText().toString();
				e.wine.category = new Category(category.getText().toString());
				e.wine.region = new Region(region.getText().toString());
				e.wine.varietal = new Varietal(varietal.getText().toString());
				e.wine.vintage = new Vintage(vintageYear.getText().toString());
				e.wine.name = new Name(producer.getText().toString());
				e.wine.addRating(rating.getRating());
				if (dry.isChecked())
					e.wine.sweetOrDry = new SweetOrDry("Dry");
				else if (sweet.isChecked())
					e.wine.sweetOrDry = new SweetOrDry("Sweet");
				// e.wine.rating = (double)rating.getRating();

				e.save();

				finish();
			}
		});

		// DatabaseManager.init(this);
	}

	/*
	 * @Override public boolean onCreateOptionsMenu(Menu menu) { // Inflate the
	 * menu; this adds items to the action bar if it is present.
	 * getMenuInflater().inflate(R.menu.new_entry, menu); return true; }
	 * 
	 * 
	 * /** Checking if the intent is even available
	 */
	/*
	 * public static boolean isIntentAvailable(Context context, String action) {
	 * final PackageManager packageManager = context.getPackageManager(); final
	 * Intent intent = new Intent(action); List<ResolveInfo> list =
	 * packageManager.queryIntentActivities(intent,
	 * PackageManager.MATCH_DEFAULT_ONLY); return list.size() > 0; }
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
		// Another activity is taking focus (this activity is about to be
		// "paused").
	}

	@Override
	protected void onStop() {
		super.onStop();
		// The activity is no longer visible (it is now "stopped")
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		/*
		 * // The activity is about to be destroyed. // Calling
		 * ViewLogEntryFragment Fragment fragment = new ViewLogEntryFragment();
		 * FragmentTransaction transaction =
		 * fragment.getFragmentManager().beginTransaction();
		 * 
		 * transaction.replace(R.id.fragment_view_log_entry, fragment);
		 * transaction.addToBackStack(null); transaction.commit();
		 */
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
