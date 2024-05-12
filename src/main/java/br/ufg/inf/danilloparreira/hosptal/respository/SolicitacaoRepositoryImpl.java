package br.ufg.inf.danilloparreira.hosptal.respository;

import br.ufg.inf.danilloparreira.hosptal.model.Solicitacao;
import br.ufg.inf.danilloparreira.hosptal.respository.generic.GenericRepositoryImpl;

public class SolicitacaoRepositoryImpl extends GenericRepositoryImpl<Solicitacao> implements SolicitacaoRepository {

    public SolicitacaoRepositoryImpl() {
        super("Solicitacao");
    }

}
