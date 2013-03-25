package com.ryanjackman.attendance;

import java.io.File;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ExportActivity extends Activity {

	File sdCard = Environment.getExternalStorageDirectory();
	File dir = new File(sdCard.getAbsolutePath() + "/Lists");

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_export);

		TextView filenameBox = (TextView) findViewById(R.id.file_location);
		filenameBox.setText(dir.toString());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_export, menu);
		return true;
	}

	public void export(View view) {

		EditText filenameBox = (EditText) findViewById(R.id.filename_box);
		String name = filenameBox.getText().toString();
		File file = new File(dir, name);

		if (!name.endsWith(".xls")) {
			Toast.makeText(this, "Filename must end with \".xls\"",
					Toast.LENGTH_LONG).show();
			return;
		}

		StorageHandler.export(MainActivity.getAdapter().itemList, file);
		finish();
	}

}
