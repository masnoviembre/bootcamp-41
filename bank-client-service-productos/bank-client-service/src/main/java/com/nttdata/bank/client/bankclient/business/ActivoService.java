package com.nttdata.bank.client.bankclient.business;

import com.nttdata.bank.client.bankclient.business.entity.Activo;
import com.nttdata.bank.client.bankclient.business.entity.ActivoDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ActivoService {

  Flux<Activo> getAll();

  Mono<Activo> save(ActivoDto activoDto);

  Mono<Activo> update(ActivoDto activoDto);

  Mono<Void> delete(Integer activoId);

  Mono<Activo> findById(Integer activoId);


}
