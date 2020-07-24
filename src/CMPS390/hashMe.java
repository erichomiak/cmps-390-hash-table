package CMPS390;

import java.util.Scanner;
import java.io.*;
import java.lang.Math;


public class hashMe {

    String fname;
    public static String[] hashTable;
    static int tabLen;
    int numSmashes;
    int col, collisions;

    public hashMe(int hashTableLen) {
        int j;

        tabLen = hashTableLen;
        numSmashes = 0;

        hashTable = new String[tabLen];

        for (j=0; j < tabLen; j++) {
            hashTable[j] = "#";
        }

        getFileName();
        readFileContents();
        showHashTable();
    }

    public void readFileContents() {
        boolean looping;
        DataInputStream in;
        String line;
        int j, len, hashIndex = 0, hInc, spot;
        len = 1;
        collisions = 0;

        try {
            in = new DataInputStream (new FileInputStream ("/Users/erichomiak/Desktop/Southeastern Louisiana University/Fall 2018/CMPS 390/Program6HashTable/src/CMPS390/" + fname));

            looping = true;
            while (looping) {
                if (null == (line = in.readLine ())) {
                    looping = false;
                    in.close ();
                }
                else {
                    hashIndex = hashFun(line);
                    len = len + 1;

                    while (!hashTable[hashIndex].equals("#")) {
                        collisions = collisions + 1;
                        hashIndex++;
                    }

                    hashTable[hashIndex] = line;

                    col = collisions;
                }
            }
        }

        catch (IOException e) {
            System.out.println ("Error " + e);
        }
    }

    int charToInt(char c) {
        int value = c;
        value = value - 96;
        return value;
    }

    public void showHashTable() {
        int j;
        System.out.println ("Number of hash clashes = " + col);
        for (j = 0; j < tabLen; j++) {
            System.out.print (hashTable[j] + " ");
        }
    }

    public int hashFun(String name) {
        int x, y, z, sortNum = 0;

        x = charToInt (name.charAt (0));
        y = charToInt (name.charAt (1));
        z = charToInt (name.charAt (2));

        x = x * 676;
        y = y * 26;

        if (tabLen == 200) {
            sortNum = (x + y + z) / 90;
        }
        else if (tabLen == 400) {
            sortNum = (x + y + z) / 45;
        }
        else if (tabLen == 700) {
            sortNum = (x + y + z) / 26;
        }
        int hashVal = sortNum;

        return hashVal;
    }

    public void getFileName() {
        Scanner in = new Scanner (System.in);

        System.out.println ("Enter file name please:");
        fname = in.nextLine ();
        System.out.println ("You entered " + fname);
    }

    public static void main(String[] args) {
        hashMe h = new hashMe (200);
        System.out.println ();
        hashMe i = new hashMe (400);
        System.out.println ();
        hashMe j = new hashMe (700);
        System.out.println ();
    }
}
