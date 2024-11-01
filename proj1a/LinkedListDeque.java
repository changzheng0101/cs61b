
public class LinkedListDeque<T> {
    class Node {
        Node prev;
        Node next;
        T val;

        public Node(T val) {
            this.val = val;
        }

        public Node() {
        }
    }

    private final Node sentinel;
    private int size;

    public LinkedListDeque() {
        sentinel = new Node();
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    public void addFirst(T item) {
        Node node = new Node(item);
        Node sentinelNext = sentinel.next;
        sentinel.next = node;
        sentinelNext.prev = node;
        node.next = sentinelNext;
        node.prev = sentinel;
        size++;
    }

    public void addLast(T item) {
        Node node = new Node(item);
        Node sentinelPrev = sentinel.prev;
        sentinel.prev = node;
        sentinelPrev.next = node;
        node.next = sentinel;
        node.prev = sentinelPrev;
        size++;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        Node cur = sentinel.next;
        while (cur != sentinel) {
            System.out.println(cur.val + " ");
            cur = cur.next;
        }
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        Node firstNode = sentinel.next;
        Node firstNext = firstNode.next;
        sentinel.next = firstNext;
        firstNext.prev = sentinel;
        // reset first node
        firstNode.next = null;
        firstNode.prev = null;
        size--;
        return firstNode.val;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        Node lastNode = sentinel.prev;
        Node lastPrev = lastNode.prev;
        lastPrev.next = sentinel;
        sentinel.prev = lastPrev;
        // reset last node
        lastNode.next = null;
        lastNode.prev = null;
        size--;
        return lastNode.val;
    }

    public T get(int index) {
        if (index >= size) {
            return null;
        }
        Node cur = sentinel.next;
        while (index > 0) {
            cur = cur.next;
            index--;
        }
        return cur.val;
    }

    public T getRecursive(int index) {
        if (index >= size) {
            return null;
        }
        return getRecursiveHelper(index, sentinel.next);
    }

    private T getRecursiveHelper(int index, Node current) {
        if (index == 0) {
            return current.val;
        }
        return getRecursiveHelper(index - 1, current.next);
    }
}
