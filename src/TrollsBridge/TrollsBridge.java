/*
 *
 * TrollsBridge.java
 *
 * Version : 1.0		Date :- 04/13/2020
 *
 * Revision : $Log$
 *
 */
package TrollsBridge;

import java.util.Queue;

import java.util.concurrent.LinkedBlockingQueue;
/**
 * The TrollsBridge is a bridge guarded by a troll.
 *
 * @author Sourav Khan(sk9675@rit.edu)
 */
public class TrollsBridge {
	LinkedBlockingQueue<Woolie> queue;
	Woolie removedWoolie;
	public TrollsBridge(int max) {
		queue = new LinkedBlockingQueue<>(3);
	}
	/**
	 * Returns a queue with Woolies with their arrival sequence.
	 * @param
	 * @return Queue of Woolies
	 */
	public Queue<Woolie> getQueue() {
		return queue;
	}

	/**
	 * Request permission to go onto the troll's bridge.
	 * @param thisWoolie
	 */
	public synchronized void enterBridgePlease(Woolie thisWoolie) {

		System.out
				.println("The troll scowls \"Get in line!\" when " + thisWoolie.getName() + " shows up at the bridge.");

		System.out.println(thisWoolie.getName() + " is starting to cross.");

	}

	/**
	 * Tell the troll of a TrollsBridge that a woolie has left the bridge so
	 * that the troll can let other woolies get on if there is room.
	 */
	public synchronized void leave() {
		System.out.println(removedWoolie.getName() + " leaves at " + removedWoolie.getDestination() + ".");
	}

}
