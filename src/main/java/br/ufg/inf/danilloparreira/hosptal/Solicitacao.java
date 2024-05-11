package br.ufg.inf.danilloparreira.hosptal;

import java.time.LocalDateTime;

/**
 *
 * @author Danillo Tomaz Parreira
 */
public class Solicitacao extends SuperClasse implements Mostrar {
    
    private String procedimento;
    private String documento;
    private Paciente paciente;
    private MedicoOrigemDestino medicoOrigem;
    private MedicoOrigemDestino medicoDestino;
    private MedicoRegulador medicoRegulador;
    private LocalDateTime dataSolicitacao;
    private LocalDateTime dataTransferencia;
    
    public Solicitacao(int id, Paciente paciente, String procedimento, String documento,
            MedicoOrigemDestino medicoOrigem) {
        super(id);
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
    
    @Override
    public void mostrarDados() {
        HospitalUtil.separador();
        HospitalUtil.dadosId(id);
        System.out.printf("Hospital Origem: %s\nMedico: %s\nPaciente: %s\nProcedimento: %s\nDocumento: %s\nData Solicitacao %s\n",
                medicoOrigem.getHospital().getNome(),
                medicoOrigem.getNome(),
                paciente.getNome(),
                procedimento,
                documento,
                HospitalUtil.dataFormatada(dataSolicitacao)
        );
        if (dataTransferencia != null) {
            System.out.printf("Medico Regulador: %s\nData Transferencia: %s\n",
                    medicoOrigem.getNome(),
                    HospitalUtil.dataFormatada(dataTransferencia));
        }
    }
}
