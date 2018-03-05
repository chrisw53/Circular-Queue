import java.util.*;

public class CircularArrayRing<Integer> extends AbstractCollection implements Ring {
    private int size;
    private int[] myArray = new int[0];
    private int addCounter = 0;
    private boolean full = false;

    public CircularArrayRing (int size) {
        this.size = size;
    }

    public void add(int value) {
        if ((myArray.length == 0 || addCounter < size) && !full) {
            myArray = Arrays.copyOf(
                    myArray,
                    myArray.length + 1
            );
            myArray[addCounter] = value;
            addCounter++;
            if (addCounter == size) {
                full = true;
                addCounter = 0;
            }
        } else {
            myArray[addCounter] = value;
            addCounter++;
        }
    }

    public Integer get(int index) {
        int actualIndex = myArray.length - 1 - index;
        if (actualIndex > myArray.length - 1) {
            throw new IndexOutOfBoundsException("Index " + actualIndex + "is out of bound");
        } else {
            Integer output = new Integer(myArray[actualIndex]);
            return output;
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
}
