package com.example.movieproject;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.example.movieproject.*;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends Activity {
    public final static String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		System.out.println("Blah");
		// Get the button and set listener
		//findViewById(R.id.ButtonSendFeedback).setOnClickListener(Feedback);
	}
	
	/*final OnClickListener Feedback = new OnClickListener(){
		public void onClick(final View v) 
		{
        	//Inform the user the button has been clicked
        	sendFeedback(v);               
    	}
	};*/

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void sendFeedback(View View)
	{
		
		System.out.println("Blah");
		//ButtonSendFeedback.performClick();
		final EditText nameField = (EditText) findViewById(R.id.genre);  
		String genre = nameField.getText().toString();  
		
		final EditText emailField = (EditText) findViewById(R.id.rating);  
		String rating = emailField.getText().toString();
		
		final EditText feedbackField = (EditText) findViewById(R.id.zipcode);  
		String zipcode = feedbackField.getText().toString(); 
		final EditText Date = (EditText) findViewById(R.id.da);
		String date = Date.getText().toString();                          
		//String newDate = date.charAt(0) + date.charAt(1) + "-" + date.charAt(3) + date.charAt(4);
		//String url = "http://data.tmsapi.com/v1/movies/showings?startDate=2014-" + newDate + "&zip=" + zipcode + "&api_key=5xmxecwv3kw4z4ndb5d5kbg6";
		String url =  "http://data.tmsapi.com/v1/movies/showings?startDate=2014-10-10&zip=43016&api_key=5xmxecwv3kw4z4ndb5d5kbg6";
		try
		{
			URL obj = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) obj.openConnection();
			conn.setReadTimeout(5000);
			conn.addRequestProperty("Accept-Language", "en-US,en;q=0.8");
			boolean redirect = false;
			 
			// normally, 3xx is redirect
			int status = conn.getResponseCode();
			if (status != HttpURLConnection.HTTP_OK) {
				if (status == HttpURLConnection.HTTP_MOVED_TEMP
					|| status == HttpURLConnection.HTTP_MOVED_PERM
						|| status == HttpURLConnection.HTTP_SEE_OTHER) 
				redirect = true;
			}
			BufferedReader in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
			//(in) contains all the movies from the API
			String inputLine;
			StringBuffer html = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				html.append(inputLine);
			}
			ArrayList<Movie> movies = new ArrayList<Movie>();
			JSONObject object = new JSONObject(inputLine);
			ArrayList<String> titles = new ArrayList<String>();
			JSONArray array = object.getJSONArray("title");
			for(int i = 0 ; i < array.length() ; i++){
			    titles.add(array.getJSONObject(i).getString("title"));
			}
			ArrayList<String> ratings = new ArrayList<String>();
			JSONArray array1 = object.getJSONArray("ratings");
			for(int i = 0 ; i < array.length() ; i++){
				ratings.add(array1.getJSONObject(i).getString("code"));
			}
			ArrayList<String> genres = new ArrayList<String>();
			JSONArray array2 = object.getJSONArray("genres");
			for(int i = 0; i < array2.length(); i++){
				genres.add(array2.getJSONObject(i).getString("genres"));
			}
			ArrayList<String> theaters = new ArrayList<String>();
			JSONArray array3 = object.getJSONArray("showTimes");
			for(int i = 0; i < array3.length(); i++){
				theaters.add(array3.getJSONObject(i).getString("name"));
			}
			ArrayList<String> showTimes = new ArrayList<String>();
			JSONArray array4 = object.getJSONArray("showTimes");
			for(int i = 0; i < array4.length(); i++){
				showTimes.add(array4.getJSONObject(i).getString("dateTime"));
			}
			for(int i = 0; i < titles.size(); i++)
			{
				Movie newMovie = new Movie(titles.get(i), genres.get(i), ratings.get(i), theaters.get(i), showTimes.get(i));
				movies.add(newMovie);
			}
			in.close();
			ArrayList<Movie> results = new ArrayList<Movie>();
			for(int i = 0; i < movies.size(); i++)
			{
				String gen = (movies.get(i).genre).toLowerCase();
				String ugen = genre.toLowerCase();
				if(gen.equals(ugen))
				{
					results.add(movies.get(i));
				}
			}
			ArrayList<Movie> newResults = new ArrayList<Movie>();
			for(int i = 0; i < results.size(); i++)
			{
				if(results.get(i).rating.equals(rating))
				{
					newResults.add(results.get(i));
				}
			}
			Intent intent = new Intent(this, DisplayMessageActivity.class);
			String finalValue = newResults.toString();
			System.out.println(finalValue);
			intent.putExtra(EXTRA_MESSAGE, finalValue);
		    startActivity(intent);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
/*	public void sendFeedback(View button)
	{  
		final EditText nameField = (EditText) findViewById(R.id.editText2);  
		String genre = nameField.getText().toString();  
		
		final EditText emailField = (EditText) findViewById(R.id.editText1);  
		String rating = emailField.getText().toString();
		
		final EditText feedbackField = (EditText) findViewById(R.id.search);  
		String zipcode = feedbackField.getText().toString();  
	} */ 
}
