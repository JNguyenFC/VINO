/**
 * Filename:    Util.java
 * Team:		VINO
 * Description: 
 * Date:        8 Jun 2013
 **/

package com.yoloswag.vino.util;

import java.io.File;

import android.net.Uri;
import android.os.Environment;
import android.util.Log;

import com.yoloswag.vino.model.Entry;

public class Util 
{

	/** Create a file Uri for saving photo
	 */
	public static Uri getOutputMediaFileUri()
	{
		return Uri.fromFile(Util.getOutputMediaFile());
	}
	
	/** Create a File for saving photo
	 */
	public static File getOutputMediaFile() 
	{
	    File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
	              Environment.DIRECTORY_PICTURES), "VINO");

	    // Creates the storage directory if nonexistent
	    if (! mediaStorageDir.exists())
	    {
	        if (! mediaStorageDir.mkdirs())
	        {
	            Log.d("VINO", "failed to create directory");
	            return null;
	        }
	    }

	    // Creates filename for photo
	    String timeStamp = String.valueOf(Entry.getAll().length);
	    File mediaFile;
	    mediaFile = new File(mediaStorageDir.getPath() + File.separator + "IMG_"+ timeStamp + ".jpg");

	    return mediaFile;
	}
	
}
