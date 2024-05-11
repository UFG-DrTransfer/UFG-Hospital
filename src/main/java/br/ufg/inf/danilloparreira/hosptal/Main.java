package br.ufg.inf.danilloparreira.hosptal;

import static br.ufg.inf.danilloparreira.hosptal.HospitalUtil.separador;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author parre
 */
public class Main {

    private static List<Hospital> hospitais = new ArrayList<>();
    private static List<Solicitacao> solicitacoes = new ArrayList<>();
    private static List<Medico> medicos = new ArrayList<>();
    private static List<Paciente> pacientes = new ArrayList<>();

    public static int getProximoIdHospital() {
        return hospitais.size() + 1;
    }

    public static int getProximoIdSolicitacoes() {
        return solicitacoes.size() + 1;
    }

    public static int getProximoIdMedico() {
        return medicos.size() + 1;
    }

    public static int getProximoIdPaciente() {
        return pacientes.size() + 1;
    }

    public static void dados() {
        Hospital hc = new Hospital(getProximoIdHospital(), "Hospital das Clinicas", "123", "Jorge", "321");
        hospitais.add(hc);
        Hospital hugo = new Hospital(getProximoIdHospital(), "Hugo", "456", "Amadeu", "999");
        hospitais.add(hugo);

        MedicoOrigemDestino medico1 = new MedicoOrigemDestino(getProximoIdMedico(), "Alvaro", "555", "Ortopedista", hc);
        medicos.add(medico1);
        MedicoOrigemDestino medico2 = new MedicoOrigemDestino(getProximoIdMedico(), "Bruno", "777", "Marceneiro", hugo);
        medicos.add(medico2);
        MedicoOrigemDestino medico3 = new MedicoOrigemDestino(getProximoIdMedico(), "Tiago", "1234", "Dentista", hugo);
        medicos.add(medico3);

        MedicoRegulador ze = new MedicoRegulador(getProximoIdMedico(), "Ze do Caixao", "666");
        medicos.add(ze);

        Paciente paciente1 = new Paciente(getProximoIdPaciente(), "Carlos", 104, Sexo.MASCULINO);
        pacientes.add(paciente1);
        Paciente paciente2 = new Paciente(getProximoIdPaciente(), "Ana", 8, Sexo.FEMININO);
        pacientes.add(paciente2);
        Paciente paciente3 = new Paciente(getProximoIdPaciente(), "Josiana", 18, Sexo.FEMININO);
        pacientes.add(paciente3);

        medico1.adicionarPaciente(paciente1);
        medico2.adicionarPaciente(paciente2);

        medico1.solicitarTransferencia(getProximoIdSolicitacoes(), paciente2, "Pediatria", "documento");
    }

    public static String operacoes() {
        return "1 - Mostrar lista Hospital\n"
                + "2 - Mostar lista Medico Responsavel\n"
                + "3 - Mostar lista Medico Regulador\n"
                + "4 - Mostar lista de Paciente\n"
                + "5 - Mostar Solicitacoes\n"
                + "0 - Sair";
    }

    public static void main(String[] args) {
        dados();
        do {
            HospitalUtil.separador();
            System.out.println(operacoes());
            int operacao = HospitalUtil.getValorInteger();
            System.out.println(operacao);
            switch (operacao) {
                case 1:
                    mostrarListaHospital();
                    break;
                case 2:
                    mostrarMedicoResponsavel();
                    break;
                case 3:
                    mostrarMedicoRegulador();
                    break;
                case 4:
                    mostrarPaciente();
                    break;
                case 5:
                    mostrarSolicitacoes(true);
                    break;
                case 0:
                    System.out.println("Saindo do sistema...");
                    return;
                default:
                    operacaoInvalida();
            }
        } while (true);
    }

    private static void operacaoInvalida() {
        System.out.println("Operacao invalida.");
    }

    private static void mostrarHospitaisDisponiveis(MedicoRegulador ze, List<Hospital> hospitais, Hospital hc) {
        List<Hospital> hospitalDisponivel = ze.buscarUnidade(hospitais, hc);
        System.out.println("Hospitais disponiveis: ");
        for (Hospital hospital : hospitalDisponivel) {
            hospital.mostrarDados();
        }
    }

    private static void mostrarListaHospital() {
        HospitalUtil.separador();
        System.out.println("LISTA DE HOSPITAIS");
        for (Hospital hospital : hospitais) {
            hospital.mostrarDados();
        }
    }

    private static void mostrarMedicoResponsavel() {
        HospitalUtil.separador();
        System.out.println("LISTA DE MEDICO RESPONSAVEL");
        for (Medico medico : medicos) {
            if (medico instanceof MedicoOrigemDestino) {
                ((MedicoOrigemDestino) medico).mostrarDados();
            }
        }
        operacaoMedicoResponsavel();
// TODO: operacao de transferencia -> listar os pacientes -> seleciona 1 paciente e informa demais dados.
    }

