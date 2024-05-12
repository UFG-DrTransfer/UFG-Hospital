package br.ufg.inf.danilloparreira.hosptal.respository;

import br.ufg.inf.danilloparreira.hosptal.model.abstracts.Medico;
import br.ufg.inf.danilloparreira.hosptal.respository.generic.GenericRepositoryImpl;

/**
 *
 * @author Danillo Tomaz Parreira
 */
public class MedicoRepositoryImpl extends GenericRepositoryImpl<Medico> implements MedicoRepository{

    public MedicoRepositoryImpl() {
        super("Medico");
    }
    
}
