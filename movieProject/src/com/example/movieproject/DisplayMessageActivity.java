package com.example.movieproject;

import com.example.movieproject.*;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

 
public class DisplayMessageActivity extends Activity 
{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.display_message_activity);
		
		Intent intent = getIntent();
		String results = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
		TextView textView = new TextView(this);
	    textView.setTextSize(40);
	    textView.setText(results);
	    setContentView(textView);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	/*public void sendFeedback(View button)
	{
		Intent intent = getIntent();
		String results = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
		TextView textView = new TextView(this);
	    textView.setTextSize(50);
	    textView.setText(results);
	    setContentView(textView);
	}*/

}
