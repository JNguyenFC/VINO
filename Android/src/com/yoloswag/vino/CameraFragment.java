package com.yoloswag.vino;

import android.content.res.Configuration;
import android.hardware.Camera;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

public class CameraFragment extends Fragment {
    
    public static Camera getCameraInstance() {
        Camera c = null;
        try {
            c = Camera.open(0); // attempt to get a Camera instance
        }
        catch (Exception e){
            // Camera is not available (in use or does not exist)
        }
        return c; // returns null if camera is unavailable
    }
    
    private Camera mCamera;
    private CameraPreview mPreview;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Create an instance of Camera
        mCamera = getCameraInstance();
        
        Camera.Parameters p = mCamera.getParameters();
        
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
        
        mCamera.setParameters(p);

        // Create our Preview view and set it as the content of our activity.
        mPreview = new CameraPreview(getActivity(), mCamera);
        FrameLayout preview = (FrameLayout)getView().findViewById(R.id.camera_preview);
        preview.addView(mPreview);
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.camera, container, false);
    }
}
