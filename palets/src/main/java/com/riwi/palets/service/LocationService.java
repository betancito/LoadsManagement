package com.riwi.palets.service;

import com.riwi.palets.model.entity.Location;
import com.riwi.palets.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationService {
    @Autowired
    public LocationRepository locationRepository;

    public List<Location> getAll(){
        return locationRepository.findAll();
    }
    public Location saveLocation(Location location){
        return locationRepository.save(location);
    }

    public void deleteLocation(Long id){
        locationRepository.deleteById(id);
    }

    public Location getById (Long id){
        return locationRepository.findById(id).get();
    }

    public Location updateLocation (Location location){
        Location locationUpdated = locationRepository.findById(location.getId()).get();
        locationUpdated.setName(location.getName());
        locationUpdated.setDescription(location.getDescription());
        return locationRepository.save(locationUpdated);
    }
}
