package com.codurance.bank;

import javax.sql.rowset.spi.TransactionalWriter;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ConsolePrinterService implements PrinterService {
  private final ConsolePrinter consolePrinter;
  private float balance;

  public ConsolePrinterService(ConsolePrinter consolePrinter) {
    this.consolePrinter = consolePrinter;
    this.balance = 0;
  }


  @Override
  public void printStatement(Stream<AccountTransaction> accountTransactionStream){
    consolePrinter.printLine("DATE       | AMOUNT | BALANCE ");

    List<AccountTransaction> accountTransactions = accountTransactionStream.collect(Collectors.toList());
    Collections.reverse(accountTransactions);
    for (AccountTransaction accountTransaction : accountTransactions){
      balance += accountTransaction.amount();
      System.out.println(balance);
      accountTransaction.set(balance);
    }

    accountTransactions.stream()
            .sorted(Comparator.comparing(AccountTransaction::dateTime).reversed())
            .forEach(t -> {
              consolePrinter.printLine(formatTransaction(t));
    });
  }

  private String formatTransaction(AccountTransaction accountTransaction){
    return String.format("%s | %.2f | %.2f ",
            accountTransaction.dateTime().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
            accountTransaction.amount(),
            accountTransaction.getBalance());
  }

}
