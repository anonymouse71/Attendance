package com.ryanjackman.attendence;

import android.os.Parcel;
import android.os.Parcelable;

public class ListItem implements Parcelable {
	
	private String text = null;
	private String subText = null;
	private boolean selected = false;
	
	public ListItem(String text, String subText){
		this.text = text;
		this.subText = subText;
	}

	public ListItem(Parcel source) {
		text = source.readString();
		subText = source.readString();
		selected = source.readByte() == 1;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getSubText() {
		return subText;
	}

	public void setSubText(String subText) {
		this.subText = subText;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	@Override
	public int describeContents() {
		return this.hashCode();
	}

	@Override
	public void writeToParcel(Parcel parcel, int flags) {
		parcel.writeString(text);
		parcel.writeString(subText);
		parcel.writeByte((byte) (selected ? 1 : 0)); 
		
	}
	
	public class ListItemCreator implements Parcelable.Creator<ListItem> {
	      public ListItem createFromParcel(Parcel source) {
	            return new ListItem(source);
	      }
	      public ListItem[] newArray(int size) {
	            return new ListItem[size];
	      }
	}
}
