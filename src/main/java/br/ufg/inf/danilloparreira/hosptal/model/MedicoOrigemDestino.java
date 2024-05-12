package br.ufg.inf.danilloparreira.hosptal.model;

import br.ufg.inf.danilloparreira.hosptal.model.abstracts.Medico;
import br.ufg.inf.danilloparreira.hosptal.utils.HospitalUtil;
import java.util.ArrayList;
import java.util.List;

public class MedicoOrigemDestino extends Medico {

    private String especialidade;
    private Hospital hospital;
    private List<Paciente> pacientes;

    public MedicoOrigemDestino(String nome, String crm, String especialidade, Hospital hospital) {
        super(nome, crm);
        this.especialidade = especialidade;
        this.hospital = hospital;
        this.pacientes = new ArrayList<>();
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public Hospital getHospital() {
        return hospital;
    }

    public void setHospital(Hospital hospital) {
        this.hospital = hospital;
    }

    public List<Paciente> getPacientes() {
        return pacientes;
    }

    /**
     * Classe MedicoOrigemDestino extende a classe Medico e implementa o método
     * de solicitarTransferencia(); Atendendo ao RF01, o método da classe
     * retorna um objeto Solicitacao, com os dados necessários pra que o médico
     * responsável destine essa transferencia
     *
     * @param paciente
     * @param procedimento
     * @param documento
     * @return
     */
    public Solicitacao solicitarTransferencia(Paciente paciente, String procedimento, String documento) {
        if (!pacientes.contains(paciente)) {
            throw new RuntimeException(String.format("Esse paciente %s nao esta vinculado a este medico %s.", paciente.getNome(), this.getNome()));
        }
        Solicitacao solicitacao = new Solicitacao(paciente, procedimento, documento, this);
        HospitalUtil.separador();
        System.out.printf("Solicitacao de transferencia do paciente %s foi criada.\n", paciente.getNome());
        System.out.println("DADOS DA SOLICITACAO");
        solicitacao.mostrarDados();
        HospitalUtil.separador();
        return solicitacao;
    }

    @Override
    public void mostrarDados() {
        HospitalUtil.dadosId(id);
        System.out.printf("Nome: %s\nCRM: %s\nHospital: %s\nEspecialidade: %s\n",
                getNome(), getCrm(), hospital.getNome(), especialidade);
    }

}
