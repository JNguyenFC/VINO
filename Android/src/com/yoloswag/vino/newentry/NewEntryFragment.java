	/**
 * 
 */
package com.yoloswag.vino.newentry;

import java.io.File;
import java.util.List;

import com.yoloswag.vino.CameraPreview;
import com.yoloswag.vino.R;
import com.yoloswag.vino.R.layout;
import com.yoloswag.vino.main.VINOActivity;
import com.yoloswag.vino.model.Entry;
import com.yoloswag.vino.viewentries.ViewLogEntryFragment;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.hardware.Camera;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.format.Time;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Toast;

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
	private CameraPreview mPreview;
	private Camera mCamera;
    
    @Override
    public void onCreate (Bundle savedInstanceState) 
    {
    	super.onCreate(savedInstanceState);
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_new_entry, container, false);
        View button = rootView.findViewById(R.id.new_entry_button);
        
        Button b = (Button) button;

        b.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg1) {
				// TODO Auto-generated method stub
				Entry e = new Entry();
				//EditText title = (EditText)rootView.findViewById(R.id.grapeAutoComplete);
				EditText location = (EditText)rootView.findViewById(R.id.location);
				EditText vintageYear = (EditText)rootView.findViewById(R.id.vintageYear);
				EditText category = (EditText)rootView.findViewById(R.id.category);
				RatingBar rating = (RatingBar)rootView.findViewById(R.id.rating);
				EditText comment = (EditText)rootView.findViewById(R.id.comments);

				//e.title = title.getText().toString();
				//Toast.makeText(getActivity(), e.title, Toast.LENGTH_SHORT).show();
				e.location = location.getText().toString();
				e.vintageYear = vintageYear.getText().toString();
				e.category = category.getText().toString();
				e.comment = comment.getText().toString();
				e.rating = (int)rating.getRating();
				
				e.save();
				((VINOActivity)getActivity()).onSubmit();
			}
        });

        return rootView;
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






