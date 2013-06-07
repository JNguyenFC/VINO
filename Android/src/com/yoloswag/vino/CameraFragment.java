/**
 * Filename:    CameraFragment.java
 * Team:        VINO
 * Description: 
 * Date:        08 Jun 2013
 **/

package com.yoloswag.vino;

import java.io.FileOutputStream;

import com.yoloswag.vino.model.Entry;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.FrameLayout;

public class CameraFragment extends Fragment 
{
	public static final int MEDIA_TYPE_IMAGE = 1;
	public static final int MEDIA_TYPE_VIDEO = 2;
	protected static final int K_STATE_FROZEN = 0;
	protected static final int K_STATE_PREVIEW = 0;
	protected static final int K_STATE_BUSY = 0;
	protected static final String TAG = null;
	
	private Camera mCamera;
	private CameraPreview mPreview;

	public static Camera getCameraInstance() 
	{
		Camera c = null;
		// Try to get a Camera object
		try 
		{
			c = Camera.open();
		}
		// Camera is not available (in use or does not exist)
		catch (Exception e)
		{
		}
		// Null is returned if Camera does not exist
		return c;
	}

	@SuppressWarnings("deprecation")
	@Override
	public void onActivityCreated(Bundle savedInstanceState) 
	{
		super.onActivityCreated(savedInstanceState);

		// Create an instance of Camera
		mCamera = getCameraInstance();

		Camera.Parameters p = mCamera.getParameters();

		// Set orientation
		if (Integer.parseInt(Build.VERSION.SDK) >= 8)
			mCamera.setDisplayOrientation(90);
		else
		{
			if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
			{
				p.set("orientation", "portrait");
				p.set("rotation", 90);
			}
			if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
			{
				p.set("orientation", "landscape");
				p.set("rotation", 90);
			}
		}

		// Changes the setting for this Camera service
		mCamera.setParameters(p);

		// Create our Preview view and set it as the content of our activity.
		mPreview = new CameraPreview(getActivity(), mCamera);
		FrameLayout preview = (FrameLayout)getView().findViewById(R.id.camera_preview);
		preview.addView(mPreview);
	}

	private PictureCallback mPicture = new PictureCallback() 
	{
		// Called when image data is available after a picture is taken
		@Override
		public void onPictureTaken(byte[] data, Camera camera) 
		{
			try
			{
				BitmapFactory.Options opts = new BitmapFactory.Options();
				Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length,opts);
				bitmap = Bitmap.createScaledBitmap(bitmap, 480, 480, false);

				Matrix matrix = new Matrix();
				matrix.postRotate(90);
				bitmap = Bitmap.createBitmap(bitmap, 0, 0, 
						bitmap.getWidth(), bitmap.getHeight(), 
						matrix, true);
				
				// Set photo name
				String name = getActivity().getFilesDir() + String.valueOf(Entry.getAll().length) + ".jpg";
				System.out.println("saving to " + name);
			    FileOutputStream out = new FileOutputStream(name);
			    bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
			    out.flush();
			    out.close();
				System.out.println("done saving to " + name);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	};

	/** Creates and returns the new Entry's camera View 
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) 
	{
		final View rootView = inflater.inflate(R.layout.camera, container, false);
		
		// New Entry buttons
		View accept = rootView.findViewById(R.id.button_accept);
		View decline = rootView.findViewById(R.id.button_decline);
		View button = rootView.findViewById(R.id.button_capture);
		
		final Button a = (Button) accept;
		final Button d = (Button)decline;
		final Button b = (Button) button;
		
		a.setVisibility(View.INVISIBLE);
		d.setVisibility(View.INVISIBLE);

		// Set Accept and Decline buttons for photo preview after photo is taken
		b.setOnClickListener(new OnClickListener() 
		{
			@Override
			public void onClick(View arg1) 
			{
				mCamera.takePicture(null, null, mPicture);
				a.setVisibility(View.VISIBLE);
				d.setVisibility(View.VISIBLE);
				b.setVisibility(View.INVISIBLE);
			}
		});

		// Set New Entry page to display after Accept is tapped
		a.setOnClickListener(new OnClickListener() 
		{
			@Override
			public void onClick(View arg1) 
			{
				// Checks if SD card exists
				Environment.getExternalStorageState();
				
				// Calling NewEntryActivity
				Intent intent = new Intent(getActivity(), NewEntryActivity.class);
				getActivity().startActivityForResult(intent, 1);

				// Hide Accept and Decline buttons, show Submit button
				a.setVisibility(View.INVISIBLE);
				d.setVisibility(View.INVISIBLE);
				b.setVisibility(View.VISIBLE);
			}
		});

		// Set camera to display after Decline is tapped
		d.setOnClickListener(new OnClickListener() 
		{
			@Override
			public void onClick(View arg1) 
			{
				// Hide Accept and Decline buttons, show Capture button
				a.setVisibility(View.INVISIBLE);
				d.setVisibility(View.INVISIBLE);
				b.setVisibility(View.VISIBLE);

				// Restart camera
				int mPreviewState = 0;
				switch(mPreviewState) 
				{
					case K_STATE_FROZEN:
						mCamera.startPreview();
						mPreviewState = K_STATE_PREVIEW;
						break;
					default:
						PictureCallback rawCallback = null;
						mCamera.takePicture( null, rawCallback, null);
						mPreviewState = K_STATE_BUSY;
						break;
				}
			}
		});  

		return rootView;
	}
}
