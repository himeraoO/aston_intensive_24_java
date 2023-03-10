package com.github.himeraoO.DZ_1;

import java.util.*;

/**
 * Реализация ArrayList
 * @param <E> тип элементов, которые будут содержаться в ArrayList
 * @author himeraoO Вадим Виноградов
 */
public class MyArrayList<E> implements List<E> {
    /**
     * Внутренний массив для хранения данных
     */
    private Object[] arr;

    /**
     * Размер ArrayList
     */
    private int size;

    /**
     * Дефолтное значение размера внутреннего массива
     */
    private static final int DEFAULT_CAPACITY = 10;

    /**
     * Конструктор, принимает значение
     * @param cap и инициализирует этим значением внутренний массив arr[]
     *            size устанавливается в 0 и
     *            может выбросить исключение
     * @RuntimeException если размер будет задан меньше 0
     */
    public MyArrayList(int cap) {
        if (cap < 0) {
            throw new RuntimeException("Размер не может быть меньше 0");
        }
        arr = new Object[cap];
        size = 0;
    }

    /**
     * Конструктор по умолчанию
     * вызывает внутри в себя
     * @see #MyArrayList(int cap)
     * и инициализируется значение 10, по умолчанию
     */
    public MyArrayList() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * Конструктор, принимает значение
     * @param c - коллекция, которая добавится при инициализации класса
     */
    public MyArrayList(Collection<? extends E> c) {
        Object[] objects = c.toArray();
        int length = objects.length;
        arr = new Object[length];
        System.arraycopy(objects, 0, arr, 0, length);
        size = length;
    }

    /**
     * @return возвращает количество элементов, {@code int}
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * возвращает boolean, проверяя есть элементы или нет
     *
     * @return {@code true} - если элементов нет
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Метод добавление элементов
     *
     * @param e - элемент, который добавляется
     * @return {@code true} если элемент добавлен
     */
    @Override
    public boolean add(E e) {
        if ((arr.length) == size) {
            arrayResizing();
        }
        arr[size] = e;
        size++;
        return true;
    }

    /**
     * Вспомогательный метод, который изменяет размер внутреннего массива
     * происходит увеличение в 1.5 раза от исходного
     */
    private void arrayResizing() {
        int oldCap = arr.length;
        if (oldCap > 0) {
            int newCap = (int) (oldCap * 1.5);
            arr = Arrays.copyOf(arr, newCap);
        } else {
            arr = new Object[10];
        }
    }

    /**
     * Вспомогательный метод, который изменяет размер внутреннего массива
     * происходит увеличение на
     *
     * @param newSize от исходного
     */
    private void arrayResizing(int newSize) {
        int oldCap = arr.length;
        if (oldCap > 0) {
            arr = Arrays.copyOf(arr, oldCap + newSize);
        } else {
            arr = new Object[newSize];
        }
    }

    /**
     * Метод позволяющий уменьшить размер чрезмерно большого внутреннего массива
     * относительно количества используемых элементов
     */
    public void trimToSize() {
        if (size < arr.length) {
            if (size > 0) {
                arr = Arrays.copyOf(arr, size);
            }
        }
    }

    /**
     * метод удаления элемента по значению
     *
     * @param o элемент, который будет удаляться
     * @return @{code true} если элемент удален
     */
    @Override
    public boolean remove(Object o) {
        int i;
        for (i = 0; i < size; i++) {
            if (o.equals(arr[i])) {
                break;
            }
        }
        if (i == size) {
            return false;
        } else {
            if (size - i >= 0) {
                System.arraycopy(arr, i + 1, arr, i, size - i - 1);
            }
            size--;
            arr[size] = null;
            return true;
        }
    }

    /**
     * метод удаления элемента по индексу
     *
     * @param index индекс элемента который будет удаляться
     * @return элемент, который был удален
     * @throws IndexOutOfBoundsException, если @{code index} находится за пределами внутреннего массива
     */
    @Override
    public E remove(int index) {
        if ((index >= 0) && (index < size)) {
            @SuppressWarnings("unchecked")
            E value = (E) arr[index];
            System.arraycopy(arr, index + 1, arr, index, size - index - 1);
            size--;
            arr[size] = null;
            return value;
        } else {
            throw new IndexOutOfBoundsException("Индекс " + index + " выходит за пределы размера " + size);
        }
    }

