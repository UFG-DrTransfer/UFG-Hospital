package br.ufg.inf.danilloparreira.hosptal.controller;

import br.ufg.inf.danilloparreira.hosptal.model.MedicoOrigemDestino;
import br.ufg.inf.danilloparreira.hosptal.model.Paciente;
import br.ufg.inf.danilloparreira.hosptal.respository.PacienteRepository;
import br.ufg.inf.danilloparreira.hosptal.respository.PacienteRepositoryImpl;
import static br.ufg.inf.danilloparreira.hosptal.utils.HospitalUtil.*;
import java.util.List;

/**
 *
 * @author Danillo Tomaz Parreira
 */
public class PacienteController extends GenericController<Paciente, PacienteRepository> {

    public PacienteController() {
        super(new PacienteRepositoryImpl());
    }

    public void listar(MedicoOrigemDestino medico) {
        List<Paciente> listaPacientes = medico == null ? findAll() : medico.getPacientes();
        if (listaPacientes.isEmpty()) {
            listaVazia();
        } else {
            for (Paciente paciente : listaPacientes) {
                paciente.mostrarDados();
            }
        }
    }

}
