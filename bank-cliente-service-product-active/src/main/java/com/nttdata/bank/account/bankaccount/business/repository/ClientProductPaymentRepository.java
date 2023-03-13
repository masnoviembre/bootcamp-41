package com.nttdata.bank.account.bankaccount.business.repository;

import com.nttdata.bank.account.bankaccount.business.entity.ClientProductPayment;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface ClientProductPaymentRepository extends ReactiveMongoRepository<ClientProductPayment, Integer> {

  Flux<ClientProductPayment> findByClientId(Integer clientId);

  Mono<ClientProductPayment> findByAccountNumber(String accountNumber);

  Flux<Object> getBalanceByClientId(Integer clientId);

  Flux<ClientProductPayment> getAccountByProductId(String productId, String dateIni, String dateEnd);

}
