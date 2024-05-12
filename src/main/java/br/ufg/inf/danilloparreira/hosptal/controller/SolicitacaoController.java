package br.ufg.inf.danilloparreira.hosptal.controller;

import br.ufg.inf.danilloparreira.hosptal.model.Solicitacao;
import br.ufg.inf.danilloparreira.hosptal.respository.SolicitacaoRepository;
import br.ufg.inf.danilloparreira.hosptal.respository.SolicitacaoRepositoryImpl;
import java.util.ArrayList;
import java.util.List;

public class SolicitacaoController extends GenericController<Solicitacao, SolicitacaoRepository> {

    public SolicitacaoController() {
        super(new SolicitacaoRepositoryImpl());
    }

    /**
     * Filtra de todas as solicitacoes somente a solicitacao em aberto
     *
     * @return lista de solicitacao em aberto.
     */
    public List<Solicitacao> findSolicitacaoEmAberto() {
        List<Solicitacao> listaFiltrada = new ArrayList<>();
        for (Solicitacao solicitacao : findAll()) {
            if (solicitacao.getDataTransferencia() == null) {
                listaFiltrada.add(solicitacao);
            }
        }
        return listaFiltrada;
    }

    /**
     * Mostra na tela todas solicitacoes em aberto
     */
    public void listarEmAberto() {
        listar(findSolicitacaoEmAberto());
    }
}
