package br.ufg.inf.danilloparreira.hosptal;

public class Paciente extends SuperClasse implements Mostrar {

    private String nome;
    private int idade;
    private Sexo sexo;

    public Paciente(int id, String nome, int idade, Sexo sexo) {
        super(id);
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
        HospitalUtil.separador();
        HospitalUtil.dadosId(id);
        System.out.printf("Nome: %s\nIdade: %d\nSexo: %s\n", nome, idade, sexo.getDescricao());
    }

}
