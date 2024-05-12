package br.ufg.inf.danilloparreira.hosptal.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public abstract class HospitalUtil {

    private static final Scanner leitor = new Scanner(System.in);

    public static void separador() {
        System.out.println("--------------------------------------------------");
    }

    /**
     * Lista todas as opcoes passada mais a opcao 0 sair/voltar menu
     *
     * @param menuPrincipal
     * @param opcoes
     * @return quantidade de opcoes disponiveis
     */
    public static int listaOpcoes(String... opcoes) {
        return listaOpcoes(false, opcoes);
    }

    /**
     * Lista todas as opcoes passada mais a opcao 0 sair quando menuPrincipal
     * for false e voltar menu quando menuPrincipal for true
     *
     * @param menuPrincipal
     * @param opcoes
     * @return quantidade de opcoes disponiveis
     */
    public static int listaOpcoes(boolean menuPrincipal, String... opcoes) {
        StringBuilder sb = new StringBuilder();
        sb.append("Selecione uma Opcao").append("\n");
        int count = 0;
        for (String opcao : opcoes) {
            if (!opcao.trim().isEmpty()) {
                sb.append(++count).append(" - ").append(opcao.trim()).append("\n");
            }
        }
        sb.append(menuPrincipal ? "0 - Sair" : "0 - Voltar ao menu");
        System.out.println(sb.toString());
        return count;
    }

    /**
     * Valida se a opcao selecionada é valida
     *
     * @param quantidadeOpcao quantidade de opcao disponivel
     * @param opcao numero da opcao
     * @return true caso opcao estiver entre 0 e a quantidadeOpcao
     */
    public static boolean validaOpcao(int quantidadeOpcao, int opcao) {
        if (opcao >= 0 && opcao <= quantidadeOpcao) {
            return true;
        }
        HospitalUtil.opcaoInvalida();
        return false;
    }

    public static void opcaoInvalida() {
        System.out.println("Opcao invalida.");
    }

    public static void validaEntidade(Object object) throws HospitalException {
        if (object == null) {
            throw new HospitalException("Operacao cancelada pelo usuario.");
        }
    }

    public static void cadastradoComSucesso(String nomeClasse) {
        System.out.printf("%s cadastrado com sucesso!\n", nomeClasse);
    }

    public static void duplicado(String nomeClasse) {
        System.out.printf("%s ja esta cadastrado. Operacao cancelada!\n", nomeClasse);
    }

    public static void listaVazia() {
        System.out.println("Nenhum registro encontrado!");
    }

    public static void dadosId(int id) {
        System.out.printf("ID: %d\n", id);
    }

    public static String dataFormatada(LocalDateTime data) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"); // Define o formato desejado
        return data.format(formatter);
    }

    public static Integer getValorInteger() {
        return getValorInteger(null);
    }

    public static Integer getValorInteger(String texto) {
        do {
            try {
                String valor = getValor(texto);
                return validarInt(valor);
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }
        } while (true);
    }

    public static String getValor(String texto) {
        System.out.println(texto == null ? "Informe o valor da opcao:" : texto);
        return leitor.next();
    }

    public static String getValor() {
        return getValor(null);
    }

    public static Integer validarInt(String valor) {
        if (valor.matches("\\d+")) {
            return Integer.valueOf(valor);
        } else {
            throw new RuntimeException(String.format("O valor da informado esta invalido, tente novamente!"));
        }
    }

}
