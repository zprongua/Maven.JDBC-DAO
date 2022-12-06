package daos;

import models.Car;

import java.util.List;
import java.util.Set;

public interface JDBCDAO {
    public Car carById(int id);
    public List<Car> findAllCars();
    public String update(Car dto);
    public String create(Car dto);
    public String delete(int id);
}
