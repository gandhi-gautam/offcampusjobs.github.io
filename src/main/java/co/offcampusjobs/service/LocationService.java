package co.offcampusjobs.service;

import co.offcampusjobs.model.Location;

import java.util.List;
import java.util.Map;

public interface LocationService {
    Map<String, Location> getLocations();

    List<Location> getAllLocations();
}
