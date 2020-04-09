package com.codurance.bank.repository;

import com.codurance.bank.domain.AccountTransaction;

import java.util.stream.Stream;

public interface AccountTransactionRepository {
    void store(AccountTransaction transaction);

    Stream<AccountTransaction> fetch();
}
