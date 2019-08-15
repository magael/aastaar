package mj.aastaar.datastructures;

import mj.aastaar.map.Node;

/**
 *
 * @author MJ
 */
public class CustomPriorityQueue {

    private int heapSize;
    private Node[] heap;
    private final int ROOT = 1;

    /**
     *
     * @param maxSize
     */
    public CustomPriorityQueue(int maxSize) {
        heapSize = 0;
        //if (maxSize < 1) maxSize = 2; // pointless to have size < 2 heap
        heap = new Node[maxSize + 1];
        heap[0] = new Node(-1, -1, Integer.MIN_VALUE);
    }

    /**
     *
     * @return
     */
    public int getHeapSize() {
        return heapSize;
    }

    /**
     *
     * @return
     */
    public boolean isEmpty() {
        return heapSize <= 0;
    }

    /**
     *
     * @return
     */
    public Node heapMin() {
        return heap[ROOT];
    }

    /**
     *
     * @return
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
     *
     * @param node
     */
    public void heapInsert(Node node) {
        if (heapSize + 1 < heap.length) {
            heap[++heapSize] = node;
            percolateUp();
        } else {
            System.out.println("Not enough space in the heap.");
        }
    }

    private void percolateUp() {
        int current = heapSize;
        int parent = parent(current);
        while (heap[current].getPriority() < heap[parent].getPriority()) {
            swap(current, parent);
            current = parent;
            parent = parent(current);
        }
    }

    private void percolateDown(int current) {
        if (leftChild(current) <= heapSize) {
            int min = minPriority(leftChild(current), rightChild(current));
        
            if (heap[current].getPriority() > heap[min].getPriority()) {
                swap(current, min);
                percolateDown(min);
            }
        }
    }

    private int minPriority(int leftChild, int rightChild) {
        if (rightChild <= heapSize
                && heap[rightChild].getPriority() < heap[leftChild].getPriority()) {
            return rightChild;
        }
        return leftChild;
    }

    private void swap(int i, int j) {
        Node temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    private int parent(int i) {
        return (int) Math.floor(i / 2);
    }

    private int leftChild(int i) {
        return 2 * i;
    }

    private int rightChild(int i) {
        return (2 * i) + 1;
    }
}
