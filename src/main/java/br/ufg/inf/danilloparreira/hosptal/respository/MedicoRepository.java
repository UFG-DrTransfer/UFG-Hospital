package br.ufg.inf.danilloparreira.hosptal.respository;

import br.ufg.inf.danilloparreira.hosptal.model.abstracts.Medico;
import br.ufg.inf.danilloparreira.hosptal.model.MedicoOrigemDestino;
import br.ufg.inf.danilloparreira.hosptal.model.MedicoRegulador;
import br.ufg.inf.danilloparreira.hosptal.model.Paciente;
import br.ufg.inf.danilloparreira.hosptal.respository.generic.GenericRepository;
import java.util.ArrayList;
import java.util.List;

public interface MedicoRepository extends GenericRepository<Medico> {

    /**
     * Método que tem a função de adicionar paciente
     *
     * @param medico
     * @param paciente
     * @return
     */
    default boolean addPaciente(MedicoOrigemDestino medico, Paciente paciente) {
        if (medico.getPacientes().contains(paciente)) {
            System.out.println("Este paciente ja esta cadastrado");
            return false;
        }
        medico.getPacientes().add(paciente);
        System.out.println("Paciente foi incluido com sucesso!");
        return true;
    }

    /**
     * Método que tem a função de remover paciente.
     *
     * @param medico
     * @param paciente
     * @return
     */
    default boolean removerPaciente(MedicoOrigemDestino medico, Paciente paciente) {
        if (!medico.getPacientes().contains(paciente)) {
            System.out.println("Este paciente nao esta inserido na lista deste medico");
            return false;
        }
        medico.getPacientes().remove(paciente);
        System.out.println("Paciente foi removido com sucesso!");
        return true;
    }

    /**
     * Método com a finalidade de listar os médicos conforme o tipo do medico,
     * se passar null no tipo ele irá retornar toda a lista
     *
     * @param tipo
     * @return
     */
    default List<Medico> findAll(Class<? extends Medico> tipo) {
        List<Medico> listaFiltrada = new ArrayList<>();
        for (Medico medico : findAll()) {
            if (tipo == null || medico.getClass().equals(tipo)) {
                listaFiltrada.add(medico);
            }
        }
        return listaFiltrada;
    }

    default List<Medico> findAllMedicoOperadorDestino() {
        return findAll(MedicoOrigemDestino.class);
    }

    default List<Medico> findAllMedicoRegulador() {
        return findAll(MedicoRegulador.class);
    }
}
