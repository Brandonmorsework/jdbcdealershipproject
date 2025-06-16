package com.yearup.dealership.db;

import com.yearup.dealership.models.LeaseContract;
import com.yearup.dealership.models.SalesContract;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SalesDao {
    private DataSource dataSource;

    public SalesDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void addSalesContract(SalesContract salesContract) {

            try (Connection connection = dataSource.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(
                         "Insert into lease_contract (contract_id, vin, lease_start, lease_end, monthly_payment) values (?, ?, ?, ?, ?)")) {
                preparedStatement.setInt(1, leaseContract.getContractId());
                preparedStatement.setString(2, sa.getVin());
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
}
