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

    public CustomPriorityQueue(int maxSize) {
        heapSize = 0;
        //if (maxSize < 1) maxSize = 2; // pointless to have size < 2 heap
        heap = new Node[maxSize + 1];
        heap[0] = new Node(-1, -1, Integer.MIN_VALUE);
    }

    public int getHeapSize() {
        return heapSize;
    }

    public boolean isEmpty() {
        return heapSize <= 0;
    }

    public Node heapMin() {
        return heap[ROOT];
    }

    public Node heapDelMin() {
        Node head = heap[ROOT];
        if (heapSize > 0) {
            heap[ROOT] = heap[heapSize];
            heapSize--;
            percolateDown();
        } else {
            System.out.println("The heap is empty.");
        }
        return head;
    }

    public void heapInsert(Node node) {
        if (heapSize + 2 < heap.length) {
            heapSize++;
            heap[heapSize] = node;
            percolateUp();
        } else {
            System.out.println("Not enough space in the heap.");
        }
    }

    private void percolateUp() {
        int current = heapSize;
        while (current > ROOT) {
            int parent = parent(current);
            while (heap[current].getPriority() < heap[parent].getPriority()) {
                swap(current, parent);
            }
            current = parent;
        }
    }

    private void percolateDown() {
        int current = ROOT;
        while (true) {
            int leftChild = leftChild(current);
            int rightChild = rightChild(current);

            if (leftChild > heapSize) {
                break;
            }

            int min = minPriority(leftChild, rightChild);

            if (heap[current].getPriority() <= heap[min].getPriority()) {
                break;
            }

            swap(current, min);
            current = min;
        }
    }

    private int minPriority(int leftChild, int rightChild) {
        Node left = heap[leftChild];
        Node right = heap[rightChild];

        if (rightChild <= heapSize && right.getPriority() < left.getPriority()) {
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
