package com.codurance.bank.domain;

import com.codurance.bank.domain.AccountTransaction;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class AccountTransactionShould {

  @Test
  void return_date_of_transaction() {
    float amount = 1000f;
    LocalDateTime timeOfTransaction = LocalDateTime.of(2020, 1, 1, 0, 0);
    AccountTransaction accountTransaction = new AccountTransaction(timeOfTransaction, amount);

    assertEquals(amount, accountTransaction.amount);
    assertEquals(timeOfTransaction, accountTransaction.dateTime);
  }
}