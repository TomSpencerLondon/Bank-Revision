package com.codurance.bank;

import java.time.LocalDateTime;

public class AccountTransaction {

  public final LocalDateTime dateTime;
  public final float amount;

  public AccountTransaction(LocalDateTime dateTime, float amount) {
    this.dateTime = dateTime;
    this.amount = amount;
  }
}
