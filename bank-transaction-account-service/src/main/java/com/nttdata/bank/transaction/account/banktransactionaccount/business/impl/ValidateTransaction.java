package com.nttdata.bank.transaction.account.banktransactionaccount.business.impl;

import com.nttdata.bank.transaction.account.banktransactionaccount.business.entity.AccountDto;
import com.nttdata.bank.transaction.account.banktransactionaccount.business.entity.TransactionAccount;
import com.nttdata.bank.transaction.account.banktransactionaccount.business.entity.TransactionAccountDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Setter
@Getter
@NoArgsConstructor
@Component
@Slf4j
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class ValidateTransaction {
  @Autowired
  private ExternalService externalService;

  public Mono<Boolean> updateBalance(TransactionAccountDto transactionAccountDto) {

    Float amount = transactionAccountDto.getTransactionAmount();
    if (transactionAccountDto.getTransactionType().equalsIgnoreCase("C")) {
      amount = amount * -1;
    }

    Mono<AccountDto> accountDtoMono = externalService.
                                externalUpdateBalance (transactionAccountDto.getAccountId(),
                                                       amount );
    accountDtoMono.subscribe();

    return Mono.just(true);
  }

  public Mono<Boolean> revertBalance(TransactionAccount transactionAccountDto) {

    Float amount = transactionAccountDto.getTransactionAmount();
    if (transactionAccountDto.getTransactionType().equalsIgnoreCase("A")) {
      amount = amount * -1;
    }

    Mono<AccountDto> accountDtoMono = externalService.
                                      externalUpdateBalance (transactionAccountDto.getAccountId(),
                                          amount );
    accountDtoMono.subscribe();

    return Mono.just(true);
  }
}
