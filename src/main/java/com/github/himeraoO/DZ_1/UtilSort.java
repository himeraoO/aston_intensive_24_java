package com.github.himeraoO.DZ_1;

import java.util.Comparator;
import java.util.List;

public class UtilSort {

    public static <T> void sort(List<T> list, Comparator<? super T> c) {
        list.sort(c);
    }

}
