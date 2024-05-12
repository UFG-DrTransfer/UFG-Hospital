package br.ufg.inf.danilloparreira.hosptal.model;

import br.ufg.inf.danilloparreira.hosptal.model.abstracts.SuperClasse;
import br.ufg.inf.danilloparreira.hosptal.enums.Sexo;
import br.ufg.inf.danilloparreira.hosptal.utils.HospitalUtil;

public class Paciente extends SuperClasse {

    private String nome;
    private int idade;
    private Sexo sexo;

    public Paciente(String nome, int idade, Sexo sexo) {
        this.nome = nome;
        setIdade(idade);
        this.sexo = sexo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    /**
     * atualiza a idade do paciente
     *
     * @param idade tem que ser entre 0 e 130 anos
     * @throws RuntimeException gera mensagem de erro caso idade for diferente
     * da especificada
     */
    public void setIdade(int idade) {
        if (idade < 0 || idade > 130) {
            throw new RuntimeException("Idade nao pode ser menor que zero ou maior que 130.");
        }
        this.idade = idade;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    @Override
    public void mostrarDados() {
        HospitalUtil.dadosId(id);
        System.out.printf("Nome: %s\nIdade: %d\nSexo: %s\n", nome, idade, sexo.getDescricao());
    }

}
