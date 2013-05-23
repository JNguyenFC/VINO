/**
 * 
 */
package com.yoloswag.vino.newentry;

import com.yoloswag.vino.CameraFragment;
import com.yoloswag.vino.R;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;

/**
 * @author tiffany
 *
 */
public class ConfirmationDialogFragment extends DialogFragment {
	public static ConfirmationDialogFragment newInstance(int title) {
        ConfirmationDialogFragment frag = new ConfirmationDialogFragment();
        Bundle args = new Bundle();
        args.putInt("title", title);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        int title = getArguments().getInt("title");

        return new AlertDialog.Builder(getActivity())
                //.setIcon(R.drawable.alert_dialog_icon)
                .setTitle(title)
                .setPositiveButton(R.id.button_yes,
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            //((FragmentAlertDialog)getActivity()).doPositiveClick();
                        }
                    }
                )
                .setNegativeButton(R.id.button_no,
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            //((FragmentAlertDialog)getActivity()).doNegativeClick();
                        }
                    }
                )
                .create();
    }

    void showDialog() {
        DialogFragment newFragment = ConfirmationDialogFragment.newInstance(
                R.id.confirmation_string);
        newFragment.show(getFragmentManager(), "dialog");
    }

    public void doPositiveClick() {
        // Do stuff here.
        Intent myIntent = new Intent(getActivity(), NewEntryFragment.class);
        getActivity().startActivity(myIntent); 
        Log.i("FragmentAlertDialog", "Positive click!");
    }

    public void doNegativeClick() {
        // Do stuff here.
    	Intent myIntent = new Intent(getActivity(), CameraFragment.class);
        getActivity().startActivity(myIntent);
        Log.i("FragmentAlertDialog", "Negative click!");
    }
	    
}
