package com.codurance.bank;

public class AccountService {

  private final PrinterService printService;
  private final ClockService clockService;
  private final AccountTransactionRepository accountTransactionRepository;
  private final AccountTransactionFactory accountTransactionFactory;

  public AccountService(PrinterService printService, ClockService clockService, AccountTransactionRepository accountTransactionRepository) {
    this.accountTransactionFactory = new AccountTransactionFactory(clockService);
    this.printService = printService;
    this.clockService = clockService;
    this.accountTransactionRepository = accountTransactionRepository;
  }

  public void deposit(int amount) {
    AccountTransaction transaction = accountTransactionFactory.newDeposit(amount);
    this.accountTransactionRepository.store(transaction);
  }

  public void printStatement(){
    this.printService.printStatement(accountTransactionRepository.fetch());
  }

  public void withdraw(int amount) {
    AccountTransaction transaction = accountTransactionFactory.newWithdrawal(amount);
    this.accountTransactionRepository.store(transaction);
  }
}
