package net.rmoreno.ideas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.parse.ParseObject;
import com.parse.ParseQueryAdapter;
import com.parse.ParseUser;

public class MainActivity extends Activity {
	
	ParseQueryAdapter<ParseObject> mainAdapter;
	CustomAdapter customAdapter;
	ListView listView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		ParseUser currentUser = ParseUser.getCurrentUser();
		ParseObject = new ParseObject("Ideas");
		
		if(currentUser == null){
			Intent intent = new Intent(MainActivity.this, LoginActivity.class);
			startActivity(intent);
		}else {
			Log.i("USERNAME", currentUser.getUsername());
			
			  mainAdapter = new ParseQueryAdapter<ParseObject>(this, "Ideas");
			  mainAdapter.setTextKey("title");
			  
			  customAdapter = new CustomAdapter(MainActivity.this, object);
			  
			  listView = (ListView) findViewById(android.R.id.list);
			  listView.setAdapter(mainAdapter);
			  
			  Toast.makeText(MainActivity.this, "adapter worked", Toast.LENGTH_LONG).show();
		}	    
	}
	
	
	
	
	@Override
	protected void onResume() {
		super.onResume();
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		switch(id){
		case R.id.action_settings:
			return true;
		
		case R.id.add_idea:
			Intent add = new Intent(MainActivity.this, AddIdeaActivity.class);
			startActivity(add);
			break;
			
		case R.id.logout:
			ParseUser.logOut();
			Intent logout = new Intent(MainActivity.this, LoginActivity.class);
			startActivity(logout);
			break;
			
		}
		
		return super.onOptionsItemSelected(item);
	}
}
