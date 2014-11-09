package net.rmoreno.ideas;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

public class IdeasApplication extends Application{
	
	public void onCreate() {
		  Parse.initialize(this, "8hKT09sn89EItYltifXfoonYY3bJJONzMQ3insqj", "FucWG96y9CZ5253ntmLNjb5AT2i5f2kAQMEWpns4");
		  
		  ParseObject testObject = new ParseObject("Test Object");
			testObject.put("foo", "bar");
			testObject.saveInBackground();
		}	
}
