package com.yoloswag.vino;

import java.util.List;

import com.yoloswag.vino.model.Entry;

public class EntryDispatch {

	public static void addOrUpdate(Entry entry) {
		EntryManager.addOrUpdate(entry);
	}

	public static List<Entry> getAllEntries() {
		return EntryManager.getAllEntries();
	}

	public static void destroy(Entry entry) {
		EntryManager.destroy(entry);
	}
}
