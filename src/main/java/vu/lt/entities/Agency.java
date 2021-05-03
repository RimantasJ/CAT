package vu.lt.entities;

import jdk.jfr.Name;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@NamedQueries({
        @NamedQuery(name = "Agency.findAll", query = "select a from Agency as a")
})
@Table(name = "AGENCY")
@Getter @Setter
public class Agency {

    public Agency(){}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    @Size(max = 50)
    @Column(name = "NAME")
    private String name;

    @Size(max = 50)
    @Column(name = "HEADQUARTERS")
    private String headquarters;

    @OneToMany(mappedBy = "agency")
    private List<Station> stations = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Agency agency = (Agency) o;
        return Objects.equals(name, agency.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name);
    }
}
