package com.ToolsChallenge.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FormaPagamento {
    @Enumerated(EnumType.STRING)
    private TipoPagamento tipoPagamento;
    private Integer parcelas;
}
