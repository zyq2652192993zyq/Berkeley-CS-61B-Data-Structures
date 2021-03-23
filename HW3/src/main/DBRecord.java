package main;

import java.util.ArrayList;

public class DBRecord {
    private ArrayList<DBBinding> records;
    private boolean selected;

    public DBRecord(String line) {
        records = new ArrayList<>();
        selected = false;

        line = line.trim();
        if (line.charAt(0) == '*') {
            setSelected();
            line = line.substring(1);
        }

        String[] strArray = line.split(",");
        for (String pair : strArray) {
            add(pair);
        }
    }

    public void add(String bindPair) {
        records.add(new DBBinding(bindPair));
    }

    public ArrayList<DBBinding> getRecords() {
        return records;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected() {
        selected = true;
    }

    public void setUnselected() {
        selected = false;
    }

    public boolean bothContains(DBBinding bind_1, DBBinding bind_2) {
        int cnt = 0;
        for (DBBinding bind : records) {
            if (bind.getKey().toLowerCase().equals(bind_1.getKey())
                    && bind.getValue().toLowerCase().contains(bind_1.getValue().toLowerCase())) {
                ++cnt;
            }
            if (bind.getKey().toLowerCase().equals(bind_2.getKey())
                    && bind.getValue().toLowerCase().contains(bind_2.getValue().toLowerCase())) {
                ++cnt;
            }
        }

        return cnt == 2;
    }

    public boolean eitherContains(DBBinding bind_1, DBBinding bind_2) {
        for (DBBinding bind : records) {
            if (bind.getKey().toLowerCase().equals(bind_1.getKey())
                    && bind.getValue().toLowerCase().contains(bind_1.getValue().toLowerCase())) {
                return true;
            }
            if (bind.getKey().toLowerCase().equals(bind_2.getKey())
                    && bind.getValue().toLowerCase().contains(bind_2.getValue().toLowerCase())) {
                return true;
            }
        }

        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int len = records.size();
        int cnt = 0;
        if (selected) {
            sb.append("*");
        }
        for (DBBinding bind : records) {
            sb.append(bind.toString());
            if (cnt != len - 1) {
                sb.append(", ");
            }
        }

        return sb.toString();
    }
}
