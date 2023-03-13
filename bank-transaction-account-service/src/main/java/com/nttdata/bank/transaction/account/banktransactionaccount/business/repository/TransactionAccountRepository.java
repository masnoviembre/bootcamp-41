package com.nttdata.bank.transaction.account.banktransactionaccount.business.repository;


import com.nttdata.bank.transaction.account.banktransactionaccount.business.entity.TransactionAccount;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionAccountRepository extends ReactiveMongoRepository<TransactionAccount,Integer> {

}
