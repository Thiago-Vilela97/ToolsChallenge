package com.ToolsChallenge.dto;

import lombok.Data;

@Data
public class TransacaoDTO {
    private String cartao;
    private DescricaoDTO descricao;
    private FormaPagamentoDTO formaPagamento;
}
