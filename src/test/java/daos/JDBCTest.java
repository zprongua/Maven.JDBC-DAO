package daos;

import junit.framework.TestCase;
import models.Car;
import org.junit.Assert;

import java.util.List;
import java.util.Random;

public class JDBCTest extends TestCase {

    public void testCarById() {
        JDBC dao = new JDBC();

        Car car = dao.carById(2);

        Assert.assertEquals("Tesla", car.getMake());
    }

    public void testFindAllCars() {
        JDBC dao = new JDBC();

        List<Car> cars = dao.findAllCars();

        Assert.assertEquals(5, cars.size());
    }

    public void testUpdate() {
        JDBC dao = new JDBC();
        Car car = dao.carById(2);
        
        int j = new Random().nextInt(90-65 + 1) + 65;
        char randomLetter = (char) j;
        String newModel = "Model " + randomLetter;
        car.setModel(newModel);

        String expected = "Car has been updated in the database.";
        String actual = dao.update(car);

        Assert.assertEquals(expected, actual);
        Assert.assertEquals(newModel, car.getModel());
    }

    //combined test to allow the same car be added and removed
    public void testCreateAndDelete() {
        JDBC dao = new JDBC();

        Car car = new Car("Chevy", "Impala", 1960, "Black", "OLDCARVINNNNNNNNN");
        car.setId(6);

        String expected1 = "Car has been added to the database.";
        String actual1 = dao.create(car);

        Assert.assertEquals(expected1, actual1);
        Assert.assertEquals("Chevy", car.getMake());

        String expected2 = "Car removed from DB";
        String actual2 = dao.delete(6);
        Assert.assertEquals(expected2, actual2);
    }
}