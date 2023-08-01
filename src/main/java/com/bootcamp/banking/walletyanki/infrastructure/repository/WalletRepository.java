package com.bootcamp.banking.walletyanki.infrastructure.repository;

import com.bootcamp.banking.walletyanki.domain.models.Wallet;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface WalletRepository extends ReactiveMongoRepository<Wallet, String> {

  Mono<Wallet> findByPhone(String phone);
  Mono<Wallet> findByDocumentNumber(String number);
}
