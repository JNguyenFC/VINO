/**
 * 
 */
package com.yoloswag.vino;

import java.io.File;
import java.util.List;

import android.content.Context;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageView;

/**
 * @author tiffany
 *
 */
public class NewEntryFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    public static final String ARG_SECTION_NUMBER = "section_number";
    ExpandableListView exv;
	private Bitmap mImageBitmap;
	private ImageView mImageView;
    
    @Override
    public void onCreate (Bundle savedInstanceState) 
    {
    	super.onCreate(savedInstanceState);
    	dispatchTakePictureIntent(15);
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
    	//android:Id = "+id/newEntryButton";
        View rootView = inflater.inflate(R.layout.fragment_new_entry, container, false);
        /*View button = rootView.findViewById(R.id.newEntryButton);
        Button b = (Button) button;
        OnClickListener ocl = new OnClickListener(){
        	@Override
        	public void onClick(View v){
        		
        	}
        };
        b.setOnClickListener((android.view.View.OnClickListener) ocl);*/
        return rootView;
    }
    
    /** An intent to take the photo */
    private void dispatchTakePictureIntent(int actionCode) 
    {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(takePictureIntent, actionCode);
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






