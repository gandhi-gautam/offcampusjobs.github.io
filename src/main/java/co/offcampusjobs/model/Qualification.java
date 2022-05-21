package co.offcampusjobs.model;

import co.offcampusjobs.dto.QualificationDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Qualification {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "qualification_name")
    private String qualificationName;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "qualifications")
    private List<Job> jobs = new ArrayList<>();

    public Qualification(QualificationDto qualificationDto) {
        this.qualificationName = qualificationDto.getQualificationName();
    }
}
