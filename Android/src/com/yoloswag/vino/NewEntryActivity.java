/**
 * Filename:    NewEntryActivity.java
 * Team:		VINO
 * Description: 
 * Date:        8 Jun 2013
 **/

package com.yoloswag.vino;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import java.util.Calendar;

import com.yoloswag.vino.model.*;
import android.os.Bundle;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
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

public class NewEntryActivity extends Activity// implements TextWatcher 
{

	// AutoComplete arrays
	AutoCompleteTextView autoComplete_producer;
	AutoCompleteTextView autoComplete_category;
	AutoCompleteTextView autoComplete_region;
	AutoCompleteTextView autoComplete_varietal;

	// TODO: ADD MOAR
	// AutoComplete lists
	String producerList[] = { "Almaden", "Barefoot", "Beringer Vineyards",
			"Cavit", "Charles Shaw", "Chateau Ste. Michelle", "Copper Ridge",
			"Cupcake", "Ecco Domani", "Foxhorn Vineyards", "Franzia Winetaps",
			"Inglenook", "Kendall-Jackson", "La Terre", "Mezzacorona",
			"Salmon Creek", "Skinnygirl", "Stone Cellars", "Sutter Homes",
			"Sycamore Lane", "Taylor California Cellars", "Woodbridge",
			"Yellow Tail" };

	String categoryList[] = { "Dessert", "Red", "Rose", "Sparkling", "White" };

	String regionList[] = { "Argentina", "Australia", "Austria", "Belgium",
			"Brazil", "Bulgaria", "Burgundy", "California", "Canada", "Chile",
			"Croatia", "Czech Republic", "Denmark", "Dominican Republic",
			"France", "Germany", "Greece", "Hungary", "India", "Israel",
			"Italy", "Jordan", "Lebanon", "Malta", "Mexico", "Montenegro",
			"New Zealand", "Peru", "Portugal", "Romania", "San Marino",
			"Serbia", "Slovenia", "South Africa", "Spain", "Switzerland",
			"Turkey", "Ukraine", "United Kingdom", "United States", "Uruguay" };

	String varietalList[] = { "Angel Food", "Barbera", "Blush Wine", "Brut",
			"Cabernet Merlot", "Cabernet Sauvignon", "California Red Blend",
			"California Rose Blend", "California White Blend", "Champagne",
			"Chardonnay", "Chenin Blanc", "Chianti", "Dolcetto",
			"Dry Vermouth", "Fume Blanc", "Gewurztraminer",
			"Late Harvest Chardonnay", "Lightly Oaked Chardonnay", "Malbec",
			"Merlot", "Moscato", "Moscato D'Asti", "Mourvedre", "Petite Sirah",
			"Pink Moscato", "Pink Pinot Grigio", "Pinot Grigio", "Pinot Gris",
			"Pinot Noir", "Port", "Prosecco", "Red Moscato", "Red Velvet",
			"Riesling", "Rose", "Sangiovese", "Sauvignon Blanc", "Shiraz",
			"Shiraz-Cabernet", "Shiraz-Grenache", "Sparkling Wine",
			"Sweet Red", "Sweet White", "Summation", "Syrah", "Viognier",
			"White Merlot", "White Zinfandel", "Zinfandel" };

