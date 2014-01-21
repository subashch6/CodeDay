package com.example.movieproject;

public class Movie {
	public String title;
	public String genre;
	public String rating;
	public String theater;
	public String showString;
	public double showTime;
	
	public Movie(String t, String g, String r, String th, String s)
	{
		title = t;
		genre = g;
		rating = r;
		theater = th;
		String temp = s.replace(':', '.');
		showTime = Double.parseDouble(temp);
	}
	
}
