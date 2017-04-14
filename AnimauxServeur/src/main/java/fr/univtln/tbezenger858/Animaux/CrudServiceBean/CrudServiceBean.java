package fr.univtln.tbezenger858.Animaux.CrudServiceBean;

import javax.persistence.*;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Tomy Bezenger on 03/03/2017.
 */
public class CrudServiceBean<T> implements CrudService<T> {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistence");
        EntityManager em = emf.createEntityManager();

        public  T create(T t) {
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            this.em.persist(t);
            this.em.flush();
            tx.commit();
            this.em.refresh(t);
            return t;
        }

        @SuppressWarnings("unchecked")
        public  T find(Class type,Object id) {
            return (T) this.em.find(type, id);
        }

        public void delete(Class type,Object id) {
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            Object ref = this.em.getReference(type, id);
            this.em.remove(ref);
            tx.commit();
        }

        public  T update(T t) {
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            t=this.em.merge(t); //marche pas :/
            tx.commit();
            return t;
        }

        public List findWithNamedQuery(String namedQueryName){
            return this.em.createNamedQuery(namedQueryName).getResultList();
        }

        public List findWithNamedQuery(String namedQueryName, Map parameters){
            return findWithNamedQuery(namedQueryName, parameters, 0);
        }

        public List findWithNamedQuery(String queryName, int resultLimit) {
            return this.em.createNamedQuery(queryName).
                    setMaxResults(resultLimit).
                    getResultList();
        }

        public List findByNativeQuery(String sql, Class type) {
            return this.em.createNativeQuery(sql, type).getResultList();
        }

        public List findWithNamedQuery(String namedQueryName, Map parameters,int resultLimit){
            Set<Map.Entry> rawParameters = parameters.entrySet();
            Query query = this.em.createNamedQuery(namedQueryName);
            if(resultLimit > 0)
                query.setMaxResults(resultLimit);
            for (Map.Entry entry : rawParameters) {
                query.setParameter((String)entry.getKey(), entry.getValue());
            }
            return query.getResultList();
        }

}
