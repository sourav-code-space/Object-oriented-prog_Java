package TrollsBridge;

import java.util.Timer;
import java.util.TimerTask;
/*
 * RunWoolies.java
 * Provided source file as a starter for a test suite.
 * See the todo text for what to complete.
 */
/**
 * Test the TrollsBridge and Woolies simulation. Test by creating a bunch of
 * Woolies and let them cross the TrollsBridge.
 *
 */
public class RunWoolies {
	static Timer timer;
	static TimerTask task;
	/**
	 * SIDE_ONE is Merctran.
	 */
	public final static String SIDE_ONE = "Merctran";

	/**
	 * SIDE_TWO is Sicstine.
	 */
	public final static String SIDE_TWO = "Sicstine";

	/**
	 * Command interface for collecting all the functions in this test suite. Single
	 * method is Command.execute().
	 */
	private interface Command {
		public void execute();
	}

	/**
	 * testSuite is the list of test cases.
	 */
	private static Command[] testSuite = { new Command() {
		public void execute() {
			RunWoolies.test0();
		}
	}, new Command() {
		public void execute() {
			RunWoolies.test1();
		}
	}, new Command() {
		public void execute() {
			RunWoolies.test2();
		}
	}, new Command() {
		public void execute() {
			RunWoolies.test3();
		}
	}, };

	/**
	 * TEST_COUNT is number of test cases.
	 */
	public final static int TEST_COUNT = testSuite.length;

	/**
	 * test0 is Test Scenario 0, an extremely simple, non-waiting test. test0
	 * provides an example template/pattern for writing a test case.
	 */
	static void test0() {

		System.out.println("Begin test0. ===============================\n");

		Thread init = Thread.currentThread(); // init spawns the Woolies

		// Create a TrollsBridge of capacity 3.
		TrollsBridge trollBridge = new TrollsBridge(3);

		// Set an optional, test delay to stagger the start of each woolie.
		int delay = 4000;

		// Create the Woolies and store them in an array.
		Thread peds[] = { 
				new Woolie("Al", 3, SIDE_ONE, trollBridge), 
				new Woolie("Bob", 4, SIDE_TWO, trollBridge), 
				};

		for (int j = 0; j < peds.length; ++j) {
			// Run them by calling their start() method.
			try {
				trollBridge.queue.put((Woolie) peds[j]);
				peds[j].start();
				Thread.sleep(delay);
			} catch (InterruptedException e) {
				System.err.println("Abort. Unexpected thread interruption.");
				break;
			}
		}
		// Now, the test must give the woolies time to finish their crossings.
		for (int j = 0; j < peds.length; ++j) {
			try {
				peds[j].join();
			} catch (InterruptedException e) {
				System.err.println("Abort. Unexpected thread interruption.");
				break;
			}
		}
		System.out.println("\n=============================== End test0.");
		return;
	}

	/**
	 * test1 is Test Scenario 1, another fairly simple simulation run. test1
	 * provides another example for writing a test case.
	 */
	static void test1() {

		System.out.println("Begin test1. ===============================\n");

		Thread init = Thread.currentThread(); // init spawns the Woolies

		// Create a TrollsBridge of capacity 3.
		TrollsBridge trollBridge = new TrollsBridge(3);

		int delay = 1000;

		// Create the Woolies and store them in an array.
		Thread peds[] = { 
				new Woolie("Al", 3, SIDE_ONE, trollBridge), 
				new Woolie("Bob", 2, SIDE_ONE, trollBridge),
				new Woolie("Cathy", 2, SIDE_TWO, trollBridge), 
				new Woolie("Doris", 3, SIDE_TWO, trollBridge),
				new Woolie("Edith", 3, SIDE_ONE, trollBridge), 
				new Woolie("Fred", 3, SIDE_TWO, trollBridge), 
				};
		
		for (int j = 0; j < peds.length; ++j) {
			// Run them by calling their start() method.
			try {
				trollBridge.queue.put((Woolie) peds[j]);
				peds[j].start();
				Thread.sleep(delay); // delay start of next woolie
			} catch (InterruptedException e) {
				System.err.println("Abort. Unexpected thread interruption.");
			}
		}
		// Now, the test must give the woolies time to finish their crossings.
		for (int j = 0; j < peds.length; ++j) {
			try {
				peds[j].join(); // wait for next woolie to finish
			} catch (InterruptedException e) {
				System.err.println("Abort. Unexpected thread interruption.");
			}
		}
		timer.cancel();
		System.out.println("\n=============================== End test1.");
	}

