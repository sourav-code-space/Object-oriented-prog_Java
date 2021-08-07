package PassengerBoarding.test;

import static PassengerBoarding.test.Tests.assertEquals;
import static PassengerBoarding.test.Tests.runAllTests;

public class MasterTest {

    public static void main(String[] args) {
        runAllTests(PassengerTest.class);
        runAllTests(LinkedQueueTest.class);
    }
}
