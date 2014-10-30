package net.rmoreno.ideas;

import com.parse.Parse;

import android.app.Application;

public class IdeasApplication extends Application{
	@Override
	public void onCreate() {
		super.onCreate();
		  Parse.initialize(this, "8hKT09sn89EItYltifXfoonYY3bJJONzMQ3insqj", "FucWG96y9CZ5253ntmLNjb5AT2i5f2kAQMEWpns4");
		}
}
