package br.ufg.inf.danilloparreira.hosptal.model;

import br.ufg.inf.danilloparreira.hosptal.model.abstracts.SuperClasse;
import br.ufg.inf.danilloparreira.hosptal.utils.HospitalUtil;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 *
 * @author Danillo Tomaz Parreira
 */
public class Solicitacao extends SuperClasse {

    private String procedimento;
    private String documento;
    private Paciente paciente;
    private MedicoOrigemDestino medicoOrigem;
    private MedicoOrigemDestino medicoDestino;
    private MedicoRegulador medicoRegulador;
    private LocalDateTime dataSolicitacao;
    private LocalDateTime dataTransferencia;

    public Solicitacao(Paciente paciente, String procedimento, String documento,
            MedicoOrigemDestino medicoOrigem) {
        this.dataSolicitacao = LocalDateTime.now();
        this.procedimento = procedimento;
        this.documento = documento;
        this.paciente = paciente;
        this.medicoOrigem = medicoOrigem;
    }

    public String getProcedimento() {
        return procedimento;
    }

    public String getDocumento() {
        return documento;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public MedicoOrigemDestino getMedicoOrigem() {
        return medicoOrigem;
    }

    public MedicoOrigemDestino getMedicoDestino() {
        return medicoDestino;
    }

    public void setMedicoDestino(MedicoOrigemDestino medicoDestino) {
        this.medicoDestino = medicoDestino;
    }

    public MedicoRegulador getMedicoRegulador() {
        return medicoRegulador;
    }

    public void setMedicoRegulador(MedicoRegulador medicoRegulador) {
        this.medicoRegulador = medicoRegulador;
        this.dataTransferencia = LocalDateTime.now();
    }

    public LocalDateTime getDataSolicitacao() {
        return dataSolicitacao;
    }

    public LocalDateTime getDataTransferencia() {
        return dataTransferencia;
    }

    public void atualizaDataTransferencia() {
        this.dataTransferencia = LocalDateTime.now();
    }

    public String getStatus() {
        return dataTransferencia == null ? "Em aberto" : "Transferido";
    }

    @Override
    public void mostrarDados() {
        HospitalUtil.separador();
        HospitalUtil.dadosId(id);
        System.out.printf("Hospital Origem: %s\nMedico Origem: %s\nPaciente: %s\nProcedimento: %s\nDocumento: %s\nData Solicitacao: %s\nStatus: %s\n",
                medicoOrigem.getHospital().getNome(),
                medicoOrigem.getNome(),
                paciente.getNome(),
                procedimento,
                documento,
                HospitalUtil.dataFormatada(dataSolicitacao),
                getStatus()
        );
        if (dataTransferencia != null) {
            System.out.printf("Medico Regulador: %s\nData Transferencia: %s\nHospital Destino: %s\nMedico Destino: %s\n",
                    medicoRegulador.getNome(),
                    HospitalUtil.dataFormatada(dataTransferencia),
                    medicoDestino.getHospital().getNome(),
                    medicoDestino.getNome());
        }
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + Objects.hashCode(this.paciente);
        hash = 37 * hash + Objects.hashCode(this.dataSolicitacao);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Solicitacao other = (Solicitacao) obj;
        if (!Objects.equals(this.paciente, other.paciente)) {
            return false;
        }
        return this.dataTransferencia == null && other.dataTransferencia == null;
    }

}