	/**
	 * Initializations for when a new Entry is being created
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_entry);
		// Submit button
		View button = this.findViewById(R.id.new_entry_button);

		EditText editText = (EditText) this.findViewById(R.id.title);
		Typeface typeface =Typeface.createFromAsset(getAssets(),"fonts/Roboto-Light.ttf");
		editText.setTypeface(typeface);
		editText.requestFocus();
		editText.setSelection(0);

		// Preview of captured image
		FileInputStream in;
		try 
		{
			String name = this.getFilesDir()
					+ String.valueOf(Entry.getAll().length) + ".jpg";
			in = new FileInputStream(name);
			Bitmap bitmap = BitmapFactory.decodeStream(in);

			ImageView imageView = (ImageView) this.findViewById(R.id.image);
			imageView.setImageBitmap(bitmap);
		} catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}

		// AutoComplete for Name/Producer
		autoComplete_producer = (AutoCompleteTextView) findViewById(R.id.producer);
//		autoComplete_producer.addTextChangedListener(this);
		// Wine[] wineList = Wine.getAll();
		// String[] producerList = new String[wineList.length];
		// String[] categoryList = new String[wineList.length];
		// String[] regionList = new String[wineList.length];
		/*
		 * for(int i = 0, j = 0, l = 0; i < wineList.length; i++) { if(i == 0 ||
		 * ((j-1)>-1 &&
		 * !(producerList[j-1].equals(wineList[i].name.producer.toString())))) {
		 * producerList[j] = wineList[i].name.producer.toString();
		 * 
		 * System.out.println("first check j-1 > -1? " + ((j-1)>-1)); if(j!= 0){
		 * System.out.println("producer? " + (producerList[j-1] ));
		 * System.out.println("second check? " + producerList[j-1] !=
		 * wineList[i].name.producer.toString()); }
		 * 
		 * System.out.println("wine? " +
		 * (wineList[i].name.producer.toString()));
		 * System.out.println("winelist: " + "i:" + i + " " +
		 * wineList[i].name.producer.toString());
		 * System.out.println("producerlist: " + "j: " + j + producerList[j]);
		 * j++; }
		 */
		/*
		 * if(i == 0 || (k-1)>-1 && categoryList[k-1] !=
		 * wineList[i].category.category.toString()) { categoryList[k] =
		 * wineList[i].category.category.toString(); k++; }
		 */
		/*
		 * if(i == 0 || ((l-1)>-1 && regionList[l-1] !=
		 * wineList[i].region.region.toString())) { regionList[l] =
		 * wineList[i].region.region.toString(); l++; } }
		 */
		autoComplete_producer.setAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_dropdown_item_1line, producerList));

		// Autocomplete for Category
		autoComplete_category = (AutoCompleteTextView) findViewById(R.id.category);
		autoComplete_category.setAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_dropdown_item_1line, categoryList));

		// Autocomplete for Region
		autoComplete_region = (AutoCompleteTextView) findViewById(R.id.region);
		autoComplete_region.setAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_dropdown_item_1line, regionList));

		// Autocomplete for Varietal
		autoComplete_varietal = (AutoCompleteTextView) findViewById(R.id.varietal);
		autoComplete_varietal.setAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_dropdown_item_1line, varietalList));

		// Single-check check boxes for sweet and dry qualities of wine
		final CheckBox dry = (CheckBox) findViewById(R.id.dryCheck);
		final CheckBox sweet = (CheckBox) findViewById(R.id.sweetCheck);

		OnCheckedChangeListener checkListener = new OnCheckedChangeListener() 
		{
			public void onCheckedChanged(CompoundButton arg0, boolean isChecked) 
			{
				if (isChecked) 
				{
					switch (arg0.getId()) 
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

		// Submit button
		Button b = (Button) button;
		b.setOnClickListener(new OnClickListener() 
		{
			@Override
			public void onClick(View arg1) 
			{
				// Creating entry
				Entry e = new Entry();
				EditText title = (EditText) findViewById(R.id.title);
				EditText category = (EditText) findViewById(R.id.category);
				EditText region = (EditText) findViewById(R.id.region);
				EditText varietal = (EditText) findViewById(R.id.varietal);
				EditText vintageYear = (EditText) findViewById(R.id.vintageYear);
				EditText comment = (EditText) findViewById(R.id.comments);
				AutoCompleteTextView producer = (AutoCompleteTextView) findViewById(R.id.producer);
				RatingBar rating = (RatingBar) findViewById(R.id.rating);

				// Gary toast - check that there is at least one field entered
				if (title.getText().toString().matches("")
						&& category.getText().toString().matches("")
						&& region.getText().toString().matches("")
						&& varietal.getText().toString().matches("")
						&& vintageYear.getText().toString().matches("")
						&& comment.getText().toString().matches("")
						&& producer.getText().toString().matches("")) 
				{
					View toastView = getLayoutInflater().inflate(
							R.layout.toast,
							(ViewGroup) findViewById(R.id.toastLayout));
					ImageView imageView = (ImageView) toastView
							.findViewById(R.id.garytoast);
					imageView.setImageResource(R.drawable.angry_gary);
					TextView textView = (TextView) toastView
							.findViewById(R.id.text);
					textView.setText("WTF you didn't enter anything!");
					Toast toast = new Toast(NewEntryActivity.this);
					toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
					toast.setDuration(Toast.LENGTH_SHORT);
					toast.setView(toastView);
					toast.show();
					return;
				}
				
				// Gary toast - check if a name has been entered
				if (producer.getText().toString().matches("")) 
				{
					View toastView = getLayoutInflater().inflate(
							R.layout.toast,
							(ViewGroup) findViewById(R.id.toastLayout));
					ImageView imageView = (ImageView) toastView
							.findViewById(R.id.garytoast);
					imageView.setImageResource(R.drawable.gary_vector);
					TextView textView = (TextView) toastView
							.findViewById(R.id.text);
					textView.setText("The winemakers need acknowledgement too :(");
					Toast toast = new Toast(NewEntryActivity.this);
					toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
					toast.setDuration(Toast.LENGTH_SHORT);
					toast.setView(toastView);
					toast.show();
					return;
				}

				// Gary toast - check if a varietal has been entered
				if (varietal.getText().toString().matches("")) 
				{
					View toastView = getLayoutInflater().inflate(
							R.layout.toast,
							(ViewGroup) findViewById(R.id.toastLayout));
					ImageView imageView = (ImageView) toastView
							.findViewById(R.id.garytoast);
					imageView.setImageResource(R.drawable.gary_vector);
					TextView textView = (TextView) toastView
							.findViewById(R.id.text);
					textView.setText("What are ya drinking?");
					Toast toast = new Toast(NewEntryActivity.this);
					toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
					toast.setDuration(Toast.LENGTH_SHORT);
					toast.setView(toastView);
					toast.show();
					return;
				}

				// Gary toast - check if a region has been entered
				if (region.getText().toString().matches("")) 
				{
					View toastView = getLayoutInflater().inflate(
							R.layout.toast,
							(ViewGroup) findViewById(R.id.toastLayout));
					ImageView imageView = (ImageView) toastView
							.findViewById(R.id.garytoast);
					imageView.setImageResource(R.drawable.gary_vector);
					TextView textView = (TextView) toastView
							.findViewById(R.id.text);
					textView.setText("You need to enter the wine region!");
					Toast toast = new Toast(NewEntryActivity.this);
					toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
					toast.setDuration(Toast.LENGTH_SHORT);
					toast.setView(toastView);
					toast.show();
					return;
				}

				// Gary toast - check if a vintage year has been entered
				if (vintageYear.getText().toString().matches("")) 
				{
					View toastView = getLayoutInflater().inflate(
							R.layout.toast,
							(ViewGroup) findViewById(R.id.toastLayout));
					ImageView imageView = (ImageView) toastView
							.findViewById(R.id.garytoast);
					imageView.setImageResource(R.drawable.gary_vector);
					TextView textView = (TextView) toastView
							.findViewById(R.id.text);
					textView.setText("You need to enter the vintage year.");
					Toast toast = new Toast(NewEntryActivity.this);
					toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
					toast.setDuration(Toast.LENGTH_SHORT);
					toast.setView(toastView);
					toast.show();
					return;
				}

				// Gary toast - check range of year
				int year = Integer.valueOf(vintageYear.getText().toString());
				Calendar calendar = Calendar.getInstance();
				int currentYear = calendar.get(Calendar.YEAR);

				if (year > currentYear) 
				{
					View toastView = getLayoutInflater().inflate(
							R.layout.toast,
							(ViewGroup) findViewById(R.id.toastLayout));
					ImageView imageView = (ImageView) toastView
							.findViewById(R.id.garytoast);
					imageView.setImageResource(R.drawable.gary_vector);
					TextView textView = (TextView) toastView
							.findViewById(R.id.text);
					textView.setText("Wine from the future? Don't think so!");
					Toast toast = new Toast(NewEntryActivity.this);
					toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
					toast.setDuration(Toast.LENGTH_SHORT);
					toast.setView(toastView);
					toast.show();
					return;
				}
				
				// Gary toast - check if a category has been entered
				if (category.getText().toString().matches("")) 
				{
					View toastView = getLayoutInflater().inflate(
							R.layout.toast,
							(ViewGroup) findViewById(R.id.toastLayout));
					ImageView imageView = (ImageView) toastView
							.findViewById(R.id.garytoast);
					imageView.setImageResource(R.drawable.gary_vector);
					TextView textView = (TextView) toastView
							.findViewById(R.id.text);
					textView.setText("What type of wine are you drinking?");
					Toast toast = new Toast(NewEntryActivity.this);
					toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
					toast.setDuration(Toast.LENGTH_SHORT);
					toast.setView(toastView);
					toast.show();
					return;
				}
				
				// Gary toast - check to see if sweet or dry has been checked
				if (!(dry.isChecked() || sweet.isChecked())) 
				{
					View toastView = getLayoutInflater().inflate(
							R.layout.toast,
							(ViewGroup) findViewById(R.id.toastLayout));
					ImageView imageView = (ImageView) toastView
							.findViewById(R.id.garytoast);
					imageView.setImageResource(R.drawable.gary_vector);
					TextView textView = (TextView) toastView
							.findViewById(R.id.text);
					textView.setText("Is the wine sweet or dry?");
					Toast toast = new Toast(NewEntryActivity.this);
					toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
					toast.setDuration(Toast.LENGTH_SHORT);
					toast.setView(toastView);
					toast.show();
					return;
				}
				
				// Gary toast - check to see if a rating has been given
				if (rating.getRating() < 0.5) 
				{
					View toastView = getLayoutInflater().inflate(
							R.layout.toast,
							(ViewGroup) findViewById(R.id.toastLayout));
					ImageView imageView = (ImageView) toastView
							.findViewById(R.id.garytoast);
					imageView.setImageResource(R.drawable.gary_vector);
					TextView textView = (TextView) toastView
							.findViewById(R.id.text);
					textView.setText("It couldn't have been THAT bad. Give at " +
									 "least a 0.5 bottle rating!");
					Toast toast = new Toast(NewEntryActivity.this);
					toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
					toast.setDuration(Toast.LENGTH_SHORT);
					toast.setView(toastView);
					toast.show();
					return;
				}

				// Create and save user-entered entry information
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

				e.save();

				finish();
			}
		});
	}
}
