package main.java.DZ_1;

import java.util.Comparator;
import java.util.List;

public class UtilSort {

//    public static <T extends Comparable<? super T>> void sort(List<T> list) {
//        list.sort(null);
//    }

    public static <T> void sort(List<T> list, Comparator<? super T> c) {
        list.sort(c);
    }

}
