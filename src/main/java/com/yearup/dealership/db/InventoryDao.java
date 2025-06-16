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

    public void addVehicleToInventory(String vin, int dealershipId) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "Update vehicles SET dealershipId = ? WHERE vin = ?")) {

            preparedStatement.setInt(1, dealershipId);
            preparedStatement.setString(2, vin);


            int entries = preparedStatement.executeUpdate();

            System.out.println("Entries Updated: " + entries);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public void removeVehicleFromInventory(String vin) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "DELETE FROM vehicles WHERE vin = ?")) {
            preparedStatement.setString(1, vin);

            int entries = preparedStatement.executeUpdate();

            System.out.println("Entries Updated: " + entries);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
