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
public class AgencyDetails implements Serializable {

    @Inject
    private AgenciesDAO agenciesDAO;

    @Inject
    private StationsDAO stationsDAO;

    @Getter @Setter
    private Agency agency;

    @Getter @Setter
    private Station stationToCreate = new Station();


    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer agencyId = Integer.parseInt(requestParameters.get("agencyId"));
        this.agency = agenciesDAO.findOne(agencyId);
    }

    @Transactional
    @LoggedInvocation
    public String createStation(){
        stationToCreate.setAgency(this.agency);
        stationsDAO.persist(stationToCreate);
        return "agencyDetails?faces-redirect=true&agencyId=" + this.agency.getId();
    }
}
