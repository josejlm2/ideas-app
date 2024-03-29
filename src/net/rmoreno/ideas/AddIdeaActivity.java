package net.rmoreno.ideas;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
		requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
		setContentView(R.layout.activity_add_idea);	

		desc = (EditText) findViewById(R.id.description);
		title = (EditText) findViewById(R.id.title);
		add = (Button) findViewById(R.id.add);
		System.out.print(add);
		add.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				setProgressBarIndeterminate(true);
				//check and see all info filled
				if(desc.getText().toString().matches("")|| title.getText().toString().matches("")){
					Toast.makeText(AddIdeaActivity.this, "Fill in Empty Forms", Toast.LENGTH_SHORT).show();					
				}//end if that checks null fields
				
				else{
					//create new idea ParseObject
					ParseObject idea = new ParseObject("Ideas");
					idea.put("User", ParseUser.getCurrentUser().getUsername());
					idea.put("Title", title.getText().toString());
					idea.put("Description", desc.getText().toString());
					idea.saveInBackground(new SaveCallback() {
						//success or failure cases.
						@Override
						public void done(ParseException e) {
							if(e == null){
								setProgressBarIndeterminate(false);
								AlertDialog.Builder builder = new AlertDialog.Builder(AddIdeaActivity.this);
								builder.setMessage("Idea Saved")
									.setPositiveButton("Ok", new OnClickListener() {
										
										@Override
										public void onClick(DialogInterface dialog, int which) {
											Intent intent = new Intent(AddIdeaActivity.this, MainActivity.class);
											startActivity(intent);
											
										}
									})
									.setTitle("Success!");
								AlertDialog dialog = builder.create();
								dialog.show();
								
							}// end e == null if
							else{
								setProgressBarIndeterminate(false);
								AlertDialog.Builder builder = new AlertDialog.Builder(AddIdeaActivity.this);
								builder.setMessage(e.getMessage())
									.setPositiveButton("Ok", null)
									.setTitle("Error");
								
								AlertDialog dialog = builder.create();
								dialog.show();
							}//end else
								
							
						}
					});//end saveInBackground
				}//end else statement
				
			}//end onClick
			
		});//end onclick listener 
			
			
	}//end on create
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
