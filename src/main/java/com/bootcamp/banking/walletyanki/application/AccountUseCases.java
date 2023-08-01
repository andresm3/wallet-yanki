package com.bootcamp.banking.walletyanki.application;

import com.bootcamp.banking.walletyanki.domain.dto.response.Account;
import com.bootcamp.banking.walletyanki.domain.dto.response.AccountResponse;
import reactor.core.publisher.Mono;

public interface AccountUseCases {

  Mono<Account> findAccountByDocumentNumber(String documentNumber);
}

