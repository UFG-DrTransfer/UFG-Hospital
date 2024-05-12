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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Danillo Tomaz Parreira
 */
public class MedicoController extends GenericController<Medico, MedicoRepository> {

    private final PacienteController pacienteController;
    private final SolicitacaoController solicitacaoController;

    public MedicoController(PacienteController pacienteController, SolicitacaoController solicitacaoController) {
        super(new MedicoRepositoryImpl());
        this.pacienteController = pacienteController;
        this.solicitacaoController = solicitacaoController;
    }

    private void listarMedicoOrigemDestino() {
        separador();
        System.out.println("LISTA DE MEDICO ORIGEM/DESTINO");
        super.listar(MedicoOrigemDestino.class);
    }

    private void listarMedicoOrigemDestino(MedicoOrigemDestino medicoAtual) {
        separador();
        System.out.println("LISTA DE MEDICO ORIGEM/DESTINO");
        listar(medicoAtual.getHospital());
    }

    /**
     * Da lista de todos medicos ele filtra por medicoOrigemDestino, medico que
     * nao seja do mesmo hospital e o hospital do medico que esteja disponivel.
     *
     * @param classe
     * @param hospital
     */
    private void listar(Hospital hospital) {
        List<MedicoOrigemDestino> filtrado = new ArrayList<>();
        for (Medico entidade : findAll()) {
            if (entidade instanceof MedicoOrigemDestino
                    && !((MedicoOrigemDestino) entidade).getHospital().equals(hospital)
                    && ((MedicoOrigemDestino) entidade).getHospital().isDisponivel()) {
                filtrado.add((MedicoOrigemDestino) entidade);
            }
        }
        super.listar(filtrado);
    }

    public boolean adicionarPaciente(MedicoOrigemDestino medico, Paciente paciente) {
        return repository.addPaciente(medico, paciente);
    }

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

    private void solicitacaoTransferencia(MedicoOrigemDestino medico) {
        try {
            System.out.println("SOLICITACAO TRANSFERENCIA");
            pacienteController.listar(medico);
            Paciente paciente = pacienteController.getValido(medico.getPacientes());
            validaEntidade(paciente);
            String procedimento = HospitalUtil.getValor("Informe o nome do procedimento:");
            String documento = HospitalUtil.getValor("Informe o nome documento:");
            Solicitacao solicitacao = medico.solicitarTransferencia(paciente, procedimento, documento);
            solicitacaoController.adicionar(solicitacao);
        } catch (HospitalException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void listarMedicoRegulador() {
        separador();
        System.out.println("LISTA DE MEDICO REGULADOR");
        super.listar(MedicoRegulador.class);
    }

    public void operacaoMedicoRegulador() {
        do {
            try {
                listarMedicoRegulador();
                List<Solicitacao> listaSolicitacaoAberto = solicitacaoController.findAll();
                int quantidadeOpcao = HospitalUtil.listaOpcoes(!listaSolicitacaoAberto.isEmpty() ? "Selecionar Medico Regulador:" : "");
                int opcao = HospitalUtil.getValorInteger();
                if (HospitalUtil.validaOpcao(quantidadeOpcao, opcao)) {
                    switch (opcao) {
                        case 1:
                            setNomeDaClasse(MEDICO__REGULADOR);
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
    private static final String MEDICO__REGULADOR = "Medico Regulador";

    private void operacaoMedicoReguladorSolicitacao(MedicoRegulador medicoRegulador) {
        do {
            try {
                separador();
                int quantidadeOpcao = HospitalUtil.listaOpcoes("Realizar Transferencia:");
                int opcao = HospitalUtil.getValorInteger();
                if (HospitalUtil.validaOpcao(quantidadeOpcao, opcao)) {
                    switch (opcao) {
                        case 1:
                            solicitacaoController.listarEmAberto();
                            Solicitacao solicitacao = solicitacaoController.getValido();
                            do {
                                try {
                                    setNomeDaClasse("Medico Destino");
                                    listarMedicoOrigemDestino(solicitacao.getMedicoOrigem());
                                    MedicoOrigemDestino medicoDestino = (MedicoOrigemDestino) getValido(MedicoOrigemDestino.class);
                                    validaEntidade(medicoDestino);
                                    medicoRegulador.atualizaSolitacao(solicitacao, medicoDestino);
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
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (true);
    }
//
//    private static Solicitacao getSolicitacaoValida() {
//        do {
//            try {
//                mostrarSolicitacoes(false);
//                separador();
//                int id = HospitalUtil.getValorInteger("Selecione o id da solicitacao: ");
//                return getSolicitacao(id);
//            } catch (Exception e) {
//                System.out.println(e.getMessage());
//            }
//        } while (true);
//    }

//    /**
//     *
//     * @param mostrarTudo caso true mostra todas solicitacoes, caso false filtra
//     * a solicitacao que esteja em aberto pela data de transferencia
//     */
//    private static void mostrarSolicitacoes(boolean mostrarTudo) {
//        System.out.printf("SOLICITACOES %s\n", !mostrarTudo ? "EM ABERTO" : "");
//        boolean contemSolicitacao = false;
//        for (Solicitacao solicitacao : solicitacoes) {
//            if (mostrarTudo || (!mostrarTudo && solicitacao.getDataTransferencia() == null)) {
//                solicitacao.mostrarDados();
//                contemSolicitacao = true;
//            }
//        }
//        if (!contemSolicitacao) {
//            System.out.printf("Nao existe solicitacao %s\n", !mostrarTudo ? "em aberto." : ".");
//        }
//    }
//
//    private static Solicitacao getSolicitacao(int id) {
//        for (Solicitacao solicitacao : solicitacoes) {
//            if (solicitacao.getId() == id) {
//                return solicitacao;
//            }
//        }
//        throw new RuntimeException(String.format("Nao foi encontrado a solicitacao com o id %d", id));
//    }
}
