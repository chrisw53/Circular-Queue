import java.util.*;
import java.lang.*;

public class CircularArrayRing<T> extends AbstractCollection implements Ring {
    private int size;
    // Instantiates an instance of array with the given type parameter
    private T[] myArray = (T[])new Object[0];
    private int addCounter = 0;
    // This keeps track of the lead index
    private int headIndex;
    private boolean reversed = false;

    /**
     * Default size set as 1 if no size is set
     */
    public CircularArrayRing () {
        this.size = 1;
    }

    /**
     * Constructor that sets the private variable
     * size
     * @param size an integer representing the max
     *             size of the array ring
     */
    public CircularArrayRing (int size) {
        this.size = size;
    }

    /**
     * Method that handles adding a new value to the
     * array ring.
     * @param value An object that is later casted to
     *              the type given by the type parameter
     *              representing the new value to be added
     * @return returns a boolean because otherwise
     * I wouldn't be able to override the add method
     * in AbstractCollection class
     */
    @Override
    public boolean add(Object value) {
        if (myArray.length < size) {
            /*
             If the array length is smaller than the
             max size, expand it by 1 and add the new
             value to the tail
            */
            myArray = Arrays.copyOf(
                    myArray,
                    myArray.length + 1
            );
        }

        headIndex = addCounter % size;
        myArray[headIndex] = (T)value;
        addCounter++;
        return true;
    }

    /**
     * A getter method that grabs the last added element
     * from the array ring.
     * @param index an integer representing the index offset
     *              from the last added element
     * @return returns the value of the element at the index
     * calculated by taking away the index offset from the
     * headIndex variable.
     */
    public T get(int index) {
        // Checks if the index offset is larger than the array length
        if (index > myArray.length - 1) {
            throw new IndexOutOfBoundsException("Index " + index + "is out of bound");
        } else {
            int actualIndex = headIndex - index;

            // Normalizes actualIndex
            if (actualIndex < 0) {
                actualIndex += size;
            }

            return myArray[actualIndex];
        }
    }

    /**
     * @return This method returns an iterator that iterates
     * the array ring backwards from the last added element
     */
    public Iterator<T> iterator() {
        // This reverses the list
        List myList = Arrays.asList(myArray);

        /*
        This checks if the list has already been
        reversed
        */
        if (!reversed) {
            Collections.reverse(myList);
            reversed = true;
        }

        Iterator<T> myIterator = myList.iterator();
        return myIterator;
    }

    /**
     * @return This returns the size of the array ring
     */
    public int size() {
        return myArray.length;
    }
}
