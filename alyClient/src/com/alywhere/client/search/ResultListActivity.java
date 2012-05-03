package com.alywhere.client.search;

import com.alywhere.client.R;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.alywhere.client.data.Item;
import com.alywhere.client.data.StoreItem;

public class ResultListActivity extends ListActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		SimpleAdapter adapter = new SimpleAdapter(this, getData(),
				R.layout.list_store_item,
				new String[] { "img", "name", "info" }, new int[] { R.id.img,
						R.id.name, R.id.info });
		setListAdapter(adapter);
	}

	private List<Map<String, Object>> getData(List<Item> resultList) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = null;
		
		for (Item item : resultList) {
			if (item instanceof StoreItem) {
				map = new HashMap<String, Object>(); 
				map.put
			}
		}
		return list;
	}

}
