package co.offcampusjobs.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.Set;

@Component
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JobDto {
    private long jobId;

    @NotEmpty(message = "Company Name cannot not be empty")
    private String companyName;

    @NotEmpty(message = "Drive Type cannot not be empty")
    private String driveType;

    @NotEmpty(message = "Profile Name cannot not be empty")
    private String profileName;

    @NotEmpty(message = "Qualification cannot not be empty")
    private String qualification;

    @NotEmpty(message = "Image Url cannot not be empty")
    private String imageUrl;

    @NotEmpty(message = "Location cannot not be empty")
    private String location;

    @NotEmpty(message = "Salary cannot not be empty")
    private String salary;

    @NotEmpty(message = "Experience cannot not be empty")
    private String experience;

    @NotEmpty(message = "Apply Link cannot not be empty")
    private String applyLink;

    private LocalDate createdAt;

    private Set<QualificationDto> qualificationDtos;
}
