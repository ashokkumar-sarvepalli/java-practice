package learning.map.sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class SortMapValues {

	public static void main(String[] args) {
		
		
		Map<Integer,String> myMap = new HashMap<Integer,String>();
		
		myMap.put(25,"Devi");
		myMap.put(29, "Ashok");
		myMap.put(1, "Thushith");
		myMap.put(47, "Lakshmi");
		
		List<Map.Entry<Integer,String>> entryList = new ArrayList<>(myMap.entrySet());
		
		Collections.sort(entryList, new Comparator<Map.Entry<Integer,String>>(){

			@Override
			public int compare(Entry<Integer, String> o1, Entry<Integer, String> o2) {
				return o1.getValue().compareTo(o2.getValue());
			}
			
		});
		
		Map<Integer,String> resultMap = new LinkedHashMap<Integer,String>();
		
		for(Map.Entry<Integer,String> entry : entryList) {
			resultMap.put(entry.getKey(), entry.getValue());
		}
		
		
		for(Map.Entry<Integer,String> entry : resultMap.entrySet()) {
			System.out.println(entry.getKey()+":"+entry.getValue());
		}		

	}

}
