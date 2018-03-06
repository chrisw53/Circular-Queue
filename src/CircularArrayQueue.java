import java.util.NoSuchElementException;

public class CircularArrayQueue implements MyQueue {

    private int[] intQueue = new int[5];
    private int queueHead = 0;
    private int queueTail = 0;
    private int elementCount = 0;

    @Override
    public void enqueue(int in) {
        if (getCapacityLeft() == 0) {
            int[] resizeArr = new int[intQueue.length * 2];

            System.arraycopy(intQueue, queueHead, resizeArr, 0, intQueue.length - queueTail);
            System.arraycopy(intQueue, 0, resizeArr, intQueue.length - queueTail, queueHead);
            queueHead = 0;
            queueTail = intQueue.length;
            intQueue = resizeArr;
        }

        queueTail = (queueTail + 1) % intQueue.length;
        intQueue[queueTail] = in;
        elementCount++;

    }

    @Override
    public int dequeue() throws NoSuchElementException {
        int out = 0;
        if (isEmpty()) {
            throw new NoSuchElementException();
        } else {
            queueHead = (queueHead + 1) % intQueue.length;
            out = intQueue[queueHead];
            elementCount--;
        }
        return out;
    }

    @Override
    public int noItems() {
        return elementCount;
        //return Math.abs(Math.abs(intQueue.length - queueHead) - Math.abs(intQueue.length - queueTail));
    }

    @Override
    public boolean isEmpty() {
        boolean listEmpty = false;

        if (elementCount == 0) {
            listEmpty = true;
        } else {
            listEmpty = false;
        }
        return listEmpty;
    }

    public int getCapacityLeft() {
        return intQueue.length - noItems();
    }
}
