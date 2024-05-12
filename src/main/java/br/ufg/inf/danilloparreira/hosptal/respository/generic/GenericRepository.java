package br.ufg.inf.danilloparreira.hosptal.respository.generic;

import br.ufg.inf.danilloparreira.hosptal.model.abstracts.SuperClasse;
import java.util.List;

/**
 *
 * @author Danillo Tomaz Parreira
 */
public interface GenericRepository<T extends SuperClasse> {

    // OPERACAO HOSPITAL
    /**
     * Este método tem a finalidade de buscar o proximo ID
     *
     * @return retorna o numero do proximo id com base na quantidade de item da
     * lista
     */
    public int getNextId();

    /**
     * Busca todos itens da lista
     *
     * @return retorna todos itens da lista.
     */
    public List<T> findAll();

    /**
     * Busca a entidade da lista pelo id da SuperClasse
     *
     * @param id a ser consultado
     * @return a entidade com base no id fornecido
     */
    public T findById(int id);

    /**
     * Tenta adicionar a entidade na lista, caso não estiver inserido ele insere
     * e informa que foi adicionado, caso contrário ele mostrará uma mensagem de
     * erro
     *
     * @param entidade a ser inserida.
     */
    public void add(T entidade);

    /**
     * Nome da classe para facilitar na hora de imprimir as mensagem de validacao.
     * @return nome da classe.
     */
    public String getNomeDaClasse();
}
