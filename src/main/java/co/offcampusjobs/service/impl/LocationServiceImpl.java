package co.offcampusjobs.service.impl;

import co.offcampusjobs.model.Location;
import co.offcampusjobs.repository.LocationRepository;
import co.offcampusjobs.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LocationServiceImpl implements LocationService {

    @Autowired
    private LocationRepository locationRepository;

    @Override
    public Map<String, Location> getLocations() {
        List<Location> locations = locationRepository.findAll();
        Map<String, Location> locationMap = new HashMap<>();
        for(Location location : locations){
            locationMap.put(location.getLocationName(), location);
        }
        return locationMap;
    }

    @Override
    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }
}
