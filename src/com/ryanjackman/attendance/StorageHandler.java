package com.ryanjackman.attendance;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

import android.content.Context;
import android.util.Log;

public class StorageHandler {

	public static boolean store(MainActivity main, ArrayList<ListItem> list, String file) throws IOException {

		FileOutputStream fOut = main.openFileOutput(file, Context.MODE_PRIVATE);
		OutputStreamWriter out = new OutputStreamWriter(fOut);

		for (ListItem t : list) {
			out.write(t.getText() + "*" + t.getSubText() + "*" + t.isSelected() + "\n");
		}

		out.flush();
		out.close();

		return false;
	}

	public static ArrayList<ListItem> retrieve(MainActivity main, String file) throws IOException {
		FileInputStream inputStream = null;
		InputStreamReader reader = null;
		BufferedReader in = null;
		ArrayList<ListItem> temp = new ArrayList<ListItem>();

		inputStream = main.openFileInput(file);
		reader = new InputStreamReader(inputStream);
		in = new BufferedReader(reader);
		
		Log.i("File Reading stuff", "file reader set up");

		String line;
		while ((line = in.readLine()) != null) {
			StringTokenizer tokenizer = new StringTokenizer(line, "*");
			
			Log.i("File Reading stuff", line);

			String text = null;
			String subtext = null;
			boolean checked = false;
			try {
				text = tokenizer.nextToken();
				subtext = tokenizer.nextToken();
				String bool = tokenizer.nextToken();
				checked = bool.equalsIgnoreCase("true");
				ListItem item = new ListItem(text, subtext, checked);
				temp.add(item);
				Log.i("File Reading stuff", item.getText() + " " + item.getSubText());
				
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		return temp;
	}
}
