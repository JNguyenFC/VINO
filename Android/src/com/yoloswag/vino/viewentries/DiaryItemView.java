package com.yoloswag.vino.viewentries;

import java.util.Random;

import com.yoloswag.vino.R;
import com.yoloswag.vino.model.Entry;
import com.yoloswag.vino.warning.TouchThisAndIWillFuckingKillYou;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

@TouchThisAndIWillFuckingKillYou
public class DiaryItemView extends LinearLayout {

	public DiaryItemView(Context context) {
		super(context);
		LayoutInflater inflater = LayoutInflater.from(context);
		inflater.inflate(R.layout.view_diary_entry, this, true);
	}

	public static DiaryItemView build(Context context) {
		return new DiaryItemView(context);
	}

	public void bind(Entry entry, ViewGroup parent) {
		final ImageView iv = (ImageView) findViewById(R.id.entry_image);

		// Sets photo to be displayed to fill the screen relative to any phone
		iv.setLayoutParams(new RelativeLayout.LayoutParams(parent.getWidth(), parent.getHeight()));

		new Thread(new Runnable() {
			@Override
			public void run() {
				final Bitmap bitmap;
				switch(new Random().nextInt(4)) {
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
				iv.post(new Runnable() {
					@Override
					public void run() {
						iv.setImageBitmap(bitmap);
					}
				});
			}
		}).start();

		// Dynamically change white on black text captions on top of photos
		TextView textview_vintage = (TextView) findViewById(R.id.vintage);
		textview_vintage.setText(entry.wine.vintage.year);

		TextView textview_producer = (TextView) findViewById(R.id.producer_name);
		textview_producer.setText(entry.wine.name.producer);

		TextView textview_varietal = (TextView) findViewById(R.id.varietal_name);
		textview_varietal.setText(entry.wine.varietal.varietal_name);

		TextView textview_entry_title = (TextView) findViewById(R.id.entry_title);
		textview_entry_title.setText(entry.title);

		TextView textview_entry_comment = (TextView) findViewById(R.id.entry_comment);
		textview_entry_comment.setText(entry.comment);

		TextView textview_entry_details = (TextView) findViewById(R.id.entry_details);
		textview_entry_details.setText("Maker: " + entry.wine.name.producer + "\n" +
				"Varietal: " + entry.wine.varietal.varietal_name + "\n" +
				"Vintage: " + entry.wine.vintage.year + "\n" +
				"Region: " + entry.wine.region.region + "\n"+
				"Category: " + entry.wine.category.category + "\n" +
				"Rating: " + entry.rating
				);
		textview_entry_details.setLayoutParams(new RelativeLayout.LayoutParams(parent.getWidth(), parent.getHeight()));


		View.OnClickListener handler = new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Animation animationFadeIn = AnimationUtils.loadAnimation(getContext(), R.anim.fadein);
				Animation animationFadeOut = AnimationUtils.loadAnimation(getContext(), R.anim.fadeout);

				RelativeLayout vG = (RelativeLayout)v.getParent();
				View elem = vG.findViewById(R.id.entry_details);
				View elem2 = vG.findViewById(R.id.entry_comment);
				View elem3 = vG.findViewById(R.id.entry_title);
				View elem4 = vG.findViewById(R.id.line);
				View editBut = vG.findViewById(R.id.editText01);
				TextView textview_vintage = (TextView) vG.findViewById(R.id.vintage);
				TextView textview_producer = (TextView) vG.findViewById(R.id.producer_name);
				TextView textview_varietal = (TextView) vG.findViewById(R.id.varietal_name);
				Button delete_but = (Button) vG.findViewById(R.id.deleteEntry);
				if(v == vG.findViewById(R.id.entry_image)) {
					elem.startAnimation(animationFadeIn);
					elem2.startAnimation(animationFadeIn);
					elem3.startAnimation(animationFadeIn);
					elem4.startAnimation(animationFadeIn);			
					elem.setVisibility(RelativeLayout.VISIBLE);
					elem2.setVisibility(RelativeLayout.VISIBLE);
					elem3.setVisibility(RelativeLayout.VISIBLE);
					elem4.setVisibility(RelativeLayout.VISIBLE);
					editBut.setVisibility(RelativeLayout.VISIBLE);
					delete_but.setVisibility(RelativeLayout.VISIBLE);					

					textview_vintage.startAnimation(animationFadeOut);
					textview_producer.startAnimation(animationFadeOut);
					textview_varietal.startAnimation(animationFadeOut);				
					textview_vintage.setVisibility(RelativeLayout.GONE);
					textview_producer.setVisibility(RelativeLayout.GONE);
					textview_varietal.setVisibility(RelativeLayout.GONE);
				}
				if(v == elem || v == elem2 || v == elem3 || v == elem4) {
					elem.startAnimation(animationFadeOut);
					elem2.startAnimation(animationFadeOut);
					elem3.startAnimation(animationFadeOut);
					elem4.startAnimation(animationFadeOut);				
					elem.setVisibility(RelativeLayout.INVISIBLE);
					elem2.setVisibility(RelativeLayout.INVISIBLE);
					elem3.setVisibility(RelativeLayout.INVISIBLE);
					elem4.setVisibility(RelativeLayout.INVISIBLE);
					editBut.setVisibility(RelativeLayout.INVISIBLE);
					delete_but.setVisibility(RelativeLayout.INVISIBLE);

					textview_vintage.startAnimation(animationFadeIn);
					textview_producer.startAnimation(animationFadeIn);
					textview_varietal.startAnimation(animationFadeIn);
					textview_vintage.setVisibility(RelativeLayout.VISIBLE);
					textview_producer.setVisibility(RelativeLayout.VISIBLE);
					textview_varietal.setVisibility(RelativeLayout.VISIBLE);
				}
			}
		}; 

		// Make description appear on click
		iv.setOnClickListener(handler);
		textview_entry_details.setOnClickListener(handler);
		textview_entry_comment.setOnClickListener(handler);
		textview_entry_title.setOnClickListener(handler);
	}
}