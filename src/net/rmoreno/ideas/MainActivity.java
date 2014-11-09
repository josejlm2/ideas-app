package net.rmoreno.ideas;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.parse.ParseUser;

public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		ParseUser currentUser = ParseUser.getCurrentUser();
		
		if(currentUser == null){
			Intent intent = new Intent(MainActivity.this, LoginActivity.class);
			startActivity(intent);
		}else {
			Log.i("USERNAME", currentUser.getUsername());
		}
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
