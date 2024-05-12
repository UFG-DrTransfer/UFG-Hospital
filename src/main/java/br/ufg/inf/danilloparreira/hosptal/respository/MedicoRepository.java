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
     * @return true caso tiver adicionado e false caso já estiver adicionado.
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
     * @return <b>false</b> caso o paciente não for do medico informado<br/>
     * <b>true</b> caso o paciente for inserido
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
     * Método com a finalidade de transferir paciente de um medico origem ao
     * medico destino
     *
     * @param origem
     * @param destino
     * @param paciente
     * @return <b>false</b> caso der alguma falha ao transferir <br/>
     * <b>true</b> caso tiver transferido com sucesso
     */
    default boolean transferirPaciente(MedicoOrigemDestino origem, MedicoOrigemDestino destino, Paciente paciente) {
        boolean confirmacao = false;
        confirmacao = removerPaciente(origem, paciente);
        if (!confirmacao) {
            throw new RuntimeException("Nao foi possivel remover o paciente de origem.");
        }
        return addPaciente(destino, paciente);
    }

    /**
     * Método com a finalidade de listar os médicos conforme o tipo do medico,
     * se passar null no tipo ele irá retornar toda a lista
     *
     * @param tipo
     * @return lista de medicos conforme o tipo passado.
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

    /**
     * Busca todos medicos origem destino
     *
     * @return todos medicos filtrando o os MedicoOrigemDestino
     */
    default List<Medico> findAllMedicoOperadorDestino() {
        return findAll(MedicoOrigemDestino.class);
    }

    /**
     * Busca todos medicos regulador
     *
     * @return todos medicos filtrando o os MedicoRegulador
     */
    default List<Medico> findAllMedicoRegulador() {
        return findAll(MedicoRegulador.class);
    }
}
