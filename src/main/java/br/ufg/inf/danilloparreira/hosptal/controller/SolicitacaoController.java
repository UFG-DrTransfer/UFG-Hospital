package br.ufg.inf.danilloparreira.hosptal.controller;

import br.ufg.inf.danilloparreira.hosptal.model.Solicitacao;
import br.ufg.inf.danilloparreira.hosptal.respository.SolicitacaoRepository;
import br.ufg.inf.danilloparreira.hosptal.respository.SolicitacaoRepositoryImpl;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Danillo Tomaz Parreira
 */
public class SolicitacaoController extends GenericController<Solicitacao, SolicitacaoRepository> {

    public SolicitacaoController() {
        super(new SolicitacaoRepositoryImpl());
    }

    public List<Solicitacao> findSolicitacaoEmAberto() {
        List<Solicitacao> listaFiltrada = new ArrayList<>();
        for (Solicitacao solicitacao : findAll()) {
            if (solicitacao.getDataTransferencia() == null) {
                listaFiltrada.add(solicitacao);
            }
        }
        return listaFiltrada;
    }

    public void listarEmAberto() {
        listar(findSolicitacaoEmAberto());
    }
}
