package net.rmoreno.ideas;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class SignupActivity extends ActionBarActivity {
	EditText mName, mUsername, mEmail, mPassword;
	Button mSignup, mCancel;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
		setContentView(R.layout.activity_signup);
		
		mName = (EditText) findViewById(R.id.name);
		mUsername = (EditText) findViewById(R.id.username);
		mEmail = (EditText) findViewById(R.id.email);
		mPassword = (EditText) findViewById(R.id.password);
		mSignup = (Button) findViewById(R.id.signup_button);
		
		
		
		mSignup.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				final String username = mName.getText().toString().trim();
				final String email = mEmail.getText().toString().trim();
				final String password = mPassword.getText().toString().trim();
				
				if(username.isEmpty() || email.isEmpty() || password.isEmpty()){
					AlertDialog.Builder builder = new AlertDialog.Builder(SignupActivity.this);
					builder.setMessage("Item missing in Signup Fields")
						.setPositiveButton("Ok", null)
						.setTitle("Error Message");
					AlertDialog dialog = builder.create();
					dialog.show();					
				}
				else{
					
					setProgressBarIndeterminateVisibility(true);
					
					ParseUser user = new ParseUser();
					user.setUsername(username);
					user.setPassword(password);
					user.setEmail(email);
					user.signUpInBackground(new SignUpCallback() {
						
						@Override
						public void done(ParseException e) {

							if(e == null){
								Intent intent = new Intent(SignupActivity.this, MainActivity.class);
								intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
								intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
								startActivity(intent);
								
							}
							else{
								AlertDialog.Builder builder = new AlertDialog.Builder(SignupActivity.this);
								builder.setMessage(e.getMessage())
									.setTitle("Error Message")
									.setPositiveButton("Ok", null);
								AlertDialog dialog = builder.create();
								dialog.show();
							}
							
						}
					});
				}
				
			}
		});
		
		
	}

	
}
