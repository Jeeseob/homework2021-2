package Sorter;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {
    public static Date[] createDateArray(String[] dateStrings) {
        SimpleDateFormat dateTimeInstance = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Date[] dates = new Date[dateStrings.length];
        for (int i = 0; i < dateStrings.length; i++) {
            dates[i] = dateTimeInstance.parse(dateStrings[i], new ParsePosition(0));
        }
        return dates;
    }

    public static void main(String[] args) {
        int[] data = { 1, 3, 7, 5, 6, 4, 10, 8 };
        String[] data2 = { "hello", "hello1", "world1", "world", "new", "next" };
        String[] dateStrings = { "2020-09-13T21:59:00", "2020-09-12T21:59:00",
                "2020-09-13T16:54:00", "2020-09-12T21:54:00", "2020-09-13T11:59:00" };

        Date[] dates = Main.createDateArray(dateStrings);
        Sorter si = new Sorter();
        //si.bubbleSortInts();
        si.bubbleSortInts(data);
        si.printInts(data);
        si.bubbleSortStrings(data2);
        si.printStrings(data2);
        si.bubbleSortDates(dates);
        si.printDates(dates);
    }
}
