package PassengerBoarding.test;
import PassengerBoarding.Passenger;

import static PassengerBoarding.test.Tests.assertEquals;
public class PassengerTest {
    static String name = "Harry Potter";
    static String boardingGroup= "A";
    static int sequenceNumber=10;


    static void testConstructor(){
        Passenger p = new Passenger(name,boardingGroup,sequenceNumber);
        assertEquals(name,p.getName());
        assertEquals(boardingGroup,p.getGroup());
        assertEquals(sequenceNumber,p.getSequence());
    }
    static void testCompareTo(){
        Passenger lp = new Passenger(name,"C",10);
        Passenger hp = new Passenger("Joker","A",1);
        assertEquals(1,lp.compareTo(hp));
    }
}
