package br.ufg.inf.danilloparreira.hosptal.controller;

import br.ufg.inf.danilloparreira.hosptal.model.abstracts.SuperClasse;
import br.ufg.inf.danilloparreira.hosptal.respository.generic.GenericRepository;
import br.ufg.inf.danilloparreira.hosptal.utils.HospitalUtil;
import static br.ufg.inf.danilloparreira.hosptal.utils.HospitalUtil.getValorInteger;
import static br.ufg.inf.danilloparreira.hosptal.utils.HospitalUtil.listaVazia;
import java.util.List;

public class GenericController<T extends SuperClasse, R extends GenericRepository<T>> {

    protected final R repository;
    private String nomeDaClasse;

    public GenericController(R repository) {
        this.repository = repository;
        this.nomeDaClasse = repository.getNomeDaClasse();
    }

    public GenericController(R repository, String nomeDaClasse) {
        this.repository = repository;
        this.nomeDaClasse = nomeDaClasse;
    }

    public String getNomeDaClasse() {
        return nomeDaClasse;
    }

    public void setNomeDaClasse(String nomeDaClasse) {
        this.nomeDaClasse = nomeDaClasse;
    }

    public List<T> findAll() {
        return repository.findAll();
    }

    /**
     * Lista todos dadas da entidade
     */
    public void listar() {
        if (findAll().isEmpty()) {
            listaVazia();
        } else {
            for (T entidade : findAll()) {
                entidade.mostrarDados();
                HospitalUtil.separador();
            }
        }
    }

    /**
     * Lista todos dados conforme a lista informada
     *
     * @param lista
     */
    public void listar(List<? extends T> lista) {
        if (lista.isEmpty()) {
            listaVazia();
        } else {
            for (T entidade : lista) {
                entidade.mostrarDados();
            }
        }
    }

    /**
     * Lista todos dados da entidade filtrando a classe caso existir
     *
     * @param classe
     */
    public void listar(Class<? extends T> classe) {
        if (findAll().isEmpty()) {
            listaVazia();
        } else {
            for (T entidade : findAll()) {
                if (classe == null || entidade.getClass().equals(classe)) {
                    entidade.mostrarDados();
                    HospitalUtil.separador();
                }
            }
        }
    }

    /**
     *
     * @param entidade
     * @return
     */
    public T getValido() {
        do {
            try {
                int id = getValorInteger(String.format("Selecione o id do(a) %s: ", getNomeDaClasse()));
                if (id == 0) {
                    return null;
                }
                return getEntidade(id);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (true);
    }

    /**
     * Busca entidade pelo ID
     *
     * @param id
     * @return entidade valida ou null caso passar valor 0 como operacao.
     */
    public T getEntidade(int id) {
        return getEntidade(id, findAll());
    }

    public T getValido(List<T> lista) {
        do {
            try {
                int id = getValorInteger(String.format("Selecione o id do(a) %s: ", getNomeDaClasse()));
                if (id == 0) {
                    return null;
                }
                return getEntidade(id, lista);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (true);
    }

    /**
     * Busca entidade pelo id em uma lista Especifica
     *
     * @param id
     * @param lista
     * @return
     */
    protected T getEntidade(int id, List<T> lista) {
        for (T entidade : lista) {
            if (entidade.getId() == id) {
                return entidade;
            }
        }
        throw new RuntimeException(String.format("Nao foi encontrado o(a) %s com o id %d", getNomeDaClasse(), id));
    }

    /**
     * Busca se é valido conforme a classe especificada
     *
     * @param classe
     * @return
     */
    public T getValido(Class<? extends T> classe) {
        do {
            try {
                int id = getValorInteger(String.format("Selecione o id do(a) %s: ", getNomeDaClasse()));
                if (id == 0) {
                    return null;
                }
                return getEntidade(id, classe);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (true);
    }

    /**
     * Busca entidade pelo ID que seja igual a classe fornecida
     *
     * @param id
     * @param classe
     * @return
     */
    protected T getEntidade(int id, Class<? extends T> classe) {
        for (T entidade : findAll()) {
            if (entidade.getClass() == classe && entidade.getId() == id) {
                return entidade;
            }
        }
        throw new RuntimeException(String.format("Nao foi encontrado o(a) %s com o id %d", getNomeDaClasse(), id));
    }

    /**
     * Adiciona a entidade a lista do repository
     *
     * @param entidade
     */
    public void adicionar(T entidade) {
        repository.add(entidade);
    }
}
