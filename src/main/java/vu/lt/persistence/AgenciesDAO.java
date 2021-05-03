package vu.lt.persistence;

import vu.lt.entities.Agency;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class AgenciesDAO {

    @Inject
    private EntityManager em;

    public List<Agency> loadAll() {
        return em.createNamedQuery("Agency.findAll", Agency.class).getResultList();
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public void persist(Agency agency){
        this.em.persist(agency);
    }

    public Agency findOne(Integer id) {
        return em.find(Agency.class, id);
    }

    public Agency update(Agency agency){
        return em.merge(agency);
    }


}
