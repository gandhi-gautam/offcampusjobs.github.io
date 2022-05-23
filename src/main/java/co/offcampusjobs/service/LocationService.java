package co.offcampusjobs.service;

import co.offcampusjobs.model.Location;

import java.util.Map;

public interface LocationService {
    Map<String, Location> getAllLocations();
}
