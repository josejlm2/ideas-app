package net.rmoreno.ideas;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.Button;
import android.widget.EditText;

public class SignupActivity extends ActionBarActivity {
	EditText name, username, email, password;
	Button ok, cancel;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_signup);
		
		
	}

	
}
