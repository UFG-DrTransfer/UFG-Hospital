package br.ufg.inf.danilloparreira.hosptal;

public abstract class Medico extends SuperClasse {

    private String nome;
    private String crm;

    public Medico(int id, String nome, String crm) {
        super(id);
        this.nome = nome;
        this.crm = crm;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

}
