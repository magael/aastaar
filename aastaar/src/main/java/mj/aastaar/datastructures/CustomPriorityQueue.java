package mj.aastaar.datastructures;

import mj.aastaar.map.Node;

/**
 * A custom implementation of the priority queue data structure, using a binary
 * min-heap.
 *
 * @author MJ
 */
public class CustomPriorityQueue {

    private int heapSize;
    private Node[] heap;
    private final int ROOT = 1;
    private final int DEFAULT_SIZE = 11; // or 513 or?

    /**
     * Using the default initial heap size. The heapSize variable holds the
     * index of the current last element of the heap. The first element in the
     * array is not used as part of the min-heap.
     *
     */
    public CustomPriorityQueue() {
        heapSize = 0;
        heap = new Node[DEFAULT_SIZE];
        heap[0] = new Node(-1, -1, Integer.MIN_VALUE);
    }

    /**
     * Specifying the initial heap size. The heapSize variable holds the index
     * of the current last element of the heap. The first element in the array
     * is not used as part of the min-heap.
     *
     * @param maxSize Maximum amount of elements that fit in the heap.
     */
    public CustomPriorityQueue(int maxSize) {
        heapSize = 0;
        heap = new Node[maxSize + 1];
        heap[0] = new Node(-1, -1, Integer.MIN_VALUE);
    }

    /**
     *
     * @return Heap size
     */
    public int getHeapSize() {
        return heapSize;
    }

    /**
     *
     * @return True if the heap is empty, otherwise false
     */
    public boolean isEmpty() {
        return heapSize <= 0;
    }

    /**
     *
     * @return The root of the heap
     */
    public Node heapMin() {
        return heap[ROOT];
    }

    /**
     * Polling the priority queue for the smallest priority Node in the heap.
     * Inserts the former last element as the new root, decreases the heap size
     * by one and rearranges the heap starting with the new root downwards.
     *
     * @return The root of the heap
     */
    public Node heapDelMin() {
        Node head = heap[ROOT];
        if (heapSize > 0) {
            heap[ROOT] = heap[heapSize--];
            percolateDown(ROOT);
        } else {
            System.out.println("The heap is empty.");
        }
        return head;
    }

    /**
     * Adding a Node to the heap calling for resize, if necessary. The heap size
     * is incremented by one, the new element is added as the last element and
     * the heap is rearranged from bottom up.
     *
     * @param node The node to be inserted
     */
    public void heapInsert(Node node) {
        if (heapSize >= heap.length - 1) {
            resize();
        }
        heap[++heapSize] = node;
        percolateUp();
    }

    /**
     * Swapping elements upwards in the binary tree of the heap until every
     * element satisfies the heap property constraint, starting from the last
     * element.
     */
    private void percolateUp() {
        int current = heapSize;
        int parent = parent(current);
        while (heap[current].getPriority() < heap[parent].getPriority()) {
            swap(current, parent);
            current = parent;
            parent = parent(current);
        }
    }

    /**
     * Swapping elements downwards in the binary tree of the heap until every
     * element satisfies the heap property constraint, starting from the
     * provided element.
     *
     * @param current The starting point for rearranging the heap
     */
    private void percolateDown(int current) {
        if (leftChild(current) <= heapSize) {
            int min = minPriority(leftChild(current), rightChild(current));

            if (heap[current].getPriority() > heap[min].getPriority()) {
                swap(current, min);
                percolateDown(min);
            }
        }
    }

    /**
     * Comparing the priorities of two sibling nodes in the binary tree.
     *
     * @param left The index of the leftmost sibling.
     * @param right
     * @return The right node index if it is in heap bounds and has a smaller
     * priority than the left one, otherwise the left index.
     */
    private int minPriority(int left, int right) {
        if (right <= heapSize
                && heap[right].getPriority() < heap[left].getPriority()) {
            return right;
        }
        return left;
    }

    /**
     * Swapping two elements of the heap.
     *
     * @param i The index of one of the elements to be swapped.
     * @param j The index of the other element to be swapped.
     */
    private void swap(int i, int j) {
        Node temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    /**
     * Doubling the capacity of the heap.
     */
    private void resize() {
        Node[] newHeap = new Node[heap.length * 2];
        for (int i = 1; i < heapSize + 1; i++) {
            newHeap[i] = heap[i];
        }
        heap = newHeap;
    }

    /**
     * Determining the parent element of an element in the heap.
     *
     * @param i The index of the requested element's child.
     * @return The parent's index.
     */
    private int parent(int i) {
        return (int) (i / 2);
    }

    /**
     * Determining the leftmost child of an element in the heap.
     *
     * @param i The index of the requested element's parent.
     * @return The child's index.
     */
    private int leftChild(int i) {
        return 2 * i;
    }

    /**
     * Determining the rightmost child of an element in the heap.
     *
     * @param i The index of the requested element's parent.
     * @return The child's index.
     */
    private int rightChild(int i) {
        return (2 * i) + 1;
    }
}
