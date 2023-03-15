package com.nttdata.bank.transaction.account.banktransactionaccount.business.impl;

import com.nttdata.bank.transaction.account.banktransactionaccount.business.entity.AccountDto;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@NoArgsConstructor
@Slf4j
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class ExternalService {
  @Autowired
  public WebClient.Builder webClientBuilder;

  public Mono<AccountDto> externalUpdateBalance(Integer accountId, Float amount) {
    return webClientBuilder.baseUrl("http://localhost:8003")
        .build()
        .post()
        .uri(uriBuilder -> uriBuilder
            .path("/accounts/updBalance/{accountId}/{amount}")
            .build(accountId, amount))
//        .uri("/accounts/updBalance/{accountId}/{amount}", accountId, amount)
        .accept(MediaType.APPLICATION_JSON)
        .retrieve()
        .bodyToMono(AccountDto.class);
  }
}
