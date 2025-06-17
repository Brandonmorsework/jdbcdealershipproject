package com.yearup.dealership.db;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InventoryDao {
    private DataSource dataSource;

    public InventoryDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void addVehicleToInventory(String vin, int dealership_id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "Insert into inventory (vin, dealership_id) Values (?, ?)")) {

            preparedStatement.setString(1, vin);
            preparedStatement.setInt(2, dealership_id);

            int entries = preparedStatement.executeUpdate();

            System.out.println("Entries Updated: " + entries);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void removeVehicleFromInventory(String vin) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "DELETE FROM inventory WHERE vin = ?")) {
            preparedStatement.setString(1, vin);

            int entries = preparedStatement.executeUpdate();

            System.out.println("Entries Updated: " + entries);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
