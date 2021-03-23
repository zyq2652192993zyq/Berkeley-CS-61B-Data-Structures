package main;

/*
 Provided class that presents a command-line interface for the DB classes.
 It does simple i/o with the console and sends messages to the DB
 to do all the work. All the code is in main().
 Edit the code so it interfaces with the DB classes.
*/

import java.io.*;

public class CommandLineInterface {
    public static void main(String[] args) {
        // DBTable to use for everything
        DBTable db = new DBTable();

        try {
            // Create reader for typed input on console
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String line;
            int length = 0;
            int selectedLength = 0;
            while (true) {
                System.out.println("\n" + length + " records (" + selectedLength + " selected)");
                System.out.println("r read, p print, sa select and, so select or, da ds du delete, c clear sel");
                System.out.print("db:");
                line = reader.readLine().toLowerCase();

                if (line.equals("r")) {
                    System.out.println("read");
                    String fname;
                    System.out.print("Filename:");
                    fname = reader.readLine();
                    db.createTableFromFile(fname);
                    length += db.size();
                }
                else if (line.equals("p")) {
                    System.out.println("print");
                    System.out.println(db.toString());
                }
                else if (line.equals("da")) {
                    System.out.println("delete all");
                    db.deleteAll();
                    length = 0;
                    selectedLength = 0;
                }
                else if (line.equals("ds")) {
                    System.out.println("delete selected");
                    length -= db.deleteSelected();
                    selectedLength = 0;
                }
                else if (line.equals("du")) {
                    System.out.println("delete unselected");
                    length -= db.deleteUnselected();
                }
                else if (line.equals("c")) {
                    System.out.println("clear selection");
                    db.clearSelection();
                    selectedLength = 0;
                }
                else if (line.equals("so") || line.equals("sa")) {
                    boolean isOr = true;
                    if (line.equals("so")) {
                        System.out.println("select or");
                    }
                    else {
                        System.out.println("select and");
                        isOr = false;
                    }

                    System.out.print("Criteria record:");
                    // get text line from user
                    String text = reader.readLine();

                    if (isOr) {
                        selectedLength += db.selectOr(text);
                    }
                    else {
                        selectedLength += db.selectAnd(text);
                    }
                }
                else if (line.equals("q") || line.equals("quit")) {
                    System.out.println("quit");
                    break;
                }
                else {
                    System.out.println("sorry, don't know that command");
                }
            }
        }
        catch (IOException e) {
            System.err.println(e);
        }
    }
}
