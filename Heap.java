import java.util.ArrayList;
import java.util.Collections;

/**
 * Heap Class - Heap data structure
 * @param <T> The type of the object in the heap
 * @author Sergio Vasquez
 */
public class Heap<T extends Comparable<T>> {

    /**
     * Container of the elements
     */
    private ArrayList<T> heap;

    /**
     * Constructor - Creates a new Heap Object
     */
    public Heap() {
        heap = new ArrayList<T>();
    }

    /**
     * Retrieve's the size of the heap
     * @return the size of the heap
     */
    public int size() {
        return heap.size();
    }

    /**
     * Check if the heap is empty
     * @return true if the heap is empty
     */
    public boolean isEmpty() {
        return heap.isEmpty();
    }

    /**
     * Return the highest priority item in the heap
     * @return the highest priority item in the heap
     */
    public T getCurrent() {
        return heap.get(0);
    }

    /**
     * Return the parent of the indicated index
     * @param i the index to determine the parent of
     * @return the parent index of the index i
     */
    private int getPlop(int i) {
        return (i - 1) / 2;
    }

    /**
     * Return the left child of the indicated index
     * @param i the index to find the left child of
     * @return the left child of the specified index
     */
    private int getLCloc(int i) {
        return 2 * i + 1;
    }

    /**
     * Return the right child of the indicated index
     * @param i the index to find the right child of
     * @return the right child of the specified index
     */
    private int getRCloc(int i) {
        return 2 * i + 2;
    }

    /**
     * Adds item to the heap
     * @param item the item to the be added to the heap
     */
    public void addItem(T item) {
        heap.add(item);
        int index = heap.size() - 1;
        while (index > 0 && heap.get(index).compareTo(heap.get(getPlop(index))) < 0) {
            Collections.swap(heap, index, getPlop(index));
            index = getPlop(index);
        }
    }

    /**
     * Retrieve's the content of the heap as a string
     * @return the content of the heap as a string
     */
    @Override
    public String toString() {
        String s = "";
        for (int i = 0; i < heap.size(); ++i) {
            s += heap.get(i) + " ";
        }
        return s;
    }

    /**
     * Remove the highest priority item from the heap
     * @return the highest priority item from the heap
     */
    public T removeItem() {
        T min = heap.get(0);
        int index = heap.size() - 1;
        T last = heap.remove(index);
        if (index > 0) {
            heap.set(0, last);
            T root = heap.get(0);
            int end = heap.size() - 1;
            index = 0;
            boolean done = false;
            while (!done) {
                if (getLCloc(index) <= end) {
                    T child = heap.get(getLCloc(index));
                    int childLoc = getLCloc(index);
                    if (getRCloc(index) <= end) {
                        if (heap.get(getRCloc(index)).compareTo(child) < 0) {
                            child = heap.get(getRCloc(index));
                            childLoc = getRCloc(index);
                        }
                    }
                    if (child.compareTo(root) < 0) {
                        heap.set(index, child);
                        index = childLoc;
                    } else {
                        done = true;
                    }
                } else {
                    done = true;
                }
            }
            heap.set(index, root);
        }
        return min;
    }
}

