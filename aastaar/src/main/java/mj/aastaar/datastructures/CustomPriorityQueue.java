package mj.aastaar.datastructures;

import mj.aastaar.map.Node;

/**
 *
 * @author MJ
 */
public class CustomPriorityQueue {

    private int heapSize;
    private Node[] heap;

    public CustomPriorityQueue(int maxSize) {
        heapSize = 0;
        //if (maxSize < 1) maxSize = 2; // pointless to have size < 2 heap
        heap = new Node[maxSize + 1];
        heap[0] = new Node(-1, -1, Integer.MIN_VALUE);
    }

    public int getHeapSize() {
        return heapSize;
    }

    public Node heapMin() {
        return heap[1];
    }

    public Node heapDelMin() {
        Node head = heap[1];
        if (heapSize > 0) {
            heap[1] = heap[heapSize];
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
        while (current > 1) {
            int parent = parent(current);
            while (heap[current].getPriority() < heap[parent].getPriority()) {
                swap(current, parent);
            }
            current = parent;
        }
    }

    private void percolateDown() {
        int current = 1;
        while (true) {
            int leftChild = leftChild(current);
            int rightChild = rightChild(current);
            Node leftChildNode = heap[leftChild];
            Node rightChildNode = heap[rightChild];

            if (leftChild > heapSize) {
                break;
            }
            // TODO: refactor getmin
            int min = leftChild;
            if (rightChild(current) <= heapSize) {
                if (rightChildNode.getPriority() < leftChildNode.getPriority()) {
                    min = rightChild;
                }
            }
            if (heap[current].getPriority() <= heap[min].getPriority()) {
                break;
            }
            swap(current, min);
            current = min;
        }
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
