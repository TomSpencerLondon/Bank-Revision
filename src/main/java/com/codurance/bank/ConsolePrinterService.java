package com.codurance.bank;

import java.util.stream.Stream;

public class ConsolePrinterService implements PrinterService {
  public ConsolePrinterService(ConsolePrinter consolePrinter) {
  }


  @Override
  public void printStatement(Stream<AccountTransaction> fetch){

  }

}
