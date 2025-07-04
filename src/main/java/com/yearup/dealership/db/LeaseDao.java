package com.yearup.dealership.db;

import com.yearup.dealership.models.LeaseContract;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LeaseDao {
    private DataSource dataSource;

    public LeaseDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void addLeaseContract(LeaseContract leaseContract) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "Insert into lease_contracts (contract_id, vin, lease_start, lease_end, monthly_payment) values (?, ?, ?, ?, ?)")) {
             preparedStatement.setInt(1, leaseContract.getContractId());
             preparedStatement.setString(2, leaseContract.getVin());
             preparedStatement.setDate(3, Date.valueOf(leaseContract.getLeaseStart()));
             preparedStatement.setDate(4, Date.valueOf(leaseContract.getLeaseEnd()));
             preparedStatement.setDouble(5, leaseContract.getMonthlyPayment());

            int entries = preparedStatement.executeUpdate();

            System.out.println("Entries Updated: " + entries);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
