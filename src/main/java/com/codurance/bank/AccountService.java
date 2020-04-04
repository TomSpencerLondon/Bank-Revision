package com.codurance.bank;

public class AccountService {

  private final PrinterService printService;
  private final ClockService clockService;
  private final AccountTransactionRepository accountTransactionRepository;

  public AccountService(PrinterService printService, ClockService clockService, AccountTransactionRepository accountTransactionRepository) {
    this.printService = printService;
    this.clockService = clockService;
    this.accountTransactionRepository = accountTransactionRepository;
  }

  public void deposit(int amount) {
    AccountTransaction transaction = new AccountTransaction(this.clockService.getDateTime(), amount);
    this.accountTransactionRepository.store(transaction);
  }

  public void printStatement(){
    this.printService.printLine("DATE       | AMOUNT | BALANCE ");
  }


  public void withdraw(int amount) {
    throw new UnsupportedOperationException("implement me!");
  }
}
