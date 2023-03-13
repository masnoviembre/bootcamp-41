package com.nttdata.bank.client.bankclient.business.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "Activo")
public class Activo {

    @Id
    private Integer activoId;
    private String tipoCliente;
    private String tipoCredito;
}
