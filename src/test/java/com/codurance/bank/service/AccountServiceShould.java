package com.codurance.bank.service;

import com.codurance.bank.repository.AccountTransactionRepository;
import com.codurance.bank.repository.MongoAccountTransactionRepository;
import com.codurance.bank.repository.SQLAccountTransactionRepository;
import com.codurance.bank.utils.ClockService;
import com.codurance.bank.ConsolePrinterService;
import com.codurance.bank.PrinterService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.codurance.bank.ConsolePrinter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.inOrder;

@ExtendWith(MockitoExtension.class)
public class AccountServiceShould {
  private static final String CONNECTION = "jdbc:mysql://localhost:3306/bank?user=root&password=password&serverTimezone=UTC";

  @Mock
  ClockService clockService;

  @Mock
  ConsolePrinter consolePrinter;

  @Test
  void print_a_statement_with_deposits_and_withdrawals() throws IOException {
    // Arrange
    given(clockService.getDateTime())
            .willReturn(LocalDateTime.of(2020, 1, 6, 0, 0))
            .willReturn(LocalDateTime.of(2020, 1, 10, 0, 0))
            .willReturn(LocalDateTime.of(2020, 2, 3, 0, 0));

    // Act
    PrinterService consolePrinterService = new ConsolePrinterService(consolePrinter);

    AccountTransactionRepository accountTransactionRepository = new MongoAccountTransactionRepository();
    AccountService accountService = new AccountService(consolePrinterService, clockService, accountTransactionRepository);

    accountService.deposit(1000);
    accountService.withdraw(1500);
    accountService.deposit(600);

    accountService.printStatement();

    // Assert
    InOrder inOrder = inOrder(consolePrinter);
    inOrder.verify(consolePrinter).printLine("DATE       | AMOUNT | BALANCE ");
    inOrder.verify(consolePrinter).printLine("03/02/2020 | 600.00 | 100.00 ");
    inOrder.verify(consolePrinter).printLine("10/01/2020 | -1500.00 | -500.00 ");
    inOrder.verify(consolePrinter).printLine("06/01/2020 | 1000.00 | 1000.00 ");
  }

  @AfterEach
  void tearDown() throws SQLException {
//    Connection connection = DriverManager.getConnection(CONNECTION);
//    PreparedStatement preparedStatement = connection.prepareStatement(
//            "DELETE FROM Transactions");
//    preparedStatement.execute();
  }
}
