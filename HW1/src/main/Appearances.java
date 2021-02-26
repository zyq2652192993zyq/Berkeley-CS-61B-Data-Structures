package main;

import java.util.*;

public class Appearances {
    public static <T> int sameCount(Collection<T> a, Collection<T> b) {
        HashMap<T, Integer> um1 = getHashMap(a), um2 = getHashMap(b);
        int res = 0;
        Set<Map.Entry<T, Integer>> entries = um1.entrySet();
        for (Map.Entry<T, Integer> entry : entries) {
            T key = entry.getKey();
            if (um2.containsKey(key) && entry.getValue().intValue() == um2.get(key).intValue()) {
                ++res;
            }
        }

        return res;
    }

    private static <T> HashMap<T, Integer> getHashMap(Collection<T> a) {
        HashMap<T, Integer> um = new HashMap<>();
        for (T e : a) {
            if (um.containsKey(e)) {
                Integer num = um.get(e);
                num += 1;
                um.put(e, num);
            }
            else {
                Integer num = 1;
                um.put(e, num);
            }
        }

        return um;
    }
}
