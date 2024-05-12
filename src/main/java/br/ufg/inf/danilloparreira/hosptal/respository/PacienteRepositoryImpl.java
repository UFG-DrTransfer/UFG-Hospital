package br.ufg.inf.danilloparreira.hosptal.respository;

import br.ufg.inf.danilloparreira.hosptal.model.Paciente;
import br.ufg.inf.danilloparreira.hosptal.respository.generic.GenericRepositoryImpl;

public class PacienteRepositoryImpl extends GenericRepositoryImpl<Paciente> implements PacienteRepository{

    public PacienteRepositoryImpl() {
        super("Paciente");
    }

}
