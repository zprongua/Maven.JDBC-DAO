package daos;

import models.Car;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBC implements JDBCDAO {
    public static final String URL = "jdbc:mysql://localhost:3306/JDBCDAO?useSSL=false";
    public static final String USER;
    public static final String PASS;

    public static void main(String[] args) throws SQLException {
        getConnection();
    }

    public static Connection getConnection() throws SQLException {
        try {
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (SQLException e) {
            throw new RuntimeException("Error connecting to the database.", e);
        }
    }

    public Car extractCarFromResultSet(ResultSet rs) throws SQLException {
        Car car = new Car();

        car.setId( rs.getInt(1));
        car.setMake( rs.getString(2));
        car.setModel( rs.getString(3));
        car.setYear( rs.getInt(4));
        car.setColor( rs.getString(5));
        car.setVin( rs.getString(6));

        return car;
    }

    @Override
    public Car carById(int id) {
        try {
            Connection conn = getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Car WHERE id=" + id);

            if (rs.next()) {
                return extractCarFromResultSet(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Car> findAllCars() {
        try {
            Connection conn = getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Car");

            List<Car> cars = new ArrayList<>();

            while (rs.next()) {
                Car car = extractCarFromResultSet(rs);
                cars.add(car);
            }

            return cars;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public String update(Car dto) {
        try {
            Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement("UPDATE Car SET " +
                    "make=?, model=?, year=?, color=?, vin=? WHERE id=?");
            ps.setInt(6, dto.getId());
            ps.setString(1, dto.getMake());
            ps.setString(2, dto.getModel());
            ps.setInt(3, dto.getYear());
            ps.setString(4, dto.getColor());
            ps.setString(5, dto.getVin());
            int i = ps.executeUpdate();

            if (i==1) return "Car has been updated in the database.";
            else return "Unable to update car in database.";
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String create(Car dto) {
        try {
            Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement("INSERT INTO Car " +
                    "(id, make, model, year, color, vin) VALUES" +
                    "(?, ?, ?, ?, ?, ?)");
            if (dto.getId()>0) ps.setInt(1, dto.getId());
            ps.setString(2, dto.getMake());
            ps.setString(3, dto.getModel());
            ps.setInt(4, dto.getYear());
            ps.setString(5, dto.getColor());
            ps.setString(6, dto.getVin());
            int i = ps.executeUpdate();

            if (i==1) return "Car has been added to the database.";
            else return "Unable to add car to database.";
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String delete(int id) {
        try {
            Connection conn = getConnection();
            Statement stmt = conn.createStatement();
            int i = stmt.executeUpdate("DELETE FROM Car WHERE id=" + id);

            if (i==1) return "Car removed from DB";
            else return "Unable to remove Car";
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Error";
    }
}
