package br.ufg.inf.danilloparreira.hosptal.controller;

import br.ufg.inf.danilloparreira.hosptal.model.Hospital;
import br.ufg.inf.danilloparreira.hosptal.respository.HospitalRepository;
import br.ufg.inf.danilloparreira.hosptal.respository.HospitalRepositoryImpl;

/**
 *
 * @author Danillo Tomaz Parreira
 */
public class HospitalController extends GenericController<Hospital, HospitalRepository> {

    public HospitalController() {
        super(new HospitalRepositoryImpl());
    }

}
