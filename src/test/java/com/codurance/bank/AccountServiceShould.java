package com.codurance.bank;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.inOrder;

@ExtendWith(MockitoExtension.class)
public class AccountServiceShould {
  @Mock
  PrinterService printerService;

  @Mock
  ClockService clockService;

  @Test
  void print_a_statement_with_deposits_and_withdrawals() {

    // Arrange
    given(clockService.getDateTime())
            .willReturn(LocalDateTime.of(2020, 1, 6, 0, 0))
            .willReturn(LocalDateTime.of(2020, 1, 10, 0, 0))
            .willReturn(LocalDateTime.of(2020, 2, 3, 0, 0));

    // Act
    AccountService accountService = new AccountService(printerService, clockService);

    accountService.deposit(1000);
    accountService.withdraw(1500);
    accountService.deposit(600);

    accountService.printStatement();

    // Assert
    InOrder inOrder = inOrder(printerService);
    inOrder.verify(printerService).printLine("DATE       | AMOUNT | BALANCE ");
    inOrder.verify(printerService).printLine("03/02/2020 |    600 |     100 ");
    inOrder.verify(printerService).printLine("10/01/2020 |  -1500 |    -500 ");
    inOrder.verify(printerService).printLine("06/01/2020 |   1000 |    1000 ");
  }
}
