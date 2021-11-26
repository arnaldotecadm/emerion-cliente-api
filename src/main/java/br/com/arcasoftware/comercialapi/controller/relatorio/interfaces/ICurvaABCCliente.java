package br.com.arcasoftware.comercialapi.controller.relatorio.interfaces;

import java.math.BigDecimal;

public interface ICurvaABCCliente {
    Integer getCodCli();

    String getNomCli();

    BigDecimal getTotalPedido();

    BigDecimal getTotalCusto();

    BigDecimal getMarkup();

    Integer getQtdPedido();

}
