package ParkingSystem.test;

import static ParkingSystem.test.Tests.*;

/**
 * Tests the {@link Dog} class to make sure that it is working properly.
 */
public class DogTest {

    /**
     * Test that verifies that the {@link Dog} constructor properly sets the
     * name, age, and weight of a new dog.
     */
    public static void testConstructor() {
        String name = "Buttercup";
        double weight = 63.0;
        int age = 8;

        Dog dog = new Dog(name, weight, age);

        assertEquals(name, dog.getName());
        assertEquals(weight, dog.getWeight());
        assertEquals(age, dog.getAge());
    }

    /**
     * Test that verifies that a {@link Dog} gains the appropriate amount of
     * weight after consuming calories.
     */
    public static void testEat() {
        double weight = 63.0;
        double calories = 4500.0;

        Dog dog = new Dog("Buttercup", weight, 8);

        dog.eat(calories);

        assertEquals(weight + 1.5, dog.getWeight());
    }

    /**
     * Test that verifies that a {@link Dog} does not lose weight when
     * consuming negative calories.
     */
    public static void testEatNegativeCalories() {
        double weight = 63;
        double calories = -3000;

        Dog dog = new Dog("Buttercup", weight, 8);

        dog.eat(calories);

        assertEquals(weight, dog.getWeight());
    }

    /**
     * Test that verifies that a {@link Dog} that walks loses the appropriate
     * amount of weight.
     */
    public static void testWalk() {
        double weight = 63;
        double distance = 1.5;

        Dog dog = new Dog("Buttercup", weight, 8);

        dog.walk(distance);

        assertEquals(weight - (distance * 100 / 3000), dog.getWeight());
    }

    /**
     * Test that verifies that a {@link Dog} that walks a negative distance
     * does not lose weight.
     */
    public static void testWalkNegativeDistance() {
        double weight = 63.0;
        double distance = -1.5;

        Dog dog = new Dog("Buttercup", weight, 8);

        dog.walk(distance);

        assertEquals(weight, dog.getWeight());
    }

    /**
     * Test that verifies that a {@link Dog} that has a birthday ages by one
     * year.
     */
    public static void testBirthday() {
        int age = 8;
        Dog dog = new Dog("Buttercup", 63.0, age);

        dog.birthday();

        assertEquals(age+1, dog.getAge());
    }

    /**
     * Test that verifies that the {@link Dog#toString()} method returns a
     * string in the correct format.
     */
    public static void testToString() {
        Dog dog = new Dog("Buttercup", 63, 8);

        String expected = "Dog[name=Buttercup, weight=63.0, age=8]";

        assertEquals(expected, dog.toString());
    }

    /**
     * Test that verifies that two {@link Dog Dogs} that should be equal are
     * in fact equal.
     */
    public static void testEquals() {
        Dog dog1 = new Dog("Buttercup", 63, 8);
        Dog dog2 = new Dog("Buttercup", 63, 8);

        assertEquals(dog1, dog2);
    }

    /**
     * Test that verifies that two {@link Dog Dogs} that should be equal are
     * in fact equal.
     */
    public static void testEqualsFalse() {
        Dog dog1 = new Dog("Buttercup", 63, 8);
        Dog dog2 = new Dog("Thunder", 60, 13);

        assertNotEquals(dog1, dog2);
    }


    /**
     * Runs all of the tests in this class.
     *
     * @param args Command line arguments. Ignored.
     */
    public static void main(String[] args) {
        runAllTests(DogTest.class);
    }
}
