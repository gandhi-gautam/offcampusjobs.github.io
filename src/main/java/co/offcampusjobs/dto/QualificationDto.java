package co.offcampusjobs.dto;

import co.offcampusjobs.model.Qualification;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QualificationDto {
    private long id;

    private String qualificationName;

    private List<JobDto> jobDtos = new ArrayList<>();

    public QualificationDto(Qualification qualification) {
        this.id = qualification.getId();
        this.qualificationName = qualification.getQualificationName();
    }
}
