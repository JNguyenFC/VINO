/**
 * Filename:    NewEntryActivity.java
 * Team:		VINO
 * Description: 
 * Date:        8 June 2013
 **/

package com.yoloswag.vino;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;

import static java.util.Arrays.asList;

import com.yoloswag.vino.model.*;
import com.yoloswag.vino.warning.GaryToast;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;

import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;

public class NewEntryActivity extends Activity {

	// AutoComplete arrays
	AutoCompleteTextView autoComplete_producer;
	AutoCompleteTextView autoComplete_category;
	AutoCompleteTextView autoComplete_region;
	AutoCompleteTextView autoComplete_varietal;

	// AutoComplete lists
	ArrayList<String> producerList = new ArrayList<String>(asList("Almaden",
			"Barefoot", "Beringer Vineyards",
			"Cavit", "Charles Shaw", "Chateau Ste. Michelle", "Copper Ridge",
			"Cupcake", "Ecco Domani", "Foxhorn Vineyards", "Franzia Winetaps",
			"Inglenook", "Kendall-Jackson", "La Terre", "Mezzacorona",
			"Salmon Creek", "Skinnygirl", "Stone Cellars", "Sutter Homes",
			"Sycamore Lane", "Taylor California Cellars", "Woodbridge",
			"Yellow Tail"));

	ArrayList<String> categoryList = new ArrayList<String>(asList("Dessert",
			"Red", "Rose", "Sparkling", "White"));

	ArrayList<String> regionList = new ArrayList<String>(asList("Argentina",
			"Australia", "Austria", "Belgium",
			"Brazil", "Bulgaria", "Burgundy", "California", "Canada", "Chile",
			"Croatia", "Czech Republic", "Denmark", "Dominican Republic",
			"France", "Germany", "Greece", "Hungary", "India", "Israel",
			"Italy", "Jordan", "Lebanon", "Malta", "Mexico", "Montenegro",
			"New Zealand", "Peru", "Portugal", "Romania", "San Marino",
			"Serbia", "Slovenia", "South Africa", "Spain", "Switzerland",
			"Turkey", "Ukraine", "United Kingdom", "United States", "Uruguay"));

	ArrayList<String> varietalList = new ArrayList<String>(asList("Angel Food",
			"Barbera", "Blush Wine", "Brut",
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
			"White Merlot", "White Zinfandel", "Zinfandel"));

