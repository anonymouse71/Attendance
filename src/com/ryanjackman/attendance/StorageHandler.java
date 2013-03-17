package com.ryanjackman.attendance;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import android.content.Context;
import android.os.Environment;
import android.util.Log;

public class StorageHandler {

	public static final String TOKEN = "*";

	public static void store(MainActivity main, ArrayList<ListItem> list, String file) throws IOException {

		FileOutputStream fOut = main.openFileOutput(file, Context.MODE_PRIVATE);
		OutputStreamWriter out = new OutputStreamWriter(fOut);

		for (ListItem t : list) {
			out.write(t.getText() + TOKEN + t.getSubText() + TOKEN + t.isSelected() + "\n");
		}

		out.flush();
		out.close();

		Log.i("Storage", "Saved!");
	}

	public static ArrayList<ListItem> retrieve(MainActivity main, String file) throws IOException {
		FileInputStream inputStream = null;
		InputStreamReader reader = null;
		BufferedReader in = null;
		ArrayList<ListItem> temp = new ArrayList<ListItem>();

		inputStream = main.openFileInput(file);
		reader = new InputStreamReader(inputStream);
		in = new BufferedReader(reader);

		Log.i("Retrieval", "File ready to read from.");

		String line;
		while ((line = in.readLine()) != null) {
			StringTokenizer tokenizer = new StringTokenizer(line, TOKEN);

			//Log.i("Retrieval", line);

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
				//Log.i("Retrieval", item.getText() + " " + item.getSubText());

			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		return temp;
	}

	public static void export(MainActivity main, final ArrayList<ListItem> list) {
		try {
			File sdCard = Environment.getExternalStorageDirectory();
			File dir = new File(sdCard.getAbsolutePath() + "/Lists");
			final File file = new File(dir, "filename.xls");
			dir.mkdirs();
			file.createNewFile();
			
			WritableWorkbook workbook = Workbook.createWorkbook(file);
			WritableSheet sheet = workbook.createSheet("Sheet 1", 0);
			
			for( int i = 0; i < list.size(); i++){
				Label textLabel = 		new Label(0, i, list.get(i).getText());
				Label subTextLabel = 	new Label(1, i, list.get(i).getSubText());
				Label boolLabel = 		new Label(2, i, list.get(i).isSelected() ? "true" : "false");
				sheet.addCell(textLabel);
				sheet.addCell(subTextLabel);
				sheet.addCell(boolLabel);
			}
			
			workbook.write();
			workbook.close();
			
			Log.i("Export", "Export complete.");
			
		} catch (IOException e) {
			Log.i("Export", "Error writing to workbook.");
			e.printStackTrace();
		} catch (WriteException e){
			Log.i("Export", "Error filling workbook.");
			e.printStackTrace();
		}
		
	}
}
