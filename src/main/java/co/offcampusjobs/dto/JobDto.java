package co.offcampusjobs.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@Getter
@Setter
public class JobDto {
    private long id;
    private String name;
    private String companyName;
    private String profileName;
    private String locations;
    private String qualifications;
    private LocalDate createdAt;
    private LocalDate lastDateToApply;
    private String experience;
    private String salary;
    private String jd;
    private String driveType;
    private int driveFlag;
    private String imageUrl;
    private String applyLink;
}