	/**
	 * Initializations for when a new Entry is being created
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_entry);

		EditText editText = (EditText) this.findViewById(R.id.title);
		Typeface typeface =Typeface.createFromAsset(getAssets(),"fonts/Roboto-Light.ttf");
		editText.setTypeface(typeface);
		editText.requestFocus();
		editText.setSelection(0);
		
		showPreviewImage();
		
		// Add all existing wine data to the default data
		for(Wine wine : Wine.getAll()) {
			producerList.add(wine.name);
			categoryList.add(wine.category);
			regionList.add(wine.region);
			varietalList.add(wine.varietal);
		}
		
		// Remove duplicates from all collections
		@SuppressWarnings("unchecked")
		List<ArrayList<String>> allCollections = asList(producerList, categoryList, regionList, varietalList);
		for(ArrayList<String> collection : allCollections) {
			HashSet<String> hs = new HashSet<String>();
			hs.addAll(collection);
			collection.clear();
			collection.addAll(hs);
		}

		// AutoComplete for Name/Producer
		autoComplete_producer = (AutoCompleteTextView) findViewById(R.id.producer);
		autoComplete_producer.setAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_dropdown_item_1line, producerList));

		// AutoComplete for Category
		autoComplete_category = (AutoCompleteTextView) findViewById(R.id.category);
		autoComplete_category.setAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_dropdown_item_1line, categoryList));

		// AutoComplete for Region
		autoComplete_region = (AutoCompleteTextView) findViewById(R.id.region);
		autoComplete_region.setAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_dropdown_item_1line, regionList));

		// AutoComplete for Varietal
		autoComplete_varietal = (AutoCompleteTextView) findViewById(R.id.varietal);
		autoComplete_varietal.setAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_dropdown_item_1line, varietalList));

		// Single-check check boxes for sweet and dry qualities of wine
		final RadioButton dry = (RadioButton) findViewById(R.id.dryCheck);
		final RadioButton sweet = (RadioButton) findViewById(R.id.sweetCheck);

		// Submit button
		Button submitButton = (Button) findViewById(R.id.new_entry_button);
		submitButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg1) {
				// Creating entry
				String title = extractText(R.id.title);
				String category = extractText(R.id.category);
				String region = extractText(R.id.region);
				String varietal = extractText(R.id.varietal);
				String vintageYear = extractText(R.id.vintageYear);
				String comment = extractText(R.id.comments);
				String producer = extractText(R.id.producer);
				RatingBar rating = (RatingBar) findViewById(R.id.rating);

				// Gary toast - check that there is at least one field entered
				if (title.matches("")
						&& category.matches("")
						&& region.matches("")
						&& varietal.matches("")
						&& vintageYear.matches("")
						&& comment.matches("")
						&& producer.matches("")) 
				{
					GaryToast.show("WTF you didn't enter anything!", NewEntryActivity.this);
					return;
				}
				
				// Gary toast - check if a name has been entered
				if (producer.matches("")) {
					GaryToast.show("The winemakers need acknowledgement too :(", NewEntryActivity.this);
					return;
				}

				// Gary toast - check if a varietal has been entered
				if (varietal.matches("")) {
					GaryToast.show("What are ya drinking?", NewEntryActivity.this);
					return;
				}

				// Gary toast - check if a region has been entered
				if (region.matches("")) {
					GaryToast.show("You need to enter the wine region!", NewEntryActivity.this);
				}

				// Gary toast - check if a vintage year has been entered
				if (vintageYear.matches("")) {
					GaryToast.show("You need to enter the vintage year.", NewEntryActivity.this);
					return;
				}

				// Gary toast - check range of year
				int year = Integer.valueOf(vintageYear);
				Calendar calendar = Calendar.getInstance();
				int currentYear = calendar.get(Calendar.YEAR);

				if (year > currentYear) {
					GaryToast.show("Wine from the future? Don't think so!", NewEntryActivity.this);
					return;
				}
				
				// Gary toast - check if a category has been entered
				if (category.matches("")) {
					GaryToast.show("What type of wine are you drinking?", NewEntryActivity.this);
					return;
				}
				
				// Gary toast - check to see if sweet or dry has been checked
				if (!(dry.isChecked() || sweet.isChecked())) {
					GaryToast.show("Is the wine sweet or dry?", NewEntryActivity.this);
					return;
				}
				
				// Gary toast - check to see if a rating has been given
				if (rating.getRating() < 0.5) {
					GaryToast.show("It couldn't have been THAT bad. Give at " +
									 "least a 0.5 bottle rating!", NewEntryActivity.this);
					return;
				}

				// Create and save user-entered entry information
				Entry e = new Entry(title, comment, getFilesDir() + String.valueOf(Entry.getAll().length) + ".jpg");
				e.wine = new Wine(producer, varietal, category, region, dry.isChecked() ? "Dry" : "Sweet", vintageYear);
				e.wine.addRating(rating.getRating());

				EntryAction.addEntry(e);

				// Return to the parent activity
				finish();
			}
		});
	}
	
	private String extractText(int id) {
		EditText field = (EditText) findViewById(id);
		return field.getText().toString();
	}

	private void showPreviewImage() {
		// Preview of captured image
		FileInputStream in;
		try {
			String name = this.getFilesDir() + String.valueOf(Entry.getAll().length) + ".jpg";
			in = new FileInputStream(name);
			Bitmap bitmap = BitmapFactory.decodeStream(in);

			ImageView imageView = (ImageView) this.findViewById(R.id.image);
			imageView.setImageBitmap(bitmap);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
