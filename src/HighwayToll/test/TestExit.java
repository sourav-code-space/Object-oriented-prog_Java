package HighwayToll.test;
import HighwayToll.Exit;

import static PassengerBoarding.test.Tests.assertEquals;

public class TestExit {
    private static int exitNum=100;
    private static String name="West_Exit";
    private static double mileMarker=300.0;
    private static Exit obj;
    public static void testConstructor(){
        obj = new Exit(exitNum,name,mileMarker);
        assertEquals(name,obj.getName());
        assertEquals(exitNum,obj.getExitNum());
        assertEquals(mileMarker,obj.getMileMarker());
    }
}
