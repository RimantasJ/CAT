package vu.lt.persistence;

import vu.lt.entities.Station;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class StationsDAO {

    @Inject
    private EntityManager em;

    public List<Station> loadAll() {
        return em.createNamedQuery("Station.findAll", Station.class).getResultList();
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public void persist(Station station){
        this.em.persist(station);
    }

    public Station findOne(Integer id){
        return em.find(Station.class, id);
    }

    public Station update(Station station){
        return em.merge(station);
    }
}
