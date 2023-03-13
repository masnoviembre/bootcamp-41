package com.nttdata.bank.client.bankclient.business;

import com.nttdata.bank.client.bankclient.business.entity.ClientDto;
import com.nttdata.bank.client.bankclient.business.entity.Pasivo;
import com.nttdata.bank.client.bankclient.business.entity.PasivoDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PasivoService {

  Flux<Pasivo> getAll();

  Mono<Pasivo> save(PasivoDto pasivoDto);

  Mono<Pasivo> update(PasivoDto pasivoDto);

  Mono<Void> delete(Integer pasivoId);

  Mono<Pasivo> findById(Integer pasivoId);


}
