package com.codurance.bank;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ConsolePrinterServiceShould {

  @Mock
  ConsolePrinter consolePrinter;
  private ConsolePrinterService consolePrinterService;

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
  void print_transaction() {
    // Arrange
    LocalDateTime dateTime = LocalDateTime.of(2020, 10, 1, 0, 0);
    float amount = 1234.43f;
    AccountTransaction transaction = new AccountTransaction(dateTime, amount);
    ArrayList<AccountTransaction> list = new ArrayList<AccountTransaction>(Arrays.asList(transaction));

    consolePrinterService.printStatement(list.stream());

    verify(consolePrinter, atLeast(1)).printLine("01/10/2020 | 1234.43 | 1234.43 ");
  }
}