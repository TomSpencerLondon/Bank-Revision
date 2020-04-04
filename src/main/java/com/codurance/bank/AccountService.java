package com.codurance.bank;

public class AccountService {

  private final PrinterService printService;
  private final ClockService clockService;

  public AccountService(PrinterService printService, ClockService clockService) {
    this.printService = printService;
    this.clockService = clockService;
  }

  public void deposit(int amount) {
    AccountTransaction transaction = new AccountTransaction(this.clockService.getDateTime(), amount);
  }

  public void printStatement(){
    this.printService.printLine("DATE       | AMOUNT | BALANCE ");
  }


  public void withdraw(int amount) {
    throw new UnsupportedOperationException("implement me!");
  }
}
