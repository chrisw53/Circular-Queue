import java.util.NoSuchElementException;

public class CircularArrayQueue implements MyQueue {

    /**
     Pointers @queueHead and @queue tail track structure of queue
     intQueue: tracks data, does not look at order
     elementCount: tracks number of elements in constant time
     */
    private int[] intQueue = new int[5];
    private int queueHead = 0;
    private int queueTail = 0;
    private int elementCount = 0;

    /**
     This method appends a new value to the front of the list and
     updates the tail pointer
     */
    @Override
    public void enqueue(int in) {
        // Check for capacity left looks if resize is required.
        if (getCapacityLeft() == 0) {
            int[] resizeArr = new int[intQueue.length * 2];
            /*
             Array is copied into two sections, head to end and beginning to tail.
             As array is circular order of values must be maintained by accounting for head and tail pointers
              */
            System.arraycopy(intQueue, queueHead, resizeArr, 0, intQueue.length - queueTail);
            System.arraycopy(intQueue, 0, resizeArr, intQueue.length - queueTail, queueHead);
            // Head and tail are updated to accommodate the new array
            queueTail = intQueue.length;
            intQueue = resizeArr;
            queueHead = 0;
        }

        // Code for appending new value to location of tail pointer
        intQueue[queueTail] = in;
        queueTail = (queueTail + 1) % intQueue.length;
        elementCount++;

    }

    /**
     Method removes least recent value from the head pointer, allowing
     for first in first out structure.
     */
    @Override
    public int dequeue() throws NoSuchElementException {
        int out;
        // Check if an element can be removed to prevent underflow error
        if (isEmpty()) {
            throw new NoSuchElementException();
        } else {
            // Code for removing the least recent value from the queue and
            // update the head pointer
            out = intQueue[queueHead];
            queueHead = (queueHead + 1) % intQueue.length;
            elementCount--;
        }
        // Return statement prints any value found from the location
        // of the head pointer if error not thrown
        return out;
    }

    /**
     Accessor method returns number of elements in the queue
     */
    @Override
    public int noItems() {
        return elementCount;
    }

    /**
     Method checks if queue is empty by looking at the number of elements in the queue
     */
    @Override
    public boolean isEmpty() {
        return (elementCount == 0);
    }

    /**
     Method returns number of spaces left in queue
     */
    public int getCapacityLeft() {
        // Calculation taken from the max number of elements take the
        // number of elements to return unallocated space
        return intQueue.length - noItems();
    }
}
