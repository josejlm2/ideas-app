package net.rmoreno.ideas;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;

public class AddIdeaActivity extends ActionBarActivity {

	//EditText title = (EditText) findViewById(R.id.title);
	EditText desc, title;
	Button add;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_idea);	

		desc = (EditText) findViewById(R.id.description);
		title = (EditText) findViewById(R.id.title);
		add = (Button) findViewById(R.id.add);
		System.out.print(add);
		add.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				if(desc.getText().toString().matches("")  || title.getText().toString().matches("")){
					AlertDialog.Builder builder = new AlertDialog.Builder(AddIdeaActivity.this);
					builder.setMessage("Missing an input field!")
					.setPositiveButton("Ok", null)
					.setTitle("Error Message");
					AlertDialog dialog = builder.create();
					dialog.show();
				} else{

					String descString = desc.getText().toString();
					String titleString = title.getText().toString();

					ParseUser user = ParseUser.getCurrentUser();
					ParseObject idea = new ParseObject("New Idea");
					idea.put("User", user);
					idea.put("Title", titleString);
					idea.put("Description", descString);
					idea.saveInBackground( new SaveCallback() {

						@Override
						public void done(ParseException arg0) {
							// TODO Auto-generated method stub
							AlertDialog.Builder builder = new AlertDialog.Builder(AddIdeaActivity.this);
							builder.setMessage("Idea Added")
							.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog, int which) {
									// TODO Auto-generated method stub
									Intent intent = new Intent(AddIdeaActivity.this, MainActivity.class);
									startActivity(intent);

								}
							} )
							.setTitle("Success");
							AlertDialog dialog = builder.create();
							dialog.show();

						}
					});


				}				
			}
		});		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_idea, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		return super.onOptionsItemSelected(item);
	}
}
