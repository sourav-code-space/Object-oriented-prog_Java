package HighwayToll.test;


import static HighwayToll.test.Tests.runAllTests;

public class MasterTest {

    public static void main(String[] args) {
        runAllTests(TestExit.class);
        runAllTests(TestTollRecord.class);
        runAllTests(TestTollRoadDatabase.class);
    }
}
