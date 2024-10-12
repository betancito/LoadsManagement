package com.riwi.palets.service;

import com.riwi.palets.model.entity.Load;
import com.riwi.palets.model.entity.User;
import com.riwi.palets.repository.LoadRepository;
import com.riwi.palets.repository.PalletRepository;
import com.riwi.palets.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class LoadsService {

    @Autowired
    private LoadRepository loadRepository;

    @Autowired
    private PalletRepository palletRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserRepository userRepository;

    public List<Load> getAllLoads(){
        return loadRepository.findAll();
    }

    public Load getLoadById(Long id){
        return loadRepository.findById(id).get();
    }

    public Load createLoad(Load load, String token){
        System.out.println(load);
        token = token.substring(7);
        //Get user creating using JWT
        String username = jwtService.extractUsername(token);
        User user = userRepository.findByUsername(username);
        load.setCreatedAt(LocalDateTime.now());
        load.setCreatedBy(user.getId());
        load.setUpdatedAt(LocalDateTime.now());
        load.setUpdatedBy(user.getId());

        return loadRepository.save(load);
    }

    public Load updateLoad(Load load, String token){
        token = token.substring(7);
        String username = jwtService.extractUsername(token);
        User user = userRepository.findByUsername(username);
        load.setUpdatedAt(LocalDateTime.now());
        load.setUpdatedBy(user.getId());

        return loadRepository.save(load);
    }
}
