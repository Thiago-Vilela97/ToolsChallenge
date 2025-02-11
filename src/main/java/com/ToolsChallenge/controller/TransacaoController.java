package com.ToolsChallenge.controller;

import com.ToolsChallenge.dto.TransacaoDTO;
import com.ToolsChallenge.service.TransacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/transacoes")
public class TransacaoController {

    @Autowired
    private TransacaoService transacaoService;

    @PostMapping("/pagamento")
    public ResponseEntity<TransacaoDTO> realizarPagamento(@RequestBody TransacaoDTO transacaoDTO) {
        TransacaoDTO novaTransacao = transacaoService.realizarPagamento(transacaoDTO);
        return ResponseEntity.ok(novaTransacao);
    }

    @PostMapping("/estorno/{id}")
    public ResponseEntity<TransacaoDTO> realizarEstorno(@PathVariable Long id) {
        TransacaoDTO transacaoEstornadaDTO = transacaoService.realizarEstorno(id);
        return ResponseEntity.ok(transacaoEstornadaDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransacaoDTO> consultarTransacaoPorId(@PathVariable Long id) {
        TransacaoDTO transacaoDTO = transacaoService.consultaTransacaoPorId(id);
        return ResponseEntity.ok(transacaoDTO);
    }

    @GetMapping
    public ResponseEntity<List<TransacaoDTO>> consultarTodasTransacoes() {
        List<TransacaoDTO> transacoesDTO = transacaoService.consultarTodasTransacoes();
        return ResponseEntity.ok(transacoesDTO);
    }
}
