package ConcurrentCollections;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapExceptionCase {
    public static void main(String[] args) {
            Map<String, Integer> map = new ConcurrentHashMap<>();
            map.put("one", 1);
            map.put("two", 2);
            map.put("three", 3);

            Iterator<Map.Entry<String, Integer>> iterator = map.entrySet().iterator();

            // Attempt to modify the map while iterating
            while (iterator.hasNext()) {
                Map.Entry<String, Integer> entry = iterator.next();
                if (entry.getKey().equals("two")) {
                    map.put("four", 4); // This will throw ConcurrentModificationException
                }
            }

        System.out.println(map);
        }
    }
