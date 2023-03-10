package com.github.himeraoO.DZ_1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class MyArrayListTest {

    @Test
    public void add(){
        MyArrayList<Integer> integerMyArrayList = new MyArrayList<>();
        int sizeBefore = integerMyArrayList.size();
        boolean added = integerMyArrayList.add(1);
        int sizeAfter = integerMyArrayList.size();
        Assertions.assertEquals(sizeBefore, 0);
        Assertions.assertTrue(added);
        Assertions.assertEquals(1, integerMyArrayList.get(0));
        Assertions.assertEquals(sizeAfter, 1);

        integerMyArrayList.add(3);
        integerMyArrayList.add(1,2);
        for (int i = 0; i < 3; i++) {
            Integer integer = integerMyArrayList.get(i);
            if(i == 1){
                Assertions.assertEquals(2,integer);
            }
        }

        MyArrayList<String> arrayList = new MyArrayList<>();
        boolean added2 = arrayList.addAll(Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10"));
        Assertions.assertTrue(added2);
        Assertions.assertEquals("[1, 2, 3, 4, 5, 6, 7, 8, 9, 10]", arrayList.toString());


        boolean added3 = arrayList.addAll(Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10"));
        Assertions.assertTrue(added3);
        Assertions.assertEquals(
                "[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10]",
                arrayList.toString()
        );


        MyArrayList<String> arrayList2 = new MyArrayList<>();
        arrayList2.addAll(Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10"));
        boolean added4 = arrayList2.addAll(6, Arrays.asList("5", "5", "5", "5", "5"));
        Assertions.assertTrue(added4);
        Assertions.assertEquals(
                "[1, 2, 3, 4, 5, 6, 5, 5, 5, 5, 5, 7, 8, 9, 10]",
                arrayList2.toString()
        );

        MyArrayList<String> arrayList3 = new MyArrayList<>();
        boolean added5 = arrayList.addAll(Arrays.asList());
        Assertions.assertFalse(added5);
    }

    @Test
    void size() {
        MyArrayList<Integer> integerMyArrayList = new MyArrayList<>();
        int sizeBefore = integerMyArrayList.size();
        Assertions.assertEquals(sizeBefore, 0);
        integerMyArrayList.add(1);
        integerMyArrayList.add(2);
        integerMyArrayList.add(3);
        int sizeAfter = integerMyArrayList.size();
        Assertions.assertEquals(sizeAfter, 3);
    }

    @Test
    void isEmpty() {
        MyArrayList<Integer> integerMyArrayList = new MyArrayList<>();
        Assertions.assertTrue(integerMyArrayList.isEmpty());
        Assertions.assertEquals(integerMyArrayList.size(), 0);

        integerMyArrayList.add(1);
        Assertions.assertFalse(integerMyArrayList.isEmpty());
        Assertions.assertEquals(integerMyArrayList.size(), 1);
    }

    @Test
    void trimToSize() {
        MyArrayList<Integer> integerMyArrayList = new MyArrayList<>();

        Field field = null;
        try {
            field = MyArrayList.class.getDeclaredField("arr");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        field.setAccessible(true);
        int len = 0;
        try {
            len = Array.getLength(field.get(integerMyArrayList));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        Assertions.assertEquals(10, len);

        integerMyArrayList.add(1);
        integerMyArrayList.trimToSize();

        int len2 = 0;
        try {
            len2 = Array.getLength(field.get(integerMyArrayList));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        Assertions.assertEquals(1, len2);
    }

    @Test
    void arrayResizing() {
        MyArrayList<Integer> integerMyArrayList = new MyArrayList<>();

        Field field = null;
        try {
            field = MyArrayList.class.getDeclaredField("arr");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        field.setAccessible(true);
        int len = 0;
        try {
            len = Array.getLength(field.get(integerMyArrayList));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        Assertions.assertEquals(10, len);

        for (int i = 0; i < 11; i++) {
            integerMyArrayList.add(i);
        }

        int len2 = 0;
        try {
            len2 = Array.getLength(field.get(integerMyArrayList));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        Assertions.assertEquals(15, len2);
    }

    @Test
    void remove() {
        MyArrayList<Integer> integerMyArrayList = new MyArrayList<>();

        for (int i = 0; i < 10; i++) {
            integerMyArrayList.add(i);
        }

        String mALBefore = "[0, 1, 2, 3, 4, 5, 6, 7, 8, 9]";

        Assertions.assertEquals(mALBefore, integerMyArrayList.toString());

        boolean removed = integerMyArrayList.remove(Integer.valueOf(4));

        String mALAfter = "[0, 1, 2, 3, 5, 6, 7, 8, 9]";

        Assertions.assertTrue(removed);
        Assertions.assertEquals(mALAfter, integerMyArrayList.toString());

        boolean removed2 = integerMyArrayList.remove(Integer.valueOf(4));

        Assertions.assertFalse(removed2);

        Integer rem = integerMyArrayList.remove(0);
        Assertions.assertEquals(0, rem);

        MyArrayList<String> arrayList = new MyArrayList<>();
        arrayList.addAll(Arrays.asList("1", "2", "3", "4", "5", "5", "5", "5", "5", "6", "7", "8", "9", "10"));
        boolean removed3 = arrayList.removeAll(Arrays.asList("5", "5", "5", "5", "5"));
        Assertions.assertTrue(removed3);
        Assertions.assertEquals("[1, 2, 3, 4, 6, 7, 8, 9, 10]", arrayList.toString());

        boolean removed4 = arrayList.removeAll(Arrays.asList("5", "5", "5", "5", "5"));
        Assertions.assertFalse(removed4);

        boolean removed5 = arrayList.removeAll(Arrays.asList("4", "5", "5", "5", "5"));
        Assertions.assertTrue(removed5);
    }

    @Test
    void indexOf() {
    }

    @Test
    void lastIndexOf() {
    }

    @Test
    void contains() {
    }

    @Test
    void toArray() {
    }

    @Test
    void clear() {
    }

    @Test
    void testToString() {
    }

    @Test
    void get() {
    }

    @Test
    void set() {
    }

    @Test
    void sort() {

        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("a");
        arrayList.add("v");
        arrayList.add("e");
        arrayList.add("d");
        arrayList.add("c");
        arrayList.add("h");
        arrayList.add("m");
        arrayList.add("p");
        arrayList.add("w");
        arrayList.add("b");

        Assertions.assertEquals("[a, v, e, d, c, h, m, p, w, b]", arrayList.toString());

        UtilSort.sort(arrayList, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o2.compareTo(o1);
            }
        });

        Assertions.assertEquals("[w, v, p, m, h, e, d, c, b, a]", arrayList.toString());

        MyArrayList<String> myArrayList = new MyArrayList<>();

        myArrayList.add("a");
        myArrayList.add("v");
        myArrayList.add("e");
        myArrayList.add("d");
        myArrayList.add("c");
        myArrayList.add("h");
        myArrayList.add("m");
        myArrayList.add("p");
        myArrayList.add("w");
        myArrayList.add("b");

        Assertions.assertEquals("[a, v, e, d, c, h, m, p, w, b]", myArrayList.toString());

        myArrayList.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });

        Assertions.assertEquals("[a, b, c, d, e, h, m, p, v, w]", myArrayList.toString());

        MyArrayList<Integer> myArrayList2 = new MyArrayList<>();

        myArrayList2.add(11);
        myArrayList2.add(2);
        myArrayList2.add(12);
        myArrayList2.add(14);
        myArrayList2.add(5);
        myArrayList2.add(31);
        myArrayList2.add(45);
        myArrayList2.add(8);
        myArrayList2.add(10);
        myArrayList2.add(45);

        Assertions.assertEquals("[11, 2, 12, 14, 5, 31, 45, 8, 10, 45]", myArrayList2.toString());

        myArrayList2.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });

        Assertions.assertEquals("[2, 5, 8, 10, 11, 12, 14, 31, 45, 45]", myArrayList2.toString());

    }

}
