package br.ufg.inf.danilloparreira.hosptal.model;

import br.ufg.inf.danilloparreira.hosptal.model.abstracts.Medico;
import br.ufg.inf.danilloparreira.hosptal.utils.HospitalUtil;
import java.util.ArrayList;
import java.util.List;

public class MedicoRegulador extends Medico {

    public MedicoRegulador(String nome, String crm) {
        super(nome, crm);
    }

    /**
     * A partir da lista de hospitais, criamos uma lista filtrando o hopital de
     * origem e somente os hospitais disponíveis.
     *
     * @param hospitais de todos hospitais cadastrados
     * @return lista de hospitais disponíveis.
     */
    public List<Hospital> buscarUnidade(List<Hospital> hospitais, Hospital origem) {
//        return hospitais.stream()
//                .filter(hospital -> hospital.isDisponivel())
//                .collect(Collectors.toList());
        List<Hospital> hospitalDisponivel = new ArrayList<>();
        for (Hospital hospital : hospitais) {
            if (hospital.isDisponivel() && !hospital.equals(origem)) {
                hospitalDisponivel.add(hospital);
            }
        }
        HospitalUtil.separador();
        if (hospitalDisponivel.isEmpty()) {
            System.out.println("Nao existe hospitais disponiveis.");
        } else {
            System.out.printf("Numero de Hospitais disponiveis: %d\n", hospitalDisponivel.size());
        }
        return hospitalDisponivel;
    }

    /**
     * Medico regulador irá atualizar a solicitação adicionando o medicoDestino
     * e o responsavel pela atualização que é ele mesmo e atualiza a
     * disponibilidade do hospital de destino
     *
     * @param medicoRegulador
     * @param medicoDestino
     */
    public void atualizaSolitacao(Solicitacao solicitacao, MedicoOrigemDestino medicoDestino) {
        if (medicoDestino.equals(solicitacao.getMedicoOrigem())) {
            throw new RuntimeException(String.format("O medico de destino e o mesmo medico da origem.", medicoDestino.getHospital().getNome()));
        }
        if (!medicoDestino.getHospital().isDisponivel()) {
            throw new RuntimeException(String.format("Hospital %s selecionado nao esta disponivel", medicoDestino.getHospital().getNome()));
        }
        if (medicoDestino.getHospital() == solicitacao.getMedicoOrigem().getHospital()) {
            throw new RuntimeException(String.format("Hospital de destino %s nao pode ser o mesmo hospital da origem.", medicoDestino.getHospital().getNome()));
        }
        solicitacao.setMedicoRegulador(this);
        solicitacao.setMedicoDestino(medicoDestino);
        medicoDestino.getHospital().atualizaDisponivel();
        if (!solicitacao.getMedicoOrigem().getHospital().isDisponivel()) {
            solicitacao.getMedicoOrigem().getHospital().atualizaDisponivel();
        }
        System.out.println("Transferencia efetivada.");
        solicitacao.mostrarDados();
    }

    @Override
    public void mostrarDados() {
        HospitalUtil.separador();
        HospitalUtil.dadosId(id);
        System.out.printf("Nome: %s\nCRM: %s\n",
                getNome(), getCrm());
    }

}
