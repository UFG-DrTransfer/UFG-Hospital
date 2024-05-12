package br.ufg.inf.danilloparreira.hosptal.model.abstracts;

public abstract class SuperClasse {

    protected int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /**
     * Mostra os dados da classe
     */
    public abstract void mostrarDados();
}
