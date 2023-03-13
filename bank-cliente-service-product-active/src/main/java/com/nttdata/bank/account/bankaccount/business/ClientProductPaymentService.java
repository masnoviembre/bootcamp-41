package com.nttdata.bank.account.bankaccount.business;


import com.nttdata.bank.account.bankaccount.business.entity.ClientProductPayment;
import com.nttdata.bank.account.bankaccount.business.entity.ClientProductPaymentDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ClientProductPaymentService {

  Flux<ClientProductPayment> getAll();

  Mono<ClientProductPayment> save(ClientProductPaymentDto productPaymentDto);

  Mono<ClientProductPayment> update(ClientProductPaymentDto productPaymentDto);


  Mono<ClientProductPayment> findById(Integer accountId);

  Mono<ClientProductPayment> uploadConsumer(Integer idClient, Float ammount,ClientProductPaymentDto request);

  //Flux<ClientProductPayment> findByClientId(Integer clientId);

}