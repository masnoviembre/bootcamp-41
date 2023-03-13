package com.nttdata.bank.account.bankaccount.business.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data

public class ClientProductPaymentDto {

    private Integer id;
    private Integer idClient;
    private String product;
    private String typeProduct;
    private String status;
}
