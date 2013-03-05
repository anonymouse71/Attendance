package com.ryanjackman.attendance;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class AddActivity extends Activity {
	
	private int position;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add);

		handleIntent(getIntent());		
	}

	private void handleIntent(Intent intent) {
		String text = null, subText = null;
		position = -1;
		Bundle extras = intent.getExtras();
		if (extras != null) {
			text = extras.getString("text");
			subText = extras.getString("subtext");
			position = extras.getInt("position");
		} else 
			return;

		EditText t = (EditText) findViewById(R.id.text_box);
		EditText s = (EditText) findViewById(R.id.subtext_box);
		if (text != null & subText != null) {
			t.setText(text);
			s.setText(subText);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_add, menu);
		return true;
	}

	public void save(View view) {
		EditText t = (EditText) findViewById(R.id.text_box);
		EditText s = (EditText) findViewById(R.id.subtext_box);

		if (position != -1) {
			MainActivity.set(new ListItem(t.getText().toString(), s.getText().toString()), position);
		} else
			MainActivity.add(new ListItem(t.getText().toString(), s.getText().toString()));
		finish();
	}

}
