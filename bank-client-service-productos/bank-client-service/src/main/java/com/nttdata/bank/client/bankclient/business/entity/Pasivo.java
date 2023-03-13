package com.nttdata.bank.client.bankclient.business.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "Pasivo")
public class Pasivo {

    @Id
    private Integer pasivoId;
    private String tipoCliente;
    private String tipoCuenta;
}
