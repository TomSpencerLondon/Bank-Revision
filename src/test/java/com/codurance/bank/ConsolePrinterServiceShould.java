package com.codurance.bank;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ConsolePrinterServiceShould {

  @Mock
  ConsolePrinter consolePrinter;

  private ConsolePrinterService consolePrinterService;

  @Mock
  private AccountTransaction accountTransaction, earlierAccountTransaction;

  @BeforeEach
  void setUp() {
    consolePrinterService = new ConsolePrinterService(consolePrinter);
  }

  @Test
  void print_statement() {
    ArrayList<AccountTransaction> list = new ArrayList<AccountTransaction>();

    consolePrinterService.printStatement(list.stream());

    verify(consolePrinter).printLine("DATE       | AMOUNT | BALANCE ");
  }

  @Test
  void print_formatted_transaction() {
    // Arrange
    LocalDateTime dateTime = LocalDateTime.of(2020, 10, 1, 0, 0);
    float amount = 1234.43f;
    AccountTransaction transaction = new AccountTransaction(dateTime, amount);
    ArrayList<AccountTransaction> list = new ArrayList<AccountTransaction>(Arrays.asList(transaction));

    consolePrinterService.printStatement(list.stream());

    verify(consolePrinter, atLeast(1)).printLine("01/10/2020 | 1234.43 | 1234.43 ");
  }

  @Test
  void print_transactions_in_reverse_date_order() {
    float amount = 500.00f;
    LocalDateTime dateTime = LocalDateTime.of(2020, 10, 2, 0, 0);
    LocalDateTime earlierDateTime = LocalDateTime.of(2020, 10, 1, 0, 0);
    AccountTransaction accountTransaction = new AccountTransaction(dateTime, amount);
    AccountTransaction earlierAccountTransaction = new AccountTransaction(earlierDateTime, amount);


    ArrayList<AccountTransaction> list = new ArrayList<AccountTransaction>();
    list.add(earlierAccountTransaction);
    list.add(accountTransaction);

    consolePrinterService.printStatement(list.stream());

    InOrder inOrder = inOrder(consolePrinter);
    inOrder.verify(consolePrinter).printLine("DATE       | AMOUNT | BALANCE ");
    inOrder.verify(consolePrinter).printLine("02/10/2020 | 500.00 | 1000.00 ");
    inOrder.verify(consolePrinter).printLine("01/10/2020 | 500.00 | 500.00 ");
  }
}