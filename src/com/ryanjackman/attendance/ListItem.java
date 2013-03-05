package com.ryanjackman.attendance;

public class ListItem {

	private String text = null;
	private String subText = null;
	private boolean selected = false;

	public ListItem(String text, String subText) {
		this.text = text;
		this.subText = subText;
	}

	public ListItem(String text, String subText, boolean selected) {
		this.text = text;
		this.subText = subText;
		this.selected = selected;
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
}
