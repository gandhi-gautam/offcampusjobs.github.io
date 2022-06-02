package co.offcampusjobs.business.impl;

import co.offcampusjobs.business.JobBusiness;
import co.offcampusjobs.model.Job;
import co.offcampusjobs.model.Location;
import co.offcampusjobs.model.Qualification;
import co.offcampusjobs.service.JobService;
import co.offcampusjobs.service.LocationService;
import co.offcampusjobs.service.QualificationService;
import co.offcampusjobs.util.JobConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Component
public class JobBusinessImpl implements JobBusiness {
    @Autowired
    private JobService jobService;

    @Autowired
    private QualificationService qualificationService;

    @Autowired
    private LocationService locationService;

    public JobBusinessImpl() {
    }

    /**
     * Scope : [This method saves the job in the DataBase]
     * Author : [Gautam Gandhi]
     * Comment : [refactoring date: 11-05-2022]
     */
    @Override
    public Job saveNewJob(Job job) {
        job.setImageUrl(changeImageURL(job.getImageUrl()));
        if (job.getDriveType().toLowerCase().trim().equals(JobConstant.INTERNSHIP.toLowerCase(Locale.ROOT))) {
            job.setDriveFlag(JobConstant.INTERNSHIP_FLAG);
        } else {
            if (Integer.parseInt(job.getMinExperience()) == 0) {
                job.setDriveFlag(JobConstant.FRESHER_FLAG);
            } else if (Integer.parseInt(job.getMinExperience()) > 0) {
                job.setDriveFlag(JobConstant.EXPERIENCE_FLAG);
            }
        }
        job = extractQualifications(job);
        job = extractLocations(job);
        job.setCreatedAt(LocalDate.now());
        job = jobService.saveJob(job);
        return job;
    }

    private Job extractLocations(Job job) {
        Map<String, Location> locationMap = locationService.getLocations();
        String[] locations = job.getLocation().split(",");
        for (String city : locations) {
            Location location = null;
            if (locationMap.containsKey(city.trim().toLowerCase(Locale.ROOT))) {
                location = locationMap.get(city.trim().toLowerCase(Locale.ROOT));
            } else {
                location = new Location(city.trim().toLowerCase(Locale.ROOT));
            }
            job.getLocations().add(location);
            location.getJobs().add(job);
        }
        return job;
    }

    /**
     * This method extract all the qualifications from string csv format and convert them into individual qualification
     * object that have many to many functionality implemented
     *
     * @param job
     * @return
     */
    private Job extractQualifications(Job job) {
        Map<String, Qualification> qualificationMap = qualificationService.getQualifications();
        String[] qualifications = job.getQualification().split(",");
        for (String courseName : qualifications) {
            Qualification qualification = null;
            if (qualificationMap.containsKey(courseName.trim())) {
                qualification = qualificationMap.get(courseName.trim());
            } else {
                qualification = new Qualification(courseName.trim());
            }
            job.getQualifications().add(qualification);
            qualification.getJobs().add(job);
        }
        return job;
    }

    /**
     * Scope : [This method returns all the off-campus-jobs from database]
     * Author : [Gautam Gandhi]
     * Comment : [refactoring date: 15-05-2022]
     */
    @Override
    public Page<Job> getOffCampusJobs(Pageable pageable) {
        Page<Job> jobs = jobService.getOffCampusJobs(pageable);
        for (Job job : jobs) {
            job.setDriveType(JobConstant.OFFCAMPUSJOBS);
            concatQualifications(job);
        }
        return jobs;
    }

    /**
     * Scope : [This method changes the Old url to new formated url]
     * Author : [Gautam Gandhi]
     * Comment : [refactoring date: 11-05-2022]
     */
    private String changeImageURL(String url) {
        return "https://drive.google.com/uc?export=view&id=" + url.split("/")[5];
    }

    /**
     * Scope : [This method returns a job for a particular Id]
     * Author : [Sarthak Singh]
     * Comment : [refactoring date: 17-05-2022]
     */
    @Override
    public Job getJob(long id) {
        Job job = jobService.getJob(id);
        int driveFlag = job.getDriveFlag();
        if (driveFlag == 0) {
            job.setDriveType(JobConstant.INTERNSHIP);
        } else if (driveFlag == 1) {
            job.setDriveType(JobConstant.FRESHER);
        } else {
            job.setDriveType(JobConstant.EXPERIENCE);
        }
        concatQualifications(job);
        concatLocations(job);
        return job;
    }

    /**
     * This method returns all the jobs with course name qualification requirement
     *
     * @param courseName
     * @param pageable
     * @return
     */
    @Override
    public Page<Job> getJobsByQualificationName(String courseName, Pageable pageable) {
        Page<Job> jobs = jobService.getJobsByQualificationName(courseName, pageable);
        for (Job job : jobs) {
            if (job.getDriveFlag() == 0) {
                job.setDriveType(JobConstant.INTERNSHIP);
            } else if (job.getDriveFlag() == 1) {
                job.setDriveType(JobConstant.FRESHER);
            } else {
                job.setDriveType(JobConstant.EXPERIENCE);
            }
            concatQualifications(job);
        }
        return jobs;
    }

    /**
     * This controller method returns all job in particular location
     *
     * @param city
     * @param pageable
     * @return
     */
    @Override
    public Page<Job> getJobsByLocation(String city, Pageable pageable) {
        Page<Job> jobs = jobService.getJobsByLocation(city, pageable);
        for (Job job : jobs) {
            if (job.getDriveFlag() == 0) {
                job.setDriveType(JobConstant.INTERNSHIP);
            } else if (job.getDriveFlag() == 1) {
                job.setDriveType(JobConstant.FRESHER);
            } else {
                job.setDriveType(JobConstant.EXPERIENCE);
            }
            concatQualifications(job);
        }
        return jobs;
    }

    /**
     * This method returns all the jobs by drive type in pageable format
     *
     * @param driveFlag
     * @param pageable
     * @return
     */
    @Override
    public Page<Job> getAllJobsByDriveFlag(int driveFlag, Pageable pageable) {
        Page<Job> jobs = jobService.getAllJobsByDriveFlag(driveFlag, pageable);
        for (Job job : jobs) {
            if (driveFlag == 0) {
                job.setDriveType(JobConstant.INTERNSHIP);
            } else if (driveFlag == 1) {
                job.setDriveType(JobConstant.FRESHER);
            } else {
                job.setDriveType(JobConstant.EXPERIENCE);
            }
            concatQualifications(job);
        }
        return jobs;
    }

    /**
     * This method takes job and set concat job qualifications
     *
     * @param job
     */
    private void concatLocations(Job job) {
        List<Location> locations = job.getLocations();
        for (Location location : locations) {
            if (job.getLocation() == null) {
                job.setLocation(location.getLocationName());
            } else {
                job.setLocation(job.getLocation() + ", " + location.getLocationName());
            }
        }
    }

    /**
     * This method takes job and set concat job qualifications
     *
     * @param job
     */
    private void concatQualifications(Job job) {
        List<Qualification> qualifications = job.getQualifications();
        for (Qualification qualification : qualifications) {
            if (job.getQualification() == null) {
                job.setQualification(qualification.getQualificationName());
            } else {
                job.setQualification(job.getQualification() + ", " + qualification.getQualificationName());
            }
        }
    }
}
