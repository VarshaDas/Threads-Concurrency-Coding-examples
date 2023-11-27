package ConcurrentCollections;

import java.util.LinkedHashMap;
import java.util.Map;

public class LinkedHashMapDemo {
    public static void main(String[] args) {
        // Create a LinkedHashMap
        LinkedHashMap<String, Integer> linkedHashMap = new LinkedHashMap<>();

        // Insert elements
        linkedHashMap.put("One", 1);
        linkedHashMap.put("Two", 2);
        linkedHashMap.put("four", 4);
        linkedHashMap.put("Three", 3);

        System.out.println("LinkedHashMap after insertion: " + linkedHashMap);


//       inp); // Output: {one=1, three=3, two=2}

    }
}
