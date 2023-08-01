package com.bootcamp.banking.walletyanki.application.impl;


import static com.bootcamp.banking.walletyanki.application.utils.Constants.AccountType.FIXED_TERM;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import com.bootcamp.banking.walletyanki.application.AccountUseCases;
import com.bootcamp.banking.walletyanki.application.exceptions.customs.CustomInformationException;
import com.bootcamp.banking.walletyanki.application.exceptions.customs.CustomNotFoundException;

import com.bootcamp.banking.walletyanki.domain.dto.response.Account;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


@RequiredArgsConstructor
@Service
public class AccountUseCasesImpl implements AccountUseCases {

  private static final String NOT_FOUND_MESSAGE = " not found";
  private final String urlAccount = "http://localhost:8082/accounts";
  @Autowired
//  @Qualifier("wcLoadBalanced")
  private WebClient.Builder webClient;

  @Override
  public Mono<Account> findAccountByDocumentNumber(String number) {
    return webClient
        .build()
        .get()
        .uri(urlAccount + "/client/main/documentNumber/{documentNumber}", number)
        .retrieve()
        .onStatus(NOT_FOUND::equals, response ->
            Mono.error(new CustomNotFoundException("Account " + number + NOT_FOUND_MESSAGE)))
        .bodyToMono(Account.class)
        .onErrorStop()
        .flatMap(account -> {
          System.out.println(">>>>>>>>> " + account.getTypeAccount().getOption());

          return Mono.just(account);
        });
  }

//  @Override
//  public void updateAccount(String id, BigDecimal amount) {
//    webClient
//        .build()
//        .put()
//        .uri(urlAccount + "/balance/{id}/amount/{amount}", id, amount)
//        .retrieve()
//        .bodyToMono(Void.class)
//        .subscribe();
//  }
}
