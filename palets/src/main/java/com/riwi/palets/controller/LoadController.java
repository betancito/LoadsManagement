package com.riwi.palets.controller;

import com.riwi.palets.model.entity.Load;
import com.riwi.palets.model.enums.LoadStatus;
import com.riwi.palets.service.LoadsService;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/loads")
public class LoadController {

    @Autowired
    private LoadsService loadsService;

    @GetMapping("/{id}")
    public Load getById(Long id){
        return loadsService.getLoadById(id);
    }

    @GetMapping
    public List<Load> getAllLoads(){
        List<Load> loads = loadsService.getAllLoads();
        return loads;
    }

    @PostMapping
    public Load createLoad(@Parameter(name = "Load id") Long id,
                           @Parameter(name = "Load name") String loadName,
                           @Parameter(name = "Load description")String description,
                           @Parameter(name = "Load weight") Long weight,
                           @Parameter(hidden = true) @RequestHeader("Authorization") String token) {
        Load load = new Load();
        load.setId(id);
        load.setName(loadName);
        load.setDescription(description);
        load.setStatus(LoadStatus.PENDING);
        load.setWeight(weight);

        return loadsService.createLoad(load, token);
    }

    @PutMapping
    public Load updateLoad(@Parameter(name = "Load id") Long id,
                           @Parameter(name = "Load name") String loadName,
                           @Parameter(name = "Load description")String description,
                           @Parameter(name = "Load weight") Long weight,
                           @Parameter(name = "Load new status") LoadStatus status,
                           @Parameter(hidden = true) @RequestHeader("Authorization") String token) {
        Load load = new Load();
        load.setId(id);
        load.setName(loadName);
        load.setDescription(description);
        load.setStatus(status);
        load.setWeight(weight);

        return loadsService.updateLoad(load, token);
    }
}
