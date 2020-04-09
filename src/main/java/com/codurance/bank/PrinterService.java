package com.codurance.bank;

import com.codurance.bank.domain.AccountTransaction;

import java.util.stream.Stream;

public interface PrinterService {
  void printStatement(Stream<AccountTransaction> fetch);
}
