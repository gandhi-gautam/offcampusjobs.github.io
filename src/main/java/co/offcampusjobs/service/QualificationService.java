package co.offcampusjobs.service;

import co.offcampusjobs.model.Qualification;

import java.util.Map;

public interface QualificationService {
    Map<String, Qualification> getAllQualifications();
}
