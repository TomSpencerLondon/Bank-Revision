package com.codurance.bank;

import java.util.ArrayList;
import java.util.stream.Stream;

public class AccountTransactionRepository {
  private ArrayList<AccountTransaction> transactions;

  public AccountTransactionRepository() {
    this.transactions = new ArrayList<AccountTransaction>();
  }

  public void store(AccountTransaction transaction) {
    transactions.add(transaction);
  }

  public Stream<AccountTransaction> fetch() {
    return transactions.stream();
  }
}
