package com.codurance.bank.domain;

import com.codurance.bank.domain.AccountTransaction;
import com.codurance.bank.domain.AccountTransactionFactory;
import com.codurance.bank.utils.ClockService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AccountTransactionFactoryShould {
  @Mock
  private ClockService clockService;
  private LocalDateTime dateTimeOfTransaction;
  private AccountTransactionFactory accountTransactionFactory;
  private float amount;

  public AccountTransactionFactoryShould() {
    amount = 1234.65f;
  }

  @BeforeEach
  void setUp() {
    dateTimeOfTransaction = LocalDateTime.of(2020, 1, 2, 2, 2);
    accountTransactionFactory = new AccountTransactionFactory(clockService);
    // Arrange
    when(clockService.getDateTime()).thenReturn(dateTimeOfTransaction);


  }

  @Test
  void create_a_new_transaction_with_current_date_and_time() {

    // Act
    AccountTransaction transaction = accountTransactionFactory.newWithdrawal(amount);

    // Assert
    assertEquals(dateTimeOfTransaction, transaction.dateTime, "Date was incorrect");
    assertEquals(-amount, transaction.amount, "Amount was incorrect");
  }

  @Test
  void create_new_deposit_with_current_date_time_and_amount() {
    AccountTransaction transaction = accountTransactionFactory.newDeposit(amount);

    assertEquals(dateTimeOfTransaction, transaction.dateTime);
    assertEquals(amount, transaction.amount);
  }
}