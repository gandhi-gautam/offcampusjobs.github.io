package co.offcampusjobs.dto;

import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class Job {
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
    private String imageUrl;
    private String applyLink;
}
