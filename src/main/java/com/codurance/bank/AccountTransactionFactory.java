package com.codurance.bank;

public class AccountTransactionFactory {
  private final ClockService clockService;

  public AccountTransactionFactory(ClockService clockService) {
    this.clockService = clockService;
  }

  public AccountTransaction newWithdrawal(float amount) {
    return new AccountTransaction(clockService.getDateTime(), -amount);
  }

  public AccountTransaction newDeposit(float amount) {
    return new AccountTransaction(clockService.getDateTime(), amount);
  }
}
