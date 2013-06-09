package com.yoloswag.vino.model.entry;

import java.util.List;


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
