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
import android.os.Bundle;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.*;

import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Toast;

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
			producerList.add(wine.name.producer);
			categoryList.add(wine.category.category);
			regionList.add(wine.region.region);
			varietalList.add(wine.varietal.varietal_name);
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
					showGaryToast("WTF you didn't enter anything!");
					return;
				}
				
				// Gary toast - check if a name has been entered
				if (producer.matches("")) {
					showGaryToast("The winemakers need acknowledgement too :(");
					return;
				}

				// Gary toast - check if a varietal has been entered
				if (varietal.matches("")) {
					showGaryToast("What are ya drinking?");
					return;
				}

				// Gary toast - check if a region has been entered
				if (region.matches("")) {
					showGaryToast("You need to enter the wine region!");
				}

				// Gary toast - check if a vintage year has been entered
				if (vintageYear.matches("")) {
					showGaryToast("You need to enter the vintage year.");
					return;
				}

				// Gary toast - check range of year
				int year = Integer.valueOf(vintageYear);
				Calendar calendar = Calendar.getInstance();
				int currentYear = calendar.get(Calendar.YEAR);

				if (year > currentYear) {
					showGaryToast("Wine from the future? Don't think so!");
					return;
				}
				
				// Gary toast - check if a category has been entered
				if (category.matches("")) {
					showGaryToast("What type of wine are you drinking?");
					return;
				}
				
				// Gary toast - check to see if sweet or dry has been checked
				if (!(dry.isChecked() || sweet.isChecked())) {
					showGaryToast("Is the wine sweet or dry?");
					return;
				}
				
				// Gary toast - check to see if a rating has been given
				if (rating.getRating() < 0.5) {
					showGaryToast("It couldn't have been THAT bad. Give at " +
									 "least a 0.5 bottle rating!");
					return;
				}

				// Create and save user-entered entry information
				Entry e = new Entry();
				e.wine = new Wine("", "", "", "", "", "");
				e.title = title;
				e.uri = getFilesDir() + String.valueOf(Entry.getAll().length)
						+ ".jpg";
				e.comment = comment;
				e.wine.category = new Category(category);
				e.wine.region = new Region(region);
				e.wine.varietal = new Varietal(varietal);
				e.wine.vintage = new Vintage(vintageYear);
				e.wine.name = new Name(producer);
				e.wine.addRating(rating.getRating());
				if (dry.isChecked())
					e.wine.sweetOrDry = new SweetOrDry("Dry");
				else if (sweet.isChecked())
					e.wine.sweetOrDry = new SweetOrDry("Sweet");

				e.save();

				// Return to the parent activity
				finish();
			}
		});
	}
	
	private String extractText(int id) {
		EditText field = (EditText) findViewById(id);
		return field.getText().toString();
	}
	
	private void showGaryToast(String message) {
		View toastView = getLayoutInflater().inflate(R.layout.toast,
				(ViewGroup) findViewById(R.id.toastLayout));
		ImageView imageView = (ImageView) toastView.findViewById(R.id.garytoast);
		imageView.setImageResource(R.drawable.gary_vector);
		TextView textView = (TextView) toastView.findViewById(R.id.text);
		textView.setText(message);
		Toast toast = new Toast(NewEntryActivity.this);
		toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
		toast.setDuration(Toast.LENGTH_SHORT);
		toast.setView(toastView);
		toast.show();
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
