package br.ufg.inf.danilloparreira.hosptal.model.abstracts;

import br.ufg.inf.danilloparreira.hosptal.model.interfaces.Mostrar;

/**
 *
 * @author Danillo Tomaz Parreira
 */
public abstract class SuperClasse implements Mostrar {

    protected int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
