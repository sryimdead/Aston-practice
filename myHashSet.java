import java.util.Iterator;
import java.util.LinkedList;

class MyHashSet<T> {
    private static final int DEFAULT_CAPACITY = 16;
    private LinkedList<T>[] buckets;
    private int size;

    @SuppressWarnings("unchecked")
    public MyHashSet() {
        buckets = (LinkedList<T>[]) new LinkedList[DEFAULT_CAPACITY];
        for (int i = 0; i < DEFAULT_CAPACITY; i++) {
            buckets[i] = new LinkedList<>();
        }
        size = 0;
    }

    private int getBucketIndex(T value) {
        return (value.hashCode() & 0x7FFFFFFF) % buckets.length;
    }

    // Вставка элемента
    public void add(T value) {
        int index = getBucketIndex(value);
        LinkedList<T> bucket = buckets[index];

        if (!bucket.contains(value)) {
            bucket.add(value);
            size++;
        }
    }

    // Удаление элемента
    public void remove(T value) {
        int index = getBucketIndex(value);
        LinkedList<T> bucket = buckets[index];

        if (bucket.remove(value)) {
            size--;
        }
    }

    public boolean contains(T value) {
        int index = getBucketIndex(value);
        return buckets[index].contains(value);
    }
}

class MyLinkedList<T> implements Iterable<T> {
    private class Node {
        T data;
        Node next;

        Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node head;
    private Node tail;
    private int size;

    public MyLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    // Добавление элемента в конец
    public void add(T value) {
        Node newNode = new Node(value);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    // Получение элемента по индексу
    public T get(int index) {
        checkIndex(index);
        Node current = head;
        for (int i=0; i<index; i++) {
            current = current.next;
        }
        return current.data;
    }

    // Удаление элемента по индексу
    public void remove(int index) {
        checkIndex(index);

        if (index == 0) {
            head = head.next;
            if (head == null) { // если список стал пустым
                tail = null;
            }
        } else {
            Node prev = getNode(index -1);
            prev.next = prev.next.next;
            if (prev.next == null) { // если удалили последний элемент
                tail = prev;
            }
        }
        size--;
    }

    // Добавление всех элементов из другого списка
    public void addAll(MyLinkedList<T> other) {
        for (T item : other) {
            add(item);
        }
    }

    // Вспомогательный метод для получения узла по индексу
    private Node getNode(int index) {
        checkIndex(index);
        Node current = head;
        for (int i=0; i<index; i++) {
            current=current.next;
        }
        return current;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Индекс вне диапазона");
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                T data = current.data;
                current= current.next;
                return data;
            }
        };
    }
}

public class Main {
    public static void main(String[] args) {

        //MyHashSet
        MyHashSet<String> set = new MyHashSet<>();

        set.add("apple");
        set.add("banana");

        System.out.println(set.contains("apple")); // true

        set.remove("apple");

        System.out.println(set.contains("apple")); // false


        //MyLinkedList
        MyLinkedList<Integer> list1= new MyLinkedList<>();
        list1.add(1);
        list1.add(2);
        list1.add(3);

        System.out.println("Элементы списка 1:");
        for (int num : list1){
            System.out.println(num);
        }

        System.out.println("Элемент по индексу 1: " + list1.get(1)); // 2

        list1.remove(1); // удаляем элемент по индексу 1

        System.out.println("После удаления:");
        for (int num : list1){
            System.out.println(num);
        }

        MyLinkedList<Integer> list2= new MyLinkedList<>();
        list2.add(4);
        list2.add(5);

        list1.addAll(list2);

        System.out.println("После добавления всех элементов из второго списка:");
        for (int num : list1){
            System.out.println(num);
        }
    }
}
