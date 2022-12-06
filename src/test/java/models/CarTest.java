package models;

import junit.framework.TestCase;
import org.junit.Assert;

public class CarTest extends TestCase {

    Car car = new Car();
    Car mine = new Car("Tesla", "Model Y", 2021, "Blue", "THISISAVIN");

    public void testId() {
        int expected = 5;
        mine.setId(expected);
        Assert.assertEquals(expected, mine.getId());
    }

    public void testMake() {
        String expected = "Tezla";
        mine.setMake(expected);
        Assert.assertEquals(expected, mine.getMake());
    }

    public void testModel() {
        String expected = "Cybertruck";
        mine.setModel(expected);
        Assert.assertEquals(expected, mine.getModel());
    }

    public void testGetYear() {
        int expected = 2024;
        mine.setYear(expected);
        Assert.assertEquals(expected, mine.getYear());
    }

    public void testGetVin() {
        String expected = "YEAHRIGHT";
        mine.setVin(expected);
        Assert.assertEquals(expected, mine.getVin());
    }

    public void testColor() {
        String expected = "Stainless Steel";
        mine.setColor(expected);
        Assert.assertEquals(expected, mine.getColor());
    }
}