package Sorter;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Sorter {
    void bubbleSortInts(int[] data) {
        for (int i = 0; i < data.length - 1; i++) {
            for (int j = 0; j < data.length - i - 1; j++) {
                if (data[j] > data[j + 1]) { // swap
                    int temp = data[j];
                    data[j] = data[j + 1];
                    data[j + 1] = temp;
                }
            }
        }
    }

    void bubbleSortDates(Date[] data3) {
        for (int i = 0; i < data3.length - 1; i++) {
            for (int j = 0; j < data3.length - i - 1; j++) {
                if (data3[j].compareTo(data3[j + 1]) > 0) { // swap
                    Date temp = data3[j];
                    data3[j] = data3[j + 1];
                    data3[j + 1] = temp;
                }
            }
        }
    }
    
    void bubbleSortStrings(String[] data2) {
        for (int i = 0; i < data2.length - 1; i++) {
            for (int j = 0; j < data2.length - i - 1; j++) {
                if (data2[j].compareTo(data2[j + 1]) > 0) { // swap
                    String temp = data2[j];
                    data2[j] = data2[j + 1];
                    data2[j + 1] = temp;
                }
            }
        }
    }

    void printInts(int[] data) {
        for (int i : data) {
            System.out.println(i);
        }
    }

    void printStrings(String[] data2) {
        for (String i : data2) {
            System.out.println(i);
        }
    }
    
    void printDates(Date[] data3) {
        for (Date d : data3) {
            System.out.println(d);
        }
    }
}
