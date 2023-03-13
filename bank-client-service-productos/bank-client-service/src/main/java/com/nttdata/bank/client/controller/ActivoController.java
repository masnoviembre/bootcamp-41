package com.nttdata.bank.client.controller;

import com.nttdata.bank.client.bankclient.business.ActivoService;
import com.nttdata.bank.client.bankclient.business.PasivoService;
import com.nttdata.bank.client.bankclient.business.entity.Activo;
import com.nttdata.bank.client.bankclient.business.entity.ActivoDto;
import com.nttdata.bank.client.bankclient.business.entity.Pasivo;
import com.nttdata.bank.client.bankclient.business.entity.PasivoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequestMapping("/activo")
public class ActivoController {

  @Autowired
  private ActivoService activoService;

  @GetMapping
  public Flux<Activo> getAll() {
    return activoService
        .getAll();
  }

  @GetMapping("/{activoId}")
  public Mono<Activo> getById(@PathVariable("activoId") Integer activoId) {
    return activoService
        .findById(activoId);
  }

  @PostMapping
  public Mono<Activo> save(@Valid @RequestBody ActivoDto activoDto) {
    return activoService
        .save(activoDto);
  }

  @PostMapping("/updActivo")
  public Mono<Activo> update(@Valid @RequestBody ActivoDto activoDto) {
    return activoService
        .update(activoDto);
  }

  @PostMapping("/delete/{activoId}")
  public Mono<Void> deleteByClientId(@PathVariable("activoId") Integer activoId) {
    return activoService
        .delete(activoId);
  }

}
