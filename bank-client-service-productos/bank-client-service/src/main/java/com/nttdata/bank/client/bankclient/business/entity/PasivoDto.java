package com.nttdata.bank.client.bankclient.business.entity;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class PasivoDto {

  @NotNull(message = "el código es requerido")
  private Integer pasivoId;

  @NotNull(message = "el nombre es requerido")
  @Size(min = 2, max = 100, message = "El nombre debe tener entre {min} y {max} caracteres")
  private String tipoCliente;

  @NotNull(message = "el nombre es requerido")
  @Size(min = 2, max = 100, message = "El nombre debe tener entre {min} y {max} caracteres")
  private String tipoCuenta;

}
