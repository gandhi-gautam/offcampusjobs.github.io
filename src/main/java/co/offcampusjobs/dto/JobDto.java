package co.offcampusjobs.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JobDto {
    private long jobId;
    private String companyName;
    private String driveType;
    private String profileName;
    private String qualification;
    private String imageUrl;
    private String location;
    private String salary;
    private String experience;
    private String applyLink;
}
