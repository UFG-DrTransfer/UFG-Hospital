package br.ufg.inf.danilloparreira.hosptal.respository.generic;

import br.ufg.inf.danilloparreira.hosptal.model.abstracts.SuperClasse;
import java.util.List;

/**
 *
 * @author Danillo Tomaz Parreira
 */
public interface GenericRepository<T extends SuperClasse> {

    // OPERACAO HOSPITAL
    public int getNextId();

    public List<T> findAll();

    public T findById(int id);

    public void add(T entidade);

    public String getNomeDaClasse();
}
