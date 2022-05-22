package co.offcampusjobs.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "location_name")
    private String locationName;

    @ManyToMany(mappedBy = "locations")
    private Set<Job> jobs;

}
