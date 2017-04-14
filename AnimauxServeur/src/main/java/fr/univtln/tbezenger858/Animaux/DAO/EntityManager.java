package fr.univtln.tbezenger858.Animaux.DAO;

import java.util.Collection;
import java.util.List;

/**
 * Created by Tomy Bezenger on 22/02/2017.
 */
public interface EntityManager<T> {
    T find(String id) throws Exception;

    List<T> findall() throws Exception;

    boolean persist(T t) throws Exception;

    boolean remove(int id) throws Exception;

    boolean update(T t) throws Exception;
}
