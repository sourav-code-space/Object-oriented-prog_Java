/*
 *
 * PassengerList.java
 *
 * Version : 1.0		Date :- 02/11/2020
 *
 * Revision : $Log$
 *
 */

package PassengerBoarding;

/**
 * This class provides the detailed implementation for the alternative underlying 
 * Structure to simulate airline passenger boarding system.
 * 
 * @author Sourav Khan	(sk9675@rit.edu)
 *
 * 
 */
public class PassengerList<Passenger> {
    private int size = 0;

    /**
     * Pointer to first node.
     */
    private PassengerNode<Passenger> first;

    /**
     * Pointer to last node.
     */
    private PassengerNode<Passenger> last;

    /**
     * 	default constructor
     */
    public PassengerList() {
    }
    
    /**
     * This method checks whether the list is empty or not.  
     * 
     * @return boolean 
     */
    public boolean isEmpty(){
        if(this.size()==0)
            return true;
        else
            return false;

    }
    
    /**
     * This method return the size of the list.
     * 
     * return size			size of the list.
     */
    public int size() {
        return size;
    }
    
    /**
     * This method ensure to add passenger object to the front of the list.
     * 
     * @param  Passenger e			passenger object
     */
    private void linkFirst(Passenger e) {
        final PassengerNode<Passenger> f = first;
        final PassengerNode<Passenger> newNode = new PassengerNode<Passenger>(null, e, f);
        first = newNode;
        if (f == null)
            last = newNode;
        else
            f.prev = newNode;
        size++;
        //modCount++;
    }
    
    /**
     * This method ensure to add passenger object to the end of the list
     * 
     * @param Passenger e			passenger object
     * 
     */
    void linkLast(Passenger e) {
        final PassengerNode<Passenger> l = last;
        final PassengerNode<Passenger> newNode = new PassengerNode<Passenger>(l, e, null);
        last = newNode;
        if (l == null)
            first = newNode;
        else
            l.next = newNode;
        size++;
        //modCount++;
    }
    
    /**
     * This method simply adds the passenger object the last of the list.
     */
    public boolean add(Passenger e) {
        linkLast(e);
        return true;
    }

    /**
     * This method generates an error message.
     * 
     * @return String			message
     */
    private String outOfBoundsMsg(int index) {
        return "Index: "+index+", Size: "+size;
    }

    /**
     * This method checks and return a node at particular index
     * 
     * @ return		PassengerNode<Passenger>
     */
    PassengerNode<Passenger> node(int index) {
        // assert isElementIndex(index);

        if (index < (size >> 1)) {
            PassengerNode<Passenger> x = first;
            for (int i = 0; i < index; i++)
                x = x.next;
            return x;
        } else {
            PassengerNode<Passenger> x = last;
            for (int i = size - 1; i > index; i--)
                x = x.prev;
            return x;
        }
    }

    /**
     * This method removes a node from the list by unlinking its prev and
     * next data.
     * 
     *  @return passenger		passenger object
     */
    Passenger unlink(PassengerNode<Passenger> x) {
        // assert x != null;
        final Passenger element = x.item;
        final PassengerNode<Passenger> next = x.next;
        final PassengerNode<Passenger> prev = x.prev;

        if (prev == null) {
            first = next;
        } else {
            prev.next = next;
            x.prev = null;
        }

        if (next == null) {
            last = prev;
        } else {
            next.prev = prev;
            x.next = null;
        }

        x.item = null;
        size--;
        //modCount++;
        return element;
    }


    private boolean isElementIndex(int index) {
        return index >= 0 && index < size;
    }

    /**
     * This method checks whether element belongs to the list or not by 
     * taking help of indexes. 
     */
    private void checkElementIndex(int index) {
        if (!isElementIndex(index))
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }

    /**
     * This method checks for the index and invokes unlink method.
     */
    public Passenger remove(int index) {
        checkElementIndex(index);
        return unlink(node(index));
    }

    /**
     * This method upon passed valid index returns a passenger object.
     * @param index
     * 
     */
    public Passenger get(int index) {
        checkElementIndex(index);
        return node(index).item;
    }

    /**
     * This method is responsible to set passenger object at particular index in a list.
     * But before doing that it checks for the index validity. 
     * @param index
     * @param element
     * 
     */
    public Passenger set(int index, Passenger element) {
        checkElementIndex(index);
        PassengerNode<Passenger> x = node(index);
        Passenger oldVal = x.item;
        x.item = element;
        return oldVal;
    }
}
