package com.codurance.bank;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class SQLAccountTransactionRepository implements AccountTransactionRepository {

    public static final String CONNECTION = "jdbc:mysql://localhost:3306/bank?user=root&password=password&serverTimezone=UTC";

    @Override
    public void store(AccountTransaction transaction) {
        try (Connection connection = DriverManager.getConnection(
                CONNECTION);

             PreparedStatement preparedStatement = connection
                     .prepareStatement("INSERT INTO Transactions (time_stamp, amount)\n"+
                             "VALUES (?, ?)");){
            System.out.println(transaction.amount);
            preparedStatement.setTimestamp(1, Timestamp.valueOf(transaction.dateTime));
            preparedStatement.setFloat(2, transaction.amount);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Stream<AccountTransaction> fetch() {
        List<AccountTransaction> transactionList = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(
                CONNECTION);

             PreparedStatement preparedStatement = connection
                     .prepareStatement("SELECT time_stamp, amount FROM Transactions");

             ResultSet resultSet = preparedStatement.executeQuery()){
            while (resultSet.next()){
                System.out.println("This is amount " + resultSet.getInt(2));

                System.out.println(resultSet.getTimestamp(1));
                //                LocalDateTime dateTime = LocalDateTime.of(resultSet.getTimestamp(1));
//                transactionList.add(new AccountTransaction());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}