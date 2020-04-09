package com.codurance.bank.repository;

import com.codurance.bank.domain.AccountTransaction;
import com.codurance.bank.repository.AccountTransactionRepository;
import com.codurance.bank.repository.InMemoryAccountTransactionRepository;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AccountTransactionRepositoryShould {
  @Test
  void store_and_return_a_transaction() throws IOException {
    LocalDateTime dateTime = LocalDateTime.of(2020, 2, 1, 0, 0);

    float amount = 1500f;
    AccountTransaction transactionToStore = new AccountTransaction(dateTime, amount);
    AccountTransactionRepository accountTransactionRepository = new InMemoryAccountTransactionRepository();

    accountTransactionRepository.store(transactionToStore);

    Stream<AccountTransaction> transactionsFetched = accountTransactionRepository.fetch();
    Optional<AccountTransaction> transaction = transactionsFetched.findFirst();

    assertTrue(transaction.isPresent(), "There was no transaction returned");
    assertEquals(transactionToStore, transaction.get());
  }
}