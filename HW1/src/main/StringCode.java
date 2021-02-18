package main;

import java.util.HashSet;

import static java.lang.Math.max;

public class StringCode {
    public String blowup(String str) {
        if (str == null || str.length() == 0) {
            return "";
        }

        int len = str.length();
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < len; ++i) {
            if (Character.isDigit(str.charAt(i)) && i != len - 1) {
                int times = str.charAt(i) - '0';
                for (int j = 0; j < times; ++j) {
                    res.append(str.charAt(i + 1));
                }
            }
            else {
                res.append(str.charAt(i));
            }
        }

        return res.toString();
    }

    public int maxRun(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }

        int len = str.length();
        int res = 1;
        int cur = 1;
        for (int i = 1; i < len; ++i) {
            if (str.charAt(i) == str.charAt(i - 1)) {
                ++cur;
            }
            else {
                res = max(res, cur);
                cur = 1;
            }
        }
        res = max(res, cur);

        return res;
    }

    public boolean stringIntersect(String a, String b, int len) {
        HashSet<Integer> hs = new HashSet<>();
        int m = a.length(), n = b.length();
        for (int i = 0; i <= m - len; ++i) {
            Integer value = a.substring(i, i + len).hashCode();
            hs.add(value);
        }

        for (int i = 0; i <= n - len; ++i) {
            Integer value = b.substring(i, i + len).hashCode();
            if (hs.contains(value)) {
                return true;
            }
        }

        return false;
    }
}
