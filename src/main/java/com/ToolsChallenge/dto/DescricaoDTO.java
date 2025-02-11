package com.ToolsChallenge.dto;

import com.ToolsChallenge.entity.StatusTransacao;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DescricaoDTO {
    private Double valor;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime dataHora;
    private String estabelecimento;
    private String nsu;
    private String codigoAutorizacao;
    private StatusTransacao status;
}
