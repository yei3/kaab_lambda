package com.yei3.oox.kaab_inventarios.util;

import org.json.simple.JSONArray;

public class Utils {
	public static String [] getStringArrayByJsonArray(JSONArray jsonArray) {
		String[] arr = new String[jsonArray.size()];
	    for(int i = 0; i < arr.length; i++) {
	        arr[i] = (String) jsonArray.get(i);
	    }
	    return arr;
	}
}
