package com.riwi.palets.controller;

import com.riwi.palets.model.entity.Location;
import com.riwi.palets.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/location")
public class LocationController {
    @Autowired
    LocationService locationService;

    @PostMapping
    public Location createLocation(@RequestBody Location location){
        return locationService.saveLocation(location);
    }

    @PutMapping
    public Location updateLocation(@RequestBody Location location){
        return locationService.updateLocation(location);
    }

    @DeleteMapping("/{id}")
    public void deleteLocation(@PathVariable Long id){
        locationService.deleteLocation(id);
    }

    @GetMapping("/{id}")
    public Location getLocationById(@PathVariable Long id){
        return locationService.getById(id);
    }

    @GetMapping
    public List<Location> getAllLocations(){
        List<Location> locations = locationService.getAll();
        return locations;
    }
}
