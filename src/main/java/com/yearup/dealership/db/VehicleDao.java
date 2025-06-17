package com.yearup.dealership.db;

import com.yearup.dealership.models.Vehicle;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VehicleDao {
    private DataSource dataSource;

    public VehicleDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void addVehicle(Vehicle vehicle) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "Insert into vehicles (vin, make, model, year, sold, color, vehicleType, odometer, price) values (?, ?, ?, ?, ?, ?, ?, ?, ?)")) {

            preparedStatement.setString(1, vehicle.getVin());
            preparedStatement.setString(2, vehicle.getMake());
            preparedStatement.setString(3, vehicle.getModel());
            preparedStatement.setInt(4, vehicle.getYear());
            preparedStatement.setBoolean(5, vehicle.isSold());
            preparedStatement.setString(6, vehicle.getColor());
            preparedStatement.setString(7, vehicle.getVehicleType());
            preparedStatement.setInt(8,  vehicle.getOdometer());
            preparedStatement.setDouble(9, vehicle.getPrice());


            int vehicleInfo = preparedStatement.executeUpdate();

            System.out.println("Vehicle Added: " + vehicleInfo);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void removeVehicle(String VIN) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(

                     "DELETE FROM inventory WHERE VIN = ?")) {

            preparedStatement.setString(1, VIN);

            int vehicleInfo = preparedStatement.executeUpdate();

            System.out.println("Vehicle Removed: " + vehicleInfo);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public List<Vehicle> searchByPriceRange(double minPrice, double maxPrice) {
        List<Vehicle> vehicles = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT * FROM vehicles WHERE price BETWEEN ? AND ?")) {

            preparedStatement.setDouble(1, minPrice);
            preparedStatement.setDouble(2, maxPrice);

            ResultSet results = preparedStatement.executeQuery();

            while (results.next()) {
                vehicles.add(createVehicleFromResultSet(results));
            }

            System.out.println("Vehicles Found: " + vehicles.size());

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return vehicles;
    }

    public List<Vehicle> searchByMakeModel(String make, String model) {

        List<Vehicle> vehicles = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT * FROM vehicles WHERE make LIKE ? AND model LIKE ?")) {

            preparedStatement.setString(1, make);
            preparedStatement.setString(2, model);

            ResultSet results = preparedStatement.executeQuery();

            while (results.next()) {
                vehicles.add(createVehicleFromResultSet(results));
            }

            System.out.println("Vehicle Found: " + vehicles.size());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return vehicles;
    }

    public List<Vehicle> searchByYearRange(int minYear, int maxYear) {
        List<Vehicle> vehicles = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT * FROM vehicles WHERE year BETWEEN ? and ?")) {

            preparedStatement.setInt(1,minYear);
            preparedStatement.setInt(2, maxYear);

            ResultSet results = preparedStatement.executeQuery();

            while (results.next()) {
                vehicles.add(createVehicleFromResultSet(results));
            }

            System.out.println("Vehicle Found: " + vehicles.size());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return vehicles;
    }

    public List<Vehicle> searchByColor(String color) {
        List<Vehicle> vehicles = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT * FROM vehicles WHERE color = ?")) {

            preparedStatement.setString(1, color);


            ResultSet results = preparedStatement.executeQuery();

            while (results.next()) {
                vehicles.add(createVehicleFromResultSet(results));
            }

            System.out.println("Vehicles Found: " + vehicles.size());

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return vehicles;
    }

    public List<Vehicle> searchByMileageRange(int minMileage, int maxMileage) {
        List<Vehicle> vehicles = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT * FROM vehicles WHERE odometer BETWEEN ? and ?")) {

            preparedStatement.setInt(1,minMileage);
            preparedStatement.setInt(2, maxMileage);

            ResultSet results = preparedStatement.executeQuery();

            while (results.next()) {
                vehicles.add(createVehicleFromResultSet(results));
            }

            System.out.println("Vehicle Found: " + vehicles.size());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return vehicles;
    }

    public List<Vehicle> searchByType(String type) {
        List<Vehicle> vehicles = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT * FROM vehicles WHERE vehicleType = ?")) {

            preparedStatement.setString(1, type);

            ResultSet results = preparedStatement.executeQuery();

            while (results.next()) {
                vehicles.add(createVehicleFromResultSet(results));
            }

            System.out.println("Vehicles Found: " + vehicles.size());

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return vehicles;
    }

    private Vehicle createVehicleFromResultSet(ResultSet resultSet) throws SQLException {
        Vehicle vehicle = new Vehicle();
        vehicle.setVin(resultSet.getString("VIN"));
        vehicle.setMake(resultSet.getString("make"));
        vehicle.setModel(resultSet.getString("model"));
        vehicle.setYear(resultSet.getInt("year"));
        vehicle.setSold(resultSet.getBoolean("SOLD"));
        vehicle.setColor(resultSet.getString("color"));
        vehicle.setVehicleType(resultSet.getString("vehicleType"));
        vehicle.setOdometer(resultSet.getInt("odometer"));
        vehicle.setPrice(resultSet.getDouble("price"));
        return vehicle;
    }
}
