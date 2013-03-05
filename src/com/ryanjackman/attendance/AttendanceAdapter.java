package com.ryanjackman.attendance;

import java.io.Serializable;
import java.util.ArrayList;

import com.ryanjackman.attendance.R;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

class AttendanceAdapter extends ArrayAdapter<ListItem> implements Serializable {

	private static final long serialVersionUID = -885419344671893557L;
	ArrayList<ListItem> itemList;
	private Context context;

	public AttendanceAdapter(Context context, int textViewResourceId,
			ArrayList<ListItem> itemList) {
		super(context, textViewResourceId, itemList);
		this.itemList = itemList;
		this.context = context;
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
			LayoutInflater inflater = ((Activity) context).getLayoutInflater();
			convertView = inflater.inflate(R.layout.item_info, null);

			holder = new ViewHolder();
			holder.text = (TextView) convertView.findViewById(R.id.list_text);
			holder.subText = (TextView) convertView
					.findViewById(R.id.list_subtext);
			holder.cBox = (CheckBox) convertView
					.findViewById(R.id.list_checkbox);
			convertView.setTag(holder);

			holder.cBox.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					CheckBox checkbox = (CheckBox) v;
					ListItem item = (ListItem) checkbox.getTag();
					item.setSelected(checkbox.isChecked());
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