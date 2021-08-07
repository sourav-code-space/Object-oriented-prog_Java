
/*
 *
 * Woolie.java
 *
 * Version : 1.0		Date :- 04/13/2020
 *
 * Revision : $Log$
 *
 */
package TrollsBridge;
/**
 * The Woolie simulates a Woolie crossing a TrollsBridge.
 * Each woolie object is constructed with a name, length of time it takes the
 * woolie to cross a bridge, a destination city, and a reference to a TrollsBridge
 * whose troll coordinates how woolies get on and off their bridge.
 *
 * A Woolie extends the Thread class and executes as an active object.
 *
 * Before crossing, a woolie must ask the troll guarding the bridge for permission
 * to cross. After the troll grants permission, the woolie begins crossing the bridge.
 * After reaching the other side, the woolie must call leave to notify the troll
 * and everyone that it is no longer on the bridge.
 *
 * @author Sourav Khan(sk9675@rit.edu)
 */
public class Woolie extends Thread {

	private String name;
	private int crossTime;
	private String destination;
	private TrollsBridge bridgeGuard;

	public Woolie(String name, int crossTime, String destination, TrollsBridge bridgeGuard) {
		super(name);

		if (crossTime >= 0) {
			this.crossTime = crossTime;
		}
		if (destination != null) {
			this.destination = destination;
		}
		if (bridgeGuard != null) {
			this.bridgeGuard = bridgeGuard;
		}
	}
	/**
	 * Getter method for crossTime.
	 */
	public int getCrossTime() {
		return crossTime;
	}
	/**
	 * Getter method for destination.
	 */
	public String getDestination() {
		return destination;
	}
	/**
	 * This getter method returns the TrollsBridge object.
	 */
	public TrollsBridge getBridgeGuard() {
		return bridgeGuard;
	}

	/**
	 * This method depicts the tasks of each Woolie when they get a chance to
	 * cross the bridge.
	 */
	public void run() {
		bridgeGuard.enterBridgePlease(this);
		for (int i = 1; i <= this.getCrossTime(); i++) {
			try {
				sleep(1000);
				System.out.println("\t" + this.getName() + " " + i + " seconds.");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		this.bridgeGuard.queue.remove(this);
		this.bridgeGuard.removedWoolie = this;
		this.bridgeGuard.leave();
		
	}

}
