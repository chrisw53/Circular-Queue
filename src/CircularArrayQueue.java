import java.util.NoSuchElementException;

public class CircularArrayQueue implements MyQueue {
    private int queueSize = 1;
    private int[] intQueue = new int[queueSize];
    private int queueHead = 0;
    private int queueTail = 0;

    @Override
    public void enqueue(int in) {
        if (getCapacityLeft() != 0) {
            if (queueTail + 1 > queueSize) {
                queueTail = (queueTail + 1) % queueSize;
                intQueue[queueTail] = in;
            } else {
                queueTail += 1;
                intQueue[queueTail] = in;
            }
        }
    }

    @Override
    public int dequeue() throws NoSuchElementException {
        int headValue = 0;
        if (noItems() != 0) {
            intQueue[queueHead] = headValue;
            if (queueHead + 1 > queueSize) {
                queueHead = (queueHead + 1) % queueSize;
            } else {
                queueHead += 1;
            }
        } else {
            throw new NoSuchElementException();
        }
        return headValue;
    }

    @Override
    public int noItems() {
        return queueSize - getCapacityLeft();
    }

    @Override
    public boolean isEmpty() {
        boolean listEmpty = false;

        if (queueHead == queueTail) {
            listEmpty = true;
        } else {
            listEmpty = false;
        }
        return listEmpty;
    }

    public int getCapacityLeft() {
        int spaceLeft = 0;

        for (int i = 0; i < queueSize; i++) {
            if (intQueue[i] == 0) {
                spaceLeft++;
            }
        }
        return spaceLeft;
    }
}
