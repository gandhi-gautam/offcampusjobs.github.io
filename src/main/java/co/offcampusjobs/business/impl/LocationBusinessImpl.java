package co.offcampusjobs.business.impl;

import co.offcampusjobs.business.LocationBusiness;
import co.offcampusjobs.model.Location;
import co.offcampusjobs.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LocationBusinessImpl implements LocationBusiness {
    @Autowired
    private LocationService locationService;


    @Override
    public List<Location> getAllLocations() {
        return locationService.getAllLocations();
    }
}
