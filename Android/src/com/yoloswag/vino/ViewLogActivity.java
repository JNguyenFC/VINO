package com.yoloswag.vino;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import com.yoloswag.vino.db.DatabaseManager;
import com.yoloswag.vino.main.VINOActivity;
import com.yoloswag.vino.model.Entry;
import com.yoloswag.vino.util.Util;
import com.yoloswag.vino.viewentries.ViewLogEntryFragment;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;

public class ViewLogActivity extends Activity {
	

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

		Button b = (Button) button;

        b.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg1) {
				// TODO Auto-generated method stub
				Entry e = new Entry();
				//EditText title = (EditText)rootView.findViewById(R.id.grapeAutoComplete);
				EditText location = (EditText)findViewById(R.id.location);
				EditText vintageYear = (EditText)findViewById(R.id.vintageYear);
				EditText category = (EditText)findViewById(R.id.category);
				EditText region = (EditText)findViewById(R.id.region);
				RatingBar rating = (RatingBar)findViewById(R.id.rating);
				EditText comment = (EditText)findViewById(R.id.comments);

				//e.title = title.getText().toString();
				//Toast.makeText(getActivity(), e.title, Toast.LENGTH_SHORT).show();

				//save picture 
				//String uri = Util.getOutputMediaFileUri().toString();// Getting URI
				
				e.location = location.getText().toString();
				e.vintageYear = vintageYear.getText().toString();
				e.category = category.getText().toString();
				e.region = region.getText().toString();
				e.comment = comment.getText().toString();
				e.rating = (int)rating.getRating();
				e.uri = getFilesDir() + String.valueOf(Entry.getAll().length)+".jpg";
				
				e.save();
				//((VINOActivity)this.getActivity()).onSubmit();
				
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
