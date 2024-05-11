package br.ufg.inf.danilloparreira.hosptal;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public abstract class HospitalUtil {

    private static final Scanner leitor = new Scanner(System.in);

    public static void separador() {
        System.out.println("--------------------------------------------------");
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
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (true);
    }

    public static String getValor() {
        return getValor(null);
    }

    public static String getValor(String texto) {
        System.out.println(texto == null ? "Informe o valor da operacao:" : texto);
        return leitor.next();
    }

    public static Integer validarInt(String valor) {
        if (valor.matches("\\d+")) {
            return Integer.valueOf(valor);
        } else {
            throw new RuntimeException(String.format("O valor da informado esta invalido, tente novamente!"));
        }
    }

}
