package com.nttdata.bank.account.bankaccount.business.impl;

import com.nttdata.bank.account.bankaccount.business.ClientProductPaymentService;
import com.nttdata.bank.account.bankaccount.business.entity.Account;
import com.nttdata.bank.account.bankaccount.business.entity.ClientProductPayment;
import com.nttdata.bank.account.bankaccount.business.entity.ClientProductPaymentDto;
import com.nttdata.bank.account.bankaccount.business.repository.ClientProductPaymentRepository;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ClientProductPaymentServiceImpl implements ClientProductPaymentService {

    @Autowired
    private ClientProductPaymentRepository repository;


    private static WebClient webClient;

    @Autowired
    private Mapper mapper;

    @Override
    public Flux<ClientProductPayment> getAll() {
        return repository
                .findAll()
                .switchIfEmpty(Flux.empty());
    }

    @Override
    public Mono<ClientProductPayment> save(ClientProductPaymentDto productPaymentDto) {
        return repository
                .existsById(productPaymentDto.getId())
                .flatMap((isExist -> {
                    if (!isExist) {
                        productPaymentDto.setStatus("Pendiente");
                        return repository.save(mapper.map(productPaymentDto, ClientProductPayment.class));
                    } else {
                        return Mono.empty();
                    }
                }));
    }

    @Override
    public Mono<ClientProductPayment> update(ClientProductPaymentDto productPaymentDto) {
        return repository
                .existsById(productPaymentDto.getId())
                .flatMap((isExist -> {
                    if (!isExist) {
                        productPaymentDto.setStatus("Exitoso");
                        return repository.save(mapper.map(productPaymentDto, ClientProductPayment.class));
                    } else {
                        return Mono.empty();
                    }
                }));
    }

    @Override
    public Mono<ClientProductPayment> findById(Integer accountId) {
        return repository.
                findById(accountId);

    }

    @Override
    public Mono<ClientProductPayment> uploadConsumer(Integer idClient, Float ammount,ClientProductPaymentDto request) {

        Mono<Account> account = webClient
                .get()
                .uri("http://localhost:8001/clients/"+ idClient)
                .retrieve()
                .bodyToMono(Account.class);

        if (account != null){
            account.map(a -> {
                if (ammount < a.getLimitCredit()){
                    repository.save(mapper.map(request, ClientProductPayment.class));
                }else{
                    return null;
                }
                return null;
            });
            //validar si viene el monto adecuado
        }
        return null;
    }

    //@Override
    //public Flux<ClientProductPayment> findByClientId(Integer clientId) {
      //  return null;
    //}
}
