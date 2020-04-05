package com.codurance.bank;

import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.stream.Stream;

public class ConsolePrinterService implements PrinterService {
  private final ConsolePrinter consolePrinter;

  public ConsolePrinterService(ConsolePrinter consolePrinter) {
    this.consolePrinter = consolePrinter;
  }


  @Override
  public void printStatement(Stream<AccountTransaction> accountTransactionStream){
    consolePrinter.printLine("DATE       | AMOUNT | BALANCE ");

    accountTransactionStream
            .sorted(Comparator.comparing(AccountTransaction::dateTime).reversed())
            .forEach(t -> {
              consolePrinter.printLine(formatTransaction(t));
    });
  }

  private String formatTransaction(AccountTransaction accountTransaction){
    return String.format("%s | %.2f | %.2f ",
            accountTransaction.dateTime().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
            accountTransaction.amount(),
            accountTransaction.amount());
  }

}
