package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class DBTable {
    private ArrayList<DBRecord> table;

    public DBTable() {
        table = new ArrayList<>();
    }

    public void createTableFromFile(String fileName) {
        try {
            BufferedReader in = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = in.readLine()) != null && line.length() != 0) {
                add(line);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void add(String line) {
        table.add(new DBRecord(line));
    }

    public ArrayList<DBRecord> getTable() {
        return table;
    }

    public void deleteAll() {
        table.clear();
    }

    public int deleteSelected() {
        int cnt = 0;
        Iterator it = table.iterator();
        while (it.hasNext()) {
            DBRecord tmp = (DBRecord)it.next();
            if (tmp.isSelected()) {
                it.remove();
                ++cnt;
            }
        }

        return cnt;
    }

    public int deleteUnselected() {
        int cnt = 0;
        Iterator it = table.iterator();
        while (it.hasNext()) {
            DBRecord tmp = (DBRecord)it.next();
            if (!tmp.isSelected()) {
                it.remove();
                ++cnt;
            }
        }

        return cnt;
    }

    public void clearSelection() {
        for (DBRecord record : table) {
            if (record.isSelected()) {
                record.setUnselected();
            }
        }
    }

    public int selectAnd(String line) {
        int cnt = 0;
        String[] strArray = line.split(",");
        DBBinding bind_1 = new DBBinding(strArray[0]);
        DBBinding bing_2 = new DBBinding(strArray[1]);
        for (DBRecord record : table) {
            if (record.bothContains(bind_1, bing_2)) {
                record.setSelected();
                ++cnt;
            }
        }

        return cnt;
    }

    public int selectOr(String line) {
        int cnt = 0;
        String[] strArray = line.split(",");
        DBBinding bind_1 = new DBBinding(strArray[0]);
        DBBinding bing_2 = new DBBinding(strArray[1]);
        for (DBRecord record : table) {
            if (record.eitherContains(bind_1, bing_2)) {
                record.setSelected();
                ++cnt;
            }
        }

        return cnt;
    }

    public int size() {
        return table.size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (DBRecord record : table) {
            sb.append(record.toString() + "\n");
        }

        return sb.toString();
    }
}
