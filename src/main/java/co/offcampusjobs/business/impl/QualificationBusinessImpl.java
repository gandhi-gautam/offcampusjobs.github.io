package co.offcampusjobs.business.impl;

import co.offcampusjobs.business.QualificationBusiness;
import co.offcampusjobs.model.Qualification;
import co.offcampusjobs.service.QualificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class QualificationBusinessImpl implements QualificationBusiness {
    @Autowired
    private QualificationService qualificationService;

    /**
     * This method returns all the qualifications
     * @author: Gautam Gandhi
     * @return
     */
    @Override
    public List<Qualification> getAllQualifications() {
        return qualificationService.getAllQualifications();
    }
}
