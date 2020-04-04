package com.codurance.bank;

import java.util.stream.Stream;

public interface PrinterService {
  void printStatement(Stream<AccountTransaction> fetch);
}
