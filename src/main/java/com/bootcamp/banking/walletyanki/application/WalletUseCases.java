package com.bootcamp.banking.walletyanki.application;

import com.bootcamp.banking.walletyanki.domain.dto.request.WalletRequest;
import com.bootcamp.banking.walletyanki.domain.models.Wallet;
import reactor.core.publisher.Mono;

public interface WalletUseCases {

  Mono<Wallet> findByDocumentNumber(String documentNumber);
  Mono<Wallet> createUser(WalletRequest request);

  Mono<Wallet> findByPhone(String documentNumber);
}
