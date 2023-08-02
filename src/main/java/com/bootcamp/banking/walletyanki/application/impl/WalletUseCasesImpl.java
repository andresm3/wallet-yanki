package com.bootcamp.banking.walletyanki.application.impl;

import com.bootcamp.banking.walletyanki.application.AccountUseCases;
import com.bootcamp.banking.walletyanki.application.WalletUseCases;
import com.bootcamp.banking.walletyanki.application.exceptions.customs.CustomInformationException;
import com.bootcamp.banking.walletyanki.application.exceptions.customs.CustomNotFoundException;
import com.bootcamp.banking.walletyanki.domain.dto.request.WalletRequest;
import com.bootcamp.banking.walletyanki.domain.models.Wallet;
import com.bootcamp.banking.walletyanki.infrastructure.repository.WalletRepository;
import org.bson.types.ObjectId;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import static com.bootcamp.banking.walletyanki.application.utils.Constants.UserType.SELLER;

@Service
public class WalletUseCasesImpl implements WalletUseCases {

  @Autowired
  private WalletRepository walletRepository;
  @Autowired
  private AccountUseCases accountUseCases;

  @Override
  public Mono<Wallet> findByDocumentNumber(String documentNumber) {
    return walletRepository.findByDocumentNumber(documentNumber)
        .switchIfEmpty(Mono.error(new CustomNotFoundException("Data not found")));
  }

  @Override
  public Mono<Wallet> createUser(WalletRequest request) {

    return walletRepository.findByDocumentNumber(request.getDocumentNumber())
        .doOnNext(res -> {
          throw new CustomInformationException("Wallet account already exists");
        })
        .switchIfEmpty(validateInformation(request)
            .flatMap(req -> associateDataAccount(req)
                .flatMap(r -> walletRepository.save(r)
                    .map(x -> {
                      System.out.println("Created a new wallet id = " + r.getId());
                      return x;
                    }))
            ));
  }

  @Override
  public Mono<Wallet> findByPhone(String phone) {
    return walletRepository.findByPhone(phone)
        .switchIfEmpty(Mono.error(new CustomNotFoundException("Data not found")));
  }

  private Mono<WalletRequest> validateInformation(WalletRequest request) {
    if (request.getProfile() == SELLER && StringUtils.isBlank(request.getAccountNumber())) {
      return Mono.error(new CustomInformationException("The seller profile "
          + "requires an account number"));
    }

    return Mono.just(request);
  }

  private Mono<Wallet> associateDataAccount(WalletRequest request) {
    Mono<Wallet> monoWallet = Mono.just(new Wallet(request));

    if (request.getProfile() == SELLER) {
      return monoWallet
          .flatMap(wallet -> accountUseCases
              .findAccountByDocumentNumber(request.getDocumentNumber())
              .doOnNext(ac -> wallet.setIdAccount(new ObjectId(ac.getId()))))
          .then(monoWallet);
    } else {
      return monoWallet;
    }
  }
}
