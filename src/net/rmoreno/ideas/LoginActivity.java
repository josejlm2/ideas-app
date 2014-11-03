package net.rmoreno.ideas;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class LoginActivity extends ActionBarActivity {
	
	EditText mUsername, mPassword;
	Button mLogin;
	TextView mSignup;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//Allows us to use progress circle
		requestWindowFeature(Window.FEATURE_PROGRESS);
		setContentView(R.layout.activity_login);
		
		//hides action bar
		getActionBar().hide();
		
		mUsername = (EditText) findViewById(R.id.username);
		mPassword = (EditText) findViewById(R.id.password);
		mLogin = (Button) findViewById(R.id.sign_in);
		mSignup = (TextView) findViewById(R.id.sign_up);
		
		//goes to signup page when clicked
		mSignup.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
				startActivity(intent);
				
			}
		});
		
		//logs user in when clicked
		mLogin.setOnClickListener(new View.OnClickListener() {
		
			String username = mUsername.getText().toString().trim();
			String password = mPassword.getText().toString().trim();

			//checks to make sure user exists or doesn't exist to sign them in
			@Override
			public void onClick(View v) {
				ParseUser.logInInBackground(username, password, new LogInCallback() {
					
					//if user exists, do this
					@Override
					public void done(ParseUser user, ParseException e) {
						if(user != null){
							Intent intent = new Intent(LoginActivity.this, MainActivity.class);
							
							//makes sure you can't press "back" in the next activity and come back to Login page
							intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
							intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
							startActivity(intent);
						}
						//if user doesn't exist, show error message
						else{
							AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
							builder.setMessage(e.getMessage())
							.setPositiveButton("Ok", null)
							.setTitle("Error Message");
							AlertDialog dialog = builder.create();
							dialog.show();
						}
						
					}
				});
				
			}
		});
		
	}
	

	
}
