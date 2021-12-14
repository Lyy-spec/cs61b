import java.util.Arrays;

/**
 * @description:
 * @author: Lyq
 */

public class ArrayDeque<T> {
    private static final int INITIAL_CAPACITY = 8;
    private static final int RFACTOR = 2;
    private static final double MIN_USAGE_RATIO = 0.25;
    private T[] elements;
    private int size;
    private int nextFirst;
    private int nextLast;


    public ArrayDeque() {
        elements = (T[]) new Object[INITIAL_CAPACITY];
        nextFirst = 4;
        nextLast = 5;
        size = 0;
    }

    public ArrayDeque(ArrayDeque<T> other) {
        elements = (T[]) new Object[other.elements.length];
        nextFirst = 4;
        nextLast = 5;
        System.arraycopy(other.elements, 0, elements, 0, other.elements.length);
    }

    public int minusOne(int index) {
        return (index - 1 + elements.length) % elements.length;
    }

    public int plusOne(int index) {
        return (index + 1) % elements.length;
    }

    public void resize(int capacity) {
        Object[] newElements = new Object[capacity];
        int cur = plusOne(nextFirst);
        for(int i = 0;i < size;i++){
            newElements[i] = elements[cur];
            cur = plusOne(cur);
        }
        elements = (T[]) newElements;
        nextFirst = capacity * RFACTOR - 1;
        nextLast = size;
    }

    public void addFirst(T item) {
        if(size == elements.length){
            resize(elements.length * RFACTOR);
        }
        elements[nextFirst] = item;
        size++;
        nextFirst = minusOne(nextFirst);
    }

    public void addLast(T item) {
        if(size == elements.length){
            resize(elements.length * RFACTOR);
        }
        elements[nextLast] = item;
        size++;
        nextLast = plusOne(nextLast);
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        int index = plusOne(nextFirst);
        while(index != nextLast){
            System.out.print(elements[index] + " ");
        }
        System.out.println();
    }

    public T removeFirst() {
        if(size == 0){
            return null;
        }
        int first = plusOne(nextFirst);
        T delItem = elements[first];
        elements[first] = null;
        size--;
        nextFirst = first;
        if(elements.length >= 16 && size < elements.length * MIN_USAGE_RATIO){
            resize(elements.length / 2);
        }
        return delItem;
    }

    public T removeLast() {
        if(size == 0){
            return null;
        }
        int last = minusOne(nextLast);
        T delItem = elements[last];
        elements[last] = null;
        size--;
        nextLast = last;
        if(elements.length >= 16 && size < elements.length * MIN_USAGE_RATIO){
            resize(elements.length / 2);
        }
        return delItem;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        return elements[(plusOne(nextFirst) + index) % elements.length];
    }
}
