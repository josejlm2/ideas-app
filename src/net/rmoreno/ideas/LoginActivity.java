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
		requestWindowFeature(Window.FEATURE_PROGRESS);
		setContentView(R.layout.activity_login);
		
		mUsername = (EditText) findViewById(R.id.username);
		mPassword = (EditText) findViewById(R.id.password);
		mLogin = (Button) findViewById(R.id.sign_in);
		mSignup = (TextView) findViewById(R.id.sign_up);
		
		mSignup.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
				startActivity(intent);
				
			}
		});
		mLogin.setOnClickListener(new View.OnClickListener() {
		
			String username = mUsername.getText().toString().trim();
			String password = mPassword.getText().toString().trim();
		
			@Override
			public void onClick(View v) {
				ParseUser.logInInBackground(username, password, new LogInCallback() {
					
					@Override
					public void done(ParseUser user, ParseException e) {
						if(user != null){
							Intent intent = new Intent(LoginActivity.this, MainActivity.class);
							intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
							intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
							startActivity(intent);
						}
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
