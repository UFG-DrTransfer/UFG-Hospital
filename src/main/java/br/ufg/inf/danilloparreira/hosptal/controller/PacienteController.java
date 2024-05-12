package br.ufg.inf.danilloparreira.hosptal.controller;

import br.ufg.inf.danilloparreira.hosptal.model.Paciente;
import br.ufg.inf.danilloparreira.hosptal.respository.PacienteRepository;
import br.ufg.inf.danilloparreira.hosptal.respository.PacienteRepositoryImpl;

public class PacienteController extends GenericController<Paciente, PacienteRepository> {

    public PacienteController() {
        super(new PacienteRepositoryImpl());
    }
}
