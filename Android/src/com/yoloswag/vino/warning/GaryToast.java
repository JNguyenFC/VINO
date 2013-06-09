package com.yoloswag.vino.warning;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.yoloswag.vino.R;

public class GaryToast {

	public static void show(String message, Context context) {
		View toastView = LayoutInflater.from(context).inflate(R.layout.toast, null);
		ImageView imageView = (ImageView) toastView.findViewById(R.id.garytoast);
		imageView.setImageResource(R.drawable.gary_vector);
		TextView textView = (TextView) toastView.findViewById(R.id.text);
		textView.setText(message);
		Toast toast = new Toast(context);
		toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
		toast.setDuration(Toast.LENGTH_SHORT);
		toast.setView(toastView);
		toast.show();
	}
}
