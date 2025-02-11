package com.ToolsChallenge.service;

import com.ToolsChallenge.dto.DescricaoDTO;
import com.ToolsChallenge.dto.FormaPagamentoDTO;
import com.ToolsChallenge.dto.TransacaoDTO;
import com.ToolsChallenge.entity.Descricao;
import com.ToolsChallenge.entity.FormaPagamento;
import com.ToolsChallenge.entity.StatusTransacao;
import com.ToolsChallenge.entity.Transacao;
import com.ToolsChallenge.repository.TransacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TransacaoService {
    @Autowired
    private TransacaoRepository transacaoRepository;

    @Transactional
    public TransacaoDTO realizarPagamento(TransacaoDTO transacaoDTO) {

        Transacao transacao = convertToEntity(transacaoDTO);

        Descricao descricao = convertToEntity(transacaoDTO.getDescricao());
        descricao.setNsu(generateNSU());
        descricao.setCodigoAutorizacao(generateCodigoAutorizacao());
        descricao.setStatus(StatusTransacao.AUTORIZADO);
        transacao.setDescricao(descricao);

        Transacao transacaoSalva = transacaoRepository.save(transacao);
        return convertToDTO(transacaoSalva);
    }

    @Transactional
    public TransacaoDTO realizarEstorno(Long idTransacao) {
        Optional<Transacao> transacaoOpt = transacaoRepository.findById(idTransacao);
        if (!transacaoOpt.isPresent()) {
            throw new RuntimeException("Transação não encontrada");
        }

        Transacao transacao = transacaoOpt.get();
        transacao.getDescricao().setStatus(StatusTransacao.NEGADO);

        Transacao transacaoSalva = transacaoRepository.save(transacao);
        return convertToDTO(transacaoSalva);
    }

    public TransacaoDTO consultaTransacaoPorId(Long idTransacao) {
        Transacao transacao = transacaoRepository.findById(idTransacao).orElseThrow(() -> new RuntimeException("Transação com o id: "+ idTransacao +" não encontrada"));
        return convertToDTO(transacao);
    }

    public List<TransacaoDTO> consultarTodasTransacoes() {
        return transacaoRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private String generateCodigoAutorizacao() {
        return String.valueOf(System.currentTimeMillis()).substring(5);
    }

    private String generateNSU() {
        return String.valueOf(System.currentTimeMillis()).substring(5);
    }

    private Transacao convertToEntity(TransacaoDTO transacaoDTO) {
        Transacao transacao = new Transacao();
        transacao.setCartao(transacaoDTO.getCartao());
        transacao.setDescricao(convertToEntity(transacaoDTO.getDescricao()));
        transacao.setFormaPagamento(convertToEntity(transacaoDTO.getFormaPagamento()));
        return transacao;
    }

    private Descricao convertToEntity(DescricaoDTO descricaoDTO) {
        Descricao descricao = new Descricao();
        descricao.setValor(descricaoDTO.getValor());
        descricao.setDataHora(descricaoDTO.getDataHora());
        descricao.setEstabelecimento(descricaoDTO.getEstabelecimento());
        descricao.setNsu(descricaoDTO.getNsu());
        descricao.setCodigoAutorizacao(descricaoDTO.getCodigoAutorizacao());
        descricao.setStatus(descricaoDTO.getStatus());
        return descricao;
    }

    private FormaPagamento convertToEntity(FormaPagamentoDTO formaPagamentoDTO) {
        FormaPagamento formaPagamento = new FormaPagamento();
        formaPagamento.setTipoPagamento(formaPagamentoDTO.getTipo());
        formaPagamento.setParcelas(formaPagamentoDTO.getParcelas());
        return formaPagamento;
    }

    private TransacaoDTO convertToDTO(Transacao transacao) {
        TransacaoDTO transacaoDTO = new TransacaoDTO();
        transacaoDTO.setCartao(transacao.getCartao());

        // Verifica se descricao não é nulo antes de acessar seus métodos
        if (transacao.getDescricao() != null) {
            DescricaoDTO descricaoDTO = convertToDTO(transacao.getDescricao());
            transacaoDTO.setDescricao(descricaoDTO);
        }

        // Verifica se formaPagamento não é nulo antes de acessar seus métodos
        if (transacao.getFormaPagamento() != null) {
            FormaPagamentoDTO formaPagamentoDTO = convertToDTO(transacao.getFormaPagamento());
            transacaoDTO.setFormaPagamento(formaPagamentoDTO);
        }

        return transacaoDTO;
    }

    private DescricaoDTO convertToDTO(Descricao descricao) {
        DescricaoDTO descricaoDTO = new DescricaoDTO();
        descricaoDTO.setValor(descricao.getValor());
        descricaoDTO.setDataHora(descricao.getDataHora());
        descricaoDTO.setEstabelecimento(descricao.getEstabelecimento());
        descricaoDTO.setNsu(descricao.getNsu());
        descricaoDTO.setCodigoAutorizacao(descricao.getCodigoAutorizacao());
        descricaoDTO.setStatus(descricao.getStatus());
        return descricaoDTO;
    }

    private FormaPagamentoDTO convertToDTO(FormaPagamento formaPagamento) {
        FormaPagamentoDTO formaPagamentoDTO = new FormaPagamentoDTO();
        formaPagamentoDTO.setTipo(formaPagamento.getTipoPagamento());
        formaPagamentoDTO.setParcelas(formaPagamento.getParcelas());
        return formaPagamentoDTO;
    }

}
