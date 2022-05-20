package co.offcampusjobs.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QualificationDto {
    private long id;

    private String qualificationName;

    private Set<JobDto> jobDtos;

}
