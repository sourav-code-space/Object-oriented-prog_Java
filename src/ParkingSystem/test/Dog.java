package ParkingSystem.test;

/**
 * A class that represents a {@link Dog}. This class is not used in the RIT
 * parking simulation, but is instead provided as an example for unit testing.
 */
public class Dog {
    /**
     * The name of the dog.
     */
    private String name;

    /**
     * The current weight of the dog.
     */
    private double weight;

    /**
     * The dog's current age.
     */
    private int age;

    /**
     * Initializing constructor for a dog.
     *
     * @param name The new dog's name.
     * @param weight The new dog's weight.
     * @param age The new dog's age.
     */
    public Dog(String name, double weight, int age) {
        this.name = name;
        this.weight = weight;
        this.age = age;
    }

    /**
     * Returns the dog's name.
     *
     * @return The name of the dog.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the dog's current weight.
     *
     * @return The dog's current weight.
     */
    public double getWeight() {
        return weight;
    }

    /**
     * Returns the dog's current age.
     *
     * @return The dog's current age.
     */
    public int getAge() {
        return age;
    }

    /**
     * The dog consumes the specified number of calories and gains weight at
     * a rate of 1 pound per 3000 calories.
     *
     * @param calories The number of calories to be consumed by the dog.
     */
    public void eat(double calories) {
        if(calories > 0) {
            weight += (calories / 3000.0f);
        }
    }

    /**
     * The dog walks the specified distance (in miles). Burns calories at a
     * rate of 100 per mile (and loses the appropriate amount of weight as a
     * result).
     *
     * @param distance The distance to walk the dog.
     */
    public void walk(double distance) {
        if(distance > 0) {
            double calories = distance * 100;
            weight -= (calories / 3000);
        }
    }

    /**
     * Ages the dog by one year.
     */
    public void birthday() {
        age++;
    }

    @Override
    public String toString() {
        return "Dog[name=" + this.name +
                ", weight=" + this.weight +
                ", age=" + this.age + "]";
    }

    @Override
    public boolean equals(Object o) {
        if(o instanceof Dog) {
            Dog dog = (Dog)o;
            return this.name.equals(dog.getName()) &&
                    this.weight == dog.weight &&
                    this.age == dog.age;
        } else {
            return false;
        }
    }
}
