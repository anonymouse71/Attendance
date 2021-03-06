package com.ryanjackman.attendance;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

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

		if (t.getText().toString().trim().equals("")) {
			Toast.makeText(this, "No Title Entered!", Toast.LENGTH_LONG).show();
			return;
		}
		if (t.getText().toString().contains(StorageHandler.TOKEN)
				|| s.getText().toString().contains(StorageHandler.TOKEN)) {
			Toast.makeText(this,
					"You may not include \"" + StorageHandler.TOKEN + "\" in either Title, or Additional Information",
					Toast.LENGTH_LONG).show();
			return;
		}

		if (position != -1) {
			MainActivity.set(new ListItem(t.getText().toString(), s.getText().toString()), position);
		} else
			MainActivity.add(new ListItem(t.getText().toString(), s.getText().toString()));
		finish();
	}
}
