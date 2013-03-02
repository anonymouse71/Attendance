package com.ryanjackman.attendence;

import java.io.Serializable;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements Serializable {

	private static final long serialVersionUID = -8275037861228299238L;

	private static AttendenceAdapter adapter = null;
	private static ListView listView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Clear focus from text field so keyboard does not open
		//findViewById(R.id.addText).clearFocus();

		if (listView == null)
			listView = (ListView) findViewById(R.id.listView1);
		if (adapter == null)
			displayListView();
		
		listView.setTranscriptMode(ListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);
		listView.setAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	public static void addItem(ListItem i){
		adapter.add(i);
		adapter.notifyDataSetChanged();
	}
	
	private void displayListView() {

		ArrayList<ListItem> itemList = new ArrayList<ListItem>();
		itemList.add(new ListItem("ONE", "one"));
		itemList.add(new ListItem("TWO", "two"));
		itemList.add(new ListItem("THREE", "three"));
		for (int i = 4; i < 20; i++)
			itemList.add(new ListItem("" + i, "" + i));

		// create an ArrayAdaptar from the String Array
		adapter = new AttendenceAdapter(this, R.layout.item_info, itemList);
		// Assign adapter to ListView
		listView.setAdapter(adapter);

		listView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

			}
		});
	}

	public void addItem(View view) {
		Intent i = new Intent(this, ItemActivity.class);
		startActivity(i);
		/*
		EditText editText1 = (EditText) findViewById(R.id.addText);
		if (!editText1.getText().toString().trim().equals("")) {
			adapter.itemList.add(new ListItem(editText1.getText().toString(),
					"Small sample of subtext for illustrative purposes."));
			adapter.notifyDataSetChanged();
			editText1.setText("");
		} else {
			Toast.makeText(getApplicationContext(), "Cannot add empty string.",
					Toast.LENGTH_SHORT).show();
		}*/
	}

	class AttendenceAdapter extends ArrayAdapter<ListItem> implements
			Serializable {

		private static final long serialVersionUID = -885419344671893557L;
		ArrayList<ListItem> itemList;

		public AttendenceAdapter(Context context, int textViewResourceId,
				ArrayList<ListItem> itemList) {
			super(context, textViewResourceId, itemList);
			this.itemList = itemList;
		}

		private class ViewHolder {
			TextView text;
			TextView subText;
			CheckBox cBox;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			ViewHolder holder = null;

			if (convertView == null) {
				LayoutInflater vi = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				convertView = vi.inflate(R.layout.item_info, null);

				holder = new ViewHolder();
				holder.text = (TextView) convertView.findViewById(R.id.text);
				holder.subText = (TextView) convertView
						.findViewById(R.id.subText);
				holder.cBox = (CheckBox) convertView
						.findViewById(R.id.checkBox1);
				convertView.setTag(holder);

				holder.cBox.setOnClickListener(new View.OnClickListener() {
					public void onClick(View v) {
					}
				});
			} else {
				holder = (ViewHolder) convertView.getTag();
			}

			ListItem item = itemList.get(position);
			holder.text.setText(item.getText());
			holder.subText.setText(item.getSubText());
			holder.cBox.setChecked(item.isSelected());
			holder.cBox.setTag(item);

			return convertView;
		}
	}
}
