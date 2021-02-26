package main;

import java.util.*;

public class Taboo<T> {
    private HashMap<T, HashSet<T>> noFollowMap = new HashMap<>();

    public Taboo(List<T> rules) {
        T key = null;
        for (T value : rules) {
            if (key != null && value != null) {
                if (!noFollowMap.containsKey(key)) {
                    noFollowMap.put(key, new HashSet<>());
                }
                noFollowMap.get(key).add(value);
            }
            key = value;
        }
    }

    public Set<T> noFollow(T ele) {
        if (noFollowMap.containsKey(ele)) {
            return noFollowMap.get(ele);
        } else {
            return Collections.emptySet();
        }
    }

    public void reduce(List<T> inputList) {
        Iterator it = inputList.iterator();
        T key = null;
        while (it.hasNext()) {
            T value = (T)it.next();
            if (key != null && value != null
                    && noFollowMap.containsKey(key) && noFollowMap.get(key).contains(value)) {
                it.remove();
            }
            else {
                key = value;
            }
        }
    }
}
