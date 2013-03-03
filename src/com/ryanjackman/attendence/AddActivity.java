package com.ryanjackman.attendence;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class AddActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_add, menu);
		return true;
	}
	
	public void addItem(View view){
		TextView t = (TextView) findViewById(R.id.text_box);
		TextView s = (TextView) findViewById(R.id.subtext_box);
		
		MainActivity.add(new ListItem(t.getText().toString(), s.getText().toString()));
		finish();
	}

}
