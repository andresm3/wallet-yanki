package com.bootcamp.banking.walletyanki.infrastructure.rest;


import static org.springframework.http.HttpStatus.CREATED;

import com.bootcamp.banking.walletyanki.application.WalletUseCases;
import com.bootcamp.banking.walletyanki.domain.dto.request.WalletRequest;
import com.bootcamp.banking.walletyanki.domain.models.Wallet;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/wallet")
public class WalletResource {

  @Autowired
  private WalletUseCases walletUseCases;

  @PostMapping("/create")
  @ResponseStatus(CREATED)
  public Mono<Wallet> createUser(@Valid @RequestBody WalletRequest request) {
    return walletUseCases.createUser(request);
  }

  @GetMapping("/documentNumber/{documentNumber}")
  public Mono<Wallet> findByDocumentNumber(@PathVariable String documentNumber) {
    return walletUseCases.findByDocumentNumber(documentNumber);
  }
}
