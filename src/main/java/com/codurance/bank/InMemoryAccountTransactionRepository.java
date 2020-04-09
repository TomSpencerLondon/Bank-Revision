package com.codurance.bank;

import java.util.ArrayList;
import java.util.stream.Stream;

public class InMemoryAccountTransactionRepository implements AccountTransactionRepository {
  private ArrayList<AccountTransaction> transactions;

  public InMemoryAccountTransactionRepository() {
    this.transactions = new ArrayList<AccountTransaction>();
  }

  @Override
  public void store(AccountTransaction transaction) {
    transactions.add(transaction);
  }

  @Override
  public Stream<AccountTransaction> fetch() {
    return transactions.stream();
  }
}
