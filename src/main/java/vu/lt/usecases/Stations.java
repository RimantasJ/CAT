package vu.lt.usecases;

import lombok.Getter;
import lombok.Setter;
import vu.lt.entities.Agency;
import vu.lt.entities.Station;
import vu.lt.interceptors.LoggedInvocation;
import vu.lt.persistence.AgenciesDAO;
import vu.lt.persistence.StationsDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Model
public class Stations implements Serializable {


    @Inject
    private AgenciesDAO agenciesDAO;

    @Inject
    private StationsDAO stationsDAO;

    @Getter @Setter
    private Station stationToCreate = new Station();

    @Getter @Setter
    private Integer tempAgencyId;

    @Getter
    private List<Station> allStations;

    @PostConstruct
    public void init() {loadAllStations();}

    @Transactional
    @LoggedInvocation
    public String createStation(){
        stationToCreate.setAgency(agenciesDAO.findOne(tempAgencyId));
        this.stationsDAO.persist(stationToCreate);
        return "stations?faces-redirect=true";
    }

    private void loadAllStations(){
        this.allStations = stationsDAO.loadAll();
    }

}
