package com.codurance.bank;

import java.time.LocalDateTime;

public class AccountTransaction {

  public final LocalDateTime dateTime;
  public final float amount;
  private float balance;

  public AccountTransaction(LocalDateTime dateTime, float amount) {
    this.dateTime = dateTime;
    this.amount = amount;
    this.balance = 0;
  }

  public LocalDateTime dateTime(){
    return dateTime;
  }

  public float amount(){
    return amount;
  }

  public void set(float amount) {
    balance += amount;
  }

  public float getBalance() {
    return balance;
  }
}
