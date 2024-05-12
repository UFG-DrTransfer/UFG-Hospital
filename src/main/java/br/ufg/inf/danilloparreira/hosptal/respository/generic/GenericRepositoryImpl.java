package br.ufg.inf.danilloparreira.hosptal.respository.generic;

import br.ufg.inf.danilloparreira.hosptal.utils.HospitalUtil;
import br.ufg.inf.danilloparreira.hosptal.model.abstracts.SuperClasse;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Danillo Tomaz Parreira
 * @param <T>
 */
public class GenericRepositoryImpl<T extends SuperClasse> implements GenericRepository<T> {

    private final List<T> lista;
    private final String nomeDaClasse;

    public GenericRepositoryImpl(String nomeDaClasse) {
        this.nomeDaClasse = nomeDaClasse;
        lista = new ArrayList<>();
    }

    @Override
    public int getNextId() {
        return lista.size() + 1;
    }

    @Override
    public List<T> findAll() {
        return lista;
    }

    @Override
    public void add(T entidade) {
        try {
            if (lista.contains(entidade)) {
                HospitalUtil.duplicado(nomeDaClasse);
                return;
            }
            entidade.setId(getNextId());
            lista.add(entidade);
            //TODO: fazer validacao se contem mesmo id, caso contrário atualiza o id para o proximo.
            HospitalUtil.cadastradoComSucesso(nomeDaClasse);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public T findById(int id) {
        for (T entidade : lista) {
            if (entidade.getId() == id) {
                return entidade;
            }
        }
        return null;
    }

    @Override
    public String getNomeDaClasse() {
        return nomeDaClasse;
    }
}
