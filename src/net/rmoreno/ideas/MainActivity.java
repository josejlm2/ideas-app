package net.rmoreno.ideas;

import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

public class MainActivity extends ListActivity {

	List<ParseObject> mIdeas;
	
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
	protected void onResume() {
		super.onResume();
		
		ParseQuery<ParseObject> query = ParseQuery.getQuery("Ideas");
		query.setLimit(1000);
		query.whereNotEqualTo("title", null);
		query.findInBackground( new FindCallback<ParseObject>() {
			
			@Override
			public void done(List<ParseObject> ideas, ParseException e) {
				// TODO Auto-generated method stub
				ideas = mIdeas;
				
				ArrayList<ParseObject> idea = new ArrayList<ParseObject>();
				
				int i = 0;
				for(ParseObject id : ideas){
					
					idea.add(id.getParseObject("title"));
					i++;
				}
				ArrayAdapter<ParseObject> adapter = new ArrayAdapter<ParseObject>(MainActivity.this, R.id.list_item, idea);
				setListAdapter(adapter);
			}
		});
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
