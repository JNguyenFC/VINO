/**
 * 
 */
package com.yoloswag.vino.newentry;

import java.util.List;

import com.yoloswag.vino.CameraPreview;
import com.yoloswag.vino.R;
import com.yoloswag.vino.model.Entry;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.hardware.Camera;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * @author tiffany
 *
 */
public class NewEntryFragment extends Activity {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    public static final String ARG_SECTION_NUMBER = "section_number";
    ExpandableListView exv;
	private Bitmap mImageBitmap;
	private ImageView mImageView;
	private CameraPreview mPreview;
	private Camera mCamera;
    
    @Override
    public void onCreate (Bundle savedInstanceState) 
    {
    	super.onCreate(savedInstanceState);
    	//dispatchTakePictureIntent(15);
    }
    
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        //final View rootView = inflater.inflate(R.layout.camera, container, false);
        final View rootView = inflater.inflate(R.layout.fragment_new_entry, container, false);
        View button = rootView.findViewById(R.id.new_entry_button);
        
        Button b = (Button) button;
        //OnClickListener ocl = ;
        b.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg1) {
				// TODO Auto-generated method stub
				EditText title = (EditText)rootView.findViewById(R.id.grapeAutoComplete);
				Entry e = new Entry();
				e.title = title.getText().toString();
				//Toast.makeText(getActivity(), e.title, Toast.LENGTH_SHORT).show();
				e.save();
			}
        });

        return rootView;
    }
    
    

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
        // The activity is about to be destroyed.
    }
    
    /** An intent to take the photo */
    private void dispatchTakePictureIntent(int actionCode) 
    {
        //Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //startActivityForResult(takePictureIntent, actionCode);
    }
    
    /** Checking if the intent is even available */
    public static boolean isIntentAvailable(Context context, String action) 
    {
        final PackageManager packageManager = context.getPackageManager();
        final Intent intent = new Intent(action);
        List<ResolveInfo> list =
                packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
        return list.size() > 0;
    }
    
    /** Getting the image */
    private void handleSmallCameraPhoto(Intent intent) 
    {
        Bundle extras = intent.getExtras();
        mImageBitmap = (Bitmap) extras.get("data");
        mImageView.setImageBitmap(mImageBitmap);
    }
    

}






