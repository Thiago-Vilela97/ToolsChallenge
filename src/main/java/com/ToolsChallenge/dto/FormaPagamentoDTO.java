package com.ToolsChallenge.dto;

import com.ToolsChallenge.entity.TipoPagamento;
import lombok.Data;

@Data
public class FormaPagamentoDTO {
    private TipoPagamento tipo;
    private Integer parcelas;
}
