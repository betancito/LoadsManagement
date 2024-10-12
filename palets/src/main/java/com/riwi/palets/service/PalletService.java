package com.riwi.palets.service;

import com.riwi.palets.model.entity.Location;
import com.riwi.palets.model.entity.Pallet;
import com.riwi.palets.model.entity.User;
import com.riwi.palets.repository.PalletRepository;
import com.riwi.palets.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
public class PalletService {
    @Autowired
    private LocationService locationService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PalletRepository palletRepository;

    @Autowired
    private JwtService jwtService;

    public List<Pallet> getAllPallets(){
        List<Pallet> pallets = palletRepository.findAll();
        return pallets;
    }

    public Pallet getPalletById(Long id){
            return palletRepository.findById(id).get();
    }

    public Pallet createPallet (Pallet pallet, Long locationId, String token){
        String username = jwtService.extractUsername(token);
        User user = userRepository.findByUsername(username);
        Location location = locationService.getById(locationId);
        pallet.setLocation(location);
        pallet.setCreatedBy(user.getId());
        pallet.setUpdatedBy(user.getId());
        pallet.setCreatedAt(LocalDateTime.now());
        pallet.setUpdatedAt(LocalDateTime.now());
        return palletRepository.save(pallet);
    }

    public Pallet updatePallet(Pallet pallet,Long id, String token){
        String username = jwtService.extractUsername(token);
        User user = userRepository.findByUsername(username);
        Location location = locationService.getById(id);
        pallet.setLocation(location);
        pallet.setUpdatedAt(LocalDateTime.now());
        pallet.setUpdatedBy(user.getId());
        return palletRepository.save(pallet);
    }

    public void deletePallet(Long id){
        palletRepository.deleteById(id);
    }
}
