package com.ToolsChallenge;

import com.ToolsChallenge.dto.DescricaoDTO;
import com.ToolsChallenge.dto.FormaPagamentoDTO;
import com.ToolsChallenge.dto.TransacaoDTO;
import com.ToolsChallenge.entity.Descricao;
import com.ToolsChallenge.entity.FormaPagamento;
import com.ToolsChallenge.entity.TipoPagamento;
import com.ToolsChallenge.entity.Transacao;
import com.ToolsChallenge.repository.TransacaoRepository;
import com.ToolsChallenge.service.TransacaoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.Mockito.when;

public class TransacaoServiceTest {

    @Mock
    private TransacaoRepository transacaoRepository;

    @InjectMocks
    private TransacaoService transacaoService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRealizarPagamento() {
        // Cria o DTO de entrada
        TransacaoDTO transacaoDTO = new TransacaoDTO();
        transacaoDTO.setCartao("123456789");

        DescricaoDTO descricaoDTO = new DescricaoDTO();
        descricaoDTO.setValor(500.50);
        descricaoDTO.setDataHora(LocalDateTime.now());
        descricaoDTO.setEstabelecimento("PetShop");
        transacaoDTO.setDescricao(descricaoDTO);

        FormaPagamentoDTO formaPagamentoDTO = new FormaPagamentoDTO();
        formaPagamentoDTO.setTipo(TipoPagamento.AVISTA);
        formaPagamentoDTO.setParcelas(1);
        transacaoDTO.setFormaPagamento(formaPagamentoDTO);

        // Simula o comportamento do repositório
        Transacao transacaoSalva = new Transacao();
        transacaoSalva.setId(1L); // O ID é gerado no repositório
        transacaoSalva.setCartao("123456789");

        Descricao descricaoSalva = new Descricao();
        descricaoSalva.setValor(500.50);
        descricaoSalva.setDataHora(LocalDateTime.now());
        descricaoSalva.setEstabelecimento("PetShop");
        transacaoSalva.setDescricao(descricaoSalva);

        FormaPagamento formaPGSalva = new FormaPagamento();
        formaPGSalva.setTipoPagamento(TipoPagamento.AVISTA);
        formaPGSalva.setParcelas(1);
        transacaoSalva.setFormaPagamento(formaPGSalva);

        // Configura outros campos da entidade...

        when(transacaoRepository.save(any(Transacao.class))).thenReturn(transacaoSalva);

        // Chama o método do serviço
        TransacaoDTO resultado = transacaoService.realizarPagamento(transacaoDTO);

        // Verifica os resultados
        assertNotNull(resultado);
        assertEquals("123456789", resultado.getCartao()); // Verifica o cartão
        assertEquals(500.50, resultado.getDescricao().getValor()); // Verifica o valor
        assertEquals("PetShop", resultado.getDescricao().getEstabelecimento()); // Verifica o estabelecimento
        assertEquals(TipoPagamento.AVISTA, resultado.getFormaPagamento().getTipo()); // Verifica o tipo de pagamento
        assertEquals(1, resultado.getFormaPagamento().getParcelas()); // Verifica as parcelas
    }

    @Test
    public void testConsultarTransacao() {
        // Cria uma entidade Transacao para simular o retorno do repositório
        Transacao transacao = new Transacao();
        transacao.setId(1L); // ID gerado pelo repositório
        transacao.setCartao("123456789");

        Descricao descricao = new Descricao();
        descricao.setValor(500.50);
        descricao.setDataHora(LocalDateTime.now());
        descricao.setEstabelecimento("PetShop");
        transacao.setDescricao(descricao);

        FormaPagamento formaPagamento = new FormaPagamento();
        formaPagamento.setTipoPagamento(TipoPagamento.AVISTA);
        formaPagamento.setParcelas(1);
        transacao.setFormaPagamento(formaPagamento);

        // Simula o comportamento do repositório
        when(transacaoRepository.findById(1L)).thenReturn(Optional.of(transacao));

        // Chama o método do serviço
        TransacaoDTO resultado = transacaoService.consultaTransacaoPorId(1L);

        // Verifica os resultados
        assertNotNull(resultado);
        assertEquals("123456789", resultado.getCartao()); // Verifica o cartão
        assertEquals(500.50, resultado.getDescricao().getValor()); // Verifica o valor
        assertEquals("PetShop", resultado.getDescricao().getEstabelecimento()); // Verifica o estabelecimento
        assertEquals(TipoPagamento.AVISTA, resultado.getFormaPagamento().getTipo()); // Verifica o tipo de pagamento
        assertEquals(1, resultado.getFormaPagamento().getParcelas()); // Verifica as parcelas
    }
}
