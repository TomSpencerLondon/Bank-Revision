package com.codurance.bank.repository;

import com.codurance.bank.domain.AccountTransaction;

import java.io.IOException;
import java.util.stream.Stream;

public interface AccountTransactionRepository {
    void store(AccountTransaction transaction) throws IOException;

    Stream<AccountTransaction> fetch();
}
