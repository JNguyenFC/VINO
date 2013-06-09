/**
 * Filename:    DiaryItemView.java
 * Team:		VINO
 * Description:
 * Date:        8 Jun 2013
 **/

package com.yoloswag.vino.view.viewentry;

import java.util.Random;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yoloswag.vino.R;
import com.yoloswag.vino.controller.viewentry.EditLogActivity;
import com.yoloswag.vino.controller.viewentry.ViewEntryFragment;
import com.yoloswag.vino.model.entry.Entry;

public class ViewEntryItemView extends LinearLayout 
{
	// Constructor for DiaryItemView
	public ViewEntryItemView(Context context) 
	{
		super(context);
		LayoutInflater inflater = LayoutInflater.from(context);
		inflater.inflate(R.layout.fragment_diary_view_entry, this, true);
	}

	/** Creates a new DiaryItemView with this context
	 */
	public static ViewEntryItemView build(Context context) 
	{
		return new ViewEntryItemView(context);
	}

	/** Connects the entry to the DiaryFragment
	 */
	public void bind(final Entry entry, ViewGroup parent, final ViewEntryFragment diary) 
	{
		final ImageView iv = (ImageView) findViewById(R.id.entry_image);

		// Sets photo to be displayed to fill the screen relative to any phone
		iv.setLayoutParams(new RelativeLayout.LayoutParams(parent.getWidth(), parent.getHeight()));

		new Thread(new Runnable() 
		{
			@Override
			public void run() 
			{
				final Bitmap bitmap;
				switch(new Random().nextInt(4)) 
				{
				case 0:
					bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.vino1);
					break;
				case 1:
					bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.vino2);
					break;
				case 2:
					bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.vino3);
					break;
				default:
					bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.vino4);
					break;
				}
				iv.post(new Runnable() 
				{
					@Override
					public void run() 
					{
						iv.setImageBitmap(bitmap);
						iv.setImageURI(Uri.parse(entry.uri));
					}
				});
			}
		}).start();

		// Dynamically change white on black text captions on top of photos
		TextView textview_producer = (TextView) findViewById(R.id.entry_caption_date);
		textview_producer.setText(entry.postDate);

		TextView textview_varietal = (TextView) findViewById(R.id.entry_caption_title);
		textview_varietal.setText(entry.title);
		
		TextView textview_entry_title = (TextView) findViewById(R.id.entry_title);
		textview_entry_title.setText(entry.title);
		
		TextView textview_entry_postDate = (TextView) findViewById(R.id.entry_postDate);
		textview_entry_postDate.setText(entry.postDate);

		TextView textview_entry_comment = (TextView) findViewById(R.id.entry_comment);
		textview_entry_comment.setText(entry.comment);

		TextView textview_entry_details = (TextView) findViewById(R.id.entry_details);
		textview_entry_details.setText("Winemaker: " + entry.wine.name + "\n" +
				"Varietal: " + entry.wine.varietal + "\n" +
				"Vintage: " + entry.wine.vintage + "\n" +
				"Region: " + entry.wine.region + "\n"+
				"Category: " + entry.wine.category + "\n" +
				"SweetOrDry: " + entry.wine.sweetOrDry + "\n" + 
				"Rating: " + entry.wine.rating
				);
		textview_entry_details.setLayoutParams(new RelativeLayout.LayoutParams(parent.getWidth(), parent.getHeight()));

		// Display entry details upon image tap; disappears when tapped again
		View.OnClickListener handler = new View.OnClickListener() 
		{
			@Override
			public void onClick(View v) 
			{
				// Subtle fade out and fade in animations upon entry tap
				Animation animationFadeIn = AnimationUtils.loadAnimation(getContext(), R.anim.fadein);
				Animation animationFadeOut = AnimationUtils.loadAnimation(getContext(), R.anim.fadeout);

				RelativeLayout relative_layout = (RelativeLayout)v.getParent();
				View elt_entryDetails = relative_layout.findViewById(R.id.entry_details);
				View elt_entryComment = relative_layout.findViewById(R.id.entry_comment);
				View elt_entryTitle = relative_layout.findViewById(R.id.entry_title);
				View elt_entryDate = relative_layout.findViewById(R.id.entry_postDate);
				View elt_entryLine = relative_layout.findViewById(R.id.line);
				View editButton = relative_layout.findViewById(R.id.editEntry);
				
				TextView textview_captionDate = (TextView) relative_layout.findViewById(R.id.entry_caption_date);
				TextView textview_captionTitle = (TextView) relative_layout.findViewById(R.id.entry_caption_title);
				
				// Set Edit button in Entry
				editButton.setOnClickListener(new OnClickListener()
				{
					public void onClick(View v)
					{
						Intent intent = new Intent(v.getContext(), EditLogActivity.class);
					
						intent.removeExtra("title");
						intent.putExtra("entry", entry);
						intent.putExtra("producer", entry.wine.name);
						intent.putExtra("varietal", entry.wine.varietal);
						intent.putExtra("category", entry.wine.category);
						intent.putExtra("region", entry.wine.region);
						intent.putExtra("vintage", entry.wine.vintage);
						intent.putExtra("sweetordry", entry.wine.sweetOrDry);
						intent.putExtra("comment", entry.comment);
						intent.putExtra("rating", entry.wine.rating);
						intent.putExtra("title", entry.title);
						v.getContext().startActivity(intent);
					
						diary.updateData();
					}
				});
				
				// Set Delete button in Entry
				ImageButton delButton = (ImageButton) relative_layout.findViewById(R.id.deleteEntry);
				delButton.setOnClickListener(new View.OnClickListener() 
				{
					@Override
					public void onClick(View v) 
					{
						entry.destroy();
						diary.updateData();
					}
				});
				
				// If Entry photo is tapped, fade in Entry's details and fade
				// out photo captions
				if (v == relative_layout.findViewById(R.id.entry_image)) 
				{
					elt_entryDetails.startAnimation(animationFadeIn);
					elt_entryComment.startAnimation(animationFadeIn);
					elt_entryTitle.startAnimation(animationFadeIn);
					elt_entryDate.startAnimation(animationFadeIn);
					elt_entryLine.startAnimation(animationFadeIn);
					editButton.startAnimation(animationFadeIn);
					delButton.startAnimation(animationFadeIn);
					elt_entryDetails.setVisibility(RelativeLayout.VISIBLE);
					elt_entryComment.setVisibility(RelativeLayout.VISIBLE);
					elt_entryTitle.setVisibility(RelativeLayout.VISIBLE);
					elt_entryDate.setVisibility(RelativeLayout.VISIBLE);
					elt_entryLine.setVisibility(RelativeLayout.VISIBLE);
					editButton.setVisibility(RelativeLayout.VISIBLE);
					delButton.setVisibility(RelativeLayout.VISIBLE);					

					textview_captionDate.startAnimation(animationFadeOut);
					textview_captionTitle.startAnimation(animationFadeOut);	
					textview_captionDate.setVisibility(RelativeLayout.GONE);
					textview_captionTitle.setVisibility(RelativeLayout.GONE);
				}
				
				// If Entry details is tapped, fade out Entry details and fade
				// in photo captions
				if (v == elt_entryDetails || v == elt_entryComment || v == elt_entryTitle || v == elt_entryLine) 
				{
					elt_entryDetails.startAnimation(animationFadeOut);
					elt_entryComment.startAnimation(animationFadeOut);
					elt_entryTitle.startAnimation(animationFadeOut);
					elt_entryDate.startAnimation(animationFadeOut);
					elt_entryLine.startAnimation(animationFadeOut);	
					editButton.startAnimation(animationFadeOut);
					delButton.startAnimation(animationFadeOut);
					elt_entryDetails.setVisibility(RelativeLayout.INVISIBLE);
					elt_entryComment.setVisibility(RelativeLayout.INVISIBLE);
					elt_entryTitle.setVisibility(RelativeLayout.INVISIBLE);
					elt_entryDate.setVisibility(RelativeLayout.INVISIBLE);
					elt_entryLine.setVisibility(RelativeLayout.INVISIBLE);
					editButton.setVisibility(RelativeLayout.INVISIBLE);
					delButton.setVisibility(RelativeLayout.INVISIBLE);

					textview_captionDate.startAnimation(animationFadeIn);
					textview_captionTitle.startAnimation(animationFadeIn);
					textview_captionDate.setVisibility(RelativeLayout.VISIBLE);
					textview_captionTitle.setVisibility(RelativeLayout.VISIBLE);
				}
			}
		}; 

		// Make description appear/disappear when anywhere on screen is tapped
		iv.setOnClickListener(handler);
		textview_entry_details.setOnClickListener(handler);
		textview_entry_comment.setOnClickListener(handler);
		textview_entry_title.setOnClickListener(handler);
		textview_entry_postDate.setOnClickListener(handler);
	}
}