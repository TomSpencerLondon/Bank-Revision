package com.codurance.bank.service;

import com.codurance.bank.PrinterService;
import com.codurance.bank.domain.AccountTransaction;
import com.codurance.bank.domain.AccountTransactionFactory;
import com.codurance.bank.repository.AccountTransactionRepository;
import com.codurance.bank.utils.ClockService;

import java.io.IOException;

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

  public void deposit(int amount) throws IOException {
    AccountTransaction transaction = accountTransactionFactory.newDeposit(amount);
    this.accountTransactionRepository.store(transaction);
  }

  public void printStatement(){
    this.printService.printStatement(accountTransactionRepository.fetch());
  }

  public void withdraw(int amount) throws IOException {
    AccountTransaction transaction = accountTransactionFactory.newWithdrawal(amount);
    this.accountTransactionRepository.store(transaction);
  }
}
