package HighwayToll.test;

import HighwayToll.TollRecord;

import static HighwayToll.test.Tests.assertEquals;

public class TestTollRecord {

    private static int time=30;
    // Vehicle license tag
    private static String tag="Sourav_Lambo";
    // Vehicle arrival or departure lane
    private static int exit=100;
    private static TollRecord obj123;
    public static void testConstructor(){
        obj123= new TollRecord(time,tag,exit);
        assertEquals(time,obj123.getTime());
        assertEquals(tag,obj123.getTag());
        assertEquals(exit,obj123.getExit());
    }
    public static void testEquals(){
        TollRecord other=new TollRecord(time,tag,exit);
        assertEquals(true,obj123.equals(other));
    }
    public static void testToString(){
        String output="[" + tag + "] on #" + exit + ", time\t" + time;
        assertEquals(obj123.toString(),output);
    }
    public static void testHashCode(){

    }
    public static void testCompareTo(){
        TollRecord other=new TollRecord(time+30,"test",exit);
        assertEquals(-1,obj123.compareTo(other));
    }
}
