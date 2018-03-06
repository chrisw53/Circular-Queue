import java.util.*;
import java.lang.*;

public class CircularArrayRing<T> extends AbstractCollection implements Ring {
    private int size;
    private T[] myArray = (T[])new Object[0];
    private int addCounter = 0;
    private boolean full = false;

    public CircularArrayRing () {
        this.size = 1;
    }

    public CircularArrayRing (int size) {
        this.size = size;
    }

    @Override
    public boolean add(Object value) {
        if (addCounter < size && !full) {
            myArray = Arrays.copyOf(
                    myArray,
                    myArray.length + 1
            );
            myArray[addCounter] = (T)value;
            addCounter++;
            if (addCounter == size) {
                full = true;
                addCounter = 0;
            }
        } else {
            if (addCounter == size) {
                addCounter = 0;
            }

            myArray[addCounter] = (T)value;
            addCounter++;
        }
        return true;
    }

    public T get(int index) {
        int actualIndex = myArray.length - 1 - index;
        if (actualIndex > myArray.length - 1) {
            throw new IndexOutOfBoundsException("Index " + actualIndex + "is out of bound");
        } else {
            return myArray[actualIndex];
        }
    }

    public Iterator<Integer> iterator() {
        List myList = Arrays.asList(myArray);
        Collections.reverse(myList);
        Iterator<Integer> myIterator = myList.iterator();
        return myIterator;
    }

    public int size() {
        return myArray.length;
    }

    public static void main(String[] args) {
        Ring<Integer> ring = new CircularArrayRing<Integer>(10);
        for(int i=0; i<12; ++i) {
            ring.add(i);
            for(int j=0; j<ring.size(); ++j) {
                int expected = i-j;
                int result = ring.get(j);
            }
        }
    }
}
