package co.offcampusjobs.service.impl;

import co.offcampusjobs.model.Qualification;
import co.offcampusjobs.repository.QualificationRepository;
import co.offcampusjobs.service.QualificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class QualificationServiceImpl implements QualificationService {
    @Autowired
    private QualificationRepository qualificationRepository;
    @Override
    public Map<String, Qualification> getQualifications() {
        List<Qualification> qualifications =  qualificationRepository.findAll();
        Map<String, Qualification> qualificationMap = new HashMap<>();
        for(Qualification qualification : qualifications){
            qualificationMap.put(qualification.getQualificationName(), qualification);
        }
        return qualificationMap;
    }

    /**
     * This method returns all the qualifications
     * @author: Gautam Gandhi
     * @return
     */
    @Override
    public List<Qualification> getAllQualifications() {
        return qualificationRepository.findAll();
    }
}
