package DZ_1;

import java.util.*;

public class MyArrayList<E> implements List<E> {

    public MyArrayList(int cap) {
        if (cap < 0){
            throw new RuntimeException("Размер не может быть меньше 0");
        }
        arr = new Object[cap];
        size = 0;
    }

    public MyArrayList() {
        this(10);
    }

    private Object arr[];

    private int size;

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean add(E e) {
        if((arr.length - 1) == size){
            arrayResizing();
        }
        arr[size] = e;
        size++;
        return true;
    }

    private void arrayResizing(){
        int oldCap = arr.length;
        if(oldCap > 0) {
            int newCap = (int) (oldCap * 1.5);
            arr = Arrays.copyOf(arr, newCap);
        }else {
            arr = new Object[10];
        }
    }

    @Override
    public boolean remove(Object o) {
        int i;
        for(i = 0; i < size; i++) {
            if (o.equals(arr[i])){
                break;
            }
        }
        if(i == size) {
            return false;
        } else {
            for(int j = i; j < size; j++){
                arr[j] = arr[j + 1];
            }
            size--;
            return true;
        }
    }

    @Override
    public E remove(int index) {
        if ((index >= 0) && (index < size)){
            E value = (E) arr[index];
            for(int i = index; i < size; i++){
                arr[i] = arr[i + 1];
            }
            size--;
            return value;
        }else {
            throw new IndexOutOfBoundsException("Индекс " + index + " выходит за пределы размера " + size);
        }
    }

    @Override
    public int indexOf(Object o) {
        int index = -1;
        for(int i = 0; i < size; i++) {
            if (o.equals(arr[i])){
                index = i;
                break;
            }
        }
        return index;
    }

    @Override
    public int lastIndexOf(Object o) {
        int index = -1;
        for(int i = size - 1; i >= 0; i--) {
            if (o.equals(arr[i])){
                index = i;
                break;
            }
        }
        return index;
    }

    @Override
    public boolean contains(Object o) {
        for(int i = 0; i < size; i++) {
            if (o.equals(arr[i])){
                return true;
            }
        }
        return false;
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOfRange(arr, 0, size);
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            arr[i] = null;
        }
        size = 0;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (Object o:arr) {
            if (o == null){
                break;
            }
            sb.append(o);
            sb.append(", ");
        }
        if (isEmpty()){
            sb.append("]");
        }else {
            sb.replace(sb.length() - 2, sb.length(), "]");
        }
        return sb.toString();
    }

    @Override
    public E get(int index) {
        if ((index >= 0) && (index < (size))){
            return (E) arr[index];
        }else {
            throw new IndexOutOfBoundsException("Индекс " + index + " выходит за пределы размера " + size);
        }
    }

    @Override
    public E set(int index, E element) {
        if ((index >= 0) && (index < (size))){
            E oldValue = (E) arr[index];
            arr[index] = element;
            return oldValue;
        }else {
            throw new IndexOutOfBoundsException("Индекс " + index + " выходит за пределы размера " + size);
        }
    }

    @Override
    public void add(int index, E element) {
        if ((index >= 0) && (index < (size))){
            if((arr.length - 1) == size){
                arrayResizing();
            }
            System.arraycopy(arr, index, arr, index + 1, size - index);
            arr[index] = element;
            size++;
        }else {
            throw new IndexOutOfBoundsException("Индекс " + index + " выходит за пределы размера " + size);
        }
    }













    @Override
    public boolean addAll(Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }
    
    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public ListIterator<E> listIterator() {
        return null;
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return null;
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return null;
    }
}
