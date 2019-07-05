package learning.map.sort;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class SortMapValuesStream {

	public static void main(String[] args) {
		Map<Integer, String> myMap = new HashMap<Integer, String>();

		myMap.put(25, "Devi");
		myMap.put(29, "Ashok");
		myMap.put(1, "Thushith");
		myMap.put(47, "Lakshmi");
		
		Map<Integer, String> resultedMap = new LinkedHashMap<Integer, String>();
		
		
		myMap.entrySet().stream().sorted(Map.Entry.<Integer, String>comparingByValue()).forEachOrdered(s -> resultedMap.put(s.getKey(),s.getValue()));
		resultedMap.entrySet().stream().forEach(s -> System.out.println(s.getKey()+":"+s.getValue()));

	}

}