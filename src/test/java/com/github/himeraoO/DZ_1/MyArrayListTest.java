package com.github.himeraoO.DZ_1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.lang.reflect.Field;

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

        Assertions.assertEquals(2, len2);
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

        for (int i = 0; i < 10; i++) {
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
    }
}