    public static void operacaoMedicoResponsavel() {
        do {
            try {
                separador();
                System.out.println("Selecione a operacao:\n"
                        + "1 - Selecionar Medico pelo id;\n"
                        + "0 - Voltar ao menu;");
                int operacao = HospitalUtil.getValorInteger();
                switch (operacao) {
                    case 1:
                        MedicoOrigemDestino medico = (MedicoOrigemDestino) getMedicoValido(MedicoOrigemDestino.class);
                        operacaoMedicoResponsavel2(medico);
                        break;
                    case 0:
                        return;
                    default:
                        operacaoInvalida();
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (true);
    }

    public static void operacaoMedicoResponsavel2(MedicoOrigemDestino medico) {
        do {
            try {
                System.out.println("Selecione a operacao:\n"
                        + "1 - Solicitar Transferencia\n"
                        + "0 - Voltar ao menu;");
                int operacao = HospitalUtil.getValorInteger();
                switch (operacao) {
                    case 1:
                        operacaoMedicoResponsavelTransferencia(medico);
                        break;
                    case 0:
                        return;
                    default:
                        operacaoInvalida();
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (true);
    }

    public static void operacaoMedicoResponsavelTransferencia(MedicoOrigemDestino medico) {
        HospitalUtil.separador();
        System.out.println("LISTA DE PACIENTES");
        int count = 1;
        for (Paciente paciente : pacientes) {
            System.out.printf("ID: %d\n", count++);
            paciente.mostrarDados();
        }
        separador();
        System.out.println("SOLICITACAO TRANSFERENCIA");
        Paciente paciente = getPacienteValido(medico);
        String procedimento = HospitalUtil.getValor("Informe o nome do procedimento:");
        String documento = HospitalUtil.getValor("Informe o nome documento:");
        Solicitacao solicitacao = medico.solicitarTransferencia(getProximoIdSolicitacoes(), paciente, procedimento, documento);
        solicitacoes.add(solicitacao);
    }

    public static Medico getMedicoValido(Class classe) {
        do {
            try {
                System.out.println("Selecione o id do medico: ");
                int id = HospitalUtil.getValorInteger();
                return getMedico(id, classe);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (true);
    }

    private static Medico getMedico(int id, Class classe) {
        for (Medico medico : medicos) {
            if (medico.getClass().equals(classe) && medico.getId() == id) {
                return medico;
            }
        }
        throw new RuntimeException(String.format("Nao foi encontrado o medico com o id %d", id));
    }

    public static Paciente getPacienteValido(MedicoOrigemDestino medico) {
        do {
            try {
                int id = HospitalUtil.getValorInteger("Selecione o paciente: ");
                return getPaciente(medico, id);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (true);
    }

    public static Paciente getPaciente(MedicoOrigemDestino medico, int id) {
        for (Paciente paciente : medico.getPacientes()) {
            if (paciente.getId() == id) {
                return paciente;
            }
        }
        throw new RuntimeException(String.format("Nao foi encontrado o paciente com o id %d", id));
    }

    private static void mostrarMedicoRegulador() {
        HospitalUtil.separador();
        System.out.println("LISTA DE MEDICO REGULADOR");
        for (Medico medico : medicos) {
            if (medico instanceof MedicoRegulador) {
                ((MedicoRegulador) medico).mostrarDados();
            }
        }
        operacaoMedicoRegulador();
        // TODO: confirmar a transferencia, selecionar hospital disponivel.
    }

    public static void operacaoMedicoRegulador() {
        do {
            try {
                separador();
                System.out.println("Selecione a operacao:\n"
                        + "1 - Listar Solicitacoes em aberto\n"
                        + "0 - Voltar ao menu;");
                int operacao = HospitalUtil.getValorInteger();
                switch (operacao) {
                    case 1:

                        break;
                    case 0:
                        return;
                    default:
                        operacaoInvalida();
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (true);
    }

    private static void mostrarPaciente() {
        HospitalUtil.separador();
        System.out.println("LISTA DE PACIENTES");
        int count = 1;
        for (Paciente paciente : pacientes) {
            System.out.printf("ID: %d\n", count++);
            paciente.mostrarDados();
        }
    }

    /**
     *
     * @param mostrarTudo caso true mostra todas solicitacoes, caso false filtra
     * a solicitacao que esteja em aberto pela data de transferencia
     */
    private static void mostrarSolicitacoes(boolean mostrarTudo) {
        HospitalUtil.separador();
        System.out.printf("SOLICITACOES %s", !mostrarTudo ? "EM ABERTO" : "");
        boolean contemSolicitacao = false;
        for (Solicitacao solicitacao : solicitacoes) {
            if (mostrarTudo || (!mostrarTudo && solicitacao.getDataTransferencia() == null)) {
                solicitacao.mostrarDados();
                contemSolicitacao = true;
            }
        }
        if (!contemSolicitacao) {
            System.out.printf("Nao existe solicitacao %s", !mostrarTudo ? "em aberto." : ".");
        }
    }

    public static void operacaoMedicoRegulador1() {
        do {
            try {
                separador();
                mostrarSolicitacoes(false);
                System.out.println("Selecione a operacao:\n"
                        + "1 - Selecionar solicitacao;\n"
                        + "0 - Voltar ao menu;");
                int operacao = HospitalUtil.getValorInteger();
                switch (operacao) {
                    case 1:
                        MedicoRegulador medicoRegulador = (MedicoRegulador) getMedicoValido(MedicoRegulador.class);
                        operacaoMedicoReguladorSolicitacao(medicoRegulador);
                        break;
                    case 0:
                        return;
                    default:
                        operacaoInvalida();
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (true);
    }

    public static void operacaoMedicoReguladorSolicitacao(MedicoRegulador medicoRegulador) {
        do {
            try {
                separador();
                mostrarSolicitacoes(false);
                System.out.println("Selecione a operacao:\n"
                        + "1 - Realizar Transferencia;\n"
                        + "0 - Voltar ao menu;");
                int operacao = HospitalUtil.getValorInteger();
                switch (operacao) {
                    case 1:
                        operacaoMedicoReguladorSolicitacao();
                        break;
                    case 0:
                        return;
                    default:
                        operacaoInvalida();
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (true);
    }

}
