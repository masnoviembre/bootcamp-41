package com.nttdata.bank.client.bankclient.business.repository;

import com.nttdata.bank.client.bankclient.business.entity.Activo;
import com.nttdata.bank.client.bankclient.business.entity.Pasivo;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivoRepository extends ReactiveMongoRepository<Activo, Integer> {

}
