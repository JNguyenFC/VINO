package com.yoloswag.vino;

import java.util.List;

import com.yoloswag.vino.db.DatabaseManager;
import com.yoloswag.vino.model.Entry;

public class EntryManager {

	public static void addOrUpdate(Entry entry) {
		if(entry.wine != null)
			DatabaseManager.getInstance().getWineDao().updateWine(entry.wine);
		DatabaseManager.getInstance().getEntryDao().updateEntry(entry);
	}

	public static List<Entry> getAllEntries() {
		return DatabaseManager.getInstance().getEntryDao().getAllEntries();
	}

	public static void destroy(Entry entry) {
		DatabaseManager.getInstance().getEntryDao().deleteEntry(entry);
	}
}
