package fr.univtln.tbezenger858.Animaux.CrudServiceBean;

import java.util.List;
import java.util.Map;

/**
 * Created by Tomy Bezenger on 03/03/2017.
 */
public interface CrudService<T> {
    public T create(T t);
    public T find(Class type, Object id);
    public T update(T t);
    public void delete(Class type, Object id);
    public List findWithNamedQuery(String queryName);
    public List findWithNamedQuery(String queryName, int resultLimit);
    public List findWithNamedQuery(String namedQueryName, Map parameters);
    public List findWithNamedQuery(String namedQueryName, Map parameters, int resultLimit);
}
