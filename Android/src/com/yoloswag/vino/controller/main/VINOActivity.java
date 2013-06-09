/**
 * Filename:    VINOActivity.java
 * Team:		VINO
 * Description: The driver for our application.
 * Date:        8 Jun 2013
 **/

package com.yoloswag.vino.controller.main;

import java.util.Locale;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;

import com.yoloswag.vino.R;
import com.yoloswag.vino.controller.favorites.FavoritesFragment;
import com.yoloswag.vino.controller.newentry.NewEntryFragment;
import com.yoloswag.vino.controller.viewentry.ViewEntryFragment;
import com.yoloswag.vino.model.db.DatabaseManager;
import com.yoloswag.vino.model.entry.Entry;
import com.yoloswag.vino.model.entry.EntryAction;
import com.yoloswag.vino.model.wine.Wine;

public class VINOActivity extends FragmentActivity implements ActionBar.TabListener 
{

    /** The PagerAdapter that will provide fragments for each of the tabs. A
     *  FragmentPagerAdapter derivative is used, which will keep every loaded
     *  fragment in memory.
     */
    SectionsPagerAdapter mSectionsPagerAdapter;
 
    
    /** The ViewPager that will host the section contents.
     */
    ViewPager mViewPager;


	public ViewEntryFragment viewEntryFragment = new ViewEntryFragment();

    @SuppressLint("NewApi")
	@Override
    protected void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vino);
        DatabaseManager.init(this);

        // Sets up the tabs
        final ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        // Create the adapter that will return a fragment for each of the three
        // tabs
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        // Allow swiping between different sections
        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() 
        {
            @Override
            public void onPageSelected(int position) 
            {
                actionBar.setSelectedNavigationItem(position);
            }
        });

        // Adds 3 tabs
        for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) 
        {
            // Create a tab with text corresponding to the page title defined by
            // the adapter
            actionBar.addTab(
                    actionBar.newTab()
                            .setText(mSectionsPagerAdapter.getPageTitle(i))
                            .setTabListener(this));
        }
        actionBar.setSplitBackgroundDrawable(new ColorDrawable(Color.parseColor("#ffffff")));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.vino, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	switch (item.getItemId()) {
    	case R.id.action_add: {
    		{
    			Entry e1 = new Entry("The Newest Wine Thieves", "Carruth Cellars, we love you (almost as much as your staff and patrons love us).", "android.resource://com.yoloswag.vino/" + R.drawable.vino1);
    			Wine w1 = new Wine("Carruth Cellars", "Sauvignon Blanc", "White", "California", "Sweet", "2011");
    			e1.wine = w1;
    			EntryAction.addEntry(e1);
    		}{
    			Entry e1 = new Entry("A Local Treat", "Checked out Orfila Winery in Escondido today! Such beautiful grounds with some lovely wines. The Cab Sauv had some really great earthy tones with a really rich berry profile. What a treat!", "android.resource://com.yoloswag.vino/" + R.drawable.vino2);
    			Wine w1 = new Wine("Orfila", "Cabernet Sauvignon", "Red", "California", "Dry", "2010");
    			e1.wine = w1;
    			EntryAction.addEntry(e1);
    		}{
    			Entry e1 = new Entry("99 Bottles of Wine on the Wall", "Added another bottle to the collection! Barefoot Zinfandel. Not sure if I liked it that much, guess I'll have to have another glass to find out?", "android.resource://com.yoloswag.vino/" + R.drawable.vino3);
    			Wine w1 = new Wine("Barefoot", "Zinfandel", "Red", "California", "Dry", "2013");
    			e1.wine = w1;
    			EntryAction.addEntry(e1);
    		}{
    			Entry e1 = new Entry("The Happiest of Hours", "Went to Underbelly in Little Italy for happy hour! They don't have a super extensive wine list, but this Riesling was off the charts! And you can't beat the presentation. Gotta come back soon.", "android.resource://com.yoloswag.vino/" + R.drawable.vino4);
    			Wine w1 = new Wine("Chateau Ste. Michelle", "Riesling", "White", "Washington", "Sweet", "2011");
    			e1.wine = w1;
    			EntryAction.addEntry(e1);
    		}{
    			Entry e1 = new Entry("Totes Adorbs", "Doyle can't drink wine, but I can! Had a glass of some White Zinfandel this afternoon while watching Doyle play in the backyard. It had the perfect amount of sweetness, definitely a great way to relax.", "android.resource://com.yoloswag.vino/" + R.drawable.vino5);
    			Wine w1 = new Wine("Sutter Homes", "White Zinfandel", "Rose", "California", "Sweet", "2013");
    			e1.wine = w1;
    			EntryAction.addEntry(e1);
    		}
    		viewEntryFragment.refresh();
    		break;
    	}
    	}
    	return super.onOptionsItemSelected(item);
    }
    
    /** Switch to corresponding page in ViewPager when the given tab is selected
     */
    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) 
    {
        mViewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) 
    {
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) 
    {
    }
    
    /** When Submit button in NewEntryActivity is clicked, switch to
     *  the corresponding page in the ViewPager
     */
    public void onSubmit() 
    {
        mViewPager.setCurrentItem(0);
    }

    /** A FragmentPagerAdapter that returns a fragment corresponding to
     *  the specified tab
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter 
    {

        public SectionsPagerAdapter(FragmentManager fm) 
        {
            super(fm);
        }

        /** Instantiates fragment for the given page
         */
        @Override
        public Fragment getItem(int position) 
        {   
            switch (position)
            {
            	case 0: return viewEntryFragment ;
            	case 1: return new NewEntryFragment();
            	case 2: return new FavoritesFragment();
            }
            
            return new ViewEntryFragment();

        }

        /** Gets total count of tabs (3)
         */
        @Override
        public int getCount() 
        {
            return 3;
        }

        /** Gets label for given tab
         */
        @Override
        public CharSequence getPageTitle(int position) 
        {
            Locale l = Locale.getDefault();
            switch (position) {
                case 0:
                    return getString(R.string.title_section1).toUpperCase(l);
                case 1:
                    return getString(R.string.title_section2).toUpperCase(l);
                case 2:
                    return getString(R.string.title_section3).toUpperCase(l);
            }
            return null;
        }
    }
	
    /** Called when the Activity exits
     */
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) 
	{
		super.onActivityResult(requestCode, resultCode, data);
		onSubmit();
	}
}
