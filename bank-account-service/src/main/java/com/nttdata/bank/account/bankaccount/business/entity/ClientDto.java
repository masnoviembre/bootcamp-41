package com.nttdata.bank.account.bankaccount.business.entity;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class ClientDto {

  private Integer clientId;
  private String clientName;
  private String clientType;
  private String clientDocument;

}
