package com.codurance.bank;

import java.util.stream.Stream;

public interface AccountTransactionRepository {
    void store(AccountTransaction transaction);

    Stream<AccountTransaction> fetch();
}
