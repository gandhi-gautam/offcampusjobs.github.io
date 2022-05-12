package co.offcampusjobs.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Component
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JobDto {
    private long jobId;

    @NotEmpty(message = "Company Name should not be empty")
    private String companyName;

    @NotEmpty(message = "Company Name should not be empty")
    private String driveType;

    @NotEmpty(message = "Profile Name should not be empty")
    private String profileName;

    @NotEmpty(message = "Qualification should not be empty")
    private String qualification;

    @NotEmpty(message = "Image Url should not be empty")
    private String imageUrl;

    @NotEmpty(message = "Location should not be empty")
    private String location;

    @NotEmpty(message = "Salary should not be empty")
    private String salary;

    @NotEmpty(message = "Experience should not be empty")
    private String experience;

    @NotEmpty(message = "Apply Link should not be empty")
    private String applyLink;
}