	/**
	 * TODO: document YOUR test case here.
	 */
	static void test2() {

		System.out.println("Begin test2. ===============================\n");

		Thread init = Thread.currentThread(); // init spawns the Woolies

		System.out.println("TODO: write a more involved test here.");
		//
		// Create a TrollsBridge of capacity 3.
		// Set an OPTIONAL, test delay to stagger the start of each woolie.
		// Create the Woolies and store them in an array.
		// Run them by calling their start() method.
		// Now, the test must give the woolies time to finish their crossings.
		//
		System.out.println("TODO: follow the pattern of the example tests.");
		// Create a TrollsBridge of capacity 3.
		TrollsBridge trollBridge = new TrollsBridge(3);

		int delay = 1000;

		// Create the Woolies and store them in an array.
		Thread peds[] = {
				new Woolie("Al", 3, SIDE_ONE, trollBridge),
				new Woolie("Bob", 2, SIDE_ONE, trollBridge),
				new Woolie("Cathy", 2, SIDE_TWO, trollBridge),
				new Woolie("Doris", 3, SIDE_TWO, trollBridge),
				new Woolie("Edith", 3, SIDE_ONE, trollBridge),
				new Woolie("Fred", 3, SIDE_TWO, trollBridge),
				new Woolie("Gwen", 5 , SIDE_ONE ,trollBridge),
				new Woolie("Harry",4,SIDE_TWO,trollBridge),
				new Woolie("Ian",2,SIDE_TWO,trollBridge),
				new Woolie("James",7,SIDE_ONE,trollBridge),
				};
		for (int j = 0; j < peds.length; ++j) {
			// Run them by calling their start() method.
			try {
				trollBridge.queue.put((Woolie) peds[j]);
				peds[j].start();
				Thread.sleep(delay); // delay start of next woolie
			} catch (InterruptedException e) {
				System.err.println("Abort. Unexpected thread interruption.");
			}
		}
		// Now, the test must give the woolies time to finish their crossings.
		for (int j = 0; j < peds.length; ++j) {
			try {
				peds[j].join(); // wait for next woolie to finish
			} catch (InterruptedException e) {
				System.err.println("Abort. Unexpected thread interruption.");
			}
		}

		System.out.println("\n=============================== End test2.");
	}

	/**
	 * TODO: document YOUR second test case here.
	 */
	static void test3() {

		System.out.println("Begin test3. ===============================\n");

		Thread init = Thread.currentThread(); // init spawns the Woolies

		// Create a TrollsBridge of capacity 3.
		TrollsBridge trollBridge = new TrollsBridge(3);

		int delay = 1000;

		// Create the Woolies and store them in an array.
		Thread peds[] = { 
				new Woolie("Alf", 7, SIDE_ONE, trollBridge),
				new Woolie("Bev", 4, SIDE_ONE, trollBridge),
				new Woolie("Cal", 6, SIDE_TWO, trollBridge),
				new Woolie("Deb", 3, SIDE_TWO, trollBridge),
				new Woolie("Eli", 3, SIDE_ONE, trollBridge),
				new Woolie("Fay", 2, SIDE_TWO, trollBridge),
				new Woolie("Gia", 4 , SIDE_ONE ,trollBridge),
				new Woolie("Hal",3,SIDE_TWO,trollBridge),
				new Woolie("Ira",3,SIDE_TWO,trollBridge),
				new Woolie("Kim",2,SIDE_TWO,trollBridge),
				};
		
		for (int j = 0; j < peds.length; ++j) {
			// Run them by calling their start() method.
			try {
				trollBridge.queue.put((Woolie) peds[j]);
				peds[j].start();
				Thread.sleep(delay); // delay start of next woolie
			} catch (InterruptedException e) {
				System.err.println("Abort. Unexpected thread interruption.");
			}
		}
		// Now, the test must give the woolies time to finish their crossings.
		for (int j = 0; j < peds.length; ++j) {
			try {
				peds[j].join(); // wait for next woolie to finish
			} catch (InterruptedException e) {
				System.err.println("Abort. Unexpected thread interruption.");
			}
		}

		System.out.println("TODO: write another, more involved test here.");

		
		
		System.out.println("\n=============================== End test3.");
	}

	/**
	 * Run all the tests in this test suite.
	 *
	 * @param args not used
	 */
	public static void main(String args[]) {

		for (int j = 0; j < TEST_COUNT; ++j) {
			testSuite[j].execute();
		}
	}

}
