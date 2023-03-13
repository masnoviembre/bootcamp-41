package com.nttdata.bank.account.bankaccount.business.impl;

import com.nttdata.bank.account.bankaccount.business.entity.AccountDto;
import com.nttdata.bank.account.bankaccount.business.entity.ClientDto;
import com.nttdata.bank.account.bankaccount.business.repository.AccountRepository;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Setter
@Getter
@NoArgsConstructor
@Component
@Slf4j
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class ValidateClient {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private ExternalService externalService;
    private static long numberOfAccounts = 0;
    //private boolean isValidate = false;

    public boolean validateTypeClient(Integer clientId){
      Mono<ClientDto> clientDto = externalService.externalFindByClientId(clientId);

      return clientDto
          .map(p->{
            if(p.getClientType().equalsIgnoreCase("P")){
              return validateNumberOfAccountsPersonal(p.getClientId());
            } else {
              return validateNumberOfAccountsbusiness(p.getClientId());
            }
          }).subscribe().isDisposed();
    }

    private boolean validateNumberOfAccountsPersonal(Integer clientId){
      System.out.println("CLiente tipo Personal");

      accountRepository.findAll()
          .filter(p -> p.getClientId().equals(clientId))
          .count()
          .subscribe(count->{
            numberOfAccounts = count;
          });

      return (numberOfAccounts == 0 );
    }

  private  boolean validateNumberOfAccountsbusiness(Integer clientId){
    accountRepository.findAll()
        .filter(p -> p.getClientId().equals(clientId))
        // falta validar el tipo de cuenta para empresarial
        .count()
        .subscribe(count->{
          numberOfAccounts = count;
        });
    return (numberOfAccounts == 0 );
  }
}
