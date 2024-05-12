package br.ufg.inf.danilloparreira.hosptal;

import br.ufg.inf.danilloparreira.hosptal.controller.HospitalController;
import br.ufg.inf.danilloparreira.hosptal.controller.MedicoController;
import br.ufg.inf.danilloparreira.hosptal.controller.PacienteController;
import br.ufg.inf.danilloparreira.hosptal.controller.SolicitacaoController;
import br.ufg.inf.danilloparreira.hosptal.enums.Sexo;
import br.ufg.inf.danilloparreira.hosptal.model.Hospital;
import br.ufg.inf.danilloparreira.hosptal.model.MedicoOrigemDestino;
import br.ufg.inf.danilloparreira.hosptal.model.MedicoRegulador;
import br.ufg.inf.danilloparreira.hosptal.model.Paciente;
import br.ufg.inf.danilloparreira.hosptal.model.Solicitacao;
import br.ufg.inf.danilloparreira.hosptal.utils.HospitalUtil;
import static br.ufg.inf.danilloparreira.hosptal.utils.HospitalUtil.opcaoInvalida;
import static br.ufg.inf.danilloparreira.hosptal.utils.HospitalUtil.separador;

/**
 *
 * @author parre
 */
public class Main {

    private static final HospitalController hospitalController = new HospitalController();
    private static final PacienteController pacienteController = new PacienteController();
    private static final SolicitacaoController solicitacaoController = new SolicitacaoController();
    private static final MedicoController medicoController = new MedicoController(pacienteController, solicitacaoController);

    public static void run() {
        System.out.println("INICIO CASDASTRO AUTOMATICO");
        Hospital hc = new Hospital("Hospital das Clinicas", "123", "Jorge", "321");
        hospitalController.adicionar(hc);

        Hospital hugo = new Hospital("Hugo", "456", "Amadeu", "999");
        hospitalController.adicionar(hugo);

        MedicoOrigemDestino medico1 = new MedicoOrigemDestino("Alvaro", "555", "Ortopedista", hc);
        medicoController.adicionar(medico1);
        MedicoOrigemDestino medico2 = new MedicoOrigemDestino("Bruno", "777", "Marceneiro", hugo);
        medicoController.adicionar(medico2);
        MedicoOrigemDestino medico3 = new MedicoOrigemDestino("Tiago", "1234", "Dentista", hugo);
        medicoController.adicionar(medico3);

        MedicoRegulador ze = new MedicoRegulador("Ze do Caixao", "666");
        medicoController.adicionar(ze);

        Paciente paciente1 = new Paciente("Carlos", 104, Sexo.MASCULINO);
        pacienteController.adicionar(paciente1);
        Paciente paciente2 = new Paciente("Ana", 8, Sexo.FEMININO);
        pacienteController.adicionar(paciente2);
        Paciente paciente3 = new Paciente("Josiana", 18, Sexo.FEMININO);
        pacienteController.adicionar(paciente3);

        medicoController.adicionarPaciente(medico2, paciente2);
        Solicitacao solicitacao = medico2.solicitarTransferencia(paciente2, "Pediatria", "documento");
        solicitacaoController.adicionar(solicitacao);
        System.out.println("FIM CASDASTRO AUTOMATICO");
        separador();
    }

    public static void main(String[] args) {
        run();
        separador();
        System.out.println("APOS SELECIONAR UMA OPCAO E POSSIVEL CANCELAR DIGITANDO 0.");
        separador();
        do {
            HospitalUtil.separador();
            int quantidadeOpcao = HospitalUtil.listaOpcoes(true,
                    "  Mostrar lista Hospital",
                    "Mostar lista Medico Responsavel",
                    "   Mostar lista Medico Regulador",
                    "Mostar lista de Paciente",
                    " Mostar Solicitacoes");

            int opcao = HospitalUtil.getValorInteger();
            if (HospitalUtil.validaOpcao(quantidadeOpcao, opcao)) {
                switch (opcao) {
                    case 1:
                        hospitalController.listar();
                        break;
                    case 2:
                        medicoController.operacaoSelecionarMedicoOrigem();
                        break;
                    case 3:
                        medicoController.operacaoMedicoRegulador();
                        break;
                    case 4:
                        pacienteController.listar();
                        break;
                    case 5:
                        solicitacaoController.listar();
                        break;
                    case 0:
                        System.out.println("Saindo do sistema...");
                        return;
                    default:
                        opcaoInvalida();
                }
            }
        } while (true);
    }
}
