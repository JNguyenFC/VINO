package com.yoloswag.vino;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

import com.yoloswag.vino.model.Entry;

public class EntryAction {

	public static void addEntry(Entry entry) {

		if(entry != null) {
			// Gets timestamp of Entry submission       
			Date today;
			DateFormat dateFormatter;
			dateFormatter = DateFormat.getDateInstance();
			today = new Date();
			entry.postDate = dateFormatter.format(today);

			EntryDispatch.addOrUpdate(entry);
		}
	}

	public static void updateEntry(Entry entry) {
		if(entry != null)
			EntryDispatch.addOrUpdate(entry);
	}

	public static List<Entry> getAllEntries() {
		return EntryDispatch.getAllEntries();
	}

	public static void destroy(Entry entry) {
		if(entry != null)
			EntryDispatch.destroy(entry);
	}

	public static void save(Entry entry) {
		if(entry != null)
			EntryDispatch.addOrUpdate(entry);
	}
}