    /**
     * Метод позволяет получить индекс первого найденного элемента
     *
     * @param o элемент, индекс которого необходимо найти
     * @return @{int}, @{-1} - если элемент не найден
     */
    @Override
    public int indexOf(Object o) {
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (o.equals(arr[i])) {
                index = i;
                break;
            }
        }
        return index;
    }

    /**
     * Метод позволяет получить индекс последнего найденного элемента
     *
     * @param o элемент, индекс которого необходимо найти
     * @return @{int}, @{-1} - если элемент не найден
     */
    @Override
    public int lastIndexOf(Object o) {
        int index = -1;
        for (int i = size - 1; i >= 0; i--) {
            if (o.equals(arr[i])) {
                index = i;
                break;
            }
        }
        return index;
    }

    /**
     * Метод позволяющий проверить, содержится ли передаваемый элемент в коллекции или нет
     *
     * @param o передаваемый элемент
     * @return @{true} если элемент найден
     */
    @Override
    public boolean contains(Object o) {
        for (int i = 0; i < size; i++) {
            if (o.equals(arr[i])) {
                return true;
            }
        }
        return false;
    }

    /**
     * Метод позволяет получить содержимое коллекции в виде массива
     *
     * @return элементы коллекции в виде массива
     */
    @Override
    public Object[] toArray() {
        return Arrays.copyOfRange(arr, 0, size);
    }

    /**
     * Метод очищает коллекцию
     */
    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            arr[i] = null;
        }
        size = 0;
    }

    /**
     * @return строковое представление коллекции
     */
    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (Object o : arr) {
            if (o == null) {
                break;
            }
            sb.append(o);
            sb.append(", ");
        }
        if (isEmpty()) {
            sb.append("]");
        } else {
            sb.replace(sb.length() - 2, sb.length(), "]");
        }
        return sb.toString();
    }

    /**
     * Метод позволяет получить элемент коллекции по индексу
     *
     * @param index элемента в коллекции
     * @return элемент коллекции
     * @throws IndexOutOfBoundsException, если @{code index} находится за пределами внутреннего массива
     */
    @Override
    public E get(int index) {
        if ((index >= 0) && (index < (size))) {
            @SuppressWarnings("unchecked")
            E result = (E) arr[index];
            return result;
        } else {
            throw new IndexOutOfBoundsException("Индекс " + index + " выходит за пределы размера " + size);
        }
    }

    /**
     * Метод позволяет изменить элемент коллекции по индексу
     *
     * @param index   элемента который необходимо заменить в коллекции
     * @param element которым заменяют старый элемент по индексу
     * @return элемент коллекции который был заменен
     * @throws IndexOutOfBoundsException, если @{code index} находится за пределами внутреннего массива
     */
    @Override
    public E set(int index, E element) {
        if ((index >= 0) && (index < (size))) {
            @SuppressWarnings("unchecked")
            E oldValue = (E) arr[index];
            arr[index] = element;
            return oldValue;
        } else {
            throw new IndexOutOfBoundsException("Индекс " + index + " выходит за пределы размера " + size);
        }
    }

    /**
     * Метод добавления элемента в середину коллекции
     * при добавлении нового элемента, все элементы справа смещаются на 1 позицию
     *
     * @param index   позиция в которую будет добавлен новый элемент
     * @param element элемент, который добавляется
     * @throws IndexOutOfBoundsException, если @{code index} находится за пределами внутреннего массива
     */
    @Override
    public void add(int index, E element) {
        if ((index >= 0) && (index < (size))) {
            if ((arr.length) == size) {
                arrayResizing();
            }
            System.arraycopy(arr, index, arr, index + 1, size - index);
            arr[index] = element;
            size++;
        } else {
            throw new IndexOutOfBoundsException("Индекс " + index + " выходит за пределы размера " + size);
        }
    }

    /**
     * Метод для сортировки коллекции (quicksort)
     *
     * @param c компаратор для сортировки
     */
    @Override
    public void sort(Comparator<? super E> c) {
        quickSort(0, size - 1, c);
    }

    /**
     * Реализация сортировки quicksort
     *
     * @param from позиция с которой будет проводиться сортировка
     * @param to   позиция до которой будет проводиться сортировка
     * @param c    компаратор для сортировки
     */
    private void quickSort(int from, int to, Comparator<? super E> c) {
        if (to - from > 0) {
            int partition = partition(from, to, c);
            quickSort(from, partition - 1, c);
            quickSort(partition, to, c);
        }
    }

    /**
     * Метод в котором непосредственно происходит сортировка
     *
     * @param from позиция с которой будет проводиться сортировка
     * @param to   позиция до которой будет проводиться сортировка
     * @param c    компаратор для сортировки
     * @return позицию int элемента для следующей разбивки массива для сортировки
     */
    private int partition(int from, int to, Comparator<? super E> c) {
        //опорный элемент
        @SuppressWarnings("unchecked")
        E pivot = (E) arr[from + (to - from) / 2];
        //левый индекс
        int li = from;
        //правый индекс
        int ri = to;

        while (li < ri) {
            //смещение левого индекса
            while (c.compare((E) arr[li], pivot) < 0) {
                li++;
            }
            //смещение правого индекса
            while ((ri > 0) && ((c.compare((E) arr[ri], pivot)) > 0)) {
                ri--;
            }
            //проверка для изменения положения элементов
            if (li <= ri) {
                swap(li, ri);
                li++;
                ri--;
            }
        }
        return li;
    }

    /**
     * Метод для изменения положения элементов
     *
     * @param el1 индекс первого элемента
     * @param el2 индекс второго элемента
     */
    private void swap(int el1, int el2) {
        @SuppressWarnings("unchecked")
        E temp = (E) arr[el1];
        arr[el1] = arr[el2];
        arr[el2] = temp;
    }

    /**
     * Метод для добавления элементов другой коллекции
     *
     * @param c - коллекция элементов
     * @return @{true} если элементы добавлены
     */
    @Override
    public boolean addAll(Collection<? extends E> c) {
        if (c.isEmpty()) {
            return false;
        }
        Object[] objects = c.toArray();
        int lengthObjects = objects.length;
        if (arr.length < (size + lengthObjects)) {
            arrayResizing(lengthObjects);
        }

        System.arraycopy(objects, 0, this.arr, this.size, lengthObjects);

        this.size = this.size + lengthObjects;
        return true;
    }

    /**
     * Метод для добавления элементов другой коллекции в заданную позицию
     *
     * @param index - позиция с которой будут добавляться элементы
     * @param c     - коллекция элементов
     * @return @{true} если элементы добавлены
     */
    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        if ((index >= 0) && (index < (size))) {
            if (c.isEmpty()) {
                return false;
            }
            Object[] objects = c.toArray();
            int lengthObjects = objects.length;
            if (arr.length < (size + lengthObjects)) {
                arrayResizing(lengthObjects);
            }
            System.arraycopy(this.arr, index, this.arr, index + lengthObjects, size - index);
            System.arraycopy(objects, 0, this.arr, index, lengthObjects);
            this.size = this.size + lengthObjects;
            return true;
        } else {
            throw new IndexOutOfBoundsException("Индекс " + index + " выходит за пределы размера " + size);
        }
    }

    /**
     * Метод для удаления группы элементов
     * @param c - коллекция элементов для удаления
     * @return @{true} если удален хотя бы 1 элемент
     */
    @Override
    public boolean removeAll(Collection<?> c) {
        if (c.isEmpty()) {
            return false;
        }

        final Object[] objects = Arrays.copyOf(this.arr, size);
        int oldSize = objects.length;

        for (Object o : c.toArray()) {
            for (int i = 0; i < oldSize; i++) {
                if (objects[i].equals(o)) {
                    remove(i);
                    break;
                }
            }
        }

        return this.size < oldSize;
    }

    @Override
    public Iterator<E> iterator() {
//        return null;
        throw new UnsupportedOperationException();
    }

    @Override
    public <T> T[] toArray(T[] a) {
//        return null;
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean containsAll(Collection<?> c) {
//        return false;
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean retainAll(Collection<?> c) {
//        return false;
        throw new UnsupportedOperationException();
    }

    @Override
    public ListIterator<E> listIterator() {
//        return null;
        throw new UnsupportedOperationException();
    }

    @Override
    public ListIterator<E> listIterator(int index) {
//        return null;
        throw new UnsupportedOperationException();
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
//        return null;
        throw new UnsupportedOperationException();
    }
}
