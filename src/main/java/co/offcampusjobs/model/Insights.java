package co.offcampusjobs.model;

import javax.persistence.*;

@Entity
public class Insights {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "no_of_clicks")
    private long noOfClicks;

    @OneToOne
    private Job job;
}
