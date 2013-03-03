package com.ryanjackman.attendence;

import java.io.Serializable;
import java.util.ArrayList;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class MainActivity extends Activity implements Serializable {

	private static final long serialVersionUID = -8275037861228299238L;

	private static AttendanceAdapter adapter = null;
	private static ListView listView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		listView = (ListView) findViewById(R.id.list_view);
		if (adapter == null)
			displayListView();
		listView.setAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	private void displayListView() {

		ArrayList<ListItem> itemList = new ArrayList<ListItem>();
		itemList.add(new ListItem("ONE", "one"));
		itemList.add(new ListItem("TWO", "two"));
		itemList.add(new ListItem("THREE", "three"));
		for (int i = 4; i < 20; i++)
			itemList.add(new ListItem("" + i, "" + i));

		// create an ArrayAdaptar from the String Array
		adapter = new AttendanceAdapter(this, R.layout.item_info, itemList);
		// Assign adapter to ListView
		listView.setAdapter(adapter);

		listView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
			}
		});
	}

	public void addItem(View view) {
		Intent i = new Intent(this, AddActivity.class);
		startActivity(i);
	}
	
	public static void add(ListItem i){
		adapter.add(i);
	}
}
