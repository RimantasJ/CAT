package vu.lt.usecases;

import lombok.Getter;
import lombok.Setter;
import vu.lt.entities.Agency;
import vu.lt.persistence.AgenciesDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Model
public class Agencies {
    @Inject
    private AgenciesDAO agenciesDAO;

    @Getter @Setter
    private Agency agencyToCreate = new Agency();

    @Getter
    private List<Agency> allAgencies;

    @PostConstruct
    public void init() {loadAllAgencies();}

    private Map<String, Integer> allAgenciesNames = new HashMap<String, Integer>();

    public Map<String, Integer> getAllAgenciesNames(){
        for (Agency agency: allAgencies) {
            allAgenciesNames.put(agency.getName(), agency.getId());
        }

        return allAgenciesNames;
    }

    @Transactional
    public String createAgency(){
        this.agenciesDAO.persist(agencyToCreate);
        return "agencies?faces-redirect=true";
    }

    private void loadAllAgencies(){
        this.allAgencies = agenciesDAO.loadAll();
    }

}
