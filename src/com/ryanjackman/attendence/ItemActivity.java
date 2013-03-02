package com.ryanjackman.attendence;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class ItemActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_item);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_item, menu);
		return true;
	}
	
	public void addItem(View v) {
		
		TextView t = (TextView) findViewById(R.id.textField);
		TextView s = (TextView) findViewById(R.id.subTextField);

		MainActivity.addItem(new ListItem(t.getText().toString(), s.getText().toString()));
		this.finish();
	}
}
