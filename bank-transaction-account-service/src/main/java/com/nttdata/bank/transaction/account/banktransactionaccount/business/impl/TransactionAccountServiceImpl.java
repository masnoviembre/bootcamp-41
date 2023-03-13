package com.nttdata.bank.transaction.account.banktransactionaccount.business.impl;

import com.nttdata.bank.transaction.account.banktransactionaccount.business.TransactionAccountService;
import com.nttdata.bank.transaction.account.banktransactionaccount.business.repository.TransactionAccountRepository;
import com.nttdata.bank.transaction.account.banktransactionaccount.business.entity.TransactionAccount;
import com.nttdata.bank.transaction.account.banktransactionaccount.business.entity.TransactionAccountDto;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class TransactionAccountServiceImpl implements TransactionAccountService {

  @Autowired
  private TransactionAccountRepository transactionAccountRepository;

  @Autowired
  private Mapper mapper;

  @Override
  public Flux<TransactionAccount> getAll() {
    return transactionAccountRepository
        .findAll();
  }

  @Override
  public Mono<TransactionAccount> save(TransactionAccountDto transactionAccountDto) {
    return transactionAccountRepository.existsById(transactionAccountDto.getTransactionId())
        .flatMap((isExist -> {
          if (!isExist) {
            return transactionAccountRepository.save(mapper.map(transactionAccountDto,
                                                                TransactionAccount.class));
          } else {
            return Mono.empty();
          }
        }));
  }

  @Override
  public Mono<TransactionAccount> update(TransactionAccountDto transactionAccountDto) {
    return transactionAccountRepository.existsById(transactionAccountDto.getAccountId())
        .flatMap(isExist -> {
          if (isExist) {
            transactionAccountRepository.deleteById(transactionAccountDto.getTransactionId());
            return this.save(transactionAccountDto);
          }
          return Mono.empty();
        });
  }

  @Override
  public Mono<Void> delete(Integer transactionAccountId) {
    return transactionAccountRepository
        .findById(transactionAccountId)
        .flatMap(a -> transactionAccountRepository.deleteById(a.getTransactionId()))
        .switchIfEmpty(Mono.empty());
  }

}
