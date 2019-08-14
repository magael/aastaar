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
        heapSize = 1;
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
            minHeapify(1);
            heapSize--;
        } else {
            System.out.println("The heap is empty.");
        }
        return head;
    }

    public void heapInsert(Node node) {
        if (heapSize + 2 < heap.length) {
            heapSize++;
            heap[heapSize] = node;
            minHeapify(heapSize);
        } else {
            System.out.println("Not enough space in the heap.");
        }
    }

    private void minHeapify(int i) {
        int l = left(i);
        int r = right(i);
        int smallest;
        Node current = heap[i];
        Node left = heap[l];
        Node right = heap[r];

        if (r <= heapSize) {
            if (left.getPriority() < right.getPriority()) {
                smallest = l;
            } else {
                smallest = r;
            }
            if (current.getPriority() < heap[smallest].getPriority()) {
                swap(i, smallest);
                minHeapify(smallest);
            }
        } else if (l == heapSize && current.getPriority() < heap[l].getPriority()) {
            swap(i, l);
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

    private int left(int i) {
        return 2 * i;
    }

    private int right(int i) {
        return (2 * i) + 1;
    }
}
