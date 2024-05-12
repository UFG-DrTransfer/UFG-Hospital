package br.ufg.inf.danilloparreira.hosptal.controller;

import br.ufg.inf.danilloparreira.hosptal.model.Hospital;
import br.ufg.inf.danilloparreira.hosptal.model.MedicoOrigemDestino;
import br.ufg.inf.danilloparreira.hosptal.model.MedicoRegulador;
import br.ufg.inf.danilloparreira.hosptal.model.Paciente;
import br.ufg.inf.danilloparreira.hosptal.model.Solicitacao;
import br.ufg.inf.danilloparreira.hosptal.model.abstracts.Medico;
import br.ufg.inf.danilloparreira.hosptal.respository.MedicoRepository;
import br.ufg.inf.danilloparreira.hosptal.respository.MedicoRepositoryImpl;
import br.ufg.inf.danilloparreira.hosptal.utils.HospitalException;
import br.ufg.inf.danilloparreira.hosptal.utils.HospitalUtil;
import static br.ufg.inf.danilloparreira.hosptal.utils.HospitalUtil.*;
import java.util.ArrayList;
import java.util.List;

public class MedicoController extends GenericController<Medico, MedicoRepository> {

    private final PacienteController pacienteController;
    private final SolicitacaoController solicitacaoController;

    /**
     * É passado o pacienteController e a solicitacaoControle para manter os
     * dados já cadastrados do repository destestes controlers
     *
     * @param pacienteController
     * @param solicitacaoController
     */
    public MedicoController(PacienteController pacienteController,
            SolicitacaoController solicitacaoController) {
        super(new MedicoRepositoryImpl());
        this.pacienteController = pacienteController;
        this.solicitacaoController = solicitacaoController;
    }

    /**
     * Lista os medicos de origem/destino
     */
    private void listarMedicoOrigemDestino() {
        separador();
        System.out.println("LISTA DE MEDICO ORIGEM/DESTINO");
        super.listar(MedicoOrigemDestino.class);
    }

    /**
     * Da lista de todos medicos ele filtra por medicoOrigemDestino, medico que
     * nao seja do mesmo hospital e o hospital do medico que esteja disponivel.
     *
     * @param hospital
     */
    public void listar(Hospital hospital) {
        List<MedicoOrigemDestino> filtrado = new ArrayList<>();
        for (Medico entidade : findAll()) {
            if (entidade instanceof MedicoOrigemDestino
                    && !((MedicoOrigemDestino) entidade).getHospital().equals(hospital)
                    && ((MedicoOrigemDestino) entidade).getHospital().isDisponivel()) {
                filtrado.add((MedicoOrigemDestino) entidade);
            }
        }
        separador();
        System.out.println("LISTA DE MEDICO ORIGEM/DESTINO");
        super.listar(filtrado);
    }

    /**
     * Adiciona o paciente ao medicoOrigem fazendo as validacoes necessárias
     *
     * @param medico
     * @param paciente
     * @return true caso tenha adicionado e false caso não tenha adicionado
     */
    public boolean adicionarPaciente(MedicoOrigemDestino medico, Paciente paciente) {
        return repository.addPaciente(medico, paciente);
    }

