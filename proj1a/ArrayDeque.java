
public class ArrayDeque<T> {
    private T[] items;
    private int size;
    private final double maxLoadFactor = 0.75;
    private final double minLoadFactor = 0.25;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = 0;
        nextLast = 1;
    }

    public void addFirst(T item) {
        if ((size + 1.0) / items.length >= maxLoadFactor) {
            resize(items.length * 2);
        }
        items[nextFirst] = item;
        nextFirst = (nextFirst - 1 + items.length) % items.length;
        size++;
    }

    public void addLast(T item) {
        if ((size + 1.0) / items.length >= maxLoadFactor) {
            resize(items.length * 2);
        }
        items[nextLast] = item;
        nextLast = (nextLast + 1) % items.length;
        size++;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        for (int i = 0; i < size; i++) {
            System.out.println(get(i) + " ");
        }
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        int firstItemIndex = (nextFirst + 1) % items.length;
        T firstItem = items[firstItemIndex];
        items[firstItemIndex] = null;
        nextFirst = firstItemIndex;
        size--;
        if ((size + 0.0) / items.length <= minLoadFactor && items.length >= 16) {
            resize(items.length / 2);
        }
        return firstItem;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        int lastItemIndex = (nextLast - 1 + items.length) % items.length;
        T lastItem = items[lastItemIndex];
        items[lastItemIndex] = null;
        nextLast = lastItemIndex;
        size--;
        if ((size + 0.0) / items.length <= minLoadFactor && items.length >= 16) {
            resize(items.length / 2);
        }
        return lastItem;
    }

    public T get(int index) {
        return items[(nextFirst + 1 + index) % items.length];
    }

    private void resize(int newSize) {
        T[] newItems = (T[]) new Object[newSize];
        int newNextFirst = 0;
        int newNextLast = 1;
        for (int i = 0; i < size; i++) {
            newItems[newNextLast] = get(i);
            newNextLast = (newNextLast + 1) % newSize;
        }
        // update current state
        this.items = newItems;
        this.nextFirst = newNextFirst;
        this.nextLast = newNextLast;
    }
}
