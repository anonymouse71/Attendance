package com.ryanjackman.attendence;

import java.io.Serializable;
import java.util.ArrayList;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ListView;
import android.widget.Toast;

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
		listView.setTranscriptMode(ListView.TRANSCRIPT_MODE_NORMAL);

		this.registerForContextMenu(listView);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();

		switch (item.getItemId()) {
		case R.id.context_edit:
			Intent intent = new Intent(this, AddActivity.class);
			ListItem i = (ListItem) listView.getItemAtPosition(info.position);
			intent.putExtra("text", i.getText());
			intent.putExtra("subtext", i.getSubText());
			intent.putExtra("position", info.position);
			Toast.makeText(getBaseContext(), "" + i.getText() + i.getSubText(), Toast.LENGTH_SHORT).show();
			startActivity(intent);
			break;
		case R.id.context_delete:
			adapter.remove(adapter.getItem(info.position));
			break;
		default:
			return super.onContextItemSelected((MenuItem) item);
		}
		return false;
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.context_menu, menu);
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

		/*
		 * Intent intent = new Intent("com.ryanjackman.attendence.AddActivity");
		 * ListItem item = (ListItem) listView.getItemAtPosition(position);
		 * intent.putExtra("text", item.getText()); intent.putExtra("subText",
		 * item.getSubText()); intent.putExtra("position", position);
		 * view.getContext().startActivity(intent);
		 */
		// return true;
		// }
		// });
	}

	public void addItem(View view) {
		Intent i = new Intent(this, AddActivity.class);
		startActivity(i);
	}

	public void editItem(int position) {
		Intent i = new Intent(this, AddActivity.class);

		startActivity(i);
	}

	public static void add(ListItem i) {
		adapter.add(i);
	}

	public static void set(ListItem i, int position) {
		ListItem item = (ListItem) listView.getItemAtPosition(position);
		item.setText(i.getText());
		item.setSubText(i.getSubText());
		item.setSelected(i.isSelected());
	}
}