    /**
     * Operacao principal do Medico Origem/Destino
     */
    public void operacaoSelecionarMedicoOrigem() {
        do {
            try {
                listarMedicoOrigemDestino();
                int quantidadeOpcao = HospitalUtil.listaOpcoes("Selecionar Medico pelo id:");
                int opcao = HospitalUtil.getValorInteger();
                if (HospitalUtil.validaOpcao(quantidadeOpcao, opcao)) {
                    switch (opcao) {
                        case 1:
                            setNomeDaClasse("Medico da Origem");
                            MedicoOrigemDestino medico = (MedicoOrigemDestino) getValido(MedicoOrigemDestino.class);
                            operacaoSolicitarTransferencia(medico);
                            break;
                        case 0:
                            return;
                        default:
                            opcaoInvalida();
                    }
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (true);
    }

    /**
     * Operacao para a solicitacao de transferencia passando o medico informado
     * na operacao anterior
     *
     * @param medico a fazer a solicitacao de transferencia
     */
    private void operacaoSolicitarTransferencia(MedicoOrigemDestino medico) {
        do {
            try {
                int quantidadeOpcao = HospitalUtil.listaOpcoes(!medico.getPacientes().isEmpty() ? "Solicitar Transferencia:" : "");
                int opcao = HospitalUtil.getValorInteger();
                if (HospitalUtil.validaOpcao(quantidadeOpcao, opcao)) {
                    switch (opcao) {
                        case 1:
                            solicitacaoTransferencia(medico);
                            return;
                        case 0:
                            return;
                        default:
                            opcaoInvalida();
                    }
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (true);
    }

    /**
     * Iniciado processo de solicitacao de transferencia do medico Origem
     *
     * @param medico
     */
    private void solicitacaoTransferencia(MedicoOrigemDestino medico) {
        try {
            System.out.println("SOLICITACAO TRANSFERENCIA");
            pacienteController.listar(medico.getPacientes());
            Paciente paciente = pacienteController.getValido(medico.getPacientes());
            validaEntidade(paciente);
            String procedimento = HospitalUtil.getValor("Informe o nome do procedimento:");
            String documento = HospitalUtil.getValor("Informe o nome documento:");
            Solicitacao solicitacao = medico.solicitarTransferencia(paciente, procedimento, documento);
            solicitacaoController.adicionar(solicitacao);
        } catch (RuntimeException | HospitalException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * lista medico regulador
     */
    private void listarMedicoRegulador() {
        separador();
        System.out.println("LISTA DE MEDICO REGULADOR");
        super.listar(MedicoRegulador.class);
    }

    /**
     * Operacao principal do medico regulador
     */
    public void operacaoMedicoRegulador() {
        do {
            try {
                listarMedicoRegulador();
                List<Solicitacao> listaSolicitacaoAberto = solicitacaoController.findSolicitacaoEmAberto();
                int quantidadeOpcao = HospitalUtil.listaOpcoes(!listaSolicitacaoAberto.isEmpty() ? "Selecionar Medico Regulador" : "");
                int opcao = HospitalUtil.getValorInteger();
                if (HospitalUtil.validaOpcao(quantidadeOpcao, opcao)) {
                    switch (opcao) {
                        case 1:
                            setNomeDaClasse("Medico Regulador");
                            listarMedicoRegulador();
                            MedicoRegulador medicoRegulador = (MedicoRegulador) getValido(MedicoRegulador.class);
                            operacaoMedicoReguladorSolicitacao(medicoRegulador);
                            break;
                        case 0:
                            return;
                        default:
                            opcaoInvalida();
                    }
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (true);
    }

    /**
     * Operacao de confirmacao de transferencia de um paciente de um medico ao
     * outro
     *
     * @param medicoRegulador
     */
    private void operacaoMedicoReguladorSolicitacao(MedicoRegulador medicoRegulador) {
        do {
            try {
                separador();
                int quantidadeOpcao = HospitalUtil.listaOpcoes(solicitacaoController.findSolicitacaoEmAberto().isEmpty() ? "" : "Realizar Transferencia");
                int opcao = HospitalUtil.getValorInteger();
                if (HospitalUtil.validaOpcao(quantidadeOpcao, opcao)) {
                    switch (opcao) {
                        case 1:
                            solicitacaoController.listarEmAberto();
                            Solicitacao solicitacao = solicitacaoController.getValido();
                            HospitalUtil.validaEntidade(opcao);
                            do {
                                try {
                                    setNomeDaClasse("Medico Destino");
                                    listar(solicitacao.getMedicoOrigem().getHospital());
                                    MedicoOrigemDestino medicoDestino = (MedicoOrigemDestino) getValido(MedicoOrigemDestino.class);
                                    validaEntidade(medicoDestino);
                                    medicoRegulador.atualizaSolitacao(solicitacao, medicoDestino);
                                    repository.transferirPaciente(solicitacao.getMedicoOrigem(),
                                            solicitacao.getMedicoDestino(),
                                            solicitacao.getPaciente());
                                    break;
                                } catch (HospitalException e) {
                                    System.out.println(e.getMessage());
                                    break;
                                } catch (Exception e) {
                                    System.out.println(e.getMessage());
                                }
                            } while (true);
                            return;
                        case 0:
                            return;
                        default:
                            opcaoInvalida();
                    }
                }
            } catch (RuntimeException | HospitalException e) {
                System.out.println(e.getMessage());
            }
        } while (true);
    }
}
