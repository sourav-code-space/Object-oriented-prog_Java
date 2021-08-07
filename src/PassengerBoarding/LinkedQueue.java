/*
 *
 * LinkedQueue.java
 *
 * Version : 1.0		Date :- 02/11/2020
 *
 * Revision : $Log$
 *
 */
package PassengerBoarding;

public class LinkedQueue implements PriorityQueue {
	/**
	 * A priority queue using a linkedList.  Assumes that smaller numbers are higher
	 * priority and therefore this is a min-heap (smallest value on top).
	 *
	 * @author Sourav Khan	(sk9675@rit.edu)
	 */
	private PassengerList<Passenger> theLinkedHeap;


    /**
     * The constructor for the LinkedQueue
     */
	public LinkedQueue() {
		theLinkedHeap = new PassengerList<Passenger>();
	}

	
    /**
     * Computes the child with max priority - but also counting the parent,
     * so that if both children are smaller than the parent, -1 is returned.
     * Also returns -1 if there are no children of the given index
     *
     * @param index the current working index
     * @return index of child with largest priority, or -1
     */
	
	private int maxChild(int index) {
		int maxc = -1;
		// children of index are 2 * index + 1, 2 * index + 2
		// if first child exists, is it higher than me?
		if (2 * index + 1 < theLinkedHeap.size()) {
			Passenger item = theLinkedHeap.get(index);
			Passenger childOne = theLinkedHeap.get(2 * index + 1);
			if (childOne.compareTo(item) > 0) {
				maxc = 2 * index + 1;
			}
			// how about a second child?
			if (2 * index + 2 < theLinkedHeap.size()) {
				Passenger childTwo = theLinkedHeap.get(2 * index + 2);
				if (childTwo.compareTo(childOne) > 0) {
					maxc = 2 * index + 2;
				}
			}
		}
		return maxc;
	}

    /**
     * Add an item to the queue at the appropriate location
     *
     * @param toInsert the passenger to insert in the queue
     */
	@Override
	public void enqueue(Passenger toInsert) {
		// TODO Auto-generated method stub
		theLinkedHeap.add(toInsert);
		int whereamI = theLinkedHeap.size() - 1;
		int parentLoc = whereamI / 2;
		while ((whereamI > 0) && (theLinkedHeap.get(whereamI).compareTo(theLinkedHeap.get(parentLoc)) > 0)) {
			Passenger temp = theLinkedHeap.get(whereamI);
			theLinkedHeap.set(whereamI, theLinkedHeap.get(parentLoc));
			theLinkedHeap.set(parentLoc, temp);
			whereamI = parentLoc;
			parentLoc = whereamI / 2;
		}
	}

    /**
     * Removes and returns the item at the front of the queue
     *
     * @return Passenger the item at the front of the queue otherwise
     * returns a null
     */
	@Override
	public Passenger dequeue() {
		// TODO Auto-generated method stub
		Passenger toReturn = null;
		if (!theLinkedHeap.isEmpty()) {
			// keep the top of the heap to return later
			toReturn = theLinkedHeap.get(0);
			// if that wasn't the last one, take end of the heap to
			// the root and bubble it down.
			if (theLinkedHeap.size() > 1) {
				theLinkedHeap.set(0, theLinkedHeap.remove(theLinkedHeap.size() - 1));
				int maxc = 0;
				int whereamI = 0;
				while ((maxc = maxChild(whereamI)) != -1) {
					// bubbling down
					Passenger temp = theLinkedHeap.get(whereamI);
					theLinkedHeap.set(whereamI, theLinkedHeap.get(maxc));
					theLinkedHeap.set(maxc, temp);
					whereamI = maxc;
				}
			} else {
				// simply remove the root and be happy!
				theLinkedHeap.remove(0);
			}
		}
		return toReturn;
	}

    /**
     * Is there anything in the queue?
     *
     * @return true if the queue is empty; otherwise returns false
     */
	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return theLinkedHeap.isEmpty();
	}

}
