package HighwayToll.test;

import HighwayToll.TollRoadDatabase;

import static HighwayToll.test.Tests.assertEquals;

public class TestTollRoadDatabase {
    private static TollRoadDatabase obj;
    public static void testCompleteTripCount(){
        obj=new TollRoadDatabase("src/hw06/data/small.txt");
        assertEquals("2 completed trips",obj.getCompleteTripCount());
    }
    public static void testSpeederReport(){
        String expected="Vehicle I_AM_STROOT, starting at time 61\n" +
                "\tfrom Rochester - Victor - I-490\n" +
                "\tto Syracuse - Fulton - I-690 - NY Route 690\n" +
                "\t 94.6 MpH\n";
        assertEquals(expected,obj.generateSpeederReport());
    }
    public static void testBillingInformation(){
        String expected="[       DR_J] on #45, time    60; off #39, time   150: $ 2.46\n" +
                "[I_AM_STROOT] on #45, time    61; off #39, time   100: $ 2.46\n" +
                "Total: $ 4.92";
        obj=new TollRoadDatabase("src/hw06/data/small.txt");
        assertEquals(expected,obj.generateBillingInformation());
    }
//    public static void testBillingInformationWithTag(){
//        String expected="[I_AM_STROOT] on #45, time    61; off #39, time   100: $ 2.46";
//        obj=new TollRoadDatabase("src/hw06/data/small.txt");
//        assertEquals(expected,obj.generateBillingInformation("I_AM_STROOT"));
//    }
    //public static void testGenerateExitReport(){
//        String expected="\nEXIT 45 REPORT\n" +
//                "==============\n" +
//                "[       DR_J] on #45, time    60; off #39, time   150\n" +
//                "[I_AM_STROOT] on #45, time    61; off #39, time   100\n" +
//                "[ BEN_KENOBI] on #45, time    62\n";
//        obj=new TollRoadDatabase("src/hw06/data/small.txt");
//        assertEquals(expected,obj.generateExitReport(45));
//    }
}
