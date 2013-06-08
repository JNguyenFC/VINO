/**
 * Filename:    CameraPreview.java
 * Team:        VINO
 * Description: 
 * Date:        08 Jun 2013
 **/

package com.yoloswag.vino;

import java.io.IOException;

import android.content.Context;
import android.hardware.Camera;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class CameraPreview extends SurfaceView implements SurfaceHolder.Callback 
{
	private SurfaceHolder mHolder;
	private Camera mCamera;

	// Constructor for CameraPreview object
	@SuppressWarnings("deprecation")
	public CameraPreview(Context context, Camera camera) 
	{
		super(context);
		mCamera = camera;

		// Install a SurfaceHolder
		mHolder = getHolder();
		
		// Callback so we get notified when the underlying surface is created
		// and destroyed
		mHolder.addCallback(this);
		
		// Deprecated setting, but required on Android versions prior to 3.0
		mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
	}

	/** Tells camera where to draw the preview after the Surface has been created
	 */
	public void surfaceCreated(SurfaceHolder holder) 
	{
		try 
		{
			mCamera.setPreviewDisplay(holder);
			mCamera.startPreview();
		} catch (IOException e) 
		{
			Log.d("OH NO", "Error setting camera preview: " + e.getMessage());
		}
	}

	/** Called immediately before Surface is destroyed
	 */
	public void surfaceDestroyed(SurfaceHolder holder) 
	{
		// Stop the preview since Surface will be destroyed
		if (mCamera != null) 
		{
		}
	}

	/** For camera preview rotation
	 */
	public void surfaceChanged(SurfaceHolder holder, int format, int w, int h) 
	{
		// Preview Surface nonexistent
		if (mHolder.getSurface() == null)
		{
			return;
		}

		// Stop camera preview before making changes
		try 
		{
			mCamera.stopPreview();
		} catch (Exception e)
		{
		}

		// Start preview with new settings
		try {
			mCamera.setPreviewDisplay(mHolder);
			mCamera.startPreview();

		} catch (Exception e){
			Log.d("OH NO", "Error starting camera preview: " + e.getMessage());
		}

		// Set camera properties first
		Camera.Parameters parameters = mCamera.getParameters();
		parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_AUTO);
		mCamera.setParameters(parameters);

		mCamera.startPreview();

		mCamera.autoFocus(null);
	}
	
	/** mCamera will be null when this function returns
	 */
	private void stopPreviewAndFreeCamera() 
	{
	    if (mCamera != null) 
	    {
	        /*
	          Call stopPreview() to stop updating the preview surface.
	        */
	        //mCamera.stopPreview();
	    
	        
	        // Important: Call release() to release the camera for use by other applications. 
	        // Applications should release the camera immediately in onPause() (and re-open() it in
	        // onResume());
	        mCamera.release();
	    
	        mCamera = null;
	    }
	}
}