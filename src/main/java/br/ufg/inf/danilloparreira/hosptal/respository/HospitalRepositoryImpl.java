package br.ufg.inf.danilloparreira.hosptal.respository;

import br.ufg.inf.danilloparreira.hosptal.model.Hospital;
import br.ufg.inf.danilloparreira.hosptal.respository.generic.GenericRepositoryImpl;

/**
 *
 * @author Danillo Tomaz Parreira
 */
public class HospitalRepositoryImpl extends GenericRepositoryImpl<Hospital> implements HospitalRepository {

    public HospitalRepositoryImpl() {
        super("Hospital");
    }

}
